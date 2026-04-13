package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class NameCommentRecord extends StandardRecord {
    public static final short sid = 2196;
    private final short field_1_record_type;
    private final short field_2_frt_cell_ref_flag;
    private final long field_3_reserved;
    private String field_6_name_text;
    private String field_7_comment_text;

    public NameCommentRecord(NameCommentRecord other) {
        this.field_1_record_type = other.field_1_record_type;
        this.field_2_frt_cell_ref_flag = other.field_2_frt_cell_ref_flag;
        this.field_3_reserved = other.field_3_reserved;
        this.field_6_name_text = other.field_6_name_text;
        this.field_7_comment_text = other.field_7_comment_text;
    }

    public NameCommentRecord(String name, String comment) {
        this.field_1_record_type = (short) 0;
        this.field_2_frt_cell_ref_flag = (short) 0;
        this.field_3_reserved = 0L;
        this.field_6_name_text = name;
        this.field_7_comment_text = comment;
    }

    public NameCommentRecord(RecordInputStream ris) {
        this.field_1_record_type = ris.readShort();
        this.field_2_frt_cell_ref_flag = ris.readShort();
        this.field_3_reserved = ris.readLong();
        int field_4_name_length = ris.readShort();
        int field_5_comment_length = ris.readShort();
        if (ris.readByte() == 0) {
            this.field_6_name_text = StringUtil.readCompressedUnicode(ris, field_4_name_length);
        } else {
            this.field_6_name_text = StringUtil.readUnicodeLE(ris, field_4_name_length);
        }
        if (ris.readByte() == 0) {
            this.field_7_comment_text = StringUtil.readCompressedUnicode(ris, field_5_comment_length);
        } else {
            this.field_7_comment_text = StringUtil.readUnicodeLE(ris, field_5_comment_length);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput littleEndianOutput) {
        int length = this.field_6_name_text.length();
        int length2 = this.field_7_comment_text.length();
        littleEndianOutput.writeShort(this.field_1_record_type);
        littleEndianOutput.writeShort(this.field_2_frt_cell_ref_flag);
        littleEndianOutput.writeLong(this.field_3_reserved);
        littleEndianOutput.writeShort(length);
        littleEndianOutput.writeShort(length2);
        boolean hasMultibyte = StringUtil.hasMultibyte(this.field_6_name_text);
        littleEndianOutput.writeByte(hasMultibyte ? 1 : 0);
        if (hasMultibyte) {
            StringUtil.putUnicodeLE(this.field_6_name_text, littleEndianOutput);
        } else {
            StringUtil.putCompressedUnicode(this.field_6_name_text, littleEndianOutput);
        }
        boolean hasMultibyte2 = StringUtil.hasMultibyte(this.field_7_comment_text);
        littleEndianOutput.writeByte(hasMultibyte2 ? 1 : 0);
        if (hasMultibyte2) {
            StringUtil.putUnicodeLE(this.field_7_comment_text, littleEndianOutput);
        } else {
            StringUtil.putCompressedUnicode(this.field_7_comment_text, littleEndianOutput);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return (StringUtil.hasMultibyte(this.field_6_name_text) ? this.field_6_name_text.length() * 2 : this.field_6_name_text.length()) + 18 + (StringUtil.hasMultibyte(this.field_7_comment_text) ? this.field_7_comment_text.length() * 2 : this.field_7_comment_text.length());
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    public String getNameText() {
        return this.field_6_name_text;
    }

    public void setNameText(String newName) {
        this.field_6_name_text = newName;
    }

    public String getCommentText() {
        return this.field_7_comment_text;
    }

    public void setCommentText(String comment) {
        this.field_7_comment_text = comment;
    }

    public short getRecordType() {
        return this.field_1_record_type;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public NameCommentRecord copy() {
        return new NameCommentRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.NAME_COMMENT;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("recordType", new Supplier() { // from class: org.apache.poi.hssf.record.NameCommentRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(NameCommentRecord.this.getRecordType());
            }
        }, "frtCellRefFlag", new Supplier() { // from class: org.apache.poi.hssf.record.NameCommentRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return NameCommentRecord.this.m2338xfb12a8c6();
            }
        }, "reserved", new Supplier() { // from class: org.apache.poi.hssf.record.NameCommentRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return NameCommentRecord.this.m2339xb48a3665();
            }
        }, "name", new Supplier() { // from class: org.apache.poi.hssf.record.NameCommentRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return NameCommentRecord.this.getNameText();
            }
        }, "comment", new Supplier() { // from class: org.apache.poi.hssf.record.NameCommentRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return NameCommentRecord.this.getCommentText();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-NameCommentRecord, reason: not valid java name */
    public /* synthetic */ Object m2338xfb12a8c6() {
        return Short.valueOf(this.field_2_frt_cell_ref_flag);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-NameCommentRecord, reason: not valid java name */
    public /* synthetic */ Object m2339xb48a3665() {
        return Long.valueOf(this.field_3_reserved);
    }
}
