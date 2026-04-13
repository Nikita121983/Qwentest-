package org.apache.poi.poifs.filesystem;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.poifs.nio.FileBackedDataSource;
import org.apache.poi.util.TempFile;

/* loaded from: classes10.dex */
public class TempFilePOIFSFileSystem extends POIFSFileSystem {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) TempFilePOIFSFileSystem.class);
    File tempFile;

    @Override // org.apache.poi.poifs.filesystem.POIFSFileSystem
    protected void createNewDataSource() {
        try {
            this.tempFile = TempFile.createTempFile("poifs", ".tmp");
            this._data = new FileBackedDataSource(this.tempFile, false);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to create data source", e);
        }
    }

    @Override // org.apache.poi.poifs.filesystem.POIFSFileSystem, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            super.close();
        } finally {
            if (this.tempFile != null && this.tempFile.exists() && !this.tempFile.delete()) {
                LOG.atDebug().log("temp file was already deleted (probably due to previous call to close this resource)");
            }
        }
    }
}
