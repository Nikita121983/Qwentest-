package org.apache.commons.lang3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* loaded from: classes9.dex */
public class LocaleUtils {
    private static final char DASH = '-';
    private static final char UNDERSCORE = '_';
    private static final String UNDETERMINED = "und";
    private static final ConcurrentMap<String, List<Locale>> cLanguagesByCountry = new ConcurrentHashMap();
    private static final ConcurrentMap<String, List<Locale>> cCountriesByLanguage = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class SyncAvoid {
        private static final List<Locale> AVAILABLE_LOCALE_ULIST = Collections.unmodifiableList(Arrays.asList((Locale[]) ArraySorter.sort(Locale.getAvailableLocales(), Comparator.comparing(new Function() { // from class: org.apache.commons.lang3.LocaleUtils$SyncAvoid$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String locale;
                locale = ((Locale) obj).toString();
                return locale;
            }
        }))));
        private static final Set<Locale> AVAILABLE_LOCALE_USET = Collections.unmodifiableSet(new LinkedHashSet(AVAILABLE_LOCALE_ULIST));

        private SyncAvoid() {
        }
    }

    public static List<Locale> availableLocaleList() {
        return SyncAvoid.AVAILABLE_LOCALE_ULIST;
    }

    private static List<Locale> availableLocaleList(Predicate<Locale> predicate) {
        return (List) availableLocaleList().stream().filter(predicate).collect(Collectors.toList());
    }

    public static Set<Locale> availableLocaleSet() {
        return SyncAvoid.AVAILABLE_LOCALE_USET;
    }

    public static List<Locale> countriesByLanguage(final String languageCode) {
        if (languageCode == null) {
            return Collections.emptyList();
        }
        return cCountriesByLanguage.computeIfAbsent(languageCode, new Function() { // from class: org.apache.commons.lang3.LocaleUtils$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                List unmodifiableList;
                unmodifiableList = Collections.unmodifiableList(LocaleUtils.availableLocaleList(new Predicate() { // from class: org.apache.commons.lang3.LocaleUtils$$ExternalSyntheticLambda2
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj2) {
                        return LocaleUtils.lambda$countriesByLanguage$0(r1, (Locale) obj2);
                    }
                }));
                return unmodifiableList;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$countriesByLanguage$0(String languageCode, Locale locale) {
        return languageCode.equals(locale.getLanguage()) && !locale.getCountry().isEmpty() && locale.getVariant().isEmpty();
    }

    public static boolean isAvailableLocale(Locale locale) {
        return availableLocaleSet().contains(locale);
    }

    private static boolean isISO3166CountryCode(String str) {
        return StringUtils.isAllUpperCase(str) && str.length() == 2;
    }

    private static boolean isISO639LanguageCode(String str) {
        return StringUtils.isAllLowerCase(str) && (str.length() == 2 || str.length() == 3);
    }

    public static boolean isLanguageUndetermined(Locale locale) {
        return locale == null || UNDETERMINED.equals(locale.toLanguageTag());
    }

    private static boolean isNumericAreaCode(String str) {
        return StringUtils.isNumeric(str) && str.length() == 3;
    }

    public static List<Locale> languagesByCountry(final String countryCode) {
        if (countryCode == null) {
            return Collections.emptyList();
        }
        return cLanguagesByCountry.computeIfAbsent(countryCode, new Function() { // from class: org.apache.commons.lang3.LocaleUtils$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                List unmodifiableList;
                unmodifiableList = Collections.unmodifiableList(LocaleUtils.availableLocaleList(new Predicate() { // from class: org.apache.commons.lang3.LocaleUtils$$ExternalSyntheticLambda0
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj2) {
                        return LocaleUtils.lambda$languagesByCountry$2(r1, (Locale) obj2);
                    }
                }));
                return unmodifiableList;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$languagesByCountry$2(String countryCode, Locale locale) {
        return countryCode.equals(locale.getCountry()) && locale.getVariant().isEmpty();
    }

    public static List<Locale> localeLookupList(Locale locale) {
        return localeLookupList(locale, locale);
    }

    public static List<Locale> localeLookupList(Locale locale, Locale defaultLocale) {
        List<Locale> list = new ArrayList<>(4);
        if (locale != null) {
            list.add(locale);
            if (!locale.getVariant().isEmpty()) {
                list.add(new Locale(locale.getLanguage(), locale.getCountry()));
            }
            if (!locale.getCountry().isEmpty()) {
                list.add(new Locale(locale.getLanguage(), ""));
            }
            if (!list.contains(defaultLocale)) {
                list.add(defaultLocale);
            }
        }
        return Collections.unmodifiableList(list);
    }

    private static Locale parseLocale(String str) {
        if (isISO639LanguageCode(str)) {
            return new Locale(str);
        }
        char separator = str.indexOf(95) == -1 ? '-' : '_';
        String[] segments = str.split(String.valueOf(separator), 3);
        String language = segments[0];
        if (segments.length == 2) {
            String country = segments[1];
            if ((isISO639LanguageCode(language) && isISO3166CountryCode(country)) || isNumericAreaCode(country)) {
                return new Locale(language, country);
            }
        } else if (segments.length == 3) {
            String country2 = segments[1];
            String variant = segments[2];
            if (isISO639LanguageCode(language) && ((country2.isEmpty() || isISO3166CountryCode(country2) || isNumericAreaCode(country2)) && !variant.isEmpty())) {
                return new Locale(language, country2, variant);
            }
        }
        throw new IllegalArgumentException("Invalid locale format: " + str);
    }

    public static Locale toLocale(Locale locale) {
        return locale != null ? locale : Locale.getDefault();
    }

    public static Locale toLocale(String str) {
        if (str == null) {
            return null;
        }
        if (str.isEmpty()) {
            return new Locale("", "");
        }
        if (str.contains("#")) {
            throw new IllegalArgumentException("Invalid locale format: " + str);
        }
        int len = str.length();
        if (len < 2) {
            throw new IllegalArgumentException("Invalid locale format: " + str);
        }
        char ch0 = str.charAt(0);
        if (ch0 == '_' || ch0 == '-') {
            if (len < 3) {
                throw new IllegalArgumentException("Invalid locale format: " + str);
            }
            char ch1 = str.charAt(1);
            char ch2 = str.charAt(2);
            if (!Character.isUpperCase(ch1) || !Character.isUpperCase(ch2)) {
                throw new IllegalArgumentException("Invalid locale format: " + str);
            }
            if (len == 3) {
                return new Locale("", str.substring(1, 3));
            }
            if (len < 5) {
                throw new IllegalArgumentException("Invalid locale format: " + str);
            }
            if (str.charAt(3) != ch0) {
                throw new IllegalArgumentException("Invalid locale format: " + str);
            }
            return new Locale("", str.substring(1, 3), str.substring(4));
        }
        return parseLocale(str);
    }

    @Deprecated
    public LocaleUtils() {
    }
}
