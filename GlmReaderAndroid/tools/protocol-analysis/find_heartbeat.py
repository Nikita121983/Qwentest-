#!/usr/bin/env python3
"""Поиск heartbeat и анализ checksum."""

import struct

filepath = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_app_control.log"

with open(filepath, "rb") as f:
    f.read(24)
    pkt_num = 0
    heartbeats = []
    all_c055 = []

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

        for i in range(len(data) - 6):
            if data[i : i + 4] == b"\xc0\x55\x02\xf1":
                heartbeats.append(
                    {
                        "pkt": pkt_num,
                        "b4": data[i + 4],
                        "b5": data[i + 5],
                        "b6": data[i + 6],
                        "hex": data[i : i + 7].hex(" "),
                    }
                )
            elif data[i : i + 2] == b"\xc0\x55":
                all_c055.append(
                    {
                        "pkt": pkt_num,
                        "b2": data[i + 2],
                        "b3": data[i + 3],
                        "hex": data[i : i + 8].hex(" "),
                    }
                )

print(f"Всего C0 55 пакетов: {len(all_c055)}")
print(f"Heartbeat (C0 55 02 F1): {len(heartbeats)}")

if heartbeats:
    print("\nHeartbeat пакеты:")
    for h in heartbeats[:20]:
        print(
            f"  #{h['pkt']:5d} b4=0x{h['b4']:02X} b5=0x{h['b5']:02X} b6=0x{h['b6']:02X}  {h['hex']}"
        )

    # Анализируем b5 как батарею
    print("\nАнализ b5:")
    b5_vals = sorted(set(h["b5"] for h in heartbeats))
    print(f"  Уникальные значения: {[f'0x{v:02X}({v})' for v in b5_vals]}")
    for h in heartbeats[:15]:
        pct = h["b5"] / 2.55 if h["b5"] > 0 else 0
        print(f"  #{h['pkt']:5d} b5={h['b5']:3d} (0x{h['b5']:02X}) → ~{pct:.0f}%")
else:
    print("\nHeartbeat пакеты НЕ найдены в этом логе!")
    print("Возможно, приложение не шлёт heartbeat или он в другом формате.")

    # Ищем другие управляющие пакеты
    print("\nУникальные C0 55 XX XX:")
    unique = {}
    for c in all_c055:
        key = f"0x{c['b2']:02X} 0x{c['b3']:02X}"
        if key not in unique:
            unique[key] = []
        unique[key].append(c)

    for key, items in sorted(unique.items(), key=lambda x: -len(x[1])):
        print(
            f"  C0 55 {key}: {len(items)} раз  пример: #{items[0]['pkt']} {items[0]['hex']}"
        )
