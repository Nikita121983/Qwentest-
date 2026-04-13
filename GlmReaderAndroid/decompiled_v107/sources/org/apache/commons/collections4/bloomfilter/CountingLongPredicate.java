package org.apache.commons.collections4.bloomfilter;

import java.util.function.LongPredicate;

/* loaded from: classes9.dex */
class CountingLongPredicate implements LongPredicate {
    private final long[] ary;
    private final LongBiPredicate func;
    private int idx;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CountingLongPredicate(long[] ary, LongBiPredicate func) {
        this.ary = ary;
        this.func = func;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean processRemaining() {
        int i = this.idx;
        long[] a = this.ary;
        int limit = a.length;
        while (i != limit && this.func.test(a[i], 0L)) {
            i++;
        }
        return i == limit;
    }

    @Override // java.util.function.LongPredicate
    public boolean test(long other) {
        long j;
        LongBiPredicate longBiPredicate = this.func;
        if (this.idx == this.ary.length) {
            j = 0;
        } else {
            long[] jArr = this.ary;
            int i = this.idx;
            this.idx = i + 1;
            j = jArr[i];
        }
        return longBiPredicate.test(j, other);
    }
}
