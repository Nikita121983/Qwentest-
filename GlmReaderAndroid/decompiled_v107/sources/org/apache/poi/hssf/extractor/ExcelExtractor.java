package org.apache.poi.hssf.extractor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.extractor.POIOLE2TextExtractor;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.Row;

/* loaded from: classes10.dex */
public class ExcelExtractor implements POIOLE2TextExtractor, org.apache.poi.ss.extractor.ExcelExtractor {
    private final HSSFDataFormatter _formatter;
    private boolean _includeBlankCells;
    private boolean _includeCellComments;
    private boolean _includeHeadersFooters;
    private boolean _includeSheetNames;
    private boolean _shouldEvaluateFormulas;
    private final HSSFWorkbook _wb;
    private boolean doCloseFilesystem;

    public ExcelExtractor(HSSFWorkbook wb) {
        this.doCloseFilesystem = true;
        this._includeSheetNames = true;
        this._shouldEvaluateFormulas = true;
        this._includeHeadersFooters = true;
        this._wb = wb;
        this._formatter = new HSSFDataFormatter();
    }

    public ExcelExtractor(POIFSFileSystem fs) throws IOException {
        this(fs.getRoot());
    }

    public ExcelExtractor(DirectoryNode dir) throws IOException {
        this(new HSSFWorkbook(dir, true));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class CommandParseException extends Exception {
        public CommandParseException(String msg) {
            super(msg);
        }
    }

    /* loaded from: classes10.dex */
    private static final class CommandArgs {
        private final boolean _evaluateFormulas;
        private final boolean _headersFooters;
        private final File _inputFile;
        private final boolean _requestHelp;
        private final boolean _showBlankCells;
        private final boolean _showCellComments;
        private final boolean _showSheetNames;

        /* JADX WARN: Code restructure failed: missing block: B:56:0x00f9, code lost:
        
            r13._requestHelp = r2;
            r13._inputFile = r1;
            r13._showSheetNames = r3;
            r13._evaluateFormulas = r4;
            r13._showCellComments = r5;
            r13._showBlankCells = r6;
            r13._headersFooters = r7;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x0107, code lost:
        
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public CommandArgs(java.lang.String[] r14) throws org.apache.poi.hssf.extractor.ExcelExtractor.CommandParseException {
            /*
                Method dump skipped, instructions count: 264
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.extractor.ExcelExtractor.CommandArgs.<init>(java.lang.String[]):void");
        }

        private static boolean parseBoolArg(String[] args, int i) throws CommandParseException {
            if (i >= args.length) {
                throw new CommandParseException("Expected value after '" + args[i - 1] + "'");
            }
            String value = args[i].toUpperCase(Locale.ROOT);
            if ("Y".equals(value) || "YES".equals(value) || "ON".equals(value) || "TRUE".equals(value)) {
                return true;
            }
            if ("N".equals(value) || "NO".equals(value) || "OFF".equals(value) || "FALSE".equals(value)) {
                return false;
            }
            throw new CommandParseException("Invalid value '" + args[i] + "' for '" + args[i - 1] + "'. Expected 'Y' or 'N'");
        }

        public boolean isRequestHelp() {
            return this._requestHelp;
        }

        public File getInputFile() {
            return this._inputFile;
        }

        public boolean shouldShowSheetNames() {
            return this._showSheetNames;
        }

        public boolean shouldEvaluateFormulas() {
            return this._evaluateFormulas;
        }

        public boolean shouldShowCellComments() {
            return this._showCellComments;
        }

        public boolean shouldShowBlankCells() {
            return this._showBlankCells;
        }

        public boolean shouldIncludeHeadersFooters() {
            return this._headersFooters;
        }
    }

    private static void printUsageMessage(PrintStream ps) {
        ps.println("Use:");
        ps.println("    " + ExcelExtractor.class.getName() + " [<flag> <value> [<flag> <value> [...]]] [-i <filename.xls>]");
        ps.println("       -i <filename.xls> specifies input file (default is to use stdin)");
        ps.println("       Flags can be set on or off by using the values 'Y' or 'N'.");
        ps.println("       Following are available flags and their default values:");
        ps.println("       --show-sheet-names  Y");
        ps.println("       --evaluate-formulas Y");
        ps.println("       --show-comments     N");
        ps.println("       --show-blanks       Y");
        ps.println("       --headers-footers   Y");
    }

    public static void main(String[] args) throws IOException {
        boolean z = true;
        try {
            CommandArgs cmdArgs = new CommandArgs(args);
            if (cmdArgs.isRequestHelp()) {
                printUsageMessage(System.out);
                return;
            }
            InputStream is = cmdArgs.getInputFile() == null ? System.in : Files.newInputStream(cmdArgs.getInputFile().toPath(), new OpenOption[0]);
            try {
                HSSFWorkbook wb = new HSSFWorkbook(is);
                try {
                    ExcelExtractor extractor = new ExcelExtractor(wb);
                    try {
                        extractor.setIncludeSheetNames(cmdArgs.shouldShowSheetNames());
                        if (cmdArgs.shouldEvaluateFormulas()) {
                            z = false;
                        }
                        extractor.setFormulasNotResults(z);
                        extractor.setIncludeCellComments(cmdArgs.shouldShowCellComments());
                        extractor.setIncludeBlankCells(cmdArgs.shouldShowBlankCells());
                        extractor.setIncludeHeadersFooters(cmdArgs.shouldIncludeHeadersFooters());
                        System.out.println(extractor.getText());
                        extractor.close();
                        wb.close();
                        if (is != null) {
                            is.close();
                        }
                    } finally {
                    }
                } finally {
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        } catch (CommandParseException e) {
            System.err.println(e.getMessage());
            printUsageMessage(System.err);
            System.exit(1);
        }
    }

    @Override // org.apache.poi.ss.extractor.ExcelExtractor
    public void setIncludeSheetNames(boolean includeSheetNames) {
        this._includeSheetNames = includeSheetNames;
    }

    @Override // org.apache.poi.ss.extractor.ExcelExtractor
    public void setFormulasNotResults(boolean formulasNotResults) {
        this._shouldEvaluateFormulas = !formulasNotResults;
    }

    @Override // org.apache.poi.ss.extractor.ExcelExtractor
    public void setIncludeCellComments(boolean includeCellComments) {
        this._includeCellComments = includeCellComments;
    }

    public void setIncludeBlankCells(boolean includeBlankCells) {
        this._includeBlankCells = includeBlankCells;
    }

    @Override // org.apache.poi.ss.extractor.ExcelExtractor
    public void setIncludeHeadersFooters(boolean includeHeadersFooters) {
        this._includeHeadersFooters = includeHeadersFooters;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public String getText() {
        int i;
        int i2;
        HSSFSheet sheet;
        int firstRow;
        int lastRow;
        int j;
        int i3;
        HSSFSheet sheet2;
        int firstRow2;
        int lastRow2;
        int j2;
        String name;
        StringBuilder text = new StringBuilder();
        this._wb.setMissingCellPolicy(Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        int i4 = 0;
        while (i4 < this._wb.getNumberOfSheets()) {
            HSSFSheet sheet3 = this._wb.getSheetAt(i4);
            if (sheet3 == null) {
                i = i4;
            } else {
                if (this._includeSheetNames && (name = this._wb.getSheetName(i4)) != null) {
                    text.append(name);
                    text.append(StringUtils.LF);
                }
                if (this._includeHeadersFooters) {
                    text.append(_extractHeaderFooter(sheet3.getHeader()));
                }
                int firstRow3 = sheet3.getFirstRowNum();
                int lastRow3 = sheet3.getLastRowNum();
                int j3 = firstRow3;
                while (j3 <= lastRow3) {
                    HSSFRow row = sheet3.getRow(j3);
                    if (row == null) {
                        i2 = i4;
                        sheet = sheet3;
                        firstRow = firstRow3;
                        lastRow = lastRow3;
                        j = j3;
                    } else {
                        int firstCell = row.getFirstCellNum();
                        int lastCell = row.getLastCellNum();
                        if (this._includeBlankCells) {
                            firstCell = 0;
                        }
                        int k = firstCell;
                        while (k < lastCell) {
                            HSSFCell cell = row.getCell(k);
                            boolean outputContents = true;
                            if (cell == null) {
                                outputContents = this._includeBlankCells;
                                i3 = i4;
                                sheet2 = sheet3;
                                firstRow2 = firstRow3;
                                lastRow2 = lastRow3;
                                j2 = j3;
                            } else {
                                switch (cell.getCellType()) {
                                    case STRING:
                                        i3 = i4;
                                        sheet2 = sheet3;
                                        firstRow2 = firstRow3;
                                        lastRow2 = lastRow3;
                                        j2 = j3;
                                        text.append(cell.getRichStringCellValue().getString());
                                        break;
                                    case NUMERIC:
                                        i3 = i4;
                                        sheet2 = sheet3;
                                        firstRow2 = firstRow3;
                                        lastRow2 = lastRow3;
                                        j2 = j3;
                                        text.append(this._formatter.formatCellValue(cell));
                                        break;
                                    case BOOLEAN:
                                        i3 = i4;
                                        sheet2 = sheet3;
                                        firstRow2 = firstRow3;
                                        lastRow2 = lastRow3;
                                        j2 = j3;
                                        text.append(cell.getBooleanCellValue());
                                        break;
                                    case ERROR:
                                        i3 = i4;
                                        sheet2 = sheet3;
                                        firstRow2 = firstRow3;
                                        lastRow2 = lastRow3;
                                        j2 = j3;
                                        int i5 = cell.getErrorCellValue();
                                        text.append(ErrorEval.getText(i5));
                                        break;
                                    case FORMULA:
                                        if (!this._shouldEvaluateFormulas) {
                                            text.append(cell.getCellFormula());
                                            i3 = i4;
                                            sheet2 = sheet3;
                                            firstRow2 = firstRow3;
                                            lastRow2 = lastRow3;
                                            j2 = j3;
                                            break;
                                        } else {
                                            switch (cell.getCachedFormulaResultType()) {
                                                case STRING:
                                                    i3 = i4;
                                                    sheet2 = sheet3;
                                                    firstRow2 = firstRow3;
                                                    lastRow2 = lastRow3;
                                                    j2 = j3;
                                                    HSSFRichTextString str = cell.getRichStringCellValue();
                                                    if (str != null && str.length() > 0) {
                                                        text.append(str);
                                                        break;
                                                    }
                                                    break;
                                                case NUMERIC:
                                                    HSSFCellStyle style = cell.getCellStyle();
                                                    i3 = i4;
                                                    sheet2 = sheet3;
                                                    double nVal = cell.getNumericCellValue();
                                                    firstRow2 = firstRow3;
                                                    short df = style.getDataFormat();
                                                    lastRow2 = lastRow3;
                                                    String dfs = style.getDataFormatString();
                                                    j2 = j3;
                                                    text.append(this._formatter.formatRawCellContents(nVal, df, dfs));
                                                    break;
                                                case BOOLEAN:
                                                    text.append(cell.getBooleanCellValue());
                                                    i3 = i4;
                                                    sheet2 = sheet3;
                                                    firstRow2 = firstRow3;
                                                    lastRow2 = lastRow3;
                                                    j2 = j3;
                                                    break;
                                                case ERROR:
                                                    text.append(ErrorEval.getText(cell.getErrorCellValue()));
                                                    i3 = i4;
                                                    sheet2 = sheet3;
                                                    firstRow2 = firstRow3;
                                                    lastRow2 = lastRow3;
                                                    j2 = j3;
                                                    break;
                                                default:
                                                    throw new IllegalStateException("Unexpected cell cached formula result type: " + cell.getCachedFormulaResultType());
                                            }
                                        }
                                    default:
                                        throw new IllegalStateException("Unexpected cell type (" + cell.getCellType() + ")");
                                }
                                HSSFComment comment = cell.getCellComment();
                                if (this._includeCellComments && comment != null) {
                                    String commentText = comment.getString().getString().replace('\n', Chars.SPACE);
                                    text.append(" Comment by ").append(comment.getAuthor()).append(": ").append(commentText);
                                }
                            }
                            if (outputContents && k < lastCell - 1) {
                                text.append("\t");
                            }
                            k++;
                            i4 = i3;
                            sheet3 = sheet2;
                            firstRow3 = firstRow2;
                            lastRow3 = lastRow2;
                            j3 = j2;
                        }
                        i2 = i4;
                        sheet = sheet3;
                        firstRow = firstRow3;
                        lastRow = lastRow3;
                        j = j3;
                        text.append(StringUtils.LF);
                    }
                    j3 = j + 1;
                    i4 = i2;
                    sheet3 = sheet;
                    firstRow3 = firstRow;
                    lastRow3 = lastRow;
                }
                i = i4;
                HSSFSheet sheet4 = sheet3;
                if (this._includeHeadersFooters) {
                    text.append(_extractHeaderFooter(sheet4.getFooter()));
                }
            }
            i4 = i + 1;
        }
        return text.toString();
    }

    public static String _extractHeaderFooter(HeaderFooter hf) {
        StringBuilder text = new StringBuilder();
        if (hf.getLeft() != null) {
            text.append(hf.getLeft());
        }
        if (hf.getCenter() != null) {
            if (text.length() > 0) {
                text.append("\t");
            }
            text.append(hf.getCenter());
        }
        if (hf.getRight() != null) {
            if (text.length() > 0) {
                text.append("\t");
            }
            text.append(hf.getRight());
        }
        if (text.length() > 0) {
            text.append(StringUtils.LF);
        }
        return text.toString();
    }

    @Override // org.apache.poi.extractor.POIOLE2TextExtractor, org.apache.poi.extractor.POITextExtractor
    public HSSFWorkbook getDocument() {
        return this._wb;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public void setCloseFilesystem(boolean doCloseFilesystem) {
        this.doCloseFilesystem = doCloseFilesystem;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public boolean isCloseFilesystem() {
        return this.doCloseFilesystem;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public HSSFWorkbook getFilesystem() {
        return this._wb;
    }
}
