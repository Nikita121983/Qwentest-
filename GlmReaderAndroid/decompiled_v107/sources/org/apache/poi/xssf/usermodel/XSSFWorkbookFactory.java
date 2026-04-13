package org.apache.poi.xssf.usermodel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentFactoryHelper;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookProvider;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
public class XSSFWorkbookFactory implements WorkbookProvider {
    @Override // org.apache.poi.ss.usermodel.WorkbookProvider
    public boolean accepts(FileMagic fm) {
        return fm == FileMagic.OOXML;
    }

    @Override // org.apache.poi.ss.usermodel.WorkbookProvider
    public XSSFWorkbook create() {
        return new XSSFWorkbook();
    }

    @Override // org.apache.poi.ss.usermodel.WorkbookProvider
    public XSSFWorkbook create(DirectoryNode root, String password) throws IOException {
        InputStream stream = DocumentFactoryHelper.getDecryptedStream(root, password);
        try {
            XSSFWorkbook create = create(stream);
            if (stream != null) {
                stream.close();
            }
            return create;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.poi.ss.usermodel.WorkbookProvider
    public Workbook create(InputStream inp, String password) throws IOException {
        InputStream bufInp = FileMagic.prepareToCheckMagic(inp);
        FileMagic fm = FileMagic.valueOf(bufInp);
        if (fm == FileMagic.OLE2) {
            POIFSFileSystem poifs = new POIFSFileSystem(bufInp);
            try {
                InputStream stream = DocumentFactoryHelper.getDecryptedStream(poifs.getRoot(), password);
                try {
                    XSSFWorkbook create = create(stream);
                    if (stream != null) {
                        stream.close();
                    }
                    poifs.close();
                    return create;
                } finally {
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        poifs.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        } else {
            if (fm == FileMagic.OOXML) {
                return create(bufInp);
            }
            return null;
        }
    }

    @Override // org.apache.poi.ss.usermodel.WorkbookProvider
    public XSSFWorkbook create(InputStream stream) throws IOException {
        try {
            OPCPackage pkg = OPCPackage.open(stream);
            return createWorkbook(pkg);
        } catch (InvalidFormatException e) {
            throw new IOException(e);
        }
    }

    public static XSSFWorkbook createWorkbook(OPCPackage pkg) throws IOException {
        try {
            return new XSSFWorkbook(pkg);
        } catch (RuntimeException ioe) {
            pkg.revert();
            throw ioe;
        }
    }

    @Override // org.apache.poi.ss.usermodel.WorkbookProvider
    public XSSFWorkbook create(File file, String password, boolean readOnly) throws IOException {
        FileMagic fm = FileMagic.valueOf(file);
        if (fm == FileMagic.OLE2) {
            POIFSFileSystem poifs = new POIFSFileSystem(file, true);
            try {
                InputStream stream = DocumentFactoryHelper.getDecryptedStream(poifs.getRoot(), password);
                try {
                    XSSFWorkbook create = create(stream);
                    if (stream != null) {
                        stream.close();
                    }
                    poifs.close();
                    return create;
                } finally {
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        poifs.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        } else {
            try {
                OPCPackage pkg = OPCPackage.open(file, readOnly ? PackageAccess.READ : PackageAccess.READ_WRITE);
                return createWorkbook(pkg);
            } catch (InvalidFormatException e) {
                throw new IOException(e);
            }
        }
    }
}
