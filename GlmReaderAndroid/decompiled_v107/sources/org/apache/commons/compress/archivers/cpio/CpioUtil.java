package org.apache.commons.compress.archivers.cpio;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import kotlin.UByte;

/* loaded from: classes9.dex */
final class CpioUtil {
    static final String DEFAULT_CHARSET_NAME = StandardCharsets.US_ASCII.name();

    CpioUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long byteArray2long(byte[] number, boolean swapHalfWord) {
        if (number.length % 2 != 0) {
            throw new UnsupportedOperationException();
        }
        byte[] tmpNumber = Arrays.copyOf(number, number.length);
        if (!swapHalfWord) {
            int pos = 0;
            while (pos < tmpNumber.length) {
                byte tmp = tmpNumber[pos];
                int pos2 = pos + 1;
                tmpNumber[pos] = tmpNumber[pos2];
                tmpNumber[pos2] = tmp;
                pos = pos2 + 1;
            }
        }
        long ret = tmpNumber[0] & UByte.MAX_VALUE;
        for (int pos3 = 1; pos3 < tmpNumber.length; pos3++) {
            ret = (ret << 8) | (tmpNumber[pos3] & UByte.MAX_VALUE);
        }
        return ret;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long fileType(long mode) {
        return 61440 & mode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] long2byteArray(long number, int length, boolean swapHalfWord) {
        byte[] ret = new byte[length];
        if (length % 2 != 0 || length < 2) {
            throw new UnsupportedOperationException();
        }
        long tmp_number = number;
        for (int pos = length - 1; pos >= 0; pos--) {
            ret[pos] = (byte) (255 & tmp_number);
            tmp_number >>= 8;
        }
        if (!swapHalfWord) {
            int pos2 = 0;
            while (pos2 < length) {
                byte tmp = ret[pos2];
                int pos3 = pos2 + 1;
                ret[pos2] = ret[pos3];
                ret[pos3] = tmp;
                pos2 = pos3 + 1;
            }
        }
        return ret;
    }
}
