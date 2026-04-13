package org.apache.xmlbeans.impl.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/* loaded from: classes11.dex */
public class IOUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Path tmpdir = Paths.get(System.getProperty("java.io.tmpdir"), new String[0]);

    public static void copyCompletely(InputStream input, OutputStream output) throws IOException {
        try {
            if ((output instanceof FileOutputStream) && (input instanceof FileInputStream)) {
                try {
                    FileChannel target = ((FileOutputStream) output).getChannel();
                    FileChannel source = ((FileInputStream) input).getChannel();
                    source.transferTo(0L, 2147483647L, target);
                    source.close();
                    target.close();
                    try {
                        input.close();
                    } catch (IOException e) {
                    }
                    try {
                        output.close();
                        return;
                    } catch (IOException e2) {
                        return;
                    }
                } catch (Exception e3) {
                }
            }
            byte[] buf = new byte[8192];
            while (true) {
                int length = input.read(buf);
                if (length < 0) {
                    try {
                        break;
                    } catch (IOException e4) {
                    }
                } else {
                    output.write(buf, 0, length);
                }
            }
            input.close();
            try {
                output.close();
            } catch (IOException e5) {
            }
        } finally {
        }
    }

    public static void copyCompletely(Reader input, Writer output) throws IOException {
        try {
            char[] buf = new char[8192];
            while (true) {
                int length = input.read(buf);
                if (length < 0) {
                    try {
                        break;
                    } catch (IOException e) {
                    }
                } else {
                    output.write(buf, 0, length);
                }
            }
            input.close();
            try {
                output.close();
            } catch (IOException e2) {
            }
        } finally {
        }
    }

    public static void copyCompletely(URI input, URI output) throws IOException {
        File out = new File(output);
        File dir = out.getParentFile();
        dir.mkdirs();
        try {
            InputStream in = urlToStream(input);
            try {
                OutputStream os = Files.newOutputStream(out.toPath(), new OpenOption[0]);
                try {
                    copyCompletely(in, os);
                    if (os != null) {
                        os.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                } finally {
                }
            } finally {
            }
        } catch (IllegalArgumentException e) {
            throw new IOException("Cannot copy to " + output);
        }
    }

    private static InputStream urlToStream(URI input) throws IOException {
        try {
            File f = new File(input);
            if (f.exists()) {
                return Files.newInputStream(f.toPath(), new OpenOption[0]);
            }
        } catch (Exception e) {
        }
        return input.toURL().openStream();
    }

    public static File createDir(File rootdir, String subdir) {
        File newdir = subdir == null ? rootdir : new File(rootdir, subdir);
        boolean created = (newdir.exists() && newdir.isDirectory()) || newdir.mkdirs();
        if (!created) {
            throw new AssertionError("Could not create " + newdir.getAbsolutePath());
        }
        return newdir;
    }

    public static Path getTempDir() {
        return tmpdir;
    }
}
