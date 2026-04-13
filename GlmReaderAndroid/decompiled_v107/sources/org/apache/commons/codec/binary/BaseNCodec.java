package org.apache.commons.codec.binary;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;

/* loaded from: classes9.dex */
public abstract class BaseNCodec implements BinaryEncoder, BinaryDecoder {
    private static final int DEFAULT_BUFFER_RESIZE_FACTOR = 2;
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    static final int EOF = -1;
    protected static final int MASK_8BITS = 255;
    private static final int MAX_BUFFER_SIZE = 2147483639;
    public static final int MIME_CHUNK_SIZE = 76;
    protected static final byte PAD_DEFAULT = 61;
    public static final int PEM_CHUNK_SIZE = 64;

    @Deprecated
    protected final byte PAD;
    private final int chunkSeparatorLength;
    final byte[] decodeTable;
    private final CodecPolicy decodingPolicy;
    final byte[] encodeTable;
    private final int encodedBlockSize;
    protected final int lineLength;
    protected final byte pad;
    private final int unencodedBlockSize;
    protected static final CodecPolicy DECODING_POLICY_DEFAULT = CodecPolicy.LENIENT;
    static final byte[] CHUNK_SEPARATOR = {13, 10};
    static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void decode(byte[] bArr, int i, int i2, Context context);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void encode(byte[] bArr, int i, int i2, Context context);

    protected abstract boolean isInAlphabet(byte b);

    /* loaded from: classes9.dex */
    public static abstract class AbstractBuilder<T, B extends AbstractBuilder<T, B>> implements Supplier<T> {
        private byte[] decodeTable;
        private final byte[] defaultEncodeTable;
        private byte[] encodeTable;
        private int encodedBlockSize;
        private int lineLength;
        private int unencodedBlockSize;
        private CodecPolicy decodingPolicy = BaseNCodec.DECODING_POLICY_DEFAULT;
        private byte[] lineSeparator = BaseNCodec.CHUNK_SEPARATOR;
        private byte padding = 61;

        /* JADX INFO: Access modifiers changed from: package-private */
        public AbstractBuilder(byte[] defaultEncodeTable) {
            this.defaultEncodeTable = defaultEncodeTable;
            this.encodeTable = defaultEncodeTable;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public B asThis() {
            return this;
        }

        byte[] getDecodeTable() {
            return this.decodeTable;
        }

        CodecPolicy getDecodingPolicy() {
            return this.decodingPolicy;
        }

        int getEncodedBlockSize() {
            return this.encodedBlockSize;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public byte[] getEncodeTable() {
            return this.encodeTable;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int getLineLength() {
            return this.lineLength;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public byte[] getLineSeparator() {
            return this.lineSeparator;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public byte getPadding() {
            return this.padding;
        }

        int getUnencodedBlockSize() {
            return this.unencodedBlockSize;
        }

        public B setDecodeTable(byte[] decodeTable) {
            this.decodeTable = decodeTable != null ? (byte[]) decodeTable.clone() : null;
            return asThis();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public B setDecodeTableRaw(byte[] decodeTable) {
            this.decodeTable = decodeTable;
            return asThis();
        }

        public B setDecodingPolicy(CodecPolicy decodingPolicy) {
            this.decodingPolicy = decodingPolicy != null ? decodingPolicy : BaseNCodec.DECODING_POLICY_DEFAULT;
            return asThis();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public B setEncodedBlockSize(int encodedBlockSize) {
            this.encodedBlockSize = encodedBlockSize;
            return asThis();
        }

        public B setEncodeTable(byte... encodeTable) {
            this.encodeTable = encodeTable != null ? (byte[]) encodeTable.clone() : this.defaultEncodeTable;
            return asThis();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public B setEncodeTableRaw(byte... encodeTable) {
            this.encodeTable = encodeTable != null ? encodeTable : this.defaultEncodeTable;
            return asThis();
        }

        public B setLineLength(int lineLength) {
            this.lineLength = Math.max(0, lineLength);
            return asThis();
        }

        public B setLineSeparator(byte... lineSeparator) {
            this.lineSeparator = lineSeparator != null ? (byte[]) lineSeparator.clone() : BaseNCodec.CHUNK_SEPARATOR;
            return asThis();
        }

        public B setPadding(byte padding) {
            this.padding = padding;
            return asThis();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public B setUnencodedBlockSize(int unencodedBlockSize) {
            this.unencodedBlockSize = unencodedBlockSize;
            return asThis();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class Context {
        byte[] buffer;
        int currentLinePos;
        boolean eof;
        int ibitWorkArea;
        long lbitWorkArea;
        int modulus;
        int pos;
        int readPos;

        public String toString() {
            return String.format("%s[buffer=%s, currentLinePos=%s, eof=%s, ibitWorkArea=%s, lbitWorkArea=%s, modulus=%s, pos=%s, readPos=%s]", getClass().getSimpleName(), Arrays.toString(this.buffer), Integer.valueOf(this.currentLinePos), Boolean.valueOf(this.eof), Integer.valueOf(this.ibitWorkArea), Long.valueOf(this.lbitWorkArea), Integer.valueOf(this.modulus), Integer.valueOf(this.pos), Integer.valueOf(this.readPos));
        }
    }

    private static int createPositiveCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError("Unable to allocate array size: " + (minCapacity & 4294967295L));
        }
        return Math.max(minCapacity, 2147483639);
    }

    public static byte[] getChunkSeparator() {
        return (byte[]) CHUNK_SEPARATOR.clone();
    }

    static int getLength(byte[] array) {
        if (array == null) {
            return 0;
        }
        return array.length;
    }

    @Deprecated
    protected static boolean isWhiteSpace(byte byteToCheck) {
        return Character.isWhitespace(byteToCheck);
    }

    private static byte[] resizeBuffer(Context context, int minCapacity) {
        int oldCapacity = context.buffer.length;
        int newCapacity = oldCapacity * 2;
        if (Integer.compareUnsigned(newCapacity, minCapacity) < 0) {
            newCapacity = minCapacity;
        }
        if (Integer.compareUnsigned(newCapacity, 2147483639) > 0) {
            newCapacity = createPositiveCapacity(minCapacity);
        }
        byte[] b = Arrays.copyOf(context.buffer, newCapacity);
        context.buffer = b;
        return b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseNCodec(AbstractBuilder<?, ?> builder) {
        this.PAD = (byte) 61;
        this.unencodedBlockSize = ((AbstractBuilder) builder).unencodedBlockSize;
        this.encodedBlockSize = ((AbstractBuilder) builder).encodedBlockSize;
        boolean useChunking = ((AbstractBuilder) builder).lineLength > 0 && ((AbstractBuilder) builder).lineSeparator.length > 0;
        this.lineLength = useChunking ? (((AbstractBuilder) builder).lineLength / ((AbstractBuilder) builder).encodedBlockSize) * ((AbstractBuilder) builder).encodedBlockSize : 0;
        this.chunkSeparatorLength = ((AbstractBuilder) builder).lineSeparator.length;
        this.pad = ((AbstractBuilder) builder).padding;
        this.decodingPolicy = (CodecPolicy) Objects.requireNonNull(((AbstractBuilder) builder).decodingPolicy, "codecPolicy");
        this.encodeTable = (byte[]) Objects.requireNonNull(builder.getEncodeTable(), "builder.getEncodeTable()");
        this.decodeTable = builder.getDecodeTable();
    }

    @Deprecated
    protected BaseNCodec(int unencodedBlockSize, int encodedBlockSize, int lineLength, int chunkSeparatorLength) {
        this(unencodedBlockSize, encodedBlockSize, lineLength, chunkSeparatorLength, (byte) 61);
    }

    @Deprecated
    protected BaseNCodec(int unencodedBlockSize, int encodedBlockSize, int lineLength, int chunkSeparatorLength, byte pad) {
        this(unencodedBlockSize, encodedBlockSize, lineLength, chunkSeparatorLength, pad, DECODING_POLICY_DEFAULT);
    }

    @Deprecated
    protected BaseNCodec(int unencodedBlockSize, int encodedBlockSize, int lineLength, int chunkSeparatorLength, byte pad, CodecPolicy decodingPolicy) {
        this.PAD = (byte) 61;
        this.unencodedBlockSize = unencodedBlockSize;
        this.encodedBlockSize = encodedBlockSize;
        boolean useChunking = lineLength > 0 && chunkSeparatorLength > 0;
        this.lineLength = useChunking ? (lineLength / encodedBlockSize) * encodedBlockSize : 0;
        this.chunkSeparatorLength = chunkSeparatorLength;
        this.pad = pad;
        this.decodingPolicy = (CodecPolicy) Objects.requireNonNull(decodingPolicy, "codecPolicy");
        this.encodeTable = null;
        this.decodeTable = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int available(Context context) {
        if (hasData(context)) {
            return context.pos - context.readPos;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean containsAlphabetOrPad(byte[] arrayOctet) {
        if (arrayOctet != null) {
            for (byte element : arrayOctet) {
                if (this.pad == element || isInAlphabet(element)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // org.apache.commons.codec.BinaryDecoder
    public byte[] decode(byte[] array) {
        if (BinaryCodec.isEmpty(array)) {
            return array;
        }
        Context context = new Context();
        decode(array, 0, array.length, context);
        decode(array, 0, -1, context);
        byte[] result = new byte[context.pos];
        readResults(result, 0, result.length, context);
        return result;
    }

    @Override // org.apache.commons.codec.Decoder
    public Object decode(Object obj) throws DecoderException {
        if (obj instanceof byte[]) {
            return decode((byte[]) obj);
        }
        if (obj instanceof String) {
            return decode((String) obj);
        }
        throw new DecoderException("Parameter supplied to Base-N decode is not a byte[] or a String");
    }

    public byte[] decode(String array) {
        return decode(StringUtils.getBytesUtf8(array));
    }

    @Override // org.apache.commons.codec.BinaryEncoder
    public byte[] encode(byte[] array) {
        if (BinaryCodec.isEmpty(array)) {
            return array;
        }
        return encode(array, 0, array.length);
    }

    public byte[] encode(byte[] array, int offset, int length) {
        if (BinaryCodec.isEmpty(array)) {
            return array;
        }
        Context context = new Context();
        encode(array, offset, length, context);
        encode(array, offset, -1, context);
        byte[] buf = new byte[context.pos - context.readPos];
        readResults(buf, 0, buf.length, context);
        return buf;
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (!(obj instanceof byte[])) {
            throw new EncoderException("Parameter supplied to Base-N encode is not a byte[]");
        }
        return encode((byte[]) obj);
    }

    public String encodeAsString(byte[] array) {
        return StringUtils.newStringUtf8(encode(array));
    }

    public String encodeToString(byte[] array) {
        return StringUtils.newStringUtf8(encode(array));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] ensureBufferSize(int size, Context context) {
        if (context.buffer == null) {
            context.buffer = new byte[Math.max(size, getDefaultBufferSize())];
            context.pos = 0;
            context.readPos = 0;
        } else if ((context.pos + size) - context.buffer.length > 0) {
            return resizeBuffer(context, context.pos + size);
        }
        return context.buffer;
    }

    public CodecPolicy getCodecPolicy() {
        return this.decodingPolicy;
    }

    protected int getDefaultBufferSize() {
        return 8192;
    }

    public long getEncodedLength(byte[] array) {
        long len = (((array.length + this.unencodedBlockSize) - 1) / this.unencodedBlockSize) * this.encodedBlockSize;
        if (this.lineLength > 0) {
            return len + ((((this.lineLength + len) - 1) / this.lineLength) * this.chunkSeparatorLength);
        }
        return len;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasData(Context context) {
        return context.pos > context.readPos;
    }

    public boolean isInAlphabet(byte[] arrayOctet, boolean allowWSPad) {
        for (byte octet : arrayOctet) {
            if (!isInAlphabet(octet) && (!allowWSPad || (octet != this.pad && !Character.isWhitespace(octet)))) {
                return false;
            }
        }
        return true;
    }

    public boolean isInAlphabet(String basen) {
        return isInAlphabet(StringUtils.getBytesUtf8(basen), true);
    }

    public boolean isStrictDecoding() {
        return this.decodingPolicy == CodecPolicy.STRICT;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int readResults(byte[] b, int bPos, int bAvail, Context context) {
        if (!hasData(context)) {
            return context.eof ? -1 : 0;
        }
        int len = Math.min(available(context), bAvail);
        System.arraycopy(context.buffer, context.readPos, b, bPos, len);
        context.readPos += len;
        if (!hasData(context)) {
            context.readPos = 0;
            context.pos = 0;
        }
        return len;
    }
}
