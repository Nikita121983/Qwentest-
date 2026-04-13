package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class SeriesIndexRecord extends StandardRecord {
    public static final short sid = 4197;
    private short field_1_index;

    public SeriesIndexRecord() {
    }

    public SeriesIndexRecord(SeriesIndexRecord other) {
        super(other);
        this.field_1_index = other.field_1_index;
    }

    public SeriesIndexRecord(RecordInputStream in) {
        this.field_1_index = in.readShort();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_index);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public SeriesIndexRecord copy() {
        return new SeriesIndexRecord(this);
    }

    public short getIndex() {
        return this.field_1_index;
    }

    public void setIndex(short field_1_index) {
        this.field_1_index = field_1_index;
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SERIES_INDEX;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("index", new Supplier() { // from class: org.apache.poi.hssf.record.chart.SeriesIndexRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(SeriesIndexRecord.this.getIndex());
            }
        });
    }
}
