package org.apache.commons.compress.compressors.pack200;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes9.dex */
abstract class AbstractStreamBridge extends FilterOutputStream {
    private InputStream inputStream;
    private final Object inputStreamLock;

    abstract InputStream createInputStream() throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractStreamBridge() {
        this(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractStreamBridge(OutputStream outputStream) {
        super(outputStream);
        this.inputStreamLock = new Object();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InputStream getInputStream() throws IOException {
        synchronized (this.inputStreamLock) {
            if (this.inputStream == null) {
                this.inputStream = createInputStream();
            }
        }
        return this.inputStream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stop() throws IOException {
        close();
        synchronized (this.inputStreamLock) {
            if (this.inputStream != null) {
                this.inputStream.close();
                this.inputStream = null;
            }
        }
    }
}
