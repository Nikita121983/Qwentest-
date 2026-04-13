package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;

/* loaded from: classes9.dex */
public class FileFilterUtils {
    private static final IOFileFilter CVS_FILTER = notFileFilter(and(directoryFileFilter(), nameFileFilter("CVS")));
    private static final IOFileFilter SVN_FILTER = notFileFilter(and(directoryFileFilter(), nameFileFilter(".svn")));

    public static IOFileFilter ageFileFilter(Date cutoffDate) {
        return new AgeFileFilter(cutoffDate);
    }

    public static IOFileFilter ageFileFilter(Date cutoffDate, boolean acceptOlder) {
        return new AgeFileFilter(cutoffDate, acceptOlder);
    }

    public static IOFileFilter ageFileFilter(File cutoffReference) {
        return new AgeFileFilter(cutoffReference);
    }

    public static IOFileFilter ageFileFilter(File cutoffReference, boolean acceptOlder) {
        return new AgeFileFilter(cutoffReference, acceptOlder);
    }

    public static IOFileFilter ageFileFilter(long cutoffMillis) {
        return new AgeFileFilter(cutoffMillis);
    }

    public static IOFileFilter ageFileFilter(long cutoffMillis, boolean acceptOlder) {
        return new AgeFileFilter(cutoffMillis, acceptOlder);
    }

    public static IOFileFilter and(IOFileFilter... filters) {
        return new AndFileFilter(toList(filters));
    }

    @Deprecated
    public static IOFileFilter andFileFilter(IOFileFilter filter1, IOFileFilter filter2) {
        return new AndFileFilter(filter1, filter2);
    }

    public static IOFileFilter asFileFilter(FileFilter filter) {
        return new DelegateFileFilter(filter);
    }

    public static IOFileFilter asFileFilter(FilenameFilter filter) {
        return new DelegateFileFilter(filter);
    }

    public static IOFileFilter directoryFileFilter() {
        return DirectoryFileFilter.DIRECTORY;
    }

    public static IOFileFilter falseFileFilter() {
        return FalseFileFilter.FALSE;
    }

    public static IOFileFilter fileFileFilter() {
        return FileFileFilter.INSTANCE;
    }

    public static File[] filter(IOFileFilter filter, File... files) {
        Objects.requireNonNull(filter, "filter");
        if (files == null) {
            return FileUtils.EMPTY_FILE_ARRAY;
        }
        return (File[]) ((List) filterFiles(filter, Stream.of((Object[]) files), Collectors.toList())).toArray(FileUtils.EMPTY_FILE_ARRAY);
    }

    public static File[] filter(IOFileFilter filter, Iterable<File> files) {
        return (File[]) filterList(filter, files).toArray(FileUtils.EMPTY_FILE_ARRAY);
    }

    private static <R, A> R filterFiles(final IOFileFilter iOFileFilter, Stream<File> stream, Collector<? super File, A, R> collector) {
        Objects.requireNonNull(iOFileFilter, "filter");
        Objects.requireNonNull(collector, "collector");
        if (stream == null) {
            return (R) Stream.empty().collect(collector);
        }
        Objects.requireNonNull(iOFileFilter);
        return (R) stream.filter(new Predicate() { // from class: org.apache.commons.io.filefilter.FileFilterUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return IOFileFilter.this.accept((File) obj);
            }
        }).collect(collector);
    }

    public static List<File> filterList(IOFileFilter filter, File... files) {
        return Arrays.asList(filter(filter, files));
    }

    public static List<File> filterList(IOFileFilter filter, Iterable<File> files) {
        if (files == null) {
            return Collections.emptyList();
        }
        return (List) filterFiles(filter, StreamSupport.stream(files.spliterator(), false), Collectors.toList());
    }

    public static Set<File> filterSet(IOFileFilter filter, File... files) {
        return new HashSet(Arrays.asList(filter(filter, files)));
    }

    public static Set<File> filterSet(IOFileFilter filter, Iterable<File> files) {
        if (files == null) {
            return Collections.emptySet();
        }
        return (Set) filterFiles(filter, StreamSupport.stream(files.spliterator(), false), Collectors.toSet());
    }

    public static IOFileFilter magicNumberFileFilter(byte[] magicNumber) {
        return new MagicNumberFileFilter(magicNumber);
    }

    public static IOFileFilter magicNumberFileFilter(byte[] magicNumber, long offset) {
        return new MagicNumberFileFilter(magicNumber, offset);
    }

    public static IOFileFilter magicNumberFileFilter(String magicNumber) {
        return new MagicNumberFileFilter(magicNumber);
    }

    public static IOFileFilter magicNumberFileFilter(String magicNumber, long offset) {
        return new MagicNumberFileFilter(magicNumber, offset);
    }

    public static IOFileFilter makeCVSAware(IOFileFilter filter) {
        return filter == null ? CVS_FILTER : and(filter, CVS_FILTER);
    }

    public static IOFileFilter makeDirectoryOnly(IOFileFilter filter) {
        if (filter == null) {
            return DirectoryFileFilter.DIRECTORY;
        }
        return DirectoryFileFilter.DIRECTORY.and(filter);
    }

    public static IOFileFilter makeFileOnly(IOFileFilter filter) {
        if (filter == null) {
            return FileFileFilter.INSTANCE;
        }
        return FileFileFilter.INSTANCE.and(filter);
    }

    public static IOFileFilter makeSVNAware(IOFileFilter filter) {
        return filter == null ? SVN_FILTER : and(filter, SVN_FILTER);
    }

    public static IOFileFilter nameFileFilter(String name) {
        return new NameFileFilter(name);
    }

    public static IOFileFilter nameFileFilter(String name, IOCase ioCase) {
        return new NameFileFilter(name, ioCase);
    }

    public static IOFileFilter notFileFilter(IOFileFilter filter) {
        return filter.negate();
    }

    public static IOFileFilter or(IOFileFilter... filters) {
        return new OrFileFilter(toList(filters));
    }

    @Deprecated
    public static IOFileFilter orFileFilter(IOFileFilter filter1, IOFileFilter filter2) {
        return new OrFileFilter(filter1, filter2);
    }

    public static IOFileFilter prefixFileFilter(String prefix) {
        return new PrefixFileFilter(prefix);
    }

    public static IOFileFilter prefixFileFilter(String prefix, IOCase ioCase) {
        return new PrefixFileFilter(prefix, ioCase);
    }

    public static IOFileFilter sizeFileFilter(long threshold) {
        return new SizeFileFilter(threshold);
    }

    public static IOFileFilter sizeFileFilter(long threshold, boolean acceptLarger) {
        return new SizeFileFilter(threshold, acceptLarger);
    }

    public static IOFileFilter sizeRangeFileFilter(long minSizeInclusive, long maxSizeInclusive) {
        IOFileFilter minimumFilter = new SizeFileFilter(minSizeInclusive, true);
        IOFileFilter maximumFilter = new SizeFileFilter(1 + maxSizeInclusive, false);
        return minimumFilter.and(maximumFilter);
    }

    public static IOFileFilter suffixFileFilter(String suffix) {
        return new SuffixFileFilter(suffix);
    }

    public static IOFileFilter suffixFileFilter(String suffix, IOCase ioCase) {
        return new SuffixFileFilter(suffix, ioCase);
    }

    public static List<IOFileFilter> toList(IOFileFilter... filters) {
        return (List) Stream.of((Object[]) Objects.requireNonNull(filters, "filters")).map(new Function() { // from class: org.apache.commons.io.filefilter.FileFilterUtils$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Object requireNonNull;
                requireNonNull = Objects.requireNonNull((IOFileFilter) obj);
                return (IOFileFilter) requireNonNull;
            }
        }).collect(Collectors.toList());
    }

    public static IOFileFilter trueFileFilter() {
        return TrueFileFilter.TRUE;
    }
}
