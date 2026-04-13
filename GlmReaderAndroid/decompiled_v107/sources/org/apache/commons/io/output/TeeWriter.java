package org.apache.commons.io.output;

import java.io.Writer;
import java.util.Collection;

/* loaded from: classes9.dex */
public class TeeWriter extends ProxyCollectionWriter {
    public TeeWriter(Collection<Writer> writers) {
        super(writers);
    }

    public TeeWriter(Writer... writers) {
        super(writers);
    }
}
