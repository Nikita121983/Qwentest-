package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.input.BoundedInputStream;

/* loaded from: classes9.dex */
public abstract class Codec {
    public static final BHSDCodec BCI5 = new BHSDCodec(5, 4);
    public static final BHSDCodec BRANCH5 = new BHSDCodec(5, 4, 2);
    public static final BHSDCodec BYTE1 = new BHSDCodec(1, 256);
    public static final BHSDCodec CHAR3 = new BHSDCodec(3, 128);
    public static final BHSDCodec DELTA5 = new BHSDCodec(5, 64, 1, 1);
    public static final BHSDCodec MDELTA5 = new BHSDCodec(5, 64, 2, 1);
    public static final BHSDCodec SIGNED5 = new BHSDCodec(5, 64, 1);
    public static final BHSDCodec UDELTA5 = new BHSDCodec(5, 64, 0, 1);
    public static final BHSDCodec UNSIGNED5 = new BHSDCodec(5, 64);
    public int lastBandLength;

    public abstract int decode(InputStream inputStream) throws IOException, Pack200Exception;

    public abstract int decode(InputStream inputStream, long j) throws IOException, Pack200Exception;

    public abstract byte[] encode(int i) throws Pack200Exception;

    public abstract byte[] encode(int i, int i2) throws Pack200Exception;

    /* JADX INFO: Access modifiers changed from: package-private */
    public int check(int n, InputStream in) throws Pack200Exception {
        if (in instanceof BoundedInputStream) {
            BoundedInputStream bin = (BoundedInputStream) in;
            long count = bin.getCount();
            long maxLength = bin.getMaxCount();
            if (maxLength > -1) {
                long remaining = maxLength - count;
                if (count < -1 || n > remaining) {
                    throw new Pack200Exception(String.format("Can't read beyond end of stream (n = %,d, count = %,d, maxLength = %,d, remaining = %,d)", Integer.valueOf(n), Long.valueOf(count), Long.valueOf(maxLength), Long.valueOf(remaining)));
                }
            }
        }
        return n;
    }

    public int[] decodeInts(int n, InputStream in) throws IOException, Pack200Exception {
        this.lastBandLength = 0;
        int[] result = new int[check(n, in)];
        int last = 0;
        for (int i = 0; i < n; i++) {
            int decode = decode(in, last);
            last = decode;
            result[i] = decode;
        }
        return result;
    }

    public int[] decodeInts(int n, InputStream in, int firstValue) throws IOException, Pack200Exception {
        int[] result = new int[check(n, in) + 1];
        result[0] = firstValue;
        int last = firstValue;
        for (int i = 1; i < n + 1; i++) {
            int decode = decode(in, last);
            last = decode;
            result[i] = decode;
        }
        return result;
    }

    public byte[] encode(int[] ints) throws Pack200Exception {
        int total = 0;
        byte[][] bytes = new byte[ints.length];
        int i = 0;
        while (true) {
            int i2 = 0;
            if (i >= ints.length) {
                break;
            }
            int i3 = ints[i];
            if (i > 0) {
                i2 = ints[i - 1];
            }
            bytes[i] = encode(i3, i2);
            total += bytes[i].length;
            i++;
        }
        byte[] encoded = new byte[total];
        int index = 0;
        for (byte[] element : bytes) {
            System.arraycopy(element, 0, encoded, index, element.length);
            index += element.length;
        }
        return encoded;
    }
}
