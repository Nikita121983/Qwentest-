package org.apache.commons.collections4.bag;

import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.collection.SynchronizedCollection;

/* loaded from: classes9.dex */
public class SynchronizedBag<E> extends SynchronizedCollection<E> implements Bag<E> {
    private static final long serialVersionUID = 8084674570753837109L;

    /* loaded from: classes9.dex */
    final class SynchronizedBagSet extends SynchronizedCollection<E> implements Set<E> {
        private static final long serialVersionUID = 2990565892366827855L;

        SynchronizedBagSet(Set<E> set, Object lock) {
            super(set, lock);
        }
    }

    public static <E> SynchronizedBag<E> synchronizedBag(Bag<E> bag) {
        return new SynchronizedBag<>(bag);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SynchronizedBag(Bag<E> bag) {
        super(bag);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SynchronizedBag(Bag<E> bag, Object lock) {
        super(bag, lock);
    }

    @Override // org.apache.commons.collections4.Bag
    public boolean add(E object, int count) {
        boolean add;
        synchronized (this.lock) {
            add = getBag().add(object, count);
        }
        return add;
    }

    @Override // org.apache.commons.collections4.collection.SynchronizedCollection, java.util.Collection
    public boolean equals(Object object) {
        boolean equals;
        if (object == this) {
            return true;
        }
        synchronized (this.lock) {
            equals = getBag().equals(object);
        }
        return equals;
    }

    protected Bag<E> getBag() {
        return (Bag) decorated();
    }

    @Override // org.apache.commons.collections4.Bag
    public int getCount(Object object) {
        int count;
        synchronized (this.lock) {
            count = getBag().getCount(object);
        }
        return count;
    }

    @Override // org.apache.commons.collections4.collection.SynchronizedCollection, java.util.Collection
    public int hashCode() {
        int hashCode;
        synchronized (this.lock) {
            hashCode = getBag().hashCode();
        }
        return hashCode;
    }

    @Override // org.apache.commons.collections4.Bag
    public boolean remove(Object object, int count) {
        boolean remove;
        synchronized (this.lock) {
            remove = getBag().remove(object, count);
        }
        return remove;
    }

    @Override // org.apache.commons.collections4.Bag
    public Set<E> uniqueSet() {
        SynchronizedBagSet synchronizedBagSet;
        synchronized (this.lock) {
            Set<E> set = getBag().uniqueSet();
            synchronizedBagSet = new SynchronizedBagSet(set, this.lock);
        }
        return synchronizedBagSet;
    }
}
