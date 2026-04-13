package org.apache.xmlbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.apache.poi.common.usermodel.fonts.FontHeader;
import org.apache.poi.xddf.usermodel.Angles;

/* loaded from: classes.dex */
public final class GDateBuilder implements GDateSpecification, Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final BigInteger TEN = BigInteger.valueOf(10);
    private static final long serialVersionUID = 1;
    private int _CY;
    private int _D;
    private int _M;
    private int _bits;
    private BigDecimal _fs;
    private int _h;
    private int _m;
    private int _s;
    private int _tzh;
    private int _tzm;
    private int _tzsign;

    public GDateBuilder() {
    }

    public Object clone() {
        return new GDateBuilder(this);
    }

    public GDate toGDate() {
        return new GDate(this);
    }

    public GDateBuilder(GDateSpecification gdate) {
        if (gdate.hasTimeZone()) {
            setTimeZone(gdate.getTimeZoneSign(), gdate.getTimeZoneHour(), gdate.getTimeZoneMinute());
        }
        if (gdate.hasTime()) {
            setTime(gdate.getHour(), gdate.getMinute(), gdate.getSecond(), gdate.getFraction());
        }
        if (gdate.hasDay()) {
            setDay(gdate.getDay());
        }
        if (gdate.hasMonth()) {
            setMonth(gdate.getMonth());
        }
        if (gdate.hasYear()) {
            setYear(gdate.getYear());
        }
    }

    public GDateBuilder(CharSequence string) {
        this(new GDate(string));
    }

    public GDateBuilder(Calendar calendar) {
        this(new GDate(calendar));
    }

    public GDateBuilder(int year, int month, int day, int hour, int minute, int second, BigDecimal fraction) {
        this._bits = 30;
        if (year == 0) {
            throw new IllegalArgumentException();
        }
        this._CY = year > 0 ? year : year + 1;
        this._M = month;
        this._D = day;
        this._h = hour;
        this._m = minute;
        this._s = second;
        this._fs = fraction == null ? GDate._zero : fraction;
        if (!isValid()) {
            throw new IllegalArgumentException();
        }
    }

    public GDateBuilder(int year, int month, int day, int hour, int minute, int second, BigDecimal fraction, int tzSign, int tzHour, int tzMinute) {
        this._bits = 31;
        if (year == 0) {
            throw new IllegalArgumentException();
        }
        this._CY = year > 0 ? year : year + 1;
        this._M = month;
        this._D = day;
        this._h = hour;
        this._m = minute;
        this._s = second;
        this._fs = fraction == null ? GDate._zero : fraction;
        this._tzsign = tzSign;
        this._tzh = tzHour;
        this._tzm = tzMinute;
        if (!isValid()) {
            throw new IllegalArgumentException();
        }
    }

    public GDateBuilder(Date date) {
        setDate(date);
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public boolean isImmutable() {
        return false;
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public int getFlags() {
        return this._bits;
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final boolean hasTimeZone() {
        return (this._bits & 1) != 0;
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final boolean hasYear() {
        return (this._bits & 2) != 0;
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final boolean hasMonth() {
        return (this._bits & 4) != 0;
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final boolean hasDay() {
        return (this._bits & 8) != 0;
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final boolean hasTime() {
        return (this._bits & 16) != 0;
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final boolean hasDate() {
        return (this._bits & 14) == 14;
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final int getYear() {
        return this._CY > 0 ? this._CY : this._CY - 1;
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final int getMonth() {
        return this._M;
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final int getDay() {
        return this._D;
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final int getHour() {
        return this._h;
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final int getMinute() {
        return this._m;
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final int getSecond() {
        return this._s;
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final BigDecimal getFraction() {
        return this._fs;
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final int getMillisecond() {
        if (this._fs == null || GDate._zero.equals(this._fs)) {
            return 0;
        }
        return this._fs.setScale(3, RoundingMode.HALF_UP).unscaledValue().intValue();
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final int getTimeZoneSign() {
        return this._tzsign;
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final int getTimeZoneHour() {
        return this._tzh;
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final int getTimeZoneMinute() {
        return this._tzm;
    }

    public void setYear(int year) {
        if (year < -292275295 || year > 292277265) {
            throw new IllegalArgumentException("year out of range");
        }
        if (year == 0) {
            throw new IllegalArgumentException("year cannot be 0");
        }
        this._bits |= 2;
        this._CY = year > 0 ? year : year + 1;
    }

    public void setMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("month out of range");
        }
        this._bits |= 4;
        this._M = month;
    }

    public void setDay(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("day out of range");
        }
        this._bits |= 8;
        this._D = day;
    }

    public void setTime(int hour, int minute, int second, BigDecimal fraction) {
        if (hour < 0 || hour > 24) {
            throw new IllegalArgumentException("hour out of range");
        }
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("minute out of range");
        }
        if (second < 0 || second > 59) {
            throw new IllegalArgumentException("second out of range");
        }
        if (fraction != null && (fraction.signum() < 0 || GDate._one.compareTo(fraction) <= 0)) {
            throw new IllegalArgumentException("fraction out of range");
        }
        if (hour == 24 && (minute != 0 || second != 0 || (fraction != null && GDate._zero.compareTo(fraction) != 0))) {
            throw new IllegalArgumentException("when hour is 24, min sec and fracton must be 0");
        }
        this._bits |= 16;
        this._h = hour;
        this._m = minute;
        this._s = second;
        this._fs = fraction == null ? GDate._zero : fraction;
    }

    public void setTimeZone(int tzSign, int tzHour, int tzMinute) {
        if ((tzSign == 0 && tzHour == 0 && tzMinute == 0) || ((tzSign == -1 || tzSign == 1) && tzHour >= 0 && tzMinute >= 0 && ((tzHour == 14 && tzMinute == 0) || (tzHour < 14 && tzMinute < 60)))) {
            this._bits = 1 | this._bits;
            this._tzsign = tzSign;
            this._tzh = tzHour;
            this._tzm = tzMinute;
            return;
        }
        throw new IllegalArgumentException("time zone out of range (-14:00 to +14:00). (" + (tzSign < 0 ? ProcessIdUtil.DEFAULT_PROCESSID : "+") + tzHour + ":" + tzMinute + ")");
    }

    public void setTimeZone(int tzTotalMinutes) {
        if (tzTotalMinutes < -840 || tzTotalMinutes > 840) {
            throw new IllegalArgumentException("time zone out of range (-840 to 840 minutes). (" + tzTotalMinutes + ")");
        }
        int tzSign = Integer.compare(tzTotalMinutes, 0);
        int tzTotalMinutes2 = tzTotalMinutes * tzSign;
        int tzH = tzTotalMinutes2 / 60;
        int tzM = tzTotalMinutes2 - (tzH * 60);
        setTimeZone(tzSign, tzH, tzM);
    }

    public void clearYear() {
        this._bits &= -3;
        this._CY = 0;
    }

    public void clearMonth() {
        this._bits &= -5;
        this._M = 0;
    }

    public void clearDay() {
        this._bits &= -9;
        this._D = 0;
    }

    public void clearTime() {
        this._bits &= -17;
        this._h = 0;
        this._m = 0;
        this._s = 0;
        this._fs = null;
    }

    public void clearTimeZone() {
        this._bits &= -2;
        this._tzsign = 0;
        this._tzh = 0;
        this._tzm = 0;
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public boolean isValid() {
        return isValidGDate(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0068, code lost:
    
        if (r0 > _maxDayInMonthFor(r3, r5.getMonth())) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0077, code lost:
    
        if (r5.getDay() > _maxDayInMonth(r5.getMonth())) goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isValidGDate(org.apache.xmlbeans.GDateSpecification r5) {
        /*
            Method dump skipped, instructions count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.GDateBuilder.isValidGDate(org.apache.xmlbeans.GDateSpecification):boolean");
    }

    public void normalize() {
        if (hasDay() == hasMonth() && hasDay() == hasYear() && hasTimeZone() && hasTime()) {
            normalizeToTimeZone(0, 0, 0);
        } else {
            _normalizeTimeAndDate();
        }
        if (hasTime() && this._fs != null && this._fs.scale() > 0) {
            if (this._fs.signum() == 0) {
                this._fs = GDate._zero;
                return;
            }
            BigInteger bi = this._fs.unscaledValue();
            String str = bi.toString();
            int lastzero = str.length();
            while (lastzero > 0 && str.charAt(lastzero - 1) == '0') {
                lastzero--;
            }
            if (lastzero < str.length()) {
                this._fs = this._fs.setScale((this._fs.scale() - str.length()) + lastzero, RoundingMode.UNNECESSARY);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void normalize24h() {
        if (!hasTime() || getHour() != 24) {
            return;
        }
        _normalizeTimeAndDate();
    }

    private void _normalizeTimeAndDate() {
        long carry = 0;
        if (hasTime()) {
            carry = _normalizeTime();
        }
        if (hasDay()) {
            this._D = Math.addExact(this._D, Math.toIntExact(carry));
        }
        if (hasDate()) {
            _normalizeDate();
            return;
        }
        if (hasMonth()) {
            if (this._M < 1 || this._M > 12) {
                int temp = this._M;
                this._M = _modulo(temp, 1, 13);
                if (hasYear()) {
                    this._CY += (int) _fQuotient(temp, 1, 13);
                }
            }
        }
    }

    public void normalizeToTimeZone(int tzSign, int tzHour, int tzMinute) {
        if ((tzSign != 0 || tzHour != 0 || tzMinute != 0) && ((tzSign != -1 && tzSign != 1) || tzHour < 0 || tzMinute < 0 || ((tzHour != 14 || tzMinute != 0) && (tzHour >= 14 || tzMinute >= 60)))) {
            throw new IllegalArgumentException("time zone must be between -14:00 and +14:00");
        }
        if (!hasTimeZone() || !hasTime()) {
            throw new IllegalStateException("cannot normalize time zone without both time and timezone");
        }
        if (hasDay() != hasMonth() || hasDay() != hasYear()) {
            throw new IllegalStateException("cannot do date math without a complete date");
        }
        int hshift = (tzSign * tzHour) - (this._tzsign * this._tzh);
        int mshift = (tzSign * tzMinute) - (this._tzsign * this._tzm);
        this._tzsign = tzSign;
        this._tzh = tzHour;
        this._tzm = tzMinute;
        addDuration(1, 0, 0, 0, hshift, mshift, 0, null);
    }

    public void normalizeToTimeZone(int tzTotalMinutes) {
        if (tzTotalMinutes < -840 || tzTotalMinutes > 840) {
            throw new IllegalArgumentException("time zone out of range (-840 to 840 minutes). (" + tzTotalMinutes + ")");
        }
        int tzSign = Integer.compare(tzTotalMinutes, 0);
        int tzTotalMinutes2 = tzTotalMinutes * tzSign;
        int tzH = tzTotalMinutes2 / 60;
        int tzM = tzTotalMinutes2 - (tzH * 60);
        normalizeToTimeZone(tzSign, tzH, tzM);
    }

    public void addGDuration(GDurationSpecification duration) {
        addDuration(duration.getSign(), duration.getYear(), duration.getMonth(), duration.getDay(), duration.getHour(), duration.getMinute(), duration.getSecond(), duration.getFraction());
    }

    public void subtractGDuration(GDurationSpecification duration) {
        addDuration(-duration.getSign(), duration.getYear(), duration.getMonth(), duration.getDay(), duration.getHour(), duration.getMinute(), duration.getSecond(), duration.getFraction());
    }

    private void _normalizeDate() {
        if (this._M < 1 || this._M > 12 || this._D < 1 || this._D > _maxDayInMonthFor(this._CY, this._M)) {
            int temp = this._M;
            this._M = _modulo(temp, 1, 13);
            this._CY += (int) _fQuotient(temp, 1, 13);
            int extradays = this._D - 1;
            this._D = 1;
            setJulianDate(getJulianDate() + extradays);
        }
    }

    private long _normalizeTime() {
        long carry = 0;
        if (this._fs != null && (this._fs.signum() < 0 || this._fs.compareTo(GDate._one) >= 0)) {
            BigDecimal bdcarry = this._fs.setScale(0, RoundingMode.FLOOR);
            this._fs = this._fs.subtract(bdcarry);
            carry = bdcarry.longValue();
        }
        if (carry != 0 || this._s < 0 || this._s > 59 || this._m < 0 || this._m > 50 || this._h < 0 || this._h > 23) {
            long temp = this._s + carry;
            long carry2 = _fQuotient(temp, 60);
            this._s = _mod(temp, 60, carry2);
            long temp2 = this._m + carry2;
            long carry3 = _fQuotient(temp2, 60);
            this._m = _mod(temp2, 60, carry3);
            long temp3 = this._h + carry3;
            long carry4 = _fQuotient(temp3, 24);
            this._h = _mod(temp3, 24, carry4);
            return carry4;
        }
        return carry;
    }

    public void addDuration(int sign, int year, int month, int day, int hour, int minute, int second, BigDecimal fraction) {
        boolean datemath = false;
        boolean timemath = (hour == 0 && minute == 0 && second == 0 && (fraction == null || fraction.signum() == 0)) ? false : true;
        if (timemath && !hasTime()) {
            throw new IllegalStateException("cannot do time math without a complete time");
        }
        if (hasDay() && (day != 0 || timemath)) {
            datemath = true;
        }
        if (datemath && !hasDate()) {
            throw new IllegalStateException("cannot do date math without a complete date");
        }
        if (month != 0 || year != 0) {
            if (hasDay()) {
                _normalizeDate();
            }
            int temp = this._M + (sign * month);
            this._M = _modulo(temp, 1, 13);
            this._CY = this._CY + (sign * year) + ((int) _fQuotient(temp, 1, 13));
            if (hasDay()) {
                if (this._D < 1) {
                    throw new AssertionError();
                }
                int temp2 = _maxDayInMonthFor(this._CY, this._M);
                if (this._D > temp2) {
                    this._D = temp2;
                }
            }
        }
        long carry = 0;
        if (timemath) {
            if (fraction != null && fraction.signum() != 0) {
                if (this._fs.signum() == 0 && sign == 1) {
                    this._fs = fraction;
                } else {
                    this._fs = sign == 1 ? this._fs.add(fraction) : this._fs.subtract(fraction);
                }
            }
            this._s += sign * second;
            this._m += sign * minute;
            this._h += sign * hour;
            carry = _normalizeTime();
        }
        if (datemath) {
            this._D = Math.addExact(this._D, Math.toIntExact(Math.addExact(Math.multiplyExact(sign, day), carry)));
            _normalizeDate();
        }
    }

    private static int _maxDayInMonthFor(int year, int month) {
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        if (month == 2) {
            return _isLeapYear(year) ? 29 : 28;
        }
        return 31;
    }

    private static int _maxDayInMonth(int month) {
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        return month == 2 ? 29 : 31;
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final int getJulianDate() {
        return julianDateForGDate(this);
    }

    public void setJulianDate(int julianday) {
        if (julianday < 0) {
            throw new IllegalArgumentException("date before year -4713");
        }
        int temp = 68569 + julianday;
        int qepoc = (temp * 4) / 146097;
        int temp2 = temp - (((146097 * qepoc) + 3) / 4);
        this._CY = ((temp2 + 1) * 4000) / 1461001;
        int temp3 = (temp2 - ((this._CY * 1461) / 4)) + 31;
        this._M = (temp3 * 80) / 2447;
        this._D = temp3 - ((this._M * 2447) / 80);
        int temp4 = this._M / 11;
        this._M = (this._M + 2) - (temp4 * 12);
        this._CY = ((qepoc - 49) * 100) + this._CY + temp4;
        this._bits |= 14;
    }

    public void setDate(Date date) {
        TimeZone dtz = TimeZone.getDefault();
        int offset = dtz.getOffset(date.getTime());
        int offsetsign = 1;
        if (offset < 0) {
            offsetsign = -1;
            offset = -offset;
        }
        int offsetmin = offset / Angles.OOXML_DEGREE;
        int offsethr = offsetmin / 60;
        int offsetmin2 = offsetmin - (offsethr * 60);
        setTimeZone(offsetsign, offsethr, offsetmin2);
        int roundedoffset = ((offsethr * 60) + offsetmin2) * offsetsign * 60 * 1000;
        setTime(0, 0, 0, GDate._zero);
        this._bits |= 14;
        this._CY = 1970;
        this._M = 1;
        this._D = 1;
        addGDuration(new GDuration(1, 0, 0, 0, 0, 0, 0, BigDecimal.valueOf(date.getTime() + roundedoffset, 3)));
        if (this._fs.signum() == 0) {
            this._fs = GDate._zero;
        }
    }

    public void setGDate(GDateSpecification gdate) {
        this._bits = gdate.getFlags() & 31;
        int year = gdate.getYear();
        this._CY = year > 0 ? year : year + 1;
        this._M = gdate.getMonth();
        this._D = gdate.getDay();
        this._h = gdate.getHour();
        this._m = gdate.getMinute();
        this._s = gdate.getSecond();
        this._fs = gdate.getFraction();
        this._tzsign = gdate.getTimeZoneSign();
        this._tzh = gdate.getTimeZoneHour();
        this._tzm = gdate.getTimeZoneMinute();
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public XmlCalendar getCalendar() {
        return new XmlCalendar(this);
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public Date getDate() {
        return dateForGDate(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int julianDateForGDate(GDateSpecification date) {
        if (!date.hasDate()) {
            throw new IllegalStateException("cannot do date math without a complete date");
        }
        int day = date.getDay();
        int month = date.getMonth();
        int year = date.getYear();
        int year2 = year > 0 ? year : year + 1;
        int result = (((day - 32075) + ((((year2 + 4800) + ((month - 14) / 12)) * 1461) / 4)) + ((((month - 2) - (((month - 14) / 12) * 12)) * 367) / 12)) - (((((year2 + 4900) + ((month - 14) / 12)) / 100) * 3) / 4);
        if (result < 0) {
            throw new IllegalStateException("date too far in the past (year allowed to -4713)");
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Date dateForGDate(GDateSpecification date) {
        long to1970Ms;
        long jDate = julianDateForGDate(date);
        long to1970Date = jDate - 2440588;
        long to1970Ms2 = (86400000 * to1970Date) + date.getMillisecond() + (date.getSecond() * 1000) + (date.getMinute() * 60 * 1000) + (date.getHour() * 60 * 60 * 1000);
        if (date.hasTimeZone()) {
            to1970Ms = (to1970Ms2 - (((date.getTimeZoneMinute() * date.getTimeZoneSign()) * 60) * 1000)) - ((((date.getTimeZoneHour() * date.getTimeZoneSign()) * 60) * 60) * 1000);
        } else {
            TimeZone def = TimeZone.getDefault();
            int offset = def.getOffset(to1970Ms2);
            to1970Ms = to1970Ms2 - offset;
        }
        return new Date(to1970Ms);
    }

    private static boolean _isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % FontHeader.REGULAR_WEIGHT == 0);
    }

    private static long _fQuotient(long a, int b) {
        return ((a > 0L ? 1 : (a == 0L ? 0 : -1)) < 0) == (b < 0) ? a / b : -(((b - a) - 1) / b);
    }

    private static int _mod(long a, int b, long quotient) {
        return (int) (a - (b * quotient));
    }

    private static int _modulo(long temp, int low, int high) {
        long a = temp - low;
        int b = high - low;
        return _mod(a, b, _fQuotient(a, b)) + low;
    }

    private static long _fQuotient(long temp, int low, int high) {
        return _fQuotient(temp - low, high - low);
    }

    private void _setToFirstMoment() {
        if (!hasYear()) {
            setYear(1584);
        }
        if (!hasMonth()) {
            setMonth(1);
        }
        if (!hasDay()) {
            setDay(1);
        }
        if (!hasTime()) {
            setTime(0, 0, 0, GDate._zero);
        }
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final int compareToGDate(GDateSpecification datespec) {
        return compareGDate(this, datespec);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int compareGDate(GDateSpecification tdate, GDateSpecification datespec) {
        int bitdiff = tdate.getFlags() ^ datespec.getFlags();
        if ((bitdiff & 31) == 0) {
            if (tdate.hasTimeZone() && (datespec.getTimeZoneHour() != tdate.getTimeZoneHour() || datespec.getTimeZoneMinute() != tdate.getTimeZoneMinute() || datespec.getTimeZoneSign() != tdate.getTimeZoneSign())) {
                datespec = new GDateBuilder(datespec);
                int flags = tdate.getFlags() & 14;
                if ((flags != 0 && flags != 14) || !tdate.hasTime()) {
                    ((GDateBuilder) datespec)._setToFirstMoment();
                    GDateSpecification tdate2 = new GDateBuilder(tdate);
                    ((GDateBuilder) tdate2)._setToFirstMoment();
                    tdate = tdate2;
                }
                ((GDateBuilder) datespec).normalizeToTimeZone(tdate.getTimeZoneSign(), tdate.getTimeZoneHour(), tdate.getTimeZoneMinute());
            }
            return fieldwiseCompare(tdate, datespec);
        }
        if ((bitdiff & 30) != 0) {
            return 2;
        }
        if (!tdate.hasTimeZone()) {
            int result = compareGDate(datespec, tdate);
            if (result == 2) {
                return 2;
            }
            return -result;
        }
        GDateBuilder pdate = new GDateBuilder(tdate);
        if ((tdate.getFlags() & 14) == 12) {
            if (tdate.getDay() == 28 && tdate.getMonth() == 2) {
                if (datespec.getDay() == 1 && datespec.getMonth() == 3) {
                    pdate.setDay(29);
                }
            } else if (datespec.getDay() == 28 && datespec.getMonth() == 2 && tdate.getDay() == 1 && tdate.getMonth() == 3) {
                pdate.setMonth(2);
                pdate.setDay(29);
            }
        }
        pdate._setToFirstMoment();
        GDateBuilder qplusdate = new GDateBuilder(datespec);
        qplusdate._setToFirstMoment();
        qplusdate.setTimeZone(1, 14, 0);
        qplusdate.normalizeToTimeZone(tdate.getTimeZoneSign(), tdate.getTimeZoneHour(), tdate.getTimeZoneMinute());
        if (fieldwiseCompare(pdate, qplusdate) == -1) {
            return -1;
        }
        qplusdate.setGDate(datespec);
        qplusdate._setToFirstMoment();
        qplusdate.setTimeZone(-1, 14, 0);
        qplusdate.normalizeToTimeZone(tdate.getTimeZoneSign(), tdate.getTimeZoneHour(), tdate.getTimeZoneMinute());
        return fieldwiseCompare(pdate, qplusdate) == 1 ? 1 : 2;
    }

    private static int fieldwiseCompare(GDateSpecification tdate, GDateSpecification date) {
        if (tdate.hasYear()) {
            int CY = date.getYear();
            int TCY = tdate.getYear();
            if (TCY < CY) {
                return -1;
            }
            if (TCY > CY) {
                return 1;
            }
        }
        if (tdate.hasMonth()) {
            int M = date.getMonth();
            int TM = tdate.getMonth();
            if (TM < M) {
                return -1;
            }
            if (TM > M) {
                return 1;
            }
        }
        if (tdate.hasDay()) {
            int D = date.getDay();
            int TD = tdate.getDay();
            if (TD < D) {
                return -1;
            }
            if (TD > D) {
                return 1;
            }
        }
        if (!tdate.hasTime()) {
            return 0;
        }
        int h = date.getHour();
        int th = tdate.getHour();
        if (th < h) {
            return -1;
        }
        if (th > h) {
            return 1;
        }
        int m = date.getMinute();
        int tm = tdate.getMinute();
        if (tm < m) {
            return -1;
        }
        if (tm > m) {
            return 1;
        }
        int s = date.getSecond();
        int ts = tdate.getSecond();
        if (ts < s) {
            return -1;
        }
        if (ts > s) {
            return 1;
        }
        BigDecimal fs = date.getFraction();
        BigDecimal tfs = tdate.getFraction();
        if (tfs == null && fs == null) {
            return 0;
        }
        return (tfs == null ? GDate._zero : tfs).compareTo(fs == null ? GDate._zero : fs);
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final int getBuiltinTypeCode() {
        return btcForFlags(this._bits);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int btcForFlags(int flags) {
        switch (flags & 30) {
            case 2:
                return 18;
            case 4:
                return 21;
            case 6:
                return 17;
            case 8:
                return 20;
            case 12:
                return 19;
            case 14:
                return 16;
            case 16:
                return 15;
            case 30:
                return 14;
            default:
                return 0;
        }
    }

    public void setBuiltinTypeCode(int typeCode) {
        switch (typeCode) {
            case 14:
                return;
            case 15:
                clearYear();
                clearMonth();
                clearDay();
                return;
            case 16:
                clearTime();
                return;
            case 17:
                clearDay();
                clearTime();
                return;
            case 18:
                clearMonth();
                clearDay();
                clearTime();
                return;
            case 19:
                clearYear();
                clearTime();
                return;
            case 20:
                clearYear();
                clearMonth();
                clearTime();
                return;
            case 21:
                clearYear();
                clearDay();
                clearTime();
                return;
            default:
                throw new IllegalArgumentException("codeType must be one of SchemaType BTC_  DATE TIME related types.");
        }
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public String canonicalString() {
        boolean needNormalize = hasTimeZone() && getTimeZoneSign() != 0 && hasTime() && hasDay() == hasMonth() && hasDay() == hasYear();
        if (!needNormalize && getFraction() != null && getFraction().scale() > 0) {
            BigInteger bi = getFraction().unscaledValue();
            needNormalize = bi.mod(TEN).signum() == 0;
        }
        if (!needNormalize) {
            return toString();
        }
        GDateBuilder cdate = new GDateBuilder(this);
        cdate.normalize();
        return cdate.toString();
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final String toString() {
        return GDate.formatGDate(this);
    }
}
