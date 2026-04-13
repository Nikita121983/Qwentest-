package org.apache.commons.compress.archivers;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Objects;
import kotlin.UByte;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.function.IOIterator;
import org.apache.commons.io.input.NullInputStream;

/* loaded from: classes9.dex */
public abstract class ArchiveInputStream<E extends ArchiveEntry> extends FilterInputStream {
    private static final int BYTE_MASK = 255;
    private long bytesRead;
    private Charset charset;
    private final byte[] single;

    public abstract E getNextEntry() throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public final class ArchiveEntryIOIterator implements IOIterator<E> {
        private E next;

        ArchiveEntryIOIterator() {
        }

        @Override // org.apache.commons.io.function.IOIterator
        public boolean hasNext() throws IOException {
            if (this.next == null) {
                this.next = (E) ArchiveInputStream.this.getNextEntry();
            }
            return this.next != null;
        }

        @Override // org.apache.commons.io.function.IOIterator
        public synchronized E next() throws IOException {
            if (this.next != null) {
                E e = this.next;
                this.next = null;
                return e;
            }
            return (E) ArchiveInputStream.this.getNextEntry();
        }

        @Override // org.apache.commons.io.function.IOIterator
        public Iterator<E> unwrap() {
            return null;
        }
    }

    public ArchiveInputStream() {
        this(new NullInputStream(), Charset.defaultCharset());
    }

    private ArchiveInputStream(InputStream inputStream, Charset charset) {
        super(inputStream);
        this.single = new byte[1];
        this.charset = Charsets.toCharset(charset);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ArchiveInputStream(InputStream inputStream, String charsetName) {
        this(inputStream, Charsets.toCharset(charsetName));
    }

    public boolean canReadEntryData(ArchiveEntry archiveEntry) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void count(int read) {
        count(read);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void count(long read) {
        if (read != -1) {
            this.bytesRead += read;
        }
    }

    public void forEach(IOConsumer<? super E> action) throws IOException {
        iterator().forEachRemaining((IOConsumer) Objects.requireNonNull(action));
    }

    public long getBytesRead() {
        return this.bytesRead;
    }

    public Charset getCharset() {
        return this.charset;
    }

    @Deprecated
    public int getCount() {
        return (int) this.bytesRead;
    }

    public IOIterator<E> iterator() {
        return new ArchiveEntryIOIterator();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int readlimit) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void pushedBackBytes(long pushedBack) {
        this.bytesRead -= pushedBack;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int num = read(this.single, 0, 1);
        if (num == -1) {
            return -1;
        }
        return this.single[0] & UByte.MAX_VALUE;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
    }
}
