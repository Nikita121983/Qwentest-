package org.apache.commons.lang3;

import org.apache.commons.lang3.builder.AbstractSupplier;
import org.apache.commons.lang3.function.ToBooleanBiFunction;

/* loaded from: classes9.dex */
public abstract class Strings {
    public static final Strings CI;
    public static final Strings CS;
    private final boolean ignoreCase;
    private final boolean nullIsLess;

    public abstract int compare(String str, String str2);

    public abstract boolean contains(CharSequence charSequence, CharSequence charSequence2);

    public abstract boolean equals(CharSequence charSequence, CharSequence charSequence2);

    public abstract boolean equals(String str, String str2);

    public abstract int indexOf(CharSequence charSequence, CharSequence charSequence2, int i);

    public abstract int lastIndexOf(CharSequence charSequence, CharSequence charSequence2, int i);

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractSupplier<Strings, Builder, RuntimeException> {
        private boolean ignoreCase;
        private boolean nullIsLess;

        private Builder() {
        }

        @Override // org.apache.commons.lang3.function.FailableSupplier
        public Strings get() {
            return this.ignoreCase ? new CiStrings(this.nullIsLess) : new CsStrings(this.nullIsLess);
        }

        public Builder setIgnoreCase(boolean ignoreCase) {
            this.ignoreCase = ignoreCase;
            return asThis();
        }

        public Builder setNullIsLess(boolean nullIsLess) {
            this.nullIsLess = nullIsLess;
            return asThis();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class CiStrings extends Strings {
        private CiStrings(boolean nullIsLess) {
            super(true, nullIsLess);
        }

        @Override // org.apache.commons.lang3.Strings
        public int compare(String s1, String s2) {
            if (s1 == s2) {
                return 0;
            }
            if (s1 == null) {
                return isNullIsLess() ? -1 : 1;
            }
            if (s2 == null) {
                return isNullIsLess() ? 1 : -1;
            }
            return s1.compareToIgnoreCase(s2);
        }

        @Override // org.apache.commons.lang3.Strings
        public boolean contains(CharSequence str, CharSequence searchStr) {
            if (str != null && searchStr != null) {
                int len = searchStr.length();
                int max = str.length() - len;
                int i = 0;
                while (i <= max) {
                    CharSequence str2 = str;
                    CharSequence searchStr2 = searchStr;
                    if (!CharSequenceUtils.regionMatches(str2, true, i, searchStr2, 0, len)) {
                        i++;
                        str = str2;
                        searchStr = searchStr2;
                    } else {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }

        @Override // org.apache.commons.lang3.Strings
        public boolean equals(CharSequence cs1, CharSequence cs2) {
            if (cs1 == cs2) {
                return true;
            }
            if (cs1 == null || cs2 == null || cs1.length() != cs2.length()) {
                return false;
            }
            return CharSequenceUtils.regionMatches(cs1, true, 0, cs2, 0, cs1.length());
        }

        @Override // org.apache.commons.lang3.Strings
        public boolean equals(String s1, String s2) {
            return s1 == null ? s2 == null : s1.equalsIgnoreCase(s2);
        }

        @Override // org.apache.commons.lang3.Strings
        public int indexOf(CharSequence str, CharSequence searchStr, int startPos) {
            if (str != null && searchStr != null) {
                if (startPos < 0) {
                    startPos = 0;
                }
                int endLimit = (str.length() - searchStr.length()) + 1;
                if (startPos > endLimit) {
                    return -1;
                }
                if (searchStr.length() == 0) {
                    return startPos;
                }
                int i = startPos;
                while (i < endLimit) {
                    CharSequence str2 = str;
                    CharSequence searchStr2 = searchStr;
                    if (!CharSequenceUtils.regionMatches(str2, true, i, searchStr2, 0, searchStr.length())) {
                        i++;
                        str = str2;
                        searchStr = searchStr2;
                    } else {
                        return i;
                    }
                }
                return -1;
            }
            return -1;
        }

        @Override // org.apache.commons.lang3.Strings
        public int lastIndexOf(CharSequence str, CharSequence searchStr, int startPos) {
            if (str != null && searchStr != null) {
                int searchStrLength = searchStr.length();
                int strLength = str.length();
                if (startPos > strLength - searchStrLength) {
                    startPos = strLength - searchStrLength;
                }
                if (startPos < 0) {
                    return -1;
                }
                if (searchStrLength == 0) {
                    return startPos;
                }
                int i = startPos;
                while (i >= 0) {
                    CharSequence str2 = str;
                    CharSequence searchStr2 = searchStr;
                    if (!CharSequenceUtils.regionMatches(str2, true, i, searchStr2, 0, searchStrLength)) {
                        i--;
                        str = str2;
                        searchStr = searchStr2;
                    } else {
                        return i;
                    }
                }
                return -1;
            }
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class CsStrings extends Strings {
        private CsStrings(boolean nullIsLess) {
            super(false, nullIsLess);
        }

        @Override // org.apache.commons.lang3.Strings
        public int compare(String s1, String s2) {
            if (s1 == s2) {
                return 0;
            }
            if (s1 == null) {
                return isNullIsLess() ? -1 : 1;
            }
            if (s2 == null) {
                return isNullIsLess() ? 1 : -1;
            }
            return s1.compareTo(s2);
        }

        @Override // org.apache.commons.lang3.Strings
        public boolean contains(CharSequence seq, CharSequence searchSeq) {
            return CharSequenceUtils.indexOf(seq, searchSeq, 0) >= 0;
        }

        @Override // org.apache.commons.lang3.Strings
        public boolean equals(CharSequence cs1, CharSequence cs2) {
            if (cs1 == cs2) {
                return true;
            }
            if (cs1 == null || cs2 == null || cs1.length() != cs2.length()) {
                return false;
            }
            if ((cs1 instanceof String) && (cs2 instanceof String)) {
                return cs1.equals(cs2);
            }
            int length = cs1.length();
            for (int i = 0; i < length; i++) {
                if (cs1.charAt(i) != cs2.charAt(i)) {
                    return false;
                }
            }
            return true;
        }

        @Override // org.apache.commons.lang3.Strings
        public boolean equals(String s1, String s2) {
            return Strings.eq(s1, s2);
        }

        @Override // org.apache.commons.lang3.Strings
        public int indexOf(CharSequence seq, CharSequence searchSeq, int startPos) {
            return CharSequenceUtils.indexOf(seq, searchSeq, startPos);
        }

        @Override // org.apache.commons.lang3.Strings
        public int lastIndexOf(CharSequence seq, CharSequence searchSeq, int startPos) {
            return CharSequenceUtils.lastIndexOf(seq, searchSeq, startPos);
        }
    }

    static {
        boolean z = true;
        CI = new CiStrings(z);
        CS = new CsStrings(z);
    }

    public static final Builder builder() {
        return new Builder();
    }

    private static boolean containsAny(ToBooleanBiFunction<CharSequence, CharSequence> test, CharSequence cs, CharSequence... searchCharSequences) {
        if (StringUtils.isEmpty(cs) || ArrayUtils.isEmpty(searchCharSequences)) {
            return false;
        }
        for (CharSequence searchCharSequence : searchCharSequences) {
            if (test.applyAsBoolean(cs, searchCharSequence)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean eq(Object o1, Object o2) {
        return o1 == null ? o2 == null : o1.equals(o2);
    }

    private Strings(boolean ignoreCase, boolean nullIsLess) {
        this.ignoreCase = ignoreCase;
        this.nullIsLess = nullIsLess;
    }

    public String appendIfMissing(String str, CharSequence suffix, CharSequence... suffixes) {
        if (str == null || StringUtils.isEmpty(suffix) || endsWith(str, suffix)) {
            return str;
        }
        if (ArrayUtils.isNotEmpty(suffixes)) {
            for (CharSequence s : suffixes) {
                if (endsWith(str, s)) {
                    return str;
                }
            }
        }
        return str + ((Object) suffix);
    }

    public boolean containsAny(CharSequence cs, CharSequence... searchCharSequences) {
        return containsAny(new ToBooleanBiFunction() { // from class: org.apache.commons.lang3.Strings$$ExternalSyntheticLambda0
            @Override // org.apache.commons.lang3.function.ToBooleanBiFunction
            public final boolean applyAsBoolean(Object obj, Object obj2) {
                return Strings.this.contains((CharSequence) obj, (CharSequence) obj2);
            }
        }, cs, searchCharSequences);
    }

    public boolean endsWith(CharSequence str, CharSequence suffix) {
        CharSequence str2;
        CharSequence suffix2;
        if (str == null) {
            str2 = str;
            suffix2 = suffix;
        } else {
            if (suffix != null) {
                int sufLen = suffix.length();
                if (sufLen > str.length()) {
                    return false;
                }
                return CharSequenceUtils.regionMatches(str, this.ignoreCase, str.length() - sufLen, suffix, 0, sufLen);
            }
            str2 = str;
            suffix2 = suffix;
        }
        return str2 == suffix2;
    }

    public boolean endsWithAny(CharSequence sequence, CharSequence... searchStrings) {
        if (StringUtils.isEmpty(sequence) || ArrayUtils.isEmpty(searchStrings)) {
            return false;
        }
        for (CharSequence searchString : searchStrings) {
            if (endsWith(sequence, searchString)) {
                return true;
            }
        }
        return false;
    }

    public boolean equalsAny(CharSequence string, CharSequence... searchStrings) {
        if (ArrayUtils.isNotEmpty(searchStrings)) {
            for (CharSequence next : searchStrings) {
                if (equals(string, next)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int indexOf(CharSequence seq, CharSequence searchSeq) {
        return indexOf(seq, searchSeq, 0);
    }

    public boolean isCaseSensitive() {
        return !this.ignoreCase;
    }

    boolean isNullIsLess() {
        return this.nullIsLess;
    }

    public int lastIndexOf(CharSequence str, CharSequence searchStr) {
        if (str == null) {
            return -1;
        }
        return lastIndexOf(str, searchStr, str.length());
    }

    public String prependIfMissing(String str, CharSequence prefix, CharSequence... prefixes) {
        if (str == null || StringUtils.isEmpty(prefix) || startsWith(str, prefix)) {
            return str;
        }
        if (ArrayUtils.isNotEmpty(prefixes)) {
            for (CharSequence p : prefixes) {
                if (startsWith(str, p)) {
                    return str;
                }
            }
        }
        return ((Object) prefix) + str;
    }

    public String remove(String str, String remove) {
        return replace(str, remove, "", -1);
    }

    public String removeEnd(String str, CharSequence remove) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(remove)) {
            return str;
        }
        if (endsWith(str, remove)) {
            return str.substring(0, str.length() - remove.length());
        }
        return str;
    }

    public String removeStart(String str, CharSequence remove) {
        if (str != null && startsWith(str, remove)) {
            return str.substring(StringUtils.length(remove));
        }
        return str;
    }

    public String replace(String text, String searchString, String replacement) {
        return replace(text, searchString, replacement, -1);
    }

    public String replace(String text, String searchString, String replacement, int max) {
        if (StringUtils.isEmpty(text) || StringUtils.isEmpty(searchString) || replacement == null || max == 0) {
            return text;
        }
        if (this.ignoreCase) {
            searchString = searchString.toLowerCase();
        }
        int start = 0;
        int end = indexOf(text, searchString, 0);
        if (end == -1) {
            return text;
        }
        int replLength = searchString.length();
        int increase = Math.max(replacement.length() - replLength, 0);
        StringBuilder buf = new StringBuilder(text.length() + (increase * (max < 0 ? 16 : Math.min(max, 64))));
        while (end != -1) {
            buf.append((CharSequence) text, start, end).append(replacement);
            start = end + replLength;
            max--;
            if (max == 0) {
                break;
            }
            end = indexOf(text, searchString, start);
        }
        buf.append((CharSequence) text, start, text.length());
        return buf.toString();
    }

    public String replaceOnce(String text, String searchString, String replacement) {
        return replace(text, searchString, replacement, 1);
    }

    public boolean startsWith(CharSequence str, CharSequence prefix) {
        CharSequence str2;
        CharSequence prefix2;
        if (str == null) {
            str2 = str;
            prefix2 = prefix;
        } else {
            if (prefix != null) {
                int preLen = prefix.length();
                if (preLen > str.length()) {
                    return false;
                }
                return CharSequenceUtils.regionMatches(str, this.ignoreCase, 0, prefix, 0, preLen);
            }
            str2 = str;
            prefix2 = prefix;
        }
        return str2 == prefix2;
    }

    public boolean startsWithAny(CharSequence sequence, CharSequence... searchStrings) {
        if (StringUtils.isEmpty(sequence) || ArrayUtils.isEmpty(searchStrings)) {
            return false;
        }
        for (CharSequence searchString : searchStrings) {
            if (startsWith(sequence, searchString)) {
                return true;
            }
        }
        return false;
    }
}
