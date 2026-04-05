# 003_2026-04-03_glm-reader-development

## Хронология

1. Изучена документация: 21_GLM_PROTOCOL_FULL_SPEC.md, 22_MINIMAL_APP_PLAN.md
2. Сравнил Android/docs (15_FULL_REVERSE_ENGINEERING, 16_BOSCH_GLM_PROTOCOL, 20_GLM_PROTOCOL_SPEC)
3. Изучены bugreport логи Measuring Master — структура EDCInputMessage (devMode, refEdge, laserOn)
4. Найдены TXT экспорты косвенных измерений — сопоставлены с HCI логами (12/12 совпадений)
5. Анализ HCI логов: `btsnoop_app_control.log` не содержит host→controller пакетов
6. Создан GlmReader — проект C# .NET 8 WinForms
7. Реализован BleManager, GlmCommands, GlmProtocol, Measurement, MainForm
8. Удалены выдуманные команды SetMeasurementType/SetReferencePoint
9. Проблема BLE: Windows не видит сервис GLM без спаривания, рулетка не спаривается
10. Попробованы: DeviceWatcher, BluetoothLEAdvertisementWatcher, FromBluetoothAddressAsync
11. Все варианты дают только 2 сервиса (Generic Access, Vendor-specific) — сервис GLM скрыт

## Ключевые решения
- Тип измерения и точка отсчёта выбираются КНОПКАМИ НА РУЛЕТКЕ, не в приложении
- Приложение только принимает данные, не отправляет команды смены типа
- ComboBox'ы в UI — только индикаторы (Enabled = false)
- Measuring Master МОЖЕТ переключать тип (подтверждено TXT экспортами), но raw байты команды НЕ найдены

## Проблемы
- BLE: Windows требует спаривания для доступа к сервису GLM
- Спаривание рулетки в Windows: "устройство не отвечает"
- Команда смены типа измерения: НЕ расшифрована (HCI не захватывает host→controller)

## Структура проекта
```
GlmReader/
├── Core/BleManager.cs       — BluetoothLEAdvertisementWatcher
├── Core/GlmCommands.cs      — Init, Measure, SyncHistory
├── Core/GlmProtocol.cs      — парсинг 9 типов
├── Models/Measurement.cs    — модель + ReferencePoint (заглушка)
├── UI/MainForm.cs           — UI с индикаторами
└── Program.cs
```

## Файлы для изучения в следующей сессии
- `Android/.gitignore-files/sources/Косвенные приложение_Список измерений.txt` — замеры с переключением ТИПА В ПРИЛОЖЕНИИ
- `Android/.gitignore-files/sources/Косвенные_Список измерений.txt` — замеры с переключением НА РУЛЕТКЕ
- `btsnoop_app_control.log`, `btsnoop_cosine.log` — HCI логи для сравнения
- Bugreport логи Measuring Master — Java-уровень команд (EDCOutputMessage)
