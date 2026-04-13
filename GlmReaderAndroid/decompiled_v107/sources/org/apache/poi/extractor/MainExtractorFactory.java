package org.apache.poi.extractor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.extractor.EventBasedExcelExtractor;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.extractor.OldExcelExtractor;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/* loaded from: classes10.dex */
public class MainExtractorFactory implements ExtractorProvider {
    @Override // org.apache.poi.extractor.ExtractorProvider
    public boolean accepts(FileMagic fm) {
        return FileMagic.OLE2 == fm;
    }

    @Override // org.apache.poi.extractor.ExtractorProvider
    public POITextExtractor create(File file, String password) throws IOException {
        return create(new POIFSFileSystem(file, true).getRoot(), password);
    }

    @Override // org.apache.poi.extractor.ExtractorProvider
    public POITextExtractor create(InputStream inputStream, String password) throws IOException {
        return create(new POIFSFileSystem(inputStream).getRoot(), password);
    }

    @Override // org.apache.poi.extractor.ExtractorProvider
    public POITextExtractor create(DirectoryNode poifsDir, String password) throws IOException {
        String oldPW = Biff8EncryptionKey.getCurrentUserPassword();
        try {
            Biff8EncryptionKey.setCurrentUserPassword(password);
            if (poifsDir.hasEntry(InternalWorkbook.OLD_WORKBOOK_DIR_ENTRY_NAME)) {
                return new OldExcelExtractor(poifsDir);
            }
            for (String workbookName : InternalWorkbook.WORKBOOK_DIR_ENTRY_NAMES_CASE_INSENSITIVE) {
                if (poifsDir.hasEntryCaseInsensitive(workbookName)) {
                    return ExtractorFactory.getPreferEventExtractor() ? new EventBasedExcelExtractor(poifsDir) : new ExcelExtractor(poifsDir);
                }
            }
            Biff8EncryptionKey.setCurrentUserPassword(oldPW);
            return null;
        } finally {
            Biff8EncryptionKey.setCurrentUserPassword(oldPW);
        }
    }
}
