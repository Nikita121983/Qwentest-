package org.apache.poi.ddf;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
public class EscherSplitMenuColorsRecord extends EscherRecord {
    public static final short RECORD_ID = EscherRecordTypes.SPLIT_MENU_COLORS.typeID;
    private int field_1_color1;
    private int field_2_color2;
    private int field_3_color3;
    private int field_4_color4;

    public EscherSplitMenuColorsRecord() {
    }

    public EscherSplitMenuColorsRecord(EscherSplitMenuColorsRecord other) {
        super(other);
        this.field_1_color1 = other.field_1_color1;
        this.field_2_color2 = other.field_2_color2;
        this.field_3_color3 = other.field_3_color3;
        this.field_4_color4 = other.field_4_color4;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        int bytesRemaining = readHeader(data, offset);
        int pos = offset + 8;
        this.field_1_color1 = LittleEndian.getInt(data, pos + 0);
        int size = 0 + 4;
        this.field_2_color2 = LittleEndian.getInt(data, pos + size);
        int size2 = size + 4;
        this.field_3_color3 = LittleEndian.getInt(data, pos + size2);
        int size3 = size2 + 4;
        this.field_4_color4 = LittleEndian.getInt(data, pos + size3);
        int size4 = size3 + 4;
        int bytesRemaining2 = bytesRemaining - size4;
        if (bytesRemaining2 != 0) {
            throw new RecordFormatException("Expecting no remaining data but got " + bytesRemaining2 + " byte(s).");
        }
        return size4 + 8 + bytesRemaining2;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, getRecordId(), this);
        LittleEndian.putShort(data, offset, getOptions());
        int pos = offset + 2;
        LittleEndian.putShort(data, pos, getRecordId());
        int pos2 = pos + 2;
        int remainingBytes = getRecordSize() - 8;
        LittleEndian.putInt(data, pos2, remainingBytes);
        int pos3 = pos2 + 4;
        LittleEndian.putInt(data, pos3, this.field_1_color1);
        int pos4 = pos3 + 4;
        LittleEndian.putInt(data, pos4, this.field_2_color2);
        int pos5 = pos4 + 4;
        LittleEndian.putInt(data, pos5, this.field_3_color3);
        int pos6 = pos5 + 4;
        LittleEndian.putInt(data, pos6, this.field_4_color4);
        int pos7 = pos6 + 4;
        listener.afterRecordSerialize(pos7, getRecordId(), pos7 - offset, this);
        return getRecordSize();
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
        return EscherRecordTypes.SPLIT_MENU_COLORS.recordName;
    }

    public int getColor1() {
        return this.field_1_color1;
    }

    public void setColor1(int field_1_color1) {
        this.field_1_color1 = field_1_color1;
    }

    public int getColor2() {
        return this.field_2_color2;
    }

    public void setColor2(int field_2_color2) {
        this.field_2_color2 = field_2_color2;
    }

    public int getColor3() {
        return this.field_3_color3;
    }

    public void setColor3(int field_3_color3) {
        this.field_3_color3 = field_3_color3;
    }

    public int getColor4() {
        return this.field_4_color4;
    }

    public void setColor4(int field_4_color4) {
        this.field_4_color4 = field_4_color4;
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.ddf.EscherSplitMenuColorsRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherSplitMenuColorsRecord.this.m2246xe5f317c1();
            }
        }, "color1", new Supplier() { // from class: org.apache.poi.ddf.EscherSplitMenuColorsRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherSplitMenuColorsRecord.this.getColor1());
            }
        }, "color2", new Supplier() { // from class: org.apache.poi.ddf.EscherSplitMenuColorsRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherSplitMenuColorsRecord.this.getColor2());
            }
        }, "color3", new Supplier() { // from class: org.apache.poi.ddf.EscherSplitMenuColorsRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherSplitMenuColorsRecord.this.getColor3());
            }
        }, "color4", new Supplier() { // from class: org.apache.poi.ddf.EscherSplitMenuColorsRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherSplitMenuColorsRecord.this.getColor4());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherSplitMenuColorsRecord, reason: not valid java name */
    public /* synthetic */ Object m2246xe5f317c1() {
        return super.getGenericProperties();
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Enum getGenericRecordType() {
        return EscherRecordTypes.SPLIT_MENU_COLORS;
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.Duplicatable
    public EscherSplitMenuColorsRecord copy() {
        return new EscherSplitMenuColorsRecord(this);
    }
}
