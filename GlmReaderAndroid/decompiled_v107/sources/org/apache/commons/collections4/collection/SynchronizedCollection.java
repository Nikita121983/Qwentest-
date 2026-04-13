package org.apache.commons.collections4.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

/* loaded from: classes9.dex */
public class SynchronizedCollection<E> implements Collection<E>, Serializable {
    private static final long serialVersionUID = 2412805092710877986L;
    private final Collection<E> collection;
    protected final Object lock;

    public static <T> SynchronizedCollection<T> synchronizedCollection(Collection<T> coll) {
        return new SynchronizedCollection<>(coll);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SynchronizedCollection(Collection<E> collection) {
        this.collection = (Collection) Objects.requireNonNull(collection, "collection");
        this.lock = this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SynchronizedCollection(Collection<E> collection, Object lock) {
        this.collection = (Collection) Objects.requireNonNull(collection, "collection");
        this.lock = Objects.requireNonNull(lock, "lock");
    }

    @Override // java.util.Collection
    public boolean add(E object) {
        boolean add;
        synchronized (this.lock) {
            add = decorated().add(object);
        }
        return add;
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends E> coll) {
        boolean addAll;
        synchronized (this.lock) {
            addAll = decorated().addAll(coll);
        }
        return addAll;
    }

    @Override // java.util.Collection
    public void clear() {
        synchronized (this.lock) {
            decorated().clear();
        }
    }

    @Override // java.util.Collection
    public boolean contains(Object object) {
        boolean contains;
        synchronized (this.lock) {
            contains = decorated().contains(object);
        }
        return contains;
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> coll) {
        boolean containsAll;
        synchronized (this.lock) {
            containsAll = decorated().containsAll(coll);
        }
        return containsAll;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Collection<E> decorated() {
        return this.collection;
    }

    @Override // java.util.Collection
    public boolean equals(Object object) {
        synchronized (this.lock) {
            boolean z = true;
            try {
                if (object == this) {
                    return true;
                }
                if (object != this && !decorated().equals(object)) {
                    z = false;
                }
                return z;
            } finally {
            }
        }
    }

    @Override // java.util.Collection
    public int hashCode() {
        int hashCode;
        synchronized (this.lock) {
            hashCode = decorated().hashCode();
        }
        return hashCode;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        boolean isEmpty;
        synchronized (this.lock) {
            isEmpty = decorated().isEmpty();
        }
        return isEmpty;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return decorated().iterator();
    }

    @Override // java.util.Collection
    public boolean remove(Object object) {
        boolean remove;
        synchronized (this.lock) {
            remove = decorated().remove(object);
        }
        return remove;
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<?> coll) {
        boolean removeAll;
        synchronized (this.lock) {
            removeAll = decorated().removeAll(coll);
        }
        return removeAll;
    }

    @Override // java.util.Collection
    public boolean removeIf(Predicate<? super E> filter) {
        boolean removeIf;
        synchronized (this.lock) {
            removeIf = decorated().removeIf(filter);
        }
        return removeIf;
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> coll) {
        boolean retainAll;
        synchronized (this.lock) {
            retainAll = decorated().retainAll(coll);
        }
        return retainAll;
    }

    @Override // java.util.Collection
    public int size() {
        int size;
        synchronized (this.lock) {
            size = decorated().size();
        }
        return size;
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        Object[] array;
        synchronized (this.lock) {
            array = decorated().toArray();
        }
        return array;
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        T[] tArr2;
        synchronized (this.lock) {
            tArr2 = (T[]) decorated().toArray(tArr);
        }
        return tArr2;
    }

    public String toString() {
        String obj;
        synchronized (this.lock) {
            obj = decorated().toString();
        }
        return obj;
    }
}
