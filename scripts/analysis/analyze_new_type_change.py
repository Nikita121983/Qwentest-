#!/usr/bin/env python3
"""Анализ btsnooz_new.log — новый HCI лог с переключением типов."""

import struct

filepath = r"btsnooz_new.log"
with open(filepath, "rb") as f:
    data = f.read()

print(f"Размер: {len(data)} байт")
print(f"Header: {data[:8]}")

if data[:8] != b"btsnoop\x00":
    print("Не btsnoop формат!")
    exit(1)

ver = struct.unpack(">I", data[8:12])[0]
typ = struct.unpack(">I", data[12:16])[0]
print(f"btsnoop v{ver}, type {typ}")

pos = 16
h2c = []
c2h = []

while pos + 24 <= len(data):
    incl = struct.unpack(">I", data[pos + 4 : pos + 8])[0]
    flags = struct.unpack(">I", data[pos + 8 : pos + 12])[0]
    direction = (flags >> 8) & 1
    if pos + 24 + incl > len(data):
        break

    pkt = data[pos + 24 : pos + 24 + incl]
    idx = pkt.find(b"\xc0\x55")
    if idx >= 0 and len(pkt) > idx + 3:
        cmd = pkt[idx + 2]
        sub = pkt[idx + 3]
        hx = pkt[idx : idx + min(16, len(pkt))].hex(" ")
        val_mm = 0
        if len(pkt) >= idx + 11:
            try:
                val = struct.unpack("<f", pkt[idx + 7 : idx + 11])[0]
                val_mm = round(val * 1000, 1)
            except:
                pass

        entry = (cmd, sub, val_mm, hx, direction)
        if direction == 1:
            h2c.append(entry)
        else:
            c2h.append(entry)

    pos += 24 + incl

print(f"\nHost -> Controller: {len(h2c)}")
for c, s, v, hx, d in h2c:
    val_str = f"{v:>10.1f} mm" if v > 0 else "         -"
    print(f"  cmd=0x{c:02X} sub=0x{s:02X}  {val_str}  | {hx}")

print(f"\nController -> Host: {len(c2h)}")
for c, s, v, hx, d in c2h:
    val_str = f"{v:>10.1f} mm" if v > 0 else "         -"
    print(f"  cmd=0x{c:02X} sub=0x{s:02X}  {val_str}  | {hx}")
