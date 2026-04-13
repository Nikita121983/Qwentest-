package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.file.PathVisitor;
import org.apache.commons.io.function.IOSupplier;

/* loaded from: classes9.dex */
public abstract class AbstractFileFilter implements IOFileFilter, PathVisitor {
    private final FileVisitResult onAccept;
    private final FileVisitResult onReject;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FileVisitResult toDefaultFileVisitResult(boolean accept) {
        return accept ? FileVisitResult.CONTINUE : FileVisitResult.TERMINATE;
    }

    public AbstractFileFilter() {
        this(FileVisitResult.CONTINUE, FileVisitResult.TERMINATE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractFileFilter(FileVisitResult onAccept, FileVisitResult onReject) {
        this.onAccept = onAccept;
        this.onReject = onReject;
    }

    @Override // org.apache.commons.io.filefilter.IOFileFilter, java.io.FileFilter
    public boolean accept(File file) {
        Objects.requireNonNull(file, "file");
        return accept(file.getParentFile(), file.getName());
    }

    @Override // org.apache.commons.io.filefilter.IOFileFilter, java.io.FilenameFilter
    public boolean accept(File dir, String name) {
        Objects.requireNonNull(name, "name");
        return accept(new File(dir, name));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void append(List<?> list, StringBuilder buffer) {
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                buffer.append(CollectionUtils.COMMA);
            }
            buffer.append(list.get(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void append(Object[] array, StringBuilder buffer) {
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                buffer.append(CollectionUtils.COMMA);
            }
            buffer.append(array[i]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileVisitResult get(IOSupplier<FileVisitResult> supplier) {
        try {
            return supplier.get();
        } catch (IOException e) {
            return handle(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FileVisitResult handle(Throwable t) {
        return FileVisitResult.TERMINATE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isDirectory(File file) {
        return file != null && file.isDirectory();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isFile(File file) {
        return file != null && file.isFile();
    }

    @Override // java.nio.file.FileVisitor
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override // java.nio.file.FileVisitor
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attributes) throws IOException {
        return accept(dir, attributes);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileVisitResult toFileVisitResult(boolean accept) {
        return accept ? this.onAccept : this.onReject;
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    @Override // java.nio.file.FileVisitor
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
        return accept(file, attributes);
    }

    @Override // java.nio.file.FileVisitor
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
