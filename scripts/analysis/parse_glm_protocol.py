#!/usr/bin/env python3
"""
Глубокий анализ RFCOMM трафика Bosch GLM 50C из HCI лога.
Ищет команды C0 40 00 EE и ответы рулетки.
"""

import struct


def parse_btsnoop(filepath):
    """Парсит btsnoop файл."""
    packets = []
    with open(filepath, "rb") as f:
        header = f.read(24)
        if header[:8] != b"btsnoop\x00":
            print("Ошибка: не btsnoop файл")
            return packets

        pkt_num = 0
        while True:
            rec_header = f.read(24)
            if len(rec_header) < 24:
                break

            orig_len = struct.unpack(">I", rec_header[0:4])[0]
            incl_len = struct.unpack(">I", rec_header[4:8])[0]
            flags = struct.unpack(">I", rec_header[8:12])[0]
            # flags: bit 0 = sent (1) / received (0), bit 1 = start of fragment

            data = f.read(incl_len)
            if len(data) < incl_len:
                break
            pkt_num += 1

            packets.append(
                {
                    "num": pkt_num,
                    "incl_len": incl_len,
                    "flags": flags,
                    "is_sent": bool(flags & 0x01),
                    "data": data,
                }
            )

    return packets


def find_glm_commands(packets):
    """Ищет команды GLM в RFCOMM данных."""
    # Команды GLM
    GLM_CMDS = {
        b"\xc0\x40\x00\xee": "MEASURE",
        b"\xc0\x41\x00\x96": "LASER_ON",
        b"\xc0\x42\x00\x1e": "LASER_OFF",
        b"\xc0\x47\x00\x20": "BACKLIGHT_ON",
        b"\xc0\x48\x00\x62": "BACKLIGHT_OFF",
        b"\xc0\x55": "BLE_NOTIFY_PREFIX",
    }

    # Ответы GLM
    GLM_RESPONSES = {
        0x00: "OK",
        0x01: "TIMEOUT",
        0x03: "CHECKSUM_ERROR",
        0x04: "UNKNOWN_CMD",
        0x05: "INVALID_ACCESS",
        0x08: "HARDWARE_ERROR",
        0x0A: "DEVICE_NOT_READY",
    }

    results = []

    for pkt in packets:
        data = pkt["data"]
        direction = "→" if pkt["is_sent"] else "←"

        # Ищем команды GLM (начинаются с C0)
        for i in range(len(data) - 3):
            # Команда 4 байта: C0 XX XX XX
            if data[i] == 0xC0:
                cmd_bytes = data[i : i + 4]
                cmd_hex = cmd_bytes.hex(" ")

                # Определяем тип команды
                cmd_name = "UNKNOWN"
                for prefix, name in GLM_CMDS.items():
                    if cmd_bytes.startswith(prefix):
                        cmd_name = name
                        break

                # Для BLE notify — парсим данные
                if cmd_name == "BLE_NOTIFY_PREFIX" and len(data) > i + 10:
                    # C0 55 10 06 ... float32
                    if data[i + 2 : i + 4] == b"\x10\x06" and len(data) > i + 10:
                        distance_m = struct.unpack("<f", data[i + 7 : i + 11])[0]
                        distance_mm = round(distance_m * 1000, 1)
                        results.append(
                            {
                                "pkt": pkt["num"],
                                "dir": direction,
                                "cmd": cmd_name,
                                "hex": cmd_hex,
                                "value": f"{distance_mm} mm",
                                "raw": data[i : i + 15].hex(" "),
                            }
                        )
                        continue

                results.append(
                    {
                        "pkt": pkt["num"],
                        "dir": direction,
                        "cmd": cmd_name,
                        "hex": cmd_hex,
                        "value": "",
                        "raw": data[i : i + 20].hex(" "),
                    }
                )

            # Ответ рулетки: 00 04 XX XX (status=0, len=4, distance)
            if data[i] == 0x00 and i + 3 < len(data) and data[i + 1] == 0x04:
                distance = struct.unpack("<H", data[i + 2 : i + 4])[0]
                status_name = GLM_RESPONSES.get(data[i], "UNKNOWN")
                results.append(
                    {
                        "pkt": pkt["num"],
                        "dir": direction,
                        "cmd": f"RESPONSE ({status_name})",
                        "hex": data[i : i + 4].hex(" "),
                        "value": f"{distance} mm",
                        "raw": data[i : i + 10].hex(" "),
                    }
                )

    return results


def main():
    filepath = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_hci.log"

    print(f"Парсим: {filepath}")
    print()

    packets = parse_btsnoop(filepath)
    print(f"Всего пакетов: {len(packets)}")

    # Фильтруем только RFCOMM/ACL пакеты
    rfcomm_packets = []
    for pkt in packets:
        data = pkt["data"]
        # HCI ACL Data: starts with 0x02
        if len(data) > 4 and data[0] == 0x02:
            rfcomm_packets.append(pkt)

    print(f"ACL/RFCOMM пакетов: {len(rfcomm_packets)}")
    print()

    # Ищем команды GLM
    glm_results = find_glm_commands(rfcomm_packets)

    print(f"Найдено GLM команд/ответов: {len(glm_results)}")
    print()

    # Группируем по типу
    commands = [r for r in glm_results if r["dir"] == "→"]
    responses = [r for r in glm_results if r["dir"] == "←"]

    print(f"Команд (→): {len(commands)}")
    print(f"Ответов (←): {len(responses)}")
    print()

    # Показываем все команды
    print("=" * 100)
    print("КОМАНДЫ GLM:")
    print("=" * 100)

    cmd_counts = {}
    for r in glm_results:
        cmd = r["cmd"]
        cmd_counts[cmd] = cmd_counts.get(cmd, 0) + 1

    for cmd, count in sorted(cmd_counts.items(), key=lambda x: -x[1]):
        print(f"  {cmd}: {count}")

    print()
    print("=" * 100)
    print("ДЕТАЛЬНЫЙ ЛОГ (первые 100 записей):")
    print("=" * 100)

    for r in glm_results[:100]:
        value_str = f" = {r['value']}" if r["value"] else ""
        print(f"  #{r['pkt']:4d} {r['dir']} {r['cmd']:25s} {r['hex']:20s}{value_str}")

    if len(glm_results) > 100:
        print(f"  ... ещё {len(glm_results) - 100} записей")

    # Ищем паттерны в сырых данных
    print()
    print("=" * 100)
    print("ПОИСК ПАТТЕРНОВ C0 XX XX XX:")
    print("=" * 100)

    all_c0_cmds = set()
    for pkt in rfcomm_packets:
        data = pkt["data"]
        for i in range(len(data) - 3):
            if data[i] == 0xC0:
                cmd = data[i : i + 4].hex(" ")
                all_c0_cmds.add(cmd)

    for cmd in sorted(all_c0_cmds):
        print(f"  {cmd}")


if __name__ == "__main__":
    main()
