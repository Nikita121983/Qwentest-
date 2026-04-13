package org.apache.commons.compress.harmony.unpack200;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.jar.JarOutputStream;
import org.apache.commons.compress.harmony.pack200.Pack200Adapter;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.java.util.jar.Pack200;
import org.apache.commons.io.input.BoundedInputStream;
import org.apache.commons.io.input.CloseShieldInputStream;
import org.apache.commons.lang3.reflect.FieldUtils;

/* loaded from: classes9.dex */
public class Pack200UnpackerAdapter extends Pack200Adapter implements Pack200.Unpacker {
    static BoundedInputStream newBoundedInputStream(File file) throws IOException {
        return newBoundedInputStream(file.toPath());
    }

    private static BoundedInputStream newBoundedInputStream(FileInputStream fileInputStream) throws IOException {
        return newBoundedInputStream(readPathString(fileInputStream), new String[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static BoundedInputStream newBoundedInputStream(InputStream inputStream) throws IOException {
        if (inputStream instanceof BoundedInputStream) {
            return (BoundedInputStream) inputStream;
        }
        if (inputStream instanceof CloseShieldInputStream) {
            return newBoundedInputStream(((BoundedInputStream.Builder) BoundedInputStream.builder().setInputStream(inputStream)).get());
        }
        if (inputStream instanceof FilterInputStream) {
            return newBoundedInputStream(unwrap((FilterInputStream) inputStream));
        }
        if (inputStream instanceof FileInputStream) {
            return newBoundedInputStream((FileInputStream) inputStream);
        }
        return newBoundedInputStream(((BoundedInputStream.Builder) BoundedInputStream.builder().setInputStream(inputStream)).get());
    }

    /* JADX WARN: Multi-variable type inference failed */
    static BoundedInputStream newBoundedInputStream(Path path) throws IOException {
        return ((BoundedInputStream.Builder) ((BoundedInputStream.Builder) ((BoundedInputStream.Builder) BoundedInputStream.builder().setInputStream(new BufferedInputStream(Files.newInputStream(path, new OpenOption[0])))).setMaxCount(Files.size(path))).setPropagateClose(false)).get();
    }

    static BoundedInputStream newBoundedInputStream(String first, String... more) throws IOException {
        return newBoundedInputStream(Paths.get(first, more));
    }

    static BoundedInputStream newBoundedInputStream(URL url) throws IOException, URISyntaxException {
        return newBoundedInputStream(Paths.get(url.toURI()));
    }

    private static <T> T readField(Object obj, String str) {
        try {
            return (T) FieldUtils.readField(obj, str, true);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String readPathString(FileInputStream fis) {
        return (String) readField(fis, "path");
    }

    static InputStream unwrap(FilterInputStream filterInputStream) {
        return (InputStream) readField(filterInputStream, "in");
    }

    static InputStream unwrap(InputStream inputStream) {
        return inputStream instanceof FilterInputStream ? unwrap((FilterInputStream) inputStream) : inputStream;
    }

    @Override // org.apache.commons.compress.java.util.jar.Pack200.Unpacker
    public void unpack(File file, JarOutputStream out) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("Must specify input file.");
        }
        if (out == null) {
            throw new IllegalArgumentException("Must specify output stream.");
        }
        long size = file.length();
        int bufferSize = (size <= 0 || size >= 8192) ? 8192 : (int) size;
        InputStream in = new BufferedInputStream(Files.newInputStream(file.toPath(), new OpenOption[0]), bufferSize);
        try {
            unpack(in, out);
            in.close();
        } catch (Throwable th) {
            try {
                in.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // org.apache.commons.compress.java.util.jar.Pack200.Unpacker
    public void unpack(InputStream in, JarOutputStream out) throws IOException {
        if (in == null) {
            throw new IllegalArgumentException("Must specify input stream.");
        }
        if (out == null) {
            throw new IllegalArgumentException("Must specify output stream.");
        }
        completed(0.0d);
        try {
            new Archive(in, out).unpack();
            completed(1.0d);
        } catch (Pack200Exception e) {
            throw new IOException("Failed to unpack Jar:" + e);
        }
    }
}
