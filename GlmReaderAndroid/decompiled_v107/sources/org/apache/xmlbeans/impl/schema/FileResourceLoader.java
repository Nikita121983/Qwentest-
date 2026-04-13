package org.apache.xmlbeans.impl.schema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.xmlbeans.ResourceLoader;

/* loaded from: classes11.dex */
public class FileResourceLoader implements ResourceLoader {
    private File _directory;
    private ZipFile _zipfile;

    public FileResourceLoader(File file) throws IOException {
        if (file.isDirectory()) {
            this._directory = file;
        } else {
            this._zipfile = new ZipFile(file);
        }
    }

    @Override // org.apache.xmlbeans.ResourceLoader
    public InputStream getResourceAsStream(String resourceName) {
        try {
            if (this._zipfile != null) {
                ZipEntry entry = this._zipfile.getEntry(resourceName);
                if (entry == null) {
                    return null;
                }
                return this._zipfile.getInputStream(entry);
            }
            return Files.newInputStream(new File(this._directory, resourceName).toPath(), new OpenOption[0]);
        } catch (IOException e) {
            return null;
        }
    }

    @Override // org.apache.xmlbeans.ResourceLoader
    public void close() {
        if (this._zipfile != null) {
            try {
                this._zipfile.close();
            } catch (IOException e) {
            }
            this._zipfile = null;
        }
    }
}
