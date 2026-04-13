package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
public final class LabelRecord extends Record implements CellValueRecordInterface {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) LabelRecord.class);
    public static final short sid = 516;
    private int field_1_row;
    private short field_2_column;
    private short field_3_xf_index;
    private short field_4_string_len;
    private byte field_5_unicode_flag;
    private String field_6_value;

    public LabelRecord() {
    }

    public LabelRecord(LabelRecord other) {
        super(other);
        this.field_1_row = other.field_1_row;
        this.field_2_column = other.field_2_column;
        this.field_3_xf_index = other.field_3_xf_index;
        this.field_4_string_len = other.field_4_string_len;
        this.field_5_unicode_flag = other.field_5_unicode_flag;
        this.field_6_value = other.field_6_value;
    }

    public LabelRecord(RecordInputStream in) {
        this.field_1_row = in.readUShort();
        this.field_2_column = in.readShort();
        this.field_3_xf_index = in.readShort();
        this.field_4_string_len = in.readShort();
        this.field_5_unicode_flag = in.readByte();
        if (this.field_4_string_len > 0) {
            if (isUnCompressedUnicode()) {
                this.field_6_value = in.readUnicodeLEString(this.field_4_string_len);
            } else {
                this.field_6_value = in.readCompressedUnicode(this.field_4_string_len);
            }
        } else {
            this.field_6_value = "";
        }
        if (in.remaining() > 0) {
            LOG.atInfo().log("LabelRecord data remains: {} : {}", Unbox.box(in.remaining()), HexDump.toHex(in.readRemainder()));
        }
    }

    @Override // org.apache.poi.hssf.record.CellValueRecordInterface
    public int getRow() {
        return this.field_1_row;
    }

    @Override // org.apache.poi.hssf.record.CellValueRecordInterface
    public short getColumn() {
        return this.field_2_column;
    }

    @Override // org.apache.poi.hssf.record.CellValueRecordInterface
    public short getXFIndex() {
        return this.field_3_xf_index;
    }

    public short getStringLength() {
        return this.field_4_string_len;
    }

    public boolean isUnCompressedUnicode() {
        return (this.field_5_unicode_flag & 1) != 0;
    }

    public String getValue() {
        return this.field_6_value;
    }

    @Override // org.apache.poi.hssf.record.RecordBase
    public int serialize(int offset, byte[] data) {
        throw new RecordFormatException("Label Records are supported READ ONLY...convert to LabelSST");
    }

    @Override // org.apache.poi.hssf.record.RecordBase
    public int getRecordSize() {
        throw new RecordFormatException("Label Records are supported READ ONLY...convert to LabelSST");
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 516;
    }

    @Override // org.apache.poi.hssf.record.CellValueRecordInterface
    public void setColumn(short col) {
    }

    @Override // org.apache.poi.hssf.record.CellValueRecordInterface
    public void setRow(int row) {
    }

    @Override // org.apache.poi.hssf.record.CellValueRecordInterface
    public void setXFIndex(short xf) {
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public LabelRecord copy() {
        return new LabelRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.LABEL;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("row", new Supplier() { // from class: org.apache.poi.hssf.record.LabelRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(LabelRecord.this.getRow());
            }
        }, "column", new Supplier() { // from class: org.apache.poi.hssf.record.LabelRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(LabelRecord.this.getColumn());
            }
        }, "xfIndex", new Supplier() { // from class: org.apache.poi.hssf.record.LabelRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(LabelRecord.this.getXFIndex());
            }
        }, "stringLen", new Supplier() { // from class: org.apache.poi.hssf.record.LabelRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(LabelRecord.this.getStringLength());
            }
        }, "unCompressedUnicode", new Supplier() { // from class: org.apache.poi.hssf.record.LabelRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(LabelRecord.this.isUnCompressedUnicode());
            }
        }, "value", new Supplier() { // from class: org.apache.poi.hssf.record.LabelRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return LabelRecord.this.getValue();
            }
        });
    }
}
