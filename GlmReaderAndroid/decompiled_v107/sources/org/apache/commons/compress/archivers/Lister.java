package org.apache.commons.compress.archivers;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.function.Consumer;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarFile;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.lang3.ArrayUtils;

/* loaded from: classes9.dex */
public final class Lister {
    private static final ArchiveStreamFactory FACTORY = ArchiveStreamFactory.DEFAULT;
    private final String[] args;
    private final boolean quiet;

    private static <T extends ArchiveInputStream<? extends E>, E extends ArchiveEntry> T createArchiveInputStream(String[] strArr, InputStream inputStream) throws ArchiveException {
        if (strArr.length > 1) {
            return (T) FACTORY.createArchiveInputStream(strArr[1], inputStream);
        }
        return (T) FACTORY.createArchiveInputStream(inputStream);
    }

    private static String detectFormat(Path file) throws ArchiveException, IOException {
        InputStream inputStream = new BufferedInputStream(Files.newInputStream(file, new OpenOption[0]));
        try {
            String detect = ArchiveStreamFactory.detect(inputStream);
            inputStream.close();
            return detect;
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static void main(String... args) throws ArchiveException, IOException {
        if (ArrayUtils.isEmpty(args)) {
            usage();
        } else {
            new Lister(false, args).go();
        }
    }

    private static void usage() {
        System.err.println("Parameters: archive-name [archive-type]\n");
        System.err.println("The magic archive-type 'zipfile' prefers ZipFile over ZipArchiveInputStream");
        System.err.println("The magic archive-type 'tarfile' prefers TarFile over TarArchiveInputStream");
    }

    @Deprecated
    public Lister() {
        this(false, "");
    }

    Lister(boolean quiet, String... args) {
        this.quiet = quiet;
        this.args = (String[]) args.clone();
        Objects.requireNonNull(args[0], "args[0]");
    }

    void go() throws ArchiveException, IOException {
        list(Paths.get(this.args[0], new String[0]), this.args);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x007e, code lost:
    
        if (r1.equals(org.apache.commons.compress.archivers.ArchiveStreamFactory.SEVEN_Z) != false) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void list(java.nio.file.Path r6, java.lang.String... r7) throws org.apache.commons.compress.archivers.ArchiveException, java.io.IOException {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Analyzing "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r6)
            java.lang.String r0 = r0.toString()
            r5.println(r0)
            r0 = 0
            java.nio.file.LinkOption[] r1 = new java.nio.file.LinkOption[r0]
            boolean r1 = java.nio.file.Files.isRegularFile(r6, r1)
            if (r1 != 0) goto L37
            java.io.PrintStream r1 = java.lang.System.err
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r2 = r2.append(r6)
            java.lang.String r3 = " doesn't exist or is a directory"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
        L37:
            int r1 = r7.length
            r2 = 1
            if (r1 <= r2) goto L3e
            r1 = r7[r2]
            goto L42
        L3e:
            java.lang.String r1 = detectFormat(r6)
        L42:
            java.lang.String r1 = org.apache.commons.lang3.StringUtils.toRootLowerCase(r1)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Detected format "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r1)
            java.lang.String r3 = r3.toString()
            r5.println(r3)
            int r3 = r1.hashCode()
            switch(r3) {
                case 1827: goto L78;
                case 114597: goto L6e;
                case 120609: goto L64;
                default: goto L63;
            }
        L63:
            goto L81
        L64:
            java.lang.String r0 = "zip"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L63
            r0 = r2
            goto L82
        L6e:
            java.lang.String r0 = "tar"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L63
            r0 = 2
            goto L82
        L78:
            java.lang.String r2 = "7z"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L63
            goto L82
        L81:
            r0 = -1
        L82:
            switch(r0) {
                case 0: goto L91;
                case 1: goto L8d;
                case 2: goto L89;
                default: goto L85;
            }
        L85:
            r5.listStream(r6, r7)
            goto L95
        L89:
            r5.listZipUsingTarFile(r6)
            goto L95
        L8d:
            r5.listZipUsingZipFile(r6)
            goto L95
        L91:
            r5.list7z(r6)
        L95:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.Lister.list(java.nio.file.Path, java.lang.String[]):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void list7z(Path file) throws IOException {
        SevenZFile sevenZFile = ((SevenZFile.Builder) SevenZFile.builder().setPath(file)).get();
        try {
            println("Created " + sevenZFile);
            while (true) {
                ArchiveEntry entry = sevenZFile.getNextEntry();
                if (entry == null) {
                    break;
                } else {
                    println(entry.getName() == null ? sevenZFile.getDefaultName() + " (entry name was null)" : entry.getName());
                }
            }
            if (sevenZFile != null) {
                sevenZFile.close();
            }
        } catch (Throwable th) {
            if (sevenZFile != null) {
                try {
                    sevenZFile.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private void listStream(Path file, String[] args) throws ArchiveException, IOException {
        InputStream inputStream = new BufferedInputStream(Files.newInputStream(file, new OpenOption[0]));
        try {
            ArchiveInputStream<?> archiveInputStream = createArchiveInputStream(args, inputStream);
            try {
                println("Created " + archiveInputStream.toString());
                archiveInputStream.forEach(new IOConsumer() { // from class: org.apache.commons.compress.archivers.Lister$$ExternalSyntheticLambda0
                    @Override // org.apache.commons.io.function.IOConsumer
                    public final void accept(Object obj) {
                        Lister.this.println((ArchiveEntry) obj);
                    }
                });
                if (archiveInputStream != null) {
                    archiveInputStream.close();
                }
                inputStream.close();
            } finally {
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private void listZipUsingTarFile(Path file) throws IOException {
        TarFile tarFile = new TarFile(file);
        try {
            println("Created " + tarFile);
            tarFile.getEntries().forEach(new Consumer() { // from class: org.apache.commons.compress.archivers.Lister$$ExternalSyntheticLambda2
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Lister.this.println((TarArchiveEntry) obj);
                }
            });
            tarFile.close();
        } catch (Throwable th) {
            try {
                tarFile.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void listZipUsingZipFile(Path file) throws IOException {
        ZipFile zipFile = ((ZipFile.Builder) ZipFile.builder().setPath(file)).get();
        try {
            println("Created " + zipFile);
            zipFile.stream().forEach(new IOConsumer() { // from class: org.apache.commons.compress.archivers.Lister$$ExternalSyntheticLambda1
                @Override // org.apache.commons.io.function.IOConsumer
                public final void accept(Object obj) {
                    Lister.this.println((ZipArchiveEntry) obj);
                }
            });
            if (zipFile != null) {
                zipFile.close();
            }
        } catch (Throwable th) {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void println(ArchiveEntry entry) {
        println(entry.getName());
    }

    private void println(String line) {
        if (!this.quiet) {
            System.out.println(line);
        }
    }
}
