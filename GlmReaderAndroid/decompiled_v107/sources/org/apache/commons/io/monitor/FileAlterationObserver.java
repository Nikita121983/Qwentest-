package org.apache.commons.io.monitor;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.build.AbstractOriginSupplier;
import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.logging.log4j.util.Chars;

/* loaded from: classes9.dex */
public class FileAlterationObserver implements Serializable {
    private static final long serialVersionUID = 1185122225658782848L;
    private final Comparator<File> comparator;
    private final transient FileFilter fileFilter;
    private final transient List<FileAlterationListener> listeners;
    private final FileEntry rootEntry;

    /* loaded from: classes9.dex */
    public static final class Builder extends AbstractOriginSupplier<FileAlterationObserver, Builder> {
        private FileFilter fileFilter;
        private IOCase ioCase;
        private FileEntry rootEntry;

        private Builder() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public File checkOriginFile() {
            return checkOrigin().getFile();
        }

        @Override // org.apache.commons.io.function.IOSupplier
        public FileAlterationObserver get() throws IOException {
            return new FileAlterationObserver(this);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder setFileFilter(FileFilter fileFilter) {
            this.fileFilter = fileFilter;
            return (Builder) asThis();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder setIOCase(IOCase ioCase) {
            this.ioCase = ioCase;
            return (Builder) asThis();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder setRootEntry(FileEntry rootEntry) {
            this.rootEntry = rootEntry;
            return (Builder) asThis();
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static Comparator<File> toComparator(IOCase ioCase) {
        switch (IOCase.value(ioCase, IOCase.SYSTEM)) {
            case SYSTEM:
                return NameFileComparator.NAME_SYSTEM_COMPARATOR;
            case INSENSITIVE:
                return NameFileComparator.NAME_INSENSITIVE_COMPARATOR;
            default:
                return NameFileComparator.NAME_COMPARATOR;
        }
    }

    private FileAlterationObserver(Builder builder) {
        this(builder.rootEntry != null ? builder.rootEntry : new FileEntry(builder.checkOriginFile()), builder.fileFilter, toComparator(builder.ioCase));
    }

    @Deprecated
    public FileAlterationObserver(File directory) {
        this(directory, (FileFilter) null);
    }

    @Deprecated
    public FileAlterationObserver(File directory, FileFilter fileFilter) {
        this(directory, fileFilter, (IOCase) null);
    }

    @Deprecated
    public FileAlterationObserver(File directory, FileFilter fileFilter, IOCase ioCase) {
        this(new FileEntry(directory), fileFilter, ioCase);
    }

    private FileAlterationObserver(FileEntry rootEntry, FileFilter fileFilter, Comparator<File> comparator) {
        this.listeners = new CopyOnWriteArrayList();
        Objects.requireNonNull(rootEntry, "rootEntry");
        Objects.requireNonNull(rootEntry.getFile(), "rootEntry.getFile()");
        this.rootEntry = rootEntry;
        this.fileFilter = fileFilter != null ? fileFilter : TrueFileFilter.INSTANCE;
        this.comparator = (Comparator) Objects.requireNonNull(comparator, "comparator");
    }

    protected FileAlterationObserver(FileEntry rootEntry, FileFilter fileFilter, IOCase ioCase) {
        this(rootEntry, fileFilter, toComparator(ioCase));
    }

    @Deprecated
    public FileAlterationObserver(String directoryName) {
        this(new File(directoryName));
    }

    @Deprecated
    public FileAlterationObserver(String directoryName, FileFilter fileFilter) {
        this(new File(directoryName), fileFilter);
    }

    @Deprecated
    public FileAlterationObserver(String directoryName, FileFilter fileFilter, IOCase ioCase) {
        this(new File(directoryName), fileFilter, ioCase);
    }

    public void addListener(FileAlterationListener listener) {
        if (listener != null) {
            this.listeners.add(listener);
        }
    }

    private void checkAndFire(FileEntry parentEntry, FileEntry[] previousEntries, File[] currentEntries) {
        int c = 0;
        FileEntry[] actualEntries = currentEntries.length > 0 ? new FileEntry[currentEntries.length] : FileEntry.EMPTY_FILE_ENTRY_ARRAY;
        for (FileEntry previousEntry : previousEntries) {
            while (c < currentEntries.length && this.comparator.compare(previousEntry.getFile(), currentEntries[c]) > 0) {
                actualEntries[c] = m2162xd1c1b90a(parentEntry, currentEntries[c]);
                fireOnCreate(actualEntries[c]);
                c++;
            }
            if (c < currentEntries.length && this.comparator.compare(previousEntry.getFile(), currentEntries[c]) == 0) {
                fireOnChange(previousEntry, currentEntries[c]);
                checkAndFire(previousEntry, previousEntry.getChildren(), listFiles(currentEntries[c]));
                actualEntries[c] = previousEntry;
                c++;
            } else {
                checkAndFire(previousEntry, previousEntry.getChildren(), FileUtils.EMPTY_FILE_ARRAY);
                fireOnDelete(previousEntry);
            }
        }
        while (c < currentEntries.length) {
            actualEntries[c] = m2162xd1c1b90a(parentEntry, currentEntries[c]);
            fireOnCreate(actualEntries[c]);
            c++;
        }
        parentEntry.setChildren(actualEntries);
    }

    public void checkAndNotify() {
        this.listeners.forEach(new Consumer() { // from class: org.apache.commons.io.monitor.FileAlterationObserver$$ExternalSyntheticLambda6
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                FileAlterationObserver.this.m2160x2d975733((FileAlterationListener) obj);
            }
        });
        File rootFile = this.rootEntry.getFile();
        if (rootFile.exists()) {
            checkAndFire(this.rootEntry, this.rootEntry.getChildren(), listFiles(rootFile));
        } else if (this.rootEntry.isExists()) {
            checkAndFire(this.rootEntry, this.rootEntry.getChildren(), FileUtils.EMPTY_FILE_ARRAY);
        }
        this.listeners.forEach(new Consumer() { // from class: org.apache.commons.io.monitor.FileAlterationObserver$$ExternalSyntheticLambda7
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                FileAlterationObserver.this.m2161x47b2d5d2((FileAlterationListener) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$checkAndNotify$0$org-apache-commons-io-monitor-FileAlterationObserver, reason: not valid java name */
    public /* synthetic */ void m2160x2d975733(FileAlterationListener listener) {
        listener.onStart(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$checkAndNotify$1$org-apache-commons-io-monitor-FileAlterationObserver, reason: not valid java name */
    public /* synthetic */ void m2161x47b2d5d2(FileAlterationListener listener) {
        listener.onStop(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: createFileEntry, reason: merged with bridge method [inline-methods] */
    public FileEntry m2162xd1c1b90a(FileEntry parent, File file) {
        FileEntry entry = parent.newChildInstance(file);
        entry.refresh(file);
        entry.setChildren(listFileEntries(file, entry));
        return entry;
    }

    public void destroy() throws Exception {
    }

    private void fireOnChange(final FileEntry entry, final File file) {
        if (entry.refresh(file)) {
            this.listeners.forEach(new Consumer() { // from class: org.apache.commons.io.monitor.FileAlterationObserver$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    FileAlterationObserver.lambda$fireOnChange$2(FileEntry.this, file, (FileAlterationListener) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$fireOnChange$2(FileEntry entry, File file, FileAlterationListener listener) {
        if (entry.isDirectory()) {
            listener.onDirectoryChange(file);
        } else {
            listener.onFileChange(file);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fireOnCreate(final FileEntry entry) {
        this.listeners.forEach(new Consumer() { // from class: org.apache.commons.io.monitor.FileAlterationObserver$$ExternalSyntheticLambda4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                FileAlterationObserver.lambda$fireOnCreate$3(FileEntry.this, (FileAlterationListener) obj);
            }
        });
        Stream.of((Object[]) entry.getChildren()).forEach(new Consumer() { // from class: org.apache.commons.io.monitor.FileAlterationObserver$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                FileAlterationObserver.this.fireOnCreate((FileEntry) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$fireOnCreate$3(FileEntry entry, FileAlterationListener listener) {
        if (entry.isDirectory()) {
            listener.onDirectoryCreate(entry.getFile());
        } else {
            listener.onFileCreate(entry.getFile());
        }
    }

    private void fireOnDelete(final FileEntry entry) {
        this.listeners.forEach(new Consumer() { // from class: org.apache.commons.io.monitor.FileAlterationObserver$$ExternalSyntheticLambda8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                FileAlterationObserver.lambda$fireOnDelete$4(FileEntry.this, (FileAlterationListener) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$fireOnDelete$4(FileEntry entry, FileAlterationListener listener) {
        if (entry.isDirectory()) {
            listener.onDirectoryDelete(entry.getFile());
        } else {
            listener.onFileDelete(entry.getFile());
        }
    }

    Comparator<File> getComparator() {
        return this.comparator;
    }

    public File getDirectory() {
        return this.rootEntry.getFile();
    }

    public FileFilter getFileFilter() {
        return this.fileFilter;
    }

    public Iterable<FileAlterationListener> getListeners() {
        return new ArrayList(this.listeners);
    }

    public void initialize() throws Exception {
        this.rootEntry.refresh(this.rootEntry.getFile());
        this.rootEntry.setChildren(listFileEntries(this.rootEntry.getFile(), this.rootEntry));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ FileEntry[] lambda$listFileEntries$6(int x$0) {
        return new FileEntry[x$0];
    }

    private FileEntry[] listFileEntries(File file, final FileEntry entry) {
        return (FileEntry[]) Stream.of((Object[]) listFiles(file)).map(new Function() { // from class: org.apache.commons.io.monitor.FileAlterationObserver$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FileAlterationObserver.this.m2162xd1c1b90a(entry, (File) obj);
            }
        }).toArray(new IntFunction() { // from class: org.apache.commons.io.monitor.FileAlterationObserver$$ExternalSyntheticLambda3
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return FileAlterationObserver.lambda$listFileEntries$6(i);
            }
        });
    }

    private File[] listFiles(File directory) {
        return directory.isDirectory() ? sort(directory.listFiles(this.fileFilter)) : FileUtils.EMPTY_FILE_ARRAY;
    }

    public void removeListener(final FileAlterationListener listener) {
        if (listener != null) {
            List<FileAlterationListener> list = this.listeners;
            Objects.requireNonNull(listener);
            list.removeIf(new Predicate() { // from class: org.apache.commons.io.monitor.FileAlterationObserver$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return FileAlterationListener.this.equals((FileAlterationListener) obj);
                }
            });
        }
    }

    private File[] sort(File[] files) {
        if (files == null) {
            return FileUtils.EMPTY_FILE_ARRAY;
        }
        if (files.length > 1) {
            Arrays.sort(files, this.comparator);
        }
        return files;
    }

    public String toString() {
        return getClass().getSimpleName() + "[file='" + getDirectory().getPath() + Chars.QUOTE + ", " + this.fileFilter.toString() + ", listeners=" + this.listeners.size() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }
}
