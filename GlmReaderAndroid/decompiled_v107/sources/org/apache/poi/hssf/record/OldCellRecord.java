package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;

/* loaded from: classes10.dex */
public abstract class OldCellRecord implements GenericRecord {
    private final int field_1_row;
    private final short field_2_column;
    private int field_3_cell_attrs;
    private short field_3_xf_index;
    private final boolean isBiff2;
    private final short sid;

    /* JADX INFO: Access modifiers changed from: protected */
    public OldCellRecord(RecordInputStream in, boolean isBiff2) {
        this.sid = in.getSid();
        this.isBiff2 = isBiff2;
        this.field_1_row = in.readUShort();
        this.field_2_column = in.readShort();
        if (isBiff2) {
            this.field_3_cell_attrs = in.readUShort() << 8;
            this.field_3_cell_attrs += in.readUByte();
        } else {
            this.field_3_xf_index = in.readShort();
        }
    }

    public final int getRow() {
        return this.field_1_row;
    }

    public final short getColumn() {
        return this.field_2_column;
    }

    public final short getXFIndex() {
        return this.field_3_xf_index;
    }

    public int getCellAttrs() {
        return this.field_3_cell_attrs;
    }

    public boolean isBiff2() {
        return this.isBiff2;
    }

    public short getSid() {
        return this.sid;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("row", new Supplier() { // from class: org.apache.poi.hssf.record.OldCellRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(OldCellRecord.this.getRow());
            }
        }, "column", new Supplier() { // from class: org.apache.poi.hssf.record.OldCellRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(OldCellRecord.this.getColumn());
            }
        }, "biff2", new Supplier() { // from class: org.apache.poi.hssf.record.OldCellRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(OldCellRecord.this.isBiff2());
            }
        }, "biff2CellAttrs", new Supplier() { // from class: org.apache.poi.hssf.record.OldCellRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(OldCellRecord.this.getCellAttrs());
            }
        }, "xfIndex", new Supplier() { // from class: org.apache.poi.hssf.record.OldCellRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(OldCellRecord.this.getXFIndex());
            }
        });
    }

    public final String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }
}
