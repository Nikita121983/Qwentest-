package org.apache.commons.compress.archivers.tar;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.ParsingUtils;

/* loaded from: classes9.dex */
public class TarUtils {
    private static final int BYTE_MASK = 255;
    private static final BigInteger NEG_1_BIG_INT = BigInteger.valueOf(-1);
    static final ZipEncoding DEFAULT_ENCODING = ZipEncodingHelper.getZipEncoding(Charset.defaultCharset());
    static final ZipEncoding FALLBACK_ENCODING = new ZipEncoding() { // from class: org.apache.commons.compress.archivers.tar.TarUtils.1
        @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
        public boolean canEncode(String name) {
            return true;
        }

        @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
        public String decode(byte[] buffer) {
            int length = buffer.length;
            StringBuilder result = new StringBuilder(length);
            for (byte b : buffer) {
                if (b == 0) {
                    break;
                }
                result.append((char) (b & UByte.MAX_VALUE));
            }
            return result.toString();
        }

        @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
        public ByteBuffer encode(String name) {
            return ByteBuffer.wrap(name.getBytes(StandardCharsets.US_ASCII));
        }
    };

    public static long computeCheckSum(byte[] buf) {
        long sum = 0;
        for (byte element : buf) {
            sum += element & UByte.MAX_VALUE;
        }
        return sum;
    }

    private static String exceptionMessage(byte[] buffer, int offset, int length, int current, byte currentByte) {
        String string = new String(buffer, offset, length, Charset.defaultCharset());
        return "Invalid byte " + ((int) currentByte) + " at offset " + (current - offset) + " in '" + string.replace("\u0000", "{NUL}") + "' len=" + length;
    }

    private static void formatBigIntegerBinary(long value, byte[] buf, int offset, int length, boolean negative) {
        BigInteger val = BigInteger.valueOf(value);
        byte[] b = val.toByteArray();
        int len = b.length;
        if (len > length - 1) {
            throw new IllegalArgumentException("Value " + value + " is too large for " + length + " byte field.");
        }
        int off = (offset + length) - len;
        System.arraycopy(b, 0, buf, off, len);
        Arrays.fill(buf, offset + 1, off, (byte) (negative ? 255 : 0));
    }

    public static int formatCheckSumOctalBytes(long value, byte[] buf, int offset, int length) {
        int idx = length - 2;
        formatUnsignedOctalString(value, buf, offset, idx);
        buf[idx + offset] = 0;
        buf[offset + idx + 1] = 32;
        return offset + length;
    }

    private static void formatLongBinary(long value, byte[] buf, int offset, int length, boolean negative) {
        int bits = (length - 1) * 8;
        long max = 1 << bits;
        long val = Math.abs(value);
        if (val < 0 || val >= max) {
            throw new IllegalArgumentException("Value " + value + " is too large for " + length + " byte field.");
        }
        if (negative) {
            val = ((val ^ (max - 1)) + 1) | (255 << bits);
        }
        for (int i = (offset + length) - 1; i >= offset; i--) {
            buf[i] = (byte) val;
            val >>= 8;
        }
    }

    public static int formatLongOctalBytes(long value, byte[] buf, int offset, int length) {
        int idx = length - 1;
        formatUnsignedOctalString(value, buf, offset, idx);
        buf[offset + idx] = 32;
        return offset + length;
    }

    public static int formatLongOctalOrBinaryBytes(long value, byte[] buf, int offset, int length) {
        byte[] buf2;
        int offset2;
        int length2;
        long maxAsOctalChar = length == 8 ? TarConstants.MAXID : TarConstants.MAXSIZE;
        boolean negative = value < 0;
        if (!negative && value <= maxAsOctalChar) {
            return formatLongOctalBytes(value, buf, offset, length);
        }
        if (length < 9) {
            buf2 = buf;
            offset2 = offset;
            length2 = length;
            formatLongBinary(value, buf2, offset2, length2, negative);
        } else {
            buf2 = buf;
            offset2 = offset;
            length2 = length;
            formatBigIntegerBinary(value, buf2, offset2, length2, negative);
        }
        buf2[offset2] = (byte) (negative ? 255 : 128);
        return offset2 + length2;
    }

    public static int formatNameBytes(String name, byte[] buf, int offset, int length) {
        try {
            return formatNameBytes(name, buf, offset, length, DEFAULT_ENCODING);
        } catch (IOException e) {
            try {
                return formatNameBytes(name, buf, offset, length, FALLBACK_ENCODING);
            } catch (IOException ex2) {
                throw new UncheckedIOException(ex2);
            }
        }
    }

    public static int formatNameBytes(String name, byte[] buf, int offset, int length, ZipEncoding encoding) throws IOException {
        int len = name.length();
        ByteBuffer b = encoding.encode(name);
        while (b.limit() > length && len > 0) {
            len--;
            b = encoding.encode(name.substring(0, len));
        }
        int limit = b.limit() - b.position();
        System.arraycopy(b.array(), b.arrayOffset(), buf, offset, limit);
        Arrays.fill(buf, offset + limit, offset + length, (byte) 0);
        return offset + length;
    }

    public static int formatOctalBytes(long value, byte[] buf, int offset, int length) {
        int idx = length - 2;
        formatUnsignedOctalString(value, buf, offset, idx);
        buf[idx + offset] = 32;
        buf[offset + idx + 1] = 0;
        return offset + length;
    }

    public static void formatUnsignedOctalString(long value, byte[] buffer, int offset, int length) {
        int remaining;
        int remaining2 = length - 1;
        if (value == 0) {
            remaining = remaining2 - 1;
            buffer[remaining2 + offset] = TarConstants.LF_NORMAL;
        } else {
            long val = value;
            while (remaining2 >= 0 && val != 0) {
                buffer[offset + remaining2] = (byte) (((byte) (7 & val)) + TarConstants.LF_NORMAL);
                val >>>= 3;
                remaining2--;
            }
            if (val != 0) {
                throw new IllegalArgumentException(value + "=" + Long.toOctalString(value) + " will not fit in octal number buffer of length " + length);
            }
            remaining = remaining2;
        }
        while (remaining >= 0) {
            buffer[offset + remaining] = TarConstants.LF_NORMAL;
            remaining--;
        }
        Arrays.fill(buffer, offset, offset + remaining + 1, TarConstants.LF_NORMAL);
    }

    private static long parseBinaryBigInteger(byte[] buffer, int offset, int length, boolean negative) {
        byte[] remainder = new byte[length - 1];
        System.arraycopy(buffer, offset + 1, remainder, 0, length - 1);
        BigInteger val = new BigInteger(remainder);
        if (negative) {
            val = val.add(NEG_1_BIG_INT).not();
        }
        if (val.bitLength() > 63) {
            throw new IllegalArgumentException("At offset " + offset + ", " + length + " byte binary number exceeds maximum signed long value");
        }
        long longValue = val.longValue();
        return negative ? -longValue : longValue;
    }

    private static long parseBinaryLong(byte[] buffer, int offset, int length, boolean negative) {
        if (length >= 9) {
            throw new IllegalArgumentException("At offset " + offset + ", " + length + " byte binary number exceeds maximum signed long value");
        }
        long val = 0;
        for (int i = 1; i < length; i++) {
            val = (val << 8) + (buffer[offset + i] & UByte.MAX_VALUE);
        }
        if (negative) {
            val = (val - 1) ^ (((long) Math.pow(2.0d, (length - 1) * 8.0d)) - 1);
        }
        return negative ? -val : val;
    }

    public static boolean parseBoolean(byte[] buffer, int offset) {
        return buffer[offset] == 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static List<TarArchiveStructSparse> parseFromPAX01SparseHeaders(String sparseMap) throws IOException {
        List<TarArchiveStructSparse> sparseHeaders = new ArrayList<>();
        String[] sparseHeaderStrings = sparseMap.split(CollectionUtils.COMMA);
        if (sparseHeaderStrings.length % 2 == 1) {
            throw new IOException("Corrupted TAR archive. Bad format in GNU.sparse.map PAX Header");
        }
        for (int i = 0; i < sparseHeaderStrings.length; i += 2) {
            long sparseOffset = ParsingUtils.parseLongValue(sparseHeaderStrings[i]);
            if (sparseOffset < 0) {
                throw new IOException("Corrupted TAR archive. Sparse struct offset contains negative value");
            }
            long sparseNumbytes = ParsingUtils.parseLongValue(sparseHeaderStrings[i + 1]);
            if (sparseNumbytes < 0) {
                throw new IOException("Corrupted TAR archive. Sparse struct numbytes contains negative value");
            }
            sparseHeaders.add(new TarArchiveStructSparse(sparseOffset, sparseNumbytes));
        }
        return Collections.unmodifiableList(sparseHeaders);
    }

    public static String parseName(byte[] buffer, int offset, int length) {
        try {
            return parseName(buffer, offset, length, DEFAULT_ENCODING);
        } catch (IOException e) {
            try {
                return parseName(buffer, offset, length, FALLBACK_ENCODING);
            } catch (IOException ex2) {
                throw new UncheckedIOException(ex2);
            }
        }
    }

    public static String parseName(byte[] buffer, int offset, int length, ZipEncoding encoding) throws IOException {
        int len = 0;
        for (int i = offset; len < length && buffer[i] != 0; i++) {
            len++;
        }
        if (len > 0) {
            byte[] b = new byte[len];
            System.arraycopy(buffer, offset, b, 0, len);
            return encoding.decode(b);
        }
        return "";
    }

    public static long parseOctal(byte[] buffer, int offset, int length) {
        long result = 0;
        int end = offset + length;
        int start = offset;
        if (length < 2) {
            throw new IllegalArgumentException("Length " + length + " must be at least 2");
        }
        if (buffer[start] == 0) {
            return 0L;
        }
        while (start < end && buffer[start] == 32) {
            start++;
        }
        byte trailer = buffer[end - 1];
        while (start < end && (trailer == 0 || trailer == 32)) {
            end--;
            trailer = buffer[end - 1];
        }
        while (start < end) {
            byte currentByte = buffer[start];
            if (currentByte < 48 || currentByte > 55) {
                throw new IllegalArgumentException(exceptionMessage(buffer, offset, length, start, currentByte));
            }
            result = (result << 3) + (currentByte - 48);
            start++;
        }
        return result;
    }

    public static long parseOctalOrBinary(byte[] buffer, int offset, int length) {
        if ((buffer[offset] & ByteCompanionObject.MIN_VALUE) == 0) {
            return parseOctal(buffer, offset, length);
        }
        boolean negative = buffer[offset] == -1;
        if (length < 9) {
            return parseBinaryLong(buffer, offset, length, negative);
        }
        return parseBinaryBigInteger(buffer, offset, length, negative);
    }

    @Deprecated
    protected static List<TarArchiveStructSparse> parsePAX01SparseHeaders(String sparseMap) {
        try {
            return parseFromPAX01SparseHeaders(sparseMap);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex.getMessage(), ex);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static List<TarArchiveStructSparse> parsePAX1XSparseHeaders(InputStream inputStream, int recordSize) throws IOException {
        List<TarArchiveStructSparse> sparseHeaders = new ArrayList<>();
        long[] readResult = readLineOfNumberForPax1x(inputStream);
        long sparseOffset = readResult[0];
        if (sparseOffset < 0) {
            throw new IOException("Corrupted TAR archive. Negative value in sparse headers block");
        }
        long bytesRead = 0 + readResult[1];
        while (true) {
            long sparseHeadersCount = sparseOffset - 1;
            if (sparseOffset <= 0) {
                long bytesToSkip = recordSize - (bytesRead % recordSize);
                IOUtils.skip(inputStream, bytesToSkip);
                return sparseHeaders;
            }
            long[] readResult2 = readLineOfNumberForPax1x(inputStream);
            long sparseOffset2 = readResult2[0];
            if (sparseOffset2 < 0) {
                throw new IOException("Corrupted TAR archive. Sparse header block offset contains negative value");
            }
            long bytesRead2 = bytesRead + readResult2[1];
            long[] readResult3 = readLineOfNumberForPax1x(inputStream);
            long sparseNumbytes = readResult3[0];
            if (sparseNumbytes < 0) {
                throw new IOException("Corrupted TAR archive. Sparse header block numbytes contains negative value");
            }
            bytesRead = bytesRead2 + readResult3[1];
            sparseHeaders.add(new TarArchiveStructSparse(sparseOffset2, sparseNumbytes));
            sparseOffset = sparseHeadersCount;
        }
    }

    @Deprecated
    protected static Map<String, String> parsePaxHeaders(InputStream inputStream, List<TarArchiveStructSparse> sparseHeaders, Map<String, String> globalPaxHeaders) throws IOException {
        return parsePaxHeaders(inputStream, sparseHeaders, globalPaxHeaders, -1L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x01ca, code lost:
    
        throw new java.io.IOException("Failed to read Paxheader. Encountered a non-number while reading length");
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x019b, code lost:
    
        r8 = r13;
        r4 = r19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Map<java.lang.String, java.lang.String> parsePaxHeaders(java.io.InputStream r22, java.util.List<org.apache.commons.compress.archivers.tar.TarArchiveStructSparse> r23, java.util.Map<java.lang.String, java.lang.String> r24, long r25) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 492
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.tar.TarUtils.parsePaxHeaders(java.io.InputStream, java.util.List, java.util.Map, long):java.util.Map");
    }

    public static TarArchiveStructSparse parseSparse(byte[] buffer, int offset) {
        long sparseOffset = parseOctalOrBinary(buffer, offset, 12);
        long sparseNumbytes = parseOctalOrBinary(buffer, offset + 12, 12);
        return new TarArchiveStructSparse(sparseOffset, sparseNumbytes);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002b, code lost:
    
        throw new java.io.IOException("Corrupted TAR archive. Non-numeric value in sparse headers block");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static long[] readLineOfNumberForPax1x(java.io.InputStream r10) throws java.io.IOException {
        /*
            r0 = 0
            r2 = 0
        L4:
            int r4 = r10.read()
            r5 = r4
            r6 = 10
            r7 = 1
            if (r4 == r6) goto L34
            long r2 = r2 + r7
            r4 = -1
            if (r5 == r4) goto L2c
            r4 = 48
            if (r5 < r4) goto L24
            r4 = 57
            if (r5 > r4) goto L24
            r6 = 10
            long r6 = r6 * r0
            int r4 = r5 + (-48)
            long r8 = (long) r4
            long r0 = r6 + r8
            goto L4
        L24:
            java.io.IOException r4 = new java.io.IOException
            java.lang.String r6 = "Corrupted TAR archive. Non-numeric value in sparse headers block"
            r4.<init>(r6)
            throw r4
        L2c:
            java.io.IOException r4 = new java.io.IOException
            java.lang.String r6 = "Unexpected EOF when reading parse information of 1.X PAX format"
            r4.<init>(r6)
            throw r4
        L34:
            long r2 = r2 + r7
            r4 = 2
            long[] r4 = new long[r4]
            r6 = 0
            r4[r6] = r0
            r6 = 1
            r4[r6] = r2
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.tar.TarUtils.readLineOfNumberForPax1x(java.io.InputStream):long[]");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<TarArchiveStructSparse> readSparseStructs(byte[] buffer, int offset, int entries) throws IOException {
        List<TarArchiveStructSparse> sparseHeaders = new ArrayList<>();
        for (int i = 0; i < entries; i++) {
            try {
                TarArchiveStructSparse sparseHeader = parseSparse(buffer, (i * 24) + offset);
                if (sparseHeader.getOffset() < 0) {
                    throw new IOException("Corrupted TAR archive, sparse entry with negative offset");
                }
                if (sparseHeader.getNumbytes() < 0) {
                    throw new IOException("Corrupted TAR archive, sparse entry with negative numbytes");
                }
                sparseHeaders.add(sparseHeader);
            } catch (IllegalArgumentException ex) {
                throw new IOException("Corrupted TAR archive, sparse entry is invalid", ex);
            }
        }
        return Collections.unmodifiableList(sparseHeaders);
    }

    public static boolean verifyCheckSum(byte[] header) {
        long storedSum = parseOctal(header, 148, 8);
        long unsignedSum = 0;
        long signedSum = 0;
        for (int i = 0; i < header.length; i++) {
            byte b = header[i];
            if (148 <= i && i < 156) {
                b = 32;
            }
            unsignedSum += b & UByte.MAX_VALUE;
            signedSum += b;
        }
        return storedSum == unsignedSum || storedSum == signedSum;
    }

    private TarUtils() {
    }
}
