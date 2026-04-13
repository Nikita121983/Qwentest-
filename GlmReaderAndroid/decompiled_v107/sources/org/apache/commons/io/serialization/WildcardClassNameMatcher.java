package org.apache.commons.io.serialization;

import org.apache.commons.io.FilenameUtils;

/* loaded from: classes9.dex */
final class WildcardClassNameMatcher implements ClassNameMatcher {
    private final String pattern;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WildcardClassNameMatcher(String pattern) {
        this.pattern = pattern;
    }

    @Override // org.apache.commons.io.serialization.ClassNameMatcher
    public boolean matches(String className) {
        return FilenameUtils.wildcardMatch(className, this.pattern);
    }
}
