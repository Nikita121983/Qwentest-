using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Devices.Bluetooth;
using Windows.Devices.Bluetooth.Advertisement;
using Windows.Devices.Bluetooth.GenericAttributeProfile;
using Windows.Storage.Streams;

namespace GlmReader.Core;

public class BleDeviceInfo
{
    public string Id { get; init; } = string.Empty;
    public string Name { get; init; } = string.Empty;
    public ulong Address { get; init; }
    public short SignalStrength { get; init; }
    public override string ToString() => $"{Name} ({Address:X12})";
}

public class BleManager : IDisposable
{
    private static readonly Guid ServiceUuid = new("02A6C0D0-0451-4000-B000-FB3210111989");
    private static readonly Guid CharacteristicUuid = new("02A6C0D1-0451-4000-B000-FB3210111989");

    private BluetoothLEAdvertisementWatcher? _watcher;
    private BluetoothLEDevice? _device;
    private GattCharacteristic? _characteristic;
    private readonly Dictionary<ulong, BleDeviceInfo> _discovered = new();

    public event Action<BleDeviceInfo[]>? DevicesFound;
    public event Action<byte[]>? DataReceived;
    public event Action<string>? StatusChanged;
    public event Action? Disconnected;

    public bool IsConnected { get; private set; }
    public List<BleDeviceInfo> DiscoveredDevices => _discovered.Values.ToList();

    public void StartScan()
    {
        _discovered.Clear();
        _watcher = new BluetoothLEAdvertisementWatcher { ScanningMode = BluetoothLEScanningMode.Active };
        _watcher.Received += OnAdvertisementReceived;
        _watcher.Start();
        StatusChanged?.Invoke("Сканирование...");
        Task.Delay(15000).ContinueWith(_ => StopScan());
    }

    public void StopScan()
    {
        if (_watcher != null)
        {
            _watcher.Received -= OnAdvertisementReceived;
            _watcher.Stop();
            _watcher = null;
        }
    }

    public async Task<bool> ConnectByAddressAsync(ulong address)
    {
        return await ConnectToDeviceAsync(address);
    }

    public async Task<bool> ConnectAsync(string deviceId)
    {
        var match = System.Text.RegularExpressions.Regex.Match(deviceId, @"([0-9A-Fa-f]{12})");
        if (!match.Success)
        {
            StatusChanged?.Invoke("Не удалось определить адрес");
            return false;
        }
        var addr = ulong.Parse(match.Groups[1].Value, System.Globalization.NumberStyles.HexNumber);
        return await ConnectToDeviceAsync(addr);
    }

    private async Task<bool> ConnectToDeviceAsync(ulong address)
    {
        try
        {
            StatusChanged?.Invoke($"Подключение к {address:X12}...");
            StopScan();

            _device = await BluetoothLEDevice.FromBluetoothAddressAsync(address);
            if (_device == null)
            {
                StatusChanged?.Invoke("Устройство не отвечает. Убедись что рулетка включена");
                return false;
            }

            StatusChanged?.Invoke($"Устройство: {_device.Name}, статус: {_device.ConnectionStatus}");
            _device.ConnectionStatusChanged += OnConnectionStatusChanged;

            // Перечисляем сервисы
            var services = await _device.GetGattServicesAsync(BluetoothCacheMode.Uncached);
            StatusChanged?.Invoke($"Сервисов: {services.Services.Count}");

            if (services.Status != GattCommunicationStatus.Success)
            {
                StatusChanged?.Invoke($"Ошибка: {services.Status}");
                return false;
            }

            GattDeviceService? targetService = null;
            foreach (var s in services.Services)
            {
                StatusChanged?.Invoke($"  {s.Uuid}");
                if (s.Uuid == ServiceUuid) targetService = s;
            }

            if (targetService == null)
            {
                StatusChanged?.Invoke("Сервис GLM не найден");
                return false;
            }

            var chars = await targetService.GetCharacteristicsAsync(BluetoothCacheMode.Uncached);
            foreach (var c in chars.Characteristics)
            {
                if (c.Uuid == CharacteristicUuid) _characteristic = c;
            }

            if (_characteristic == null)
            {
                StatusChanged?.Invoke("Характеристика не найдена");
                return false;
            }

            _characteristic.ValueChanged += OnCharacteristicValueChanged;
            await _characteristic.WriteClientCharacteristicConfigurationDescriptorAsync(
                GattClientCharacteristicConfigurationDescriptorValue.Notify);

            IsConnected = true;
            StatusChanged?.Invoke($"Подключено: {_device.Name}");
            await WriteCommandAsync(GlmCommands.Init);
            return true;
        }
        catch (Exception ex)
        {
            StatusChanged?.Invoke($"Ошибка: {ex.Message}");
            return false;
        }
    }

    private void OnAdvertisementReceived(BluetoothLEAdvertisementWatcher sender, BluetoothLEAdvertisementReceivedEventArgs args)
    {
        var name = args.Advertisement.LocalName;
        if (string.IsNullOrEmpty(name) || !name.Contains("Bosch", StringComparison.OrdinalIgnoreCase))
            return;

        lock (_discovered)
        {
            if (_discovered.ContainsKey(args.BluetoothAddress)) return;

            var info = new BleDeviceInfo
            {
                Id = args.BluetoothAddress.ToString("X12"),
                Name = name,
                Address = args.BluetoothAddress,
                SignalStrength = args.RawSignalStrengthInDBm
            };
            _discovered[args.BluetoothAddress] = info;
            DevicesFound?.Invoke(_discovered.Values.ToArray());
        }
    }

    public void Disconnect()
    {
        if (_characteristic != null) _characteristic.ValueChanged -= OnCharacteristicValueChanged;
        if (_device != null)
        {
            _device.ConnectionStatusChanged -= OnConnectionStatusChanged;
            _device.Dispose();
        }
        _characteristic = null;
        _device = null;
        IsConnected = false;
        StatusChanged?.Invoke("Отключено");
        Disconnected?.Invoke();
    }

    public async Task<bool> WriteCommandAsync(byte[] command)
    {
        if (_characteristic == null || !IsConnected) return false;
        try
        {
            var writer = new DataWriter();
            writer.WriteBytes(command);
            var result = await _characteristic.WriteValueAsync(writer.DetachBuffer(), GattWriteOption.WriteWithResponse);
            if (result == GattCommunicationStatus.Success)
            {
                StatusChanged?.Invoke($"TX: {BitConverter.ToString(command).Replace("-", " ")}");
                return true;
            }
            StatusChanged?.Invoke($"Ошибка записи: {result}");
            return false;
        }
        catch (Exception ex)
        {
            StatusChanged?.Invoke($"Ошибка: {ex.Message}");
            return false;
        }
    }

    private void OnConnectionStatusChanged(BluetoothLEDevice s, object a)
    {
        if (s.ConnectionStatus == BluetoothConnectionStatus.Disconnected) Disconnect();
    }

    private void OnCharacteristicValueChanged(GattCharacteristic s, GattValueChangedEventArgs e)
    {
        DataReceived?.Invoke(e.CharacteristicValue.ToArray());
    }

    public void Dispose() { Disconnect(); StopScan(); }
}
