package org.apache.commons.compress.harmony.unpack200;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.GZIPInputStream;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BoundedInputStream;
import org.apache.poi.hssf.usermodel.HSSFShapeTypes;

/* loaded from: classes9.dex */
public class Archive {
    private static final int[] MAGIC = {HSSFShapeTypes.TextBox, 254, 208, 13};
    private final boolean closeStreams;
    private boolean deflateHint;
    private final Path inputPath;
    private final long inputSize;
    private BoundedInputStream inputStream;
    private FileOutputStream logFile;
    private int logLevel;
    private final String outputFileName;
    private final JarOutputStream outputStream;
    private boolean overrideDeflateHint;
    private boolean removePackFile;

    public Archive(InputStream inputStream, JarOutputStream outputStream) throws IOException {
        this.logLevel = 1;
        this.inputStream = Pack200UnpackerAdapter.newBoundedInputStream(inputStream);
        this.outputStream = outputStream;
        if (inputStream instanceof FileInputStream) {
            this.inputPath = Paths.get(Pack200UnpackerAdapter.readPathString((FileInputStream) inputStream), new String[0]);
        } else {
            this.inputPath = null;
        }
        this.outputFileName = null;
        this.inputSize = -1L;
        this.closeStreams = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Archive(String inputFileName, String outputFileName) throws FileNotFoundException, IOException {
        this.logLevel = 1;
        this.inputPath = Paths.get(inputFileName, new String[0]);
        this.inputSize = Files.size(this.inputPath);
        this.inputStream = ((BoundedInputStream.Builder) ((BoundedInputStream.Builder) BoundedInputStream.builder().setPath(this.inputPath)).setMaxCount(this.inputSize)).get();
        this.outputStream = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(outputFileName)));
        this.outputFileName = outputFileName;
        this.closeStreams = true;
    }

    private boolean available(InputStream inputStream) throws IOException {
        inputStream.mark(1);
        int check = inputStream.read();
        inputStream.reset();
        return check != -1;
    }

    public void setDeflateHint(boolean deflateHint) {
        this.overrideDeflateHint = true;
        this.deflateHint = deflateHint;
    }

    public void setLogFile(String logFileName) throws FileNotFoundException {
        this.logFile = new FileOutputStream(logFileName);
    }

    public void setLogFile(String logFileName, boolean append) throws FileNotFoundException {
        this.logFile = new FileOutputStream(logFileName, append);
    }

    public void setQuiet(boolean quiet) {
        if (quiet || this.logLevel == 0) {
            this.logLevel = 0;
        }
    }

    public void setRemovePackFile(boolean removePackFile) {
        this.removePackFile = removePackFile;
    }

    public void setVerbose(boolean verbose) {
        if (!verbose) {
            if (this.logLevel == 2) {
                this.logLevel = 1;
                return;
            }
            return;
        }
        this.logLevel = 2;
    }

    /* JADX WARN: Finally extract failed */
    public void unpack() throws Pack200Exception, IOException {
        this.outputStream.setComment("PACK200");
        try {
            if (!this.inputStream.markSupported()) {
                this.inputStream = new BoundedInputStream(new BufferedInputStream(this.inputStream));
                if (!this.inputStream.markSupported()) {
                    throw new IllegalStateException();
                }
            }
            this.inputStream.mark(2);
            if (((this.inputStream.read() & 255) | ((this.inputStream.read() & 255) << 8)) == 35615) {
                this.inputStream.reset();
                this.inputStream = new BoundedInputStream(new BufferedInputStream(new GZIPInputStream(this.inputStream)));
            } else {
                this.inputStream.reset();
            }
            this.inputStream.mark(MAGIC.length);
            int[] word = new int[MAGIC.length];
            for (int i = 0; i < word.length; i++) {
                word[i] = this.inputStream.read();
            }
            int i2 = 0;
            int m = 0;
            while (true) {
                if (m >= MAGIC.length) {
                    break;
                }
                if (word[m] == MAGIC[m]) {
                    m++;
                } else {
                    i2 = 1;
                    break;
                }
            }
            this.inputStream.reset();
            if (i2 != 0) {
                JarInputStream jarInputStream = new JarInputStream(this.inputStream);
                while (true) {
                    JarEntry jarEntry = jarInputStream.getNextJarEntry();
                    if (jarEntry == null) {
                        break;
                    }
                    this.outputStream.putNextEntry(jarEntry);
                    IOUtils.copy(jarInputStream, this.outputStream, 16384);
                    this.outputStream.closeEntry();
                }
            } else {
                int i3 = 0;
                while (available(this.inputStream)) {
                    i3++;
                    Segment segment = new Segment();
                    segment.setLogLevel(this.logLevel);
                    segment.setLogStream(this.logFile);
                    segment.setPreRead(false);
                    if (i3 == 1) {
                        segment.log(2, "Unpacking from " + this.inputPath + " to " + this.outputFileName);
                    }
                    segment.log(2, "Reading segment " + i3);
                    if (this.overrideDeflateHint) {
                        segment.overrideDeflateHint(this.deflateHint);
                    }
                    segment.unpack(this.inputStream, this.outputStream);
                    this.outputStream.flush();
                }
            }
            if (this.closeStreams) {
                IOUtils.closeQuietly((InputStream) this.inputStream);
                IOUtils.closeQuietly((OutputStream) this.outputStream);
            }
            IOUtils.closeQuietly((OutputStream) this.logFile);
            if (this.removePackFile && this.inputPath != null) {
                Files.delete(this.inputPath);
            }
        } catch (Throwable th) {
            if (this.closeStreams) {
                IOUtils.closeQuietly((InputStream) this.inputStream);
                IOUtils.closeQuietly((OutputStream) this.outputStream);
            }
            IOUtils.closeQuietly((OutputStream) this.logFile);
            throw th;
        }
    }
}
