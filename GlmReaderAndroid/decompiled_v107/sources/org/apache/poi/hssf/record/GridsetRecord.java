package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class GridsetRecord extends StandardRecord {
    public static final short sid = 130;
    private short field_1_gridset_flag;

    public GridsetRecord() {
    }

    public GridsetRecord(GridsetRecord other) {
        super(other);
        this.field_1_gridset_flag = other.field_1_gridset_flag;
    }

    public GridsetRecord(RecordInputStream in) {
        this.field_1_gridset_flag = in.readShort();
    }

    public void setGridset(boolean gridset) {
        if (gridset) {
            this.field_1_gridset_flag = (short) 1;
        } else {
            this.field_1_gridset_flag = (short) 0;
        }
    }

    public boolean getGridset() {
        return this.field_1_gridset_flag == 1;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_gridset_flag);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 130;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public GridsetRecord copy() {
        return new GridsetRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.GRIDSET;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("gridset", new Supplier() { // from class: org.apache.poi.hssf.record.GridsetRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(GridsetRecord.this.getGridset());
            }
        });
    }
}
