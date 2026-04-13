package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;
import java.util.Spliterator;

/* loaded from: classes9.dex */
public interface IOIterable<T> {
    IOIterator<T> iterator();

    Iterable<T> unwrap();

    default Iterable<T> asIterable() {
        return new UncheckedIOIterable(this);
    }

    default void forEach(IOConsumer<? super T> action) throws IOException {
        iterator().forEachRemaining((IOConsumer) Objects.requireNonNull(action));
    }

    default IOSpliterator<T> spliterator() {
        return IOSpliteratorAdapter.adapt((Spliterator) new UncheckedIOIterable(this).spliterator());
    }
}
