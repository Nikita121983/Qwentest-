package org.apache.poi.ss.util;

import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.text.AttributedString;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.ExceptionUtil;
import org.apache.poi.util.Internal;

/* loaded from: classes10.dex */
public class SheetUtil {
    public static final int DEFAULT_CHAR_WIDTH = 5;
    private static final char defaultChar = '0';
    private static final double fontHeightMultiple = 2.0d;
    private static final FormulaEvaluator dummyEvaluator = new FormulaEvaluator() { // from class: org.apache.poi.ss.util.SheetUtil.1
        @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
        public void clearAllCachedResultValues() {
        }

        @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
        public void notifySetFormula(Cell cell) {
        }

        @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
        public void notifyDeleteCell(Cell cell) {
        }

        @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
        public void notifyUpdateCell(Cell cell) {
        }

        @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
        public CellValue evaluate(Cell cell) {
            return null;
        }

        @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
        public Cell evaluateInCell(Cell cell) {
            return null;
        }

        @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
        public void setupReferencedWorkbooks(Map<String, FormulaEvaluator> workbooks) {
        }

        @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
        public void setDebugEvaluationOutputForNextEval(boolean value) {
        }

        @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
        public void setIgnoreMissingWorkbooks(boolean ignore) {
        }

        @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
        public void evaluateAll() {
        }

        @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
        public CellType evaluateFormulaCell(Cell cell) {
            return cell.getCachedFormulaResultType();
        }
    };
    private static boolean ignoreMissingFontSystem = initIgnoreMissingFontSystemFlag();
    private static final Function5Arity<Float, Integer, CellStyle, Double, AttributedString, Float> DEFAULT_FAILOVER_FUNCTION = new Function5Arity() { // from class: org.apache.poi.ss.util.SheetUtil$$ExternalSyntheticLambda0
        @Override // org.apache.poi.ss.util.SheetUtil.Function5Arity
        public final Object apply(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return SheetUtil.lambda$static$0((Float) obj, (Integer) obj2, (CellStyle) obj3, (Double) obj4, (AttributedString) obj5);
        }
    };
    private static Function5Arity<Float, Integer, CellStyle, Double, AttributedString, Float> FAILOVER_FUNCTION = DEFAULT_FAILOVER_FUNCTION;

    @FunctionalInterface
    /* loaded from: classes10.dex */
    public interface Function5Arity<A, B, C, D, E, R> {
        R apply(A a, B b, C c, D d, E e);
    }

    public static double getCellWidth(Cell cell, int defaultCharWidth, DataFormatter formatter, boolean useMergedCells) {
        return getCellWidth(cell, defaultCharWidth, formatter, useMergedCells);
    }

    public static double getCellWidth(Cell cell, float defaultCharWidth, DataFormatter formatter, boolean useMergedCells) {
        List<CellRangeAddress> mergedRegions = cell.getSheet().getMergedRegions();
        return getCellWidth(cell, defaultCharWidth, formatter, useMergedCells, mergedRegions);
    }

    public static double getCellWidth(Cell cell, int defaultCharWidth, DataFormatter formatter, boolean useMergedCells, List<CellRangeAddress> mergedRegions) {
        return getCellWidth(cell, defaultCharWidth, formatter, useMergedCells, mergedRegions);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static double getCellWidth(org.apache.poi.ss.usermodel.Cell r20, float r21, org.apache.poi.ss.usermodel.DataFormatter r22, boolean r23, java.util.List<org.apache.poi.ss.util.CellRangeAddress> r24) {
        /*
            Method dump skipped, instructions count: 305
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.util.SheetUtil.getCellWidth(org.apache.poi.ss.usermodel.Cell, float, org.apache.poi.ss.usermodel.DataFormatter, boolean, java.util.List):double");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class WithJavaDesktop {
        private static FontRenderContext fontRenderContext = new FontRenderContext((AffineTransform) null, true, true);

        private WithJavaDesktop() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static double getCellWidth(float defaultCharWidth, int colspan, CellStyle style, double minWidth, AttributedString str) {
            Rectangle bounds;
            try {
                TextLayout layout = new TextLayout(str.getIterator(), fontRenderContext);
                if (style.getRotation() != 0) {
                    AffineTransform trans = new AffineTransform();
                    trans.concatenate(AffineTransform.getRotateInstance(((style.getRotation() * SheetUtil.fontHeightMultiple) * 3.141592653589793d) / 360.0d));
                    trans.concatenate(AffineTransform.getScaleInstance(1.0d, SheetUtil.fontHeightMultiple));
                    bounds = layout.getOutline(trans).getBounds();
                } else {
                    bounds = layout.getBounds();
                }
                double frameWidth = bounds.getX() + bounds.getWidth();
                return Math.max(minWidth, ((frameWidth / colspan) / defaultCharWidth) + style.getIndention());
            } catch (Throwable t) {
                if (SheetUtil.shouldIgnoreMissingFontSystem(t)) {
                    return ((Float) SheetUtil.FAILOVER_FUNCTION.apply(Float.valueOf(defaultCharWidth), Integer.valueOf(colspan), style, Double.valueOf(minWidth), str)).floatValue();
                }
                throw t;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static float getDefaultCharWidthAsFloat(AttributedString str) {
            TextLayout layout = new TextLayout(str.getIterator(), fontRenderContext);
            return layout.getAdvance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void modifyAttributedString(AttributedString str, Font font, int startIdx, int endIdx) {
            str.addAttribute(TextAttribute.FAMILY, font.getFontName(), startIdx, endIdx);
            str.addAttribute(TextAttribute.SIZE, Float.valueOf(font.getFontHeightInPoints()));
            if (font.getBold()) {
                str.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD, startIdx, endIdx);
            }
            if (font.getItalic()) {
                str.addAttribute(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE, startIdx, endIdx);
            }
            if (font.getUnderline() == 1) {
                str.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, startIdx, endIdx);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean canComputeColumnWidth(Font font, AttributedString str) {
            modifyAttributedString(str, font, 0, "1w".length());
            TextLayout layout = new TextLayout(str.getIterator(), fontRenderContext);
            return layout.getBounds().getWidth() > 0.0d;
        }
    }

    public static double getColumnWidth(Sheet sheet, int column, boolean useMergedCells) {
        return getColumnWidth(sheet, column, useMergedCells, sheet.getFirstRowNum(), sheet.getLastRowNum());
    }

    public static double getColumnWidth(Sheet sheet, int column, boolean useMergedCells, int firstRow, int lastRow) {
        int column2;
        boolean useMergedCells2;
        DataFormatter formatter = new DataFormatter();
        float defaultCharWidth = getDefaultCharWidthAsFloat(sheet.getWorkbook());
        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
        double width = -1.0d;
        int rowIdx = firstRow;
        while (rowIdx <= lastRow) {
            Row row = sheet.getRow(rowIdx);
            if (row == null) {
                column2 = column;
                useMergedCells2 = useMergedCells;
            } else {
                column2 = column;
                useMergedCells2 = useMergedCells;
                double cellWidth = getColumnWidthForRow(row, column2, defaultCharWidth, formatter, useMergedCells2, mergedRegions);
                width = Math.max(width, cellWidth);
            }
            rowIdx++;
            column = column2;
            useMergedCells = useMergedCells2;
        }
        return width;
    }

    @Internal
    public static int getDefaultCharWidth(Workbook wb) {
        return Math.round(getDefaultCharWidthAsFloat(wb));
    }

    @Internal
    public static float getDefaultCharWidthAsFloat(Workbook wb) {
        Font defaultFont = wb.getFontAt(0);
        AttributedString str = new AttributedString(String.valueOf(defaultChar));
        try {
            WithJavaDesktop.modifyAttributedString(str, defaultFont, 0, 1);
            return WithJavaDesktop.getDefaultCharWidthAsFloat(str);
        } catch (Throwable t) {
            if (shouldIgnoreMissingFontSystem(t)) {
                return 5.0f;
            }
            throw t;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Float lambda$static$0(Float defaultCharWidth, Integer colspan, CellStyle style, Double minWidth, AttributedString string) {
        return defaultCharWidth;
    }

    public static void setFailoverFunction(Function5Arity<Float, Integer, CellStyle, Double, AttributedString, Float> failoverFunction) {
        FAILOVER_FUNCTION = failoverFunction == null ? DEFAULT_FAILOVER_FUNCTION : failoverFunction;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean shouldIgnoreMissingFontSystem(Throwable t) {
        return ignoreMissingFontSystem && ((t instanceof UnsatisfiedLinkError) || (t instanceof NoClassDefFoundError) || (t instanceof InternalError) || !ExceptionUtil.isFatal(t));
    }

    private static double getColumnWidthForRow(Row row, int column, float defaultCharWidth, DataFormatter formatter, boolean useMergedCells, List<CellRangeAddress> mergedRegions) {
        Cell cell;
        if (row == null || (cell = row.getCell(column)) == null) {
            return -1.0d;
        }
        return getCellWidth(cell, defaultCharWidth, formatter, useMergedCells, mergedRegions);
    }

    public static boolean canComputeColumnWidth(Font font) {
        AttributedString str = new AttributedString("1w");
        try {
            return WithJavaDesktop.canComputeColumnWidth(font, str);
        } catch (Throwable t) {
            if (shouldIgnoreMissingFontSystem(t)) {
                return false;
            }
            throw t;
        }
    }

    public static Cell getCell(Sheet sheet, int rowIx, int colIx) {
        Row r = sheet.getRow(rowIx);
        if (r != null) {
            return r.getCell(colIx);
        }
        return null;
    }

    public static Cell getCellWithMerges(Sheet sheet, int rowIx, int colIx) {
        Row r;
        Cell c = getCell(sheet, rowIx, colIx);
        if (c != null) {
            return c;
        }
        for (CellRangeAddress mergedRegion : sheet.getMergedRegions()) {
            if (mergedRegion.isInRange(rowIx, colIx) && (r = sheet.getRow(mergedRegion.getFirstRow())) != null) {
                return r.getCell(mergedRegion.getFirstColumn());
            }
        }
        return null;
    }

    protected static boolean isIgnoreMissingFontSystem() {
        return ignoreMissingFontSystem;
    }

    protected static void setIgnoreMissingFontSystem(boolean value) {
        ignoreMissingFontSystem = value;
    }

    protected static FontRenderContext getFontRenderContext() {
        return WithJavaDesktop.fontRenderContext;
    }

    protected static void setFontRenderContext(FontRenderContext fontRenderContext) {
        FontRenderContext unused = WithJavaDesktop.fontRenderContext = fontRenderContext;
    }

    private static boolean initIgnoreMissingFontSystemFlag() {
        String flag = System.getProperty("org.apache.poi.ss.ignoreMissingFontSystem");
        if (flag != null) {
            return true ^ flag.trim().equalsIgnoreCase("false");
        }
        return true;
    }
}
