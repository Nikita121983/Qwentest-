package org.apache.poi.hssf.usermodel;

import java.util.EnumMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import kotlin.jvm.internal.ShortCompanionObject;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.record.FontRecord;
import org.apache.poi.hssf.record.FormatRecord;
import org.apache.poi.hssf.record.StyleRecord;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellPropertyType;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.util.Removal;
import org.apache.poi.util.ThreadLocalUtil;

/* loaded from: classes10.dex */
public final class HSSFCellStyle implements CellStyle, Duplicatable {
    private EnumMap<CellPropertyType, Object> _cachedProperties;
    private final ExtendedFormatRecord _format;
    private final HSSFWorkbook _hssfWorkbook;
    private final short _index;
    private final InternalWorkbook _workbook;
    private static final ThreadLocal<Short> lastDateFormat = ThreadLocal.withInitial(new Supplier() { // from class: org.apache.poi.hssf.usermodel.HSSFCellStyle$$ExternalSyntheticLambda0
        @Override // java.util.function.Supplier
        public final Object get() {
            Short valueOf;
            valueOf = Short.valueOf(ShortCompanionObject.MIN_VALUE);
            return valueOf;
        }
    });
    private static final ThreadLocal<List<FormatRecord>> lastFormats = new ThreadLocal<>();
    private static final ThreadLocal<String> getDataFormatStringCache = new ThreadLocal<>();

    /* JADX INFO: Access modifiers changed from: protected */
    public HSSFCellStyle(short index, ExtendedFormatRecord rec, HSSFWorkbook workbook) {
        this._workbook = workbook.getInternalWorkbook();
        this._hssfWorkbook = workbook;
        this._index = index;
        this._format = rec;
    }

    @Removal(version = "7.0.0")
    @Deprecated
    protected HSSFCellStyle(short index, ExtendedFormatRecord rec, InternalWorkbook workbook) {
        this._workbook = workbook;
        this._hssfWorkbook = null;
        this._index = index;
        this._format = rec;
    }

    protected HSSFCellStyle(HSSFCellStyle other) {
        this._workbook = other._workbook;
        this._hssfWorkbook = other._hssfWorkbook;
        this._index = other._index;
        this._format = other._format;
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public short getIndex() {
        return this._index;
    }

    public HSSFCellStyle getParentStyle() {
        short parentIndex = this._format.getParentIndex();
        if (parentIndex == 0 || parentIndex == 4095) {
            return null;
        }
        return new HSSFCellStyle(parentIndex, this._workbook.getExFormatAt(parentIndex), this._workbook);
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setDataFormat(short fmt) {
        this._format.setFormatIndex(fmt);
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public short getDataFormat() {
        return this._format.getFormatIndex();
    }

    static {
        ThreadLocalUtil.registerCleaner(new Runnable() { // from class: org.apache.poi.hssf.usermodel.HSSFCellStyle$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                HSSFCellStyle.lambda$static$1();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$static$1() {
        lastDateFormat.remove();
        lastFormats.remove();
        getDataFormatStringCache.remove();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public String getDataFormatString() {
        if (getDataFormatStringCache.get() != null && lastDateFormat.get().shortValue() == getDataFormat() && this._workbook.getFormats().equals(lastFormats.get())) {
            return getDataFormatStringCache.get();
        }
        lastFormats.set(this._workbook.getFormats());
        lastDateFormat.set(Short.valueOf(getDataFormat()));
        getDataFormatStringCache.set(getDataFormatString(this._workbook));
        return getDataFormatStringCache.get();
    }

    public String getDataFormatString(Workbook workbook) {
        HSSFDataFormat format = new HSSFDataFormat(((HSSFWorkbook) workbook).getWorkbook());
        int idx = getDataFormat();
        return idx == -1 ? "General" : format.getFormat(getDataFormat());
    }

    public String getDataFormatString(InternalWorkbook workbook) {
        HSSFDataFormat format = new HSSFDataFormat(workbook);
        return format.getFormat(getDataFormat());
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setFont(Font font) {
        setFont((HSSFFont) font);
    }

    public void setFont(HSSFFont font) {
        this._format.setIndentNotParentFont(true);
        short fontindex = (short) font.getIndex();
        this._format.setFontIndex(fontindex);
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public int getFontIndex() {
        return this._format.getFontIndex();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    @Removal(version = "6.0.0")
    @Deprecated
    public int getFontIndexAsInt() {
        return this._format.getFontIndex();
    }

    public HSSFFont getFont(Workbook parentWorkbook) {
        return ((HSSFWorkbook) parentWorkbook).getFontAt(getFontIndex());
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setHidden(boolean hidden) {
        this._format.setIndentNotParentCellOptions(true);
        this._format.setHidden(hidden);
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public boolean getHidden() {
        return this._format.isHidden();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setLocked(boolean locked) {
        this._format.setIndentNotParentCellOptions(true);
        this._format.setLocked(locked);
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public boolean getLocked() {
        return this._format.isLocked();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setQuotePrefixed(boolean quotePrefix) {
        this._format.set123Prefix(quotePrefix);
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public boolean getQuotePrefixed() {
        return this._format.get123Prefix();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setAlignment(HorizontalAlignment align) {
        this._format.setIndentNotParentAlignment(true);
        this._format.setAlignment(align.getCode());
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public HorizontalAlignment getAlignment() {
        return HorizontalAlignment.forInt(this._format.getAlignment());
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setWrapText(boolean wrapped) {
        this._format.setIndentNotParentAlignment(true);
        this._format.setWrapText(wrapped);
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public boolean getWrapText() {
        return this._format.getWrapText();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setVerticalAlignment(VerticalAlignment align) {
        this._format.setVerticalAlignment(align.getCode());
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public VerticalAlignment getVerticalAlignment() {
        return VerticalAlignment.forInt(this._format.getVerticalAlignment());
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setRotation(short rotation) {
        if (rotation != 255) {
            if (rotation < 0 && rotation >= -90) {
                rotation = (short) (90 - rotation);
            } else if ((rotation <= 90 || rotation > 180) && (rotation < -90 || rotation > 90)) {
                throw new IllegalArgumentException("The rotation must be between -90 and 90 degrees, or 0xff");
            }
        }
        this._format.setRotation(rotation);
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public short getRotation() {
        short rotation = this._format.getRotation();
        if (rotation == 255) {
            return rotation;
        }
        if (rotation > 90) {
            return (short) (90 - rotation);
        }
        return rotation;
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setIndention(short indent) {
        this._format.setIndent(indent);
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public short getIndention() {
        return this._format.getIndent();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setBorderLeft(BorderStyle border) {
        this._format.setIndentNotParentBorder(true);
        this._format.setBorderLeft(border.getCode());
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public BorderStyle getBorderLeft() {
        return BorderStyle.valueOf(this._format.getBorderLeft());
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setBorderRight(BorderStyle border) {
        this._format.setIndentNotParentBorder(true);
        this._format.setBorderRight(border.getCode());
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public BorderStyle getBorderRight() {
        return BorderStyle.valueOf(this._format.getBorderRight());
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setBorderTop(BorderStyle border) {
        this._format.setIndentNotParentBorder(true);
        this._format.setBorderTop(border.getCode());
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public BorderStyle getBorderTop() {
        return BorderStyle.valueOf(this._format.getBorderTop());
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setBorderBottom(BorderStyle border) {
        this._format.setIndentNotParentBorder(true);
        this._format.setBorderBottom(border.getCode());
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public BorderStyle getBorderBottom() {
        return BorderStyle.valueOf(this._format.getBorderBottom());
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setLeftBorderColor(short color) {
        this._format.setLeftBorderPaletteIdx(color);
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public short getLeftBorderColor() {
        return this._format.getLeftBorderPaletteIdx();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setRightBorderColor(short color) {
        this._format.setRightBorderPaletteIdx(color);
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public short getRightBorderColor() {
        return this._format.getRightBorderPaletteIdx();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setTopBorderColor(short color) {
        this._format.setTopBorderPaletteIdx(color);
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public short getTopBorderColor() {
        return this._format.getTopBorderPaletteIdx();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setBottomBorderColor(short color) {
        this._format.setBottomBorderPaletteIdx(color);
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public short getBottomBorderColor() {
        return this._format.getBottomBorderPaletteIdx();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setFillPattern(FillPatternType fp) {
        this._format.setAdtlFillPattern(fp.getCode());
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public FillPatternType getFillPattern() {
        return FillPatternType.forInt(this._format.getAdtlFillPattern());
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public EnumMap<CellPropertyType, Object> getFormatProperties() {
        EnumMap<CellPropertyType, Object> props = this._cachedProperties;
        if (props == null) {
            EnumMap<CellPropertyType, Object> props2 = CellUtil.getFormatProperties(this);
            this._cachedProperties = props2;
            return props2;
        }
        return props;
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void invalidateCachedProperties() {
        this._cachedProperties = null;
    }

    private void checkDefaultBackgroundFills() {
        short autoIdx = HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex();
        if (this._format.getFillForeground() == autoIdx) {
            if (this._format.getFillBackground() != autoIdx + 1) {
                setFillBackgroundColor((short) (autoIdx + 1));
            }
        } else if (this._format.getFillBackground() == autoIdx + 1 && this._format.getFillForeground() != autoIdx) {
            setFillBackgroundColor(autoIdx);
        }
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setFillBackgroundColor(short bg) {
        this._format.setFillBackground(bg);
        checkDefaultBackgroundFills();
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setFillBackgroundColor(Color color) {
        if (color instanceof HSSFColor) {
            short index = ((HSSFColor) color).getIndex();
            if (index != -1) {
                setFillBackgroundColor(index);
                return;
            }
            return;
        }
        if (color != null) {
            throw new IllegalArgumentException("HSSFCellStyle only accepts HSSFColor instances");
        }
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public short getFillBackgroundColor() {
        short autoIndex = HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex();
        short result = this._format.getFillBackground();
        if (result == autoIndex + 1) {
            return autoIndex;
        }
        return result;
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public HSSFColor getFillBackgroundColorColor() {
        HSSFPalette pallette = new HSSFPalette(this._workbook.getCustomPalette());
        return pallette.getColor(getFillBackgroundColor());
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setFillForegroundColor(short bg) {
        this._format.setFillForeground(bg);
        checkDefaultBackgroundFills();
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setFillForegroundColor(Color color) {
        if (color instanceof HSSFColor) {
            short index = ((HSSFColor) color).getIndex();
            if (index != -1) {
                setFillForegroundColor(index);
                return;
            }
            return;
        }
        if (color != null) {
            throw new IllegalArgumentException("HSSFCellStyle only accepts HSSFColor instances");
        }
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public short getFillForegroundColor() {
        return this._format.getFillForeground();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public HSSFColor getFillForegroundColorColor() {
        HSSFPalette pallette = new HSSFPalette(this._workbook.getCustomPalette());
        return pallette.getColor(getFillForegroundColor());
    }

    public String getUserStyleName() {
        StyleRecord sr = this._workbook.getStyleRecord(this._index);
        if (sr == null || sr.isBuiltin()) {
            return null;
        }
        return sr.getName();
    }

    public void setUserStyleName(String styleName) {
        StyleRecord sr = this._workbook.getStyleRecord(this._index);
        if (sr == null) {
            sr = this._workbook.createStyleRecord(this._index);
        }
        if (sr.isBuiltin() && this._index <= 20) {
            throw new IllegalArgumentException("Unable to set user specified style names for built in styles!");
        }
        sr.setName(styleName);
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void setShrinkToFit(boolean shrinkToFit) {
        this._format.setShrinkToFit(shrinkToFit);
        invalidateCachedProperties();
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public boolean getShrinkToFit() {
        return this._format.getShrinkToFit();
    }

    public short getReadingOrder() {
        return this._format.getReadingOrder();
    }

    public void setReadingOrder(short order) {
        this._format.setReadingOrder(order);
        invalidateCachedProperties();
    }

    public void verifyBelongsToWorkbook(HSSFWorkbook wb) {
        if (wb.getWorkbook() != this._workbook) {
            throw new IllegalArgumentException("This Style does not belong to the supplied Workbook. Are you trying to assign a style from one workbook to the cell of a differnt workbook?");
        }
    }

    @Override // org.apache.poi.ss.usermodel.CellStyle
    public void cloneStyleFrom(CellStyle source) {
        if (source instanceof HSSFCellStyle) {
            cloneStyleFrom((HSSFCellStyle) source);
        } else {
            CellUtil.cloneStyle(source, this, this._hssfWorkbook);
        }
    }

    public void cloneStyleFrom(HSSFCellStyle source) {
        this._format.cloneStyleFrom(source._format);
        if (this._workbook != source._workbook) {
            lastDateFormat.set(Short.valueOf(ShortCompanionObject.MIN_VALUE));
            lastFormats.remove();
            getDataFormatStringCache.remove();
            short fmt = (short) this._workbook.createFormat(source.getDataFormatString());
            setDataFormat(fmt);
            FontRecord fr = this._workbook.createNewFont();
            fr.cloneStyleFrom(source._workbook.getFontRecordAt(source.getFontIndex()));
            HSSFFont font = new HSSFFont((short) this._workbook.getFontIndex(fr), fr);
            setFont(font);
        }
    }

    public int hashCode() {
        return Objects.hash(this._format, Short.valueOf(this._index));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof HSSFCellStyle)) {
            return false;
        }
        HSSFCellStyle other = (HSSFCellStyle) obj;
        if (this._format == null) {
            if (other._format != null) {
                return false;
            }
        } else if (!this._format.equals(other._format)) {
            return false;
        }
        if (this._index == other._index) {
            return true;
        }
        return false;
    }

    @Override // org.apache.poi.common.Duplicatable
    public HSSFCellStyle copy() {
        return new HSSFCellStyle(this);
    }
}
