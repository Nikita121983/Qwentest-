package org.apache.commons.lang3;

import java.util.Objects;
import java.util.Random;

/* loaded from: classes9.dex */
final class CachedRandomBits {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int BITS_PER_BYTE = 8;
    private static final int BIT_INDEX_MASK = 7;
    private static final int MAX_BITS = 32;
    private static final int MAX_CACHE_SIZE = 268435455;
    private int bitIndex;
    private final byte[] cache;
    private final Random random;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CachedRandomBits(int cacheSize, Random random) {
        if (cacheSize <= 0) {
            throw new IllegalArgumentException("cacheSize must be positive");
        }
        this.cache = cacheSize <= MAX_CACHE_SIZE ? new byte[cacheSize] : new byte[MAX_CACHE_SIZE];
        this.random = (Random) Objects.requireNonNull(random, "random");
        this.random.nextBytes(this.cache);
        this.bitIndex = 0;
    }

    public int nextBits(int bits) {
        if (bits > 32 || bits <= 0) {
            throw new IllegalArgumentException("number of bits must be between 1 and 32");
        }
        int result = 0;
        int generatedBits = 0;
        while (generatedBits < bits) {
            if ((this.bitIndex >> 3) >= this.cache.length) {
                if (this.bitIndex != this.cache.length * 8) {
                    throw new AssertionError();
                }
                this.random.nextBytes(this.cache);
                this.bitIndex = 0;
            }
            int generatedBitsInIteration = Math.min(8 - (this.bitIndex & 7), bits - generatedBits);
            result = (result << generatedBitsInIteration) | ((this.cache[this.bitIndex >> 3] >> (this.bitIndex & 7)) & ((1 << generatedBitsInIteration) - 1));
            generatedBits += generatedBitsInIteration;
            this.bitIndex += generatedBitsInIteration;
        }
        return result;
    }
}
