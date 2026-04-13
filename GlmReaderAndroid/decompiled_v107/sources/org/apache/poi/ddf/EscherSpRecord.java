package org.apache.poi.ddf;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;

/* loaded from: classes10.dex */
public class EscherSpRecord extends EscherRecord {
    public static final int FLAG_BACKGROUND = 1024;
    public static final int FLAG_CHILD = 2;
    public static final int FLAG_CONNECTOR = 256;
    public static final int FLAG_DELETED = 8;
    public static final int FLAG_FLIPHORIZ = 64;
    public static final int FLAG_FLIPVERT = 128;
    public static final int FLAG_GROUP = 1;
    public static final int FLAG_HASSHAPETYPE = 2048;
    public static final int FLAG_HAVEANCHOR = 512;
    public static final int FLAG_HAVEMASTER = 32;
    public static final int FLAG_OLESHAPE = 16;
    public static final int FLAG_PATRIARCH = 4;
    private int field_1_shapeId;
    private int field_2_flags;
    public static final short RECORD_ID = EscherRecordTypes.SP.typeID;
    private static final int[] FLAGS_MASKS = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048};
    private static final String[] FLAGS_NAMES = {"GROUP", "CHILD", "PATRIARCH", "DELETED", "OLESHAPE", "HAVEMASTER", "FLIPHORIZ", "FLIPVERT", "CONNECTOR", "HAVEANCHOR", "BACKGROUND", "HASSHAPETYPE"};

    public EscherSpRecord() {
    }

    public EscherSpRecord(EscherSpRecord other) {
        super(other);
        this.field_1_shapeId = other.field_1_shapeId;
        this.field_2_flags = other.field_2_flags;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        readHeader(data, offset);
        int pos = offset + 8;
        this.field_1_shapeId = LittleEndian.getInt(data, pos + 0);
        int size = 0 + 4;
        this.field_2_flags = LittleEndian.getInt(data, pos + size);
        int i = size + 4;
        return getRecordSize();
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, getRecordId(), this);
        LittleEndian.putShort(data, offset, getOptions());
        LittleEndian.putShort(data, offset + 2, getRecordId());
        LittleEndian.putInt(data, offset + 4, 8);
        LittleEndian.putInt(data, offset + 8, this.field_1_shapeId);
        LittleEndian.putInt(data, offset + 12, this.field_2_flags);
        listener.afterRecordSerialize(getRecordSize() + offset, getRecordId(), getRecordSize(), this);
        return 16;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int getRecordSize() {
        return 16;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public short getRecordId() {
        return RECORD_ID;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public String getRecordName() {
        return EscherRecordTypes.SP.recordName;
    }

    private String decodeFlags(int flags) {
        StringBuilder result = new StringBuilder();
        result.append((flags & 1) != 0 ? "|GROUP" : "");
        result.append((flags & 2) != 0 ? "|CHILD" : "");
        result.append((flags & 4) != 0 ? "|PATRIARCH" : "");
        result.append((flags & 8) != 0 ? "|DELETED" : "");
        result.append((flags & 16) != 0 ? "|OLESHAPE" : "");
        result.append((flags & 32) != 0 ? "|HAVEMASTER" : "");
        result.append((flags & 64) != 0 ? "|FLIPHORIZ" : "");
        result.append((flags & 128) != 0 ? "|FLIPVERT" : "");
        result.append((flags & 256) != 0 ? "|CONNECTOR" : "");
        result.append((flags & 512) != 0 ? "|HAVEANCHOR" : "");
        result.append((flags & 1024) != 0 ? "|BACKGROUND" : "");
        result.append((flags & 2048) != 0 ? "|HASSHAPETYPE" : "");
        if (result.length() > 0) {
            result.deleteCharAt(0);
        }
        return result.toString();
    }

    public int getShapeId() {
        return this.field_1_shapeId;
    }

    public void setShapeId(int field_1_shapeId) {
        this.field_1_shapeId = field_1_shapeId;
    }

    public int getFlags() {
        return this.field_2_flags;
    }

    public void setFlags(int field_2_flags) {
        this.field_2_flags = field_2_flags;
    }

    public short getShapeType() {
        return getInstance();
    }

    public void setShapeType(short value) {
        setInstance(value);
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.ddf.EscherSpRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherSpRecord.this.m2244lambda$getGenericProperties$0$orgapachepoiddfEscherSpRecord();
            }
        }, "shapeType", new Supplier() { // from class: org.apache.poi.ddf.EscherSpRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(EscherSpRecord.this.getShapeType());
            }
        }, "shapeId", new Supplier() { // from class: org.apache.poi.ddf.EscherSpRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherSpRecord.this.getShapeId());
            }
        }, "flags", GenericRecordUtil.getBitsAsString((Supplier<Number>) new Supplier() { // from class: org.apache.poi.ddf.EscherSpRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherSpRecord.this.getFlags());
            }
        }, FLAGS_MASKS, FLAGS_NAMES));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherSpRecord, reason: not valid java name */
    public /* synthetic */ Object m2244lambda$getGenericProperties$0$orgapachepoiddfEscherSpRecord() {
        return super.getGenericProperties();
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Enum getGenericRecordType() {
        return EscherRecordTypes.SP;
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.Duplicatable
    public EscherSpRecord copy() {
        return new EscherSpRecord(this);
    }
}
