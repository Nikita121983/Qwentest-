package org.apache.commons.math3.dfp;

/* loaded from: classes10.dex */
public class DfpDec extends Dfp {
    protected DfpDec(DfpField factory) {
        super(factory);
    }

    protected DfpDec(DfpField factory, byte x) {
        super(factory, x);
    }

    protected DfpDec(DfpField factory, int x) {
        super(factory, x);
    }

    protected DfpDec(DfpField factory, long x) {
        super(factory, x);
    }

    protected DfpDec(DfpField factory, double x) {
        super(factory, x);
        round(0);
    }

    public DfpDec(Dfp d) {
        super(d);
        round(0);
    }

    protected DfpDec(DfpField factory, String s) {
        super(factory, s);
        round(0);
    }

    protected DfpDec(DfpField factory, byte sign, byte nans) {
        super(factory, sign, nans);
    }

    @Override // org.apache.commons.math3.dfp.Dfp
    public Dfp newInstance() {
        return new DfpDec(getField());
    }

    @Override // org.apache.commons.math3.dfp.Dfp
    public Dfp newInstance(byte x) {
        return new DfpDec(getField(), x);
    }

    @Override // org.apache.commons.math3.dfp.Dfp
    public Dfp newInstance(int x) {
        return new DfpDec(getField(), x);
    }

    @Override // org.apache.commons.math3.dfp.Dfp
    public Dfp newInstance(long x) {
        return new DfpDec(getField(), x);
    }

    @Override // org.apache.commons.math3.dfp.Dfp
    public Dfp newInstance(double x) {
        return new DfpDec(getField(), x);
    }

    @Override // org.apache.commons.math3.dfp.Dfp
    public Dfp newInstance(Dfp d) {
        if (getField().getRadixDigits() != d.getField().getRadixDigits()) {
            getField().setIEEEFlagsBits(1);
            Dfp result = newInstance(getZero());
            result.nans = (byte) 3;
            return dotrap(1, "newInstance", d, result);
        }
        return new DfpDec(d);
    }

    @Override // org.apache.commons.math3.dfp.Dfp
    public Dfp newInstance(String s) {
        return new DfpDec(getField(), s);
    }

    @Override // org.apache.commons.math3.dfp.Dfp
    public Dfp newInstance(byte sign, byte nans) {
        return new DfpDec(getField(), sign, nans);
    }

    protected int getDecimalDigits() {
        return (getRadixDigits() * 4) - 3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.dfp.Dfp
    public int round(int in) {
        int n;
        int discarded;
        boolean inc;
        int i;
        boolean z = true;
        int msb = this.mant[this.mant.length - 1];
        int i2 = 0;
        if (msb == 0) {
            return 0;
        }
        int cmaxdigits = this.mant.length * 4;
        int lsbthreshold = 1000;
        while (lsbthreshold > msb) {
            lsbthreshold /= 10;
            cmaxdigits--;
        }
        int digits = getDecimalDigits();
        int lsbshift = cmaxdigits - digits;
        int lsd = lsbshift / 4;
        int lsbthreshold2 = 1;
        for (int i3 = 0; i3 < lsbshift % 4; i3++) {
            lsbthreshold2 *= 10;
        }
        int lsb = this.mant[lsd];
        if (lsbthreshold2 <= 1 && digits == (this.mant.length * 4) - 3) {
            return super.round(in);
        }
        if (lsbthreshold2 == 1) {
            n = (this.mant[lsd - 1] / 1000) % 10;
            int[] iArr = this.mant;
            int i4 = lsd - 1;
            iArr[i4] = iArr[i4] % 1000;
            discarded = in | this.mant[lsd - 1];
        } else {
            int n2 = lsb * 10;
            n = (n2 / lsbthreshold2) % 10;
            discarded = in | (lsb % (lsbthreshold2 / 10));
        }
        for (int i5 = 0; i5 < lsd; i5++) {
            discarded |= this.mant[i5];
            this.mant[i5] = 0;
        }
        this.mant[lsd] = (lsb / lsbthreshold2) * lsbthreshold2;
        switch (getField().getRoundingMode()) {
            case ROUND_DOWN:
                inc = false;
                break;
            case ROUND_UP:
                if (n != 0 || discarded != 0) {
                    inc = true;
                    break;
                } else {
                    inc = false;
                    break;
                }
            case ROUND_HALF_UP:
                if (n >= 5) {
                    inc = true;
                    break;
                } else {
                    inc = false;
                    break;
                }
            case ROUND_HALF_DOWN:
                if (n > 5) {
                    inc = true;
                    break;
                } else {
                    inc = false;
                    break;
                }
            case ROUND_HALF_EVEN:
                if (n > 5 || ((n == 5 && discarded != 0) || (n == 5 && discarded == 0 && ((lsb / lsbthreshold2) & 1) == 1))) {
                    inc = true;
                    break;
                } else {
                    inc = false;
                    break;
                }
            case ROUND_HALF_ODD:
                if (n > 5 || ((n == 5 && discarded != 0) || (n == 5 && discarded == 0 && ((lsb / lsbthreshold2) & 1) == 0))) {
                    inc = true;
                    break;
                } else {
                    inc = false;
                    break;
                }
            case ROUND_CEIL:
                if (this.sign != 1 || (n == 0 && discarded == 0)) {
                    inc = false;
                    break;
                } else {
                    inc = true;
                    break;
                }
            default:
                if (this.sign != -1 || (n == 0 && discarded == 0)) {
                    inc = false;
                    break;
                } else {
                    inc = true;
                    break;
                }
        }
        if (!inc) {
            i = 0;
        } else {
            int rh = lsbthreshold2;
            int i6 = lsd;
            while (true) {
                i = i2;
                if (i6 < this.mant.length) {
                    int r = this.mant[i6] + rh;
                    rh = r / 10000;
                    this.mant[i6] = r % 10000;
                    i6++;
                    i2 = i;
                    z = z;
                } else if (rh != 0) {
                    shiftRight();
                    this.mant[this.mant.length - 1] = rh;
                }
            }
        }
        if (this.exp < -32767) {
            getField().setIEEEFlagsBits(8);
            return 8;
        }
        if (this.exp > 32768) {
            getField().setIEEEFlagsBits(4);
            return 4;
        }
        if (n != 0 || discarded != 0) {
            getField().setIEEEFlagsBits(16);
            return 16;
        }
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.dfp.Dfp
    public Dfp nextAfter(Dfp x) {
        Dfp inc;
        Dfp result;
        if (getField().getRadixDigits() != x.getField().getRadixDigits()) {
            getField().setIEEEFlagsBits(1);
            Dfp result2 = newInstance(getZero());
            result2.nans = (byte) 3;
            return dotrap(1, "nextAfter", x, result2);
        }
        boolean up = false;
        if (lessThan(x)) {
            up = true;
        }
        if (equals(x)) {
            return newInstance(x);
        }
        if (lessThan(getZero())) {
            up = !up;
        }
        if (up) {
            Dfp inc2 = copysign(power10((intLog10() - getDecimalDigits()) + 1), this);
            if (equals(getZero())) {
                inc2 = power10K(((-32767) - this.mant.length) - 1);
            }
            if (inc2.equals(getZero())) {
                result = copysign(newInstance(getZero()), this);
            } else {
                result = add(inc2);
            }
        } else {
            Dfp inc3 = copysign(power10(intLog10()), this);
            if (equals(inc3)) {
                inc = inc3.divide(power10(getDecimalDigits()));
            } else {
                inc = inc3.divide(power10(getDecimalDigits() - 1));
            }
            if (equals(getZero())) {
                inc = power10K(((-32767) - this.mant.length) - 1);
            }
            if (inc.equals(getZero())) {
                result = copysign(newInstance(getZero()), this);
            } else {
                result = subtract(inc);
            }
        }
        if (result.classify() == 1 && classify() != 1) {
            getField().setIEEEFlagsBits(16);
            result = dotrap(16, "nextAfter", x, result);
        }
        if (result.equals(getZero()) && !equals(getZero())) {
            getField().setIEEEFlagsBits(16);
            return dotrap(16, "nextAfter", x, result);
        }
        return result;
    }
}
