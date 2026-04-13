package org.apache.commons.io.input;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.function.IOSupplier;
import org.apache.commons.io.function.Uncheck;

/* loaded from: classes9.dex */
public class SequenceReader extends Reader {
    private Reader reader;
    private final Iterator<? extends Reader> readers;

    public SequenceReader(Iterable<? extends Reader> readers) {
        this.readers = ((Iterable) Objects.requireNonNull(readers, "readers")).iterator();
        this.reader = (Reader) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.input.SequenceReader$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                Reader nextReader;
                nextReader = SequenceReader.this.nextReader();
                return nextReader;
            }
        });
    }

    public SequenceReader(Reader... readers) {
        this(Arrays.asList(readers));
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        do {
        } while (nextReader() != null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Reader nextReader() throws IOException {
        if (this.reader != null) {
            this.reader.close();
        }
        if (this.readers.hasNext()) {
            this.reader = this.readers.next();
        } else {
            this.reader = null;
        }
        return this.reader;
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        int c = -1;
        while (this.reader != null && (c = this.reader.read()) == -1) {
            nextReader();
        }
        return c;
    }

    @Override // java.io.Reader
    public int read(char[] cbuf, int off, int len) throws IOException {
        IOUtils.checkFromIndexSize(cbuf, off, len);
        if (len == 0) {
            return 0;
        }
        int count = 0;
        while (this.reader != null) {
            int readLen = this.reader.read(cbuf, off, len);
            if (readLen == -1) {
                nextReader();
            } else {
                count += readLen;
                off += readLen;
                len -= readLen;
                if (len <= 0) {
                    break;
                }
            }
        }
        if (count > 0) {
            return count;
        }
        return -1;
    }
}
