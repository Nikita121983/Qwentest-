#!/usr/bin/env python3
"""Детальное сравнение типов замеров в cosine vs app_control."""

import struct


def count_types(filepath):
    with open(filepath, "rb") as f:
        data = f.read()

    counts = {}
    total = 0

    for i in range(len(data) - 12):
        if data[i : i + 3] == b"\xc0\x55\x10":
            t = data[i + 3]
            try:
                val = struct.unpack("<f", data[i + 7 : i + 11])[0]
                val_mm = round(val * 1000, 1)
            except:
                val_mm = 0

            if 0 < val_mm < 50000:
                counts[t] = counts.get(t, 0) + 1
                total += 1

    return counts, total


type_names = {
    0x06: "Прямой (0x06)",
    0x2A: "Косв. высота (0x2A)",
    0x2E: "Косв. длина (0x2E)",
    0x36: "Двойная косв. (0x36)",
    0x21: "Угол (0x21)",
    0x1E: "Объём (0x1E)",
    0x3E: "Площадь (0x3E)",
    0x0A: "Непрерывный (0x0A)",
    0x12: "Промежуточный (0x12)",
    0xF2: "Референс (0xF2)",
}

cosine_counts, cosine_total = count_types(
    r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_cosine.log"
)
app_counts, app_total = count_types(
    r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_app_control.log"
)

print(f"{'Тип замера':<25} | {'Рулетка':>8} | {'Приложение':>10} | Разница")
print("-" * 60)

all_types = sorted(set(list(cosine_counts.keys()) + list(app_counts.keys())))

for t in all_types:
    name = type_names.get(t, f"Неизвестный (0x{t:02X})")
    c_val = cosine_counts.get(t, 0)
    a_val = app_counts.get(t, 0)
    diff = a_val - c_val
    diff_str = f"+{diff}" if diff > 0 else str(diff)
    print(f"{name:<25} | {c_val:>8} | {a_val:>10} | {diff_str}")

print("-" * 60)
print(
    f"{'ИТОГО':<25} | {cosine_total:>8} | {app_total:>10} | +{app_total - cosine_total}"
)
