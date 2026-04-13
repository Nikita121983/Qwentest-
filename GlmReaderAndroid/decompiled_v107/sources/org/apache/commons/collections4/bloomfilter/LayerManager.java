package org.apache.commons.collections4.bloomfilter;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.apache.commons.collections4.bloomfilter.BloomFilter;
import org.apache.commons.collections4.bloomfilter.LayerManager;

/* loaded from: classes9.dex */
public class LayerManager<T extends BloomFilter<T>> implements BloomFilterExtractor {
    private final Predicate<LayerManager<T>> extendCheck;
    private final Consumer<Deque<T>> filterCleanup;
    private final Supplier<T> filterSupplier;
    private final LinkedList<T> filters;

    /* loaded from: classes9.dex */
    public static class Builder<T extends BloomFilter<T>> implements Supplier<LayerManager<T>> {
        private Consumer<Deque<T>> cleanup;
        private Predicate<LayerManager<T>> extendCheck;
        private Supplier<T> supplier;

        private Builder() {
            this.extendCheck = ExtendCheck.neverAdvance();
            this.cleanup = Cleanup.noCleanup();
        }

        @Override // java.util.function.Supplier
        public LayerManager<T> get() {
            return new LayerManager<>(this.supplier, this.extendCheck, this.cleanup, true);
        }

        public Builder<T> setCleanup(Consumer<Deque<T>> cleanup) {
            this.cleanup = cleanup;
            return this;
        }

        public Builder<T> setExtendCheck(Predicate<LayerManager<T>> extendCheck) {
            this.extendCheck = extendCheck;
            return this;
        }

        public Builder<T> setSupplier(Supplier<T> supplier) {
            this.supplier = supplier;
            return this;
        }
    }

    /* loaded from: classes9.dex */
    public static final class Cleanup {
        public static <T extends BloomFilter<T>> Consumer<Deque<T>> noCleanup() {
            return new Consumer() { // from class: org.apache.commons.collections4.bloomfilter.LayerManager$Cleanup$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    LayerManager.Cleanup.lambda$noCleanup$0((Deque) obj);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$noCleanup$0(Deque x) {
        }

        public static <T extends BloomFilter<T>> Consumer<Deque<T>> onMaxSize(final int maxSize) {
            if (maxSize <= 0) {
                throw new IllegalArgumentException("'maxSize' must be greater than 0");
            }
            return new Consumer() { // from class: org.apache.commons.collections4.bloomfilter.LayerManager$Cleanup$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    LayerManager.Cleanup.lambda$onMaxSize$1(maxSize, (Deque) obj);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onMaxSize$1(int maxSize, Deque ll) {
            while (ll.size() > maxSize) {
                ll.removeFirst();
            }
        }

        public static <T extends BloomFilter<T>> Consumer<Deque<T>> removeEmptyTarget() {
            return new Consumer() { // from class: org.apache.commons.collections4.bloomfilter.LayerManager$Cleanup$$ExternalSyntheticLambda2
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    LayerManager.Cleanup.lambda$removeEmptyTarget$2((Deque) obj);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$removeEmptyTarget$2(Deque x) {
            if (!x.isEmpty() && ((BloomFilter) x.getLast()).isEmpty()) {
                x.removeLast();
            }
        }

        public static <T extends BloomFilter<T>> Consumer<Deque<T>> removeIf(final Predicate<? super T> test) {
            return new Consumer() { // from class: org.apache.commons.collections4.bloomfilter.LayerManager$Cleanup$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((Deque) obj).removeIf(test);
                }
            };
        }

        private Cleanup() {
        }
    }

    /* loaded from: classes9.dex */
    public static final class ExtendCheck {
        public static <T extends BloomFilter<T>> Predicate<LayerManager<T>> advanceOnCount(final int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("'breakAt' must be greater than 0");
            }
            return (Predicate<LayerManager<T>>) new Predicate<LayerManager<T>>() { // from class: org.apache.commons.collections4.bloomfilter.LayerManager.ExtendCheck.1
                int count;

                @Override // java.util.function.Predicate
                public boolean test(LayerManager<T> filter) {
                    int i2 = this.count + 1;
                    this.count = i2;
                    if (i2 != i) {
                        return false;
                    }
                    this.count = 0;
                    return true;
                }
            };
        }

        public static <T extends BloomFilter<T>> Predicate<LayerManager<T>> advanceOnPopulated() {
            return new Predicate() { // from class: org.apache.commons.collections4.bloomfilter.LayerManager$ExtendCheck$$ExternalSyntheticLambda2
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return LayerManager.ExtendCheck.lambda$advanceOnPopulated$0((LayerManager) obj);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ boolean lambda$advanceOnPopulated$0(LayerManager lm) {
            return !lm.last().isEmpty();
        }

        public static <T extends BloomFilter<T>> Predicate<LayerManager<T>> advanceOnSaturation(final double maxN) {
            if (maxN <= 0.0d) {
                throw new IllegalArgumentException("'maxN' must be greater than 0");
            }
            return new Predicate() { // from class: org.apache.commons.collections4.bloomfilter.LayerManager$ExtendCheck$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return LayerManager.ExtendCheck.lambda$advanceOnSaturation$1(maxN, (LayerManager) obj);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ boolean lambda$advanceOnSaturation$1(double maxN, LayerManager manager) {
            BloomFilter last = manager.last();
            return maxN <= last.getShape().estimateN(last.cardinality());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ boolean lambda$neverAdvance$2(LayerManager x) {
            return false;
        }

        public static <T extends BloomFilter<T>> Predicate<LayerManager<T>> neverAdvance() {
            return new Predicate() { // from class: org.apache.commons.collections4.bloomfilter.LayerManager$ExtendCheck$$ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return LayerManager.ExtendCheck.lambda$neverAdvance$2((LayerManager) obj);
                }
            };
        }

        private ExtendCheck() {
        }
    }

    public static <T extends BloomFilter<T>> Builder<T> builder() {
        return new Builder<>();
    }

    private LayerManager(Supplier<T> filterSupplier, Predicate<LayerManager<T>> extendCheck, Consumer<Deque<T>> filterCleanup, boolean initialize) {
        this.filters = new LinkedList<>();
        this.filterSupplier = (Supplier) Objects.requireNonNull(filterSupplier, "filterSupplier");
        this.extendCheck = (Predicate) Objects.requireNonNull(extendCheck, "extendCheck");
        this.filterCleanup = (Consumer) Objects.requireNonNull(filterCleanup, "filterCleanup");
        if (initialize) {
            addFilter();
        }
    }

    private void addFilter() {
        this.filters.add((BloomFilter) Objects.requireNonNull(this.filterSupplier.get(), "filterSupplier.get() returned null."));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cleanup() {
        this.filterCleanup.accept(this.filters);
        if (this.filters.isEmpty()) {
            addFilter();
        }
    }

    public final void clear() {
        this.filters.clear();
        addFilter();
    }

    public LayerManager<T> copy() {
        LayerManager<T> layerManager = new LayerManager<>(this.filterSupplier, this.extendCheck, this.filterCleanup, false);
        Iterator<T> it = this.filters.iterator();
        while (it.hasNext()) {
            layerManager.filters.add(it.next().copy());
        }
        return layerManager;
    }

    public final T first() {
        return this.filters.getFirst();
    }

    public final T get(int depth) {
        if (depth < 0 || depth >= this.filters.size()) {
            throw new NoSuchElementException(String.format("Depth must be in the range [0,%s)", Integer.valueOf(this.filters.size())));
        }
        return this.filters.get(depth);
    }

    public final int getDepth() {
        return this.filters.size();
    }

    public final T getTarget() {
        if (this.extendCheck.test(this)) {
            next();
        }
        return last();
    }

    public final T last() {
        return this.filters.getLast();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void next() {
        this.filterCleanup.accept(this.filters);
        addFilter();
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilterExtractor
    public boolean processBloomFilters(Predicate<BloomFilter> bloomFilterPredicate) {
        return this.filters.stream().allMatch(bloomFilterPredicate);
    }
}
