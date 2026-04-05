#!/usr/bin/env python3
"""
Анализ управляющих команд в первом HCI логе (Новый проект2).
Ищем: laser on/off, backlight, sync, battery.
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
    hci_file = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_hci.log"
    packets = parse_btsnoop(hci_file)

    print(f"Всего пакетов: {len(packets)}")
    print()

    # 1. Ищем известные управляющие команды (RFCOMM style)
    print("=" * 100)
    print("УПРАВЛЯЮЩИЕ КОМАНДЫ (RFCOMM):")
    print("=" * 100)

    known_cmds = {
        b"\xc0\x40\x00\xee": "MEASURE",
        b"\xc0\x41\x00\x96": "LASER_ON",
        b"\xc0\x42\x00\x1e": "LASER_OFF",
        b"\xc0\x47\x00\x20": "BACKLIGHT_ON",
        b"\xc0\x48\x00\x62": "BACKLIGHT_OFF",
        b"\xc0\x06\x00\x4a": "GET_SERIAL",
        b"\xc0\x04\x00\xba": "GET_FIRMWARE",
    }

    for pkt in packets:
        data = pkt["data"]
        for cmd_hex, cmd_name in known_cmds.items():
            idx = data.find(cmd_hex)
            if idx >= 0:
                print(
                    f"  #{pkt['num']:4d} {'→' if pkt['is_sent'] else '←'} {cmd_name:20s} "
                    f"offset={idx:4d}  t+{pkt['delta_ms']/1000:.1f}s  "
                    f"hex={data[max(0,idx-2):idx+6].hex(' ')}"
                )

    # 2. Ищем BLE управляющие команды (C0 55 02 XX)
    print("\n" + "=" * 100)
    print("BLE УПРАВЛЯЮЩИЕ КОМАНДЫ (C0 55 02 XX):")
    print("=" * 100)

    for pkt in packets:
        data = pkt["data"]
        for i in range(len(data) - 5):
            if data[i : i + 3] == b"\xc0\x55\x02":
                byte3 = data[i + 3]
                print(
                    f"  #{pkt['num']:4d} {'→' if pkt['is_sent'] else '←'} "
                    f"b3=0x{byte3:02X}  t+{pkt['delta_ms']/1000:.1f}s  "
                    f"hex={data[i:i+8].hex(' ')}"
                )

    # 3. Ищем C0 56 (BLE команда из Nordic DevZone: c05601001e)
    print("\n" + "=" * 100)
    print("BLE КОМАНДЫ C0 56:")
    print("=" * 100)

    for pkt in packets:
        data = pkt["data"]
        for i in range(len(data) - 5):
            if data[i : i + 2] == b"\xc0\x56":
                print(
                    f"  #{pkt['num']:4d} {'→' if pkt['is_sent'] else '←'} "
                    f"t+{pkt['delta_ms']/1000:.1f}s  "
                    f"hex={data[i:i+10].hex(' ')}"
                )

    # 4. Ищем паттерны батареи (battery level)
    print("\n" + "=" * 100)
    print("ПОИСК УРОВНЯ БАТАРЕИ:")
    print("=" * 100)

    # Ищем значения 0-100 вблизи C0 команд
    for pkt in packets:
        data = pkt["data"]
        for i in range(len(data) - 3):
            if data[i] == 0xC0 and 1 < data[i + 1] < 100:
                # Возможный battery level
                val = data[i + 1]
                if val <= 100:
                    print(
                        f"  #{pkt['num']:4d} {'→' if pkt['is_sent'] else '←'} "
                        f"offset={i:4d}  possible_battery={val}%  "
                        f"hex={data[i:i+6].hex(' ')}"
                    )

    # 5. Все уникальные C0 команды
    print("\n" + "=" * 100)
    print("ВСЕ УНИКАЛЬНЫЕ C0 КОМАНДЫ (первые 4 байта):")
    print("=" * 100)

    c0_cmds = {}
    for pkt in packets:
        data = pkt["data"]
        for i in range(len(data) - 3):
            if data[i] == 0xC0:
                cmd = data[i : i + 4].hex(" ")
                if cmd not in c0_cmds:
                    c0_cmds[cmd] = {
                        "count": 0,
                        "dir": "→" if pkt["is_sent"] else "←",
                        "pkt": pkt["num"],
                    }
                c0_cmds[cmd]["count"] += 1

    for cmd, info in sorted(c0_cmds.items(), key=lambda x: -x[1]["count"]):
        print(
            f"  {cmd:15s}: {info['count']:4d} раз  {info['dir']}  первый: #{info['pkt']}"
        )


if __name__ == "__main__":
    main()
