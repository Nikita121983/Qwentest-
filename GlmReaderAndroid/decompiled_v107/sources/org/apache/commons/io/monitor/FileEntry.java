package org.apache.commons.io.monitor;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.FileTime;
import java.util.Objects;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.file.attribute.FileTimes;

/* loaded from: classes9.dex */
public class FileEntry implements Serializable {
    static final FileEntry[] EMPTY_FILE_ENTRY_ARRAY = new FileEntry[0];
    private static final long serialVersionUID = -2505664948818681153L;
    private FileEntry[] children;
    private boolean directory;
    private boolean exists;
    private final File file;
    private SerializableFileTime lastModified;
    private long length;
    private String name;
    private final FileEntry parent;

    public FileEntry(File file) {
        this(null, file);
    }

    public FileEntry(FileEntry parent, File file) {
        this.lastModified = SerializableFileTime.EPOCH;
        this.file = (File) Objects.requireNonNull(file, "file");
        this.parent = parent;
        this.name = file.getName();
    }

    public FileEntry[] getChildren() {
        return this.children != null ? this.children : EMPTY_FILE_ENTRY_ARRAY;
    }

    public File getFile() {
        return this.file;
    }

    public long getLastModified() {
        return this.lastModified.toMillis();
    }

    public FileTime getLastModifiedFileTime() {
        return this.lastModified.unwrap();
    }

    public long getLength() {
        return this.length;
    }

    public int getLevel() {
        if (this.parent == null) {
            return 0;
        }
        return this.parent.getLevel() + 1;
    }

    public String getName() {
        return this.name;
    }

    public FileEntry getParent() {
        return this.parent;
    }

    public boolean isDirectory() {
        return this.directory;
    }

    public boolean isExists() {
        return this.exists;
    }

    public FileEntry newChildInstance(File file) {
        return new FileEntry(this, file);
    }

    public boolean refresh(File file) {
        boolean origExists = this.exists;
        SerializableFileTime origLastModified = this.lastModified;
        boolean origDirectory = this.directory;
        long origLength = this.length;
        this.name = file.getName();
        this.exists = Files.exists(file.toPath(), new LinkOption[0]);
        this.directory = this.exists && file.isDirectory();
        try {
            setLastModified(this.exists ? FileUtils.lastModifiedFileTime(file) : FileTimes.EPOCH);
        } catch (IOException e) {
            setLastModified(SerializableFileTime.EPOCH);
        }
        this.length = (!this.exists || this.directory) ? 0L : file.length();
        return (this.exists == origExists && this.lastModified.equals(origLastModified) && this.directory == origDirectory && this.length == origLength) ? false : true;
    }

    public void setChildren(FileEntry... children) {
        this.children = children;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public void setLastModified(FileTime lastModified) {
        setLastModified(new SerializableFileTime(lastModified));
    }

    public void setLastModified(long lastModified) {
        setLastModified(FileTime.fromMillis(lastModified));
    }

    void setLastModified(SerializableFileTime lastModified) {
        this.lastModified = lastModified;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public void setName(String name) {
        this.name = name;
    }
}
