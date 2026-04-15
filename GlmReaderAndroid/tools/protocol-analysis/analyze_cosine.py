#!/usr/bin/env python3
"""
Анализ нового HCI лога с косвенными измерениями.
"""

import struct


def parse_btsnoop(filepath):
    packets = []
    with open(filepath, "rb") as f:
        f.read(24)  # header
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

    print(f"Всего пакетов: {len(packets)}")
    print(f"Длительность: {packets[-1]['delta_ms']/1000:.0f} секунд")
    print()

    # Извлекаем BLE_NOTIFY
    ble_distances = []
    for pkt in packets:
        data = pkt["data"]
        for i in range(len(data) - 10):
            if data[i : i + 4] == b"\xc0\x55\x10\x06":
                dist_m = struct.unpack("<f", data[i + 7 : i + 11])[0]
                dist_mm = round(dist_m * 1000, 1)
                if 0 < dist_mm < 50000:
                    ble_distances.append(
                        {
                            "pkt": pkt["num"],
                            "value": dist_mm,
                            "delta_s": pkt["delta_ms"] / 1000,
                            "hex": data[i : i + 11].hex(" "),
                        }
                    )

    # Извлекаем RFCOMM ответы
    rfcomm_distances = []
    for pkt in packets:
        data = pkt["data"]
        for i in range(len(data) - 3):
            if data[i] == 0x00 and data[i + 1] == 0x04:
                dist = struct.unpack("<H", data[i + 2 : i + 4])[0]
                if 100 < dist < 50000:  # фильтруем heartbeat 257
                    rfcomm_distances.append(
                        {
                            "pkt": pkt["num"],
                            "value": dist,
                            "delta_s": pkt["delta_ms"] / 1000,
                            "hex": data[i : i + 4].hex(" "),
                        }
                    )

    print(f"BLE_NOTIFY замеров: {len(ble_distances)}")
    print(f"RFCOMM ответов (>100mm): {len(rfcomm_distances)}")
    print()

    # Показываем BLE замеры
    print("=" * 80)
    print("BLE_NOTIFY ЗАМЕРЫ:")
    print("=" * 80)
    for i, d in enumerate(ble_distances):
        print(
            f"  {i+1:3d}. {d['value']:8.1f} mm  пакет #{d['pkt']:5d}  t+{d['delta_s']:7.1f}s  {d['hex']}"
        )

    # Показываем RFCOMM замеры
    print("\n" + "=" * 80)
    print("RFCOMM ОТВЕТЫ (>100mm):")
    print("=" * 80)
    for i, d in enumerate(rfcomm_distances[:30]):
        print(
            f"  {i+1:3d}. {d['value']:8.1f} mm  пакет #{d['pkt']:5d}  t+{d['delta_s']:7.1f}s  {d['hex']}"
        )
    if len(rfcomm_distances) > 30:
        print(f"  ... ещё {len(rfcomm_distances) - 30}")


if __name__ == "__main__":
    main()
