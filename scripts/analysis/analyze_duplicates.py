#!/usr/bin/env python3
"""Анализ промежуточных/повторных замеров в HCI логах."""

import struct


def analyze_results(filepath, name):
    with open(filepath, "rb") as f:
        data = f.read()

    results = []
    for i in range(len(data) - 12):
        if data[i : i + 3] == b"\xc0\x55\x10":
            t = data[i + 3]
            try:
                val = struct.unpack("<f", data[i + 7 : i + 11])[0]
                val_mm = round(val * 1000, 1)
            except:
                val_mm = 0

            if 0 < val_mm < 50000:
                results.append({"pos": i, "type": t, "val": val_mm})

    print(f"\n{'='*60}")
    print(f"  {name}: {len(results)} результатов")
    print(f"{'='*60}")

    # Группируем по типу
    by_type = {}
    for r in results:
        t = r["type"]
        if t not in by_type:
            by_type[t] = []
        by_type[t].append(r)

    type_names = {
        0x06: "direct",
        0x2A: "h_ind",
        0x2E: "l_ind",
        0x36: "dbl",
        0x0A: "cont",
        0x12: "inter",
        0x1E: "vol",
        0x3E: "area",
        0x21: "deg",
        0xF2: "ref",
    }

    for t in sorted(by_type.keys()):
        items = by_type[t]
        tn = type_names.get(t, f"0x{t:02X}")
        print(f"\n  {tn} ({len(items)} шт):")

        # Показываем все значения
        vals = [r["val"] for r in items]
        unique_vals = sorted(set(vals))

        print(f"    Уникальных значений: {len(unique_vals)}")
        print(f"    Все значения: {vals[:15]}{'...' if len(vals) > 15 else ''}")

        # Ищем дубликаты
        from collections import Counter

        counts = Counter(vals)
        dups = {v: c for v, c in counts.items() if c > 1}
        if dups:
            print(f"    Дубликаты: {dict(list(dups.items())[:5])}")


analyze_results(
    r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_cosine.log", "COSINE (рулетка)"
)
analyze_results(
    r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_app_control.log",
    "APP_CONTROL (приложение)",
)
