package org.apache.poi.poifs.eventfilesystem;

import org.apache.poi.hpsf.ClassID;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.POIFSDocumentPath;

/* loaded from: classes10.dex */
public class POIFSReaderEvent {
    private final String documentName;
    private final POIFSDocumentPath path;
    private final ClassID storageClassId;
    private final DocumentInputStream stream;

    /* JADX INFO: Access modifiers changed from: package-private */
    public POIFSReaderEvent(DocumentInputStream stream, POIFSDocumentPath path, String documentName, ClassID storageClassId) {
        this.stream = stream;
        this.path = path;
        this.documentName = documentName;
        this.storageClassId = storageClassId;
    }

    public DocumentInputStream getStream() {
        return this.stream;
    }

    public POIFSDocumentPath getPath() {
        return this.path;
    }

    public String getName() {
        return this.documentName;
    }

    public ClassID getStorageClassId() {
        return this.storageClassId;
    }
}
