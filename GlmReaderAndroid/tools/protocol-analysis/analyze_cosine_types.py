#!/usr/bin/env python3
"""
Анализ BLE_NOTIFY для определения типа косвенного измерения.
Сопоставляет последовательность BLE пакетов с порядком замеров из TXT.
"""

import struct


def parse_btsnoop(filepath):
    packets = []
    with open(filepath, "rb") as f:
        f.read(24)
        pkt_num = 0
        first_ts = None
        while True:
            rec_header = f.read(24)
            if len(rec_header) < 24:
                break
            incl_len = struct.unpack(">I", rec_header[4:8])[0]
            flags = struct.unpack(">I", rec_header[8:12])[0]
            ts_high = struct.unpack(">I", rec_header[16:20])[0]
            ts_low = struct.unpack(">I", rec_header[20:24])[0]
            ts = (ts_high << 32) | ts_low
            if first_ts is None:
                first_ts = ts
            delta_ms = (ts - first_ts) / 1000
            data = f.read(incl_len)
            if len(data) < incl_len:
                break
            pkt_num += 1
            packets.append(
                {
                    "num": pkt_num,
                    "incl_len": incl_len,
                    "flags": flags,
                    "is_sent": bool(flags & 0x01),
                    "delta_ms": delta_ms,
                    "data": data,
                }
            )
    return packets


def main():
    hci_file = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_cosine.log"
    packets = parse_btsnoop(hci_file)

    # Порядок замеров из TXT (в обратном порядке - TXT снизу вверх)
    expected = [
        # Косвенная высота (3 замера)
        {"type": "Косв.высота", "result": 1444.6, "a": 1850.4, "b": 51.0},
        {"type": "Косв.высота", "result": 1479.4, "a": 1867.9, "b": 52.0},
        {"type": "Косв.высота", "result": 1688.9, "a": 2034.4, "b": 55.7},
        # Прямой замер
        {"type": "Прямой", "result": 5579, "a": None, "b": None},
        # Косвенная длина (3 замера)
        {"type": "Косв.длина", "result": 1161.4, "a": 1819.9, "b": 50.0},
        {"type": "Косв.длина", "result": 1132.4, "a": 1874.4, "b": 52.5},
        {"type": "Косв.длина", "result": 1135.6, "a": 1989.6, "b": 54.8},
        # Прямой замер
        {"type": "Прямой", "result": 5943.1, "a": None, "b": None},
        # Двойная косвенная (3 замера)
        {"type": "Двойная", "result": 1919.6, "a": 1883.8, "b": 1161.8, "c": 73.9},
        {"type": "Двойная", "result": 1844.6, "a": 1845.8, "b": 1174.3, "c": 71.4},
        {"type": "Двойная", "result": 1161.7, "a": 1676.5, "b": 1179.3, "c": 43.8},
        # Прямой замер
        {"type": "Прямой", "result": 5400, "a": None, "b": None},
    ]

    # Извлекаем все BLE_NOTIFY
    ble_notifies = []
    for pkt in packets:
        data = pkt["data"]
        for i in range(len(data) - 10):
            if data[i : i + 2] == b"\xc0\x55":
                prefix = data[i : i + 4].hex(" ")
                byte3 = data[i + 3] if len(data) > i + 3 else 0
                byte4 = data[i + 4] if len(data) > i + 4 else 0
                byte5 = data[i + 5] if len(data) > i + 5 else 0
                byte6 = data[i + 6] if len(data) > i + 6 else 0

                # Пробуем float32 из байт 7-10
                if len(data) > i + 10:
                    try:
                        dist_m = struct.unpack("<f", data[i + 7 : i + 11])[0]
                        dist_mm = round(dist_m * 1000, 1)
                    except:
                        dist_mm = 0
                else:
                    dist_mm = 0

                ble_notifies.append(
                    {
                        "pkt": pkt["num"],
                        "byte3": byte3,
                        "byte4": byte4,
                        "byte5": byte5,
                        "byte6": byte6,
                        "dist_mm": dist_mm,
                        "delta_s": pkt["delta_ms"] / 1000,
                        "hex": data[i : i + 12].hex(" "),
                        "dir": "→" if pkt["is_sent"] else "←",
                    }
                )

    print(f"Всего BLE_NOTIFY: {len(ble_notifies)}")
    print()

    # Группируем по byte3 (тип?)
    by_type = {}
    for n in ble_notifies:
        t = n["byte3"]
        if t not in by_type:
            by_type[t] = []
        by_type[t].append(n)

    print("=" * 100)
    print("BLE_NOTIFY ПО ТИПУ (byte3):")
    print("=" * 100)

    for t in sorted(by_type.keys()):
        items = by_type[t]
        dists = [n["dist_mm"] for n in items if n["dist_mm"] > 0]
        print(f"\n  byte3=0x{t:02X}: {len(items)} пакетов")
        if dists:
            print(f"    Расстояния: {dists[:10]}{'...' if len(dists) > 10 else ''}")
        for n in items[:5]:
            print(
                f"    #{n['pkt']:4d} {n['dir']} b3=0x{n['byte3']:02X} b4=0x{n['byte4']:02X} "
                f"b5=0x{n['byte5']:02X} b6=0x{n['byte6']:02X}  dist={n['dist_mm']:8.1f}  "
                f"t+{n['delta_s']:7.1f}s  {n['hex']}"
            )
        if len(items) > 5:
            print(f"    ... ещё {len(items) - 5}")

    # Сопоставляем с ожидаемыми замерами
    print("\n" + "=" * 100)
    print("СОПОСТАВЛЕНИЕ С ЗАМЕРАМИ:")
    print("=" * 100)

    # Фильтруем только результаты (dist > 100)
    results = [n for n in ble_notifies if n["dist_mm"] > 100]

    print(f"\nОжидаемых замеров: {len(expected)}")
    print(f"Найдено результатов в HCI: {len(results)}")
    print()

    # Показываем все результаты
    for i, n in enumerate(results):
        # Ищем ближайший ожидаемый замер
        closest = None
        min_diff = float("inf")
        for e in expected:
            diff = abs(n["dist_mm"] - e["result"])
            if diff < min_diff:
                min_diff = diff
                closest = e

        match = "✓" if closest and min_diff < 1.0 else "✗"
        expected_type = closest["type"] if closest else "?"

        print(
            f"  {match} #{n['pkt']:4d} {n['dist_mm']:8.1f} mm  "
            f"b3=0x{n['byte3']:02X} b4=0x{n['byte4']:02X} b5=0x{n['byte5']:02X} b6=0x{n['byte6']:02X}  "
            f"→ ожидается: {expected_type} ({closest['result'] if closest else '?'})"
        )


if __name__ == "__main__":
    main()
