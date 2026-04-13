package org.apache.commons.io.channels;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SeekableByteChannel;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.commons.io.IOUtils;

/* loaded from: classes9.dex */
public class ByteArraySeekableByteChannel implements SeekableByteChannel {
    private static final int RESIZE_LIMIT = 1073741823;
    private volatile boolean closed;
    private byte[] data;
    private final ReentrantLock lock;
    private int position;
    private int size;

    public static ByteArraySeekableByteChannel wrap(byte[] bytes) {
        Objects.requireNonNull(bytes, "bytes");
        return new ByteArraySeekableByteChannel(bytes);
    }

    public ByteArraySeekableByteChannel() {
        this(8192);
    }

    private ByteArraySeekableByteChannel(byte[] data) {
        this.lock = new ReentrantLock();
        this.data = data;
        this.position = 0;
        this.size = data.length;
    }

    public ByteArraySeekableByteChannel(int size) {
        this.lock = new ReentrantLock();
        if (size < 0) {
            throw new IllegalArgumentException("Size must be non-negative");
        }
        this.data = new byte[size];
        this.position = 0;
        this.size = 0;
    }

    public byte[] array() {
        return this.data;
    }

    private void checkOpen() throws ClosedChannelException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
    }

    private int checkRange(long newSize, String method) {
        if (newSize < 0 || newSize > 2147483639) {
            throw new IllegalArgumentException(String.format("%s must be in range [0..%,d]: %,d", method, Integer.valueOf(IOUtils.SOFT_MAX_ARRAY_LENGTH), Long.valueOf(newSize)));
        }
        return (int) newSize;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.closed = true;
    }

    public long getSize() {
        return this.size;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.closed;
    }

    @Override // java.nio.channels.SeekableByteChannel
    public long position() throws ClosedChannelException {
        checkOpen();
        this.lock.lock();
        try {
            return this.position;
        } finally {
            this.lock.unlock();
        }
    }

    @Override // java.nio.channels.SeekableByteChannel
    public SeekableByteChannel position(long newPosition) throws IOException {
        checkOpen();
        int intPos = checkRange(newPosition, "position()");
        this.lock.lock();
        try {
            this.position = intPos;
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer buf) throws IOException {
        checkOpen();
        this.lock.lock();
        try {
            int wanted = buf.remaining();
            int possible = this.size - this.position;
            if (possible > 0) {
                if (wanted > possible) {
                    wanted = possible;
                }
                buf.put(this.data, this.position, wanted);
                this.position += wanted;
                return wanted;
            }
            this.lock.unlock();
            return -1;
        } finally {
            this.lock.unlock();
        }
    }

    private void resize(int newLength) {
        int len = this.data.length;
        if (len == 0) {
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
    public long size() throws ClosedChannelException {
        checkOpen();
        this.lock.lock();
        try {
            return this.size;
        } finally {
            this.lock.unlock();
        }
    }

    public byte[] toByteArray() {
        return Arrays.copyOf(this.data, this.size);
    }

    @Override // java.nio.channels.SeekableByteChannel
    public SeekableByteChannel truncate(long newSize) throws ClosedChannelException {
        checkOpen();
        int intSize = checkRange(newSize, "truncate()");
        this.lock.lock();
        try {
            if (this.size > intSize) {
                this.size = intSize;
            }
            if (this.position > intSize) {
                this.position = intSize;
            }
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.WritableByteChannel
    public int write(ByteBuffer b) throws IOException {
        checkOpen();
        this.lock.lock();
        try {
            int wanted = b.remaining();
            int possibleWithoutResize = Math.max(0, this.size - this.position);
            if (wanted > possibleWithoutResize) {
                int newSize = this.position + wanted;
                if (newSize < 0 || newSize > 2147483639) {
                    throw new OutOfMemoryError("required array size " + Integer.toUnsignedString(newSize) + " too large");
                }
                resize(newSize);
            }
            b.get(this.data, this.position, wanted);
            this.position += wanted;
            if (this.size < this.position) {
                this.size = this.position;
            }
            return wanted;
        } finally {
            this.lock.unlock();
        }
    }
}
