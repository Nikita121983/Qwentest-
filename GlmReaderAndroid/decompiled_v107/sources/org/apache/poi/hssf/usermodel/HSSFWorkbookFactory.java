package org.apache.poi.hssf.usermodel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookProvider;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
public class HSSFWorkbookFactory implements WorkbookProvider {
    @Override // org.apache.poi.ss.usermodel.WorkbookProvider
    public boolean accepts(FileMagic fm) {
        return FileMagic.OLE2 == fm;
    }

    @Override // org.apache.poi.ss.usermodel.WorkbookProvider
    public HSSFWorkbook create() {
        return new HSSFWorkbook();
    }

    public static HSSFWorkbook createWorkbook(POIFSFileSystem fs) throws IOException {
        return new HSSFWorkbook(fs);
    }

    @Override // org.apache.poi.ss.usermodel.WorkbookProvider
    public HSSFWorkbook create(DirectoryNode root, String password) throws IOException {
        boolean passwordSet = false;
        if (password != null) {
            Biff8EncryptionKey.setCurrentUserPassword(password);
            passwordSet = true;
        }
        try {
            return new HSSFWorkbook(root, true);
        } finally {
            if (passwordSet) {
                Biff8EncryptionKey.setCurrentUserPassword(null);
            }
        }
    }

    @Override // org.apache.poi.ss.usermodel.WorkbookProvider
    public Workbook create(InputStream inp) throws IOException {
        return create(inp, (String) null);
    }

    @Override // org.apache.poi.ss.usermodel.WorkbookProvider
    public Workbook create(InputStream inp, String password) throws IOException {
        POIFSFileSystem fs = new POIFSFileSystem(inp);
        return create(fs.getRoot(), password);
    }

    @Override // org.apache.poi.ss.usermodel.WorkbookProvider
    public Workbook create(File file, String password, boolean readOnly) throws IOException {
        boolean passwordSet = false;
        if (password != null) {
            Biff8EncryptionKey.setCurrentUserPassword(password);
            passwordSet = true;
        }
        try {
            POIFSFileSystem fs = new POIFSFileSystem(file, readOnly);
            try {
                return new HSSFWorkbook(fs, true);
            } catch (RuntimeException e) {
                fs.close();
                throw e;
            }
        } finally {
            if (passwordSet) {
                Biff8EncryptionKey.setCurrentUserPassword(null);
            }
        }
    }
}
