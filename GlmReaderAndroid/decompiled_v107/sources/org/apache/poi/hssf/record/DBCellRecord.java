package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class DBCellRecord extends StandardRecord {
    public static final int BLOCK_SIZE = 32;
    public static final short sid = 215;
    private final int field_1_row_offset;
    private final short[] field_2_cell_offsets;

    public DBCellRecord(int rowOffset, short[] cellOffsets) {
        this.field_1_row_offset = rowOffset;
        this.field_2_cell_offsets = cellOffsets;
    }

    public DBCellRecord(RecordInputStream in) {
        this.field_1_row_offset = in.readUShort();
        int size = in.remaining();
        this.field_2_cell_offsets = new short[size / 2];
        for (int i = 0; i < this.field_2_cell_offsets.length; i++) {
            this.field_2_cell_offsets[i] = in.readShort();
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeInt(this.field_1_row_offset);
        for (short field_2_cell_offset : this.field_2_cell_offsets) {
            out.writeShort(field_2_cell_offset);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return (this.field_2_cell_offsets.length * 2) + 4;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public DBCellRecord copy() {
        return this;
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DB_CELL;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rowOffset", new Supplier() { // from class: org.apache.poi.hssf.record.DBCellRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return DBCellRecord.this.m2270x4ead68b0();
            }
        }, "cellOffsets", new Supplier() { // from class: org.apache.poi.hssf.record.DBCellRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return DBCellRecord.this.m2271xdb4d93b1();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-DBCellRecord, reason: not valid java name */
    public /* synthetic */ Object m2270x4ead68b0() {
        return Integer.valueOf(this.field_1_row_offset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-DBCellRecord, reason: not valid java name */
    public /* synthetic */ Object m2271xdb4d93b1() {
        return this.field_2_cell_offsets;
    }
}
