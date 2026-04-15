"""
Полный анализ открытого Excel: формулы, spill, зависимости, логика.
Excel ДОЛЖЕН быть открыт!
"""
import win32com.client as win32
import pythoncom
import time
import json
import os

OUTPUT_DIR = r'C:\Users\Nik\SyncthingServiceAcct\Qwentest\analysis'
os.makedirs(OUTPUT_DIR, exist_ok=True)

pythoncom.CoInitialize()

# Подключаемся к открытому Excel
try:
    e = win32.GetActiveObject('Excel.Application')
    print(f"✅ Подключился к Excel. Открыто книг: {e.Workbooks.Count}")
except:
    print("❌ Excel не найден. Открой Обработка.xlsx и запусти скрипт.")
    exit()

# Находим нужную книгу
wb = None
for i in range(1, e.Workbooks.Count + 1):
    name = e.Workbooks(i).Name
    print(f"  {i}. {name}")
    if 'Обработка' in name:
        wb = e.Workbooks(i)

if not wb:
    print("❌ Файл 'Обработка.xlsx' не найден среди открытых.")
    exit()

print(f"\n📂 Работаем с: {wb.Name}")
print(f"   Листов: {wb.Sheets.Count}")

# Сохраняем настройки
prev_calc = e.Calculation
prev_screen = e.ScreenUpdating
e.ScreenUpdating = False
e.Calculation = -4135  # manual — не пересчитываем чтобы не тормозило

results = {}

for i in range(1, wb.Sheets.Count + 1):
    ws = wb.Sheets(i)
    ws_name = ws.Name
    
    print(f"\n--- [{i}/{wb.Sheets.Count}] {ws_name} ---")
    
    sheet_info = {
        'name': ws_name,
        'used_rows': ws.UsedRange.Rows.Count,
        'used_cols': ws.UsedRange.Columns.Count,
        'spill_formulas': [],
        'dynamic_ranges': [],    # формулы с CONCAT/INDIRECT для динамических диапазонов
        'formula_templates': {},  # уникальные формулы по столбцам
        'column_types': {},       # тип столбца: spill / index-match / direct-ref / value / empty
    }
    
    max_row = min(ws.UsedRange.Rows.Count, 50)
    max_col = min(ws.UsedRange.Columns.Count, 225)
    
    # 1. Ищем spill-формулы
    spill_count = 0
    for row in range(1, max_row):
        for col in range(1, max_col):
            cell = ws.Cells(row, col)
            try:
                if cell.HasSpill:
                    col_letter = cell.Address.split('$')[1]
                    try:
                        sr = cell.SpillingToRange.Address
                    except:
                        sr = '?'
                    
                    spill_count += 1
                    sheet_info['spill_formulas'].append({
                        'cell': cell.Address,
                        'formula': str(cell.Formula)[:200],
                        'spill_range': sr,
                    })
            except:
                pass
    
    if spill_count > 0:
        print(f"  ✅ Spill-формулы: {spill_count}")
        for sf in sheet_info['spill_formulas']:
            print(f"    {sf['cell']}: {sf['formula'][:100]}")
    
    # 2. Ищем динамические диапазоны (CONCAT, INDIRECT)
    dyn_count = 0
    for row in range(1, max_row):
        for col in range(1, max_col):
            cell = ws.Cells(row, col)
            try:
                f = str(cell.Formula)
                if any(kw in f for kw in ['CONCAT', 'ДВССЫЛ', 'INDIRECT']) and 'HasSpill' not in dir(cell):
                    if not cell.HasSpill:
                        dyn_count += 1
                        col_letter = cell.Address.split('$')[1]
                        sheet_info['dynamic_ranges'].append({
                            'cell': cell.Address,
                            'formula': f[:200],
                        })
            except:
                pass
    
    if dyn_count > 0:
        print(f"  📐 Динамические диапазоны: {dyn_count}")
    
    # 3. Анализ столбцов — первые 3 строки с формулами
    for col in range(1, max_col):
        col_letter = ws.Cells(1, col).Address.split('$')[1]
        col_formulas = []
        
        for row in range(1, min(max_row, 10)):
            cell = ws.Cells(row, col)
            try:
                f = str(cell.Formula)
                if f and f != '' and not f.startswith('=') is False:
                    if cell.HasFormula:
                        # Нормализуем
                        import re
                        tpl = re.sub(r'\$?[A-Z]+\$?\d+', '$REF', f)
                        col_formulas.append((row, tpl, f[:100]))
            except:
                pass
        
        if col_formulas:
            unique_templates = set(t for _, t, _ in col_formulas)
            sheet_info['column_types'][col_letter] = {
                'type': 'spill' if any(kw in col_formulas[0][2] for kw in ['СОРТ', 'УНИК', 'ФИЛЬТР']) else
                       'dynamic' if any(kw in col_formulas[0][2] for kw in ['CONCAT', 'ДВССЫЛ']) else
                       'index-match' if 'INDEX' in col_formulas[0][2] or 'ИНДЕКС' in col_formulas[0][2] else
                       'direct' if '!' in col_formulas[0][2] else
                       'calc',
                'templates': len(unique_templates),
                'first_formula': col_formulas[0][2][:80] if col_formulas else ''
            }
    
    results[ws_name] = sheet_info

# Восстанавливаем настройки
e.ScreenUpdating = prev_screen
e.Calculation = prev_calc

# Сохраняем
with open(os.path.join(OUTPUT_DIR, 'full_logic_analysis.json'), 'w', encoding='utf-8') as f:
    json.dump(results, f, ensure_ascii=False, indent=2)

# Итоговый отчёт
print(f"\n{'='*80}")
print("ИТОГОВЫЙ ОТЧЁТ")
print(f"{'='*80}")

total_spill = 0
total_dynamic = 0
spill_sheets = []

for ws_name, info in sorted(results.items()):
    spill_n = len(info['spill_formulas'])
    dyn_n = len(info['dynamic_ranges'])
    total_spill += spill_n
    total_dynamic += dyn_n
    
    if spill_n > 0:
        spill_sheets.append(ws_name)
    
    print(f"\n{ws_name}:")
    print(f"  Размер: {info['used_rows']}x{info['used_cols']}")
    print(f"  Spill-формулы: {spill_n}")
    print(f"  Динамические диапазоны: {dyn_n}")
    print(f"  Столбцов с формулами: {len(info['column_types'])}")

print(f"\n{'='*80}")
print(f"ВСЕГО: {total_spill} spill-формул на {len(spill_sheets)} листах")
print(f"       {total_dynamic} динамических диапазонов")
print(f"Результат: {OUTPUT_DIR}/full_logic_analysis.json")

print("\nГотово!")
