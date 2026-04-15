#!/usr/bin/env python3
"""
Поиск компонентных замеров (a, b, c) и углов в HCI логе.
Ищем float32 значения, совпадающие с известными данными.
"""

import struct

# Известные значения из TXT
KNOWN_VALUES = [
    # Прямые замеры
    5400.0,
    5943.1,
    5579.0,
    # Двойная косвенная
    1161.7,
    1676.5,
    1179.3,
    43.8,
    1844.6,
    1845.8,
    1174.3,
    71.4,
    1919.6,
    1883.8,
    1161.8,
    73.9,
    # Косвенная длина
    1135.6,
    1989.6,
    54.8,
    1132.4,
    1874.4,
    52.5,
    1161.4,
    1819.9,
    50.0,
    # Косвенная высота
    1688.9,
    2034.4,
    55.7,
    1479.4,
    1867.9,
    52.0,
    1444.6,
    1850.4,
    51.0,
]


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


def find_float_matches(packets, known_values, tolerance=0.5):
    """Ищет float32 значения в пакетах, совпадающие с известными."""
    matches = []

    for pkt in packets:
        data = pkt["data"]
        # Ищем float32 по всему пакету
        for i in range(len(data) - 3):
            try:
                val = struct.unpack("<f", data[i : i + 4])[0]
                # Проверяем совпадение с известными значениями
                for known in known_values:
                    if abs(val - known) <= tolerance and val > 0:
                        matches.append(
                            {
                                "pkt": pkt["num"],
                                "offset": i,
                                "value": round(val, 1),
                                "known": known,
                                "delta_s": pkt["delta_ms"] / 1000,
                                "hex_context": data[max(0, i - 4) : i + 8].hex(" "),
                                "dir": "→" if pkt["is_sent"] else "←",
                            }
                        )
            except:
                pass

    return matches


def find_all_floats(packets):
    """Извлекает все float32 значения из пакетов."""
    all_floats = []

    for pkt in packets:
        data = pkt["data"]
        for i in range(len(data) - 3):
            try:
                val = struct.unpack("<f", data[i : i + 4])[0]
                # Фильтруем реалистичные значения
                if 10 < val < 10000:
                    all_floats.append(
                        {
                            "pkt": pkt["num"],
                            "offset": i,
                            "value": round(val, 1),
                            "delta_s": pkt["delta_ms"] / 1000,
                            "hex": data[i : i + 4].hex(" "),
                            "dir": "→" if pkt["is_sent"] else "←",
                        }
                    )
            except:
                pass

    return all_floats


def main():
    hci_file = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_cosine.log"
    packets = parse_btsnoop(hci_file)

    print(f"Всего пакетов: {len(packets)}")
    print()

    # 1. Ищем совпадения с известными значениями
    print("=" * 100)
    print("ПОИСК ИЗВЕСТНЫХ ЗНАЧЕНИЙ В HCI:")
    print("=" * 100)

    matches = find_float_matches(packets, KNOWN_VALUES, tolerance=1.0)

    # Группируем по значению
    by_value = {}
    for m in matches:
        v = m["known"]
        if v not in by_value:
            by_value[v] = []
        by_value[v].append(m)

    print(f"\nНайдено совпадений: {len(matches)}")
    print(f"Уникальных значений: {len(by_value)}")
    print()

    for val in sorted(by_value.keys()):
        items = by_value[val]
        print(f"  {val:8.1f}: {len(items)} совпадений")
        for item in items[:3]:
            print(
                f"    #{item['pkt']:4d} {item['dir']} t+{item['delta_s']:7.1f}s  offset={item['offset']:4d}  hex={item['hex_context']}"
            )
        if len(items) > 3:
            print(f"    ... ещё {len(items) - 3}")

    # 2. Все float32 значения (только уникальные)
    print("\n" + "=" * 100)
    print("ВСЕ FLOAT32 ЗНАЧЕНИЯ (10 < val < 10000):")
    print("=" * 100)

    all_floats = find_all_floats(packets)

    # Группируем по пакетам
    by_pkt = {}
    for f in all_floats:
        p = f["pkt"]
        if p not in by_pkt:
            by_pkt[p] = []
        by_pkt[p].append(f)

    print(f"\nПакетов с float: {len(by_pkt)}")
    print(f"Всего float значений: {len(all_floats)}")
    print()

    # Показываем пакеты с несколькими float значениями (вероятно компонентные замеры)
    multi_float_pkts = {k: v for k, v in by_pkt.items() if len(v) >= 2}
    print(f"Пакетов с 2+ float: {len(multi_float_pkts)}")
    print()

    for pkt_num in sorted(multi_float_pkts.keys())[:30]:
        items = multi_float_pkts[pkt_num]
        vals = [f["value"] for f in items]
        print(f"  Пакет #{pkt_num:4d}: {vals}")

    if len(multi_float_pkts) > 30:
        print(f"  ... ещё {len(multi_float_pkts) - 30}")


if __name__ == "__main__":
    main()
