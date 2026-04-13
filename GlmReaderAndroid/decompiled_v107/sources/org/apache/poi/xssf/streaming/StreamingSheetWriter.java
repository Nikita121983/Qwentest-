package org.apache.poi.xssf.streaming;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.Removal;

/* loaded from: classes10.dex */
public class StreamingSheetWriter extends SheetDataWriter {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) StreamingSheetWriter.class);
    private boolean closed;

    @Removal(version = "6.0.0")
    public StreamingSheetWriter() throws IOException {
        this.closed = false;
        throw new IllegalStateException("StreamingSheetWriter requires OutputStream");
    }

    public StreamingSheetWriter(OutputStream out) throws IOException {
        super(createWriter(out));
        this.closed = false;
        LOG.atDebug().log("Preparing SXSSF sheet writer");
    }

    @Override // org.apache.poi.xssf.streaming.SheetDataWriter
    public File createTempFile() throws IOException {
        throw new IllegalStateException("Not supported with StreamingSheetWriter");
    }

    @Override // org.apache.poi.xssf.streaming.SheetDataWriter
    public Writer createWriter(File fd) throws IOException {
        throw new IllegalStateException("Not supported with StreamingSheetWriter");
    }

    protected static Writer createWriter(OutputStream out) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
    }

    @Override // org.apache.poi.xssf.streaming.SheetDataWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closed) {
            this._out.flush();
        }
    }

    @Override // org.apache.poi.xssf.streaming.SheetDataWriter
    public InputStream getWorksheetXMLInputStream() throws IOException {
        throw new IllegalStateException("Not supported with StreamingSheetWriter");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.poi.xssf.streaming.SheetDataWriter
    public boolean dispose() throws IOException {
        if (!this.closed) {
            this._out.close();
        }
        this.closed = true;
        return true;
    }
}
