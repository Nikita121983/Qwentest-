package org.apache.commons.compress.compressors.lz4;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Consumer;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.lz4.BlockLZ4CompressorOutputStream;
import org.apache.commons.compress.compressors.lz77support.LZ77Compressor;
import org.apache.commons.compress.compressors.lz77support.Parameters;
import org.apache.commons.compress.utils.ByteUtils;

/* loaded from: classes9.dex */
public class BlockLZ4CompressorOutputStream extends CompressorOutputStream<OutputStream> {
    private static final int MIN_BACK_REFERENCE_LENGTH = 4;
    private static final int MIN_OFFSET_OF_LAST_BACK_REFERENCE = 12;
    private final LZ77Compressor compressor;
    private final Deque<byte[]> expandedBlocks;
    private final byte[] oneByte;
    private final Deque<Pair> pairs;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static final class Pair {
        private int brLength;
        private int brOffset;
        private int literalLength;
        private final Deque<byte[]> literals = new LinkedList();
        private boolean written;

        Pair() {
        }

        private static int lengths(int litLength, int brLength) {
            int br = 15;
            int l = Math.min(litLength, 15);
            if (brLength < 4) {
                br = 0;
            } else if (brLength < 19) {
                br = brLength - 4;
            }
            return (l << 4) | br;
        }

        private static void writeLength(int length, OutputStream out) throws IOException {
            while (length >= 255) {
                out.write(255);
                length -= 255;
            }
            out.write(length);
        }

        byte[] addLiteral(LZ77Compressor.LiteralBlock block) {
            byte[] copy = Arrays.copyOfRange(block.getData(), block.getOffset(), block.getOffset() + block.getLength());
            this.literals.add(copy);
            this.literalLength += copy.length;
            return copy;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int backReferenceLength() {
            return this.brLength;
        }

        boolean canBeWritten(int lengthOfBlocksAfterThisPair) {
            return hasBackReference() && lengthOfBlocksAfterThisPair >= 16;
        }

        boolean hasBackReference() {
            return this.brOffset > 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean hasBeenWritten() {
            return this.written;
        }

        int length() {
            return literalLength() + this.brLength;
        }

        private int literalLength() {
            if (this.literalLength != 0) {
                return this.literalLength;
            }
            int length = 0;
            for (byte[] b : this.literals) {
                length += b.length;
            }
            this.literalLength = length;
            return length;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void prependLiteral(byte[] data) {
            this.literals.addFirst(data);
            this.literalLength += data.length;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void prependTo(Pair other) {
            Iterator<byte[]> listBackwards = this.literals.descendingIterator();
            while (listBackwards.hasNext()) {
                other.prependLiteral(listBackwards.next());
            }
        }

        void setBackReference(LZ77Compressor.BackReference block) {
            if (hasBackReference()) {
                throw new IllegalStateException();
            }
            this.brOffset = block.getOffset();
            this.brLength = block.getLength();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Pair splitWithNewBackReferenceLengthOf(int newBackReferenceLength) {
            Pair p = new Pair();
            p.literals.addAll(this.literals);
            p.brOffset = this.brOffset;
            p.brLength = newBackReferenceLength;
            return p;
        }

        void writeTo(OutputStream out) throws IOException {
            int litLength = literalLength();
            out.write(lengths(litLength, this.brLength));
            if (litLength >= 15) {
                writeLength(litLength - 15, out);
            }
            for (byte[] b : this.literals) {
                out.write(b);
            }
            if (hasBackReference()) {
                ByteUtils.toLittleEndian(out, this.brOffset, 2);
                if (this.brLength - 4 >= 15) {
                    writeLength((this.brLength - 4) - 15, out);
                }
            }
            this.written = true;
        }
    }

    public static Parameters.Builder createParameterBuilder() {
        return Parameters.builder(65536).withMinBackReferenceLength(4).withMaxBackReferenceLength(65535).withMaxOffset(65535).withMaxLiteralLength(65535);
    }

    public BlockLZ4CompressorOutputStream(OutputStream out) {
        this(out, createParameterBuilder().build());
    }

    public BlockLZ4CompressorOutputStream(OutputStream out, Parameters params) {
        super(out);
        this.oneByte = new byte[1];
        this.pairs = new LinkedList();
        this.expandedBlocks = new LinkedList();
        this.compressor = new LZ77Compressor(params, new LZ77Compressor.Callback() { // from class: org.apache.commons.compress.compressors.lz4.BlockLZ4CompressorOutputStream$$ExternalSyntheticLambda0
            @Override // org.apache.commons.compress.compressors.lz77support.LZ77Compressor.Callback
            public final void accept(LZ77Compressor.Block block) {
                BlockLZ4CompressorOutputStream.this.m2063x9ebf33ca(block);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$org-apache-commons-compress-compressors-lz4-BlockLZ4CompressorOutputStream, reason: not valid java name */
    public /* synthetic */ void m2063x9ebf33ca(LZ77Compressor.Block block) throws IOException {
        switch (block.getType()) {
            case LITERAL:
                addLiteralBlock((LZ77Compressor.LiteralBlock) block);
                return;
            case BACK_REFERENCE:
                addBackReference((LZ77Compressor.BackReference) block);
                return;
            case EOD:
                writeFinalLiteralBlock();
                return;
            default:
                return;
        }
    }

    private void addBackReference(LZ77Compressor.BackReference block) throws IOException {
        Pair last = writeBlocksAndReturnUnfinishedPair(block.getLength());
        last.setBackReference(block);
        recordBackReference(block);
        clearUnusedBlocksAndPairs();
    }

    private void addLiteralBlock(LZ77Compressor.LiteralBlock block) throws IOException {
        Pair last = writeBlocksAndReturnUnfinishedPair(block.getLength());
        recordLiteral(last.addLiteral(block));
        clearUnusedBlocksAndPairs();
    }

    private void clearUnusedBlocks() {
        int blockLengths = 0;
        int blocksToKeep = 0;
        for (byte[] b : this.expandedBlocks) {
            blocksToKeep++;
            blockLengths += b.length;
            if (blockLengths >= 65536) {
                break;
            }
        }
        int size = this.expandedBlocks.size();
        for (int i = blocksToKeep; i < size; i++) {
            this.expandedBlocks.removeLast();
        }
    }

    private void clearUnusedBlocksAndPairs() {
        clearUnusedBlocks();
        clearUnusedPairs();
    }

    private void clearUnusedPairs() {
        int pairLengths = 0;
        int pairsToKeep = 0;
        Iterator<Pair> it = this.pairs.descendingIterator();
        while (it.hasNext()) {
            Pair p = it.next();
            pairsToKeep++;
            pairLengths += p.length();
            if (pairLengths >= 65536) {
                break;
            }
        }
        int size = this.pairs.size();
        for (int i = pairsToKeep; i < size; i++) {
            Pair p2 = this.pairs.peekFirst();
            if (p2.hasBeenWritten()) {
                this.pairs.removeFirst();
            } else {
                return;
            }
        }
    }

    @Override // org.apache.commons.compress.CompressFilterOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            finish();
        } finally {
            super.close();
        }
    }

    private byte[] expand(int offset, int length) {
        byte[] expanded = new byte[length];
        if (offset == 1) {
            byte[] block = this.expandedBlocks.peekFirst();
            byte b = block[block.length - 1];
            if (b != 0) {
                Arrays.fill(expanded, b);
            }
        } else {
            expandFromList(expanded, offset, length);
        }
        return expanded;
    }

    private void expandFromList(byte[] expanded, int offset, int length) {
        int copyOffset;
        int blockOffset;
        int offsetRemaining = offset;
        int lengthRemaining = length;
        int writeOffset = 0;
        while (lengthRemaining > 0) {
            byte[] block = null;
            if (offsetRemaining > 0) {
                int blockOffset2 = 0;
                Iterator<byte[]> it = this.expandedBlocks.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    byte[] b = it.next();
                    if (b.length + blockOffset2 >= offsetRemaining) {
                        block = b;
                        break;
                    }
                    blockOffset2 += b.length;
                }
                if (block == null) {
                    throw new IllegalStateException("Failed to find a block containing offset " + offset);
                }
                copyOffset = (block.length + blockOffset2) - offsetRemaining;
                blockOffset = Math.min(lengthRemaining, block.length - copyOffset);
            } else {
                block = expanded;
                copyOffset = -offsetRemaining;
                blockOffset = Math.min(lengthRemaining, writeOffset + offsetRemaining);
            }
            System.arraycopy(block, copyOffset, expanded, writeOffset, blockOffset);
            offsetRemaining -= blockOffset;
            lengthRemaining -= blockOffset;
            writeOffset += blockOffset;
        }
    }

    @Override // org.apache.commons.compress.CompressFilterOutputStream
    public void finish() throws IOException {
        if (!isFinished()) {
            this.compressor.finish();
            super.finish();
        }
    }

    public void prefill(byte[] data, int off, int len) {
        if (len > 0) {
            byte[] b = Arrays.copyOfRange(data, off, off + len);
            this.compressor.prefill(b);
            recordLiteral(b);
        }
    }

    private void recordBackReference(LZ77Compressor.BackReference block) {
        this.expandedBlocks.addFirst(expand(block.getOffset(), block.getLength()));
    }

    private void recordLiteral(byte[] b) {
        this.expandedBlocks.addFirst(b);
    }

    private void rewriteLastPairs() {
        LinkedList<Pair> lastPairs = new LinkedList<>();
        LinkedList<Integer> pairLength = new LinkedList<>();
        int offset = 0;
        Iterator<Pair> it = this.pairs.descendingIterator();
        while (it.hasNext()) {
            Pair p = it.next();
            if (p.hasBeenWritten()) {
                break;
            }
            int len = p.length();
            pairLength.addFirst(Integer.valueOf(len));
            lastPairs.addFirst(p);
            offset += len;
            if (offset >= 12) {
                break;
            }
        }
        final Deque<Pair> deque = this.pairs;
        Objects.requireNonNull(deque);
        lastPairs.forEach(new Consumer() { // from class: org.apache.commons.compress.compressors.lz4.BlockLZ4CompressorOutputStream$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                deque.remove((BlockLZ4CompressorOutputStream.Pair) obj);
            }
        });
        int lastPairsSize = lastPairs.size();
        int toExpand = 0;
        for (int i = 1; i < lastPairsSize; i++) {
            toExpand += pairLength.get(i).intValue();
        }
        Pair replacement = new Pair();
        if (toExpand > 0) {
            replacement.prependLiteral(expand(toExpand, toExpand));
        }
        int brLen = 0;
        Pair splitCandidate = lastPairs.get(0);
        int stillNeeded = 12 - toExpand;
        if (splitCandidate.hasBackReference()) {
            brLen = splitCandidate.backReferenceLength();
        }
        if (splitCandidate.hasBackReference() && brLen >= stillNeeded + 4) {
            replacement.prependLiteral(expand(toExpand + stillNeeded, stillNeeded));
            this.pairs.add(splitCandidate.splitWithNewBackReferenceLengthOf(brLen - stillNeeded));
        } else {
            if (splitCandidate.hasBackReference()) {
                replacement.prependLiteral(expand(toExpand + brLen, brLen));
            }
            splitCandidate.prependTo(replacement);
        }
        this.pairs.add(replacement);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] data, int off, int len) throws IOException {
        this.compressor.compress(data, off, len);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int b) throws IOException {
        this.oneByte[0] = (byte) (b & 255);
        write(this.oneByte);
    }

    private Pair writeBlocksAndReturnUnfinishedPair(int length) throws IOException {
        writeWritablePairs(length);
        Pair last = this.pairs.peekLast();
        if (last == null || last.hasBackReference()) {
            Pair last2 = new Pair();
            this.pairs.addLast(last2);
            return last2;
        }
        return last;
    }

    private void writeFinalLiteralBlock() throws IOException {
        rewriteLastPairs();
        for (Pair p : this.pairs) {
            if (!p.hasBeenWritten()) {
                p.writeTo(this.out);
            }
        }
        this.pairs.clear();
    }

    private void writeWritablePairs(int lengthOfBlocksAfterLastPair) throws IOException {
        int unwrittenLength = lengthOfBlocksAfterLastPair;
        Iterator<Pair> it = this.pairs.descendingIterator();
        while (it.hasNext()) {
            Pair p = it.next();
            if (p.hasBeenWritten()) {
                break;
            } else {
                unwrittenLength += p.length();
            }
        }
        for (Pair p2 : this.pairs) {
            if (!p2.hasBeenWritten()) {
                unwrittenLength -= p2.length();
                if (p2.canBeWritten(unwrittenLength)) {
                    p2.writeTo(this.out);
                } else {
                    return;
                }
            }
        }
    }
}
