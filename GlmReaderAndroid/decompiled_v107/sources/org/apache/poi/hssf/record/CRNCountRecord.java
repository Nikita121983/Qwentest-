package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class CRNCountRecord extends StandardRecord {
    private static final short DATA_SIZE = 4;
    public static final short sid = 89;
    private int field_1_number_crn_records;
    private int field_2_sheet_table_index;

    public CRNCountRecord(CRNCountRecord other) {
        super(other);
        this.field_1_number_crn_records = other.field_1_number_crn_records;
        this.field_2_sheet_table_index = other.field_2_sheet_table_index;
    }

    public CRNCountRecord(RecordInputStream in) {
        this.field_1_number_crn_records = in.readShort();
        if (this.field_1_number_crn_records < 0) {
            this.field_1_number_crn_records = (short) (-this.field_1_number_crn_records);
        }
        this.field_2_sheet_table_index = in.readShort();
    }

    public int getNumberOfCRNs() {
        return this.field_1_number_crn_records;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort((short) this.field_1_number_crn_records);
        out.writeShort((short) this.field_2_sheet_table_index);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 4;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 89;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public CRNCountRecord copy() {
        return new CRNCountRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CRN_COUNT;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("numberOfCRNs", new Supplier() { // from class: org.apache.poi.hssf.record.CRNCountRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(CRNCountRecord.this.getNumberOfCRNs());
            }
        }, "sheetTableIndex", new Supplier() { // from class: org.apache.poi.hssf.record.CRNCountRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return CRNCountRecord.this.m2264x5957ac80();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-CRNCountRecord, reason: not valid java name */
    public /* synthetic */ Object m2264x5957ac80() {
        return Integer.valueOf(this.field_2_sheet_table_index);
    }
}
