package org.apache.commons.io.output;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import org.apache.commons.io.IOUtils;

/* loaded from: classes9.dex */
public class ChunkedWriter extends FilterWriter {
    private static final int DEFAULT_CHUNK_SIZE = 8192;
    private final int chunkSize;

    public ChunkedWriter(Writer writer) {
        this(writer, 8192);
    }

    public ChunkedWriter(Writer writer, int chunkSize) {
        super(writer);
        if (chunkSize <= 0) {
            throw new IllegalArgumentException();
        }
        this.chunkSize = chunkSize;
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(char[] data, int srcOffset, int length) throws IOException {
        IOUtils.checkFromIndexSize(data, srcOffset, length);
        int bytes = length;
        int dstOffset = srcOffset;
        while (bytes > 0) {
            int chunk = Math.min(bytes, this.chunkSize);
            this.out.write(data, dstOffset, chunk);
            bytes -= chunk;
            dstOffset += chunk;
        }
    }
}
