#!/usr/bin/env python3
"""
Сопоставление PDF экспорта с HCI логом.
PDF → порядок замеров + значения
HCI → сырые пакеты с расстояниями
"""

import fitz
import struct


def parse_pdf(filepath):
    """Извлекает все замеры из PDF."""
    doc = fitz.open(filepath)
    measurements = []

    for page in doc:
        text = page.get_text()
        if not text:
            continue

        # Парсим текст PDF
        # Формат: "Список измерений : Новый проект2\n1\n0°\nУгол\n03/04/26 14:26\n..."
        lines = text.strip().split("\n")

        i = 0
        while i < len(lines):
            line = lines[i].strip()

            # Пропускаем заголовок
            if "Список измерений" in line:
                i += 1
                continue

            # Значение (число с единицей или без)
            if line and (
                line.replace(",", ".")
                .replace("°", "")
                .replace("mm", "")
                .replace("³", "")
                .replace("²", "")
                .replace(" ", "")
                .replace("-", "")
                .replace(".", "")
                .isdigit()
                or "°" in line
                or "mm" in line
            ):
                value_str = line.replace(",", ".")
                unit = ""
                if "°" in line:
                    unit = "°"
                    value_str = line.replace("°", "").strip()
                elif "mm³" in line:
                    unit = "mm³"
                    value_str = line.replace("mm³", "").strip()
                elif "mm²" in line:
                    unit = "mm²"
                    value_str = line.replace("mm²", "").strip()
                elif "mm" in line:
                    unit = "mm"
                    value_str = line.replace("mm", "").strip()

                try:
                    value = float(value_str)
                except:
                    i += 1
                    continue

                # Следующая строка — тип
                mtype = ""
                if i + 1 < len(lines):
                    mtype = lines[i + 1].strip()

                # Ещё следующая — дата/время
                datetime_str = ""
                if i + 2 < len(lines):
                    datetime_str = lines[i + 2].strip()

                measurements.append(
                    {
                        "value": value,
                        "unit": unit,
                        "type": mtype,
                        "datetime": datetime_str,
                    }
                )
                i += 3
            else:
                i += 1

    return measurements


def parse_btsnoop(filepath):
    """Извлекает расстояния из HCI лога."""
    packets = []
    with open(filepath, "rb") as f:
        f.read(24)  # header
        pkt_num = 0
        while True:
            rec_header = f.read(24)
            if len(rec_header) < 24:
                break
            incl_len = struct.unpack(">I", rec_header[4:8])[0]
            flags = struct.unpack(">I", rec_header[8:12])[0]
            data = f.read(incl_len)
            if len(data) < incl_len:
                break
            pkt_num += 1

            # Ищем ответы рулетки: 00 04 XX XX
            for i in range(len(data) - 3):
                if data[i] == 0x00 and data[i + 1] == 0x04:
                    dist = struct.unpack("<H", data[i + 2 : i + 4])[0]
                    if 0 < dist < 50000:
                        packets.append(
                            {"pkt": pkt_num, "value_mm": dist, "type": "rfcomm"}
                        )

            # Ищем BLE уведомления: C0 55 10 06
            for i in range(len(data) - 10):
                if data[i : i + 4] == b"\xc0\x55\x10\x06":
                    dist_m = struct.unpack("<f", data[i + 7 : i + 11])[0]
                    dist_mm = round(dist_m * 1000, 1)
                    if 0 < dist_mm < 50000:
                        packets.append(
                            {"pkt": pkt_num, "value_mm": dist_mm, "type": "ble"}
                        )

    return packets


def main():
    pdf_file = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\Android\.gitignore-files\sources\Новый проект2_Список_измерений.pdf"
    hci_file = r"C:\Users\Nik\SyncthingServiceAcct\Qwentest\btsnoop_hci.log"

    # Извлекаем из PDF
    pdf_measurements = parse_pdf(pdf_file)
    pdf_distances = [m for m in pdf_measurements if m["unit"] == "mm"]

    print(f"PDF замеров всего: {len(pdf_measurements)}")
    print(f"  Длины (mm): {len(pdf_distances)}")
    print()

    # Извлекаем из HCI
    hci_distances = parse_btsnoop(hci_file)
    print(f"HCI расстояний: {len(hci_distances)}")
    print()

    # Сопоставляем по порядку (порядок должен совпадать)
    print("=" * 100)
    print("СОПОСТАВЛЕНИЕ ПО ПОРЯДКУ:")
    print("=" * 100)

    # Берём первые N замеров из каждого источника
    min_len = min(len(pdf_distances), len(hci_distances))

    print(
        f"\n{'PDF #':>5} {'PDF значение':>12} {'PDF тип':>25} {'HCI #':>6} {'HCI значение':>12} {'Совпало?':>10}"
    )
    print("-" * 80)

    matches = 0
    for i in range(min_len):
        pdf_m = pdf_distances[i]
        hci_m = hci_distances[i]

        pdf_val = pdf_m["value"]
        hci_val = hci_m["value_mm"]

        # Допуск ±1 мм
        matched = abs(pdf_val - hci_val) <= 1.0
        if matched:
            matches += 1

        marker = "✓" if matched else "✗"
        print(
            f"{marker} {i+1:4d} {pdf_val:10.1f} mm {pdf_m['type']:>25s} {hci_m['pkt']:5d} {hci_val:10.1f} mm {'СОВПАЛО' if matched else '---'}"
        )

    print(f"\nСовпало: {matches} из {min_len} ({matches/min_len*100:.0f}%)")

    # Показываем все PDF замеры
    print("\n" + "=" * 100)
    print("ВСЕ ЗАМЕРЫ ИЗ PDF (по порядку):")
    print("=" * 100)

    for i, m in enumerate(pdf_measurements):
        print(
            f"  {i+1:3d}. {m['value']:10.1f} {m['unit']:>5s}  {m['type']:>25s}  {m['datetime']}"
        )


if __name__ == "__main__":
    main()
