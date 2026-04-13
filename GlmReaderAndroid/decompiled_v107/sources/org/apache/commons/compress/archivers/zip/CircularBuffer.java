package org.apache.commons.compress.archivers.zip;

/* loaded from: classes9.dex */
final class CircularBuffer {
    private final byte[] buffer;
    private int readIndex;
    private final int size;
    private int writeIndex;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CircularBuffer(int size) {
        this.size = size;
        this.buffer = new byte[size];
    }

    public boolean available() {
        return this.readIndex != this.writeIndex;
    }

    public void copy(int distance, int length) {
        int pos1 = this.writeIndex - distance;
        int pos2 = pos1 + length;
        for (int i = pos1; i < pos2; i++) {
            this.buffer[this.writeIndex] = this.buffer[(this.size + i) % this.size];
            this.writeIndex = (this.writeIndex + 1) % this.size;
        }
    }

    public int get() {
        if (available()) {
            int value = this.buffer[this.readIndex];
            this.readIndex = (this.readIndex + 1) % this.size;
            return value & 255;
        }
        return -1;
    }

    public void put(int value) {
        this.buffer[this.writeIndex] = (byte) value;
        this.writeIndex = (this.writeIndex + 1) % this.size;
    }
}
