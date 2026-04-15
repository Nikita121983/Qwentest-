#!/usr/bin/env python3
"""
Сопоставление HCI лога с TXT экспортом замеров.
Извлекает все расстояния из HCI лога и сравнивает с TXT.
"""

import struct


def parse_btsnoop(filepath):
    """Парсит btsnoop файл."""
    packets = []
    with open(filepath, "rb") as f:
        f.read(24)  # header
        pkt_num = 0
        while True:
            rec_header = f.read(24)
            if len(rec_header) < 24:
                break
            orig_len = struct.unpack(">I", rec_header[0:4])[0]
            incl_len = struct.unpack(">I", rec_header[4:8])[0]
            flags = struct.unpack(">I", rec_header[8:12])[0]
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


def extract_all_distances(packets):
    """Извлекает все расстояния из HCI лога."""
    distances = []

    for pkt in packets:
        data = pkt["data"]
        direction = "→" if pkt["is_sent"] else "←"

        # 1. Ответы рулетки: 00 04 XX XX (RFCOMM style)
        for i in range(len(data) - 3):
            if data[i] == 0x00 and data[i + 1] == 0x04:
                dist = struct.unpack("<H", data[i + 2 : i + 4])[0]
                if 0 < dist < 50000:  # фильтр нереалистичных
                    distances.append(
                        {
                            "pkt": pkt["num"],
                            "dir": direction,
                            "type": "rfcomm_response",
                            "value_mm": dist,
                            "hex": data[i : i + 4].hex(" "),
                        }
                    )

        # 2. BLE уведомления: C0 55 10 06 ... float32
        for i in range(len(data) - 10):
            if data[i : i + 4] == b"\xc0\x55\x10\x06":
                dist_m = struct.unpack("<f", data[i + 7 : i + 11])[0]
                dist_mm = round(dist_m * 1000, 1)
                if 0 < dist_mm < 50000:
                    distances.append(
                        {
                            "pkt": pkt["num"],
                            "dir": direction,
                            "type": "ble_notify",
                            "value_mm": dist_mm,
                            "hex": data[i : i + 11].hex(" "),
                        }
                    )

    return distances


def parse_txt_export(filepath):
    """Парсит TXT экспорт замеров."""
    measurements = []
    with open(filepath, "rb") as f:
        raw = f.read()
    # Пробуем разные кодировки
    for enc in ["utf-16-le", "utf-16-be", "cp1251", "latin-1"]:
        try:
            text = raw.decode(enc)
            lines = text.splitlines()
            break
        except:
            continue
    else:
        print(f"Не удалось декодировать {filepath}")
        return []

    # Пропускаем заголовок
    for line in lines[1:]:
        parts = line.strip().split("\t")
        if len(parts) >= 6:
            name = parts[0]
            try:
                value = float(parts[1].replace(",", "."))
            except:
                continue
            unit = parts[2]
            time = parts[4]
            mtype = parts[5]

            measurements.append(
                {
                    "name": name,
                    "value": value,
                    "unit": unit,
                    "time": time,
                    "type": mtype,
                }
            )

    return measurements


def main():
    hci_file = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_hci.log"
    txt_file = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\Android\.gitignore-files\sources\Новый проект2_Список измерений.txt"

    # Извлекаем расстояния из HCI
    packets = parse_btsnoop(hci_file)
    hci_distances = extract_all_distances(packets)

    print(f"Извлечено расстояний из HCI: {len(hci_distances)}")
    print()

    # Фильтруем только уникальные значения
    unique_hci = {}
    for d in hci_distances:
        val = d["value_mm"]
        if val not in unique_hci:
            unique_hci[val] = d

    print(f"Уникальных значений в HCI: {len(unique_hci)}")
    print()

    # Парсим TXT
    txt_measurements = parse_txt_export(txt_file)
    txt_distances = [m for m in txt_measurements if m["unit"] == "mm"]
    txt_angles = [m for m in txt_measurements if m["unit"] == "°"]
    txt_areas = [m for m in txt_measurements if m["unit"] == "mm²"]
    txt_volumes = [m for m in txt_measurements if m["unit"] == "mm³"]

    print(f"TXT замеров всего: {len(txt_measurements)}")
    print(f"  Длины (mm): {len(txt_distances)}")
    print(f"  Углы (°): {len(txt_angles)}")
    print(f"  Площади (mm²): {len(txt_areas)}")
    print(f"  Объемы (mm³): {len(txt_volumes)}")
    print()

    # Сопоставляем расстояния
    print("=" * 80)
    print("СОПОСТАВЛЕНИЕ РАССТОЯНИЙ (mm):")
    print("=" * 80)

    txt_values = set(round(m["value"], 1) for m in txt_distances)
    hci_values = set(unique_hci.keys())

    matched = txt_values & hci_values
    only_txt = txt_values - hci_values
    only_hci = hci_values - txt_values

    print(f"\nСовпало: {len(matched)} из {len(txt_values)}")
    print(f"Только в TXT: {len(only_txt)}")
    print(f"Только в HCI: {len(only_hci)}")

    print("\n--- Совпавшие значения ---")
    for val in sorted(matched):
        hci_pkt = unique_hci[val]
        print(f"  {val:8.1f} mm  ← HCI пакет #{hci_pkt['pkt']} ({hci_pkt['type']})")

    print(f"\n--- Только в TXT ({len(only_txt)} значений) ---")
    for val in sorted(only_txt)[:20]:
        print(f"  {val:8.1f} mm")
    if len(only_txt) > 20:
        print(f"  ... ещё {len(only_txt) - 20}")

    print(f"\n--- Только в HCI ({len(only_hci)} значений) ---")
    for val in sorted(only_hci)[:20]:
        pkt = unique_hci[val]
        print(f"  {val:8.1f} mm  ← пакет #{pkt['pkt']} ({pkt['type']})")
    if len(only_hci) > 20:
        print(f"  ... ещё {len(only_hci) - 20}")

    # Показываем все HCI расстояния по порядку
    print("\n" + "=" * 80)
    print("ВСЕ РАССТОЯНИЯ ИЗ HCI (по порядку пакетов):")
    print("=" * 80)

    for d in hci_distances[:60]:
        match_marker = "✓" if d["value_mm"] in txt_values else " "
        print(
            f"  {match_marker} Пакет #{d['pkt']:4d} {d['dir']} {d['type']:20s} {d['value_mm']:8.1f} mm  {d['hex']}"
        )

    if len(hci_distances) > 60:
        print(f"  ... ещё {len(hci_distances) - 60}")

    # Углы из TXT
    print("\n" + "=" * 80)
    print("УГЛЫ из TXT (нужно найти в HCI):")
    print("=" * 80)
    for m in txt_angles:
        print(f"  {m['name']:15s} {m['value']:6.1f}°  время: {m['time']}")


if __name__ == "__main__":
    main()
