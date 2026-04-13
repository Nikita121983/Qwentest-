package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class HideObjRecord extends StandardRecord {
    public static final short HIDE_ALL = 2;
    public static final short SHOW_ALL = 0;
    public static final short SHOW_PLACEHOLDERS = 1;
    public static final short sid = 141;
    private short field_1_hide_obj;

    public HideObjRecord() {
    }

    public HideObjRecord(HideObjRecord other) {
        super(other);
        this.field_1_hide_obj = other.field_1_hide_obj;
    }

    public HideObjRecord(RecordInputStream in) {
        this.field_1_hide_obj = in.readShort();
    }

    public void setHideObj(short hide) {
        this.field_1_hide_obj = hide;
    }

    public short getHideObj() {
        return this.field_1_hide_obj;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(getHideObj());
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 141;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public HideObjRecord copy() {
        return new HideObjRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.HIDE_OBJ;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("hideObj", new Supplier() { // from class: org.apache.poi.hssf.record.HideObjRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(HideObjRecord.this.getHideObj());
            }
        });
    }
}
