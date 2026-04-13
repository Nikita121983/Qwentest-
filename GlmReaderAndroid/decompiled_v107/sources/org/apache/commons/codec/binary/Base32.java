package org.apache.commons.codec.binary;

import java.util.Arrays;
import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.binary.BaseNCodec;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.poi.hssf.record.PaletteRecord;
import org.apache.poi.ss.formula.ptg.AttrPtg;
import org.apache.poi.ss.formula.ptg.BoolPtg;
import org.apache.poi.ss.formula.ptg.IntPtg;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;
import org.apache.poi.ss.formula.ptg.MissingArgPtg;
import org.apache.poi.ss.formula.ptg.NotEqualPtg;
import org.apache.poi.ss.formula.ptg.NumberPtg;
import org.apache.poi.ss.formula.ptg.ParenthesisPtg;
import org.apache.poi.ss.formula.ptg.PercentPtg;
import org.apache.poi.ss.formula.ptg.RangePtg;
import org.apache.poi.ss.formula.ptg.StringPtg;
import org.apache.poi.ss.formula.ptg.UnaryMinusPtg;
import org.apache.poi.ss.formula.ptg.UnaryPlusPtg;
import org.apache.poi.ss.formula.ptg.UnionPtg;

/* loaded from: classes9.dex */
public class Base32 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 5;
    private static final int BYTES_PER_ENCODED_BLOCK = 8;
    private static final int BYTES_PER_UNENCODED_BLOCK = 5;
    private static final byte[] DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 26, 27, 28, BoolPtg.sid, IntPtg.sid, NumberPtg.sid, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, NotEqualPtg.sid, IntersectionPtg.sid, UnionPtg.sid, RangePtg.sid, UnaryPlusPtg.sid, UnaryMinusPtg.sid, PercentPtg.sid, ParenthesisPtg.sid, MissingArgPtg.sid, StringPtg.sid, 24, AttrPtg.sid, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, NotEqualPtg.sid, IntersectionPtg.sid, UnionPtg.sid, RangePtg.sid, UnaryPlusPtg.sid, UnaryMinusPtg.sid, PercentPtg.sid, ParenthesisPtg.sid, MissingArgPtg.sid, StringPtg.sid, 24, AttrPtg.sid};
    private static final byte[] ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, TarConstants.LF_GNUTYPE_LONGLINK, TarConstants.LF_GNUTYPE_LONGNAME, TarConstants.LF_MULTIVOLUME, 78, 79, 80, 81, 82, TarConstants.LF_GNUTYPE_SPARSE, 84, 85, 86, 87, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 89, 90, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG};
    private static final byte[] HEX_DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, NotEqualPtg.sid, IntersectionPtg.sid, UnionPtg.sid, RangePtg.sid, UnaryPlusPtg.sid, UnaryMinusPtg.sid, PercentPtg.sid, ParenthesisPtg.sid, MissingArgPtg.sid, StringPtg.sid, 24, AttrPtg.sid, 26, 27, 28, BoolPtg.sid, IntPtg.sid, NumberPtg.sid, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, NotEqualPtg.sid, IntersectionPtg.sid, UnionPtg.sid, RangePtg.sid, UnaryPlusPtg.sid, UnaryMinusPtg.sid, PercentPtg.sid, ParenthesisPtg.sid, MissingArgPtg.sid, StringPtg.sid, 24, AttrPtg.sid, 26, 27, 28, BoolPtg.sid, IntPtg.sid, NumberPtg.sid};
    private static final byte[] HEX_ENCODE_TABLE = {TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, PaletteRecord.STANDARD_PALETTE_SIZE, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, TarConstants.LF_GNUTYPE_LONGLINK, TarConstants.LF_GNUTYPE_LONGNAME, TarConstants.LF_MULTIVOLUME, 78, 79, 80, 81, 82, TarConstants.LF_GNUTYPE_SPARSE, 84, 85, 86};
    private static final long MASK_1_BITS = 1;
    private static final long MASK_2_BITS = 3;
    private static final long MASK_3_BITS = 7;
    private static final long MASK_4_BITS = 15;
    private static final int MASK_5_BITS = 31;
    private final int encodeSize;
    private final byte[] lineSeparator;

    /* loaded from: classes9.dex */
    public static class Builder extends BaseNCodec.AbstractBuilder<Base32, Builder> {
        public Builder() {
            super(Base32.ENCODE_TABLE);
            setDecodeTableRaw(Base32.DECODE_TABLE);
            setEncodeTableRaw(Base32.ENCODE_TABLE);
            setEncodedBlockSize(8);
            setUnencodedBlockSize(5);
        }

        @Override // java.util.function.Supplier
        public Base32 get() {
            return new Base32(this);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.commons.codec.binary.BaseNCodec.AbstractBuilder
        public Builder setEncodeTable(byte... encodeTable) {
            super.setDecodeTableRaw(Arrays.equals(encodeTable, Base32.HEX_ENCODE_TABLE) ? Base32.HEX_DECODE_TABLE : Base32.DECODE_TABLE);
            return (Builder) super.setEncodeTable(encodeTable);
        }

        public Builder setHexDecodeTable(boolean useHex) {
            return setEncodeTable(Base32.decodeTable(useHex));
        }

        public Builder setHexEncodeTable(boolean useHex) {
            return setEncodeTable(Base32.encodeTable(useHex));
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] decodeTable(boolean useHex) {
        return useHex ? HEX_DECODE_TABLE : DECODE_TABLE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] encodeTable(boolean useHex) {
        return useHex ? HEX_ENCODE_TABLE : ENCODE_TABLE;
    }

    public Base32() {
        this(false);
    }

    @Deprecated
    public Base32(boolean useHex) {
        this(0, null, useHex, (byte) 61);
    }

    @Deprecated
    public Base32(boolean useHex, byte padding) {
        this(0, null, useHex, padding);
    }

    private Base32(Builder builder) {
        super(builder);
        if (builder.getLineLength() > 0) {
            byte[] lineSeparator = builder.getLineSeparator();
            if (containsAlphabetOrPad(lineSeparator)) {
                String sep = StringUtils.newStringUtf8(lineSeparator);
                throw new IllegalArgumentException("lineSeparator must not contain Base32 characters: [" + sep + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            }
            this.encodeSize = lineSeparator.length + 8;
            this.lineSeparator = lineSeparator;
        } else {
            this.encodeSize = 8;
            this.lineSeparator = null;
        }
        if (isInAlphabet(builder.getPadding()) || Character.isWhitespace(builder.getPadding())) {
            throw new IllegalArgumentException("pad must not be in alphabet or whitespace");
        }
    }

    @Deprecated
    public Base32(byte pad) {
        this(false, pad);
    }

    @Deprecated
    public Base32(int lineLength) {
        this(lineLength, CHUNK_SEPARATOR);
    }

    @Deprecated
    public Base32(int lineLength, byte[] lineSeparator) {
        this(lineLength, lineSeparator, false, (byte) 61);
    }

    @Deprecated
    public Base32(int lineLength, byte[] lineSeparator, boolean useHex) {
        this(lineLength, lineSeparator, useHex, (byte) 61);
    }

    @Deprecated
    public Base32(int lineLength, byte[] lineSeparator, boolean useHex, byte padding) {
        this(lineLength, lineSeparator, useHex, padding, DECODING_POLICY_DEFAULT);
    }

    @Deprecated
    public Base32(int lineLength, byte[] lineSeparator, boolean useHex, byte padding, CodecPolicy decodingPolicy) {
        this(builder().setLineLength(lineLength).setLineSeparator(lineSeparator != null ? lineSeparator : EMPTY_BYTE_ARRAY).setDecodeTable(decodeTable(useHex)).setEncodeTableRaw(encodeTable(useHex)).setPadding(padding).setDecodingPolicy(decodingPolicy));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void decode(byte[] input, int inPos, int inAvail, BaseNCodec.Context context) {
        char c;
        char c2;
        char c3;
        long j;
        int result;
        if (context.eof) {
            return;
        }
        if (inAvail < 0) {
            context.eof = true;
        }
        int decodeSize = this.encodeSize - 1;
        int i = 0;
        int inPos2 = inPos;
        while (true) {
            if (i >= inAvail) {
                c = '\b';
                c2 = 24;
                c3 = 16;
                j = 255;
                break;
            }
            int inPos3 = inPos2 + 1;
            byte b = input[inPos2];
            if (b == this.pad) {
                context.eof = true;
                c = '\b';
                c2 = 24;
                c3 = 16;
                j = 255;
                break;
            }
            byte[] buffer = ensureBufferSize(decodeSize, context);
            if (b >= 0 && b < this.decodeTable.length && (result = this.decodeTable[b]) >= 0) {
                context.modulus = (context.modulus + 1) % 8;
                context.lbitWorkArea = (context.lbitWorkArea << 5) + result;
                if (context.modulus == 0) {
                    int i2 = context.pos;
                    context.pos = i2 + 1;
                    buffer[i2] = (byte) ((context.lbitWorkArea >> 32) & 255);
                    int i3 = context.pos;
                    context.pos = i3 + 1;
                    buffer[i3] = (byte) ((context.lbitWorkArea >> 24) & 255);
                    int i4 = context.pos;
                    context.pos = i4 + 1;
                    buffer[i4] = (byte) ((context.lbitWorkArea >> 16) & 255);
                    int i5 = context.pos;
                    context.pos = i5 + 1;
                    buffer[i5] = (byte) ((context.lbitWorkArea >> 8) & 255);
                    int i6 = context.pos;
                    context.pos = i6 + 1;
                    buffer[i6] = (byte) (context.lbitWorkArea & 255);
                }
            }
            i++;
            inPos2 = inPos3;
        }
        if (context.eof && context.modulus > 0) {
            byte[] buffer2 = ensureBufferSize(decodeSize, context);
            switch (context.modulus) {
                case 1:
                    validateTrailingCharacters();
                    break;
                case 2:
                    break;
                case 3:
                    validateTrailingCharacters();
                    int i7 = context.pos;
                    context.pos = i7 + 1;
                    buffer2[i7] = (byte) ((context.lbitWorkArea >> MASK_3_BITS) & j);
                    return;
                case 4:
                    validateCharacter(MASK_4_BITS, context);
                    context.lbitWorkArea >>= 4;
                    int i8 = context.pos;
                    context.pos = i8 + 1;
                    buffer2[i8] = (byte) ((context.lbitWorkArea >> c) & j);
                    int i9 = context.pos;
                    context.pos = i9 + 1;
                    buffer2[i9] = (byte) (context.lbitWorkArea & j);
                    return;
                case 5:
                    validateCharacter(1L, context);
                    context.lbitWorkArea >>= 1;
                    int i10 = context.pos;
                    context.pos = i10 + 1;
                    buffer2[i10] = (byte) ((context.lbitWorkArea >> c3) & j);
                    int i11 = context.pos;
                    context.pos = i11 + 1;
                    buffer2[i11] = (byte) ((context.lbitWorkArea >> c) & j);
                    int i12 = context.pos;
                    context.pos = i12 + 1;
                    buffer2[i12] = (byte) (context.lbitWorkArea & j);
                    return;
                case 6:
                    validateTrailingCharacters();
                    context.lbitWorkArea >>= 6;
                    int i13 = context.pos;
                    context.pos = i13 + 1;
                    buffer2[i13] = (byte) ((context.lbitWorkArea >> c3) & j);
                    int i14 = context.pos;
                    context.pos = i14 + 1;
                    buffer2[i14] = (byte) ((context.lbitWorkArea >> c) & j);
                    int i15 = context.pos;
                    context.pos = i15 + 1;
                    buffer2[i15] = (byte) (context.lbitWorkArea & j);
                    return;
                case 7:
                    validateCharacter(MASK_3_BITS, context);
                    context.lbitWorkArea >>= MASK_2_BITS;
                    int i16 = context.pos;
                    context.pos = i16 + 1;
                    buffer2[i16] = (byte) ((context.lbitWorkArea >> c2) & j);
                    int i17 = context.pos;
                    context.pos = i17 + 1;
                    buffer2[i17] = (byte) ((context.lbitWorkArea >> c3) & j);
                    int i18 = context.pos;
                    context.pos = i18 + 1;
                    buffer2[i18] = (byte) ((context.lbitWorkArea >> c) & j);
                    int i19 = context.pos;
                    context.pos = i19 + 1;
                    buffer2[i19] = (byte) (context.lbitWorkArea & j);
                    return;
                default:
                    throw new IllegalStateException("Impossible modulus " + context.modulus);
            }
            validateCharacter(MASK_2_BITS, context);
            int i20 = context.pos;
            context.pos = i20 + 1;
            buffer2[i20] = (byte) ((context.lbitWorkArea >> 2) & j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void encode(byte[] bArr, int inPos, int inAvail, BaseNCodec.Context context) {
        if (context.eof) {
            return;
        }
        if (inAvail < 0) {
            context.eof = true;
            if (context.modulus != 0 || this.lineLength != 0) {
                byte[] buffer = ensureBufferSize(this.encodeSize, context);
                int savedPos = context.pos;
                switch (context.modulus) {
                    case 0:
                        break;
                    case 1:
                        int i = context.pos;
                        context.pos = i + 1;
                        buffer[i] = this.encodeTable[((int) (context.lbitWorkArea >> MASK_2_BITS)) & 31];
                        int i2 = context.pos;
                        context.pos = i2 + 1;
                        buffer[i2] = this.encodeTable[((int) (context.lbitWorkArea << 2)) & 31];
                        int i3 = context.pos;
                        context.pos = i3 + 1;
                        buffer[i3] = this.pad;
                        int i4 = context.pos;
                        context.pos = i4 + 1;
                        buffer[i4] = this.pad;
                        int i5 = context.pos;
                        context.pos = i5 + 1;
                        buffer[i5] = this.pad;
                        int i6 = context.pos;
                        context.pos = i6 + 1;
                        buffer[i6] = this.pad;
                        int i7 = context.pos;
                        context.pos = i7 + 1;
                        buffer[i7] = this.pad;
                        int i8 = context.pos;
                        context.pos = i8 + 1;
                        buffer[i8] = this.pad;
                        break;
                    case 2:
                        int i9 = context.pos;
                        context.pos = i9 + 1;
                        buffer[i9] = this.encodeTable[((int) (context.lbitWorkArea >> 11)) & 31];
                        int i10 = context.pos;
                        context.pos = i10 + 1;
                        buffer[i10] = this.encodeTable[((int) (context.lbitWorkArea >> 6)) & 31];
                        int i11 = context.pos;
                        context.pos = i11 + 1;
                        buffer[i11] = this.encodeTable[((int) (context.lbitWorkArea >> 1)) & 31];
                        int i12 = context.pos;
                        context.pos = i12 + 1;
                        buffer[i12] = this.encodeTable[((int) (context.lbitWorkArea << 4)) & 31];
                        int i13 = context.pos;
                        context.pos = i13 + 1;
                        buffer[i13] = this.pad;
                        int i14 = context.pos;
                        context.pos = i14 + 1;
                        buffer[i14] = this.pad;
                        int i15 = context.pos;
                        context.pos = i15 + 1;
                        buffer[i15] = this.pad;
                        int i16 = context.pos;
                        context.pos = i16 + 1;
                        buffer[i16] = this.pad;
                        break;
                    case 3:
                        int i17 = context.pos;
                        context.pos = i17 + 1;
                        buffer[i17] = this.encodeTable[((int) (context.lbitWorkArea >> 19)) & 31];
                        int i18 = context.pos;
                        context.pos = i18 + 1;
                        buffer[i18] = this.encodeTable[((int) (context.lbitWorkArea >> 14)) & 31];
                        int i19 = context.pos;
                        context.pos = i19 + 1;
                        buffer[i19] = this.encodeTable[((int) (context.lbitWorkArea >> 9)) & 31];
                        int i20 = context.pos;
                        context.pos = i20 + 1;
                        buffer[i20] = this.encodeTable[((int) (context.lbitWorkArea >> 4)) & 31];
                        int i21 = context.pos;
                        context.pos = i21 + 1;
                        buffer[i21] = this.encodeTable[((int) (context.lbitWorkArea << 1)) & 31];
                        int i22 = context.pos;
                        context.pos = i22 + 1;
                        buffer[i22] = this.pad;
                        int i23 = context.pos;
                        context.pos = i23 + 1;
                        buffer[i23] = this.pad;
                        int i24 = context.pos;
                        context.pos = i24 + 1;
                        buffer[i24] = this.pad;
                        break;
                    case 4:
                        int i25 = context.pos;
                        context.pos = i25 + 1;
                        buffer[i25] = this.encodeTable[((int) (context.lbitWorkArea >> 27)) & 31];
                        int i26 = context.pos;
                        context.pos = i26 + 1;
                        buffer[i26] = this.encodeTable[((int) (context.lbitWorkArea >> 22)) & 31];
                        int i27 = context.pos;
                        context.pos = i27 + 1;
                        buffer[i27] = this.encodeTable[((int) (context.lbitWorkArea >> 17)) & 31];
                        int i28 = context.pos;
                        context.pos = i28 + 1;
                        buffer[i28] = this.encodeTable[((int) (context.lbitWorkArea >> 12)) & 31];
                        int i29 = context.pos;
                        context.pos = i29 + 1;
                        buffer[i29] = this.encodeTable[((int) (context.lbitWorkArea >> MASK_3_BITS)) & 31];
                        int i30 = context.pos;
                        context.pos = i30 + 1;
                        buffer[i30] = this.encodeTable[((int) (context.lbitWorkArea >> 2)) & 31];
                        int i31 = context.pos;
                        context.pos = i31 + 1;
                        buffer[i31] = this.encodeTable[((int) (context.lbitWorkArea << MASK_2_BITS)) & 31];
                        int i32 = context.pos;
                        context.pos = i32 + 1;
                        buffer[i32] = this.pad;
                        break;
                    default:
                        throw new IllegalStateException("Impossible modulus " + context.modulus);
                }
                context.currentLinePos += context.pos - savedPos;
                if (this.lineLength > 0 && context.currentLinePos > 0) {
                    System.arraycopy(this.lineSeparator, 0, buffer, context.pos, this.lineSeparator.length);
                    context.pos += this.lineSeparator.length;
                }
                return;
            }
            return;
        }
        int i33 = 0;
        int inPos2 = inPos;
        while (i33 < inAvail) {
            byte[] buffer2 = ensureBufferSize(this.encodeSize, context);
            context.modulus = (context.modulus + 1) % 5;
            int inPos3 = inPos2 + 1;
            int i34 = bArr[inPos2];
            if (i34 < 0) {
                i34 += 256;
            }
            context.lbitWorkArea = (context.lbitWorkArea << 8) + i34;
            if (context.modulus == 0) {
                int i35 = context.pos;
                context.pos = i35 + 1;
                buffer2[i35] = this.encodeTable[((int) (context.lbitWorkArea >> 35)) & 31];
                int i36 = context.pos;
                context.pos = i36 + 1;
                buffer2[i36] = this.encodeTable[((int) (context.lbitWorkArea >> 30)) & 31];
                int i37 = context.pos;
                context.pos = i37 + 1;
                buffer2[i37] = this.encodeTable[((int) (context.lbitWorkArea >> 25)) & 31];
                int i38 = context.pos;
                context.pos = i38 + 1;
                buffer2[i38] = this.encodeTable[((int) (context.lbitWorkArea >> 20)) & 31];
                int i39 = context.pos;
                context.pos = i39 + 1;
                buffer2[i39] = this.encodeTable[((int) (context.lbitWorkArea >> MASK_4_BITS)) & 31];
                int i40 = context.pos;
                context.pos = i40 + 1;
                buffer2[i40] = this.encodeTable[((int) (context.lbitWorkArea >> 10)) & 31];
                int i41 = context.pos;
                context.pos = i41 + 1;
                buffer2[i41] = this.encodeTable[((int) (context.lbitWorkArea >> 5)) & 31];
                int i42 = context.pos;
                context.pos = i42 + 1;
                buffer2[i42] = this.encodeTable[((int) context.lbitWorkArea) & 31];
                context.currentLinePos += 8;
                if (this.lineLength > 0 && this.lineLength <= context.currentLinePos) {
                    System.arraycopy(this.lineSeparator, 0, buffer2, context.pos, this.lineSeparator.length);
                    context.pos += this.lineSeparator.length;
                    context.currentLinePos = 0;
                }
            }
            i33++;
            inPos2 = inPos3;
        }
    }

    byte[] getLineSeparator() {
        return this.lineSeparator;
    }

    @Override // org.apache.commons.codec.binary.BaseNCodec
    public boolean isInAlphabet(byte octet) {
        return octet >= 0 && octet < this.decodeTable.length && this.decodeTable[octet] != -1;
    }

    private void validateCharacter(long emptyBitsMask, BaseNCodec.Context context) {
        if (isStrictDecoding() && (context.lbitWorkArea & emptyBitsMask) != 0) {
            throw new IllegalArgumentException("Strict decoding: Last encoded character (before the paddings if any) is a valid base 32 alphabet but not a possible encoding. Expected the discarded bits from the character to be zero.");
        }
    }

    private void validateTrailingCharacters() {
        if (isStrictDecoding()) {
            throw new IllegalArgumentException("Strict decoding: Last encoded character(s) (before the paddings if any) are valid base 32 alphabet but not a possible encoding. Decoding requires either 2, 4, 5, or 7 trailing 5-bit characters to create bytes.");
        }
    }
}
