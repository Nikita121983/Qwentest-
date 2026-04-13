package org.apache.xmlbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/* loaded from: classes.dex */
public class GDurationBuilder implements GDurationSpecification, Serializable {
    private static final GDate[] _compDate = {new GDate(1696, 9, 1, 0, 0, 0, null, 0, 0, 0), new GDate(1697, 2, 1, 0, 0, 0, null, 0, 0, 0), new GDate(1903, 3, 1, 0, 0, 0, null, 0, 0, 0), new GDate(1903, 7, 1, 0, 0, 0, null, 0, 0, 0)};
    private static final long serialVersionUID = 1;
    private int _CY;
    private int _D;
    private int _M;
    private BigDecimal _fs;
    private int _h;
    private int _m;
    private int _s;
    private int _sign;

    public GDurationBuilder() {
        this._sign = 1;
        this._fs = GDate._zero;
    }

    public GDurationBuilder(String s) {
        this(new GDuration(s));
    }

    public GDurationBuilder(int sign, int year, int month, int day, int hour, int minute, int second, BigDecimal fraction) {
        if (sign != 1 && sign != -1) {
            throw new IllegalArgumentException();
        }
        this._sign = sign;
        this._CY = year;
        this._M = month;
        this._D = day;
        this._h = hour;
        this._m = minute;
        this._s = second;
        this._fs = fraction == null ? GDate._zero : fraction;
    }

    public GDurationBuilder(GDurationSpecification gDuration) {
        this._sign = gDuration.getSign();
        this._CY = gDuration.getYear();
        this._M = gDuration.getMonth();
        this._D = gDuration.getDay();
        this._h = gDuration.getHour();
        this._m = gDuration.getMinute();
        this._s = gDuration.getSecond();
        this._fs = gDuration.getFraction();
    }

    public Object clone() {
        return new GDurationBuilder(this);
    }

    public GDuration toGDuration() {
        return new GDuration(this);
    }

    public void addGDuration(GDurationSpecification duration) {
        int sign = this._sign * duration.getSign();
        _add(duration, sign);
    }

    public void subtractGDuration(GDurationSpecification duration) {
        int sign = (-this._sign) * duration.getSign();
        _add(duration, sign);
    }

    private void _add(GDurationSpecification duration, int sign) {
        BigDecimal subtract;
        this._CY += duration.getYear() * sign;
        this._M += duration.getMonth() * sign;
        this._D += duration.getDay() * sign;
        this._h += duration.getHour() * sign;
        this._m += duration.getMinute() * sign;
        this._s += duration.getSecond() * sign;
        if (duration.getFraction().signum() == 0) {
            return;
        }
        if (this._fs.signum() == 0 && sign == 1) {
            this._fs = duration.getFraction();
            return;
        }
        if (sign > 0) {
            subtract = this._fs.add(duration.getFraction());
        } else {
            subtract = this._fs.subtract(duration.getFraction());
        }
        this._fs = subtract;
    }

    public final void setSign(int sign) {
        if (sign != 1 && sign != -1) {
            throw new IllegalArgumentException();
        }
        this._sign = sign;
    }

    public void setYear(int year) {
        this._CY = year;
    }

    public void setMonth(int month) {
        this._M = month;
    }

    public void setDay(int day) {
        this._D = day;
    }

    public void setHour(int hour) {
        this._h = hour;
    }

    public void setMinute(int minute) {
        this._m = minute;
    }

    public void setSecond(int second) {
        this._s = second;
    }

    public void setFraction(BigDecimal fraction) {
        this._fs = fraction == null ? GDate._zero : fraction;
    }

    @Override // org.apache.xmlbeans.GDurationSpecification
    public final boolean isImmutable() {
        return true;
    }

    @Override // org.apache.xmlbeans.GDurationSpecification
    public final int getSign() {
        return this._sign;
    }

    @Override // org.apache.xmlbeans.GDurationSpecification
    public final int getYear() {
        return this._CY;
    }

    @Override // org.apache.xmlbeans.GDurationSpecification
    public final int getMonth() {
        return this._M;
    }

    @Override // org.apache.xmlbeans.GDurationSpecification
    public final int getDay() {
        return this._D;
    }

    @Override // org.apache.xmlbeans.GDurationSpecification
    public final int getHour() {
        return this._h;
    }

    @Override // org.apache.xmlbeans.GDurationSpecification
    public final int getMinute() {
        return this._m;
    }

    @Override // org.apache.xmlbeans.GDurationSpecification
    public final int getSecond() {
        return this._s;
    }

    @Override // org.apache.xmlbeans.GDurationSpecification
    public BigDecimal getFraction() {
        return this._fs;
    }

    @Override // org.apache.xmlbeans.GDurationSpecification
    public boolean isValid() {
        return isValidDuration(this);
    }

    public void normalize() {
        _normalizeImpl(true);
    }

    private static long _fQuotient(long a, int b) {
        if ((a < 0) == (b < 0)) {
            return a / b;
        }
        return -(((b - a) - 1) / b);
    }

    private static int _mod(long a, int b, long quotient) {
        return (int) (a - (b * quotient));
    }

    private void _normalizeImpl(boolean adjustSign) {
        if (this._M < 0 || this._M > 11) {
            long temp = this._M;
            long ycarry = _fQuotient(temp, 12);
            this._M = _mod(temp, 12, ycarry);
            this._CY = Math.addExact(this._CY, Math.toIntExact(ycarry));
        }
        long carry = 0;
        if (this._fs != null && (this._fs.signum() < 0 || this._fs.compareTo(GDate._one) >= 0)) {
            BigDecimal bdcarry = this._fs.setScale(0, RoundingMode.FLOOR);
            this._fs = this._fs.subtract(bdcarry);
            carry = bdcarry.intValue();
        }
        if (carry != 0 || this._s < 0 || this._s > 59 || this._m < 0 || this._m > 50 || this._h < 0 || this._h > 23) {
            long temp2 = this._s + carry;
            long carry2 = _fQuotient(temp2, 60);
            this._s = _mod(temp2, 60, carry2);
            long temp3 = this._m + carry2;
            long carry3 = _fQuotient(temp3, 60);
            this._m = _mod(temp3, 60, carry3);
            long temp4 = this._h + carry3;
            long carry4 = _fQuotient(temp4, 24);
            this._h = _mod(temp4, 24, carry4);
            this._D = Math.addExact(this._D, Math.toIntExact(carry4));
        }
        if (this._CY == 0 && this._M == 0 && this._D == 0 && this._h == 0 && this._m == 0 && this._s == 0 && (this._fs == null || this._fs.signum() == 0)) {
            this._sign = 1;
        }
        if (adjustSign) {
            if (this._D < 0 || this._CY < 0) {
                int sign = (this._D > 0 || (this._CY >= 0 && !(this._CY == 0 && this._M == 0))) ? _getTotalSignSlowly() : -this._sign;
                if (sign == 2) {
                    sign = this._CY < 0 ? -this._sign : this._sign;
                }
                if (sign == 0) {
                    sign = 1;
                }
                if (sign != this._sign) {
                    this._sign = sign;
                    this._CY = -this._CY;
                    this._M = -this._M;
                    this._D = -this._D;
                    this._h = -this._h;
                    this._m = -this._m;
                    this._s = -this._s;
                    if (this._fs != null) {
                        this._fs = this._fs.negate();
                    }
                }
                _normalizeImpl(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isValidDuration(GDurationSpecification spec) {
        return (spec.getSign() == 1 || spec.getSign() == -1) && spec.getYear() >= 0 && spec.getMonth() >= 0 && spec.getDay() >= 0 && spec.getHour() >= 0 && spec.getMinute() >= 0 && spec.getSecond() >= 0 && spec.getFraction().signum() >= 0;
    }

    @Override // org.apache.xmlbeans.GDurationSpecification
    public final int compareToGDuration(GDurationSpecification duration) {
        return compareDurations(this, duration);
    }

    public String toString() {
        return formatDuration(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int compareDurations(GDurationSpecification d1, GDurationSpecification d2) {
        if (d1.getFraction().signum() == 0 && d2.getFraction().signum() == 0) {
            int s1 = d1.getSign();
            int s2 = d2.getSign();
            long month1 = s1 * ((d1.getYear() * 12) + d1.getMonth());
            long month2 = s2 * ((d2.getYear() * 12) + d2.getMonth());
            long sec1 = s1 * ((((((d1.getDay() * 24) + d1.getHour()) * 60) + d1.getMinute()) * 60) + d1.getSecond());
            long sec2 = s2 * ((((((d2.getDay() * 24) + d2.getHour()) * 60) + d2.getMinute()) * 60) + d2.getSecond());
            if (month1 == month2) {
                if (sec1 == sec2) {
                    return 0;
                }
                return sec1 < sec2 ? -1 : 1;
            }
            if (month1 < month2 && sec1 - sec2 < 2419200) {
                return -1;
            }
            if (month1 > month2 && sec2 - sec1 < 2419200) {
                return 1;
            }
        }
        GDurationBuilder diff = new GDurationBuilder(d1);
        diff.subtractGDuration(d2);
        return diff._getTotalSignSlowly();
    }

    private int _getTotalSignSlowly() {
        int pos = 0;
        int neg = 0;
        int zer = 0;
        GDateBuilder enddate = new GDateBuilder();
        for (GDate gDate : _compDate) {
            enddate.setGDate(gDate);
            enddate.addGDuration(this);
            switch (enddate.compareToGDate(gDate)) {
                case -1:
                    neg++;
                    break;
                case 0:
                    zer++;
                    break;
                case 1:
                    pos++;
                    break;
            }
        }
        if (pos == _compDate.length) {
            return 1;
        }
        if (neg == _compDate.length) {
            return -1;
        }
        return zer == _compDate.length ? 0 : 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String formatDuration(GDurationSpecification duration) {
        StringBuilder message = new StringBuilder(30);
        if (duration.getSign() < 0) {
            message.append('-');
        }
        message.append('P');
        if (duration.getYear() != 0) {
            message.append(duration.getYear());
            message.append('Y');
        }
        if (duration.getMonth() != 0) {
            message.append(duration.getMonth());
            message.append('M');
        }
        if (duration.getDay() != 0) {
            message.append(duration.getDay());
            message.append('D');
        }
        if (duration.getHour() != 0 || duration.getMinute() != 0 || duration.getSecond() != 0 || duration.getFraction().signum() != 0) {
            message.append('T');
        }
        if (duration.getHour() != 0) {
            message.append(duration.getHour());
            message.append('H');
        }
        if (duration.getMinute() != 0) {
            message.append(duration.getMinute());
            message.append('M');
        }
        if (duration.getFraction().signum() != 0) {
            BigDecimal s = duration.getFraction();
            if (duration.getSecond() != 0) {
                s = s.add(BigDecimal.valueOf(duration.getSecond()));
            }
            message.append(stripTrailingZeros(toPlainString(s)));
            message.append('S');
        } else if (duration.getSecond() != 0) {
            message.append(duration.getSecond());
            message.append('S');
        } else if (message.length() <= 2) {
            message.append("T0S");
        }
        return message.toString();
    }

    public static String toPlainString(BigDecimal bd) {
        BigInteger intVal = bd.unscaledValue();
        int scale = bd.scale();
        String intValStr = intVal.toString();
        if (scale == 0) {
            return intValStr;
        }
        boolean isNegative = intValStr.charAt(0) == '-';
        int point = (intValStr.length() - scale) - (isNegative ? 1 : 0);
        StringBuilder sb = new StringBuilder(intValStr.length() + 2 + (point <= 0 ? (-point) + 1 : 0));
        if (point <= 0) {
            if (isNegative) {
                sb.append('-');
            }
            sb.append('0').append('.');
            while (point < 0) {
                sb.append('0');
                point++;
            }
            sb.append(intValStr.substring(isNegative ? 1 : 0));
        } else if (point < intValStr.length()) {
            sb.append(intValStr);
            sb.insert((isNegative ? 1 : 0) + point, '.');
        } else {
            sb.append(intValStr);
            if (!intVal.equals(BigInteger.ZERO)) {
                for (int i = intValStr.length(); i < point; i++) {
                    sb.append('0');
                }
            }
        }
        return sb.toString();
    }

    public static String stripTrailingZeros(String s) {
        boolean seenDot = false;
        int i = s.length() - 1;
        int zeroIndex = i;
        while (i >= 0 && s.charAt(i) == '0') {
            i--;
            zeroIndex--;
        }
        while (true) {
            if (i < 0) {
                break;
            }
            if (s.charAt(i) == 'E') {
                return s;
            }
            if (s.charAt(i) == '.') {
                seenDot = true;
                break;
            }
            i--;
        }
        return seenDot ? s.substring(0, zeroIndex + 1) : s;
    }
}
