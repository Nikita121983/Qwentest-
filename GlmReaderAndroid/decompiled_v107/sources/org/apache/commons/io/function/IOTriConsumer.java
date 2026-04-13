package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface IOTriConsumer<T, U, V> {
    void accept(T t, U u, V v) throws IOException;

    static <T, U, V> IOTriConsumer<T, U, V> noop() {
        return Constants.IO_TRI_CONSUMER;
    }

    default IOTriConsumer<T, U, V> andThen(final IOTriConsumer<? super T, ? super U, ? super V> after) {
        Objects.requireNonNull(after);
        return new IOTriConsumer() { // from class: org.apache.commons.io.function.IOTriConsumer$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOTriConsumer
            public final void accept(Object obj, Object obj2, Object obj3) {
                IOTriConsumer.lambda$andThen$0(IOTriConsumer.this, after, obj, obj2, obj3);
            }
        };
    }

    static /* synthetic */ void lambda$andThen$0(IOTriConsumer _this, IOTriConsumer after, Object t, Object u, Object v) throws IOException {
        _this.accept(t, u, v);
        after.accept(t, u, v);
    }
}
