package org.apache.commons.compress.archivers.tar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class TarArchiveSparseEntry implements TarConstants {
    private final boolean isExtended;
    private final List<TarArchiveStructSparse> sparseHeaders;

    public TarArchiveSparseEntry(byte[] headerBuf) throws IOException {
        this.sparseHeaders = new ArrayList(TarUtils.readSparseStructs(headerBuf, 0, 21));
        int offset = 0 + 504;
        this.isExtended = TarUtils.parseBoolean(headerBuf, offset);
    }

    public List<TarArchiveStructSparse> getSparseHeaders() {
        return this.sparseHeaders;
    }

    public boolean isExtended() {
        return this.isExtended;
    }
}
