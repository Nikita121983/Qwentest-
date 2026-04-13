package org.apache.commons.compress.archivers.zip;

import java.io.InputStream;
import org.apache.commons.compress.parallel.InputStreamSupplier;

/* loaded from: classes9.dex */
public class ZipArchiveEntryRequest {
    private final int method;
    private final InputStreamSupplier payloadSupplier;
    private final ZipArchiveEntry zipArchiveEntry;

    public static ZipArchiveEntryRequest createZipArchiveEntryRequest(ZipArchiveEntry zipArchiveEntry, InputStreamSupplier payloadSupplier) {
        return new ZipArchiveEntryRequest(zipArchiveEntry, payloadSupplier);
    }

    private ZipArchiveEntryRequest(ZipArchiveEntry zipArchiveEntry, InputStreamSupplier payloadSupplier) {
        this.zipArchiveEntry = zipArchiveEntry;
        this.payloadSupplier = payloadSupplier;
        this.method = zipArchiveEntry.getMethod();
    }

    public int getMethod() {
        return this.method;
    }

    public InputStream getPayloadStream() {
        return this.payloadSupplier.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZipArchiveEntry getZipArchiveEntry() {
        return this.zipArchiveEntry;
    }
}
