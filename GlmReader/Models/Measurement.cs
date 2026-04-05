namespace GlmReader.Models;

/// <summary>
/// Типы измерений Bosch GLM 50C
/// </summary>
public enum MeasurementType
{
    Unknown = 0,
    Direct = 0x06,           // Прямой замер
    IndirectHeight = 0x2A,   // Косвенная высота
    IndirectLength = 0x2E,   // Косвенная длина
    DoubleIndirect = 0x36,   // Двойная косвенная
    Area = 0x3E,             // Площадь
    Volume = 0x1E,           // Объём
    Angle = 0x21,            // Угол
    Continuous = 0x0A,       // Непрерывный замер
    Reference = 0xF2         // Референс
}

/// <summary>
/// Точка отсчёта (Reference Point) — ЗАГЛУШКА
/// Пока не расшифровано в протоколе. Точная локализация флага неизвестна.
/// </summary>
public enum ReferencePoint
{
    Unknown = 0,
    RearEdge = 1,      // Задняя грань (по умолчанию)
    TripodAxis = 2,    // Ось штатива
    FrontEdge = 3      // Передняя грань
}

/// <summary>
/// Модель данных измерения
/// </summary>
public class Measurement
{
    public DateTime Timestamp { get; init; }
    public MeasurementType Type { get; init; }
    public ReferencePoint ReferencePoint { get; init; } = ReferencePoint.Unknown;
    public float Result { get; init; }        // Основной результат (метры)
    public float? ComponentA { get; init; }   // Компонент a (метры)
    public float? ComponentB { get; init; }   // Компонент b (метры)
    public float? Angle { get; init; }        // Угол (градусы)
    public byte SequenceNumber { get; init; }
    public byte Status { get; init; }
    public bool LaserOn { get; init; }        // Лазер вкл/выкл
    public int MeasID { get; init; }          // ID замера
    public byte[]? Tail { get; init; }        // Байты 19-30 (метаданные)
    public byte[]? RawPacket { get; init; }   // Сырой пакет для отладки

    public float ResultMm => Result * 1000f;
    public float? ComponentAMm => ComponentA * 1000f;
    public float? ComponentBMm => ComponentB * 1000f;

    public override string ToString()
    {
        var baseStr = $"[{Type}] {ResultMm:F1} мм";
        if (ReferencePoint != ReferencePoint.Unknown) baseStr += $" | ref={ReferencePoint}";
        if (LaserOn) baseStr += " | 🔴";
        if (ComponentA.HasValue) baseStr += $" | a={ComponentAMm:F1} мм";
        if (ComponentB.HasValue) baseStr += $" | b={ComponentBMm:F1} мм";
        if (Angle.HasValue) baseStr += $" | angle={Angle.Value:F1}°";
        return baseStr;
    }
}
