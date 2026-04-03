# CI/CD Best Practices

## Типичные ошибки и как их избежать

### 1. `hashFiles` в job-level `if`

❌ **Нельзя:**
```yaml
jobs:
  test:
    if: ${{ hashFiles('pyproject.toml') != '' }}  # ОШИБКА!
```

✅ **Правильно:**
```yaml
jobs:
  test:
    steps:
      - name: Check if project exists
        id: check
        run: echo "exists=${{ hashFiles('pyproject.toml') != '' }}" >> $GITHUB_OUTPUT

      - name: Run tests
        if: steps.check.outputs.exists == 'true'
        run: pytest
```

**Почему:** `hashFiles` работает только в step-level контексте.

---

### 2. Codecov без `continue-on-error`

❌ **Нельзя:**
```yaml
- uses: codecov/codecov-action@v4
```

✅ **Правильно:**
```yaml
- uses: codecov/codecov-action@v4
  if: ${{ always() }}
  continue-on-error: true
  with:
    token: ${{ secrets.CODECOV_TOKEN }}
```

**Почему:** Codecov может быть недоступен или токен не настроен — не должно ломать CI.

---

### 3. `build-*` job зависит от пропущенных тестов

❌ **Нельзя:**
```yaml
build-python:
  needs: test-python
  if: ${{ hashFiles('src/main.py') != '' }}
```

✅ **Правильно:**
```yaml
build-python:
  needs: test-python
  if: ${{ always() && hashFiles('src/main.py') != '' && needs.test-python.result == 'success' }}
```

**Почему:** Если `test-python` пропущен (skipped), `build-python` тоже будет пропущен.

---

### 4. Отсутствие Android SDK

❌ **Нельзя:**
```yaml
- name: Build APK
  run: ./gradlew assembleDebug
```

✅ **Правильно:**
```yaml
- uses: android-actions/setup-android@v3
- run: yes | sdkmanager --licenses || true
- uses: gradle/actions/setup-gradle@v4
- run: gradle assembleDebug
  working-directory: Android/MeasuringAssistant
```

**Почему:** На runner нет Android SDK и gradlew файла.

---

### 5. Использование `@master` / `@main` в `uses`

❌ **Нельзя:**
```yaml
- uses: actions/checkout@master
```

✅ **Правильно:**
```yaml
- uses: actions/checkout@v4
```

**Почему:** Теги версий стабильнее, `@master` может сломаться при обновлении.

---

## Чеклист перед коммитом workflow

- [ ] YAML валиден (`python scripts/validate_workflows.py`)
- [ ] Нет `hashFiles` в job-level `if`
- [ ] Codecov шаги имеют `continue-on-error: true`
- [ ] `build-*` job'ы проверяют `needs.*.result == 'success'`
- [ ] Все `uses` имеют теги версий (не `@master`)
- [ ] Android job включает setup-android + лицензии
- [ ] Pre-commit хуки прошли

---

## Автоматическая проверка

### Локально
```bash
python scripts/validate_workflows.py
```

### Pre-commit (автоматически)
Хук `validate-workflows` запускается при каждом коммите.

### GitHub Actions
Workflow запускается при push/PR. Если ошибка — придёт email.

---

## Структура CI/CD

```
.github/workflows/
├── ci.yml              ← Основная проверка кода
└── validate-pr.yml     ← Валидация Pull Request

scripts/
└── validate_workflows.py  ← Локальная валидация workflow
```

### Jobs в ci.yml

| Job | OS | Когда запускается |
|-----|-----|-------------------|
| `test-python` | Windows | Если есть `python/pyproject.toml` |
| `test-csharp` | Windows | Если есть `csharp/*.csproj` |
| `test-android` | Ubuntu | Если есть `Android/.../settings.gradle` |
| `build-python` | Windows | Если есть GUI + тесты прошли |
| `build-csharp` | Windows | Если есть проект + тесты прошли |

---

*Последнее обновление: 3 апреля 2026 г.*
