package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.UByte;
import org.apache.commons.compress.MemoryLimitException;
import org.tukaani.xz.FinishableWrapperOutputStream;
import org.tukaani.xz.LZMA2InputStream;
import org.tukaani.xz.LZMA2Options;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class LZMA2Decoder extends AbstractCoder {
    /* JADX INFO: Access modifiers changed from: package-private */
    public LZMA2Decoder() {
        super(LZMA2Options.class, Number.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.compress.archivers.sevenz.AbstractCoder
    public InputStream decode(String archiveName, InputStream in, long uncompressedLength, Coder coder, byte[] password, int maxMemoryLimitKiB) throws IOException {
        try {
            int dictionarySize = getDictionarySize(coder);
            int memoryUsageInKiB = LZMA2InputStream.getMemoryUsage(dictionarySize);
            if (memoryUsageInKiB > maxMemoryLimitKiB) {
                throw new MemoryLimitException(memoryUsageInKiB, maxMemoryLimitKiB);
            }
            return new LZMA2InputStream(in, dictionarySize);
        } catch (IllegalArgumentException ex) {
            throw new IOException(ex);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.compress.archivers.sevenz.AbstractCoder
    public OutputStream encode(OutputStream out, Object opts) throws IOException {
        return getOptions(opts).getOutputStream(new FinishableWrapperOutputStream(out));
    }

    private int getDictionarySize(Coder coder) throws IOException {
        if (coder.properties == null) {
            throw new IOException("Missing LZMA2 properties");
        }
        if (coder.properties.length < 1) {
            throw new IOException("LZMA2 properties too short");
        }
        int dictionarySizeBits = coder.properties[0] & UByte.MAX_VALUE;
        if ((dictionarySizeBits & (-64)) != 0) {
            throw new IOException("Unsupported LZMA2 property bits");
        }
        if (dictionarySizeBits > 40) {
            throw new IOException("Dictionary larger than 4GiB maximum size");
        }
        if (dictionarySizeBits == 40) {
            return -1;
        }
        return ((dictionarySizeBits & 1) | 2) << ((dictionarySizeBits / 2) + 11);
    }

    private int getDictSize(Object opts) {
        if (opts instanceof LZMA2Options) {
            return ((LZMA2Options) opts).getDictSize();
        }
        return numberOptionOrDefault(opts);
    }

    private LZMA2Options getOptions(Object opts) throws IOException {
        if (opts instanceof LZMA2Options) {
            return (LZMA2Options) opts;
        }
        LZMA2Options options = new LZMA2Options();
        options.setDictSize(numberOptionOrDefault(opts));
        return options;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.compress.archivers.sevenz.AbstractCoder
    public byte[] getOptionsAsProperties(Object opts) {
        int dictSize = getDictSize(opts);
        int lead = Integer.numberOfLeadingZeros(dictSize);
        int secondBit = (dictSize >>> (30 - lead)) - 2;
        return new byte[]{(byte) (((19 - lead) * 2) + secondBit)};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.compress.archivers.sevenz.AbstractCoder
    public Object getOptionsFromCoder(Coder coder, InputStream in) throws IOException {
        return Integer.valueOf(getDictionarySize(coder));
    }

    private int numberOptionOrDefault(Object opts) {
        return toInt(opts, 8388608);
    }
}
