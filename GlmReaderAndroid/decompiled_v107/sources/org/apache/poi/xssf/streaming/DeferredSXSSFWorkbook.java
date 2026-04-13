package org.apache.poi.xssf.streaming;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/* loaded from: classes10.dex */
public class DeferredSXSSFWorkbook extends SXSSFWorkbook {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) DeferredSXSSFWorkbook.class);

    public DeferredSXSSFWorkbook() {
        this((XSSFWorkbook) null);
    }

    public DeferredSXSSFWorkbook(int rowAccessWindowSize) {
        this(null, rowAccessWindowSize);
    }

    public DeferredSXSSFWorkbook(XSSFWorkbook workbook) {
        this(workbook, 100);
    }

    public DeferredSXSSFWorkbook(XSSFWorkbook workbook, int rowAccessWindowSize) {
        super(workbook, rowAccessWindowSize, false, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.xssf.streaming.SXSSFWorkbook
    @NotImplemented
    public SheetDataWriter createSheetDataWriter() {
        throw new IllegalStateException("Not supported by DeferredSXSSFWorkbook");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StreamingSheetWriter createSheetDataWriter(OutputStream out) throws IOException {
        return new StreamingSheetWriter(out);
    }

    @Override // org.apache.poi.xssf.streaming.SXSSFWorkbook
    protected SXSSFWorkbook.ISheetInjector createSheetInjector(SXSSFSheet sxSheet) {
        final DeferredSXSSFSheet ssxSheet = (DeferredSXSSFSheet) sxSheet;
        ssxSheet.getClass();
        return new SXSSFWorkbook.ISheetInjector() { // from class: org.apache.poi.xssf.streaming.DeferredSXSSFWorkbook$$ExternalSyntheticLambda0
            @Override // org.apache.poi.xssf.streaming.SXSSFWorkbook.ISheetInjector
            public final void writeSheetData(OutputStream outputStream) {
                DeferredSXSSFSheet.this.writeRows(outputStream);
            }
        };
    }

    @Override // org.apache.poi.xssf.streaming.SXSSFWorkbook
    SXSSFSheet createAndRegisterSXSSFSheet(XSSFSheet xSheet) {
        try {
            DeferredSXSSFSheet sxSheet = new DeferredSXSSFSheet(this, xSheet);
            registerSheetMapping(sxSheet, xSheet);
            return sxSheet;
        } catch (IOException ioe) {
            throw new IllegalStateException(ioe);
        }
    }

    @Override // org.apache.poi.xssf.streaming.SXSSFWorkbook, org.apache.poi.ss.usermodel.Workbook
    public DeferredSXSSFSheet createSheet() {
        return (DeferredSXSSFSheet) super.createSheet();
    }

    @Override // org.apache.poi.xssf.streaming.SXSSFWorkbook, org.apache.poi.ss.usermodel.Workbook
    public DeferredSXSSFSheet createSheet(String sheetname) {
        return (DeferredSXSSFSheet) super.createSheet(sheetname);
    }

    @Override // org.apache.poi.xssf.streaming.SXSSFWorkbook, org.apache.poi.ss.usermodel.Workbook
    public Iterator<Sheet> sheetIterator() {
        return new SXSSFWorkbook.SheetIterator();
    }

    public DeferredSXSSFSheet getStreamingSheetAt(int index) {
        XSSFSheet xSheet = this._wb.getSheetAt(index);
        SXSSFSheet sxSheet = getSXSSFSheet(xSheet);
        if (sxSheet == null && xSheet != null) {
            return (DeferredSXSSFSheet) createAndRegisterSXSSFSheet(xSheet);
        }
        return (DeferredSXSSFSheet) sxSheet;
    }

    public XSSFSheet getXSSFSheet(String name) {
        return this._wb.getSheet(name);
    }

    public DeferredSXSSFSheet getStreamingSheet(String name) {
        XSSFSheet xSheet = this._wb.getSheet(name);
        DeferredSXSSFSheet sxSheet = (DeferredSXSSFSheet) getSXSSFSheet(xSheet);
        if (sxSheet == null && xSheet != null) {
            return (DeferredSXSSFSheet) createAndRegisterSXSSFSheet(xSheet);
        }
        return sxSheet;
    }

    @Override // org.apache.poi.xssf.streaming.SXSSFWorkbook, org.apache.poi.ss.usermodel.Workbook
    public void removeSheetAt(int index) {
        XSSFSheet xSheet = this._wb.getSheetAt(index);
        SXSSFSheet sxSheet = getSXSSFSheet(xSheet);
        this._wb.removeSheetAt(index);
        if (sxSheet != null) {
            deregisterSheetMapping(xSheet);
            try {
                sxSheet.dispose();
            } catch (IOException e) {
                LOG.atWarn().withThrowable(e).log("Failed to cleanup old sheet");
            }
        }
    }
}
