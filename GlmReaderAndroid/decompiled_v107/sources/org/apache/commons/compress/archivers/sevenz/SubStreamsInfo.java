package org.apache.commons.compress.archivers.sevenz;

import java.util.BitSet;

/* loaded from: classes9.dex */
final class SubStreamsInfo {
    final long[] crcs;
    final BitSet hasCrc;
    final long[] unpackSizes;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubStreamsInfo(int totalUnpackStreams) {
        this.unpackSizes = new long[totalUnpackStreams];
        this.hasCrc = new BitSet(totalUnpackStreams);
        this.crcs = new long[totalUnpackStreams];
    }
}
