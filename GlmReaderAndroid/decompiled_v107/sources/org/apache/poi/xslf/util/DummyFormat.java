package org.apache.poi.xslf.util;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;

/* loaded from: classes10.dex */
public class DummyFormat implements OutputFormat {
    private final UnsynchronizedByteArrayOutputStream bos;
    private final DummyGraphics2d dummy2d;

    public DummyFormat() {
        try {
            this.bos = UnsynchronizedByteArrayOutputStream.builder().get();
            this.dummy2d = new DummyGraphics2d(new PrintStream((OutputStream) this.bos, true, StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // org.apache.poi.xslf.util.OutputFormat
    public Graphics2D addSlide(double width, double height) {
        this.bos.reset();
        return this.dummy2d;
    }

    @Override // org.apache.poi.xslf.util.OutputFormat
    public void writeSlide(MFProxy proxy, File outFile) throws IOException {
        OutputStream fos = Files.newOutputStream(outFile.toPath(), new OpenOption[0]);
        try {
            this.bos.writeTo(fos);
            this.bos.reset();
            if (fos != null) {
                fos.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.poi.xslf.util.OutputFormat
    public void writeDocument(MFProxy proxy, File outFile) {
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.bos.reset();
    }
}
