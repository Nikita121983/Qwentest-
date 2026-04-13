package org.apache.poi.ss.usermodel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.common.usermodel.fonts.FontHeader;
import org.apache.poi.ss.formula.ConditionalFormattingEvaluator;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.ThreadLocalUtil;

/* loaded from: classes10.dex */
public class DateUtil {
    private static final int BAD_DATE = -1;
    public static final long DAY_MILLISECONDS = 86400000;
    public static final int HOURS_PER_DAY = 24;
    public static final int MINUTES_PER_HOUR = 60;
    public static final int SECONDS_PER_DAY = 86400;
    public static final int SECONDS_PER_MINUTE = 60;
    private static final BigDecimal BD_NANOSEC_DAY = BigDecimal.valueOf(8.64E13d);
    private static final BigDecimal BD_MILISEC_RND = BigDecimal.valueOf(500000.0d);
    private static final BigDecimal BD_SECOND_RND = BigDecimal.valueOf(5.0E8d);
    private static final Pattern TIME_SEPARATOR_PATTERN = Pattern.compile(":");
    private static final Pattern date_ptrn1 = Pattern.compile("^\\[\\$-.*?]");
    private static final Pattern date_ptrn2 = Pattern.compile("^\\[[a-zA-Z]+]");
    private static final Pattern date_ptrn3a = Pattern.compile("[yYmMdDhHsS]");
    private static final Pattern date_ptrn3b = Pattern.compile("^[\\[\\]yYmMdDhHsS\\-T/年月日,. :\"\\\\]+0* ?[ampAMP/]*$");
    private static final Pattern date_ptrn4 = Pattern.compile("^\\[([hH]+|[mM]+|[sS]+)]");
    private static final Pattern date_ptrn5 = Pattern.compile("^\\[DBNum([123])]");
    private static final DateTimeFormatter dateTimeFormats = new DateTimeFormatterBuilder().appendPattern("[d[.] [MMMM][MMM][ yyyy]][[ ]h:m[:s][.SSS] a][[ ]H:m[:s][.SSS]]").appendPattern("[[yyyy ]d-[MMMM][MMM][-yyyy]][[ ]h:m[:s][.SSS] a][[ ]H:m[:s][.SSS]]").appendPattern("[M/dd[/yyyy]][[ ]h:m[:s][.SSS] a][[ ]H:m[:s][.SSS]]").appendPattern("[[yyyy/]M/dd][[ ]h:m[:s][.SSS] a][[ ]H:m[:s][.SSS]]").parseDefaulting(ChronoField.YEAR_OF_ERA, LocaleUtil.getLocaleCalendar().get(1)).toFormatter(LocaleUtil.getUserLocale());
    private static boolean maintainCache = true;
    private static final ThreadLocal<Integer> lastFormatIndex = ThreadLocal.withInitial(new Supplier() { // from class: org.apache.poi.ss.usermodel.DateUtil$$ExternalSyntheticLambda0
        @Override // java.util.function.Supplier
        public final Object get() {
            return DateUtil.lambda$static$0();
        }
    });
    private static final ThreadLocal<String> lastFormatString = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> lastCachedResult = new ThreadLocal<>();

    private DateUtil() {
    }

    static {
        ThreadLocalUtil.registerCleaner(new Runnable() { // from class: org.apache.poi.ss.usermodel.DateUtil$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DateUtil.lambda$static$1();
            }
        });
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.time.LocalDateTime] */
    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(LocaleUtil.TIMEZONE_UTC.toZoneId()).toLocalDateTime();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.time.LocalDateTime] */
    public static LocalDateTime toLocalDateTime(Calendar date) {
        return date.toInstant().atZone(LocaleUtil.TIMEZONE_UTC.toZoneId()).toLocalDateTime();
    }

    public static double getExcelDate(LocalDate date) {
        return getExcelDate(date, false);
    }

    public static double getExcelDate(LocalDate date, boolean use1904windowing) {
        int year = date.getYear();
        int dayOfYear = date.getDayOfYear();
        return internalGetExcelDate(year, dayOfYear, 0, 0, 0, 0, use1904windowing);
    }

    public static double getExcelDate(LocalDateTime date) {
        return getExcelDate(date, false);
    }

    public static double getExcelDate(LocalDateTime date, boolean use1904windowing) {
        int year = date.getYear();
        int dayOfYear = date.getDayOfYear();
        int hour = date.getHour();
        int minute = date.getMinute();
        int second = date.getSecond();
        int milliSecond = date.getNano() / 1000000;
        return internalGetExcelDate(year, dayOfYear, hour, minute, second, milliSecond, use1904windowing);
    }

    public static double getExcelDate(Date date) {
        return getExcelDate(date, false);
    }

    public static double getExcelDate(Date date, boolean use1904windowing) {
        Calendar calStart = LocaleUtil.getLocaleCalendar();
        calStart.setTime(date);
        int year = calStart.get(1);
        int dayOfYear = calStart.get(6);
        int hour = calStart.get(11);
        int minute = calStart.get(12);
        int second = calStart.get(13);
        int milliSecond = calStart.get(14);
        return internalGetExcelDate(year, dayOfYear, hour, minute, second, milliSecond, use1904windowing);
    }

    public static double getExcelDate(Calendar date, boolean use1904windowing) {
        int year = date.get(1);
        int dayOfYear = date.get(6);
        int hour = date.get(11);
        int minute = date.get(12);
        int second = date.get(13);
        int milliSecond = date.get(14);
        return internalGetExcelDate(year, dayOfYear, hour, minute, second, milliSecond, use1904windowing);
    }

    private static boolean isLastDay1899(int year, int dayOfYear) {
        return year == 1899 && dayOfYear == 365;
    }

    private static double internalGetExcelDate(int year, int dayOfYear, int hour, int minute, int second, int milliSecond, boolean use1904windowing) {
        if (!use1904windowing && year < 1900 && !isLastDay1899(year, dayOfYear)) {
            return -1.0d;
        }
        if (use1904windowing && year < 1904) {
            return -1.0d;
        }
        double fraction = ((((((hour * 60.0d) + minute) * 60.0d) + second) * 1000.0d) + milliSecond) / 8.64E7d;
        double value = absoluteDay(year, dayOfYear, use1904windowing) + fraction;
        if (!use1904windowing && value >= 60.0d) {
            return value + 1.0d;
        }
        if (use1904windowing) {
            return value - 1.0d;
        }
        return value;
    }

    public static Date getJavaDate(double date, TimeZone tz) {
        return getJavaDate(date, false, tz, false);
    }

    public static Date getJavaDate(double date) {
        return getJavaDate(date, false, null, false);
    }

    public static Date getJavaDate(double date, boolean use1904windowing, TimeZone tz) {
        return getJavaDate(date, use1904windowing, tz, false);
    }

    public static Date getJavaDate(double date, boolean use1904windowing, TimeZone tz, boolean roundSeconds) {
        Calendar calendar = getJavaCalendar(date, use1904windowing, tz, roundSeconds);
        if (calendar == null) {
            return null;
        }
        return calendar.getTime();
    }

    public static Date getJavaDate(double date, boolean use1904windowing) {
        return getJavaDate(date, use1904windowing, null, false);
    }

    public static LocalDateTime getLocalDateTime(double date) {
        return getLocalDateTime(date, false, false);
    }

    public static LocalDateTime getLocalDateTime(double date, boolean use1904windowing) {
        return getLocalDateTime(date, use1904windowing, false);
    }

    public static LocalDateTime getLocalDateTime(double date, boolean use1904windowing, boolean roundSeconds) {
        if (!isValidExcelDate(date)) {
            return null;
        }
        BigDecimal bd = BigDecimal.valueOf(date);
        int wholeDays = bd.intValue();
        int startYear = 1900;
        int dayAdjust = -1;
        if (use1904windowing) {
            startYear = 1904;
            dayAdjust = 1;
        } else if (wholeDays < 61) {
            dayAdjust = 0;
        }
        LocalDateTime ldt = LocalDateTime.of(startYear, 1, 1, 0, 0);
        LocalDateTime ldt2 = ldt.plusDays((wholeDays + dayAdjust) - 1);
        long nanosTime = bd.subtract(BigDecimal.valueOf(wholeDays)).multiply(BD_NANOSEC_DAY).add(roundSeconds ? BD_SECOND_RND : BD_MILISEC_RND).longValue();
        return ldt2.plusNanos(nanosTime).truncatedTo(roundSeconds ? ChronoUnit.SECONDS : ChronoUnit.MILLIS);
    }

    public static void setCalendar(Calendar calendar, int wholeDays, int millisecondsInDay, boolean use1904windowing, boolean roundSeconds) {
        int startYear;
        int dayAdjust = -1;
        if (use1904windowing) {
            dayAdjust = 1;
            startYear = 1904;
        } else if (wholeDays >= 61) {
            startYear = 1900;
        } else {
            dayAdjust = 0;
            startYear = 1900;
        }
        calendar.set(startYear, 0, wholeDays + dayAdjust, 0, 0, 0);
        calendar.set(14, millisecondsInDay);
        if (calendar.get(14) == 0) {
            calendar.clear(14);
        }
        if (roundSeconds) {
            calendar.add(14, 500);
            calendar.clear(14);
        }
    }

    public static Calendar getJavaCalendar(double date) {
        return getJavaCalendar(date, false, null, false);
    }

    public static Calendar getJavaCalendar(double date, boolean use1904windowing) {
        return getJavaCalendar(date, use1904windowing, null, false);
    }

    public static Calendar getJavaCalendarUTC(double date, boolean use1904windowing) {
        return getJavaCalendar(date, use1904windowing, LocaleUtil.TIMEZONE_UTC, false);
    }

    public static Calendar getJavaCalendar(double date, boolean use1904windowing, TimeZone timeZone) {
        return getJavaCalendar(date, use1904windowing, timeZone, false);
    }

    public static Calendar getJavaCalendar(double date, boolean use1904windowing, TimeZone timeZone, boolean roundSeconds) {
        Calendar calendar;
        if (!isValidExcelDate(date)) {
            return null;
        }
        int wholeDays = (int) Math.floor(date);
        int millisecondsInDay = (int) (((date - wholeDays) * 8.64E7d) + 0.5d);
        if (timeZone != null) {
            calendar = LocaleUtil.getLocaleCalendar(timeZone);
        } else {
            calendar = LocaleUtil.getLocaleCalendar();
        }
        setCalendar(calendar, wholeDays, millisecondsInDay, use1904windowing, roundSeconds);
        return calendar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Integer lambda$static$0() {
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$static$1() {
        lastFormatIndex.remove();
        lastFormatString.remove();
        lastCachedResult.remove();
    }

    private static boolean isCached(String formatString, int formatIndex) {
        return maintainCache && formatIndex == lastFormatIndex.get().intValue() && formatString.equals(lastFormatString.get());
    }

    private static void cache(String formatString, int formatIndex, boolean cached) {
        if (maintainCache) {
            if (formatString == null || "".equals(formatString)) {
                lastFormatString.remove();
            } else {
                lastFormatString.set(formatString);
            }
            if (formatIndex == -1) {
                lastFormatIndex.remove();
            } else {
                lastFormatIndex.set(Integer.valueOf(formatIndex));
            }
            lastCachedResult.set(Boolean.valueOf(cached));
        }
    }

    public static boolean isADateFormat(ExcelNumberFormat numFmt) {
        if (numFmt == null) {
            return false;
        }
        return isADateFormat(numFmt.getIdx(), numFmt.getFormat());
    }

    public static boolean isADateFormat(int formatIndex, String formatString) {
        if (isInternalDateFormat(formatIndex)) {
            cache(formatString, formatIndex, true);
            return true;
        }
        if (formatString == null || formatString.isEmpty()) {
            return false;
        }
        if (isCached(formatString, formatIndex)) {
            return lastCachedResult.get().booleanValue();
        }
        int length = formatString.length();
        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char c = formatString.charAt(i);
            if (i < length - 1) {
                char nc = formatString.charAt(i + 1);
                if (c == '\\') {
                    switch (nc) {
                        case ' ':
                        case ',':
                        case '-':
                        case '.':
                        case '\\':
                            break;
                    }
                } else if (c == ';' && nc == '@') {
                    i++;
                }
                i++;
            }
            sb.append(c);
            i++;
        }
        String fs = sb.toString();
        if (date_ptrn4.matcher(fs).matches()) {
            cache(formatString, formatIndex, true);
            return true;
        }
        String fs2 = date_ptrn2.matcher(date_ptrn1.matcher(date_ptrn5.matcher(fs).replaceAll("")).replaceAll("")).replaceAll("");
        int separatorIndex = fs2.indexOf(59);
        if (separatorIndex > 0 && separatorIndex < fs2.length() - 1) {
            fs2 = fs2.substring(0, separatorIndex);
        }
        if (!date_ptrn3a.matcher(fs2).find()) {
            return false;
        }
        boolean result = date_ptrn3b.matcher(fs2).matches();
        cache(formatString, formatIndex, result);
        return result;
    }

    public static boolean isInternalDateFormat(int format) {
        switch (format) {
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 45:
            case 46:
            case 47:
                return true;
            default:
                return false;
        }
    }

    public static boolean isCellDateFormatted(Cell cell) {
        return isCellDateFormatted(cell, null);
    }

    public static boolean isCellDateFormatted(Cell cell, ConditionalFormattingEvaluator cfEvaluator) {
        ExcelNumberFormat nf;
        if (cell == null) {
            return false;
        }
        double d = cell.getNumericCellValue();
        if (!isValidExcelDate(d) || (nf = ExcelNumberFormat.from(cell, cfEvaluator)) == null) {
            return false;
        }
        boolean bDate = isADateFormat(nf);
        return bDate;
    }

    public static boolean isCellInternalDateFormatted(Cell cell) {
        if (cell == null) {
            return false;
        }
        double d = cell.getNumericCellValue();
        if (!isValidExcelDate(d)) {
            return false;
        }
        CellStyle style = cell.getCellStyle();
        int i = style.getDataFormat();
        boolean bDate = isInternalDateFormat(i);
        return bDate;
    }

    public static boolean isValidExcelDate(double value) {
        return value > -4.9E-324d;
    }

    protected static int absoluteDay(Calendar cal, boolean use1904windowing) {
        return absoluteDay(cal.get(1), cal.get(6), use1904windowing);
    }

    protected static int absoluteDay(LocalDateTime date, boolean use1904windowing) {
        return absoluteDay(date.getYear(), date.getDayOfYear(), use1904windowing);
    }

    private static int absoluteDay(int year, int dayOfYear, boolean use1904windowing) {
        return daysInPriorYears(year, dayOfYear, use1904windowing) + dayOfYear;
    }

    private static int daysInPriorYears(int year, int dayOfYear, boolean use1904windowing) {
        if ((!use1904windowing && year < 1900 && !isLastDay1899(year, dayOfYear)) || (use1904windowing && year < 1904)) {
            throw new IllegalArgumentException("'year' must be 1900 or greater");
        }
        int yr1 = year - 1;
        int leapDays = (((yr1 / 4) - (yr1 / 100)) + (yr1 / FontHeader.REGULAR_WEIGHT)) - 460;
        return ((year - (use1904windowing ? 1904 : 1900)) * 365) + leapDays;
    }

    private static Calendar dayStart(Calendar cal) {
        cal.get(11);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        cal.get(11);
        return cal;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class FormatException extends Exception {
        public FormatException(String msg) {
            super(msg);
        }
    }

    public static double convertTime(String timeStr) {
        try {
            return convertTimeInternal(timeStr);
        } catch (FormatException e) {
            String msg = "Bad time format '" + timeStr + "' expected 'HH:MM' or 'HH:MM:SS' - " + e.getMessage();
            throw new IllegalArgumentException(msg);
        }
    }

    private static double convertTimeInternal(String timeStr) throws FormatException {
        String secStr;
        int len = timeStr.length();
        if (len < 4 || len > 8) {
            throw new FormatException("Bad length");
        }
        String[] parts = TIME_SEPARATOR_PATTERN.split(timeStr);
        switch (parts.length) {
            case 2:
                secStr = TarConstants.VERSION_POSIX;
                break;
            case 3:
                secStr = parts[2];
                break;
            default:
                throw new FormatException("Expected 2 or 3 fields but got (" + parts.length + ")");
        }
        String hourStr = parts[0];
        String minStr = parts[1];
        int hours = parseInt(hourStr, "hour", 24);
        int minutes = parseInt(minStr, "minute", 60);
        int seconds = parseInt(secStr, "second", 60);
        double totalSeconds = seconds + ((minutes + (hours * 60.0d)) * 60.0d);
        return totalSeconds / 86400.0d;
    }

    public static Date parseYYYYMMDDDate(String dateStr) {
        try {
            return parseYYYYMMDDDateInternal(dateStr);
        } catch (FormatException e) {
            String msg = "Bad time format " + dateStr + " expected 'YYYY/MM/DD' - " + e.getMessage();
            throw new IllegalArgumentException(msg);
        }
    }

    private static Date parseYYYYMMDDDateInternal(String timeStr) throws FormatException {
        if (timeStr.length() != 10) {
            throw new FormatException("Bad length");
        }
        String yearStr = timeStr.substring(0, 4);
        String monthStr = timeStr.substring(5, 7);
        String dayStr = timeStr.substring(8, 10);
        int year = parseInt(yearStr, "year", -32768, 32767);
        int month = parseInt(monthStr, "month", 1, 12);
        int day = parseInt(dayStr, "day", 1, 31);
        Calendar cal = LocaleUtil.getLocaleCalendar(year, month - 1, day);
        return cal.getTime();
    }

    private static int parseInt(String strVal, String fieldName, int rangeMax) throws FormatException {
        return parseInt(strVal, fieldName, 0, rangeMax - 1);
    }

    private static int parseInt(String strVal, String fieldName, int lowerLimit, int upperLimit) throws FormatException {
        try {
            int result = Integer.parseInt(strVal);
            if (result < lowerLimit || result > upperLimit) {
                throw new FormatException(fieldName + " value (" + result + ") is outside the allowable range(0.." + upperLimit + ")");
            }
            return result;
        } catch (NumberFormatException e) {
            throw new FormatException("Bad int format '" + strVal + "' for " + fieldName + " field");
        }
    }

    /* JADX WARN: Type inference failed for: r5v6, types: [java.time.ZonedDateTime] */
    public static Double parseDateTime(String str) {
        TemporalAccessor tmp = dateTimeFormats.parse(str.replaceAll("\\s+", StringUtils.SPACE));
        LocalTime time = (LocalTime) tmp.query(TemporalQueries.localTime());
        LocalDate date = (LocalDate) tmp.query(TemporalQueries.localDate());
        if (time == null && date == null) {
            return null;
        }
        double tm = 0.0d;
        if (date != null) {
            Date d = Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            tm = getExcelDate(d);
        }
        if (time != null) {
            tm += (time.toSecondOfDay() * 1.0d) / 86400.0d;
        }
        return Double.valueOf(tm);
    }

    public static void enableThreadLocalCache(boolean enable) {
        maintainCache = enable;
    }
}
