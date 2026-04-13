package org.apache.commons.compress.archivers.examples;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.Objects;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;

/* loaded from: classes9.dex */
public class Archiver {
    public static final EnumSet<FileVisitOption> EMPTY_FileVisitOption = EnumSet.noneOf(FileVisitOption.class);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class ArchiverFileVisitor<O extends ArchiveOutputStream<E>, E extends ArchiveEntry> extends SimpleFileVisitor<Path> {
        private final Path directory;
        private final LinkOption[] linkOptions;
        private final O outputStream;

        private ArchiverFileVisitor(O target, Path directory, LinkOption... linkOptions) {
            this.outputStream = target;
            this.directory = directory;
            this.linkOptions = linkOptions == null ? IOUtils.EMPTY_LINK_OPTIONS : (LinkOption[]) linkOptions.clone();
        }

        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            return visit(dir, attrs, false);
        }

        /* JADX WARN: Multi-variable type inference failed */
        protected FileVisitResult visit(Path path, BasicFileAttributes basicFileAttributes, boolean z) throws IOException {
            Objects.requireNonNull(path);
            Objects.requireNonNull(basicFileAttributes);
            String replace = this.directory.relativize(path).toString().replace(org.apache.commons.io.IOUtils.DIR_SEPARATOR_WINDOWS, '/');
            if (!replace.isEmpty()) {
                this.outputStream.putArchiveEntry(this.outputStream.createArchiveEntry(path, (z || replace.endsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) ? replace : replace + PackagingURIHelper.FORWARD_SLASH_STRING, this.linkOptions));
                if (z) {
                    this.outputStream.write(path);
                }
                this.outputStream.closeArchiveEntry();
            }
            return FileVisitResult.CONTINUE;
        }

        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            return visit(file, attrs, true);
        }
    }

    public void create(ArchiveOutputStream<?> target, File directory) throws IOException {
        create(target, directory.toPath(), EMPTY_FileVisitOption, new LinkOption[0]);
    }

    public void create(ArchiveOutputStream<?> target, Path directory) throws IOException {
        create(target, directory, EMPTY_FileVisitOption, new LinkOption[0]);
    }

    public void create(ArchiveOutputStream<?> target, Path directory, EnumSet<FileVisitOption> fileVisitOptions, LinkOption... linkOptions) throws IOException {
        Files.walkFileTree(directory, fileVisitOptions, Integer.MAX_VALUE, new ArchiverFileVisitor(target, directory, linkOptions));
        target.finish();
    }

    public void create(SevenZOutputFile target, File directory) throws IOException {
        create(target, directory.toPath());
    }

    public void create(final SevenZOutputFile target, final Path directory) throws IOException {
        Files.walkFileTree(directory, new ArchiverFileVisitor<ArchiveOutputStream<ArchiveEntry>, ArchiveEntry>(null, directory, new LinkOption[0]) { // from class: org.apache.commons.compress.archivers.examples.Archiver.1
            @Override // org.apache.commons.compress.archivers.examples.Archiver.ArchiverFileVisitor
            protected FileVisitResult visit(Path path, BasicFileAttributes attrs, boolean isFile) throws IOException {
                Objects.requireNonNull(path);
                Objects.requireNonNull(attrs);
                String name = directory.relativize(path).toString().replace(org.apache.commons.io.IOUtils.DIR_SEPARATOR_WINDOWS, '/');
                if (!name.isEmpty()) {
                    SevenZArchiveEntry archiveEntry = target.createArchiveEntry(path, (isFile || name.endsWith(PackagingURIHelper.FORWARD_SLASH_STRING)) ? name : name + PackagingURIHelper.FORWARD_SLASH_STRING, new LinkOption[0]);
                    target.putArchiveEntry(archiveEntry);
                    if (isFile) {
                        target.write(path, new OpenOption[0]);
                    }
                    target.closeArchiveEntry();
                }
                return FileVisitResult.CONTINUE;
            }
        });
        target.finish();
    }

    public void create(String format, File target, File directory) throws IOException, ArchiveException {
        create(format, target.toPath(), directory.toPath());
    }

    @Deprecated
    public void create(String format, OutputStream target, File directory) throws IOException, ArchiveException {
        create(format, target, directory, CloseableConsumer.NULL_CONSUMER);
    }

    public void create(String format, OutputStream target, File directory, CloseableConsumer closeableConsumer) throws IOException, ArchiveException {
        CloseableConsumerAdapter c = new CloseableConsumerAdapter(closeableConsumer);
        try {
            ArchiveOutputStream<? extends ArchiveEntry> archiveOutputStream = ArchiveStreamFactory.DEFAULT.createArchiveOutputStream(format, target);
            create((ArchiveOutputStream<?>) c.track(archiveOutputStream), directory);
            c.close();
        } catch (Throwable th) {
            try {
                c.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public void create(String format, Path target, Path directory) throws IOException, ArchiveException {
        if (prefersSeekableByteChannel(format)) {
            SeekableByteChannel channel = FileChannel.open(target, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            try {
                create(format, channel, directory);
                if (channel != null) {
                    channel.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        ArchiveOutputStream<?> outputStream = ArchiveStreamFactory.DEFAULT.createArchiveOutputStream(format, Files.newOutputStream(target, new OpenOption[0]));
        try {
            create(outputStream, directory, EMPTY_FileVisitOption, new LinkOption[0]);
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (Throwable th3) {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
            }
            throw th3;
        }
    }

    @Deprecated
    public void create(String format, SeekableByteChannel target, File directory) throws IOException, ArchiveException {
        create(format, target, directory, CloseableConsumer.NULL_CONSUMER);
    }

    public void create(String format, SeekableByteChannel target, File directory, CloseableConsumer closeableConsumer) throws IOException, ArchiveException {
        CloseableConsumerAdapter c = new CloseableConsumerAdapter(closeableConsumer);
        try {
            if (!prefersSeekableByteChannel(format)) {
                create(format, (OutputStream) c.track(Channels.newOutputStream(target)), directory);
            } else if (ArchiveStreamFactory.ZIP.equalsIgnoreCase(format)) {
                create((ArchiveOutputStream<?>) c.track(new ZipArchiveOutputStream(target)), directory);
            } else if (ArchiveStreamFactory.SEVEN_Z.equalsIgnoreCase(format)) {
                create((SevenZOutputFile) c.track(new SevenZOutputFile(target)), directory);
            } else {
                throw new ArchiveException("Don't know how to handle format " + format);
            }
            c.close();
        } catch (Throwable th) {
            try {
                c.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public void create(String format, SeekableByteChannel target, Path directory) throws IOException {
        if (ArchiveStreamFactory.SEVEN_Z.equalsIgnoreCase(format)) {
            SevenZOutputFile sevenZFile = new SevenZOutputFile(target);
            try {
                create(sevenZFile, directory);
                sevenZFile.close();
                return;
            } catch (Throwable th) {
                try {
                    sevenZFile.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (ArchiveStreamFactory.ZIP.equalsIgnoreCase(format)) {
            ZipArchiveOutputStream archiveOutputStream = new ZipArchiveOutputStream(target);
            try {
                create(archiveOutputStream, directory, EMPTY_FileVisitOption, new LinkOption[0]);
                archiveOutputStream.close();
                return;
            } catch (Throwable th3) {
                try {
                    archiveOutputStream.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
                throw th3;
            }
        }
        throw new IllegalStateException(format);
    }

    private boolean prefersSeekableByteChannel(String format) {
        return ArchiveStreamFactory.ZIP.equalsIgnoreCase(format) || ArchiveStreamFactory.SEVEN_Z.equalsIgnoreCase(format);
    }
}
