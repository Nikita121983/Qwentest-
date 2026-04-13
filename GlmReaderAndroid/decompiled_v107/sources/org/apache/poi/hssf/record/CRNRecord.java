package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.constant.ConstantValueParser;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class CRNRecord extends StandardRecord {
    public static final short sid = 90;
    private int field_1_last_column_index;
    private int field_2_first_column_index;
    private int field_3_row_index;
    private Object[] field_4_constant_values;

    public CRNRecord(CRNRecord other) {
        super(other);
        this.field_1_last_column_index = other.field_1_last_column_index;
        this.field_2_first_column_index = other.field_2_first_column_index;
        this.field_3_row_index = other.field_3_row_index;
        this.field_4_constant_values = other.field_4_constant_values == null ? null : (Object[]) other.field_4_constant_values.clone();
    }

    public CRNRecord(RecordInputStream in) {
        this.field_1_last_column_index = in.readUByte();
        this.field_2_first_column_index = in.readUByte();
        this.field_3_row_index = in.readShort();
        int nValues = (this.field_1_last_column_index - this.field_2_first_column_index) + 1;
        this.field_4_constant_values = ConstantValueParser.parse(in, nValues);
    }

    public int getNumberOfCRNs() {
        return this.field_1_last_column_index;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return ConstantValueParser.getEncodedSize(this.field_4_constant_values) + 4;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeByte(this.field_1_last_column_index);
        out.writeByte(this.field_2_first_column_index);
        out.writeShort(this.field_3_row_index);
        ConstantValueParser.encode(out, this.field_4_constant_values);
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 90;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public CRNRecord copy() {
        return new CRNRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CRN;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("row", new Supplier() { // from class: org.apache.poi.hssf.record.CRNRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return CRNRecord.this.m2265x5d48ba11();
            }
        }, "firstColumn", new Supplier() { // from class: org.apache.poi.hssf.record.CRNRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return CRNRecord.this.m2266xbe9b56b0();
            }
        }, "lastColumn", new Supplier() { // from class: org.apache.poi.hssf.record.CRNRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return CRNRecord.this.m2267x1fedf34f();
            }
        }, "constantValues", new Supplier() { // from class: org.apache.poi.hssf.record.CRNRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return CRNRecord.this.m2268x81408fee();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-CRNRecord, reason: not valid java name */
    public /* synthetic */ Object m2265x5d48ba11() {
        return Integer.valueOf(this.field_3_row_index);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-CRNRecord, reason: not valid java name */
    public /* synthetic */ Object m2266xbe9b56b0() {
        return Integer.valueOf(this.field_2_first_column_index);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-CRNRecord, reason: not valid java name */
    public /* synthetic */ Object m2267x1fedf34f() {
        return Integer.valueOf(this.field_1_last_column_index);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-CRNRecord, reason: not valid java name */
    public /* synthetic */ Object m2268x81408fee() {
        return this.field_4_constant_values;
    }
}
