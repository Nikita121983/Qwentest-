package org.apache.commons.compress.utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.util.function.Supplier;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.NullOutputStream;

/* loaded from: classes9.dex */
public final class IOUtils {
    public static final LinkOption[] EMPTY_LINK_OPTIONS = new LinkOption[0];

    @Deprecated
    public static void closeQuietly(Closeable c) {
        org.apache.commons.io.IOUtils.closeQuietly(c);
    }

    @Deprecated
    public static void copy(File sourceFile, OutputStream outputStream) throws IOException {
        FileUtils.copyFile(sourceFile, outputStream);
    }

    @Deprecated
    public static long copy(InputStream input, OutputStream output) throws IOException {
        return org.apache.commons.io.IOUtils.copy(input, output);
    }

    @Deprecated
    public static long copy(InputStream input, OutputStream output, int bufferSize) throws IOException {
        return org.apache.commons.io.IOUtils.copy(input, output, bufferSize);
    }

    @Deprecated
    public static long copyRange(InputStream input, long len, OutputStream output) throws IOException {
        return org.apache.commons.io.IOUtils.copyLarge(input, output, 0L, len);
    }

    @Deprecated
    public static long copyRange(InputStream input, long length, OutputStream output, int bufferSize) throws IOException {
        if (bufferSize < 1) {
            throw new IllegalArgumentException("bufferSize must be bigger than 0");
        }
        byte[] buffer = new byte[(int) Math.min(bufferSize, Math.max(0L, length))];
        return org.apache.commons.io.IOUtils.copyLarge(input, output != null ? output : NullOutputStream.INSTANCE, 0L, length, buffer);
    }

    @Deprecated
    public static int read(File file, byte[] array) throws IOException {
        InputStream inputStream = Files.newInputStream(file.toPath(), new OpenOption[0]);
        try {
            int readFully = readFully(inputStream, array, 0, array.length);
            if (inputStream != null) {
                inputStream.close();
            }
            return readFully;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static int readFully(InputStream input, byte[] array) throws IOException {
        return readFully(input, array, 0, array.length);
    }

    public static int readFully(InputStream input, byte[] array, int offset, int length) throws IOException {
        if (length < 0 || offset < 0 || length + offset > array.length || length + offset < 0) {
            throw new IndexOutOfBoundsException();
        }
        return org.apache.commons.io.IOUtils.read(input, array, offset, length);
    }

    public static void readFully(ReadableByteChannel channel, ByteBuffer byteBuffer) throws IOException {
        int expectedLength = byteBuffer.remaining();
        int read = org.apache.commons.io.IOUtils.read(channel, byteBuffer);
        if (read < expectedLength) {
            throw new EOFException();
        }
    }

    public static byte[] readRange(InputStream input, int length) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        org.apache.commons.io.IOUtils.copyLarge(input, output, 0L, length);
        return output.toByteArray();
    }

    public static byte[] readRange(ReadableByteChannel input, int length) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ByteBuffer byteBuffer = ByteBuffer.allocate(Math.min(length, 8192));
        int read = 0;
        while (read < length) {
            byteBuffer.limit(Math.min(length - read, byteBuffer.capacity()));
            int readCount = input.read(byteBuffer);
            if (readCount <= 0) {
                break;
            }
            output.write(byteBuffer.array(), 0, readCount);
            byteBuffer.rewind();
            read += readCount;
        }
        return output.toByteArray();
    }

    public static long skip(InputStream input, long toSkip) throws IOException {
        return org.apache.commons.io.IOUtils.skip(input, toSkip, new Supplier() { // from class: org.apache.commons.compress.utils.IOUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                byte[] byteArray;
                byteArray = org.apache.commons.io.IOUtils.byteArray();
                return byteArray;
            }
        });
    }

    @Deprecated
    public static byte[] toByteArray(InputStream input) throws IOException {
        return org.apache.commons.io.IOUtils.toByteArray(input);
    }

    private IOUtils() {
    }
}
