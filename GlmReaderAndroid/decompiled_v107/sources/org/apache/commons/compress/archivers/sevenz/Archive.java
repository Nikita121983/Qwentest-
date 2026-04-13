package org.apache.commons.compress.archivers.sevenz;

import java.util.BitSet;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class Archive {
    long[] packCrcs;
    BitSet packCrcsDefined;
    long packPos;
    StreamMap streamMap;
    SubStreamsInfo subStreamsInfo;
    long[] packSizes = new long[0];
    Folder[] folders = Folder.EMPTY_FOLDER_ARRAY;
    SevenZArchiveEntry[] files = SevenZArchiveEntry.EMPTY_SEVEN_Z_ARCHIVE_ENTRY_ARRAY;

    private static String lengthOf(long[] a) {
        return a == null ? "(null)" : Integer.toString(a.length);
    }

    private static String lengthOf(Object[] a) {
        return a == null ? "(null)" : Integer.toString(a.length);
    }

    public String toString() {
        return "Archive with packed streams starting at offset " + this.packPos + ", " + lengthOf(this.packSizes) + " pack sizes, " + lengthOf(this.packCrcs) + " CRCs, " + lengthOf(this.folders) + " folders, " + lengthOf(this.files) + " files and " + this.streamMap;
    }
}
