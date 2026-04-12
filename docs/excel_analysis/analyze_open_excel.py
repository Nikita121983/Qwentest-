"""Анализ открытого Excel — ищет ВСЕ экземпляры"""
import win32com.client
import pythoncom
import os
import json

OUTPUT_DIR = r'C:\Users\Nik\SyncthingServiceAcct\Qwentest\analysis'
os.makedirs(OUTPUT_DIR, exist_ok=True)

pythoncom.CoInitialize()

# Пробуем разные способы подключения
excel = None

# Способ 1: GetActiveObject
try:
    excel = win32com.client.GetActiveObject('Excel.Application')
    print(f"Способ 1: подключился, Workbooks={excel.Workbooks.Count}")
except:
    print("Способ 1: не удалось")

# Если 0 книг — может это другой Excel процесс
if excel and excel.Workbooks.Count == 0:
    print("0 книг, пробуем через Dispatch...")
    try:
        excel2 = win32com.client.Dispatch("Excel.Application")
        print(f"Dispatch: Workbooks={excel2.Workbooks.Count}")
        if excel2.Workbooks.Count > 0:
            excel = excel2
    except Exception as ex:
        print(f"Dispatch ошибка: {ex}")

if not excel or excel.Workbooks.Count == 0:
    print("\n❌ Не удалось подключиться к Excel с книгами.")
    print("Excel с Обработка.xlsx — это PID 6372.")
    print("Попробуй: закрой ВСЕ Excel кроме одного с Обработка.xlsx")
    exit()

# Нашли Excel с книгами
print(f"\n✅ Нашёл Excel с {excel.Workbooks.Count} книгами:")
wb = None
for i in range(1, excel.Workbooks.Count + 1):
    w = excel.Workbooks(i)
    print(f"  {i}. {w.Name}")
    if 'Обработка' in w.Name:
        wb = w

if not wb:
    # Берём первую книгу
    wb = excel.Workbooks(1)
    print(f"\nБерём первую: {wb.Name}")

print(f"\n📂 Анализирую: {wb.Name}")

# Отключаем пересчёт и экран для скорости
excel.ScreenUpdating = False
excel.Calculation = -4135

results = {}
total_spill = 0

for i in range(1, wb.Sheets.Count + 1):
    ws = wb.Sheets(i)
    ws_name = ws.Name
    print(f"\n[{i}/{wb.Sheets.Count}] {ws_name}...", end=" ", flush=True)
    
    used_r = ws.UsedRange.Rows.Count
    used_c = ws.UsedRange.Columns.Count
    
    sheet_data = {
        'rows': used_r, 'cols': used_c,
        'spill_formulas': [],
        'dynamic_ranges': [],
        'column_analysis': {}
    }
    
    # Ищем spill-формулы
    for row in range(1, min(used_r + 1, 20)):
        for col in range(1, min(used_c + 1, 50)):
            try:
                cell = ws.Cells(row, col)
                if cell.HasSpill:
                    col_l = cell.Address.split('$')[1]
                    f = str(cell.Formula)
                    try:
                        sr = cell.SpillingToRange.Address
                    except: sr = '?'
                    sheet_data['spill_formulas'].append({
                        'cell': cell.Address, 'formula': f[:200], 'spill_to': sr
                    })
                    print(f"SPILL! ", end="")
            except: pass
    
    # Ищем CONCAT/ДВССЫЛ
    for row in range(1, min(used_r + 1, 5)):
        for col in range(1, min(used_c + 1, 50)):
            try:
                cell = ws.Cells(row, col)
                f = str(cell.Formula)
                if ('CONCAT' in f or 'ДВССЫЛ' in f) and not cell.HasSpill:
                    col_l = cell.Address.split('$')[1]
                    sheet_data['dynamic_ranges'].append({
                        'cell': cell.Address, 'formula': f[:200]
                    })
            except: pass
    
    # Типы столбцов
    for col in range(1, min(used_c + 1, 100)):
        try:
            cell = ws.Cells(4, col)
            if cell.HasFormula:
                f = str(cell.Formula).upper()
                col_l = cell.Address.split('$')[1]
                if any(kw in f for kw in ['СОРТ','УНИК','ФИЛЬТР']):
                    t = 'spill'
                elif 'CONCAT' in f or 'ДВССЫЛ' in f:
                    t = 'dynamic_range'
                elif 'INDEX' in f or 'ИНДЕКС' in f:
                    t = 'index_match'
                elif '!' in f:
                    t = 'cross_sheet'
                else:
                    t = 'calc'
                sheet_data['column_analysis'][col_l] = {
                    'type': t,
                    'formula': str(cell.Formula)[:80]
                }
        except: pass
    
    results[ws_name] = sheet_data
    print(f"OK ({used_r}x{used_c})")
    total_spill += len(sheet_data['spill_formulas'])

# Сохраняем
with open(os.path.join(OUTPUT_DIR, 'open_excel_analysis.json'), 'w', encoding='utf-8') as f:
    json.dump(results, f, ensure_ascii=False, indent=2)

# Отчёт
print(f"\n{'='*80}")
print(f"ИТОГО: {total_spill} spill-формул")
print(f"{'='*80}")

for ws_name, info in results.items():
    spill_n = len(info['spill_formulas'])
    dyn_n = len(info['dynamic_ranges'])
    print(f"\n{ws_name}: {info['rows']}x{info['cols']}")
    print(f"  Spill: {spill_n}, Динамические: {dyn_n}")
    if spill_n > 0:
        for sf in info['spill_formulas']:
            print(f"  ✅ {sf['cell']} spill→{sf['spill_to']}: {sf['formula'][:120]}")
    if dyn_n > 0:
        for dr in info['dynamic_ranges']:
            print(f"  📐 {dr['cell']}: {dr['formula'][:100]}")
    
    # Сводка по типам столбцов
    types = {}
    for col_l, info_c in info['column_analysis'].items():
        types[info_c['type']] = types.get(info_c['type'], 0) + 1
    if types:
        print(f"  Типы: {types}")

print(f"\nРезультат: {OUTPUT_DIR}/open_excel_analysis.json")
