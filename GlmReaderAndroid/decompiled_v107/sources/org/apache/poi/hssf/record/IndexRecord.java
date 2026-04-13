package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IntList;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
public final class IndexRecord extends StandardRecord {
    public static final short sid = 523;
    private int field_2_first_row;
    private int field_3_last_row_add1;
    private int field_4_zero;
    private IntList field_5_dbcells;

    public IndexRecord() {
    }

    public IndexRecord(IndexRecord other) {
        super(other);
        this.field_2_first_row = other.field_2_first_row;
        this.field_3_last_row_add1 = other.field_3_last_row_add1;
        this.field_4_zero = other.field_4_zero;
        this.field_5_dbcells = other.field_5_dbcells == null ? null : new IntList(other.field_5_dbcells);
    }

    public IndexRecord(RecordInputStream in) {
        int field_1_zero = in.readInt();
        if (field_1_zero != 0) {
            throw new RecordFormatException("Expected zero for field 1 but got " + field_1_zero);
        }
        this.field_2_first_row = in.readInt();
        this.field_3_last_row_add1 = in.readInt();
        this.field_4_zero = in.readInt();
        int nCells = in.remaining() / 4;
        this.field_5_dbcells = new IntList(nCells);
        for (int i = 0; i < nCells; i++) {
            this.field_5_dbcells.add(in.readInt());
        }
    }

    public void setFirstRow(int row) {
        this.field_2_first_row = row;
    }

    public void setLastRowAdd1(int row) {
        this.field_3_last_row_add1 = row;
    }

    public void addDbcell(int cell) {
        if (this.field_5_dbcells == null) {
            this.field_5_dbcells = new IntList();
        }
        this.field_5_dbcells.add(cell);
    }

    public void setDbcell(int cell, int value) {
        this.field_5_dbcells.set(cell, value);
    }

    public int getFirstRow() {
        return this.field_2_first_row;
    }

    public int getLastRowAdd1() {
        return this.field_3_last_row_add1;
    }

    public int getNumDbcells() {
        if (this.field_5_dbcells == null) {
            return 0;
        }
        return this.field_5_dbcells.size();
    }

    public int getDbcellAt(int cellnum) {
        return this.field_5_dbcells.get(cellnum);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeInt(0);
        out.writeInt(getFirstRow());
        out.writeInt(getLastRowAdd1());
        out.writeInt(this.field_4_zero);
        for (int k = 0; k < getNumDbcells(); k++) {
            out.writeInt(getDbcellAt(k));
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return (getNumDbcells() * 4) + 16;
    }

    public static int getRecordSizeForBlockCount(int blockCount) {
        return (blockCount * 4) + 20;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public IndexRecord copy() {
        return new IndexRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.INDEX;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        Supplier supplier;
        Supplier supplier2 = new Supplier() { // from class: org.apache.poi.hssf.record.IndexRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(IndexRecord.this.getFirstRow());
            }
        };
        Supplier supplier3 = new Supplier() { // from class: org.apache.poi.hssf.record.IndexRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(IndexRecord.this.getLastRowAdd1());
            }
        };
        if (this.field_5_dbcells == null) {
            supplier = new Supplier() { // from class: org.apache.poi.hssf.record.IndexRecord$$ExternalSyntheticLambda2
                @Override // java.util.function.Supplier
                public final Object get() {
                    return IndexRecord.lambda$getGenericProperties$0();
                }
            };
        } else {
            final IntList intList = this.field_5_dbcells;
            intList.getClass();
            supplier = new Supplier() { // from class: org.apache.poi.hssf.record.IndexRecord$$ExternalSyntheticLambda3
                @Override // java.util.function.Supplier
                public final Object get() {
                    return IntList.this.toArray();
                }
            };
        }
        return GenericRecordUtil.getGenericProperties("firstRow", supplier2, "lastRowAdd1", supplier3, "dbcell_", supplier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$getGenericProperties$0() {
        return null;
    }
}
