package org.apache.commons.collections4.bloomfilter;

/* loaded from: classes9.dex */
public class BitMaps {
    private static final int DIVIDE_BY_64 = 6;

    public static boolean contains(long[] bitMaps, int bitIndex) {
        return (bitMaps[getLongIndex(bitIndex)] & getLongBit(bitIndex)) != 0;
    }

    public static long getLongBit(int bitIndex) {
        return 1 << bitIndex;
    }

    public static int getLongIndex(int bitIndex) {
        return bitIndex >> 6;
    }

    public static int mod(long dividend, int divisor) {
        long quotient = ((dividend >>> 1) / divisor) << 1;
        long remainder = dividend - (divisor * quotient);
        return (int) (remainder >= ((long) divisor) ? remainder - divisor : remainder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long[] newBitMap(int numberOfBits) {
        return new long[numberOfBitMaps(numberOfBits)];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long[] newBitMap(Shape shape) {
        return newBitMap(shape.getNumberOfBits());
    }

    public static int numberOfBitMaps(int numberOfBits) {
        return ((numberOfBits - 1) >> 6) + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int numberOfBitMaps(Shape shape) {
        return numberOfBitMaps(shape.getNumberOfBits());
    }

    public static void set(long[] bitMaps, int bitIndex) {
        int longIndex = getLongIndex(bitIndex);
        bitMaps[longIndex] = bitMaps[longIndex] | getLongBit(bitIndex);
    }

    private BitMaps() {
    }
}
