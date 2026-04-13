package org.apache.commons.math3.geometry.partitioning.utilities;

import java.util.Arrays;
import org.apache.commons.math3.util.FastMath;

@Deprecated
/* loaded from: classes10.dex */
public class OrderedTuple implements Comparable<OrderedTuple> {
    private static final long EXPONENT_MASK = 9218868437227405312L;
    private static final long IMPLICIT_ONE = 4503599627370496L;
    private static final long MANTISSA_MASK = 4503599627370495L;
    private static final long SIGN_MASK = Long.MIN_VALUE;
    private double[] components;
    private long[] encoding;
    private int lsb;
    private boolean nan;
    private boolean negInf;
    private int offset;
    private boolean posInf;

    public OrderedTuple(double... components) {
        this.components = (double[]) components.clone();
        int msb = Integer.MIN_VALUE;
        this.lsb = Integer.MAX_VALUE;
        this.posInf = false;
        this.negInf = false;
        this.nan = false;
        for (int i = 0; i < components.length; i++) {
            if (Double.isInfinite(components[i])) {
                if (components[i] < 0.0d) {
                    this.negInf = true;
                } else {
                    this.posInf = true;
                }
            } else if (Double.isNaN(components[i])) {
                this.nan = true;
            } else {
                long b = Double.doubleToLongBits(components[i]);
                long m = mantissa(b);
                if (m != 0) {
                    int e = exponent(b);
                    msb = FastMath.max(msb, computeMSB(m) + e);
                    this.lsb = FastMath.min(this.lsb, computeLSB(m) + e);
                }
            }
        }
        if (this.posInf && this.negInf) {
            this.posInf = false;
            this.negInf = false;
            this.nan = true;
        }
        if (this.lsb <= msb) {
            encode(msb + 16);
        } else {
            this.encoding = new long[]{0};
        }
    }

    private void encode(int minOffset) {
        this.offset = minOffset + 31;
        this.offset -= this.offset % 32;
        if (this.encoding != null && this.encoding.length == 1 && this.encoding[0] == 0) {
            return;
        }
        int neededBits = (this.offset + 1) - this.lsb;
        int neededLongs = (neededBits + 62) / 63;
        this.encoding = new long[this.components.length * neededLongs];
        int eIndex = 0;
        int shift = 62;
        long word = 0;
        int k = this.offset;
        while (eIndex < this.encoding.length) {
            for (int vIndex = 0; vIndex < this.components.length; vIndex++) {
                if (getBit(vIndex, k) != 0) {
                    word |= 1 << shift;
                }
                int shift2 = shift - 1;
                if (shift != 0) {
                    shift = shift2;
                } else {
                    this.encoding[eIndex] = word;
                    shift = 62;
                    word = 0;
                    eIndex++;
                }
            }
            k--;
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(OrderedTuple ot) {
        if (this.components.length == ot.components.length) {
            if (this.nan) {
                return 1;
            }
            if (ot.nan || this.negInf || ot.posInf) {
                return -1;
            }
            if (this.posInf || ot.negInf) {
                return 1;
            }
            if (this.offset < ot.offset) {
                encode(ot.offset);
            } else if (this.offset > ot.offset) {
                ot.encode(this.offset);
            }
            int limit = FastMath.min(this.encoding.length, ot.encoding.length);
            for (int i = 0; i < limit; i++) {
                if (this.encoding[i] < ot.encoding[i]) {
                    return -1;
                }
                if (this.encoding[i] > ot.encoding[i]) {
                    return 1;
                }
            }
            if (this.encoding.length < ot.encoding.length) {
                return -1;
            }
            return this.encoding.length > ot.encoding.length ? 1 : 0;
        }
        return this.components.length - ot.components.length;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof OrderedTuple) && compareTo((OrderedTuple) other) == 0;
    }

    public int hashCode() {
        int hash = Arrays.hashCode(this.components);
        return (((((((((hash * 37) + this.offset) * 37) + this.lsb) * 37) + (this.posInf ? 97 : 71)) * 37) + (this.negInf ? 97 : 71)) * 37) + (this.nan ? 97 : 71);
    }

    public double[] getComponents() {
        return (double[]) this.components.clone();
    }

    private static long sign(long bits) {
        return Long.MIN_VALUE & bits;
    }

    private static int exponent(long bits) {
        return ((int) ((EXPONENT_MASK & bits) >> 52)) - 1075;
    }

    private static long mantissa(long bits) {
        return (EXPONENT_MASK & bits) == 0 ? (bits & 4503599627370495L) << 1 : (bits & 4503599627370495L) | 4503599627370496L;
    }

    private static int computeMSB(long l) {
        long ll = l;
        long mask = 4294967295L;
        int scale = 32;
        int msb = 0;
        while (scale != 0) {
            if ((ll & mask) != ll) {
                msb |= scale;
                ll >>= scale;
            }
            scale >>= 1;
            mask >>= scale;
        }
        return msb;
    }

    private static int computeLSB(long l) {
        long ll = l;
        long mask = -4294967296L;
        int scale = 32;
        int lsb = 0;
        while (scale != 0) {
            if ((ll & mask) == ll) {
                lsb |= scale;
                ll >>= scale;
            }
            scale >>= 1;
            mask >>= scale;
        }
        return lsb;
    }

    private int getBit(int i, int k) {
        long bits = Double.doubleToLongBits(this.components[i]);
        int e = exponent(bits);
        if (k < e || k > this.offset) {
            return 0;
        }
        if (k == this.offset) {
            return sign(bits) == 0 ? 1 : 0;
        }
        if (k > e + 52) {
            return sign(bits) == 0 ? 0 : 1;
        }
        long m = sign(bits) == 0 ? mantissa(bits) : -mantissa(bits);
        return (int) ((m >> (k - e)) & 1);
    }
}
