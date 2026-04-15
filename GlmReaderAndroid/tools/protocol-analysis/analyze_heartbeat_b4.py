#!/usr/bin/env python3
"""Анализ heartbeat b4 — режим или батарея?"""

import struct

# Сопоставим heartbeat b4 с типами замеров в то же время
filepath = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_app_control.log"

with open(filepath, "rb") as f:
    f.read(24)
    pkt_num = 0
    heartbeats = []
    results = []

    while True:
        rec = f.read(24)
        if len(rec) < 24:
            break
        incl = struct.unpack(">I", rec[4:8])[0]
        data = f.read(incl)
        if len(data) < incl:
            break
        pkt_num += 1

        for i in range(len(data) - 11):
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
            elif data[i : i + 3] == b"\xc0\x55\x10":
                t = data[i + 3]
                try:
                    val = struct.unpack("<f", data[i + 7 : i + 11])[0]
                    val_mm = round(val * 1000, 1)
                except:
                    val_mm = 0
                if val_mm > 0:
                    results.append({"pkt": pkt_num, "type": t, "value": val_mm})

print(f"Heartbeats: {len(heartbeats)}")
print(f"Results: {len(results)}")
print()

# Сопоставим heartbeat с ближайшими результатами
print("=" * 80)
print("HEARTBEAT + ближайшие результаты:")
print("=" * 80)

type_names = {
    0x06: "mm",
    0x2A: "h_ind",
    0x2E: "l_ind",
    0x36: "dbl",
    0x21: "deg",
    0x1E: "vol",
    0x3E: "area",
    0x0A: "cont",
    0x12: "inter",
    0xF2: "ref",
}

for hb in heartbeats[:20]:
    # Найдём ближайшие результаты (в пределах ±50 пакетов)
    nearby = [r for r in results if abs(r["pkt"] - hb["pkt"]) <= 50]
    nearby_types = set()
    for r in nearby:
        tn = type_names.get(r["type"], f'0x{r["type"]:02X}')
        nearby_types.add(tn)

    print(
        f"  #{hb['pkt']:5d} b4={hb['b4']:3d}(0x{hb['b4']:02X}) b5={hb['b5']:3d}(0x{hb['b5']:02X})  "
        f"рядом: {', '.join(sorted(nearby_types)) if nearby_types else '(нет результатов)'}"
    )

# Анализ b4
print("\n" + "=" * 80)
print("АНАЛИЗ b4:")
print("=" * 80)

b4_values = sorted(set(hb["b4"] for hb in heartbeats))
print(f"\nУникальные значения b4: {b4_values}")
print(f"Мин: {min(b4_values)}, Макс: {max(b4_values)}")

# Группируем по b4
by_b4 = {}
for hb in heartbeats:
    b4 = hb["b4"]
    if b4 not in by_b4:
        by_b4[b4] = []
    by_b4[b4].append(hb)

for b4 in sorted(by_b4.keys()):
    items = by_b4[b4]
    # Найдём ближайшие результаты
    nearby_types = set()
    for hb in items:
        nearby = [r for r in results if abs(r["pkt"] - hb["pkt"]) <= 50]
        for r in nearby:
            tn = type_names.get(r["type"], f'0x{r["type"]:02X}')
            nearby_types.add(tn)

    print(f"\n  b4={b4:3d} (0x{b4:02X}): {len(items)} heartbeat'ов")
    print(
        f"    Рядом типы: {', '.join(sorted(nearby_types)) if nearby_types else '(нет)'}"
    )
