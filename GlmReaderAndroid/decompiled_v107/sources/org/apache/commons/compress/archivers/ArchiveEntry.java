package org.apache.commons.compress.archivers;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;

/* loaded from: classes9.dex */
public interface ArchiveEntry {
    public static final long SIZE_UNKNOWN = -1;

    Date getLastModifiedDate();

    String getName();

    long getSize();

    boolean isDirectory();

    default Path resolveIn(Path parentPath) throws IOException {
        String name = getName();
        Path outputFile = parentPath.resolve(name).normalize();
        if (!outputFile.startsWith(parentPath)) {
            throw new IOException(String.format("Zip slip '%s' + '%s' -> '%s'", parentPath, name, outputFile));
        }
        return outputFile;
    }
}
