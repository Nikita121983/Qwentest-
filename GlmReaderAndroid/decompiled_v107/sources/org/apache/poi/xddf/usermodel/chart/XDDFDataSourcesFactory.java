package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData;

/* loaded from: classes10.dex */
public class XDDFDataSourcesFactory {
    private XDDFDataSourcesFactory() {
    }

    public static XDDFCategoryDataSource fromDataSource(final CTAxDataSource categoryDS) {
        if (categoryDS == null) {
            return null;
        }
        if (categoryDS.getNumRef() != null && categoryDS.getNumRef().getNumCache() != null) {
            return new XDDFCategoryDataSource() { // from class: org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory.1
                private final CTNumData category;
                private final String formatCode;

                {
                    this.category = (CTNumData) CTAxDataSource.this.getNumRef().getNumCache().copy();
                    this.formatCode = this.category.isSetFormatCode() ? this.category.getFormatCode() : null;
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public boolean isCellRange() {
                    return true;
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFCategoryDataSource, org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public boolean isNumeric() {
                    return true;
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public String getDataRangeReference() {
                    return CTAxDataSource.this.getNumRef().getF();
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public int getPointCount() {
                    return (int) this.category.getPtCount().getVal();
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public String getPointAt(int index) {
                    if (this.category.sizeOfPtArray() <= index) {
                        throw new IllegalArgumentException("Cannot access 0-based index " + index + " in point-array with " + this.category.sizeOfPtArray() + " items");
                    }
                    return this.category.getPtArray(index).getV();
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public String getFormatCode() {
                    return this.formatCode;
                }
            };
        }
        if (categoryDS.getStrRef() != null && categoryDS.getStrRef().getStrCache() != null) {
            return new XDDFCategoryDataSource() { // from class: org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory.2
                private final CTStrData category;

                {
                    this.category = (CTStrData) CTAxDataSource.this.getStrRef().getStrCache().copy();
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public boolean isCellRange() {
                    return true;
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public String getDataRangeReference() {
                    return CTAxDataSource.this.getStrRef().getF();
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public int getPointCount() {
                    return (int) this.category.getPtCount().getVal();
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public String getPointAt(int index) {
                    return this.category.getPtArray(index).getV();
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public String getFormatCode() {
                    return null;
                }
            };
        }
        if (categoryDS.getNumLit() != null) {
            return new XDDFCategoryDataSource() { // from class: org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory.3
                private final CTNumData category;
                private final String formatCode;

                {
                    this.category = (CTNumData) CTAxDataSource.this.getNumLit().copy();
                    this.formatCode = this.category.isSetFormatCode() ? this.category.getFormatCode() : null;
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public boolean isCellRange() {
                    return false;
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFCategoryDataSource, org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public boolean isLiteral() {
                    return true;
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFCategoryDataSource, org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public boolean isNumeric() {
                    return true;
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFCategoryDataSource, org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public boolean isReference() {
                    return false;
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public String getDataRangeReference() {
                    return null;
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public int getPointCount() {
                    return (int) this.category.getPtCount().getVal();
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public String getPointAt(int index) {
                    return this.category.getPtArray(index).getV();
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public String getFormatCode() {
                    return this.formatCode;
                }
            };
        }
        if (categoryDS.getStrLit() == null) {
            return null;
        }
        return new XDDFCategoryDataSource() { // from class: org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory.4
            private final CTStrData category;

            {
                this.category = (CTStrData) CTAxDataSource.this.getStrLit().copy();
            }

            @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
            public boolean isCellRange() {
                return false;
            }

            @Override // org.apache.poi.xddf.usermodel.chart.XDDFCategoryDataSource, org.apache.poi.xddf.usermodel.chart.XDDFDataSource
            public boolean isLiteral() {
                return true;
            }

            @Override // org.apache.poi.xddf.usermodel.chart.XDDFCategoryDataSource, org.apache.poi.xddf.usermodel.chart.XDDFDataSource
            public boolean isReference() {
                return false;
            }

            @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
            public String getDataRangeReference() {
                return null;
            }

            @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
            public int getPointCount() {
                return (int) this.category.getPtCount().getVal();
            }

            @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
            public String getPointAt(int index) {
                return this.category.getPtArray(index).getV();
            }

            @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
            public String getFormatCode() {
                return null;
            }
        };
    }

    public static XDDFNumericalDataSource<Double> fromDataSource(final CTNumDataSource valuesDS) {
        if (valuesDS == null) {
            return null;
        }
        if (valuesDS.getNumRef() != null && valuesDS.getNumRef().getNumCache() != null) {
            return new XDDFNumericalDataSource<Double>() { // from class: org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory.5
                private String formatCode;
                private final CTNumData values;

                {
                    this.values = (CTNumData) CTNumDataSource.this.getNumRef().getNumCache().copy();
                    this.formatCode = this.values.isSetFormatCode() ? this.values.getFormatCode() : null;
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public String getFormatCode() {
                    return this.formatCode;
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource
                public void setFormatCode(String formatCode) {
                    this.formatCode = formatCode;
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public boolean isCellRange() {
                    return true;
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public boolean isNumeric() {
                    return true;
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public boolean isReference() {
                    return true;
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public int getPointCount() {
                    return (int) this.values.getPtCount().getVal();
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public Double getPointAt(int index) {
                    return Double.valueOf(this.values.getPtArray(index).getV());
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public String getDataRangeReference() {
                    return CTNumDataSource.this.getNumRef().getF();
                }

                @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
                public int getColIndex() {
                    return 0;
                }
            };
        }
        if (valuesDS.getNumLit() == null) {
            return null;
        }
        return new XDDFNumericalDataSource<Double>() { // from class: org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory.6
            private String formatCode;
            private final CTNumData values;

            {
                this.values = (CTNumData) CTNumDataSource.this.getNumLit().copy();
                this.formatCode = this.values.isSetFormatCode() ? this.values.getFormatCode() : null;
            }

            @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
            public String getFormatCode() {
                return this.formatCode;
            }

            @Override // org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource
            public void setFormatCode(String formatCode) {
                this.formatCode = formatCode;
            }

            @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
            public boolean isCellRange() {
                return false;
            }

            @Override // org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource, org.apache.poi.xddf.usermodel.chart.XDDFDataSource
            public boolean isLiteral() {
                return true;
            }

            @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
            public boolean isNumeric() {
                return true;
            }

            @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
            public boolean isReference() {
                return false;
            }

            @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
            public int getPointCount() {
                return (int) this.values.getPtCount().getVal();
            }

            @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
            public Double getPointAt(int index) {
                return Double.valueOf(this.values.getPtArray(index).getV());
            }

            @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
            public String getDataRangeReference() {
                return null;
            }

            @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
            public int getColIndex() {
                return 0;
            }
        };
    }

    public static <T extends Number> XDDFNumericalDataSource<T> fromArray(T[] elements) {
        return new LiteralNumericalArrayDataSource(elements);
    }

    public static XDDFCategoryDataSource fromArray(String[] elements) {
        return new LiteralStringArrayDataSource(elements);
    }

    public static <T extends Number> XDDFNumericalDataSource<T> fromArray(T[] elements, String dataRange) {
        return new NumericalArrayDataSource(elements, dataRange);
    }

    public static XDDFCategoryDataSource fromArray(String[] elements, String dataRange) {
        return new StringArrayDataSource(elements, dataRange);
    }

    public static <T extends Number> XDDFNumericalDataSource<T> fromArray(T[] elements, String dataRange, int col) {
        return new NumericalArrayDataSource(elements, dataRange, col);
    }

    public static XDDFCategoryDataSource fromArray(String[] elements, String dataRange, int col) {
        return new StringArrayDataSource(elements, dataRange, col);
    }

    public static XDDFNumericalDataSource<Double> fromNumericCellRange(XSSFSheet sheet, CellRangeAddress cellRangeAddress) {
        return new NumericalCellRangeDataSource(sheet, cellRangeAddress);
    }

    public static XDDFCategoryDataSource fromStringCellRange(XSSFSheet sheet, CellRangeAddress cellRangeAddress) {
        return new StringCellRangeDataSource(sheet, cellRangeAddress);
    }

    /* loaded from: classes10.dex */
    private static abstract class AbstractArrayDataSource<T> implements XDDFDataSource<T> {
        private int col;
        private final String dataRange;
        private final T[] elements;

        public AbstractArrayDataSource(T[] tArr, String str) {
            this.col = 0;
            this.elements = (T[]) ((Object[]) tArr.clone());
            this.dataRange = str;
        }

        public AbstractArrayDataSource(T[] tArr, String str, int i) {
            this.col = 0;
            this.elements = (T[]) ((Object[]) tArr.clone());
            this.dataRange = str;
            this.col = i;
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public int getPointCount() {
            return this.elements.length;
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public T getPointAt(int index) {
            return this.elements[index];
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public boolean isCellRange() {
            return false;
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public boolean isReference() {
            return this.dataRange != null;
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public boolean isNumeric() {
            Class<?> arrayComponentType = this.elements.getClass().getComponentType();
            return Number.class.isAssignableFrom(arrayComponentType);
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public String getDataRangeReference() {
            if (this.dataRange == null) {
                throw new UnsupportedOperationException("Literal data source can not be expressed by reference.");
            }
            return this.dataRange;
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public int getColIndex() {
            return this.col;
        }
    }

    /* loaded from: classes10.dex */
    private static class NumericalArrayDataSource<T extends Number> extends AbstractArrayDataSource<T> implements XDDFNumericalDataSource<T> {
        private String formatCode;

        public NumericalArrayDataSource(T[] elements, String dataRange) {
            super(elements, dataRange);
        }

        public NumericalArrayDataSource(T[] elements, String dataRange, int col) {
            super(elements, dataRange, col);
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public String getFormatCode() {
            return this.formatCode;
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource
        public void setFormatCode(String formatCode) {
            this.formatCode = formatCode;
        }
    }

    /* loaded from: classes10.dex */
    private static class StringArrayDataSource extends AbstractArrayDataSource<String> implements XDDFCategoryDataSource {
        public StringArrayDataSource(String[] elements, String dataRange) {
            super(elements, dataRange);
        }

        public StringArrayDataSource(String[] elements, String dataRange, int col) {
            super(elements, dataRange, col);
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public String getFormatCode() {
            return null;
        }
    }

    /* loaded from: classes10.dex */
    private static class LiteralNumericalArrayDataSource<T extends Number> extends NumericalArrayDataSource<T> {
        public LiteralNumericalArrayDataSource(T[] elements) {
            super(elements, null, 0);
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public boolean isLiteral() {
            return true;
        }
    }

    /* loaded from: classes10.dex */
    private static class LiteralStringArrayDataSource extends StringArrayDataSource {
        public LiteralStringArrayDataSource(String[] elements) {
            super(elements, null, 0);
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public boolean isLiteral() {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static abstract class AbstractCellRangeDataSource<T> implements XDDFDataSource<T> {
        private final CellRangeAddress cellRangeAddress;
        private final XSSFFormulaEvaluator evaluator;
        private final int numOfCells;
        private final XSSFSheet sheet;

        protected AbstractCellRangeDataSource(XSSFSheet sheet, CellRangeAddress cellRangeAddress) {
            this.sheet = sheet;
            this.cellRangeAddress = cellRangeAddress.copy();
            this.numOfCells = this.cellRangeAddress.getNumberOfCells();
            this.evaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public int getPointCount() {
            return this.numOfCells;
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public boolean isCellRange() {
            return true;
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public boolean isReference() {
            return true;
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public int getColIndex() {
            return this.cellRangeAddress.getFirstColumn();
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public String getDataRangeReference() {
            return this.cellRangeAddress.formatAsString(this.sheet.getSheetName(), true);
        }

        protected CellValue getCellValueAt(int index) {
            if (index < 0 || index >= this.numOfCells) {
                throw new IndexOutOfBoundsException("Index must be between 0 and " + (this.numOfCells - 1) + " (inclusive), given: " + index);
            }
            int firstRow = this.cellRangeAddress.getFirstRow();
            int firstCol = this.cellRangeAddress.getFirstColumn();
            int lastCol = this.cellRangeAddress.getLastColumn();
            int width = (lastCol - firstCol) + 1;
            int rowIndex = (index / width) + firstRow;
            int cellIndex = (index % width) + firstCol;
            XSSFRow row = this.sheet.getRow(rowIndex);
            if (row == null) {
                return null;
            }
            return this.evaluator.evaluate(row.getCell(cellIndex));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class NumericalCellRangeDataSource extends AbstractCellRangeDataSource<Double> implements XDDFNumericalDataSource<Double> {
        private String formatCode;

        protected NumericalCellRangeDataSource(XSSFSheet sheet, CellRangeAddress cellRangeAddress) {
            super(sheet, cellRangeAddress);
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public String getFormatCode() {
            return this.formatCode;
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource
        public void setFormatCode(String formatCode) {
            this.formatCode = formatCode;
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public Double getPointAt(int index) {
            CellValue cellValue = getCellValueAt(index);
            if (cellValue != null && cellValue.getCellType() == CellType.NUMERIC) {
                return Double.valueOf(cellValue.getNumberValue());
            }
            return null;
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public boolean isNumeric() {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class StringCellRangeDataSource extends AbstractCellRangeDataSource<String> implements XDDFCategoryDataSource {
        protected StringCellRangeDataSource(XSSFSheet sheet, CellRangeAddress cellRangeAddress) {
            super(sheet, cellRangeAddress);
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public String getPointAt(int index) {
            CellValue cellValue = getCellValueAt(index);
            if (cellValue != null && cellValue.getCellType() == CellType.STRING) {
                return cellValue.getStringValue();
            }
            return null;
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public boolean isNumeric() {
            return false;
        }

        @Override // org.apache.poi.xddf.usermodel.chart.XDDFDataSource
        public String getFormatCode() {
            return null;
        }
    }
}
