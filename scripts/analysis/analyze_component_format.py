#!/usr/bin/env python3
"""Анализ формата передачи компонент (a, b, c)."""

import struct

filepath = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_app_control.log"

# Точные значения компонент из TXT
targets = {
    1088.7: "a_dbl_1",
    1193.3: "b_dbl_1",
    6.7: "c_dbl_1",
    1316.6: "a_dbl_2",
    1048.2: "b_dbl_2",
    26.8: "c_dbl_2",
    1858.1: "a_dbl_3",
    1689.9: "b_dbl_3",
    3.8: "c_dbl_3",
    1884.2: "a_len_1",
    50.2: "c_len_1",
    1351.9: "a_len_2",
    30.4: "c_len_2",
    1656.1: "a_len_3",
    37.7: "c_len_3",
    1418.4: "a_hgt_1",
    34.9: "c_hgt_1",
    1742.4: "a_hgt_2",
    42.5: "c_hgt_2",
    1825.1: "a_hgt_3",
    48.8: "c_hgt_3",
}

print(f"Ищем {len(targets)} значений компонент...")

with open(filepath, "rb") as f:
    f.read(24)
    pkt_num = 0
    found_in_pkt = {}  # pkt_num -> list of (offset, value, name)

    while True:
        rec = f.read(24)
        if len(rec) < 24:
            break
        incl = struct.unpack(">I", rec[4:8])[0]
        data = f.read(incl)
        if len(data) < incl:
            break
        pkt_num += 1

        # Ищем float32 по всему пакету
        for i in range(len(data) - 3):
            try:
                val = struct.unpack("<f", data[i : i + 4])[0]
                val_mm = round(val * 1000, 1)
                for t, name in targets.items():
                    if abs(val_mm - t) < 0.5:
                        if pkt_num not in found_in_pkt:
                            found_in_pkt[pkt_num] = []
                        found_in_pkt[pkt_num].append(
                            (i, val_mm, name, data[max(0, i - 8) : i + 12].hex(" "))
                        )
            except:
                pass

print(f"\nНайдено в {len(found_in_pkt)} пакетах")
print(f"\n{'Пакет':>5} {'Смещ':>4} {'Знач':>8} {'Имя':>10} {'Контекст (hex)'}")
print("-" * 70)

# Показываем уникальные пакеты
for pkt_num in sorted(found_in_pkt.keys()):
    entries = found_in_pkt[pkt_num]
    for offset, val, name, hex_ctx in entries:
        print(f"#{pkt_num:5d} {offset:4d} {val:8.1f} {name:>10s}  {hex_ctx}")

# Анализируем структуру пакетов с компонентами
print("\n" + "=" * 80)
print("АНАЛИЗ СТРУКТУРЫ ПАКЕТОВ С КОМПОНЕНТАМИ:")
print("=" * 80)

# Берём первые 10 пакетов с компонентами
sample_pkts = sorted(found_in_pkt.keys())[:10]

with open(filepath, "rb") as f:
    f.read(24)
    pkt_num = 0
    while True:
        rec = f.read(24)
        if len(rec) < 24:
            break
        incl = struct.unpack(">I", rec[4:8])[0]
        flags = struct.unpack(">I", rec[8:12])[0]
        data = f.read(incl)
        if len(data) < incl:
            break
        pkt_num += 1

        if pkt_num in sample_pkts:
            print(f"\nПакет #{pkt_num} (len={incl}, flags=0x{flags:02X}):")
            # Показываем hex dump
            for i in range(0, min(len(data), 200), 16):
                hex_str = " ".join(f"{b:02X}" for b in data[i : i + 16])
                ascii_str = "".join(
                    chr(b) if 32 <= b < 127 else "." for b in data[i : i + 16]
                )
                print(f"  {i:04X}: {hex_str:<48s} {ascii_str}")

            # Проверяем заголовок пакета
            if len(data) > 4:
                print(
                    f"  Заголовок: {data[0]:02X} {data[1]:02X} {data[2]:02X} {data[3]:02X}"
                )
