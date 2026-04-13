package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.commons.io.FileUtils$$ExternalSyntheticLambda19;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.file.PathUtils;

/* loaded from: classes9.dex */
public class PrefixFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 8533897440809599867L;
    private final IOCase isCase;
    private final String[] prefixes;

    public PrefixFileFilter(List<String> prefixes) {
        this(prefixes, IOCase.SENSITIVE);
    }

    public PrefixFileFilter(List<String> prefixes, IOCase ioCase) {
        Objects.requireNonNull(prefixes, "prefixes");
        this.prefixes = (String[]) prefixes.toArray(EMPTY_STRING_ARRAY);
        this.isCase = IOCase.value(ioCase, IOCase.SENSITIVE);
    }

    public PrefixFileFilter(String prefix) {
        this(prefix, IOCase.SENSITIVE);
    }

    public PrefixFileFilter(String... prefixes) {
        this(prefixes, IOCase.SENSITIVE);
    }

    public PrefixFileFilter(String prefix, IOCase ioCase) {
        Objects.requireNonNull(prefix, "prefix");
        this.prefixes = new String[]{prefix};
        this.isCase = IOCase.value(ioCase, IOCase.SENSITIVE);
    }

    public PrefixFileFilter(String[] prefixes, IOCase ioCase) {
        Objects.requireNonNull(prefixes, "prefixes");
        this.prefixes = (String[]) prefixes.clone();
        this.isCase = IOCase.value(ioCase, IOCase.SENSITIVE);
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter, org.apache.commons.io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return accept(file == null ? null : file.getName());
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter, org.apache.commons.io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File file, String name) {
        return accept(name);
    }

    @Override // org.apache.commons.io.filefilter.IOFileFilter, org.apache.commons.io.file.PathFilter
    public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
        return toFileVisitResult(accept((File) PathUtils.getFileName(file, new FileUtils$$ExternalSyntheticLambda19())));
    }

    private boolean accept(final String name) {
        return Stream.of((Object[]) this.prefixes).anyMatch(new Predicate() { // from class: org.apache.commons.io.filefilter.PrefixFileFilter$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return PrefixFileFilter.this.m2121x16e103a0(name, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$accept$0$org-apache-commons-io-filefilter-PrefixFileFilter, reason: not valid java name */
    public /* synthetic */ boolean m2121x16e103a0(String name, String prefix) {
        return this.isCase.checkStartsWith(name, prefix);
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(super.toString());
        buffer.append("(");
        append(this.prefixes, buffer);
        buffer.append(")");
        return buffer.toString();
    }
}
