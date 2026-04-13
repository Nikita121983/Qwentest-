package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class DVALRecord extends StandardRecord {
    public static final short sid = 434;
    private short field_1_options;
    private int field_2_horiz_pos;
    private int field_3_vert_pos;
    private int field_5_dv_no;
    private int field_cbo_id;

    public DVALRecord() {
        this.field_cbo_id = -1;
        this.field_5_dv_no = 0;
    }

    public DVALRecord(DVALRecord other) {
        super(other);
        this.field_1_options = other.field_1_options;
        this.field_2_horiz_pos = other.field_2_horiz_pos;
        this.field_3_vert_pos = other.field_3_vert_pos;
        this.field_cbo_id = other.field_cbo_id;
        this.field_5_dv_no = other.field_5_dv_no;
    }

    public DVALRecord(RecordInputStream in) {
        this.field_1_options = in.readShort();
        this.field_2_horiz_pos = in.readInt();
        this.field_3_vert_pos = in.readInt();
        this.field_cbo_id = in.readInt();
        this.field_5_dv_no = in.readInt();
    }

    public void setOptions(short options) {
        this.field_1_options = options;
    }

    public void setHorizontalPos(int horiz_pos) {
        this.field_2_horiz_pos = horiz_pos;
    }

    public void setVerticalPos(int vert_pos) {
        this.field_3_vert_pos = vert_pos;
    }

    public void setObjectID(int cboID) {
        this.field_cbo_id = cboID;
    }

    public void setDVRecNo(int dvNo) {
        this.field_5_dv_no = dvNo;
    }

    public short getOptions() {
        return this.field_1_options;
    }

    public int getHorizontalPos() {
        return this.field_2_horiz_pos;
    }

    public int getVerticalPos() {
        return this.field_3_vert_pos;
    }

    public int getObjectID() {
        return this.field_cbo_id;
    }

    public int getDVRecNo() {
        return this.field_5_dv_no;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(getOptions());
        out.writeInt(getHorizontalPos());
        out.writeInt(getVerticalPos());
        out.writeInt(getObjectID());
        out.writeInt(getDVRecNo());
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 18;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public DVALRecord copy() {
        return new DVALRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DVAL;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("options", new Supplier() { // from class: org.apache.poi.hssf.record.DVALRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(DVALRecord.this.getOptions());
            }
        }, "horizPos", new Supplier() { // from class: org.apache.poi.hssf.record.DVALRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(DVALRecord.this.getHorizontalPos());
            }
        }, "vertPos", new Supplier() { // from class: org.apache.poi.hssf.record.DVALRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(DVALRecord.this.getVerticalPos());
            }
        }, "comboObjectID", new Supplier() { // from class: org.apache.poi.hssf.record.DVALRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(DVALRecord.this.getObjectID());
            }
        }, "dvRecordsNumber", new Supplier() { // from class: org.apache.poi.hssf.record.DVALRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(DVALRecord.this.getDVRecNo());
            }
        });
    }
}
