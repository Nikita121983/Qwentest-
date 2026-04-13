package org.apache.commons.collections4.bloomfilter;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/* loaded from: classes9.dex */
class CountingPredicate<T> implements Predicate<T> {
    private final T[] ary;
    private final BiPredicate<T, T> func;
    private int idx;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CountingPredicate(T[] ary, BiPredicate<T, T> func) {
        this.ary = ary;
        this.func = func;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean processRemaining() {
        int i = this.idx;
        T[] a = this.ary;
        int limit = a.length;
        while (i != limit && this.func.test(a[i], null)) {
            i++;
        }
        return i == limit;
    }

    @Override // java.util.function.Predicate
    public boolean test(T other) {
        T t;
        BiPredicate<T, T> biPredicate = this.func;
        if (this.idx == this.ary.length) {
            t = null;
        } else {
            T[] tArr = this.ary;
            int i = this.idx;
            this.idx = i + 1;
            t = tArr[i];
        }
        return biPredicate.test(t, other);
    }
}
