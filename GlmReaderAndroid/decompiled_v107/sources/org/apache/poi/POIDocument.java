package org.apache.poi;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.List;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.PropertySet;
import org.apache.poi.hpsf.PropertySetFactory;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hpsf.WritingNotSupportedException;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDecryptor;
import org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIEncryptor;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;

/* loaded from: classes10.dex */
public abstract class POIDocument implements Closeable {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) POIDocument.class);
    private DirectoryNode directory;
    private DocumentSummaryInformation dsInf;
    private boolean initialized;
    private SummaryInformation sInf;

    public abstract void write() throws IOException;

    public abstract void write(File file) throws IOException;

    public abstract void write(OutputStream outputStream) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public POIDocument(DirectoryNode dir) {
        this.directory = dir;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public POIDocument(POIFSFileSystem fs) {
        this(fs.getRoot());
    }

    public DocumentSummaryInformation getDocumentSummaryInformation() {
        if (!this.initialized) {
            readProperties();
        }
        return this.dsInf;
    }

    public SummaryInformation getSummaryInformation() {
        if (!this.initialized) {
            readProperties();
        }
        return this.sInf;
    }

    public void createInformationProperties() {
        if (!this.initialized) {
            readProperties(false);
        }
        if (this.sInf == null) {
            this.sInf = PropertySetFactory.newSummaryInformation();
        }
        if (this.dsInf == null) {
            this.dsInf = PropertySetFactory.newDocumentSummaryInformation();
        }
    }

    @Internal
    public void readProperties() {
        readProperties(true);
    }

    @Internal
    public void readProperties(boolean warnIfNull) {
        if (this.initialized) {
            return;
        }
        DocumentSummaryInformation dsi = (DocumentSummaryInformation) readPropertySet(DocumentSummaryInformation.class, DocumentSummaryInformation.DEFAULT_STREAM_NAME, warnIfNull);
        if (dsi != null) {
            this.dsInf = dsi;
        }
        SummaryInformation si = (SummaryInformation) readPropertySet(SummaryInformation.class, SummaryInformation.DEFAULT_STREAM_NAME, warnIfNull);
        if (si != null) {
            this.sInf = si;
        }
        this.initialized = true;
    }

    private <T> T readPropertySet(Class<T> cls, String str, boolean z) {
        String substring = cls.getName().substring(cls.getName().lastIndexOf(46) + 1);
        try {
            T t = (T) getPropertySet(str);
            if (cls.isInstance(t)) {
                return t;
            }
            if (t != null) {
                LOG.atWarn().log("{} property set came back with wrong class - {}", substring, t.getClass().getName());
            } else if (z) {
                LOG.atWarn().log("{} property set came back as null", substring);
            }
            return null;
        } catch (IOException e) {
            LOG.atError().withThrowable(e).log("can't retrieve property set");
            return null;
        }
    }

    protected PropertySet getPropertySet(String setName) throws IOException {
        return getPropertySet(setName, getEncryptionInfo());
    }

    /* JADX WARN: Finally extract failed */
    protected PropertySet getPropertySet(String setName, EncryptionInfo encryptionInfo) throws IOException {
        DirectoryNode dirNode = this.directory;
        POIFSFileSystem encPoifs = null;
        try {
            if (encryptionInfo != null) {
                try {
                    if (encryptionInfo.isDocPropsEncrypted()) {
                        String encryptedStream = getEncryptedPropertyStreamName();
                        if (!dirNode.hasEntryCaseInsensitive(encryptedStream)) {
                            throw new EncryptedDocumentException("can't find encrypted property stream '" + encryptedStream + "'");
                        }
                        CryptoAPIDecryptor dec = (CryptoAPIDecryptor) encryptionInfo.getDecryptor();
                        encPoifs = dec.getSummaryEntries(dirNode, encryptedStream);
                        dirNode = encPoifs.getRoot();
                    }
                } catch (IOException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new IOException("Error getting property set with name " + setName, e2);
                }
            }
            if (dirNode != null && dirNode.hasEntryCaseInsensitive(setName)) {
                DocumentInputStream dis = dirNode.createDocumentInputStream(dirNode.getEntryCaseInsensitive(setName));
                try {
                    PropertySet create = PropertySetFactory.create(dis);
                    if (dis != null) {
                        dis.close();
                    }
                    IOUtils.closeQuietly(encPoifs);
                    return create;
                } finally {
                }
            }
            IOUtils.closeQuietly(encPoifs);
            return null;
        } catch (Throwable th) {
            IOUtils.closeQuietly(null);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeProperties() throws IOException {
        validateInPlaceWritePossible();
        writeProperties(this.directory.getFileSystem(), null);
    }

    @Internal
    public void writeProperties(POIFSFileSystem outFS) throws IOException {
        writeProperties(outFS, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeProperties(POIFSFileSystem outFS, List<String> writtenEntries) throws IOException {
        EncryptionInfo ei = getEncryptionInfo();
        Encryptor encGen = ei == null ? null : ei.getEncryptor();
        boolean encryptProps = ei != null && ei.isDocPropsEncrypted() && (encGen instanceof CryptoAPIEncryptor);
        POIFSFileSystem tmpFS = new POIFSFileSystem();
        POIFSFileSystem fs = encryptProps ? tmpFS : outFS;
        try {
            writePropertySet(SummaryInformation.DEFAULT_STREAM_NAME, getSummaryInformation(), fs, writtenEntries);
            writePropertySet(DocumentSummaryInformation.DEFAULT_STREAM_NAME, getDocumentSummaryInformation(), fs, writtenEntries);
            if (encryptProps) {
                writePropertySet(DocumentSummaryInformation.DEFAULT_STREAM_NAME, PropertySetFactory.newDocumentSummaryInformation(), outFS);
                if (outFS.getRoot().hasEntryCaseInsensitive(SummaryInformation.DEFAULT_STREAM_NAME)) {
                    outFS.getRoot().getEntryCaseInsensitive(SummaryInformation.DEFAULT_STREAM_NAME).delete();
                }
                CryptoAPIEncryptor enc = (CryptoAPIEncryptor) encGen;
                try {
                    enc.setSummaryEntries(outFS.getRoot(), getEncryptedPropertyStreamName(), fs);
                    tmpFS.close();
                    return;
                } catch (GeneralSecurityException e) {
                    throw new IOException(e);
                }
            }
            tmpFS.close();
        } finally {
        }
    }

    private void writePropertySet(String name, PropertySet ps, POIFSFileSystem outFS, List<String> writtenEntries) throws IOException {
        if (ps == null) {
            return;
        }
        writePropertySet(name, ps, outFS);
        if (writtenEntries != null) {
            writtenEntries.add(name);
        }
    }

    private void writePropertySet(String name, PropertySet set, POIFSFileSystem outFS) throws IOException {
        try {
            UnsynchronizedByteArrayOutputStream bOut = UnsynchronizedByteArrayOutputStream.builder().get();
            try {
                PropertySet mSet = new PropertySet(set);
                mSet.write(bOut);
                InputStream bIn = bOut.toInputStream();
                try {
                    outFS.createOrUpdateDocument(bIn, name);
                    if (bIn != null) {
                        bIn.close();
                    }
                    LOG.atInfo().log("Wrote property set {} of size {}", name, Unbox.box(bOut.size()));
                    if (bOut != null) {
                        bOut.close();
                    }
                } finally {
                }
            } finally {
            }
        } catch (WritingNotSupportedException e) {
            LOG.atError().log("Couldn't write property set with name {} as not supported by HPSF yet", name);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void validateInPlaceWritePossible() throws IllegalStateException {
        if (this.directory == null) {
            throw new IllegalStateException("Newly created Document, cannot save in-place");
        }
        if (this.directory.getParent() != null) {
            throw new IllegalStateException("This is not the root Document, cannot save embedded resource in-place");
        }
        if (this.directory.getFileSystem() == null || !this.directory.getFileSystem().isInPlaceWriteable()) {
            throw new IllegalStateException("Opened read-only or via an InputStream, a Writeable File is required");
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.directory != null && this.directory.getFileSystem() != null) {
            this.directory.getFileSystem().close();
            clearDirectory();
        }
    }

    @Internal
    public DirectoryNode getDirectory() {
        return this.directory;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public void clearDirectory() {
        this.directory = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public boolean initDirectory() {
        if (this.directory == null) {
            this.directory = new POIFSFileSystem().getRoot();
            return true;
        }
        return false;
    }

    @Internal
    protected void replaceDirectory(DirectoryNode newDirectory) throws IOException {
        if (newDirectory != this.directory) {
            if (newDirectory != null && this.directory != null && newDirectory.getFileSystem() == this.directory.getFileSystem()) {
                return;
            }
            if (this.directory != null && this.directory.getFileSystem() != null) {
                this.directory.getFileSystem().close();
            }
            this.directory = newDirectory;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getEncryptedPropertyStreamName() {
        return "encryption";
    }

    public EncryptionInfo getEncryptionInfo() throws IOException {
        return null;
    }
}
