package org.apache.commons.compress.archivers.zip;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public final class ZipEightByteInteger implements Serializable {
    static final int BYTES = 8;
    private static final long serialVersionUID = 1;
    private final long value;
    public static final ZipEightByteInteger ZERO = new ZipEightByteInteger(0);
    private static final BigInteger HIGHEST_BIT = BigInteger.ONE.shiftLeft(63);

    public static byte[] getBytes(BigInteger value) {
        return getBytes(value.longValue());
    }

    public static byte[] getBytes(long value) {
        return ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(value).array();
    }

    public static long getLongValue(byte[] bytes) {
        return getLongValue(bytes, 0);
    }

    public static long getLongValue(byte[] bytes, int offset) {
        return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getLong(offset);
    }

    public static BigInteger getValue(byte[] bytes) {
        return getValue(bytes, 0);
    }

    public static BigInteger getValue(byte[] bytes, int offset) {
        return toUnsignedBigInteger(getLongValue(bytes, offset));
    }

    static BigInteger toUnsignedBigInteger(long value) {
        if (value >= 0) {
            return BigInteger.valueOf(value);
        }
        return BigInteger.valueOf(Long.MAX_VALUE & value).add(HIGHEST_BIT);
    }

    public ZipEightByteInteger(BigInteger value) {
        this.value = value.longValue();
    }

    public ZipEightByteInteger(byte[] bytes) {
        this(bytes, 0);
    }

    public ZipEightByteInteger(byte[] bytes, int offset) {
        this.value = getLongValue(bytes, offset);
    }

    public ZipEightByteInteger(long value) {
        this.value = value;
    }

    public boolean equals(Object o) {
        return (o instanceof ZipEightByteInteger) && this.value == ((ZipEightByteInteger) o).value;
    }

    public byte[] getBytes() {
        return getBytes(this.value);
    }

    public long getLongValue() {
        return this.value;
    }

    public BigInteger getValue() {
        return toUnsignedBigInteger(this.value);
    }

    public int hashCode() {
        return Long.hashCode(this.value);
    }

    public String toString() {
        return Long.toUnsignedString(this.value);
    }
}
