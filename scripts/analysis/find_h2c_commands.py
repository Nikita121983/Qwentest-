#!/usr/bin/env python3
"""Поиск ВСЕХ пакетов C0 55 в обоих направлениях."""

import struct
from datetime import datetime

filepath = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_app_control.log"

print(f"Анализ: {filepath}")
print("=" * 140)

with open(filepath, "rb") as f:
    f.read(16)

    pkt_num = 0
    h2c = []  # Host -> Controller (app -> device)
    c2h = []  # Controller -> Host (device -> app)

    while True:
        hdr = f.read(24)
        if len(hdr) < 24:
            break
        incl = struct.unpack(">I", hdr[4:8])[0]
        flags = struct.unpack(">I", hdr[8:12])[0]
        ts_sec = struct.unpack(">I", hdr[16:20])[0]
        ts_usec = struct.unpack(">I", hdr[20:24])[0]
        direction = (flags >> 8) & 1

        data = f.read(incl)
        if len(data) < incl:
            break
        pkt_num += 1

        idx = data.find(b"\xc0\x55")
        if idx < 0 or len(data) < idx + 4:
            continue

        cmd = data[idx + 2]
        sub = data[idx + 3]
        hex_preview = data[idx : idx + min(16, len(data))].hex(" ")
        ts = (
            datetime.fromtimestamp(ts_sec).strftime("%H:%M:%S")
            + f".{ts_usec // 1000:03d}"
        )

        entry = (pkt_num, ts, cmd, sub, hex_preview)
        if direction == 1:
            h2c.append(entry)
        else:
            c2h.append(entry)

print(f"\nHost → Controller (Приложение → Рулетка): {len(h2c)} пакетов C0 55")
print("-" * 140)
for n, ts, cmd, sub, hx in h2c:
    print(f"  #{n:>5d} {ts}  cmd=0x{cmd:02X} sub=0x{sub:02X}  {hx}")

print(f"\nController → Host (Рулетка → Приложение): {len(c2h)} пакетов C0 55")
print("-" * 140)
for n, ts, cmd, sub, hx in c2h[:40]:
    print(f"  #{n:>5d} {ts}  cmd=0x{cmd:02X} sub=0x{sub:02X}  {hx}")
if len(c2h) > 40:
    print(f"  ... ещё {len(c2h) - 40} пакетов")

# Ключевой анализ: какие команды host→device с cmd=0x02 sub=0xF1
print(f"\n{'=' * 140}")
print("КЛЮЧЕВОЙ ВОПРОС: Есть ли C0 55 02 F1 от ПРИЛОЖЕНИЯ к рулетке?")
print("=" * 140)
h2c_f1 = [x for x in h2c if x[2] == 0x02 and x[3] == 0xF1]
if h2c_f1:
    print(f"НАЙДЕНО {len(h2c_f1)} пакетов C0 55 02 F1 host→controller:")
    for n, ts, cmd, sub, hx in h2c_f1:
        print(f"  #{n:>5d} {ts}  {hx}")
else:
    print("НЕ НАЙДЕНО ни одного C0 55 02 F1 от приложения к рулетке.")
    print("\nТогда чем приложение переключает режим?")
    print("Покажу ВСЕ уникальные команды host→controller:")
    unique_cmds = set((x[2], x[3]) for x in h2c)
    for cmd, sub in sorted(unique_cmds):
        entries = [x for x in h2c if x[2] == cmd and x[3] == sub]
        print(f"\n  cmd=0x{cmd:02X} sub=0x{sub:02X} — {len(entries)} раз:")
        for n, ts, c, s, hx in entries[:5]:
            print(f"    #{n:>5d} {ts}  {hx}")
        if len(entries) > 5:
            print(f"    ... ещё {len(entries) - 5}")
