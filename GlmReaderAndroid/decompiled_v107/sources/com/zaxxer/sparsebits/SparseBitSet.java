package com.zaxxer.sparsebits;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.apache.logging.log4j.util.Chars;

/* loaded from: classes9.dex */
public class SparseBitSet implements Cloneable, Serializable {
    protected static final int INDEX_SIZE = 31;
    protected static final int LENGTH2 = 32;
    protected static final int LENGTH2_SIZE = 31;
    protected static final int LENGTH3 = 32;
    protected static final int LENGTH3_SIZE = 31;
    protected static final int LENGTH4 = 64;
    protected static final int LENGTH4_SIZE = 63;
    protected static final int LEVEL1 = 15;
    protected static final int LEVEL2 = 5;
    protected static final int LEVEL3 = 5;
    protected static final int LEVEL4 = 6;
    protected static final int MASK2 = 31;
    protected static final int MASK3 = 31;
    protected static final int MAX_LENGTH1 = 32768;
    protected static final int SHIFT1 = 10;
    protected static final int SHIFT2 = 5;
    protected static final int SHIFT3 = 6;
    protected static final int UNIT = 65536;
    private static final long serialVersionUID = -6663013367427929992L;
    protected transient long[][][] bits;
    protected transient int bitsLength;
    protected transient Cache cache;
    protected transient int compactionCount;
    protected transient EqualsStrategy equalsStrategy;
    protected transient long[] spare;
    protected transient UpdateStrategy updateStrategy;
    static int compactionCountDefault = 2;
    static final long[] ZERO_BLOCK = new long[32];
    protected static final transient AndStrategy andStrategy = new AndStrategy();
    protected static final transient AndNotStrategy andNotStrategy = new AndNotStrategy();
    protected static final transient ClearStrategy clearStrategy = new ClearStrategy();
    protected static final transient CopyStrategy copyStrategy = new CopyStrategy();
    protected static final transient FlipStrategy flipStrategy = new FlipStrategy();
    protected static transient IntersectsStrategy intersectsStrategy = new IntersectsStrategy();
    protected static final transient OrStrategy orStrategy = new OrStrategy();
    protected static final transient SetStrategy setStrategy = new SetStrategy();
    protected static final transient XorStrategy xorStrategy = new XorStrategy();

    /* loaded from: classes9.dex */
    public enum Statistics {
        Size,
        Length,
        Cardinality,
        Total_words,
        Set_array_length,
        Set_array_max_length,
        Level2_areas,
        Level2_area_length,
        Level3_blocks,
        Level3_block_length,
        Compaction_count_value
    }

    protected SparseBitSet(int capacity, int compactionCount) throws NegativeArraySizeException {
        if (capacity < 0) {
            throw new NegativeArraySizeException("(requested capacity=" + capacity + ") < 0");
        }
        resize(capacity - 1);
        this.compactionCount = compactionCount;
        constructorHelper();
        statisticsUpdate();
    }

    public SparseBitSet() {
        this(1, compactionCountDefault);
    }

    public SparseBitSet(int nbits) throws NegativeArraySizeException {
        this(nbits, compactionCountDefault);
    }

    public void and(int i, boolean value) throws IndexOutOfBoundsException {
        if (i + 1 < 1) {
            throw new IndexOutOfBoundsException("i=" + i);
        }
        if (!value) {
            clear(i);
        }
    }

    public void and(int i, int j, SparseBitSet b) throws IndexOutOfBoundsException {
        setScanner(i, j, b, andStrategy);
    }

    public void and(SparseBitSet b) {
        nullify(Math.min(this.bits.length, b.bits.length));
        setScanner(0, Math.min(this.bitsLength, b.bitsLength), b, andStrategy);
    }

    public static SparseBitSet and(SparseBitSet a, SparseBitSet b) {
        SparseBitSet result = a.m391clone();
        result.and(b);
        return result;
    }

    public void andNot(int i, boolean value) {
        if (i + 1 < 1) {
            throw new IndexOutOfBoundsException("i=" + i);
        }
        if (value) {
            clear(i);
        }
    }

    public void andNot(int i, int j, SparseBitSet b) throws IndexOutOfBoundsException {
        setScanner(i, j, b, andNotStrategy);
    }

    public void andNot(SparseBitSet b) {
        setScanner(0, Math.min(this.bitsLength, b.bitsLength), b, andNotStrategy);
    }

    public static SparseBitSet andNot(SparseBitSet a, SparseBitSet b) {
        SparseBitSet result = a.m391clone();
        result.andNot(b);
        return result;
    }

    public int cardinality() {
        statisticsUpdate();
        return this.cache.cardinality;
    }

    public void clear(int i) {
        long[] a3;
        if (i + 1 < 1) {
            throw new IndexOutOfBoundsException("i=" + i);
        }
        if (i >= this.bitsLength) {
            return;
        }
        int w = i >> 6;
        long[][] a2 = this.bits[w >> 10];
        if (a2 == null || (a3 = a2[(w >> 5) & 31]) == null) {
            return;
        }
        int i2 = w & 31;
        a3[i2] = a3[i2] & (~(1 << i));
        this.cache.hash = 0;
    }

    public void clear(int i, int j) throws IndexOutOfBoundsException {
        setScanner(i, j, null, clearStrategy);
    }

    public void clear() {
        nullify(0);
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public SparseBitSet m391clone() {
        try {
            SparseBitSet result = (SparseBitSet) super.clone();
            result.bits = null;
            result.resize(1);
            result.constructorHelper();
            result.equalsStrategy = null;
            result.setScanner(0, this.bitsLength, this, copyStrategy);
            return result;
        } catch (CloneNotSupportedException ex) {
            throw new InternalError(ex.getMessage());
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SparseBitSet)) {
            return false;
        }
        SparseBitSet b = (SparseBitSet) obj;
        if (this == b) {
            return true;
        }
        if (this.equalsStrategy == null) {
            this.equalsStrategy = new EqualsStrategy();
        }
        setScanner(0, Math.max(this.bitsLength, b.bitsLength), b, this.equalsStrategy);
        return this.equalsStrategy.result;
    }

    public void flip(int i) {
        if (i + 1 < 1) {
            throw new IndexOutOfBoundsException("i=" + i);
        }
        int w = i >> 6;
        int w1 = w >> 10;
        int w2 = (w >> 5) & 31;
        if (i >= this.bitsLength) {
            resize(i);
        }
        long[][] jArr = this.bits[w1];
        long[][] a2 = jArr;
        if (jArr == null) {
            long[][] jArr2 = new long[32];
            this.bits[w1] = jArr2;
            a2 = jArr2;
        }
        long[] jArr3 = a2[w2];
        long[] a3 = jArr3;
        if (jArr3 == null) {
            long[] jArr4 = new long[32];
            a2[w2] = jArr4;
            a3 = jArr4;
        }
        int i2 = w & 31;
        a3[i2] = a3[i2] ^ (1 << i);
        this.cache.hash = 0;
    }

    public void flip(int i, int j) throws IndexOutOfBoundsException {
        setScanner(i, j, null, flipStrategy);
    }

    public boolean get(int i) {
        long[][] a2;
        long[] a3;
        if (i + 1 < 1) {
            throw new IndexOutOfBoundsException("i=" + i);
        }
        int w = i >> 6;
        return (i >= this.bitsLength || (a2 = this.bits[w >> 10]) == null || (a3 = a2[(w >> 5) & 31]) == null || (a3[w & 31] & (1 << i)) == 0) ? false : true;
    }

    public SparseBitSet get(int i, int j) throws IndexOutOfBoundsException {
        SparseBitSet result = new SparseBitSet(j, this.compactionCount);
        result.setScanner(i, j, this, copyStrategy);
        return result;
    }

    public int hashCode() {
        statisticsUpdate();
        return this.cache.hash;
    }

    public boolean intersects(int i, int j, SparseBitSet b) throws IndexOutOfBoundsException {
        setScanner(i, j, b, intersectsStrategy);
        return intersectsStrategy.result;
    }

    public boolean intersects(SparseBitSet b) {
        setScanner(0, Math.max(this.bitsLength, b.bitsLength), b, intersectsStrategy);
        return intersectsStrategy.result;
    }

    public boolean isEmpty() {
        statisticsUpdate();
        return this.cache.cardinality == 0;
    }

    public int length() {
        statisticsUpdate();
        return this.cache.length;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0066, code lost:
    
        r3 = 0;
        r4 = 0;
        r5 = r5 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int nextClearBit(int r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            if (r1 < 0) goto L82
            int r2 = r1 >> 6
            r3 = r2 & 31
            int r4 = r2 >> 5
            r4 = r4 & 31
            int r5 = r2 >> 10
            r6 = -1
            long r8 = r6 << r1
            long[][][] r10 = r0.bits
            int r10 = r10.length
            if (r5 >= r10) goto L6c
            long[][][] r11 = r0.bits
            r11 = r11[r5]
            r12 = r11
            if (r11 == 0) goto L6c
            r11 = r12[r4]
            r13 = r11
            if (r11 == 0) goto L6c
            r14 = r13[r3]
            long r14 = ~r14
            long r6 = r6 << r1
            long r6 = r6 & r14
            r8 = r6
            r14 = 0
            int r6 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r6 != 0) goto L6c
            int r2 = r2 + 1
            r3 = r2 & 31
            int r6 = r2 >> 5
            r4 = r6 & 31
            int r5 = r2 >> 10
            r6 = -1
            r8 = r6
        L3e:
            if (r5 == r10) goto L6c
            long[][][] r6 = r0.bits
            r6 = r6[r5]
            r12 = r6
            if (r6 != 0) goto L48
            goto L6c
        L48:
            r6 = 32
            if (r4 == r6) goto L66
            r7 = r12[r4]
            r13 = r7
            if (r7 != 0) goto L52
            goto L6c
        L52:
            if (r3 == r6) goto L62
            r6 = r13[r3]
            long r6 = ~r6
            r8 = r6
            int r6 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r6 == 0) goto L5d
            goto L6c
        L5d:
            int r3 = r3 + 1
            r6 = 32
            goto L52
        L62:
            r3 = 0
            int r4 = r4 + 1
            goto L48
        L66:
            r6 = 0
            r3 = r6
            r4 = r6
            int r5 = r5 + 1
            goto L3e
        L6c:
            int r6 = r5 << 10
            int r7 = r4 << 5
            int r6 = r6 + r7
            int r6 = r6 + r3
            int r6 = r6 << 6
            int r7 = java.lang.Long.numberOfTrailingZeros(r8)
            int r6 = r6 + r7
            r7 = 2147483647(0x7fffffff, float:NaN)
            if (r6 != r7) goto L80
            r7 = -1
            goto L81
        L80:
            r7 = r6
        L81:
            return r7
        L82:
            java.lang.IndexOutOfBoundsException r2 = new java.lang.IndexOutOfBoundsException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "i="
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r1)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zaxxer.sparsebits.SparseBitSet.nextClearBit(int):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0030, code lost:
    
        if (r14 == 0) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int nextSetBit(int r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            if (r1 < 0) goto L77
            int r2 = r1 >> 6
            r3 = r2 & 31
            int r4 = r2 >> 5
            r4 = r4 & 31
            int r5 = r2 >> 10
            r6 = 0
            long[][][] r8 = r0.bits
            int r8 = r8.length
            if (r5 >= r8) goto L65
            long[][][] r9 = r0.bits
            r9 = r9[r5]
            r10 = r9
            r11 = 0
            if (r9 == 0) goto L32
            r9 = r10[r4]
            r13 = r9
            if (r9 == 0) goto L32
            r14 = r13[r3]
            r16 = -1
            long r16 = r16 << r1
            long r14 = r14 & r16
            r6 = r14
            int r9 = (r14 > r11 ? 1 : (r14 == r11 ? 0 : -1))
            if (r9 != 0) goto L65
        L32:
            int r2 = r2 + 1
            r3 = r2 & 31
            int r9 = r2 >> 5
            r4 = r9 & 31
            int r5 = r2 >> 10
        L3c:
            if (r5 == r8) goto L65
            long[][][] r9 = r0.bits
            r9 = r9[r5]
            r10 = r9
            if (r9 == 0) goto L5f
        L45:
            r9 = 32
            if (r4 == r9) goto L5f
            r13 = r10[r4]
            r14 = r13
            if (r13 == 0) goto L5b
        L4e:
            if (r3 == r9) goto L5b
            r15 = r14[r3]
            r6 = r15
            int r13 = (r15 > r11 ? 1 : (r15 == r11 ? 0 : -1))
            if (r13 == 0) goto L58
            goto L65
        L58:
            int r3 = r3 + 1
            goto L4e
        L5b:
            r3 = 0
            int r4 = r4 + 1
            goto L45
        L5f:
            r9 = 0
            r3 = r9
            r4 = r9
            int r5 = r5 + 1
            goto L3c
        L65:
            if (r5 < r8) goto L69
            r9 = -1
            goto L76
        L69:
            int r9 = r5 << 10
            int r10 = r4 << 5
            int r9 = r9 + r10
            int r9 = r9 + r3
            int r9 = r9 << 6
            int r10 = java.lang.Long.numberOfTrailingZeros(r6)
            int r9 = r9 + r10
        L76:
            return r9
        L77:
            java.lang.IndexOutOfBoundsException r2 = new java.lang.IndexOutOfBoundsException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "i="
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r1)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zaxxer.sparsebits.SparseBitSet.nextSetBit(int):int");
    }

    public int previousClearBit(int i) {
        if (i < 0) {
            if (i == -1) {
                return -1;
            }
            throw new IndexOutOfBoundsException("i=" + i);
        }
        long[][][] bits = this.bits;
        int aSize = bits.length - 1;
        int w = i >> 6;
        int w3 = w & 31;
        int w2 = (w >> 5) & 31;
        int w1 = w >> 10;
        if (w1 > aSize) {
            return i;
        }
        int w4 = i % 64;
        while (w1 >= 0) {
            long[][] a2 = bits[w1];
            if (a2 == null) {
                return ((((w1 << 10) + (w2 << 5)) + w3) << 6) + w4;
            }
            while (w2 >= 0) {
                long[] a3 = a2[w2];
                if (a3 == null) {
                    return ((((w1 << 10) + (w2 << 5)) + w3) << 6) + w4;
                }
                while (w3 >= 0) {
                    long word = a3[w3];
                    if (word == 0) {
                        return ((((w1 << 10) + (w2 << 5)) + w3) << 6) + w4;
                    }
                    for (int bitIdx = w4; bitIdx >= 0; bitIdx--) {
                        if (((1 << bitIdx) & word) == 0) {
                            return ((((w1 << 10) + (w2 << 5)) + w3) << 6) + bitIdx;
                        }
                    }
                    w4 = 63;
                    w3--;
                }
                w3 = 31;
                w2--;
            }
            w2 = 31;
            w1--;
        }
        return -1;
    }

    public int previousSetBit(int i) {
        int w2;
        int w3;
        int w4;
        if (i < 0) {
            if (i == -1) {
                return -1;
            }
            throw new IndexOutOfBoundsException("i=" + i);
        }
        long[][][] bits = this.bits;
        int aSize = bits.length - 1;
        int w = i >> 6;
        int w1 = w >> 10;
        if (w1 > aSize) {
            w1 = aSize;
            w2 = 31;
            w3 = 31;
            w4 = 63;
        } else {
            int w22 = w >> 5;
            w2 = w22 & 31;
            w3 = w & 31;
            w4 = i % 64;
        }
        while (w1 >= 0) {
            long[][] a2 = bits[w1];
            if (a2 != null) {
                while (w2 >= 0) {
                    long[] a3 = a2[w2];
                    if (a3 != null) {
                        while (w3 >= 0) {
                            long word = a3[w3];
                            if (word != 0) {
                                for (int bitIdx = w4; bitIdx >= 0; bitIdx--) {
                                    if (((1 << bitIdx) & word) != 0) {
                                        return ((((w1 << 10) + (w2 << 5)) + w3) << 6) + bitIdx;
                                    }
                                }
                            }
                            w4 = 63;
                            w3--;
                        }
                    }
                    w3 = 31;
                    w4 = 63;
                    w2--;
                }
            }
            w2 = 31;
            w3 = 31;
            w4 = 63;
            w1--;
        }
        return -1;
    }

    public void or(int i, boolean value) {
        if (i + 1 < 1) {
            throw new IndexOutOfBoundsException("i=" + i);
        }
        if (value) {
            set(i);
        }
    }

    public void or(int i, int j, SparseBitSet b) throws IndexOutOfBoundsException {
        setScanner(i, j, b, orStrategy);
    }

    public void or(SparseBitSet b) {
        setScanner(0, b.bitsLength, b, orStrategy);
    }

    public static SparseBitSet or(SparseBitSet a, SparseBitSet b) {
        SparseBitSet result = a.m391clone();
        result.or(b);
        return result;
    }

    public void set(int i) {
        if (i + 1 < 1) {
            throw new IndexOutOfBoundsException("i=" + i);
        }
        int w = i >> 6;
        int w1 = w >> 10;
        int w2 = (w >> 5) & 31;
        if (i >= this.bitsLength) {
            resize(i);
        }
        long[][] jArr = this.bits[w1];
        long[][] a2 = jArr;
        if (jArr == null) {
            long[][] jArr2 = new long[32];
            this.bits[w1] = jArr2;
            a2 = jArr2;
        }
        long[] jArr3 = a2[w2];
        long[] a3 = jArr3;
        if (jArr3 == null) {
            long[] jArr4 = new long[32];
            a2[w2] = jArr4;
            a3 = jArr4;
        }
        int i2 = w & 31;
        a3[i2] = a3[i2] | (1 << i);
        this.cache.hash = 0;
    }

    public void set(int i, boolean value) {
        if (value) {
            set(i);
        } else {
            clear(i);
        }
    }

    public void set(int i, int j) throws IndexOutOfBoundsException {
        setScanner(i, j, null, setStrategy);
    }

    public void set(int i, int j, boolean value) {
        if (value) {
            set(i, j);
        } else {
            clear(i, j);
        }
    }

    public int size() {
        statisticsUpdate();
        return this.cache.size;
    }

    public String statistics() {
        return statistics(null);
    }

    public String statistics(String[] values) {
        statisticsUpdate();
        String[] v = new String[Statistics.values().length];
        v[Statistics.Size.ordinal()] = Integer.toString(size());
        v[Statistics.Length.ordinal()] = Integer.toString(length());
        v[Statistics.Cardinality.ordinal()] = Integer.toString(cardinality());
        v[Statistics.Total_words.ordinal()] = Integer.toString(this.cache.count);
        v[Statistics.Set_array_length.ordinal()] = Integer.toString(this.bits.length);
        v[Statistics.Set_array_max_length.ordinal()] = Integer.toString(32768);
        v[Statistics.Level2_areas.ordinal()] = Integer.toString(this.cache.a2Count);
        v[Statistics.Level2_area_length.ordinal()] = Integer.toString(32);
        v[Statistics.Level3_blocks.ordinal()] = Integer.toString(this.cache.a3Count);
        v[Statistics.Level3_block_length.ordinal()] = Integer.toString(32);
        v[Statistics.Compaction_count_value.ordinal()] = Integer.toString(this.compactionCount);
        int longestLabel = 0;
        for (Statistics statistics : Statistics.values()) {
            longestLabel = Math.max(longestLabel, statistics.name().length());
        }
        StringBuilder result = new StringBuilder();
        for (Statistics s : Statistics.values()) {
            result.append(s.name());
            for (int i = 0; i != longestLabel - s.name().length(); i++) {
                result.append(Chars.SPACE);
            }
            result.append(" = ");
            result.append(v[s.ordinal()]);
            result.append('\n');
        }
        for (int i2 = 0; i2 != result.length(); i2++) {
            if (result.charAt(i2) == '_') {
                result.setCharAt(i2, Chars.SPACE);
            }
        }
        if (values != null) {
            int len = Math.min(values.length, v.length);
            System.arraycopy(v, 0, values, 0, len);
        }
        return result.toString();
    }

    public String toString() {
        StringBuilder p = new StringBuilder(200);
        p.append('{');
        int i = nextSetBit(0);
        while (i >= 0) {
            p.append(i);
            int j = nextSetBit(i + 1);
            if (this.compactionCount > 0) {
                if (j < 0) {
                    break;
                }
                int last = nextClearBit(i);
                int last2 = last < 0 ? Integer.MAX_VALUE : last;
                if (this.compactionCount + i < last2) {
                    p.append("..").append(last2 - 1);
                    j = nextSetBit(last2);
                }
            }
            if (j >= 0) {
                p.append(", ");
            }
            i = j;
        }
        p.append('}');
        return p.toString();
    }

    public void toStringCompaction(int count) {
        this.compactionCount = count;
    }

    public void toStringCompaction(boolean change) {
        if (change) {
            compactionCountDefault = this.compactionCount;
        }
    }

    public void xor(int i, boolean value) {
        if (i + 1 < 1) {
            throw new IndexOutOfBoundsException("i=" + i);
        }
        if (value) {
            flip(i);
        }
    }

    public void xor(int i, int j, SparseBitSet b) throws IndexOutOfBoundsException {
        setScanner(i, j, b, xorStrategy);
    }

    public void xor(SparseBitSet b) {
        setScanner(0, b.bitsLength, b, xorStrategy);
    }

    public static SparseBitSet xor(SparseBitSet a, SparseBitSet b) {
        SparseBitSet result = a.m391clone();
        result.xor(b);
        return result;
    }

    protected static void throwIndexOutOfBoundsException(int i, int j) throws IndexOutOfBoundsException {
        String s = "";
        if (i < 0) {
            s = "(i=" + i + ") < 0";
        }
        if (i == Integer.MAX_VALUE) {
            s = s + "(i=" + i + ")";
        }
        if (j < 0) {
            s = s + (s.isEmpty() ? "" : ", ") + "(j=" + j + ") < 0";
        }
        if (i > j) {
            s = s + (s.isEmpty() ? "" : ", ") + "(i=" + i + ") > (j=" + j + ")";
        }
        throw new IndexOutOfBoundsException(s);
    }

    protected final void constructorHelper() {
        this.spare = new long[32];
        this.cache = new Cache();
        this.updateStrategy = new UpdateStrategy();
    }

    protected final void nullify(int start) {
        int aLength = this.bits.length;
        if (start < aLength) {
            for (int w = start; w != aLength; w++) {
                this.bits[w] = null;
            }
            this.cache.hash = 0;
        }
    }

    protected final void resize(int index) {
        int w1 = (index >> 6) >> 10;
        int newSize = Integer.highestOneBit(w1);
        if (newSize == 0) {
            newSize = 1;
        }
        if (w1 >= newSize) {
            newSize <<= 1;
        }
        if (newSize > 32768) {
            newSize = 32768;
        }
        int aLength1 = this.bits != null ? this.bits.length : 0;
        if (newSize != aLength1 || this.bits == null) {
            long[][][] temp = new long[newSize][];
            if (aLength1 != 0) {
                System.arraycopy(this.bits, 0, temp, 0, Math.min(aLength1, newSize));
                nullify(0);
            }
            this.bits = temp;
            this.bitsLength = newSize == 32768 ? Integer.MAX_VALUE : 65536 * newSize;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0185 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x02d6 A[EDGE_INSN: B:149:0x02d6->B:150:0x02d6 BREAK  A[LOOP:1: B:67:0x010b->B:91:0x02ba], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:152:0x02f0 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00c4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0329 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x013a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0181  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected final void setScanner(int r49, int r50, com.zaxxer.sparsebits.SparseBitSet r51, com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy r52) throws java.lang.IndexOutOfBoundsException {
        /*
            Method dump skipped, instructions count: 834
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zaxxer.sparsebits.SparseBitSet.setScanner(int, int, com.zaxxer.sparsebits.SparseBitSet, com.zaxxer.sparsebits.SparseBitSet$AbstractStrategy):void");
    }

    protected final void statisticsUpdate() {
        if (this.cache.hash != 0) {
            return;
        }
        setScanner(0, this.bitsLength, null, this.updateStrategy);
    }

    private void writeObject(ObjectOutputStream s) throws IOException, InternalError {
        statisticsUpdate();
        s.defaultWriteObject();
        s.writeInt(this.compactionCount);
        s.writeInt(this.cache.length);
        int count = this.cache.count;
        s.writeInt(count);
        long[][][] a1 = this.bits;
        int aLength1 = a1.length;
        for (int w1 = 0; w1 != aLength1; w1++) {
            long[][] a2 = a1[w1];
            if (a2 != null) {
                for (int w2 = 0; w2 != 32; w2++) {
                    long[] a3 = a2[w2];
                    if (a3 != null) {
                        int base = (w1 << 10) + (w2 << 5);
                        for (int w3 = 0; w3 != 32; w3++) {
                            long word = a3[w3];
                            if (word != 0) {
                                s.writeInt(base + w3);
                                s.writeLong(word);
                                count--;
                            }
                        }
                    }
                }
            }
        }
        if (count != 0) {
            throw new InternalError("count of entries not consistent");
        }
        s.writeInt(this.cache.hash);
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.compactionCount = s.readInt();
        int aLength = s.readInt();
        resize(aLength);
        int count = s.readInt();
        for (int n = 0; n != count; n++) {
            int w = s.readInt();
            int w3 = w & 31;
            int w2 = (w >> 5) & 31;
            int w1 = w >> 10;
            long word = s.readLong();
            long[][] jArr = this.bits[w1];
            long[][] a2 = jArr;
            if (jArr == null) {
                long[][] jArr2 = new long[32];
                this.bits[w1] = jArr2;
                a2 = jArr2;
            }
            long[] jArr3 = a2[w2];
            long[] a3 = jArr3;
            if (jArr3 == null) {
                long[] jArr4 = new long[32];
                a2[w2] = jArr4;
                a3 = jArr4;
            }
            a3[w3] = word;
        }
        constructorHelper();
        statisticsUpdate();
        if (count != this.cache.count) {
            throw new InternalError("count of entries not consistent");
        }
        int hash = s.readInt();
        if (hash != this.cache.hash) {
            throw new IOException("deserialized hashCode mis-match");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public class Cache {
        protected transient int a2Count;
        protected transient int a3Count;
        protected transient int cardinality;
        protected transient int count;
        protected transient int hash;
        protected transient int length;
        protected transient int size;

        protected Cache() {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public static abstract class AbstractStrategy {
        static final int F_OP_F_EQ_F = 1;
        static final int F_OP_X_EQ_F = 2;
        static final int X_OP_F_EQ_F = 4;
        static final int X_OP_F_EQ_X = 8;

        protected abstract boolean block(int i, int i2, int i3, long[] jArr, long[] jArr2);

        protected abstract int properties();

        protected abstract boolean start(SparseBitSet sparseBitSet);

        protected abstract boolean word(int i, int i2, long[] jArr, long[] jArr2, long j);

        protected AbstractStrategy() {
        }

        protected void finish(int a2Count, int a3Count) {
        }

        protected final boolean isZeroBlock(long[] a3) {
            for (long word : a3) {
                if (word != 0) {
                    return false;
                }
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public static class AndStrategy extends AbstractStrategy {
        protected AndStrategy() {
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected int properties() {
            return 7;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean start(SparseBitSet b) {
            if (b == null) {
                throw new NullPointerException();
            }
            return true;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean word(int base, int u3, long[] a3, long[] b3, long mask) {
            long j = a3[u3] & (b3[u3] | (~mask));
            a3[u3] = j;
            return j == 0;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean block(int base, int u3, int v3, long[] a3, long[] b3) {
            boolean isZero = true;
            for (int w3 = u3; w3 != v3; w3++) {
                long j = a3[w3] & b3[w3];
                a3[w3] = j;
                isZero &= j == 0;
            }
            return isZero;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public static class AndNotStrategy extends AbstractStrategy {
        protected AndNotStrategy() {
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected int properties() {
            return 11;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean start(SparseBitSet b) {
            if (b == null) {
                throw new NullPointerException();
            }
            return true;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean word(int base, int u3, long[] a3, long[] b3, long mask) {
            long j = a3[u3] & (~(b3[u3] & mask));
            a3[u3] = j;
            return j == 0;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean block(int base, int u3, int v3, long[] a3, long[] b3) {
            boolean isZero = true;
            for (int w3 = u3; w3 != v3; w3++) {
                long j = a3[w3] & (~b3[w3]);
                a3[w3] = j;
                isZero &= j == 0;
            }
            return isZero;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public static class ClearStrategy extends AbstractStrategy {
        protected ClearStrategy() {
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected int properties() {
            return 3;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean start(SparseBitSet b) {
            return true;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean word(int base, int u3, long[] a3, long[] b3, long mask) {
            long j = a3[u3] & (~mask);
            a3[u3] = j;
            return j == 0;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean block(int base, int u3, int v3, long[] a3, long[] b3) {
            if (u3 != 0 || v3 != 32) {
                for (int w3 = u3; w3 != v3; w3++) {
                    a3[w3] = 0;
                }
                return true;
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public static class CopyStrategy extends AbstractStrategy {
        protected CopyStrategy() {
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected int properties() {
            return 5;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean start(SparseBitSet b) {
            return true;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean word(int base, int u3, long[] a3, long[] b3, long mask) {
            long j = b3[u3] & mask;
            a3[u3] = j;
            return j == 0;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean block(int base, int u3, int v3, long[] a3, long[] b3) {
            boolean isZero = true;
            for (int w3 = u3; w3 != v3; w3++) {
                long j = b3[w3];
                a3[w3] = j;
                isZero &= j == 0;
            }
            return isZero;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public static class EqualsStrategy extends AbstractStrategy {
        boolean result;

        protected EqualsStrategy() {
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected int properties() {
            return 1;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean start(SparseBitSet b) {
            if (b == null) {
                throw new NullPointerException();
            }
            this.result = true;
            return false;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean word(int base, int u3, long[] a3, long[] b3, long mask) {
            long word = a3[u3];
            this.result &= (word & mask) == (b3[u3] & mask);
            return word == 0;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean block(int base, int u3, int v3, long[] a3, long[] b3) {
            boolean isZero = true;
            for (int w3 = u3; w3 != v3; w3++) {
                long word = a3[w3];
                boolean z = false;
                this.result &= word == b3[w3];
                if (word == 0) {
                    z = true;
                }
                isZero &= z;
            }
            return isZero;
        }
    }

    /* loaded from: classes9.dex */
    protected static class FlipStrategy extends AbstractStrategy {
        protected FlipStrategy() {
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected int properties() {
            return 0;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean start(SparseBitSet b) {
            return true;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean word(int base, int u3, long[] a3, long[] b3, long mask) {
            long j = a3[u3] ^ mask;
            a3[u3] = j;
            return j == 0;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean block(int base, int u3, int v3, long[] a3, long[] b3) {
            boolean isZero = true;
            for (int w3 = u3; w3 != v3; w3++) {
                long j = ~a3[w3];
                a3[w3] = j;
                isZero &= j == 0;
            }
            return isZero;
        }
    }

    /* loaded from: classes9.dex */
    protected static class IntersectsStrategy extends AbstractStrategy {
        protected boolean result;

        protected IntersectsStrategy() {
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected int properties() {
            return 3;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean start(SparseBitSet b) {
            if (b == null) {
                throw new NullPointerException();
            }
            this.result = false;
            return false;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean word(int base, int u3, long[] a3, long[] b3, long mask) {
            long word = a3[u3];
            this.result |= ((b3[u3] & word) & mask) != 0;
            return word == 0;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean block(int base, int u3, int v3, long[] a3, long[] b3) {
            boolean isZero = true;
            for (int w3 = u3; w3 != v3; w3++) {
                long word = a3[w3];
                boolean z = false;
                this.result |= (b3[w3] & word) != 0;
                if (word == 0) {
                    z = true;
                }
                isZero &= z;
            }
            return isZero;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public static class OrStrategy extends AbstractStrategy {
        protected OrStrategy() {
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected int properties() {
            return 9;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean start(SparseBitSet b) {
            if (b == null) {
                throw new NullPointerException();
            }
            return true;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean word(int base, int u3, long[] a3, long[] b3, long mask) {
            long j = a3[u3] | (b3[u3] & mask);
            a3[u3] = j;
            return j == 0;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean block(int base, int u3, int v3, long[] a3, long[] b3) {
            boolean isZero = true;
            for (int w3 = u3; w3 != v3; w3++) {
                long j = a3[w3] | b3[w3];
                a3[w3] = j;
                isZero &= j == 0;
            }
            return isZero;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public static class SetStrategy extends AbstractStrategy {
        protected SetStrategy() {
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected int properties() {
            return 0;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean start(SparseBitSet b) {
            return true;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean word(int base, int u3, long[] a3, long[] b3, long mask) {
            a3[u3] = a3[u3] | mask;
            return false;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean block(int base, int u3, int v3, long[] a3, long[] b3) {
            for (int w3 = u3; w3 != v3; w3++) {
                a3[w3] = -1;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public class UpdateStrategy extends AbstractStrategy {
        protected transient int cardinality;
        protected transient int count;
        protected transient long hash;
        protected transient int wMax;
        protected transient int wMin;
        protected transient long wordMax;
        protected transient long wordMin;

        protected UpdateStrategy() {
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected int properties() {
            return 3;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean start(SparseBitSet b) {
            this.hash = 1234L;
            this.wMin = -1;
            this.wordMin = 0L;
            this.wMax = 0;
            this.wordMax = 0L;
            this.count = 0;
            this.cardinality = 0;
            return false;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean word(int base, int u3, long[] a3, long[] b3, long mask) {
            long word = a3[u3];
            long word1 = word & mask;
            if (word1 != 0) {
                compute(base + u3, word1);
            }
            return word == 0;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean block(int base, int u3, int v3, long[] a3, long[] b3) {
            boolean isZero = true;
            for (int w3 = 0; w3 != v3; w3++) {
                long word = a3[w3];
                if (word != 0) {
                    isZero = false;
                    compute(base + w3, word);
                }
            }
            return isZero;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected void finish(int a2Count, int a3Count) {
            SparseBitSet.this.cache.a2Count = a2Count;
            SparseBitSet.this.cache.a3Count = a3Count;
            SparseBitSet.this.cache.count = this.count;
            SparseBitSet.this.cache.cardinality = this.cardinality;
            SparseBitSet.this.cache.length = ((this.wMax + 1) * 64) - Long.numberOfLeadingZeros(this.wordMax);
            SparseBitSet.this.cache.size = (SparseBitSet.this.cache.length - (this.wMin * 64)) - Long.numberOfTrailingZeros(this.wordMin);
            SparseBitSet.this.cache.hash = (int) ((this.hash >> 32) ^ this.hash);
        }

        private void compute(int index, long word) {
            this.count++;
            this.hash ^= (index + 1) * word;
            if (this.wMin < 0) {
                this.wMin = index;
                this.wordMin = word;
            }
            this.wMax = index;
            this.wordMax = word;
            this.cardinality += Long.bitCount(word);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public static class XorStrategy extends AbstractStrategy {
        protected XorStrategy() {
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected int properties() {
            return 9;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean start(SparseBitSet b) {
            if (b == null) {
                throw new NullPointerException();
            }
            return true;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean word(int base, int u3, long[] a3, long[] b3, long mask) {
            long j = a3[u3] ^ (b3[u3] & mask);
            a3[u3] = j;
            return j == 0;
        }

        @Override // com.zaxxer.sparsebits.SparseBitSet.AbstractStrategy
        protected boolean block(int base, int u3, int v3, long[] a3, long[] b3) {
            boolean isZero = true;
            for (int w3 = u3; w3 != v3; w3++) {
                long j = a3[w3] ^ b3[w3];
                a3[w3] = j;
                isZero &= j == 0;
            }
            return isZero;
        }
    }
}
