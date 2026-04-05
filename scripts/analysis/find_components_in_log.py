#!/usr/bin/env python3
"""Поиск компонентных значений (a, b, c) в HCI логе."""

import struct

filepath = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_app_control.log"
# Значения компонент из TXT (в мм и градусах)
targets = [
    1088.7,
    1193.3,
    6.7,
    1316.6,
    1048.2,
    26.8,
    1858.1,
    1689.9,
    3.8,
    1884.2,
    50.2,
    1351.9,
    30.4,
    1656.1,
    37.7,
    1418.4,
    34.9,
    1742.4,
    42.5,
    1825.1,
    48.8,
]

print(f"Ищем {len(targets)} значений компонент...")
found = []

with open(filepath, "rb") as f:
    f.read(24)
    pkt_num = 0
    while True:
        rec = f.read(24)
        if len(rec) < 24:
            break
        incl = struct.unpack(">I", rec[4:8])[0]
        data = f.read(incl)
        if len(data) < incl:
            break
        pkt_num += 1

        # Ищем float32 по всему пакету
        for i in range(len(data) - 3):
            try:
                val = struct.unpack("<f", data[i : i + 4])[0]
                val_mm = round(val * 1000, 1)
                # Проверяем совпадение с любым из targets
                for t in targets:
                    if abs(val_mm - t) < 1.0:  # допуск 1 мм
                        found.append(
                            {
                                "pkt": pkt_num,
                                "offset": i,
                                "value": val_mm,
                                "target": t,
                                "hex_context": data[max(0, i - 4) : i + 8].hex(" "),
                            }
                        )
            except:
                pass

print(f"\nНайдено совпадений: {len(found)}")
if found:
    print(
        f"\n{'Пакет':>5} {'Смещение':>4} {'Найдено':>8} {'Ожидалось':>8} {'Контекст (hex)'}"
    )
    print("-" * 60)
    for f_item in found:
        print(
            f"#{f_item['pkt']:5d} {f_item['offset']:4d} {f_item['value']:8.1f} {f_item['target']:8.1f}  {f_item['hex_context']}"
        )
else:
    print("\nКомпонентные значения (a, b, c) НЕ найдены в BLE потоке!")
    print("Вывод: Рулетка вычисляет результат internally и отправляет только его.")
    print("Компоненты (a, b, c) либо не передаются, либо передаются в другом формате.")
