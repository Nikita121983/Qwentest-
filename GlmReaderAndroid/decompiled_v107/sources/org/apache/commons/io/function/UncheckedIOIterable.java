package org.apache.commons.io.function;

import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes9.dex */
final class UncheckedIOIterable<E> implements Iterable<E> {
    private final IOIterable<E> delegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UncheckedIOIterable(IOIterable<E> delegate) {
        this.delegate = (IOIterable) Objects.requireNonNull(delegate, "delegate");
    }

    @Override // java.lang.Iterable
    public Iterator<E> iterator() {
        return new UncheckedIOIterator(this.delegate.iterator());
    }
}
