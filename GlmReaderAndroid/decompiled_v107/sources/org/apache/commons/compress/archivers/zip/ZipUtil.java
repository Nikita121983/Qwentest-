package org.apache.commons.compress.archivers.zip;

import androidx.core.view.InputDeviceCompat;
import java.io.IOException;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.CRC32;
import java.util.zip.ZipException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;

/* loaded from: classes9.dex */
public abstract class ZipUtil {
    private static final long DOSTIME_BEFORE_1980 = 2162688;
    private static final long DOSTIME_BEFORE_1980_AS_JAVA_TIME = dosToJavaTime(DOSTIME_BEFORE_1980);
    private static final long UPPER_DOSTIME_BOUND = 4036608000000L;

    public static long adjustToLong(int i) {
        if (i < 0) {
            return i + 4294967296L;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean canHandleEntryData(ZipArchiveEntry entry) {
        return supportsEncryptionOf(entry) && supportsMethodOf(entry);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkRequestedFeatures(ZipArchiveEntry ze) throws UnsupportedZipFeatureException {
        if (!supportsEncryptionOf(ze)) {
            throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.ENCRYPTION, ze);
        }
        if (!supportsMethodOf(ze)) {
            ZipMethod m = ZipMethod.getMethodByCode(ze.getMethod());
            if (m == null) {
                throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.METHOD, ze);
            }
            throw new UnsupportedZipFeatureException(m, ze);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] copy(byte[] from) {
        if (from != null) {
            return Arrays.copyOf(from, from.length);
        }
        return null;
    }

    static void copy(byte[] from, byte[] to, int offset) {
        if (from != null) {
            System.arraycopy(from, 0, to, offset, from.length);
        }
    }

    private static Date dosToJavaDate(long dosTime) {
        Calendar cal = Calendar.getInstance();
        cal.set(1, ((int) ((dosTime >> 25) & 127)) + 1980);
        cal.set(2, ((int) ((dosTime >> 21) & 15)) - 1);
        cal.set(5, ((int) (dosTime >> 16)) & 31);
        cal.set(11, ((int) (dosTime >> 11)) & 31);
        cal.set(12, ((int) (dosTime >> 5)) & 63);
        cal.set(13, ((int) (dosTime << 1)) & 62);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static long dosToJavaTime(long dosTime) {
        return dosToJavaDate(dosTime).getTime();
    }

    public static Date fromDosTime(ZipLong zipDosTime) {
        long dosTime = zipDosTime.getValue();
        return dosToJavaDate(dosTime);
    }

    private static String getUnicodeStringIfOriginalMatches(AbstractUnicodeExtraField field, byte[] originalNameBytes) {
        if (field != null) {
            CRC32 crc32 = new CRC32();
            crc32.update(originalNameBytes);
            long origCRC32 = crc32.getValue();
            if (origCRC32 == field.getNameCRC32()) {
                try {
                    return ZipEncodingHelper.ZIP_ENCODING_UTF_8.decode(field.getUnicodeName());
                } catch (IOException e) {
                    return null;
                }
            }
            return null;
        }
        return null;
    }

    public static boolean isDosTime(long time) {
        return time <= UPPER_DOSTIME_BOUND && (time == DOSTIME_BEFORE_1980_AS_JAVA_TIME || javaToDosTime(time) != DOSTIME_BEFORE_1980);
    }

    private static LocalDateTime javaEpochToLocalDateTime(long time) {
        Instant instant = Instant.ofEpochMilli(time);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    private static long javaToDosTime(long t) {
        LocalDateTime ldt = javaEpochToLocalDateTime(t);
        if (ldt.getYear() < 1980) {
            return DOSTIME_BEFORE_1980;
        }
        return (((ldt.getYear() - 1980) << 25) | (ldt.getMonthValue() << 21) | (ldt.getDayOfMonth() << 16) | (ldt.getHour() << 11) | (ldt.getMinute() << 5) | (ldt.getSecond() >> 1)) & 4294967295L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BigInteger longToBig(long l) {
        if (l < -2147483648L) {
            throw new IllegalArgumentException("Negative longs < -2^31 not permitted: [" + l + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        }
        if (l < 0 && l >= -2147483648L) {
            l = adjustToLong((int) l);
        }
        return BigInteger.valueOf(l);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ZipException newZipException(String message, Throwable cause) {
        return (ZipException) new ZipException(message).initCause(cause);
    }

    public static byte[] reverse(byte[] array) {
        int z = array.length - 1;
        for (int i = 0; i < array.length / 2; i++) {
            byte x = array[i];
            array[i] = array[z - i];
            array[z - i] = x;
        }
        return array;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setNameAndCommentFromExtraFields(ZipArchiveEntry ze, byte[] originalNameBytes, byte[] commentBytes) {
        ZipExtraField nameCandidate = ze.getExtraField(UnicodePathExtraField.UPATH_ID);
        UnicodePathExtraField name = nameCandidate instanceof UnicodePathExtraField ? (UnicodePathExtraField) nameCandidate : null;
        String newName = getUnicodeStringIfOriginalMatches(name, originalNameBytes);
        if (newName != null) {
            ze.setName(newName);
            ze.setNameSource(ZipArchiveEntry.NameSource.UNICODE_EXTRA_FIELD);
        }
        if (commentBytes != null && commentBytes.length > 0) {
            ZipExtraField cmtCandidate = ze.getExtraField(UnicodeCommentExtraField.UCOM_ID);
            UnicodeCommentExtraField cmt = cmtCandidate instanceof UnicodeCommentExtraField ? (UnicodeCommentExtraField) cmtCandidate : null;
            String newComment = getUnicodeStringIfOriginalMatches(cmt, commentBytes);
            if (newComment != null) {
                ze.setComment(newComment);
                ze.setCommentSource(ZipArchiveEntry.CommentSource.UNICODE_EXTRA_FIELD);
            }
        }
    }

    @Deprecated
    public static int signedByteToUnsignedInt(byte b) {
        return Byte.toUnsignedInt(b);
    }

    private static boolean supportsEncryptionOf(ZipArchiveEntry entry) {
        return !entry.getGeneralPurposeBit().usesEncryption();
    }

    private static boolean supportsMethodOf(ZipArchiveEntry entry) {
        int method = entry.getMethod();
        return method == 0 || method == ZipMethod.UNSHRINKING.getCode() || method == ZipMethod.IMPLODING.getCode() || method == 8 || method == ZipMethod.ENHANCED_DEFLATED.getCode() || method == ZipMethod.BZIP2.getCode() || ZipMethod.isZstd(method) || method == ZipMethod.XZ.getCode();
    }

    public static ZipLong toDosTime(Date time) {
        return new ZipLong(toDosTime(time.getTime()));
    }

    public static byte[] toDosTime(long t) {
        byte[] result = new byte[4];
        toDosTime(t, result, 0);
        return result;
    }

    public static void toDosTime(long t, byte[] buf, int offset) {
        ZipLong.putLong(javaToDosTime(t), buf, offset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long toLong(BigInteger big) {
        try {
            return big.longValueExact();
        } catch (ArithmeticException e) {
            throw new NumberFormatException("The BigInteger cannot fit inside a 64 bit java long: [" + big + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        }
    }

    public static byte unsignedIntToSignedByte(int i) {
        if (i > 255 || i < 0) {
            throw new IllegalArgumentException("Can only convert non-negative integers between [0,255] to byte: [" + i + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        }
        if (i < 128) {
            return (byte) i;
        }
        return (byte) (i + InputDeviceCompat.SOURCE_ANY);
    }
}
