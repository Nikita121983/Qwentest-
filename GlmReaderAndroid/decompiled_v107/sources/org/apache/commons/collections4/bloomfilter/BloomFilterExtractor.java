package org.apache.commons.collections4.bloomfilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface BloomFilterExtractor {
    boolean processBloomFilters(Predicate<BloomFilter> predicate);

    static <T extends BloomFilter<T>> BloomFilterExtractor fromBloomFilterArray(final BloomFilter<?>... filters) {
        Objects.requireNonNull(filters, "filters");
        return new BloomFilterExtractor() { // from class: org.apache.commons.collections4.bloomfilter.BloomFilterExtractor.1
            @Override // org.apache.commons.collections4.bloomfilter.BloomFilterExtractor
            public BloomFilter[] asBloomFilterArray() {
                return (BloomFilter[]) filters.clone();
            }

            @Override // org.apache.commons.collections4.bloomfilter.BloomFilterExtractor
            public boolean processBloomFilterPair(BloomFilterExtractor other, BiPredicate<BloomFilter, BloomFilter> func) {
                CountingPredicate<BloomFilter> p = new CountingPredicate<>(filters, func);
                return other.processBloomFilters(p) && p.processRemaining();
            }

            @Override // org.apache.commons.collections4.bloomfilter.BloomFilterExtractor
            public boolean processBloomFilters(Predicate<BloomFilter> predicate) {
                return Arrays.stream(filters).allMatch(predicate);
            }
        };
    }

    default BloomFilter[] asBloomFilterArray() {
        final List<BloomFilter> filters = new ArrayList<>();
        processBloomFilters(new Predicate() { // from class: org.apache.commons.collections4.bloomfilter.BloomFilterExtractor$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean add;
                add = filters.add(((BloomFilter) obj).copy());
                return add;
            }
        });
        return (BloomFilter[]) filters.toArray(new BloomFilter[0]);
    }

    default BloomFilter flatten() {
        final AtomicReference<BloomFilter> ref = new AtomicReference<>();
        processBloomFilters(new Predicate() { // from class: org.apache.commons.collections4.bloomfilter.BloomFilterExtractor$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return BloomFilterExtractor.lambda$flatten$1(ref, (BloomFilter) obj);
            }
        });
        return (BloomFilter) Objects.requireNonNull(ref.get(), "No filters.");
    }

    static /* synthetic */ boolean lambda$flatten$1(AtomicReference ref, BloomFilter x) {
        if (ref.get() == null) {
            ref.set(new SimpleBloomFilter(x.getShape()));
        }
        return ((BloomFilter) ref.get()).merge((BloomFilter<?>) x);
    }

    default boolean processBloomFilterPair(BloomFilterExtractor other, BiPredicate<BloomFilter, BloomFilter> func) {
        CountingPredicate<BloomFilter> p = new CountingPredicate<>(asBloomFilterArray(), func);
        return other.processBloomFilters(p) && p.processRemaining();
    }
}
