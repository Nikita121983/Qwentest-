package org.apache.poi.poifs.crypt.temp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.openxml4j.util.ZipEntrySource;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.streaming.SheetDataWriter;

/* loaded from: classes10.dex */
public class SXSSFWorkbookWithCustomZipEntrySource extends SXSSFWorkbook {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) SXSSFWorkbookWithCustomZipEntrySource.class);

    public SXSSFWorkbookWithCustomZipEntrySource() {
        super(20);
        setCompressTempFiles(true);
    }

    @Override // org.apache.poi.xssf.streaming.SXSSFWorkbook, org.apache.poi.ss.usermodel.Workbook
    public void write(OutputStream stream) throws IOException {
        flushSheets();
        EncryptedTempData tempData = new EncryptedTempData();
        ZipEntrySource source = null;
        try {
            OutputStream os = tempData.getOutputStream();
            try {
                getXSSFWorkbook().write(os);
                if (os != null) {
                    os.close();
                }
                InputStream tempStream = tempData.getInputStream();
                try {
                    source = AesZipFileZipEntrySource.createZipEntrySource(tempStream);
                    if (tempStream != null) {
                        tempStream.close();
                    }
                    injectData(source, stream);
                } finally {
                }
            } finally {
            }
        } finally {
            tempData.dispose();
            IOUtils.closeQuietly(source);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.xssf.streaming.SXSSFWorkbook
    public SheetDataWriter createSheetDataWriter() throws IOException {
        LOG.atInfo().log("isCompressTempFiles: {}", Unbox.box(isCompressTempFiles()));
        LOG.atInfo().log("SharedStringSource: {}", getSharedStringSource());
        return new SheetDataWriterWithDecorator();
    }
}
