package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.UByte;
import org.apache.commons.lang3.ArrayUtils;
import org.tukaani.xz.DeltaOptions;
import org.tukaani.xz.FinishableWrapperOutputStream;
import org.tukaani.xz.UnsupportedOptionsException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class DeltaDecoder extends AbstractCoder {
    /* JADX INFO: Access modifiers changed from: package-private */
    public DeltaDecoder() {
        super(Number.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.compress.archivers.sevenz.AbstractCoder
    public InputStream decode(String archiveName, InputStream in, long uncompressedLength, Coder coder, byte[] password, int maxMemoryLimitKiB) throws IOException {
        return new DeltaOptions(getOptionsFromCoder(coder)).getInputStream(in);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.compress.archivers.sevenz.AbstractCoder
    public OutputStream encode(OutputStream out, Object options) throws IOException {
        int distance = toInt(options, 1);
        try {
            return new DeltaOptions(distance).getOutputStream(new FinishableWrapperOutputStream(out));
        } catch (UnsupportedOptionsException ex) {
            throw new IOException(ex.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.compress.archivers.sevenz.AbstractCoder
    public byte[] getOptionsAsProperties(Object options) {
        return new byte[]{(byte) (toInt(options, 1) - 1)};
    }

    private int getOptionsFromCoder(Coder coder) {
        if (coder == null || ArrayUtils.isEmpty(coder.properties)) {
            return 1;
        }
        return (coder.properties[0] & UByte.MAX_VALUE) + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.compress.archivers.sevenz.AbstractCoder
    public Object getOptionsFromCoder(Coder coder, InputStream in) {
        return Integer.valueOf(getOptionsFromCoder(coder));
    }
}
