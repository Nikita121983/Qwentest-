package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.compress.parallel.FileBasedScatterGatherBackingStore;
import org.apache.commons.compress.parallel.ScatterGatherBackingStore;
import org.apache.commons.compress.parallel.ScatterGatherBackingStoreSupplier;

/* loaded from: classes9.dex */
public class DefaultBackingStoreSupplier implements ScatterGatherBackingStoreSupplier {
    private static final String PREFIX = "parallelscatter";
    private final Path dir;
    private final AtomicInteger storeNum = new AtomicInteger();

    public DefaultBackingStoreSupplier(Path dir) {
        this.dir = dir;
    }

    @Override // org.apache.commons.compress.parallel.ScatterGatherBackingStoreSupplier
    public ScatterGatherBackingStore get() throws IOException {
        String suffix = "n" + this.storeNum.incrementAndGet();
        Path tempFile = this.dir == null ? Files.createTempFile(PREFIX, suffix, new FileAttribute[0]) : Files.createTempFile(this.dir, PREFIX, suffix, new FileAttribute[0]);
        return new FileBasedScatterGatherBackingStore(tempFile);
    }
}
