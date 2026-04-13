package org.apache.commons.codec.binary;

import java.util.Arrays;
import kotlin.UByte;
import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.binary.BaseNCodec;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.poi.hssf.record.PaletteRecord;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;
import org.apache.poi.ss.formula.ptg.NotEqualPtg;

/* loaded from: classes9.dex */
public class Base16 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 4;
    private static final int BYTES_PER_ENCODED_BLOCK = 2;
    private static final int BYTES_PER_UNENCODED_BLOCK = 1;
    private static final int MASK_4_BITS = 15;
    private static final byte[] UPPER_CASE_DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, NotEqualPtg.sid, IntersectionPtg.sid};
    private static final byte[] UPPER_CASE_ENCODE_TABLE = {TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, PaletteRecord.STANDARD_PALETTE_SIZE, 57, 65, 66, 67, 68, 69, 70};
    private static final byte[] LOWER_CASE_DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, NotEqualPtg.sid, IntersectionPtg.sid};
    private static final byte[] LOWER_CASE_ENCODE_TABLE = {TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, PaletteRecord.STANDARD_PALETTE_SIZE, 57, 97, 98, 99, 100, 101, 102};

    /* loaded from: classes9.dex */
    public static class Builder extends BaseNCodec.AbstractBuilder<Base16, Builder> {
        public Builder() {
            super(null);
            setDecodeTable(Base16.UPPER_CASE_DECODE_TABLE);
            setEncodeTable(Base16.UPPER_CASE_ENCODE_TABLE);
            setEncodedBlockSize(2);
            setUnencodedBlockSize(1);
            setLineLength(0);
            setLineSeparator(BaseNCodec.EMPTY_BYTE_ARRAY);
        }

        @Override // java.util.function.Supplier
        public Base16 get() {
            return new Base16(this);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.commons.codec.binary.BaseNCodec.AbstractBuilder
        public Builder setEncodeTable(byte... encodeTable) {
            super.setDecodeTableRaw(Arrays.equals(encodeTable, Base16.LOWER_CASE_ENCODE_TABLE) ? Base16.LOWER_CASE_DECODE_TABLE : Base16.UPPER_CASE_DECODE_TABLE);
            return (Builder) super.setEncodeTable(encodeTable);
        }

        public Builder setLowerCase(boolean lowerCase) {
            setEncodeTableRaw(lowerCase ? Base16.LOWER_CASE_ENCODE_TABLE : Base16.UPPER_CASE_ENCODE_TABLE);
            return asThis();
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public Base16() {
        this(false);
    }

    @Deprecated
    public Base16(boolean lowerCase) {
        this(lowerCase, DECODING_POLICY_DEFAULT);
    }

    @Deprecated
    public Base16(boolean lowerCase, CodecPolicy decodingPolicy) {
        this(builder().setEncodeTable(lowerCase ? LOWER_CASE_ENCODE_TABLE : UPPER_CASE_ENCODE_TABLE).setDecodingPolicy(decodingPolicy));
    }

    private Base16(Builder builder) {
        super(builder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void decode(byte[] data, int result, int length, BaseNCodec.Context context) {
        if (context.eof || length < 0) {
            context.eof = true;
            if (context.ibitWorkArea != 0) {
                validateTrailingCharacter();
                return;
            }
            return;
        }
        int dataLen = Math.min(data.length - result, length);
        int availableChars = (context.ibitWorkArea != 0 ? 1 : 0) + dataLen;
        if (availableChars == 1 && availableChars == dataLen) {
            context.ibitWorkArea = decodeOctet(data[result]) + 1;
            return;
        }
        int charsToProcess = availableChars % 2 == 0 ? availableChars : availableChars - 1;
        int end = result + dataLen;
        byte[] buffer = ensureBufferSize(charsToProcess / 2, context);
        if (dataLen < availableChars) {
            int offset = result + 1;
            int result2 = decodeOctet(data[result]) | ((context.ibitWorkArea - 1) << 4);
            int result3 = context.pos;
            context.pos = result3 + 1;
            buffer[result3] = (byte) result2;
            context.ibitWorkArea = 0;
            result = offset;
        }
        int loopEnd = end - 1;
        while (result < loopEnd) {
            int offset2 = result + 1;
            int offset3 = offset2 + 1;
            int result4 = (decodeOctet(data[result]) << 4) | decodeOctet(data[offset2]);
            int i = context.pos;
            context.pos = i + 1;
            buffer[i] = (byte) result4;
            result = offset3;
        }
        if (result < end) {
            context.ibitWorkArea = decodeOctet(data[result]) + 1;
        }
    }

    private int decodeOctet(byte octet) {
        int decoded = -1;
        if ((octet & UByte.MAX_VALUE) < this.decodeTable.length) {
            decoded = this.decodeTable[octet];
        }
        if (decoded == -1) {
            throw new IllegalArgumentException("Invalid octet in encoded value: " + ((int) octet));
        }
        return decoded;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void encode(byte[] data, int offset, int length, BaseNCodec.Context context) {
        if (context.eof) {
            return;
        }
        if (length < 0) {
            context.eof = true;
            return;
        }
        int size = length * 2;
        if (size < 0) {
            throw new IllegalArgumentException("Input length exceeds maximum size for encoded data: " + length);
        }
        byte[] buffer = ensureBufferSize(size, context);
        int end = offset + length;
        for (int i = offset; i < end; i++) {
            int value = data[i];
            int high = (value >> 4) & 15;
            int low = value & 15;
            int i2 = context.pos;
            context.pos = i2 + 1;
            buffer[i2] = this.encodeTable[high];
            int i3 = context.pos;
            context.pos = i3 + 1;
            buffer[i3] = this.encodeTable[low];
        }
    }

    @Override // org.apache.commons.codec.binary.BaseNCodec
    public boolean isInAlphabet(byte octet) {
        return (octet & UByte.MAX_VALUE) < this.decodeTable.length && this.decodeTable[octet] != -1;
    }

    private void validateTrailingCharacter() {
        if (isStrictDecoding()) {
            throw new IllegalArgumentException("Strict decoding: Last encoded character is a valid Base 16 alphabet character but not a possible encoding. Decoding requires at least two characters to create one byte.");
        }
    }
}
