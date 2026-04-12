"""Находим spill-формулы через Excel COM — быстрый поиск по формулам"""
import win32com.client as win32
import pythoncom
import time
import os
import re

PROCESS_FILE = r'c:\Users\Nik\SyncthingServiceAcct\0 Работа\Юнтолово 4 корпус\8 корпус\Обработка.xlsx'

pythoncom.CoInitialize()
e = win32.Dispatch('Excel.Application')
e.Visible = False
e.DisplayAlerts = False
e.ScreenUpdating = False
e.Calculation = -4135

print("Открываю файл...")
t0 = time.time()
wb = e.Workbooks.Open(os.path.abspath(PROCESS_FILE), ReadOnly=True, UpdateLinks=0)
print(f"Открыт за {time.time()-t0:.1f}с\n")

spill_keywords = ['СОРТ', 'УНИК', 'ФИЛЬТР', 'СЖПРОБЕЛЫ', 'ДВССЫЛ']

for i in range(1, wb.Sheets.Count + 1):
    ws = wb.Sheets(i)
    ws_name = ws.Name
    
    found = False
    for row in range(1, 10):
        for col in range(1, 50):
            cell = ws.Cells(row, col)
            try:
                f = str(cell.Formula)
                if any(kw in f for kw in spill_keywords):
                    if not found:
                        print(f"\n--- {ws_name} ---")
                        found = True
                    
                    col_letter = cell.Address.split('$')[1]
                    print(f"  {col_letter}{row}: {f[:150]}")
                    
                    # Проверяем HasSpill
                    if cell.HasSpill:
                        print(f"    ✅ SPILL!")
                        try:
                            print(f"    Spill range: {cell.SpillingToRange.Address}")
                        except:
                            pass
            except:
                pass

wb.Close(False)
e.Quit()
print("\nГотово!")
