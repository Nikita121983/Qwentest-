package org.apache.commons.lang3.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import org.apache.poi.util.CodePageUtil;

/* loaded from: classes9.dex */
public class CalendarUtils {
    public static final CalendarUtils INSTANCE = getInstance();
    private final Calendar calendar;
    private final Locale locale;

    public static CalendarUtils getInstance() {
        return new CalendarUtils(Calendar.getInstance());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CalendarUtils getInstance(Locale locale) {
        return new CalendarUtils(Calendar.getInstance(locale), locale);
    }

    public static LocalDateTime toLocalDateTime(Calendar calendar) {
        return LocalDateTime.ofInstant(calendar.toInstant(), toZoneId(calendar));
    }

    public static OffsetDateTime toOffsetDateTime(Calendar calendar) {
        return OffsetDateTime.ofInstant(calendar.toInstant(), toZoneId(calendar));
    }

    public static ZonedDateTime toZonedDateTime(Calendar calendar) {
        return ZonedDateTime.ofInstant(calendar.toInstant(), toZoneId(calendar));
    }

    private static ZoneId toZoneId(Calendar calendar) {
        return calendar.getTimeZone().toZoneId();
    }

    public CalendarUtils(Calendar calendar) {
        this(calendar, Locale.getDefault());
    }

    CalendarUtils(Calendar calendar, Locale locale) {
        this.calendar = (Calendar) Objects.requireNonNull(calendar, "calendar");
        this.locale = (Locale) Objects.requireNonNull(locale, "locale");
    }

    public int getDayOfMonth() {
        return this.calendar.get(5);
    }

    public int getDayOfYear() {
        return this.calendar.get(6);
    }

    public int getMonth() {
        return this.calendar.get(2);
    }

    String[] getMonthDisplayNames(int style) {
        Map<String, Integer> displayNames = this.calendar.getDisplayNames(2, style, this.locale);
        if (displayNames == null) {
            return null;
        }
        final String[] monthNames = new String[displayNames.size()];
        displayNames.forEach(new BiConsumer() { // from class: org.apache.commons.lang3.time.CalendarUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                CalendarUtils.lambda$getMonthDisplayNames$0(monthNames, (String) obj, (Integer) obj2);
            }
        });
        return monthNames;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getMonthDisplayNames$0(String[] monthNames, String k, Integer v) {
        monthNames[v.intValue()] = k;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] getStandaloneLongMonthNames() {
        return getMonthDisplayNames(32770);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String[] getStandaloneShortMonthNames() {
        return getMonthDisplayNames(CodePageUtil.CP_WINDOWS_1252_BIFF23);
    }

    public int getYear() {
        return this.calendar.get(1);
    }

    public LocalDate toLocalDate() {
        return toLocalDateTime().toLocalDate();
    }

    public LocalDateTime toLocalDateTime() {
        return toLocalDateTime(this.calendar);
    }

    public OffsetDateTime toOffsetDateTime() {
        return toOffsetDateTime(this.calendar);
    }

    public ZonedDateTime toZonedDateTime() {
        return toZonedDateTime(this.calendar);
    }
}
