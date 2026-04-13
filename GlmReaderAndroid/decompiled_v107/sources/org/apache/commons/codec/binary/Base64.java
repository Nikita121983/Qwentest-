package org.apache.commons.codec.binary;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.binary.BaseNCodec;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.poi.hssf.record.PaletteRecord;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.AreaErrPtg;
import org.apache.poi.ss.formula.ptg.AttrPtg;
import org.apache.poi.ss.formula.ptg.BoolPtg;
import org.apache.poi.ss.formula.ptg.DeletedRef3DPtg;
import org.apache.poi.ss.formula.ptg.IntPtg;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;
import org.apache.poi.ss.formula.ptg.MemFuncPtg;
import org.apache.poi.ss.formula.ptg.MissingArgPtg;
import org.apache.poi.ss.formula.ptg.NotEqualPtg;
import org.apache.poi.ss.formula.ptg.NumberPtg;
import org.apache.poi.ss.formula.ptg.ParenthesisPtg;
import org.apache.poi.ss.formula.ptg.PercentPtg;
import org.apache.poi.ss.formula.ptg.RangePtg;
import org.apache.poi.ss.formula.ptg.Ref3DPtg;
import org.apache.poi.ss.formula.ptg.RefErrorPtg;
import org.apache.poi.ss.formula.ptg.RefNPtg;
import org.apache.poi.ss.formula.ptg.RefPtg;
import org.apache.poi.ss.formula.ptg.StringPtg;
import org.apache.poi.ss.formula.ptg.UnaryMinusPtg;
import org.apache.poi.ss.formula.ptg.UnaryPlusPtg;
import org.apache.poi.ss.formula.ptg.UnionPtg;

/* loaded from: classes9.dex */
public class Base64 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 6;
    private static final int BYTES_PER_ENCODED_BLOCK = 4;
    private static final int BYTES_PER_UNENCODED_BLOCK = 3;
    private static final int DECODING_TABLE_LENGTH = 256;
    private static final int MASK_2_BITS = 3;
    private static final int MASK_4_BITS = 15;
    private static final int MASK_6_BITS = 63;
    private final int encodeSize;
    private final boolean isStandardEncodeTable;
    private final boolean isUrlSafe;
    private final byte[] lineSeparator;
    private static final byte[] STANDARD_ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, TarConstants.LF_GNUTYPE_LONGLINK, TarConstants.LF_GNUTYPE_LONGNAME, TarConstants.LF_MULTIVOLUME, 78, 79, 80, 81, 82, TarConstants.LF_GNUTYPE_SPARSE, 84, 85, 86, 87, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 89, 90, 97, 98, 99, 100, 101, 102, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 121, 122, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, PaletteRecord.STANDARD_PALETTE_SIZE, 57, AreaErrPtg.sid, 47};
    private static final byte[] URL_SAFE_ENCODE_TABLE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, TarConstants.LF_GNUTYPE_LONGLINK, TarConstants.LF_GNUTYPE_LONGNAME, TarConstants.LF_MULTIVOLUME, 78, 79, 80, 81, 82, TarConstants.LF_GNUTYPE_SPARSE, 84, 85, 86, 87, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 89, 90, 97, 98, 99, 100, 101, 102, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 121, 122, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, PaletteRecord.STANDARD_PALETTE_SIZE, 57, 45, 95};
    private static final byte[] DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, PaletteRecord.STANDARD_PALETTE_SIZE, 57, Ref3DPtg.sid, Area3DPtg.sid, DeletedRef3DPtg.sid, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, NotEqualPtg.sid, IntersectionPtg.sid, UnionPtg.sid, RangePtg.sid, UnaryPlusPtg.sid, UnaryMinusPtg.sid, PercentPtg.sid, ParenthesisPtg.sid, MissingArgPtg.sid, StringPtg.sid, 24, AttrPtg.sid, -1, -1, -1, -1, 63, -1, 26, 27, 28, BoolPtg.sid, IntPtg.sid, NumberPtg.sid, 32, 33, 34, 35, RefPtg.sid, 37, 38, 39, 40, MemFuncPtg.sid, RefErrorPtg.sid, AreaErrPtg.sid, RefNPtg.sid, 45, 46, 47, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR};

    /* loaded from: classes9.dex */
    public static class Builder extends BaseNCodec.AbstractBuilder<Base64, Builder> {
        public Builder() {
            super(Base64.STANDARD_ENCODE_TABLE);
            setDecodeTableRaw(Base64.DECODE_TABLE);
            setEncodeTableRaw(Base64.STANDARD_ENCODE_TABLE);
            setEncodedBlockSize(4);
            setUnencodedBlockSize(3);
        }

        @Override // java.util.function.Supplier
        public Base64 get() {
            return new Base64(this);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.apache.commons.codec.binary.BaseNCodec.AbstractBuilder
        public Builder setEncodeTable(byte... encodeTable) {
            boolean isStandardEncodeTable = Arrays.equals(encodeTable, Base64.STANDARD_ENCODE_TABLE);
            boolean isUrlSafe = Arrays.equals(encodeTable, Base64.URL_SAFE_ENCODE_TABLE);
            super.setDecodeTableRaw((isStandardEncodeTable || isUrlSafe) ? Base64.DECODE_TABLE : Base64.calculateDecodeTable(encodeTable));
            return (Builder) super.setEncodeTable(encodeTable);
        }

        public Builder setUrlSafe(boolean urlSafe) {
            return setEncodeTable(Base64.toUrlSafeEncodeTable(urlSafe));
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] calculateDecodeTable(byte[] encodeTable) {
        byte[] decodeTable = new byte[256];
        Arrays.fill(decodeTable, (byte) -1);
        for (int i = 0; i < encodeTable.length; i++) {
            decodeTable[encodeTable[i]] = (byte) i;
        }
        return decodeTable;
    }

    public static byte[] decodeBase64(byte[] base64Data) {
        return new Base64().decode(base64Data);
    }

    public static byte[] decodeBase64(String base64String) {
        return new Base64().decode(base64String);
    }

    public static BigInteger decodeInteger(byte[] array) {
        return new BigInteger(1, decodeBase64(array));
    }

    public static byte[] encodeBase64(byte[] binaryData) {
        return encodeBase64(binaryData, false);
    }

    public static byte[] encodeBase64(byte[] binaryData, boolean isChunked) {
        return encodeBase64(binaryData, isChunked, false);
    }

    public static byte[] encodeBase64(byte[] binaryData, boolean isChunked, boolean urlSafe) {
        return encodeBase64(binaryData, isChunked, urlSafe, Integer.MAX_VALUE);
    }

    public static byte[] encodeBase64(byte[] binaryData, boolean isChunked, boolean urlSafe, int maxResultSize) {
        if (BinaryCodec.isEmpty(binaryData)) {
            return binaryData;
        }
        Base64 b64 = isChunked ? new Base64(urlSafe) : new Base64(0, CHUNK_SEPARATOR, urlSafe);
        long len = b64.getEncodedLength(binaryData);
        if (len > maxResultSize) {
            throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + len + ") than the specified maximum size of " + maxResultSize);
        }
        return b64.encode(binaryData);
    }

    public static byte[] encodeBase64Chunked(byte[] binaryData) {
        return encodeBase64(binaryData, true);
    }

    public static String encodeBase64String(byte[] binaryData) {
        return StringUtils.newStringUsAscii(encodeBase64(binaryData, false));
    }

    public static byte[] encodeBase64URLSafe(byte[] binaryData) {
        return encodeBase64(binaryData, false, true);
    }

    public static String encodeBase64URLSafeString(byte[] binaryData) {
        return StringUtils.newStringUsAscii(encodeBase64(binaryData, false, true));
    }

    public static byte[] encodeInteger(BigInteger bigInteger) {
        Objects.requireNonNull(bigInteger, "bigInteger");
        return encodeBase64(toIntegerBytes(bigInteger), false);
    }

    @Deprecated
    public static boolean isArrayByteBase64(byte[] arrayOctet) {
        return isBase64(arrayOctet);
    }

    public static boolean isBase64(byte octet) {
        return octet == 61 || (octet >= 0 && octet < DECODE_TABLE.length && DECODE_TABLE[octet] != -1);
    }

    public static boolean isBase64(byte[] arrayOctet) {
        for (byte element : arrayOctet) {
            if (!isBase64(element) && !Character.isWhitespace(element)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isBase64(String base64) {
        return isBase64(StringUtils.getBytesUtf8(base64));
    }

    static byte[] toIntegerBytes(BigInteger bigInt) {
        int bitlen = ((bigInt.bitLength() + 7) >> 3) << 3;
        byte[] bigBytes = bigInt.toByteArray();
        if (bigInt.bitLength() % 8 != 0 && (bigInt.bitLength() / 8) + 1 == bitlen / 8) {
            return bigBytes;
        }
        int startSrc = 0;
        int len = bigBytes.length;
        if (bigInt.bitLength() % 8 == 0) {
            startSrc = 1;
            len--;
        }
        int startDst = (bitlen / 8) - len;
        byte[] resizedBytes = new byte[bitlen / 8];
        System.arraycopy(bigBytes, startSrc, resizedBytes, startDst, len);
        return resizedBytes;
    }

    static byte[] toUrlSafeEncodeTable(boolean urlSafe) {
        return urlSafe ? URL_SAFE_ENCODE_TABLE : STANDARD_ENCODE_TABLE;
    }

    public Base64() {
        this(0);
    }

    @Deprecated
    public Base64(boolean urlSafe) {
        this(76, CHUNK_SEPARATOR, urlSafe);
    }

    private Base64(Builder builder) {
        super(builder);
        byte[] encTable = builder.getEncodeTable();
        if (encTable.length != STANDARD_ENCODE_TABLE.length) {
            throw new IllegalArgumentException("encodeTable must have exactly 64 entries.");
        }
        this.isStandardEncodeTable = Arrays.equals(encTable, STANDARD_ENCODE_TABLE);
        this.isUrlSafe = Arrays.equals(encTable, URL_SAFE_ENCODE_TABLE);
        if (builder.getLineSeparator().length > 0) {
            byte[] lineSeparatorB = builder.getLineSeparator();
            if (containsAlphabetOrPad(lineSeparatorB)) {
                String sep = StringUtils.newStringUtf8(lineSeparatorB);
                throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + sep + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            }
            if (builder.getLineLength() > 0) {
                this.encodeSize = lineSeparatorB.length + 4;
                this.lineSeparator = lineSeparatorB;
                return;
            } else {
                this.encodeSize = 4;
                this.lineSeparator = null;
                return;
            }
        }
        this.encodeSize = 4;
        this.lineSeparator = null;
    }

    @Deprecated
    public Base64(int lineLength) {
        this(lineLength, CHUNK_SEPARATOR);
    }

    @Deprecated
    public Base64(int lineLength, byte[] lineSeparator) {
        this(lineLength, lineSeparator, false);
    }

    @Deprecated
    public Base64(int lineLength, byte[] lineSeparator, boolean urlSafe) {
        this(builder().setLineLength(lineLength).setLineSeparator(lineSeparator != null ? lineSeparator : EMPTY_BYTE_ARRAY).setPadding((byte) 61).setEncodeTableRaw(toUrlSafeEncodeTable(urlSafe)).setDecodingPolicy(DECODING_POLICY_DEFAULT));
    }

    @Deprecated
    public Base64(int lineLength, byte[] lineSeparator, boolean urlSafe, CodecPolicy decodingPolicy) {
        this(builder().setLineLength(lineLength).setLineSeparator(lineSeparator).setPadding((byte) 61).setEncodeTableRaw(toUrlSafeEncodeTable(urlSafe)).setDecodingPolicy(decodingPolicy));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.codec.binary.BaseNCodec
    public void decode(byte[] input, int inPos, int inAvail, BaseNCodec.Context context) {
        int result;
        if (context.eof) {
            return;
        }
        if (inAvail < 0) {
            context.eof = true;
        }
        int decodeSize = this.encodeSize - 1;
        int i = 0;
        while (true) {
            if (i >= inAvail) {
                break;
            }
            byte[] buffer = ensureBufferSize(decodeSize, context);
            int inPos2 = inPos + 1;
            byte b = input[inPos];
            if (b == this.pad) {
                context.eof = true;
                break;
            }
            if (b >= 0 && b < this.decodeTable.length && (result = this.decodeTable[b]) >= 0) {
                context.modulus = (context.modulus + 1) % 4;
                context.ibitWorkArea = (context.ibitWorkArea << 6) + result;
                if (context.modulus == 0) {
                    int i2 = context.pos;
                    context.pos = i2 + 1;
                    buffer[i2] = (byte) ((context.ibitWorkArea >> 16) & 255);
                    int i3 = context.pos;
                    context.pos = i3 + 1;
                    buffer[i3] = (byte) ((context.ibitWorkArea >> 8) & 255);
                    int i4 = context.pos;
                    context.pos = i4 + 1;
                    buffer[i4] = (byte) (context.ibitWorkArea & 255);
                }
            }
            i++;
            inPos = inPos2;
        }
        if (context.eof && context.modulus != 0) {
            byte[] buffer2 = ensureBufferSize(decodeSize, context);
            switch (context.modulus) {
                case 1:
                    validateTrailingCharacter();
                    return;
                case 2:
                    validateCharacter(15, context);
                    context.ibitWorkArea >>= 4;
                    int i5 = context.pos;
                    context.pos = i5 + 1;
                    buffer2[i5] = (byte) (context.ibitWorkArea & 255);
                    return;
                case 3:
                    validateCharacter(3, context);
                    context.ibitWorkArea >>= 2;
                    int i6 = context.pos;
                    context.pos = i6 + 1;
                    buffer2[i6] = (byte) ((context.ibitWorkArea >> 8) & 255);
                    int i7 = context.pos;
                    context.pos = i7 + 1;
                    buffer2[i7] = (byte) (context.ibitWorkArea & 255);
                    return;
                default:
                    throw new IllegalStateException("Impossible modulus " + context.modulus);
            }
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
            if (context.modulus == 0 && this.lineLength == 0) {
                return;
            }
            byte[] buffer = ensureBufferSize(this.encodeSize, context);
            int savedPos = context.pos;
            switch (context.modulus) {
                case 0:
                    break;
                case 1:
                    int i = context.pos;
                    context.pos = i + 1;
                    buffer[i] = this.encodeTable[(context.ibitWorkArea >> 2) & 63];
                    int i2 = context.pos;
                    context.pos = i2 + 1;
                    buffer[i2] = this.encodeTable[(context.ibitWorkArea << 4) & 63];
                    if (this.isStandardEncodeTable) {
                        int i3 = context.pos;
                        context.pos = i3 + 1;
                        buffer[i3] = this.pad;
                        int i4 = context.pos;
                        context.pos = i4 + 1;
                        buffer[i4] = this.pad;
                        break;
                    }
                    break;
                case 2:
                    int i5 = context.pos;
                    context.pos = i5 + 1;
                    buffer[i5] = this.encodeTable[(context.ibitWorkArea >> 10) & 63];
                    int i6 = context.pos;
                    context.pos = i6 + 1;
                    buffer[i6] = this.encodeTable[(context.ibitWorkArea >> 4) & 63];
                    int i7 = context.pos;
                    context.pos = i7 + 1;
                    buffer[i7] = this.encodeTable[(context.ibitWorkArea << 2) & 63];
                    if (this.isStandardEncodeTable) {
                        int i8 = context.pos;
                        context.pos = i8 + 1;
                        buffer[i8] = this.pad;
                        break;
                    }
                    break;
                default:
                    throw new IllegalStateException("Impossible modulus " + context.modulus);
            }
            context.currentLinePos += context.pos - savedPos;
            if (this.lineLength > 0 && context.currentLinePos > 0) {
                System.arraycopy(this.lineSeparator, 0, buffer, context.pos, this.lineSeparator.length);
                context.pos += this.lineSeparator.length;
                return;
            }
            return;
        }
        int i9 = 0;
        while (i9 < inAvail) {
            byte[] buffer2 = ensureBufferSize(this.encodeSize, context);
            context.modulus = (context.modulus + 1) % 3;
            int inPos2 = inPos + 1;
            int i10 = bArr[inPos];
            if (i10 < 0) {
                i10 += 256;
            }
            context.ibitWorkArea = (context.ibitWorkArea << 8) + i10;
            if (context.modulus == 0) {
                int i11 = context.pos;
                context.pos = i11 + 1;
                buffer2[i11] = this.encodeTable[(context.ibitWorkArea >> 18) & 63];
                int i12 = context.pos;
                context.pos = i12 + 1;
                buffer2[i12] = this.encodeTable[(context.ibitWorkArea >> 12) & 63];
                int i13 = context.pos;
                context.pos = i13 + 1;
                buffer2[i13] = this.encodeTable[(context.ibitWorkArea >> 6) & 63];
                int i14 = context.pos;
                context.pos = i14 + 1;
                buffer2[i14] = this.encodeTable[context.ibitWorkArea & 63];
                context.currentLinePos += 4;
                if (this.lineLength > 0 && this.lineLength <= context.currentLinePos) {
                    System.arraycopy(this.lineSeparator, 0, buffer2, context.pos, this.lineSeparator.length);
                    context.pos += this.lineSeparator.length;
                    context.currentLinePos = 0;
                }
            }
            i9++;
            inPos = inPos2;
        }
    }

    byte[] getLineSeparator() {
        return this.lineSeparator;
    }

    @Override // org.apache.commons.codec.binary.BaseNCodec
    protected boolean isInAlphabet(byte octet) {
        return octet >= 0 && octet < this.decodeTable.length && this.decodeTable[octet] != -1;
    }

    public boolean isUrlSafe() {
        return this.isUrlSafe;
    }

    private void validateCharacter(int emptyBitsMask, BaseNCodec.Context context) {
        if (isStrictDecoding() && (context.ibitWorkArea & emptyBitsMask) != 0) {
            throw new IllegalArgumentException("Strict decoding: Last encoded character (before the paddings if any) is a valid base 64 alphabet but not a possible encoding. Expected the discarded bits from the character to be zero.");
        }
    }

    private void validateTrailingCharacter() {
        if (isStrictDecoding()) {
            throw new IllegalArgumentException("Strict decoding: Last encoded character (before the paddings if any) is a valid base 64 alphabet but not a possible encoding. Decoding requires at least two trailing 6-bit characters to create bytes.");
        }
    }
}
