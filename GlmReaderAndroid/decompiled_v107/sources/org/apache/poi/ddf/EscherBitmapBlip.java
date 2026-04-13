package org.apache.poi.ddf;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;

/* loaded from: classes10.dex */
public class EscherBitmapBlip extends EscherBlipRecord {
    private static final int HEADER_SIZE = 8;
    private final byte[] field_1_UID;
    private byte field_2_marker;
    public static final short RECORD_ID_JPEG = EscherRecordTypes.BLIP_JPEG.typeID;
    public static final short RECORD_ID_PNG = EscherRecordTypes.BLIP_PNG.typeID;
    public static final short RECORD_ID_DIB = EscherRecordTypes.BLIP_DIB.typeID;

    public EscherBitmapBlip() {
        this.field_1_UID = new byte[16];
        this.field_2_marker = (byte) -1;
    }

    public EscherBitmapBlip(EscherBitmapBlip other) {
        super(other);
        this.field_1_UID = new byte[16];
        this.field_2_marker = (byte) -1;
        System.arraycopy(other.field_1_UID, 0, this.field_1_UID, 0, this.field_1_UID.length);
        this.field_2_marker = other.field_2_marker;
    }

    @Override // org.apache.poi.ddf.EscherBlipRecord, org.apache.poi.ddf.EscherRecord
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        int bytesAfterHeader = readHeader(data, offset);
        int pos = offset + 8;
        System.arraycopy(data, pos, this.field_1_UID, 0, 16);
        int pos2 = pos + 16;
        this.field_2_marker = data[pos2];
        setPictureData(data, pos2 + 1, bytesAfterHeader - 17);
        return bytesAfterHeader + 8;
    }

    @Override // org.apache.poi.ddf.EscherBlipRecord, org.apache.poi.ddf.EscherRecord
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, getRecordId(), this);
        LittleEndian.putShort(data, offset, getOptions());
        LittleEndian.putShort(data, offset + 2, getRecordId());
        LittleEndian.putInt(data, offset + 4, getRecordSize() - 8);
        int pos = offset + 8;
        System.arraycopy(this.field_1_UID, 0, data, pos, 16);
        data[pos + 16] = this.field_2_marker;
        byte[] pd = getPicturedata();
        System.arraycopy(pd, 0, data, pos + 17, pd.length);
        listener.afterRecordSerialize(getRecordSize() + offset, getRecordId(), getRecordSize(), this);
        return pd.length + 25;
    }

    @Override // org.apache.poi.ddf.EscherBlipRecord, org.apache.poi.ddf.EscherRecord
    public int getRecordSize() {
        return (getPicturedata() == null ? 0 : getPicturedata().length) + 25;
    }

    public byte[] getUID() {
        return this.field_1_UID;
    }

    public void setUID(byte[] field_1_UID) {
        if (field_1_UID == null || field_1_UID.length != 16) {
            throw new IllegalArgumentException("field_1_UID must be byte[16]");
        }
        System.arraycopy(field_1_UID, 0, this.field_1_UID, 0, 16);
    }

    public byte getMarker() {
        return this.field_2_marker;
    }

    public void setMarker(byte field_2_marker) {
        this.field_2_marker = field_2_marker;
    }

    @Override // org.apache.poi.ddf.EscherBlipRecord, org.apache.poi.ddf.EscherRecord, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.ddf.EscherBitmapBlip$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherBitmapBlip.this.m2234xa66e0f79();
            }
        }, "uid", new Supplier() { // from class: org.apache.poi.ddf.EscherBitmapBlip$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherBitmapBlip.this.getUID();
            }
        }, "marker", new Supplier() { // from class: org.apache.poi.ddf.EscherBitmapBlip$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Byte.valueOf(EscherBitmapBlip.this.getMarker());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherBitmapBlip, reason: not valid java name */
    public /* synthetic */ Object m2234xa66e0f79() {
        return super.getGenericProperties();
    }

    @Override // org.apache.poi.ddf.EscherBlipRecord, org.apache.poi.ddf.EscherRecord, org.apache.poi.common.Duplicatable
    public EscherBitmapBlip copy() {
        return new EscherBitmapBlip(this);
    }
}
