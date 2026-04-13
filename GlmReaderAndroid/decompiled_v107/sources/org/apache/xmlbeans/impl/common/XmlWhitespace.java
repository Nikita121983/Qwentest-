package org.apache.xmlbeans.impl.common;

import org.apache.logging.log4j.util.Chars;

/* loaded from: classes11.dex */
public class XmlWhitespace {
    public static final int WS_COLLAPSE = 3;
    public static final int WS_PRESERVE = 1;
    public static final int WS_REPLACE = 2;
    public static final int WS_UNSPECIFIED = 0;

    public static boolean isSpace(char ch) {
        switch (ch) {
            case '\t':
            case '\n':
            case '\r':
            case ' ':
                return true;
            default:
                return false;
        }
    }

    public static boolean isAllSpace(String v) {
        int len = v.length();
        for (int i = 0; i < len; i++) {
            if (!isSpace(v.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAllSpace(CharSequence v) {
        int len = v.length();
        for (int i = 0; i < len; i++) {
            if (!isSpace(v.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String collapse(String v) {
        return collapse(v, 3);
    }

    public static String collapse(String v, int wsr) {
        String v2;
        int i;
        if (wsr == 1 || wsr == 0) {
            return v;
        }
        if (v.indexOf(10) >= 0) {
            v = v.replace('\n', Chars.SPACE);
        }
        if (v.indexOf(9) >= 0) {
            v = v.replace('\t', Chars.SPACE);
        }
        if (v.indexOf(13) < 0) {
            v2 = v;
        } else {
            v2 = v.replace('\r', Chars.SPACE);
        }
        if (wsr == 2) {
            return v2;
        }
        int j = 0;
        int len = v2.length();
        if (len == 0) {
            return v2;
        }
        if (v2.charAt(0) != ' ') {
            j = 2;
            while (true) {
                if (j < len) {
                    if (v2.charAt(j) == ' ') {
                        if (v2.charAt(j - 1) == ' ' || j == len - 1) {
                            break;
                        }
                        j++;
                        if (v2.charAt(j) == ' ') {
                            break;
                        }
                    }
                    j += 2;
                } else if (j != len || v2.charAt(j - 1) != ' ') {
                    return v2;
                }
            }
            i = j;
        } else {
            while (j + 1 < v2.length() && v2.charAt(j + 1) == ' ') {
                j++;
            }
            i = 0;
        }
        char[] ch = v2.toCharArray();
        loop1: while (true) {
            j++;
            if (j >= len) {
                break;
            }
            if (v2.charAt(j) != ' ') {
                while (true) {
                    int i2 = i + 1;
                    int j2 = j + 1;
                    ch[i] = ch[j];
                    if (j2 >= len) {
                        i = i2;
                        break loop1;
                    }
                    if (ch[j2] != ' ') {
                        i = i2;
                        j = j2;
                    } else {
                        i = i2 + 1;
                        j = j2 + 1;
                        ch[i2] = ch[j2];
                        if (j >= len) {
                            break loop1;
                        }
                        if (ch[j] == ' ') {
                            break;
                        }
                    }
                }
            }
        }
        return new String(ch, 0, (i == 0 || ch[i + (-1)] != ' ') ? i : i - 1);
    }
}
