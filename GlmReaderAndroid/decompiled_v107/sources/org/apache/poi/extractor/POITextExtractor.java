package org.apache.poi.extractor;

import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes10.dex */
public interface POITextExtractor extends Closeable {
    Object getDocument();

    Closeable getFilesystem();

    POITextExtractor getMetadataTextExtractor();

    String getText();

    boolean isCloseFilesystem();

    void setCloseFilesystem(boolean z);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    default void close() throws IOException {
        Closeable fs = getFilesystem();
        if (isCloseFilesystem() && fs != null) {
            fs.close();
        }
    }
}
