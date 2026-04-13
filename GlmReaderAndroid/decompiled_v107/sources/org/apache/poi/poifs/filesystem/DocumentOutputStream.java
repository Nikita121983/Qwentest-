package org.apache.poi.poifs.filesystem;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.poi.poifs.property.DocumentProperty;

/* loaded from: classes10.dex */
public final class DocumentOutputStream extends OutputStream {
    private UnsynchronizedByteArrayOutputStream _buffer;
    private boolean _closed;
    private final POIFSDocument _document;
    private int _document_size;
    private final long _limit;
    private final DocumentProperty _property;
    private POIFSStream _stream;
    private OutputStream _stream_output;

    public DocumentOutputStream(DocumentEntry document) throws IOException {
        this(document, -1L);
    }

    public DocumentOutputStream(DirectoryEntry parent, String name) throws IOException {
        this(createDocument(parent, name), -1L);
    }

    DocumentOutputStream(DocumentEntry document, long limit) throws IOException {
        this(getDocument(document), limit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DocumentOutputStream(POIFSDocument document, long limit) throws IOException {
        this._document_size = 0;
        this._closed = false;
        this._buffer = UnsynchronizedByteArrayOutputStream.builder().setBufferSize(4096).get();
        this._document = document;
        this._document.free();
        this._property = document.getDocumentProperty();
        this._limit = limit;
    }

    private static POIFSDocument getDocument(DocumentEntry document) throws IOException {
        if (!(document instanceof DocumentNode)) {
            throw new IOException("Cannot open internal document storage, " + document + " not a Document Node");
        }
        return new POIFSDocument((DocumentNode) document);
    }

    private static DocumentEntry createDocument(DirectoryEntry parent, String name) throws IOException {
        if (!(parent instanceof DirectoryNode)) {
            throw new IOException("Cannot open internal directory storage, " + parent + " not a Directory Node");
        }
        return parent.createDocument(name, UnsynchronizedByteArrayInputStream.builder().setByteArray(new byte[0]).get());
    }

    private void checkBufferSize() throws IOException {
        if (this._buffer.size() > 4096) {
            byte[] data = this._buffer.toByteArray();
            this._buffer = null;
            write(data, 0, data.length);
        }
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        write(new byte[]{(byte) b}, 0, 1);
    }

    @Override // java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        if (this._closed) {
            throw new IOException("cannot perform requested operation on a closed stream");
        }
        if (this._limit > -1 && size() + len > this._limit) {
            throw new IOException("tried to write too much data");
        }
        if (this._buffer != null) {
            this._buffer.write(b, off, len);
            checkBufferSize();
            return;
        }
        if (this._stream == null) {
            this._stream = new POIFSStream(this._document.getFileSystem());
            this._stream_output = this._stream.getOutputStream();
        }
        this._stream_output.write(b, off, len);
        this._document_size += len;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this._buffer != null) {
            this._document.replaceContents(this._buffer.toInputStream());
        } else {
            this._stream_output.close();
            this._property.updateSize(this._document_size);
            this._property.setStartBlock(this._stream.getStartBlock());
        }
        this._closed = true;
    }

    public long size() {
        return this._document_size + (this._buffer == null ? 0L : this._buffer.size());
    }
}
