package org.apache.commons.collections4.bloomfilter;

import java.util.TreeMap;
import java.util.function.IntPredicate;
import java.util.function.ToIntFunction;
import org.apache.commons.collections4.bloomfilter.CellExtractor;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface CellExtractor extends IndexExtractor {

    @FunctionalInterface
    /* loaded from: classes9.dex */
    public interface CellPredicate {
        boolean test(int i, int i2);
    }

    boolean processCells(CellPredicate cellPredicate);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.apache.commons.collections4.bloomfilter.CellExtractor$1, reason: invalid class name */
    /* loaded from: classes9.dex */
    public class AnonymousClass1 implements CellExtractor {
        TreeMap<CounterCell, CounterCell> counterCells = new TreeMap<>();
        final /* synthetic */ IndexExtractor val$indexExtractor;

        AnonymousClass1(IndexExtractor indexExtractor) {
            this.val$indexExtractor = indexExtractor;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: org.apache.commons.collections4.bloomfilter.CellExtractor$1$CounterCell */
        /* loaded from: classes9.dex */
        public final class CounterCell implements Comparable<CounterCell> {
            int count;
            final int idx;

            CounterCell(int idx, int count) {
                this.idx = idx;
                this.count = count;
            }

            @Override // java.lang.Comparable
            public int compareTo(CounterCell other) {
                return Integer.compare(this.idx, other.idx);
            }
        }

        @Override // org.apache.commons.collections4.bloomfilter.IndexExtractor
        public int[] asIndexArray() {
            populate();
            return this.counterCells.keySet().stream().mapToInt(new ToIntFunction() { // from class: org.apache.commons.collections4.bloomfilter.CellExtractor$1$$ExternalSyntheticLambda1
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    int i;
                    i = ((CellExtractor.AnonymousClass1.CounterCell) obj).idx;
                    return i;
                }
            }).toArray();
        }

        private void populate() {
            if (this.counterCells.isEmpty()) {
                this.val$indexExtractor.processIndices(new IntPredicate() { // from class: org.apache.commons.collections4.bloomfilter.CellExtractor$1$$ExternalSyntheticLambda0
                    @Override // java.util.function.IntPredicate
                    public final boolean test(int i) {
                        return CellExtractor.AnonymousClass1.this.m2037xb15feafa(i);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$populate$1$org-apache-commons-collections4-bloomfilter-CellExtractor$1, reason: not valid java name */
        public /* synthetic */ boolean m2037xb15feafa(int idx) {
            CounterCell cell = new CounterCell(idx, 1);
            CounterCell counter = this.counterCells.get(cell);
            if (counter == null) {
                this.counterCells.put(cell, cell);
            } else {
                counter.count++;
            }
            return true;
        }

        @Override // org.apache.commons.collections4.bloomfilter.CellExtractor
        public boolean processCells(CellPredicate consumer) {
            populate();
            for (CounterCell cell : this.counterCells.values()) {
                if (!consumer.test(cell.idx, cell.count)) {
                    return false;
                }
            }
            return true;
        }
    }

    static CellExtractor from(IndexExtractor indexExtractor) {
        return new AnonymousClass1(indexExtractor);
    }

    default boolean processIndices(final IntPredicate predicate) {
        return processCells(new CellPredicate() { // from class: org.apache.commons.collections4.bloomfilter.CellExtractor$$ExternalSyntheticLambda0
            @Override // org.apache.commons.collections4.bloomfilter.CellExtractor.CellPredicate
            public final boolean test(int i, int i2) {
                boolean test;
                test = predicate.test(i);
                return test;
            }
        });
    }

    @Override // org.apache.commons.collections4.bloomfilter.IndexExtractor
    default IndexExtractor uniqueIndices() {
        return this;
    }
}
