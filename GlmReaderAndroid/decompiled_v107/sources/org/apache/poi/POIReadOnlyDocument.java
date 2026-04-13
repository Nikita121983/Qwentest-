package org.apache.poi;

import java.io.File;
import java.io.OutputStream;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/* loaded from: classes10.dex */
public abstract class POIReadOnlyDocument extends POIDocument {
    protected POIReadOnlyDocument(DirectoryNode dir) {
        super(dir);
    }

    protected POIReadOnlyDocument(POIFSFileSystem fs) {
        super(fs);
    }

    @Override // org.apache.poi.POIDocument
    public void write() {
        notImplemented();
    }

    @Override // org.apache.poi.POIDocument
    public void write(File file) {
        notImplemented();
    }

    @Override // org.apache.poi.POIDocument
    public void write(OutputStream out) {
        notImplemented();
    }

    private static void notImplemented() {
        throw new IllegalStateException("Writing is not yet implemented for this Document Format");
    }
}
