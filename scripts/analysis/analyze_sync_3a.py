#!/usr/bin/env python3
"""Анализ команды синхронизации истории (0x3A)."""

import struct

filepath = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_app_control.log"
with open(filepath, "rb") as f:
    data = f.read()

print("=== ПОИСК КОМАНДЫ 0x3A И АНАЛИЗ ОТВЕТА ===\n")

found_count = 0

for i in range(len(data) - 12):
    # Ищем C0 55 10 3A
    if data[i : i + 4] == b"\xc0\x55\x10\x3a":
        found_count += 1
        print(f"Найдена команда 0x3A на позиции {i}")
        print(f"  Hex: {data[i:i+12].hex(' ')}")

        # Смотрим следующие 500 байт (ответ рулетки)
        response = data[i + 12 : i + 512]
        print("  Ответ (первые 500 байт):")

        # Разбиваем на строки по 16 байт
        for j in range(0, len(response), 16):
            chunk = response[j : j + 16]
            hex_str = " ".join(f"{b:02X}" for b in chunk)
            ascii_str = "".join(chr(b) if 32 <= b < 127 else "." for b in chunk)
            print(f"    +{j:03X}: {hex_str:<48s} {ascii_str}")

            # Проверяем, есть ли там float32 (результаты замеров)
            if len(chunk) >= 4:
                try:
                    val = struct.unpack("<f", chunk[:4])[0] * 1000
                    if 0 < val < 10000:
                        print(f"           -> Float: {val:.1f} mm")
                except:
                    pass

        print("-" * 60)
        # Для первого найденного достаточно
        if found_count >= 1:
            break

if found_count == 0:
    print("Команда 0x3A не найдена в этом логе.")
