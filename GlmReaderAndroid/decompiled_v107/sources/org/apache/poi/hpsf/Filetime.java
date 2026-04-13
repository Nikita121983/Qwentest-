package org.apache.poi.hpsf;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
/* loaded from: classes10.dex */
public class Filetime {
    private static final BigInteger EPOCH_DIFF = BigInteger.valueOf(-11644473600000L);
    private static final BigInteger NANO_100 = BigInteger.valueOf(10000);
    private long fileTime;

    public Filetime() {
    }

    public Filetime(java.util.Date date) {
        this.fileTime = dateToFileTime(date);
    }

    public void read(LittleEndianByteArrayInputStream lei) {
        this.fileTime = lei.readLong();
    }

    public byte[] toByteArray() {
        byte[] result = new byte[8];
        LittleEndian.putLong(result, 0, this.fileTime);
        return result;
    }

    public int write(OutputStream out) throws IOException {
        out.write(toByteArray());
        return 8;
    }

    public java.util.Date getJavaValue() {
        return filetimeToDate(this.fileTime);
    }

    public static java.util.Date filetimeToDate(long filetime) {
        BigInteger bi = filetime < 0 ? twoComplement(filetime) : BigInteger.valueOf(filetime);
        return new java.util.Date(bi.divide(NANO_100).add(EPOCH_DIFF).longValue());
    }

    public static long dateToFileTime(java.util.Date date) {
        return BigInteger.valueOf(date.getTime()).subtract(EPOCH_DIFF).multiply(NANO_100).longValue();
    }

    public static boolean isUndefined(java.util.Date date) {
        return date == null || dateToFileTime(date) == 0;
    }

    private static BigInteger twoComplement(long val) {
        byte[] contents = {(byte) (val < 0 ? 0 : -1), (byte) ((val >> 56) & 255), (byte) ((val >> 48) & 255), (byte) ((val >> 40) & 255), (byte) ((val >> 32) & 255), (byte) ((val >> 24) & 255), (byte) ((val >> 16) & 255), (byte) ((val >> 8) & 255), (byte) (255 & val)};
        return new BigInteger(contents);
    }
}
