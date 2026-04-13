package org.apache.commons.compress.changes;

import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.changes.Change;

/* loaded from: classes9.dex */
public final class ChangeSet<E extends ArchiveEntry> {
    private final Set<Change<E>> changes = new LinkedHashSet();

    public void add(E entry, InputStream input) {
        add(entry, input, true);
    }

    public void add(E entry, InputStream input, boolean replace) {
        addAddition(new Change<>(entry, input, replace));
    }

    private void addAddition(Change<E> addChange) {
        if (Change.ChangeType.ADD != addChange.getType() || addChange.getInputStream() == null) {
            return;
        }
        if (!this.changes.isEmpty()) {
            Iterator<Change<E>> it = this.changes.iterator();
            while (it.hasNext()) {
                Change<E> change = it.next();
                if (change.getType() == Change.ChangeType.ADD && change.getEntry() != null) {
                    ArchiveEntry entry = change.getEntry();
                    if (entry.equals(addChange.getEntry())) {
                        if (addChange.isReplaceMode()) {
                            it.remove();
                            this.changes.add(addChange);
                            return;
                        }
                        return;
                    }
                }
            }
        }
        this.changes.add(addChange);
    }

    private void addDeletion(Change<E> deleteChange) {
        String target;
        if ((Change.ChangeType.DELETE != deleteChange.getType() && Change.ChangeType.DELETE_DIR != deleteChange.getType()) || deleteChange.getTargetFileName() == null) {
            return;
        }
        String source = deleteChange.getTargetFileName();
        Pattern pattern = Pattern.compile(source + "/.*");
        if (source != null && !this.changes.isEmpty()) {
            Iterator<Change<E>> it = this.changes.iterator();
            while (it.hasNext()) {
                Change<E> change = it.next();
                if (change.getType() == Change.ChangeType.ADD && change.getEntry() != null && (target = change.getEntry().getName()) != null && ((Change.ChangeType.DELETE == deleteChange.getType() && source.equals(target)) || (Change.ChangeType.DELETE_DIR == deleteChange.getType() && pattern.matcher(target).matches()))) {
                    it.remove();
                }
            }
        }
        this.changes.add(deleteChange);
    }

    public void delete(String fileName) {
        addDeletion(new Change<>(fileName, Change.ChangeType.DELETE));
    }

    public void deleteDir(String dirName) {
        addDeletion(new Change<>(dirName, Change.ChangeType.DELETE_DIR));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<Change<E>> getChanges() {
        return new LinkedHashSet(this.changes);
    }
}
