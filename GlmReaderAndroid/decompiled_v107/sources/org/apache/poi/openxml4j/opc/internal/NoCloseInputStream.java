package org.apache.poi.openxml4j.opc.internal;

import java.io.FilterInputStream;
import java.io.InputStream;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
final class NoCloseInputStream extends FilterInputStream {
    /* JADX INFO: Access modifiers changed from: package-private */
    public NoCloseInputStream(InputStream stream) {
        super(stream);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }
}
