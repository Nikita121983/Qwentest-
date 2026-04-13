package org.apache.commons.io;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* loaded from: classes9.dex */
public enum IOCase {
    SENSITIVE("Sensitive", true),
    INSENSITIVE("Insensitive", false),
    SYSTEM("System", FileSystem.getCurrent().isCaseSensitive());

    private static final long serialVersionUID = -6343169151696340687L;
    private final String name;
    private final transient boolean sensitive;

    public static IOCase forName(final String name) {
        return (IOCase) Stream.of((Object[]) values()).filter(new Predicate() { // from class: org.apache.commons.io.IOCase$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = ((IOCase) obj).getName().equals(name);
                return equals;
            }
        }).findFirst().orElseThrow(new Supplier() { // from class: org.apache.commons.io.IOCase$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return IOCase.lambda$forName$1(name);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ IllegalArgumentException lambda$forName$1(String name) {
        return new IllegalArgumentException("Illegal IOCase name: " + name);
    }

    public static boolean isCaseSensitive(IOCase ioCase) {
        return ioCase != null && ioCase.isCaseSensitive();
    }

    public static IOCase value(IOCase value, IOCase defaultValue) {
        return value != null ? value : defaultValue;
    }

    IOCase(String name, boolean sensitive) {
        this.name = name;
        this.sensitive = sensitive;
    }

    public int checkCompareTo(String str1, String str2) {
        Objects.requireNonNull(str1, "str1");
        Objects.requireNonNull(str2, "str2");
        return this.sensitive ? str1.compareTo(str2) : str1.compareToIgnoreCase(str2);
    }

    public boolean checkEndsWith(String str, String end) {
        if (str != null && end != null) {
            int endLen = end.length();
            return str.regionMatches(!this.sensitive, str.length() - endLen, end, 0, endLen);
        }
        return false;
    }

    public boolean checkEquals(String str1, String str2) {
        return str1 == str2 || (str1 != null && (!this.sensitive ? !str1.equalsIgnoreCase(str2) : !str1.equals(str2)));
    }

    public int checkIndexOf(String str, int strStartIndex, String search) {
        int endIndex;
        if (str != null && search != null && (endIndex = str.length() - search.length()) >= strStartIndex) {
            for (int i = strStartIndex; i <= endIndex; i++) {
                if (checkRegionMatches(str, i, search)) {
                    return i;
                }
            }
            return -1;
        }
        return -1;
    }

    public boolean checkRegionMatches(String str, int strStartIndex, String search) {
        if (str != null && search != null) {
            if (str.regionMatches(!this.sensitive, strStartIndex, search, 0, search.length())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkStartsWith(String str, String start) {
        if (str != null && start != null) {
            if (str.regionMatches(!this.sensitive, 0, start, 0, start.length())) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCaseSensitive() {
        return this.sensitive;
    }

    private Object readResolve() {
        return forName(this.name);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }
}
