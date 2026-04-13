package org.apache.commons.lang3;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* loaded from: classes9.dex */
public class CharSet implements Serializable {
    private static final long serialVersionUID = 5947847346149275958L;
    private final Set<CharRange> set = Collections.synchronizedSet(new HashSet());
    public static final CharSet EMPTY = new CharSet(null);
    public static final CharSet ASCII_ALPHA = new CharSet("a-zA-Z");
    public static final CharSet ASCII_ALPHA_LOWER = new CharSet("a-z");
    public static final CharSet ASCII_ALPHA_UPPER = new CharSet("A-Z");
    public static final CharSet ASCII_NUMERIC = new CharSet("0-9");
    protected static final Map<String, CharSet> COMMON = Collections.synchronizedMap(new HashMap());

    static {
        COMMON.put(null, EMPTY);
        COMMON.put("", EMPTY);
        COMMON.put("a-zA-Z", ASCII_ALPHA);
        COMMON.put("A-Za-z", ASCII_ALPHA);
        COMMON.put("a-z", ASCII_ALPHA_LOWER);
        COMMON.put("A-Z", ASCII_ALPHA_UPPER);
        COMMON.put("0-9", ASCII_NUMERIC);
    }

    public static CharSet getInstance(String... setStrs) {
        CharSet common;
        if (setStrs == null) {
            return null;
        }
        if (setStrs.length == 1 && (common = COMMON.get(setStrs[0])) != null) {
            return common;
        }
        return new CharSet(setStrs);
    }

    protected CharSet(String... set) {
        Stream.of((Object[]) set).forEach(new Consumer() { // from class: org.apache.commons.lang3.CharSet$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CharSet.this.add((String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void add(String str) {
        if (str == null) {
            return;
        }
        int len = str.length();
        int pos = 0;
        while (pos < len) {
            int remainder = len - pos;
            if (remainder >= 4 && str.charAt(pos) == '^' && str.charAt(pos + 2) == '-') {
                this.set.add(CharRange.isNotIn(str.charAt(pos + 1), str.charAt(pos + 3)));
                pos += 4;
            } else if (remainder >= 3 && str.charAt(pos + 1) == '-') {
                this.set.add(CharRange.isIn(str.charAt(pos), str.charAt(pos + 2)));
                pos += 3;
            } else if (remainder >= 2 && str.charAt(pos) == '^') {
                this.set.add(CharRange.isNot(str.charAt(pos + 1)));
                pos += 2;
            } else {
                this.set.add(CharRange.is(str.charAt(pos)));
                pos++;
            }
        }
    }

    public boolean contains(final char ch) {
        boolean anyMatch;
        synchronized (this.set) {
            anyMatch = this.set.stream().anyMatch(new Predicate() { // from class: org.apache.commons.lang3.CharSet$$ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean contains;
                    contains = ((CharRange) obj).contains(ch);
                    return contains;
                }
            });
        }
        return anyMatch;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CharSet)) {
            return false;
        }
        CharSet other = (CharSet) obj;
        return this.set.equals(other.set);
    }

    CharRange[] getCharRanges() {
        return (CharRange[]) this.set.toArray(CharRange.EMPTY_ARRAY);
    }

    public int hashCode() {
        return this.set.hashCode() + 89;
    }

    public String toString() {
        return this.set.toString();
    }
}
