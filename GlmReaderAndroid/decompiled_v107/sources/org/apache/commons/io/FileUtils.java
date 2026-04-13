package org.apache.commons.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.CopyOption;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.file.AccumulatorPathVisitor;
import org.apache.commons.io.file.Counters;
import org.apache.commons.io.file.PathFilter;
import org.apache.commons.io.file.PathUtils;
import org.apache.commons.io.file.StandardDeleteOption;
import org.apache.commons.io.filefilter.FileEqualsFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.function.IOBiFunction;
import org.apache.commons.io.function.IOBooleanSupplier;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.function.IOFunction;
import org.apache.commons.io.function.IOLongSupplier;
import org.apache.commons.io.function.IOSupplier;
import org.apache.commons.io.function.Uncheck;
import org.apache.xmlbeans.XmlErrorCodes;

/* loaded from: classes9.dex */
public class FileUtils {
    public static final long ONE_EB = 1152921504606846976L;
    public static final long ONE_GB = 1073741824;
    public static final long ONE_MB = 1048576;
    public static final long ONE_PB = 1125899906842624L;
    public static final long ONE_TB = 1099511627776L;
    private static final String PROTOCOL_FILE = "file";
    public static final long ONE_KB = 1024;
    public static final BigInteger ONE_KB_BI = BigInteger.valueOf(ONE_KB);
    public static final BigInteger ONE_MB_BI = ONE_KB_BI.multiply(ONE_KB_BI);
    public static final BigInteger ONE_GB_BI = ONE_KB_BI.multiply(ONE_MB_BI);
    public static final BigInteger ONE_TB_BI = ONE_KB_BI.multiply(ONE_GB_BI);
    public static final BigInteger ONE_PB_BI = ONE_KB_BI.multiply(ONE_TB_BI);
    public static final BigInteger ONE_EB_BI = ONE_KB_BI.multiply(ONE_PB_BI);
    public static final BigInteger ONE_ZB = ONE_KB_BI.multiply(ONE_EB_BI);
    public static final BigInteger ONE_YB = ONE_KB_BI.multiply(ONE_ZB);
    public static final BigInteger ONE_RB = ONE_KB_BI.multiply(ONE_YB);
    public static final BigInteger ONE_QB = ONE_KB_BI.multiply(ONE_RB);
    public static final File[] EMPTY_FILE_ARRAY = new File[0];

    public static String byteCountToDisplaySize(BigInteger size) {
        Objects.requireNonNull(size, "size");
        if (size.divide(ONE_QB).compareTo(BigInteger.ZERO) > 0) {
            String displaySize = size.divide(ONE_QB) + " QB";
            return displaySize;
        }
        if (size.divide(ONE_RB).compareTo(BigInteger.ZERO) > 0) {
            String displaySize2 = size.divide(ONE_RB) + " RB";
            return displaySize2;
        }
        if (size.divide(ONE_YB).compareTo(BigInteger.ZERO) > 0) {
            String displaySize3 = size.divide(ONE_YB) + " YB";
            return displaySize3;
        }
        if (size.divide(ONE_ZB).compareTo(BigInteger.ZERO) > 0) {
            String displaySize4 = size.divide(ONE_ZB) + " ZB";
            return displaySize4;
        }
        if (size.divide(ONE_EB_BI).compareTo(BigInteger.ZERO) > 0) {
            String displaySize5 = size.divide(ONE_EB_BI) + " EB";
            return displaySize5;
        }
        if (size.divide(ONE_PB_BI).compareTo(BigInteger.ZERO) > 0) {
            String displaySize6 = size.divide(ONE_PB_BI) + " PB";
            return displaySize6;
        }
        if (size.divide(ONE_TB_BI).compareTo(BigInteger.ZERO) > 0) {
            String displaySize7 = size.divide(ONE_TB_BI) + " TB";
            return displaySize7;
        }
        if (size.divide(ONE_GB_BI).compareTo(BigInteger.ZERO) > 0) {
            String displaySize8 = size.divide(ONE_GB_BI) + " GB";
            return displaySize8;
        }
        if (size.divide(ONE_MB_BI).compareTo(BigInteger.ZERO) > 0) {
            String displaySize9 = size.divide(ONE_MB_BI) + " MB";
            return displaySize9;
        }
        if (size.divide(ONE_KB_BI).compareTo(BigInteger.ZERO) > 0) {
            String displaySize10 = size.divide(ONE_KB_BI) + " KB";
            return displaySize10;
        }
        String displaySize11 = size + " bytes";
        return displaySize11;
    }

    public static String byteCountToDisplaySize(long size) {
        return byteCountToDisplaySize(BigInteger.valueOf(size));
    }

    public static String byteCountToDisplaySize(Number size) {
        return byteCountToDisplaySize(size.longValue());
    }

    private static void checkExists(File file, boolean strict) throws FileNotFoundException {
        Objects.requireNonNull(file, PROTOCOL_FILE);
        if (strict && !file.exists() && !isSymlink(file)) {
            throw new FileNotFoundException(file.toString());
        }
    }

    private static void checkFileExists(File file, String name) throws FileNotFoundException {
        Objects.requireNonNull(file, name);
        if (!file.isFile()) {
            if (file.exists()) {
                throw new IllegalArgumentException("Parameter '" + name + "' is not a file: " + file);
            }
            if (!Files.isSymbolicLink(file.toPath())) {
                throw new FileNotFoundException("Source '" + file + "' does not exist");
            }
        }
    }

    private static File checkIsFile(File file, String name) {
        if (file.isFile()) {
            return file;
        }
        throw new IllegalArgumentException(String.format("Parameter '%s' is not a file: %s", name, file));
    }

    public static Checksum checksum(File file, Checksum checksum) throws IOException {
        checkFileExists(file, PROTOCOL_FILE);
        Objects.requireNonNull(checksum, "checksum");
        InputStream inputStream = new CheckedInputStream(Files.newInputStream(file.toPath(), new OpenOption[0]), checksum);
        try {
            IOUtils.consume(inputStream);
            inputStream.close();
            return checksum;
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static long checksumCRC32(File file) throws IOException {
        return checksum(file, new CRC32()).getValue();
    }

    public static void cleanDirectory(File directory) throws IOException {
        IOConsumer.forAll(new IOConsumer() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda16
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                FileUtils.forceDelete((File) obj, false);
            }
        }, listFiles(directory, null));
    }

    private static void cleanDirectoryOnExit(File directory) throws IOException {
        IOConsumer.forAll(new IOConsumer() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda9
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                FileUtils.forceDeleteOnExit((File) obj);
            }
        }, listFiles(directory, null));
    }

    public static boolean contentEquals(File file1, File file2) throws IOException {
        boolean file1Exists;
        if (file1 == null && file2 == null) {
            return true;
        }
        if (file1 == null || file2 == null || (file1Exists = file1.exists()) != file2.exists()) {
            return false;
        }
        if (!file1Exists) {
            return true;
        }
        checkIsFile(file1, "file1");
        checkIsFile(file2, "file2");
        if (file1.length() != file2.length()) {
            return false;
        }
        if (file1.getCanonicalFile().equals(file2.getCanonicalFile())) {
            return true;
        }
        return PathUtils.fileContentEquals(file1.toPath(), file2.toPath());
    }

    public static boolean contentEqualsIgnoreEOL(File file1, File file2, String charsetName) throws IOException {
        boolean file1Exists;
        if (file1 == null && file2 == null) {
            return true;
        }
        if (file1 == null || file2 == null || (file1Exists = file1.exists()) != file2.exists()) {
            return false;
        }
        if (!file1Exists) {
            return true;
        }
        checkFileExists(file1, "file1");
        checkFileExists(file2, "file2");
        if (file1.getCanonicalFile().equals(file2.getCanonicalFile())) {
            return true;
        }
        Charset charset = Charsets.toCharset(charsetName);
        Reader input1 = new InputStreamReader(Files.newInputStream(file1.toPath(), new OpenOption[0]), charset);
        try {
            Reader input2 = new InputStreamReader(Files.newInputStream(file2.toPath(), new OpenOption[0]), charset);
            try {
                boolean contentEqualsIgnoreEOL = IOUtils.contentEqualsIgnoreEOL(input1, input2);
                input2.close();
                input1.close();
                return contentEqualsIgnoreEOL;
            } finally {
            }
        } catch (Throwable th) {
            try {
                input1.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static File[] convertFileCollectionToFileArray(Collection<File> files) {
        return (File[]) files.toArray(EMPTY_FILE_ARRAY);
    }

    public static void copyDirectory(File srcDir, File destDir) throws IOException {
        copyDirectory(srcDir, destDir, true);
    }

    public static void copyDirectory(File srcDir, File destDir, boolean preserveFileDate) throws IOException {
        copyDirectory(srcDir, destDir, null, preserveFileDate);
    }

    public static void copyDirectory(File srcDir, File destDir, FileFilter filter) throws IOException {
        copyDirectory(srcDir, destDir, filter, true);
    }

    public static void copyDirectory(File srcDir, File destDir, FileFilter filter, boolean preserveFileDate) throws IOException {
        copyDirectory(srcDir, destDir, filter, preserveFileDate, StandardCopyOption.REPLACE_EXISTING, LinkOption.NOFOLLOW_LINKS);
    }

    public static void copyDirectory(File srcDir, File destDir, FileFilter fileFilter, boolean preserveFileDate, CopyOption... copyOptions) throws IOException {
        List<String> exclusionList;
        Objects.requireNonNull(destDir, "destination");
        requireDirectoryExists(srcDir, "srcDir");
        requireCanonicalPathsNotEquals(srcDir, destDir);
        String srcDirCanonicalPath = srcDir.getCanonicalPath();
        String destDirCanonicalPath = destDir.getCanonicalPath();
        if (destDirCanonicalPath.startsWith(srcDirCanonicalPath)) {
            File[] srcFiles = listFiles(srcDir, fileFilter);
            if (srcFiles.length > 0) {
                List<String> exclusionList2 = new ArrayList<>(srcFiles.length);
                for (File srcFile : srcFiles) {
                    exclusionList2.add(new File(destDir, srcFile.getName()).getCanonicalPath());
                }
                exclusionList = exclusionList2;
                doCopyDirectory(srcDir, destDir, fileFilter, exclusionList, preserveFileDate, copyOptions);
            }
        }
        exclusionList = null;
        doCopyDirectory(srcDir, destDir, fileFilter, exclusionList, preserveFileDate, copyOptions);
    }

    public static void copyDirectoryToDirectory(File sourceDir, File destinationDir) throws IOException {
        Objects.requireNonNull(sourceDir, "sourceDir");
        requireDirectoryIfExists(destinationDir, "destinationDir");
        copyDirectory(sourceDir, new File(destinationDir, sourceDir.getName()), true);
    }

    public static void copyFile(File srcFile, File destFile) throws IOException {
        copyFile(srcFile, destFile, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {
        copyFile(srcFile, destFile, preserveFileDate, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copyFile(File srcFile, File destFile, boolean preserveFileDate, CopyOption... copyOptions) throws IOException {
        Objects.requireNonNull(destFile, "destination");
        checkFileExists(srcFile, "srcFile");
        requireCanonicalPathsNotEquals(srcFile, destFile);
        createParentDirectories(destFile);
        if (destFile.exists()) {
            checkFileExists(destFile, "destFile");
        }
        Path srcPath = srcFile.toPath();
        Files.copy(srcPath, destFile.toPath(), copyOptions);
        if (preserveFileDate && !Files.isSymbolicLink(srcPath) && !setTimes(srcFile, destFile)) {
            throw new IOException("Cannot set the file time.");
        }
    }

    public static void copyFile(File srcFile, File destFile, CopyOption... copyOptions) throws IOException {
        copyFile(srcFile, destFile, true, copyOptions);
    }

    public static long copyFile(File input, OutputStream output) throws IOException {
        InputStream fis = Files.newInputStream(input.toPath(), new OpenOption[0]);
        try {
            long copyLarge = IOUtils.copyLarge(fis, output);
            if (fis != null) {
                fis.close();
            }
            return copyLarge;
        } catch (Throwable th) {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static void copyFileToDirectory(File srcFile, File destDir) throws IOException {
        copyFileToDirectory(srcFile, destDir, true);
    }

    public static void copyFileToDirectory(File sourceFile, File destinationDir, boolean preserveFileDate) throws IOException {
        Objects.requireNonNull(sourceFile, "sourceFile");
        requireDirectoryIfExists(destinationDir, "destinationDir");
        copyFile(sourceFile, new File(destinationDir, sourceFile.getName()), preserveFileDate);
    }

    public static void copyInputStreamToFile(InputStream source, File destination) throws IOException {
        try {
            copyToFile(source, destination);
            if (source != null) {
                source.close();
            }
        } catch (Throwable th) {
            if (source != null) {
                try {
                    source.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static void copyToDirectory(File sourceFile, File destinationDir) throws IOException {
        Objects.requireNonNull(sourceFile, "sourceFile");
        if (sourceFile.isFile()) {
            copyFileToDirectory(sourceFile, destinationDir);
        } else {
            if (sourceFile.isDirectory()) {
                copyDirectoryToDirectory(sourceFile, destinationDir);
                return;
            }
            throw new FileNotFoundException("The source " + sourceFile + " does not exist");
        }
    }

    public static void copyToDirectory(Iterable<File> sourceIterable, File destinationDir) throws IOException {
        Objects.requireNonNull(sourceIterable, "sourceIterable");
        for (File src : sourceIterable) {
            copyFileToDirectory(src, destinationDir);
        }
    }

    public static void copyToFile(InputStream inputStream, File file) throws IOException {
        OutputStream out = newOutputStream(file, false);
        try {
            IOUtils.copy(inputStream, out);
            if (out != null) {
                out.close();
            }
        } catch (Throwable th) {
            if (out != null) {
                try {
                    out.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static void copyURLToFile(final URL source, File destination) throws IOException {
        Path path = destination.toPath();
        PathUtils.createParentDirectories(path, new FileAttribute[0]);
        Objects.requireNonNull(source);
        PathUtils.copy(new IOSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda8
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                InputStream openStream;
                openStream = source.openStream();
                return openStream;
            }
        }, path, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copyURLToFile(URL source, File destination, int connectionTimeoutMillis, int readTimeoutMillis) throws IOException {
        CloseableURLConnection urlConnection = CloseableURLConnection.open(source);
        try {
            urlConnection.setConnectTimeout(connectionTimeoutMillis);
            urlConnection.setReadTimeout(readTimeoutMillis);
            InputStream stream = urlConnection.getInputStream();
            try {
                copyInputStreamToFile(stream, destination);
                if (stream != null) {
                    stream.close();
                }
                if (urlConnection != null) {
                    urlConnection.close();
                }
            } finally {
            }
        } catch (Throwable th) {
            if (urlConnection != null) {
                try {
                    urlConnection.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static File createParentDirectories(File file) throws IOException {
        return mkdirs(getParentFile(file));
    }

    public static File current() {
        return PathUtils.current().toFile();
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.lang.String decodeUrl(java.lang.String r8) {
        /*
            r0 = r8
            if (r8 == 0) goto L9f
            r1 = 37
            int r2 = r8.indexOf(r1)
            if (r2 < 0) goto L9f
            int r2 = r8.length()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocate(r2)
            r5 = 0
        L19:
            if (r5 >= r2) goto L9b
            char r6 = r8.charAt(r5)
            if (r6 != r1) goto L8f
        L21:
            int r6 = r5 + 1
            int r7 = r5 + 3
            java.lang.String r6 = r8.substring(r6, r7)     // Catch: java.lang.Throwable -> L58 java.lang.NumberFormatException -> L73 java.lang.IndexOutOfBoundsException -> L75
            r7 = 16
            int r6 = java.lang.Integer.parseInt(r6, r7)     // Catch: java.lang.Throwable -> L58 java.lang.NumberFormatException -> L73 java.lang.IndexOutOfBoundsException -> L75
            byte r6 = (byte) r6     // Catch: java.lang.Throwable -> L58 java.lang.NumberFormatException -> L73 java.lang.IndexOutOfBoundsException -> L75
            r4.put(r6)     // Catch: java.lang.Throwable -> L58 java.lang.NumberFormatException -> L73 java.lang.IndexOutOfBoundsException -> L75
            int r5 = r5 + 3
            if (r5 >= r2) goto L3e
            char r6 = r8.charAt(r5)     // Catch: java.lang.Throwable -> L58 java.lang.NumberFormatException -> L73 java.lang.IndexOutOfBoundsException -> L75
            if (r6 == r1) goto L21
        L3e:
            int r6 = r4.position()
            if (r6 <= 0) goto L19
            r4.flip()
            java.nio.charset.Charset r6 = java.nio.charset.StandardCharsets.UTF_8
            java.nio.CharBuffer r6 = r6.decode(r4)
            java.lang.String r6 = r6.toString()
            r3.append(r6)
            r4.clear()
            goto L19
        L58:
            r1 = move-exception
            int r6 = r4.position()
            if (r6 <= 0) goto L72
            r4.flip()
            java.nio.charset.Charset r6 = java.nio.charset.StandardCharsets.UTF_8
            java.nio.CharBuffer r6 = r6.decode(r4)
            java.lang.String r6 = r6.toString()
            r3.append(r6)
            r4.clear()
        L72:
            throw r1
        L73:
            r6 = move-exception
            goto L76
        L75:
            r6 = move-exception
        L76:
            int r6 = r4.position()
            if (r6 <= 0) goto L8f
            r4.flip()
            java.nio.charset.Charset r6 = java.nio.charset.StandardCharsets.UTF_8
            java.nio.CharBuffer r6 = r6.decode(r4)
            java.lang.String r6 = r6.toString()
            r3.append(r6)
            r4.clear()
        L8f:
            int r6 = r5 + 1
            char r5 = r8.charAt(r5)
            r3.append(r5)
            r5 = r6
            goto L19
        L9b:
            java.lang.String r0 = r3.toString()
        L9f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.FileUtils.decodeUrl(java.lang.String):java.lang.String");
    }

    public static File delete(File file) throws IOException {
        Objects.requireNonNull(file, PROTOCOL_FILE);
        Files.delete(file.toPath());
        return file;
    }

    public static void deleteDirectory(File directory) throws IOException {
        Objects.requireNonNull(directory, "directory");
        if (!directory.exists()) {
            return;
        }
        if (!isSymlink(directory)) {
            cleanDirectory(directory);
        }
        delete(directory);
    }

    private static void deleteDirectoryOnExit(File directory) throws IOException {
        if (!directory.exists()) {
            return;
        }
        directory.deleteOnExit();
        if (!isSymlink(directory)) {
            cleanDirectoryOnExit(directory);
        }
    }

    public static boolean deleteQuietly(File file) {
        if (file == null) {
            return false;
        }
        try {
            if (file.isDirectory()) {
                cleanDirectory(file);
            }
        } catch (Exception e) {
        }
        try {
            return file.delete();
        } catch (Exception e2) {
            return false;
        }
    }

    public static boolean directoryContains(File directory, File child) throws IOException {
        requireDirectoryExists(directory, "directory");
        if (child == null || !child.exists()) {
            return false;
        }
        return FilenameUtils.directoryContains(directory.getCanonicalPath(), child.getCanonicalPath());
    }

    private static void doCopyDirectory(File srcDir, File destDir, FileFilter fileFilter, List<String> exclusionList, boolean preserveDirDate, CopyOption... copyOptions) throws IOException {
        FileFilter fileFilter2;
        List<String> exclusionList2;
        boolean preserveDirDate2;
        CopyOption[] copyOptions2;
        File[] srcFiles = listFiles(srcDir, fileFilter);
        requireDirectoryIfExists(destDir, "destDir");
        mkdirs(destDir);
        int length = srcFiles.length;
        int i = 0;
        while (i < length) {
            File srcFile = srcFiles[i];
            File dstFile = new File(destDir, srcFile.getName());
            if (exclusionList != null && exclusionList.contains(srcFile.getCanonicalPath())) {
                fileFilter2 = fileFilter;
                exclusionList2 = exclusionList;
                preserveDirDate2 = preserveDirDate;
                copyOptions2 = copyOptions;
            } else if (srcFile.isDirectory()) {
                fileFilter2 = fileFilter;
                exclusionList2 = exclusionList;
                preserveDirDate2 = preserveDirDate;
                copyOptions2 = copyOptions;
                doCopyDirectory(srcFile, dstFile, fileFilter2, exclusionList2, preserveDirDate2, copyOptions2);
            } else {
                fileFilter2 = fileFilter;
                exclusionList2 = exclusionList;
                preserveDirDate2 = preserveDirDate;
                copyOptions2 = copyOptions;
                copyFile(srcFile, dstFile, preserveDirDate2, copyOptions2);
            }
            i++;
            fileFilter = fileFilter2;
            exclusionList = exclusionList2;
            preserveDirDate = preserveDirDate2;
            copyOptions = copyOptions2;
        }
        if (preserveDirDate) {
            setTimes(srcDir, destDir);
        }
    }

    public static void forceDelete(File file) throws IOException {
        forceDelete(file, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void forceDelete(File file, boolean strict) throws IOException {
        checkExists(file, strict);
        try {
            Counters.PathCounters deleteCounters = PathUtils.delete(file.toPath(), PathUtils.EMPTY_LINK_OPTION_ARRAY, StandardDeleteOption.OVERRIDE_READ_ONLY);
            if (deleteCounters.getFileCounter().get() < 1 && deleteCounters.getDirectoryCounter().get() < 1) {
                throw new FileNotFoundException("File does not exist: " + file);
            }
        } catch (NoSuchFileException e) {
            FileNotFoundException nioEx = new FileNotFoundException("Cannot delete file: " + file);
            nioEx.initCause(e);
            throw nioEx;
        } catch (IOException e2) {
            throw new IOException("Cannot delete file: " + file, e2);
        }
    }

    public static void forceDeleteOnExit(File file) throws IOException {
        Objects.requireNonNull(file, PROTOCOL_FILE);
        if (file.isDirectory()) {
            deleteDirectoryOnExit(file);
        } else {
            file.deleteOnExit();
        }
    }

    public static void forceMkdir(File directory) throws IOException {
        mkdirs(directory);
    }

    public static void forceMkdirParent(File file) throws IOException {
        forceMkdir(getParentFile((File) Objects.requireNonNull(file, PROTOCOL_FILE)));
    }

    public static File getFile(File directory, String... names) {
        Objects.requireNonNull(directory, "directory");
        Objects.requireNonNull(names, "names");
        File file = directory;
        for (String name : names) {
            file = new File(file, name);
        }
        return file;
    }

    public static File getFile(String... names) {
        File file;
        Objects.requireNonNull(names, "names");
        File file2 = null;
        for (String name : names) {
            if (file2 == null) {
                file = new File(name);
            } else {
                file = new File(file2, name);
            }
            file2 = file;
        }
        return file2;
    }

    private static File getParentFile(File file) {
        if (file == null) {
            return null;
        }
        return file.getParentFile();
    }

    public static File getTempDirectory() {
        return new File(getTempDirectoryPath());
    }

    public static String getTempDirectoryPath() {
        return System.getProperty("java.io.tmpdir");
    }

    public static File getUserDirectory() {
        return new File(getUserDirectoryPath());
    }

    public static String getUserDirectoryPath() {
        return System.getProperty("user.home");
    }

    public static boolean isDirectory(File file, LinkOption... options) {
        return file != null && Files.isDirectory(file.toPath(), options);
    }

    public static boolean isEmptyDirectory(File directory) throws IOException {
        return PathUtils.isEmptyDirectory(directory.toPath());
    }

    public static boolean isFileNewer(File file, ChronoLocalDate chronoLocalDate) {
        return isFileNewer(file, chronoLocalDate, LocalTime.MAX);
    }

    public static boolean isFileNewer(File file, ChronoLocalDate chronoLocalDate, LocalTime localTime) {
        Objects.requireNonNull(chronoLocalDate, "chronoLocalDate");
        Objects.requireNonNull(localTime, "localTime");
        return isFileNewer(file, chronoLocalDate.atTime(localTime));
    }

    public static boolean isFileNewer(File file, ChronoLocalDate chronoLocalDate, OffsetTime offsetTime) {
        Objects.requireNonNull(chronoLocalDate, "chronoLocalDate");
        Objects.requireNonNull(offsetTime, "offsetTime");
        return isFileNewer(file, chronoLocalDate.atTime(offsetTime.toLocalTime()));
    }

    public static boolean isFileNewer(File file, ChronoLocalDateTime<?> chronoLocalDateTime) {
        return isFileNewer(file, chronoLocalDateTime, ZoneId.systemDefault());
    }

    public static boolean isFileNewer(File file, ChronoLocalDateTime<?> chronoLocalDateTime, ZoneId zoneId) {
        Objects.requireNonNull(chronoLocalDateTime, "chronoLocalDateTime");
        Objects.requireNonNull(zoneId, "zoneId");
        return isFileNewer(file, chronoLocalDateTime.atZone(zoneId));
    }

    public static boolean isFileNewer(final File file, final ChronoZonedDateTime<?> chronoZonedDateTime) {
        Objects.requireNonNull(file, PROTOCOL_FILE);
        Objects.requireNonNull(chronoZonedDateTime, "chronoZonedDateTime");
        return Uncheck.getAsBoolean(new IOBooleanSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda12
            @Override // org.apache.commons.io.function.IOBooleanSupplier
            public final boolean getAsBoolean() {
                boolean isNewer;
                isNewer = PathUtils.isNewer(file.toPath(), (ChronoZonedDateTime<?>) chronoZonedDateTime, new LinkOption[0]);
                return isNewer;
            }
        });
    }

    public static boolean isFileNewer(File file, Date date) {
        Objects.requireNonNull(date, XmlErrorCodes.DATE);
        return isFileNewer(file, date.getTime());
    }

    public static boolean isFileNewer(final File file, final File reference) {
        return Uncheck.getAsBoolean(new IOBooleanSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda1
            @Override // org.apache.commons.io.function.IOBooleanSupplier
            public final boolean getAsBoolean() {
                boolean isNewer;
                isNewer = PathUtils.isNewer(file.toPath(), reference.toPath());
                return isNewer;
            }
        });
    }

    public static boolean isFileNewer(File file, FileTime fileTime) throws IOException {
        Objects.requireNonNull(file, PROTOCOL_FILE);
        return PathUtils.isNewer(file.toPath(), fileTime, new LinkOption[0]);
    }

    public static boolean isFileNewer(final File file, final Instant instant) {
        Objects.requireNonNull(instant, "instant");
        return Uncheck.getAsBoolean(new IOBooleanSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda14
            @Override // org.apache.commons.io.function.IOBooleanSupplier
            public final boolean getAsBoolean() {
                boolean isNewer;
                isNewer = PathUtils.isNewer(file.toPath(), instant, new LinkOption[0]);
                return isNewer;
            }
        });
    }

    public static boolean isFileNewer(final File file, final long timeMillis) {
        Objects.requireNonNull(file, PROTOCOL_FILE);
        return Uncheck.getAsBoolean(new IOBooleanSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda23
            @Override // org.apache.commons.io.function.IOBooleanSupplier
            public final boolean getAsBoolean() {
                boolean isNewer;
                isNewer = PathUtils.isNewer(file.toPath(), timeMillis, new LinkOption[0]);
                return isNewer;
            }
        });
    }

    public static boolean isFileNewer(File file, OffsetDateTime offsetDateTime) {
        Objects.requireNonNull(offsetDateTime, "offsetDateTime");
        return isFileNewer(file, offsetDateTime.toInstant());
    }

    public static boolean isFileOlder(File file, ChronoLocalDate chronoLocalDate) {
        return isFileOlder(file, chronoLocalDate, LocalTime.MAX);
    }

    public static boolean isFileOlder(File file, ChronoLocalDate chronoLocalDate, LocalTime localTime) {
        Objects.requireNonNull(chronoLocalDate, "chronoLocalDate");
        Objects.requireNonNull(localTime, "localTime");
        return isFileOlder(file, chronoLocalDate.atTime(localTime));
    }

    public static boolean isFileOlder(File file, ChronoLocalDate chronoLocalDate, OffsetTime offsetTime) {
        Objects.requireNonNull(chronoLocalDate, "chronoLocalDate");
        Objects.requireNonNull(offsetTime, "offsetTime");
        return isFileOlder(file, chronoLocalDate.atTime(offsetTime.toLocalTime()));
    }

    public static boolean isFileOlder(File file, ChronoLocalDateTime<?> chronoLocalDateTime) {
        return isFileOlder(file, chronoLocalDateTime, ZoneId.systemDefault());
    }

    public static boolean isFileOlder(File file, ChronoLocalDateTime<?> chronoLocalDateTime, ZoneId zoneId) {
        Objects.requireNonNull(chronoLocalDateTime, "chronoLocalDateTime");
        Objects.requireNonNull(zoneId, "zoneId");
        return isFileOlder(file, chronoLocalDateTime.atZone(zoneId));
    }

    public static boolean isFileOlder(File file, ChronoZonedDateTime<?> chronoZonedDateTime) {
        Objects.requireNonNull(chronoZonedDateTime, "chronoZonedDateTime");
        return isFileOlder(file, chronoZonedDateTime.toInstant());
    }

    public static boolean isFileOlder(File file, Date date) {
        Objects.requireNonNull(date, XmlErrorCodes.DATE);
        return isFileOlder(file, date.getTime());
    }

    public static boolean isFileOlder(final File file, final File reference) throws FileNotFoundException {
        return Uncheck.getAsBoolean(new IOBooleanSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda10
            @Override // org.apache.commons.io.function.IOBooleanSupplier
            public final boolean getAsBoolean() {
                boolean isOlder;
                isOlder = PathUtils.isOlder(file.toPath(), reference.toPath());
                return isOlder;
            }
        });
    }

    public static boolean isFileOlder(File file, FileTime fileTime) throws IOException {
        Objects.requireNonNull(file, PROTOCOL_FILE);
        return PathUtils.isOlder(file.toPath(), fileTime, new LinkOption[0]);
    }

    public static boolean isFileOlder(final File file, final Instant instant) {
        Objects.requireNonNull(instant, "instant");
        return Uncheck.getAsBoolean(new IOBooleanSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda7
            @Override // org.apache.commons.io.function.IOBooleanSupplier
            public final boolean getAsBoolean() {
                boolean isOlder;
                isOlder = PathUtils.isOlder(file.toPath(), instant, new LinkOption[0]);
                return isOlder;
            }
        });
    }

    public static boolean isFileOlder(final File file, final long timeMillis) {
        Objects.requireNonNull(file, PROTOCOL_FILE);
        return Uncheck.getAsBoolean(new IOBooleanSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda15
            @Override // org.apache.commons.io.function.IOBooleanSupplier
            public final boolean getAsBoolean() {
                boolean isOlder;
                isOlder = PathUtils.isOlder(file.toPath(), timeMillis, new LinkOption[0]);
                return isOlder;
            }
        });
    }

    public static boolean isFileOlder(File file, OffsetDateTime offsetDateTime) {
        Objects.requireNonNull(offsetDateTime, "offsetDateTime");
        return isFileOlder(file, offsetDateTime.toInstant());
    }

    private static boolean isFileProtocol(URL url) {
        return PROTOCOL_FILE.equalsIgnoreCase(url.getProtocol());
    }

    public static boolean isRegularFile(File file, LinkOption... options) {
        return file != null && Files.isRegularFile(file.toPath(), options);
    }

    public static boolean isSymlink(File file) {
        return file != null && Files.isSymbolicLink(file.toPath());
    }

    public static Iterator<File> iterateFiles(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
        return listFiles(directory, fileFilter, dirFilter).iterator();
    }

    public static Iterator<File> iterateFiles(final File directory, final String[] extensions, final boolean recursive) {
        return StreamIterator.iterator((Stream) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda20
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                Stream streamFiles;
                streamFiles = FileUtils.streamFiles(directory, recursive, extensions);
                return streamFiles;
            }
        }));
    }

    public static Iterator<File> iterateFilesAndDirs(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
        return listFilesAndDirs(directory, fileFilter, dirFilter).iterator();
    }

    public static long lastModified(File file) throws IOException {
        return lastModifiedFileTime(file).toMillis();
    }

    public static FileTime lastModifiedFileTime(File file) throws IOException {
        return Files.getLastModifiedTime(((File) Objects.requireNonNull(file, PROTOCOL_FILE)).toPath(), new LinkOption[0]);
    }

    public static long lastModifiedUnchecked(File file) {
        return ((Long) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda4
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                return Long.valueOf(FileUtils.lastModified((File) obj));
            }
        }, file)).longValue();
    }

    public static LineIterator lineIterator(File file) throws IOException {
        return lineIterator(file, null);
    }

    public static LineIterator lineIterator(File file, String charsetName) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = Files.newInputStream(file.toPath(), new OpenOption[0]);
            return IOUtils.lineIterator(inputStream, charsetName);
        } catch (IOException | RuntimeException ex) {
            Objects.requireNonNull(ex);
            IOUtils.closeQuietly(inputStream, new Consumer() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda17
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ex.addSuppressed((Exception) obj);
                }
            });
            throw ex;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AccumulatorPathVisitor listAccumulate(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter, FileVisitOption... options) throws IOException {
        boolean isDirFilterSet = dirFilter != null;
        FileEqualsFileFilter rootDirFilter = new FileEqualsFileFilter(directory);
        PathFilter dirPathFilter = isDirFilterSet ? rootDirFilter.or(dirFilter) : rootDirFilter;
        AccumulatorPathVisitor visitor = ((AccumulatorPathVisitor.Builder) AccumulatorPathVisitor.builder().setPathCounters(Counters.noopPathCounters()).setFileFilter(fileFilter).setDirectoryFilter(dirPathFilter).setVisitFileFailedFunction(new IOBiFunction() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda3
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                FileVisitResult fileVisitResult;
                fileVisitResult = FileVisitResult.CONTINUE;
                return fileVisitResult;
            }
        })).get();
        Set<FileVisitOption> optionSet = new HashSet<>();
        if (options != null) {
            Collections.addAll(optionSet, options);
        }
        Files.walkFileTree(directory.toPath(), optionSet, toMaxDepth(isDirFilterSet), visitor);
        return visitor;
    }

    private static File[] listFiles(File directory, FileFilter fileFilter) throws IOException {
        requireDirectoryExists(directory, "directory");
        File[] files = directory.listFiles(fileFilter);
        if (files == null) {
            throw new IOException("Unknown I/O error listing contents of directory: " + directory);
        }
        return files;
    }

    public static Collection<File> listFiles(File directory, final IOFileFilter fileFilter, final IOFileFilter dirFilter) {
        AccumulatorPathVisitor visitor = (AccumulatorPathVisitor) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda18
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                AccumulatorPathVisitor listAccumulate;
                File file = (File) obj;
                listAccumulate = FileUtils.listAccumulate(file, FileFileFilter.INSTANCE.and(IOFileFilter.this), dirFilter, FileVisitOption.FOLLOW_LINKS);
                return listAccumulate;
            }
        }, directory);
        return toList(visitor.getFileList().stream().map(new FileUtils$$ExternalSyntheticLambda19()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<File> listFiles(final File directory, final List<File> files, final boolean recursive, final FilenameFilter filter) {
        File[] listFiles = directory.listFiles();
        if (listFiles == null) {
            return files;
        }
        final List<File> dirs = recursive ? new ArrayList<>() : null;
        Arrays.stream(listFiles).forEach(new Consumer() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda21
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                FileUtils.lambda$listFiles$11(recursive, dirs, filter, directory, files, (File) obj);
            }
        });
        if (!recursive) {
            return files;
        }
        dirs.forEach(new Consumer() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda22
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                FileUtils.listFiles((File) obj, files, true, filter);
            }
        });
        return files;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$listFiles$11(boolean recursive, List dirs, FilenameFilter filter, File directory, List files, File f) {
        if (recursive && f.isDirectory()) {
            dirs.add(f);
        } else if (f.isFile() && filter.accept(directory, f.getName())) {
            files.add(f);
        }
    }

    public static Collection<File> listFiles(File directory, String[] extensions, boolean recursive) {
        return listFiles(directory, new ArrayList(), recursive, extensions != null ? toSuffixFileFilter(extensions) : TrueFileFilter.INSTANCE);
    }

    public static Collection<File> listFilesAndDirs(File directory, final IOFileFilter fileFilter, final IOFileFilter dirFilter) {
        AccumulatorPathVisitor visitor = (AccumulatorPathVisitor) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda25
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                AccumulatorPathVisitor listAccumulate;
                listAccumulate = FileUtils.listAccumulate((File) obj, IOFileFilter.this, dirFilter, FileVisitOption.FOLLOW_LINKS);
                return listAccumulate;
            }
        }, directory);
        List<Path> list = visitor.getFileList();
        list.addAll(visitor.getDirList());
        return toList(list.stream().map(new FileUtils$$ExternalSyntheticLambda19()));
    }

    private static File mkdirs(File directory) throws IOException {
        if (directory != null && !directory.mkdirs() && !directory.isDirectory()) {
            throw new IOException("Cannot create directory '" + directory + "'.");
        }
        return directory;
    }

    public static void moveDirectory(File srcDir, File destDir) throws IOException {
        Objects.requireNonNull(destDir, "destination");
        requireDirectoryExists(srcDir, "srcDir");
        requireAbsent(destDir, "destDir");
        if (!srcDir.renameTo(destDir)) {
            if (destDir.getCanonicalPath().startsWith(srcDir.getCanonicalPath() + File.separator)) {
                throw new IOException("Cannot move directory: " + srcDir + " to a subdirectory of itself: " + destDir);
            }
            copyDirectory(srcDir, destDir);
            deleteDirectory(srcDir);
            if (srcDir.exists()) {
                throw new IOException("Failed to delete original directory '" + srcDir + "' after copy to '" + destDir + "'");
            }
        }
    }

    public static void moveDirectoryToDirectory(File source, File destDir, boolean createDestDir) throws IOException {
        validateMoveParameters(source, destDir);
        if (!destDir.isDirectory()) {
            if (destDir.exists()) {
                throw new IOException("Destination '" + destDir + "' is not a directory");
            }
            if (!createDestDir) {
                throw new FileNotFoundException("Destination directory '" + destDir + "' does not exist [createDestDir=false" + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            }
            mkdirs(destDir);
        }
        moveDirectory(source, new File(destDir, source.getName()));
    }

    public static void moveFile(File srcFile, File destFile) throws IOException {
        moveFile(srcFile, destFile, StandardCopyOption.COPY_ATTRIBUTES);
    }

    public static void moveFile(File srcFile, File destFile, CopyOption... copyOptions) throws IOException {
        Objects.requireNonNull(destFile, "destFile");
        checkFileExists(srcFile, "srcFile");
        requireAbsent(destFile, "destFile");
        boolean rename = srcFile.renameTo(destFile);
        if (!rename) {
            copyFile(srcFile, destFile, false, copyOptions);
            if (!srcFile.delete()) {
                deleteQuietly(destFile);
                throw new IOException("Failed to delete original file '" + srcFile + "' after copy to '" + destFile + "'");
            }
        }
    }

    public static void moveFileToDirectory(File srcFile, File destDir, boolean createDestDir) throws IOException {
        validateMoveParameters(srcFile, destDir);
        if (!destDir.exists() && createDestDir) {
            mkdirs(destDir);
        }
        requireDirectoryExists(destDir, "destDir");
        moveFile(srcFile, new File(destDir, srcFile.getName()));
    }

    public static void moveToDirectory(File src, File destDir, boolean createDestDir) throws IOException {
        validateMoveParameters(src, destDir);
        if (src.isDirectory()) {
            moveDirectoryToDirectory(src, destDir, createDestDir);
        } else {
            moveFileToDirectory(src, destDir, createDestDir);
        }
    }

    public static OutputStream newOutputStream(File file, boolean append) throws IOException {
        return PathUtils.newOutputStream(((File) Objects.requireNonNull(file, PROTOCOL_FILE)).toPath(), append);
    }

    public static FileInputStream openInputStream(File file) throws IOException {
        Objects.requireNonNull(file, PROTOCOL_FILE);
        return new FileInputStream(file);
    }

    public static FileOutputStream openOutputStream(File file) throws IOException {
        return openOutputStream(file, false);
    }

    public static FileOutputStream openOutputStream(File file, boolean append) throws IOException {
        Objects.requireNonNull(file, PROTOCOL_FILE);
        if (file.exists()) {
            checkIsFile(file, PROTOCOL_FILE);
        } else {
            createParentDirectories(file);
        }
        return new FileOutputStream(file, append);
    }

    public static byte[] readFileToByteArray(File file) throws IOException {
        Objects.requireNonNull(file, PROTOCOL_FILE);
        return Files.readAllBytes(file.toPath());
    }

    @Deprecated
    public static String readFileToString(File file) throws IOException {
        return readFileToString(file, Charset.defaultCharset());
    }

    public static String readFileToString(final File file, Charset charsetName) throws IOException {
        return IOUtils.toString((IOSupplier<InputStream>) new IOSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                InputStream newInputStream;
                newInputStream = Files.newInputStream(file.toPath(), new OpenOption[0]);
                return newInputStream;
            }
        }, Charsets.toCharset(charsetName));
    }

    public static String readFileToString(File file, String charsetName) throws IOException {
        return readFileToString(file, Charsets.toCharset(charsetName));
    }

    @Deprecated
    public static List<String> readLines(File file) throws IOException {
        return readLines(file, Charset.defaultCharset());
    }

    public static List<String> readLines(File file, Charset charset) throws IOException {
        return Files.readAllLines(file.toPath(), Charsets.toCharset(charset));
    }

    public static List<String> readLines(File file, String charsetName) throws IOException {
        return readLines(file, Charsets.toCharset(charsetName));
    }

    private static void requireAbsent(File file, String name) throws FileExistsException {
        if (file.exists()) {
            throw new FileExistsException(String.format("File element in parameter '%s' already exists: '%s'", name, file));
        }
    }

    private static void requireCanonicalPathsNotEquals(File file1, File file2) throws IOException {
        String canonicalPath = file1.getCanonicalPath();
        if (canonicalPath.equals(file2.getCanonicalPath())) {
            throw new IllegalArgumentException(String.format("File canonical paths are equal: '%s' (file1='%s', file2='%s')", canonicalPath, file1, file2));
        }
    }

    private static void requireDirectoryExists(File directory, String name) throws FileNotFoundException {
        Objects.requireNonNull(directory, name);
        if (!directory.isDirectory()) {
            if (directory.exists()) {
                throw new IllegalArgumentException("Parameter '" + name + "' is not a directory: '" + directory + "'");
            }
            throw new FileNotFoundException("Directory '" + directory + "' does not exist.");
        }
    }

    private static void requireDirectoryIfExists(File directory, String name) {
        Objects.requireNonNull(directory, name);
        if (directory.exists() && !directory.isDirectory()) {
            throw new IllegalArgumentException("Parameter '" + name + "' is not a directory: '" + directory + "'");
        }
    }

    private static boolean setTimes(File sourceFile, File targetFile) {
        Objects.requireNonNull(sourceFile, "sourceFile");
        Objects.requireNonNull(targetFile, "targetFile");
        try {
            BasicFileAttributes srcAttr = Files.readAttributes(sourceFile.toPath(), (Class<BasicFileAttributes>) BasicFileAttributes.class, new LinkOption[0]);
            BasicFileAttributeView destAttrView = (BasicFileAttributeView) Files.getFileAttributeView(targetFile.toPath(), BasicFileAttributeView.class, new LinkOption[0]);
            destAttrView.setTimes(srcAttr.lastModifiedTime(), srcAttr.lastAccessTime(), srcAttr.creationTime());
            return true;
        } catch (IOException e) {
            return targetFile.setLastModified(sourceFile.lastModified());
        }
    }

    public static long sizeOf(final File file) {
        return Uncheck.getAsLong(new IOLongSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda11
            @Override // org.apache.commons.io.function.IOLongSupplier
            public final long getAsLong() {
                long sizeOf;
                sizeOf = PathUtils.sizeOf(file.toPath());
                return sizeOf;
            }
        });
    }

    public static BigInteger sizeOfAsBigInteger(final File file) {
        return (BigInteger) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda13
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                BigInteger sizeOfAsBigInteger;
                sizeOfAsBigInteger = PathUtils.sizeOfAsBigInteger(file.toPath());
                return sizeOfAsBigInteger;
            }
        });
    }

    public static long sizeOfDirectory(final File directory) {
        try {
            requireDirectoryExists(directory, "directory");
            return Uncheck.getAsLong(new IOLongSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda2
                @Override // org.apache.commons.io.function.IOLongSupplier
                public final long getAsLong() {
                    long sizeOfDirectory;
                    sizeOfDirectory = PathUtils.sizeOfDirectory(directory.toPath());
                    return sizeOfDirectory;
                }
            });
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static BigInteger sizeOfDirectoryAsBigInteger(final File directory) {
        try {
            requireDirectoryExists(directory, "directory");
            return (BigInteger) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda24
                @Override // org.apache.commons.io.function.IOSupplier
                public final Object get() {
                    BigInteger sizeOfDirectoryAsBigInteger;
                    sizeOfDirectoryAsBigInteger = PathUtils.sizeOfDirectoryAsBigInteger(directory.toPath());
                    return sizeOfDirectoryAsBigInteger;
                }
            });
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static Stream<File> streamFiles(File directory, boolean recursive, String... extensions) throws IOException {
        IOFileFilter filter;
        if (extensions == null) {
            filter = FileFileFilter.INSTANCE;
        } else {
            filter = FileFileFilter.INSTANCE.and(toSuffixFileFilter(extensions));
        }
        return PathUtils.walk(directory.toPath(), filter, toMaxDepth(recursive), false, FileVisitOption.FOLLOW_LINKS).map(new FileUtils$$ExternalSyntheticLambda19());
    }

    public static File toFile(URL url) {
        if (url == null || !isFileProtocol(url)) {
            return null;
        }
        String fileName = url.getFile().replace('/', File.separatorChar);
        return new File(decodeUrl(fileName));
    }

    public static File[] toFiles(URL... urls) {
        if (IOUtils.length(urls) == 0) {
            return EMPTY_FILE_ARRAY;
        }
        File[] files = new File[urls.length];
        for (int i = 0; i < urls.length; i++) {
            URL url = urls[i];
            if (url != null) {
                if (!isFileProtocol(url)) {
                    throw new IllegalArgumentException("Can only convert file URL to a File: " + url);
                }
                files[i] = toFile(url);
            }
        }
        return files;
    }

    private static List<File> toList(Stream<File> stream) {
        return (List) stream.collect(Collectors.toList());
    }

    private static int toMaxDepth(boolean recursive) {
        return recursive ? Integer.MAX_VALUE : 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$toSuffixes$19(String s) {
        return s.charAt(0) == '.' ? s : "." + s;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$toSuffixes$20(int x$0) {
        return new String[x$0];
    }

    private static String[] toSuffixes(String... extensions) {
        return (String[]) Stream.of((Object[]) Objects.requireNonNull(extensions, "extensions")).map(new Function() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return FileUtils.lambda$toSuffixes$19((String) obj);
            }
        }).toArray(new IntFunction() { // from class: org.apache.commons.io.FileUtils$$ExternalSyntheticLambda6
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return FileUtils.lambda$toSuffixes$20(i);
            }
        });
    }

    private static SuffixFileFilter toSuffixFileFilter(String... extensions) {
        return new SuffixFileFilter(toSuffixes(extensions));
    }

    public static void touch(File file) throws IOException {
        PathUtils.touch(((File) Objects.requireNonNull(file, PROTOCOL_FILE)).toPath());
    }

    public static URL[] toURLs(File... files) throws IOException {
        Objects.requireNonNull(files, "files");
        URL[] urls = new URL[files.length];
        for (int i = 0; i < urls.length; i++) {
            urls[i] = files[i].toURI().toURL();
        }
        return urls;
    }

    private static void validateMoveParameters(File source, File destination) throws FileNotFoundException {
        Objects.requireNonNull(source, "source");
        Objects.requireNonNull(destination, "destination");
        if (!source.exists()) {
            throw new FileNotFoundException("Source '" + source + "' does not exist");
        }
    }

    public static boolean waitFor(File file, int seconds) {
        Objects.requireNonNull(file, PROTOCOL_FILE);
        return PathUtils.waitFor(file.toPath(), Duration.ofSeconds(seconds), PathUtils.EMPTY_LINK_OPTION_ARRAY);
    }

    @Deprecated
    public static void write(File file, CharSequence data) throws IOException {
        write(file, data, Charset.defaultCharset(), false);
    }

    @Deprecated
    public static void write(File file, CharSequence data, boolean append) throws IOException {
        write(file, data, Charset.defaultCharset(), append);
    }

    public static void write(File file, CharSequence data, Charset charset) throws IOException {
        write(file, data, charset, false);
    }

    public static void write(File file, CharSequence data, Charset charset, boolean append) throws IOException {
        writeStringToFile(file, Objects.toString(data, null), charset, append);
    }

    public static void write(File file, CharSequence data, String charsetName) throws IOException {
        write(file, data, charsetName, false);
    }

    public static void write(File file, CharSequence data, String charsetName, boolean append) throws IOException {
        write(file, data, Charsets.toCharset(charsetName), append);
    }

    public static void writeByteArrayToFile(File file, byte[] data) throws IOException {
        writeByteArrayToFile(file, data, false);
    }

    public static void writeByteArrayToFile(File file, byte[] data, boolean append) throws IOException {
        writeByteArrayToFile(file, data, 0, data.length, append);
    }

    public static void writeByteArrayToFile(File file, byte[] data, int off, int len) throws IOException {
        writeByteArrayToFile(file, data, off, len, false);
    }

    public static void writeByteArrayToFile(File file, byte[] data, int off, int len, boolean append) throws IOException {
        OutputStream out = newOutputStream(file, append);
        try {
            out.write(data, off, len);
            if (out != null) {
                out.close();
            }
        } catch (Throwable th) {
            if (out != null) {
                try {
                    out.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static void writeLines(File file, Collection<?> lines) throws IOException {
        writeLines(file, null, lines, null, false);
    }

    public static void writeLines(File file, Collection<?> lines, boolean append) throws IOException {
        writeLines(file, null, lines, null, append);
    }

    public static void writeLines(File file, Collection<?> lines, String lineEnding) throws IOException {
        writeLines(file, null, lines, lineEnding, false);
    }

    public static void writeLines(File file, Collection<?> lines, String lineEnding, boolean append) throws IOException {
        writeLines(file, null, lines, lineEnding, append);
    }

    public static void writeLines(File file, String charsetName, Collection<?> lines) throws IOException {
        writeLines(file, charsetName, lines, null, false);
    }

    public static void writeLines(File file, String charsetName, Collection<?> lines, boolean append) throws IOException {
        writeLines(file, charsetName, lines, null, append);
    }

    public static void writeLines(File file, String charsetName, Collection<?> lines, String lineEnding) throws IOException {
        writeLines(file, charsetName, lines, lineEnding, false);
    }

    public static void writeLines(File file, String charsetName, Collection<?> lines, String lineEnding, boolean append) throws IOException {
        OutputStream out = new BufferedOutputStream(newOutputStream(file, append));
        try {
            IOUtils.writeLines(lines, lineEnding, out, charsetName);
            out.close();
        } catch (Throwable th) {
            try {
                out.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Deprecated
    public static void writeStringToFile(File file, String data) throws IOException {
        writeStringToFile(file, data, Charset.defaultCharset(), false);
    }

    @Deprecated
    public static void writeStringToFile(File file, String data, boolean append) throws IOException {
        writeStringToFile(file, data, Charset.defaultCharset(), append);
    }

    public static void writeStringToFile(File file, String data, Charset charset) throws IOException {
        writeStringToFile(file, data, charset, false);
    }

    public static void writeStringToFile(File file, String data, Charset charset, boolean append) throws IOException {
        OutputStream out = newOutputStream(file, append);
        try {
            IOUtils.write(data, out, charset);
            if (out != null) {
                out.close();
            }
        } catch (Throwable th) {
            if (out != null) {
                try {
                    out.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static void writeStringToFile(File file, String data, String charsetName) throws IOException {
        writeStringToFile(file, data, charsetName, false);
    }

    public static void writeStringToFile(File file, String data, String charsetName, boolean append) throws IOException {
        writeStringToFile(file, data, Charsets.toCharset(charsetName), append);
    }

    @Deprecated
    public FileUtils() {
    }
}
