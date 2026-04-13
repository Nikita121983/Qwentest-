package org.apache.commons.lang3.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.function.Failable;
import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableFunction;
import org.apache.commons.lang3.function.FailablePredicate;
import org.apache.commons.lang3.stream.Streams;

/* loaded from: classes9.dex */
public class Streams {

    /* loaded from: classes9.dex */
    public static class ArrayCollector<E> implements Collector<E, List<E>, E[]> {
        private static final Set<Collector.Characteristics> characteristics = Collections.emptySet();
        private final Class<E> elementType;

        public static /* synthetic */ ArrayList $r8$lambda$aw4WkQINtNlXlsGxYEbzatsgkDc() {
            return new ArrayList();
        }

        public ArrayCollector(Class<E> elementType) {
            this.elementType = (Class) Objects.requireNonNull(elementType, "elementType");
        }

        @Override // java.util.stream.Collector
        public BiConsumer<List<E>, E> accumulator() {
            return new org.apache.commons.lang3.Streams$ArrayCollector$$ExternalSyntheticLambda2();
        }

        @Override // java.util.stream.Collector
        public Set<Collector.Characteristics> characteristics() {
            return characteristics;
        }

        @Override // java.util.stream.Collector
        public BinaryOperator<List<E>> combiner() {
            return new BinaryOperator() { // from class: org.apache.commons.lang3.stream.Streams$ArrayCollector$$ExternalSyntheticLambda2
                @Override // java.util.function.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return Streams.ArrayCollector.lambda$combiner$0((List) obj, (List) obj2);
                }
            };
        }

        public static /* synthetic */ List lambda$combiner$0(List left, List right) {
            left.addAll(right);
            return left;
        }

        @Override // java.util.stream.Collector
        public Function<List<E>, E[]> finisher() {
            return new Function() { // from class: org.apache.commons.lang3.stream.Streams$ArrayCollector$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Streams.ArrayCollector.this.m2197x3e66eb69((List) obj);
                }
            };
        }

        /* renamed from: lambda$finisher$1$org-apache-commons-lang3-stream-Streams$ArrayCollector */
        public /* synthetic */ Object[] m2197x3e66eb69(List list) {
            return list.toArray(ArrayUtils.newInstance(this.elementType, list.size()));
        }

        @Override // java.util.stream.Collector
        public Supplier<List<E>> supplier() {
            return new Supplier() { // from class: org.apache.commons.lang3.stream.Streams$ArrayCollector$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Streams.ArrayCollector.$r8$lambda$aw4WkQINtNlXlsGxYEbzatsgkDc();
                }
            };
        }
    }

    /* loaded from: classes9.dex */
    private static final class EnumerationSpliterator<T> extends Spliterators.AbstractSpliterator<T> {
        private final Enumeration<T> enumeration;

        protected EnumerationSpliterator(long estimatedSize, int additionalCharacteristics, Enumeration<T> enumeration) {
            super(estimatedSize, additionalCharacteristics);
            this.enumeration = (Enumeration) Objects.requireNonNull(enumeration, "enumeration");
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super T> action) {
            while (this.enumeration.hasMoreElements()) {
                next(action);
            }
        }

        private boolean next(Consumer<? super T> consumer) {
            consumer.accept(this.enumeration.nextElement());
            return true;
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super T> action) {
            return this.enumeration.hasMoreElements() && next(action);
        }
    }

    /* loaded from: classes9.dex */
    public static class FailableStream<T> {
        private Stream<T> stream;
        private boolean terminated;

        public FailableStream(Stream<T> stream) {
            this.stream = stream;
        }

        public boolean allMatch(FailablePredicate<T, ?> predicate) {
            assertNotTerminated();
            return stream().allMatch(Failable.asPredicate(predicate));
        }

        public boolean anyMatch(FailablePredicate<T, ?> predicate) {
            assertNotTerminated();
            return stream().anyMatch(Failable.asPredicate(predicate));
        }

        protected void assertNotTerminated() {
            if (this.terminated) {
                throw new IllegalStateException("This stream is already terminated.");
            }
        }

        public <A, R> R collect(Collector<? super T, A, R> collector) {
            makeTerminated();
            return (R) stream().collect(collector);
        }

        public <A, R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> biConsumer, BiConsumer<R, R> biConsumer2) {
            makeTerminated();
            return (R) stream().collect(supplier, biConsumer, biConsumer2);
        }

        public FailableStream<T> filter(FailablePredicate<T, ?> predicate) {
            assertNotTerminated();
            this.stream = this.stream.filter(Failable.asPredicate(predicate));
            return this;
        }

        public void forEach(FailableConsumer<T, ?> action) {
            makeTerminated();
            stream().forEach(Failable.asConsumer(action));
        }

        protected void makeTerminated() {
            assertNotTerminated();
            this.terminated = true;
        }

        public <R> FailableStream<R> map(FailableFunction<T, R, ?> mapper) {
            assertNotTerminated();
            return new FailableStream<>(this.stream.map(Failable.asFunction(mapper)));
        }

        public T reduce(T identity, BinaryOperator<T> accumulator) {
            makeTerminated();
            return stream().reduce(identity, accumulator);
        }

        public Stream<T> stream() {
            return this.stream;
        }
    }

    public static <T> FailableStream<T> failableStream(Collection<T> stream) {
        return failableStream(of((Collection) stream));
    }

    public static <T> FailableStream<T> failableStream(Stream<T> stream) {
        return new FailableStream<>(stream);
    }

    public static <T> FailableStream<T> failableStream(T value) {
        return failableStream(streamOf(value));
    }

    @SafeVarargs
    public static <T> FailableStream<T> failableStream(T... values) {
        return failableStream(of(values));
    }

    public static <E> Stream<E> instancesOf(Class<? super E> clazz, Collection<? super E> collection) {
        return instancesOf(clazz, (Stream<?>) of((Collection) collection));
    }

    private static <E> Stream<E> instancesOf(final Class<? super E> clazz, Stream<?> stream) {
        Stream of = of(stream);
        Objects.requireNonNull(clazz);
        return of.filter(new Predicate() { // from class: org.apache.commons.lang3.stream.Streams$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean isInstance;
                isInstance = clazz.isInstance(obj);
                return isInstance;
            }
        });
    }

    public static <E> Stream<E> nonNull(Collection<E> collection) {
        return of((Collection) collection).filter(new Streams$$ExternalSyntheticLambda0());
    }

    public static <E> Stream<E> nonNull(E array) {
        return nonNull(streamOf(array));
    }

    @SafeVarargs
    public static <E> Stream<E> nonNull(E... array) {
        return nonNull(of(array));
    }

    public static <E> Stream<E> nonNull(Stream<E> stream) {
        return of(stream).filter(new Streams$$ExternalSyntheticLambda0());
    }

    public static <E> Stream<E> of(Collection<E> collection) {
        return collection == null ? Stream.empty() : collection.stream();
    }

    public static <E> Stream<E> of(Enumeration<E> enumeration) {
        return StreamSupport.stream(new EnumerationSpliterator(Long.MAX_VALUE, 16, enumeration), false);
    }

    public static <E> Stream<E> of(Iterable<E> iterable) {
        return iterable == null ? Stream.empty() : StreamSupport.stream(iterable.spliterator(), false);
    }

    public static <E> Stream<E> of(Iterator<E> iterator) {
        return iterator == null ? Stream.empty() : StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 16), false);
    }

    private static <E> Stream<E> of(Stream<E> stream) {
        return stream == null ? Stream.empty() : stream;
    }

    @SafeVarargs
    public static <T> Stream<T> of(T... values) {
        return values == null ? Stream.empty() : Stream.of((Object[]) values);
    }

    @Deprecated
    public static <E> FailableStream<E> stream(Collection<E> collection) {
        return failableStream((Collection) collection);
    }

    @Deprecated
    public static <T> FailableStream<T> stream(Stream<T> stream) {
        return failableStream((Stream) stream);
    }

    private static <T> Stream<T> streamOf(T value) {
        return value == null ? Stream.empty() : Stream.of(value);
    }

    public static <T> Collector<T, List<T>, T[]> toArray(Class<T> elementType) {
        return new ArrayCollector(elementType);
    }

    @Deprecated
    public Streams() {
    }
}
