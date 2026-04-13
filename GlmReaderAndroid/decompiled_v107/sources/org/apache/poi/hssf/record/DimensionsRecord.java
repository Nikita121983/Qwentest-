package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class DimensionsRecord extends StandardRecord {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) DimensionsRecord.class);
    public static final short sid = 512;
    private int field_1_first_row;
    private int field_2_last_row;
    private short field_3_first_col;
    private short field_4_last_col;
    private short field_5_zero;

    public DimensionsRecord() {
    }

    public DimensionsRecord(DimensionsRecord other) {
        super(other);
        this.field_1_first_row = other.field_1_first_row;
        this.field_2_last_row = other.field_2_last_row;
        this.field_3_first_col = other.field_3_first_col;
        this.field_4_last_col = other.field_4_last_col;
        this.field_5_zero = other.field_5_zero;
    }

    public DimensionsRecord(RecordInputStream in) {
        this.field_1_first_row = in.readInt();
        this.field_2_last_row = in.readInt();
        this.field_3_first_col = in.readShort();
        this.field_4_last_col = in.readShort();
        this.field_5_zero = in.readShort();
        if (in.available() == 2) {
            LOG.atInfo().log("DimensionsRecord has extra 2 bytes.");
            in.readShort();
        }
    }

    public void setFirstRow(int row) {
        this.field_1_first_row = row;
    }

    public void setLastRow(int row) {
        this.field_2_last_row = row;
    }

    public void setFirstCol(short col) {
        this.field_3_first_col = col;
    }

    public void setLastCol(short col) {
        this.field_4_last_col = col;
    }

    public int getFirstRow() {
        return this.field_1_first_row;
    }

    public int getLastRow() {
        return this.field_2_last_row;
    }

    public short getFirstCol() {
        return this.field_3_first_col;
    }

    public short getLastCol() {
        return this.field_4_last_col;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeInt(getFirstRow());
        out.writeInt(getLastRow());
        out.writeShort(getFirstCol());
        out.writeShort(getLastCol());
        out.writeShort(0);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 14;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public DimensionsRecord copy() {
        return new DimensionsRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DIMENSIONS;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("firstRow", new Supplier() { // from class: org.apache.poi.hssf.record.DimensionsRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(DimensionsRecord.this.getFirstRow());
            }
        }, "lastRow", new Supplier() { // from class: org.apache.poi.hssf.record.DimensionsRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(DimensionsRecord.this.getLastRow());
            }
        }, "firstColumn", new Supplier() { // from class: org.apache.poi.hssf.record.DimensionsRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(DimensionsRecord.this.getFirstCol());
            }
        }, "lastColumn", new Supplier() { // from class: org.apache.poi.hssf.record.DimensionsRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(DimensionsRecord.this.getLastCol());
            }
        }, "zero", new Supplier() { // from class: org.apache.poi.hssf.record.DimensionsRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return DimensionsRecord.this.m2277xa50b105d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-DimensionsRecord, reason: not valid java name */
    public /* synthetic */ Object m2277xa50b105d() {
        return Short.valueOf(this.field_5_zero);
    }
}
