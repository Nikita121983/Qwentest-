#!/usr/bin/env python3
"""Тест CRC8 — точная копия алгоритма из Crc.java"""


def calc_crc8(b, crc, poly=0xA6):
    """Один байт CRC8 — точно как в Java: calcCrc8(byte b, byte b2, byte b3)"""
    for i in range(8):
        msb = (crc & 0x80) != 0
        bit = ((b >> (7 - i)) & 1) == 1
        if msb != bit:
            crc = ((crc << 1) ^ poly) & 0xFF
        else:
            crc = (crc << 1) & 0xFF
    return crc


def calc_crc8_array(arr, init=0xAA, poly=0xA6):
    """CRC8 по массиву байт — точно как calcCrc8(byte[] bArr, byte b)"""
    crc = init
    for b in arr:
        crc = calc_crc8(b, crc, poly)
    return crc


print("=== CRC8 по ВСЕМУ фрейму (FrameMode + Command + Len + Payload) ===\n")

# Init: C0 55 02 40 00
test1 = bytes([0xC0, 0x55, 0x02, 0x40, 0x00])
crc1 = calc_crc8_array(test1)
print(f"Init APK:     {test1.hex()} → CRC=0x{crc1:02X}")

# Set Type (Distance): C0 55 02 BC 01
test2 = bytes([0xC0, 0x55, 0x02, 0xBC, 0x01])
crc2 = calc_crc8_array(test2)
print(f"Set Type:     {test2.hex()} → CRC=0x{crc2:02X}")

# Set Ref (Rear=2): C0 55 02 BE 02
test3 = bytes([0xC0, 0x55, 0x02, 0xBE, 0x02])
crc3 = calc_crc8_array(test3)
print(f"Set Ref(Rear):{test3.hex()} → CRC=0x{crc3:02X}")

# Sync History (index=0): C0 55 02 BA 00
test4 = bytes([0xC0, 0x55, 0x02, 0xBA, 0x00])
crc4 = calc_crc8_array(test4)
print(f"Sync History: {test4.hex()} → CRC=0x{crc4:02X}")

# Measure: C0 56 01 00
test5 = bytes([0xC0, 0x56, 0x01, 0x00])
crc5 = calc_crc8_array(test5)
print(f"Measure:      {test5.hex()} → CRC=0x{crc5:02X}")

# Проверка: Init из HCI лога — C0 55 02 01 00 1A AC
# Если CRC по [C0 55 02 01 00 1A] → должен быть AC
test6 = bytes([0xC0, 0x55, 0x02, 0x01, 0x00, 0x1A])
crc6 = calc_crc8_array(test6)
print(
    f"\nInit HCI:     {bytes([0xC0, 0x55, 0x02, 0x01, 0x00]).hex()} 1A → CRC=0x{crc6:02X} (ожидается 0xAC?)"
)

# Может CRC только по payload [02 01 00]?
test7 = bytes([0x02, 0x01, 0x00])
crc7 = calc_crc8_array(test7)
print(f"Init HCI payload only: {test7.hex()} → CRC=0x{crc7:02X}")

# А если Init HCI это [C0 55 02 01 00] и CRC=1A, а AC это что-то другое?
test8 = bytes([0xC0, 0x55, 0x02, 0x01, 0x00])
crc8_val = calc_crc8_array(test8)
print(f"Init HCI full: {test8.hex()} → CRC=0x{crc8_val:02X}")

# Попробуем с Init=0x00
test9 = bytes([0xC0, 0x55, 0x02, 0x01, 0x00])
crc9 = calc_crc8_array(test9, init=0x00)
print(f"Init HCI (init=0x00): {test9.hex()} → CRC=0x{crc9:02X}")

# Проверим: Measure из HCI C0 56 01 00 1E AC
# CRC по [C0 56 01 00 1E] = AC?
test10 = bytes([0xC0, 0x56, 0x01, 0x00, 0x1E])
crc10 = calc_crc8_array(test10)
print(
    f"\nMeasure HCI:  {bytes([0xC0, 0x56, 0x01, 0x00, 0x1E]).hex()} → CRC=0x{crc10:02X} (ожидается 0xAC?)"
)

# CRC только по [01 00]?
test11 = bytes([0x01, 0x00])
crc11 = calc_crc8_array(test11)
print(f"Measure payload only: {test11.hex()} → CRC=0x{crc11:02X}")

# Может в HCI логе CRC по [56 01 00]?
test12 = bytes([0x56, 0x01, 0x00])
crc12 = calc_crc8_array(test12)
print(f"Measure [56 01 00]: {test12.hex()} → CRC=0x{crc12:02X}")

print("\n=== Все итоговые команды ===")
for name, test in [
    ("Init APK", test1),
    ("Set Type", test2),
    ("Set Ref Rear", test3),
    ("Sync History", test4),
    ("Measure", test5),
]:
    crc = calc_crc8_array(test)
    print(f"  {name}: {' '.join(f'0x{b:02X}' for b in test)} 0x{crc:02X}")
