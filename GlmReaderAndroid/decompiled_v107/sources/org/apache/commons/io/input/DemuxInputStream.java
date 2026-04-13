package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

/* loaded from: classes9.dex */
public class DemuxInputStream extends InputStream {
    private final InheritableThreadLocal<InputStream> inputStreamLocal = new InheritableThreadLocal<>();

    public InputStream bindStream(InputStream input) {
        InputStream oldValue = this.inputStreamLocal.get();
        this.inputStreamLocal.set(input);
        return oldValue;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        IOUtils.close(this.inputStreamLocal.get());
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        InputStream inputStream = this.inputStreamLocal.get();
        if (inputStream != null) {
            return inputStream.read();
        }
        return -1;
    }
}
