package org.apache.commons.collections4.bag;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;
import org.apache.commons.collections4.Bag;

/* loaded from: classes9.dex */
public final class CollectionBag<E> extends AbstractBagDecorator<E> {
    private static final long serialVersionUID = -2560033712679053143L;

    public static <E> Bag<E> collectionBag(Bag<E> bag) {
        return new CollectionBag(bag);
    }

    public CollectionBag(Bag<E> bag) {
        super(bag);
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean add(E object) {
        return add(object, 1);
    }

    @Override // org.apache.commons.collections4.bag.AbstractBagDecorator, org.apache.commons.collections4.Bag
    public boolean add(E object, int count) {
        decorated().add(object, count);
        return true;
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public boolean addAll(Collection<? extends E> coll) {
        boolean changed = false;
        for (E current : coll) {
            boolean z = true;
            boolean added = add(current, 1);
            if (!changed && !added) {
                z = false;
            }
            changed = z;
        }
        return changed;
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean containsAll(Collection<?> coll) {
        return coll.stream().allMatch(new Predicate() { // from class: org.apache.commons.collections4.bag.CollectionBag$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CollectionBag.this.contains(obj);
            }
        });
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        setCollection((Collection) in.readObject());
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean remove(Object object) {
        return remove(object, 1);
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean removeAll(Collection<?> coll) {
        if (coll != null) {
            boolean result = false;
            for (Object obj : coll) {
                boolean changed = remove(obj, getCount(obj));
                result = result || changed;
            }
            return result;
        }
        return decorated().removeAll(null);
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean retainAll(Collection<?> coll) {
        if (coll != null) {
            boolean modified = false;
            Iterator<E> e = iterator();
            while (e.hasNext()) {
                if (!coll.contains(e.next())) {
                    e.remove();
                    modified = true;
                }
            }
            return modified;
        }
        return decorated().retainAll(null);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(decorated());
    }
}
