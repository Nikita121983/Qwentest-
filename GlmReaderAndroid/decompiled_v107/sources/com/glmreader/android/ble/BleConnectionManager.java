package com.glmreader.android.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

/* compiled from: BleConnectionManager.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u001a\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010=\u001a\u00020\u00192\u0006\u0010>\u001a\u00020 H\u0007J\u000e\u0010?\u001a\u00020\u000f2\u0006\u0010@\u001a\u00020&J\b\u0010A\u001a\u00020\u0019H\u0002J\b\u0010B\u001a\u00020\u0019H\u0002J\u0006\u0010C\u001a\u00020\u0019J\u0006\u0010D\u001a\u00020 R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n \u0016*\u0004\u0018\u00010\u00150\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR*\u0010\u001e\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010 \u0012\u0004\u0012\u00020\u0019\u0018\u00010\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R(\u0010%\u001a\u0010\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\"\"\u0004\b(\u0010$R.\u0010)\u001a\u0016\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u0019\u0018\u00010*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001e\u00101\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\"\u00103\u001a\u0004\u0018\u00010 2\b\u00100\u001a\u0004\u0018\u00010 @BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\"\u00106\u001a\u0004\u0018\u00010 2\b\u00100\u001a\u0004\u0018\u00010 @BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b7\u00105R\u001e\u00108\u001a\u00020+2\u0006\u00100\u001a\u00020+@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u001e\u0010;\u001a\u00020+2\u0006\u00100\u001a\u00020+@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b<\u0010:¨\u0006E"}, d2 = {"Lcom/glmreader/android/ble/BleConnectionManager;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "rfcommSocket", "Landroid/bluetooth/BluetoothSocket;", "inputStream", "Ljava/io/InputStream;", "outputStream", "Ljava/io/OutputStream;", "readThread", "Ljava/lang/Thread;", "isReading", "", "readBuffer", "Ljava/io/ByteArrayOutputStream;", "bufferLock", "Ljava/lang/Object;", "SPP_UUID", "Ljava/util/UUID;", "kotlin.jvm.PlatformType", "onConnected", "Lkotlin/Function0;", "", "getOnConnected", "()Lkotlin/jvm/functions/Function0;", "setOnConnected", "(Lkotlin/jvm/functions/Function0;)V", "onDisconnected", "Lkotlin/Function1;", "", "getOnDisconnected", "()Lkotlin/jvm/functions/Function1;", "setOnDisconnected", "(Lkotlin/jvm/functions/Function1;)V", "onDataReceived", "", "getOnDataReceived", "setOnDataReceived", "onRawChunk", "Lkotlin/Function2;", "", "getOnRawChunk", "()Lkotlin/jvm/functions/Function2;", "setOnRawChunk", "(Lkotlin/jvm/functions/Function2;)V", "value", "isConnected", "()Z", "connectedDeviceName", "getConnectedDeviceName", "()Ljava/lang/String;", "connectedDeviceMac", "getConnectedDeviceMac", "totalBytesRead", "getTotalBytesRead", "()I", "totalBytesWritten", "getTotalBytesWritten", "connect", "macAddress", "write", "data", "readLoop", "processBuffer", "disconnect", "getStatusInfo", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class BleConnectionManager {
    private final UUID SPP_UUID;
    private final Object bufferLock;
    private String connectedDeviceMac;
    private String connectedDeviceName;
    private final Context context;
    private InputStream inputStream;
    private volatile boolean isConnected;
    private volatile boolean isReading;
    private Function0<Unit> onConnected;
    private Function1<? super byte[], Unit> onDataReceived;
    private Function1<? super String, Unit> onDisconnected;
    private Function2<? super byte[], ? super Integer, Unit> onRawChunk;
    private OutputStream outputStream;
    private final ByteArrayOutputStream readBuffer;
    private Thread readThread;
    private BluetoothSocket rfcommSocket;
    private volatile int totalBytesRead;
    private volatile int totalBytesWritten;

    public BleConnectionManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.readBuffer = new ByteArrayOutputStream();
        this.bufferLock = new Object();
        this.SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    }

    public final Function0<Unit> getOnConnected() {
        return this.onConnected;
    }

    public final void setOnConnected(Function0<Unit> function0) {
        this.onConnected = function0;
    }

    public final Function1<String, Unit> getOnDisconnected() {
        return this.onDisconnected;
    }

    public final void setOnDisconnected(Function1<? super String, Unit> function1) {
        this.onDisconnected = function1;
    }

    public final Function1<byte[], Unit> getOnDataReceived() {
        return this.onDataReceived;
    }

    public final void setOnDataReceived(Function1<? super byte[], Unit> function1) {
        this.onDataReceived = function1;
    }

    public final Function2<byte[], Integer, Unit> getOnRawChunk() {
        return this.onRawChunk;
    }

    public final void setOnRawChunk(Function2<? super byte[], ? super Integer, Unit> function2) {
        this.onRawChunk = function2;
    }

    /* renamed from: isConnected, reason: from getter */
    public final boolean getIsConnected() {
        return this.isConnected;
    }

    public final String getConnectedDeviceName() {
        return this.connectedDeviceName;
    }

    public final String getConnectedDeviceMac() {
        return this.connectedDeviceMac;
    }

    public final int getTotalBytesRead() {
        return this.totalBytesRead;
    }

    public final int getTotalBytesWritten() {
        return this.totalBytesWritten;
    }

    public final void connect(final String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        if (this.isConnected) {
            Log.w("BleConn", "Already connected, disconnecting first");
            disconnect();
        }
        new Thread(new Runnable() { // from class: com.glmreader.android.ble.BleConnectionManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BleConnectionManager.connect$lambda$0(BleConnectionManager.this, macAddress);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void connect$lambda$0(final BleConnectionManager this$0, String $macAddress) {
        try {
            Object systemService = this$0.context.getSystemService("bluetooth");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
            BluetoothManager bluetoothManager = (BluetoothManager) systemService;
            BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
            BluetoothDevice device = bluetoothAdapter.getRemoteDevice($macAddress);
            Log.d("BleConn", "Connecting to " + $macAddress + " (insecure RFCOMM, no bonding)");
            this$0.rfcommSocket = device.createInsecureRfcommSocketToServiceRecord(this$0.SPP_UUID);
            BluetoothSocket bluetoothSocket = this$0.rfcommSocket;
            if (bluetoothSocket != null) {
                bluetoothSocket.connect();
            }
            Log.d("BleConn", "✅ RFCOMM Connected: " + device.getName());
            this$0.isConnected = true;
            this$0.connectedDeviceName = device.getName();
            this$0.connectedDeviceMac = $macAddress;
            BluetoothSocket bluetoothSocket2 = this$0.rfcommSocket;
            this$0.inputStream = bluetoothSocket2 != null ? bluetoothSocket2.getInputStream() : null;
            BluetoothSocket bluetoothSocket3 = this$0.rfcommSocket;
            this$0.outputStream = bluetoothSocket3 != null ? bluetoothSocket3.getOutputStream() : null;
            this$0.totalBytesRead = 0;
            this$0.totalBytesWritten = 0;
            this$0.isReading = true;
            this$0.readThread = new Thread(new Runnable() { // from class: com.glmreader.android.ble.BleConnectionManager$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    BleConnectionManager.this.readLoop();
                }
            }, "BleReadThread");
            Thread thread = this$0.readThread;
            if (thread != null) {
                thread.start();
            }
            Function0<Unit> function0 = this$0.onConnected;
            if (function0 != null) {
                function0.invoke();
            }
        } catch (Exception e) {
            Log.e("BleConn", "❌ Connection failed: " + e.getMessage());
            this$0.isConnected = false;
            Function1<? super String, Unit> function1 = this$0.onDisconnected;
            if (function1 != null) {
                function1.invoke(e.getMessage());
            }
        }
    }

    public final synchronized boolean write(byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        boolean z = false;
        if (this.isConnected && this.outputStream != null) {
            try {
                OutputStream outputStream = this.outputStream;
                if (outputStream != null) {
                    try {
                        outputStream.write(data);
                    } catch (Exception e) {
                        e = e;
                        Log.e("BleConn", "Write error: " + e.getMessage());
                        return z;
                    }
                }
                OutputStream outputStream2 = this.outputStream;
                if (outputStream2 != null) {
                    outputStream2.flush();
                }
                this.totalBytesWritten += data.length;
            } catch (Exception e2) {
                e = e2;
            }
            try {
                Log.d("BleConn", "📤 Wrote " + data.length + " bytes: " + ArraysKt.joinToString$default(data, (CharSequence) StringUtils.SPACE, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.glmreader.android.ble.BleConnectionManager$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        CharSequence write$lambda$1;
                        write$lambda$1 = BleConnectionManager.write$lambda$1(((Byte) obj).byteValue());
                        return write$lambda$1;
                    }
                }, 30, (Object) null));
                z = true;
            } catch (Exception e3) {
                e = e3;
                Log.e("BleConn", "Write error: " + e.getMessage());
                return z;
            }
            return z;
        }
        Log.w("BleConn", "Write failed: not connected");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence write$lambda$1(byte it) {
        String format = String.format("%02X", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void readLoop() {
        Function1<? super String, Unit> function1;
        Function1<? super String, Unit> function12;
        byte[] tempBuf = new byte[1024];
        try {
            try {
                Log.d("BleConn", "Read thread started");
                while (true) {
                    if (!this.isReading || !this.isConnected || this.inputStream == null) {
                        break;
                    }
                    InputStream inputStream = this.inputStream;
                    int count = inputStream != null ? inputStream.read(tempBuf) : -1;
                    if (count > 0) {
                        synchronized (this.bufferLock) {
                            this.totalBytesRead += count;
                            byte[] copyOf = Arrays.copyOf(tempBuf, count);
                            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(...)");
                            this.readBuffer.write(copyOf);
                            Function2<? super byte[], ? super Integer, Unit> function2 = this.onRawChunk;
                            if (function2 != null) {
                                function2.invoke(copyOf, Integer.valueOf(this.totalBytesRead));
                            }
                            processBuffer();
                            Unit unit = Unit.INSTANCE;
                        }
                    } else if (count == -1) {
                        Log.w("BleConn", "Stream closed (EOF)");
                        break;
                    }
                }
                if (!this.isConnected || (function12 = this.onDisconnected) == null) {
                    return;
                }
            } catch (Exception e) {
                Log.e("BleConn", "Read error: " + e.getMessage());
                if (!this.isConnected || (function12 = this.onDisconnected) == null) {
                    return;
                }
            }
            function12.invoke("Stream error");
        } catch (Throwable th) {
            if (this.isConnected && (function1 = this.onDisconnected) != null) {
                function1.invoke("Stream error");
            }
            throw th;
        }
    }

    private final void processBuffer() {
        byte[] data = this.readBuffer.toByteArray();
        if (data.length < 4) {
            return;
        }
        int startIdx = -1;
        int i = 0;
        int length = data.length - 1;
        while (true) {
            if (i < length) {
                if (data[i] != -64 || data[i + 1] != 85) {
                    i++;
                } else {
                    startIdx = i;
                    break;
                }
            } else {
                break;
            }
        }
        if (startIdx == -1) {
            if (this.readBuffer.size() > 2048) {
                Log.w("BleConn", "No header found, clearing buffer (" + this.readBuffer.size() + " bytes)");
                this.readBuffer.reset();
                return;
            }
            return;
        }
        if (startIdx > 0) {
            Log.d("BleConn", "Skipping " + startIdx + " bytes before header");
            Intrinsics.checkNotNull(data);
            byte[] validData = ArraysKt.copyOfRange(data, startIdx, data.length);
            this.readBuffer.reset();
            this.readBuffer.write(validData);
        }
        byte[] packetData = this.readBuffer.toByteArray();
        if (packetData.length >= 4) {
            int payloadLen = packetData[2] & UByte.MAX_VALUE;
            int expectedSize = payloadLen + 3 + 1;
            if (packetData.length >= expectedSize) {
                Intrinsics.checkNotNull(packetData);
                byte[] fullPacket = Arrays.copyOf(packetData, expectedSize);
                Intrinsics.checkNotNullExpressionValue(fullPacket, "copyOf(...)");
                this.readBuffer.reset();
                if (packetData.length > expectedSize) {
                    this.readBuffer.write(ArraysKt.copyOfRange(packetData, expectedSize, packetData.length));
                }
                Log.d("BleConn", "📦 Packet ready: " + expectedSize + " bytes");
                Function1<? super byte[], Unit> function1 = this.onDataReceived;
                if (function1 != null) {
                    function1.invoke(fullPacket);
                }
            }
        }
    }

    public final void disconnect() {
        Object obj;
        Function1<? super String, Unit> function1;
        Log.d("BleConn", "Disconnecting...");
        boolean wasConnected = this.isConnected;
        this.isConnected = false;
        this.isReading = false;
        try {
            try {
                Thread thread = this.readThread;
                if (thread != null) {
                    thread.interrupt();
                }
                Thread thread2 = this.readThread;
                if (thread2 != null) {
                    thread2.join(1000L);
                }
                BluetoothSocket bluetoothSocket = this.rfcommSocket;
                if (bluetoothSocket != null) {
                    bluetoothSocket.close();
                }
                InputStream inputStream = this.inputStream;
                if (inputStream != null) {
                    inputStream.close();
                }
                OutputStream outputStream = this.outputStream;
                if (outputStream != null) {
                    outputStream.close();
                }
                obj = this.bufferLock;
            } catch (Exception e) {
                Log.w("BleConn", "Disconnect error: " + e.getMessage());
                obj = this.bufferLock;
                synchronized (obj) {
                    this.readBuffer.reset();
                    Unit unit = Unit.INSTANCE;
                }
            }
            synchronized (obj) {
                this.readBuffer.reset();
                Unit unit2 = Unit.INSTANCE;
                this.rfcommSocket = null;
                this.inputStream = null;
                this.outputStream = null;
                this.readThread = null;
                if (wasConnected && (function1 = this.onDisconnected) != null) {
                    function1.invoke(null);
                }
                Log.d("BleConn", "Disconnected");
            }
        } catch (Throwable th) {
            synchronized (this.bufferLock) {
                this.readBuffer.reset();
                Unit unit3 = Unit.INSTANCE;
                this.rfcommSocket = null;
                this.inputStream = null;
                this.outputStream = null;
                this.readThread = null;
                throw th;
            }
        }
    }

    public final String getStatusInfo() {
        boolean z = this.isConnected;
        String str = this.connectedDeviceName;
        String str2 = this.connectedDeviceMac;
        int i = this.totalBytesRead;
        int i2 = this.totalBytesWritten;
        Thread thread = this.readThread;
        return "Connected: " + z + " | Device: " + str + " | MAC: " + str2 + " | RX: " + i + " | TX: " + i2 + " | ReadThread: " + (thread != null ? Boolean.valueOf(thread.isAlive()) : null);
    }
}
