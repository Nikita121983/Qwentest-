package org.apache.poi.ddf;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;

/* loaded from: classes10.dex */
public class EscherBlipRecord extends EscherRecord {
    private static final int HEADER_SIZE = 8;
    private byte[] field_pictureData;
    private static final int DEFAULT_MAX_RECORD_LENGTH = 104857600;
    private static int MAX_RECORD_LENGTH = DEFAULT_MAX_RECORD_LENGTH;
    public static final short RECORD_ID_START = EscherRecordTypes.BLIP_START.typeID;
    public static final short RECORD_ID_END = EscherRecordTypes.BLIP_END.typeID;

    public static void setMaxRecordLength(int length) {
        MAX_RECORD_LENGTH = length;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public EscherBlipRecord() {
    }

    public EscherBlipRecord(EscherBlipRecord other) {
        super(other);
        this.field_pictureData = other.field_pictureData == null ? null : (byte[]) other.field_pictureData.clone();
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        int bytesAfterHeader = readHeader(data, offset);
        int pos = offset + 8;
        this.field_pictureData = IOUtils.safelyClone(data, pos, bytesAfterHeader, MAX_RECORD_LENGTH);
        return bytesAfterHeader + 8;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, getRecordId(), this);
        LittleEndian.putShort(data, offset, getOptions());
        LittleEndian.putShort(data, offset + 2, getRecordId());
        System.arraycopy(this.field_pictureData, 0, data, offset + 4, this.field_pictureData.length);
        listener.afterRecordSerialize(offset + 4 + this.field_pictureData.length, getRecordId(), this.field_pictureData.length + 4, this);
        return this.field_pictureData.length + 4;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int getRecordSize() {
        return this.field_pictureData.length + 8;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public String getRecordName() {
        EscherRecordTypes t = EscherRecordTypes.forTypeID(getRecordId());
        return (t != EscherRecordTypes.UNKNOWN ? t : EscherRecordTypes.BLIP_START).recordName;
    }

    public byte[] getPicturedata() {
        return this.field_pictureData;
    }

    public void setPictureData(byte[] pictureData) {
        setPictureData(pictureData, 0, pictureData == null ? 0 : pictureData.length);
    }

    public void setPictureData(byte[] pictureData, int offset, int length) {
        if (pictureData == null || offset < 0 || length < 0 || pictureData.length < offset + length) {
            throw new IllegalArgumentException("picture data can't be null");
        }
        this.field_pictureData = IOUtils.safelyClone(pictureData, offset, length, MAX_RECORD_LENGTH);
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.ddf.EscherBlipRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherBlipRecord.this.m2235x711c795b();
            }
        }, "pictureData", new Supplier() { // from class: org.apache.poi.ddf.EscherBlipRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherBlipRecord.this.getPicturedata();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherBlipRecord, reason: not valid java name */
    public /* synthetic */ Object m2235x711c795b() {
        return super.getGenericProperties();
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Enum getGenericRecordType() {
        EscherRecordTypes t = EscherRecordTypes.forTypeID(getRecordId());
        return t != EscherRecordTypes.UNKNOWN ? t : EscherRecordTypes.BLIP_START;
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.Duplicatable
    public EscherBlipRecord copy() {
        return new EscherBlipRecord(this);
    }
}
