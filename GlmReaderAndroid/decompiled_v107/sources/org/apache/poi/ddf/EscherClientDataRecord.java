package org.apache.poi.ddf;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;

/* loaded from: classes10.dex */
public class EscherClientDataRecord extends EscherRecord {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000;
    private byte[] remainingData;
    public static final short RECORD_ID = EscherRecordTypes.CLIENT_DATA.typeID;
    private static int MAX_RECORD_LENGTH = 100000;
    private static final byte[] EMPTY = new byte[0];

    public static void setMaxRecordLength(int length) {
        MAX_RECORD_LENGTH = length;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public EscherClientDataRecord() {
    }

    public EscherClientDataRecord(EscherClientDataRecord other) {
        super(other);
        this.remainingData = other.remainingData == null ? null : (byte[]) other.remainingData.clone();
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        int bytesRemaining = readHeader(data, offset);
        int pos = offset + 8;
        this.remainingData = bytesRemaining == 0 ? EMPTY : IOUtils.safelyClone(data, pos, bytesRemaining, MAX_RECORD_LENGTH);
        return bytesRemaining + 8;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, getRecordId(), this);
        if (this.remainingData == null) {
            this.remainingData = EMPTY;
        }
        LittleEndian.putShort(data, offset, getOptions());
        LittleEndian.putShort(data, offset + 2, getRecordId());
        LittleEndian.putInt(data, offset + 4, this.remainingData.length);
        System.arraycopy(this.remainingData, 0, data, offset + 8, this.remainingData.length);
        int pos = offset + 8 + this.remainingData.length;
        listener.afterRecordSerialize(pos, getRecordId(), pos - offset, this);
        return pos - offset;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int getRecordSize() {
        return (this.remainingData == null ? 0 : this.remainingData.length) + 8;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public short getRecordId() {
        return RECORD_ID;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public String getRecordName() {
        return EscherRecordTypes.CLIENT_DATA.recordName;
    }

    public byte[] getRemainingData() {
        return this.remainingData;
    }

    public void setRemainingData(byte[] remainingData) {
        this.remainingData = remainingData == null ? new byte[0] : (byte[]) remainingData.clone();
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.ddf.EscherClientDataRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherClientDataRecord.this.m2237x10d22abf();
            }
        }, "remainingData", new Supplier() { // from class: org.apache.poi.ddf.EscherClientDataRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherClientDataRecord.this.getRemainingData();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherClientDataRecord, reason: not valid java name */
    public /* synthetic */ Object m2237x10d22abf() {
        return super.getGenericProperties();
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Enum getGenericRecordType() {
        return EscherRecordTypes.CLIENT_DATA;
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.Duplicatable
    public EscherClientDataRecord copy() {
        return new EscherClientDataRecord(this);
    }
}
