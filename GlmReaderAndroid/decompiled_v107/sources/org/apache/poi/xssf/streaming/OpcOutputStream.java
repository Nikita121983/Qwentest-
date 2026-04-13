package org.apache.poi.xssf.streaming;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import org.apache.poi.xssf.streaming.Zip64Impl;

/* loaded from: classes10.dex */
class OpcOutputStream extends DeflaterOutputStream {
    private final CRC32 crc;
    private Zip64Impl.Entry current;
    private final List<Zip64Impl.Entry> entries;
    private boolean finished;
    private final Zip64Impl spec;
    private long written;

    public OpcOutputStream(OutputStream out) {
        super(out, new Deflater(-1, true));
        this.entries = new ArrayList();
        this.crc = new CRC32();
        this.written = 0L;
        this.finished = false;
        this.spec = new Zip64Impl(out);
    }

    public void setLevel(int level) {
        ((DeflaterOutputStream) this).def.setLevel(level);
    }

    public void putNextEntry(String name) throws IOException {
        if (this.current != null) {
            closeEntry();
        }
        this.current = new Zip64Impl.Entry(name);
        this.current.offset = this.written;
        this.written += this.spec.writeLFH(this.current);
        this.entries.add(this.current);
    }

    public void closeEntry() throws IOException {
        if (this.current == null) {
            throw new IllegalStateException("not current zip current");
        }
        this.def.finish();
        while (!this.def.finished()) {
            deflate();
        }
        this.current.size = this.def.getBytesRead();
        this.current.compressedSize = this.def.getBytesWritten();
        this.current.crc = this.crc.getValue();
        this.written += this.current.compressedSize;
        this.written += this.spec.writeDAT(this.current);
        this.current = null;
        this.def.reset();
        this.crc.reset();
    }

    @Override // java.util.zip.DeflaterOutputStream
    public void finish() throws IOException {
        if (this.finished) {
            return;
        }
        if (this.current != null) {
            closeEntry();
        }
        long offset = this.written;
        for (Zip64Impl.Entry entry : this.entries) {
            this.written += this.spec.writeCEN(entry);
        }
        this.written += this.spec.writeEND(this.entries.size(), offset, this.written - offset);
        this.finished = true;
    }

    @Override // java.util.zip.DeflaterOutputStream, java.io.FilterOutputStream, java.io.OutputStream
    public synchronized void write(byte[] b, int off, int len) throws IOException {
        if (off >= 0 && len >= 0) {
            if (off <= b.length - len) {
                if (len == 0) {
                    return;
                }
                super.write(b, off, len);
                this.crc.update(b, off, len);
                return;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // java.util.zip.DeflaterOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        finish();
        this.out.close();
    }
}
