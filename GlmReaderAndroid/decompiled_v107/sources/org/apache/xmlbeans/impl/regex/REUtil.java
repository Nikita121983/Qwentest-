package org.apache.xmlbeans.impl.regex;

import java.text.CharacterIterator;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.util.IEEEDouble;

/* loaded from: classes11.dex */
public final class REUtil {
    static final int CACHESIZE = 20;
    static final RegularExpression[] regexCache = new RegularExpression[20];

    private REUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final int composeFromSurrogates(int high, int low) {
        return ((((high - 55296) << 10) + 65536) + low) - 56320;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final boolean isLowSurrogate(int ch) {
        return (64512 & ch) == 56320;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final boolean isHighSurrogate(int ch) {
        return (64512 & ch) == 55296;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final String decomposeToSurrogates(int ch) {
        int ch2 = ch - 65536;
        char[] chs = {(char) ((ch2 >> 10) + 55296), (char) ((ch2 & IEEEDouble.EXPONENT_BIAS) + 56320)};
        return new String(chs);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final String substring(CharacterIterator iterator, int begin, int end) {
        char[] src = new char[end - begin];
        for (int i = 0; i < src.length; i++) {
            src[i] = iterator.setIndex(i + begin);
        }
        return new String(src);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final int getOptionValue(int ch) {
        switch (ch) {
            case 44:
                return 1024;
            case 70:
                return 256;
            case 72:
                return 128;
            case 88:
                return 512;
            case 105:
                return 2;
            case 109:
                return 8;
            case 115:
                return 4;
            case 117:
                return 32;
            case 119:
                return 64;
            case 120:
                return 16;
            default:
                return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final int parseOptions(String opts) throws ParseException {
        if (opts == null) {
            return 0;
        }
        int options = 0;
        for (int i = 0; i < opts.length(); i++) {
            int v = getOptionValue(opts.charAt(i));
            if (v == 0) {
                throw new ParseException("Unknown Option: " + opts.substring(i), -1);
            }
            options |= v;
        }
        return options;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final String createOptionString(int options) {
        StringBuilder sb = new StringBuilder(9);
        if ((options & 256) != 0) {
            sb.append('F');
        }
        if ((options & 128) != 0) {
            sb.append('H');
        }
        if ((options & 512) != 0) {
            sb.append('X');
        }
        if ((options & 2) != 0) {
            sb.append('i');
        }
        if ((options & 8) != 0) {
            sb.append('m');
        }
        if ((options & 4) != 0) {
            sb.append('s');
        }
        if ((options & 32) != 0) {
            sb.append('u');
        }
        if ((options & 64) != 0) {
            sb.append('w');
        }
        if ((options & 16) != 0) {
            sb.append('x');
        }
        if ((options & 1024) != 0) {
            sb.append(',');
        }
        return sb.toString().intern();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String stripExtendedComment(String regex) {
        int len = regex.length();
        StringBuilder buffer = new StringBuilder(len);
        int ch = 0;
        while (ch < len) {
            int offset = ch + 1;
            int ch2 = regex.charAt(ch);
            if (ch2 == 9 || ch2 == 10 || ch2 == 12 || ch2 == 13 || ch2 == 32) {
                ch = offset;
            } else if (ch2 == 35) {
                ch = offset;
                while (ch < len) {
                    int offset2 = ch + 1;
                    int ch3 = regex.charAt(ch);
                    if (ch3 == 13 || ch3 == 10) {
                        ch = offset2;
                        break;
                    }
                    ch = offset2;
                }
            } else if (ch2 == 92 && offset < len) {
                int next = regex.charAt(offset);
                if (next == 35 || next == 9 || next == 10 || next == 12 || next == 13 || next == 32) {
                    buffer.append((char) next);
                    ch = offset + 1;
                } else {
                    buffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                    buffer.append((char) next);
                    ch = offset + 1;
                }
            } else {
                buffer.append((char) ch2);
                ch = offset;
            }
        }
        return buffer.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0109, code lost:
    
        r2 = r11[r5];
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void main(java.lang.String[] r11) {
        /*
            Method dump skipped, instructions count: 573
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.regex.REUtil.main(java.lang.String[]):void");
    }

    public static RegularExpression createRegex(String pattern, String options) throws ParseException {
        RegularExpression re = null;
        int intOptions = parseOptions(options);
        synchronized (regexCache) {
            int i = 0;
            while (true) {
                if (i >= 20) {
                    break;
                }
                try {
                    RegularExpression cached = regexCache[i];
                    if (cached == null) {
                        i = -1;
                        break;
                    }
                    if (!cached.equals(pattern, intOptions)) {
                        i++;
                    } else {
                        re = cached;
                        break;
                    }
                } finally {
                }
            }
            if (re != null) {
                if (i != 0) {
                    System.arraycopy(regexCache, 0, regexCache, 1, i);
                    regexCache[0] = re;
                }
            } else {
                re = new RegularExpression(pattern, options);
                System.arraycopy(regexCache, 0, regexCache, 1, 19);
                regexCache[0] = re;
            }
        }
        return re;
    }

    public static boolean matches(String regex, String target) throws ParseException {
        return createRegex(regex, null).matches(target);
    }

    public static boolean matches(String regex, String options, String target) throws ParseException {
        return createRegex(regex, options).matches(target);
    }

    public static String quoteMeta(String literal) {
        int len = literal.length();
        StringBuilder buffer = null;
        for (int i = 0; i < len; i++) {
            int ch = literal.charAt(i);
            if (".*+?{[()|\\^$".indexOf(ch) >= 0) {
                if (buffer == null) {
                    buffer = new StringBuilder(((len - i) * 2) + i);
                    if (i > 0) {
                        buffer.append(literal.substring(0, i));
                    }
                }
                buffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                buffer.append((char) ch);
            } else if (buffer != null) {
                buffer.append((char) ch);
            }
        }
        return buffer != null ? buffer.toString() : literal;
    }

    static void dumpString(String v) {
        for (int i = 0; i < v.length(); i++) {
            System.out.print(Integer.toHexString(v.charAt(i)));
            System.out.print(StringUtils.SPACE);
        }
        System.out.println();
    }
}
