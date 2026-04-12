"""Глубокий анализ листа Автомаркировка"""
import win32com.client
import pythoncom
import json
import os

OUTPUT_DIR = r'C:\Users\Nik\SyncthingServiceAcct\Qwentest\analysis'

pythoncom.CoInitialize()
e = win32com.client.GetActiveObject('Excel.Application')

for i in range(1, e.Workbooks.Count + 1):
    wb = e.Workbooks(i)
    if 'Обработка' in wb.Name:
        break

ws = wb.Sheets('Автомаркировка')
print(f"Автомаркировка: {ws.UsedRange.Rows.Count}x{ws.UsedRange.Columns.Count}\n")

# 1. Заголовки (строка 1)
print("=== ЗАГОЛОВКИ (строка 1) ===")
headers = {}
for col in range(1, min(ws.UsedRange.Columns.Count + 1, 192)):
    val = ws.Cells(1, col).Value
    if val:
        col_letter = ws.Cells(1, col).Address.split('$')[1]
        headers[col_letter] = str(val)[:50]

for cl, h in sorted(headers.items()):
    print(f"  {cl:4s} {h}")

# 2. Какие столбцы формулы, а какие значения
print(f"\n=== ТИПЫ СТОЛБЦОВ (строка 2) ===")
for col in range(1, min(ws.UsedRange.Columns.Count + 1, 192)):
    cell = ws.Cells(2, col)
    col_letter = cell.Address.split('$')[1]
    
    if col_letter not in headers:
        continue
    
    is_formula = cell.HasFormula
    has_spill = False
    try:
        has_spill = cell.HasSpill
    except: pass
    
    f = str(cell.Formula)[:80] if is_formula else str(cell.Value)[:50]
    t = "📐 формула" if is_formula else "📊 значение"
    if has_spill: t += " (SPILL)"
    
    print(f"  {col_letter:4s} {t:20s} {f}")

# 3. Карта зависимостей: какие столбцы от каких зависят
print(f"\n=== ЗАВИСИМОСТИ СТОЛБЦОВ ===")
col_deps = {}
for col in range(1, min(ws.UsedRange.Columns.Count + 1, 192)):
    cell = ws.Cells(2, col)
    if cell.HasFormula:
        col_letter = cell.Address.split('$')[1]
        f = str(cell.Formula).upper()
        # Ищем ссылки на другие столбцы этого же листа
        import re
        refs = set()
        for m in re.findall(r'([A-Z]+)\d+', f):
            if m in headers and m != col_letter:
                refs.add(m)
        if refs:
            col_deps[col_letter] = {
                'depends_on': sorted(refs),
                'formula': str(cell.Formula)[:120]
            }

# Выводим цепочки
for col_l, info in sorted(col_deps.items()):
    dep_count = len(info['depends_on'])
    if dep_count <= 6:
        deps_str = ', '.join(info['depends_on'])
        print(f"  {col_l:4s} ← {deps_str:30s} | {info['formula'][:80]}")

# 4. Считаем "пустые" формулы (которые вернули "")
print(f"\n=== ПУСТЫЕ ФОРМУЛЫ (первые 10 строк) ===")
for col in range(1, min(ws.UsedRange.Columns.Count + 1, 192)):
    col_letter = ws.Cells(1, col).Address.split('$')[1]
    if col_letter not in headers:
        continue
    cell = ws.Cells(2, col)
    if not cell.HasFormula:
        continue
    
    empty_count = 0
    nonempty_count = 0
    for row in range(2, 12):
        v = ws.Cells(row, col).Value
        if v is None or v == "":
            empty_count += 1
        else:
            nonempty_count += 1
    
    if empty_count > 0:
        print(f"  {col_letter:4s} {headers[col_letter][:40]:40s} → пусто: {empty_count}/10, есть данные: {nonempty_count}/10")

e.Quit()
print("\nГотово!")
