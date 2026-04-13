package org.apache.commons.io.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileStore;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Stream;
import org.apache.commons.io.function.IOBiFunction;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.function.IOFunction;
import org.apache.commons.io.function.IOQuadFunction;
import org.apache.commons.io.function.IOTriFunction;
import org.apache.commons.io.function.Uncheck;

/* loaded from: classes9.dex */
public final class FilesUncheck {
    public static long copy(InputStream in, Path target, CopyOption... options) {
        return ((Long) Uncheck.apply(new IOTriFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda55
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                long copy;
                copy = Files.copy((InputStream) obj, (Path) obj2, (CopyOption[]) obj3);
                return Long.valueOf(copy);
            }
        }, in, target, options)).longValue();
    }

    public static long copy(Path source, OutputStream out) {
        return ((Long) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda8
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                long copy;
                copy = Files.copy((Path) obj, (OutputStream) obj2);
                return Long.valueOf(copy);
            }
        }, source, out)).longValue();
    }

    public static Path copy(Path source, Path target, CopyOption... options) {
        return (Path) Uncheck.apply(new IOTriFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda22
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                Path copy;
                copy = Files.copy((Path) obj, (Path) obj2, (CopyOption[]) obj3);
                return copy;
            }
        }, source, target, options);
    }

    public static Path createDirectories(Path dir, FileAttribute<?>... attrs) {
        return (Path) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda2
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                Path createDirectories;
                createDirectories = Files.createDirectories((Path) obj, (FileAttribute[]) obj2);
                return createDirectories;
            }
        }, dir, attrs);
    }

    public static Path createDirectory(Path dir, FileAttribute<?>... attrs) {
        return (Path) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda33
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                Path createDirectory;
                createDirectory = Files.createDirectory((Path) obj, (FileAttribute[]) obj2);
                return createDirectory;
            }
        }, dir, attrs);
    }

    public static Path createFile(Path path, FileAttribute<?>... attrs) {
        return (Path) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda37
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                Path createFile;
                createFile = Files.createFile((Path) obj, (FileAttribute[]) obj2);
                return createFile;
            }
        }, path, attrs);
    }

    public static Path createLink(Path link, Path existing) {
        return (Path) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda3
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                Path createLink;
                createLink = Files.createLink((Path) obj, (Path) obj2);
                return createLink;
            }
        }, link, existing);
    }

    public static Path createSymbolicLink(Path link, Path target, FileAttribute<?>... attrs) {
        return (Path) Uncheck.apply(new IOTriFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda39
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                Path createSymbolicLink;
                createSymbolicLink = Files.createSymbolicLink((Path) obj, (Path) obj2, (FileAttribute[]) obj3);
                return createSymbolicLink;
            }
        }, link, target, attrs);
    }

    public static Path createTempDirectory(Path dir, String prefix, FileAttribute<?>... attrs) {
        return (Path) Uncheck.apply(new IOTriFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda54
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                Path createTempDirectory;
                createTempDirectory = Files.createTempDirectory((Path) obj, (String) obj2, (FileAttribute[]) obj3);
                return createTempDirectory;
            }
        }, dir, prefix, attrs);
    }

    public static Path createTempDirectory(String prefix, FileAttribute<?>... attrs) {
        return (Path) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda46
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                Path createTempDirectory;
                createTempDirectory = Files.createTempDirectory((String) obj, (FileAttribute[]) obj2);
                return createTempDirectory;
            }
        }, prefix, attrs);
    }

    public static Path createTempFile(Path dir, String prefix, String suffix, FileAttribute<?>... attrs) {
        return (Path) Uncheck.apply(new IOQuadFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda30
            @Override // org.apache.commons.io.function.IOQuadFunction
            public final Object apply(Object obj, Object obj2, Object obj3, Object obj4) {
                Path createTempFile;
                createTempFile = Files.createTempFile((Path) obj, (String) obj2, (String) obj3, (FileAttribute[]) obj4);
                return createTempFile;
            }
        }, dir, prefix, suffix, attrs);
    }

    public static Path createTempFile(String prefix, String suffix, FileAttribute<?>... attrs) {
        return (Path) Uncheck.apply(new IOTriFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda16
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                Path createTempFile;
                createTempFile = Files.createTempFile((String) obj, (String) obj2, (FileAttribute[]) obj3);
                return createTempFile;
            }
        }, prefix, suffix, attrs);
    }

    public static void delete(Path path) {
        Uncheck.accept((IOConsumer<Path>) new IOConsumer() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda23
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                Files.delete((Path) obj);
            }
        }, path);
    }

    public static boolean deleteIfExists(Path path) {
        return ((Boolean) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda40
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                boolean deleteIfExists;
                deleteIfExists = Files.deleteIfExists((Path) obj);
                return Boolean.valueOf(deleteIfExists);
            }
        }, path)).booleanValue();
    }

    public static Stream<Path> find(Path start, int maxDepth, BiPredicate<Path, BasicFileAttributes> matcher, FileVisitOption... options) {
        return (Stream) Uncheck.apply(new IOQuadFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda18
            @Override // org.apache.commons.io.function.IOQuadFunction
            public final Object apply(Object obj, Object obj2, Object obj3, Object obj4) {
                Stream find;
                find = Files.find((Path) obj, ((Integer) obj2).intValue(), (BiPredicate) obj3, (FileVisitOption[]) obj4);
                return find;
            }
        }, start, Integer.valueOf(maxDepth), matcher, options);
    }

    public static Object getAttribute(Path path, String attribute, LinkOption... options) {
        return Uncheck.apply(new IOTriFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda7
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                Object attribute2;
                attribute2 = Files.getAttribute((Path) obj, (String) obj2, (LinkOption[]) obj3);
                return attribute2;
            }
        }, path, attribute, options);
    }

    public static FileStore getFileStore(Path path) {
        return (FileStore) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda44
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                FileStore fileStore;
                fileStore = Files.getFileStore((Path) obj);
                return fileStore;
            }
        }, path);
    }

    public static FileTime getLastModifiedTime(Path path, LinkOption... options) {
        return (FileTime) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda49
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                FileTime lastModifiedTime;
                lastModifiedTime = Files.getLastModifiedTime((Path) obj, (LinkOption[]) obj2);
                return lastModifiedTime;
            }
        }, path, options);
    }

    public static UserPrincipal getOwner(Path path, LinkOption... options) {
        return (UserPrincipal) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda21
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                UserPrincipal owner;
                owner = Files.getOwner((Path) obj, (LinkOption[]) obj2);
                return owner;
            }
        }, path, options);
    }

    public static Set<PosixFilePermission> getPosixFilePermissions(Path path, LinkOption... options) {
        return (Set) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda14
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                Set posixFilePermissions;
                posixFilePermissions = Files.getPosixFilePermissions((Path) obj, (LinkOption[]) obj2);
                return posixFilePermissions;
            }
        }, path, options);
    }

    public static boolean isHidden(Path path) {
        return ((Boolean) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda38
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                boolean isHidden;
                isHidden = Files.isHidden((Path) obj);
                return Boolean.valueOf(isHidden);
            }
        }, path)).booleanValue();
    }

    public static boolean isSameFile(Path path, Path path2) {
        return ((Boolean) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda47
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                boolean isSameFile;
                isSameFile = Files.isSameFile((Path) obj, (Path) obj2);
                return Boolean.valueOf(isSameFile);
            }
        }, path, path2)).booleanValue();
    }

    public static Stream<String> lines(Path path) {
        return (Stream) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda45
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                Stream lines;
                lines = Files.lines((Path) obj);
                return lines;
            }
        }, path);
    }

    public static Stream<String> lines(Path path, Charset cs) {
        return (Stream) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda31
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                Stream lines;
                lines = Files.lines((Path) obj, (Charset) obj2);
                return lines;
            }
        }, path, cs);
    }

    public static Stream<Path> list(Path dir) {
        return (Stream) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda4
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                Stream list;
                list = Files.list((Path) obj);
                return list;
            }
        }, dir);
    }

    public static Path move(Path source, Path target, CopyOption... options) {
        return (Path) Uncheck.apply(new IOTriFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda9
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                Path move;
                move = Files.move((Path) obj, (Path) obj2, (CopyOption[]) obj3);
                return move;
            }
        }, source, target, options);
    }

    public static BufferedReader newBufferedReader(Path path) {
        return (BufferedReader) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda24
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                BufferedReader newBufferedReader;
                newBufferedReader = Files.newBufferedReader((Path) obj);
                return newBufferedReader;
            }
        }, path);
    }

    public static BufferedReader newBufferedReader(Path path, Charset cs) {
        return (BufferedReader) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda10
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                BufferedReader newBufferedReader;
                newBufferedReader = Files.newBufferedReader((Path) obj, (Charset) obj2);
                return newBufferedReader;
            }
        }, path, cs);
    }

    public static BufferedWriter newBufferedWriter(Path path, Charset cs, OpenOption... options) {
        return (BufferedWriter) Uncheck.apply(new IOTriFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda17
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                BufferedWriter newBufferedWriter;
                newBufferedWriter = Files.newBufferedWriter((Path) obj, (Charset) obj2, (OpenOption[]) obj3);
                return newBufferedWriter;
            }
        }, path, cs, options);
    }

    public static BufferedWriter newBufferedWriter(Path path, OpenOption... options) {
        return (BufferedWriter) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda50
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                BufferedWriter newBufferedWriter;
                newBufferedWriter = Files.newBufferedWriter((Path) obj, (OpenOption[]) obj2);
                return newBufferedWriter;
            }
        }, path, options);
    }

    public static SeekableByteChannel newByteChannel(Path path, OpenOption... options) {
        return (SeekableByteChannel) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda32
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                SeekableByteChannel newByteChannel;
                newByteChannel = Files.newByteChannel((Path) obj, (OpenOption[]) obj2);
                return newByteChannel;
            }
        }, path, options);
    }

    public static SeekableByteChannel newByteChannel(Path path, Set<? extends OpenOption> options, FileAttribute<?>... attrs) {
        return (SeekableByteChannel) Uncheck.apply(new IOTriFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda35
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                SeekableByteChannel newByteChannel;
                newByteChannel = Files.newByteChannel((Path) obj, (Set) obj2, (FileAttribute[]) obj3);
                return newByteChannel;
            }
        }, path, options, attrs);
    }

    public static DirectoryStream<Path> newDirectoryStream(Path dir) {
        return (DirectoryStream) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda48
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                DirectoryStream newDirectoryStream;
                newDirectoryStream = Files.newDirectoryStream((Path) obj);
                return newDirectoryStream;
            }
        }, dir);
    }

    public static DirectoryStream<Path> newDirectoryStream(Path dir, DirectoryStream.Filter<? super Path> filter) {
        return (DirectoryStream) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda13
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                DirectoryStream newDirectoryStream;
                newDirectoryStream = Files.newDirectoryStream((Path) obj, (DirectoryStream.Filter<? super Path>) obj2);
                return newDirectoryStream;
            }
        }, dir, filter);
    }

    public static DirectoryStream<Path> newDirectoryStream(Path dir, String glob) {
        return (DirectoryStream) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda19
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                DirectoryStream newDirectoryStream;
                newDirectoryStream = Files.newDirectoryStream((Path) obj, (String) obj2);
                return newDirectoryStream;
            }
        }, dir, glob);
    }

    public static InputStream newInputStream(Path path, OpenOption... options) {
        return (InputStream) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda42
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                InputStream newInputStream;
                newInputStream = Files.newInputStream((Path) obj, (OpenOption[]) obj2);
                return newInputStream;
            }
        }, path, options);
    }

    public static OutputStream newOutputStream(Path path, OpenOption... options) {
        return (OutputStream) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda51
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                OutputStream newOutputStream;
                newOutputStream = Files.newOutputStream((Path) obj, (OpenOption[]) obj2);
                return newOutputStream;
            }
        }, path, options);
    }

    public static String probeContentType(Path path) {
        return (String) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda29
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                String probeContentType;
                probeContentType = Files.probeContentType((Path) obj);
                return probeContentType;
            }
        }, path);
    }

    public static byte[] readAllBytes(Path path) {
        return (byte[]) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda6
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                byte[] readAllBytes;
                readAllBytes = Files.readAllBytes((Path) obj);
                return readAllBytes;
            }
        }, path);
    }

    public static List<String> readAllLines(Path path) {
        return (List) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda11
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                List readAllLines;
                readAllLines = Files.readAllLines((Path) obj);
                return readAllLines;
            }
        }, path);
    }

    public static List<String> readAllLines(Path path, Charset cs) {
        return (List) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda27
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                List readAllLines;
                readAllLines = Files.readAllLines((Path) obj, (Charset) obj2);
                return readAllLines;
            }
        }, path, cs);
    }

    public static <A extends BasicFileAttributes> A readAttributes(Path path, Class<A> type, LinkOption... options) {
        return (A) Uncheck.apply(new IOTriFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda12
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                BasicFileAttributes readAttributes;
                readAttributes = Files.readAttributes((Path) obj, (Class<BasicFileAttributes>) obj2, (LinkOption[]) obj3);
                return readAttributes;
            }
        }, path, type, options);
    }

    public static Map<String, Object> readAttributes(Path path, String attributes, LinkOption... options) {
        return (Map) Uncheck.apply(new IOTriFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda52
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                Map readAttributes;
                readAttributes = Files.readAttributes((Path) obj, (String) obj2, (LinkOption[]) obj3);
                return readAttributes;
            }
        }, path, attributes, options);
    }

    public static Path readSymbolicLink(Path link) {
        return (Path) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda26
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                Path readSymbolicLink;
                readSymbolicLink = Files.readSymbolicLink((Path) obj);
                return readSymbolicLink;
            }
        }, link);
    }

    public static Path setAttribute(Path path, String attribute, Object value, LinkOption... options) {
        return (Path) Uncheck.apply(new IOQuadFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda25
            @Override // org.apache.commons.io.function.IOQuadFunction
            public final Object apply(Object obj, Object obj2, Object obj3, Object obj4) {
                Path attribute2;
                attribute2 = Files.setAttribute((Path) obj, (String) obj2, obj3, (LinkOption[]) obj4);
                return attribute2;
            }
        }, path, attribute, value, options);
    }

    public static Path setLastModifiedTime(Path path, FileTime time) {
        return (Path) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda20
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                Path lastModifiedTime;
                lastModifiedTime = Files.setLastModifiedTime((Path) obj, (FileTime) obj2);
                return lastModifiedTime;
            }
        }, path, time);
    }

    public static Path setOwner(Path path, UserPrincipal owner) {
        return (Path) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                Path owner2;
                owner2 = Files.setOwner((Path) obj, (UserPrincipal) obj2);
                return owner2;
            }
        }, path, owner);
    }

    public static Path setPosixFilePermissions(Path path, Set<PosixFilePermission> perms) {
        return (Path) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda36
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                Path posixFilePermissions;
                posixFilePermissions = Files.setPosixFilePermissions((Path) obj, (Set) obj2);
                return posixFilePermissions;
            }
        }, path, perms);
    }

    public static long size(Path path) {
        return ((Long) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda15
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                long size;
                size = Files.size((Path) obj);
                return Long.valueOf(size);
            }
        }, path)).longValue();
    }

    public static Stream<Path> walk(Path start, FileVisitOption... options) {
        return (Stream) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda43
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                Stream walk;
                walk = Files.walk((Path) obj, (FileVisitOption[]) obj2);
                return walk;
            }
        }, start, options);
    }

    public static Stream<Path> walk(Path start, int maxDepth, FileVisitOption... options) {
        return (Stream) Uncheck.apply(new IOTriFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda5
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                Stream walk;
                walk = Files.walk((Path) obj, ((Integer) obj2).intValue(), (FileVisitOption[]) obj3);
                return walk;
            }
        }, start, Integer.valueOf(maxDepth), options);
    }

    public static Path walkFileTree(Path start, FileVisitor<? super Path> visitor) {
        return (Path) Uncheck.apply(new IOBiFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda53
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                Path walkFileTree;
                walkFileTree = Files.walkFileTree((Path) obj, (FileVisitor) obj2);
                return walkFileTree;
            }
        }, start, visitor);
    }

    public static Path walkFileTree(Path start, Set<FileVisitOption> options, int maxDepth, FileVisitor<? super Path> visitor) {
        return (Path) Uncheck.apply(new IOQuadFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda41
            @Override // org.apache.commons.io.function.IOQuadFunction
            public final Object apply(Object obj, Object obj2, Object obj3, Object obj4) {
                Path walkFileTree;
                walkFileTree = Files.walkFileTree((Path) obj, (Set) obj2, ((Integer) obj3).intValue(), (FileVisitor) obj4);
                return walkFileTree;
            }
        }, start, options, Integer.valueOf(maxDepth), visitor);
    }

    public static Path write(Path path, byte[] bytes, OpenOption... options) {
        return (Path) Uncheck.apply(new IOTriFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda1
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                Path write;
                write = Files.write((Path) obj, (byte[]) obj2, (OpenOption[]) obj3);
                return write;
            }
        }, path, bytes, options);
    }

    public static Path write(Path path, Iterable<? extends CharSequence> lines, Charset cs, OpenOption... options) {
        return (Path) Uncheck.apply(new IOQuadFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda28
            @Override // org.apache.commons.io.function.IOQuadFunction
            public final Object apply(Object obj, Object obj2, Object obj3, Object obj4) {
                Path write;
                write = Files.write((Path) obj, (Iterable) obj2, (Charset) obj3, (OpenOption[]) obj4);
                return write;
            }
        }, path, lines, cs, options);
    }

    public static Path write(Path path, Iterable<? extends CharSequence> lines, OpenOption... options) {
        return (Path) Uncheck.apply(new IOTriFunction() { // from class: org.apache.commons.io.file.FilesUncheck$$ExternalSyntheticLambda34
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                Path write;
                write = Files.write((Path) obj, (Iterable<? extends CharSequence>) obj2, (OpenOption[]) obj3);
                return write;
            }
        }, path, lines, options);
    }

    private FilesUncheck() {
    }
}
