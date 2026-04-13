## Qwen Added Memories
- ПРАВИЛО: При работе с UI всегда сначала делать HTML мокап для тестирования раскладки, и только потом переносить в XML layout. Мокапы сохранять в {Project}/docs/conversations/ или {Project}/GlmReaderAndroid/docs/
- ПРАВИЛО: ПЕРЕД коммитом ЛЮБОГО кода Android — ОБЯЗАТЕЛЬНО собрать APK, установить на телефон, запустить приложение и проверить что основной экран открывается без краша. НЕ коммитить непроверенный код. Тест = сборка + установка + ручной запуск + проверка основного flow.
- Исходные коды MM/MO находятся в: C:\Users\Nik\SyncthingServiceAcct\Qwentest\Android\.gitignore-files\sources\decompiled\sources\com\bosch\
- Measuring Master: measuringmaster/bluetooth/impl/ + mtprotocol/
- MeasureOn: ptmt/measron/bluetooth/impl/ + mtprotocol/glm100C/
Ключевые файлы: BTDeviceManagerImpl.java, BluetoothConnection.java, AutoConnectThread.java, GLMDeviceController.java, MtProtocolImpl.java
Документация анализа: docs/24_MM_MO_PROTOCOL_DEEP_ANALYSIS.md, docs/26_MM_CONNECTION_MECHANISM.md
- Исходники MM/MO находятся в: C:\Users\Nik\SyncthingServiceAcct\Qwentest\Android\.gitignore-files\sources\decompiled\sources\com\bosch\measuringmaster\bluetooth\impl\GLMDeviceController.java и MeasureOn_decompiled\sources\com\bosch\ptmt\measron\bluetooth\impl\GLMDeviceController.java. Перед каждым изменением BLE кода ОБЯЗАТЕЛЬНО сопоставлять с этими файлами.

## Правила внедрения по анализу чужого кода

При реализации на основе анализа чужого кода (декомпиляция, реверс-инжиниринг) ОБЯЗАТЕЛЬНО следовать процессу из `docs/CORRECT_IMPLEMENTATION_PROCESS.md`:

1. **Один коммит — одно изменение** (НЕ массовые переписки)
2. **Копировать МЕХАНИЗМ, не только каркас** (НЕ упрощать синхронизацию)
3. **Тестировать ПОСЛЕ каждого коммита** (не работает шаг N → НЕ начинать N+1)
4. **ADR → План → Пошаговое выполнение** (весь план НЕ за один раз)
5. **Чек-лист перед коммитом** (проверить все пункты)

**Анти-паттерн (НИКОГДА не делать):** One Big Commit с упрощениями — привело к "сборной солянке" в GlmBleManager.kt (коммит 6791fa1).

## Режим работы

1. **Обсуждение → План → Одобрение → Выполнение**
2. Если что-то сломалось — **откат на шаг назад по запросу**
3. Режим YOLO НЕ даёт права менять процесс без согласования
4. Перед каждым изменением — **сопоставление с исходным кодом MM/MO**
