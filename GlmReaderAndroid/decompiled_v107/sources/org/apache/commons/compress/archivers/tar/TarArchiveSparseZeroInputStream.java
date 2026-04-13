package org.apache.commons.compress.archivers.tar;

import java.io.InputStream;

/* loaded from: classes9.dex */
final class TarArchiveSparseZeroInputStream extends InputStream {
    @Override // java.io.InputStream
    public int read() {
        return 0;
    }

    @Override // java.io.InputStream
    public long skip(long n) {
        return n;
    }
}
