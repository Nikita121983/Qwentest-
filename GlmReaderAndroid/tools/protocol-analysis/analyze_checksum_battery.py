#!/usr/bin/env python3
"""Анализ checksum и уровня батареи."""

import struct

filepath = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_app_control.log"
with open(filepath, "rb") as f:
    f.read(24)
    pkt_num = 0
    results = []
    heartbeats = []

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
            if data[i : i + 2] == b"\xc0\x55":
                raw = data[i : i + 12]
                t = data[i + 3]

                # Result packets: C0 55 10 XX
                if data[i : i + 3] == b"\xc0\x55\x10" and len(raw) >= 12:
                    checksum = raw[11]
                    # Try different checksum algorithms
                    bytes_0_10 = raw[:11]
                    xor_all = 0
                    sum_all = 0
                    for b in bytes_0_10:
                        xor_all ^= b
                        sum_all = (sum_all + b) % 256

                    results.append(
                        {
                            "pkt": pkt_num,
                            "type": t,
                            "checksum": checksum,
                            "xor": xor_all,
                            "sum": sum_all,
                            "hex": raw.hex(" "),
                        }
                    )

                # Heartbeat: C0 55 02 F1
                elif data[i : i + 4] == b"\xc0\x55\x02\xf1" and len(raw) >= 7:
                    heartbeats.append(
                        {
                            "pkt": pkt_num,
                            "b4": raw[4],
                            "b5": raw[5],
                            "b6": raw[6],
                            "hex": raw.hex(" "),
                        }
                    )

# Checksum analysis
print("=" * 80)
print("АНАЛИЗ CHECKSUM:")
print("=" * 80)

by_type = {}
for r in results:
    t = r["type"]
    if t not in by_type:
        by_type[t] = []
    by_type[t].append(r)

for t in sorted(by_type.keys()):
    items = by_type[t]
    print(f"\n  Тип 0x{t:02X} ({len(items)} пакетов):")

    # Проверяем паттерны
    xor_match = sum(1 for r in items if r["checksum"] == r["xor"])
    sum_match = sum(1 for r in items if r["checksum"] == r["sum"])
    sum_plus1 = sum(1 for r in items if r["checksum"] == (r["sum"] + 1) % 256)

    print(f"    XOR совпадений: {xor_match}/{len(items)}")
    print(f"    SUM совпадений: {sum_match}/{len(items)}")
    print(f"    SUM+1 совпадений: {sum_plus1}/{len(items)}")

    # Показываем первые 5
    for r in items[:5]:
        print(
            f"    #{r['pkt']:5d} cs=0x{r['checksum']:02X} xor=0x{r['xor']:02X} sum=0x{r['sum']:02X}  {r['hex']}"
        )

# Heartbeat analysis
print("\n" + "=" * 80)
print("АНАЛИЗ HEARTBEAT (C0 55 02 F1):")
print("=" * 80)

print(f"\nВсего heartbeat пакетов: {len(heartbeats)}")
print(f"\n{'#':>5} {'b4':>4} {'b5':>4} {'b6':>4}  b4_dec  b5_dec  b6_dec  hex")
print("-" * 60)

for h in heartbeats[:20]:
    print(
        f"#{h['pkt']:5d} 0x{h['b4']:02X} 0x{h['b5']:02X} 0x{h['b6']:02X}  "
        f"  {h['b4']:3d}   {h['b5']:3d}   {h['b6']:3d}    {h['hex']}"
    )

# Проверяем b6 как checksum heartbeat
print("\nПроверка b6 как checksum:")
for h in heartbeats[:10]:
    raw_bytes = [0xC0, 0x55, 0x02, 0xF1, h["b4"], h["b5"]]
    xor_val = 0
    for b in raw_bytes:
        xor_val ^= b
    sum_val = sum(raw_bytes) % 256
    match_xor = "✓" if xor_val == h["b6"] else "✗"
    match_sum = "✓" if sum_val == h["b6"] else "✗"
    print(
        f"  #{h['pkt']:5d} XOR=0x{xor_val:02X} {match_xor}  SUM=0x{sum_val:02X} {match_sum}  b6=0x{h['b6']:02X}"
    )
