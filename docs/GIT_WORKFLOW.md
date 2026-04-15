# Git Workflow — GlmReaderAndroid

## 📁 Структура репозитория

```
Qwentest/
├── GlmReaderAndroid/          # ПРОЕКТ 1 — Android (АКТИВНЫЙ)
│   ├── app/                   # Исходный код
│   ├── docs/                  # UI мокапы
│   ├── tools/                 # Скрипты анализа
│   ├── releases/              # APK (НЕ в git!)
│   └── reference/             # Bosch декомпиляция (НЕ в git!)
├── python/                    # ПРОЕКТ 2 — Python (будущее)
├── csharp/                    # ПРОЕКТ 3 — C# (будущее)
├── docs/                      # ОБЩАЯ документация
├── archives/                  # ОБЩИЕ логи
└── tools/                     # ОБЩИЕ инструменты (jadx)
```

---

## 🔄 Стандартный цикл разработки

### 1. Начать работу

```bash
git checkout develop
```

### 2. Внести изменения и собрать APK

```bash
cd GlmReaderAndroid
gradlew.bat assembleDebug
```

### 3. Проверить APK на телефоне

```bash
# Установка
adb install -r app\build\outputs\apk\debug\GlmReader-v*.apk

# Запуск
adb shell am force-stop com.glmreader.android
adb shell monkey -p com.glmreader.android -c android.intent.category.LAUNCHER 1
```

**→ РАБОТАЕТ?**
- ✅ **ДА** → Перейти к шагу 4
- ❌ **НЕТ** → Исправить → Вернуться к шагу 2

### 4. Сохранить APK локально

```bash
# Создать папку версии
mkdir releases\v1.0.XX

# Копировать APK
copy app\build\outputs\apk\debug\GlmReader-v*.apk releases\v1.0.XX\
```

### 5. Закоммитить

```bash
git add -A
git commit -m "fix: описание изменений"
git push origin develop
```

### 6. Релиз (когда версия готова)

```bash
git tag v1.0.XX
git push origin v1.0.XX
```

---

## 📂 Структура releases/

```
GlmReaderAndroid/releases/
├── v1.0.17/
│   └── GlmReader-v1.0.17.apk
├── v1.0.23/
│   └── GlmReader-v1.0.23.apk
└── v1.0.XX/
    └── GlmReader-v1.0.XX.apk
```

**Важно:** `releases/` в `.gitignore` — APK не попадают в git!

---

## 🚀 Переход к любой версии

### Вариант A: Собрать из исходников

```bash
git checkout v1.0.23
cd GlmReaderAndroid
gradlew.bat assembleDebug
```

### Вариант B: Взять готовый APK

```bash
# APK уже лежит в releases/v1.0.23/
adb install -r GlmReaderAndroid\releases\v1.0.23\GlmReader-v1.0.23.apk
```

### Вернуться к актуальной

```bash
git checkout develop
```

---

## 📊 Git Graph (VS Code)

### Установка
1. `Ctrl+Shift+X` → Extensions
2. Поиск: `Git Graph`
3. Install (автор: mhutchie)

### Использование
```
Ctrl+Shift+P → Git Graph: View
```

### Что показывает
- ✅ Визуальное дерево веток
- ✅ Все теги (`v1.0.17`, `v1.0.23`)
- ✅ История коммитов
- ✅ Разница между версиями
- ✅ Checkout к любой версии одним кликом

---

## 🔑 Правила

| Правило | Почему |
|---------|--------|
| **Вся работа в `develop`** | `main` = стабильная |
| **APK не в git** | `.gitignore` → `releases/` |
| **Тег = релиз** | `v1.0.24` = точка возврата |
| **1 коммит = 1 изменение** | Легко откатить |
| **Тест перед коммитом** | Собрал → проверил → закоммитил |

---

## 📦 Чек-лист перед коммитом

- [ ] `gradlew.bat assembleDebug` — собирается?
- [ ] APK установлен на телефон — работает?
- [ ] `git status` — только нужные файлы?
- [ ] `git diff` — изменения правильные?
- [ ] `git commit -m "fix: ..."` — понятное сообщение?
- [ ] `git push origin develop` — запушено?

---

## ⚠️ Частые команды

```bash
# Посмотреть статус
git status

# Посмотреть изменения
git diff

# Отменить изменения в файле
git checkout -- файл.txt

# Отменить последний коммит (мягко)
git reset --soft HEAD~1

# Отменить последний коммит (жёстко)
git reset --hard HEAD~1

# Посмотреть теги
git tag -l

# Перейти к тегу
git checkout v1.0.23

# Вернуться к develop
git checkout develop
```

---

*Последнее обновление: 13 апреля 2026*
