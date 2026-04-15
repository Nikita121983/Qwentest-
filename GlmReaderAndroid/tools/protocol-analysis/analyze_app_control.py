#!/usr/bin/env python3
"""Анализ нового HCI лога (управление из приложения)."""

import struct

filepath = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_app_control.log"
with open(filepath, "rb") as f:
    f.read(24)
    pkt_num = 0
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
        for i in range(len(data) - 10):
            if data[i : i + 2] == b"\xc0\x55":
                t = data[i + 3]
                try:
                    val = struct.unpack("<f", data[i + 7 : i + 11])[0]
                    val_mm = round(val * 1000, 1)
                except:
                    val_mm = 0
                if val_mm > 0:
                    results.append((pkt_num, t, val_mm))

print(f"Результатов: {len(results)}")
print()
type_names = {
    0x06: "mm",
    0x2A: "h_ind",
    0x2E: "l_ind",
    0x36: "dbl",
    0x21: "deg",
    0x1E: "vol",
    0x3E: "area",
    0x0A: "cont",
    0xF2: "ref",
}
for i, (pkt, t, val) in enumerate(results):
    tn = type_names.get(t, f"0x{t:02X}")
    print(f"{i+1:3d}. #{pkt:5d} {tn:>6s} {val:10.1f} mm")
