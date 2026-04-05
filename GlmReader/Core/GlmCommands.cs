using System.Linq;

namespace GlmReader.Core;

/// <summary>
/// Команды управления Bosch GLM 50C
///
/// Источники:
/// - docs/21_GLM_PROTOCOL_FULL_SPEC.md (HCI логи + JADX анализ APK)
/// - RemoteControlCommandsUtils.java (MeasureOn APK)
///
/// Смена типа и точки отсчёта РЕАЛИЗОВАНА через EDCOutputMessage:
/// - devMode=60 (Set Dev Mode) + remoteCtrlData = тип измерения
/// - devMode=62 (Set Distance Reference) + remoteCtrlData = точка отсчёта
/// </summary>
public static class GlmCommands
{
    /// <summary>Запуск измерения (команда 86, кнопка Measure) — SHORT формат</summary>
    public static byte[] Measure
    {
        get
        {
            // SHORT формат: CRC по [FrameMode, Command]
            // Но HCI показывает C0 56 01 00 1E AC — значит это LONG с payload [00]
            byte[] payload = { 0xC0, 0x56, 0x01, 0x00 }; // LONG: [C0][56][Len=1][00][CRC]
            byte crc = GlmProtocol.CalcCrc8(payload);
            return payload.Concat(new[] { crc }).ToArray();
        }
    }

    /// <summary>Инициализация (AutoSync ON, devMode=0)</summary>
    public static byte[] Init => GlmProtocol.CreateEdcCommand(0x40, 0x00);

    /// <summary>Запрос элемента истории (index=0, indicator=0)</summary>
    public static byte[] SyncHistory => GlmProtocol.CreateEdcCommand(0xBA, 0x00);

    // ==========================================
    // Маппинг syncMode -> EDC devMode (из APK)
    // ==========================================
    private static readonly Dictionary<int, int> SyncModeToEDC = new()
    {
        { 1, 1 },   // Прямой замер
        { 2, 4 },   // Площадь
        { 3, 7 },   // Объём
        { 4, 8 },   // Угол
        { 6, 2 },   // Непрерывный
        { 7, 10 },  // Косвенная высота
        { 8, 11 },  // Косвенная длина
        { 9, 13 },  // Двойная косвенная
        { 10, 15 }, // Поверхность стены
        { 14, 26 }  // Трапеция
    };

    /// <summary>
    /// Создание команды смены типа измерения (devMode=60)
    /// </summary>
    /// <param name="syncMode">Режим из таблицы (1=Direct, 2=Area, 3=Volume, 4=Angle, 6=Cont, 7=IndHt, 8=IndLn, 9=Dbl, 10=Wall, 14=Trap)</param>
    public static byte[] SetMeasurementType(int syncMode)
    {
        if (!SyncModeToEDC.TryGetValue(syncMode, out int edcMode))
            throw new ArgumentException($"Неизвестный syncMode: {syncMode}");

        // Header: [syncCtrl=1][keypadBypass=0][devMode=60(0b111100)] = 0xBC
        return GlmProtocol.CreateEdcCommand(0xBC, (byte)edcMode);
    }

    /// <summary>
    /// Создание команды смены точки отсчёта (devMode=62)
    /// </summary>
    /// <param name="refLevel">0=Передняя, 1=Штатив, 2=Задняя, 3=Pin</param>
    public static byte[] SetReferencePoint(int refLevel)
    {
        // Header: [syncCtrl=1][keypadBypass=0][devMode=62(0b111110)] = 0xBE
        return GlmProtocol.CreateEdcCommand(0xBE, (byte)refLevel);
    }

    /// <summary>
    /// Создание команды пакетного запроса истории (команда 81)
    /// </summary>
    /// <param name="indexFrom">Начальный индекс (0-255)</param>
    /// <param name="indexTo">Конечный индекс (0-255)</param>
    public static byte[] SyncList(int indexFrom, int indexTo)
    {
        // [C0][51][02][indexFrom][indexTo][CRC8]
        byte[] payload = { 0xC0, 0x51, 0x02, (byte)indexFrom, (byte)indexTo };
        byte crc = GlmProtocol.CalcCrc8(payload);
        return payload.Concat(new[] { crc }).ToArray();
    }

    /// <summary>
    /// Создание команды запроса настроек (команда 84)
    /// </summary>
    public static byte[] GetSettings()
    {
        // [C0][54][07][spirit][dispRot][speaker][laser][backlight][angleUnit][measUnit][CRC8]
        // Запрашиваем текущие настройки (все поля = 0)
        byte[] payload = { 0xC0, 0x54, 0x07, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
        byte crc = GlmProtocol.CalcCrc8(payload);
        return payload.Concat(new[] { crc }).ToArray();
    }

    public static string ToHexString(byte[] data) => BitConverter.ToString(data).Replace("-", " ");
}
