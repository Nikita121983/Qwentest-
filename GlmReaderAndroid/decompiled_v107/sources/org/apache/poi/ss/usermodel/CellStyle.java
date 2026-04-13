package org.apache.poi.ss.usermodel;

import java.util.EnumMap;
import org.apache.poi.util.Removal;

/* loaded from: classes10.dex */
public interface CellStyle {
    void cloneStyleFrom(CellStyle cellStyle);

    HorizontalAlignment getAlignment();

    BorderStyle getBorderBottom();

    BorderStyle getBorderLeft();

    BorderStyle getBorderRight();

    BorderStyle getBorderTop();

    short getBottomBorderColor();

    short getDataFormat();

    String getDataFormatString();

    short getFillBackgroundColor();

    Color getFillBackgroundColorColor();

    short getFillForegroundColor();

    Color getFillForegroundColorColor();

    FillPatternType getFillPattern();

    int getFontIndex();

    @Removal(version = "6.0.0")
    @Deprecated
    int getFontIndexAsInt();

    EnumMap<CellPropertyType, Object> getFormatProperties();

    boolean getHidden();

    short getIndention();

    short getIndex();

    short getLeftBorderColor();

    boolean getLocked();

    boolean getQuotePrefixed();

    short getRightBorderColor();

    short getRotation();

    boolean getShrinkToFit();

    short getTopBorderColor();

    VerticalAlignment getVerticalAlignment();

    boolean getWrapText();

    void invalidateCachedProperties();

    void setAlignment(HorizontalAlignment horizontalAlignment);

    void setBorderBottom(BorderStyle borderStyle);

    void setBorderLeft(BorderStyle borderStyle);

    void setBorderRight(BorderStyle borderStyle);

    void setBorderTop(BorderStyle borderStyle);

    void setBottomBorderColor(short s);

    void setDataFormat(short s);

    void setFillBackgroundColor(Color color);

    void setFillBackgroundColor(short s);

    void setFillForegroundColor(Color color);

    void setFillForegroundColor(short s);

    void setFillPattern(FillPatternType fillPatternType);

    void setFont(Font font);

    void setHidden(boolean z);

    void setIndention(short s);

    void setLeftBorderColor(short s);

    void setLocked(boolean z);

    void setQuotePrefixed(boolean z);

    void setRightBorderColor(short s);

    void setRotation(short s);

    void setShrinkToFit(boolean z);

    void setTopBorderColor(short s);

    void setVerticalAlignment(VerticalAlignment verticalAlignment);

    void setWrapText(boolean z);
}
