package org.apache.commons.compress.archivers.zip;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.utils.MultiReadOnlySeekableByteChannel;
import org.apache.commons.io.file.PathUtils;

/* loaded from: classes9.dex */
public class ZipSplitReadOnlySeekableByteChannel extends MultiReadOnlySeekableByteChannel {
    private static final Path[] EMPTY_PATH_ARRAY = new Path[0];
    private static final int ZIP_SPLIT_SIGNATURE_LENGTH = 4;
    private final ByteBuffer zipSplitSignatureByteBuffer;

    /* renamed from: $r8$lambda$wBZ5N9SAmxJLRX-vx8hIUeo_fgc, reason: not valid java name */
    public static /* synthetic */ ArrayList m2062$r8$lambda$wBZ5N9SAmxJLRXvx8hIUeo_fgc() {
        return new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class ZipSplitSegmentComparator implements Comparator<Path>, Serializable {
        private static final long serialVersionUID = 20200123;

        private ZipSplitSegmentComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Path file1, Path file2) {
            String extension1 = PathUtils.getExtension(file1);
            String extension2 = PathUtils.getExtension(file2);
            if (!extension1.startsWith(CompressorStreamFactory.Z)) {
                return -1;
            }
            if (!extension2.startsWith(CompressorStreamFactory.Z)) {
                return 1;
            }
            Integer splitSegmentNumber1 = Integer.valueOf(Integer.parseInt(extension1.substring(1)));
            Integer splitSegmentNumber2 = Integer.valueOf(Integer.parseInt(extension2.substring(1)));
            return splitSegmentNumber1.compareTo(splitSegmentNumber2);
        }
    }

    public static SeekableByteChannel buildFromLastSplitSegment(File lastSegmentFile) throws IOException {
        return buildFromLastSplitSegment(lastSegmentFile.toPath());
    }

    public static SeekableByteChannel buildFromLastSplitSegment(Path lastSegmentPath) throws IOException {
        String extension = PathUtils.getExtension(lastSegmentPath);
        if (!extension.equalsIgnoreCase(ArchiveStreamFactory.ZIP)) {
            throw new IllegalArgumentException("The extension of last ZIP split segment should be .zip");
        }
        Path parent = lastSegmentPath.getParent();
        Objects.nonNull(parent);
        Path parent2 = parent != null ? lastSegmentPath.getParent() : lastSegmentPath.getFileSystem().getPath(".", new String[0]);
        String fileBaseName = PathUtils.getBaseName(lastSegmentPath);
        final Pattern pattern = Pattern.compile(Pattern.quote(fileBaseName) + ".[zZ][0-9]+");
        Stream<Path> walk = Files.walk(parent2, 1, new FileVisitOption[0]);
        try {
            ArrayList<Path> splitZipSegments = (ArrayList) walk.filter(new Predicate() { // from class: org.apache.commons.compress.archivers.zip.ZipSplitReadOnlySeekableByteChannel$$ExternalSyntheticLambda2
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean isRegularFile;
                    isRegularFile = Files.isRegularFile((Path) obj, new LinkOption[0]);
                    return isRegularFile;
                }
            }).filter(new Predicate() { // from class: org.apache.commons.compress.archivers.zip.ZipSplitReadOnlySeekableByteChannel$$ExternalSyntheticLambda3
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean matches;
                    matches = pattern.matcher(((Path) obj).getFileName().toString()).matches();
                    return matches;
                }
            }).sorted(new ZipSplitSegmentComparator()).collect(Collectors.toCollection(new Supplier() { // from class: org.apache.commons.compress.archivers.zip.ZipSplitReadOnlySeekableByteChannel$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return ZipSplitReadOnlySeekableByteChannel.m2062$r8$lambda$wBZ5N9SAmxJLRXvx8hIUeo_fgc();
                }
            }));
            if (walk != null) {
                walk.close();
            }
            return forPaths(lastSegmentPath, splitZipSegments);
        } catch (Throwable th) {
            if (walk != null) {
                try {
                    walk.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static SeekableByteChannel forFiles(File... files) throws IOException {
        List<Path> paths = new ArrayList<>();
        for (File f : (File[]) Objects.requireNonNull(files, "files")) {
            paths.add(f.toPath());
        }
        return forPaths((Path[]) paths.toArray(EMPTY_PATH_ARRAY));
    }

    public static SeekableByteChannel forFiles(File lastSegmentFile, Iterable<File> files) throws IOException {
        Objects.requireNonNull(files, "files");
        Objects.requireNonNull(lastSegmentFile, "lastSegmentFile");
        final List<Path> filesList = new ArrayList<>();
        files.forEach(new Consumer() { // from class: org.apache.commons.compress.archivers.zip.ZipSplitReadOnlySeekableByteChannel$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                filesList.add(((File) obj).toPath());
            }
        });
        return forPaths(lastSegmentFile.toPath(), filesList);
    }

    public static SeekableByteChannel forOrderedSeekableByteChannels(SeekableByteChannel... channels) throws IOException {
        if (((SeekableByteChannel[]) Objects.requireNonNull(channels, "channels")).length == 1) {
            return channels[0];
        }
        return new ZipSplitReadOnlySeekableByteChannel(Arrays.asList(channels));
    }

    public static SeekableByteChannel forOrderedSeekableByteChannels(SeekableByteChannel lastSegmentChannel, Iterable<SeekableByteChannel> channels) throws IOException {
        Objects.requireNonNull(channels, "channels");
        Objects.requireNonNull(lastSegmentChannel, "lastSegmentChannel");
        final List<SeekableByteChannel> channelsList = new ArrayList<>();
        Objects.requireNonNull(channelsList);
        channels.forEach(new Consumer() { // from class: org.apache.commons.compress.archivers.zip.ZipSplitReadOnlySeekableByteChannel$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                channelsList.add((SeekableByteChannel) obj);
            }
        });
        channelsList.add(lastSegmentChannel);
        return forOrderedSeekableByteChannels((SeekableByteChannel[]) channelsList.toArray(new SeekableByteChannel[0]));
    }

    public static SeekableByteChannel forPaths(List<Path> paths, OpenOption[] openOptions) throws IOException {
        List<SeekableByteChannel> channels = new ArrayList<>();
        for (Path path : (List) Objects.requireNonNull(paths, "paths")) {
            channels.add(Files.newByteChannel(path, openOptions));
        }
        if (channels.size() == 1) {
            return channels.get(0);
        }
        return new ZipSplitReadOnlySeekableByteChannel(channels);
    }

    public static SeekableByteChannel forPaths(Path... paths) throws IOException {
        return forPaths((List<Path>) Arrays.asList(paths), new OpenOption[]{StandardOpenOption.READ});
    }

    public static SeekableByteChannel forPaths(Path lastSegmentPath, Iterable<Path> paths) throws IOException {
        Objects.requireNonNull(paths, "paths");
        Objects.requireNonNull(lastSegmentPath, "lastSegmentPath");
        List<Path> filesList = new ArrayList<>();
        Objects.requireNonNull(filesList);
        paths.forEach(new ZipSplitReadOnlySeekableByteChannel$$ExternalSyntheticLambda0(filesList));
        filesList.add(lastSegmentPath);
        return forPaths((Path[]) filesList.toArray(EMPTY_PATH_ARRAY));
    }

    public ZipSplitReadOnlySeekableByteChannel(List<SeekableByteChannel> channels) throws IOException {
        super(channels);
        this.zipSplitSignatureByteBuffer = ByteBuffer.allocate(4);
        assertSplitSignature(channels);
    }

    private void assertSplitSignature(List<SeekableByteChannel> channels) throws IOException {
        SeekableByteChannel channel = channels.get(0);
        channel.position(0L);
        this.zipSplitSignatureByteBuffer.rewind();
        channel.read(this.zipSplitSignatureByteBuffer);
        ZipLong signature = new ZipLong(this.zipSplitSignatureByteBuffer.array());
        if (!signature.equals(ZipLong.DD_SIG)) {
            channel.position(0L);
            throw new IOException("The first ZIP split segment does not begin with split ZIP file signature");
        }
        channel.position(0L);
    }
}
