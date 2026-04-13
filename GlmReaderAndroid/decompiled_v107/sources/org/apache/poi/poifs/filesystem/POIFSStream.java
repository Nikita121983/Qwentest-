package org.apache.poi.poifs.filesystem;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.poi.poifs.filesystem.BlockStore;

/* loaded from: classes10.dex */
public class POIFSStream implements Iterable<ByteBuffer> {
    private final BlockStore blockStore;
    private OutputStream outStream;
    private int startBlock;

    public POIFSStream(BlockStore blockStore, int startBlock) {
        this.blockStore = blockStore;
        this.startBlock = startBlock;
    }

    public POIFSStream(BlockStore blockStore) {
        this.blockStore = blockStore;
        this.startBlock = -2;
    }

    public int getStartBlock() {
        return this.startBlock;
    }

    @Override // java.lang.Iterable
    public Iterator<ByteBuffer> iterator() {
        return getBlockIterator();
    }

    Iterator<ByteBuffer> getBlockIterator() {
        if (this.startBlock == -2) {
            throw new IllegalStateException("Can't read from a new stream before it has been written to");
        }
        return new StreamBlockByteBufferIterator(this.startBlock);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Iterator<Integer> getBlockOffsetIterator() {
        if (this.startBlock == -2) {
            throw new IllegalStateException("Can't read from a new stream before it has been written to");
        }
        return new StreamBlockOffsetIterator(this.startBlock);
    }

    void updateContents(byte[] contents) throws IOException {
        OutputStream os = getOutputStream();
        os.write(contents);
        os.close();
    }

    public OutputStream getOutputStream() throws IOException {
        if (this.outStream == null) {
            this.outStream = new StreamBlockByteBuffer();
        }
        return this.outStream;
    }

    public void free() throws IOException {
        BlockStore.ChainLoopDetector loopDetector = this.blockStore.getChainLoopDetector();
        free(loopDetector);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void free(BlockStore.ChainLoopDetector loopDetector) {
        int nextBlock = this.startBlock;
        while (nextBlock != -2) {
            int thisBlock = nextBlock;
            loopDetector.claim(thisBlock);
            nextBlock = this.blockStore.getNextBlock(thisBlock);
            this.blockStore.setNextBlock(thisBlock, -1);
        }
        this.startBlock = -2;
    }

    /* loaded from: classes10.dex */
    private class StreamBlockOffsetIterator implements Iterator<Integer> {
        private final BlockStore.ChainLoopDetector loopDetector;
        private int nextBlock;

        StreamBlockOffsetIterator(int firstBlock) {
            this.nextBlock = firstBlock;
            try {
                this.loopDetector = POIFSStream.this.blockStore.getChainLoopDetector();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.nextBlock != -2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Can't read past the end of the stream");
            }
            this.loopDetector.claim(this.nextBlock);
            int currentBlock = this.nextBlock;
            this.nextBlock = POIFSStream.this.blockStore.getNextBlock(this.nextBlock);
            return Integer.valueOf(currentBlock);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public class StreamBlockByteBufferIterator implements Iterator<ByteBuffer> {
        private final BlockStore.ChainLoopDetector loopDetector;
        private int nextBlock;

        StreamBlockByteBufferIterator(int firstBlock) {
            this.nextBlock = firstBlock;
            try {
                this.loopDetector = POIFSStream.this.blockStore.getChainLoopDetector();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.nextBlock != -2;
        }

        @Override // java.util.Iterator
        public ByteBuffer next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Can't read past the end of the stream");
            }
            try {
                this.loopDetector.claim(this.nextBlock);
                ByteBuffer data = POIFSStream.this.blockStore.getBlockAt(this.nextBlock);
                this.nextBlock = POIFSStream.this.blockStore.getNextBlock(this.nextBlock);
                return data;
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes10.dex */
    public class StreamBlockByteBuffer extends OutputStream {
        ByteBuffer buffer;
        BlockStore.ChainLoopDetector loopDetector;
        int nextBlock;
        byte[] oneByte = new byte[1];
        int prevBlock = -2;

        StreamBlockByteBuffer() throws IOException {
            this.loopDetector = POIFSStream.this.blockStore.getChainLoopDetector();
            this.nextBlock = POIFSStream.this.startBlock;
        }

        void createBlockIfNeeded() throws IOException {
            if (this.buffer == null || !this.buffer.hasRemaining()) {
                int thisBlock = this.nextBlock;
                if (thisBlock == -2) {
                    thisBlock = POIFSStream.this.blockStore.getFreeBlock();
                    this.loopDetector.claim(thisBlock);
                    this.nextBlock = -2;
                    if (this.prevBlock != -2) {
                        POIFSStream.this.blockStore.setNextBlock(this.prevBlock, thisBlock);
                    }
                    POIFSStream.this.blockStore.setNextBlock(thisBlock, -2);
                    if (POIFSStream.this.startBlock == -2) {
                        POIFSStream.this.startBlock = thisBlock;
                    }
                } else {
                    this.loopDetector.claim(thisBlock);
                    this.nextBlock = POIFSStream.this.blockStore.getNextBlock(thisBlock);
                }
                if (this.buffer != null) {
                    POIFSStream.this.blockStore.releaseBuffer(this.buffer);
                }
                this.buffer = POIFSStream.this.blockStore.createBlockIfNeeded(thisBlock);
                this.prevBlock = thisBlock;
            }
        }

        @Override // java.io.OutputStream
        public void write(int b) throws IOException {
            this.oneByte[0] = (byte) (b & 255);
            write(this.oneByte);
        }

        @Override // java.io.OutputStream
        public void write(byte[] b, int off, int len) throws IOException {
            if (off < 0 || off > b.length || len < 0 || off + len > b.length || off + len < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (len == 0) {
                return;
            }
            do {
                createBlockIfNeeded();
                int writeBytes = Math.min(this.buffer.remaining(), len);
                this.buffer.put(b, off, writeBytes);
                off += writeBytes;
                len -= writeBytes;
            } while (len > 0);
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            POIFSStream toFree = new POIFSStream(POIFSStream.this.blockStore, this.nextBlock);
            toFree.free(this.loopDetector);
            if (this.prevBlock != -2) {
                POIFSStream.this.blockStore.setNextBlock(this.prevBlock, -2);
            }
        }
    }
}
