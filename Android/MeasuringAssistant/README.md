# MeasuringAssistant — Android приложение для замеров дверей

## 📱 Описание

Приложение для профессиональных замеров дверей в строительстве с:
- Подключением к лазерной рулетке Bosch GLM 50 C по Bluetooth
- Голосовыми заметками с оффлайн-распознаванием
- Привязкой замеров к PDF-планам
- Экспортом в XLSX/CSV с правильным временем замера

## 🏗️ Архитектура

```
app/src/main/java/com/measuringassistant/app/
├── data/                    # Data layer
│   ├── database/            # Room Database
│   ├── repository/          # Repositories
│   └── model/               # Data models
├── ble/                     # Bluetooth LE (Bosch GLM 50 C)
├── voice/                   # Voice recognition (Whisper)
├── export/                  # Export (XLSX/CSV)
├── ui/                      # UI layer
│   ├── main/                # Main activity
│   ├── measurements/        # Measurements list
│   └── settings/            # Settings
└── utils/                   # Utilities
```

## 🚀 Быстрый старт

1. Открыть проект в Android Studio
2. Sync Gradle
3. Run на устройстве/эмуляторе

## 📋 Требования

- minSdk: 26 (Android 8.0)
- targetSdk: 34 (Android 14)
- Kotlin: 1.9.0
- Gradle: 8.0

## 📚 Документация

Полная документация: [`../../.gitignore-files/Android/docs/`](../../.gitignore-files/Android/docs/)

---

*Проект создан: 2 апреля 2026 г.*
