package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Pattern;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOCase;

/* loaded from: classes9.dex */
public class RegexFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 4269646126155225062L;
    private final transient Function<Path, String> pathToString;
    private final Pattern pattern;

    private static Pattern compile(String pattern, int flags) {
        Objects.requireNonNull(pattern, "pattern");
        return Pattern.compile(pattern, flags);
    }

    private static int toFlags(IOCase ioCase) {
        return IOCase.isCaseSensitive(ioCase) ? 0 : 2;
    }

    public RegexFileFilter(Pattern pattern) {
        this(pattern, new RegexFileFilter$$ExternalSyntheticLambda1());
    }

    public RegexFileFilter(Pattern pattern, Function<Path, String> pathToString) {
        Objects.requireNonNull(pattern, "pattern");
        this.pattern = pattern;
        this.pathToString = pathToString != null ? pathToString : new Function() { // from class: org.apache.commons.io.filefilter.RegexFileFilter$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String objects;
                objects = Objects.toString((Path) obj);
                return objects;
            }
        };
    }

    public RegexFileFilter(String pattern) {
        this(pattern, 0);
    }

    public RegexFileFilter(String pattern, int flags) {
        this(compile(pattern, flags));
    }

    public RegexFileFilter(String pattern, IOCase ioCase) {
        this(compile(pattern, toFlags(ioCase)));
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter, org.apache.commons.io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File dir, String name) {
        return this.pattern.matcher(name).matches();
    }

    @Override // org.apache.commons.io.filefilter.IOFileFilter, org.apache.commons.io.file.PathFilter
    public FileVisitResult accept(Path path, BasicFileAttributes attributes) {
        String result = this.pathToString.apply(path);
        return toFileVisitResult(result != null && this.pattern.matcher(result).matches());
    }

    @Override // org.apache.commons.io.filefilter.AbstractFileFilter
    public String toString() {
        return "RegexFileFilter [pattern=" + this.pattern + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }
}
