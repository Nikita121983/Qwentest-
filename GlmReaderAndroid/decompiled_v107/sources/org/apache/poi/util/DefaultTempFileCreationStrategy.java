package org.apache.poi.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes10.dex */
public class DefaultTempFileCreationStrategy implements TempFileCreationStrategy {
    public static final String DELETE_FILES_ON_EXIT = "poi.delete.tmp.files.on.exit";
    public static final String POIFILES = "poifiles";
    private volatile File dir;
    private final Lock dirLock;
    private final File initDir;

    public DefaultTempFileCreationStrategy() {
        this(null);
    }

    public DefaultTempFileCreationStrategy(File dir) {
        this.dirLock = new ReentrantLock();
        this.initDir = dir;
        this.dir = dir;
        if (dir != null && dir.exists() && !dir.isDirectory()) {
            throw new IllegalArgumentException("The provided file is not a directory: " + dir);
        }
    }

    @Override // org.apache.poi.util.TempFileCreationStrategy
    public File createTempFile(String prefix, String suffix) throws IOException {
        createPOIFilesDirectoryIfNecessary();
        File newFile = Files.createTempFile(this.dir.toPath(), prefix, suffix, new FileAttribute[0]).toFile();
        if (System.getProperty(DELETE_FILES_ON_EXIT) != null) {
            newFile.deleteOnExit();
        }
        return newFile;
    }

    @Override // org.apache.poi.util.TempFileCreationStrategy
    public File createTempDirectory(String prefix) throws IOException {
        createPOIFilesDirectoryIfNecessary();
        File newDirectory = Files.createTempDirectory(this.dir.toPath(), prefix, new FileAttribute[0]).toFile();
        newDirectory.deleteOnExit();
        return newDirectory;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getJavaIoTmpDir() throws IOException {
        String tmpDir = System.getProperty("java.io.tmpdir");
        if (tmpDir == null) {
            throw new IOException("System's temporary directory not defined - set the -Djava.io.tmpdir jvm property!");
        }
        return tmpDir;
    }

    protected Path getPOIFilesDirectoryPath() throws IOException {
        return this.initDir == null ? Paths.get(getJavaIoTmpDir(), POIFILES) : this.initDir.toPath();
    }

    private void createPOIFilesDirectoryIfNecessary() throws IOException {
        if (this.dir != null && !this.dir.exists()) {
            this.dir = null;
        }
        if (this.dir == null) {
            this.dirLock.lock();
            try {
                if (this.dir == null) {
                    Path dirPath = getPOIFilesDirectoryPath();
                    File fileDir = dirPath.toFile();
                    if (fileDir.exists()) {
                        if (!fileDir.isDirectory()) {
                            throw new IOException("Could not create temporary directory. '" + fileDir + "' exists but is not a directory.");
                        }
                        this.dir = fileDir;
                    } else {
                        this.dir = Files.createDirectories(dirPath, new FileAttribute[0]).toFile();
                    }
                }
            } finally {
                this.dirLock.unlock();
            }
        }
    }
}
