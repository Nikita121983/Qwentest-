package org.apache.poi.sl.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.IOUtils;

/* loaded from: classes10.dex */
public interface ObjectData {
    String getFileName();

    InputStream getInputStream() throws IOException;

    String getOLE2ClassName();

    OutputStream getOutputStream() throws IOException;

    default byte[] getBytes() throws IOException {
        InputStream is = getInputStream();
        try {
            byte[] byteArray = IOUtils.toByteArray(is);
            if (is != null) {
                is.close();
            }
            return byteArray;
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
    }

    default boolean hasDirectoryEntry() {
        try {
            InputStream is = FileMagic.prepareToCheckMagic(getInputStream());
            try {
                FileMagic fm = FileMagic.valueOf(is);
                boolean z = fm == FileMagic.OLE2;
                if (is != null) {
                    is.close();
                }
                return z;
            } finally {
            }
        } catch (IOException e) {
            Logger LOG = PoiLogManager.getLogger((Class<?>) ObjectData.class);
            LOG.atWarn().withThrowable(e).log("Can't determine filemagic of ole stream");
            return false;
        }
    }

    default DirectoryEntry getDirectory() throws IOException {
        InputStream is = getInputStream();
        try {
            DirectoryNode root = new POIFSFileSystem(is).getRoot();
            if (is != null) {
                is.close();
            }
            return root;
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
    }
}
