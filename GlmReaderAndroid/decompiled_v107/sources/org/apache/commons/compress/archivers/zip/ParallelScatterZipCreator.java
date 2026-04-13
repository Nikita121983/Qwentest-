package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Deque;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.apache.commons.compress.parallel.InputStreamSupplier;
import org.apache.commons.compress.parallel.ScatterGatherBackingStore;
import org.apache.commons.compress.parallel.ScatterGatherBackingStoreSupplier;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateUtils;

/* loaded from: classes9.dex */
public class ParallelScatterZipCreator {
    private final ScatterGatherBackingStoreSupplier backingStoreSupplier;
    private long compressionDoneAt;
    private final int compressionLevel;
    private final ExecutorService executorService;
    private final Deque<Future<? extends ScatterZipOutputStream>> futures;
    private long scatterDoneAt;
    private final long startedAt;
    private final Deque<ScatterZipOutputStream> streams;
    private final ThreadLocal<ScatterZipOutputStream> tlScatterStreams;

    public ParallelScatterZipCreator() {
        this(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
    }

    public ParallelScatterZipCreator(ExecutorService executorService) {
        this(executorService, new DefaultBackingStoreSupplier(null));
    }

    public ParallelScatterZipCreator(ExecutorService executorService, ScatterGatherBackingStoreSupplier backingStoreSupplier) {
        this(executorService, backingStoreSupplier, -1);
    }

    public ParallelScatterZipCreator(ExecutorService executorService, ScatterGatherBackingStoreSupplier backingStoreSupplier, int compressionLevel) throws IllegalArgumentException {
        this.streams = new ConcurrentLinkedDeque();
        this.futures = new ConcurrentLinkedDeque();
        this.startedAt = System.currentTimeMillis();
        this.tlScatterStreams = new ThreadLocal<ScatterZipOutputStream>() { // from class: org.apache.commons.compress.archivers.zip.ParallelScatterZipCreator.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public ScatterZipOutputStream initialValue() {
                try {
                    ScatterZipOutputStream scatterStream = ParallelScatterZipCreator.this.createDeferred(ParallelScatterZipCreator.this.backingStoreSupplier);
                    ParallelScatterZipCreator.this.streams.add(scatterStream);
                    return scatterStream;
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
        };
        if ((compressionLevel < 0 || compressionLevel > 9) && compressionLevel != -1) {
            throw new IllegalArgumentException("Compression level is expected between -1~9");
        }
        this.backingStoreSupplier = backingStoreSupplier;
        this.executorService = executorService;
        this.compressionLevel = compressionLevel;
    }

    public void addArchiveEntry(ZipArchiveEntry zipArchiveEntry, InputStreamSupplier source) {
        submitStreamAwareCallable(createCallable(zipArchiveEntry, source));
    }

    public void addArchiveEntry(ZipArchiveEntryRequestSupplier zipArchiveEntryRequestSupplier) {
        submitStreamAwareCallable(createCallable(zipArchiveEntryRequestSupplier));
    }

    private void closeAll() {
        for (ScatterZipOutputStream scatterStream : this.streams) {
            IOUtils.closeQuietly(scatterStream);
        }
    }

    public final Callable<ScatterZipOutputStream> createCallable(ZipArchiveEntry zipArchiveEntry, InputStreamSupplier source) {
        int method = zipArchiveEntry.getMethod();
        if (method == -1) {
            throw new IllegalArgumentException("Method must be set on zipArchiveEntry: " + zipArchiveEntry);
        }
        final ZipArchiveEntryRequest zipArchiveEntryRequest = ZipArchiveEntryRequest.createZipArchiveEntryRequest(zipArchiveEntry, source);
        return new Callable() { // from class: org.apache.commons.compress.archivers.zip.ParallelScatterZipCreator$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ParallelScatterZipCreator.this.m2058x5cb3ae55(zipArchiveEntryRequest);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$createCallable$0$org-apache-commons-compress-archivers-zip-ParallelScatterZipCreator, reason: not valid java name */
    public /* synthetic */ ScatterZipOutputStream m2058x5cb3ae55(ZipArchiveEntryRequest zipArchiveEntryRequest) throws Exception {
        ScatterZipOutputStream scatterStream = this.tlScatterStreams.get();
        scatterStream.addArchiveEntry(zipArchiveEntryRequest);
        return scatterStream;
    }

    public final Callable<ScatterZipOutputStream> createCallable(final ZipArchiveEntryRequestSupplier zipArchiveEntryRequestSupplier) {
        return new Callable() { // from class: org.apache.commons.compress.archivers.zip.ParallelScatterZipCreator$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ParallelScatterZipCreator.this.m2059x4e053dd6(zipArchiveEntryRequestSupplier);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$createCallable$1$org-apache-commons-compress-archivers-zip-ParallelScatterZipCreator, reason: not valid java name */
    public /* synthetic */ ScatterZipOutputStream m2059x4e053dd6(ZipArchiveEntryRequestSupplier zipArchiveEntryRequestSupplier) throws Exception {
        ScatterZipOutputStream scatterStream = this.tlScatterStreams.get();
        scatterStream.addArchiveEntry(zipArchiveEntryRequestSupplier.get());
        return scatterStream;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ScatterZipOutputStream createDeferred(ScatterGatherBackingStoreSupplier scatterGatherBackingStoreSupplier) throws IOException {
        ScatterGatherBackingStore bs = scatterGatherBackingStoreSupplier.get();
        StreamCompressor sc = StreamCompressor.create(this.compressionLevel, bs);
        return new ScatterZipOutputStream(bs, sc);
    }

    public ScatterStatistics getStatisticsMessage() {
        return new ScatterStatistics(this.compressionDoneAt - this.startedAt, this.scatterDoneAt - this.compressionDoneAt);
    }

    public final void submit(final Callable<? extends Object> callable) {
        submitStreamAwareCallable(new Callable() { // from class: org.apache.commons.compress.archivers.zip.ParallelScatterZipCreator$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ParallelScatterZipCreator.this.m2060x592c3d9b(callable);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$submit$2$org-apache-commons-compress-archivers-zip-ParallelScatterZipCreator, reason: not valid java name */
    public /* synthetic */ ScatterZipOutputStream m2060x592c3d9b(Callable callable) throws Exception {
        callable.call();
        return this.tlScatterStreams.get();
    }

    public final void submitStreamAwareCallable(Callable<? extends ScatterZipOutputStream> callable) {
        this.futures.add(this.executorService.submit(callable));
    }

    public void writeTo(ZipArchiveOutputStream targetStream) throws IOException, InterruptedException, ExecutionException {
        try {
            try {
                for (Future<?> future : this.futures) {
                    future.get();
                }
                this.executorService.shutdown();
                this.executorService.awaitTermination(DateUtils.MILLIS_PER_MINUTE, TimeUnit.SECONDS);
                this.compressionDoneAt = System.currentTimeMillis();
                for (Future<? extends ScatterZipOutputStream> future2 : this.futures) {
                    ScatterZipOutputStream scatterStream = future2.get();
                    scatterStream.zipEntryWriter().writeNextZipEntry(targetStream);
                }
                for (ScatterZipOutputStream scatterStream2 : this.streams) {
                    scatterStream2.close();
                }
                this.scatterDoneAt = System.currentTimeMillis();
            } catch (Throwable th) {
                this.executorService.shutdown();
                throw th;
            }
        } finally {
            closeAll();
        }
    }
}
