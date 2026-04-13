package com.glmreader.android.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.util.Log;
import com.glmreader.android.protocol.BlePacketParser;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.Ptg;

/* compiled from: GlmBleManager.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\bH\n\u0002\u0018\u0002\n\u0002\b+\u0018\u00002\u00020\u0001:\u0004¤\u0001¥\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J \u0010`\u001a\u00020\u00172\u0018\u0010a\u001a\u0014\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020(0'J\u001a\u0010b\u001a\u00020\u00172\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020(0*J\u001a\u0010c\u001a\u00020\u00172\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020(0*J\u001a\u0010d\u001a\u00020\u00172\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020(0*J\u001a\u0010e\u001a\u00020\u00172\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020(0*J \u0010f\u001a\u00020\u00172\u0018\u0010a\u001a\u0014\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020(0'J \u0010g\u001a\u00020\u00172\u0018\u0010a\u001a\u0014\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020(0'J \u0010h\u001a\u00020\u00172\u0018\u0010a\u001a\u0014\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020(0'J\u001a\u0010i\u001a\u00020\u00172\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020(0*J\u001a\u0010j\u001a\u00020\u00172\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020(0*J\u001a\u0010k\u001a\u00020\u00172\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020(0*J\u001a\u0010l\u001a\u00020\u00172\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020(0*J \u0010m\u001a\u00020\u00172\u0018\u0010a\u001a\u0014\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020(0'J \u0010n\u001a\u00020\u00172\u0018\u0010a\u001a\u0014\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020(0'J\b\u0010|\u001a\u00020(H\u0007J\b\u0010}\u001a\u00020(H\u0007J\u0010\u0010~\u001a\u00020(2\u0006\u0010\u007f\u001a\u00020\u001eH\u0007J\t\u0010\u0080\u0001\u001a\u00020(H\u0002J\t\u0010\u0081\u0001\u001a\u00020(H\u0002J\u0014\u0010\u0082\u0001\u001a\u00020(2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u001eH\u0002J\u001c\u0010\u0084\u0001\u001a\u00020(2\u0006\u0010\u007f\u001a\u00020\u001e2\u000b\b\u0002\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u001eJ\u0007\u0010\u0086\u0001\u001a\u00020(J\u001c\u0010\u0087\u0001\u001a\u00020(2\u0006\u0010\u007f\u001a\u00020\u001e2\u000b\b\u0002\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u001eJ\u0012\u0010\u0088\u0001\u001a\u00020(2\u0007\u0010\u0089\u0001\u001a\u00020+H\u0002J\u0012\u0010\u008a\u0001\u001a\u00020(2\u0007\u0010\u008b\u0001\u001a\u00020\u0015H\u0002J\u001b\u0010\u008c\u0001\u001a\u00020\u00172\u0007\u0010\u008d\u0001\u001a\u00020\u00152\u0007\u0010\u008e\u0001\u001a\u00020\u0019H\u0002J\u0019\u0010\u008f\u0001\u001a\u00020(2\u0007\u0010\u0090\u0001\u001a\u00020+2\u0007\u0010\u0091\u0001\u001a\u00020\u001eJ\t\u0010\u0092\u0001\u001a\u00020(H\u0002J\u0007\u0010\u0093\u0001\u001a\u00020(J\u0007\u0010\u0094\u0001\u001a\u00020(J\u0007\u0010\u0095\u0001\u001a\u00020(J\u0010\u0010\u0096\u0001\u001a\u00020(2\u0007\u0010\u0097\u0001\u001a\u000202J\u0010\u0010\u0098\u0001\u001a\u00020(2\u0007\u0010\u0099\u0001\u001a\u000202J\u0010\u0010\u009a\u0001\u001a\u00020(2\u0007\u0010\u009b\u0001\u001a\u000202J\u0019\u0010\u009c\u0001\u001a\u00020(2\u0007\u0010\u0090\u0001\u001a\u00020+2\u0007\u0010\u0091\u0001\u001a\u00020\u001eJ\u0012\u0010\u009d\u0001\u001a\u00020\u00172\u0007\u0010\u009e\u0001\u001a\u00020+H\u0002J\u0012\u0010\u009f\u0001\u001a\u00020(2\u0007\u0010\u009e\u0001\u001a\u00020+H\u0002J\u0007\u0010 \u0001\u001a\u00020(J\u0007\u0010¡\u0001\u001a\u00020\u001eJ\u0012\u0010¢\u0001\u001a\u00020\u001e2\u0007\u0010£\u0001\u001a\u000202H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0019X\u0082D¢\u0006\u0002\n\u0000R\"\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\"\u0010\"\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010!R\u000e\u0010$\u001a\u00020\u0019X\u0082D¢\u0006\u0002\n\u0000R&\u0010%\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020(0'0&X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010)\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020(0*0&X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010,\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020(0*0&X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010.\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020(0*0&X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010/\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020(0*0&X\u0082\u0004¢\u0006\u0002\n\u0000R&\u00100\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020(0'0&X\u0082\u0004¢\u0006\u0002\n\u0000R&\u00101\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020(0'0&X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u00103\u001a\u0002022\u0006\u0010\u001d\u001a\u000202@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u001e\u00106\u001a\u0002022\u0006\u0010\u001d\u001a\u000202@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b7\u00105R\u000e\u00108\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R(\u00109\u001a\u0010\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020(\u0018\u00010*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R(\u0010>\u001a\u0010\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020(\u0018\u00010*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010;\"\u0004\b@\u0010=RH\u0010A\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020(\u0018\u00010*2\u0014\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020(\u0018\u00010*8\u0006@FX\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bB\u0010C\u001a\u0004\bD\u0010;\"\u0004\bE\u0010=RH\u0010F\u001a\u0010\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020(\u0018\u00010*2\u0014\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020(\u0018\u00010*8\u0006@FX\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bG\u0010C\u001a\u0004\bH\u0010;\"\u0004\bI\u0010=RH\u0010J\u001a\u0010\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020(\u0018\u00010*2\u0014\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020(\u0018\u00010*8\u0006@FX\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bK\u0010C\u001a\u0004\bL\u0010;\"\u0004\bM\u0010=RH\u0010N\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020(\u0018\u00010*2\u0014\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020(\u0018\u00010*8\u0006@FX\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bO\u0010C\u001a\u0004\bP\u0010;\"\u0004\bQ\u0010=RT\u0010R\u001a\u0016\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020(\u0018\u00010'2\u001a\u0010\u001d\u001a\u0016\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020(\u0018\u00010'8\u0006@FX\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bS\u0010C\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WRT\u0010X\u001a\u0016\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020(\u0018\u00010'2\u001a\u0010\u001d\u001a\u0016\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020(\u0018\u00010'8\u0006@FX\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bY\u0010C\u001a\u0004\bZ\u0010U\"\u0004\b[\u0010WRT\u0010\\\u001a\u0016\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020(\u0018\u00010'2\u001a\u0010\u001d\u001a\u0016\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020(\u0018\u00010'8\u0006@FX\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b]\u0010C\u001a\u0004\b^\u0010U\"\u0004\b_\u0010WR\u001e\u0010o\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0017@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bo\u0010pR\u0011\u0010q\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\bq\u0010pR\u0013\u0010r\u001a\u0004\u0018\u00010\u001e8F¢\u0006\u0006\u001a\u0004\bs\u0010!R\u0013\u0010t\u001a\u0004\u0018\u00010\u001e8F¢\u0006\u0006\u001a\u0004\bu\u0010!R\u0011\u0010v\u001a\u00020\u001e8F¢\u0006\u0006\u001a\u0004\bw\u0010!R\u0011\u0010x\u001a\u0002028F¢\u0006\u0006\u001a\u0004\by\u00105R\u000e\u0010z\u001a\u00020{X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006¦\u0001"}, d2 = {"Lcom/glmreader/android/ble/GlmBleManager;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "connectionManager", "Lcom/glmreader/android/ble/BleConnectionManager;", "bluetoothManager", "Landroid/bluetooth/BluetoothManager;", "bluetoothAdapter", "Landroid/bluetooth/BluetoothAdapter;", "kotlin.jvm.PlatformType", "commandQueue", "Ljava/util/concurrent/LinkedBlockingDeque;", "Lcom/glmreader/android/ble/GlmBleManager$QueuedCommand;", "sendThread", "Ljava/lang/Thread;", "stateLock", "Ljava/lang/Object;", "protocolState", "Lcom/glmreader/android/ble/GlmBleManager$ProtocolState;", "timeoutPending", "", "commandTimeoutMs", "", "autoConnectThread", "autoConnectRunning", "autoConnectDelayMs", "value", "", "lastKnownDeviceMac", "getLastKnownDeviceMac", "()Ljava/lang/String;", "lastKnownDeviceName", "getLastKnownDeviceName", "initDelayMs", "deviceFoundListeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lkotlin/Function2;", "", "dataReceivedListeners", "Lkotlin/Function1;", "", "parsedMeasurementListeners", "Lcom/glmreader/android/protocol/BlePacketParser$ParsedMeasurement;", "connectionStateListeners", "stateChangeListeners", "rawTxListeners", "rawChunkListeners", "", "currentRefEdge", "getCurrentRefEdge", "()I", "currentMeasurementType", "getCurrentMeasurementType", "initSyncRequest", "onRefEdgeChanged", "getOnRefEdgeChanged", "()Lkotlin/jvm/functions/Function1;", "setOnRefEdgeChanged", "(Lkotlin/jvm/functions/Function1;)V", "onMeasurementTypeChanged", "getOnMeasurementTypeChanged", "setOnMeasurementTypeChanged", "onConnectionStateChanged", "getOnConnectionStateChanged$annotations", "()V", "getOnConnectionStateChanged", "setOnConnectionStateChanged", "onDataReceived", "getOnDataReceived$annotations", "getOnDataReceived", "setOnDataReceived", "onParsedMeasurement", "getOnParsedMeasurement$annotations", "getOnParsedMeasurement", "setOnParsedMeasurement", "onStateChange", "getOnStateChange$annotations", "getOnStateChange", "setOnStateChange", "onRawTx", "getOnRawTx$annotations", "getOnRawTx", "()Lkotlin/jvm/functions/Function2;", "setOnRawTx", "(Lkotlin/jvm/functions/Function2;)V", "onRawChunk", "getOnRawChunk$annotations", "getOnRawChunk", "setOnRawChunk", "onDeviceFound", "getOnDeviceFound$annotations", "getOnDeviceFound", "setOnDeviceFound", "observeDeviceFound", "listener", "observeDataReceived", "observeParsedMeasurement", "observeConnectionState", "observeStateChange", "observeRawTx", "observeRawChunk", "removeDeviceFound", "removeDataReceived", "removeParsedMeasurement", "removeConnectionState", "removeStateChange", "removeRawTx", "removeRawChunk", "isScanning", "()Z", "isConnected", "connectedDeviceName", "getConnectedDeviceName", "connectedDeviceMac", "getConnectedDeviceMac", "protocolStateName", "getProtocolStateName", "queueSize", "getQueueSize", "scanCallbackLegacy", "Landroid/bluetooth/BluetoothAdapter$LeScanCallback;", "startScan", "stopScan", "connect", "macAddress", "onTransportConnected", "saveLastKnownDevice", "onTransportDisconnected", "reason", "startAutoConnect", "deviceName", "stopAutoConnect", "connectAndAutoConnect", "onPacketReceived", "fullPacket", "transitionTo", "newState", "waitForState", "targetState", "timeoutMs", "enqueueCommand", "payload", "name", "startSendThread", "sendInit", "sendGetSettings", "sendTrigger", "sendSyncHistory", "index", "setMeasurementType", "mode", "setReferencePoint", "ref", "sendRawCommand", "isHeartbeatPacket", "data", "handleHeartbeat", "disconnect", "getDebugInfo", "getRefEdgeName", "refEdge", "ProtocolState", "QueuedCommand", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GlmBleManager {
    private final long autoConnectDelayMs;
    private volatile boolean autoConnectRunning;
    private Thread autoConnectThread;
    private final BluetoothAdapter bluetoothAdapter;
    private final BluetoothManager bluetoothManager;
    private final LinkedBlockingDeque<QueuedCommand> commandQueue;
    private final long commandTimeoutMs;
    private final BleConnectionManager connectionManager;
    private final CopyOnWriteArrayList<Function1<Boolean, Unit>> connectionStateListeners;
    private volatile int currentMeasurementType;
    private volatile int currentRefEdge;
    private final CopyOnWriteArrayList<Function1<byte[], Unit>> dataReceivedListeners;
    private final CopyOnWriteArrayList<Function2<String, String, Unit>> deviceFoundListeners;
    private final long initDelayMs;
    private volatile boolean initSyncRequest;
    private boolean isScanning;
    private String lastKnownDeviceMac;
    private String lastKnownDeviceName;
    private Function1<? super Boolean, Unit> onConnectionStateChanged;
    private Function1<? super byte[], Unit> onDataReceived;
    private Function2<? super String, ? super String, Unit> onDeviceFound;
    private Function1<? super Integer, Unit> onMeasurementTypeChanged;
    private Function1<? super BlePacketParser.ParsedMeasurement, Unit> onParsedMeasurement;
    private Function2<? super byte[], ? super Integer, Unit> onRawChunk;
    private Function2<? super String, ? super String, Unit> onRawTx;
    private Function1<? super Integer, Unit> onRefEdgeChanged;
    private Function1<? super ProtocolState, Unit> onStateChange;
    private final CopyOnWriteArrayList<Function1<BlePacketParser.ParsedMeasurement, Unit>> parsedMeasurementListeners;
    private volatile ProtocolState protocolState;
    private final CopyOnWriteArrayList<Function2<byte[], Integer, Unit>> rawChunkListeners;
    private final CopyOnWriteArrayList<Function2<String, String, Unit>> rawTxListeners;
    private final BluetoothAdapter.LeScanCallback scanCallbackLegacy;
    private Thread sendThread;
    private final CopyOnWriteArrayList<Function1<ProtocolState, Unit>> stateChangeListeners;
    private final Object stateLock;
    private volatile boolean timeoutPending;

    /* compiled from: GlmBleManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/glmreader/android/ble/GlmBleManager$ProtocolState;", "", "<init>", "(Ljava/lang/String;I)V", "SLAVE_LISTENING", "MASTER_READY", "MASTER_SENDING", "MASTER_RECEIVING", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public enum ProtocolState {
        SLAVE_LISTENING,
        MASTER_READY,
        MASTER_SENDING,
        MASTER_RECEIVING;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<ProtocolState> getEntries() {
            return $ENTRIES;
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use observeConnectionState()")
    public static /* synthetic */ void getOnConnectionStateChanged$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use observeDataReceived()")
    public static /* synthetic */ void getOnDataReceived$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use observeDeviceFound()")
    public static /* synthetic */ void getOnDeviceFound$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use observeParsedMeasurement()")
    public static /* synthetic */ void getOnParsedMeasurement$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use observeRawChunk()")
    public static /* synthetic */ void getOnRawChunk$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use observeRawTx()")
    public static /* synthetic */ void getOnRawTx$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use observeStateChange()")
    public static /* synthetic */ void getOnStateChange$annotations() {
    }

    public GlmBleManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.connectionManager = new BleConnectionManager(context);
        Object systemService = context.getSystemService("bluetooth");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.bluetooth.BluetoothManager");
        this.bluetoothManager = (BluetoothManager) systemService;
        this.bluetoothAdapter = this.bluetoothManager.getAdapter();
        this.commandQueue = new LinkedBlockingDeque<>();
        this.stateLock = new Object();
        this.protocolState = ProtocolState.SLAVE_LISTENING;
        this.commandTimeoutMs = 500L;
        this.autoConnectDelayMs = 2000L;
        this.initDelayMs = 500L;
        this.deviceFoundListeners = new CopyOnWriteArrayList<>();
        this.dataReceivedListeners = new CopyOnWriteArrayList<>();
        this.parsedMeasurementListeners = new CopyOnWriteArrayList<>();
        this.connectionStateListeners = new CopyOnWriteArrayList<>();
        this.stateChangeListeners = new CopyOnWriteArrayList<>();
        this.rawTxListeners = new CopyOnWriteArrayList<>();
        this.rawChunkListeners = new CopyOnWriteArrayList<>();
        this.scanCallbackLegacy = new BluetoothAdapter.LeScanCallback() { // from class: com.glmreader.android.ble.GlmBleManager$$ExternalSyntheticLambda2
            @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
            public final void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
                GlmBleManager.scanCallbackLegacy$lambda$1(GlmBleManager.this, bluetoothDevice, i, bArr);
            }
        };
        this.connectionManager.setOnConnected(new Function0() { // from class: com.glmreader.android.ble.GlmBleManager$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit _init_$lambda$2;
                _init_$lambda$2 = GlmBleManager._init_$lambda$2(GlmBleManager.this);
                return _init_$lambda$2;
            }
        });
        this.connectionManager.setOnDisconnected(new Function1() { // from class: com.glmreader.android.ble.GlmBleManager$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit _init_$lambda$3;
                _init_$lambda$3 = GlmBleManager._init_$lambda$3(GlmBleManager.this, (String) obj);
                return _init_$lambda$3;
            }
        });
        this.connectionManager.setOnDataReceived(new Function1() { // from class: com.glmreader.android.ble.GlmBleManager$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit _init_$lambda$4;
                _init_$lambda$4 = GlmBleManager._init_$lambda$4(GlmBleManager.this, (byte[]) obj);
                return _init_$lambda$4;
            }
        });
        this.connectionManager.setOnRawChunk(new Function2() { // from class: com.glmreader.android.ble.GlmBleManager$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit _init_$lambda$6;
                _init_$lambda$6 = GlmBleManager._init_$lambda$6(GlmBleManager.this, (byte[]) obj, ((Integer) obj2).intValue());
                return _init_$lambda$6;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: GlmBleManager.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/glmreader/android/ble/GlmBleManager$QueuedCommand;", "", "payload", "", "name", "", "<init>", "([BLjava/lang/String;)V", "getPayload", "()[B", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final /* data */ class QueuedCommand {
        private final String name;
        private final byte[] payload;

        public static /* synthetic */ QueuedCommand copy$default(QueuedCommand queuedCommand, byte[] bArr, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                bArr = queuedCommand.payload;
            }
            if ((i & 2) != 0) {
                str = queuedCommand.name;
            }
            return queuedCommand.copy(bArr, str);
        }

        /* renamed from: component1, reason: from getter */
        public final byte[] getPayload() {
            return this.payload;
        }

        /* renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final QueuedCommand copy(byte[] payload, String name) {
            Intrinsics.checkNotNullParameter(payload, "payload");
            Intrinsics.checkNotNullParameter(name, "name");
            return new QueuedCommand(payload, name);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof QueuedCommand)) {
                return false;
            }
            QueuedCommand queuedCommand = (QueuedCommand) other;
            return Intrinsics.areEqual(this.payload, queuedCommand.payload) && Intrinsics.areEqual(this.name, queuedCommand.name);
        }

        public int hashCode() {
            return (Arrays.hashCode(this.payload) * 31) + this.name.hashCode();
        }

        public String toString() {
            return "QueuedCommand(payload=" + Arrays.toString(this.payload) + ", name=" + this.name + ")";
        }

        public QueuedCommand(byte[] payload, String name) {
            Intrinsics.checkNotNullParameter(payload, "payload");
            Intrinsics.checkNotNullParameter(name, "name");
            this.payload = payload;
            this.name = name;
        }

        public final String getName() {
            return this.name;
        }

        public final byte[] getPayload() {
            return this.payload;
        }
    }

    public final String getLastKnownDeviceMac() {
        return this.lastKnownDeviceMac;
    }

    public final String getLastKnownDeviceName() {
        return this.lastKnownDeviceName;
    }

    public final int getCurrentRefEdge() {
        return this.currentRefEdge;
    }

    public final int getCurrentMeasurementType() {
        return this.currentMeasurementType;
    }

    public final Function1<Integer, Unit> getOnRefEdgeChanged() {
        return this.onRefEdgeChanged;
    }

    public final void setOnRefEdgeChanged(Function1<? super Integer, Unit> function1) {
        this.onRefEdgeChanged = function1;
    }

    public final Function1<Integer, Unit> getOnMeasurementTypeChanged() {
        return this.onMeasurementTypeChanged;
    }

    public final void setOnMeasurementTypeChanged(Function1<? super Integer, Unit> function1) {
        this.onMeasurementTypeChanged = function1;
    }

    public final Function1<Boolean, Unit> getOnConnectionStateChanged() {
        return this.onConnectionStateChanged;
    }

    public final void setOnConnectionStateChanged(Function1<? super Boolean, Unit> function1) {
        this.onConnectionStateChanged = function1;
        if (function1 != null) {
            this.connectionStateListeners.add(function1);
        }
    }

    public final Function1<byte[], Unit> getOnDataReceived() {
        return this.onDataReceived;
    }

    public final void setOnDataReceived(Function1<? super byte[], Unit> function1) {
        this.onDataReceived = function1;
        if (function1 != null) {
            this.dataReceivedListeners.add(function1);
        }
    }

    public final Function1<BlePacketParser.ParsedMeasurement, Unit> getOnParsedMeasurement() {
        return this.onParsedMeasurement;
    }

    public final void setOnParsedMeasurement(Function1<? super BlePacketParser.ParsedMeasurement, Unit> function1) {
        this.onParsedMeasurement = function1;
        if (function1 != null) {
            this.parsedMeasurementListeners.add(function1);
        }
    }

    public final Function1<ProtocolState, Unit> getOnStateChange() {
        return this.onStateChange;
    }

    public final void setOnStateChange(Function1<? super ProtocolState, Unit> function1) {
        this.onStateChange = function1;
        if (function1 != null) {
            this.stateChangeListeners.add(function1);
        }
    }

    public final Function2<String, String, Unit> getOnRawTx() {
        return this.onRawTx;
    }

    public final void setOnRawTx(Function2<? super String, ? super String, Unit> function2) {
        this.onRawTx = function2;
        if (function2 != null) {
            this.rawTxListeners.add(function2);
        }
    }

    public final Function2<byte[], Integer, Unit> getOnRawChunk() {
        return this.onRawChunk;
    }

    public final void setOnRawChunk(Function2<? super byte[], ? super Integer, Unit> function2) {
        this.onRawChunk = function2;
        if (function2 != null) {
            this.rawChunkListeners.add(function2);
        }
    }

    public final Function2<String, String, Unit> getOnDeviceFound() {
        return this.onDeviceFound;
    }

    public final void setOnDeviceFound(Function2<? super String, ? super String, Unit> function2) {
        this.onDeviceFound = function2;
        if (function2 != null) {
            this.deviceFoundListeners.add(function2);
        }
    }

    public final boolean observeDeviceFound(Function2<? super String, ? super String, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.deviceFoundListeners.add(listener);
    }

    public final boolean observeDataReceived(Function1<? super byte[], Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.dataReceivedListeners.add(listener);
    }

    public final boolean observeParsedMeasurement(Function1<? super BlePacketParser.ParsedMeasurement, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.parsedMeasurementListeners.add(listener);
    }

    public final boolean observeConnectionState(Function1<? super Boolean, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.connectionStateListeners.add(listener);
    }

    public final boolean observeStateChange(Function1<? super ProtocolState, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.stateChangeListeners.add(listener);
    }

    public final boolean observeRawTx(Function2<? super String, ? super String, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.rawTxListeners.add(listener);
    }

    public final boolean observeRawChunk(Function2<? super byte[], ? super Integer, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.rawChunkListeners.add(listener);
    }

    public final boolean removeDeviceFound(Function2<? super String, ? super String, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.deviceFoundListeners.remove(listener);
    }

    public final boolean removeDataReceived(Function1<? super byte[], Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.dataReceivedListeners.remove(listener);
    }

    public final boolean removeParsedMeasurement(Function1<? super BlePacketParser.ParsedMeasurement, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.parsedMeasurementListeners.remove(listener);
    }

    public final boolean removeConnectionState(Function1<? super Boolean, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.connectionStateListeners.remove(listener);
    }

    public final boolean removeStateChange(Function1<? super ProtocolState, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.stateChangeListeners.remove(listener);
    }

    public final boolean removeRawTx(Function2<? super String, ? super String, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.rawTxListeners.remove(listener);
    }

    public final boolean removeRawChunk(Function2<? super byte[], ? super Integer, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return this.rawChunkListeners.remove(listener);
    }

    /* renamed from: isScanning, reason: from getter */
    public final boolean getIsScanning() {
        return this.isScanning;
    }

    public final boolean isConnected() {
        return this.connectionManager.getIsConnected();
    }

    public final String getConnectedDeviceName() {
        return this.connectionManager.getConnectedDeviceName();
    }

    public final String getConnectedDeviceMac() {
        return this.connectionManager.getConnectedDeviceMac();
    }

    public final String getProtocolStateName() {
        return this.protocolState.name();
    }

    public final int getQueueSize() {
        return this.commandQueue.size();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void scanCallbackLegacy$lambda$1(GlmBleManager this$0, BluetoothDevice device, int rssi, byte[] scanRecord) {
        String name = device.getName();
        if (name == null) {
            name = "Unknown";
        }
        String mac = device.getAddress();
        if (StringsKt.contains((CharSequence) name, (CharSequence) "Bosch", true) || StringsKt.contains((CharSequence) name, (CharSequence) "GLM", true)) {
            Log.w("BLE_SCAN", ">>> BOSCH FOUND: " + name + " (" + mac + ")");
            for (Function2 function2 : this$0.deviceFoundListeners) {
                Intrinsics.checkNotNull(mac);
                function2.invoke(mac, name);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$2(GlmBleManager this$0) {
        this$0.onTransportConnected();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$3(GlmBleManager this$0, String reason) {
        this$0.onTransportDisconnected(reason);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$4(GlmBleManager this$0, byte[] packet) {
        Intrinsics.checkNotNullParameter(packet, "packet");
        this$0.onPacketReceived(packet);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$6(GlmBleManager this$0, byte[] chunk, int total) {
        Intrinsics.checkNotNullParameter(chunk, "chunk");
        Iterator it = this$0.rawChunkListeners.iterator();
        while (it.hasNext()) {
            ((Function2) it.next()).invoke(chunk, Integer.valueOf(total));
        }
        return Unit.INSTANCE;
    }

    public final void startScan() {
        if (!this.bluetoothAdapter.isEnabled()) {
            Log.w("BLE", "Bluetooth not enabled");
            return;
        }
        if (this.isScanning) {
            stopScan();
        }
        this.isScanning = true;
        try {
            this.bluetoothAdapter.startLeScan(this.scanCallbackLegacy);
            Log.d("BLE", "Scan started (Bosch filter active)");
        } catch (Exception e) {
            Log.e("BLE", "Scan failed", e);
            this.isScanning = false;
        }
    }

    public final void stopScan() {
        if (this.isScanning) {
            this.isScanning = false;
            try {
                this.bluetoothAdapter.stopLeScan(this.scanCallbackLegacy);
            } catch (Exception e) {
                Log.w("BLE", "Stop scan error", e);
            }
            Log.d("BLE", "Scan stopped");
        }
    }

    public final void connect(String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Log.d("BLE", "=== Connecting to " + macAddress + " (INSECURE RFCOMM, no bonding) ===");
        stopScan();
        stopAutoConnect();
        this.connectionManager.connect(macAddress);
    }

    private final void onTransportConnected() {
        Log.d("BLE_CONN", "✅ Transport connected! Waiting for device init...");
        saveLastKnownDevice();
        Iterator it = this.connectionStateListeners.iterator();
        while (it.hasNext()) {
            ((Function1) it.next()).invoke(true);
        }
        Thread.sleep(this.initDelayMs);
        transitionTo(ProtocolState.MASTER_READY);
        this.initSyncRequest = true;
        Log.d("BLE_CMD", "Setting initSyncRequest=true");
        Log.d("BLE_CMD", "Sending Init (AutoSync ON)");
        enqueueCommand(new byte[]{-64, 85, 2, Ptg.CLASS_ARRAY, 0}, "Init (AutoSync ON)");
    }

    private final void saveLastKnownDevice() {
        this.lastKnownDeviceMac = this.connectionManager.getConnectedDeviceMac();
        this.lastKnownDeviceName = this.connectionManager.getConnectedDeviceName();
        Log.d("BLE", "Saved last known device: " + this.lastKnownDeviceName + " (" + this.lastKnownDeviceMac + ")");
    }

    private final void onTransportDisconnected(String reason) {
        Log.d("BLE_CONN", "Transport disconnected: " + (reason == null ? "normal" : reason));
        Iterator it = this.connectionStateListeners.iterator();
        while (it.hasNext()) {
            ((Function1) it.next()).invoke(false);
        }
        this.commandQueue.clear();
        this.timeoutPending = false;
        this.sendThread = null;
        this.protocolState = ProtocolState.SLAVE_LISTENING;
        Log.d("BLE_CONN", "AutoConnect will reconnect automatically in 2s if running");
    }

    public static /* synthetic */ void startAutoConnect$default(GlmBleManager glmBleManager, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        glmBleManager.startAutoConnect(str, str2);
    }

    public final void startAutoConnect(final String macAddress, String deviceName) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Log.d("BLE_AUTO", "startAutoConnect: " + macAddress + " (" + deviceName + ")");
        this.lastKnownDeviceMac = macAddress;
        this.lastKnownDeviceName = deviceName;
        stopAutoConnect();
        this.autoConnectRunning = true;
        Thread thread = new Thread(new Runnable() { // from class: com.glmreader.android.ble.GlmBleManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                GlmBleManager.startAutoConnect$lambda$9(GlmBleManager.this, macAddress);
            }
        });
        thread.setPriority(1);
        thread.start();
        this.autoConnectThread = thread;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startAutoConnect$lambda$9(GlmBleManager this$0, String $macAddress) {
        Log.d("BLE_AUTO", "AutoConnectThread started");
        while (this$0.autoConnectRunning && !Thread.interrupted()) {
            try {
                if (this$0.isConnected()) {
                    Thread.sleep(this$0.autoConnectDelayMs);
                } else {
                    Log.d("BLE_AUTO", "AutoConnect: trying to connect " + $macAddress);
                    this$0.connectionManager.connect($macAddress);
                    Thread.sleep(this$0.autoConnectDelayMs);
                }
            } catch (InterruptedException e) {
                Log.d("BLE_AUTO", "AutoConnectThread interrupted");
                return;
            }
        }
        Log.d("BLE_AUTO", "AutoConnectThread stopped");
    }

    public final void stopAutoConnect() {
        this.autoConnectRunning = false;
        Thread thread = this.autoConnectThread;
        if (thread != null) {
            thread.interrupt();
        }
        try {
            Thread thread2 = this.autoConnectThread;
            if (thread2 != null) {
                thread2.join(500L);
            }
        } catch (Exception e) {
        }
        this.autoConnectThread = null;
        Log.d("BLE_AUTO", "AutoConnect stopped");
    }

    public static /* synthetic */ void connectAndAutoConnect$default(GlmBleManager glmBleManager, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        glmBleManager.connectAndAutoConnect(str, str2);
    }

    public final void connectAndAutoConnect(String macAddress, String deviceName) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        Log.d("BLE_AUTO", "connectAndAutoConnect: " + macAddress + " (" + deviceName + ")");
        stopAutoConnect();
        if (isConnected()) {
            disconnect();
        }
        this.lastKnownDeviceMac = macAddress;
        this.lastKnownDeviceName = deviceName;
        connect(macAddress);
        startAutoConnect(macAddress, deviceName);
    }

    private final void onPacketReceived(byte[] fullPacket) {
        String hex = ArraysKt.joinToString$default(fullPacket, (CharSequence) StringUtils.SPACE, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.glmreader.android.ble.GlmBleManager$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                CharSequence onPacketReceived$lambda$11;
                onPacketReceived$lambda$11 = GlmBleManager.onPacketReceived$lambda$11(((Byte) obj).byteValue());
                return onPacketReceived$lambda$11;
            }
        }, 30, (Object) null);
        Iterator it = this.rawTxListeners.iterator();
        while (it.hasNext()) {
            ((Function2) it.next()).invoke("RX", hex);
        }
        Log.d("BLE_PACKET", "📦 Received: " + hex);
        if (fullPacket.length < 2) {
            Log.w("BLE_PACKET", "Packet too short: " + fullPacket.length + " bytes");
            return;
        }
        int receivedCrc = ArraysKt.last(fullPacket) & UByte.MAX_VALUE;
        byte[] dataForCrc = Arrays.copyOf(fullPacket, fullPacket.length - 1);
        Intrinsics.checkNotNullExpressionValue(dataForCrc, "copyOf(...)");
        int calculatedCrc = BlePacketParser.INSTANCE.calcCrc8(dataForCrc);
        if (receivedCrc != calculatedCrc) {
            Log.w("BLE_PACKET", "⚠️ CRC mismatch: expected=" + receivedCrc + ", got=" + calculatedCrc + ", packet discarded");
            return;
        }
        if (fullPacket.length >= 4) {
            int devModeRef = fullPacket[3] & UByte.MAX_VALUE;
            int refEdge = devModeRef & 3;
            int devMode = (devModeRef >> 2) & 63;
            String num = Integer.toString(devModeRef, CharsKt.checkRadix(16));
            Intrinsics.checkNotNullExpressionValue(num, "toString(...)");
            String upperCase = num.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            Log.d("BLE_STATE", "📐 Raw: devModeRef=0x" + upperCase + ", refEdge=" + refEdge + ", devMode=" + devMode);
            if (this.currentRefEdge != refEdge) {
                this.currentRefEdge = refEdge;
                Log.d("BLE_STATE", "🔄 refEdge changed: " + refEdge + " (" + getRefEdgeName(refEdge) + ")");
                Function1<? super Integer, Unit> function1 = this.onRefEdgeChanged;
                if (function1 != null) {
                    function1.invoke(Integer.valueOf(refEdge));
                }
            }
            if (devMode != 0 && devMode != 60 && devMode != 62 && this.currentMeasurementType != devMode) {
                this.currentMeasurementType = devMode;
                Log.d("BLE_STATE", "📏 measurementType changed: " + devMode);
                Function1<? super Integer, Unit> function12 = this.onMeasurementTypeChanged;
                if (function12 != null) {
                    function12.invoke(Integer.valueOf(devMode));
                }
            }
        }
        if (isHeartbeatPacket(fullPacket)) {
            handleHeartbeat(fullPacket);
            if (this.protocolState == ProtocolState.MASTER_RECEIVING) {
                transitionTo(ProtocolState.MASTER_READY);
                return;
            }
            return;
        }
        boolean isInitResponse = this.initSyncRequest;
        if (isInitResponse) {
            this.initSyncRequest = false;
            Log.d("BLE_STATE", "⏸️ Skipping init response packet (like MM/MO initSyncRequest)");
        }
        BlePacketParser.ParsedMeasurement parsed = BlePacketParser.INSTANCE.parseWithCrc(fullPacket);
        if (parsed == null) {
            Log.w("BLE", "⚠️ Parse returned null (likely heartbeat or status packet)");
        } else {
            Log.d("BLE", "✅ Parsed: devMode=" + parsed.getDevMode() + ", refEdge=" + parsed.getRefEdge() + ", " + parsed.getResultValue() + "m, " + parsed.getComp2Value() + "°");
            if (!isInitResponse) {
                if (!(parsed.getResultValue() == 0.0d)) {
                    Iterator it2 = this.dataReceivedListeners.iterator();
                    while (it2.hasNext()) {
                        ((Function1) it2.next()).invoke(fullPacket);
                    }
                    Iterator it3 = this.parsedMeasurementListeners.iterator();
                    while (it3.hasNext()) {
                        ((Function1) it3.next()).invoke(parsed);
                    }
                }
            }
        }
        if (this.protocolState == ProtocolState.MASTER_RECEIVING) {
            transitionTo(ProtocolState.MASTER_READY);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence onPacketReceived$lambda$11(byte it) {
        String format = String.format("%02X", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    private final void transitionTo(ProtocolState newState) {
        synchronized (this.stateLock) {
            ProtocolState protocolState = this.protocolState;
            this.protocolState = newState;
            Log.d("BLE_STATE", protocolState + " → " + newState);
            Iterator it = this.stateChangeListeners.iterator();
            while (it.hasNext()) {
                ((Function1) it.next()).invoke(newState);
            }
            this.stateLock.notifyAll();
            Unit unit = Unit.INSTANCE;
        }
    }

    private final boolean waitForState(ProtocolState targetState, long timeoutMs) {
        synchronized (this.stateLock) {
            long currentTimeMillis = System.currentTimeMillis() + timeoutMs;
            while (this.protocolState != targetState) {
                long currentTimeMillis2 = currentTimeMillis - System.currentTimeMillis();
                if (currentTimeMillis2 <= 0) {
                    Log.w("BLE", "⏱️ waitForState timeout: wanted " + targetState + ", got " + this.protocolState);
                    return false;
                }
                try {
                    this.stateLock.wait(currentTimeMillis2);
                } catch (InterruptedException e) {
                    return false;
                }
            }
            return true;
        }
    }

    public final void enqueueCommand(byte[] payload, String name) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        Intrinsics.checkNotNullParameter(name, "name");
        if (!isConnected()) {
            Log.w("BLE", "Not connected, command dropped: " + name);
            return;
        }
        this.commandQueue.offerLast(new QueuedCommand(payload, name));
        Log.d("BLE", "📋 Queued: " + name + " (queue size: " + this.commandQueue.size() + ")");
        Thread thread = this.sendThread;
        boolean z = false;
        if (thread != null && thread.isAlive()) {
            z = true;
        }
        if (!z) {
            startSendThread();
        }
    }

    private final void startSendThread() {
        Thread thread = new Thread(new Runnable() { // from class: com.glmreader.android.ble.GlmBleManager$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                GlmBleManager.startSendThread$lambda$20(GlmBleManager.this);
            }
        });
        thread.start();
        this.sendThread = thread;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startSendThread$lambda$20(GlmBleManager this$0) {
        long j;
        QueuedCommand cmd;
        Log.d("BLE_SEND", "SendThread started");
        while (!Thread.interrupted() && this$0.isConnected()) {
            try {
                cmd = this$0.commandQueue.pollFirst();
            } catch (Exception e) {
                e = e;
                j = 500;
            }
            if (cmd != null) {
                boolean ready = this$0.waitForState(ProtocolState.MASTER_READY, 3000L);
                if (ready) {
                    if (this$0.isConnected()) {
                        this$0.transitionTo(ProtocolState.MASTER_SENDING);
                        int crc = BlePacketParser.INSTANCE.calcCrc8(cmd.getPayload());
                        byte[] fullPacket = ArraysKt.plus(cmd.getPayload(), (byte) crc);
                        String hex = ArraysKt.joinToString$default(fullPacket, (CharSequence) StringUtils.SPACE, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.glmreader.android.ble.GlmBleManager$$ExternalSyntheticLambda7
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                CharSequence startSendThread$lambda$20$lambda$18;
                                startSendThread$lambda$20$lambda$18 = GlmBleManager.startSendThread$lambda$20$lambda$18(((Byte) obj).byteValue());
                                return startSendThread$lambda$20$lambda$18;
                            }
                        }, 30, (Object) null);
                        Log.d("BLE_SEND", "📤 Sending: " + cmd.getName() + " -> " + hex);
                        boolean writeOk = this$0.connectionManager.write(fullPacket);
                        Iterator it = this$0.rawTxListeners.iterator();
                        while (it.hasNext()) {
                            j = 500;
                            try {
                                ((Function2) it.next()).invoke("TX", hex);
                            } catch (Exception e2) {
                                e = e2;
                                Log.e("BLE_SEND", "❌ SendThread error: " + e.getMessage());
                                this$0.transitionTo(ProtocolState.MASTER_READY);
                                Thread.sleep(j);
                            }
                        }
                        if (writeOk) {
                            this$0.transitionTo(ProtocolState.MASTER_RECEIVING);
                            boolean responseReceived = this$0.waitForState(ProtocolState.MASTER_READY, 3000L);
                            if (!responseReceived) {
                                Log.w("BLE_SEND", "⏱️ Command timeout: " + cmd.getName() + " (no response in 3000ms)");
                                this$0.transitionTo(ProtocolState.MASTER_READY);
                                Thread.sleep(500L);
                            } else {
                                Log.d("BLE_SEND", "✅ Response received for " + cmd.getName());
                            }
                        } else {
                            Log.e("BLE_SEND", "❌ Write failed for " + cmd.getName());
                            this$0.transitionTo(ProtocolState.MASTER_READY);
                            Thread.sleep(500L);
                        }
                    } else {
                        j = 500;
                    }
                } else {
                    j = 500;
                }
                Log.w("BLE_SEND", "⚠️ Not ready or disconnected, dropping: " + cmd.getName());
                break;
            }
            break;
        }
        Log.d("BLE_SEND", "SendThread stopped");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence startSendThread$lambda$20$lambda$18(byte it) {
        String format = String.format("%02X", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    public final void sendInit() {
        enqueueCommand(new byte[]{-64, 85, 2, Ptg.CLASS_ARRAY, 0}, "Init");
    }

    public final void sendGetSettings() {
        enqueueCommand(new byte[]{-64, 85, 2, Area3DPtg.sid, 0}, "Get Settings");
    }

    public final void sendTrigger() {
        enqueueCommand(new byte[]{-64, 86, 1, 0}, "Trigger");
    }

    public final void sendSyncHistory(int index) {
        enqueueCommand(new byte[]{-64, 85, 2, -70, (byte) index}, "Sync History " + index);
    }

    public final void setMeasurementType(int mode) {
        Log.d("BLE_CMD", "🎯 Set Measurement Type: mode=" + mode);
        enqueueCommand(new byte[]{-64, 85, 2, -68, (byte) mode}, "Set Mode " + mode);
    }

    public final void setReferencePoint(int ref) {
        Log.d("BLE_CMD", "📍 Set Reference Point: ref=" + ref);
        enqueueCommand(new byte[]{-64, 85, 2, -66, (byte) ref}, "Set Ref " + ref);
    }

    public final void sendRawCommand(byte[] payload, String name) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        Intrinsics.checkNotNullParameter(name, "name");
        enqueueCommand(payload, name);
    }

    private final boolean isHeartbeatPacket(byte[] data) {
        return data.length >= 5 && data[0] == -64 && data[1] == 85 && data[2] == 2 && (data[3] & UByte.MAX_VALUE) == 241;
    }

    private final void handleHeartbeat(byte[] data) {
        if (data.length >= 6) {
            int devStatus = data[4] & UByte.MAX_VALUE;
            int angle = data[5] & UByte.MAX_VALUE;
            Log.d("BLE", "💓 Heartbeat: devStatus=" + devStatus + ", angle=" + angle);
            return;
        }
        Log.d("BLE", "💓 Heartbeat received (short)");
    }

    public final void disconnect() {
        Log.d("BLE", "Disconnecting...");
        this.connectionManager.disconnect();
    }

    public final String getDebugInfo() {
        return "State: " + this.protocolState + " | Queue: " + this.commandQueue.size() + " | " + this.connectionManager.getStatusInfo();
    }

    private final String getRefEdgeName(int refEdge) {
        switch (refEdge) {
            case 0:
                return "Rear";
            case 1:
                return "Tripod";
            case 2:
                return "Front";
            case 3:
                return "Pin";
            default:
                return "Unknown";
        }
    }
}
