package org.apache.commons.io.channels;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.util.Objects;

/* loaded from: classes9.dex */
public final class FileChannels {
    @Deprecated
    public static boolean contentEquals(FileChannel channel1, FileChannel channel2, int bufferCapacity) throws IOException {
        return contentEquals((SeekableByteChannel) channel1, (SeekableByteChannel) channel2, bufferCapacity);
    }

    public static boolean contentEquals(ReadableByteChannel channel1, ReadableByteChannel channel2, int bufferCapacity) throws IOException {
        if (Objects.equals(channel1, channel2)) {
            return true;
        }
        ByteBuffer c1Buffer = ByteBuffer.allocateDirect(bufferCapacity);
        ByteBuffer c2Buffer = ByteBuffer.allocateDirect(bufferCapacity);
        int c1NumRead = 0;
        int c2NumRead = 0;
        boolean c1Read0 = false;
        boolean c2Read0 = false;
        while (true) {
            if (!c2Read0) {
                c1NumRead = readToLimit(channel1, c1Buffer);
                c1Buffer.clear();
                c1Read0 = c1NumRead == 0;
            }
            if (!c1Read0) {
                c2NumRead = readToLimit(channel2, c2Buffer);
                c2Buffer.clear();
                c2Read0 = c2NumRead == 0;
            }
            if (c1NumRead == -1 && c2NumRead == -1) {
                return c1Buffer.equals(c2Buffer);
            }
            if (c1NumRead == 0 || c2NumRead == 0) {
                Thread.yield();
            } else if (c1NumRead != c2NumRead || !c1Buffer.equals(c2Buffer)) {
                return false;
            }
        }
    }

    public static boolean contentEquals(SeekableByteChannel channel1, SeekableByteChannel channel2, int bufferCapacity) throws IOException {
        if (Objects.equals(channel1, channel2)) {
            return true;
        }
        long size1 = size(channel1);
        long size2 = size(channel2);
        if (size1 != size2) {
            return false;
        }
        return (size1 == 0 && size2 == 0) || contentEquals((ReadableByteChannel) channel1, (ReadableByteChannel) channel2, bufferCapacity);
    }

    private static int readToLimit(ReadableByteChannel channel, ByteBuffer dst) throws IOException {
        int numRead;
        if (!dst.hasRemaining()) {
            throw new IllegalArgumentException();
        }
        int totalRead = 0;
        while (dst.hasRemaining() && (numRead = channel.read(dst)) != -1) {
            if (numRead == 0) {
                Thread.yield();
            } else {
                totalRead += numRead;
            }
        }
        if (totalRead != 0) {
            return totalRead;
        }
        return -1;
    }

    private static long size(SeekableByteChannel channel) throws IOException {
        if (channel != null) {
            return channel.size();
        }
        return 0L;
    }

    private FileChannels() {
    }
}
