package org.apache.poi.hssf.record.cf;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.common.usermodel.fonts.FontHeader;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;

/* loaded from: classes10.dex */
public final class FontFormatting implements Duplicatable, GenericRecord {
    public static final int FONT_CELL_HEIGHT_PRESERVED = -1;
    private static final short FONT_WEIGHT_BOLD = 700;
    private static final short FONT_WEIGHT_NORMAL = 400;
    private static final int OFFSET_ESCAPEMENT_TYPE = 74;
    private static final int OFFSET_ESCAPEMENT_TYPE_MODIFIED = 92;
    private static final int OFFSET_FONT_COLOR_INDEX = 80;
    private static final int OFFSET_FONT_FORMATING_END = 116;
    private static final int OFFSET_FONT_HEIGHT = 64;
    private static final int OFFSET_FONT_NAME = 0;
    private static final int OFFSET_FONT_OPTIONS = 68;
    private static final int OFFSET_FONT_WEIGHT = 72;
    private static final int OFFSET_FONT_WEIGHT_MODIFIED = 100;
    private static final int OFFSET_NOT_USED1 = 104;
    private static final int OFFSET_NOT_USED2 = 108;
    private static final int OFFSET_NOT_USED3 = 112;
    private static final int OFFSET_OPTION_FLAGS = 88;
    private static final int OFFSET_UNDERLINE_TYPE = 76;
    private static final int OFFSET_UNDERLINE_TYPE_MODIFIED = 96;
    private static final int RAW_DATA_SIZE = 118;
    private final byte[] _rawData;
    private static final BitField POSTURE = BitFieldFactory.getInstance(2);
    private static final BitField OUTLINE = BitFieldFactory.getInstance(8);
    private static final BitField SHADOW = BitFieldFactory.getInstance(16);
    private static final BitField CANCELLATION = BitFieldFactory.getInstance(128);

    public FontFormatting() {
        this._rawData = new byte[118];
        setFontHeight(-1);
        setItalic(false);
        setFontWieghtModified(false);
        setOutline(false);
        setShadow(false);
        setStrikeout(false);
        setEscapementType((short) 0);
        setUnderlineType((short) 0);
        setFontColorIndex((short) -1);
        setFontStyleModified(false);
        setFontOutlineModified(false);
        setFontShadowModified(false);
        setFontCancellationModified(false);
        setEscapementTypeModified(false);
        setUnderlineTypeModified(false);
        setShort(0, 0);
        setInt(104, 1);
        setInt(108, 0);
        setInt(112, Integer.MAX_VALUE);
        setShort(116, 1);
    }

    public FontFormatting(FontFormatting other) {
        this._rawData = new byte[118];
        System.arraycopy(other._rawData, 0, this._rawData, 0, 118);
    }

    public FontFormatting(RecordInputStream in) {
        this._rawData = new byte[118];
        in.readFully(this._rawData);
    }

    private short getShort(int offset) {
        return LittleEndian.getShort(this._rawData, offset);
    }

    private void setShort(int offset, int value) {
        LittleEndian.putShort(this._rawData, offset, (short) value);
    }

    private int getInt(int offset) {
        return LittleEndian.getInt(this._rawData, offset);
    }

    private void setInt(int offset, int value) {
        LittleEndian.putInt(this._rawData, offset, value);
    }

    public byte[] getRawRecord() {
        return this._rawData;
    }

    public int getDataLength() {
        return 118;
    }

    public void setFontHeight(int height) {
        setInt(64, height);
    }

    public int getFontHeight() {
        return getInt(64);
    }

    private void setFontOption(boolean option, BitField field) {
        int options = getInt(68);
        setInt(68, field.setBoolean(options, option));
    }

    private boolean getFontOption(BitField field) {
        int options = getInt(68);
        return field.isSet(options);
    }

    public void setItalic(boolean italic) {
        setFontOption(italic, POSTURE);
    }

    public boolean isItalic() {
        return getFontOption(POSTURE);
    }

    public void setOutline(boolean on) {
        setFontOption(on, OUTLINE);
    }

    public boolean isOutlineOn() {
        return getFontOption(OUTLINE);
    }

    public void setShadow(boolean on) {
        setFontOption(on, SHADOW);
    }

    public boolean isShadowOn() {
        return getFontOption(SHADOW);
    }

    public void setStrikeout(boolean strike) {
        setFontOption(strike, CANCELLATION);
    }

    public boolean isStruckout() {
        return getFontOption(CANCELLATION);
    }

    private void setFontWeight(short bw) {
        setShort(72, Math.max(100, Math.min(1000, (int) bw)));
    }

    public void setBold(boolean bold) {
        setFontWeight(bold ? FONT_WEIGHT_BOLD : FONT_WEIGHT_NORMAL);
    }

    public short getFontWeight() {
        return getShort(72);
    }

    public boolean isBold() {
        return getFontWeight() == 700;
    }

    public short getEscapementType() {
        return getShort(74);
    }

    public void setEscapementType(short escapementType) {
        setShort(74, escapementType);
    }

    public short getUnderlineType() {
        return getShort(76);
    }

    public void setUnderlineType(short underlineType) {
        setShort(76, underlineType);
    }

    public short getFontColorIndex() {
        return (short) getInt(80);
    }

    public void setFontColorIndex(short fci) {
        setInt(80, fci);
    }

    private boolean getOptionFlag(BitField field) {
        int optionFlags = getInt(88);
        int value = field.getValue(optionFlags);
        return value == 0;
    }

    private void setOptionFlag(boolean z, BitField bitField) {
        setInt(88, bitField.setValue(getInt(88), !z ? 1 : 0));
    }

    public boolean isFontStyleModified() {
        return getOptionFlag(POSTURE);
    }

    public void setFontStyleModified(boolean modified) {
        setOptionFlag(modified, POSTURE);
    }

    public boolean isFontOutlineModified() {
        return getOptionFlag(OUTLINE);
    }

    public void setFontOutlineModified(boolean modified) {
        setOptionFlag(modified, OUTLINE);
    }

    public boolean isFontShadowModified() {
        return getOptionFlag(SHADOW);
    }

    public void setFontShadowModified(boolean modified) {
        setOptionFlag(modified, SHADOW);
    }

    public void setFontCancellationModified(boolean modified) {
        setOptionFlag(modified, CANCELLATION);
    }

    public boolean isFontCancellationModified() {
        return getOptionFlag(CANCELLATION);
    }

    public void setEscapementTypeModified(boolean z) {
        setInt(92, !z ? 1 : 0);
    }

    public boolean isEscapementTypeModified() {
        int escapementModified = getInt(92);
        return escapementModified == 0;
    }

    public void setUnderlineTypeModified(boolean z) {
        setInt(96, !z ? 1 : 0);
    }

    public boolean isUnderlineTypeModified() {
        int underlineModified = getInt(96);
        return underlineModified == 0;
    }

    public void setFontWieghtModified(boolean z) {
        setInt(100, !z ? 1 : 0);
    }

    public boolean isFontWeightModified() {
        int fontStyleModified = getInt(100);
        return fontStyleModified == 0;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        Map<String, Supplier<?>> m = new LinkedHashMap<>();
        m.put("fontHeight", new Supplier() { // from class: org.apache.poi.hssf.record.cf.FontFormatting$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(FontFormatting.this.getFontHeight());
            }
        });
        m.put("options", GenericRecordUtil.getBitsAsString((Supplier<Number>) new Supplier() { // from class: org.apache.poi.hssf.record.cf.FontFormatting$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontFormatting.this.m2403x146279ff();
            }
        }, new BitField[]{POSTURE, OUTLINE, SHADOW, CANCELLATION}, new String[]{"POSTURE_MODIFIED", "OUTLINE_MODIFIED", "SHADOW_MODIFIED", "STRUCKOUT_MODIFIED"}));
        m.put("fontOptions", GenericRecordUtil.getBitsAsString((Supplier<Number>) new Supplier() { // from class: org.apache.poi.hssf.record.cf.FontFormatting$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return FontFormatting.this.m2404xcdda079e();
            }
        }, new BitField[]{POSTURE, OUTLINE, SHADOW, CANCELLATION}, new String[]{"ITALIC", "OUTLINE", "SHADOW", "STRUCKOUT"}));
        m.put("fontWEightModified", new Supplier() { // from class: org.apache.poi.hssf.record.cf.FontFormatting$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(FontFormatting.this.isFontWeightModified());
            }
        });
        m.put("fontWeight", GenericRecordUtil.getEnumBitsAsString(new Supplier() { // from class: org.apache.poi.hssf.record.cf.FontFormatting$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(FontFormatting.this.getFontWeight());
            }
        }, new int[]{FontHeader.REGULAR_WEIGHT, TypedValues.TransitionType.TYPE_DURATION}, new String[]{"NORMAL", "BOLD"}));
        m.put("escapementTypeModified", new Supplier() { // from class: org.apache.poi.hssf.record.cf.FontFormatting$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(FontFormatting.this.isEscapementTypeModified());
            }
        });
        m.put("escapementType", new Supplier() { // from class: org.apache.poi.hssf.record.cf.FontFormatting$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(FontFormatting.this.getEscapementType());
            }
        });
        m.put("underlineTypeModified", new Supplier() { // from class: org.apache.poi.hssf.record.cf.FontFormatting$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(FontFormatting.this.isUnderlineTypeModified());
            }
        });
        m.put("underlineType", new Supplier() { // from class: org.apache.poi.hssf.record.cf.FontFormatting$$ExternalSyntheticLambda8
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(FontFormatting.this.getUnderlineType());
            }
        });
        m.put("colorIndex", new Supplier() { // from class: org.apache.poi.hssf.record.cf.FontFormatting$$ExternalSyntheticLambda9
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(FontFormatting.this.getFontColorIndex());
            }
        });
        return Collections.unmodifiableMap(m);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-cf-FontFormatting, reason: not valid java name */
    public /* synthetic */ Number m2403x146279ff() {
        return Integer.valueOf(getInt(88));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-cf-FontFormatting, reason: not valid java name */
    public /* synthetic */ Number m2404xcdda079e() {
        return Integer.valueOf(getInt(68));
    }

    public String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    @Override // org.apache.poi.common.Duplicatable
    public FontFormatting copy() {
        return new FontFormatting(this);
    }
}
