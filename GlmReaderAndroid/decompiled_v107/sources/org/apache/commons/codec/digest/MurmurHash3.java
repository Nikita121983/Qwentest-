package org.apache.commons.codec.digest;

import kotlin.UByte;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.ss.formula.ptg.UnionPtg;

/* loaded from: classes9.dex */
public final class MurmurHash3 {
    private static final long C1 = -8663945395140668459L;
    private static final int C1_32 = -862048943;
    private static final long C2 = 5545529020109919103L;
    private static final int C2_32 = 461845907;
    public static final int DEFAULT_SEED = 104729;
    private static final int M = 5;
    private static final int M_32 = 5;
    private static final int N1 = 1390208809;
    private static final int N2 = 944331445;

    @Deprecated
    public static final long NULL_HASHCODE = 2862933555777941757L;
    private static final int N_32 = -430675100;
    private static final int R1 = 31;
    private static final int R1_32 = 15;
    private static final int R2 = 27;
    private static final int R2_32 = 13;
    private static final int R3 = 33;

    @Deprecated
    /* loaded from: classes9.dex */
    public static class IncrementalHash32 extends IncrementalHash32x86 {
        @Override // org.apache.commons.codec.digest.MurmurHash3.IncrementalHash32x86
        @Deprecated
        int finalise(int hash, int unprocessedLength, byte[] unprocessed, int totalLen) {
            int result = hash;
            int k1 = 0;
            switch (unprocessedLength) {
                case 3:
                    k1 = 0 ^ (unprocessed[2] << UnionPtg.sid);
                case 2:
                    k1 ^= unprocessed[1] << 8;
                case 1:
                    result ^= Integer.rotateLeft((k1 ^ unprocessed[0]) * (-862048943), 15) * MurmurHash3.C2_32;
                    break;
            }
            return MurmurHash3.fmix32(result ^ totalLen);
        }
    }

    /* loaded from: classes9.dex */
    public static class IncrementalHash32x86 {
        private static final int BLOCK_SIZE = 4;
        private int hash;
        private int totalLen;
        private final byte[] unprocessed = new byte[3];
        private int unprocessedLength;

        private static int orBytes(byte b1, byte b2, byte b3, byte b4) {
            return (b1 & UByte.MAX_VALUE) | ((b2 & UByte.MAX_VALUE) << 8) | ((b3 & UByte.MAX_VALUE) << 16) | ((b4 & UByte.MAX_VALUE) << 24);
        }

        public final void add(byte[] data, int offset, int length) {
            int newOffset;
            int k;
            int k2;
            if (length <= 0) {
                return;
            }
            this.totalLen += length;
            if ((this.unprocessedLength + length) - 4 < 0) {
                System.arraycopy(data, offset, this.unprocessed, this.unprocessedLength, length);
                this.unprocessedLength += length;
                return;
            }
            if (this.unprocessedLength > 0) {
                switch (this.unprocessedLength) {
                    case 1:
                        k2 = orBytes(this.unprocessed[0], data[offset], data[offset + 1], data[offset + 2]);
                        break;
                    case 2:
                        k2 = orBytes(this.unprocessed[0], this.unprocessed[1], data[offset], data[offset + 1]);
                        break;
                    case 3:
                        k2 = orBytes(this.unprocessed[0], this.unprocessed[1], this.unprocessed[2], data[offset]);
                        break;
                    default:
                        throw new IllegalStateException("Unprocessed length should be 1, 2, or 3: " + this.unprocessedLength);
                }
                this.hash = MurmurHash3.mix32(k2, this.hash);
                int consumed = 4 - this.unprocessedLength;
                newOffset = offset + consumed;
                k = length - consumed;
            } else {
                newOffset = offset;
                k = length;
            }
            int nblocks = k >> 2;
            for (int i = 0; i < nblocks; i++) {
                int index = (i << 2) + newOffset;
                int k3 = MurmurHash.getLittleEndianInt(data, index);
                this.hash = MurmurHash3.mix32(k3, this.hash);
            }
            int i2 = nblocks << 2;
            this.unprocessedLength = k - i2;
            if (this.unprocessedLength != 0) {
                System.arraycopy(data, newOffset + i2, this.unprocessed, 0, this.unprocessedLength);
            }
        }

        public final int end() {
            return finalise(this.hash, this.unprocessedLength, this.unprocessed, this.totalLen);
        }

        int finalise(int hash, int unprocessedLength, byte[] unprocessed, int totalLen) {
            int result = hash;
            int k1 = 0;
            switch (unprocessedLength) {
                case 3:
                    k1 = 0 ^ ((unprocessed[2] & UByte.MAX_VALUE) << 16);
                case 2:
                    k1 ^= (unprocessed[1] & UByte.MAX_VALUE) << 8;
                case 1:
                    result ^= Integer.rotateLeft((k1 ^ (unprocessed[0] & UByte.MAX_VALUE)) * (-862048943), 15) * MurmurHash3.C2_32;
                    break;
            }
            return MurmurHash3.fmix32(result ^ totalLen);
        }

        public final void start(int seed) {
            this.totalLen = 0;
            this.unprocessedLength = 0;
            this.hash = seed;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int fmix32(int hash) {
        int hash2 = (hash ^ (hash >>> 16)) * (-2048144789);
        int hash3 = (hash2 ^ (hash2 >>> 13)) * (-1028477387);
        return hash3 ^ (hash3 >>> 16);
    }

    private static long fmix64(long hash) {
        long hash2 = (hash ^ (hash >>> 33)) * (-49064778989728563L);
        long hash3 = (hash2 ^ (hash2 >>> 33)) * (-4265267296055464877L);
        return hash3 ^ (hash3 >>> 33);
    }

    public static long[] hash128(byte[] data) {
        return hash128(data, 0, data.length, DEFAULT_SEED);
    }

    @Deprecated
    public static long[] hash128(byte[] data, int offset, int length, int seed) {
        return hash128x64Internal(data, offset, length, seed);
    }

    @Deprecated
    public static long[] hash128(String data) {
        byte[] bytes = StringUtils.getBytesUtf8(data);
        return hash128(bytes, 0, bytes.length, DEFAULT_SEED);
    }

    public static long[] hash128x64(byte[] data) {
        return hash128x64(data, 0, data.length, 0);
    }

    public static long[] hash128x64(byte[] data, int offset, int length, int seed) {
        return hash128x64Internal(data, offset, length, seed & 4294967295L);
    }

    private static long[] hash128x64Internal(byte[] data, int offset, int length, long seed) {
        long k1;
        long h1 = seed;
        long h2 = seed;
        int nblocks = length >> 4;
        for (int i = 0; i < nblocks; i++) {
            int index = offset + (i << 4);
            long k12 = MurmurHash.getLittleEndianLong(data, index);
            long k2 = MurmurHash.getLittleEndianLong(data, index + 8);
            h1 = ((Long.rotateLeft(h1 ^ (Long.rotateLeft(k12 * C1, 31) * C2), 27) + h2) * 5) + 1390208809;
            h2 = (5 * (Long.rotateLeft(h2 ^ (Long.rotateLeft(k2 * C2, 33) * C1), 31) + h1)) + 944331445;
        }
        long k13 = 0;
        long k22 = 0;
        int index2 = offset + (nblocks << 4);
        switch ((offset + length) - index2) {
            case 1:
                h1 ^= Long.rotateLeft(((data[index2] & UByte.MAX_VALUE) ^ k13) * C1, 31) * C2;
                break;
            case 2:
                k13 ^= (data[index2 + 1] & 255) << 8;
                h1 ^= Long.rotateLeft(((data[index2] & UByte.MAX_VALUE) ^ k13) * C1, 31) * C2;
                break;
            case 3:
                k13 ^= (data[index2 + 2] & 255) << 16;
                k13 ^= (data[index2 + 1] & 255) << 8;
                h1 ^= Long.rotateLeft(((data[index2] & UByte.MAX_VALUE) ^ k13) * C1, 31) * C2;
                break;
            case 4:
                k13 ^= (data[index2 + 3] & 255) << 24;
                k13 ^= (data[index2 + 2] & 255) << 16;
                k13 ^= (data[index2 + 1] & 255) << 8;
                h1 ^= Long.rotateLeft(((data[index2] & UByte.MAX_VALUE) ^ k13) * C1, 31) * C2;
                break;
            case 5:
                k13 ^= (data[index2 + 4] & 255) << 32;
                k13 ^= (data[index2 + 3] & 255) << 24;
                k13 ^= (data[index2 + 2] & 255) << 16;
                k13 ^= (data[index2 + 1] & 255) << 8;
                h1 ^= Long.rotateLeft(((data[index2] & UByte.MAX_VALUE) ^ k13) * C1, 31) * C2;
                break;
            case 6:
                k13 ^= (data[index2 + 5] & 255) << 40;
                k13 ^= (data[index2 + 4] & 255) << 32;
                k13 ^= (data[index2 + 3] & 255) << 24;
                k13 ^= (data[index2 + 2] & 255) << 16;
                k13 ^= (data[index2 + 1] & 255) << 8;
                h1 ^= Long.rotateLeft(((data[index2] & UByte.MAX_VALUE) ^ k13) * C1, 31) * C2;
                break;
            case 7:
                k13 ^= (data[index2 + 6] & 255) << 48;
                k13 ^= (data[index2 + 5] & 255) << 40;
                k13 ^= (data[index2 + 4] & 255) << 32;
                k13 ^= (data[index2 + 3] & 255) << 24;
                k13 ^= (data[index2 + 2] & 255) << 16;
                k13 ^= (data[index2 + 1] & 255) << 8;
                h1 ^= Long.rotateLeft(((data[index2] & UByte.MAX_VALUE) ^ k13) * C1, 31) * C2;
                break;
            case 8:
                k1 = 0;
                k13 = k1 ^ ((data[index2 + 7] & 255) << 56);
                k13 ^= (data[index2 + 6] & 255) << 48;
                k13 ^= (data[index2 + 5] & 255) << 40;
                k13 ^= (data[index2 + 4] & 255) << 32;
                k13 ^= (data[index2 + 3] & 255) << 24;
                k13 ^= (data[index2 + 2] & 255) << 16;
                k13 ^= (data[index2 + 1] & 255) << 8;
                h1 ^= Long.rotateLeft(((data[index2] & UByte.MAX_VALUE) ^ k13) * C1, 31) * C2;
                break;
            case 9:
                k1 = 0;
                h2 ^= Long.rotateLeft(((data[index2 + 8] & UByte.MAX_VALUE) ^ k22) * C2, 33) * C1;
                k13 = k1 ^ ((data[index2 + 7] & 255) << 56);
                k13 ^= (data[index2 + 6] & 255) << 48;
                k13 ^= (data[index2 + 5] & 255) << 40;
                k13 ^= (data[index2 + 4] & 255) << 32;
                k13 ^= (data[index2 + 3] & 255) << 24;
                k13 ^= (data[index2 + 2] & 255) << 16;
                k13 ^= (data[index2 + 1] & 255) << 8;
                h1 ^= Long.rotateLeft(((data[index2] & UByte.MAX_VALUE) ^ k13) * C1, 31) * C2;
                break;
            case 10:
                k1 = 0;
                k22 ^= (data[index2 + 9] & 255) << 8;
                h2 ^= Long.rotateLeft(((data[index2 + 8] & UByte.MAX_VALUE) ^ k22) * C2, 33) * C1;
                k13 = k1 ^ ((data[index2 + 7] & 255) << 56);
                k13 ^= (data[index2 + 6] & 255) << 48;
                k13 ^= (data[index2 + 5] & 255) << 40;
                k13 ^= (data[index2 + 4] & 255) << 32;
                k13 ^= (data[index2 + 3] & 255) << 24;
                k13 ^= (data[index2 + 2] & 255) << 16;
                k13 ^= (data[index2 + 1] & 255) << 8;
                h1 ^= Long.rotateLeft(((data[index2] & UByte.MAX_VALUE) ^ k13) * C1, 31) * C2;
                break;
            case 11:
                k1 = 0;
                k22 ^= (data[index2 + 10] & 255) << 16;
                k22 ^= (data[index2 + 9] & 255) << 8;
                h2 ^= Long.rotateLeft(((data[index2 + 8] & UByte.MAX_VALUE) ^ k22) * C2, 33) * C1;
                k13 = k1 ^ ((data[index2 + 7] & 255) << 56);
                k13 ^= (data[index2 + 6] & 255) << 48;
                k13 ^= (data[index2 + 5] & 255) << 40;
                k13 ^= (data[index2 + 4] & 255) << 32;
                k13 ^= (data[index2 + 3] & 255) << 24;
                k13 ^= (data[index2 + 2] & 255) << 16;
                k13 ^= (data[index2 + 1] & 255) << 8;
                h1 ^= Long.rotateLeft(((data[index2] & UByte.MAX_VALUE) ^ k13) * C1, 31) * C2;
                break;
            case 12:
                k1 = 0;
                k22 ^= (data[index2 + 11] & 255) << 24;
                k22 ^= (data[index2 + 10] & 255) << 16;
                k22 ^= (data[index2 + 9] & 255) << 8;
                h2 ^= Long.rotateLeft(((data[index2 + 8] & UByte.MAX_VALUE) ^ k22) * C2, 33) * C1;
                k13 = k1 ^ ((data[index2 + 7] & 255) << 56);
                k13 ^= (data[index2 + 6] & 255) << 48;
                k13 ^= (data[index2 + 5] & 255) << 40;
                k13 ^= (data[index2 + 4] & 255) << 32;
                k13 ^= (data[index2 + 3] & 255) << 24;
                k13 ^= (data[index2 + 2] & 255) << 16;
                k13 ^= (data[index2 + 1] & 255) << 8;
                h1 ^= Long.rotateLeft(((data[index2] & UByte.MAX_VALUE) ^ k13) * C1, 31) * C2;
                break;
            case 13:
                k1 = 0;
                k22 ^= (data[index2 + 12] & 255) << 32;
                k22 ^= (data[index2 + 11] & 255) << 24;
                k22 ^= (data[index2 + 10] & 255) << 16;
                k22 ^= (data[index2 + 9] & 255) << 8;
                h2 ^= Long.rotateLeft(((data[index2 + 8] & UByte.MAX_VALUE) ^ k22) * C2, 33) * C1;
                k13 = k1 ^ ((data[index2 + 7] & 255) << 56);
                k13 ^= (data[index2 + 6] & 255) << 48;
                k13 ^= (data[index2 + 5] & 255) << 40;
                k13 ^= (data[index2 + 4] & 255) << 32;
                k13 ^= (data[index2 + 3] & 255) << 24;
                k13 ^= (data[index2 + 2] & 255) << 16;
                k13 ^= (data[index2 + 1] & 255) << 8;
                h1 ^= Long.rotateLeft(((data[index2] & UByte.MAX_VALUE) ^ k13) * C1, 31) * C2;
                break;
            case 14:
                k1 = 0;
                k22 ^= (data[index2 + 13] & 255) << 40;
                k22 ^= (data[index2 + 12] & 255) << 32;
                k22 ^= (data[index2 + 11] & 255) << 24;
                k22 ^= (data[index2 + 10] & 255) << 16;
                k22 ^= (data[index2 + 9] & 255) << 8;
                h2 ^= Long.rotateLeft(((data[index2 + 8] & UByte.MAX_VALUE) ^ k22) * C2, 33) * C1;
                k13 = k1 ^ ((data[index2 + 7] & 255) << 56);
                k13 ^= (data[index2 + 6] & 255) << 48;
                k13 ^= (data[index2 + 5] & 255) << 40;
                k13 ^= (data[index2 + 4] & 255) << 32;
                k13 ^= (data[index2 + 3] & 255) << 24;
                k13 ^= (data[index2 + 2] & 255) << 16;
                k13 ^= (data[index2 + 1] & 255) << 8;
                h1 ^= Long.rotateLeft(((data[index2] & UByte.MAX_VALUE) ^ k13) * C1, 31) * C2;
                break;
            case 15:
                k1 = 0;
                k22 = 0 ^ ((data[index2 + 14] & 255) << 48);
                k22 ^= (data[index2 + 13] & 255) << 40;
                k22 ^= (data[index2 + 12] & 255) << 32;
                k22 ^= (data[index2 + 11] & 255) << 24;
                k22 ^= (data[index2 + 10] & 255) << 16;
                k22 ^= (data[index2 + 9] & 255) << 8;
                h2 ^= Long.rotateLeft(((data[index2 + 8] & UByte.MAX_VALUE) ^ k22) * C2, 33) * C1;
                k13 = k1 ^ ((data[index2 + 7] & 255) << 56);
                k13 ^= (data[index2 + 6] & 255) << 48;
                k13 ^= (data[index2 + 5] & 255) << 40;
                k13 ^= (data[index2 + 4] & 255) << 32;
                k13 ^= (data[index2 + 3] & 255) << 24;
                k13 ^= (data[index2 + 2] & 255) << 16;
                k13 ^= (data[index2 + 1] & 255) << 8;
                h1 ^= Long.rotateLeft(((data[index2] & UByte.MAX_VALUE) ^ k13) * C1, 31) * C2;
                break;
        }
        long h22 = h2 ^ length;
        long h12 = (h1 ^ length) + h22;
        long h23 = h22 + h12;
        long h13 = fmix64(h12);
        long h24 = fmix64(h23);
        long h14 = h13 + h24;
        return new long[]{h14, h24 + h14};
    }

    @Deprecated
    public static int hash32(byte[] data) {
        return hash32(data, 0, data.length, DEFAULT_SEED);
    }

    @Deprecated
    public static int hash32(byte[] data, int length) {
        return hash32(data, length, DEFAULT_SEED);
    }

    @Deprecated
    public static int hash32(byte[] data, int length, int seed) {
        return hash32(data, 0, length, seed);
    }

    @Deprecated
    public static int hash32(byte[] data, int offset, int length, int seed) {
        int hash = seed;
        int nblocks = length >> 2;
        for (int i = 0; i < nblocks; i++) {
            int k = MurmurHash.getLittleEndianInt(data, (i << 2) + offset);
            hash = mix32(k, hash);
        }
        int i2 = nblocks << 2;
        int index = i2 + offset;
        int k1 = 0;
        switch ((offset + length) - index) {
            case 3:
                k1 = 0 ^ (data[index + 2] << UnionPtg.sid);
            case 2:
                k1 ^= data[index + 1] << 8;
            case 1:
                hash ^= Integer.rotateLeft((k1 ^ data[index]) * (-862048943), 15) * C2_32;
                break;
        }
        return fmix32(hash ^ length);
    }

    public static int hash32(long data) {
        return hash32(data, DEFAULT_SEED);
    }

    public static int hash32(long data, int seed) {
        long r0 = Long.reverseBytes(data);
        int hash = mix32((int) r0, seed);
        return fmix32(mix32((int) (r0 >>> 32), hash) ^ 8);
    }

    public static int hash32(long data1, long data2) {
        return hash32(data1, data2, DEFAULT_SEED);
    }

    public static int hash32(long data1, long data2, int seed) {
        long r0 = Long.reverseBytes(data1);
        long r1 = Long.reverseBytes(data2);
        int hash = mix32((int) r0, seed);
        return fmix32(mix32((int) (r1 >>> 32), mix32((int) r1, mix32((int) (r0 >>> 32), hash))) ^ 16);
    }

    @Deprecated
    public static int hash32(String data) {
        byte[] bytes = StringUtils.getBytesUtf8(data);
        return hash32(bytes, 0, bytes.length, DEFAULT_SEED);
    }

    public static int hash32x86(byte[] data) {
        return hash32x86(data, 0, data.length, 0);
    }

    public static int hash32x86(byte[] data, int offset, int length, int seed) {
        int hash = seed;
        int nblocks = length >> 2;
        for (int i = 0; i < nblocks; i++) {
            int k = MurmurHash.getLittleEndianInt(data, (i << 2) + offset);
            hash = mix32(k, hash);
        }
        int i2 = nblocks << 2;
        int index = i2 + offset;
        int k1 = 0;
        switch ((offset + length) - index) {
            case 3:
                k1 = 0 ^ ((data[index + 2] & UByte.MAX_VALUE) << 16);
            case 2:
                k1 ^= (data[index + 1] & UByte.MAX_VALUE) << 8;
            case 1:
                hash ^= Integer.rotateLeft((k1 ^ (data[index] & UByte.MAX_VALUE)) * (-862048943), 15) * C2_32;
                break;
        }
        return fmix32(hash ^ length);
    }

    @Deprecated
    public static long hash64(byte[] data) {
        return hash64(data, 0, data.length, DEFAULT_SEED);
    }

    @Deprecated
    public static long hash64(byte[] data, int offset, int length) {
        return hash64(data, offset, length, DEFAULT_SEED);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0043. Please report as an issue. */
    @Deprecated
    public static long hash64(byte[] data, int offset, int length, int seed) {
        long j;
        long hash = seed;
        int nblocks = length >> 3;
        for (int i = 0; i < nblocks; i++) {
            long k = MurmurHash.getLittleEndianLong(data, offset + (i << 3));
            hash = (Long.rotateLeft(hash ^ (Long.rotateLeft(k * C1, 31) * C2), 27) * 5) + 1390208809;
        }
        long k1 = 0;
        int index = offset + (nblocks << 3);
        switch ((offset + length) - index) {
            case 1:
                j = 5545529020109919103L;
                hash ^= Long.rotateLeft(((data[index] & 255) ^ k1) * C1, 31) * j;
                break;
            case 2:
                j = 5545529020109919103L;
                k1 ^= (data[index + 1] & 255) << 8;
                hash ^= Long.rotateLeft(((data[index] & 255) ^ k1) * C1, 31) * j;
                break;
            case 3:
                j = 5545529020109919103L;
                k1 ^= (data[index + 2] & 255) << 16;
                k1 ^= (data[index + 1] & 255) << 8;
                hash ^= Long.rotateLeft(((data[index] & 255) ^ k1) * C1, 31) * j;
                break;
            case 4:
                j = 5545529020109919103L;
                k1 ^= (data[index + 3] & 255) << 24;
                k1 ^= (data[index + 2] & 255) << 16;
                k1 ^= (data[index + 1] & 255) << 8;
                hash ^= Long.rotateLeft(((data[index] & 255) ^ k1) * C1, 31) * j;
                break;
            case 5:
                j = 5545529020109919103L;
                k1 ^= (data[index + 4] & 255) << 32;
                k1 ^= (data[index + 3] & 255) << 24;
                k1 ^= (data[index + 2] & 255) << 16;
                k1 ^= (data[index + 1] & 255) << 8;
                hash ^= Long.rotateLeft(((data[index] & 255) ^ k1) * C1, 31) * j;
                break;
            case 6:
                j = 5545529020109919103L;
                k1 ^= (data[index + 5] & 255) << 40;
                k1 ^= (data[index + 4] & 255) << 32;
                k1 ^= (data[index + 3] & 255) << 24;
                k1 ^= (data[index + 2] & 255) << 16;
                k1 ^= (data[index + 1] & 255) << 8;
                hash ^= Long.rotateLeft(((data[index] & 255) ^ k1) * C1, 31) * j;
                break;
            case 7:
                j = 5545529020109919103L;
                k1 = 0 ^ ((data[index + 6] & 255) << 48);
                k1 ^= (data[index + 5] & 255) << 40;
                k1 ^= (data[index + 4] & 255) << 32;
                k1 ^= (data[index + 3] & 255) << 24;
                k1 ^= (data[index + 2] & 255) << 16;
                k1 ^= (data[index + 1] & 255) << 8;
                hash ^= Long.rotateLeft(((data[index] & 255) ^ k1) * C1, 31) * j;
                break;
        }
        return fmix64(hash ^ length);
    }

    @Deprecated
    public static long hash64(int data) {
        long k1 = Integer.reverseBytes(data) & 4294967295L;
        long hash = 104729 ^ (Long.rotateLeft(k1 * C1, 31) * C2);
        return fmix64(hash ^ 4);
    }

    @Deprecated
    public static long hash64(long data) {
        long k = Long.reverseBytes(data);
        long hash = 104729 ^ (Long.rotateLeft(k * C1, 31) * C2);
        return fmix64(8 ^ ((Long.rotateLeft(hash, 27) * 5) + 1390208809));
    }

    @Deprecated
    public static long hash64(short data) {
        long k1 = 0 ^ ((data & 255) << 8);
        long hash = 104729 ^ (Long.rotateLeft((k1 ^ (((65280 & data) >> 8) & 255)) * C1, 31) * C2);
        return fmix64(hash ^ 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int mix32(int k, int hash) {
        return (Integer.rotateLeft(hash ^ (Integer.rotateLeft(k * (-862048943), 15) * C2_32), 13) * 5) + N_32;
    }

    private MurmurHash3() {
    }
}
