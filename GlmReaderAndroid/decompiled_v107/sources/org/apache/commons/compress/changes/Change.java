package org.apache.commons.compress.changes;

import java.io.InputStream;
import java.util.Objects;
import org.apache.commons.compress.archivers.ArchiveEntry;

/* loaded from: classes9.dex */
final class Change<E extends ArchiveEntry> {
    private final E entry;
    private final InputStream inputStream;
    private final boolean replaceMode;
    private final String targetFileName;
    private final ChangeType type;

    /* loaded from: classes9.dex */
    enum ChangeType {
        DELETE,
        ADD,
        MOVE,
        DELETE_DIR
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Change(E archiveEntry, InputStream inputStream, boolean replace) {
        this.entry = (E) Objects.requireNonNull(archiveEntry, "archiveEntry");
        this.inputStream = (InputStream) Objects.requireNonNull(inputStream, "inputStream");
        this.type = ChangeType.ADD;
        this.targetFileName = null;
        this.replaceMode = replace;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Change(String fileName, ChangeType type) {
        this.targetFileName = (String) Objects.requireNonNull(fileName, "fileName");
        this.type = type;
        this.inputStream = null;
        this.entry = null;
        this.replaceMode = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public E getEntry() {
        return this.entry;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InputStream getInputStream() {
        return this.inputStream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getTargetFileName() {
        return this.targetFileName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChangeType getType() {
        return this.type;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isReplaceMode() {
        return this.replaceMode;
    }
}
