package org.apache.commons.io.function;

import java.io.IOException;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.commons.io.IOExceptionList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class IOStreams {
    static final Object NONE = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> void forAll(Stream<T> stream, IOConsumer<T> action) throws IOExceptionList {
        forAll(stream, action, new BiFunction() { // from class: org.apache.commons.io.function.IOStreams$$ExternalSyntheticLambda1
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return IOStreams.lambda$forAll$0((Integer) obj, (IOException) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ IOException lambda$forAll$0(Integer i, IOException e) {
        return e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> void forAll(Stream<T> stream, IOConsumer<T> action, BiFunction<Integer, IOException, IOException> exSupplier) throws IOExceptionList {
        IOStream.adapt(stream).forAll(action, new IOConsumer$$ExternalSyntheticLambda1());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> void forEach(Stream<T> stream, IOConsumer<T> action) throws IOException {
        final IOConsumer<T> actualAction = toIOConsumer(action);
        of(stream).forEach(new Consumer() { // from class: org.apache.commons.io.function.IOStreams$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Erase.accept(IOConsumer.this, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> Stream<T> of(Iterable<T> values) {
        return values == null ? Stream.empty() : StreamSupport.stream(values.spliterator(), false);
    }

    static <T> Stream<T> of(Stream<T> stream) {
        return stream == null ? Stream.empty() : stream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SafeVarargs
    public static <T> Stream<T> of(T... values) {
        return values == null ? Stream.empty() : Stream.of((Object[]) values);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> IOConsumer<T> toIOConsumer(IOConsumer<T> action) {
        return action != null ? action : IOConsumer.noop();
    }

    private IOStreams() {
    }
}
