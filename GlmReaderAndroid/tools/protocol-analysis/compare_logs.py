#!/usr/bin/env python3
"""Сравнение cosine (кнопки рулетки) vs app_control (кнопки приложения)."""

import struct


def analyze_log(filepath, name):
    with open(filepath, "rb") as f:
        data = f.read()

    heartbeats = []
    results = []
    c056_cmds = []
    c055_02_cmds = []

    for i in range(len(data) - 12):
        # Heartbeat
        if data[i : i + 4] == b"\xc0\x55\x02\xf1":
            heartbeats.append({"pos": i, "b4": data[i + 4], "b5": data[i + 5]})
        # Result
        elif data[i : i + 3] == b"\xc0\x55\x10":
            t = data[i + 3]
            try:
                val = struct.unpack("<f", data[i + 7 : i + 11])[0]
                val_mm = round(val * 1000, 1)
            except:
                val_mm = 0
            if 0 < val_mm < 50000:
                results.append({"pos": i, "type": t, "val": val_mm})
        # C0 56 command
        elif data[i : i + 2] == b"\xc0\x56":
            c056_cmds.append({"pos": i, "hex": data[i : i + 6].hex(" ")})
        # C0 55 02 XX commands
        elif data[i : i + 3] == b"\xc0\x55\x02":
            c055_02_cmds.append(
                {"pos": i, "b3": data[i + 3], "hex": data[i : i + 7].hex(" ")}
            )

    print(f"\n{'='*60}")
    print(f"  {name}")
    print(f"{'='*60}")
    print(f"  Heartbeats: {len(heartbeats)}")
    print(f"  Results: {len(results)}")
    print(f"  C0 56 cmds: {len(c056_cmds)}")
    print(f"  C0 55 02 cmds: {len(c055_02_cmds)}")

    # Уникальные C0 56
    c056_unique = set(c["hex"] for c in c056_cmds)
    print("\n  Уникальные C0 56:")
    for cmd in sorted(c056_unique):
        count = sum(1 for c in c056_cmds if c["hex"] == cmd)
        print(f"    {cmd} ×{count}")

    # Уникальные C0 55 02
    c055_02_unique = {}
    for c in c055_02_cmds:
        key = c["hex"]
        if key not in c055_02_unique:
            c055_02_unique[key] = 0
        c055_02_unique[key] += 1

    print("\n  Уникальные C0 55 02:")
    for cmd, count in sorted(c055_02_unique.items(), key=lambda x: -x[1]):
        print(f"    {cmd} ×{count}")

    return results, heartbeats, c056_cmds, c055_02_cmds


# Анализируем оба лога
cosine = analyze_log(
    r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_cosine.log",
    "COSINE (кнопки на рулетке)",
)

app_ctrl = analyze_log(
    r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_app_control.log",
    "APP_CONTROL (кнопки в приложении)",
)

# Сравнение
print(f"\n{'='*60}")
print("  СРАВНЕНИЕ")
print(f"{'='*60}")

cosine_results = cosine[0]
app_results = app_ctrl[0]

print(f"\n  Замеров в cosine: {len(cosine_results)}")
print(f"  Замеров в app_control: {len(app_results)}")

# Сравниваем C0 56 команды
cosine_c056 = set(c["hex"] for c in cosine[2])
app_c056 = set(c["hex"] for c in app_ctrl[2])

print(f"\n  C0 56 в cosine: {cosine_c056}")
print(f"  C0 56 в app_control: {app_c056}")
print(f"  Совпадают: {cosine_c056 == app_c056}")

# Сравниваем C0 55 02 команды
cosine_02 = set(c["hex"] for c in cosine[3])
app_02 = set(c["hex"] for c in app_ctrl[3])

print(f"\n  C0 55 02 в cosine: {len(cosine_02)} уникальных")
print(f"  C0 55 02 в app_control: {len(app_02)} уникальных")

only_cosine = cosine_02 - app_02
only_app = app_02 - cosine_02

if only_cosine:
    print("\n  Только в cosine:")
    for cmd in sorted(only_cosine):
        print(f"    {cmd}")

if only_app:
    print("\n  Только в app_control:")
    for cmd in sorted(only_app):
        print(f"    {cmd}")
