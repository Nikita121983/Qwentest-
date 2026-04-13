package org.apache.commons.lang3.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.xddf.usermodel.Angles;
import org.apache.xmlbeans.impl.common.NameUtil;

/* loaded from: classes9.dex */
public class FastDatePrinter implements DatePrinter, Serializable {
    public static final int FULL = 0;
    public static final int LONG = 1;
    private static final int MAX_DIGITS = 10;
    public static final int MEDIUM = 2;
    public static final int SHORT = 3;
    private static final long serialVersionUID = 1;
    private final Locale locale;
    private transient int maxLengthEstimate;
    private final String pattern;
    private transient Rule[] rules;
    private final TimeZone timeZone;
    private static final Rule[] EMPTY_RULE_ARRAY = new Rule[0];
    private static final ConcurrentMap<TimeZoneDisplayKey, String> timeZoneDisplayCache = new ConcurrentHashMap(7);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public interface NumberRule extends Rule {
        void appendTo(Appendable appendable, int i) throws IOException;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public interface Rule {
        void appendTo(Appendable appendable, Calendar calendar) throws IOException;

        int estimateLength();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class CharacterLiteral implements Rule {
        private final char value;

        CharacterLiteral(char value) {
            this.value = value;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable buffer, Calendar calendar) throws IOException {
            buffer.append(this.value);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class DayInWeekField implements NumberRule {
        private final NumberRule rule;

        DayInWeekField(NumberRule rule) {
            this.rule = rule;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable buffer, Calendar calendar) throws IOException {
            int value = calendar.get(7);
            this.rule.appendTo(buffer, value != 1 ? value - 1 : 7);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable buffer, int value) throws IOException {
            this.rule.appendTo(buffer, value);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.rule.estimateLength();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class Iso8601_Rule implements Rule {
        private final int length;
        static final Iso8601_Rule ISO8601_HOURS = new Iso8601_Rule(3);
        static final Iso8601_Rule ISO8601_HOURS_MINUTES = new Iso8601_Rule(5);
        static final Iso8601_Rule ISO8601_HOURS_COLON_MINUTES = new Iso8601_Rule(6);

        static Iso8601_Rule getRule(int tokenLen) {
            switch (tokenLen) {
                case 1:
                    return ISO8601_HOURS;
                case 2:
                    return ISO8601_HOURS_MINUTES;
                case 3:
                    return ISO8601_HOURS_COLON_MINUTES;
                default:
                    throw new IllegalArgumentException("invalid number of X");
            }
        }

        Iso8601_Rule(int length) {
            this.length = length;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable buffer, Calendar calendar) throws IOException {
            int offset = calendar.get(15) + calendar.get(16);
            if (offset == 0) {
                buffer.append("Z");
                return;
            }
            if (offset < 0) {
                buffer.append('-');
                offset = -offset;
            } else {
                buffer.append('+');
            }
            int hours = offset / 3600000;
            FastDatePrinter.appendDigits(buffer, hours);
            if (this.length < 5) {
                return;
            }
            if (this.length == 6) {
                buffer.append(NameUtil.COLON);
            }
            int minutes = (offset / Angles.OOXML_DEGREE) - (hours * 60);
            FastDatePrinter.appendDigits(buffer, minutes);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.length;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class PaddedNumberField implements NumberRule {
        private final int field;
        private final int size;

        PaddedNumberField(int field, int size) {
            if (size < 3) {
                throw new IllegalArgumentException();
            }
            this.field = field;
            this.size = size;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable buffer, Calendar calendar) throws IOException {
            appendTo(buffer, calendar.get(this.field));
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable buffer, int value) throws IOException {
            FastDatePrinter.appendFullDigits(buffer, value, this.size);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.size;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class StringLiteral implements Rule {
        private final String value;

        StringLiteral(String value) {
            this.value = value;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable buffer, Calendar calendar) throws IOException {
            buffer.append(this.value);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.value.length();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class TextField implements Rule {
        private final int field;
        private final String[] values;

        TextField(int field, String[] values) {
            this.field = field;
            this.values = values;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable buffer, Calendar calendar) throws IOException {
            buffer.append(this.values[calendar.get(this.field)]);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            int max = 0;
            int i = this.values.length;
            while (true) {
                i--;
                if (i >= 0) {
                    int len = this.values[i].length();
                    if (len > max) {
                        max = len;
                    }
                } else {
                    return max;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class TimeZoneDisplayKey {
        private final Locale locale;
        private final int style;
        private final TimeZone timeZone;

        TimeZoneDisplayKey(TimeZone timeZone, boolean daylight, int style, Locale locale) {
            this.timeZone = timeZone;
            if (daylight) {
                this.style = Integer.MIN_VALUE | style;
            } else {
                this.style = style;
            }
            this.locale = LocaleUtils.toLocale(locale);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TimeZoneDisplayKey)) {
                return false;
            }
            TimeZoneDisplayKey other = (TimeZoneDisplayKey) obj;
            return this.timeZone.equals(other.timeZone) && this.style == other.style && this.locale.equals(other.locale);
        }

        public int hashCode() {
            return (((this.style * 31) + this.locale.hashCode()) * 31) + this.timeZone.hashCode();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class TimeZoneNameRule implements Rule {
        private final String daylight;
        private final Locale locale;
        private final String standard;
        private final int style;

        TimeZoneNameRule(TimeZone timeZone, Locale locale, int style) {
            this.locale = LocaleUtils.toLocale(locale);
            this.style = style;
            this.standard = FastDatePrinter.getTimeZoneDisplay(timeZone, false, style, locale);
            this.daylight = FastDatePrinter.getTimeZoneDisplay(timeZone, true, style, locale);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable buffer, Calendar calendar) throws IOException {
            TimeZone zone = calendar.getTimeZone();
            boolean daylight = calendar.get(16) != 0;
            buffer.append(FastDatePrinter.getTimeZoneDisplay(zone, daylight, this.style, this.locale));
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return Math.max(this.standard.length(), this.daylight.length());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class TimeZoneNumberRule implements Rule {
        static final TimeZoneNumberRule INSTANCE_COLON = new TimeZoneNumberRule(true);
        static final TimeZoneNumberRule INSTANCE_NO_COLON = new TimeZoneNumberRule(false);
        private final boolean colon;

        TimeZoneNumberRule(boolean colon) {
            this.colon = colon;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable buffer, Calendar calendar) throws IOException {
            int offset = calendar.get(15) + calendar.get(16);
            if (offset < 0) {
                buffer.append('-');
                offset = -offset;
            } else {
                buffer.append('+');
            }
            int hours = offset / 3600000;
            FastDatePrinter.appendDigits(buffer, hours);
            if (this.colon) {
                buffer.append(NameUtil.COLON);
            }
            int minutes = (offset / Angles.OOXML_DEGREE) - (hours * 60);
            FastDatePrinter.appendDigits(buffer, minutes);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 5;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class TwelveHourField implements NumberRule {
        private final NumberRule rule;

        TwelveHourField(NumberRule rule) {
            this.rule = rule;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable buffer, Calendar calendar) throws IOException {
            int value = calendar.get(10);
            if (value == 0) {
                value = calendar.getLeastMaximum(10) + 1;
            }
            this.rule.appendTo(buffer, value);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable buffer, int value) throws IOException {
            this.rule.appendTo(buffer, value);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.rule.estimateLength();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class TwentyFourHourField implements NumberRule {
        private final NumberRule rule;

        TwentyFourHourField(NumberRule rule) {
            this.rule = rule;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable buffer, Calendar calendar) throws IOException {
            int value = calendar.get(11);
            if (value == 0) {
                value = calendar.getMaximum(11) + 1;
            }
            this.rule.appendTo(buffer, value);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable buffer, int value) throws IOException {
            this.rule.appendTo(buffer, value);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.rule.estimateLength();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class TwoDigitMonthField implements NumberRule {
        static final TwoDigitMonthField INSTANCE = new TwoDigitMonthField();

        TwoDigitMonthField() {
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable buffer, Calendar calendar) throws IOException {
            appendTo(buffer, calendar.get(2) + 1);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable buffer, int value) throws IOException {
            FastDatePrinter.appendDigits(buffer, value);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class TwoDigitNumberField implements NumberRule {
        private final int field;

        TwoDigitNumberField(int field) {
            this.field = field;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable buffer, Calendar calendar) throws IOException {
            appendTo(buffer, calendar.get(this.field));
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable buffer, int value) throws IOException {
            if (value < 100) {
                FastDatePrinter.appendDigits(buffer, value);
            } else {
                FastDatePrinter.appendFullDigits(buffer, value, 2);
            }
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class TwoDigitYearField implements NumberRule {
        static final TwoDigitYearField INSTANCE = new TwoDigitYearField();

        TwoDigitYearField() {
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable buffer, Calendar calendar) throws IOException {
            appendTo(buffer, calendar.get(1) % 100);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable buffer, int value) throws IOException {
            FastDatePrinter.appendDigits(buffer, value % 100);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class UnpaddedMonthField implements NumberRule {
        static final UnpaddedMonthField INSTANCE = new UnpaddedMonthField();

        UnpaddedMonthField() {
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable buffer, Calendar calendar) throws IOException {
            appendTo(buffer, calendar.get(2) + 1);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable buffer, int value) throws IOException {
            if (value >= 10) {
                FastDatePrinter.appendDigits(buffer, value);
            } else {
                buffer.append((char) (value + 48));
            }
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class UnpaddedNumberField implements NumberRule {
        private final int field;

        UnpaddedNumberField(int field) {
            this.field = field;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable buffer, Calendar calendar) throws IOException {
            appendTo(buffer, calendar.get(this.field));
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable buffer, int value) throws IOException {
            if (value < 10) {
                buffer.append((char) (value + 48));
            } else if (value < 100) {
                FastDatePrinter.appendDigits(buffer, value);
            } else {
                FastDatePrinter.appendFullDigits(buffer, value, 1);
            }
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return 4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class WeekYear implements NumberRule {
        private final NumberRule rule;

        WeekYear(NumberRule rule) {
            this.rule = rule;
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public void appendTo(Appendable buffer, Calendar calendar) throws IOException {
            this.rule.appendTo(buffer, calendar.getWeekYear());
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.NumberRule
        public void appendTo(Appendable buffer, int value) throws IOException {
            this.rule.appendTo(buffer, value);
        }

        @Override // org.apache.commons.lang3.time.FastDatePrinter.Rule
        public int estimateLength() {
            return this.rule.estimateLength();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void appendDigits(Appendable buffer, int value) throws IOException {
        buffer.append((char) ((value / 10) + 48));
        buffer.append((char) ((value % 10) + 48));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to find 'out' block for switch in B:14:0x0023. Please report as an issue. */
    public static void appendFullDigits(Appendable buffer, int value, int minFieldWidth) throws IOException {
        if (value < 10000) {
            int nDigits = 4;
            if (value < 1000) {
                nDigits = 4 - 1;
                if (value < 100) {
                    nDigits--;
                    if (value < 10) {
                        nDigits--;
                    }
                }
            }
            for (int i = minFieldWidth - nDigits; i > 0; i--) {
                buffer.append('0');
            }
            switch (nDigits) {
                case 4:
                    buffer.append((char) ((value / 1000) + 48));
                    value %= 1000;
                case 3:
                    if (value >= 100) {
                        buffer.append((char) ((value / 100) + 48));
                        value %= 100;
                    } else {
                        buffer.append('0');
                    }
                case 2:
                    if (value >= 10) {
                        buffer.append((char) ((value / 10) + 48));
                        value %= 10;
                    } else {
                        buffer.append('0');
                    }
                case 1:
                    buffer.append((char) (value + 48));
                    return;
                default:
                    return;
            }
        } else {
            char[] work = new char[10];
            int digit = 0;
            while (value != 0) {
                work[digit] = (char) ((value % 10) + 48);
                value /= 10;
                digit++;
            }
            while (digit < minFieldWidth) {
                buffer.append('0');
                minFieldWidth--;
            }
            while (true) {
                digit--;
                if (digit >= 0) {
                    buffer.append(work[digit]);
                } else {
                    return;
                }
            }
        }
    }

    static void clear() {
        timeZoneDisplayCache.clear();
    }

    static String getTimeZoneDisplay(final TimeZone tz, final boolean daylight, final int style, final Locale locale) {
        TimeZoneDisplayKey key = new TimeZoneDisplayKey(tz, daylight, style, locale);
        return timeZoneDisplayCache.computeIfAbsent(key, new Function() { // from class: org.apache.commons.lang3.time.FastDatePrinter$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String displayName;
                displayName = tz.getDisplayName(daylight, style, locale);
                return displayName;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FastDatePrinter(String pattern, TimeZone timeZone, Locale locale) {
        this.pattern = pattern;
        this.timeZone = timeZone;
        this.locale = LocaleUtils.toLocale(locale);
        init();
    }

    private <B extends Appendable> B applyRules(Calendar calendar, B buf) {
        try {
            for (Rule rule : this.rules) {
                rule.appendTo(buf, calendar);
            }
        } catch (IOException ioe) {
            ExceptionUtils.asRuntimeException(ioe);
        }
        return buf;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public StringBuffer applyRules(Calendar calendar, StringBuffer buf) {
        return (StringBuffer) applyRules(calendar, (Calendar) buf);
    }

    private String applyRulesToString(Calendar c) {
        return ((StringBuilder) applyRules(c, (Calendar) new StringBuilder(this.maxLengthEstimate))).toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FastDatePrinter)) {
            return false;
        }
        FastDatePrinter other = (FastDatePrinter) obj;
        return this.pattern.equals(other.pattern) && this.timeZone.equals(other.timeZone) && this.locale.equals(other.locale);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public String format(Calendar calendar) {
        return ((StringBuilder) format(calendar, (Calendar) new StringBuilder(this.maxLengthEstimate))).toString();
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public <B extends Appendable> B format(Calendar calendar, B b) {
        if (!calendar.getTimeZone().equals(this.timeZone)) {
            calendar = (Calendar) calendar.clone();
            calendar.setTimeZone(this.timeZone);
        }
        return (B) applyRules(calendar, (Calendar) b);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public StringBuffer format(Calendar calendar, StringBuffer buf) {
        return format(calendar.getTime(), buf);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public String format(Date date) {
        Calendar c = newCalendar();
        c.setTime(date);
        return applyRulesToString(c);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public <B extends Appendable> B format(Date date, B b) {
        Calendar newCalendar = newCalendar();
        newCalendar.setTime(date);
        return (B) applyRules(newCalendar, (Calendar) b);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public StringBuffer format(Date date, StringBuffer buf) {
        Calendar c = newCalendar();
        c.setTime(date);
        return (StringBuffer) applyRules(c, (Calendar) buf);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public String format(long millis) {
        Calendar c = newCalendar();
        c.setTimeInMillis(millis);
        return applyRulesToString(c);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public <B extends Appendable> B format(long j, B b) {
        Calendar newCalendar = newCalendar();
        newCalendar.setTimeInMillis(j);
        return (B) applyRules(newCalendar, (Calendar) b);
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public StringBuffer format(long millis, StringBuffer buf) {
        Calendar c = newCalendar();
        c.setTimeInMillis(millis);
        return (StringBuffer) applyRules(c, (Calendar) buf);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String format(Object obj) {
        if (obj instanceof Date) {
            return format((Date) obj);
        }
        if (obj instanceof Calendar) {
            return format((Calendar) obj);
        }
        if (obj instanceof Long) {
            return format(((Long) obj).longValue());
        }
        throw new IllegalArgumentException("Unknown class: " + ClassUtils.getName(obj, "<null>"));
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    @Deprecated
    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
        if (obj instanceof Date) {
            return format((Date) obj, toAppendTo);
        }
        if (obj instanceof Calendar) {
            return format((Calendar) obj, toAppendTo);
        }
        if (obj instanceof Long) {
            return format(((Long) obj).longValue(), toAppendTo);
        }
        throw new IllegalArgumentException("Unknown class: " + ClassUtils.getName(obj, "<null>"));
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public Locale getLocale() {
        return this.locale;
    }

    public int getMaxLengthEstimate() {
        return this.maxLengthEstimate;
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public String getPattern() {
        return this.pattern;
    }

    @Override // org.apache.commons.lang3.time.DatePrinter
    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    public int hashCode() {
        return this.pattern.hashCode() + ((this.timeZone.hashCode() + (this.locale.hashCode() * 13)) * 13);
    }

    private void init() {
        List<Rule> rulesList = parsePattern();
        this.rules = (Rule[]) rulesList.toArray(EMPTY_RULE_ARRAY);
        int len = 0;
        int i = this.rules.length;
        while (true) {
            i--;
            if (i >= 0) {
                len += this.rules[i].estimateLength();
            } else {
                this.maxLengthEstimate = len;
                return;
            }
        }
    }

    private Calendar newCalendar() {
        return Calendar.getInstance(this.timeZone, this.locale);
    }

    protected List<Rule> parsePattern() {
        DateFormatSymbols symbols;
        String[] weekdays;
        String[] shortWeekdays;
        boolean z;
        Rule rule;
        DateFormatSymbols symbols2 = new DateFormatSymbols(this.locale);
        List<Rule> rules = new ArrayList<>();
        String[] ERAs = symbols2.getEras();
        String[] months = symbols2.getMonths();
        String[] shortMonths = symbols2.getShortMonths();
        String[] weekdays2 = symbols2.getWeekdays();
        String[] shortWeekdays2 = symbols2.getShortWeekdays();
        String[] AmPmStrings = symbols2.getAmPmStrings();
        int length = this.pattern.length();
        int[] indexRef = new int[1];
        int i = 0;
        while (i < length) {
            indexRef[0] = i;
            String token = parseToken(this.pattern, indexRef);
            int i2 = indexRef[0];
            int tokenLen = token.length();
            if (tokenLen != 0) {
                char c = token.charAt(0);
                switch (c) {
                    case '\'':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        z = true;
                        String sub = token.substring(1);
                        if (sub.length() == 1) {
                            rule = new CharacterLiteral(sub.charAt(0));
                            break;
                        } else {
                            rule = new StringLiteral(sub);
                            break;
                        }
                    case 'D':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        rule = selectNumberRule(6, tokenLen);
                        z = true;
                        break;
                    case 'E':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        rule = new TextField(7, tokenLen < 4 ? shortWeekdays : weekdays);
                        z = true;
                        break;
                    case 'F':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        rule = selectNumberRule(8, tokenLen);
                        z = true;
                        break;
                    case 'G':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        rule = new TextField(0, ERAs);
                        z = true;
                        break;
                    case 'H':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        rule = selectNumberRule(11, tokenLen);
                        z = true;
                        break;
                    case 'K':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        rule = selectNumberRule(10, tokenLen);
                        z = true;
                        break;
                    case 'L':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        if (tokenLen >= 4) {
                            rule = new TextField(2, CalendarUtils.getInstance(this.locale).getStandaloneLongMonthNames());
                            z = true;
                            break;
                        } else if (tokenLen == 3) {
                            rule = new TextField(2, CalendarUtils.getInstance(this.locale).getStandaloneShortMonthNames());
                            z = true;
                            break;
                        } else if (tokenLen == 2) {
                            rule = TwoDigitMonthField.INSTANCE;
                            z = true;
                            break;
                        } else {
                            rule = UnpaddedMonthField.INSTANCE;
                            z = true;
                            break;
                        }
                    case 'M':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        if (tokenLen >= 4) {
                            rule = new TextField(2, months);
                            z = true;
                            break;
                        } else if (tokenLen == 3) {
                            rule = new TextField(2, shortMonths);
                            z = true;
                            break;
                        } else if (tokenLen == 2) {
                            rule = TwoDigitMonthField.INSTANCE;
                            z = true;
                            break;
                        } else {
                            rule = UnpaddedMonthField.INSTANCE;
                            z = true;
                            break;
                        }
                    case 'S':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        rule = selectNumberRule(14, tokenLen);
                        z = true;
                        break;
                    case 'W':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        rule = selectNumberRule(4, tokenLen);
                        z = true;
                        break;
                    case 'X':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        rule = Iso8601_Rule.getRule(tokenLen);
                        z = true;
                        break;
                    case 'Y':
                    case 'y':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        if (tokenLen != 2) {
                            rule = selectNumberRule(1, Math.max(tokenLen, 4));
                        } else {
                            rule = TwoDigitYearField.INSTANCE;
                        }
                        if (c != 'Y') {
                            z = true;
                            break;
                        } else {
                            rule = new WeekYear((NumberRule) rule);
                            z = true;
                            break;
                        }
                    case 'Z':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        if (tokenLen == 1) {
                            rule = TimeZoneNumberRule.INSTANCE_NO_COLON;
                            z = true;
                            break;
                        } else if (tokenLen == 2) {
                            rule = Iso8601_Rule.ISO8601_HOURS_COLON_MINUTES;
                            z = true;
                            break;
                        } else {
                            rule = TimeZoneNumberRule.INSTANCE_COLON;
                            z = true;
                            break;
                        }
                    case 'a':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        rule = new TextField(9, AmPmStrings);
                        z = true;
                        break;
                    case 'd':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        rule = selectNumberRule(5, tokenLen);
                        z = true;
                        break;
                    case 'h':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        rule = new TwelveHourField(selectNumberRule(10, tokenLen));
                        z = true;
                        break;
                    case 'k':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        rule = new TwentyFourHourField(selectNumberRule(11, tokenLen));
                        z = true;
                        break;
                    case 'm':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        rule = selectNumberRule(12, tokenLen);
                        z = true;
                        break;
                    case 's':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        rule = selectNumberRule(13, tokenLen);
                        z = true;
                        break;
                    case 'u':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        rule = new DayInWeekField(selectNumberRule(7, tokenLen));
                        z = true;
                        break;
                    case 'w':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        rule = selectNumberRule(3, tokenLen);
                        z = true;
                        break;
                    case 'z':
                        symbols = symbols2;
                        weekdays = weekdays2;
                        shortWeekdays = shortWeekdays2;
                        rule = new TimeZoneNameRule(this.timeZone, this.locale, tokenLen >= 4 ? 1 : 0);
                        z = true;
                        break;
                    default:
                        throw new IllegalArgumentException("Illegal pattern component: " + token);
                }
                rules.add(rule);
                i = i2 + 1;
                symbols2 = symbols;
                weekdays2 = weekdays;
                shortWeekdays2 = shortWeekdays;
            } else {
                return rules;
            }
        }
        return rules;
    }

    protected String parseToken(String pattern, int[] indexRef) {
        boolean z;
        StringBuilder buf = new StringBuilder();
        int i = indexRef[0];
        int length = pattern.length();
        char c = pattern.charAt(i);
        if (CharUtils.isAsciiAlpha(c)) {
            buf.append(c);
            while (i + 1 < length) {
                char peek = pattern.charAt(i + 1);
                if (peek != c) {
                    break;
                }
                buf.append(c);
                i++;
            }
        } else {
            buf.append(Chars.QUOTE);
            boolean inLiteral = false;
            while (true) {
                if (i >= length) {
                    break;
                }
                char c2 = pattern.charAt(i);
                if (c2 == '\'') {
                    if (i + 1 < length && pattern.charAt(i + 1) == '\'') {
                        i++;
                        buf.append(c2);
                    } else {
                        if (inLiteral) {
                            z = false;
                        } else {
                            z = true;
                        }
                        inLiteral = z;
                    }
                } else {
                    if (!inLiteral && CharUtils.isAsciiAlpha(c2)) {
                        i--;
                        break;
                    }
                    buf.append(c2);
                }
                i++;
            }
        }
        indexRef[0] = i;
        return buf.toString();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        init();
    }

    protected NumberRule selectNumberRule(int field, int padding) {
        switch (padding) {
            case 1:
                return new UnpaddedNumberField(field);
            case 2:
                return new TwoDigitNumberField(field);
            default:
                return new PaddedNumberField(field, padding);
        }
    }

    public String toString() {
        return "FastDatePrinter[" + this.pattern + CollectionUtils.COMMA + this.locale + CollectionUtils.COMMA + this.timeZone.getID() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }
}
