package org.apache.commons.compress.archivers.sevenz;

/* loaded from: classes9.dex */
final class StreamMap {
    final int[] fileFolderIndex;
    final int[] folderFirstFileIndex;
    final int[] folderFirstPackStreamIndex;
    final long[] packStreamOffsets;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StreamMap(int[] folderFirstPackStreamIndex, long[] packStreamOffsets, int[] folderFirstFileIndex, int[] fileFolderIndex) {
        this.folderFirstPackStreamIndex = folderFirstPackStreamIndex;
        this.packStreamOffsets = packStreamOffsets;
        this.folderFirstFileIndex = folderFirstFileIndex;
        this.fileFolderIndex = fileFolderIndex;
    }

    public String toString() {
        return "StreamMap with indices of " + this.folderFirstPackStreamIndex.length + " folders, offsets of " + this.packStreamOffsets.length + " packed streams, first files of " + this.folderFirstFileIndex.length + " folders and folder indices for " + this.fileFolderIndex.length + " files";
    }
}
