package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;
import java.util.function.BiConsumer;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface IOBiConsumer<T, U> {
    void accept(T t, U u) throws IOException;

    static <T, U> IOBiConsumer<T, U> noop() {
        return Constants.IO_BI_CONSUMER;
    }

    default IOBiConsumer<T, U> andThen(final IOBiConsumer<? super T, ? super U> after) {
        Objects.requireNonNull(after);
        return new IOBiConsumer() { // from class: org.apache.commons.io.function.IOBiConsumer$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOBiConsumer
            public final void accept(Object obj, Object obj2) {
                IOBiConsumer.lambda$andThen$0(IOBiConsumer.this, after, obj, obj2);
            }
        };
    }

    static /* synthetic */ void lambda$andThen$0(IOBiConsumer _this, IOBiConsumer after, Object t, Object u) throws IOException {
        _this.accept(t, u);
        after.accept(t, u);
    }

    default BiConsumer<T, U> asBiConsumer() {
        return new BiConsumer() { // from class: org.apache.commons.io.function.IOBiConsumer$$ExternalSyntheticLambda1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Uncheck.accept(IOBiConsumer.this, obj, obj2);
            }
        };
    }
}
