package org.apache.commons.collections4.collection;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.list.UnmodifiableList;

/* loaded from: classes9.dex */
public class CompositeCollection<E> implements Collection<E>, Serializable {
    private static final long serialVersionUID = 8417515734108306801L;
    private final List<Collection<E>> all = new ArrayList();
    private CollectionMutator<E> mutator;

    /* loaded from: classes9.dex */
    public interface CollectionMutator<E> extends Serializable {
        boolean add(CompositeCollection<E> compositeCollection, List<Collection<E>> list, E e);

        boolean addAll(CompositeCollection<E> compositeCollection, List<Collection<E>> list, Collection<? extends E> collection);

        boolean remove(CompositeCollection<E> compositeCollection, List<Collection<E>> list, Object obj);
    }

    public CompositeCollection() {
    }

    public CompositeCollection(Collection<E> compositeCollection) {
        addComposited(compositeCollection);
    }

    public CompositeCollection(Collection<E>... compositeCollections) {
        addComposited(compositeCollections);
    }

    public CompositeCollection(Collection<E> compositeCollection1, Collection<E> compositeCollection2) {
        addComposited(compositeCollection1, compositeCollection2);
    }

    @Override // java.util.Collection
    public boolean add(E obj) {
        if (this.mutator == null) {
            throw new UnsupportedOperationException("add() is not supported on CompositeCollection without a CollectionMutator strategy");
        }
        return this.mutator.add(this, this.all, obj);
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends E> coll) {
        if (this.mutator == null) {
            throw new UnsupportedOperationException("addAll() is not supported on CompositeCollection without a CollectionMutator strategy");
        }
        return this.mutator.addAll(this, this.all, coll);
    }

    public void addComposited(Collection<E> compositeCollection) {
        if (compositeCollection != null) {
            this.all.add(compositeCollection);
        }
    }

    public void addComposited(Collection<E>... compositeCollections) {
        for (Collection<E> compositeCollection : compositeCollections) {
            if (compositeCollection != null) {
                this.all.add(compositeCollection);
            }
        }
    }

    public void addComposited(Collection<E> compositeCollection1, Collection<E> compositeCollection2) {
        if (compositeCollection1 != null) {
            this.all.add(compositeCollection1);
        }
        if (compositeCollection2 != null) {
            this.all.add(compositeCollection2);
        }
    }

    @Override // java.util.Collection
    public void clear() {
        for (Collection<E> coll : this.all) {
            coll.clear();
        }
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        for (Collection<E> item : this.all) {
            if (item.contains(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> coll) {
        if (coll == null) {
            return false;
        }
        for (Object item : coll) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    public List<Collection<E>> getCollections() {
        return UnmodifiableList.unmodifiableList(this.all);
    }

    protected CollectionMutator<E> getMutator() {
        return this.mutator;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        for (Collection<E> item : this.all) {
            if (!item.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        if (this.all.isEmpty()) {
            return EmptyIterator.emptyIterator();
        }
        final IteratorChain<E> chain = new IteratorChain<>();
        this.all.forEach(new Consumer() { // from class: org.apache.commons.collections4.collection.CompositeCollection$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                IteratorChain.this.addIterator(((Collection) obj).iterator());
            }
        });
        return chain;
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        if (this.mutator == null) {
            throw new UnsupportedOperationException("remove() is not supported on CompositeCollection without a CollectionMutator strategy");
        }
        return this.mutator.remove(this, this.all, obj);
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<?> coll) {
        if (CollectionUtils.isEmpty(coll)) {
            return false;
        }
        boolean changed = false;
        for (Collection<E> item : this.all) {
            changed |= item.removeAll(coll);
        }
        return changed;
    }

    public void removeComposited(Collection<E> coll) {
        this.all.remove(coll);
    }

    @Override // java.util.Collection
    public boolean removeIf(Predicate<? super E> filter) {
        Objects.isNull(filter);
        if (filter == null) {
            return false;
        }
        boolean changed = false;
        for (Collection<E> item : this.all) {
            changed |= item.removeIf(filter);
        }
        return changed;
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> coll) {
        boolean changed = false;
        if (coll != null) {
            for (Collection<E> item : this.all) {
                changed |= item.retainAll(coll);
            }
        }
        return changed;
    }

    public void setMutator(CollectionMutator<E> mutator) {
        this.mutator = mutator;
    }

    @Override // java.util.Collection
    public int size() {
        int size = 0;
        for (Collection<E> item : this.all) {
            size += item.size();
        }
        return size;
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        Object[] result = new Object[size()];
        int i = 0;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            result[i] = it.next();
            i++;
        }
        return result;
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        Object[] objArr;
        int size = size();
        if (tArr.length >= size) {
            objArr = tArr;
        } else {
            objArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size));
        }
        int i = 0;
        Iterator<Collection<E>> it = this.all.iterator();
        while (it.hasNext()) {
            Iterator<E> it2 = it.next().iterator();
            while (it2.hasNext()) {
                objArr[i] = it2.next();
                i++;
            }
        }
        if (objArr.length > size) {
            objArr[size] = null;
        }
        return (T[]) objArr;
    }

    public Collection<E> toCollection() {
        return new ArrayList(this);
    }
}
