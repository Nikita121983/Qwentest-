package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class NoteRecord extends StandardRecord {
    public static final short NOTE_HIDDEN = 0;
    public static final short NOTE_VISIBLE = 2;
    public static final short sid = 28;
    private int field_1_row;
    private int field_2_col;
    private short field_3_flags;
    private int field_4_shapeid;
    private boolean field_5_hasMultibyte;
    private String field_6_author;
    private Byte field_7_padding;
    public static final NoteRecord[] EMPTY_ARRAY = new NoteRecord[0];
    private static final Byte DEFAULT_PADDING = (byte) 0;

    public NoteRecord() {
        this.field_6_author = "";
        this.field_3_flags = (short) 0;
        this.field_7_padding = DEFAULT_PADDING;
    }

    public NoteRecord(NoteRecord other) {
        super(other);
        this.field_1_row = other.field_1_row;
        this.field_2_col = other.field_2_col;
        this.field_3_flags = other.field_3_flags;
        this.field_4_shapeid = other.field_4_shapeid;
        this.field_5_hasMultibyte = other.field_5_hasMultibyte;
        this.field_6_author = other.field_6_author;
        this.field_7_padding = other.field_7_padding;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 28;
    }

    public NoteRecord(RecordInputStream in) {
        this.field_1_row = in.readUShort();
        this.field_2_col = in.readShort();
        this.field_3_flags = in.readShort();
        this.field_4_shapeid = in.readUShort();
        int length = in.readShort();
        this.field_5_hasMultibyte = in.readByte() != 0;
        if (this.field_5_hasMultibyte) {
            this.field_6_author = StringUtil.readUnicodeLE(in, length);
        } else {
            this.field_6_author = StringUtil.readCompressedUnicode(in, length);
        }
        if (in.available() == 1) {
            this.field_7_padding = Byte.valueOf(in.readByte());
        } else if (in.available() == 2 && length == 0) {
            this.field_7_padding = Byte.valueOf(in.readByte());
            in.readByte();
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_row);
        littleEndianOutput.writeShort(this.field_2_col);
        littleEndianOutput.writeShort(this.field_3_flags);
        littleEndianOutput.writeShort(this.field_4_shapeid);
        littleEndianOutput.writeShort(this.field_6_author.length());
        littleEndianOutput.writeByte(this.field_5_hasMultibyte ? 1 : 0);
        if (this.field_5_hasMultibyte) {
            StringUtil.putUnicodeLE(this.field_6_author, littleEndianOutput);
        } else {
            StringUtil.putCompressedUnicode(this.field_6_author, littleEndianOutput);
        }
        if (this.field_7_padding != null) {
            littleEndianOutput.writeByte(this.field_7_padding.intValue());
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return (this.field_6_author.length() * (this.field_5_hasMultibyte ? 2 : 1)) + 11 + (this.field_7_padding == null ? 0 : 1);
    }

    public int getRow() {
        return this.field_1_row;
    }

    public void setRow(int row) {
        this.field_1_row = row;
    }

    public int getColumn() {
        return this.field_2_col;
    }

    public void setColumn(int col) {
        this.field_2_col = col;
    }

    public short getFlags() {
        return this.field_3_flags;
    }

    public void setFlags(short flags) {
        this.field_3_flags = flags;
    }

    boolean authorIsMultibyte() {
        return this.field_5_hasMultibyte;
    }

    public int getShapeId() {
        return this.field_4_shapeid;
    }

    public void setShapeId(int id) {
        this.field_4_shapeid = id;
    }

    public String getAuthor() {
        return this.field_6_author;
    }

    public void setAuthor(String author) {
        this.field_6_author = author;
        this.field_5_hasMultibyte = StringUtil.hasMultibyte(author);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public NoteRecord copy() {
        return new NoteRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.NOTE;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("row", new Supplier() { // from class: org.apache.poi.hssf.record.NoteRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(NoteRecord.this.getRow());
            }
        }, "column", new Supplier() { // from class: org.apache.poi.hssf.record.NoteRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(NoteRecord.this.getColumn());
            }
        }, "flags", new Supplier() { // from class: org.apache.poi.hssf.record.NoteRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(NoteRecord.this.getFlags());
            }
        }, "shapeId", new Supplier() { // from class: org.apache.poi.hssf.record.NoteRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(NoteRecord.this.getShapeId());
            }
        }, "author", new Supplier() { // from class: org.apache.poi.hssf.record.NoteRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return NoteRecord.this.getAuthor();
            }
        });
    }
}
