package org.apache.commons.compress.compressors.deflate64;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.Arrays;
import org.apache.commons.compress.utils.BitInputStream;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.compress.utils.ExactMath;
import org.apache.commons.lang3.ArrayFill;
import org.apache.poi.hssf.record.ArrayRecord;
import org.apache.poi.hssf.record.DSFRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.record.chart.SeriesIndexRecord;
import org.apache.poi.hssf.record.pivottable.ExtendedPivotTableViewFieldsRecord;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class HuffmanDecoder implements Closeable {
    private static final int[] FIXED_DISTANCE;
    private boolean finalBlock;
    private final InputStream in;
    private final DecodingMemory memory;
    private BitInputStream reader;
    private DecoderState state;
    private static final short[] RUN_LENGTH_TABLE = {96, 128, 160, EscherAggregate.ST_ACTIONBUTTONINFORMATION, ExtendedFormatRecord.sid, ExtendedPivotTableViewFieldsRecord.sid, 288, 320, DSFRecord.sid, 417, 481, ArrayRecord.sid, 610, 738, 866, 994, 1123, 1379, 1635, 1891, 2148, 2660, 3172, 3684, SeriesIndexRecord.sid, 5221, 6245, 7269, EscherAggregate.ST_FLOWCHARTPREDEFINEDPROCESS};
    private static final int[] DISTANCE_TABLE = {16, 32, 48, 64, 81, 113, 146, 210, 275, TypedValues.CycleType.TYPE_ALPHA, 532, 788, 1045, 1557, 2070, 3094, 4119, 6167, 8216, 12312, 16409, 24601, 32794, 49178, 65563, 98331, 131100, 196636, 262173, 393245, 524318, 786462};
    private static final int[] CODE_LENGTHS_ORDER = {16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15};
    private static final int[] FIXED_LITERALS = new int[288];

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class BinaryTreeNode {
        private final int bits;
        BinaryTreeNode leftNode;
        int literal;
        BinaryTreeNode rightNode;

        private BinaryTreeNode(int bits) {
            this.literal = -1;
            this.bits = bits;
        }

        void leaf(int symbol) {
            this.literal = symbol;
            this.leftNode = null;
            this.rightNode = null;
        }

        BinaryTreeNode left() {
            if (this.leftNode == null && this.literal == -1) {
                this.leftNode = new BinaryTreeNode(this.bits + 1);
            }
            return this.leftNode;
        }

        BinaryTreeNode right() {
            if (this.rightNode == null && this.literal == -1) {
                this.rightNode = new BinaryTreeNode(this.bits + 1);
            }
            return this.rightNode;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static abstract class DecoderState {
        abstract int available() throws IOException;

        abstract boolean hasData();

        abstract int read(byte[] bArr, int i, int i2) throws IOException;

        abstract HuffmanState state();

        private DecoderState() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class DecodingMemory {
        private final int mask;
        private final byte[] memory;
        private int wHead;
        private boolean wrappedAround;

        private DecodingMemory() {
            this(16);
        }

        private DecodingMemory(int bits) {
            this.memory = new byte[1 << bits];
            this.mask = this.memory.length - 1;
        }

        byte add(byte b) {
            this.memory[this.wHead] = b;
            this.wHead = incCounter(this.wHead);
            return b;
        }

        void add(byte[] b, int off, int len) {
            for (int i = off; i < off + len; i++) {
                add(b[i]);
            }
        }

        private int incCounter(int counter) {
            int newCounter = (counter + 1) & this.mask;
            if (!this.wrappedAround && newCounter < counter) {
                this.wrappedAround = true;
            }
            return newCounter;
        }

        void recordToBuffer(int distance, int length, byte[] buff) {
            if (distance > this.memory.length) {
                throw new IllegalStateException("Illegal distance parameter: " + distance);
            }
            int start = (this.wHead - distance) & this.mask;
            if (!this.wrappedAround && start >= this.wHead) {
                throw new IllegalStateException("Attempt to read beyond memory: dist=" + distance);
            }
            int i = 0;
            int pos = start;
            while (i < length) {
                buff[i] = add(this.memory[pos]);
                i++;
                pos = incCounter(pos);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public final class HuffmanCodes extends DecoderState {
        private final BinaryTreeNode distanceTree;
        private boolean endOfBlock;
        private final BinaryTreeNode lengthTree;
        private byte[] runBuffer;
        private int runBufferLength;
        private int runBufferPos;
        private final HuffmanState state;

        HuffmanCodes(HuffmanState state, int[] lengths, int[] distance) {
            super();
            this.runBuffer = ByteUtils.EMPTY_BYTE_ARRAY;
            this.state = state;
            this.lengthTree = HuffmanDecoder.buildTree(lengths);
            this.distanceTree = HuffmanDecoder.buildTree(distance);
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        int available() {
            return this.runBufferLength - this.runBufferPos;
        }

        private int copyFromRunBuffer(byte[] b, int off, int len) {
            int bytesInBuffer = this.runBufferLength - this.runBufferPos;
            if (bytesInBuffer <= 0) {
                return 0;
            }
            int copiedBytes = Math.min(len, bytesInBuffer);
            System.arraycopy(this.runBuffer, this.runBufferPos, b, off, copiedBytes);
            this.runBufferPos += copiedBytes;
            return copiedBytes;
        }

        private int decodeNext(byte[] b, int off, int len) throws IOException {
            if (this.endOfBlock) {
                return -1;
            }
            int result = copyFromRunBuffer(b, off, len);
            while (result < len) {
                int symbol = HuffmanDecoder.nextSymbol(HuffmanDecoder.this.reader, this.lengthTree);
                if (symbol < 256) {
                    b[result + off] = HuffmanDecoder.this.memory.add((byte) symbol);
                    result++;
                } else if (symbol > 256) {
                    short s = HuffmanDecoder.RUN_LENGTH_TABLE[symbol - 257];
                    int runXtra = s & 31;
                    int run = ExactMath.add(s >>> 5, HuffmanDecoder.this.readBits(runXtra));
                    int distSym = HuffmanDecoder.nextSymbol(HuffmanDecoder.this.reader, this.distanceTree);
                    int distMask = HuffmanDecoder.DISTANCE_TABLE[distSym];
                    int dist = distMask >>> 4;
                    int distXtra = distMask & 15;
                    int dist2 = ExactMath.add(dist, HuffmanDecoder.this.readBits(distXtra));
                    if (this.runBuffer.length < run) {
                        this.runBuffer = new byte[run];
                    }
                    this.runBufferLength = run;
                    this.runBufferPos = 0;
                    HuffmanDecoder.this.memory.recordToBuffer(dist2, run, this.runBuffer);
                    result += copyFromRunBuffer(b, off + result, len - result);
                } else {
                    this.endOfBlock = true;
                    return result;
                }
            }
            return result;
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        boolean hasData() {
            return !this.endOfBlock;
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        int read(byte[] b, int off, int len) throws IOException {
            if (len == 0) {
                return 0;
            }
            return decodeNext(b, off, len);
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        HuffmanState state() {
            return this.endOfBlock ? HuffmanState.INITIAL : this.state;
        }
    }

    /* loaded from: classes9.dex */
    private static final class InitialState extends DecoderState {
        private InitialState() {
            super();
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        int available() {
            return 0;
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        boolean hasData() {
            return false;
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        int read(byte[] b, int off, int len) throws IOException {
            if (len == 0) {
                return 0;
            }
            throw new IllegalStateException("Cannot read in this state");
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        HuffmanState state() {
            return HuffmanState.INITIAL;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public final class UncompressedState extends DecoderState {
        private final long blockLength;
        private long read;

        private UncompressedState(long blockLength) {
            super();
            this.blockLength = blockLength;
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        int available() throws IOException {
            return (int) Math.min(this.blockLength - this.read, HuffmanDecoder.this.reader.bitsAvailable() / 8);
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        boolean hasData() {
            return this.read < this.blockLength;
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        int read(byte[] b, int off, int len) throws IOException {
            int readNow;
            if (len == 0) {
                return 0;
            }
            int max = (int) Math.min(this.blockLength - this.read, len);
            int readSoFar = 0;
            while (readSoFar < max) {
                if (HuffmanDecoder.this.reader.bitsCached() > 0) {
                    byte next = (byte) HuffmanDecoder.this.readBits(8);
                    b[off + readSoFar] = HuffmanDecoder.this.memory.add(next);
                    readNow = 1;
                } else {
                    readNow = HuffmanDecoder.this.in.read(b, off + readSoFar, max - readSoFar);
                    if (readNow != -1) {
                        HuffmanDecoder.this.memory.add(b, off + readSoFar, readNow);
                    } else {
                        throw new EOFException("Truncated Deflate64 Stream");
                    }
                }
                this.read += readNow;
                readSoFar += readNow;
            }
            return max;
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        HuffmanState state() {
            return this.read < this.blockLength ? HuffmanState.STORED : HuffmanState.INITIAL;
        }
    }

    static {
        Arrays.fill(FIXED_LITERALS, 0, 144, 8);
        Arrays.fill(FIXED_LITERALS, 144, 256, 9);
        Arrays.fill(FIXED_LITERALS, 256, 280, 7);
        Arrays.fill(FIXED_LITERALS, 280, 288, 8);
        FIXED_DISTANCE = ArrayFill.fill(new int[32], 5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BinaryTreeNode buildTree(int[] litTable) {
        int[] literalCodes = getCodes(litTable);
        BinaryTreeNode root = new BinaryTreeNode(0);
        for (int i = 0; i < litTable.length; i++) {
            int len = litTable[i];
            if (len != 0) {
                BinaryTreeNode node = root;
                int lit = literalCodes[len - 1];
                for (int p = len - 1; p >= 0; p--) {
                    int bit = (1 << p) & lit;
                    node = bit == 0 ? node.left() : node.right();
                    if (node == null) {
                        throw new IllegalStateException("node doesn't exist in Huffman tree");
                    }
                }
                node.leaf(i);
                int i2 = len - 1;
                literalCodes[i2] = literalCodes[i2] + 1;
            }
        }
        return root;
    }

    private static int[] getCodes(int[] litTable) {
        int max = 0;
        int[] blCount = new int[65];
        for (int aLitTable : litTable) {
            if (aLitTable < 0 || aLitTable > 64) {
                throw new IllegalArgumentException("Invalid code " + aLitTable + " in literal table");
            }
            max = Math.max(max, aLitTable);
            blCount[aLitTable] = blCount[aLitTable] + 1;
        }
        int[] blCount2 = Arrays.copyOf(blCount, max + 1);
        int code = 0;
        int[] nextCode = new int[max + 1];
        for (int i = 0; i <= max; i++) {
            code = (blCount2[i] + code) << 1;
            nextCode[i] = code;
        }
        return nextCode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int nextSymbol(BitInputStream reader, BinaryTreeNode tree) throws IOException {
        BinaryTreeNode node = tree;
        while (node != null && node.literal == -1) {
            long bit = readBits(reader, 1);
            node = bit == 0 ? node.leftNode : node.rightNode;
        }
        if (node != null) {
            return node.literal;
        }
        return -1;
    }

    private static void populateDynamicTables(BitInputStream reader, int[] literals, int[] distances) throws IOException {
        int codeLengths = (int) (readBits(reader, 4) + 4);
        int[] codeLengthValues = new int[19];
        for (int cLen = 0; cLen < codeLengths; cLen++) {
            codeLengthValues[CODE_LENGTHS_ORDER[cLen]] = (int) readBits(reader, 3);
        }
        BinaryTreeNode codeLengthTree = buildTree(codeLengthValues);
        int[] auxBuffer = new int[literals.length + distances.length];
        int value = -1;
        int length = 0;
        int off = 0;
        while (off < auxBuffer.length) {
            if (length > 0) {
                auxBuffer[off] = value;
                length--;
                off++;
            } else {
                int symbol = nextSymbol(reader, codeLengthTree);
                if (symbol < 16) {
                    value = symbol;
                    auxBuffer[off] = value;
                    off++;
                } else {
                    switch (symbol) {
                        case 16:
                            length = (int) (readBits(reader, 2) + 3);
                            break;
                        case 17:
                            value = 0;
                            length = (int) (readBits(reader, 3) + 3);
                            break;
                        case 18:
                            value = 0;
                            length = (int) (readBits(reader, 7) + 11);
                            break;
                    }
                }
            }
        }
        System.arraycopy(auxBuffer, 0, literals, 0, literals.length);
        System.arraycopy(auxBuffer, literals.length, distances, 0, distances.length);
    }

    private static long readBits(BitInputStream reader, int numBits) throws IOException {
        long r = reader.readBits(numBits);
        if (r == -1) {
            throw new EOFException("Truncated Deflate64 Stream");
        }
        return r;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HuffmanDecoder(InputStream in) {
        this.memory = new DecodingMemory();
        this.reader = new BitInputStream(in, ByteOrder.LITTLE_ENDIAN);
        this.in = in;
        this.state = new InitialState();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int available() throws IOException {
        return this.state.available();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.state = new InitialState();
        this.reader = null;
    }

    public int decode(byte[] b) throws IOException {
        return decode(b, 0, b.length);
    }

    public int decode(byte[] b, int off, int len) throws IOException {
        while (true) {
            if (!this.finalBlock || this.state.hasData()) {
                if (this.state.state() == HuffmanState.INITIAL) {
                    this.finalBlock = readBits(1) == 1;
                    int mode = (int) readBits(2);
                    switch (mode) {
                        case 0:
                            switchToUncompressedState();
                            break;
                        case 1:
                            this.state = new HuffmanCodes(HuffmanState.FIXED_CODES, FIXED_LITERALS, FIXED_DISTANCE);
                            break;
                        case 2:
                            int[][] tables = readDynamicTables();
                            this.state = new HuffmanCodes(HuffmanState.DYNAMIC_CODES, tables[0], tables[1]);
                            break;
                        default:
                            throw new IllegalStateException("Unsupported compression: " + mode);
                    }
                } else {
                    int r = this.state.read(b, off, len);
                    if (r != 0) {
                        return r;
                    }
                }
            } else {
                return -1;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getBytesRead() {
        return this.reader.getBytesRead();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long readBits(int numBits) throws IOException {
        return readBits(this.reader, numBits);
    }

    private int[][] readDynamicTables() throws IOException {
        int literals = (int) (readBits(5) + 257);
        int distances = (int) (readBits(5) + 1);
        int[][] result = {new int[literals], new int[distances]};
        populateDynamicTables(this.reader, result[0], result[1]);
        return result;
    }

    private void switchToUncompressedState() throws IOException {
        this.reader.alignWithByteBoundary();
        long bLen = readBits(16);
        long bNLen = readBits(16);
        if ((65535 & (bLen ^ 65535)) != bNLen) {
            throw new IllegalStateException("Illegal LEN / NLEN values");
        }
        this.state = new UncompressedState(bLen);
    }
}
