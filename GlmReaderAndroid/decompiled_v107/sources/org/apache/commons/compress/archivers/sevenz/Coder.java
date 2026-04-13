package org.apache.commons.compress.archivers.sevenz;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class Coder {
    final byte[] decompressionMethodId;
    final long numInStreams;
    final long numOutStreams;
    final byte[] properties;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Coder(byte[] decompressionMethodId, long numInStreams, long numOutStreams, byte[] properties) {
        this.decompressionMethodId = decompressionMethodId;
        this.numInStreams = numInStreams;
        this.numOutStreams = numOutStreams;
        this.properties = properties;
    }
}
