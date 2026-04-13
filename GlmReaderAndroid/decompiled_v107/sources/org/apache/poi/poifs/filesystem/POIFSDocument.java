package org.apache.poi.poifs.filesystem;

import androidx.fragment.app.FragmentTransaction;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import org.apache.poi.poifs.dev.POIFSViewable;
import org.apache.poi.poifs.property.DocumentProperty;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.IOUtils;

/* loaded from: classes10.dex */
public final class POIFSDocument implements POIFSViewable, Iterable<ByteBuffer> {
    private int _block_size;
    private POIFSFileSystem _filesystem;
    private DocumentProperty _property;
    private POIFSStream _stream;

    public POIFSDocument(DocumentNode document) {
        this((DocumentProperty) document.getProperty(), ((DirectoryNode) document.getParent()).getFileSystem());
    }

    public POIFSDocument(DocumentProperty property, POIFSFileSystem filesystem) {
        this._property = property;
        this._filesystem = filesystem;
        if (property.getSize() < 4096) {
            this._stream = new POIFSStream(this._filesystem.getMiniStore(), property.getStartBlock());
            this._block_size = this._filesystem.getMiniStore().getBlockStoreBlockSize();
        } else {
            this._stream = new POIFSStream(this._filesystem, property.getStartBlock());
            this._block_size = this._filesystem.getBlockStoreBlockSize();
        }
    }

    public POIFSDocument(String name, POIFSFileSystem filesystem, InputStream stream) throws IOException {
        this._filesystem = filesystem;
        int length = store(stream);
        this._property = new DocumentProperty(name, length);
        this._property.setStartBlock(this._stream.getStartBlock());
        this._property.setDocument(this);
    }

    public POIFSDocument(String name, int size, POIFSFileSystem filesystem, POIFSWriterListener writer) throws IOException {
        this._filesystem = filesystem;
        if (size < 4096) {
            this._stream = new POIFSStream(filesystem.getMiniStore());
            this._block_size = this._filesystem.getMiniStore().getBlockStoreBlockSize();
        } else {
            this._stream = new POIFSStream(filesystem);
            this._block_size = this._filesystem.getBlockStoreBlockSize();
        }
        this._property = new DocumentProperty(name, size);
        this._property.setStartBlock(this._stream.getStartBlock());
        this._property.setDocument(this);
        DocumentOutputStream os = new DocumentOutputStream(this, size);
        try {
            POIFSDocumentPath path = new POIFSDocumentPath(name.split("\\\\"));
            String docName = path.getComponent(path.length() - 1);
            POIFSWriterEvent event = new POIFSWriterEvent(os, path, docName, size);
            writer.processPOIFSWriterEvent(event);
            os.close();
        } finally {
        }
    }

    private int store(InputStream stream) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(stream, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        bis.mark(4096);
        long streamBlockSize = IOUtils.skipFully(bis, 4096L);
        if (streamBlockSize < 4096) {
            this._stream = new POIFSStream(this._filesystem.getMiniStore());
            this._block_size = this._filesystem.getMiniStore().getBlockStoreBlockSize();
        } else {
            this._stream = new POIFSStream(this._filesystem);
            this._block_size = this._filesystem.getBlockStoreBlockSize();
        }
        bis.reset();
        OutputStream os = this._stream.getOutputStream();
        try {
            long length = IOUtils.copy(bis, os);
            int usedInBlock = (int) (length % this._block_size);
            if (usedInBlock != 0 && usedInBlock != this._block_size) {
                int toBlockEnd = this._block_size - usedInBlock;
                byte[] padding = IOUtils.safelyAllocate(toBlockEnd, POIFSFileSystem.getMaxRecordLength());
                Arrays.fill(padding, (byte) -1);
                os.write(padding);
            }
            if (os != null) {
                os.close();
            }
            return Math.toIntExact(length);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (os != null) {
                    try {
                        os.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void free() throws IOException {
        this._stream.free();
        this._property.setStartBlock(-2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public POIFSFileSystem getFileSystem() {
        return this._filesystem;
    }

    int getDocumentBlockSize() {
        return this._block_size;
    }

    @Override // java.lang.Iterable
    public Iterator<ByteBuffer> iterator() {
        return getBlockIterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Iterator<ByteBuffer> getBlockIterator() {
        return (getSize() > 0 ? this._stream : Collections.emptyList()).iterator();
    }

    public int getSize() {
        return this._property.getSize();
    }

    public void replaceContents(InputStream stream) throws IOException {
        free();
        int size = store(stream);
        this._property.setStartBlock(this._stream.getStartBlock());
        this._property.updateSize(size);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DocumentProperty getDocumentProperty() {
        return this._property;
    }

    @Override // org.apache.poi.poifs.dev.POIFSViewable
    public Object[] getViewableArray() {
        String result = "<NO DATA>";
        if (getSize() > 0) {
            byte[] data = IOUtils.safelyAllocate(getSize(), POIFSFileSystem.getMaxRecordLength());
            int offset = 0;
            Iterator<ByteBuffer> it = this._stream.iterator();
            while (it.hasNext()) {
                ByteBuffer buffer = it.next();
                int length = Math.min(this._block_size, data.length - offset);
                buffer.get(data, offset, length);
                offset += length;
            }
            result = HexDump.dump(data, 0L, 0);
        }
        return new String[]{result};
    }

    @Override // org.apache.poi.poifs.dev.POIFSViewable
    public Iterator<Object> getViewableIterator() {
        return Collections.emptyIterator();
    }

    @Override // org.apache.poi.poifs.dev.POIFSViewable
    public boolean preferArray() {
        return true;
    }

    @Override // org.apache.poi.poifs.dev.POIFSViewable
    public String getShortDescription() {
        return "Document: \"" + this._property.getName() + "\" size = " + getSize();
    }
}
