package org.apache.poi.ss.util;

import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import org.apache.logging.log4j.Logger;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellCopyContext;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.CellPropertyType;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;

/* loaded from: classes10.dex */
public final class CellUtil {

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String ALIGNMENT = "alignment";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String BORDER_BOTTOM = "borderBottom";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String BORDER_LEFT = "borderLeft";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String BORDER_RIGHT = "borderRight";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String BORDER_TOP = "borderTop";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String BOTTOM_BORDER_COLOR = "bottomBorderColor";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String DATA_FORMAT = "dataFormat";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String FILL_BACKGROUND_COLOR = "fillBackgroundColor";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String FILL_BACKGROUND_COLOR_COLOR = "fillBackgroundColorColor";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String FILL_FOREGROUND_COLOR = "fillForegroundColor";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String FILL_FOREGROUND_COLOR_COLOR = "fillForegroundColorColor";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String FILL_PATTERN = "fillPattern";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String FONT = "font";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String HIDDEN = "hidden";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String INDENTION = "indention";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String LEFT_BORDER_COLOR = "leftBorderColor";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String LOCKED = "locked";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String QUOTE_PREFIXED = "quotePrefixed";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String RIGHT_BORDER_COLOR = "rightBorderColor";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String ROTATION = "rotation";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String SHRINK_TO_FIT = "shrinkToFit";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String TOP_BORDER_COLOR = "topBorderColor";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String VERTICAL_ALIGNMENT = "verticalAlignment";

    @Removal(version = "7.0.0")
    @Deprecated
    public static final String WRAP_TEXT = "wrapText";
    static final Map<String, CellPropertyType> namePropertyMap;
    private static final UnicodeMapping[] unicodeMappings;
    private static final Logger LOGGER = PoiLogManager.getLogger((Class<?>) CellUtil.class);
    private static final Set<CellPropertyType> SHORT_VALUES = EnumSet.of(CellPropertyType.BOTTOM_BORDER_COLOR, CellPropertyType.LEFT_BORDER_COLOR, CellPropertyType.RIGHT_BORDER_COLOR, CellPropertyType.TOP_BORDER_COLOR, CellPropertyType.FILL_FOREGROUND_COLOR, CellPropertyType.FILL_BACKGROUND_COLOR, CellPropertyType.INDENTION, CellPropertyType.DATA_FORMAT, CellPropertyType.ROTATION);
    private static final Set<CellPropertyType> COLOR_VALUES = EnumSet.of(CellPropertyType.FILL_FOREGROUND_COLOR_COLOR, CellPropertyType.FILL_BACKGROUND_COLOR_COLOR);
    private static final Set<CellPropertyType> INT_VALUES = EnumSet.of(CellPropertyType.FONT);
    private static final Set<CellPropertyType> BOOLEAN_VALUES = EnumSet.of(CellPropertyType.LOCKED, CellPropertyType.HIDDEN, CellPropertyType.WRAP_TEXT, CellPropertyType.SHRINK_TO_FIT, CellPropertyType.QUOTE_PREFIXED);
    private static final Set<CellPropertyType> BORDER_TYPE_VALUES = EnumSet.of(CellPropertyType.BORDER_BOTTOM, CellPropertyType.BORDER_LEFT, CellPropertyType.BORDER_RIGHT, CellPropertyType.BORDER_TOP);

    static {
        Map<String, CellPropertyType> map = new HashMap<>();
        map.put(ALIGNMENT, CellPropertyType.ALIGNMENT);
        map.put(BORDER_BOTTOM, CellPropertyType.BORDER_BOTTOM);
        map.put(BORDER_LEFT, CellPropertyType.BORDER_LEFT);
        map.put(BORDER_RIGHT, CellPropertyType.BORDER_RIGHT);
        map.put(BORDER_TOP, CellPropertyType.BORDER_TOP);
        map.put(BOTTOM_BORDER_COLOR, CellPropertyType.BOTTOM_BORDER_COLOR);
        map.put(LEFT_BORDER_COLOR, CellPropertyType.LEFT_BORDER_COLOR);
        map.put(RIGHT_BORDER_COLOR, CellPropertyType.RIGHT_BORDER_COLOR);
        map.put(TOP_BORDER_COLOR, CellPropertyType.TOP_BORDER_COLOR);
        map.put(DATA_FORMAT, CellPropertyType.DATA_FORMAT);
        map.put(FILL_BACKGROUND_COLOR, CellPropertyType.FILL_BACKGROUND_COLOR);
        map.put(FILL_FOREGROUND_COLOR, CellPropertyType.FILL_FOREGROUND_COLOR);
        map.put(FILL_BACKGROUND_COLOR_COLOR, CellPropertyType.FILL_BACKGROUND_COLOR_COLOR);
        map.put(FILL_FOREGROUND_COLOR_COLOR, CellPropertyType.FILL_FOREGROUND_COLOR_COLOR);
        map.put(FILL_PATTERN, CellPropertyType.FILL_PATTERN);
        map.put(FONT, CellPropertyType.FONT);
        map.put(HIDDEN, CellPropertyType.HIDDEN);
        map.put(INDENTION, CellPropertyType.INDENTION);
        map.put(LOCKED, CellPropertyType.LOCKED);
        map.put(QUOTE_PREFIXED, CellPropertyType.QUOTE_PREFIXED);
        map.put("rotation", CellPropertyType.ROTATION);
        map.put(SHRINK_TO_FIT, CellPropertyType.SHRINK_TO_FIT);
        map.put(VERTICAL_ALIGNMENT, CellPropertyType.VERTICAL_ALIGNMENT);
        map.put(WRAP_TEXT, CellPropertyType.WRAP_TEXT);
        namePropertyMap = Collections.unmodifiableMap(map);
        unicodeMappings = new UnicodeMapping[]{um("alpha", "α"), um("beta", "β"), um("gamma", "γ"), um("delta", "δ"), um("epsilon", "ε"), um("zeta", "ζ"), um("eta", "η"), um("theta", "θ"), um("iota", "ι"), um("kappa", "κ"), um("lambda", "λ"), um("mu", "μ"), um("nu", "ν"), um("xi", "ξ"), um("omicron", "ο")};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class UnicodeMapping {
        public final String entityName;
        public final String resolvedValue;

        public UnicodeMapping(String pEntityName, String pResolvedValue) {
            this.entityName = "&" + pEntityName + ";";
            this.resolvedValue = pResolvedValue;
        }
    }

    private CellUtil() {
    }

    public static Row getRow(int rowIndex, Sheet sheet) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            return sheet.createRow(rowIndex);
        }
        return row;
    }

    public static Cell getCell(Row row, int columnIndex) {
        Cell cell = row.getCell(columnIndex);
        if (cell == null) {
            return row.createCell(columnIndex);
        }
        return cell;
    }

    public static Cell createCell(Row row, int column, String value, CellStyle style) {
        Cell cell = getCell(row, column);
        cell.setCellValue(cell.getRow().getSheet().getWorkbook().getCreationHelper().createRichTextString(value));
        if (style != null) {
            cell.setCellStyle(style);
        }
        return cell;
    }

    public static Cell createCell(Row row, int column, String value) {
        return createCell(row, column, value, null);
    }

    public static void copyCell(Cell srcCell, Cell destCell, CellCopyPolicy policy, CellCopyContext context) {
        if (policy.isCopyCellValue()) {
            if (srcCell != null) {
                CellType copyCellType = srcCell.getCellType();
                if (copyCellType == CellType.FORMULA && !policy.isCopyCellFormula()) {
                    copyCellType = srcCell.getCachedFormulaResultType();
                }
                switch (copyCellType) {
                    case NUMERIC:
                        if (!policy.isCopyCellStyle() && DateUtil.isCellDateFormatted(srcCell)) {
                            destCell.setCellValue(srcCell.getDateCellValue());
                            break;
                        } else {
                            destCell.setCellValue(srcCell.getNumericCellValue());
                            break;
                        }
                        break;
                    case STRING:
                        destCell.setCellValue(srcCell.getRichStringCellValue());
                        break;
                    case FORMULA:
                        destCell.setCellFormula(srcCell.getCellFormula());
                        break;
                    case BLANK:
                        destCell.setBlank();
                        break;
                    case BOOLEAN:
                        destCell.setCellValue(srcCell.getBooleanCellValue());
                        break;
                    case ERROR:
                        destCell.setCellErrorValue(srcCell.getErrorCellValue());
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid cell type " + srcCell.getCellType());
                }
            } else {
                destCell.setBlank();
            }
        }
        if (policy.isCopyCellStyle() && srcCell != null) {
            if (srcCell.getSheet() != null && destCell.getSheet() != null && destCell.getSheet().getWorkbook() == srcCell.getSheet().getWorkbook()) {
                destCell.setCellStyle(srcCell.getCellStyle());
            } else {
                CellStyle srcStyle = srcCell.getCellStyle();
                CellStyle destStyle = context == null ? null : context.getMappedStyle(srcStyle);
                if (destStyle == null) {
                    destStyle = destCell.getSheet().getWorkbook().createCellStyle();
                    destStyle.cloneStyleFrom(srcStyle);
                    if (context != null) {
                        context.putMappedStyle(srcStyle, destStyle);
                    }
                }
                destCell.setCellStyle(destStyle);
            }
        }
        Hyperlink srcHyperlink = srcCell == null ? null : srcCell.getHyperlink();
        if (policy.isMergeHyperlink()) {
            if (srcHyperlink != null) {
                if (srcHyperlink instanceof Duplicatable) {
                    Hyperlink newHyperlink = (Hyperlink) ((Duplicatable) srcHyperlink).copy();
                    destCell.setHyperlink(newHyperlink);
                    return;
                }
                throw new IllegalStateException("srcCell hyperlink is not an instance of Duplicatable");
            }
            return;
        }
        if (policy.isCopyHyperlink()) {
            if (srcHyperlink == null) {
                destCell.setHyperlink(null);
            } else {
                if (srcHyperlink instanceof Duplicatable) {
                    Hyperlink newHyperlink2 = (Hyperlink) ((Duplicatable) srcHyperlink).copy();
                    destCell.setHyperlink(newHyperlink2);
                    return;
                }
                throw new IllegalStateException("srcCell hyperlink is not an instance of Duplicatable");
            }
        }
    }

    public static void setAlignment(Cell cell, HorizontalAlignment align) {
        setCellStyleProperty(cell, CellPropertyType.ALIGNMENT, align);
    }

    public static void setVerticalAlignment(Cell cell, VerticalAlignment align) {
        setCellStyleProperty(cell, CellPropertyType.VERTICAL_ALIGNMENT, align);
    }

    public static void setFont(Cell cell, Font font) {
        Workbook wb = cell.getSheet().getWorkbook();
        int fontIndex = font.getIndex();
        if (!wb.getFontAt(fontIndex).equals(font)) {
            throw new IllegalArgumentException("Font does not belong to this workbook");
        }
        setCellStyleProperty(cell, CellPropertyType.FONT, Integer.valueOf(fontIndex));
    }

    @Removal(version = "7.0.0")
    @Deprecated
    public static void setCellStyleProperties(Cell cell, Map<String, Object> properties) {
        final EnumMap<CellPropertyType, Object> strPropMap = new EnumMap<>((Class<CellPropertyType>) CellPropertyType.class);
        properties.forEach(new BiConsumer() { // from class: org.apache.poi.ss.util.CellUtil$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                strPropMap.put((EnumMap) CellUtil.namePropertyMap.get((String) obj), (EnumMap) ((CellPropertyType) obj2));
            }
        });
        setCellStyleProperties(cell, strPropMap, false);
    }

    public static void setCellStylePropertiesEnum(Cell cell, Map<CellPropertyType, Object> properties) {
        setCellStyleProperties(cell, properties, false);
    }

    private static void setCellStyleProperties(Cell cell, Map<CellPropertyType, Object> properties, boolean disableNullColorCheck) {
        Workbook workbook = cell.getSheet().getWorkbook();
        CellStyle originalStyle = cell.getCellStyle();
        CellStyle newStyle = null;
        EnumMap<CellPropertyType, Object> values = originalStyle.getFormatProperties();
        if (properties.containsKey(CellPropertyType.FILL_FOREGROUND_COLOR_COLOR) && properties.get(CellPropertyType.FILL_FOREGROUND_COLOR_COLOR) == null) {
            values.remove(CellPropertyType.FILL_FOREGROUND_COLOR);
        }
        if (properties.containsKey(CellPropertyType.FILL_FOREGROUND_COLOR) && !properties.containsKey(CellPropertyType.FILL_FOREGROUND_COLOR_COLOR)) {
            values.remove(CellPropertyType.FILL_FOREGROUND_COLOR_COLOR);
        }
        if (properties.containsKey(CellPropertyType.FILL_BACKGROUND_COLOR_COLOR) && properties.get(CellPropertyType.FILL_BACKGROUND_COLOR_COLOR) == null) {
            values.remove(CellPropertyType.FILL_BACKGROUND_COLOR);
        }
        if (properties.containsKey(CellPropertyType.FILL_BACKGROUND_COLOR) && !properties.containsKey(CellPropertyType.FILL_BACKGROUND_COLOR_COLOR)) {
            values.remove(CellPropertyType.FILL_BACKGROUND_COLOR_COLOR);
        }
        putAll(properties, values);
        int numberCellStyles = workbook.getNumCellStyles();
        int i = 0;
        while (true) {
            if (i >= numberCellStyles) {
                break;
            }
            CellStyle wbStyle = workbook.getCellStyleAt(i);
            EnumMap<CellPropertyType, Object> wbStyleMap = wbStyle.getFormatProperties();
            if (!styleMapsMatch(wbStyleMap, values, disableNullColorCheck)) {
                i++;
            } else {
                newStyle = wbStyle;
                break;
            }
        }
        if (newStyle == null) {
            newStyle = workbook.createCellStyle();
            setFormatProperties(newStyle, workbook, values);
        }
        cell.setCellStyle(newStyle);
    }

    private static boolean styleMapsMatch(Map<CellPropertyType, Object> newProps, Map<CellPropertyType, Object> storedProps, boolean disableNullColorCheck) {
        EnumMap<CellPropertyType, Object> map1Copy = new EnumMap<>((Map<CellPropertyType, ? extends Object>) newProps);
        EnumMap<CellPropertyType, Object> map2Copy = new EnumMap<>((Map<CellPropertyType, ? extends Object>) storedProps);
        Object backColor1 = map1Copy.remove(CellPropertyType.FILL_BACKGROUND_COLOR_COLOR);
        Object backColor2 = map2Copy.remove(CellPropertyType.FILL_BACKGROUND_COLOR_COLOR);
        Object foreColor1 = map1Copy.remove(CellPropertyType.FILL_FOREGROUND_COLOR_COLOR);
        Object foreColor2 = map2Copy.remove(CellPropertyType.FILL_FOREGROUND_COLOR_COLOR);
        if (!map1Copy.equals(map2Copy)) {
            return false;
        }
        boolean backColorsMatch = (!disableNullColorCheck && backColor2 == null) || Objects.equals(backColor1, backColor2);
        boolean foreColorsMatch = (!disableNullColorCheck && foreColor2 == null) || Objects.equals(foreColor1, foreColor2);
        return backColorsMatch && foreColorsMatch;
    }

    public static void setCellStyleProperty(Cell cell, CellPropertyType property, Object propertyValue) {
        Map<CellPropertyType, Object> propMap;
        if (cell == null) {
            throw new NullPointerException("Cell must not be null");
        }
        if (property == null) {
            throw new NullPointerException("CellPropertyType must not be null");
        }
        boolean disableNullColorCheck = false;
        if (CellPropertyType.FILL_FOREGROUND_COLOR_COLOR.equals(property) && propertyValue == null) {
            disableNullColorCheck = true;
            propMap = new EnumMap<>(CellPropertyType.class);
            propMap.put(CellPropertyType.FILL_FOREGROUND_COLOR_COLOR, null);
            propMap.put(CellPropertyType.FILL_FOREGROUND_COLOR, null);
        } else if (CellPropertyType.FILL_BACKGROUND_COLOR_COLOR.equals(property) && propertyValue == null) {
            disableNullColorCheck = true;
            propMap = new EnumMap<>(CellPropertyType.class);
            propMap.put(CellPropertyType.FILL_BACKGROUND_COLOR_COLOR, null);
            propMap.put(CellPropertyType.FILL_BACKGROUND_COLOR, null);
        } else {
            propMap = Collections.singletonMap(property, propertyValue);
        }
        setCellStyleProperties(cell, propMap, disableNullColorCheck);
    }

    @Removal(version = "7.0.0")
    @Deprecated
    public static void setCellStyleProperty(Cell cell, String propertyName, Object propertyValue) {
        setCellStyleProperty(cell, namePropertyMap.get(propertyName), propertyValue);
    }

    public static EnumMap<CellPropertyType, Object> getFormatProperties(CellStyle style) {
        EnumMap<CellPropertyType, Object> properties = new EnumMap<>((Class<CellPropertyType>) CellPropertyType.class);
        put(properties, CellPropertyType.ALIGNMENT, style.getAlignment());
        put(properties, CellPropertyType.VERTICAL_ALIGNMENT, style.getVerticalAlignment());
        put(properties, CellPropertyType.BORDER_BOTTOM, style.getBorderBottom());
        put(properties, CellPropertyType.BORDER_LEFT, style.getBorderLeft());
        put(properties, CellPropertyType.BORDER_RIGHT, style.getBorderRight());
        put(properties, CellPropertyType.BORDER_TOP, style.getBorderTop());
        put(properties, CellPropertyType.BOTTOM_BORDER_COLOR, Short.valueOf(style.getBottomBorderColor()));
        put(properties, CellPropertyType.DATA_FORMAT, Short.valueOf(style.getDataFormat()));
        put(properties, CellPropertyType.FILL_PATTERN, style.getFillPattern());
        put(properties, CellPropertyType.FILL_FOREGROUND_COLOR, Short.valueOf(style.getFillForegroundColor()));
        put(properties, CellPropertyType.FILL_BACKGROUND_COLOR, Short.valueOf(style.getFillBackgroundColor()));
        put(properties, CellPropertyType.FILL_FOREGROUND_COLOR_COLOR, style.getFillForegroundColorColor());
        put(properties, CellPropertyType.FILL_BACKGROUND_COLOR_COLOR, style.getFillBackgroundColorColor());
        put(properties, CellPropertyType.FONT, Integer.valueOf(style.getFontIndex()));
        put(properties, CellPropertyType.HIDDEN, Boolean.valueOf(style.getHidden()));
        put(properties, CellPropertyType.INDENTION, Short.valueOf(style.getIndention()));
        put(properties, CellPropertyType.LEFT_BORDER_COLOR, Short.valueOf(style.getLeftBorderColor()));
        put(properties, CellPropertyType.LOCKED, Boolean.valueOf(style.getLocked()));
        put(properties, CellPropertyType.RIGHT_BORDER_COLOR, Short.valueOf(style.getRightBorderColor()));
        put(properties, CellPropertyType.ROTATION, Short.valueOf(style.getRotation()));
        put(properties, CellPropertyType.TOP_BORDER_COLOR, Short.valueOf(style.getTopBorderColor()));
        put(properties, CellPropertyType.WRAP_TEXT, Boolean.valueOf(style.getWrapText()));
        put(properties, CellPropertyType.SHRINK_TO_FIT, Boolean.valueOf(style.getShrinkToFit()));
        put(properties, CellPropertyType.QUOTE_PREFIXED, Boolean.valueOf(style.getQuotePrefixed()));
        return properties;
    }

    private static void putAll(Map<CellPropertyType, Object> src, Map<CellPropertyType, Object> dest) {
        for (CellPropertyType key : src.keySet()) {
            if (SHORT_VALUES.contains(key)) {
                dest.put(key, nullableShort(src, key));
            } else if (COLOR_VALUES.contains(key)) {
                dest.put(key, getColor(src, key));
            } else if (INT_VALUES.contains(key)) {
                dest.put(key, Integer.valueOf(getInt(src, key)));
            } else if (BOOLEAN_VALUES.contains(key)) {
                dest.put(key, Boolean.valueOf(getBoolean(src, key)));
            } else if (BORDER_TYPE_VALUES.contains(key)) {
                dest.put(key, getBorderStyle(src, key));
            } else if (CellPropertyType.ALIGNMENT.equals(key)) {
                dest.put(key, getHorizontalAlignment(src, key));
            } else if (CellPropertyType.VERTICAL_ALIGNMENT.equals(key)) {
                dest.put(key, getVerticalAlignment(src, key));
            } else if (CellPropertyType.FILL_PATTERN.equals(key)) {
                dest.put(key, getFillPattern(src, key));
            } else {
                LOGGER.atInfo().log("Ignoring unrecognized CellUtil format properties key: {}", key);
            }
        }
    }

    private static void setFormatProperties(CellStyle style, Workbook workbook, Map<CellPropertyType, Object> properties) {
        style.setAlignment(getHorizontalAlignment(properties, CellPropertyType.ALIGNMENT));
        style.setVerticalAlignment(getVerticalAlignment(properties, CellPropertyType.VERTICAL_ALIGNMENT));
        style.setBorderBottom(getBorderStyle(properties, CellPropertyType.BORDER_BOTTOM));
        style.setBorderLeft(getBorderStyle(properties, CellPropertyType.BORDER_LEFT));
        style.setBorderRight(getBorderStyle(properties, CellPropertyType.BORDER_RIGHT));
        style.setBorderTop(getBorderStyle(properties, CellPropertyType.BORDER_TOP));
        style.setBottomBorderColor(getShort(properties, CellPropertyType.BOTTOM_BORDER_COLOR));
        style.setDataFormat(getShort(properties, CellPropertyType.DATA_FORMAT));
        style.setFillPattern(getFillPattern(properties, CellPropertyType.FILL_PATTERN));
        Short fillForeColorShort = nullableShort(properties, CellPropertyType.FILL_FOREGROUND_COLOR);
        if (fillForeColorShort != null) {
            style.setFillForegroundColor(fillForeColorShort.shortValue());
        }
        Short fillBackColorShort = nullableShort(properties, CellPropertyType.FILL_BACKGROUND_COLOR);
        if (fillBackColorShort != null) {
            style.setFillBackgroundColor(fillBackColorShort.shortValue());
        }
        Color foregroundFillColor = getColor(properties, CellPropertyType.FILL_FOREGROUND_COLOR_COLOR);
        Color backgroundFillColor = getColor(properties, CellPropertyType.FILL_BACKGROUND_COLOR_COLOR);
        if (foregroundFillColor != null) {
            try {
                style.setFillForegroundColor(foregroundFillColor);
            } catch (IllegalArgumentException iae) {
                LOGGER.atDebug().log("Mismatched FillForegroundColor instance used", iae);
            }
        }
        if (backgroundFillColor != null) {
            try {
                style.setFillBackgroundColor(backgroundFillColor);
            } catch (IllegalArgumentException iae2) {
                LOGGER.atDebug().log("Mismatched FillBackgroundColor instance used", iae2);
            }
        }
        if (workbook != null) {
            style.setFont(workbook.getFontAt(getInt(properties, CellPropertyType.FONT)));
        }
        style.setHidden(getBoolean(properties, CellPropertyType.HIDDEN));
        style.setIndention(getShort(properties, CellPropertyType.INDENTION));
        style.setLeftBorderColor(getShort(properties, CellPropertyType.LEFT_BORDER_COLOR));
        style.setLocked(getBoolean(properties, CellPropertyType.LOCKED));
        style.setRightBorderColor(getShort(properties, CellPropertyType.RIGHT_BORDER_COLOR));
        style.setRotation(getShort(properties, CellPropertyType.ROTATION));
        style.setTopBorderColor(getShort(properties, CellPropertyType.TOP_BORDER_COLOR));
        style.setWrapText(getBoolean(properties, CellPropertyType.WRAP_TEXT));
        style.setShrinkToFit(getBoolean(properties, CellPropertyType.SHRINK_TO_FIT));
        style.setQuotePrefixed(getBoolean(properties, CellPropertyType.QUOTE_PREFIXED));
    }

    @Internal
    public static void cloneStyle(CellStyle src, CellStyle dest, Workbook destWorkbook) {
        if (src == null || dest == null) {
            throw new IllegalArgumentException("Source and destination styles must not be null");
        }
        EnumMap<CellPropertyType, Object> properties = src.getFormatProperties();
        setFormatProperties(dest, destWorkbook, properties);
    }

    private static short getShort(Map<CellPropertyType, Object> properties, CellPropertyType property) {
        Object value = properties.get(property);
        if (value instanceof Number) {
            return ((Number) value).shortValue();
        }
        return (short) 0;
    }

    private static Short nullableShort(Map<CellPropertyType, Object> properties, CellPropertyType property) {
        Object value = properties.get(property);
        if (value instanceof Short) {
            return (Short) value;
        }
        if (value instanceof Number) {
            return Short.valueOf(((Number) value).shortValue());
        }
        return null;
    }

    private static Color getColor(Map<CellPropertyType, Object> properties, CellPropertyType property) {
        Object value = properties.get(property);
        if (value instanceof Color) {
            return (Color) value;
        }
        return null;
    }

    private static int getInt(Map<CellPropertyType, Object> properties, CellPropertyType property) {
        Object value = properties.get(property);
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return 0;
    }

    private static BorderStyle getBorderStyle(Map<CellPropertyType, Object> properties, CellPropertyType property) {
        Object value = properties.get(property);
        if (value instanceof BorderStyle) {
            BorderStyle border = (BorderStyle) value;
            return border;
        }
        if (value instanceof Short) {
            LOGGER.atWarn().log("Deprecation warning: CellUtil properties map uses Short values for {}. Should use BorderStyle enums instead.", property);
            short code = ((Short) value).shortValue();
            BorderStyle border2 = BorderStyle.valueOf(code);
            return border2;
        }
        if (value == null) {
            BorderStyle border3 = BorderStyle.NONE;
            return border3;
        }
        throw new IllegalStateException("Unexpected border style class. Must be BorderStyle or Short (deprecated).");
    }

    private static FillPatternType getFillPattern(Map<CellPropertyType, Object> properties, CellPropertyType property) {
        Object value = properties.get(property);
        if (value instanceof FillPatternType) {
            FillPatternType pattern = (FillPatternType) value;
            return pattern;
        }
        if (value instanceof Short) {
            LOGGER.atWarn().log("Deprecation warning: CellUtil properties map uses Short values for {}. Should use FillPatternType enums instead.", property);
            short code = ((Short) value).shortValue();
            FillPatternType pattern2 = FillPatternType.forInt(code);
            return pattern2;
        }
        if (value == null) {
            FillPatternType pattern3 = FillPatternType.NO_FILL;
            return pattern3;
        }
        throw new IllegalStateException("Unexpected fill pattern style class. Must be FillPatternType or Short (deprecated).");
    }

    private static HorizontalAlignment getHorizontalAlignment(Map<CellPropertyType, Object> properties, CellPropertyType property) {
        Object value = properties.get(property);
        if (value instanceof HorizontalAlignment) {
            HorizontalAlignment align = (HorizontalAlignment) value;
            return align;
        }
        if (value instanceof Short) {
            LOGGER.atWarn().log("Deprecation warning: CellUtil properties map used a Short value for {}. Should use HorizontalAlignment enums instead.", property);
            short code = ((Short) value).shortValue();
            HorizontalAlignment align2 = HorizontalAlignment.forInt(code);
            return align2;
        }
        if (value == null) {
            HorizontalAlignment align3 = HorizontalAlignment.GENERAL;
            return align3;
        }
        throw new IllegalStateException("Unexpected horizontal alignment style class. Must be HorizontalAlignment or Short (deprecated).");
    }

    private static VerticalAlignment getVerticalAlignment(Map<CellPropertyType, Object> properties, CellPropertyType property) {
        Object value = properties.get(property);
        if (value instanceof VerticalAlignment) {
            VerticalAlignment align = (VerticalAlignment) value;
            return align;
        }
        if (value instanceof Short) {
            LOGGER.atWarn().log("Deprecation warning: CellUtil properties map used a Short value for {}. Should use VerticalAlignment enums instead.", property);
            short code = ((Short) value).shortValue();
            VerticalAlignment align2 = VerticalAlignment.forInt(code);
            return align2;
        }
        if (value == null) {
            VerticalAlignment align3 = VerticalAlignment.BOTTOM;
            return align3;
        }
        throw new IllegalStateException("Unexpected vertical alignment style class. Must be VerticalAlignment or Short (deprecated).");
    }

    private static boolean getBoolean(Map<CellPropertyType, Object> properties, CellPropertyType property) {
        Object value = properties.get(property);
        if (value instanceof Boolean) {
            return ((Boolean) value).booleanValue();
        }
        return false;
    }

    private static void put(Map<CellPropertyType, Object> properties, CellPropertyType property, Object value) {
        properties.put(property, value);
    }

    public static Cell translateUnicodeValues(Cell cell) {
        String s = cell.getRichStringCellValue().getString();
        boolean foundUnicode = false;
        String lowerCaseStr = s.toLowerCase(Locale.ROOT);
        for (UnicodeMapping entry : unicodeMappings) {
            String key = entry.entityName;
            if (lowerCaseStr.contains(key)) {
                s = s.replaceAll(key, entry.resolvedValue);
                foundUnicode = true;
            }
        }
        if (foundUnicode) {
            cell.setCellValue(cell.getRow().getSheet().getWorkbook().getCreationHelper().createRichTextString(s));
        }
        return cell;
    }

    private static UnicodeMapping um(String entityName, String resolvedValue) {
        return new UnicodeMapping(entityName, resolvedValue);
    }
}
