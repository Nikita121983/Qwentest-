package org.apache.commons.compress.archivers.sevenz;

import java.io.File;
import java.io.IOException;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;

/* loaded from: classes9.dex */
public class CLI {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public enum Mode {
        LIST("Analysing") { // from class: org.apache.commons.compress.archivers.sevenz.CLI.Mode.1
            private String getContentMethods(SevenZArchiveEntry entry) {
                StringBuilder sb = new StringBuilder();
                boolean first = true;
                for (SevenZMethodConfiguration m : entry.getContentMethods()) {
                    if (!first) {
                        sb.append(", ");
                    }
                    first = false;
                    sb.append(m.getMethod());
                    if (m.getOptions() != null) {
                        sb.append("(").append(m.getOptions()).append(")");
                    }
                }
                return sb.toString();
            }

            @Override // org.apache.commons.compress.archivers.sevenz.CLI.Mode
            public void takeAction(SevenZFile archive, SevenZArchiveEntry entry) {
                System.out.print(entry.getName());
                if (entry.isDirectory()) {
                    System.out.print(" dir");
                } else {
                    System.out.print(StringUtils.SPACE + entry.getCompressedSize() + PackagingURIHelper.FORWARD_SLASH_STRING + entry.getSize());
                }
                if (entry.getHasLastModifiedDate()) {
                    System.out.print(StringUtils.SPACE + entry.getLastModifiedDate());
                } else {
                    System.out.print(" no last modified date");
                }
                if (!entry.isDirectory()) {
                    System.out.println(StringUtils.SPACE + getContentMethods(entry));
                } else {
                    System.out.println();
                }
            }
        };

        private final String message;

        public abstract void takeAction(SevenZFile sevenZFile, SevenZArchiveEntry sevenZArchiveEntry) throws IOException;

        Mode(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }
    }

    private static Mode grabMode(String[] args) {
        if (args.length < 2) {
            return Mode.LIST;
        }
        return (Mode) Enum.valueOf(Mode.class, StringUtils.toRootUpperCase(args[1]));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void main(String[] args) throws IOException {
        if (ArrayUtils.isEmpty(args)) {
            usage();
            return;
        }
        Mode mode = grabMode(args);
        System.out.println(mode.getMessage() + StringUtils.SPACE + args[0]);
        File file = new File(args[0]);
        if (!file.isFile()) {
            System.err.println(file + " doesn't exist or is a directory");
        }
        SevenZFile archive = ((SevenZFile.Builder) SevenZFile.builder().setFile(file)).get();
        while (true) {
            try {
                SevenZArchiveEntry ae = archive.getNextEntry();
                if (ae == null) {
                    break;
                } else {
                    mode.takeAction(archive, ae);
                }
            } catch (Throwable th) {
                if (archive != null) {
                    try {
                        archive.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        if (archive != null) {
            archive.close();
        }
    }

    private static void usage() {
        System.out.println("Parameters: archive-name [list]");
    }
}
