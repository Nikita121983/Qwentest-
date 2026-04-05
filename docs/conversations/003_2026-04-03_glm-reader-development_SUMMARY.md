# 003_2026-04-03_glm-reader-development — Резюме

## Что сделано
- ✅ Изучена вся документация (GLM протокол, Measuring Master APK реверс, bugreport логи)
- ✅ Сопоставлены TXT экспорты с HCI логами — GlmProtocol парсит правильно (12/12)
- ✅ Создан проект GlmReader (C# .NET 8 WinForms)
- ✅ Удалены выдуманные команды смены типа/точки отсчёта
- ✅ Найден MAC адрес устройства: 001343D5215D (x9370) и второй 001343D55747 (x5747)

## Главная проблема
**BLE не работает** — Windows BLE API не видит сервис GLM (`02A6C0D0-...`) без спаривания. Рулетка не отвечает на запрос спаривания в Windows.

## Что нужно в следующей сессии
1. **Решить проблему BLE**: попробовать другой подход (Python/bleak, Android мост, или прямая работа с WinRT COM)
2. **Найти команду смены типа**: сравнить HCI логи `btsnoop_cosine.log` (рулетка) vs `btsnoop_app_control.log` (приложение) — разница в host→controller пакетах
3. **Текстовые файлы** для сравнения:
   - `Android/.gitignore-files/sources/Косвенные приложение_Список измерений.txt` — переключение типа В ПРИЛОЖЕНИИ
   - `Android/.gitignore-files/sources/Косвенные_Список измерений.txt` — переключение НА РУЛЕТКЕ
4. Когда BLE заработает — экспериментально подобрать команду смены типа

## Где проект
`C:\Users\Nik\SyncthingServiceAcct\Qwentest\GlmReader\`
Запуск: `dotnet run --project C:\Users\Nik\SyncthingServiceAcct\Qwentest\GlmReader`
