package org.apache.commons.math3.dfp;

import java.util.Arrays;
import kotlin.time.InstantKt;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.dfp.DfpField;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.apache.poi.ss.util.IEEEDouble;

/* loaded from: classes10.dex */
public class Dfp implements RealFieldElement<Dfp> {
    private static final String ADD_TRAP = "add";
    private static final String ALIGN_TRAP = "align";
    private static final String DIVIDE_TRAP = "divide";
    public static final int ERR_SCALE = 32760;
    public static final byte FINITE = 0;
    private static final String GREATER_THAN_TRAP = "greaterThan";
    public static final byte INFINITE = 1;
    private static final String LESS_THAN_TRAP = "lessThan";
    public static final int MAX_EXP = 32768;
    public static final int MIN_EXP = -32767;
    private static final String MULTIPLY_TRAP = "multiply";
    private static final String NAN_STRING = "NaN";
    private static final String NEG_INFINITY_STRING = "-Infinity";
    private static final String NEW_INSTANCE_TRAP = "newInstance";
    private static final String NEXT_AFTER_TRAP = "nextAfter";
    private static final String POS_INFINITY_STRING = "Infinity";
    public static final byte QNAN = 3;
    public static final int RADIX = 10000;
    public static final byte SNAN = 2;
    private static final String SQRT_TRAP = "sqrt";
    private static final String TRUNC_TRAP = "trunc";
    protected int exp;
    private final DfpField field;
    protected int[] mant;
    protected byte nans;
    protected byte sign;

    /* JADX INFO: Access modifiers changed from: protected */
    public Dfp(DfpField field) {
        this.mant = new int[field.getRadixDigits()];
        this.sign = (byte) 1;
        this.exp = 0;
        this.nans = (byte) 0;
        this.field = field;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Dfp(DfpField field, byte x) {
        this(field, x);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Dfp(DfpField field, int x) {
        this(field, x);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Dfp(DfpField field, long x) {
        this.mant = new int[field.getRadixDigits()];
        this.nans = (byte) 0;
        this.field = field;
        boolean isLongMin = false;
        if (x == Long.MIN_VALUE) {
            isLongMin = true;
            x++;
        }
        if (x < 0) {
            this.sign = (byte) -1;
            x = -x;
        } else {
            this.sign = (byte) 1;
        }
        this.exp = 0;
        while (x != 0) {
            System.arraycopy(this.mant, this.mant.length - this.exp, this.mant, (this.mant.length - 1) - this.exp, this.exp);
            this.mant[this.mant.length - 1] = (int) (x % 10000);
            x /= 10000;
            this.exp++;
        }
        if (isLongMin) {
            for (int i = 0; i < this.mant.length - 1; i++) {
                if (this.mant[i] != 0) {
                    int[] iArr = this.mant;
                    iArr[i] = iArr[i] + 1;
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Dfp(DfpField field, double x) {
        this.mant = new int[field.getRadixDigits()];
        this.sign = (byte) 1;
        this.exp = 0;
        this.nans = (byte) 0;
        this.field = field;
        long bits = Double.doubleToLongBits(x);
        long mantissa = bits & IEEEDouble.FRAC_MASK;
        int exponent = ((int) ((9218868437227405312L & bits) >> 52)) - 1023;
        if (exponent == -1023) {
            if (x == 0.0d) {
                if ((bits & Long.MIN_VALUE) != 0) {
                    this.sign = (byte) -1;
                    return;
                }
                return;
            } else {
                exponent++;
                while ((mantissa & IEEEDouble.FRAC_ASSUMED_HIGH_BIT) == 0) {
                    exponent--;
                    mantissa <<= 1;
                }
                mantissa &= IEEEDouble.FRAC_MASK;
            }
        }
        if (exponent != 1024) {
            Dfp xdfp = new Dfp(field, mantissa).divide(new Dfp(field, IEEEDouble.FRAC_ASSUMED_HIGH_BIT)).add(field.getOne()).multiply(DfpMath.pow(field.getTwo(), exponent));
            xdfp = (bits & Long.MIN_VALUE) != 0 ? xdfp.negate() : xdfp;
            System.arraycopy(xdfp.mant, 0, this.mant, 0, this.mant.length);
            this.sign = xdfp.sign;
            this.exp = xdfp.exp;
            this.nans = xdfp.nans;
            return;
        }
        if (x != x) {
            this.sign = (byte) 1;
            this.nans = (byte) 3;
        } else if (x < 0.0d) {
            this.sign = (byte) -1;
            this.nans = (byte) 1;
        } else {
            this.sign = (byte) 1;
            this.nans = (byte) 1;
        }
    }

    public Dfp(Dfp d) {
        this.mant = (int[]) d.mant.clone();
        this.sign = d.sign;
        this.exp = d.exp;
        this.nans = d.nans;
        this.field = d.field;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Dfp(DfpField field, String s) {
        char c;
        char c2;
        int i;
        char c3;
        String fpexp;
        char c4;
        int i2;
        char c5;
        this.mant = new int[field.getRadixDigits()];
        this.sign = (byte) 1;
        this.exp = 0;
        this.nans = (byte) 0;
        this.field = field;
        boolean decimalFound = false;
        int i3 = 4;
        char[] striped = new char[(getRadixDigits() * 4) + 8];
        if (s.equals(POS_INFINITY_STRING)) {
            this.sign = (byte) 1;
            this.nans = (byte) 1;
            return;
        }
        char c6 = 65535;
        if (s.equals(NEG_INFINITY_STRING)) {
            this.sign = (byte) -1;
            this.nans = (byte) 1;
            return;
        }
        char c7 = 3;
        if (s.equals(NAN_STRING)) {
            this.sign = (byte) 1;
            this.nans = (byte) 3;
            return;
        }
        int p = s.indexOf("e");
        p = p == -1 ? s.indexOf("E") : p;
        int sciexp = 0;
        char c8 = '0';
        if (p != -1) {
            String fpdecimal = s.substring(0, p);
            c = 1;
            String fpexp2 = s.substring(p + 1);
            boolean negative = false;
            c2 = 0;
            int i4 = 0;
            while (true) {
                c3 = c7;
                if (i4 >= fpexp2.length()) {
                    break;
                }
                int i5 = i3;
                if (fpexp2.charAt(i4) == '-') {
                    negative = true;
                } else if (fpexp2.charAt(i4) >= '0' && fpexp2.charAt(i4) <= '9') {
                    sciexp = ((sciexp * 10) + fpexp2.charAt(i4)) - 48;
                }
                i4++;
                c7 = c3;
                i3 = i5;
            }
            i = i3;
            sciexp = negative ? -sciexp : sciexp;
            fpexp = fpdecimal;
        } else {
            c = 1;
            c2 = 0;
            i = 4;
            c3 = 3;
            fpexp = s;
        }
        if (fpexp.indexOf(ProcessIdUtil.DEFAULT_PROCESSID) != -1) {
            this.sign = (byte) -1;
        }
        int p2 = 0;
        int decimalPos = 0;
        while (true) {
            char c9 = c6;
            c4 = '.';
            if (fpexp.charAt(p2) >= '1' && fpexp.charAt(p2) <= '9') {
                break;
            }
            if (decimalFound && fpexp.charAt(p2) == '0') {
                decimalPos--;
            }
            decimalFound = fpexp.charAt(p2) == '.' ? true : decimalFound;
            p2++;
            if (p2 == fpexp.length()) {
                break;
            } else {
                c6 = c9;
            }
        }
        int q = 4;
        striped[c2] = '0';
        striped[c] = '0';
        striped[2] = '0';
        striped[c3] = '0';
        int significantDigits = 0;
        while (p2 != fpexp.length() && q != (this.mant.length * 4) + 4 + 1) {
            char c10 = c8;
            if (fpexp.charAt(p2) == c4) {
                decimalFound = true;
                decimalPos = significantDigits;
                p2++;
                c8 = c10;
            } else {
                c8 = c10;
                if (fpexp.charAt(p2) >= c8) {
                    c5 = '9';
                    if (fpexp.charAt(p2) <= '9') {
                        striped[q] = fpexp.charAt(p2);
                        q++;
                        p2++;
                        significantDigits++;
                        c4 = '.';
                    }
                } else {
                    c5 = '9';
                }
                p2++;
                c4 = '.';
            }
        }
        if (decimalFound && q != (i2 = i)) {
            while (true) {
                q--;
                if (q == i2 || striped[q] != c8) {
                    break;
                }
                significantDigits--;
                i2 = 4;
            }
        }
        if (decimalFound && significantDigits == 0) {
            decimalPos = 0;
        }
        decimalPos = decimalFound ? decimalPos : q - 4;
        int p3 = (significantDigits - 1) + 4;
        while (p3 > 4 && striped[p3] == c8) {
            p3--;
        }
        int i6 = 4;
        int i7 = ((400 - decimalPos) - (sciexp % 4)) % 4;
        int q2 = 4 - i7;
        int decimalPos2 = decimalPos + i7;
        while (true) {
            char c11 = c8;
            if (p3 - q2 >= this.mant.length * 4) {
                break;
            }
            int i8 = 0;
            for (int i9 = i6; i8 < i9; i9 = 4) {
                p3++;
                striped[p3] = c11;
                i8++;
            }
            c8 = c11;
            i6 = 4;
        }
        for (int i10 = this.mant.length - 1; i10 >= 0; i10--) {
            this.mant[i10] = ((striped[q2] - '0') * 1000) + ((striped[q2 + 1] - '0') * 100) + ((striped[q2 + 2] - '0') * 10) + (striped[q2 + 3] - '0');
            q2 += 4;
        }
        this.exp = (decimalPos2 + sciexp) / 4;
        if (q2 < striped.length) {
            round((striped[q2] - '0') * 1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Dfp(DfpField field, byte sign, byte nans) {
        this.field = field;
        this.mant = new int[field.getRadixDigits()];
        this.sign = sign;
        this.exp = 0;
        this.nans = nans;
    }

    public Dfp newInstance() {
        return new Dfp(getField());
    }

    public Dfp newInstance(byte x) {
        return new Dfp(getField(), x);
    }

    public Dfp newInstance(int x) {
        return new Dfp(getField(), x);
    }

    public Dfp newInstance(long x) {
        return new Dfp(getField(), x);
    }

    public Dfp newInstance(double x) {
        return new Dfp(getField(), x);
    }

    public Dfp newInstance(Dfp d) {
        if (this.field.getRadixDigits() != d.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = newInstance(getZero());
            result.nans = (byte) 3;
            return dotrap(1, NEW_INSTANCE_TRAP, d, result);
        }
        return new Dfp(d);
    }

    public Dfp newInstance(String s) {
        return new Dfp(this.field, s);
    }

    public Dfp newInstance(byte sig, byte code) {
        return this.field.newDfp(sig, code);
    }

    @Override // org.apache.commons.math3.FieldElement
    public DfpField getField() {
        return this.field;
    }

    public int getRadixDigits() {
        return this.field.getRadixDigits();
    }

    public Dfp getZero() {
        return this.field.getZero();
    }

    public Dfp getOne() {
        return this.field.getOne();
    }

    public Dfp getTwo() {
        return this.field.getTwo();
    }

    protected void shiftLeft() {
        for (int i = this.mant.length - 1; i > 0; i--) {
            this.mant[i] = this.mant[i - 1];
        }
        this.mant[0] = 0;
        this.exp--;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void shiftRight() {
        for (int i = 0; i < this.mant.length - 1; i++) {
            this.mant[i] = this.mant[i + 1];
        }
        this.mant[this.mant.length - 1] = 0;
        this.exp++;
    }

    protected int align(int e) {
        int lostdigit = 0;
        boolean inexact = false;
        int diff = this.exp - e;
        int adiff = diff;
        if (adiff < 0) {
            adiff = -adiff;
        }
        if (diff == 0) {
            return 0;
        }
        if (adiff > this.mant.length + 1) {
            Arrays.fill(this.mant, 0);
            this.exp = e;
            this.field.setIEEEFlagsBits(16);
            dotrap(16, ALIGN_TRAP, this, this);
            return 0;
        }
        for (int i = 0; i < adiff; i++) {
            if (diff < 0) {
                if (lostdigit != 0) {
                    inexact = true;
                }
                lostdigit = this.mant[0];
                shiftRight();
            } else {
                shiftLeft();
            }
        }
        if (inexact) {
            this.field.setIEEEFlagsBits(16);
            dotrap(16, ALIGN_TRAP, this, this);
        }
        return lostdigit;
    }

    public boolean lessThan(Dfp x) {
        if (this.field.getRadixDigits() != x.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = newInstance(getZero());
            result.nans = (byte) 3;
            dotrap(1, LESS_THAN_TRAP, x, result);
            return false;
        }
        if (!isNaN() && !x.isNaN()) {
            return compare(this, x) < 0;
        }
        this.field.setIEEEFlagsBits(1);
        dotrap(1, LESS_THAN_TRAP, x, newInstance(getZero()));
        return false;
    }

    public boolean greaterThan(Dfp x) {
        if (this.field.getRadixDigits() != x.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = newInstance(getZero());
            result.nans = (byte) 3;
            dotrap(1, GREATER_THAN_TRAP, x, result);
            return false;
        }
        if (!isNaN() && !x.isNaN()) {
            return compare(this, x) > 0;
        }
        this.field.setIEEEFlagsBits(1);
        dotrap(1, GREATER_THAN_TRAP, x, newInstance(getZero()));
        return false;
    }

    public boolean negativeOrNull() {
        if (!isNaN()) {
            return this.sign < 0 || (this.mant[this.mant.length - 1] == 0 && !isInfinite());
        }
        this.field.setIEEEFlagsBits(1);
        dotrap(1, LESS_THAN_TRAP, this, newInstance(getZero()));
        return false;
    }

    public boolean strictlyNegative() {
        if (isNaN()) {
            this.field.setIEEEFlagsBits(1);
            dotrap(1, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        }
        if (this.sign < 0) {
            return this.mant[this.mant.length - 1] != 0 || isInfinite();
        }
        return false;
    }

    public boolean positiveOrNull() {
        if (!isNaN()) {
            return this.sign > 0 || (this.mant[this.mant.length - 1] == 0 && !isInfinite());
        }
        this.field.setIEEEFlagsBits(1);
        dotrap(1, LESS_THAN_TRAP, this, newInstance(getZero()));
        return false;
    }

    public boolean strictlyPositive() {
        if (isNaN()) {
            this.field.setIEEEFlagsBits(1);
            dotrap(1, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        }
        if (this.sign > 0) {
            return this.mant[this.mant.length - 1] != 0 || isInfinite();
        }
        return false;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp abs() {
        Dfp result = newInstance(this);
        result.sign = (byte) 1;
        return result;
    }

    public boolean isInfinite() {
        return this.nans == 1;
    }

    public boolean isNaN() {
        return this.nans == 3 || this.nans == 2;
    }

    public boolean isZero() {
        if (!isNaN()) {
            return this.mant[this.mant.length - 1] == 0 && !isInfinite();
        }
        this.field.setIEEEFlagsBits(1);
        dotrap(1, LESS_THAN_TRAP, this, newInstance(getZero()));
        return false;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Dfp)) {
            return false;
        }
        Dfp x = (Dfp) other;
        return !isNaN() && !x.isNaN() && this.field.getRadixDigits() == x.field.getRadixDigits() && compare(this, x) == 0;
    }

    public int hashCode() {
        return (isZero() ? 0 : this.sign << 8) + 17 + (this.nans << UnionPtg.sid) + this.exp + Arrays.hashCode(this.mant);
    }

    public boolean unequal(Dfp x) {
        if (isNaN() || x.isNaN() || this.field.getRadixDigits() != x.field.getRadixDigits()) {
            return false;
        }
        return greaterThan(x) || lessThan(x);
    }

    private static int compare(Dfp a, Dfp b) {
        if (a.mant[a.mant.length - 1] == 0 && b.mant[b.mant.length - 1] == 0 && a.nans == 0 && b.nans == 0) {
            return 0;
        }
        if (a.sign != b.sign) {
            return a.sign == -1 ? -1 : 1;
        }
        if (a.nans == 1 && b.nans == 0) {
            return a.sign;
        }
        if (a.nans == 0 && b.nans == 1) {
            return -b.sign;
        }
        if (a.nans == 1 && b.nans == 1) {
            return 0;
        }
        if (b.mant[b.mant.length - 1] != 0 && a.mant[b.mant.length - 1] != 0) {
            if (a.exp < b.exp) {
                return -a.sign;
            }
            if (a.exp > b.exp) {
                return a.sign;
            }
        }
        for (int i = a.mant.length - 1; i >= 0; i--) {
            if (a.mant[i] > b.mant[i]) {
                return a.sign;
            }
            if (a.mant[i] < b.mant[i]) {
                return -a.sign;
            }
        }
        return 0;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp rint() {
        return trunc(DfpField.RoundingMode.ROUND_HALF_EVEN);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp floor() {
        return trunc(DfpField.RoundingMode.ROUND_FLOOR);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp ceil() {
        return trunc(DfpField.RoundingMode.ROUND_CEIL);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp remainder(Dfp d) {
        Dfp result = subtract(divide(d).rint().multiply(d));
        if (result.mant[this.mant.length - 1] == 0) {
            result.sign = this.sign;
        }
        return result;
    }

    protected Dfp trunc(DfpField.RoundingMode rmode) {
        boolean changed = false;
        if (isNaN()) {
            return newInstance(this);
        }
        if (this.nans == 1) {
            return newInstance(this);
        }
        if (this.mant[this.mant.length - 1] == 0) {
            return newInstance(this);
        }
        if (this.exp < 0) {
            this.field.setIEEEFlagsBits(16);
            return dotrap(16, TRUNC_TRAP, this, newInstance(getZero()));
        }
        if (this.exp >= this.mant.length) {
            return newInstance(this);
        }
        Dfp result = newInstance(this);
        for (int i = 0; i < this.mant.length - result.exp; i++) {
            changed |= result.mant[i] != 0;
            result.mant[i] = 0;
        }
        if (changed) {
            switch (rmode) {
                case ROUND_FLOOR:
                    if (result.sign == -1) {
                        result = result.add(newInstance(-1));
                        break;
                    }
                    break;
                case ROUND_CEIL:
                    if (result.sign == 1) {
                        result = result.add(getOne());
                        break;
                    }
                    break;
                default:
                    Dfp half = newInstance("0.5");
                    Dfp a = subtract(result);
                    a.sign = (byte) 1;
                    if (a.greaterThan(half)) {
                        a = newInstance(getOne());
                        a.sign = this.sign;
                        result = result.add(a);
                    }
                    if (a.equals(half) && result.exp > 0 && (1 & result.mant[this.mant.length - result.exp]) != 0) {
                        Dfp a2 = newInstance(getOne());
                        a2.sign = this.sign;
                        result = result.add(a2);
                        break;
                    }
                    break;
            }
            this.field.setIEEEFlagsBits(16);
            return dotrap(16, TRUNC_TRAP, this, result);
        }
        return result;
    }

    public int intValue() {
        int result = 0;
        Dfp rounded = rint();
        if (rounded.greaterThan(newInstance(Integer.MAX_VALUE))) {
            return Integer.MAX_VALUE;
        }
        if (rounded.lessThan(newInstance(Integer.MIN_VALUE))) {
            return Integer.MIN_VALUE;
        }
        int i = this.mant.length;
        while (true) {
            i--;
            if (i < this.mant.length - rounded.exp) {
                break;
            }
            result = (result * 10000) + rounded.mant[i];
        }
        int i2 = rounded.sign;
        if (i2 == -1) {
            return -result;
        }
        return result;
    }

    public int log10K() {
        return this.exp - 1;
    }

    public Dfp power10K(int e) {
        Dfp d = newInstance(getOne());
        d.exp = e + 1;
        return d;
    }

    public int intLog10() {
        if (this.mant[this.mant.length - 1] > 1000) {
            return (this.exp * 4) - 1;
        }
        if (this.mant[this.mant.length - 1] > 100) {
            return (this.exp * 4) - 2;
        }
        if (this.mant[this.mant.length - 1] > 10) {
            return (this.exp * 4) - 3;
        }
        return (this.exp * 4) - 4;
    }

    public Dfp power10(int e) {
        Dfp d = newInstance(getOne());
        if (e >= 0) {
            d.exp = (e / 4) + 1;
        } else {
            d.exp = (e + 1) / 4;
        }
        switch (((e % 4) + 4) % 4) {
            case 0:
                return d;
            case 1:
                return d.multiply(10);
            case 2:
                return d.multiply(100);
            default:
                return d.multiply(1000);
        }
    }

    protected int complement(int extra) {
        int extra2 = 10000 - extra;
        for (int i = 0; i < this.mant.length; i++) {
            this.mant[i] = (10000 - this.mant[i]) - 1;
        }
        int rh = extra2 / 10000;
        int extra3 = extra2 - (rh * 10000);
        for (int i2 = 0; i2 < this.mant.length; i2++) {
            int r = this.mant[i2] + rh;
            rh = r / 10000;
            this.mant[i2] = r - (rh * 10000);
        }
        return extra3;
    }

    @Override // org.apache.commons.math3.FieldElement
    public Dfp add(Dfp x) {
        byte b = 1;
        if (this.field.getRadixDigits() != x.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = newInstance(getZero());
            result.nans = (byte) 3;
            return dotrap(1, ADD_TRAP, x, result);
        }
        if (this.nans != 0 || x.nans != 0) {
            if (isNaN()) {
                return this;
            }
            if (x.isNaN()) {
                return x;
            }
            if (this.nans == 1 && x.nans == 0) {
                return this;
            }
            if (x.nans == 1 && this.nans == 0) {
                return x;
            }
            if (x.nans == 1 && this.nans == 1 && this.sign == x.sign) {
                return x;
            }
            if (x.nans == 1 && this.nans == 1 && this.sign != x.sign) {
                this.field.setIEEEFlagsBits(1);
                Dfp result2 = newInstance(getZero());
                result2.nans = (byte) 3;
                return dotrap(1, ADD_TRAP, x, result2);
            }
        }
        Dfp a = newInstance(this);
        Dfp b2 = newInstance(x);
        Dfp result3 = newInstance(getZero());
        byte asign = a.sign;
        byte bsign = b2.sign;
        a.sign = (byte) 1;
        b2.sign = (byte) 1;
        byte rsign = bsign;
        if (compare(a, b2) > 0) {
            rsign = asign;
        }
        if (b2.mant[this.mant.length - 1] == 0) {
            b2.exp = a.exp;
        }
        if (a.mant[this.mant.length - 1] == 0) {
            a.exp = b2.exp;
        }
        int aextradigit = 0;
        int bextradigit = 0;
        if (a.exp < b2.exp) {
            aextradigit = a.align(b2.exp);
        } else {
            bextradigit = b2.align(a.exp);
        }
        if (asign != bsign) {
            if (asign == rsign) {
                bextradigit = b2.complement(bextradigit);
            } else {
                aextradigit = a.complement(aextradigit);
            }
        }
        int rh = 0;
        int i = 0;
        while (i < this.mant.length) {
            int r = a.mant[i] + b2.mant[i] + rh;
            rh = r / 10000;
            result3.mant[i] = r - (rh * 10000);
            i++;
            b = b;
        }
        byte b3 = b;
        result3.exp = a.exp;
        result3.sign = rsign;
        if (rh != 0 && asign == bsign) {
            int lostdigit = result3.mant[0];
            result3.shiftRight();
            result3.mant[this.mant.length - 1] = rh;
            int excp = result3.round(lostdigit);
            if (excp != 0) {
                result3 = dotrap(excp, ADD_TRAP, x, result3);
            }
        }
        for (int i2 = 0; i2 < this.mant.length && result3.mant[this.mant.length - 1] == 0; i2++) {
            result3.shiftLeft();
            if (i2 == 0) {
                result3.mant[0] = aextradigit + bextradigit;
                aextradigit = 0;
                bextradigit = 0;
            }
        }
        if (result3.mant[this.mant.length - 1] == 0) {
            result3.exp = 0;
            if (asign != bsign) {
                result3.sign = b3;
            }
        }
        int excp2 = result3.round(aextradigit + bextradigit);
        if (excp2 != 0) {
            return dotrap(excp2, ADD_TRAP, x, result3);
        }
        return result3;
    }

    @Override // org.apache.commons.math3.FieldElement
    public Dfp negate() {
        Dfp result = newInstance(this);
        result.sign = (byte) (-result.sign);
        return result;
    }

    @Override // org.apache.commons.math3.FieldElement
    public Dfp subtract(Dfp x) {
        return add(x.negate());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int round(int n) {
        boolean inc;
        switch (this.field.getRoundingMode()) {
            case ROUND_CEIL:
                if (this.sign != 1 || n == 0) {
                    inc = false;
                    break;
                } else {
                    inc = true;
                    break;
                }
            case ROUND_HALF_EVEN:
                if (n > 5000 || (n == 5000 && (this.mant[0] & 1) == 1)) {
                    inc = true;
                    break;
                } else {
                    inc = false;
                    break;
                }
            case ROUND_DOWN:
                inc = false;
                break;
            case ROUND_UP:
                if (n != 0) {
                    inc = true;
                    break;
                } else {
                    inc = false;
                    break;
                }
            case ROUND_HALF_UP:
                if (n >= 5000) {
                    inc = true;
                    break;
                } else {
                    inc = false;
                    break;
                }
            case ROUND_HALF_DOWN:
                if (n > 5000) {
                    inc = true;
                    break;
                } else {
                    inc = false;
                    break;
                }
            case ROUND_HALF_ODD:
                if (n > 5000 || (n == 5000 && (this.mant[0] & 1) == 0)) {
                    inc = true;
                    break;
                } else {
                    inc = false;
                    break;
                }
            default:
                if (this.sign != -1 || n == 0) {
                    inc = false;
                    break;
                } else {
                    inc = true;
                    break;
                }
        }
        if (inc) {
            int rh = 1;
            for (int i = 0; i < this.mant.length; i++) {
                int r = this.mant[i] + rh;
                rh = r / 10000;
                this.mant[i] = r - (rh * 10000);
            }
            if (rh != 0) {
                shiftRight();
                this.mant[this.mant.length - 1] = rh;
            }
        }
        int rh2 = this.exp;
        if (rh2 < -32767) {
            this.field.setIEEEFlagsBits(8);
            return 8;
        }
        if (this.exp > 32768) {
            this.field.setIEEEFlagsBits(4);
            return 4;
        }
        if (n == 0) {
            return 0;
        }
        this.field.setIEEEFlagsBits(16);
        return 16;
    }

    @Override // org.apache.commons.math3.FieldElement
    public Dfp multiply(Dfp x) {
        int excp;
        if (this.field.getRadixDigits() != x.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = newInstance(getZero());
            result.nans = (byte) 3;
            return dotrap(1, MULTIPLY_TRAP, x, result);
        }
        Dfp result2 = newInstance(getZero());
        if (this.nans != 0 || x.nans != 0) {
            if (isNaN()) {
                return this;
            }
            if (x.isNaN()) {
                return x;
            }
            if (this.nans == 1 && x.nans == 0 && x.mant[this.mant.length - 1] != 0) {
                Dfp result3 = newInstance(this);
                result3.sign = (byte) (this.sign * x.sign);
                return result3;
            }
            if (x.nans == 1 && this.nans == 0 && this.mant[this.mant.length - 1] != 0) {
                Dfp result4 = newInstance(x);
                result4.sign = (byte) (this.sign * x.sign);
                return result4;
            }
            if (x.nans == 1 && this.nans == 1) {
                Dfp result5 = newInstance(this);
                result5.sign = (byte) (this.sign * x.sign);
                return result5;
            }
            if ((x.nans == 1 && this.nans == 0 && this.mant[this.mant.length - 1] == 0) || (this.nans == 1 && x.nans == 0 && x.mant[this.mant.length - 1] == 0)) {
                this.field.setIEEEFlagsBits(1);
                Dfp result6 = newInstance(getZero());
                result6.nans = (byte) 3;
                return dotrap(1, MULTIPLY_TRAP, x, result6);
            }
        }
        int[] product = new int[this.mant.length * 2];
        for (int i = 0; i < this.mant.length; i++) {
            int rh = 0;
            for (int j = 0; j < this.mant.length; j++) {
                int r = (this.mant[i] * x.mant[j]) + product[i + j] + rh;
                rh = r / 10000;
                product[i + j] = r - (rh * 10000);
            }
            product[this.mant.length + i] = rh;
        }
        int md = (this.mant.length * 2) - 1;
        int i2 = (this.mant.length * 2) - 1;
        while (true) {
            if (i2 < 0) {
                break;
            }
            if (product[i2] != 0) {
                md = i2;
                break;
            }
            i2--;
        }
        for (int i3 = 0; i3 < this.mant.length; i3++) {
            result2.mant[(this.mant.length - i3) - 1] = product[md - i3];
        }
        int i4 = this.exp;
        result2.exp = (((i4 + x.exp) + md) - (this.mant.length * 2)) + 1;
        result2.sign = (byte) (this.sign == x.sign ? 1 : -1);
        if (result2.mant[this.mant.length - 1] == 0) {
            result2.exp = 0;
        }
        if (md > this.mant.length - 1) {
            excp = result2.round(product[md - this.mant.length]);
        } else {
            excp = result2.round(0);
        }
        if (excp != 0) {
            return dotrap(excp, MULTIPLY_TRAP, x, result2);
        }
        return result2;
    }

    @Override // org.apache.commons.math3.FieldElement
    public Dfp multiply(int x) {
        if (x >= 0 && x < 10000) {
            return multiplyFast(x);
        }
        return multiply(newInstance(x));
    }

    private Dfp multiplyFast(int x) {
        Dfp result = newInstance(this);
        if (this.nans != 0) {
            if (isNaN()) {
                return this;
            }
            if (this.nans == 1 && x != 0) {
                return newInstance(this);
            }
            if (this.nans == 1 && x == 0) {
                this.field.setIEEEFlagsBits(1);
                Dfp result2 = newInstance(getZero());
                result2.nans = (byte) 3;
                return dotrap(1, MULTIPLY_TRAP, newInstance(getZero()), result2);
            }
        }
        if (x < 0 || x >= 10000) {
            this.field.setIEEEFlagsBits(1);
            Dfp result3 = newInstance(getZero());
            result3.nans = (byte) 3;
            return dotrap(1, MULTIPLY_TRAP, result3, result3);
        }
        int rh = 0;
        for (int i = 0; i < this.mant.length; i++) {
            int r = (this.mant[i] * x) + rh;
            rh = r / 10000;
            result.mant[i] = r - (rh * 10000);
        }
        int lostdigit = 0;
        if (rh != 0) {
            lostdigit = result.mant[0];
            result.shiftRight();
            result.mant[this.mant.length - 1] = rh;
        }
        if (result.mant[this.mant.length - 1] == 0) {
            result.exp = 0;
        }
        int excp = result.round(lostdigit);
        if (excp != 0) {
            return dotrap(excp, MULTIPLY_TRAP, result, result);
        }
        return result;
    }

    @Override // org.apache.commons.math3.FieldElement
    public Dfp divide(Dfp divisor) {
        int i;
        int i2;
        int excp;
        int md;
        boolean trialgood;
        int trial = 0;
        int md2 = 0;
        int i3 = 1;
        if (this.field.getRadixDigits() == divisor.field.getRadixDigits()) {
            Dfp result = newInstance(getZero());
            if (this.nans != 0 || divisor.nans != 0) {
                if (isNaN()) {
                    return this;
                }
                if (divisor.isNaN()) {
                    return divisor;
                }
                if (this.nans == 1 && divisor.nans == 0) {
                    Dfp result2 = newInstance(this);
                    result2.sign = (byte) (this.sign * divisor.sign);
                    return result2;
                }
                if (divisor.nans == 1 && this.nans == 0) {
                    Dfp result3 = newInstance(getZero());
                    result3.sign = (byte) (this.sign * divisor.sign);
                    return result3;
                }
                if (divisor.nans == 1 && this.nans == 1) {
                    this.field.setIEEEFlagsBits(1);
                    Dfp result4 = newInstance(getZero());
                    result4.nans = (byte) 3;
                    return dotrap(1, DIVIDE_TRAP, divisor, result4);
                }
            }
            int i4 = 2;
            if (divisor.mant[this.mant.length - 1] == 0) {
                this.field.setIEEEFlagsBits(2);
                Dfp result5 = newInstance(getZero());
                result5.sign = (byte) (this.sign * divisor.sign);
                result5.nans = (byte) 1;
                return dotrap(2, DIVIDE_TRAP, divisor, result5);
            }
            int[] dividend = new int[this.mant.length + 1];
            int[] quotient = new int[this.mant.length + 2];
            int[] remainder = new int[this.mant.length + 1];
            int i5 = 0;
            dividend[this.mant.length] = 0;
            quotient[this.mant.length] = 0;
            quotient[this.mant.length + 1] = 0;
            remainder[this.mant.length] = 0;
            for (int i6 = 0; i6 < this.mant.length; i6++) {
                dividend[i6] = this.mant[i6];
                quotient[i6] = 0;
                remainder[i6] = 0;
            }
            int nsqd = 0;
            int qd = this.mant.length + 1;
            while (true) {
                if (qd < 0) {
                    i = i3;
                    i2 = i5;
                    break;
                }
                int divMsb = (dividend[this.mant.length] * 10000) + dividend[this.mant.length - i3];
                i = i3;
                int min = divMsb / (divisor.mant[this.mant.length - 1] + 1);
                i2 = i5;
                int i7 = i4;
                int max = (divMsb + 1) / divisor.mant[this.mant.length - 1];
                boolean trialgood2 = false;
                while (!trialgood2) {
                    int trial2 = (min + max) / 2;
                    int rh = 0;
                    int trial3 = 0;
                    while (true) {
                        md = md2;
                        if (trial3 >= this.mant.length + 1) {
                            break;
                        }
                        int dm = trial3 < this.mant.length ? divisor.mant[trial3] : i2;
                        int i8 = trial3;
                        int i9 = (dm * trial2) + rh;
                        rh = i9 / 10000;
                        remainder[i8] = i9 - (rh * 10000);
                        trial3 = i8 + 1;
                        md2 = md;
                    }
                    int rh2 = 1;
                    int i10 = 0;
                    while (i10 < this.mant.length + 1) {
                        int r = (9999 - remainder[i10]) + dividend[i10] + rh2;
                        rh2 = r / 10000;
                        int i11 = i10;
                        int i12 = rh2 * 10000;
                        remainder[i11] = r - i12;
                        i10 = i11 + 1;
                    }
                    if (rh2 != 0) {
                        int minadj = ((remainder[this.mant.length] * 10000) + remainder[this.mant.length - 1]) / (divisor.mant[this.mant.length - 1] + 1);
                        int i13 = i7;
                        if (minadj >= i13) {
                            min = trial2 + minadj;
                            i7 = i13;
                            trial = trial2;
                            md2 = md;
                        } else {
                            boolean trialgood3 = false;
                            int i14 = this.mant.length - 1;
                            while (true) {
                                if (i14 < 0) {
                                    break;
                                }
                                boolean trialgood4 = trialgood3;
                                if (divisor.mant[i14] <= remainder[i14]) {
                                    trialgood = trialgood4;
                                } else {
                                    trialgood = true;
                                }
                                boolean trialgood5 = trialgood;
                                if (divisor.mant[i14] < remainder[i14]) {
                                    trialgood3 = trialgood5;
                                    break;
                                }
                                i14--;
                                trialgood3 = trialgood5;
                            }
                            if (remainder[this.mant.length] == 0) {
                                trialgood2 = trialgood3;
                            } else {
                                trialgood2 = false;
                            }
                            if (!trialgood2) {
                                min = trial2 + 1;
                            }
                            trial = trial2;
                            md2 = md;
                            i7 = 2;
                        }
                    } else {
                        max = trial2 - 1;
                        trial = trial2;
                        md2 = md;
                    }
                }
                int md3 = md2;
                quotient[qd] = trial;
                if (trial != 0 || nsqd != 0) {
                    nsqd++;
                }
                if ((this.field.getRoundingMode() == DfpField.RoundingMode.ROUND_DOWN && nsqd == this.mant.length) || nsqd > this.mant.length) {
                    break;
                }
                dividend[i2] = i2;
                for (int i15 = 0; i15 < this.mant.length; i15++) {
                    dividend[i15 + 1] = remainder[i15];
                }
                qd--;
                i3 = i;
                i5 = i2;
                md2 = md3;
                i4 = 2;
            }
            int md4 = this.mant.length;
            int i16 = this.mant.length + 1;
            while (true) {
                if (i16 < 0) {
                    break;
                }
                if (quotient[i16] != 0) {
                    md4 = i16;
                    break;
                }
                i16--;
            }
            for (int i17 = 0; i17 < this.mant.length; i17++) {
                result.mant[(this.mant.length - i17) - 1] = quotient[md4 - i17];
            }
            int i18 = this.exp;
            result.exp = ((i18 - divisor.exp) + md4) - this.mant.length;
            result.sign = (byte) (this.sign == divisor.sign ? i : -1);
            if (result.mant[this.mant.length - 1] == 0) {
                result.exp = i2;
            }
            if (md4 > this.mant.length - 1) {
                excp = result.round(quotient[md4 - this.mant.length]);
            } else {
                excp = result.round(0);
            }
            if (excp != 0) {
                return dotrap(excp, DIVIDE_TRAP, divisor, result);
            }
            return result;
        }
        this.field.setIEEEFlagsBits(1);
        Dfp result6 = newInstance(getZero());
        result6.nans = (byte) 3;
        return dotrap(1, DIVIDE_TRAP, divisor, result6);
    }

    public Dfp divide(int divisor) {
        if (this.nans != 0) {
            if (isNaN()) {
                return this;
            }
            if (this.nans == 1) {
                return newInstance(this);
            }
        }
        if (divisor == 0) {
            this.field.setIEEEFlagsBits(2);
            Dfp result = newInstance(getZero());
            result.sign = this.sign;
            result.nans = (byte) 1;
            return dotrap(2, DIVIDE_TRAP, getZero(), result);
        }
        if (divisor < 0 || divisor >= 10000) {
            this.field.setIEEEFlagsBits(1);
            Dfp result2 = newInstance(getZero());
            result2.nans = (byte) 3;
            return dotrap(1, DIVIDE_TRAP, result2, result2);
        }
        Dfp result3 = newInstance(this);
        int rl = 0;
        for (int i = this.mant.length - 1; i >= 0; i--) {
            int r = (rl * 10000) + result3.mant[i];
            int rh = r / divisor;
            rl = r - (rh * divisor);
            result3.mant[i] = rh;
        }
        if (result3.mant[this.mant.length - 1] == 0) {
            result3.shiftLeft();
            int r2 = rl * 10000;
            int rh2 = r2 / divisor;
            rl = r2 - (rh2 * divisor);
            result3.mant[0] = rh2;
        }
        int excp = result3.round((rl * 10000) / divisor);
        if (excp != 0) {
            return dotrap(excp, DIVIDE_TRAP, result3, result3);
        }
        return result3;
    }

    @Override // org.apache.commons.math3.RealFieldElement, org.apache.commons.math3.FieldElement
    public Dfp reciprocal() {
        return this.field.getOne().divide(this);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp sqrt() {
        if (this.nans == 0 && this.mant[this.mant.length - 1] == 0) {
            return newInstance(this);
        }
        if (this.nans != 0) {
            if (this.nans == 1 && this.sign == 1) {
                return newInstance(this);
            }
            if (this.nans == 3) {
                return newInstance(this);
            }
            if (this.nans == 2) {
                this.field.setIEEEFlagsBits(1);
                return dotrap(1, SQRT_TRAP, null, newInstance(this));
            }
        }
        if (this.sign == -1) {
            this.field.setIEEEFlagsBits(1);
            Dfp result = newInstance(this);
            result.nans = (byte) 3;
            return dotrap(1, SQRT_TRAP, null, result);
        }
        Dfp x = newInstance(this);
        if (x.exp < -1 || x.exp > 1) {
            x.exp = this.exp / 2;
        }
        switch (x.mant[this.mant.length - 1] / 2000) {
            case 0:
                x.mant[this.mant.length - 1] = (x.mant[this.mant.length - 1] / 2) + 1;
                break;
            case 1:
            default:
                x.mant[this.mant.length - 1] = 3000;
                break;
            case 2:
                x.mant[this.mant.length - 1] = 1500;
                break;
            case 3:
                x.mant[this.mant.length - 1] = 2200;
                break;
        }
        newInstance(x);
        Dfp px = getZero();
        getZero();
        while (x.unequal(px)) {
            Dfp dx = newInstance(x);
            dx.sign = (byte) -1;
            Dfp dx2 = dx.add(divide(x)).divide(2);
            Dfp ppx = px;
            px = x;
            x = x.add(dx2);
            if (!x.equals(ppx) && dx2.mant[this.mant.length - 1] != 0) {
            }
            return x;
        }
        return x;
    }

    public String toString() {
        if (this.nans != 0) {
            if (this.nans == 1) {
                return this.sign < 0 ? NEG_INFINITY_STRING : POS_INFINITY_STRING;
            }
            return NAN_STRING;
        }
        if (this.exp > this.mant.length || this.exp < -1) {
            return dfp2sci();
        }
        return dfp2string();
    }

    protected String dfp2sci() {
        char[] rawdigits = new char[this.mant.length * 4];
        char[] outputbuffer = new char[(this.mant.length * 4) + 20];
        int p = 0;
        int i = this.mant.length;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            int p2 = p + 1;
            rawdigits[p] = (char) ((this.mant[i] / 1000) + 48);
            int p3 = p2 + 1;
            rawdigits[p2] = (char) (((this.mant[i] / 100) % 10) + 48);
            int p4 = p3 + 1;
            rawdigits[p3] = (char) (((this.mant[i] / 10) % 10) + 48);
            p = p4 + 1;
            rawdigits[p4] = (char) ((this.mant[i] % 10) + 48);
        }
        int p5 = 0;
        while (p5 < rawdigits.length && rawdigits[p5] == '0') {
            p5++;
        }
        int shf = p5;
        int q = 0;
        if (this.sign == -1) {
            int q2 = 0 + 1;
            outputbuffer[0] = '-';
            q = q2;
        }
        int q3 = rawdigits.length;
        if (p5 != q3) {
            int q4 = q + 1;
            outputbuffer[q] = rawdigits[p5];
            int q5 = q4 + 1;
            outputbuffer[q4] = '.';
            for (int p6 = p5 + 1; p6 < rawdigits.length; p6++) {
                outputbuffer[q5] = rawdigits[p6];
                q5++;
            }
            int q6 = q5 + 1;
            outputbuffer[q5] = 'e';
            int e = ((this.exp * 4) - shf) - 1;
            int ae = e;
            if (e < 0) {
                ae = -e;
            }
            int p7 = InstantKt.NANOS_PER_SECOND;
            while (p7 > ae) {
                p7 /= 10;
            }
            if (e < 0) {
                outputbuffer[q6] = '-';
                q6++;
            }
            while (p7 > 0) {
                outputbuffer[q6] = (char) ((ae / p7) + 48);
                ae %= p7;
                p7 /= 10;
                q6++;
            }
            return new String(outputbuffer, 0, q6);
        }
        int ae2 = q + 1;
        outputbuffer[q] = '0';
        int q7 = ae2 + 1;
        outputbuffer[ae2] = '.';
        int q8 = q7 + 1;
        outputbuffer[q7] = '0';
        int q9 = q8 + 1;
        outputbuffer[q8] = 'e';
        int i2 = q9 + 1;
        outputbuffer[q9] = '0';
        return new String(outputbuffer, 0, 5);
    }

    protected String dfp2string() {
        char[] buffer = new char[(this.mant.length * 4) + 20];
        int p = 1;
        int e = this.exp;
        boolean pointInserted = false;
        buffer[0] = Chars.SPACE;
        if (e <= 0) {
            int p2 = 1 + 1;
            buffer[1] = '0';
            p = p2 + 1;
            buffer[p2] = '.';
            pointInserted = true;
        }
        while (e < 0) {
            int p3 = p + 1;
            buffer[p] = '0';
            int p4 = p3 + 1;
            buffer[p3] = '0';
            int p5 = p4 + 1;
            buffer[p4] = '0';
            p = p5 + 1;
            buffer[p5] = '0';
            e++;
        }
        for (int i = this.mant.length - 1; i >= 0; i--) {
            int p6 = p + 1;
            buffer[p] = (char) ((this.mant[i] / 1000) + 48);
            int p7 = p6 + 1;
            buffer[p6] = (char) (((this.mant[i] / 100) % 10) + 48);
            int p8 = p7 + 1;
            buffer[p7] = (char) (((this.mant[i] / 10) % 10) + 48);
            p = p8 + 1;
            buffer[p8] = (char) ((this.mant[i] % 10) + 48);
            e--;
            if (e == 0) {
                buffer[p] = '.';
                pointInserted = true;
                p++;
            }
        }
        while (e > 0) {
            int p9 = p + 1;
            buffer[p] = '0';
            int p10 = p9 + 1;
            buffer[p9] = '0';
            int p11 = p10 + 1;
            buffer[p10] = '0';
            p = p11 + 1;
            buffer[p11] = '0';
            e--;
        }
        if (!pointInserted) {
            buffer[p] = '.';
            p++;
        }
        int q = 1;
        while (buffer[q] == '0') {
            q++;
        }
        if (buffer[q] == '.') {
            q--;
        }
        while (buffer[p - 1] == '0') {
            p--;
        }
        if (this.sign < 0) {
            q--;
            buffer[q] = '-';
        }
        return new String(buffer, q, p - q);
    }

    public Dfp dotrap(int type, String what, Dfp oper, Dfp result) {
        Dfp def;
        Dfp def2;
        Dfp def3 = result;
        switch (type) {
            case 1:
                Dfp def4 = newInstance(getZero());
                def4.sign = result.sign;
                def4.nans = (byte) 3;
                def = def4;
                break;
            case 2:
                if (this.nans == 0 && this.mant[this.mant.length - 1] != 0) {
                    def3 = newInstance(getZero());
                    def3.sign = (byte) (this.sign * oper.sign);
                    def3.nans = (byte) 1;
                }
                if (this.nans == 0 && this.mant[this.mant.length - 1] == 0) {
                    def3 = newInstance(getZero());
                    def3.nans = (byte) 3;
                }
                if (this.nans == 1 || this.nans == 3) {
                    def3 = newInstance(getZero());
                    def3.nans = (byte) 3;
                }
                if (this.nans != 1 && this.nans != 2) {
                    def = def3;
                    break;
                } else {
                    Dfp def5 = newInstance(getZero());
                    def5.nans = (byte) 3;
                    def = def5;
                    break;
                }
                break;
            case 4:
                result.exp -= 32760;
                Dfp def6 = newInstance(getZero());
                def6.sign = result.sign;
                def6.nans = (byte) 1;
                def = def6;
                break;
            case 8:
                if (result.exp + this.mant.length < -32767) {
                    def2 = newInstance(getZero());
                    def2.sign = result.sign;
                } else {
                    def2 = newInstance(result);
                }
                result.exp += ERR_SCALE;
                def = def2;
                break;
            default:
                def = result;
                break;
        }
        return trap(type, what, oper, def, result);
    }

    protected Dfp trap(int type, String what, Dfp oper, Dfp def, Dfp result) {
        return def;
    }

    public int classify() {
        return this.nans;
    }

    public static Dfp copysign(Dfp x, Dfp y) {
        Dfp result = x.newInstance(x);
        result.sign = y.sign;
        return result;
    }

    public Dfp nextAfter(Dfp x) {
        Dfp result;
        if (this.field.getRadixDigits() != x.field.getRadixDigits()) {
            this.field.setIEEEFlagsBits(1);
            Dfp result2 = newInstance(getZero());
            result2.nans = (byte) 3;
            return dotrap(1, NEXT_AFTER_TRAP, x, result2);
        }
        boolean up = false;
        if (lessThan(x)) {
            up = true;
        }
        if (compare(this, x) == 0) {
            return newInstance(x);
        }
        if (lessThan(getZero())) {
            up = !up;
        }
        if (up) {
            Dfp inc = newInstance(getOne());
            inc.exp = (this.exp - this.mant.length) + 1;
            inc.sign = this.sign;
            if (equals(getZero())) {
                inc.exp = (-32767) - this.mant.length;
            }
            result = add(inc);
        } else {
            Dfp inc2 = newInstance(getOne());
            inc2.exp = this.exp;
            inc2.sign = this.sign;
            if (equals(inc2)) {
                inc2.exp = this.exp - this.mant.length;
            } else {
                inc2.exp = (this.exp - this.mant.length) + 1;
            }
            if (equals(getZero())) {
                inc2.exp = (-32767) - this.mant.length;
            }
            result = subtract(inc2);
        }
        if (result.classify() == 1 && classify() != 1) {
            this.field.setIEEEFlagsBits(16);
            result = dotrap(16, NEXT_AFTER_TRAP, x, result);
        }
        if (result.equals(getZero()) && !equals(getZero())) {
            this.field.setIEEEFlagsBits(16);
            return dotrap(16, NEXT_AFTER_TRAP, x, result);
        }
        return result;
    }

    public double toDouble() {
        if (isInfinite()) {
            return lessThan(getZero()) ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        }
        if (isNaN()) {
            return Double.NaN;
        }
        Dfp y = this;
        boolean negate = false;
        int cmp0 = compare(this, getZero());
        if (cmp0 == 0) {
            return this.sign < 0 ? -0.0d : 0.0d;
        }
        if (cmp0 < 0) {
            y = negate();
            negate = true;
        }
        int exponent = (int) (y.intLog10() * 3.32d);
        if (exponent < 0) {
            exponent--;
        }
        Dfp tempDfp = DfpMath.pow(getTwo(), exponent);
        while (true) {
            if (!tempDfp.lessThan(y) && !tempDfp.equals(y)) {
                break;
            }
            tempDfp = tempDfp.multiply(2);
            exponent++;
        }
        int exponent2 = exponent - 1;
        Dfp y2 = y.divide(DfpMath.pow(getTwo(), exponent2));
        if (exponent2 > -1023) {
            y2 = y2.subtract(getOne());
        }
        if (exponent2 < -1074) {
            return 0.0d;
        }
        if (exponent2 > 1023) {
            return negate ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        }
        String str = y2.multiply(newInstance(IEEEDouble.FRAC_ASSUMED_HIGH_BIT)).rint().toString();
        long mantissa = Long.parseLong(str.substring(0, str.length() - 1));
        if (mantissa == IEEEDouble.FRAC_ASSUMED_HIGH_BIT) {
            mantissa = 0;
            exponent2++;
        }
        if (exponent2 <= -1023) {
            exponent2--;
        }
        while (exponent2 < -1023) {
            exponent2++;
            mantissa >>>= 1;
        }
        long bits = ((exponent2 + 1023) << 52) | mantissa;
        double x = Double.longBitsToDouble(bits);
        if (negate) {
            return -x;
        }
        return x;
    }

    public double[] toSplitDouble() {
        double[] split = {Double.longBitsToDouble(Double.doubleToLongBits(toDouble()) & (-1073741824)), subtract(newInstance(split[0])).toDouble()};
        return split;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public double getReal() {
        return toDouble();
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp add(double a) {
        return add(newInstance(a));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp subtract(double a) {
        return subtract(newInstance(a));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp multiply(double a) {
        return multiply(newInstance(a));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp divide(double a) {
        return divide(newInstance(a));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp remainder(double a) {
        return remainder(newInstance(a));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public long round() {
        return FastMath.round(toDouble());
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp signum() {
        if (isNaN() || isZero()) {
            return this;
        }
        return newInstance(this.sign > 0 ? 1 : -1);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp copySign(Dfp s) {
        if ((this.sign >= 0 && s.sign >= 0) || (this.sign < 0 && s.sign < 0)) {
            return this;
        }
        return negate();
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp copySign(double s) {
        long sb = Double.doubleToLongBits(s);
        if ((this.sign >= 0 && sb >= 0) || (this.sign < 0 && sb < 0)) {
            return this;
        }
        return negate();
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp scalb(int n) {
        return multiply(DfpMath.pow(getTwo(), n));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp hypot(Dfp y) {
        return multiply(this).add(y.multiply(y)).sqrt();
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp cbrt() {
        return rootN(3);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp rootN(int n) {
        return this.sign >= 0 ? DfpMath.pow(this, getOne().divide(n)) : DfpMath.pow(negate(), getOne().divide(n)).negate();
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp pow(double p) {
        return DfpMath.pow(this, newInstance(p));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp pow(int n) {
        return DfpMath.pow(this, n);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp pow(Dfp e) {
        return DfpMath.pow(this, e);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp exp() {
        return DfpMath.exp(this);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp expm1() {
        return DfpMath.exp(this).subtract(getOne());
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp log() {
        return DfpMath.log(this);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp log1p() {
        return DfpMath.log(add(getOne()));
    }

    @Deprecated
    public int log10() {
        return intLog10();
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp cos() {
        return DfpMath.cos(this);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp sin() {
        return DfpMath.sin(this);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp tan() {
        return DfpMath.tan(this);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp acos() {
        return DfpMath.acos(this);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp asin() {
        return DfpMath.asin(this);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp atan() {
        return DfpMath.atan(this);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp atan2(Dfp x) throws DimensionMismatchException {
        Dfp r = x.multiply(x).add(multiply(this)).sqrt();
        if (x.sign >= 0) {
            return getTwo().multiply(divide(r.add(x)).atan());
        }
        Dfp tmp = getTwo().multiply(divide(r.subtract(x)).atan());
        Dfp pmPi = newInstance(tmp.sign <= 0 ? -3.141592653589793d : 3.141592653589793d);
        return pmPi.subtract(tmp);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp cosh() {
        return DfpMath.exp(this).add(DfpMath.exp(negate())).divide(2);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp sinh() {
        return DfpMath.exp(this).subtract(DfpMath.exp(negate())).divide(2);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp tanh() {
        Dfp ePlus = DfpMath.exp(this);
        Dfp eMinus = DfpMath.exp(negate());
        return ePlus.subtract(eMinus).divide(ePlus.add(eMinus));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp acosh() {
        return multiply(this).subtract(getOne()).sqrt().add(this).log();
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp asinh() {
        return multiply(this).add(getOne()).sqrt().add(this).log();
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp atanh() {
        return getOne().add(this).divide(getOne().subtract(this)).log().divide(2);
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp linearCombination(Dfp[] a, Dfp[] b) throws DimensionMismatchException {
        if (a.length != b.length) {
            throw new DimensionMismatchException(a.length, b.length);
        }
        Dfp r = getZero();
        for (int i = 0; i < a.length; i++) {
            r = r.add(a[i].multiply(b[i]));
        }
        return r;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp linearCombination(double[] a, Dfp[] b) throws DimensionMismatchException {
        if (a.length != b.length) {
            throw new DimensionMismatchException(a.length, b.length);
        }
        Dfp r = getZero();
        for (int i = 0; i < a.length; i++) {
            r = r.add(b[i].multiply(a[i]));
        }
        return r;
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp linearCombination(Dfp a1, Dfp b1, Dfp a2, Dfp b2) {
        return a1.multiply(b1).add(a2.multiply(b2));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp linearCombination(double a1, Dfp b1, double a2, Dfp b2) {
        return b1.multiply(a1).add(b2.multiply(a2));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp linearCombination(Dfp a1, Dfp b1, Dfp a2, Dfp b2, Dfp a3, Dfp b3) {
        return a1.multiply(b1).add(a2.multiply(b2)).add(a3.multiply(b3));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp linearCombination(double a1, Dfp b1, double a2, Dfp b2, double a3, Dfp b3) {
        return b1.multiply(a1).add(b2.multiply(a2)).add(b3.multiply(a3));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp linearCombination(Dfp a1, Dfp b1, Dfp a2, Dfp b2, Dfp a3, Dfp b3, Dfp a4, Dfp b4) {
        return a1.multiply(b1).add(a2.multiply(b2)).add(a3.multiply(b3)).add(a4.multiply(b4));
    }

    @Override // org.apache.commons.math3.RealFieldElement
    public Dfp linearCombination(double a1, Dfp b1, double a2, Dfp b2, double a3, Dfp b3, double a4, Dfp b4) {
        return b1.multiply(a1).add(b2.multiply(a2)).add(b3.multiply(a3)).add(b4.multiply(a4));
    }
}
