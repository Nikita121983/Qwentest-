package org.apache.commons.io.input.buffer;

import java.util.Objects;
import org.apache.commons.io.IOUtils;

/* loaded from: classes9.dex */
public class CircularByteBuffer {
    private final byte[] buffer;
    private int currentNumberOfBytes;
    private int endOffset;
    private int startOffset;

    public CircularByteBuffer() {
        this(8192);
    }

    public CircularByteBuffer(int size) {
        this.buffer = IOUtils.byteArray(size);
        this.startOffset = 0;
        this.endOffset = 0;
        this.currentNumberOfBytes = 0;
    }

    public void add(byte value) {
        if (this.currentNumberOfBytes >= this.buffer.length) {
            throw new IllegalStateException("No space available");
        }
        this.buffer[this.endOffset] = value;
        this.currentNumberOfBytes++;
        int i = this.endOffset + 1;
        this.endOffset = i;
        if (i == this.buffer.length) {
            this.endOffset = 0;
        }
    }

    public void add(byte[] targetBuffer, int offset, int length) {
        Objects.requireNonNull(targetBuffer, "Buffer");
        if (offset < 0 || offset >= targetBuffer.length) {
            throw new IllegalArgumentException("Illegal offset: " + offset);
        }
        if (length < 0) {
            throw new IllegalArgumentException("Illegal length: " + length);
        }
        if (this.currentNumberOfBytes + length > this.buffer.length) {
            throw new IllegalStateException("No space available");
        }
        for (int i = 0; i < length; i++) {
            this.buffer[this.endOffset] = targetBuffer[offset + i];
            int i2 = this.endOffset + 1;
            this.endOffset = i2;
            if (i2 == this.buffer.length) {
                this.endOffset = 0;
            }
        }
        int i3 = this.currentNumberOfBytes;
        this.currentNumberOfBytes = i3 + length;
    }

    public void clear() {
        this.startOffset = 0;
        this.endOffset = 0;
        this.currentNumberOfBytes = 0;
    }

    public int getCurrentNumberOfBytes() {
        return this.currentNumberOfBytes;
    }

    public int getSpace() {
        return this.buffer.length - this.currentNumberOfBytes;
    }

    public boolean hasBytes() {
        return this.currentNumberOfBytes > 0;
    }

    public boolean hasSpace() {
        return this.currentNumberOfBytes < this.buffer.length;
    }

    public boolean hasSpace(int count) {
        return this.currentNumberOfBytes + count <= this.buffer.length;
    }

    public boolean peek(byte[] sourceBuffer, int offset, int length) {
        Objects.requireNonNull(sourceBuffer, "Buffer");
        if (offset < 0 || offset >= sourceBuffer.length) {
            throw new IllegalArgumentException("Illegal offset: " + offset);
        }
        if (length < 0 || length > this.buffer.length) {
            throw new IllegalArgumentException("Illegal length: " + length);
        }
        if (length < this.currentNumberOfBytes) {
            return false;
        }
        int localOffset = this.startOffset;
        for (int i = 0; i < length; i++) {
            if (this.buffer[localOffset] != sourceBuffer[i + offset]) {
                return false;
            }
            localOffset++;
            if (localOffset == this.buffer.length) {
                localOffset = 0;
            }
        }
        return true;
    }

    public byte read() {
        if (this.currentNumberOfBytes <= 0) {
            throw new IllegalStateException("No bytes available.");
        }
        byte b = this.buffer[this.startOffset];
        this.currentNumberOfBytes--;
        int i = this.startOffset + 1;
        this.startOffset = i;
        if (i == this.buffer.length) {
            this.startOffset = 0;
        }
        return b;
    }

    public void read(byte[] targetBuffer, int targetOffset, int length) {
        Objects.requireNonNull(targetBuffer, "targetBuffer");
        if (targetOffset < 0 || targetOffset >= targetBuffer.length) {
            throw new IllegalArgumentException("Illegal offset: " + targetOffset);
        }
        if (length < 0 || length > this.buffer.length) {
            throw new IllegalArgumentException("Illegal length: " + length);
        }
        if (targetOffset + length > targetBuffer.length) {
            throw new IllegalArgumentException("The supplied byte array contains only " + targetBuffer.length + " bytes, but offset, and length would require " + ((targetOffset + length) - 1));
        }
        if (this.currentNumberOfBytes < length) {
            throw new IllegalStateException("Currently, there are only " + this.currentNumberOfBytes + "in the buffer, not " + length);
        }
        int offset = targetOffset;
        int i = 0;
        while (i < length) {
            int offset2 = offset + 1;
            targetBuffer[offset] = this.buffer[this.startOffset];
            this.currentNumberOfBytes--;
            int i2 = this.startOffset + 1;
            this.startOffset = i2;
            if (i2 == this.buffer.length) {
                this.startOffset = 0;
            }
            i++;
            offset = offset2;
        }
    }
}
