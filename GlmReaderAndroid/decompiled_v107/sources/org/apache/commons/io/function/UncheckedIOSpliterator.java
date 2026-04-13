package org.apache.commons.io.function;

import java.util.Comparator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/* loaded from: classes9.dex */
final class UncheckedIOSpliterator<T> implements Spliterator<T> {
    private final IOSpliterator<T> delegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UncheckedIOSpliterator(IOSpliterator<T> delegate) {
        this.delegate = (IOSpliterator) Objects.requireNonNull(delegate, "delegate");
    }

    @Override // java.util.Spliterator
    public int characteristics() {
        return this.delegate.characteristics();
    }

    @Override // java.util.Spliterator
    public long estimateSize() {
        return this.delegate.estimateSize();
    }

    @Override // java.util.Spliterator
    public void forEachRemaining(Consumer<? super T> action) {
        final IOSpliterator<T> iOSpliterator = this.delegate;
        Objects.requireNonNull(iOSpliterator);
        IOConsumer iOConsumer = new IOConsumer() { // from class: org.apache.commons.io.function.UncheckedIOSpliterator$$ExternalSyntheticLambda3
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                IOSpliterator.this.forEachRemaining((IOConsumer) obj);
            }
        };
        Objects.requireNonNull(action);
        Uncheck.accept((IOConsumer<UncheckedIOSpliterator$$ExternalSyntheticLambda1>) iOConsumer, new UncheckedIOSpliterator$$ExternalSyntheticLambda1(action));
    }

    @Override // java.util.Spliterator
    public Comparator<? super T> getComparator() {
        return this.delegate.getComparator().asComparator();
    }

    @Override // java.util.Spliterator
    public long getExactSizeIfKnown() {
        return this.delegate.getExactSizeIfKnown();
    }

    @Override // java.util.Spliterator
    public boolean hasCharacteristics(int characteristics) {
        return this.delegate.hasCharacteristics(characteristics);
    }

    @Override // java.util.Spliterator
    public boolean tryAdvance(Consumer<? super T> action) {
        final IOSpliterator<T> iOSpliterator = this.delegate;
        Objects.requireNonNull(iOSpliterator);
        IOFunction iOFunction = new IOFunction() { // from class: org.apache.commons.io.function.UncheckedIOSpliterator$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                return Boolean.valueOf(IOSpliterator.this.tryAdvance((IOConsumer) obj));
            }
        };
        Objects.requireNonNull(action);
        return ((Boolean) Uncheck.apply(iOFunction, new UncheckedIOSpliterator$$ExternalSyntheticLambda1(action))).booleanValue();
    }

    @Override // java.util.Spliterator
    public Spliterator<T> trySplit() {
        final IOSpliterator<T> iOSpliterator = this.delegate;
        Objects.requireNonNull(iOSpliterator);
        return ((IOSpliterator) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.function.UncheckedIOSpliterator$$ExternalSyntheticLambda2
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                return IOSpliterator.this.trySplit();
            }
        })).unwrap();
    }
}
