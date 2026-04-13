package org.apache.commons.collections4.bloomfilter;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import org.apache.commons.collections4.bloomfilter.BloomFilter;

/* loaded from: classes9.dex */
public class LayeredBloomFilter<T extends BloomFilter<T>> implements BloomFilter<LayeredBloomFilter<T>>, BloomFilterExtractor {
    private final LayerManager<T> layerManager;
    private final Shape shape;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class Finder implements Predicate<BloomFilter> {
        BloomFilter<?> bf;
        int bfIdx;
        int[] result;
        int resultIdx;

        Finder(BloomFilter<?> bf) {
            this.result = new int[LayeredBloomFilter.this.layerManager.getDepth()];
            this.bf = bf;
        }

        int[] getResult() {
            return Arrays.copyOf(this.result, this.resultIdx);
        }

        @Override // java.util.function.Predicate
        public boolean test(BloomFilter x) {
            if (x.contains(this.bf)) {
                int[] iArr = this.result;
                int i = this.resultIdx;
                this.resultIdx = i + 1;
                iArr[i] = this.bfIdx;
            }
            this.bfIdx++;
            return true;
        }
    }

    public LayeredBloomFilter(Shape shape, LayerManager<T> layerManager) {
        this.shape = shape;
        this.layerManager = layerManager;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public int cardinality() {
        return SetOperations.cardinality(this);
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public int characteristics() {
        return 0;
    }

    public void cleanup() {
        this.layerManager.cleanup();
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public final void clear() {
        this.layerManager.clear();
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean contains(BitMapExtractor bitMapExtractor) {
        return contains((BloomFilter) createFilter(bitMapExtractor));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$contains$0(BloomFilter other, BloomFilter x) {
        return !x.contains((BloomFilter<?>) other);
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean contains(final BloomFilter other) {
        return other instanceof BloomFilterExtractor ? contains((BloomFilterExtractor) other) : !processBloomFilters(new Predicate() { // from class: org.apache.commons.collections4.bloomfilter.LayeredBloomFilter$$ExternalSyntheticLambda4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return LayeredBloomFilter.lambda$contains$0(BloomFilter.this, (BloomFilter) obj);
            }
        });
    }

    public boolean contains(BloomFilterExtractor bloomFilterExtractor) {
        final boolean[] result = {true};
        return bloomFilterExtractor.processBloomFilters(new Predicate() { // from class: org.apache.commons.collections4.bloomfilter.LayeredBloomFilter$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return LayeredBloomFilter.this.m2038x5565950b(result, (BloomFilter) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$contains$1$org-apache-commons-collections4-bloomfilter-LayeredBloomFilter, reason: not valid java name */
    public /* synthetic */ boolean m2038x5565950b(boolean[] result, BloomFilter x) {
        result[0] = result[0] & contains(x);
        return result[0];
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean contains(Hasher hasher) {
        return contains((BloomFilter) createFilter(hasher));
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean contains(IndexExtractor indexExtractor) {
        return contains((BloomFilter) createFilter(indexExtractor));
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public LayeredBloomFilter<T> copy() {
        return new LayeredBloomFilter<>(this.shape, this.layerManager.copy());
    }

    private SimpleBloomFilter createFilter(BitMapExtractor bitMapExtractor) {
        SimpleBloomFilter bf = new SimpleBloomFilter(this.shape);
        bf.merge(bitMapExtractor);
        return bf;
    }

    private SimpleBloomFilter createFilter(Hasher hasher) {
        SimpleBloomFilter bf = new SimpleBloomFilter(this.shape);
        bf.merge(hasher);
        return bf;
    }

    private SimpleBloomFilter createFilter(IndexExtractor indexExtractor) {
        SimpleBloomFilter bf = new SimpleBloomFilter(this.shape);
        bf.merge(indexExtractor);
        return bf;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public int estimateN() {
        return flatten().estimateN();
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public int estimateUnion(BloomFilter other) {
        Objects.requireNonNull(other, "other");
        BloomFilter cpy = flatten();
        cpy.merge((BloomFilter<?>) other);
        return cpy.estimateN();
    }

    public int[] find(BitMapExtractor bitMapExtractor) {
        SimpleBloomFilter bf = new SimpleBloomFilter(this.shape);
        bf.merge(bitMapExtractor);
        return find((BloomFilter) bf);
    }

    public int[] find(BloomFilter bf) {
        LayeredBloomFilter<T>.Finder finder = new Finder(bf);
        processBloomFilters(finder);
        return finder.getResult();
    }

    public int[] find(Hasher hasher) {
        SimpleBloomFilter bf = new SimpleBloomFilter(this.shape);
        bf.merge(hasher);
        return find((BloomFilter) bf);
    }

    public int[] find(IndexExtractor indexExtractor) {
        SimpleBloomFilter bf = new SimpleBloomFilter(this.shape);
        bf.merge(indexExtractor);
        return find((BloomFilter) bf);
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilterExtractor
    public SimpleBloomFilter flatten() {
        final SimpleBloomFilter bf = new SimpleBloomFilter(this.shape);
        Objects.requireNonNull(bf);
        processBloomFilters(new Predicate() { // from class: org.apache.commons.collections4.bloomfilter.LayeredBloomFilter$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return SimpleBloomFilter.this.merge((BloomFilter<?>) obj);
            }
        });
        return bf;
    }

    public T get(int depth) {
        return this.layerManager.get(depth);
    }

    public final int getDepth() {
        return this.layerManager.getDepth();
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public final Shape getShape() {
        return this.shape;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean isEmpty() {
        return processBloomFilters(new Predicate() { // from class: org.apache.commons.collections4.bloomfilter.LayeredBloomFilter$$ExternalSyntheticLambda2
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((BloomFilter) obj).isEmpty();
            }
        });
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean merge(BitMapExtractor bitMapExtractor) {
        return this.layerManager.getTarget().merge(bitMapExtractor);
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean merge(BloomFilter bf) {
        return this.layerManager.getTarget().merge(bf);
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean merge(IndexExtractor indexExtractor) {
        return this.layerManager.getTarget().merge(indexExtractor);
    }

    public void next() {
        this.layerManager.next();
    }

    @Override // org.apache.commons.collections4.bloomfilter.BitMapExtractor
    public boolean processBitMaps(LongPredicate predicate) {
        return flatten().processBitMaps(predicate);
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilterExtractor
    public final boolean processBloomFilters(Predicate<BloomFilter> bloomFilterPredicate) {
        return this.layerManager.processBloomFilters(bloomFilterPredicate);
    }

    @Override // org.apache.commons.collections4.bloomfilter.IndexExtractor, org.apache.commons.collections4.bloomfilter.CellExtractor
    public boolean processIndices(final IntPredicate predicate) {
        return processBloomFilters(new Predicate() { // from class: org.apache.commons.collections4.bloomfilter.LayeredBloomFilter$$ExternalSyntheticLambda3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean processIndices;
                processIndices = ((BloomFilter) obj).processIndices(predicate);
                return processIndices;
            }
        });
    }
}
