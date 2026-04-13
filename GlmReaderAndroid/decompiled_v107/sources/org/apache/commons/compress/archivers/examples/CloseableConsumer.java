package org.apache.commons.compress.archivers.examples;

import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes9.dex */
public interface CloseableConsumer {
    public static final CloseableConsumer CLOSING_CONSUMER = new CloseableConsumer() { // from class: org.apache.commons.compress.archivers.examples.CloseableConsumer$$ExternalSyntheticLambda0
        @Override // org.apache.commons.compress.archivers.examples.CloseableConsumer
        public final void accept(Closeable closeable) {
            closeable.close();
        }
    };
    public static final CloseableConsumer NULL_CONSUMER = new CloseableConsumer() { // from class: org.apache.commons.compress.archivers.examples.CloseableConsumer$$ExternalSyntheticLambda1
        @Override // org.apache.commons.compress.archivers.examples.CloseableConsumer
        public final void accept(Closeable closeable) {
            CloseableConsumer.lambda$static$0(closeable);
        }
    };

    void accept(Closeable closeable) throws IOException;

    static /* synthetic */ void lambda$static$0(Closeable c) throws IOException {
    }
}
