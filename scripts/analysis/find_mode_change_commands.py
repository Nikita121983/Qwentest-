#!/usr/bin/env python3
"""
Поиск команд смены типа измерения в HCI snoop логе.

Вывод: Команда C0 55 02 F1 отправляется ПЕРЕД каждым новым типом измерения.
Это команда смены режима (devMode) от приложения Measuring Master к рулетке.
"""

import struct
from datetime import datetime

filepath = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_app_control.log"

# Типы измерений Bosch GLM (байт sub_cmd в команде C0 55 10 XX)
MEASURE_TYPES = {
    0x02: "Measure_Start",
    0x06: "Distance",
    0x0A: "Continuous",
    0x0E: "Trig_area_base",
    0x12: "Trig_area_result",
    0x16: "Trig_2pt_unknown",
    0x1A: "Trig_2pt_base",
    0x1E: "Volume",
    0x21: "Angle",
    0x2A: "Height_indirect",
    0x2E: "Length_indirect",
    0x32: "Trig_3pt_unknown",
    0x36: "Trig_3pt_result",
    0x3A: "Trig_3pt_base",
    0x3E: "Area",
    0xF2: "Reference",
}

print(f"Анализ: {filepath}")
print("=" * 120)

with open(filepath, "rb") as f:
    f.read(16)  # file header

    pkt_num = 0
    all_c055 = []

    while True:
        rec_hdr = f.read(24)
        if len(rec_hdr) < 24:
            break

        incl_len = struct.unpack(">I", rec_hdr[4:8])[0]
        flags = struct.unpack(">I", rec_hdr[8:12])[0]
        ts_sec = struct.unpack(">I", rec_hdr[16:20])[0]
        ts_usec = struct.unpack(">I", rec_hdr[20:24])[0]
        direction = (flags >> 8) & 1

        data = f.read(incl_len)
        if len(data) < incl_len:
            break

        pkt_num += 1

        if direction != 0:  # Только host->controller
            continue

        idx = data.find(b"\xc0\x55")
        if idx < 0:
            continue

        ts = datetime.fromtimestamp(ts_sec)
        ts_str = ts.strftime("%H:%M:%S") + f".{ts_usec // 1000:03d}"

        cmd_data = data[idx:]
        if len(cmd_data) < 4:
            continue

        cmd_byte = cmd_data[2]
        sub_cmd = cmd_data[3]

        # Значение (если есть)
        val_mm = 0
        if len(cmd_data) >= 11:
            try:
                val = struct.unpack("<f", cmd_data[7:11])[0]
                val_mm = round(val * 1000, 1)
            except:
                pass

        all_c055.append(
            {
                "num": pkt_num,
                "ts": ts_str,
                "ts_sec": ts_sec + ts_usec / 1e6,
                "cmd": cmd_byte,
                "sub": sub_cmd,
                "val_mm": val_mm,
                "data": cmd_data,
            }
        )

# Разделяем на команды смены режима и измерения
mode_changes = [c for c in all_c055 if c["cmd"] == 0x02 and c["sub"] == 0xF1]
measures = [c for c in all_c055 if c["cmd"] == 0x10 and c["sub"] != 0x02]

print(f"\nВСЕГО пакетов C0 55 (host->device): {len(all_c055)}")
print(f"Команд смены режима (C0 55 02 F1): {len(mode_changes)}")
print(
    f"Команд измерения (C0 55 10 XX): {len([c for c in all_c055 if c['cmd'] == 0x10])}"
)

# Показываем последовательность: mode_change -> measurements
print("\n" + "=" * 120)
print("ПОСЛЕДОВАТЕЛЬНОСТЬ: СМЕНА РЕЖИМА -> ИЗМЕРЕНИЯ")
print("=" * 120)

# Находим что происходит после каждой команды C0 55 02 F1
for i, mc in enumerate(mode_changes):
    mc_idx = all_c055.index(mc)

    # Следующие пакеты до следующей смены режима
    next_mc_idx = (
        all_c055.index(mode_changes[i + 1])
        if i + 1 < len(mode_changes)
        else len(all_c055)
    )

    related = all_c055[mc_idx + 1 : next_mc_idx]
    measure_cmds = [r for r in related if r["cmd"] == 0x10 and r["sub"] != 0x02]

    if not measure_cmds:
        continue

    # Определяем тип измерения
    first_measure = measure_cmds[0]
    type_name = MEASURE_TYPES.get(first_measure["sub"], f'0x{first_measure["sub"]:02X}')

    print(f"\n{'─' * 80}")
    print(f"  [{mc['ts']}] Команда смены режима: C0 55 02 F1 (пакет #{mc['num']})")
    print(f"  -> Тип измерения: {type_name} (0x{first_measure['sub']:02X})")
    print(f"  -> Измерений в сессии: {len(measure_cmds)}")

    for m in measure_cmds[:5]:
        type_n = MEASURE_TYPES.get(m["sub"], f'0x{m["sub"]:02X}')
        val_str = f"{m['val_mm']:.1f} mm" if m["val_mm"] > 0 else "-"
        hex_preview = m["data"][: min(16, len(m["data"]))].hex(" ")
        print(
            f"     {m['ts']} #{m['num']:5d} {type_n:>20s} {val_str:>14s} | {hex_preview}"
        )

    if len(measure_cmds) > 5:
        print(f"     ... еще {len(measure_cmds) - 5} измерений")

# Статистика типов измерений
print("\n" + "=" * 120)
print("СТАТИСТИКА ТИПОВ ИЗМЕРЕНИЙ")
print("=" * 120)

type_stats = {}
for mc in mode_changes:
    mc_idx = all_c055.index(mc)
    next_mc_idx = (
        all_c055.index(mode_changes[mode_changes.index(mc) + 1])
        if mode_changes.index(mc) + 1 < len(mode_changes)
        else len(all_c055)
    )
    related = all_c055[mc_idx + 1 : next_mc_idx]
    measure_cmds = [r for r in related if r["cmd"] == 0x10 and r["sub"] != 0x02]

    if not measure_cmds:
        continue

    type_byte = measure_cmds[0]["sub"]
    type_name = MEASURE_TYPES.get(type_byte, f"0x{type_byte:02X}")

    if type_name not in type_stats:
        type_stats[type_name] = {"count": 0, "type_byte": type_byte}
    type_stats[type_name]["count"] += 1

print(f"\n{'Тип измерения':<25s} {'Кол-во сессий':>15s} {'Байт типа':>10s}")
print("-" * 50)
for type_name, stats in sorted(type_stats.items(), key=lambda x: -x[1]["count"]):
    print(f"  {type_name:<23s} {stats['count']:>15d}       0x{stats['type_byte']:02X}")

# Формат команды смены режима
print("\n" + "=" * 120)
print("ФОРМАТ КОМАНДЫ СМЕНЫ РЕЖИМА")
print("=" * 120)

print("\nКоманда: C0 55 02 F1 XX YY ZZ")
print("  C0 55    - BLE protocol header")
print("  02       - команда setup/mode")
print("  F1       - sub-command: смена типа измерения (devMode)")
print("  XX       - параметр (varies)")
print("  YY ZZ    - checksum/CRC")

print("\nВсе найденные команды C0 55 02 F1:")
for mc in mode_changes:
    hex_full = mc["data"].hex(" ")
    print(f"  #{mc['num']:5d} {mc['ts']} | {hex_full}")
