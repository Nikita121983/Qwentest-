package org.apache.commons.compress.compressors.pack200;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;

/* loaded from: classes9.dex */
final class TempFileCachingStreamBridge extends AbstractStreamBridge {
    private final Path path = Files.createTempFile("commons-compress", "packtemp", new FileAttribute[0]);

    /* JADX INFO: Access modifiers changed from: package-private */
    public TempFileCachingStreamBridge() throws IOException {
        this.path.toFile().deleteOnExit();
        this.out = Files.newOutputStream(this.path, new OpenOption[0]);
    }

    @Override // org.apache.commons.compress.compressors.pack200.AbstractStreamBridge
    InputStream createInputStream() throws IOException {
        this.out.close();
        return new FilterInputStream(Files.newInputStream(this.path, new OpenOption[0])) { // from class: org.apache.commons.compress.compressors.pack200.TempFileCachingStreamBridge.1
            @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                try {
                    super.close();
                    try {
                        Files.deleteIfExists(TempFileCachingStreamBridge.this.path);
                    } catch (IOException e) {
                    }
                } catch (Throwable th) {
                    try {
                        Files.deleteIfExists(TempFileCachingStreamBridge.this.path);
                    } catch (IOException e2) {
                    }
                    throw th;
                }
            }
        };
    }
}
