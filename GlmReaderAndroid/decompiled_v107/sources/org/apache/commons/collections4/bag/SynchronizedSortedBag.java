package org.apache.commons.collections4.bag;

import java.util.Comparator;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.SortedBag;

/* loaded from: classes9.dex */
public class SynchronizedSortedBag<E> extends SynchronizedBag<E> implements SortedBag<E> {
    private static final long serialVersionUID = 722374056718497858L;

    public static <E> SynchronizedSortedBag<E> synchronizedSortedBag(SortedBag<E> bag) {
        return new SynchronizedSortedBag<>(bag);
    }

    protected SynchronizedSortedBag(Bag<E> bag, Object lock) {
        super(bag, lock);
    }

    protected SynchronizedSortedBag(SortedBag<E> bag) {
        super(bag);
    }

    @Override // org.apache.commons.collections4.SortedBag
    public synchronized Comparator<? super E> comparator() {
        Comparator<? super E> comparator;
        synchronized (this.lock) {
            try {
                comparator = getSortedBag().comparator();
            } catch (Throwable th) {
                th = th;
                while (true) {
                    try {
                        break;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                throw th;
            }
        }
        return comparator;
    }

    @Override // org.apache.commons.collections4.SortedBag
    public synchronized E first() {
        E first;
        synchronized (this.lock) {
            try {
                first = getSortedBag().first();
            } catch (Throwable th) {
                th = th;
                while (true) {
                    try {
                        break;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                throw th;
            }
        }
        return first;
    }

    protected SortedBag<E> getSortedBag() {
        return (SortedBag) decorated();
    }

    @Override // org.apache.commons.collections4.SortedBag
    public synchronized E last() {
        E last;
        synchronized (this.lock) {
            try {
                last = getSortedBag().last();
            } catch (Throwable th) {
                th = th;
                while (true) {
                    try {
                        break;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                throw th;
            }
        }
        return last;
    }
}
