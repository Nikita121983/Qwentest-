package org.apache.commons.codec.net;

import java.nio.ByteBuffer;
import java.util.BitSet;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.poi.ss.formula.ptg.AreaErrPtg;

/* loaded from: classes9.dex */
public class PercentCodec implements BinaryEncoder, BinaryDecoder {
    private static final byte ESCAPE_CHAR = 37;
    private final BitSet alwaysEncodeChars;
    private int alwaysEncodeCharsMax;
    private int alwaysEncodeCharsMin;
    private final boolean plusForSpace;

    public PercentCodec() {
        this.alwaysEncodeChars = new BitSet();
        this.alwaysEncodeCharsMin = Integer.MAX_VALUE;
        this.alwaysEncodeCharsMax = Integer.MIN_VALUE;
        this.plusForSpace = false;
        insertAlwaysEncodeChar(ESCAPE_CHAR);
    }

    public PercentCodec(byte[] alwaysEncodeChars, boolean plusForSpace) {
        this.alwaysEncodeChars = new BitSet();
        this.alwaysEncodeCharsMin = Integer.MAX_VALUE;
        this.alwaysEncodeCharsMax = Integer.MIN_VALUE;
        this.plusForSpace = plusForSpace;
        insertAlwaysEncodeChars(alwaysEncodeChars);
    }

    private boolean canEncode(byte c) {
        return !isAsciiChar(c) || (inAlwaysEncodeCharsRange(c) && this.alwaysEncodeChars.get(c));
    }

    private boolean containsSpace(byte[] bytes) {
        for (byte b : bytes) {
            if (b == 32) {
                return true;
            }
        }
        return false;
    }

    @Override // org.apache.commons.codec.BinaryDecoder
    public byte[] decode(byte[] bytes) throws DecoderException {
        if (bytes == null) {
            return null;
        }
        ByteBuffer buffer = ByteBuffer.allocate(expectedDecodingBytes(bytes));
        int i = 0;
        while (i < bytes.length) {
            byte b = bytes[i];
            if (b == 37) {
                int i2 = i + 1;
                try {
                    int u = Utils.digit16(bytes[i2]);
                    i = i2 + 1;
                    int l = Utils.digit16(bytes[i]);
                    buffer.put((byte) ((u << 4) + l));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DecoderException("Invalid percent decoding: ", e);
                }
            } else if (this.plusForSpace && b == 43) {
                buffer.put((byte) 32);
            } else {
                buffer.put(b);
            }
            i++;
        }
        return buffer.array();
    }

    @Override // org.apache.commons.codec.Decoder
    public Object decode(Object obj) throws DecoderException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            return decode((byte[]) obj);
        }
        throw new DecoderException("Objects of type " + obj.getClass().getName() + " cannot be Percent decoded");
    }

    private byte[] doEncode(byte[] bytes, int expectedLength, boolean willEncode) {
        ByteBuffer buffer = ByteBuffer.allocate(expectedLength);
        for (byte b : bytes) {
            if (willEncode && canEncode(b)) {
                byte bb = b;
                if (bb < 0) {
                    bb = (byte) (bb + 256);
                }
                char hex1 = Utils.hexChar(bb >> 4);
                char hex2 = Utils.hexChar(bb);
                buffer.put(ESCAPE_CHAR);
                buffer.put((byte) hex1);
                buffer.put((byte) hex2);
            } else if (this.plusForSpace && b == 32) {
                buffer.put(AreaErrPtg.sid);
            } else {
                buffer.put(b);
            }
        }
        return buffer.array();
    }

    @Override // org.apache.commons.codec.BinaryEncoder
    public byte[] encode(byte[] bytes) throws EncoderException {
        if (bytes == null) {
            return null;
        }
        int expectedEncodingBytes = expectedEncodingBytes(bytes);
        boolean willEncode = expectedEncodingBytes != bytes.length;
        if (willEncode || (this.plusForSpace && containsSpace(bytes))) {
            return doEncode(bytes, expectedEncodingBytes, willEncode);
        }
        return bytes;
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            return encode((byte[]) obj);
        }
        throw new EncoderException("Objects of type " + obj.getClass().getName() + " cannot be Percent encoded");
    }

    private int expectedDecodingBytes(byte[] bytes) {
        int byteCount = 0;
        int i = 0;
        while (i < bytes.length) {
            byte b = bytes[i];
            i += b == 37 ? 3 : 1;
            byteCount++;
        }
        return byteCount;
    }

    private int expectedEncodingBytes(byte[] bytes) {
        int byteCount = 0;
        for (byte b : bytes) {
            byteCount += canEncode(b) ? 3 : 1;
        }
        return byteCount;
    }

    private boolean inAlwaysEncodeCharsRange(byte c) {
        return c >= this.alwaysEncodeCharsMin && c <= this.alwaysEncodeCharsMax;
    }

    private void insertAlwaysEncodeChar(byte b) {
        if (b < 0) {
            throw new IllegalArgumentException("byte must be >= 0");
        }
        this.alwaysEncodeChars.set(b);
        if (b < this.alwaysEncodeCharsMin) {
            this.alwaysEncodeCharsMin = b;
        }
        if (b > this.alwaysEncodeCharsMax) {
            this.alwaysEncodeCharsMax = b;
        }
    }

    private void insertAlwaysEncodeChars(byte[] alwaysEncodeCharsArray) {
        if (alwaysEncodeCharsArray != null) {
            for (byte b : alwaysEncodeCharsArray) {
                insertAlwaysEncodeChar(b);
            }
        }
        insertAlwaysEncodeChar(ESCAPE_CHAR);
    }

    private boolean isAsciiChar(byte c) {
        return c >= 0;
    }
}
