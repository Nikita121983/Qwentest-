package org.apache.poi.ddf;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.commons.compress.compressors.bzip2.BZip2Constants;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
public final class EscherTextboxRecord extends EscherRecord {
    private byte[] thedata;
    private static int DEFAULT_MAX_RECORD_LENGTH = BZip2Constants.BASEBLOCKSIZE;
    private static int MAX_RECORD_LENGTH = DEFAULT_MAX_RECORD_LENGTH;
    public static final short RECORD_ID = EscherRecordTypes.CLIENT_TEXTBOX.typeID;
    private static final byte[] NO_BYTES = new byte[0];

    public static void setMaxRecordLength(int length) {
        MAX_RECORD_LENGTH = length;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public EscherTextboxRecord() {
        this.thedata = NO_BYTES;
    }

    public EscherTextboxRecord(EscherTextboxRecord other) {
        super(other);
        this.thedata = NO_BYTES;
        this.thedata = other.thedata == null ? NO_BYTES : (byte[]) other.thedata.clone();
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        int bytesRemaining = readHeader(data, offset);
        this.thedata = IOUtils.safelyClone(data, offset + 8, bytesRemaining, MAX_RECORD_LENGTH);
        return bytesRemaining + 8;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, getRecordId(), this);
        LittleEndian.putShort(data, offset, getOptions());
        LittleEndian.putShort(data, offset + 2, getRecordId());
        int remainingBytes = this.thedata.length;
        LittleEndian.putInt(data, offset + 4, remainingBytes);
        System.arraycopy(this.thedata, 0, data, offset + 8, this.thedata.length);
        int pos = offset + 8 + this.thedata.length;
        listener.afterRecordSerialize(pos, getRecordId(), pos - offset, this);
        int size = pos - offset;
        if (size != getRecordSize()) {
            throw new RecordFormatException(size + " bytes written but getRecordSize() reports " + getRecordSize());
        }
        return size;
    }

    public byte[] getData() {
        return this.thedata;
    }

    public void setData(byte[] b, int start, int length) {
        this.thedata = IOUtils.safelyClone(b, start, length, MAX_RECORD_LENGTH);
    }

    public void setData(byte[] b) {
        setData(b, 0, b.length);
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int getRecordSize() {
        return this.thedata.length + 8;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public String getRecordName() {
        return EscherRecordTypes.CLIENT_TEXTBOX.recordName;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Enum getGenericRecordType() {
        return EscherRecordTypes.CLIENT_TEXTBOX;
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.ddf.EscherTextboxRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherTextboxRecord.this.m2247x72387e16();
            }
        }, "isContainer", new Supplier() { // from class: org.apache.poi.ddf.EscherTextboxRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(EscherTextboxRecord.this.isContainerRecord());
            }
        }, "extraData", new Supplier() { // from class: org.apache.poi.ddf.EscherTextboxRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherTextboxRecord.this.getData();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherTextboxRecord, reason: not valid java name */
    public /* synthetic */ Object m2247x72387e16() {
        return super.getGenericProperties();
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.Duplicatable
    public EscherTextboxRecord copy() {
        return new EscherTextboxRecord(this);
    }
}
