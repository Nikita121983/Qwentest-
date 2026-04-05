using System.Text;
using GlmReader.Models;

namespace GlmReader.Core;

/// <summary>
/// Парсинг пакетов Bosch GLM 50C по спецификации
/// </summary>
public static class GlmProtocol
{
    private const byte StartByte = 0xC0;
    private const byte DataPrefix = 0x10;
    private const byte Constant71 = 0x71;

    /// <summary>
    /// Попытка распознать и распарсить пакет данных
    /// </summary>
    public static Measurement? TryParseDataPacket(byte[] data, out string? logMessage)
    {
        logMessage = null;

        // Минимальная длина: C0 55 10 + DevModeRef(1) + DevStatus(1) + SeqNum(1) + 0x71(1) + Result(4) + Comp1(4) + Comp2(4) = 20
        if (data.Length < 20 || data[0] != 0xC0 || data[1] != 0x55 || data[2] != 0x10 || data[6] != 0x71)
        {
            // Проверяем heartbeat (EDCInputMessage с devMode=60)
            // C0 55 02 F1 [mode] [CRC] — рулетка сообщает свой режим
            if (data.Length >= 7 && data[0] == 0xC0 && data[1] == 0x55 && data[2] == 0x02 && data[3] == 0xF1)
            {
                int mode = data[4]; // текущий режим рулетки
                logMessage = $"[Heartbeat] Mode={mode}, Raw={BitConverter.ToString(data)}";
                return null;
            }
            logMessage = $"[Unknown] {BitConverter.ToString(data)}";
            return null;
        }

        // Структура входящего пакета (из EDCMessageFactory.java):
        // [0]   C0 - FrameMode
        // [1]   55 - Command
        // [2]   10 - Length (16 байт payload)
        // [3]   DevModeRef: [refEdge:2][devMode:6]
        // [4]   DevStatus: [laser:1][tempWarn:1][battWarn:1][configUnits:1][deviceStatus:4]
        // [5]   Seq Number (NN)
        // [6]   0x71 (constant)
        // [7-10]  Result (float32 LE)
        // [11-14] Comp1 (float32 LE)
        // [15-18] Comp2 (float32 LE)
        // [19+]   Tail

        byte devModeRef = data[3];
        byte devStatus = data[4];

        int refEdge = devModeRef & 0x03;         // биты 0-1
        int devMode = (devModeRef >> 2) & 0x3F;   // биты 2-7
        bool laserOn = (devStatus & 0x01) != 0;
        int deviceStatus = (devStatus >> 4) & 0x0F;

        var type = MapDevModeToType(devMode);
        var seqNum = data[5];
        var result = BitConverter.ToSingle(data, 7);
        var comp1 = BitConverter.ToSingle(data, 11);
        var comp2 = BitConverter.ToSingle(data, 15);
        var measID = 0;

        // Извлекаем MeasID если доступен (следующие 2 байта после comp2)
        if (data.Length >= 22)
        {
            measID = BitConverter.ToUInt16(data, 19);
        }

        var refPoint = MapRefEdgeToPoint(refEdge);

        var measurement = new Measurement
        {
            Timestamp = DateTime.Now,
            Type = type,
            ReferencePoint = refPoint,
            Result = result,
            LaserOn = laserOn,
            Status = (byte)deviceStatus,
            SequenceNumber = seqNum,
            MeasID = measID,
            RawPacket = (byte[])data.Clone()
        };

        // Заполняем компоненты в зависимости от типа
        if (type == MeasurementType.IndirectHeight || type == MeasurementType.IndirectLength)
        {
            measurement = new Measurement
            {
                Timestamp = measurement.Timestamp, Type = measurement.Type, ReferencePoint = measurement.ReferencePoint,
                Result = measurement.Result, ComponentA = comp1, Angle = comp2, LaserOn = measurement.LaserOn,
                Status = measurement.Status, SequenceNumber = measurement.SequenceNumber, MeasID = measurement.MeasID,
                RawPacket = measurement.RawPacket, Tail = data.Length > 19 ? data[19..] : null
            };
        }
        else if (type == MeasurementType.DoubleIndirect)
        {
            measurement = new Measurement
            {
                Timestamp = measurement.Timestamp, Type = measurement.Type, ReferencePoint = measurement.ReferencePoint,
                Result = measurement.Result, ComponentB = comp1, Angle = comp2, LaserOn = measurement.LaserOn,
                Status = measurement.Status, SequenceNumber = measurement.SequenceNumber, MeasID = measurement.MeasID,
                RawPacket = measurement.RawPacket, Tail = data.Length > 19 ? data[19..] : null
            };
        }

        return measurement;
    }

    /// <summary>
    /// Маппинг devMode из EDCInputMessage → MeasurementType
    /// </summary>
    private static MeasurementType MapDevModeToType(int devMode) => devMode switch
    {
        1 => MeasurementType.Direct,
        2 => MeasurementType.Continuous,
        3 or 4 => MeasurementType.Area,
        5 or 6 or 7 => MeasurementType.Volume,
        8 or 9 => MeasurementType.Angle,
        10 => MeasurementType.IndirectHeight,
        11 => MeasurementType.IndirectLength,
        12 or 13 => MeasurementType.DoubleIndirect,
        14 or 15 => MeasurementType.Area, // Wall Area
        16 or 17 => MeasurementType.Direct, // Calculated Distance
        18 or 19 => MeasurementType.Area, // Calculated Area
        20 or 21 => MeasurementType.Volume, // Calculated Volume
        22 or 23 => MeasurementType.Angle, // Level
        24 or 25 or 26 => MeasurementType.DoubleIndirect, // Trapezoid
        60 => MeasurementType.Unknown, // Set Dev Mode
        62 => MeasurementType.Unknown, // Set Distance Reference
        _ => MeasurementType.Unknown
    };

    /// <summary>
    /// Маппинг refEdge → ReferencePoint
    /// Из EDCInputMessage: 0=Front, 1=Tripod, 2=Rear, 3=Pin
    /// </summary>
    private static ReferencePoint MapRefEdgeToPoint(int refEdge) => refEdge switch
    {
        0 => ReferencePoint.FrontEdge,
        1 => ReferencePoint.TripodAxis,
        2 => ReferencePoint.RearEdge,
        3 => ReferencePoint.Unknown, // Pin — нет в нашем enum
        _ => ReferencePoint.Unknown
    };

    /// <summary>
    /// Форматирование байтов для логирования
    /// </summary>
    public static string FormatBytes(byte[] data) => BitConverter.ToString(data).Replace("-", " ");

    /// <summary>
    /// Расчёт CRC8 (полином 0xA6, Init 0xAA)
    /// Источник: Crc.java из APK MeasureOn
    /// CRC8 вычисляется по ВСЕМ байтам фрейма (FrameMode + Command + Len + Payload)
    /// </summary>
    public static byte CalcCrc8(byte[] data)
    {
        byte crc = 0xAA; // Initial value (EN_CRC8_INITIAL_VALUE)
        foreach (byte b in data)
        {
            byte b2 = crc;
            for (int i = 0; i < 8; i++)
            {
                bool msb = (b2 & 0x80) != 0;
                bool bit = ((b >> (7 - i)) & 1) == 1;
                b2 = (byte)((msb != bit) ? ((b2 << 1) ^ 0xA6) : (b2 << 1));
            }
            crc = b2;
        }
        return crc;
    }

    /// <summary>
    /// Создание EDC-команды с правильным CRC (LONG формат)
    /// Формат: [FrameMode:0xC0][Command:0x55][PayloadLen:1][Payload:2][CRC8:1]
    /// CRC8 вычисляется по [FrameMode, Command, PayloadLen, Payload...]
    /// Источник: MtFrameByteWriter.java
    /// </summary>
    public static byte[] CreateEdcCommand(byte header, byte remoteCtrlData)
    {
        byte payloadLen = 0x02; // 2 байта payload
        byte[] payload = { 0xC0, 0x55, payloadLen, header, remoteCtrlData };
        byte crc = CalcCrc8(payload);
        return payload.Concat(new[] { crc }).ToArray();
    }
}
