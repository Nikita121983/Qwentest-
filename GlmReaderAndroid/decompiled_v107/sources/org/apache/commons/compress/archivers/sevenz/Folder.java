package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import org.apache.commons.lang3.ArrayUtils;

/* loaded from: classes9.dex */
final class Folder {
    static final Folder[] EMPTY_FOLDER_ARRAY = new Folder[0];
    BindPair[] bindPairs;
    Coder[] coders;
    long crc;
    boolean hasCrc;
    int numUnpackSubStreams;
    long[] packedStreams;
    long totalInputStreams;
    long totalOutputStreams;
    long[] unpackSizes;

    /* JADX INFO: Access modifiers changed from: package-private */
    public int findBindPairForInStream(int index) {
        if (this.bindPairs != null) {
            for (int i = 0; i < this.bindPairs.length; i++) {
                if (this.bindPairs[i].inIndex == index) {
                    return i;
                }
            }
            return -1;
        }
        return -1;
    }

    int findBindPairForOutStream(int index) {
        if (this.bindPairs != null) {
            for (int i = 0; i < this.bindPairs.length; i++) {
                if (this.bindPairs[i].outIndex == index) {
                    return i;
                }
            }
            return -1;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Iterable<Coder> getOrderedCoders() throws IOException {
        if (ArrayUtils.isEmpty(this.packedStreams) || ArrayUtils.isEmpty(this.coders)) {
            return Collections.emptyList();
        }
        LinkedList<Coder> list = new LinkedList<>();
        int current = (int) this.packedStreams[0];
        while (current >= 0 && current < this.coders.length) {
            if (list.contains(this.coders[current])) {
                throw new IOException("folder uses the same coder more than once in coder chain");
            }
            list.addLast(this.coders[current]);
            int pair = findBindPairForOutStream(current);
            int i = -1;
            if (pair != -1) {
                i = (int) this.bindPairs[pair].inIndex;
            }
            current = i;
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getUnpackSize() {
        if (this.totalOutputStreams == 0) {
            return 0L;
        }
        for (int i = ((int) this.totalOutputStreams) - 1; i >= 0; i--) {
            if (findBindPairForOutStream(i) < 0) {
                return this.unpackSizes[i];
            }
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getUnpackSizeForCoder(Coder coder) {
        if (this.coders != null) {
            for (int i = 0; i < this.coders.length; i++) {
                if (this.coders[i] == coder) {
                    return this.unpackSizes[i];
                }
            }
            return 0L;
        }
        return 0L;
    }

    public String toString() {
        return "Folder with " + this.coders.length + " coders, " + this.totalInputStreams + " input streams, " + this.totalOutputStreams + " output streams, " + this.bindPairs.length + " bind pairs, " + this.packedStreams.length + " packed streams, " + this.unpackSizes.length + " unpack sizes, " + (this.hasCrc ? "with CRC " + this.crc : "without CRC") + " and " + this.numUnpackSubStreams + " unpack streams";
    }
}
