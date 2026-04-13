package org.apache.poi.util;

import java.nio.charset.Charset;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes10.dex */
public final class LocaleUtil {
    public static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(ZoneOffset.UTC);
    public static final Charset CHARSET_1252 = Charset.forName("CP1252");
    private static final ThreadLocal<TimeZone> userTimeZone = new ThreadLocal<>();
    private static final ThreadLocal<Locale> userLocale = new ThreadLocal<>();

    private LocaleUtil() {
    }

    static {
        ThreadLocalUtil.registerCleaner(new Runnable() { // from class: org.apache.poi.util.LocaleUtil$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LocaleUtil.lambda$static$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$static$0() {
        userTimeZone.remove();
        userLocale.remove();
    }

    public static void setUserTimeZone(TimeZone timezone) {
        userTimeZone.set(timezone);
    }

    public static TimeZone getUserTimeZone() {
        TimeZone timeZone = userTimeZone.get();
        return timeZone != null ? timeZone : TimeZone.getDefault();
    }

    public static void resetUserTimeZone() {
        userTimeZone.remove();
    }

    public static void setUserLocale(Locale locale) {
        userLocale.set(locale);
    }

    public static Locale getUserLocale() {
        Locale locale = userLocale.get();
        return locale != null ? locale : Locale.getDefault();
    }

    public static void resetUserLocale() {
        userLocale.remove();
    }

    public static Calendar getLocaleCalendar() {
        return getLocaleCalendar(getUserTimeZone());
    }

    public static Calendar getLocaleCalendar(int year, int month, int day) {
        return getLocaleCalendar(year, month, day, 0, 0, 0);
    }

    public static Calendar getLocaleCalendar(int year, int month, int day, int hour, int minute, int second) {
        Calendar cal = getLocaleCalendar();
        cal.set(year, month, day, hour, minute, second);
        cal.clear(14);
        return cal;
    }

    public static Calendar getLocaleCalendar(TimeZone timeZone) {
        return Calendar.getInstance(timeZone, getUserLocale());
    }

    public static String getLocaleFromLCID(int lcid) {
        LocaleID lid = LocaleID.lookupByLcid(65535 & lcid);
        return lid == null ? "invalid" : lid.getLanguageTag();
    }

    public static int getDefaultCodePageFromLCID(int lcid) {
        LocaleID lid = LocaleID.lookupByLcid(65535 & lcid);
        if (lid == null) {
            return 0;
        }
        return lid.getDefaultCodepage();
    }
}
