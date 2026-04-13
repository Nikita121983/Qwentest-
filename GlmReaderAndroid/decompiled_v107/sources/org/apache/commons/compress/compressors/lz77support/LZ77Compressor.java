package org.apache.commons.compress.compressors.lz77support;

import java.io.IOException;
import java.util.Objects;
import kotlin.UByte;
import org.apache.commons.lang3.ArrayFill;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes9.dex */
public class LZ77Compressor {
    private static final int HASH_MASK = 32767;
    private static final int HASH_SIZE = 32768;
    private static final int H_SHIFT = 5;
    private static final int NO_MATCH = -1;
    static final int NUMBER_OF_BYTES_IN_HASH = 3;
    private int blockStart;
    private final Callback callback;
    private int currentPosition;
    private final int[] head;
    private boolean initialized;
    private int insertHash;
    private int lookahead;
    private int matchStart = -1;
    private int missedInserts;
    private final Parameters params;
    private final int[] prev;
    private final int wMask;
    private final byte[] window;

    /* loaded from: classes9.dex */
    public interface Callback {
        void accept(Block block) throws IOException;
    }

    /* loaded from: classes9.dex */
    public static abstract class AbstractReference extends Block {
        private final int length;
        private final int offset;

        public AbstractReference(Block.BlockType blockType, int offset, int length) {
            super(blockType);
            this.offset = offset;
            this.length = length;
        }

        public int getLength() {
            return this.length;
        }

        public int getOffset() {
            return this.offset;
        }

        @Override // org.apache.commons.compress.compressors.lz77support.LZ77Compressor.Block
        public String toString() {
            return super.toString() + " with offset " + this.offset + " and length " + this.length;
        }
    }

    /* loaded from: classes9.dex */
    public static final class BackReference extends AbstractReference {
        public BackReference(int offset, int length) {
            super(Block.BlockType.BACK_REFERENCE, offset, length);
        }
    }

    /* loaded from: classes9.dex */
    public static abstract class Block {
        private final BlockType type;

        /* loaded from: classes9.dex */
        public enum BlockType {
            LITERAL,
            BACK_REFERENCE,
            EOD
        }

        @Deprecated
        public Block() {
            this.type = null;
        }

        protected Block(BlockType type) {
            this.type = (BlockType) Objects.requireNonNull(type);
        }

        public BlockType getType() {
            return this.type;
        }

        public String toString() {
            return getClass().getSimpleName() + StringUtils.SPACE + getType();
        }
    }

    /* loaded from: classes9.dex */
    public static final class EOD extends Block {
        private static final EOD INSTANCE = new EOD();

        public EOD() {
            super(Block.BlockType.EOD);
        }
    }

    /* loaded from: classes9.dex */
    public static final class LiteralBlock extends AbstractReference {
        private final byte[] data;

        public LiteralBlock(byte[] data, int offset, int length) {
            super(Block.BlockType.LITERAL, offset, length);
            this.data = data;
        }

        public byte[] getData() {
            return this.data;
        }
    }

    public LZ77Compressor(Parameters params, Callback callback) {
        Objects.requireNonNull(params, "params");
        Objects.requireNonNull(callback, "callback");
        this.params = params;
        this.callback = callback;
        int wSize = params.getWindowSize();
        this.window = new byte[wSize * 2];
        this.wMask = wSize - 1;
        this.head = ArrayFill.fill(new int[32768], -1);
        this.prev = new int[wSize];
    }

    private void catchUpMissedInserts() {
        while (this.missedInserts > 0) {
            int i = this.currentPosition;
            int i2 = this.missedInserts;
            this.missedInserts = i2 - 1;
            insertString(i - i2);
        }
    }

    private void compress() throws IOException {
        int minMatch = this.params.getMinBackReferenceLength();
        boolean lazy = this.params.getLazyMatching();
        int lazyThreshold = this.params.getLazyMatchingThreshold();
        while (this.lookahead >= minMatch) {
            catchUpMissedInserts();
            int matchLength = 0;
            int hashHead = insertString(this.currentPosition);
            if (hashHead != -1 && hashHead - this.currentPosition <= this.params.getMaxOffset()) {
                matchLength = longestMatch(hashHead);
                if (lazy && matchLength <= lazyThreshold && this.lookahead > minMatch) {
                    matchLength = longestMatchForNextPosition(matchLength);
                }
            }
            if (matchLength >= minMatch) {
                if (this.blockStart != this.currentPosition) {
                    flushLiteralBlock();
                    this.blockStart = -1;
                }
                flushBackReference(matchLength);
                insertStringsInMatch(matchLength);
                this.lookahead -= matchLength;
                this.currentPosition += matchLength;
                this.blockStart = this.currentPosition;
            } else {
                this.lookahead--;
                this.currentPosition++;
                if (this.currentPosition - this.blockStart >= this.params.getMaxLiteralLength()) {
                    flushLiteralBlock();
                    this.blockStart = this.currentPosition;
                }
            }
        }
    }

    public void compress(byte[] data) throws IOException {
        compress(data, 0, data.length);
    }

    public void compress(byte[] data, int off, int len) throws IOException {
        int wSize = this.params.getWindowSize();
        while (len > wSize) {
            doCompress(data, off, wSize);
            off += wSize;
            len -= wSize;
        }
        if (len > 0) {
            doCompress(data, off, len);
        }
    }

    private void doCompress(byte[] data, int off, int len) throws IOException {
        int spaceLeft = (this.window.length - this.currentPosition) - this.lookahead;
        if (len > spaceLeft) {
            slide();
        }
        System.arraycopy(data, off, this.window, this.currentPosition + this.lookahead, len);
        this.lookahead += len;
        if (!this.initialized && this.lookahead >= this.params.getMinBackReferenceLength()) {
            initialize();
        }
        if (this.initialized) {
            compress();
        }
    }

    public void finish() throws IOException {
        if (this.blockStart != this.currentPosition || this.lookahead > 0) {
            this.currentPosition += this.lookahead;
            flushLiteralBlock();
        }
        this.callback.accept(EOD.INSTANCE);
    }

    private void flushBackReference(int matchLength) throws IOException {
        this.callback.accept(new BackReference(this.currentPosition - this.matchStart, matchLength));
    }

    private void flushLiteralBlock() throws IOException {
        this.callback.accept(new LiteralBlock(this.window, this.blockStart, this.currentPosition - this.blockStart));
    }

    private void initialize() {
        for (int i = 0; i < 2; i++) {
            this.insertHash = nextHash(this.insertHash, this.window[i]);
        }
        this.initialized = true;
    }

    private int insertString(int pos) {
        this.insertHash = nextHash(this.insertHash, this.window[(pos - 1) + 3]);
        int hashHead = this.head[this.insertHash];
        this.prev[this.wMask & pos] = hashHead;
        this.head[this.insertHash] = pos;
        return hashHead;
    }

    private void insertStringsInMatch(int matchLength) {
        int stop = Math.min(matchLength - 1, this.lookahead - 3);
        for (int i = 1; i <= stop; i++) {
            insertString(this.currentPosition + i);
        }
        int i2 = matchLength - stop;
        this.missedInserts = i2 - 1;
    }

    private int longestMatch(int matchHead) {
        int minLength = this.params.getMinBackReferenceLength();
        int longestMatchLength = minLength - 1;
        int maxPossibleLength = Math.min(this.params.getMaxBackReferenceLength(), this.lookahead);
        int minIndex = Math.max(0, this.currentPosition - this.params.getMaxOffset());
        int niceBackReferenceLength = Math.min(maxPossibleLength, this.params.getNiceBackReferenceLength());
        int maxCandidates = this.params.getMaxCandidates();
        for (int candidates = 0; candidates < maxCandidates && matchHead >= minIndex; candidates++) {
            int currentLength = 0;
            for (int i = 0; i < maxPossibleLength && this.window[matchHead + i] == this.window[this.currentPosition + i]; i++) {
                currentLength++;
            }
            if (currentLength > longestMatchLength) {
                longestMatchLength = currentLength;
                this.matchStart = matchHead;
                if (currentLength >= niceBackReferenceLength) {
                    break;
                }
            }
            matchHead = this.prev[this.wMask & matchHead];
        }
        return longestMatchLength;
    }

    private int longestMatchForNextPosition(int prevMatchLength) {
        int prevMatchStart = this.matchStart;
        int prevInsertHash = this.insertHash;
        this.lookahead--;
        this.currentPosition++;
        int hashHead = insertString(this.currentPosition);
        int prevHashHead = this.prev[this.currentPosition & this.wMask];
        int matchLength = longestMatch(hashHead);
        if (matchLength <= prevMatchLength) {
            this.matchStart = prevMatchStart;
            this.head[this.insertHash] = prevHashHead;
            this.insertHash = prevInsertHash;
            this.currentPosition--;
            this.lookahead++;
            return prevMatchLength;
        }
        return matchLength;
    }

    private int nextHash(int oldHash, byte nextByte) {
        int nextVal = nextByte & UByte.MAX_VALUE;
        return ((oldHash << 5) ^ nextVal) & HASH_MASK;
    }

    public void prefill(byte[] data) {
        if (this.currentPosition != 0 || this.lookahead != 0) {
            throw new IllegalStateException("The compressor has already started to accept data, can't prefill anymore");
        }
        int len = Math.min(this.params.getWindowSize(), data.length);
        System.arraycopy(data, data.length - len, this.window, 0, len);
        if (len >= 3) {
            initialize();
            int stop = (len - 3) + 1;
            for (int i = 0; i < stop; i++) {
                insertString(i);
            }
            this.missedInserts = 2;
        } else {
            this.missedInserts = len;
        }
        this.currentPosition = len;
        this.blockStart = len;
    }

    private void slide() throws IOException {
        int wSize = this.params.getWindowSize();
        if (this.blockStart != this.currentPosition && this.blockStart < wSize) {
            flushLiteralBlock();
            this.blockStart = this.currentPosition;
        }
        System.arraycopy(this.window, wSize, this.window, 0, wSize);
        this.currentPosition -= wSize;
        this.matchStart -= wSize;
        this.blockStart -= wSize;
        int i = 0;
        while (true) {
            int i2 = -1;
            if (i >= 32768) {
                break;
            }
            int h = this.head[i];
            int[] iArr = this.head;
            if (h >= wSize) {
                i2 = h - wSize;
            }
            iArr[i] = i2;
            i++;
        }
        for (int i3 = 0; i3 < wSize; i3++) {
            int p = this.prev[i3];
            this.prev[i3] = p >= wSize ? p - wSize : -1;
        }
    }
}
