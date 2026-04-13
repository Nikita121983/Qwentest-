package org.apache.poi.poifs.filesystem;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;

@Internal
/* loaded from: classes10.dex */
public final class DocumentFactoryHelper {
    private DocumentFactoryHelper() {
    }

    public static InputStream getDecryptedStream(final POIFSFileSystem fs, String password) throws IOException {
        return new FilterInputStream(getDecryptedStream(fs.getRoot(), password)) { // from class: org.apache.poi.poifs.filesystem.DocumentFactoryHelper.1
            @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                fs.close();
                super.close();
            }
        };
    }

    public static InputStream getDecryptedStream(DirectoryNode root, String password) throws IOException {
        if (root.hasEntryCaseInsensitive(ExtractorFactory.OOXML_PACKAGE)) {
            return root.createDocumentInputStream(ExtractorFactory.OOXML_PACKAGE);
        }
        EncryptionInfo info = new EncryptionInfo(root);
        Decryptor d = Decryptor.getInstance(info);
        boolean passwordCorrect = false;
        if (password != null) {
            try {
                if (d.verifyPassword(password)) {
                    passwordCorrect = true;
                }
            } catch (GeneralSecurityException e) {
                throw new IOException(e);
            }
        }
        if (!passwordCorrect && d.verifyPassword(Decryptor.DEFAULT_PASSWORD)) {
            passwordCorrect = true;
        }
        if (passwordCorrect) {
            return d.getDataStream(root);
        }
        if (password != null) {
            throw new EncryptedDocumentException("Password incorrect");
        }
        throw new EncryptedDocumentException("The supplied spreadsheet is protected, but no password was supplied");
    }

    @Removal(version = "4.0")
    @Deprecated
    public static boolean hasOOXMLHeader(InputStream inp) throws IOException {
        return FileMagic.valueOf(inp) == FileMagic.OOXML;
    }
}
