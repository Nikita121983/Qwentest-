package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

/* loaded from: classes9.dex */
public class ClosedInputStream extends InputStream {
    public static final ClosedInputStream INSTANCE = new ClosedInputStream();

    @Deprecated
    public static final ClosedInputStream CLOSED_INPUT_STREAM = INSTANCE;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InputStream ifNull(InputStream in) {
        return in != null ? in : INSTANCE;
    }

    @Override // java.io.InputStream
    public int read() {
        return -1;
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        IOUtils.checkFromIndexSize(b, off, len);
        if (len == 0) {
            return 0;
        }
        return -1;
    }
}
