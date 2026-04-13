package org.apache.poi.ss.util;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellPropertyType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.Removal;

/* loaded from: classes10.dex */
public final class RegionUtil {
    private RegionUtil() {
    }

    /* loaded from: classes10.dex */
    private static final class CellPropertySetter {
        private final Object _propertyValue;
        private final CellPropertyType property;

        @Removal(version = "7.0.0")
        @Deprecated
        public CellPropertySetter(String propertyName, int value) {
            this(CellUtil.namePropertyMap.get(propertyName), value);
        }

        @Removal(version = "7.0.0")
        @Deprecated
        public CellPropertySetter(String propertyName, BorderStyle value) {
            this(CellUtil.namePropertyMap.get(propertyName), value);
        }

        public CellPropertySetter(CellPropertyType property, int value) {
            this.property = property;
            this._propertyValue = Integer.valueOf(value);
        }

        public CellPropertySetter(CellPropertyType property, BorderStyle value) {
            this.property = property;
            this._propertyValue = value;
        }

        public void setProperty(Row row, int column) {
            Cell cell = CellUtil.getCell(row, column);
            CellUtil.setCellStyleProperty(cell, this.property, this._propertyValue);
        }
    }

    public static void setBorderLeft(BorderStyle border, CellRangeAddress region, Sheet sheet) {
        int rowStart = region.getFirstRow();
        int rowEnd = region.getLastRow();
        int column = region.getFirstColumn();
        CellPropertySetter cps = new CellPropertySetter(CellPropertyType.BORDER_LEFT, border);
        for (int i = rowStart; i <= rowEnd; i++) {
            cps.setProperty(CellUtil.getRow(i, sheet), column);
        }
    }

    public static void setLeftBorderColor(int color, CellRangeAddress region, Sheet sheet) {
        int rowStart = region.getFirstRow();
        int rowEnd = region.getLastRow();
        int column = region.getFirstColumn();
        CellPropertySetter cps = new CellPropertySetter(CellPropertyType.LEFT_BORDER_COLOR, color);
        for (int i = rowStart; i <= rowEnd; i++) {
            cps.setProperty(CellUtil.getRow(i, sheet), column);
        }
    }

    public static void setBorderRight(BorderStyle border, CellRangeAddress region, Sheet sheet) {
        int rowStart = region.getFirstRow();
        int rowEnd = region.getLastRow();
        int column = region.getLastColumn();
        CellPropertySetter cps = new CellPropertySetter(CellPropertyType.BORDER_RIGHT, border);
        for (int i = rowStart; i <= rowEnd; i++) {
            cps.setProperty(CellUtil.getRow(i, sheet), column);
        }
    }

    public static void setRightBorderColor(int color, CellRangeAddress region, Sheet sheet) {
        int rowStart = region.getFirstRow();
        int rowEnd = region.getLastRow();
        int column = region.getLastColumn();
        CellPropertySetter cps = new CellPropertySetter(CellPropertyType.RIGHT_BORDER_COLOR, color);
        for (int i = rowStart; i <= rowEnd; i++) {
            cps.setProperty(CellUtil.getRow(i, sheet), column);
        }
    }

    public static void setBorderBottom(BorderStyle border, CellRangeAddress region, Sheet sheet) {
        int colStart = region.getFirstColumn();
        int colEnd = region.getLastColumn();
        int rowIndex = region.getLastRow();
        CellPropertySetter cps = new CellPropertySetter(CellPropertyType.BORDER_BOTTOM, border);
        Row row = CellUtil.getRow(rowIndex, sheet);
        for (int i = colStart; i <= colEnd; i++) {
            cps.setProperty(row, i);
        }
    }

    public static void setBottomBorderColor(int color, CellRangeAddress region, Sheet sheet) {
        int colStart = region.getFirstColumn();
        int colEnd = region.getLastColumn();
        int rowIndex = region.getLastRow();
        CellPropertySetter cps = new CellPropertySetter(CellPropertyType.BOTTOM_BORDER_COLOR, color);
        Row row = CellUtil.getRow(rowIndex, sheet);
        for (int i = colStart; i <= colEnd; i++) {
            cps.setProperty(row, i);
        }
    }

    public static void setBorderTop(BorderStyle border, CellRangeAddress region, Sheet sheet) {
        int colStart = region.getFirstColumn();
        int colEnd = region.getLastColumn();
        int rowIndex = region.getFirstRow();
        CellPropertySetter cps = new CellPropertySetter(CellPropertyType.BORDER_TOP, border);
        Row row = CellUtil.getRow(rowIndex, sheet);
        for (int i = colStart; i <= colEnd; i++) {
            cps.setProperty(row, i);
        }
    }

    public static void setTopBorderColor(int color, CellRangeAddress region, Sheet sheet) {
        int colStart = region.getFirstColumn();
        int colEnd = region.getLastColumn();
        int rowIndex = region.getFirstRow();
        CellPropertySetter cps = new CellPropertySetter(CellPropertyType.TOP_BORDER_COLOR, color);
        Row row = CellUtil.getRow(rowIndex, sheet);
        for (int i = colStart; i <= colEnd; i++) {
            cps.setProperty(row, i);
        }
    }
}
