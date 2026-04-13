package org.apache.commons.lang3.stream;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/* loaded from: classes9.dex */
public final class LangCollectors {
    private static final Set<Collector.Characteristics> CH_NOID = Collections.emptySet();

    /* renamed from: $r8$lambda$V_uu1X1UKpbECUj-5gbjPr-ONSE, reason: not valid java name */
    public static /* synthetic */ StringBuilder m2195$r8$lambda$V_uu1X1UKpbECUj5gbjPrONSE() {
        return new StringBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class SimpleCollector<T, A, R> implements Collector<T, A, R> {
        private final BiConsumer<A, T> accumulator;
        private final Set<Collector.Characteristics> characteristics;
        private final BinaryOperator<A> combiner;
        private final Function<A, R> finisher;
        private final Supplier<A> supplier;

        private SimpleCollector(Supplier<A> supplier, BiConsumer<A, T> accumulator, BinaryOperator<A> combiner, Function<A, R> finisher, Set<Collector.Characteristics> characteristics) {
            this.supplier = supplier;
            this.accumulator = accumulator;
            this.combiner = combiner;
            this.finisher = finisher;
            this.characteristics = characteristics;
        }

        @Override // java.util.stream.Collector
        public BiConsumer<A, T> accumulator() {
            return this.accumulator;
        }

        @Override // java.util.stream.Collector
        public Set<Collector.Characteristics> characteristics() {
            return this.characteristics;
        }

        @Override // java.util.stream.Collector
        public BinaryOperator<A> combiner() {
            return this.combiner;
        }

        @Override // java.util.stream.Collector
        public Function<A, R> finisher() {
            return this.finisher;
        }

        @Override // java.util.stream.Collector
        public Supplier<A> supplier() {
            return this.supplier;
        }
    }

    @SafeVarargs
    public static <T, R, A> R collect(Collector<? super T, A, R> collector, T... tArr) {
        return (R) Streams.of(tArr).collect(collector);
    }

    public static Collector<Object, ?, String> joining() {
        return new SimpleCollector(new Supplier() { // from class: org.apache.commons.lang3.stream.LangCollectors$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return LangCollectors.m2195$r8$lambda$V_uu1X1UKpbECUj5gbjPrONSE();
            }
        }, new BiConsumer() { // from class: org.apache.commons.lang3.stream.LangCollectors$$ExternalSyntheticLambda1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((StringBuilder) obj).append(obj2);
            }
        }, new BinaryOperator() { // from class: org.apache.commons.lang3.stream.LangCollectors$$ExternalSyntheticLambda2
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                StringBuilder append;
                append = ((StringBuilder) obj).append((CharSequence) obj2);
                return append;
            }
        }, new Function() { // from class: org.apache.commons.lang3.stream.LangCollectors$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String sb;
                sb = ((StringBuilder) obj).toString();
                return sb;
            }
        }, CH_NOID);
    }

    public static Collector<Object, ?, String> joining(CharSequence delimiter) {
        return joining(delimiter, "", "");
    }

    public static Collector<Object, ?, String> joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix) {
        return joining(delimiter, prefix, suffix, new Function() { // from class: org.apache.commons.lang3.stream.LangCollectors$$ExternalSyntheticLambda8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String objects;
                objects = Objects.toString(obj);
                return objects;
            }
        });
    }

    public static Collector<Object, ?, String> joining(final CharSequence delimiter, final CharSequence prefix, final CharSequence suffix, final Function<Object, String> toString) {
        return new SimpleCollector(new Supplier() { // from class: org.apache.commons.lang3.stream.LangCollectors$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return LangCollectors.lambda$joining$0(delimiter, prefix, suffix);
            }
        }, new BiConsumer() { // from class: org.apache.commons.lang3.stream.LangCollectors$$ExternalSyntheticLambda5
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((StringJoiner) obj).add((CharSequence) toString.apply(obj2));
            }
        }, new BinaryOperator() { // from class: org.apache.commons.lang3.stream.LangCollectors$$ExternalSyntheticLambda6
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                StringJoiner merge;
                merge = ((StringJoiner) obj).merge((StringJoiner) obj2);
                return merge;
            }
        }, new Function() { // from class: org.apache.commons.lang3.stream.LangCollectors$$ExternalSyntheticLambda7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String stringJoiner;
                stringJoiner = ((StringJoiner) obj).toString();
                return stringJoiner;
            }
        }, CH_NOID);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ StringJoiner lambda$joining$0(CharSequence delimiter, CharSequence prefix, CharSequence suffix) {
        return new StringJoiner(delimiter, prefix, suffix);
    }

    private LangCollectors() {
    }
}
