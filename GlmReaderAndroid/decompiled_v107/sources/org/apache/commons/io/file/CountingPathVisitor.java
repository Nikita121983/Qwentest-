package org.apache.commons.io.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;
import java.util.function.UnaryOperator;
import org.apache.commons.io.build.AbstractSupplier;
import org.apache.commons.io.file.Counters;
import org.apache.commons.io.file.SimplePathVisitor;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SymbolicLinkFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.function.IOBiFunction;

/* loaded from: classes9.dex */
public class CountingPathVisitor extends SimplePathVisitor {
    static final String[] EMPTY_STRING_ARRAY = new String[0];
    private final PathFilter directoryFilter;
    private final UnaryOperator<Path> directoryPostTransformer;
    private final PathFilter fileFilter;
    private final Counters.PathCounters pathCounters;

    /* loaded from: classes9.dex */
    public static abstract class AbstractBuilder<T, B extends AbstractBuilder<T, B>> extends SimplePathVisitor.AbstractBuilder<T, B> {
        private Counters.PathCounters pathCounters = CountingPathVisitor.defaultPathCounters();
        private PathFilter fileFilter = CountingPathVisitor.defaultFileFilter();
        private PathFilter directoryFilter = CountingPathVisitor.defaultDirectoryFilter();
        private UnaryOperator<Path> directoryPostTransformer = CountingPathVisitor.defaultDirectoryTransformer();

        @Override // org.apache.commons.io.file.SimplePathVisitor.AbstractBuilder
        public /* bridge */ /* synthetic */ AbstractSupplier setVisitFileFailedFunction(IOBiFunction iOBiFunction) {
            return super.setVisitFileFailedFunction(iOBiFunction);
        }

        PathFilter getDirectoryFilter() {
            return this.directoryFilter;
        }

        UnaryOperator<Path> getDirectoryPostTransformer() {
            return this.directoryPostTransformer;
        }

        PathFilter getFileFilter() {
            return this.fileFilter;
        }

        Counters.PathCounters getPathCounters() {
            return this.pathCounters;
        }

        public B setDirectoryFilter(PathFilter directoryFilter) {
            this.directoryFilter = directoryFilter != null ? directoryFilter : CountingPathVisitor.defaultDirectoryFilter();
            return (B) asThis();
        }

        public B setDirectoryPostTransformer(UnaryOperator<Path> directoryTransformer) {
            this.directoryPostTransformer = directoryTransformer != null ? directoryTransformer : CountingPathVisitor.defaultDirectoryTransformer();
            return (B) asThis();
        }

        public B setFileFilter(PathFilter fileFilter) {
            this.fileFilter = fileFilter != null ? fileFilter : CountingPathVisitor.defaultFileFilter();
            return (B) asThis();
        }

        public B setPathCounters(Counters.PathCounters pathCounters) {
            this.pathCounters = pathCounters != null ? pathCounters : CountingPathVisitor.defaultPathCounters();
            return (B) asThis();
        }
    }

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractBuilder<CountingPathVisitor, Builder> {
        @Override // org.apache.commons.io.function.IOSupplier
        public CountingPathVisitor get() {
            return new CountingPathVisitor(this);
        }
    }

    static IOFileFilter defaultDirectoryFilter() {
        return TrueFileFilter.INSTANCE;
    }

    static UnaryOperator<Path> defaultDirectoryTransformer() {
        return UnaryOperator.identity();
    }

    static IOFileFilter defaultFileFilter() {
        return new SymbolicLinkFileFilter(FileVisitResult.TERMINATE, FileVisitResult.CONTINUE);
    }

    static Counters.PathCounters defaultPathCounters() {
        return Counters.longPathCounters();
    }

    public static CountingPathVisitor withBigIntegerCounters() {
        return new Builder().setPathCounters(Counters.bigIntegerPathCounters()).get();
    }

    public static CountingPathVisitor withLongCounters() {
        return new Builder().setPathCounters(Counters.longPathCounters()).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CountingPathVisitor(AbstractBuilder<?, ?> builder) {
        super(builder);
        this.pathCounters = builder.getPathCounters();
        this.fileFilter = builder.getFileFilter();
        this.directoryFilter = builder.getDirectoryFilter();
        this.directoryPostTransformer = builder.getDirectoryPostTransformer();
    }

    public CountingPathVisitor(Counters.PathCounters pathCounters) {
        this(new Builder().setPathCounters(pathCounters));
    }

    public CountingPathVisitor(Counters.PathCounters pathCounters, PathFilter fileFilter, PathFilter directoryFilter) {
        this.pathCounters = (Counters.PathCounters) Objects.requireNonNull(pathCounters, "pathCounters");
        this.fileFilter = (PathFilter) Objects.requireNonNull(fileFilter, "fileFilter");
        this.directoryFilter = (PathFilter) Objects.requireNonNull(directoryFilter, "directoryFilter");
        this.directoryPostTransformer = UnaryOperator.identity();
    }

    @Deprecated
    public CountingPathVisitor(Counters.PathCounters pathCounters, PathFilter fileFilter, PathFilter directoryFilter, IOBiFunction<Path, IOException, FileVisitResult> visitFileFailed) {
        super(visitFileFailed);
        this.pathCounters = (Counters.PathCounters) Objects.requireNonNull(pathCounters, "pathCounters");
        this.fileFilter = (PathFilter) Objects.requireNonNull(fileFilter, "fileFilter");
        this.directoryFilter = (PathFilter) Objects.requireNonNull(directoryFilter, "directoryFilter");
        this.directoryPostTransformer = UnaryOperator.identity();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean accept(Path file, BasicFileAttributes attributes) {
        return Files.exists(file, new LinkOption[0]) && this.fileFilter.accept(file, attributes) == FileVisitResult.CONTINUE;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CountingPathVisitor)) {
            return false;
        }
        CountingPathVisitor other = (CountingPathVisitor) obj;
        return Objects.equals(this.pathCounters, other.pathCounters);
    }

    public Counters.PathCounters getPathCounters() {
        return this.pathCounters;
    }

    public int hashCode() {
        return Objects.hash(this.pathCounters);
    }

    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        updateDirCounter((Path) this.directoryPostTransformer.apply(dir), exc);
        return FileVisitResult.CONTINUE;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attributes) throws IOException {
        FileVisitResult accept = this.directoryFilter.accept(dir, attributes);
        return accept != FileVisitResult.CONTINUE ? FileVisitResult.SKIP_SUBTREE : FileVisitResult.CONTINUE;
    }

    public String toString() {
        return this.pathCounters.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateDirCounter(Path dir, IOException exc) {
        this.pathCounters.getDirectoryCounter().increment();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateFileCounters(Path file, BasicFileAttributes attributes) {
        this.pathCounters.getFileCounter().increment();
        this.pathCounters.getByteCounter().add(attributes.size());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
        if (accept(file, attributes)) {
            updateFileCounters(file, attributes);
        }
        return FileVisitResult.CONTINUE;
    }
}
