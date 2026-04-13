package org.apache.commons.compress.archivers.sevenz;

/* loaded from: classes9.dex */
final class StartHeader {
    final long nextHeaderCrc;
    final long nextHeaderOffset;
    final long nextHeaderSize;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StartHeader(long nextHeaderOffset, long nextHeaderSize, long nextHeaderCrc) {
        this.nextHeaderOffset = nextHeaderOffset;
        this.nextHeaderSize = nextHeaderSize;
        this.nextHeaderCrc = nextHeaderCrc;
    }
}
