package org.apache.commons.compress.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SeekableByteChannel;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes9.dex */
public class SeekableInMemoryByteChannel implements SeekableByteChannel {
    private static final int NAIVE_RESIZE_LIMIT = 1073741823;
    private final AtomicBoolean closed;
    private byte[] data;
    private int position;
    private int size;

    public SeekableInMemoryByteChannel() {
        this(ByteUtils.EMPTY_BYTE_ARRAY);
    }

    public SeekableInMemoryByteChannel(byte[] data) {
        this.closed = new AtomicBoolean();
        this.data = data;
        this.size = data.length;
    }

    public SeekableInMemoryByteChannel(int size) {
        this(new byte[size]);
    }

    public byte[] array() {
        return this.data;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.closed.set(true);
    }

    private void ensureOpen() throws ClosedChannelException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.closed.get();
    }

    @Override // java.nio.channels.SeekableByteChannel
    public long position() {
        return this.position;
    }

    @Override // java.nio.channels.SeekableByteChannel
    public SeekableByteChannel position(long newPosition) throws IOException {
        ensureOpen();
        if (newPosition < 0 || newPosition > 2147483647L) {
            throw new IOException("Position must be in range [0..2147483647]");
        }
        this.position = (int) newPosition;
        return this;
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer buf) throws IOException {
        ensureOpen();
        int wanted = buf.remaining();
        int possible = this.size - this.position;
        if (possible <= 0) {
            return -1;
        }
        if (wanted > possible) {
            wanted = possible;
        }
        buf.put(this.data, this.position, wanted);
        this.position += wanted;
        return wanted;
    }

    private void resize(int newLength) {
        int len = this.data.length;
        if (len <= 0) {
            len = 1;
        }
        if (newLength < 1073741823) {
            while (len < newLength) {
                len <<= 1;
            }
        } else {
            len = newLength;
        }
        this.data = Arrays.copyOf(this.data, len);
    }

    @Override // java.nio.channels.SeekableByteChannel
    public long size() {
        return this.size;
    }

    @Override // java.nio.channels.SeekableByteChannel
    public SeekableByteChannel truncate(long newSize) {
        if (newSize < 0 || newSize > 2147483647L) {
            throw new IllegalArgumentException("Size must be range [0..2147483647]");
        }
        if (this.size > newSize) {
            this.size = (int) newSize;
        }
        if (this.position > newSize) {
            this.position = (int) newSize;
        }
        return this;
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.WritableByteChannel
    public int write(ByteBuffer b) throws IOException {
        ensureOpen();
        int wanted = b.remaining();
        int possibleWithoutResize = this.size - this.position;
        if (wanted > possibleWithoutResize) {
            int newSize = this.position + wanted;
            if (newSize < 0) {
                resize(Integer.MAX_VALUE);
                wanted = Integer.MAX_VALUE - this.position;
            } else {
                resize(newSize);
            }
        }
        b.get(this.data, this.position, wanted);
        this.position += wanted;
        if (this.size < this.position) {
            this.size = this.position;
        }
        return wanted;
    }
}
