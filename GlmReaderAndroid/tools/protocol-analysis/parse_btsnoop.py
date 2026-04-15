#!/usr/bin/env python3
"""
Парсер btsnoop HCI лога для анализа BLE трафика Bosch GLM 50C.
Ищет пакеты с UUID 02A6C0D1 и парсит данные измерений.
"""

import struct


def parse_btsnoop(filepath):
    """Парсит btsnoop файл и возвращает список пакетов."""
    packets = []

    with open(filepath, "rb") as f:
        # Заголовок btsnoop
        header = f.read(24)
        if header[:8] != b"btsnoop\x00":
            print("Ошибка: не btsnoop файл")
            return packets

        version = struct.unpack(">I", header[8:12])[0]
        link_type = struct.unpack(">I", header[12:16])[0]
        print(f"Версия btsnoop: {version}")
        print(f"Тип линка: {link_type} (1=HCI UART, 1001=BLE)")
        print()

        # Читаем пакеты
        pkt_num = 0
        while True:
            rec_header = f.read(24)
            if len(rec_header) < 24:
                break

            # Формат записи: original_length(4) included_length(4) flags(4) drops(4)
            # + timestamp_microseconds(8)
            orig_len = struct.unpack(">I", rec_header[0:4])[0]
            incl_len = struct.unpack(">I", rec_header[4:8])[0]
            flags = struct.unpack(">I", rec_header[8:12])[0]
            drops = struct.unpack(">I", rec_header[12:16])[0]
            # timestamp: 8 bytes (high + low)
            # ts_high = struct.unpack('>I', rec_header[16:20])[0]
            # ts_low = struct.unpack('>I', rec_header[20:24])[0]

            data = f.read(incl_len)
            if len(data) < incl_len:
                break
            pkt_num += 1

            packets.append(
                {
                    "num": pkt_num,
                    "orig_len": orig_len,
                    "incl_len": incl_len,
                    "flags": flags,
                    "data": data,
                }
            )

    return packets


def find_ble_uuid(packets, target_uuid_hex="02a6c0d1"):
    """Ищем пакеты содержащие UUID характеристики GLM."""
    target = bytes.fromhex(target_uuid_hex)
    # UUID в BLE передаётся в little-endian
    target_le = target[::-1]  # d1c0a602...

    results = []
    for pkt in packets:
        data = pkt["data"]
        # Ищем UUID в данных (оба порядка байт)
        if target in data or target_le in data:
            results.append(pkt)

    return results


def parse_att_data(data):
    """Парсит ATT пакет и извлекает данные."""
    if len(data) < 4:
        return None

    # Ищем начало ATT пакета (opcode 0x1B = Handle Value Notification, 0x13 = Write Response)
    for i in range(len(data) - 3):
        # Handle Value Notification: opcode 0x1B, handle (2 bytes), value...
        if data[i] == 0x1B and i + 3 < len(data):
            handle = struct.unpack("<H", data[i + 1 : i + 3])[0]
            value = data[i + 3 :]
            return {"type": "notify", "handle": handle, "value": value}

        # Handle Value Indication: opcode 0x1D
        if data[i] == 0x1D and i + 3 < len(data):
            handle = struct.unpack("<H", data[i + 1 : i + 3])[0]
            value = data[i + 3 :]
            return {"type": "indicate", "handle": handle, "value": value}

        # Write Request: opcode 0x12
        if data[i] == 0x12 and i + 3 < len(data):
            handle = struct.unpack("<H", data[i + 1 : i + 3])[0]
            value = data[i + 3 :]
            return {"type": "write", "handle": handle, "value": value}

    return None


def parse_glm_measurement(value):
    """Парсит данные измерения GLM."""
    if len(value) < 4:
        return None

    # Формат BLE: C0 55 10 06 ... float32 (bytes 7-10)
    if value[:4] == b"\xc0\x55\x10\x06" and len(value) >= 11:
        distance_m = struct.unpack("<f", value[7:11])[0]
        distance_mm = round(distance_m * 1000, 1)
        return {"type": "distance", "value_mm": distance_mm, "raw_hex": value.hex(" ")}

    # Формат RFCOMM-подобный: C0 40 00 EE → ответ 00 04 XX XX
    if value[:2] == b"\x00\x04" and len(value) >= 4:
        distance = struct.unpack("<H", value[2:4])[0]
        return {
            "type": "distance_rfcomm",
            "value_mm": distance,
            "raw_hex": value.hex(" "),
        }

    return {"type": "unknown", "raw_hex": value.hex(" ")}


def main():
    filepath = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_hci.log"

    print(f"Парсим: {filepath}")
    print()

    packets = parse_btsnoop(filepath)
    print(f"Всего пакетов: {len(packets)}")

    # Ищем UUID 02A6C0D1
    glm_packets = find_ble_uuid(packets, "02a6c0d1")
    print(f"Пакетов с UUID 02A6C0D1: {len(glm_packets)}")
    print()

    # Ищем UUID 02A6C0D0 (сервис)
    svc_packets = find_ble_uuid(packets, "02a6c0d0")
    print(f"Пакетов с UUID 02A6C0D0 (сервис): {len(svc_packets)}")
    print()

    # Ищем UUID 02A6C0D2 (новый)
    new_packets = find_ble_uuid(packets, "02a6c0d2")
    print(f"Пакетов с UUID 02A6C0D2 (новый): {len(new_packets)}")
    print()

    # Ищем UUID 0000fde8 (Miraculix2 сервис)
    fde8_packets = find_ble_uuid(packets, "fde8")
    print(f"Пакетов с UUID FDE8: {len(fde8_packets)}")
    print()

    # Парсим все пакеты GLM
    print("=" * 80)
    print("ДЕТАЛИ ПАКЕТОВ GLM 50C (UUID 02A6C0D1):")
    print("=" * 80)

    for i, pkt in enumerate(glm_packets[:50]):  # первые 50
        parsed = parse_att_data(pkt["data"])
        if parsed:
            measurement = parse_glm_measurement(parsed["value"])
            if measurement:
                print(f"\nПакет #{pkt['num']}:")
                print(f"  Тип: {parsed['type']}, Handle: 0x{parsed['handle']:04X}")
                print(f"  Данные: {measurement}")
                if measurement.get("raw_hex"):
                    print(f"  Hex: {measurement['raw_hex'][:80]}")

    # Общий поиск BLE соединений
    print("\n" + "=" * 80)
    print("ОБЩИЙ BLE ТРАФИК (поиск BLE событий):")
    print("=" * 80)

    ble_events = 0
    for pkt in packets:
        data = pkt["data"]
        # HCI LE Meta Event (0x3E)
        if len(data) > 5 and data[0] == 0x04 and data[1] == 0x3E:
            ble_events += 1
            if ble_events <= 20:
                subevent = data[2] if len(data) > 2 else 0
                print(
                    f"  Пакет #{pkt['num']}: HCI LE Meta Event, subevent=0x{subevent:02X}, len={pkt['incl_len']}"
                )

    print(f"\nВсего BLE событий: {ble_events}")

    # Поиск HCI Write/Read команд
    print("\n" + "=" * 80)
    print("HCI WRITE COMMANDS:")
    print("=" * 80)

    write_count = 0
    for pkt in packets:
        data = pkt["data"]
        # HCI ACL Data Out (host → controller)
        if len(data) > 4 and data[0] == 0x02:
            write_count += 1
            if write_count <= 30:
                print(
                    f"  Пакет #{pkt['num']}: ACL Out, len={pkt['incl_len']}, hex={data[:30].hex(' ')}"
                )

    print(f"\nВсего ACL Out пакетов: {write_count}")


if __name__ == "__main__":
    main()
