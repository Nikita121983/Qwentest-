#!/usr/bin/env python3
"""Глубокий анализ: команды приложения, батарея, checksum."""

import struct

filepath = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_app_control.log"
with open(filepath, "rb") as f:
    f.read(24)
    pkt_num = 0
    sent_cmds = []
    recv_cmds = []
    heartbeats = []

    while True:
        rec = f.read(24)
        if len(rec) < 24:
            break
        incl = struct.unpack(">I", rec[4:8])[0]
        flags = struct.unpack(">I", rec[8:12])[0]
        is_sent = bool(flags & 0x01)  # 1 = device→phone, 0 = phone→device
        data = f.read(incl)
        if len(data) < incl:
            break
        pkt_num += 1

        for i in range(len(data) - 5):
            if data[i : i + 2] == b"\xc0\x55":
                entry = {
                    "pkt": pkt_num,
                    "dir": "←" if is_sent else "→",
                    "hex": data[i : i + 12].hex(" "),
                    "raw": data[i : i + 12],
                }

                # Heartbeat: C0 55 02 F1
                if data[i : i + 4] == b"\xc0\x55\x02\xf1":
                    entry["type"] = "heartbeat"
                    if len(data) > i + 5:
                        entry["byte4"] = data[i + 4]
                        entry["byte5"] = data[i + 5]
                    heartbeats.append(entry)

                # Init: C0 55 02 01
                elif data[i : i + 4] == b"\xc0\x55\x02\x01":
                    entry["type"] = "init"
                    if is_sent:
                        sent_cmds.append(entry)
                    else:
                        recv_cmds.append(entry)

                # Result: C0 55 10 XX
                elif data[i : i + 3] == b"\xc0\x55\x10":
                    entry["type"] = f"result_0x{data[i+3]:02X}"
                    if is_sent:
                        sent_cmds.append(entry)
                    else:
                        recv_cmds.append(entry)

                # C0 56 command
                elif data[i : i + 2] == b"\xc0\x56":
                    entry["type"] = "C0_56_cmd"
                    if is_sent:
                        sent_cmds.append(entry)
                    else:
                        recv_cmds.append(entry)

                # C0 FC command
                elif data[i : i + 2] == b"\xc0\xfc":
                    entry["type"] = "C0_FC_cmd"
                    if is_sent:
                        sent_cmds.append(entry)
                    else:
                        recv_cmds.append(entry)

print("=" * 80)
print("КОМАНДЫ ОТ ПРИЛОЖЕНИЯ (→):")
print("=" * 80)
for e in sent_cmds[:20]:
    print(f"  #{e['pkt']:5d} {e['type']:20s} {e['hex']}")
if len(sent_cmds) > 20:
    print(f"  ... ещё {len(sent_cmds) - 20}")

print("\n" + "=" * 80)
print("ОТВЕТЫ РУЛЕТКИ (←):")
print("=" * 80)
for e in recv_cmds[:20]:
    print(f"  #{e['pkt']:5d} {e['type']:20s} {e['hex']}")
if len(recv_cmds) > 20:
    print(f"  ... ещё {len(recv_cmds) - 20}")

print("\n" + "=" * 80)
print("HEARTBEAT ПАКЕТЫ (C0 55 02 F1):")
print("=" * 80)
for e in heartbeats[:15]:
    b4 = e.get("byte4", 0)
    b5 = e.get("byte5", 0)
    print(
        f"  #{e['pkt']:5d} {e['dir']} b4={b4:3d}(0x{b4:02X}) b5={b5:3d}(0x{b5:02X})  {e['hex']}"
    )
if len(heartbeats) > 15:
    print(f"  ... ещё {len(heartbeats) - 15}")

# Анализ checksum для результатов
print("\n" + "=" * 80)
print("АНАЛИЗ CHECKSUM (последний байт):")
print("=" * 80)
checksums = {}
for e in recv_cmds:
    if e["type"].startswith("result_"):
        raw = e["raw"]
        if len(raw) >= 12:
            last_byte = raw[11]
            type_byte = raw[3]
            key = f"0x{type_byte:02X}"
            if key not in checksums:
                checksums[key] = []
            checksums[key].append(last_byte)

for t, vals in sorted(checksums.items()):
    unique = set(vals)
    print(
        f"  {t}: checksum байты = {sorted(unique)[:10]}{'...' if len(unique) > 10 else ''}"
    )
