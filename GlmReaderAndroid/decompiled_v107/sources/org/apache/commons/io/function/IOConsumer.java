package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.apache.commons.io.IOExceptionList;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface IOConsumer<T> {
    public static final IOConsumer<?> NOOP_IO_CONSUMER = new IOConsumer() { // from class: org.apache.commons.io.function.IOConsumer$$ExternalSyntheticLambda3
        @Override // org.apache.commons.io.function.IOConsumer
        public final void accept(Object obj) {
            IOConsumer.lambda$static$0(obj);
        }
    };

    void accept(T t) throws IOException;

    static /* synthetic */ void lambda$static$0(Object t) throws IOException {
    }

    static <T> void forAll(IOConsumer<T> action, Iterable<T> iterable) throws IOExceptionList {
        IOStreams.forAll(IOStreams.of(iterable), action);
    }

    static <T> void forAll(IOConsumer<T> action, Stream<T> stream) throws IOExceptionList {
        IOStreams.forAll(stream, action, new IOConsumer$$ExternalSyntheticLambda1());
    }

    @SafeVarargs
    static <T> void forAll(IOConsumer<T> action, T... array) throws IOExceptionList {
        IOStreams.forAll(IOStreams.of(array), action);
    }

    static <T> void forEach(Iterable<T> iterable, IOConsumer<T> action) throws IOException {
        IOStreams.forEach(IOStreams.of(iterable), action);
    }

    static <T> void forEach(Stream<T> stream, IOConsumer<T> action) throws IOException {
        IOStreams.forEach(stream, action);
    }

    static <T> void forEach(T[] array, IOConsumer<T> action) throws IOException {
        IOStreams.forEach(IOStreams.of(array), action);
    }

    static <T> IOConsumer<T> noop() {
        return (IOConsumer<T>) NOOP_IO_CONSUMER;
    }

    default IOConsumer<T> andThen(final IOConsumer<? super T> after) {
        Objects.requireNonNull(after, "after");
        return new IOConsumer() { // from class: org.apache.commons.io.function.IOConsumer$$ExternalSyntheticLambda2
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                IOConsumer.lambda$andThen$1(IOConsumer.this, after, obj);
            }
        };
    }

    static /* synthetic */ void lambda$andThen$1(IOConsumer _this, IOConsumer after, Object t) throws IOException {
        _this.accept(t);
        after.accept(t);
    }

    default Consumer<T> asConsumer() {
        return new Consumer() { // from class: org.apache.commons.io.function.IOConsumer$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Uncheck.accept((IOConsumer<Object>) IOConsumer.this, obj);
            }
        };
    }
}
