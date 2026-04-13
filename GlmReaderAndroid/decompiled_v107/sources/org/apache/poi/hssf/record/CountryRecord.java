package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class CountryRecord extends StandardRecord {
    public static final short sid = 140;
    private short field_1_default_country;
    private short field_2_current_country;

    public CountryRecord() {
    }

    public CountryRecord(CountryRecord other) {
        super(other);
        this.field_1_default_country = other.field_1_default_country;
        this.field_2_current_country = other.field_2_current_country;
    }

    public CountryRecord(RecordInputStream in) {
        this.field_1_default_country = in.readShort();
        this.field_2_current_country = in.readShort();
    }

    public void setDefaultCountry(short country) {
        this.field_1_default_country = country;
    }

    public void setCurrentCountry(short country) {
        this.field_2_current_country = country;
    }

    public short getDefaultCountry() {
        return this.field_1_default_country;
    }

    public short getCurrentCountry() {
        return this.field_2_current_country;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(getDefaultCountry());
        out.writeShort(getCurrentCountry());
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 4;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 140;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public CountryRecord copy() {
        return new CountryRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.COUNTRY;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("defaultCountry", new Supplier() { // from class: org.apache.poi.hssf.record.CountryRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(CountryRecord.this.getDefaultCountry());
            }
        }, "currentCountry", new Supplier() { // from class: org.apache.poi.hssf.record.CountryRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(CountryRecord.this.getCurrentCountry());
            }
        });
    }
}
