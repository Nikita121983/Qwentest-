package org.apache.commons.compress.archivers.examples;

import java.io.Closeable;
import java.io.IOException;
import java.util.Objects;

/* loaded from: classes9.dex */
final class CloseableConsumerAdapter implements Closeable {
    private Closeable closeable;
    private final CloseableConsumer consumer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CloseableConsumerAdapter(CloseableConsumer consumer) {
        this.consumer = (CloseableConsumer) Objects.requireNonNull(consumer, "consumer");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closeable != null) {
            this.consumer.accept(this.closeable);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <C extends Closeable> C track(C closeable) {
        this.closeable = closeable;
        return closeable;
    }
}
