package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/* loaded from: classes9.dex */
final class ZipIoUtil {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeAll(FileChannel channel, ByteBuffer buffer, long position) throws IOException {
        long currentPos = position;
        while (buffer.hasRemaining()) {
            int remaining = buffer.remaining();
            int written = channel.write(buffer, currentPos);
            if (written == 0) {
                Thread.yield();
            } else {
                if (written < 0) {
                    throw new IOException("Failed to write all bytes in the buffer for channel=" + channel + ", length=" + remaining + ", written=" + written);
                }
                currentPos += written;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeAll(WritableByteChannel channel, ByteBuffer buffer) throws IOException {
        while (buffer.hasRemaining()) {
            int remaining = buffer.remaining();
            int written = channel.write(buffer);
            if (written == 0) {
                Thread.yield();
            } else if (written < 0) {
                throw new IOException("Failed to write all bytes in the buffer for channel=" + channel + ", length=" + remaining + ", written=" + written);
            }
        }
    }

    private ZipIoUtil() {
    }
}
