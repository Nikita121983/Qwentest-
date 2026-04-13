package org.apache.poi.ss.util;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.BorderExtent;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellPropertyType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.Removal;

/* loaded from: classes10.dex */
public final class PropertyTemplate {
    private final Map<CellAddress, EnumMap<CellPropertyType, Object>> _propertyTemplate;

    public PropertyTemplate() {
        this._propertyTemplate = new HashMap();
    }

    public PropertyTemplate(PropertyTemplate template) {
        this();
        for (Map.Entry<CellAddress, EnumMap<CellPropertyType, Object>> entry : template.getTemplate().entrySet()) {
            this._propertyTemplate.put(new CellAddress(entry.getKey()), cloneCellProperties(entry.getValue()));
        }
    }

    private Map<CellAddress, EnumMap<CellPropertyType, Object>> getTemplate() {
        return this._propertyTemplate;
    }

    private static EnumMap<CellPropertyType, Object> cloneCellProperties(EnumMap<CellPropertyType, Object> properties) {
        return new EnumMap<>((EnumMap) properties);
    }

    public void drawBorders(CellRangeAddress range, BorderStyle borderType, BorderExtent extent) {
        switch (extent) {
            case NONE:
                removeBorders(range);
                return;
            case ALL:
                drawHorizontalBorders(range, borderType, BorderExtent.ALL);
                drawVerticalBorders(range, borderType, BorderExtent.ALL);
                return;
            case INSIDE:
                drawHorizontalBorders(range, borderType, BorderExtent.INSIDE);
                drawVerticalBorders(range, borderType, BorderExtent.INSIDE);
                return;
            case OUTSIDE:
                drawOutsideBorders(range, borderType, BorderExtent.ALL);
                return;
            case TOP:
                drawTopBorder(range, borderType);
                return;
            case BOTTOM:
                drawBottomBorder(range, borderType);
                return;
            case LEFT:
                drawLeftBorder(range, borderType);
                return;
            case RIGHT:
                drawRightBorder(range, borderType);
                return;
            case HORIZONTAL:
                drawHorizontalBorders(range, borderType, BorderExtent.ALL);
                return;
            case INSIDE_HORIZONTAL:
                drawHorizontalBorders(range, borderType, BorderExtent.INSIDE);
                return;
            case OUTSIDE_HORIZONTAL:
                drawOutsideBorders(range, borderType, BorderExtent.HORIZONTAL);
                return;
            case VERTICAL:
                drawVerticalBorders(range, borderType, BorderExtent.ALL);
                return;
            case INSIDE_VERTICAL:
                drawVerticalBorders(range, borderType, BorderExtent.INSIDE);
                return;
            case OUTSIDE_VERTICAL:
                drawOutsideBorders(range, borderType, BorderExtent.VERTICAL);
                return;
            default:
                return;
        }
    }

    public void drawBorders(CellRangeAddress range, BorderStyle borderType, short color, BorderExtent extent) {
        drawBorders(range, borderType, extent);
        if (borderType != BorderStyle.NONE) {
            drawBorderColors(range, color, extent);
        }
    }

    private void drawTopBorder(CellRangeAddress range, BorderStyle borderType) {
        int row = range.getFirstRow();
        int firstCol = range.getFirstColumn();
        int lastCol = range.getLastColumn();
        for (int i = firstCol; i <= lastCol; i++) {
            addProperty(row, i, CellPropertyType.BORDER_TOP, borderType);
            if (borderType == BorderStyle.NONE && row > 0) {
                addProperty(row - 1, i, CellPropertyType.BORDER_BOTTOM, borderType);
            }
        }
    }

    private void drawBottomBorder(CellRangeAddress range, BorderStyle borderType) {
        int row = range.getLastRow();
        int firstCol = range.getFirstColumn();
        int lastCol = range.getLastColumn();
        for (int i = firstCol; i <= lastCol; i++) {
            addProperty(row, i, CellPropertyType.BORDER_BOTTOM, borderType);
            if (borderType == BorderStyle.NONE && row < SpreadsheetVersion.EXCEL2007.getMaxRows() - 1) {
                addProperty(row + 1, i, CellPropertyType.BORDER_TOP, borderType);
            }
        }
    }

    private void drawLeftBorder(CellRangeAddress range, BorderStyle borderType) {
        int firstRow = range.getFirstRow();
        int lastRow = range.getLastRow();
        int col = range.getFirstColumn();
        for (int i = firstRow; i <= lastRow; i++) {
            addProperty(i, col, CellPropertyType.BORDER_LEFT, borderType);
            if (borderType == BorderStyle.NONE && col > 0) {
                addProperty(i, col - 1, CellPropertyType.BORDER_RIGHT, borderType);
            }
        }
    }

    private void drawRightBorder(CellRangeAddress range, BorderStyle borderType) {
        int firstRow = range.getFirstRow();
        int lastRow = range.getLastRow();
        int col = range.getLastColumn();
        for (int i = firstRow; i <= lastRow; i++) {
            addProperty(i, col, CellPropertyType.BORDER_RIGHT, borderType);
            if (borderType == BorderStyle.NONE && col < SpreadsheetVersion.EXCEL2007.getMaxColumns() - 1) {
                addProperty(i, col + 1, CellPropertyType.BORDER_LEFT, borderType);
            }
        }
    }

    private void drawOutsideBorders(CellRangeAddress range, BorderStyle borderType, BorderExtent extent) {
        switch (extent) {
            case ALL:
            case HORIZONTAL:
            case VERTICAL:
                if (extent == BorderExtent.ALL || extent == BorderExtent.HORIZONTAL) {
                    drawTopBorder(range, borderType);
                    drawBottomBorder(range, borderType);
                }
                if (extent == BorderExtent.ALL || extent == BorderExtent.VERTICAL) {
                    drawLeftBorder(range, borderType);
                    drawRightBorder(range, borderType);
                    return;
                }
                return;
            default:
                throw new IllegalArgumentException("Unsupported PropertyTemplate.Extent, valid Extents are ALL, HORIZONTAL, and VERTICAL");
        }
    }

    private void drawHorizontalBorders(CellRangeAddress range, BorderStyle borderType, BorderExtent extent) {
        switch (extent) {
            case ALL:
            case INSIDE:
                int firstRow = range.getFirstRow();
                int lastRow = range.getLastRow();
                int firstCol = range.getFirstColumn();
                int lastCol = range.getLastColumn();
                for (int i = firstRow; i <= lastRow; i++) {
                    CellRangeAddress row = new CellRangeAddress(i, i, firstCol, lastCol);
                    if (extent == BorderExtent.ALL || i > firstRow) {
                        drawTopBorder(row, borderType);
                    }
                    if (extent == BorderExtent.ALL || i < lastRow) {
                        drawBottomBorder(row, borderType);
                    }
                }
                return;
            default:
                throw new IllegalArgumentException("Unsupported PropertyTemplate.Extent, valid Extents are ALL and INSIDE");
        }
    }

    private void drawVerticalBorders(CellRangeAddress range, BorderStyle borderType, BorderExtent extent) {
        switch (extent) {
            case ALL:
            case INSIDE:
                int firstRow = range.getFirstRow();
                int lastRow = range.getLastRow();
                int firstCol = range.getFirstColumn();
                int lastCol = range.getLastColumn();
                for (int i = firstCol; i <= lastCol; i++) {
                    CellRangeAddress row = new CellRangeAddress(firstRow, lastRow, i, i);
                    if (extent == BorderExtent.ALL || i > firstCol) {
                        drawLeftBorder(row, borderType);
                    }
                    if (extent == BorderExtent.ALL || i < lastCol) {
                        drawRightBorder(row, borderType);
                    }
                }
                return;
            default:
                throw new IllegalArgumentException("Unsupported PropertyTemplate.Extent, valid Extents are ALL and INSIDE");
        }
    }

    private void removeBorders(CellRangeAddress range) {
        EnumSet<CellPropertyType> properties = EnumSet.of(CellPropertyType.BORDER_TOP, CellPropertyType.BORDER_BOTTOM, CellPropertyType.BORDER_LEFT, CellPropertyType.BORDER_RIGHT);
        for (int row = range.getFirstRow(); row <= range.getLastRow(); row++) {
            for (int col = range.getFirstColumn(); col <= range.getLastColumn(); col++) {
                removeProperties(row, col, properties);
            }
        }
        removeBorderColors(range);
    }

    public void applyBorders(Sheet sheet) {
        Workbook wb = sheet.getWorkbook();
        for (Map.Entry<CellAddress, EnumMap<CellPropertyType, Object>> entry : this._propertyTemplate.entrySet()) {
            CellAddress cellAddress = entry.getKey();
            if (cellAddress.getRow() < wb.getSpreadsheetVersion().getMaxRows() && cellAddress.getColumn() < wb.getSpreadsheetVersion().getMaxColumns()) {
                Map<CellPropertyType, Object> properties = entry.getValue();
                Row row = CellUtil.getRow(cellAddress.getRow(), sheet);
                Cell cell = CellUtil.getCell(row, cellAddress.getColumn());
                CellUtil.setCellStylePropertiesEnum(cell, properties);
            }
        }
    }

    public void drawBorderColors(CellRangeAddress range, short color, BorderExtent extent) {
        switch (extent) {
            case NONE:
                removeBorderColors(range);
                return;
            case ALL:
                drawHorizontalBorderColors(range, color, BorderExtent.ALL);
                drawVerticalBorderColors(range, color, BorderExtent.ALL);
                return;
            case INSIDE:
                drawHorizontalBorderColors(range, color, BorderExtent.INSIDE);
                drawVerticalBorderColors(range, color, BorderExtent.INSIDE);
                return;
            case OUTSIDE:
                drawOutsideBorderColors(range, color, BorderExtent.ALL);
                return;
            case TOP:
                drawTopBorderColor(range, color);
                return;
            case BOTTOM:
                drawBottomBorderColor(range, color);
                return;
            case LEFT:
                drawLeftBorderColor(range, color);
                return;
            case RIGHT:
                drawRightBorderColor(range, color);
                return;
            case HORIZONTAL:
                drawHorizontalBorderColors(range, color, BorderExtent.ALL);
                return;
            case INSIDE_HORIZONTAL:
                drawHorizontalBorderColors(range, color, BorderExtent.INSIDE);
                return;
            case OUTSIDE_HORIZONTAL:
                drawOutsideBorderColors(range, color, BorderExtent.HORIZONTAL);
                return;
            case VERTICAL:
                drawVerticalBorderColors(range, color, BorderExtent.ALL);
                return;
            case INSIDE_VERTICAL:
                drawVerticalBorderColors(range, color, BorderExtent.INSIDE);
                return;
            case OUTSIDE_VERTICAL:
                drawOutsideBorderColors(range, color, BorderExtent.VERTICAL);
                return;
            default:
                return;
        }
    }

    private void drawTopBorderColor(CellRangeAddress range, short color) {
        int row = range.getFirstRow();
        int firstCol = range.getFirstColumn();
        int lastCol = range.getLastColumn();
        for (int i = firstCol; i <= lastCol; i++) {
            if (getBorderStyle(row, i, CellPropertyType.BORDER_TOP) == BorderStyle.NONE) {
                drawTopBorder(new CellRangeAddress(row, row, i, i), BorderStyle.THIN);
            }
            addProperty(row, i, CellPropertyType.TOP_BORDER_COLOR, color);
        }
    }

    private void drawBottomBorderColor(CellRangeAddress range, short color) {
        int row = range.getLastRow();
        int firstCol = range.getFirstColumn();
        int lastCol = range.getLastColumn();
        for (int i = firstCol; i <= lastCol; i++) {
            if (getBorderStyle(row, i, CellPropertyType.BORDER_BOTTOM) == BorderStyle.NONE) {
                drawBottomBorder(new CellRangeAddress(row, row, i, i), BorderStyle.THIN);
            }
            addProperty(row, i, CellPropertyType.BOTTOM_BORDER_COLOR, color);
        }
    }

    private void drawLeftBorderColor(CellRangeAddress range, short color) {
        int firstRow = range.getFirstRow();
        int lastRow = range.getLastRow();
        int col = range.getFirstColumn();
        for (int i = firstRow; i <= lastRow; i++) {
            if (getBorderStyle(i, col, CellPropertyType.BORDER_LEFT) == BorderStyle.NONE) {
                drawLeftBorder(new CellRangeAddress(i, i, col, col), BorderStyle.THIN);
            }
            addProperty(i, col, CellPropertyType.LEFT_BORDER_COLOR, color);
        }
    }

    private void drawRightBorderColor(CellRangeAddress range, short color) {
        int firstRow = range.getFirstRow();
        int lastRow = range.getLastRow();
        int col = range.getLastColumn();
        for (int i = firstRow; i <= lastRow; i++) {
            if (getBorderStyle(i, col, CellPropertyType.BORDER_RIGHT) == BorderStyle.NONE) {
                drawRightBorder(new CellRangeAddress(i, i, col, col), BorderStyle.THIN);
            }
            addProperty(i, col, CellPropertyType.RIGHT_BORDER_COLOR, color);
        }
    }

    private void drawOutsideBorderColors(CellRangeAddress range, short color, BorderExtent extent) {
        switch (extent) {
            case ALL:
            case HORIZONTAL:
            case VERTICAL:
                if (extent == BorderExtent.ALL || extent == BorderExtent.HORIZONTAL) {
                    drawTopBorderColor(range, color);
                    drawBottomBorderColor(range, color);
                }
                if (extent == BorderExtent.ALL || extent == BorderExtent.VERTICAL) {
                    drawLeftBorderColor(range, color);
                    drawRightBorderColor(range, color);
                    return;
                }
                return;
            default:
                throw new IllegalArgumentException("Unsupported PropertyTemplate.Extent, valid Extents are ALL, HORIZONTAL, and VERTICAL");
        }
    }

    private void drawHorizontalBorderColors(CellRangeAddress range, short color, BorderExtent extent) {
        switch (extent) {
            case ALL:
            case INSIDE:
                int firstRow = range.getFirstRow();
                int lastRow = range.getLastRow();
                int firstCol = range.getFirstColumn();
                int lastCol = range.getLastColumn();
                for (int i = firstRow; i <= lastRow; i++) {
                    CellRangeAddress row = new CellRangeAddress(i, i, firstCol, lastCol);
                    if (extent == BorderExtent.ALL || i > firstRow) {
                        drawTopBorderColor(row, color);
                    }
                    if (extent == BorderExtent.ALL || i < lastRow) {
                        drawBottomBorderColor(row, color);
                    }
                }
                return;
            default:
                throw new IllegalArgumentException("Unsupported PropertyTemplate.Extent, valid Extents are ALL and INSIDE");
        }
    }

    private void drawVerticalBorderColors(CellRangeAddress range, short color, BorderExtent extent) {
        switch (extent) {
            case ALL:
            case INSIDE:
                int firstRow = range.getFirstRow();
                int lastRow = range.getLastRow();
                int firstCol = range.getFirstColumn();
                int lastCol = range.getLastColumn();
                for (int i = firstCol; i <= lastCol; i++) {
                    CellRangeAddress row = new CellRangeAddress(firstRow, lastRow, i, i);
                    if (extent == BorderExtent.ALL || i > firstCol) {
                        drawLeftBorderColor(row, color);
                    }
                    if (extent == BorderExtent.ALL || i < lastCol) {
                        drawRightBorderColor(row, color);
                    }
                }
                return;
            default:
                throw new IllegalArgumentException("Unsupported PropertyTemplate.Extent, valid Extents are ALL and INSIDE");
        }
    }

    private void removeBorderColors(CellRangeAddress range) {
        Set<CellPropertyType> properties = EnumSet.of(CellPropertyType.TOP_BORDER_COLOR, CellPropertyType.BOTTOM_BORDER_COLOR, CellPropertyType.LEFT_BORDER_COLOR, CellPropertyType.RIGHT_BORDER_COLOR);
        for (int row = range.getFirstRow(); row <= range.getLastRow(); row++) {
            for (int col = range.getFirstColumn(); col <= range.getLastColumn(); col++) {
                removeProperties(row, col, properties);
            }
        }
    }

    private void addProperty(int row, int col, CellPropertyType property, short value) {
        addProperty(row, col, property, Short.valueOf(value));
    }

    private void addProperty(int row, int col, CellPropertyType property, Object value) {
        CellAddress cell = new CellAddress(row, col);
        EnumMap<CellPropertyType, Object> cellProperties = this._propertyTemplate.get(cell);
        if (cellProperties == null) {
            cellProperties = new EnumMap<>((Class<CellPropertyType>) CellPropertyType.class);
        }
        cellProperties.put((EnumMap<CellPropertyType, Object>) property, (CellPropertyType) value);
        this._propertyTemplate.put(cell, cellProperties);
    }

    private void removeProperties(int row, int col, Set<CellPropertyType> properties) {
        CellAddress cell = new CellAddress(row, col);
        EnumMap<CellPropertyType, Object> cellProperties = this._propertyTemplate.get(cell);
        if (cellProperties != null) {
            cellProperties.keySet().removeAll(properties);
            if (cellProperties.isEmpty()) {
                this._propertyTemplate.remove(cell);
            } else {
                this._propertyTemplate.put(cell, cellProperties);
            }
        }
    }

    public int getNumBorders(CellAddress cell) {
        Map<CellPropertyType, Object> cellProperties = this._propertyTemplate.get(cell);
        if (cellProperties == null) {
            return 0;
        }
        int count = 0;
        for (CellPropertyType property : cellProperties.keySet()) {
            if (property.equals(CellPropertyType.BORDER_TOP)) {
                count++;
            }
            if (property.equals(CellPropertyType.BORDER_BOTTOM)) {
                count++;
            }
            if (property.equals(CellPropertyType.BORDER_LEFT)) {
                count++;
            }
            if (property.equals(CellPropertyType.BORDER_RIGHT)) {
                count++;
            }
        }
        return count;
    }

    public int getNumBorders(int row, int col) {
        return getNumBorders(new CellAddress(row, col));
    }

    public int getNumBorderColors(CellAddress cell) {
        Map<CellPropertyType, Object> cellProperties = this._propertyTemplate.get(cell);
        if (cellProperties == null) {
            return 0;
        }
        int count = 0;
        for (CellPropertyType property : cellProperties.keySet()) {
            if (property.equals(CellPropertyType.TOP_BORDER_COLOR)) {
                count++;
            }
            if (property.equals(CellPropertyType.BOTTOM_BORDER_COLOR)) {
                count++;
            }
            if (property.equals(CellPropertyType.LEFT_BORDER_COLOR)) {
                count++;
            }
            if (property.equals(CellPropertyType.RIGHT_BORDER_COLOR)) {
                count++;
            }
        }
        return count;
    }

    public int getNumBorderColors(int row, int col) {
        return getNumBorderColors(new CellAddress(row, col));
    }

    public BorderStyle getBorderStyle(CellAddress cell, CellPropertyType property) {
        BorderStyle value = BorderStyle.NONE;
        Map<CellPropertyType, Object> cellProperties = this._propertyTemplate.get(cell);
        if (cellProperties != null) {
            Object obj = cellProperties.get(property);
            if (obj instanceof BorderStyle) {
                return (BorderStyle) obj;
            }
            return value;
        }
        return value;
    }

    @Deprecated
    public BorderStyle getBorderStyle(CellAddress cell, String propertyName) {
        return getBorderStyle(cell, CellUtil.namePropertyMap.get(propertyName));
    }

    public BorderStyle getBorderStyle(int row, int col, CellPropertyType property) {
        return getBorderStyle(new CellAddress(row, col), property);
    }

    @Deprecated
    public BorderStyle getBorderStyle(int row, int col, String propertyName) {
        return getBorderStyle(new CellAddress(row, col), CellUtil.namePropertyMap.get(propertyName));
    }

    public short getTemplateProperty(CellAddress cell, CellPropertyType property) {
        Object obj;
        Map<CellPropertyType, Object> cellProperties = this._propertyTemplate.get(cell);
        if (cellProperties == null || (obj = cellProperties.get(property)) == null) {
            return (short) 0;
        }
        short value = getShort(obj);
        return value;
    }

    @Deprecated
    public short getTemplateProperty(CellAddress cell, String propertyName) {
        return getTemplateProperty(cell, CellUtil.namePropertyMap.get(propertyName));
    }

    public short getTemplateProperty(int row, int col, CellPropertyType property) {
        return getTemplateProperty(new CellAddress(row, col), property);
    }

    @Removal(version = "7.0.0")
    @Deprecated
    public short getTemplateProperty(int row, int col, String propertyName) {
        return getTemplateProperty(new CellAddress(row, col), CellUtil.namePropertyMap.get(propertyName));
    }

    private static short getShort(Object value) {
        if (value instanceof Number) {
            return ((Number) value).shortValue();
        }
        return (short) 0;
    }
}
