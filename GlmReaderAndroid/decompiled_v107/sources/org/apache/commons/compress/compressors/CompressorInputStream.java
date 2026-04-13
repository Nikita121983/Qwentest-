package org.apache.commons.compress.compressors;

import java.io.InputStream;

/* loaded from: classes9.dex */
public abstract class CompressorInputStream extends InputStream {
    private long bytesRead;

    /* JADX INFO: Access modifiers changed from: protected */
    public void count(int read) {
        count(read);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void count(long read) {
        if (read != -1) {
            this.bytesRead += read;
        }
    }

    public long getBytesRead() {
        return this.bytesRead;
    }

    @Deprecated
    public int getCount() {
        return (int) this.bytesRead;
    }

    public long getUncompressedCount() {
        return getBytesRead();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void pushedBackBytes(long pushedBack) {
        this.bytesRead -= pushedBack;
    }
}
