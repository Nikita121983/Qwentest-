package org.apache.poi.xssf.streaming;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.openxml4j.util.ZipArchiveThresholdInputStream;
import org.apache.poi.openxml4j.util.ZipEntrySource;
import org.apache.poi.openxml4j.util.ZipFileZipEntrySource;
import org.apache.poi.openxml4j.util.ZipInputStreamZipEntrySource;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.CellReferenceType;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetVisibility;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.util.Removal;
import org.apache.poi.util.TempFile;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFChartSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/* loaded from: classes10.dex */
public class SXSSFWorkbook implements Workbook {
    public static final int DEFAULT_WINDOW_SIZE = 100;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) SXSSFWorkbook.class);
    private boolean _compressTmpFiles;
    private int _randomAccessWindowSize;
    protected final SharedStringsTable _sharedStringSource;
    private final Map<SXSSFSheet, XSSFSheet> _sxFromXHash;
    protected final XSSFWorkbook _wb;
    private final Map<XSSFSheet, SXSSFSheet> _xFromSxHash;
    private boolean shouldCalculateSheetDimensions;
    protected Zip64Mode zip64Mode;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes10.dex */
    public interface ISheetInjector {
        void writeSheetData(OutputStream outputStream) throws IOException;
    }

    public SXSSFWorkbook() {
        this((XSSFWorkbook) null);
    }

    public SXSSFWorkbook(XSSFWorkbook workbook) {
        this(workbook, 100);
    }

    public SXSSFWorkbook(XSSFWorkbook workbook, int rowAccessWindowSize) {
        this(workbook, rowAccessWindowSize, false);
    }

    public SXSSFWorkbook(XSSFWorkbook workbook, int rowAccessWindowSize, boolean compressTmpFiles) {
        this(workbook, rowAccessWindowSize, compressTmpFiles, false);
    }

    public SXSSFWorkbook(XSSFWorkbook workbook, int rowAccessWindowSize, boolean compressTmpFiles, boolean useSharedStringsTable) {
        this._sxFromXHash = new HashMap();
        this._xFromSxHash = new HashMap();
        this._randomAccessWindowSize = 100;
        this.zip64Mode = Zip64Mode.Always;
        this.shouldCalculateSheetDimensions = true;
        setRandomAccessWindowSize(rowAccessWindowSize);
        setCompressTempFiles(compressTmpFiles);
        if (workbook == null) {
            this._wb = new XSSFWorkbook();
            this._sharedStringSource = useSharedStringsTable ? this._wb.getSharedStringSource() : null;
            return;
        }
        this._wb = workbook;
        this._sharedStringSource = useSharedStringsTable ? this._wb.getSharedStringSource() : null;
        Iterator<Sheet> it = this._wb.iterator();
        while (it.hasNext()) {
            Sheet sheet = it.next();
            createAndRegisterSXSSFSheet((XSSFSheet) sheet);
        }
    }

    public SXSSFWorkbook(int rowAccessWindowSize) {
        this(null, rowAccessWindowSize);
    }

    public int getRandomAccessWindowSize() {
        return this._randomAccessWindowSize;
    }

    protected void setRandomAccessWindowSize(int rowAccessWindowSize) {
        if (rowAccessWindowSize == 0 || rowAccessWindowSize < -1) {
            throw new IllegalArgumentException("rowAccessWindowSize must be greater than 0 or -1");
        }
        this._randomAccessWindowSize = rowAccessWindowSize;
    }

    public void setZip64Mode(Zip64Mode zip64Mode) {
        this.zip64Mode = zip64Mode;
    }

    public boolean isCompressTempFiles() {
        return this._compressTmpFiles;
    }

    public void setCompressTempFiles(boolean compress) {
        this._compressTmpFiles = compress;
    }

    public void setShouldCalculateSheetDimensions(boolean shouldCalculateSheetDimensions) {
        this.shouldCalculateSheetDimensions = shouldCalculateSheetDimensions;
    }

    public boolean shouldCalculateSheetDimensions() {
        return this.shouldCalculateSheetDimensions;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public SharedStringsTable getSharedStringSource() {
        return this._sharedStringSource;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SheetDataWriter createSheetDataWriter() throws IOException {
        if (this._compressTmpFiles) {
            return new GZIPSheetDataWriter(this._sharedStringSource);
        }
        return new SheetDataWriter(this._sharedStringSource);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSSFSheet getXSSFSheet(SXSSFSheet sheet) {
        return this._sxFromXHash.get(sheet);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SXSSFSheet getSXSSFSheet(XSSFSheet sheet) {
        return this._xFromSxHash.get(sheet);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerSheetMapping(SXSSFSheet sxSheet, XSSFSheet xSheet) {
        this._sxFromXHash.put(sxSheet, xSheet);
        this._xFromSxHash.put(xSheet, sxSheet);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void deregisterSheetMapping(XSSFSheet xSheet) {
        SXSSFSheet sxSheet = getSXSSFSheet(xSheet);
        if (sxSheet != null) {
            IOUtils.closeQuietly(sxSheet.getSheetDataWriter());
            this._sxFromXHash.remove(sxSheet);
            this._xFromSxHash.remove(xSheet);
        }
    }

    protected XSSFSheet getSheetFromZipEntryName(String sheetRef) {
        for (XSSFSheet sheet : this._sxFromXHash.values()) {
            if (sheetRef.equals(sheet.getPackagePart().getPartName().getName().substring(1))) {
                return sheet;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void injectData(ZipEntrySource zipEntrySource, OutputStream out) throws IOException {
        ZipArchiveOutputStream zos = createArchiveOutputStream(out);
        try {
            Enumeration<? extends ZipArchiveEntry> en = zipEntrySource.getEntries();
            while (en.hasMoreElements()) {
                ZipArchiveEntry ze = en.nextElement();
                ZipArchiveEntry zeOut = new ZipArchiveEntry(ze.getName());
                if (ze.getSize() >= 0) {
                    zeOut.setSize(ze.getSize());
                }
                if (ze.getTime() >= 0) {
                    zeOut.setTime(ze.getTime());
                }
                zos.putArchiveEntry(zeOut);
                try {
                    InputStream is = zipEntrySource.getInputStream(ze);
                    try {
                        if (is instanceof ZipArchiveThresholdInputStream) {
                            ((ZipArchiveThresholdInputStream) is).setGuardState(false);
                        }
                        XSSFSheet xSheet = getSheetFromZipEntryName(ze.getName());
                        if (xSheet != null && !(xSheet instanceof XSSFChartSheet)) {
                            SXSSFSheet sxSheet = getSXSSFSheet(xSheet);
                            copyStreamAndInjectWorksheet(is, zos, createSheetInjector(sxSheet));
                        } else {
                            IOUtils.copy(is, zos);
                        }
                        if (is != null) {
                            is.close();
                        }
                    } finally {
                    }
                } finally {
                    zos.closeArchiveEntry();
                }
            }
        } finally {
            zos.finish();
            zipEntrySource.close();
        }
    }

    protected ZipArchiveOutputStream createArchiveOutputStream(OutputStream out) {
        if (Zip64Mode.Always.equals(this.zip64Mode)) {
            return new OpcZipArchiveOutputStream(out);
        }
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(out);
        zos.setUseZip64(this.zip64Mode);
        return zos;
    }

    protected ISheetInjector createSheetInjector(final SXSSFSheet sxSheet) throws IOException {
        return new ISheetInjector() { // from class: org.apache.poi.xssf.streaming.SXSSFWorkbook$$ExternalSyntheticLambda0
            @Override // org.apache.poi.xssf.streaming.SXSSFWorkbook.ISheetInjector
            public final void writeSheetData(OutputStream outputStream) {
                SXSSFWorkbook.lambda$createSheetInjector$0(SXSSFSheet.this, outputStream);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$createSheetInjector$0(SXSSFSheet sxSheet, OutputStream output) throws IOException {
        InputStream xis = sxSheet.getWorksheetXMLInputStream();
        try {
            IOUtils.copy(xis, output);
            if (xis != null) {
                xis.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (xis != null) {
                    try {
                        xis.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private static void copyStreamAndInjectWorksheet(InputStream in, OutputStream out, ISheetInjector sheetInjector) throws IOException {
        Reader inReader = new InputStreamReader(in, StandardCharsets.UTF_8);
        Writer outWriter = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        boolean needsStartTag = true;
        int pos = 0;
        String s = "<sheetData";
        int n = "<sheetData".length();
        while (true) {
            int c = inReader.read();
            if (c == -1) {
                break;
            }
            if (c == s.charAt(pos)) {
                pos++;
                if (pos != n) {
                    continue;
                } else {
                    if (!"<sheetData".equals(s)) {
                        break;
                    }
                    int c2 = inReader.read();
                    if (c2 == -1) {
                        outWriter.write(s);
                        break;
                    }
                    if (c2 == 62) {
                        outWriter.write(s);
                        outWriter.write(c2);
                        s = "</sheetData>";
                        n = "</sheetData>".length();
                        pos = 0;
                        needsStartTag = false;
                    } else if (c2 == 47) {
                        int c3 = inReader.read();
                        if (c3 == -1) {
                            outWriter.write(s);
                            break;
                        } else {
                            if (c3 == 62) {
                                break;
                            }
                            outWriter.write(s);
                            outWriter.write(47);
                            outWriter.write(c3);
                            pos = 0;
                        }
                    } else {
                        outWriter.write(s);
                        outWriter.write(47);
                        outWriter.write(c2);
                        pos = 0;
                    }
                }
            } else {
                if (pos > 0) {
                    outWriter.write(s, 0, pos);
                }
                if (c == s.charAt(0)) {
                    pos = 1;
                } else {
                    outWriter.write(c);
                    pos = 0;
                }
            }
        }
        outWriter.flush();
        if (needsStartTag) {
            outWriter.write("<sheetData>\n");
            outWriter.flush();
        }
        sheetInjector.writeSheetData(out);
        outWriter.write("</sheetData>");
        outWriter.flush();
        while (true) {
            int c4 = inReader.read();
            if (c4 != -1) {
                outWriter.write(c4);
            } else {
                outWriter.flush();
                return;
            }
        }
    }

    public XSSFWorkbook getXSSFWorkbook() {
        return this._wb;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int getActiveSheetIndex() {
        return this._wb.getActiveSheetIndex();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setActiveSheet(int sheetIndex) {
        this._wb.setActiveSheet(sheetIndex);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int getFirstVisibleTab() {
        return this._wb.getFirstVisibleTab();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setFirstVisibleTab(int sheetIndex) {
        this._wb.setFirstVisibleTab(sheetIndex);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setSheetOrder(String sheetname, int pos) {
        this._wb.setSheetOrder(sheetname, pos);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setSelectedTab(int index) {
        this._wb.setSelectedTab(index);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setSheetName(int sheet, String name) {
        this._wb.setSheetName(sheet, name);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public String getSheetName(int sheet) {
        return this._wb.getSheetName(sheet);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int getSheetIndex(String name) {
        return this._wb.getSheetIndex(name);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int getSheetIndex(Sheet sheet) {
        return this._wb.getSheetIndex(getXSSFSheet((SXSSFSheet) sheet));
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public SXSSFSheet createSheet() {
        return createAndRegisterSXSSFSheet(this._wb.createSheet());
    }

    SXSSFSheet createAndRegisterSXSSFSheet(XSSFSheet xSheet) {
        try {
            SXSSFSheet sxSheet = new SXSSFSheet(this, xSheet);
            registerSheetMapping(sxSheet, xSheet);
            return sxSheet;
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public SXSSFSheet createSheet(String sheetname) {
        return createAndRegisterSXSSFSheet(this._wb.createSheet(sheetname));
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    @NotImplemented
    public Sheet cloneSheet(int sheetNum) {
        throw new IllegalStateException("Not Implemented");
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int getNumberOfSheets() {
        return this._wb.getNumberOfSheets();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public Iterator<Sheet> sheetIterator() {
        return new SheetIterator();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook, java.lang.Iterable
    public Spliterator<Sheet> spliterator() {
        return this._wb.spliterator();
    }

    /* loaded from: classes10.dex */
    protected final class SheetIterator<T extends Sheet> implements Iterator<T> {
        private final Iterator<XSSFSheet> it;

        public SheetIterator() {
            this.it = SXSSFWorkbook.this._wb.iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.it.hasNext();
        }

        @Override // java.util.Iterator
        public T next() throws NoSuchElementException {
            XSSFSheet xssfSheet = this.it.next();
            return SXSSFWorkbook.this.getSXSSFSheet(xssfSheet);
        }

        @Override // java.util.Iterator
        public void remove() throws IllegalStateException {
            throw new UnsupportedOperationException("remove method not supported on XSSFWorkbook.iterator(). Use Sheet.removeSheetAt(int) instead.");
        }
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public SXSSFSheet getSheetAt(int index) {
        return getSXSSFSheet(this._wb.getSheetAt(index));
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public SXSSFSheet getSheet(String name) {
        return getSXSSFSheet(this._wb.getSheet(name));
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void removeSheetAt(int index) {
        XSSFSheet xSheet = this._wb.getSheetAt(index);
        SXSSFSheet sxSheet = getSXSSFSheet(xSheet);
        this._wb.removeSheetAt(index);
        deregisterSheetMapping(xSheet);
        try {
            sxSheet.dispose();
        } catch (IOException e) {
            LOG.atWarn().withThrowable(e).log("Failed to dispose old sheet");
        }
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public Font createFont() {
        return this._wb.createFont();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public Font findFont(boolean bold, short color, short fontHeight, String name, boolean italic, boolean strikeout, short typeOffset, byte underline) {
        return this._wb.findFont(bold, color, fontHeight, name, italic, strikeout, typeOffset, underline);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int getNumberOfFonts() {
        return this._wb.getNumberOfFonts();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    @Removal(version = "6.0.0")
    @Deprecated
    public int getNumberOfFontsAsInt() {
        return getNumberOfFonts();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public Font getFontAt(int idx) {
        return this._wb.getFontAt(idx);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public CellStyle createCellStyle() {
        return this._wb.createCellStyle();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int getNumCellStyles() {
        return this._wb.getNumCellStyles();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public CellStyle getCellStyleAt(int idx) {
        return this._wb.getCellStyleAt(idx);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        for (SXSSFSheet sheet : this._xFromSxHash.values()) {
            try {
                SheetDataWriter _writer = sheet.getSheetDataWriter();
                if (_writer != null) {
                    _writer.close();
                }
            } catch (IOException e) {
                LOG.atWarn().withThrowable(e).log("An exception occurred while closing sheet data writer for sheet {}.", sheet.getSheetName());
            }
        }
        dispose();
        this._wb.close();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void write(OutputStream stream) throws IOException {
        boolean deleted;
        flushSheets();
        File tmplFile = TempFile.createTempFile("poi-sxssf-template", ".xlsx");
        try {
            OutputStream os = Files.newOutputStream(tmplFile.toPath(), new OpenOption[0]);
            try {
                this._wb.write(os);
                if (os != null) {
                    os.close();
                }
                ZipSecureFile zf = new ZipSecureFile(tmplFile);
                try {
                    ZipFileZipEntrySource source = new ZipFileZipEntrySource(zf);
                    try {
                        injectData(source, stream);
                        source.close();
                        zf.close();
                        if (!deleted) {
                            throw new IOException("Could not delete temporary file after processing: " + tmplFile);
                        }
                    } finally {
                    }
                } finally {
                }
            } finally {
            }
        } finally {
            tmplFile.delete();
        }
    }

    public void writeAvoidingTempFiles(OutputStream stream) throws IOException {
        flushSheets();
        UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().get();
        try {
            this._wb.write(bos);
            InputStream is = bos.toInputStream();
            try {
                ZipArchiveInputStream zis = new ZipArchiveInputStream(is);
                try {
                    ZipInputStreamZipEntrySource source = new ZipInputStreamZipEntrySource(new ZipArchiveThresholdInputStream(zis));
                    try {
                        injectData(source, stream);
                        source.close();
                        zis.close();
                        if (is != null) {
                            is.close();
                        }
                        if (bos != null) {
                            bos.close();
                        }
                    } finally {
                    }
                } finally {
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void flushSheets() throws IOException {
        for (SXSSFSheet sheet : this._xFromSxHash.values()) {
            sheet.deriveDimension();
            sheet.flushRows();
        }
    }

    @Deprecated
    public boolean dispose() {
        boolean success = true;
        for (SXSSFSheet sheet : this._sxFromXHash.keySet()) {
            try {
                boolean success2 = sheet.dispose() && success;
                success = success2;
            } catch (IOException e) {
                LOG.atWarn().withThrowable(e).log("Failed to dispose sheet");
                success = false;
            }
        }
        return success;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int getNumberOfNames() {
        return this._wb.getNumberOfNames();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public Name getName(String name) {
        return this._wb.getName(name);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public List<? extends Name> getNames(String name) {
        return this._wb.getNames(name);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public List<? extends Name> getAllNames() {
        return this._wb.getAllNames();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public Name createName() {
        return this._wb.createName();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void removeName(Name name) {
        this._wb.removeName(name);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setPrintArea(int sheetIndex, String reference) {
        this._wb.setPrintArea(sheetIndex, reference);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setPrintArea(int sheetIndex, int startColumn, int endColumn, int startRow, int endRow) {
        this._wb.setPrintArea(sheetIndex, startColumn, endColumn, startRow, endRow);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public String getPrintArea(int sheetIndex) {
        return this._wb.getPrintArea(sheetIndex);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void removePrintArea(int sheetIndex) {
        this._wb.removePrintArea(sheetIndex);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public Row.MissingCellPolicy getMissingCellPolicy() {
        return this._wb.getMissingCellPolicy();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setMissingCellPolicy(Row.MissingCellPolicy missingCellPolicy) {
        this._wb.setMissingCellPolicy(missingCellPolicy);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public DataFormat createDataFormat() {
        return this._wb.createDataFormat();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int addPicture(byte[] pictureData, int format) {
        return this._wb.addPicture(pictureData, format);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public List<? extends PictureData> getAllPictures() {
        return this._wb.getAllPictures();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public CreationHelper getCreationHelper() {
        return new SXSSFCreationHelper(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isDate1904() {
        return this._wb.isDate1904();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    @NotImplemented("XSSFWorkbook#isHidden is not implemented")
    public boolean isHidden() {
        return this._wb.isHidden();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    @NotImplemented("XSSFWorkbook#setHidden is not implemented")
    public void setHidden(boolean hiddenFlag) {
        this._wb.setHidden(hiddenFlag);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public boolean isSheetHidden(int sheetIx) {
        return this._wb.isSheetHidden(sheetIx);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public boolean isSheetVeryHidden(int sheetIx) {
        return this._wb.isSheetVeryHidden(sheetIx);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public SheetVisibility getSheetVisibility(int sheetIx) {
        return this._wb.getSheetVisibility(sheetIx);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setSheetHidden(int sheetIx, boolean hidden) {
        this._wb.setSheetHidden(sheetIx, hidden);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setSheetVisibility(int sheetIx, SheetVisibility visibility) {
        this._wb.setSheetVisibility(sheetIx, visibility);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    @NotImplemented
    public int linkExternalWorkbook(String name, Workbook workbook) {
        throw new IllegalStateException("Not Implemented");
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void addToolPack(UDFFinder toolpack) {
        this._wb.addToolPack(toolpack);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setForceFormulaRecalculation(boolean value) {
        this._wb.setForceFormulaRecalculation(value);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public boolean getForceFormulaRecalculation() {
        return this._wb.getForceFormulaRecalculation();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL2007;
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public int addOlePackage(byte[] oleData, String label, String fileName, String command) throws IOException {
        return this._wb.addOlePackage(oleData, label, fileName, command);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public EvaluationWorkbook createEvaluationWorkbook() {
        return SXSSFEvaluationWorkbook.create(this);
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public CellReferenceType getCellReferenceType() {
        return getXSSFWorkbook().getCellReferenceType();
    }

    @Override // org.apache.poi.ss.usermodel.Workbook
    public void setCellReferenceType(CellReferenceType cellReferenceType) {
        getXSSFWorkbook().setCellReferenceType(cellReferenceType);
    }
}
