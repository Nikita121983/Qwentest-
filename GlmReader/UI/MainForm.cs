using System.Collections.Generic;
using GlmReader.Core;
using GlmReader.Models;

namespace GlmReader.UI;

public partial class MainForm : Form
{
    private readonly BleManager _bleManager;
    private readonly List<Measurement> _measurements = new();
    private float? _lastDirectComponentA; // Для типа 0x36 (двойная косвенная)

    // UI элементы
    private readonly ComboBox _cmbDevices = new();
    private readonly Button _btnScan = new();
    private readonly TextBox _txtMacAddress = new();
    private readonly Button _btnConnect = new();
    private readonly Button _btnDisconnect = new();
    private readonly Label _lblStatus = new();
    private readonly ComboBox _cmbMeasurementType = new();
    private readonly ComboBox _cmbReferencePoint = new();
    private readonly Button _btnMeasure = new();
    private readonly Button _btnSyncHistory = new();
    private readonly Label _lblLastValue = new();
    private readonly DataGridView _gridMeasurements = new();
    private readonly TextBox _txtLog = new();
    private readonly Button _btnExportCsv = new();

    public MainForm()
    {
        try
        {
            _bleManager = new BleManager();
            InitializeUi();
            SubscribeEvents();
        }
        catch (Exception ex)
        {
            MessageBox.Show($"Ошибка инициализации:\n{ex}", "GlmReader",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
    }

    private void InitializeUi()
    {
        Text = "Bosch GLM 50C Reader";
        Size = new Size(1000, 750);
        MinimumSize = new Size(800, 550);
        StartPosition = FormStartPosition.CenterScreen;
        TopMost = true; // Поверх всех окон
        Padding = new Padding(10);

        // Панель управления
        var pnlControl = new TableLayoutPanel
        {
            Dock = DockStyle.Top,
            Height = 100,
            ColumnCount = 5,
            RowCount = 2,
            Margin = new Padding(0, 0, 0, 10)
        };
        pnlControl.ColumnStyles.Add(new ColumnStyle(SizeType.Absolute, 120));
        pnlControl.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100));
        pnlControl.ColumnStyles.Add(new ColumnStyle(SizeType.Absolute, 120));
        pnlControl.ColumnStyles.Add(new ColumnStyle(SizeType.Absolute, 120));
        pnlControl.ColumnStyles.Add(new ColumnStyle(SizeType.Absolute, 120));
        pnlControl.RowStyles.Add(new RowStyle(SizeType.Absolute, 45));
        pnlControl.RowStyles.Add(new RowStyle(SizeType.Absolute, 45));

        _btnScan.Text = "Поиск";
        _btnScan.Dock = DockStyle.Fill;
        pnlControl.Controls.Add(_btnScan, 0, 0);

        _cmbDevices.DropDownStyle = ComboBoxStyle.DropDownList;
        _cmbDevices.Dock = DockStyle.Fill;
        pnlControl.Controls.Add(_cmbDevices, 1, 0);

        _txtMacAddress.Text = "001343D5215D";
        _txtMacAddress.Dock = DockStyle.Fill;
        _txtMacAddress.Margin = new Padding(5, 5, 0, 0);
        pnlControl.Controls.Add(_txtMacAddress, 2, 0);

        _btnConnect.Text = "Подключиться";
        _btnConnect.Dock = DockStyle.Fill;
        pnlControl.Controls.Add(_btnConnect, 3, 0);

        _btnDisconnect.Text = "Отключиться";
        _btnDisconnect.Dock = DockStyle.Fill;
        _btnDisconnect.Enabled = false;
        pnlControl.Controls.Add(_btnDisconnect, 3, 0);

        _lblStatus.Text = "Готово";
        _lblStatus.AutoSize = false;
        _lblStatus.Dock = DockStyle.Fill;
        _lblStatus.TextAlign = ContentAlignment.MiddleLeft;
        pnlControl.Controls.Add(_lblStatus, 4, 0);
        pnlControl.SetColumnSpan(_lblStatus, 1);

        // Панель настроек (тип измерения + точка отсчёта)
        var pnlSettings = new TableLayoutPanel
        {
            Dock = DockStyle.Top,
            Height = 45,
            ColumnCount = 4,
            RowCount = 1,
            Margin = new Padding(0, 0, 0, 10)
        };
        pnlSettings.ColumnStyles.Add(new ColumnStyle(SizeType.Absolute, 120));
        pnlSettings.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 50));
        pnlSettings.ColumnStyles.Add(new ColumnStyle(SizeType.Absolute, 100));
        pnlSettings.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 50));

        var lblType = new Label { Text = "Тип измерения:", Dock = DockStyle.Fill, TextAlign = ContentAlignment.MiddleLeft, AutoSize = false };
        _cmbMeasurementType.DropDownStyle = ComboBoxStyle.DropDownList;
        _cmbMeasurementType.Dock = DockStyle.Fill;
        _cmbMeasurementType.Enabled = true; // Теперь можно переключать!
        _cmbMeasurementType.Items.AddRange(new object[]
        {
            "Прямой замер",
            "Косвенная высота",
            "Косвенная длина",
            "Двойная косвенная",
            "Площадь",
            "Объём",
            "Угол",
            "Непрерывный"
        });
        _cmbMeasurementType.SelectedIndex = 0;

        var lblRef = new Label { Text = "Точка отсч.:", Dock = DockStyle.Fill, TextAlign = ContentAlignment.MiddleLeft, AutoSize = false };
        _cmbReferencePoint.DropDownStyle = ComboBoxStyle.DropDownList;
        _cmbReferencePoint.Dock = DockStyle.Fill;
        _cmbReferencePoint.Enabled = true; // Теперь можно переключать!
        _cmbReferencePoint.Items.AddRange(new object[]
        {
            "Задняя грань",
            "Ось штатива",
            "Передняя грань"
        });
        _cmbReferencePoint.SelectedIndex = 0;

        pnlSettings.Controls.Add(lblType, 0, 0);
        pnlSettings.Controls.Add(_cmbMeasurementType, 1, 0);
        pnlSettings.Controls.Add(lblRef, 2, 0);
        pnlSettings.Controls.Add(_cmbReferencePoint, 3, 0);

        // Панель управления рулеткой
        var pnlCommands = new FlowLayoutPanel
        {
            Dock = DockStyle.Top,
            Height = 45,
            Margin = new Padding(0, 0, 0, 10)
        };

        _btnMeasure.Text = "Измерить";
        _btnMeasure.Enabled = false;
        _btnMeasure.Size = new Size(120, 35);
        pnlCommands.Controls.Add(_btnMeasure);

        _btnSyncHistory.Text = "Синхронизировать историю";
        _btnSyncHistory.Enabled = false;
        _btnSyncHistory.Size = new Size(200, 35);
        pnlCommands.Controls.Add(_btnSyncHistory);

        _btnExportCsv.Text = "Сохранить в CSV";
        _btnExportCsv.Size = new Size(150, 35);
        pnlCommands.Controls.Add(_btnExportCsv);

        // Последнее значение
        _lblLastValue.Text = "—";
        _lblLastValue.Font = new Font("Segoe UI", 48, FontStyle.Bold);
        _lblLastValue.TextAlign = ContentAlignment.MiddleCenter;
        _lblLastValue.Dock = DockStyle.Top;
        _lblLastValue.Height = 80;
        _lblLastValue.Margin = new Padding(0, 0, 0, 10);

        // Таблица измерений
        _gridMeasurements.Dock = DockStyle.Fill;
        _gridMeasurements.AllowUserToAddRows = false;
        _gridMeasurements.ReadOnly = true;
        _gridMeasurements.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
        _gridMeasurements.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;

        _gridMeasurements.Columns.Add("Time", "Время");
        _gridMeasurements.Columns.Add("Type", "Тип");
        _gridMeasurements.Columns.Add("RefPoint", "Точка отсч.");
        _gridMeasurements.Columns.Add("Result", "Результат (мм)");
        _gridMeasurements.Columns.Add("ComponentA", "Комп. A (мм)");
        _gridMeasurements.Columns.Add("Angle", "Угол (°)");
        _gridMeasurements.Columns.Add("SeqNum", "№");

        // Лог
        _txtLog.Dock = DockStyle.Bottom;
        _txtLog.Height = 150;
        _txtLog.Multiline = true;
        _txtLog.ScrollBars = ScrollBars.Vertical;
        _txtLog.ReadOnly = true;
        _txtLog.Font = new Font("Consolas", 9);

        // Собираем форму
        Controls.Add(_gridMeasurements);
        Controls.Add(_txtLog);
        Controls.Add(_lblLastValue);
        Controls.Add(pnlCommands);
        Controls.Add(pnlSettings);
        Controls.Add(pnlControl);
    }

    private void SubscribeEvents()
    {
        _btnScan.Click += (s, e) => ScanDevices();
        _btnConnect.Click += (s, e) => ConnectToDevice();
        _btnDisconnect.Click += (s, e) => DisconnectFromDevice();
        _btnMeasure.Click += async (s, e) => await StartMeasurement();
        _btnSyncHistory.Click += async (s, e) => await SyncHistory();
        _btnExportCsv.Click += (s, e) => ExportToCsv();
        _cmbMeasurementType.SelectedIndexChanged += async (s, e) => await OnMeasurementTypeChanged();
        _cmbReferencePoint.SelectedIndexChanged += async (s, e) => await OnReferencePointChanged();

        _bleManager.DevicesFound += OnDevicesFound;
        _bleManager.DataReceived += OnDataReceived;
        _bleManager.StatusChanged += OnStatusChanged;
        _bleManager.Disconnected += OnDisconnected;
    }

    private void ScanDevices()
    {
        _cmbDevices.Items.Clear();
        _bleManager.DiscoveredDevices.Clear();
        _bleManager.StartScan();

        // Автостоп через 10 секунд
        Task.Delay(10000).ContinueWith(_ => Invoke(_bleManager.StopScan));
    }

    private async void ConnectToDevice()
    {
        // Если выбрано устройство из списка — пробуем через DeviceInfo
        if (_cmbDevices.SelectedItem is BleDeviceInfo device)
        {
            var connected = await _bleManager.ConnectAsync(device.Id);
            if (connected)
            {
                _btnConnect.Enabled = false;
                _btnDisconnect.Enabled = true;
                _btnMeasure.Enabled = true;
                _btnSyncHistory.Enabled = true;
                _cmbDevices.Enabled = false;
                _btnScan.Enabled = false;
            }
            return;
        }

        // Иначе — пробуем по MAC адресу из текстового поля
        var macText = _txtMacAddress.Text.Trim().Replace(":", "").Replace("-", "").Replace(" ", "");
        if (macText.Length != 12)
        {
            Log($"Неверный MAC адрес: {_txtMacAddress.Text}. Формат: 001343D5215D");
            return;
        }

        try
        {
            var addr = ulong.Parse(macText, System.Globalization.NumberStyles.HexNumber);
            var connected = await _bleManager.ConnectByAddressAsync(addr);
            if (connected)
            {
                _btnConnect.Enabled = false;
                _btnDisconnect.Enabled = true;
                _btnMeasure.Enabled = true;
                _btnSyncHistory.Enabled = true;
                _cmbDevices.Enabled = false;
                _btnScan.Enabled = false;
            }
        }
        catch (Exception ex)
        {
            Log($"Ошибка: {ex.Message}");
        }
    }

    private void DisconnectFromDevice()
    {
        _bleManager.Disconnect();
    }

    private async Task StartMeasurement()
    {
        await _bleManager.WriteCommandAsync(GlmCommands.Measure);
    }

    private async Task SyncHistory()
    {
        await _bleManager.WriteCommandAsync(GlmCommands.SyncHistory);
    }

    // syncMode mapping из RemoteControlCommandsUtils.java
    private static readonly Dictionary<int, int> ComboBoxIndexToSyncMode = new()
    {
        { 0, 1 },  // Прямой замер
        { 1, 7 },  // Косвенная высота
        { 2, 8 },  // Косвенная длина
        { 3, 9 },  // Двойная косвенная
        { 4, 2 },  // Площадь -> Area (syncMode=2 -> EDC=4)
        { 5, 3 },  // Объём -> Volume (syncMode=3 -> EDC=7)
        { 6, 4 },  // Угол (syncMode=4 -> EDC=8)
        { 7, 6 }   // Непрерывный (syncMode=6 -> EDC=2)
    };

    private async Task OnMeasurementTypeChanged()
    {
        if (!_bleManager.IsConnected) return;
        if (!ComboBoxIndexToSyncMode.TryGetValue(_cmbMeasurementType.SelectedIndex, out int syncMode))
        {
            Log($"Неизвестный тип измерения (index={_cmbMeasurementType.SelectedIndex})");
            return;
        }

        var cmd = GlmCommands.SetMeasurementType(syncMode);
        Log($"Смена типа → {GlmCommands.ToHexString(cmd)}");
        await _bleManager.WriteCommandAsync(cmd);
    }

    private async Task OnReferencePointChanged()
    {
        if (!_bleManager.IsConnected) return;
        // 0=Передняя, 1=Штатив, 2=Задняя, 3=Pin
        int refLevel = _cmbReferencePoint.SelectedIndex switch
        {
            0 => 2, // Задняя грань (по умолчанию в APK=2)
            1 => 1, // Штатив
            2 => 0, // Передняя грань
            _ => 2
        };

        var cmd = GlmCommands.SetReferencePoint(refLevel);
        Log($"Точка отсчёта → {GlmCommands.ToHexString(cmd)} (refLevel={refLevel})");
        await _bleManager.WriteCommandAsync(cmd);
    }

    private void OnDevicesFound(BleDeviceInfo[] devices)
    {
        Invoke(() =>
        {
            _cmbDevices.Items.Clear();
            foreach (var device in devices)
            {
                _cmbDevices.Items.Add(device);
                // Авто-заполнение MAC адреса первого устройства
                if (string.IsNullOrEmpty(_txtMacAddress.Text) && device.Address > 0)
                    _txtMacAddress.Text = device.Address.ToString("X12");
            }
            if (_cmbDevices.Items.Count > 0)
                _cmbDevices.SelectedIndex = 0;
        });
    }

    private void OnDataReceived(byte[] data)
    {
        Invoke(() =>
        {
            Log($"RX: {GlmProtocol.FormatBytes(data)}");

            var measurement = GlmProtocol.TryParseDataPacket(data, out var logMessage);

            if (logMessage != null)
                Log(logMessage);

            if (measurement != null)
            {
                // Обновляем индикаторы из пакета
                var refIdx = measurement.ReferencePoint switch
                {
                    Models.ReferencePoint.RearEdge => 0,
                    Models.ReferencePoint.TripodAxis => 1,
                    Models.ReferencePoint.FrontEdge => 2,
                    _ => 0
                };
                _cmbReferencePoint.SelectedIndex = refIdx;

                var typeIdx = measurement.Type switch
                {
                    MeasurementType.Direct => 0,
                    MeasurementType.IndirectHeight => 1,
                    MeasurementType.IndirectLength => 2,
                    MeasurementType.DoubleIndirect => 3,
                    MeasurementType.Area => 4,
                    MeasurementType.Volume => 5,
                    MeasurementType.Angle => 6,
                    MeasurementType.Continuous => 7,
                    _ => -1
                };
                if (typeIdx >= 0) _cmbMeasurementType.SelectedIndex = typeIdx;

                // Для двойной косвенной сохраняем компонент A из предыдущего прямого замера
                if (measurement.Type == MeasurementType.Direct)
                {
                    _lastDirectComponentA = measurement.Result;
                }
                else if (measurement.Type == MeasurementType.DoubleIndirect && _lastDirectComponentA.HasValue)
                {
                    measurement = new Measurement
                    {
                        Timestamp = measurement.Timestamp,
                        Type = measurement.Type,
                        ReferencePoint = measurement.ReferencePoint,
                        Result = measurement.Result,
                        ComponentA = _lastDirectComponentA.Value,
                        ComponentB = measurement.ComponentB,
                        Angle = measurement.Angle,
                        SequenceNumber = measurement.SequenceNumber,
                        Status = measurement.Status,
                        Tail = measurement.Tail
                    };
                }

                _measurements.Add(measurement);
                AddMeasurementToGrid(measurement);
                _lblLastValue.Text = $"{measurement.ResultMm:F1} мм";
                Log($"Измерение: {measurement}");
            }
        });
    }

    private void OnStatusChanged(string message)
    {
        Invoke(() =>
        {
            _lblStatus.Text = message;
            Log(message);
        });
    }

    private void OnDisconnected()
    {
        Invoke(() =>
        {
            _btnConnect.Enabled = true;
            _btnDisconnect.Enabled = false;
            _btnMeasure.Enabled = false;
            _btnSyncHistory.Enabled = false;
            _cmbDevices.Enabled = true;
            _btnScan.Enabled = true;
        });
    }

    private void AddMeasurementToGrid(Measurement m)
    {
        var refPointStr = m.ReferencePoint switch
        {
            ReferencePoint.RearEdge => "Задняя",
            ReferencePoint.TripodAxis => "Штатив",
            ReferencePoint.FrontEdge => "Передняя",
            _ => "—"
        };

        _gridMeasurements.Rows.Add(
            m.Timestamp.ToString("HH:mm:ss.fff"),
            m.Type,
            refPointStr,
            $"{m.ResultMm:F1}",
            m.ComponentAMm?.ToString("F1") ?? "—",
            m.Angle?.ToString("F1") ?? "—",
            m.SequenceNumber
        );
        _gridMeasurements.FirstDisplayedScrollingRowIndex = _gridMeasurements.Rows.Count - 1;
    }

    private void ExportToCsv()
    {
        if (_measurements.Count == 0)
        {
            Log("Нет данных для экспорта");
            return;
        }

        using var dialog = new SaveFileDialog
        {
            Filter = "CSV файлы|*.csv|Все файлы|*.*",
            FileName = $"GLM_Measurements_{DateTime.Now:yyyyMMdd_HHmmss}.csv"
        };

        if (dialog.ShowDialog() != DialogResult.OK) return;

        try
        {
            using var writer = new StreamWriter(dialog.FileName);
            writer.WriteLine("Время,Тип,ТочкаОтсчёта,Результат(мм),Комп.A(мм),Комп.B(мм),Угол(°),Последовательность");

            foreach (var m in _measurements)
            {
                var refPointStr = m.ReferencePoint switch
                {
                    ReferencePoint.RearEdge => "Задняя",
                    ReferencePoint.TripodAxis => "Штатив",
                    ReferencePoint.FrontEdge => "Передняя",
                    _ => ""
                };

                writer.WriteLine(
                    $"{m.Timestamp:HH:mm:ss.fff}," +
                    $"{m.Type}," +
                    $"{refPointStr}," +
                    $"{m.ResultMm:F1}," +
                    $"{(m.ComponentAMm?.ToString("F1") ?? "")}," +
                    $"{(m.ComponentBMm?.ToString("F1") ?? "")}," +
                    $"{(m.Angle?.ToString("F1") ?? "")}," +
                    $"{m.SequenceNumber}");
            }

            Log($"Экспорт в {dialog.FileName}: {_measurements.Count} записей");
        }
        catch (Exception ex)
        {
            Log($"Ошибка экспорта: {ex.Message}");
        }
    }

    private void Log(string message)
    {
        _txtLog.AppendText($"[{DateTime.Now:HH:mm:ss}] {message}{Environment.NewLine}");
        // Автопрокрутка
        _txtLog.SelectionStart = _txtLog.Text.Length;
        _txtLog.ScrollToCaret();
    }

    protected override void OnFormClosing(FormClosingEventArgs e)
    {
        _bleManager.Dispose();
        base.OnFormClosing(e);
    }
}
