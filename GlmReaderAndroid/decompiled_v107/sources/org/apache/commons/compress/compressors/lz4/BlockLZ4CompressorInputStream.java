package org.apache.commons.compress.compressors.lz4;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.lz77support.AbstractLZ77CompressorInputStream;
import org.apache.commons.compress.utils.ByteUtils;

/* loaded from: classes9.dex */
public class BlockLZ4CompressorInputStream extends AbstractLZ77CompressorInputStream {
    static final int BACK_REFERENCE_SIZE_MASK = 15;
    static final int LITERAL_SIZE_MASK = 240;
    static final int SIZE_BITS = 4;
    static final int WINDOW_SIZE = 65536;
    private int nextBackReferenceSize;
    private State state;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public enum State {
        NO_BLOCK,
        IN_LITERAL,
        LOOKING_FOR_BACK_REFERENCE,
        IN_BACK_REFERENCE,
        EOF
    }

    public BlockLZ4CompressorInputStream(InputStream is) {
        super(is, 65536);
        this.state = State.NO_BLOCK;
    }

    private boolean initializeBackReference() throws IOException {
        try {
            int backReferenceOffset = (int) ByteUtils.fromLittleEndian(this.supplier, 2);
            long backReferenceSize = this.nextBackReferenceSize;
            if (this.nextBackReferenceSize == 15) {
                backReferenceSize += readSizeBytes();
            }
            if (backReferenceSize < 0) {
                throw new IOException("Illegal block with a negative match length found");
            }
            try {
                startBackReference(backReferenceOffset, 4 + backReferenceSize);
                this.state = State.IN_BACK_REFERENCE;
                return true;
            } catch (IllegalArgumentException ex) {
                throw new IOException("Illegal block with bad offset found", ex);
            }
        } catch (IOException ex2) {
            if (this.nextBackReferenceSize == 0) {
                return false;
            }
            throw ex2;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x000b. Please report as an issue. */
    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        if (len == 0) {
            return 0;
        }
        switch (this.state) {
            case NO_BLOCK:
                readSizes();
            case IN_LITERAL:
                int litLen = readLiteral(b, off, len);
                if (!hasMoreDataInBlock()) {
                    this.state = State.LOOKING_FOR_BACK_REFERENCE;
                }
                return litLen > 0 ? litLen : read(b, off, len);
            case LOOKING_FOR_BACK_REFERENCE:
                if (!initializeBackReference()) {
                    this.state = State.EOF;
                    return -1;
                }
            case IN_BACK_REFERENCE:
                int backReferenceLen = readBackReference(b, off, len);
                if (!hasMoreDataInBlock()) {
                    this.state = State.NO_BLOCK;
                }
                return backReferenceLen > 0 ? backReferenceLen : read(b, off, len);
            case EOF:
                return -1;
            default:
                throw new IOException("Unknown stream state " + this.state);
        }
    }

    private long readSizeBytes() throws IOException {
        int nextByte;
        long accum = 0;
        do {
            nextByte = readOneByte();
            if (nextByte == -1) {
                throw new IOException("Premature end of stream while parsing length");
            }
            accum += nextByte;
        } while (nextByte == 255);
        return accum;
    }

    private void readSizes() throws IOException {
        int nextBlock = readOneByte();
        if (nextBlock == -1) {
            throw new IOException("Premature end of stream while looking for next block");
        }
        this.nextBackReferenceSize = nextBlock & 15;
        long literalSizePart = (nextBlock & LITERAL_SIZE_MASK) >> 4;
        if (literalSizePart == 15) {
            literalSizePart += readSizeBytes();
        }
        if (literalSizePart < 0) {
            throw new IOException("Illegal block with a negative literal size found");
        }
        startLiteral(literalSizePart);
        this.state = State.IN_LITERAL;
    }
}
