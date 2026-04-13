package org.apache.commons.lang3.time;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.lang3.time.AbstractFormatCache;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public abstract class AbstractFormatCache<F extends Format> {
    static final int NONE = -1;
    private static final ConcurrentMap<ArrayKey, String> dateTimeInstanceCache = new ConcurrentHashMap(7);
    private final ConcurrentMap<ArrayKey, F> instanceCache = new ConcurrentHashMap(7);

    protected abstract F createInstance(String str, TimeZone timeZone, Locale locale);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class ArrayKey {
        private final int hashCode;
        private final Object[] keys;

        ArrayKey(Object... keys) {
            this.keys = keys;
            this.hashCode = Objects.hash(keys);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ArrayKey other = (ArrayKey) obj;
            return Arrays.deepEquals(this.keys, other.keys);
        }

        public int hashCode() {
            return this.hashCode;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void clear() {
        dateTimeInstanceCache.clear();
    }

    static String getPatternForStyle(final Integer dateStyle, final Integer timeStyle, Locale locale) {
        final Locale safeLocale = LocaleUtils.toLocale(locale);
        ArrayKey key = new ArrayKey(dateStyle, timeStyle, safeLocale);
        return dateTimeInstanceCache.computeIfAbsent(key, new Function() { // from class: org.apache.commons.lang3.time.AbstractFormatCache$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractFormatCache.lambda$getPatternForStyle$0(dateStyle, timeStyle, safeLocale, (AbstractFormatCache.ArrayKey) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$getPatternForStyle$0(Integer dateStyle, Integer timeStyle, Locale safeLocale, ArrayKey k) {
        DateFormat formatter;
        try {
            if (dateStyle == null) {
                formatter = DateFormat.getTimeInstance(timeStyle.intValue(), safeLocale);
            } else if (timeStyle == null) {
                formatter = DateFormat.getDateInstance(dateStyle.intValue(), safeLocale);
            } else {
                formatter = DateFormat.getDateTimeInstance(dateStyle.intValue(), timeStyle.intValue(), safeLocale);
            }
            return ((SimpleDateFormat) formatter).toPattern();
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("No date time pattern for locale: " + safeLocale);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearInstance() {
        this.instanceCache.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public F getDateInstance(int dateStyle, TimeZone timeZone, Locale locale) {
        return getDateTimeInstance(Integer.valueOf(dateStyle), (Integer) null, timeZone, locale);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public F getDateTimeInstance(int dateStyle, int timeStyle, TimeZone timeZone, Locale locale) {
        return getDateTimeInstance(Integer.valueOf(dateStyle), Integer.valueOf(timeStyle), timeZone, locale);
    }

    private F getDateTimeInstance(Integer dateStyle, Integer timeStyle, TimeZone timeZone, Locale locale) {
        Locale locale2 = LocaleUtils.toLocale(locale);
        String pattern = getPatternForStyle(dateStyle, timeStyle, locale2);
        return getInstance(pattern, timeZone, locale2);
    }

    public F getInstance() {
        return getDateTimeInstance(3, 3, TimeZone.getDefault(), Locale.getDefault());
    }

    public F getInstance(final String pattern, TimeZone timeZone, Locale locale) {
        Objects.requireNonNull(pattern, "pattern");
        final TimeZone actualTimeZone = TimeZones.toTimeZone(timeZone);
        final Locale actualLocale = LocaleUtils.toLocale(locale);
        ArrayKey key = new ArrayKey(pattern, actualTimeZone, actualLocale);
        return this.instanceCache.computeIfAbsent(key, new Function() { // from class: org.apache.commons.lang3.time.AbstractFormatCache$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractFormatCache.this.m2200xcaeba003(pattern, actualTimeZone, actualLocale, (AbstractFormatCache.ArrayKey) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getInstance$1$org-apache-commons-lang3-time-AbstractFormatCache, reason: not valid java name */
    public /* synthetic */ Format m2200xcaeba003(String pattern, TimeZone actualTimeZone, Locale actualLocale, ArrayKey k) {
        return createInstance(pattern, actualTimeZone, actualLocale);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public F getTimeInstance(int timeStyle, TimeZone timeZone, Locale locale) {
        return getDateTimeInstance((Integer) null, Integer.valueOf(timeStyle), timeZone, locale);
    }
}
