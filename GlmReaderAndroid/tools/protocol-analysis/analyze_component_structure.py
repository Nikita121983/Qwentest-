#!/usr/bin/env python3
"""Анализ структуры компонентных пакетов (a, b, c)."""

import struct

filepath = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_app_control.log"
with open(filepath, "rb") as f:
    data = f.read()

# Ищем пакеты с компонентами: 0x2A (h_ind), 0x2E (l_ind), 0x36 (dbl)
targets = {0x2A: "h_ind", 0x2E: "l_ind", 0x36: "dbl"}
found = {t: [] for t in targets}

for i in range(len(data) - 30):
    if data[i : i + 3] == b"\xc0\x55\x10":
        t = data[i + 3]
        if t in targets:
            # Берём 30 байт контекста
            chunk = data[i : i + 30]
            found[t].append({"pos": i, "hex": chunk.hex(" "), "raw": chunk})

print("=== СТРУКТУРА КОМПОНЕНТНЫХ ПАКЕТОВ ===\n")

for t, name in targets.items():
    packets = found[t]
    print(f"--- Тип 0x{t:02X} ({name}) — {len(packets)} пакетов ---")

    # Показываем первые 3 пакета
    for idx, p in enumerate(packets[:3]):
        raw = p["raw"]
        print(f"\n  Пакет #{idx+1} (pos {p['pos']}):")
        print(f"  Hex: {p['hex']}")

        # Пробуем распарсить
        # C0 55 10 TT SS NN 71 ...
        if len(raw) >= 7:
            header = raw[:7]
            print(f"  Header: {header.hex(' ')}")
            print("    C0 55 10 = Start")
            print(f"    TT = 0x{raw[3]:02X} (Type)")
            print(f"    SS = 0x{raw[4]:02X} (Status)")
            print(f"    NN = 0x{raw[5]:02X} (Seq)")
            print("    71 = Const")

            # Ищем float значения
            # Обычно идут подряд
            floats = []
            for offset in range(7, len(raw) - 3, 4):
                try:
                    val = struct.unpack("<f", raw[offset : offset + 4])[0]
                    val_mm = round(val * 1000, 1)
                    if 0 < val_mm < 100000:  # Фильтр реалистичных значений
                        floats.append((offset, val_mm))
                except:
                    pass

            if floats:
                print(f"  Floats found at offsets: {floats}")
                for off, val in floats:
                    print(f"    Offset {off}: {val:.1f} mm")
            else:
                print("  Floats not found in first 30 bytes")
