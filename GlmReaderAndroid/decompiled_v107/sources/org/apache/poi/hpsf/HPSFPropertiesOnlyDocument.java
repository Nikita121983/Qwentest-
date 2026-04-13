package org.apache.poi.hpsf;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.POIDocument;
import org.apache.poi.poifs.filesystem.EntryUtils;
import org.apache.poi.poifs.filesystem.FilteringDirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/* loaded from: classes10.dex */
public class HPSFPropertiesOnlyDocument extends POIDocument {
    public HPSFPropertiesOnlyDocument(POIFSFileSystem fs) {
        super(fs);
    }

    @Override // org.apache.poi.POIDocument
    public void write() throws IOException {
        POIFSFileSystem fs = getDirectory().getFileSystem();
        validateInPlaceWritePossible();
        writeProperties(fs, null);
        fs.writeFilesystem();
    }

    @Override // org.apache.poi.POIDocument
    public void write(File newFile) throws IOException {
        POIFSFileSystem fs = POIFSFileSystem.create(newFile);
        try {
            write(fs);
            fs.writeFilesystem();
            if (fs != null) {
                fs.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (fs != null) {
                    try {
                        fs.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.poi.POIDocument
    public void write(OutputStream out) throws IOException {
        POIFSFileSystem fs = new POIFSFileSystem();
        try {
            write(fs);
            fs.writeFilesystem(out);
            fs.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    fs.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    private void write(POIFSFileSystem fs) throws IOException {
        List<String> excepts = new ArrayList<>(2);
        writeProperties(fs, excepts);
        FilteringDirectoryNode src = new FilteringDirectoryNode(getDirectory(), excepts);
        FilteringDirectoryNode dest = new FilteringDirectoryNode(fs.getRoot(), excepts);
        EntryUtils.copyNodes(src, dest);
    }
}
