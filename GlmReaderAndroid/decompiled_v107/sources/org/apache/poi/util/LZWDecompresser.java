package org.apache.poi.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;

/* loaded from: classes10.dex */
public abstract class LZWDecompresser {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 1000000;
    public static final int DICT_MASK = 4095;
    public static final int DICT_SIZE = 4096;
    private static int MAX_RECORD_LENGTH = 1000000;
    private final int codeLengthIncrease;
    private final boolean maskMeansCompressed;
    private final boolean positionIsBigEndian;

    protected abstract int adjustDictionaryOffset(int i);

    protected abstract int populateDictionary(byte[] bArr);

    public static void setMaxRecordLength(int length) {
        MAX_RECORD_LENGTH = length;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    protected LZWDecompresser(boolean maskMeansCompressed, int codeLengthIncrease, boolean positionIsBigEndian) {
        this.maskMeansCompressed = maskMeansCompressed;
        this.codeLengthIncrease = codeLengthIncrease;
        this.positionIsBigEndian = positionIsBigEndian;
    }

    public byte[] decompress(InputStream src) throws IOException {
        UnsynchronizedByteArrayOutputStream res = UnsynchronizedByteArrayOutputStream.builder().get();
        decompress(src, res);
        return res.toByteArray();
    }

    public void decompress(InputStream src, OutputStream res) throws IOException {
        int pntr;
        byte[] buffer = new byte[4096];
        int pos = populateDictionary(buffer);
        byte[] dataB = IOUtils.safelyAllocate(this.codeLengthIncrease + 16, MAX_RECORD_LENGTH);
        while (true) {
            int flag = src.read();
            if (flag != -1) {
                for (int mask = 1; mask < 256; mask <<= 1) {
                    boolean isMaskSet = (flag & mask) > 0;
                    if (this.maskMeansCompressed ^ isMaskSet) {
                        int dataI = src.read();
                        if (dataI != -1) {
                            buffer[pos & 4095] = (byte) dataI;
                            res.write(dataI);
                            pos++;
                        }
                    } else {
                        int dataIPt1 = src.read();
                        int dataIPt2 = src.read();
                        if (dataIPt1 != -1 && dataIPt2 != -1) {
                            int len = (dataIPt2 & 15) + this.codeLengthIncrease;
                            if (this.positionIsBigEndian) {
                                pntr = (dataIPt1 << 4) + (dataIPt2 >>> 4);
                            } else {
                                int pntr2 = dataIPt2 & 240;
                                pntr = (pntr2 << 4) + dataIPt1;
                            }
                            int pntr3 = adjustDictionaryOffset(pntr);
                            for (int i = 0; i < len; i++) {
                                dataB[i] = buffer[(pntr3 + i) & 4095];
                                buffer[(pos + i) & 4095] = dataB[i];
                            }
                            res.write(dataB, 0, len);
                            pos += len;
                        }
                    }
                }
            } else {
                return;
            }
        }
    }
}
