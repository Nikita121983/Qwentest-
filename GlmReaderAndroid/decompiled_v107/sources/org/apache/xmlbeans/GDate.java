package org.apache.xmlbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.apache.commons.lang3.time.TimeZones;
import org.apache.poi.xddf.usermodel.Angles;
import org.apache.xmlbeans.impl.common.NameUtil;

/* loaded from: classes.dex */
public final class GDate implements GDateSpecification, Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int MAX_YEAR = 292277265;
    static final int MIN_YEAR = -292275295;
    private static final long serialVersionUID = 1;
    private int _CY;
    private int _D;
    private int _M;
    private int _bits;
    private transient String _canonicalString;
    private BigDecimal _fs;
    private int _h;
    private int _m;
    private int _s;
    private transient String _string;
    private int _tzh;
    private int _tzm;
    private int _tzsign;
    static final BigDecimal _zero = BigDecimal.ZERO;
    static final BigDecimal _one = BigDecimal.ONE;
    private static final char[] _tensDigit = {'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'};
    private static final char[] _onesDigit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final TimeZone GMTZONE = TimeZone.getTimeZone(TimeZones.GMT_ID);
    private static final TimeZone[] MINUSZONE = {TimeZone.getTimeZone("GMT-00:00"), TimeZone.getTimeZone("GMT-01:00"), TimeZone.getTimeZone("GMT-02:00"), TimeZone.getTimeZone("GMT-03:00"), TimeZone.getTimeZone("GMT-04:00"), TimeZone.getTimeZone("GMT-05:00"), TimeZone.getTimeZone("GMT-06:00"), TimeZone.getTimeZone("GMT-07:00"), TimeZone.getTimeZone("GMT-08:00"), TimeZone.getTimeZone("GMT-09:00"), TimeZone.getTimeZone("GMT-10:00"), TimeZone.getTimeZone("GMT-11:00"), TimeZone.getTimeZone("GMT-12:00"), TimeZone.getTimeZone("GMT-13:00"), TimeZone.getTimeZone("GMT-14:00")};
    private static final TimeZone[] PLUSZONE = {TimeZone.getTimeZone("GMT+00:00"), TimeZone.getTimeZone("GMT+01:00"), TimeZone.getTimeZone("GMT+02:00"), TimeZone.getTimeZone("GMT+03:00"), TimeZone.getTimeZone("GMT+04:00"), TimeZone.getTimeZone("GMT+05:00"), TimeZone.getTimeZone("GMT+06:00"), TimeZone.getTimeZone("GMT+07:00"), TimeZone.getTimeZone("GMT+08:00"), TimeZone.getTimeZone("GMT+09:00"), TimeZone.getTimeZone("GMT+10:00"), TimeZone.getTimeZone("GMT+11:00"), TimeZone.getTimeZone("GMT+12:00"), TimeZone.getTimeZone("GMT+13:00"), TimeZone.getTimeZone("GMT+14:00")};

    /* JADX WARN: Failed to find 'out' block for switch in B:204:0x005a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public GDate(java.lang.CharSequence r28) {
        /*
            Method dump skipped, instructions count: 832
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.GDate.<init>(java.lang.CharSequence):void");
    }

    public GDate(int year, int month, int day, int hour, int minute, int second, BigDecimal fraction) {
        this._bits = 30;
        this._CY = year;
        this._M = month;
        this._D = day;
        this._h = hour;
        this._m = minute;
        this._s = second;
        this._fs = fraction == null ? _zero : fraction;
        if (!isValid()) {
            throw new IllegalArgumentException();
        }
    }

    public GDate(int year, int month, int day, int hour, int minute, int second, BigDecimal fraction, int tzSign, int tzHour, int tzMinute) {
        this._bits = 31;
        this._CY = year;
        this._M = month;
        this._D = day;
        this._h = hour;
        this._m = minute;
        this._s = second;
        this._fs = fraction == null ? _zero : fraction;
        this._tzsign = tzSign;
        this._tzh = tzHour;
        this._tzm = tzMinute;
        if (!isValid()) {
            throw new IllegalArgumentException();
        }
    }

    public GDate(Date date) {
        this(new GDateBuilder(date));
    }

    public GDate(Calendar calendar) {
        int i;
        int i2;
        BigDecimal fs;
        boolean isSetYear = calendar.isSet(1);
        boolean isSetEra = calendar.isSet(0);
        boolean isSetMonth = calendar.isSet(2);
        boolean isSetDay = calendar.isSet(5);
        boolean isSetHourOfDay = calendar.isSet(11);
        boolean isSetHour = calendar.isSet(10);
        boolean isSetAmPm = calendar.isSet(9);
        boolean isSetMinute = calendar.isSet(12);
        boolean isSetSecond = calendar.isSet(13);
        boolean isSetMillis = calendar.isSet(14);
        boolean isSetZone = calendar.isSet(15);
        boolean isSetDst = calendar.isSet(16);
        if (!isSetYear) {
            i = 1;
            i2 = 16;
        } else {
            i2 = 16;
            int y = calendar.get(1);
            if (isSetEra) {
                i = 1;
                if ((calendar instanceof GregorianCalendar) && calendar.get(0) == 0) {
                    y = -y;
                }
            } else {
                i = 1;
            }
            this._bits |= 2;
            this._CY = y;
        }
        if (isSetMonth) {
            this._bits |= 4;
            this._M = calendar.get(2) + 1;
        }
        if (isSetDay) {
            this._bits |= 8;
            this._D = calendar.get(5);
        }
        boolean gotTime = false;
        int h = 0;
        int m = 0;
        int s = 0;
        BigDecimal fs2 = _zero;
        if (isSetHourOfDay) {
            h = calendar.get(11);
            gotTime = true;
        } else if (isSetHour && isSetAmPm) {
            h = calendar.get(10) + (calendar.get(9) * 12);
            gotTime = true;
        }
        if (isSetMinute) {
            m = calendar.get(12);
            gotTime = true;
        }
        if (isSetSecond) {
            s = calendar.get(13);
            gotTime = true;
        }
        if (isSetMillis) {
            BigDecimal fs3 = BigDecimal.valueOf(calendar.get(14), 3);
            gotTime = true;
            fs = fs3;
        } else {
            fs = fs2;
        }
        if (gotTime) {
            this._bits |= 16;
            this._h = h;
            this._m = m;
            this._s = s;
            this._fs = fs;
        }
        if (isSetZone) {
            int zoneOffsetInMilliseconds = calendar.get(15);
            zoneOffsetInMilliseconds = isSetDst ? zoneOffsetInMilliseconds + calendar.get(i2) : zoneOffsetInMilliseconds;
            this._bits |= 1;
            if (zoneOffsetInMilliseconds == 0) {
                this._tzsign = 0;
                this._tzh = 0;
                this._tzm = 0;
                TimeZone zone = calendar.getTimeZone();
                String id = zone.getID();
                if (id != null && id.length() > 3) {
                    switch (id.charAt(3)) {
                        case '+':
                            this._tzsign = i;
                            return;
                        case ',':
                        default:
                            return;
                        case '-':
                            this._tzsign = -1;
                            return;
                    }
                }
                return;
            }
            this._tzsign = zoneOffsetInMilliseconds < 0 ? -1 : i;
            int zoneOffsetInMilliseconds2 = zoneOffsetInMilliseconds * this._tzsign;
            this._tzh = zoneOffsetInMilliseconds2 / 3600000;
            this._tzm = (zoneOffsetInMilliseconds2 - (this._tzh * 3600000)) / Angles.OOXML_DEGREE;
        }
    }

    public GDate(GDateSpecification gdate) {
        if (gdate.hasTimeZone()) {
            this._bits |= 1;
            this._tzsign = gdate.getTimeZoneSign();
            this._tzh = gdate.getTimeZoneHour();
            this._tzm = gdate.getTimeZoneMinute();
        }
        if (gdate.hasTime()) {
            this._bits |= 16;
            this._h = gdate.getHour();
            this._m = gdate.getMinute();
            this._s = gdate.getSecond();
            this._fs = gdate.getFraction();
        }
        if (gdate.hasDay()) {
            this._bits |= 8;
            this._D = gdate.getDay();
        }
        if (gdate.hasMonth()) {
            this._bits |= 4;
            this._M = gdate.getMonth();
        }
        if (gdate.hasYear()) {
            this._bits |= 2;
            this._CY = gdate.getYear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isDigit(char ch) {
        return ((char) (ch + 65488)) <= '\t';
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSpace(char ch) {
        switch (ch) {
            case '\t':
            case '\n':
            case '\r':
            case ' ':
                return true;
            default:
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int digitVal(char ch) {
        return ch - '0';
    }

    private static int twoDigit(CharSequence str, int index) {
        char ch1 = str.charAt(index);
        char ch2 = str.charAt(index + 1);
        if (!isDigit(ch1) || !isDigit(ch2)) {
            return 100;
        }
        return (digitVal(ch1) * 10) + digitVal(ch2);
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public final boolean isImmutable() {
        return true;
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
        return this._CY;
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

    @Override // org.apache.xmlbeans.GDateSpecification
    public int getMillisecond() {
        if (this._fs == null) {
            return 0;
        }
        return this._fs.setScale(3, RoundingMode.DOWN).unscaledValue().intValue();
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public String canonicalString() {
        ensureCanonicalString();
        return this._canonicalString;
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public boolean isValid() {
        return GDateBuilder.isValidGDate(this);
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public int getJulianDate() {
        return GDateBuilder.julianDateForGDate(this);
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public XmlCalendar getCalendar() {
        return new XmlCalendar(this);
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public Date getDate() {
        return GDateBuilder.dateForGDate(this);
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public int compareToGDate(GDateSpecification datespec) {
        return GDateBuilder.compareGDate(this, datespec);
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public int getBuiltinTypeCode() {
        return GDateBuilder.btcForFlags(this._bits);
    }

    public GDate add(GDurationSpecification duration) {
        GDateBuilder builder = new GDateBuilder(this);
        builder.addGDuration(duration);
        return builder.toGDate();
    }

    public GDate subtract(GDurationSpecification duration) {
        GDateBuilder builder = new GDateBuilder(this);
        builder.subtractGDuration(duration);
        return builder.toGDate();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GDate)) {
            return false;
        }
        ensureCanonicalString();
        return this._canonicalString.equals(((GDate) obj).canonicalString());
    }

    public int hashCode() {
        ensureCanonicalString();
        return this._canonicalString.hashCode();
    }

    private void ensureCanonicalString() {
        if (this._canonicalString != null) {
            return;
        }
        boolean needNormalize = hasTimeZone() && getTimeZoneSign() != 0 && hasTime() && hasDay() == hasMonth() && hasDay() == hasYear();
        if (!needNormalize && getFraction() != null && getFraction().scale() > 0) {
            BigInteger bi = getFraction().unscaledValue();
            needNormalize = bi.mod(GDateBuilder.TEN).signum() == 0;
        }
        if (!needNormalize) {
            this._canonicalString = toString();
            return;
        }
        GDateBuilder gdb = new GDateBuilder(this);
        gdb.normalize();
        this._canonicalString = gdb.toString();
    }

    @Override // org.apache.xmlbeans.GDateSpecification
    public String toString() {
        if (this._string == null) {
            this._string = formatGDate(this);
        }
        return this._string;
    }

    private static int _padTwoAppend(char[] b, int i, int n) {
        if (n < 0 || n >= 100) {
            throw new AssertionError();
        }
        b[i] = _tensDigit[n];
        b[i + 1] = _onesDigit[n];
        return i + 2;
    }

    private static int _padFourAppend(char[] b, int n) {
        int i = 0;
        if (n < 0) {
            int i2 = 0 + 1;
            b[0] = '-';
            n = -n;
            i = i2;
        }
        if (n >= 10000) {
            String s = Integer.toString(n);
            s.getChars(0, s.length(), b, i);
            return s.length() + i;
        }
        int q = n / 100;
        int r = n - (q * 100);
        b[i] = _tensDigit[q];
        b[i + 1] = _onesDigit[q];
        b[i + 2] = _tensDigit[r];
        b[i + 3] = _onesDigit[r];
        return i + 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TimeZone timeZoneForGDate(GDateSpecification date) {
        if (!date.hasTimeZone()) {
            return TimeZone.getDefault();
        }
        if (date.getTimeZoneSign() == 0) {
            return GMTZONE;
        }
        if (date.getTimeZoneMinute() == 0 && date.getTimeZoneHour() <= 14 && date.getTimeZoneHour() >= 0) {
            return date.getTimeZoneSign() < 0 ? MINUSZONE[date.getTimeZoneHour()] : PLUSZONE[date.getTimeZoneHour()];
        }
        char[] zb = new char[9];
        zb[0] = 'G';
        zb[1] = 'M';
        zb[2] = 'T';
        zb[3] = date.getTimeZoneSign() < 0 ? '-' : '+';
        _padTwoAppend(zb, 4, date.getTimeZoneHour());
        zb[6] = NameUtil.COLON;
        _padTwoAppend(zb, 7, date.getTimeZoneMinute());
        return TimeZone.getTimeZone(new String(zb));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String formatGDate(GDateSpecification spec) {
        String frac;
        int point;
        BigDecimal fs = spec.getFraction();
        char[] message = new char[(fs == null ? 0 : fs.scale()) + 33];
        int i = 0;
        if (spec.hasYear() || spec.hasMonth() || spec.hasDay()) {
            if (spec.hasYear()) {
                i = _padFourAppend(message, spec.getYear());
            } else {
                int i2 = 0 + 1;
                message[0] = '-';
                i = i2;
            }
            if (spec.hasMonth() || spec.hasDay()) {
                int i3 = i + 1;
                message[i] = '-';
                if (spec.hasMonth()) {
                    i3 = _padTwoAppend(message, i3, spec.getMonth());
                }
                if (!spec.hasDay()) {
                    i = i3;
                } else {
                    message[i3] = '-';
                    i = _padTwoAppend(message, i3 + 1, spec.getDay());
                }
            }
            if (spec.hasTime()) {
                message[i] = 'T';
                i++;
            }
        }
        if (spec.hasTime()) {
            int i4 = _padTwoAppend(message, i, spec.getHour());
            message[i4] = NameUtil.COLON;
            int i5 = _padTwoAppend(message, i4 + 1, spec.getMinute());
            message[i5] = NameUtil.COLON;
            i = _padTwoAppend(message, i5 + 1, spec.getSecond());
            if (fs != null && !_zero.equals(fs) && (point = (frac = fs.toString()).indexOf(46)) >= 0) {
                frac.getChars(point, frac.length(), message, i);
                i += frac.length() - point;
            }
        }
        if (spec.hasTimeZone()) {
            if (spec.getTimeZoneSign() == 0) {
                message[i] = 'Z';
                i++;
            } else {
                int i6 = i + 1;
                message[i] = spec.getTimeZoneSign() > 0 ? '+' : '-';
                int i7 = _padTwoAppend(message, i6, spec.getTimeZoneHour());
                message[i7] = NameUtil.COLON;
                i = _padTwoAppend(message, i7 + 1, spec.getTimeZoneMinute());
            }
        }
        return new String(message, 0, i);
    }
}
