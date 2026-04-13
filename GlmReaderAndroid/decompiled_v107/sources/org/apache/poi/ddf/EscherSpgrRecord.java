package org.apache.poi.ddf;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
public class EscherSpgrRecord extends EscherRecord {
    public static final short RECORD_ID = EscherRecordTypes.SPGR.typeID;
    private int field_1_rectX1;
    private int field_2_rectY1;
    private int field_3_rectX2;
    private int field_4_rectY2;

    public EscherSpgrRecord() {
    }

    public EscherSpgrRecord(EscherSpgrRecord other) {
        super(other);
        this.field_1_rectX1 = other.field_1_rectX1;
        this.field_2_rectY1 = other.field_2_rectY1;
        this.field_3_rectX2 = other.field_3_rectX2;
        this.field_4_rectY2 = other.field_4_rectY2;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        int bytesRemaining = readHeader(data, offset);
        int pos = offset + 8;
        this.field_1_rectX1 = LittleEndian.getInt(data, pos + 0);
        int size = 0 + 4;
        this.field_2_rectY1 = LittleEndian.getInt(data, pos + size);
        int size2 = size + 4;
        this.field_3_rectX2 = LittleEndian.getInt(data, pos + size2);
        int size3 = size2 + 4;
        this.field_4_rectY2 = LittleEndian.getInt(data, pos + size3);
        int size4 = size3 + 4;
        int bytesRemaining2 = bytesRemaining - size4;
        if (bytesRemaining2 != 0) {
            throw new RecordFormatException("Expected no remaining bytes but got " + bytesRemaining2);
        }
        return size4 + 8 + bytesRemaining2;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, getRecordId(), this);
        LittleEndian.putShort(data, offset, getOptions());
        LittleEndian.putShort(data, offset + 2, getRecordId());
        LittleEndian.putInt(data, offset + 4, 16);
        LittleEndian.putInt(data, offset + 8, this.field_1_rectX1);
        LittleEndian.putInt(data, offset + 12, this.field_2_rectY1);
        LittleEndian.putInt(data, offset + 16, this.field_3_rectX2);
        LittleEndian.putInt(data, offset + 20, this.field_4_rectY2);
        listener.afterRecordSerialize(getRecordSize() + offset, getRecordId(), getRecordSize() + offset, this);
        return 24;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int getRecordSize() {
        return 24;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public short getRecordId() {
        return RECORD_ID;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public String getRecordName() {
        return EscherRecordTypes.SPGR.recordName;
    }

    public int getRectX1() {
        return this.field_1_rectX1;
    }

    public void setRectX1(int x1) {
        this.field_1_rectX1 = x1;
    }

    public int getRectY1() {
        return this.field_2_rectY1;
    }

    public void setRectY1(int y1) {
        this.field_2_rectY1 = y1;
    }

    public int getRectX2() {
        return this.field_3_rectX2;
    }

    public void setRectX2(int x2) {
        this.field_3_rectX2 = x2;
    }

    public int getRectY2() {
        return this.field_4_rectY2;
    }

    public void setRectY2(int rectY2) {
        this.field_4_rectY2 = rectY2;
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.ddf.EscherSpgrRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherSpgrRecord.this.m2245x9518d532();
            }
        }, "rectX1", new Supplier() { // from class: org.apache.poi.ddf.EscherSpgrRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherSpgrRecord.this.getRectX1());
            }
        }, "rectY1", new Supplier() { // from class: org.apache.poi.ddf.EscherSpgrRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherSpgrRecord.this.getRectY1());
            }
        }, "rectX2", new Supplier() { // from class: org.apache.poi.ddf.EscherSpgrRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherSpgrRecord.this.getRectX2());
            }
        }, "rectY2", new Supplier() { // from class: org.apache.poi.ddf.EscherSpgrRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherSpgrRecord.this.getRectY2());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherSpgrRecord, reason: not valid java name */
    public /* synthetic */ Object m2245x9518d532() {
        return super.getGenericProperties();
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Enum getGenericRecordType() {
        return EscherRecordTypes.SPGR;
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.Duplicatable
    public EscherSpgrRecord copy() {
        return new EscherSpgrRecord(this);
    }
}
