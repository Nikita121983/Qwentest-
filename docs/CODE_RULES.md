# Правила кода Qwentest

Стандарты разработки для всех проектов.

---

## 📁 Разделение кода

### Принцип

**«Максимально разумное разделение»** — не минимальное, не максимальное, а **разумное**.

### Критерии

| Критерий | Правильно | Неправильно |
|----------|-----------|-------------|
| **Размер файла** | 200-400 строк | <100 или >500 |
| **Размер класса** | 100-200 строк | >300 |
| **Размер функции** | 20-50 строк | >100 |
| **Ответственность** | Одна функция/класс | Несколько функций в одном |
| **Зависимости** | Только вниз по иерархии | Круговые зависимости |
| **Тестируемость** | Можно протестировать отдельно | Требует запуска всего приложения |

### Параметры функции

| Параметров | Оценка |
|------------|--------|
| ≤4 | ✅ Идеально |
| 5-6 | 🟡 Допустимо |
| ≥7 | ❌ Слишком много (используй dataclass) |

---

## 🏗️ Архитектурные слои

```
┌─────────────────────────────────────┐
│              GUI Layer              │  ← Интерфейс
│         (зависит от всех)           │
├─────────────────────────────────────┤
│              IO Layer               │  ← Ввод-вывод
│      (зависит от core, utils)       │
├─────────────────────────────────────┤
│             Core Layer              │  ← Бизнес-логика
│    (зависит только от utils)        │
├─────────────────────────────────────┤
│            Utils Layer              │  ← Утилиты
│         (без зависимостей)          │
└─────────────────────────────────────┘
```

### Правила

1. **Зависимости только вниз** — GUI → IO → Core → Utils
2. **Core не знает о GUI** — ядро независимое
3. **Utils без зависимостей** — утилиты автономны
4. **IO тонкий слой** — только чтение/запись, без логики

---

## 📝 Python правила

### 1. Type hints — обязательно

```python
# ✅ Правильно
def calculate_volume(width: float, height: float, depth: float) -> float:
    return width * height * depth

def merge_layers(slices: list[Layer], factor: int) -> list[Layer]:
    ...

# ❌ Неправильно
def calculate_volume(width, height, depth):
    return width * height * depth
```

---

### 2. Docstring — Google style

```python
# ✅ Правильно
def merge_layers(slices: list[Layer], factor: int) -> list[Layer]:
    """Объединяет слои для сглаживания.

    Args:
        slices: Список слоёв для объединения.
        factor: Коэффициент объединения (сколько в один).

    Returns:
        Новый список с объединёнными слоями.

    Raises:
        ValueError: Если factor < 1 или slices пуст.

    Example:
        >>> merge_layers([1,2,3,4,5,6], 3)
        [2, 5]
    """
    ...

# ❌ Неправильно
def merge_layers(slices, factor):
    # объединяет слои
    ...
```

---

### 3. Один класс/функция — одна ответственность

```python
# ✅ Правильно
class LayerMerger:
    """Объединение слоёв."""

    def merge(self, slices: list[Layer], factor: int) -> list[Layer]:
        ...

class LayerSmoother:
    """Сглаживание слоёв."""

    def smooth(self, layers: list[Layer]) -> list[Layer]:
        ...

# ❌ Неправильно
class LayerProcessor:
    """Всё про слои."""

    def merge(self, ...): ...
    def smooth(self, ...): ...
    def validate(self, ...): ...
    def export(self, ...): ...
```

---

### 4. Максимум 5 параметров на функцию

```python
# ✅ Правильно
@dataclass
class MergeConfig:
    factor: int
    method: str = "average"
    preserve_details: bool = True

def merge_layers(slices: list[Layer], config: MergeConfig) -> list[Layer]:
    ...

# ❌ Неправильно
def merge_layers(slices, factor, method, preserve_details,
                 min_thickness, max_thickness, callback):
    ...
```

---

### 5. Исключения — конкретно

```python
# ✅ Правильно
class LayerProcessingError(Exception):
    """Ошибка обработки слоя."""

class InvalidSliceError(LayerProcessingError):
    """Невалидный срез."""

class MergeFactorError(LayerProcessingError):
    """Некорректный коэффициент объединения."""

def merge_layers(slices: list[Layer], factor: int) -> list[Layer]:
    if factor < 1:
        raise MergeFactorError(f"factor должен быть >= 1, получено {factor}")
    if not slices:
        raise InvalidSliceError("список слоёв пуст")
    ...

# ❌ Неправильно
def merge_layers(slices, factor):
    if factor < 1 or not slices:
        raise Exception("Ошибка")
```

---

### 6. Async/await для IO

```python
# ✅ Правильно
async def read_file(path: Path) -> bytes:
    """Асинхронное чтение файла."""
    return await aiofiles.open(path, 'rb').read()

async def process_file(path: Path) -> Result:
    """Обработка файла."""
    data = await read_file(path)
    return parse(data)

# ❌ Неправильно
def read_file(path):
    with open(path, 'rb') as f:  # Блокирующий IO
        return f.read()
```

---

## 💻 C# правила

### 1. File-scoped namespaces

```csharp
// ✅ Правильно (C# 10+)
namespace MyApp.Core;

public class LayerMerger { }

// ❌ Неправильно
namespace MyApp.Core
{
    public class LayerMerger { }
}
```

---

### 2. Primary constructors

```csharp
// ✅ Правильно (C# 12)
public class LayerMerger(ILayerValidator validator, ILogger logger)
{
    public List<Layer> Merge(List<Layer> slices, int factor) { }
}

// ❌ Неправильно
public class LayerMerger
{
    private readonly ILayerValidator _validator;
    private readonly ILogger _logger;

    public LayerMerger(ILayerValidator validator, ILogger logger)
    {
        _validator = validator;
        _logger = logger;
    }
}
```

---

### 3. Pattern matching

```csharp
// ✅ Правильно
public string GetStatus(Layer layer) => layer switch
{
    { Thickness: < 0.01 } => "Слишком тонкий",
    { Thickness: > 0.5 } => "Слишком толстый",
    { IsProcessed: true } => "Обработан",
    _ => "Готов к обработке"
};

// ❌ Неправильно
public string GetStatus(Layer layer)
{
    if (layer.Thickness < 0.01) return "Слишком тонкий";
    if (layer.Thickness > 0.5) return "Слишком толстый";
    if (layer.IsProcessed) return "Обработан";
    return "Готов к обработке";
}
```

---

### 4. Nullable reference types

```csharp
// ✅ Правильно
#nullable enable

public class Layer
{
    public string Name { get; set; } = "";  // Не null
    public string? Description { get; set; }  // Может быть null
}

// ❌ Неправильно
#nullable disable

public class Layer
{
    public string Name;  // Может быть null, но компилятор не предупредит
}
```

---

### 5. Async/await везде

```csharp
// ✅ Правильно
public async Task<Layer> ProcessLayerAsync(Layer layer, CancellationToken ct = default)
{
    await Task.Delay(100, ct);
    return layer;
}

// ❌ Неправильно
public Task<Layer> ProcessLayerAsync(Layer layer)
{
    return Task.FromResult(layer);  // Fake async
}
```

---

## 🧪 Тесты правила

### 1. Названия тестов

```python
# ✅ Правильно
def test_merge_layers_with_empty_input():
    ...

def test_merge_layers_preserves_order():
    ...

def test_merge_layers_invalid_factor_raises_error():
    ...

# ❌ Неправильно
def test1():
    ...

def test_merge():  # Слишком общее
    ...
```

---

### 2. Arrange-Act-Assert

```python
# ✅ Правильно
def test_merge_layers_basic():
    # Arrange
    slices = [Layer(1), Layer(2), Layer(3), Layer(4), Layer(5), Layer(6)]
    merger = LayerMerger()

    # Act
    result = merger.merge(slices, factor=3)

    # Assert
    assert len(result) == 2
    assert result[0].thickness == 3  # Объединено 3 слоя
```

---

### 3. Тесты на граничные случаи

```python
# ✅ Правильно
def test_empty_input():
    assert merge_layers([], 3) == []

def test_factor_one():
    assert merge_layers([1, 2, 3], 1) == [1, 2, 3]

def test_remainder():
    assert merge_layers([1, 2, 3, 4], 3) == [2, 4]  # Остаток

def test_invalid_factor_zero():
    with pytest.raises(ValueError):
        merge_layers([1, 2, 3], 0)

def test_invalid_factor_negative():
    with pytest.raises(ValueError):
        merge_layers([1, 2, 3], -1)
```

---

## 📏 Метрики качества

| Метрика | Цель | Критично |
|---------|------|----------|
| **Покрытие тестами** | >80% | <50% |
| **Строк на файл** | 100-500 | >1000 |
| **Параметров на функцию** | ≤5 | >7 |
| **Сложность функции (cyclomatic)** | ≤10 | >20 |
| **Время сборки** | <1 мин | >5 мин |

---

## 🔧 Инструменты проверки

### Python

```bash
# Линтер
ruff check src/

# Форматтер
black --check src/

# Type checker
mypy src/

# Тесты с покрытием
pytest --cov=src --cov-fail-under=80

# Всё вместе
ruff check src/ && black --check src/ && mypy src/ && pytest
```

### C#

```bash
# Форматтер
dotnet format --verify-no-changes

# Тесты
dotnet test --collect:"XPlat Code Coverage"

# Анализ
dotnet build /warnaserror
```

---

*Правила обновлены: 2 апреля 2026 г.*
