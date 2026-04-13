package org.apache.commons.compress.utils;

import java.io.InputStream;

@Deprecated
/* loaded from: classes9.dex */
public class BoundedInputStream extends org.apache.commons.io.input.BoundedInputStream {
    public BoundedInputStream(InputStream in, long size) {
        super(in, size);
        setPropagateClose(false);
    }

    public long getBytesRemaining() {
        return getMaxCount() - getCount();
    }
}
