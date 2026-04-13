package org.apache.xmlbeans;

import java.io.Serializable;
import java.math.BigDecimal;

/* loaded from: classes.dex */
public final class GDuration implements GDurationSpecification, Serializable {
    private static final int SEEN_DAY = 3;
    private static final int SEEN_HOUR = 4;
    private static final int SEEN_MINUTE = 5;
    private static final int SEEN_MONTH = 2;
    private static final int SEEN_NOTHING = 0;
    private static final int SEEN_SECOND = 6;
    private static final int SEEN_YEAR = 1;
    private static final long serialVersionUID = 1;
    private int _CY;
    private int _D;
    private int _M;
    private BigDecimal _fs;
    private int _h;
    private int _m;
    private int _s;
    private int _sign;

    public GDuration() {
        this._sign = 1;
        this._fs = GDate._zero;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:70:0x00cc. Please report as an issue. */
    public GDuration(CharSequence str) {
        char charAt;
        int len = str.length();
        int start = 0;
        while (len > 0 && GDate.isSpace(str.charAt(len - 1))) {
            len--;
        }
        while (start < len && GDate.isSpace(str.charAt(start))) {
            start++;
        }
        this._sign = 1;
        boolean tmark = false;
        if (start < len && str.charAt(start) == '-') {
            this._sign = -1;
            start++;
        }
        if (start >= len || str.charAt(start) != 'P') {
            throw new IllegalArgumentException("duration must begin with P");
        }
        int start2 = start + 1;
        int seen = 0;
        this._fs = GDate._zero;
        while (start2 < len) {
            char ch = str.charAt(start2);
            if (ch == 'T') {
                if (tmark) {
                    throw new IllegalArgumentException("duration must have no more than one T'");
                }
                if (seen > 3) {
                    throw new IllegalArgumentException("T in duration must precede time fields");
                }
                seen = 3;
                tmark = true;
                start2++;
                if (start2 >= len) {
                    throw new IllegalArgumentException("illegal duration");
                }
                ch = str.charAt(start2);
            }
            if (!GDate.isDigit(ch)) {
                throw new IllegalArgumentException("illegal duration at char[" + start2 + "]: '" + ch + "'");
            }
            int value = GDate.digitVal(ch);
            while (true) {
                start2++;
                char ch2 = start2 < len ? str.charAt(start2) : (char) 0;
                if (GDate.isDigit(ch2)) {
                    value = (value * 10) + GDate.digitVal(ch2);
                } else {
                    if (ch2 == '.') {
                        int i = start2;
                        do {
                            i++;
                            if (i < len) {
                                charAt = str.charAt(i);
                                ch2 = charAt;
                            }
                            this._fs = new BigDecimal(str.subSequence(start2, i).toString());
                            if (i < len || ch2 != 'S') {
                                throw new IllegalArgumentException("illegal duration");
                            }
                            start2 = i;
                        } while (GDate.isDigit(charAt));
                        this._fs = new BigDecimal(str.subSequence(start2, i).toString());
                        if (i < len) {
                        }
                        throw new IllegalArgumentException("illegal duration");
                    }
                    switch (seen) {
                        case 0:
                            if (ch2 == 'Y') {
                                seen = 1;
                                this._CY = value;
                                start2++;
                            }
                        case 1:
                            if (ch2 == 'M') {
                                seen = 2;
                                this._M = value;
                                start2++;
                            }
                        case 2:
                            if (ch2 == 'D') {
                                seen = 3;
                                this._D = value;
                                start2++;
                            }
                        case 3:
                            if (ch2 == 'H') {
                                if (!tmark) {
                                    throw new IllegalArgumentException("time in duration must follow T");
                                }
                                seen = 4;
                                this._h = value;
                                start2++;
                            }
                        case 4:
                            if (ch2 == 'M') {
                                if (!tmark) {
                                    throw new IllegalArgumentException("time in duration must follow T");
                                }
                                seen = 5;
                                this._m = value;
                                start2++;
                            }
                        case 5:
                            if (ch2 == 'S') {
                                if (!tmark) {
                                    throw new IllegalArgumentException("time in duration must follow T");
                                }
                                seen = 6;
                                this._s = value;
                                start2++;
                            } else {
                                throw new IllegalArgumentException("duration must specify Y M D T H M S in order");
                            }
                        default:
                            throw new IllegalArgumentException("duration must specify Y M D T H M S in order");
                    }
                }
            }
        }
        if (seen == 0) {
            throw new IllegalArgumentException("duration must contain at least one number and its designator: " + ((Object) str));
        }
    }

    public GDuration(int sign, int year, int month, int day, int hour, int minute, int second, BigDecimal fraction) {
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

    public GDuration(GDurationSpecification gDuration) {
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
        return new GDuration(this);
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
        return GDurationBuilder.isValidDuration(this);
    }

    @Override // org.apache.xmlbeans.GDurationSpecification
    public final int compareToGDuration(GDurationSpecification duration) {
        return GDurationBuilder.compareDurations(this, duration);
    }

    public String toString() {
        return GDurationBuilder.formatDuration(this);
    }

    public GDuration add(GDurationSpecification duration) {
        int sign = this._sign * duration.getSign();
        return _add(duration, sign);
    }

    public GDuration subtract(GDurationSpecification duration) {
        int sign = (-this._sign) * duration.getSign();
        return _add(duration, sign);
    }

    private GDuration _add(GDurationSpecification duration, int sign) {
        BigDecimal subtract;
        GDuration result = new GDuration(this);
        result._CY += duration.getYear() * sign;
        result._M += duration.getMonth() * sign;
        result._D += duration.getDay() * sign;
        result._h += duration.getHour() * sign;
        result._m += duration.getMinute() * sign;
        result._s += duration.getSecond() * sign;
        if (duration.getFraction().signum() == 0) {
            return result;
        }
        if (result._fs.signum() == 0 && sign == 1) {
            result._fs = duration.getFraction();
        } else {
            if (sign > 0) {
                subtract = result._fs.add(duration.getFraction());
            } else {
                subtract = result._fs.subtract(duration.getFraction());
            }
            result._fs = subtract;
        }
        return result;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GDuration)) {
            return false;
        }
        GDuration duration = (GDuration) obj;
        return this._sign == duration.getSign() && this._CY == duration.getYear() && this._M == duration.getMonth() && this._D == duration.getDay() && this._h == duration.getHour() && this._m == duration.getMinute() && this._s == duration.getSecond() && this._fs.equals(duration.getFraction());
    }

    public int hashCode() {
        return this._s + (this._m * 67) + (this._h * 3607) + (this._D * 86407) + (this._M * 2678407) + (this._CY * 32140807) + (this._sign * 11917049);
    }
}
