package org.apache.poi.poifs.crypt;

import com.zaxxer.sparsebits.SparseBitSet;
import java.io.File;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSWriterEvent;
import org.apache.poi.poifs.filesystem.POIFSWriterListener;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.TempFile;

@Internal
/* loaded from: classes10.dex */
public abstract class ChunkedCipherOutputStream extends FilterOutputStream {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) ChunkedCipherOutputStream.class);
    private static final int STREAMING = -1;
    private final byte[] chunk;
    private final int chunkBits;
    private final int chunkSize;
    private Cipher cipher;
    private final DirectoryNode dir;
    private final File fileOut;
    private boolean isClosed;
    private final SparseBitSet plainByteFlags;
    private long pos;
    private long totalPos;
    private long written;

    protected abstract void calculateChecksum(File file, int i) throws GeneralSecurityException, IOException;

    protected abstract void createEncryptionInfoEntry(DirectoryNode directoryNode, File file) throws IOException, GeneralSecurityException;

    protected abstract Cipher initCipherForBlock(Cipher cipher, int i, boolean z) throws IOException, GeneralSecurityException;

    public ChunkedCipherOutputStream(DirectoryNode dir, int chunkSize) throws IOException, GeneralSecurityException {
        super(null);
        this.chunkSize = chunkSize;
        int cs = chunkSize == -1 ? 4096 : chunkSize;
        this.chunk = IOUtils.safelyAllocate(cs, CryptoFunctions.MAX_RECORD_LENGTH);
        this.plainByteFlags = new SparseBitSet(cs);
        this.chunkBits = Integer.bitCount(cs - 1);
        this.fileOut = TempFile.createTempFile("encrypted_package", "crypt");
        this.out = Files.newOutputStream(this.fileOut.toPath(), new OpenOption[0]);
        this.dir = dir;
        this.cipher = initCipherForBlock(null, 0, false);
    }

    public ChunkedCipherOutputStream(OutputStream stream, int chunkSize) throws IOException, GeneralSecurityException {
        super(stream);
        this.chunkSize = chunkSize;
        int cs = chunkSize == -1 ? 4096 : chunkSize;
        this.chunk = IOUtils.safelyAllocate(cs, CryptoFunctions.MAX_RECORD_LENGTH);
        this.plainByteFlags = new SparseBitSet(cs);
        this.chunkBits = Integer.bitCount(cs - 1);
        this.fileOut = null;
        this.dir = null;
        this.cipher = initCipherForBlock(null, 0, false);
    }

    public final Cipher initCipherForBlock(int block, boolean lastChunk) throws IOException, GeneralSecurityException {
        return initCipherForBlock(this.cipher, block, lastChunk);
    }

    @Internal
    protected Cipher initCipherForBlockNoFlush(Cipher existing, int block, boolean lastChunk) throws IOException, GeneralSecurityException {
        return initCipherForBlock(this.cipher, block, lastChunk);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int b) throws IOException {
        write(new byte[]{(byte) b});
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        write(b, off, len, false);
    }

    public void writePlain(byte[] b, int off, int len) throws IOException {
        write(b, off, len, true);
    }

    protected void write(byte[] b, int off, int len, boolean writePlain) throws IOException {
        if (len == 0) {
            return;
        }
        if (len < 0 || b.length < off + len) {
            throw new IOException("not enough bytes in your input buffer");
        }
        int chunkMask = getChunkMask();
        while (len > 0) {
            int posInChunk = (int) (this.pos & chunkMask);
            int nextLen = Math.min(this.chunk.length - posInChunk, len);
            System.arraycopy(b, off, this.chunk, posInChunk, nextLen);
            if (writePlain) {
                this.plainByteFlags.set(posInChunk, posInChunk + nextLen);
            }
            this.pos += nextLen;
            this.totalPos += nextLen;
            off += nextLen;
            len -= nextLen;
            if ((this.pos & chunkMask) == 0) {
                writeChunk(len > 0);
            }
        }
    }

    protected int getChunkMask() {
        return this.chunk.length - 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeChunk(boolean continued) throws IOException {
        boolean lastChunk;
        if (this.pos == 0 || this.totalPos == this.written) {
            return;
        }
        int posInChunk = (int) (this.pos & getChunkMask());
        int index = (int) (this.pos >> this.chunkBits);
        if (posInChunk == 0) {
            index--;
            posInChunk = this.chunk.length;
            lastChunk = false;
        } else {
            lastChunk = true;
        }
        boolean doFinal = true;
        try {
            long oldPos = this.pos;
            this.pos = 0L;
            if (this.chunkSize == -1) {
                if (continued) {
                    doFinal = false;
                }
            } else {
                this.cipher = initCipherForBlock(this.cipher, index, lastChunk);
                this.pos = oldPos;
            }
            int ciLen = invokeCipher(posInChunk, doFinal);
            this.out.write(this.chunk, 0, ciLen);
            this.plainByteFlags.clear();
            this.written += ciLen;
        } catch (GeneralSecurityException e) {
            throw new IOException("can't re-/initialize cipher", e);
        }
    }

    protected int invokeCipher(int posInChunk, boolean doFinal) throws GeneralSecurityException, IOException {
        int ciLen;
        boolean lastChunk;
        byte[] plain = this.plainByteFlags.isEmpty() ? null : (byte[]) this.chunk.clone();
        if (doFinal) {
            ciLen = this.cipher.doFinal(this.chunk, 0, posInChunk, this.chunk);
        } else {
            ciLen = this.cipher.update(this.chunk, 0, posInChunk, this.chunk);
        }
        if (doFinal && "IBMJCE".equals(this.cipher.getProvider().getName()) && "RC4".equals(this.cipher.getAlgorithm())) {
            int index = (int) (this.pos >> this.chunkBits);
            if (posInChunk == 0) {
                index--;
                posInChunk = this.chunk.length;
                lastChunk = false;
            } else {
                lastChunk = true;
            }
            this.cipher = initCipherForBlockNoFlush(this.cipher, index, lastChunk);
        }
        if (plain != null) {
            int i = this.plainByteFlags.nextSetBit(0);
            while (i >= 0 && i < posInChunk) {
                this.chunk[i] = plain[i];
                i = this.plainByteFlags.nextSetBit(i + 1);
            }
        }
        return ciLen;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.isClosed) {
            LOG.atDebug().log("ChunkedCipherOutputStream was already closed - ignoring");
            return;
        }
        this.isClosed = true;
        try {
            try {
                writeChunk(false);
                super.close();
                if (this.fileOut != null) {
                    int oleStreamSize = (int) (this.fileOut.length() + 8);
                    calculateChecksum(this.fileOut, (int) this.pos);
                    this.dir.createDocument(Decryptor.DEFAULT_POIFS_ENTRY, oleStreamSize, new POIFSWriterListener() { // from class: org.apache.poi.poifs.crypt.ChunkedCipherOutputStream$$ExternalSyntheticLambda0
                        @Override // org.apache.poi.poifs.filesystem.POIFSWriterListener
                        public final void processPOIFSWriterEvent(POIFSWriterEvent pOIFSWriterEvent) {
                            ChunkedCipherOutputStream.this.processPOIFSWriterEvent(pOIFSWriterEvent);
                        }
                    });
                    createEncryptionInfoEntry(this.dir, this.fileOut);
                }
            } catch (GeneralSecurityException e) {
                throw new IOException(e);
            }
        } finally {
            if (this.fileOut != null) {
                this.fileOut.delete();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] getChunk() {
        return this.chunk;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SparseBitSet getPlainByteFlags() {
        return this.plainByteFlags;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long getPos() {
        return this.pos;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long getTotalPos() {
        return this.totalPos;
    }

    public void setNextRecordSize(int recordSize, boolean isPlain) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processPOIFSWriterEvent(POIFSWriterEvent event) {
        try {
            OutputStream os = event.getStream();
            try {
                InputStream fis = Files.newInputStream(this.fileOut.toPath(), new OpenOption[0]);
                try {
                    byte[] buf = new byte[8];
                    LittleEndian.putLong(buf, 0, this.pos);
                    os.write(buf);
                    IOUtils.copy(fis, os);
                    if (fis != null) {
                        fis.close();
                    }
                    if (os != null) {
                        os.close();
                    }
                    if (!this.fileOut.delete()) {
                        LOG.atError().log("Can't delete temporary encryption file: {}", this.fileOut);
                    }
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            throw new EncryptedDocumentException(e);
        }
    }
}
