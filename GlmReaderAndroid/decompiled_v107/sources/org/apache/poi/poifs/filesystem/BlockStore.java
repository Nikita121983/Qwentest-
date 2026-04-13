package org.apache.poi.poifs.filesystem;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.poi.poifs.storage.BATBlock;

/* loaded from: classes10.dex */
public abstract class BlockStore {
    /* JADX INFO: Access modifiers changed from: protected */
    public abstract ByteBuffer createBlockIfNeeded(int i) throws IOException;

    protected abstract BATBlock.BATBlockAndIndex getBATBlockAndIndex(int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract ByteBuffer getBlockAt(int i) throws IOException;

    protected abstract int getBlockStoreBlockSize();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract ChainLoopDetector getChainLoopDetector() throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int getFreeBlock() throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int getNextBlock(int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void releaseBuffer(ByteBuffer byteBuffer);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void setNextBlock(int i, int i2);

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes10.dex */
    public class ChainLoopDetector {
        private final boolean[] used_blocks;

        /* JADX INFO: Access modifiers changed from: protected */
        public ChainLoopDetector(long rawSize) {
            if (rawSize < 0) {
                throw new IllegalArgumentException("Cannot create a ChainLoopDetector with negative size, but had: " + rawSize);
            }
            int blkSize = BlockStore.this.getBlockStoreBlockSize();
            int numBlocks = (int) (rawSize / blkSize);
            this.used_blocks = new boolean[rawSize % ((long) blkSize) != 0 ? numBlocks + 1 : numBlocks];
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void claim(int offset) {
            if (offset >= this.used_blocks.length) {
                return;
            }
            if (this.used_blocks[offset]) {
                throw new IllegalStateException("Potential loop detected - Block " + offset + " was already claimed but was just requested again");
            }
            this.used_blocks[offset] = true;
        }
    }
}
