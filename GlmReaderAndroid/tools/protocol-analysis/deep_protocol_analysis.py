#!/usr/bin/env python3
"""
Глубокий анализ HCI лога:
1. Временная шкала замеров
2. Паттерны команд для разных типов измерений
3. Сопоставление с PDF по времени
"""

import struct


def parse_btsnoop(filepath):
    """Парсит btsnoop файл с временными метками."""
    packets = []
    with open(filepath, "rb") as f:
        header = f.read(24)
        # btsnoop v1: timestamp в микросекундах от 1 января 0000
        # Упрощённо: считаем от первого пакета

        first_ts = None
        pkt_num = 0

        while True:
            rec_header = f.read(24)
            if len(rec_header) < 24:
                break

            orig_len = struct.unpack(">I", rec_header[0:4])[0]
            incl_len = struct.unpack(">I", rec_header[4:8])[0]
            flags = struct.unpack(">I", rec_header[8:12])[0]
            ts_high = struct.unpack(">I", rec_header[16:20])[0]
            ts_low = struct.unpack(">I", rec_header[20:24])[0]

            # Timestamp в микросекундах от 1 января 0000
            # Преобразуем в секунды от epoch
            # btsnoop использует offset от 1 января 0000
            # Для простоты: считаем дельту от первого пакета
            ts_microseconds = (ts_high << 32) | ts_low

            if first_ts is None:
                first_ts = ts_microseconds

            delta_ms = (ts_microseconds - first_ts) / 1000  # миллисекунды от начала

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
                    "delta_ms": delta_ms,
                    "data": data,
                }
            )

    return packets


def analyze_timeline(packets):
    """Анализирует временную шкалу активности."""
    print("=" * 100)
    print("ВРЕМЕННАЯ ШКАЛА HCI ЛОГА:")
    print("=" * 100)

    # Группируем по секундам
    second_buckets = {}
    for pkt in packets:
        sec = int(pkt["delta_ms"] / 1000)
        if sec not in second_buckets:
            second_buckets[sec] = {
                "count": 0,
                "ble_notify": 0,
                "rfcomm_response": 0,
                "commands": [],
            }
        second_buckets[sec]["count"] += 1

        data = pkt["data"]
        # BLE_NOTIFY
        for i in range(len(data) - 10):
            if data[i : i + 4] == b"\xc0\x55\x10\x06":
                dist_m = struct.unpack("<f", data[i + 7 : i + 11])[0]
                dist_mm = round(dist_m * 1000, 1)
                second_buckets[sec]["ble_notify"] += 1
                second_buckets[sec]["commands"].append(f"BLE:{dist_mm}")

        # RFCOMM response
        for i in range(len(data) - 3):
            if data[i] == 0x00 and data[i + 1] == 0x04:
                dist = struct.unpack("<H", data[i + 2 : i + 4])[0]
                second_buckets[sec]["rfcomm_response"] += 1
                if dist > 100:  # только значимые расстояния
                    second_buckets[sec]["commands"].append(f"RFCOMM:{dist}")

    # Показываем секунды с активностью
    for sec in sorted(second_buckets.keys()):
        bucket = second_buckets[sec]
        cmds = bucket["commands"]
        print(
            f"  t+{sec:4.0f}s: {bucket['count']:3d} пакетов, "
            f"BLE={bucket['ble_notify']}, RFCOMM={bucket['rfcomm_response']}, "
            f"команды: {', '.join(cmds[:5])}{'...' if len(cmds) > 5 else ''}"
        )


def find_command_patterns(packets):
    """Ищет паттерны команд C0 XX XX XX."""
    print("\n" + "=" * 100)
    print("ПАТТЕРНЫ КОМАНД C0 XX XX XX:")
    print("=" * 100)

    cmd_sequences = []  # последовательности команд
    current_seq = []

    for pkt in packets:
        data = pkt["data"]
        direction = "→" if pkt["is_sent"] else "←"

        for i in range(len(data) - 3):
            if data[i] == 0xC0:
                cmd = data[i : i + 4]
                cmd_hex = cmd.hex(" ")

                # Определяем тип
                if cmd[:4] == b"\xc0\x55\x10\x06":
                    dist_m = (
                        struct.unpack("<f", data[i + 7 : i + 11])[0]
                        if len(data) > i + 10
                        else 0
                    )
                    cmd_type = f"BLE_NOTIFY({dist_m*1000:.1f}mm)"
                elif cmd[:4] == b"\xc0\x55\x02\x01":
                    cmd_type = "BLE_INIT_1"
                elif cmd[:4] == b"\xc0\x55\x02\xf1":
                    cmd_type = "BLE_INIT_2"
                elif cmd[:2] == b"\xc0\x56":
                    cmd_type = "BLE_CMD_56"
                elif cmd[:2] == b"\xc0\x40":
                    cmd_type = "MEASURE"
                elif cmd[:2] == b"\xc0\x41":
                    cmd_type = "LASER_ON"
                elif cmd[:2] == b"\xc0\x42":
                    cmd_type = "LASER_OFF"
                else:
                    cmd_type = f"UNKNOWN({cmd_hex})"

                current_seq.append(
                    {
                        "pkt": pkt["num"],
                        "dir": direction,
                        "cmd": cmd_hex,
                        "type": cmd_type,
                        "delta_ms": pkt["delta_ms"],
                    }
                )

    # Группируем по типам
    type_counts = {}
    for item in current_seq:
        t = item["type"]
        if t not in type_counts:
            type_counts[t] = []
        type_counts[t].append(item)

    print(f"\nВсего команд C0: {len(current_seq)}")
    print(f"Уникальных типов: {len(type_counts)}")
    print()

    for t, items in sorted(type_counts.items(), key=lambda x: -len(x[1])):
        print(f"  {t:30s}: {len(items):4d} раз")
        if len(items) <= 5:
            for item in items:
                print(
                    f"    #{item['pkt']:4d} {item['dir']} {item['cmd']}  t+{item['delta_ms']/1000:.1f}s"
                )
        else:
            for item in items[:3]:
                print(
                    f"    #{item['pkt']:4d} {item['dir']} {item['cmd']}  t+{item['delta_ms']/1000:.1f}s"
                )
            print(f"    ... ещё {len(items) - 3}")


def correlate_with_pdf(packets):
    """Сопоставляет HCI замеры с PDF данными."""
    print("\n" + "=" * 100)
    print("КОРРЕЛЯЦИЯ HCI ↔ PDF:")
    print("=" * 100)

    # PDF замеры (из TXT файла, который мы уже парсили)
    pdf_distances = [
        # Последние 4 замера — 2078.x mm в 14:23
        {"value": 2078.3, "time": "14:23"},
        {"value": 2078.4, "time": "14:23"},
        {"value": 2078.3, "time": "14:23"},
        {"value": 2078.2, "time": "14:23"},
        # Непрерывный
        {"value": 955.6, "time": "14:25"},
        {"value": 955.0, "time": "14:25"},
        # Косвенная длина
        {"value": 941.9, "time": "14:24"},
        # Двойная косвенная
        {"value": 482.3, "time": "14:25"},
        {"value": 782.7, "time": "14:25"},
    ]

    # Извлекаем BLE_NOTIFY из HCI
    ble_distances = []
    for pkt in packets:
        data = pkt["data"]
        for i in range(len(data) - 10):
            if data[i : i + 4] == b"\xc0\x55\x10\x06":
                dist_m = struct.unpack("<f", data[i + 7 : i + 11])[0]
                dist_mm = round(dist_m * 1000, 1)
                ble_distances.append(
                    {
                        "pkt": pkt["num"],
                        "value": dist_mm,
                        "delta_s": pkt["delta_ms"] / 1000,
                    }
                )

    print(f"\nBLE_NOTIFY замеров в HCI: {len(ble_distances)}")
    print(f"PDF замеров (длины): {len(pdf_distances)}")
    print()

    # Ищем совпадения
    for pdf_m in pdf_distances:
        pdf_val = pdf_m["value"]
        matched = [b for b in ble_distances if abs(b["value"] - pdf_val) <= 0.5]
        if matched:
            for m in matched:
                print(
                    f"  ✓ PDF {pdf_val:8.1f} mm (время {pdf_m['time']}) ← HCI пакет #{m['pkt']} (t+{m['delta_s']:.1f}s)"
                )
        else:
            # Ищем ближайшее
            closest = (
                min(ble_distances, key=lambda x: abs(x["value"] - pdf_val))
                if ble_distances
                else None
            )
            if closest:
                print(
                    f"  ✗ PDF {pdf_val:8.1f} mm (время {pdf_m['time']}) ← ближайшее HCI {closest['value']:8.1f} mm (пакет #{closest['pkt']})"
                )


def main():
    hci_file = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_hci.log"

    packets = parse_btsnoop(hci_file)
    print(f"Всего пакетов: {len(packets)}")
    print(f"Длительность записи: {packets[-1]['delta_ms']/1000:.0f} секунд")
    print()

    # 1. Временная шкала
    analyze_timeline(packets)

    # 2. Паттерны команд
    find_command_patterns(packets)

    # 3. Корреляция с PDF
    correlate_with_pdf(packets)


if __name__ == "__main__":
    main()
