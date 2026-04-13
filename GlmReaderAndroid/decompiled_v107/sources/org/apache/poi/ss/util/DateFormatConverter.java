package org.apache.poi.ss.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.LocaleID;
import org.apache.xmlbeans.impl.common.NameUtil;

/* loaded from: classes10.dex */
public final class DateFormatConverter {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) DateFormatConverter.class);
    private static Map<String, String> tokenConversions = prepareTokenConversions();

    private DateFormatConverter() {
    }

    /* loaded from: classes10.dex */
    public static class DateFormatTokenizer {
        String format;
        int pos;

        public DateFormatTokenizer(String format) {
            this.format = format;
        }

        public String getNextToken() {
            if (this.pos >= this.format.length()) {
                return null;
            }
            int subStart = this.pos;
            char curChar = this.format.charAt(this.pos);
            this.pos++;
            if (curChar == '\'') {
                while (this.pos < this.format.length() && this.format.charAt(this.pos) != '\'') {
                    this.pos++;
                }
                if (this.pos < this.format.length()) {
                    this.pos++;
                }
            } else {
                while (this.pos < this.format.length() && this.format.charAt(this.pos) == curChar) {
                    this.pos++;
                }
            }
            return this.format.substring(subStart, this.pos);
        }

        public static String[] tokenize(String format) {
            List<String> result = new ArrayList<>();
            DateFormatTokenizer tokenizer = new DateFormatTokenizer(format);
            while (true) {
                String token = tokenizer.getNextToken();
                if (token != null) {
                    result.add(token);
                } else {
                    return (String[]) result.toArray(new String[0]);
                }
            }
        }

        public String toString() {
            StringBuilder result = new StringBuilder();
            DateFormatTokenizer tokenizer = new DateFormatTokenizer(this.format);
            while (true) {
                String token = tokenizer.getNextToken();
                if (token != null) {
                    if (result.length() > 0) {
                        result.append(", ");
                    }
                    result.append(CollectionUtils.DEFAULT_TOSTRING_PREFIX).append(token).append(CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
                } else {
                    return result.toString();
                }
            }
        }
    }

    private static Map<String, String> prepareTokenConversions() {
        Map<String, String> result = new HashMap<>();
        result.put("EEEE", "dddd");
        result.put("EEE", "ddd");
        result.put("EE", "ddd");
        result.put("E", "d");
        result.put("Z", "");
        result.put(CompressorStreamFactory.Z, "");
        result.put("a", "am/pm");
        result.put("A", "AM/PM");
        result.put("K", "H");
        result.put("KK", "HH");
        result.put("k", "h");
        result.put("kk", "hh");
        result.put("S", "0");
        result.put("SS", TarConstants.VERSION_POSIX);
        result.put("SSS", "000");
        result.put("y", "yyyy");
        return result;
    }

    public static String getPrefixForLocale(Locale locale) {
        String languageTag = locale.toLanguageTag();
        if (Locale.ROOT.equals(locale) || "".equals(languageTag)) {
            return "";
        }
        LocaleID loc = LocaleID.lookupByLanguageTag(languageTag);
        if (loc == null) {
            String cmpTag = languageTag.indexOf(95) > -1 ? languageTag.replace(NameUtil.USCORE, '-') : languageTag;
            int idx = languageTag.length();
            while (loc == null) {
                int lastIndexOf = cmpTag.lastIndexOf(45, idx - 1);
                idx = lastIndexOf;
                if (lastIndexOf <= 0) {
                    break;
                }
                loc = LocaleID.lookupByLanguageTag(languageTag.substring(0, idx));
            }
        }
        if (loc == null) {
            LOG.atError().log("Unable to find prefix for Locale '{}' or its parent locales.", languageTag);
            return "";
        }
        return String.format(Locale.ROOT, "[$-%04X]", Integer.valueOf(loc.getLcid()));
    }

    public static String convert(Locale locale, DateFormat df) {
        String ptrn = ((SimpleDateFormat) df).toPattern();
        return convert(locale, ptrn);
    }

    public static String convert(Locale locale, String format) {
        StringBuilder result = new StringBuilder();
        result.append(getPrefixForLocale(locale));
        DateFormatTokenizer tokenizer = new DateFormatTokenizer(format);
        while (true) {
            String token = tokenizer.getNextToken();
            if (token != null) {
                if (token.startsWith("'")) {
                    result.append(token.replace(Chars.QUOTE, '\"'));
                } else if (!Character.isLetter(token.charAt(0))) {
                    result.append(token);
                } else {
                    String mappedToken = tokenConversions.get(token);
                    result.append(mappedToken == null ? token : mappedToken);
                }
            } else {
                result.append(";@");
                return result.toString().trim();
            }
        }
    }

    public static String getJavaDatePattern(int style, Locale locale) {
        DateFormat df = DateFormat.getDateInstance(style, locale);
        if (df instanceof SimpleDateFormat) {
            return ((SimpleDateFormat) df).toPattern();
        }
        switch (style) {
            case 0:
                return "dddd, MMMM d, yyyy";
            case 1:
                return "MMMM d, yyyy";
            case 2:
            default:
                return "MMM d, yyyy";
            case 3:
                return "d/MM/yy";
        }
    }

    public static String getJavaTimePattern(int style, Locale locale) {
        DateFormat df = DateFormat.getTimeInstance(style, locale);
        if (df instanceof SimpleDateFormat) {
            return ((SimpleDateFormat) df).toPattern();
        }
        switch (style) {
            case 3:
                return "h:mm a";
            default:
                return "h:mm:ss a";
        }
    }

    public static String getJavaDateTimePattern(int style, Locale locale) {
        DateFormat df = DateFormat.getDateTimeInstance(style, style, locale);
        if (df instanceof SimpleDateFormat) {
            return ((SimpleDateFormat) df).toPattern();
        }
        switch (style) {
            case 0:
                return "dddd, MMMM d, yyyy h:mm:ss a";
            case 1:
                return "MMMM d, yyyy h:mm:ss a";
            case 2:
            default:
                return "MMM d, yyyy h:mm:ss a";
            case 3:
                return "M/d/yy h:mm a";
        }
    }
}
