"""Находим ВСЕ spill-формулы через Excel COM"""

import win32com.client as win32
import pythoncom
import time
import os

PROCESS_FILE = r"c:\Users\Nik\SyncthingServiceAcct\0 Работа\Юнтолово 4 корпус\8 корпус\Обработка.xlsx"

pythoncom.CoInitialize()
e = win32.Dispatch("Excel.Application")
e.Visible = False
e.DisplayAlerts = False
e.ScreenUpdating = False
e.Calculation = -4135  # xlCalculationManual

print("Открываю файл...")
t0 = time.time()
wb = e.Workbooks.Open(os.path.abspath(PROCESS_FILE), ReadOnly=True, UpdateLinks=0)
print(f"Открыт за {time.time()-t0:.1f}с, {wb.Sheets.Count} листов\n")

spill_formulas = []

for i in range(1, wb.Sheets.Count + 1):
    ws = wb.Sheets(i)
    ws_name = ws.Name

    # Ищем spill-ячейки через SpecialCells
    try:
        # xlCellTypeSameValidation = -4174, но нам нужны spill
        # Проверяем через HasSpill
        used = ws.UsedRange
        max_r = min(used.Row + used.Rows.Count - 1, 500)
        max_c = min(used.Column + used.Columns.Count - 1, 225)

        for row in range(1, max_r + 1):
            for col in range(1, max_c + 1):
                cell = ws.Cells(row, col)
                if cell.HasSpill:
                    formula = str(cell.Formula)[:200]
                    spill_range = ""
                    try:
                        sr = cell.SpillingToRange
                        spill_range = sr.Address
                    except:
                        pass
                    spill_formulas.append(
                        {
                            "sheet": ws_name,
                            "cell": cell.Address,
                            "formula": formula,
                            "spill_to": spill_range,
                        }
                    )
    except Exception:
        pass

wb.Close(False)
e.Quit()

# Вывод
print(f"Найдено {len(spill_formulas)} spill-формул:\n")

for sf in spill_formulas:
    print(f"{'='*80}")
    print(f"{sf['sheet']}!{sf['cell']}  →  spill: {sf['spill_to']}")
    print(f"  {sf['formula']}")
    print()

if not spill_formulas:
    print("Не найдено spill-формул через COM. Попробуем другой подход...")
    # Попробуем найти ячейки с формулами содержащими СОРТ/УНИК/ФИЛЬТР
    wb = e.Workbooks.Open(os.path.abspath(PROCESS_FILE), ReadOnly=True, UpdateLinks=0)
    for i in range(1, wb.Sheets.Count + 1):
        ws = wb.Sheets(i)
        for row in range(1, 100):
            for col in range(1, 225):
                cell = ws.Cells(row, col)
                f = str(cell.Formula)
                if any(
                    kw in f for kw in ["СОРТ", "УНИК", "ФИЛЬТР", "СЖПРОБЕЛЫ", "ДВССЫЛ"]
                ):
                    print(f"{ws.Name}!{cell.Address}: {f[:150]}")
    wb.Close(False)
    e.Quit()
