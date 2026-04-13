package org.apache.poi.util;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Arrays;
import java.util.Locale;
import java.util.zip.CRC32;
import java.util.zip.Checksum;
import org.apache.commons.io.input.BoundedInputStream;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EmptyFileException;
import org.apache.poi.logging.PoiLogManager;

@Internal
/* loaded from: classes10.dex */
public final class IOUtils {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final int SKIP_BUFFER_SIZE = 2048;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) IOUtils.class);
    private static int BYTE_ARRAY_MAX_OVERRIDE = -1;
    private static int MAX_BYTE_ARRAY_INIT_SIZE = -1;

    private IOUtils() {
    }

    public static void setMaxByteArrayInitSize(int maxOverride) {
        MAX_BYTE_ARRAY_INIT_SIZE = maxOverride;
    }

    public static int getMaxByteArrayInitSize() {
        return MAX_BYTE_ARRAY_INIT_SIZE;
    }

    public static void setByteArrayMaxOverride(int maxOverride) {
        BYTE_ARRAY_MAX_OVERRIDE = maxOverride;
    }

    public static int getByteArrayMaxOverride() {
        return BYTE_ARRAY_MAX_OVERRIDE;
    }

    public static byte[] peekFirst8Bytes(InputStream stream) throws IOException, EmptyFileException {
        return peekFirstNBytes(stream, 8);
    }

    private static void checkByteSizeLimit(int length) {
        if (BYTE_ARRAY_MAX_OVERRIDE != -1 && length > BYTE_ARRAY_MAX_OVERRIDE) {
            throwRFE(length, BYTE_ARRAY_MAX_OVERRIDE);
        }
    }

    private static void checkByteSizeLimit(long length) {
        if (BYTE_ARRAY_MAX_OVERRIDE != -1 && length > BYTE_ARRAY_MAX_OVERRIDE) {
            throwRFE(length, BYTE_ARRAY_MAX_OVERRIDE);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static byte[] peekFirstNBytes(InputStream stream, int limit) throws IOException, EmptyFileException {
        checkByteSizeLimit(limit);
        stream.mark(limit);
        UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().setBufferSize(limit).get();
        try {
            copy(((BoundedInputStream.Builder) ((BoundedInputStream.Builder) BoundedInputStream.builder().setInputStream(stream)).setMaxCount(limit)).get(), bos);
            int readBytes = bos.size();
            if (readBytes == 0) {
                throw new EmptyFileException();
            }
            if (readBytes < limit) {
                bos.write(new byte[limit - readBytes]);
            }
            byte[] peekedBytes = bos.toByteArray();
            if (stream instanceof PushbackInputStream) {
                PushbackInputStream pin = (PushbackInputStream) stream;
                pin.unread(peekedBytes, 0, readBytes);
            } else {
                stream.reset();
            }
            if (bos != null) {
                bos.close();
            }
            return peekedBytes;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public static byte[] toByteArray(InputStream stream) throws IOException {
        return toByteArray(stream, Integer.MAX_VALUE);
    }

    public static byte[] toByteArray(InputStream stream, int length) throws IOException {
        return toByteArray(stream, length, Integer.MAX_VALUE);
    }

    public static byte[] toByteArray(InputStream stream, int length, int maxLength) throws IOException {
        return toByteArray(stream, length, maxLength, true, length != Integer.MAX_VALUE);
    }

    public static byte[] toByteArray(InputStream stream, long length, int maxLength) throws IOException {
        return toByteArray(stream, length > 2147483647L ? Integer.MAX_VALUE : (int) length, maxLength, true, length != 2147483647L);
    }

    public static byte[] toByteArrayWithMaxLength(InputStream stream, int maxLength) throws IOException {
        return toByteArray(stream, maxLength, maxLength, false, false);
    }

    private static byte[] toByteArray(InputStream stream, int length, int maxLength, boolean checkEOFException, boolean isLengthKnown) throws IOException {
        int readBytes;
        int derivedMaxLength = Math.max(maxLength, BYTE_ARRAY_MAX_OVERRIDE);
        if (length != Integer.MAX_VALUE || derivedMaxLength != Integer.MAX_VALUE) {
            checkLength(length, derivedMaxLength);
        }
        int derivedLen = (!isLengthKnown || length < 0) ? derivedMaxLength : Math.min(length, derivedMaxLength);
        int byteArrayInitLen = calculateByteArrayInitLength(isLengthKnown, length, derivedMaxLength);
        UnsynchronizedByteArrayOutputStream baos = UnsynchronizedByteArrayOutputStream.builder().setBufferSize(byteArrayInitLen).get();
        try {
            byte[] buffer = new byte[4096];
            int totalBytes = 0;
            do {
                try {
                    readBytes = stream.read(buffer, 0, Math.min(4096, derivedLen - totalBytes));
                    totalBytes += Math.max(readBytes, 0);
                    if (readBytes > 0) {
                        baos.write(buffer, 0, readBytes);
                    }
                    checkByteSizeLimit(totalBytes);
                    if (totalBytes >= derivedLen) {
                        break;
                    }
                } catch (Throwable th) {
                    th = th;
                    try {
                        throw th;
                    } finally {
                    }
                }
            } while (readBytes > -1);
            if (BYTE_ARRAY_MAX_OVERRIDE < 0 && readBytes > -1 && !isLengthKnown && stream.read() >= 0) {
                throwRecordTruncationException(derivedMaxLength);
            }
            if (checkEOFException && length >= 0 && derivedLen != Integer.MAX_VALUE && totalBytes < derivedLen) {
                throw new EOFException("unexpected EOF - expected len: " + derivedLen + " - actual len: " + totalBytes);
            }
            byte[] byteArray = baos.toByteArray();
            if (baos != null) {
                baos.close();
            }
            return byteArray;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    static int calculateByteArrayInitLength(boolean isLengthKnown, int length, int maxLength) {
        int derivedLen = Math.min(length, maxLength);
        int bufferLen = isLengthKnown ? derivedLen : Math.min(4096, derivedLen);
        if (MAX_BYTE_ARRAY_INIT_SIZE > 0 && bufferLen > MAX_BYTE_ARRAY_INIT_SIZE) {
            return MAX_BYTE_ARRAY_INIT_SIZE;
        }
        return bufferLen;
    }

    private static void checkLength(long length, int maxLength) {
        if (BYTE_ARRAY_MAX_OVERRIDE > 0) {
            if (length > BYTE_ARRAY_MAX_OVERRIDE) {
                throwRFE(length, BYTE_ARRAY_MAX_OVERRIDE);
            }
        } else if (length > maxLength) {
            throwRFE(length, maxLength);
        }
    }

    public static byte[] toByteArray(ByteBuffer buffer, int length) {
        if (buffer.hasArray() && buffer.arrayOffset() == 0) {
            return buffer.array();
        }
        checkByteSizeLimit(length);
        byte[] data = new byte[length];
        buffer.get(data);
        return data;
    }

    public static int readFully(InputStream in, byte[] b) throws IOException {
        return readFully(in, b, 0, b.length);
    }

    public static int readFully(InputStream in, byte[] b, int off, int len) throws IOException {
        int total = 0;
        do {
            int got = in.read(b, off + total, len - total);
            if (got < 0) {
                if (total == 0) {
                    return -1;
                }
                return total;
            }
            total += got;
        } while (total != len);
        return total;
    }

    public static int readFully(ReadableByteChannel channel, ByteBuffer b) throws IOException {
        int total = 0;
        do {
            int got = channel.read(b);
            if (got < 0) {
                if (total == 0) {
                    return -1;
                }
                return total;
            }
            total += got;
            if (total == b.capacity()) {
                break;
            }
        } while (b.position() != b.capacity());
        return total;
    }

    public static long copy(InputStream inp, OutputStream out) throws IOException {
        return copy(inp, out, -1L);
    }

    public static long copy(InputStream inp, OutputStream out, long limit) throws IOException {
        byte[] buff = new byte[4096];
        long totalCount = 0;
        int readBytes = -1;
        while (true) {
            int todoBytes = (int) (limit >= 0 ? Math.min(limit - totalCount, 4096L) : 4096L);
            if (todoBytes > 0 && (readBytes = inp.read(buff, 0, todoBytes)) > 0) {
                out.write(buff, 0, readBytes);
                totalCount += readBytes;
            }
            if (readBytes < 0 || (limit != -1 && totalCount >= limit)) {
                break;
            }
        }
        return totalCount;
    }

    public static long copy(InputStream srcStream, File destFile) throws IOException {
        File destDirectory = destFile.getParentFile();
        if (!destDirectory.exists() && !destDirectory.mkdirs()) {
            throw new IllegalStateException("Can't create destination directory: " + destDirectory);
        }
        OutputStream destStream = Files.newOutputStream(destFile.toPath(), new OpenOption[0]);
        try {
            long copy = copy(srcStream, destStream);
            if (destStream != null) {
                destStream.close();
            }
            return copy;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (destStream != null) {
                    try {
                        destStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public static long calculateChecksum(byte[] data) {
        Checksum sum = new CRC32();
        sum.update(data, 0, data.length);
        return sum.getValue();
    }

    public static long calculateChecksum(InputStream stream) throws IOException {
        Checksum sum = new CRC32();
        byte[] buf = new byte[4096];
        while (true) {
            int count = stream.read(buf);
            if (count != -1) {
                if (count > 0) {
                    sum.update(buf, 0, count);
                }
            } else {
                return sum.getValue();
            }
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception exc) {
            LOG.atError().withThrowable(exc).log("Unable to close resource");
        }
    }

    public static long skipFully(InputStream input, long toSkip) throws IOException {
        if (toSkip < 0) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
        }
        if (toSkip == 0) {
            return 0L;
        }
        byte[] skipBuffer = new byte[2048];
        long remain = toSkip;
        while (remain > 0) {
            long n = input.read(skipBuffer, 0, (int) Math.min(remain, 2048L));
            if (n < 0) {
                break;
            }
            remain -= n;
        }
        if (toSkip == remain) {
            return -1L;
        }
        return toSkip - remain;
    }

    public static byte[] safelyAllocate(long length, int maxLength) {
        safelyAllocateCheck(length, maxLength);
        checkByteSizeLimit(length);
        return new byte[(int) length];
    }

    public static void safelyAllocateCheck(long length, int maxLength) {
        if (length < 0) {
            throw new RecordFormatException("Can't allocate an array of length < 0, but had " + length + " and " + maxLength);
        }
        if (length > 2147483647L) {
            throw new RecordFormatException("Can't allocate an array > 2147483647");
        }
        checkLength(length, maxLength);
    }

    public static byte[] safelyClone(byte[] src, int offset, int length, int maxLength) {
        if (src == null) {
            return null;
        }
        if (offset < 0 || length < 0 || maxLength < 0) {
            throw new RecordFormatException("Invalid offset/length specified: offset: " + offset + ", lenght: " + length + ", maxLength: " + maxLength);
        }
        int realLength = Math.min(src.length - offset, length);
        safelyAllocateCheck(realLength, maxLength);
        return Arrays.copyOfRange(src, offset, offset + realLength);
    }

    public static int readByte(InputStream is) throws IOException {
        int b = is.read();
        if (b == -1) {
            throw new EOFException();
        }
        return b;
    }

    public static File newFile(File parent, String name) throws IOException {
        File file = new File(parent, name);
        if (!file.toPath().toAbsolutePath().normalize().startsWith(parent.toPath().toAbsolutePath().normalize())) {
            throw new IOException(String.format(Locale.ROOT, "Failing due to path traversal in `%s`", name));
        }
        return file;
    }

    private static void throwRFE(long length, int maxLength) {
        throw new RecordFormatException(String.format(Locale.ROOT, "Tried to allocate an array of length %,d, but the maximum length for this record type is %,d.%nIf the file is not corrupt and not large, please open an issue on bugzilla to request %nincreasing the maximum allowable size for this record type.%nYou can set a higher override value with IOUtils.setByteArrayMaxOverride()", Long.valueOf(length), Integer.valueOf(maxLength)));
    }

    private static void throwRecordTruncationException(int maxLength) {
        throw new RecordFormatException(String.format(Locale.ROOT, "Tried to read data but the maximum length for this record type is %,d.%nIf the file is not corrupt and not large, please open an issue on bugzilla to request %nincreasing the maximum allowable size for this record type.%nYou can set a higher override value with IOUtils.setByteArrayMaxOverride()", Integer.valueOf(maxLength)));
    }
}
