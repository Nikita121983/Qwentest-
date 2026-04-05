#!/usr/bin/env python3
"""Корреляция heartbeat b4 с типами замеров."""

import struct

filepath = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_app_control.log"

with open(filepath, "rb") as f:
    data = f.read()

heartbeats = []
results = []

for i in range(len(data) - 12):
    if data[i : i + 4] == b"\xc0\x55\x02\xf1":
        heartbeats.append({"pos": i, "b4": data[i + 4], "b5": data[i + 5]})
    elif data[i : i + 3] == b"\xc0\x55\x10":
        t = data[i + 3]
        try:
            val = struct.unpack("<f", data[i + 7 : i + 11])[0]
            val_mm = round(val * 1000, 1)
        except:
            val_mm = 0
        if 0 < val_mm < 50000:
            results.append({"pos": i, "type": t, "val": val_mm})

type_names = {
    0x06: "direct",
    0x2A: "h_ind",
    0x2E: "l_ind",
    0x36: "dbl",
    0x21: "deg",
    0x1E: "vol",
    0x3E: "area",
    0x0A: "cont",
    0x12: "inter",
    0xF2: "ref",
    0x02: "live",
}

print(f"Heartbeats: {len(heartbeats)}, Results: {len(results)}\n")

# Корреляция b4 → типы замеров
b4_to_types = {}
for hb in heartbeats:
    nearby = [r for r in results if abs(r["pos"] - hb["pos"]) < 2000]
    types = set()
    for r in nearby:
        tn = type_names.get(r["type"], f'0x{r["type"]:02X}')
        types.add(tn)

    b4 = hb["b4"]
    if b4 not in b4_to_types:
        b4_to_types[b4] = set()
    b4_to_types[b4].update(types)

print("Корреляция b4 → типы замеров рядом:")
print("=" * 50)
for b4 in sorted(b4_to_types.keys()):
    types = b4_to_types[b4]
    print(
        f"  b4={b4:3d} (0x{b4:02X}) → {', '.join(sorted(types)) if types else '(нет замеров)'}"
    )

# Теперь посмотрим на порядок замеров в TXT
print("\n" + "=" * 50)
print("Порядок замеров в TXT (app_control):")
print("=" * 50)
txt_order = [
    ("5351.8", "direct"),
    ("169.8", "dbl"),
    ("607.5", "dbl"),
    ("205.7", "dbl"),
    ("1881.5", "direct"),
    ("1197.9", "l_ind"),
    ("1165", "l_ind"),
    ("1306.6", "l_ind"),
    ("1940.3", "direct"),
    ("813.5", "h_ind"),
    ("1183", "h_ind"),
    ("1379.8", "h_ind"),
]

for i, (val, t) in enumerate(txt_order):
    print(f"  {i+1:2d}. {val:>8s} mm  {t}")
