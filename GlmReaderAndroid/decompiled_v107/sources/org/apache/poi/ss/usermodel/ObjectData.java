package org.apache.poi.ss.usermodel;

import java.io.IOException;
import org.apache.poi.poifs.filesystem.DirectoryEntry;

/* loaded from: classes10.dex */
public interface ObjectData extends SimpleShape {
    DirectoryEntry getDirectory() throws IOException;

    String getFileName();

    String getOLE2ClassName();

    byte[] getObjectData() throws IOException;

    PictureData getPictureData();

    boolean hasDirectoryEntry();

    default String getContentType() {
        return "binary/octet-stream";
    }
}
