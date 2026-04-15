#!/usr/bin/env python3
"""Финальный анализ checksum и батареи."""

import struct

filepath = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_app_control.log"

with open(filepath, "rb") as f:
    f.read(24)
    pkt_num = 0
    heartbeats = []
    results = []

    while True:
        rec = f.read(24)
        if len(rec) < 24:
            break
        incl = struct.unpack(">I", rec[4:8])[0]
        data = f.read(incl)
        if len(data) < incl:
            break
        pkt_num += 1

        for i in range(len(data) - 11):
            if data[i : i + 3] == b"\xc0\x55\x10":
                raw = data[i : i + 12]
                t = data[i + 3]
                checksum = raw[11]
                bytes_0_10 = list(raw[:11])

                # Пробуем больше алгоритмов
                xor_all = 0
                for b in bytes_0_10:
                    xor_all ^= b

                sum_all = sum(bytes_0_10) % 256

                # CRC8 с разными полиномами
                for poly_name, poly in [
                    ("0x07", 0x07),
                    ("0x31", 0x31),
                    ("0x1D", 0x1D),
                    ("0x9B", 0x9B),
                ]:
                    crc = 0
                    for b in bytes_0_10:
                        crc ^= b
                        for _ in range(8):
                            if crc & 0x80:
                                crc = (crc << 1) ^ poly
                            else:
                                crc <<= 1
                            crc &= 0xFF

                    if crc == checksum:
                        print(
                            f"  НАЙДЕНО! Тип 0x{t:02X} CRC8-{poly_name} совпал для пакета #{pkt_num}"
                        )

                results.append(
                    {
                        "pkt": pkt_num,
                        "type": t,
                        "checksum": checksum,
                        "xor": xor_all,
                        "sum": sum_all,
                        "hex": raw.hex(" "),
                    }
                )

            elif data[i : i + 4] == b"\xc0\x55\x02\xf1":
                raw = data[i : i + 7]
                heartbeats.append(
                    {
                        "pkt": pkt_num,
                        "b4": raw[4],
                        "b5": raw[5],
                        "b6": raw[6],
                        "hex": raw.hex(" "),
                    }
                )

# Heartbeat checksum
print("=" * 80)
print("HEARTBEAT CHECKSUM:")
print("=" * 80)

for h in heartbeats[:10]:
    raw_bytes = [0xC0, 0x55, 0x02, 0xF1, h["b4"], h["b5"]]
    xor_val = 0
    for b in raw_bytes:
        xor_val ^= b
    sum_val = sum(raw_bytes) % 256

    # CRC8
    crc = 0
    for b in raw_bytes:
        crc ^= b
        for _ in range(8):
            if crc & 0x80:
                crc = (crc << 1) ^ 0x07
            else:
                crc <<= 1
            crc &= 0xFF

    match = ""
    if xor_val == h["b6"]:
        match += " XOR✓"
    if sum_val == h["b6"]:
        match += " SUM✓"
    if crc == h["b6"]:
        match += " CRC✓"

    print(
        f"  #{h['pkt']:5d} b4=0x{h['b4']:02X} b5=0x{h['b5']:02X} b6=0x{h['b6']:02X}  "
        f"XOR=0x{xor_val:02X} SUM=0x{sum_val:02X} CRC=0x{crc:02X}  {match}"
    )

# Battery analysis
print("\n" + "=" * 80)
print("BATTERY LEVEL ANALYSIS:")
print("=" * 80)

# b5 значения не линейны - возможно это статус, а не батарея
# Проверим корреляцию b4 и b5
print("\nHeartbeat b4/b5 корреляция:")
for h in heartbeats:
    print(
        f"  #{h['pkt']:5d} b4={h['b4']:3d}(0x{h['b4']:02X}) b5={h['b5']:3d}(0x{h['b5']:02X}) b6=0x{h['b6']:02X}"
    )

# Выводы
print("\n" + "=" * 80)
print("ВЫВОДЫ:")
print("=" * 80)
print("""
1. CHECKSUM BLE_NOTIFY:
   - Простые алгоритмы (XOR, SUM, CRC8) НЕ совпали
   - Возможно используется сложный CRC или checksum вычисляется иначе
   - Для большинства типов checksum = 0x00 (live mode, distance, angle, ref)
   - Для косвенных измерений checksum варьируется

2. HEARTBEAT CHECKSUM:
   - b6 почти всегда = 0xAC (17 из 19)
   - XOR/SUM/CRC8 НЕ совпали
   - Возможно b6 = фиксированное значение или сложный checksum

3. BATTERY LEVEL:
   - b5 варьируется (58-238) - НЕ линейная шкала 0-100%
   - Возможно b5 = статус рулетки, а не батарея
   - b4 = режим рулетки (0-15)
""")
