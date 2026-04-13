package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class FontRecord extends StandardRecord {
    public static final short SS_NONE = 0;
    public static final short SS_SUB = 2;
    public static final short SS_SUPER = 1;
    public static final byte U_DOUBLE = 2;
    public static final byte U_DOUBLE_ACCOUNTING = 34;
    public static final byte U_NONE = 0;
    public static final byte U_SINGLE = 1;
    public static final byte U_SINGLE_ACCOUNTING = 33;
    public static final short sid = 49;
    private String field_11_font_name;
    private short field_1_font_height;
    private short field_2_attributes;
    private short field_3_color_palette_index;
    private short field_4_bold_weight;
    private short field_5_super_sub_script;
    private byte field_6_underline;
    private byte field_7_family;
    private byte field_8_charset;
    private byte field_9_zero;
    private static final BitField italic = BitFieldFactory.getInstance(2);
    private static final BitField strikeout = BitFieldFactory.getInstance(8);
    private static final BitField macoutline = BitFieldFactory.getInstance(16);
    private static final BitField macshadow = BitFieldFactory.getInstance(32);

    public FontRecord() {
    }

    public FontRecord(FontRecord other) {
        super(other);
        this.field_1_font_height = other.field_1_font_height;
        this.field_2_attributes = other.field_2_attributes;
        this.field_3_color_palette_index = other.field_3_color_palette_index;
        this.field_4_bold_weight = other.field_4_bold_weight;
        this.field_5_super_sub_script = other.field_5_super_sub_script;
        this.field_6_underline = other.field_6_underline;
        this.field_7_family = other.field_7_family;
        this.field_8_charset = other.field_8_charset;
        this.field_9_zero = other.field_9_zero;
        this.field_11_font_name = other.field_11_font_name;
    }

    public FontRecord(RecordInputStream in) {
        this.field_1_font_height = in.readShort();
        this.field_2_attributes = in.readShort();
        this.field_3_color_palette_index = in.readShort();
        this.field_4_bold_weight = in.readShort();
        this.field_5_super_sub_script = in.readShort();
        this.field_6_underline = in.readByte();
        this.field_7_family = in.readByte();
        this.field_8_charset = in.readByte();
        this.field_9_zero = in.readByte();
        int field_10_font_name_len = in.readUByte();
        int unicodeFlags = in.readUByte();
        if (field_10_font_name_len > 0) {
            if (unicodeFlags == 0) {
                this.field_11_font_name = in.readCompressedUnicode(field_10_font_name_len);
                return;
            } else {
                this.field_11_font_name = in.readUnicodeLEString(field_10_font_name_len);
                return;
            }
        }
        this.field_11_font_name = "";
    }

    public void setFontHeight(short height) {
        this.field_1_font_height = height;
    }

    public void setAttributes(short attributes) {
        this.field_2_attributes = attributes;
    }

    public void setItalic(boolean italics) {
        this.field_2_attributes = italic.setShortBoolean(this.field_2_attributes, italics);
    }

    public void setStrikeout(boolean strike) {
        this.field_2_attributes = strikeout.setShortBoolean(this.field_2_attributes, strike);
    }

    public void setMacoutline(boolean mac) {
        this.field_2_attributes = macoutline.setShortBoolean(this.field_2_attributes, mac);
    }

    public void setMacshadow(boolean mac) {
        this.field_2_attributes = macshadow.setShortBoolean(this.field_2_attributes, mac);
    }

    public void setColorPaletteIndex(short cpi) {
        this.field_3_color_palette_index = cpi;
    }

    public void setBoldWeight(short bw) {
        this.field_4_bold_weight = bw;
    }

    public void setSuperSubScript(short sss) {
        this.field_5_super_sub_script = sss;
    }

    public void setUnderline(byte u) {
        this.field_6_underline = u;
    }

    public void setFamily(byte f) {
        this.field_7_family = f;
    }

    public void setCharset(byte charset) {
        this.field_8_charset = charset;
    }

    public void setFontName(String fn) {
        this.field_11_font_name = fn;
    }

    public short getFontHeight() {
        return this.field_1_font_height;
    }

    public short getAttributes() {
        return this.field_2_attributes;
    }

    public boolean isItalic() {
        return italic.isSet(this.field_2_attributes);
    }

    public boolean isStruckout() {
        return strikeout.isSet(this.field_2_attributes);
    }

    public boolean isMacoutlined() {
        return macoutline.isSet(this.field_2_attributes);
    }

    public boolean isMacshadowed() {
        return macshadow.isSet(this.field_2_attributes);
    }

    public short getColorPaletteIndex() {
        return this.field_3_color_palette_index;
    }

    public short getBoldWeight() {
        return this.field_4_bold_weight;
    }

    public short getSuperSubScript() {
        return this.field_5_super_sub_script;
    }

    public byte getUnderline() {
        return this.field_6_underline;
    }

    public byte getFamily() {
        return this.field_7_family;
    }

    public byte getCharset() {
        return this.field_8_charset;
    }

    public String getFontName() {
        return this.field_11_font_name;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getFontHeight());
        littleEndianOutput.writeShort(getAttributes());
        littleEndianOutput.writeShort(getColorPaletteIndex());
        littleEndianOutput.writeShort(getBoldWeight());
        littleEndianOutput.writeShort(getSuperSubScript());
        littleEndianOutput.writeByte(getUnderline());
        littleEndianOutput.writeByte(getFamily());
        littleEndianOutput.writeByte(getCharset());
        littleEndianOutput.writeByte(this.field_9_zero);
        int length = this.field_11_font_name.length();
        littleEndianOutput.writeByte(length);
        boolean hasMultibyte = StringUtil.hasMultibyte(this.field_11_font_name);
        littleEndianOutput.writeByte(hasMultibyte ? 1 : 0);
        if (length > 0) {
            if (hasMultibyte) {
                StringUtil.putUnicodeLE(this.field_11_font_name, littleEndianOutput);
            } else {
                StringUtil.putCompressedUnicode(this.field_11_font_name, littleEndianOutput);
            }
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        int fontNameLen = this.field_11_font_name.length();
        if (fontNameLen < 1) {
            return 16;
        }
        boolean hasMultibyte = StringUtil.hasMultibyte(this.field_11_font_name);
        return ((hasMultibyte ? 2 : 1) * fontNameLen) + 16;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 49;
    }

    public void cloneStyleFrom(FontRecord source) {
        this.field_1_font_height = source.field_1_font_height;
        this.field_2_attributes = source.field_2_attributes;
        this.field_3_color_palette_index = source.field_3_color_palette_index;
        this.field_4_bold_weight = source.field_4_bold_weight;
        this.field_5_super_sub_script = source.field_5_super_sub_script;
        this.field_6_underline = source.field_6_underline;
        this.field_7_family = source.field_7_family;
        this.field_8_charset = source.field_8_charset;
        this.field_9_zero = source.field_9_zero;
        this.field_11_font_name = source.field_11_font_name;
    }

    public int hashCode() {
        return Objects.hash(Short.valueOf(this.field_1_font_height), Short.valueOf(this.field_2_attributes), Short.valueOf(this.field_3_color_palette_index), Short.valueOf(this.field_4_bold_weight), Short.valueOf(this.field_5_super_sub_script), Byte.valueOf(this.field_6_underline), Byte.valueOf(this.field_7_family), Byte.valueOf(this.field_8_charset), Byte.valueOf(this.field_9_zero), this.field_11_font_name);
    }

    public boolean sameProperties(FontRecord other) {
        return this.field_1_font_height == other.field_1_font_height && this.field_2_attributes == other.field_2_attributes && this.field_3_color_palette_index == other.field_3_color_palette_index && this.field_4_bold_weight == other.field_4_bold_weight && this.field_5_super_sub_script == other.field_5_super_sub_script && this.field_6_underline == other.field_6_underline && this.field_7_family == other.field_7_family && this.field_8_charset == other.field_8_charset && this.field_9_zero == other.field_9_zero && Objects.equals(this.field_11_font_name, other.field_11_font_name);
    }

    public boolean equals(Object o) {
        return (o instanceof FontRecord) && sameProperties((FontRecord) o);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public FontRecord copy() {
        return new FontRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FONT;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("fontHeight", new Supplier() { // from class: org.apache.poi.hssf.record.FontRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(FontRecord.this.getFontHeight());
            }
        }, "attributes", GenericRecordUtil.getBitsAsString((Supplier<Number>) new Supplier() { // from class: org.apache.poi.hssf.record.FontRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(FontRecord.this.getAttributes());
            }
        }, new BitField[]{italic, strikeout, macoutline, macshadow}, new String[]{"ITALIC", "STRIKEOUT", "MACOUTLINE", "MACSHADOW"}), "colorPalette", new Supplier() { // from class: org.apache.poi.hssf.record.FontRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(FontRecord.this.getColorPaletteIndex());
            }
        }, "boldWeight", new Supplier() { // from class: org.apache.poi.hssf.record.FontRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(FontRecord.this.getBoldWeight());
            }
        }, "superSubScript", new Supplier() { // from class: org.apache.poi.hssf.record.FontRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(FontRecord.this.getSuperSubScript());
            }
        }, "underline", new Supplier() { // from class: org.apache.poi.hssf.record.FontRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return Byte.valueOf(FontRecord.this.getUnderline());
            }
        }, "family", new Supplier() { // from class: org.apache.poi.hssf.record.FontRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return Byte.valueOf(FontRecord.this.getFamily());
            }
        }, "charset", new Supplier() { // from class: org.apache.poi.hssf.record.FontRecord$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return Byte.valueOf(FontRecord.this.getCharset());
            }
        }, "fontName", new Supplier() { // from class: org.apache.poi.hssf.record.FontRecord$$ExternalSyntheticLambda8
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontRecord.this.getFontName();
            }
        });
    }
}
