package org.apache.commons.compress.compressors;

import java.io.OutputStream;
import org.apache.commons.compress.CompressFilterOutputStream;

/* loaded from: classes9.dex */
public abstract class CompressorOutputStream<T extends OutputStream> extends CompressFilterOutputStream<T> {
    public CompressorOutputStream() {
    }

    public CompressorOutputStream(T out) {
        super(out);
    }
}
