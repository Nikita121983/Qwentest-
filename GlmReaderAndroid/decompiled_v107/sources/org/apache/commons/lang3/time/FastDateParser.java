package org.apache.commons.lang3.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.LocaleUtils;

/* loaded from: classes9.dex */
public class FastDateParser implements DateParser, Serializable {
    private static final long serialVersionUID = 3;
    private final int century;
    private final Locale locale;
    private final String pattern;
    private transient List<StrategyAndWidth> patterns;
    private final int startYear;
    private final TimeZone timeZone;
    static final Locale JAPANESE_IMPERIAL = new Locale("ja", "JP", "JP");
    private static final Comparator<String> LONGER_FIRST_LOWERCASE = Comparator.reverseOrder();
    private static final ConcurrentMap<Locale, Strategy>[] CACHES = new ConcurrentMap[17];
    private static final Strategy ABBREVIATED_YEAR_STRATEGY = new NumberStrategy(1) { // from class: org.apache.commons.lang3.time.FastDateParser.1
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        int modify(FastDateParser parser, int iValue) {
            return iValue < 100 ? parser.adjustYear(iValue) : iValue;
        }
    };
    private static final Strategy NUMBER_MONTH_STRATEGY = new NumberStrategy(2) { // from class: org.apache.commons.lang3.time.FastDateParser.2
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        int modify(FastDateParser parser, int iValue) {
            return iValue - 1;
        }
    };
    private static final Strategy LITERAL_YEAR_STRATEGY = new NumberStrategy(1);
    private static final Strategy WEEK_OF_YEAR_STRATEGY = new NumberStrategy(3);
    private static final Strategy WEEK_OF_MONTH_STRATEGY = new NumberStrategy(4);
    private static final Strategy DAY_OF_YEAR_STRATEGY = new NumberStrategy(6);
    private static final Strategy DAY_OF_MONTH_STRATEGY = new NumberStrategy(5);
    private static final Strategy DAY_OF_WEEK_STRATEGY = new NumberStrategy(7) { // from class: org.apache.commons.lang3.time.FastDateParser.3
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        int modify(FastDateParser parser, int iValue) {
            if (iValue == 7) {
                return 1;
            }
            return iValue + 1;
        }
    };
    private static final Strategy DAY_OF_WEEK_IN_MONTH_STRATEGY = new NumberStrategy(8);
    private static final Strategy HOUR_OF_DAY_STRATEGY = new NumberStrategy(11);
    private static final Strategy HOUR24_OF_DAY_STRATEGY = new NumberStrategy(11) { // from class: org.apache.commons.lang3.time.FastDateParser.4
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        int modify(FastDateParser parser, int iValue) {
            if (iValue == 24) {
                return 0;
            }
            return iValue;
        }
    };
    private static final Strategy HOUR12_STRATEGY = new NumberStrategy(10) { // from class: org.apache.commons.lang3.time.FastDateParser.5
        @Override // org.apache.commons.lang3.time.FastDateParser.NumberStrategy
        int modify(FastDateParser parser, int iValue) {
            if (iValue == 12) {
                return 0;
            }
            return iValue;
        }
    };
    private static final Strategy HOUR_STRATEGY = new NumberStrategy(10);
    private static final Strategy MINUTE_STRATEGY = new NumberStrategy(12);
    private static final Strategy SECOND_STRATEGY = new NumberStrategy(13);
    private static final Strategy MILLISECOND_STRATEGY = new NumberStrategy(14);

    /* loaded from: classes9.dex */
    private static final class CaseInsensitiveTextStrategy extends PatternStrategy {
        private final int field;
        private final Map<String, Integer> lKeyValues;
        private final Locale locale;

        CaseInsensitiveTextStrategy(int field, Calendar definingCalendar, Locale locale) {
            super();
            this.field = field;
            this.locale = LocaleUtils.toLocale(locale);
            StringBuilder regex = new StringBuilder();
            regex.append("((?iu)");
            this.lKeyValues = FastDateParser.appendDisplayNames(definingCalendar, locale, field, regex);
            regex.setLength(regex.length() - 1);
            regex.append(")");
            createPattern(regex);
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.PatternStrategy
        void setCalendar(FastDateParser parser, Calendar calendar, String value) {
            String lowerCase = value.toLowerCase(this.locale);
            Integer iVal = this.lKeyValues.get(lowerCase);
            if (iVal == null) {
                iVal = this.lKeyValues.get(lowerCase + '.');
            }
            if (9 != this.field || iVal.intValue() <= 1) {
                calendar.set(this.field, iVal.intValue());
            }
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.PatternStrategy
        public String toString() {
            return "CaseInsensitiveTextStrategy [field=" + this.field + ", locale=" + this.locale + ", lKeyValues=" + this.lKeyValues + ", pattern=" + this.pattern + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class CopyQuotedStrategy extends Strategy {
        private final String formatField;

        CopyQuotedStrategy(String formatField) {
            super();
            this.formatField = formatField;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean isNumber() {
            return false;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean parse(FastDateParser parser, Calendar calendar, String source, ParsePosition pos, int maxWidth) {
            for (int idx = 0; idx < this.formatField.length(); idx++) {
                int sIdx = pos.getIndex() + idx;
                if (sIdx == source.length()) {
                    pos.setErrorIndex(sIdx);
                    return false;
                }
                if (this.formatField.charAt(idx) != source.charAt(sIdx)) {
                    pos.setErrorIndex(sIdx);
                    return false;
                }
            }
            pos.setIndex(this.formatField.length() + pos.getIndex());
            return true;
        }

        public String toString() {
            return "CopyQuotedStrategy [formatField=" + this.formatField + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class ISO8601TimeZoneStrategy extends PatternStrategy {
        private static final Strategy ISO_8601_1_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}))");
        private static final Strategy ISO_8601_2_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}\\d{2}))");
        private static final Strategy ISO_8601_3_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}(?::)\\d{2}))");

        static Strategy getStrategy(int tokenLen) {
            switch (tokenLen) {
                case 1:
                    return ISO_8601_1_STRATEGY;
                case 2:
                    return ISO_8601_2_STRATEGY;
                case 3:
                    return ISO_8601_3_STRATEGY;
                default:
                    throw new IllegalArgumentException("invalid number of X");
            }
        }

        ISO8601TimeZoneStrategy(String pattern) {
            super();
            createPattern(pattern);
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.PatternStrategy
        void setCalendar(FastDateParser parser, Calendar calendar, String value) {
            calendar.setTimeZone(FastTimeZone.getGmtTimeZone(value));
        }
    }

    /* loaded from: classes9.dex */
    private static class NumberStrategy extends Strategy {
        private final int field;

        NumberStrategy(int field) {
            super();
            this.field = field;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean isNumber() {
            return true;
        }

        int modify(FastDateParser parser, int iValue) {
            return iValue;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean parse(FastDateParser parser, Calendar calendar, String source, ParsePosition pos, int maxWidth) {
            int idx = pos.getIndex();
            int last = source.length();
            if (maxWidth == 0) {
                while (idx < last) {
                    char c = source.charAt(idx);
                    if (!Character.isWhitespace(c)) {
                        break;
                    }
                    idx++;
                }
                pos.setIndex(idx);
            } else {
                int end = idx + maxWidth;
                if (last > end) {
                    last = end;
                }
            }
            while (idx < last) {
                char c2 = source.charAt(idx);
                if (!Character.isDigit(c2)) {
                    break;
                }
                idx++;
            }
            if (pos.getIndex() == idx) {
                pos.setErrorIndex(idx);
                return false;
            }
            int value = Integer.parseInt(source.substring(pos.getIndex(), idx));
            pos.setIndex(idx);
            calendar.set(this.field, modify(parser, value));
            return true;
        }

        public String toString() {
            return "NumberStrategy [field=" + this.field + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
        }
    }

    /* loaded from: classes9.dex */
    private static abstract class PatternStrategy extends Strategy {
        Pattern pattern;

        abstract void setCalendar(FastDateParser fastDateParser, Calendar calendar, String str);

        private PatternStrategy() {
            super();
        }

        void createPattern(String regex) {
            this.pattern = Pattern.compile(regex);
        }

        void createPattern(StringBuilder regex) {
            createPattern(regex.toString());
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean isNumber() {
            return false;
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.Strategy
        boolean parse(FastDateParser parser, Calendar calendar, String source, ParsePosition pos, int maxWidth) {
            Matcher matcher = this.pattern.matcher(source.substring(pos.getIndex()));
            if (!matcher.lookingAt()) {
                pos.setErrorIndex(pos.getIndex());
                return false;
            }
            pos.setIndex(pos.getIndex() + matcher.end(1));
            setCalendar(parser, calendar, matcher.group(1));
            return true;
        }

        public String toString() {
            return getClass().getSimpleName() + " [pattern=" + this.pattern + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static abstract class Strategy {
        abstract boolean parse(FastDateParser fastDateParser, Calendar calendar, String str, ParsePosition parsePosition, int i);

        private Strategy() {
        }

        boolean isNumber() {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class StrategyAndWidth {
        final Strategy strategy;
        final int width;

        StrategyAndWidth(Strategy strategy, int width) {
            this.strategy = (Strategy) Objects.requireNonNull(strategy, "strategy");
            this.width = width;
        }

        int getMaxWidth(ListIterator<StrategyAndWidth> lt) {
            if (!this.strategy.isNumber() || !lt.hasNext()) {
                return 0;
            }
            Strategy nextStrategy = lt.next().strategy;
            lt.previous();
            if (nextStrategy.isNumber()) {
                return this.width;
            }
            return 0;
        }

        public String toString() {
            return "StrategyAndWidth [strategy=" + this.strategy + ", width=" + this.width + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public final class StrategyParser {
        private int currentIdx;
        private final Calendar definingCalendar;

        StrategyParser(Calendar definingCalendar) {
            this.definingCalendar = (Calendar) Objects.requireNonNull(definingCalendar, "definingCalendar");
        }

        StrategyAndWidth getNextStrategy() {
            if (this.currentIdx < FastDateParser.this.pattern.length()) {
                char c = FastDateParser.this.pattern.charAt(this.currentIdx);
                if (CharUtils.isAsciiAlpha(c)) {
                    return letterPattern(c);
                }
                return literal();
            }
            return null;
        }

        private StrategyAndWidth letterPattern(char c) {
            int begin = this.currentIdx;
            do {
                int i = this.currentIdx + 1;
                this.currentIdx = i;
                if (i >= FastDateParser.this.pattern.length()) {
                    break;
                }
            } while (FastDateParser.this.pattern.charAt(this.currentIdx) == c);
            int width = this.currentIdx - begin;
            return new StrategyAndWidth(FastDateParser.this.getStrategy(c, width, this.definingCalendar), width);
        }

        private StrategyAndWidth literal() {
            boolean activeQuote = false;
            StringBuilder sb = new StringBuilder();
            while (this.currentIdx < FastDateParser.this.pattern.length()) {
                char c = FastDateParser.this.pattern.charAt(this.currentIdx);
                if (!activeQuote && CharUtils.isAsciiAlpha(c)) {
                    break;
                }
                if (c == '\'') {
                    int i = this.currentIdx + 1;
                    this.currentIdx = i;
                    if (i == FastDateParser.this.pattern.length() || FastDateParser.this.pattern.charAt(this.currentIdx) != '\'') {
                        activeQuote = activeQuote ? false : true;
                    }
                }
                this.currentIdx++;
                sb.append(c);
            }
            if (activeQuote) {
                throw new IllegalArgumentException("Unterminated quote");
            }
            String formatField = sb.toString();
            return new StrategyAndWidth(new CopyQuotedStrategy(formatField), formatField.length());
        }
    }

    /* loaded from: classes9.dex */
    static class TimeZoneStrategy extends PatternStrategy {
        private static final String GMT_OPTION = "GMT[+-]\\d{1,2}:\\d{2}";
        private static final int ID = 0;
        private static final String RFC_822_TIME_ZONE = "[+-]\\d{4}";
        private final Locale locale;
        private final Map<String, TzInfo> tzNames;

        /* loaded from: classes9.dex */
        private static final class TzInfo {
            final int dstOffset;
            final TimeZone zone;

            TzInfo(TimeZone tz, boolean useDst) {
                this.zone = tz;
                this.dstOffset = useDst ? tz.getDSTSavings() : 0;
            }

            public String toString() {
                return "TzInfo [zone=" + this.zone + ", dstOffset=" + this.dstOffset + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x005b, code lost:
        
            r12 = r7[r11];
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x005d, code lost:
        
            if (r12 == null) goto L37;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0063, code lost:
        
            if (r1.add(r12) == false) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0065, code lost:
        
            r14.tzNames.put(r12, r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x006a, code lost:
        
            r11 = r11 + 1;
         */
        /* JADX WARN: Removed duplicated region for block: B:9:0x004e  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        TimeZoneStrategy(java.util.Locale r15) {
            /*
                r14 = this;
                r0 = 0
                r14.<init>()
                java.util.TreeMap r0 = new java.util.TreeMap
                java.util.Comparator r1 = java.lang.String.CASE_INSENSITIVE_ORDER
                r0.<init>(r1)
                r14.tzNames = r0
                java.util.Locale r0 = org.apache.commons.lang3.LocaleUtils.toLocale(r15)
                r14.locale = r0
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "((?iu)[+-]\\d{4}|GMT[+-]\\d{1,2}:\\d{2}"
                r0.append(r1)
                java.util.TreeSet r1 = new java.util.TreeSet
                java.util.Comparator r2 = org.apache.commons.lang3.time.FastDateParser.access$500()
                r1.<init>(r2)
                java.text.DateFormatSymbols r2 = java.text.DateFormatSymbols.getInstance(r15)
                java.lang.String[][] r2 = r2.getZoneStrings()
                int r3 = r2.length
                r4 = 0
                r5 = r4
            L31:
                java.lang.String r6 = "GMT"
                if (r5 >= r3) goto L70
                r7 = r2[r5]
                r8 = r7[r4]
                boolean r6 = r8.equalsIgnoreCase(r6)
                if (r6 == 0) goto L40
                goto L6d
            L40:
                java.util.TimeZone r6 = java.util.TimeZone.getTimeZone(r8)
                org.apache.commons.lang3.time.FastDateParser$TimeZoneStrategy$TzInfo r9 = new org.apache.commons.lang3.time.FastDateParser$TimeZoneStrategy$TzInfo
                r9.<init>(r6, r4)
                r10 = r9
                r11 = 1
            L4b:
                int r12 = r7.length
                if (r11 >= r12) goto L6d
                switch(r11) {
                    case 3: goto L54;
                    case 4: goto L51;
                    case 5: goto L52;
                    default: goto L51;
                }
            L51:
                goto L5b
            L52:
                r10 = r9
                goto L5b
            L54:
                org.apache.commons.lang3.time.FastDateParser$TimeZoneStrategy$TzInfo r12 = new org.apache.commons.lang3.time.FastDateParser$TimeZoneStrategy$TzInfo
                r13 = 1
                r12.<init>(r6, r13)
                r10 = r12
            L5b:
                r12 = r7[r11]
                if (r12 == 0) goto L6a
                boolean r13 = r1.add(r12)
                if (r13 == 0) goto L6a
                java.util.Map<java.lang.String, org.apache.commons.lang3.time.FastDateParser$TimeZoneStrategy$TzInfo> r13 = r14.tzNames
                r13.put(r12, r10)
            L6a:
                int r11 = r11 + 1
                goto L4b
            L6d:
                int r5 = r5 + 1
                goto L31
            L70:
                java.lang.String[] r3 = java.util.TimeZone.getAvailableIDs()
                java.lang.Object[] r3 = org.apache.commons.lang3.ArraySorter.sort(r3)
                java.lang.String[] r3 = (java.lang.String[]) r3
                int r5 = r3.length
            L7b:
                if (r4 >= r5) goto La5
                r7 = r3[r4]
                boolean r8 = r7.equalsIgnoreCase(r6)
                if (r8 == 0) goto L86
                goto La2
            L86:
                java.util.TimeZone r8 = java.util.TimeZone.getTimeZone(r7)
                java.lang.String r9 = r8.getDisplayName(r15)
                boolean r10 = r1.add(r9)
                if (r10 == 0) goto La2
                java.util.Map<java.lang.String, org.apache.commons.lang3.time.FastDateParser$TimeZoneStrategy$TzInfo> r10 = r14.tzNames
                org.apache.commons.lang3.time.FastDateParser$TimeZoneStrategy$TzInfo r11 = new org.apache.commons.lang3.time.FastDateParser$TimeZoneStrategy$TzInfo
                boolean r12 = r8.observesDaylightTime()
                r11.<init>(r8, r12)
                r10.put(r9, r11)
            La2:
                int r4 = r4 + 1
                goto L7b
            La5:
                org.apache.commons.lang3.time.FastDateParser$TimeZoneStrategy$$ExternalSyntheticLambda0 r3 = new org.apache.commons.lang3.time.FastDateParser$TimeZoneStrategy$$ExternalSyntheticLambda0
                r3.<init>()
                r1.forEach(r3)
                java.lang.String r3 = ")"
                r0.append(r3)
                r14.createPattern(r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.time.FastDateParser.TimeZoneStrategy.<init>(java.util.Locale):void");
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.PatternStrategy
        void setCalendar(FastDateParser parser, Calendar calendar, String timeZone) {
            TimeZone tz = FastTimeZone.getGmtTimeZone(timeZone);
            if (tz != null) {
                calendar.setTimeZone(tz);
                return;
            }
            TzInfo tzInfo = this.tzNames.get(timeZone);
            if (tzInfo == null && (tzInfo = this.tzNames.get(timeZone + '.')) == null) {
                char[] charArray = timeZone.toCharArray();
                throw new IllegalStateException(String.format("Can't find time zone '%s' (%d %s) in %s", timeZone, Integer.valueOf(charArray.length), Arrays.toString(charArray), new TreeSet(this.tzNames.keySet())));
            }
            calendar.set(16, tzInfo.dstOffset);
            calendar.set(15, tzInfo.zone.getRawOffset());
        }

        @Override // org.apache.commons.lang3.time.FastDateParser.PatternStrategy
        public String toString() {
            return "TimeZoneStrategy [locale=" + this.locale + ", tzNames=" + this.tzNames + ", pattern=" + this.pattern + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<String, Integer> appendDisplayNames(Calendar calendar, Locale locale, int field, final StringBuilder regex) {
        Objects.requireNonNull(calendar, "calendar");
        final Map<String, Integer> values = new HashMap<>();
        final Locale actualLocale = LocaleUtils.toLocale(locale);
        Map<String, Integer> displayNames = calendar.getDisplayNames(field, 0, actualLocale);
        final TreeSet<String> sorted = new TreeSet<>(LONGER_FIRST_LOWERCASE);
        displayNames.forEach(new BiConsumer() { // from class: org.apache.commons.lang3.time.FastDateParser$$ExternalSyntheticLambda3
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                FastDateParser.lambda$appendDisplayNames$0(actualLocale, sorted, values, (String) obj, (Integer) obj2);
            }
        });
        sorted.forEach(new Consumer() { // from class: org.apache.commons.lang3.time.FastDateParser$$ExternalSyntheticLambda4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                FastDateParser.simpleQuote(regex, (String) obj).append('|');
            }
        });
        return values;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$appendDisplayNames$0(Locale actualLocale, TreeSet sorted, Map values, String k, Integer v) {
        String keyLc = k.toLowerCase(actualLocale);
        if (sorted.add(keyLc)) {
            values.put(keyLc, v);
        }
    }

    static void clear() {
        Stream.of((Object[]) CACHES).filter(new Predicate() { // from class: org.apache.commons.lang3.time.FastDateParser$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean nonNull;
                nonNull = Objects.nonNull((ConcurrentMap) obj);
                return nonNull;
            }
        }).forEach(new Consumer() { // from class: org.apache.commons.lang3.time.FastDateParser$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((ConcurrentMap) obj).clear();
            }
        });
    }

    private static ConcurrentMap<Locale, Strategy> getCache(int field) {
        ConcurrentMap<Locale, Strategy> concurrentMap;
        synchronized (CACHES) {
            if (CACHES[field] == null) {
                CACHES[field] = new ConcurrentHashMap(3);
            }
            concurrentMap = CACHES[field];
        }
        return concurrentMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static StringBuilder simpleQuote(StringBuilder sb, String value) {
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            switch (c) {
                case '$':
                case '(':
                case ')':
                case '*':
                case '+':
                case '.':
                case '?':
                case '[':
                case '\\':
                case '^':
                case '{':
                case '|':
                    sb.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                    break;
            }
            sb.append(c);
        }
        int i2 = sb.length();
        if (sb.charAt(i2 - 1) == '.') {
            sb.append('?');
        }
        return sb;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FastDateParser(String pattern, TimeZone timeZone, Locale locale) {
        this(pattern, timeZone, locale, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FastDateParser(String pattern, TimeZone timeZone, Locale locale, Date centuryStart) {
        int centuryStartYear;
        this.pattern = (String) Objects.requireNonNull(pattern, "pattern");
        this.timeZone = (TimeZone) Objects.requireNonNull(timeZone, "timeZone");
        this.locale = LocaleUtils.toLocale(locale);
        Calendar definingCalendar = Calendar.getInstance(timeZone, this.locale);
        if (centuryStart != null) {
            definingCalendar.setTime(centuryStart);
            centuryStartYear = definingCalendar.get(1);
        } else if (this.locale.equals(JAPANESE_IMPERIAL)) {
            centuryStartYear = 0;
        } else {
            definingCalendar.setTime(new Date());
            centuryStartYear = definingCalendar.get(1) - 80;
        }
        this.century = (centuryStartYear / 100) * 100;
        this.startYear = centuryStartYear - this.century;
        init(definingCalendar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int adjustYear(int twoDigitYear) {
        int trial = this.century + twoDigitYear;
        return twoDigitYear >= this.startYear ? trial : trial + 100;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FastDateParser)) {
            return false;
        }
        FastDateParser other = (FastDateParser) obj;
        return this.pattern.equals(other.pattern) && this.timeZone.equals(other.timeZone) && this.locale.equals(other.locale);
    }

    @Override // org.apache.commons.lang3.time.DateParser, org.apache.commons.lang3.time.DatePrinter
    public Locale getLocale() {
        return this.locale;
    }

    private Strategy getLocaleSpecificStrategy(final int field, final Calendar definingCalendar) {
        ConcurrentMap<Locale, Strategy> cache = getCache(field);
        return cache.computeIfAbsent(this.locale, new Function() { // from class: org.apache.commons.lang3.time.FastDateParser$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FastDateParser.this.m2201xdc739664(field, definingCalendar, (Locale) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getLocaleSpecificStrategy$2$org-apache-commons-lang3-time-FastDateParser, reason: not valid java name */
    public /* synthetic */ Strategy m2201xdc739664(int field, Calendar definingCalendar, Locale k) {
        return field == 15 ? new TimeZoneStrategy(this.locale) : new CaseInsensitiveTextStrategy(field, definingCalendar, this.locale);
    }

    @Override // org.apache.commons.lang3.time.DateParser, org.apache.commons.lang3.time.DatePrinter
    public String getPattern() {
        return this.pattern;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Strategy getStrategy(char f, int width, Calendar definingCalendar) {
        switch (f) {
            case 'D':
                return DAY_OF_YEAR_STRATEGY;
            case 'E':
                return getLocaleSpecificStrategy(7, definingCalendar);
            case 'F':
                return DAY_OF_WEEK_IN_MONTH_STRATEGY;
            case 'G':
                return getLocaleSpecificStrategy(0, definingCalendar);
            case 'H':
                return HOUR_OF_DAY_STRATEGY;
            case 'K':
                return HOUR_STRATEGY;
            case 'L':
            case 'M':
                return width >= 3 ? getLocaleSpecificStrategy(2, definingCalendar) : NUMBER_MONTH_STRATEGY;
            case 'S':
                return MILLISECOND_STRATEGY;
            case 'W':
                return WEEK_OF_MONTH_STRATEGY;
            case 'X':
                return ISO8601TimeZoneStrategy.getStrategy(width);
            case 'Y':
            case 'y':
                return width > 2 ? LITERAL_YEAR_STRATEGY : ABBREVIATED_YEAR_STRATEGY;
            case 'Z':
                if (width == 2) {
                    return ISO8601TimeZoneStrategy.ISO_8601_3_STRATEGY;
                }
                break;
            case 'a':
                return getLocaleSpecificStrategy(9, definingCalendar);
            case 'd':
                return DAY_OF_MONTH_STRATEGY;
            case 'h':
                return HOUR12_STRATEGY;
            case 'k':
                return HOUR24_OF_DAY_STRATEGY;
            case 'm':
                return MINUTE_STRATEGY;
            case 's':
                return SECOND_STRATEGY;
            case 'u':
                return DAY_OF_WEEK_STRATEGY;
            case 'w':
                return WEEK_OF_YEAR_STRATEGY;
            case 'z':
                break;
            default:
                throw new IllegalArgumentException("Format '" + f + "' not supported");
        }
        return getLocaleSpecificStrategy(15, definingCalendar);
    }

    @Override // org.apache.commons.lang3.time.DateParser, org.apache.commons.lang3.time.DatePrinter
    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    public int hashCode() {
        return this.pattern.hashCode() + ((this.timeZone.hashCode() + (this.locale.hashCode() * 13)) * 13);
    }

    private void init(Calendar definingCalendar) {
        this.patterns = new ArrayList();
        StrategyParser strategyParser = new StrategyParser(definingCalendar);
        while (true) {
            StrategyAndWidth field = strategyParser.getNextStrategy();
            if (field != null) {
                this.patterns.add(field);
            } else {
                return;
            }
        }
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public Date parse(String source) throws ParseException {
        ParsePosition pp = new ParsePosition(0);
        Date date = parse(source, pp);
        if (date == null) {
            if (this.locale.equals(JAPANESE_IMPERIAL)) {
                throw new ParseException("(The " + this.locale + " locale does not support dates before 1868 AD)\nUnparseable date: \"" + source, pp.getErrorIndex());
            }
            throw new ParseException("Unparseable date: " + source, pp.getErrorIndex());
        }
        return date;
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public Date parse(String source, ParsePosition pos) {
        Calendar cal = Calendar.getInstance(this.timeZone, this.locale);
        cal.clear();
        if (parse(source, pos, cal)) {
            return cal.getTime();
        }
        return null;
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public boolean parse(String source, ParsePosition pos, Calendar calendar) {
        ListIterator<StrategyAndWidth> lt = this.patterns.listIterator();
        while (lt.hasNext()) {
            StrategyAndWidth strategyAndWidth = lt.next();
            int maxWidth = strategyAndWidth.getMaxWidth(lt);
            String source2 = source;
            ParsePosition pos2 = pos;
            Calendar calendar2 = calendar;
            if (!strategyAndWidth.strategy.parse(this, calendar2, source2, pos2, maxWidth)) {
                return false;
            }
            calendar = calendar2;
            source = source2;
            pos = pos2;
        }
        return true;
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public Object parseObject(String source) throws ParseException {
        return parse(source);
    }

    @Override // org.apache.commons.lang3.time.DateParser
    public Object parseObject(String source, ParsePosition pos) {
        return parse(source, pos);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        Calendar definingCalendar = Calendar.getInstance(this.timeZone, this.locale);
        init(definingCalendar);
    }

    public String toString() {
        return "FastDateParser[" + this.pattern + ", " + this.locale + ", " + this.timeZone.getID() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }

    public String toStringAll() {
        return "FastDateParser [pattern=" + this.pattern + ", timeZone=" + this.timeZone + ", locale=" + this.locale + ", century=" + this.century + ", startYear=" + this.startYear + ", patterns=" + this.patterns + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }
}
