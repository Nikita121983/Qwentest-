package org.apache.poi.ss.usermodel;

/* loaded from: classes10.dex */
public enum CellPropertyType {
    BORDER_BOTTOM(CellPropertyCategory.BORDER_TYPE),
    BORDER_LEFT(CellPropertyCategory.BORDER_TYPE),
    BORDER_RIGHT(CellPropertyCategory.BORDER_TYPE),
    BORDER_TOP(CellPropertyCategory.BORDER_TYPE),
    BOTTOM_BORDER_COLOR(CellPropertyCategory.SHORT),
    LEFT_BORDER_COLOR(CellPropertyCategory.SHORT),
    RIGHT_BORDER_COLOR(CellPropertyCategory.SHORT),
    TOP_BORDER_COLOR(CellPropertyCategory.SHORT),
    DATA_FORMAT(CellPropertyCategory.SHORT),
    FILL_BACKGROUND_COLOR(CellPropertyCategory.SHORT),
    FILL_FOREGROUND_COLOR(CellPropertyCategory.SHORT),
    INDENTION(CellPropertyCategory.SHORT),
    ROTATION(CellPropertyCategory.SHORT),
    FILL_BACKGROUND_COLOR_COLOR(CellPropertyCategory.COLOR),
    FILL_FOREGROUND_COLOR_COLOR(CellPropertyCategory.COLOR),
    FONT(CellPropertyCategory.INT),
    HIDDEN(CellPropertyCategory.BOOL),
    LOCKED(CellPropertyCategory.BOOL),
    WRAP_TEXT(CellPropertyCategory.BOOL),
    SHRINK_TO_FIT(CellPropertyCategory.BOOL),
    QUOTE_PREFIXED(CellPropertyCategory.BOOL),
    ALIGNMENT(CellPropertyCategory.OTHER),
    FILL_PATTERN(CellPropertyCategory.OTHER),
    VERTICAL_ALIGNMENT(CellPropertyCategory.OTHER);

    private final CellPropertyCategory category;

    CellPropertyType(CellPropertyCategory category) {
        this.category = category;
    }

    public CellPropertyCategory getCategory() {
        return this.category;
    }
}
