package org.apache.commons.collections4.set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.OrderedIterator;
import org.apache.commons.collections4.functors.UniquePredicate;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.list.UnmodifiableList;
import org.apache.xmlbeans.XmlErrorCodes;

/* loaded from: classes9.dex */
public class ListOrderedSet<E> extends AbstractSerializableSetDecorator<E> {
    private static final long serialVersionUID = -228664372470420141L;
    private final List<E> setOrder;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class OrderedSetIterator<E> extends AbstractIteratorDecorator<E> implements OrderedIterator<E> {
        private E last;
        private final Collection<E> set;

        private OrderedSetIterator(ListIterator<E> iterator, Collection<E> set) {
            super(iterator);
            this.set = set;
        }

        @Override // org.apache.commons.collections4.OrderedIterator
        public boolean hasPrevious() {
            return ((ListIterator) getIterator()).hasPrevious();
        }

        @Override // org.apache.commons.collections4.iterators.AbstractIteratorDecorator, java.util.Iterator
        public E next() {
            this.last = getIterator().next();
            return this.last;
        }

        @Override // org.apache.commons.collections4.OrderedIterator
        public E previous() {
            this.last = (E) ((ListIterator) getIterator()).previous();
            return this.last;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractUntypedIteratorDecorator, java.util.Iterator
        public void remove() {
            this.set.remove(this.last);
            getIterator().remove();
            this.last = null;
        }
    }

    public static <E> ListOrderedSet<E> listOrderedSet(List<E> list) {
        Objects.requireNonNull(list, XmlErrorCodes.LIST);
        CollectionUtils.filter(list, UniquePredicate.uniquePredicate());
        Set<E> set = new HashSet<>(list);
        return new ListOrderedSet<>(set, list);
    }

    public static <E> ListOrderedSet<E> listOrderedSet(Set<E> set) {
        return new ListOrderedSet<>(set);
    }

    public static <E> ListOrderedSet<E> listOrderedSet(Set<E> set, List<E> list) {
        Objects.requireNonNull(set, "set");
        Objects.requireNonNull(list, XmlErrorCodes.LIST);
        if (!set.isEmpty() || !list.isEmpty()) {
            throw new IllegalArgumentException("Set and List must be empty");
        }
        return new ListOrderedSet<>(set, list);
    }

    public ListOrderedSet() {
        super(new HashSet());
        this.setOrder = new ArrayList();
    }

    protected ListOrderedSet(Set<E> set) {
        super(set);
        this.setOrder = new ArrayList(set);
    }

    protected ListOrderedSet(Set<E> set, List<E> list) {
        super(set);
        this.setOrder = (List) Objects.requireNonNull(list, XmlErrorCodes.LIST);
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean add(E object) {
        if (decorated().add(object)) {
            this.setOrder.add(object);
            return true;
        }
        return false;
    }

    public void add(int index, E object) {
        if (!contains(object)) {
            decorated().add(object);
            this.setOrder.add(index, object);
        }
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public boolean addAll(Collection<? extends E> coll) {
        boolean result = false;
        for (E e : coll) {
            result |= add(e);
        }
        return result;
    }

    public boolean addAll(int index, Collection<? extends E> coll) {
        boolean changed = false;
        List<E> toAdd = new ArrayList<>();
        for (E e : coll) {
            if (!contains(e)) {
                decorated().add(e);
                toAdd.add(e);
                changed = true;
            }
        }
        if (changed) {
            this.setOrder.addAll(index, toAdd);
        }
        return changed;
    }

    public List<E> asList() {
        return UnmodifiableList.unmodifiableList(this.setOrder);
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public void clear() {
        decorated().clear();
        this.setOrder.clear();
    }

    public E get(int index) {
        return this.setOrder.get(index);
    }

    public int indexOf(Object object) {
        return this.setOrder.indexOf(object);
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.lang.Iterable, org.apache.commons.collections4.Bag
    public OrderedIterator<E> iterator() {
        return new OrderedSetIterator(this.setOrder.listIterator(), decorated());
    }

    public E remove(int index) {
        E obj = this.setOrder.remove(index);
        remove(obj);
        return obj;
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean remove(Object object) {
        boolean result = decorated().remove(object);
        if (result) {
            this.setOrder.remove(object);
        }
        return result;
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean removeAll(Collection<?> coll) {
        boolean result = false;
        for (Object name : coll) {
            result |= remove(name);
        }
        return result;
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public boolean removeIf(Predicate<? super E> filter) {
        Objects.isNull(filter);
        if (filter == null) {
            return false;
        }
        boolean result = decorated().removeIf(filter);
        if (result) {
            this.setOrder.removeIf(filter);
        }
        return result;
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean retainAll(Collection<?> coll) {
        boolean result = decorated().retainAll(coll);
        if (!result) {
            return false;
        }
        if (decorated().isEmpty()) {
            this.setOrder.clear();
        } else {
            this.setOrder.removeIf(new Predicate() { // from class: org.apache.commons.collections4.set.ListOrderedSet$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ListOrderedSet.this.m2053x583e3d8a(obj);
                }
            });
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$retainAll$0$org-apache-commons-collections4-set-ListOrderedSet, reason: not valid java name */
    public /* synthetic */ boolean m2053x583e3d8a(Object e) {
        return !decorated().contains(e);
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public Object[] toArray() {
        return this.setOrder.toArray();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) this.setOrder.toArray(tArr);
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator
    public String toString() {
        return this.setOrder.toString();
    }
}
