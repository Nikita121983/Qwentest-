#!/usr/bin/env python3
"""
Анализ синхронизации истории в HCI логе.
Ищем команды синхронизации (SyncOutputMessage, EDCOutputMessage devMode=58).
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

    # Извлекаем все C0 55 пакеты
    c055_packets = []
    for pkt in packets:
        data = pkt["data"]
        for i in range(len(data) - 10):
            if data[i : i + 2] == b"\xc0\x55":
                byte3 = data[i + 3] if len(data) > i + 3 else 0
                byte4 = data[i + 4] if len(data) > i + 4 else 0
                byte5 = data[i + 5] if len(data) > i + 5 else 0
                byte6 = data[i + 6] if len(data) > i + 6 else 0

                if len(data) > i + 10:
                    try:
                        dist_m = struct.unpack("<f", data[i + 7 : i + 11])[0]
                        dist_mm = round(dist_m * 1000, 1)
                    except:
                        dist_mm = 0
                else:
                    dist_mm = 0

                c055_packets.append(
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

    print(f"Всего C0 55 пакетов: {len(c055_packets)}")
    print()

    # Группируем по byte3
    by_type = {}
    for p in c055_packets:
        t = p["byte3"]
        if t not in by_type:
            by_type[t] = []
        by_type[t].append(p)

    print("=" * 100)
    print("C0 55 ПАКЕТЫ ПО ТИПУ (byte3):")
    print("=" * 100)

    for t in sorted(by_type.keys()):
        items = by_type[t]
        dists = [p["dist_mm"] for p in items if p["dist_mm"] > 0]
        print(f"\n  byte3=0x{t:02X} ({t:3d} dec): {len(items)} пакетов")
        if dists:
            print(f"    Расстояния: {dists[:5]}{'...' if len(dists) > 5 else ''}")
        else:
            print("    Расстояния: все 0 (control/sync)")

        for p in items[:3]:
            print(
                f"    #{p['pkt']:4d} {p['dir']} b4=0x{p['byte4']:02X} b5=0x{p['byte5']:02X} b6=0x{p['byte6']:02X}  "
                f"dist={p['dist_mm']:8.1f}  t+{p['delta_s']:7.1f}s  {p['hex']}"
            )
        if len(items) > 3:
            print(f"    ... ещё {len(items) - 3}")

    # Фокус на 0x3A (devMode=58 из APK)
    print("\n" + "=" * 100)
    print("АНАЛИЗ 0x3A (СИНХРОНИЗАЦИЯ ИСТОРИИ?):")
    print("=" * 100)

    sync_3a = by_type.get(0x3A, [])
    if sync_3a:
        print(f"Найдено пакетов 0x3A: {len(sync_3a)}")
        for p in sync_3a:
            print(f"  #{p['pkt']:4d} {p['dir']} {p['hex']}  t+{p['delta_s']:.1f}s")

        # Показываем контекст (пакеты до и после)
        for p in sync_3a:
            print(f"\n  Контекст пакета #{p['pkt']}:")
            for pkt in packets:
                if abs(pkt["num"] - p["pkt"]) <= 3:
                    print(
                        f"    #{pkt['num']:4d} {'→' if pkt['is_sent'] else '←'} len={pkt['incl_len']:4d}  "
                        f"t+{pkt['delta_ms']/1000:.1f}s  hex={pkt['data'][:20].hex(' ')}"
                    )
    else:
        print("Пакетов 0x3A не найдено.")

    # Проверяем 0x01 и 0x00 (SyncControl)
    print("\n" + "=" * 100)
    print("ПРОВЕРКА 0x01 и 0x00 (SYNC CONTROL?):")
    print("=" * 100)

    for target in [0x01, 0x00]:
        items = by_type.get(target, [])
        print(f"\n  byte3=0x{target:02X}: {len(items)} пакетов")
        for p in items[:5]:
            print(f"    #{p['pkt']:4d} {p['dir']} {p['hex']}  t+{p['delta_s']:.1f}s")


if __name__ == "__main__":
    main()
