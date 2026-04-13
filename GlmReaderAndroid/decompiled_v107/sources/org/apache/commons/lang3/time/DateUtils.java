package org.apache.commons.lang3.time;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.xmlbeans.XmlErrorCodes;

/* loaded from: classes9.dex */
public class DateUtils {
    public static final long MILLIS_PER_DAY = 86400000;
    public static final long MILLIS_PER_HOUR = 3600000;
    public static final long MILLIS_PER_MINUTE = 60000;
    public static final long MILLIS_PER_SECOND = 1000;
    public static final int RANGE_MONTH_MONDAY = 6;
    public static final int RANGE_MONTH_SUNDAY = 5;
    public static final int RANGE_WEEK_CENTER = 4;
    public static final int RANGE_WEEK_MONDAY = 2;
    public static final int RANGE_WEEK_RELATIVE = 3;
    public static final int RANGE_WEEK_SUNDAY = 1;
    public static final int SEMI_MONTH = 1001;
    private static final int[][] fields = {new int[]{14}, new int[]{13}, new int[]{12}, new int[]{11, 10}, new int[]{5, 5, 9}, new int[]{2, 1001}, new int[]{1}, new int[]{0}};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public enum ModifyType {
        TRUNCATE,
        ROUND,
        CEILING
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static final class DateIterator implements Iterator<Calendar> {
        private final Calendar endFinal;
        private final Calendar spot;

        DateIterator(Calendar startFinal, Calendar endFinal) {
            this.endFinal = endFinal;
            this.spot = startFinal;
            this.spot.add(5, -1);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.spot.before(this.endFinal);
        }

        @Override // java.util.Iterator
        public Calendar next() {
            if (this.spot.equals(this.endFinal)) {
                throw new NoSuchElementException();
            }
            this.spot.add(5, 1);
            return (Calendar) this.spot.clone();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static Date add(Date date, int calendarField, int amount) {
        validateDateNotNull(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    public static Date addDays(Date date, int amount) {
        return add(date, 5, amount);
    }

    public static Date addHours(Date date, int amount) {
        return add(date, 11, amount);
    }

    public static Date addMilliseconds(Date date, int amount) {
        return add(date, 14, amount);
    }

    public static Date addMinutes(Date date, int amount) {
        return add(date, 12, amount);
    }

    public static Date addMonths(Date date, int amount) {
        return add(date, 2, amount);
    }

    public static Date addSeconds(Date date, int amount) {
        return add(date, 13, amount);
    }

    public static Date addWeeks(Date date, int amount) {
        return add(date, 3, amount);
    }

    public static Date addYears(Date date, int amount) {
        return add(date, 1, amount);
    }

    public static Calendar ceiling(Calendar calendar, int field) {
        Objects.requireNonNull(calendar, "calendar");
        return modify((Calendar) calendar.clone(), field, ModifyType.CEILING);
    }

    public static Date ceiling(Date date, int field) {
        return modify(toCalendar(date), field, ModifyType.CEILING).getTime();
    }

    public static Date ceiling(Object date, int field) {
        Objects.requireNonNull(date, XmlErrorCodes.DATE);
        if (date instanceof Date) {
            return ceiling((Date) date, field);
        }
        if (date instanceof Calendar) {
            return ceiling((Calendar) date, field).getTime();
        }
        throw new ClassCastException("Could not find ceiling of for type: " + date.getClass());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0030. Please report as an issue. */
    private static long getFragment(Calendar calendar, int fragment, TimeUnit unit) {
        Objects.requireNonNull(calendar, "calendar");
        long result = 0;
        int offset = unit == TimeUnit.DAYS ? 0 : 1;
        switch (fragment) {
            case 1:
                result = 0 + unit.convert(calendar.get(6) - offset, TimeUnit.DAYS);
                break;
            case 2:
                result = 0 + unit.convert(calendar.get(5) - offset, TimeUnit.DAYS);
                break;
        }
        switch (fragment) {
            case 1:
            case 2:
            case 5:
            case 6:
                result += unit.convert(calendar.get(11), TimeUnit.HOURS);
                result += unit.convert(calendar.get(12), TimeUnit.MINUTES);
                result += unit.convert(calendar.get(13), TimeUnit.SECONDS);
                return result + unit.convert(calendar.get(14), TimeUnit.MILLISECONDS);
            case 3:
            case 4:
            case 7:
            case 8:
            case 9:
            case 10:
            default:
                throw new IllegalArgumentException("The fragment " + fragment + " is not supported");
            case 11:
                result += unit.convert(calendar.get(12), TimeUnit.MINUTES);
                result += unit.convert(calendar.get(13), TimeUnit.SECONDS);
                return result + unit.convert(calendar.get(14), TimeUnit.MILLISECONDS);
            case 12:
                result += unit.convert(calendar.get(13), TimeUnit.SECONDS);
                return result + unit.convert(calendar.get(14), TimeUnit.MILLISECONDS);
            case 13:
                return result + unit.convert(calendar.get(14), TimeUnit.MILLISECONDS);
            case 14:
                return result;
        }
    }

    private static long getFragment(Date date, int fragment, TimeUnit unit) {
        validateDateNotNull(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFragment(calendar, fragment, unit);
    }

    public static long getFragmentInDays(Calendar calendar, int fragment) {
        return getFragment(calendar, fragment, TimeUnit.DAYS);
    }

    public static long getFragmentInDays(Date date, int fragment) {
        return getFragment(date, fragment, TimeUnit.DAYS);
    }

    public static long getFragmentInHours(Calendar calendar, int fragment) {
        return getFragment(calendar, fragment, TimeUnit.HOURS);
    }

    public static long getFragmentInHours(Date date, int fragment) {
        return getFragment(date, fragment, TimeUnit.HOURS);
    }

    public static long getFragmentInMilliseconds(Calendar calendar, int fragment) {
        return getFragment(calendar, fragment, TimeUnit.MILLISECONDS);
    }

    public static long getFragmentInMilliseconds(Date date, int fragment) {
        return getFragment(date, fragment, TimeUnit.MILLISECONDS);
    }

    public static long getFragmentInMinutes(Calendar calendar, int fragment) {
        return getFragment(calendar, fragment, TimeUnit.MINUTES);
    }

    public static long getFragmentInMinutes(Date date, int fragment) {
        return getFragment(date, fragment, TimeUnit.MINUTES);
    }

    public static long getFragmentInSeconds(Calendar calendar, int fragment) {
        return getFragment(calendar, fragment, TimeUnit.SECONDS);
    }

    public static long getFragmentInSeconds(Date date, int fragment) {
        return getFragment(date, fragment, TimeUnit.SECONDS);
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        Objects.requireNonNull(cal1, "cal1");
        Objects.requireNonNull(cal2, "cal2");
        if (cal1.get(0) != cal2.get(0) || cal1.get(1) != cal2.get(1) || cal1.get(6) != cal2.get(6)) {
            return false;
        }
        return true;
    }

    public static boolean isSameDay(Date date1, Date date2) {
        return isSameDay(toCalendar(date1), toCalendar(date2));
    }

    public static boolean isSameInstant(Calendar cal1, Calendar cal2) {
        Objects.requireNonNull(cal1, "cal1");
        Objects.requireNonNull(cal2, "cal2");
        return cal1.getTime().getTime() == cal2.getTime().getTime();
    }

    public static boolean isSameInstant(Date date1, Date date2) {
        Objects.requireNonNull(date1, "date1");
        Objects.requireNonNull(date2, "date2");
        return date1.getTime() == date2.getTime();
    }

    public static boolean isSameLocalTime(Calendar cal1, Calendar cal2) {
        Objects.requireNonNull(cal1, "cal1");
        Objects.requireNonNull(cal2, "cal2");
        return cal1.get(14) == cal2.get(14) && cal1.get(13) == cal2.get(13) && cal1.get(12) == cal2.get(12) && cal1.get(11) == cal2.get(11) && cal1.get(6) == cal2.get(6) && cal1.get(1) == cal2.get(1) && cal1.get(0) == cal2.get(0) && cal1.getClass() == cal2.getClass();
    }

    public static Iterator<Calendar> iterator(Calendar calendar, int rangeStyle) {
        Calendar start;
        Calendar end;
        Objects.requireNonNull(calendar, "calendar");
        int startCutoff = 1;
        int endCutoff = 7;
        switch (rangeStyle) {
            case 1:
            case 2:
            case 3:
            case 4:
                start = truncate(calendar, 5);
                end = truncate(calendar, 5);
                switch (rangeStyle) {
                    case 2:
                        startCutoff = 2;
                        endCutoff = 1;
                        break;
                    case 3:
                        startCutoff = calendar.get(7);
                        endCutoff = startCutoff - 1;
                        break;
                    case 4:
                        startCutoff = calendar.get(7) - 3;
                        endCutoff = calendar.get(7) + 3;
                        break;
                }
            case 5:
            case 6:
                start = truncate(calendar, 2);
                end = (Calendar) start.clone();
                end.add(2, 1);
                end.add(5, -1);
                if (rangeStyle == 6) {
                    startCutoff = 2;
                    endCutoff = 1;
                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("The range style " + rangeStyle + " is not valid.");
        }
        if (startCutoff < 1) {
            startCutoff += 7;
        }
        if (startCutoff > 7) {
            startCutoff -= 7;
        }
        if (endCutoff < 1) {
            endCutoff += 7;
        }
        if (endCutoff > 7) {
            endCutoff -= 7;
        }
        while (start.get(7) != startCutoff) {
            start.add(5, -1);
        }
        while (end.get(7) != endCutoff) {
            end.add(5, 1);
        }
        return new DateIterator(start, end);
    }

    public static Iterator<Calendar> iterator(Date focus, int rangeStyle) {
        return iterator(toCalendar(focus), rangeStyle);
    }

    public static Iterator<?> iterator(Object calendar, int rangeStyle) {
        Objects.requireNonNull(calendar, "calendar");
        if (calendar instanceof Date) {
            return iterator((Date) calendar, rangeStyle);
        }
        if (calendar instanceof Calendar) {
            return iterator((Calendar) calendar, rangeStyle);
        }
        throw new ClassCastException("Could not iterate based on " + calendar);
    }

    private static Calendar modify(Calendar val, int field, ModifyType modType) {
        boolean z;
        ModifyType modifyType = modType;
        if (val.get(1) > 280000000) {
            throw new ArithmeticException("Calendar value too large for accurate calculations");
        }
        if (field == 14) {
            return val;
        }
        Date date = val.getTime();
        long time = date.getTime();
        boolean done = false;
        int millisecs = val.get(14);
        if (ModifyType.TRUNCATE == modifyType || millisecs < 500) {
            time -= millisecs;
        }
        if (field == 13) {
            done = true;
        }
        int seconds = val.get(13);
        if (!done && (ModifyType.TRUNCATE == modifyType || seconds < 30)) {
            time -= seconds * 1000;
        }
        if (field == 12) {
            done = true;
        }
        int minutes = val.get(12);
        if (!done && (ModifyType.TRUNCATE == modifyType || minutes < 30)) {
            time -= minutes * MILLIS_PER_MINUTE;
        }
        if (date.getTime() != time) {
            date.setTime(time);
            val.setTime(date);
        }
        boolean roundUp = false;
        int[][] iArr = fields;
        int length = iArr.length;
        int i = 0;
        while (i < length) {
            int[] aField = iArr[i];
            int length2 = aField.length;
            int millisecs2 = millisecs;
            int millisecs3 = 0;
            while (true) {
                Date date2 = date;
                if (millisecs3 < length2) {
                    int element = aField[millisecs3];
                    if (element != field) {
                        millisecs3++;
                        date = date2;
                    } else {
                        if (modifyType == ModifyType.CEILING || (modifyType == ModifyType.ROUND && roundUp)) {
                            if (field == 1001) {
                                if (val.get(5) == 1) {
                                    val.add(5, 15);
                                } else {
                                    val.add(5, -15);
                                    val.add(2, 1);
                                }
                            } else if (field == 9) {
                                if (val.get(11) == 0) {
                                    val.add(11, 12);
                                } else {
                                    val.add(11, -12);
                                    val.add(5, 1);
                                }
                            } else {
                                val.add(aField[0], 1);
                            }
                        }
                        return val;
                    }
                } else {
                    int offset = 0;
                    boolean offsetSet = false;
                    switch (field) {
                        case 9:
                            z = true;
                            if (aField[0] == 11) {
                                int offset2 = val.get(11);
                                if (offset2 < 12) {
                                    offset = offset2;
                                } else {
                                    offset = offset2 - 12;
                                }
                                roundUp = offset >= 6;
                                offsetSet = true;
                                break;
                            } else {
                                break;
                            }
                        case 1001:
                            if (aField[0] != 5) {
                                z = true;
                                break;
                            } else {
                                z = true;
                                int offset3 = val.get(5) - 1;
                                if (offset3 < 15) {
                                    offset = offset3;
                                } else {
                                    offset = offset3 - 15;
                                }
                                roundUp = offset > 7;
                                offsetSet = true;
                                break;
                            }
                        default:
                            z = true;
                            break;
                    }
                    if (!offsetSet) {
                        int min = val.getActualMinimum(aField[0]);
                        int max = val.getActualMaximum(aField[0]);
                        offset = val.get(aField[0]) - min;
                        roundUp = offset > (max - min) / 2 ? z : false;
                    }
                    if (offset != 0) {
                        val.set(aField[0], val.get(aField[0]) - offset);
                    }
                    i++;
                    modifyType = modType;
                    millisecs = millisecs2;
                    date = date2;
                }
            }
        }
        throw new IllegalArgumentException("The field " + field + " is not supported");
    }

    public static Date parseDate(String str, Locale locale, String... parsePatterns) throws ParseException {
        return parseDateWithLeniency(str, locale, parsePatterns, true);
    }

    public static Date parseDate(String str, String... parsePatterns) throws ParseException {
        return parseDate(str, null, parsePatterns);
    }

    public static Date parseDateStrictly(String str, Locale locale, String... parsePatterns) throws ParseException {
        return parseDateWithLeniency(str, locale, parsePatterns, false);
    }

    public static Date parseDateStrictly(String str, String... parsePatterns) throws ParseException {
        return parseDateStrictly(str, null, parsePatterns);
    }

    private static Date parseDateWithLeniency(String dateStr, Locale locale, String[] parsePatterns, boolean lenient) throws ParseException {
        Objects.requireNonNull(dateStr, "str");
        Objects.requireNonNull(parsePatterns, "parsePatterns");
        TimeZone tz = TimeZone.getDefault();
        Locale lcl = LocaleUtils.toLocale(locale);
        int i = 0;
        ParsePosition pos = new ParsePosition(0);
        Calendar calendar = Calendar.getInstance(tz, lcl);
        calendar.setLenient(lenient);
        for (String parsePattern : parsePatterns) {
            FastDateParser fdp = new FastDateParser(parsePattern, tz, lcl);
            calendar.clear();
            try {
                if (fdp.parse(dateStr, pos, calendar) && pos.getIndex() == dateStr.length()) {
                    return calendar.getTime();
                }
            } catch (IllegalArgumentException e) {
            }
            pos.setIndex(i);
        }
        throw new ParseException("Unable to parse the date: " + dateStr, -1);
    }

    public static Calendar round(Calendar calendar, int field) {
        Objects.requireNonNull(calendar, "calendar");
        return modify((Calendar) calendar.clone(), field, ModifyType.ROUND);
    }

    public static Date round(Date date, int field) {
        return modify(toCalendar(date), field, ModifyType.ROUND).getTime();
    }

    public static Date round(Object date, int field) {
        Objects.requireNonNull(date, XmlErrorCodes.DATE);
        if (date instanceof Date) {
            return round((Date) date, field);
        }
        if (date instanceof Calendar) {
            return round((Calendar) date, field).getTime();
        }
        throw new ClassCastException("Could not round " + date);
    }

    private static Date set(Date date, int calendarField, int amount) {
        validateDateNotNull(date);
        Calendar c = Calendar.getInstance();
        c.setLenient(false);
        c.setTime(date);
        c.set(calendarField, amount);
        return c.getTime();
    }

    public static Date setDays(Date date, int amount) {
        return set(date, 5, amount);
    }

    public static Date setHours(Date date, int amount) {
        return set(date, 11, amount);
    }

    public static Date setMilliseconds(Date date, int amount) {
        return set(date, 14, amount);
    }

    public static Date setMinutes(Date date, int amount) {
        return set(date, 12, amount);
    }

    public static Date setMonths(Date date, int amount) {
        return set(date, 2, amount);
    }

    public static Date setSeconds(Date date, int amount) {
        return set(date, 13, amount);
    }

    public static Date setYears(Date date, int amount) {
        return set(date, 1, amount);
    }

    public static Calendar toCalendar(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime((Date) Objects.requireNonNull(date, XmlErrorCodes.DATE));
        return c;
    }

    public static Calendar toCalendar(Date date, TimeZone tz) {
        Calendar c = Calendar.getInstance(tz);
        c.setTime((Date) Objects.requireNonNull(date, XmlErrorCodes.DATE));
        return c;
    }

    public static Calendar truncate(Calendar date, int field) {
        Objects.requireNonNull(date, XmlErrorCodes.DATE);
        return modify((Calendar) date.clone(), field, ModifyType.TRUNCATE);
    }

    public static Date truncate(Date date, int field) {
        return modify(toCalendar(date), field, ModifyType.TRUNCATE).getTime();
    }

    public static Date truncate(Object date, int field) {
        Objects.requireNonNull(date, XmlErrorCodes.DATE);
        if (date instanceof Date) {
            return truncate((Date) date, field);
        }
        if (date instanceof Calendar) {
            return truncate((Calendar) date, field).getTime();
        }
        throw new ClassCastException("Could not truncate " + date);
    }

    public static int truncatedCompareTo(Calendar cal1, Calendar cal2, int field) {
        Calendar truncatedCal1 = truncate(cal1, field);
        Calendar truncatedCal2 = truncate(cal2, field);
        return truncatedCal1.compareTo(truncatedCal2);
    }

    public static int truncatedCompareTo(Date date1, Date date2, int field) {
        Date truncatedDate1 = truncate(date1, field);
        Date truncatedDate2 = truncate(date2, field);
        return truncatedDate1.compareTo(truncatedDate2);
    }

    public static boolean truncatedEquals(Calendar cal1, Calendar cal2, int field) {
        return truncatedCompareTo(cal1, cal2, field) == 0;
    }

    public static boolean truncatedEquals(Date date1, Date date2, int field) {
        return truncatedCompareTo(date1, date2, field) == 0;
    }

    private static void validateDateNotNull(Date date) {
        Objects.requireNonNull(date, XmlErrorCodes.DATE);
    }

    @Deprecated
    public DateUtils() {
    }
}
