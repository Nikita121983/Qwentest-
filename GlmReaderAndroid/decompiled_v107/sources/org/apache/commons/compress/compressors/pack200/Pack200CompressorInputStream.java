package org.apache.commons.compress.compressors.pack200;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Map;
import java.util.jar.JarOutputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.java.util.jar.Pack200;
import org.apache.commons.io.IOUtils;

/* loaded from: classes9.dex */
public class Pack200CompressorInputStream extends CompressorInputStream {
    private static final byte[] CAFE_DOOD = {-54, -2, -48, 13};
    private static final int SIG_LENGTH = CAFE_DOOD.length;
    private final AbstractStreamBridge abstractStreamBridge;
    private final InputStream originalInputStream;

    public static boolean matches(byte[] signature, int length) {
        if (length < SIG_LENGTH) {
            return false;
        }
        for (int i = 0; i < SIG_LENGTH; i++) {
            if (signature[i] != CAFE_DOOD[i]) {
                return false;
            }
        }
        return true;
    }

    public Pack200CompressorInputStream(File file) throws IOException {
        this(file, Pack200Strategy.IN_MEMORY);
    }

    public Pack200CompressorInputStream(File file, Map<String, String> properties) throws IOException {
        this(file, Pack200Strategy.IN_MEMORY, properties);
    }

    public Pack200CompressorInputStream(File file, Pack200Strategy mode) throws IOException {
        this(null, file, mode, null);
    }

    public Pack200CompressorInputStream(File file, Pack200Strategy mode, Map<String, String> properties) throws IOException {
        this(null, file, mode, properties);
    }

    public Pack200CompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, Pack200Strategy.IN_MEMORY);
    }

    private Pack200CompressorInputStream(InputStream inputStream, File file, Pack200Strategy mode, Map<String, String> properties) throws IOException {
        this.originalInputStream = inputStream;
        this.abstractStreamBridge = mode.newStreamBridge();
        JarOutputStream jarOut = new JarOutputStream(this.abstractStreamBridge);
        try {
            Pack200.Unpacker unpacker = Pack200.newUnpacker();
            if (properties != null) {
                unpacker.properties().putAll(properties);
            }
            if (file == null) {
                unpacker.unpack(inputStream, jarOut);
            } else {
                unpacker.unpack(file, jarOut);
            }
            jarOut.close();
        } catch (Throwable th) {
            try {
                jarOut.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public Pack200CompressorInputStream(InputStream inputStream, Map<String, String> properties) throws IOException {
        this(inputStream, Pack200Strategy.IN_MEMORY, properties);
    }

    public Pack200CompressorInputStream(InputStream inputStream, Pack200Strategy mode) throws IOException {
        this(inputStream, null, mode, null);
    }

    public Pack200CompressorInputStream(InputStream inputStream, Pack200Strategy mode, Map<String, String> properties) throws IOException {
        this(inputStream, null, mode, properties);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return getInputStream().available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            this.abstractStreamBridge.stop();
        } finally {
            IOUtils.close(this.originalInputStream);
        }
    }

    private InputStream getInputStream() throws IOException {
        return this.abstractStreamBridge.getInputStream();
    }

    @Override // java.io.InputStream
    public synchronized void mark(int limit) {
        try {
            getInputStream().mark(limit);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        try {
            return getInputStream().markSupported();
        } catch (IOException e) {
            return false;
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return getInputStream().read();
    }

    @Override // java.io.InputStream
    public int read(byte[] b) throws IOException {
        return getInputStream().read(b);
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int count) throws IOException {
        return getInputStream().read(b, off, count);
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        getInputStream().reset();
    }

    @Override // java.io.InputStream
    public long skip(long count) throws IOException {
        return IOUtils.skip(getInputStream(), count);
    }
}
