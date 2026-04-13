package org.apache.poi.hssf.record;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class StyleRecord extends StandardRecord {
    public static final short sid = 659;
    private int field_1_xf_index;
    private int field_2_builtin_style;
    private int field_3_outline_style_level;
    private boolean field_3_stringHasMultibyte;
    private String field_4_name;
    private static final BitField styleIndexMask = BitFieldFactory.getInstance(4095);
    private static final BitField isBuiltinFlag = BitFieldFactory.getInstance(32768);

    public StyleRecord() {
        this.field_1_xf_index = isBuiltinFlag.set(0);
    }

    public StyleRecord(StyleRecord other) {
        super(other);
        this.field_1_xf_index = other.field_1_xf_index;
        this.field_2_builtin_style = other.field_2_builtin_style;
        this.field_3_outline_style_level = other.field_3_outline_style_level;
        this.field_3_stringHasMultibyte = other.field_3_stringHasMultibyte;
        this.field_4_name = other.field_4_name;
    }

    public StyleRecord(RecordInputStream in) {
        this.field_1_xf_index = in.readShort();
        if (isBuiltin()) {
            this.field_2_builtin_style = in.readByte();
            this.field_3_outline_style_level = in.readByte();
            return;
        }
        int field_2_name_length = in.readShort();
        if (in.remaining() < 1) {
            if (field_2_name_length != 0) {
                throw new RecordFormatException("Ran out of data reading style record");
            }
            this.field_4_name = "";
        } else {
            this.field_3_stringHasMultibyte = in.readByte() != 0;
            if (this.field_3_stringHasMultibyte) {
                this.field_4_name = StringUtil.readUnicodeLE(in, field_2_name_length);
            } else {
                this.field_4_name = StringUtil.readCompressedUnicode(in, field_2_name_length);
            }
        }
    }

    public void setXFIndex(int xfIndex) {
        this.field_1_xf_index = styleIndexMask.setValue(this.field_1_xf_index, xfIndex);
    }

    public int getXFIndex() {
        return styleIndexMask.getValue(this.field_1_xf_index);
    }

    public void setName(String name) {
        this.field_4_name = name;
        this.field_3_stringHasMultibyte = StringUtil.hasMultibyte(name);
        this.field_1_xf_index = isBuiltinFlag.clear(this.field_1_xf_index);
    }

    public void setBuiltinStyle(int builtinStyleId) {
        this.field_1_xf_index = isBuiltinFlag.set(this.field_1_xf_index);
        this.field_2_builtin_style = builtinStyleId;
    }

    public void setOutlineStyleLevel(int level) {
        this.field_3_outline_style_level = level & 255;
    }

    public boolean isBuiltin() {
        return isBuiltinFlag.isSet(this.field_1_xf_index);
    }

    public String getName() {
        return this.field_4_name;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        if (isBuiltin()) {
            return 4;
        }
        return (this.field_4_name.length() * (this.field_3_stringHasMultibyte ? 2 : 1)) + 5;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_xf_index);
        if (isBuiltin()) {
            littleEndianOutput.writeByte(this.field_2_builtin_style);
            littleEndianOutput.writeByte(this.field_3_outline_style_level);
            return;
        }
        littleEndianOutput.writeShort(this.field_4_name.length());
        littleEndianOutput.writeByte(this.field_3_stringHasMultibyte ? 1 : 0);
        if (this.field_3_stringHasMultibyte) {
            StringUtil.putUnicodeLE(getName(), littleEndianOutput);
        } else {
            StringUtil.putCompressedUnicode(getName(), littleEndianOutput);
        }
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public StyleRecord copy() {
        return new StyleRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.STYLE;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("xfIndex", new Supplier() { // from class: org.apache.poi.hssf.record.StyleRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(StyleRecord.this.getXFIndex());
            }
        }, ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, new Supplier() { // from class: org.apache.poi.hssf.record.StyleRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return StyleRecord.this.m2373x44d5f823();
            }
        }, "builtin_style", new Supplier() { // from class: org.apache.poi.hssf.record.StyleRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return StyleRecord.this.m2374x9bf3e902();
            }
        }, "outline_level", new Supplier() { // from class: org.apache.poi.hssf.record.StyleRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return StyleRecord.this.m2375xf311d9e1();
            }
        }, "name", new Supplier() { // from class: org.apache.poi.hssf.record.StyleRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return StyleRecord.this.getName();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-StyleRecord, reason: not valid java name */
    public /* synthetic */ Object m2373x44d5f823() {
        return isBuiltin() ? "built-in" : "user-defined";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-StyleRecord, reason: not valid java name */
    public /* synthetic */ Object m2374x9bf3e902() {
        return Integer.valueOf(this.field_2_builtin_style);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-StyleRecord, reason: not valid java name */
    public /* synthetic */ Object m2375xf311d9e1() {
        return Integer.valueOf(this.field_3_outline_style_level);
    }
}
