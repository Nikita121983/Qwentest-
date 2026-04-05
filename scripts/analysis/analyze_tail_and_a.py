#!/usr/bin/env python3
"""Анализ байтов 19-30 и поиск компонента 'a' для 0x36."""

import struct

filepath = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_app_control.log"
with open(filepath, "rb") as f:
    data = f.read()

print("=== АНАЛИЗ БАЙТОВ 19-30 И ПОИСК 'a' ===\n")

# Ищем все 0x36 пакеты
for i in range(len(data) - 30):
    if data[i : i + 3] == b"\xc0\x55\x10" and data[i + 3] == 0x36:
        chunk = data[i : i + 30]
        result = struct.unpack("<f", chunk[7:11])[0] * 1000
        val_b = struct.unpack("<f", chunk[11:15])[0] * 1000
        val_c = struct.unpack("<f", chunk[15:19])[0] / 1000  # mdeg to deg

        # Байты 19-30
        tail = chunk[19:30]

        print(f"Пакет 0x36 (pos {i}):")
        print(f"  Result: {result:.1f} mm")
        print(f"  Comp b: {val_b:.1f} mm")
        print(f"  Angle c: {val_c:.1f}°")
        print(f"  Tail (19-30): {tail.hex(' ')}")

        # Проверяем предыдущие 500 байт на наличие 0x06 (Direct) с похожим значением
        # Компонент 'a' для 1919.6 должен быть ~1883.8
        search_start = max(0, i - 500)
        prev_chunk = data[search_start:i]

        # Ищем float32 близкий к 1883.8
        target_a = 1883.8  # Из TXT для этого замера
        found_a = False
        for j in range(len(prev_chunk) - 3):
            try:
                val = struct.unpack("<f", prev_chunk[j : j + 4])[0] * 1000
                if abs(val - target_a) < 10:
                    print(
                        f"  -> Найден 'a' ({val:.1f} mm) в предыдущих данных (offset {j})"
                    )
                    found_a = True
                    break
            except:
                pass

        if not found_a:
            print("  -> Компонент 'a' НЕ найден в предыдущих 500 байтах.")

        print()
