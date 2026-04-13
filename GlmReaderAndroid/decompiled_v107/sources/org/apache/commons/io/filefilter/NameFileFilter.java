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
import org.apache.commons.io.IOCase;
import org.apache.commons.io.file.PathUtils;

/* loaded from: classes9.dex */
public class NameFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 176844364689077340L;
    private final IOCase ioCase;
    private final String[] names;

    public NameFileFilter(List<String> names) {
        this(names, (IOCase) null);
    }

    public NameFileFilter(List<String> names, IOCase ioCase) {
        Objects.requireNonNull(names, "names");
        this.names = (String[]) names.toArray(EMPTY_STRING_ARRAY);
        this.ioCase = toIOCase(ioCase);
    }

    public NameFileFilter(String name) {
        this(name, IOCase.SENSITIVE);
    }

    public NameFileFilter(String... names) {
        this(names, IOCase.SENSITIVE);
    }

    public NameFileFilter(String name, IOCase ioCase) {
        Objects.requireNonNull(name, "name");
        this.names = new String[]{name};
        this.ioCase = toIOCase(ioCase);
    }

    public NameFileFilter(String[] names, IOCase ioCase) {
        Objects.requireNonNull(names, "names");
        this.names = (String[]) names.clone();
        this.ioCase = toIOCase(ioCase);
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter, org.apache.commons.io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        return file != null && acceptBaseName(file.getName());
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter, org.apache.commons.io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File dir, String name) {
        return acceptBaseName(name);
    }

    @Override // org.apache.commons.io.filefilter.IOFileFilter, org.apache.commons.io.file.PathFilter
    public FileVisitResult accept(Path path, BasicFileAttributes attributes) {
        return toFileVisitResult(acceptBaseName(PathUtils.getFileNameString(path)));
    }

    private boolean acceptBaseName(final String baseName) {
        return Stream.of((Object[]) this.names).anyMatch(new Predicate() { // from class: org.apache.commons.io.filefilter.NameFileFilter$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return NameFileFilter.this.m2119xeb0d6bf5(baseName, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$acceptBaseName$0$org-apache-commons-io-filefilter-NameFileFilter, reason: not valid java name */
    public /* synthetic */ boolean m2119xeb0d6bf5(String baseName, String testName) {
        return this.ioCase.checkEquals(baseName, testName);
    }

    private IOCase toIOCase(IOCase ioCase) {
        return IOCase.value(ioCase, IOCase.SENSITIVE);
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(super.toString());
        buffer.append("(");
        append(this.names, buffer);
        buffer.append(")");
        return buffer.toString();
    }
}
