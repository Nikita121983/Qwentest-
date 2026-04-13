package org.apache.commons.compress.archivers.sevenz;

/* loaded from: classes9.dex */
final class BindPair {
    final long inIndex;
    final long outIndex;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BindPair(long inIndex, long outIndex) {
        this.inIndex = inIndex;
        this.outIndex = outIndex;
    }

    public String toString() {
        return "BindPair binding input " + this.inIndex + " to output " + this.outIndex;
    }
}
