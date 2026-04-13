package org.apache.commons.compress.archivers.dump;

/* loaded from: classes9.dex */
final class Dirent {
    private final int ino;
    private final String name;
    private final int parentIno;
    private final int type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Dirent(int ino, int parentIno, int type, String name) {
        this.ino = ino;
        this.parentIno = parentIno;
        this.type = type;
        this.name = name;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getIno() {
        return this.ino;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getName() {
        return this.name;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getParentIno() {
        return this.parentIno;
    }

    int getType() {
        return this.type;
    }

    public String toString() {
        return String.format("[%d]: %s", Integer.valueOf(this.ino), this.name);
    }
}
