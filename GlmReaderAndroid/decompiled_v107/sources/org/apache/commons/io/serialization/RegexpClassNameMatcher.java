package org.apache.commons.io.serialization;

import java.util.Objects;
import java.util.regex.Pattern;

/* loaded from: classes9.dex */
final class RegexpClassNameMatcher implements ClassNameMatcher {
    private final Pattern pattern;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RegexpClassNameMatcher(Pattern pattern) {
        this.pattern = (Pattern) Objects.requireNonNull(pattern, "pattern");
    }

    RegexpClassNameMatcher(String regex) {
        this(Pattern.compile(regex));
    }

    @Override // org.apache.commons.io.serialization.ClassNameMatcher
    public boolean matches(String className) {
        return this.pattern.matcher(className).matches();
    }
}
