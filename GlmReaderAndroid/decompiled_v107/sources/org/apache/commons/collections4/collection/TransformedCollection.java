package org.apache.commons.collections4.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.Transformer;

/* loaded from: classes9.dex */
public class TransformedCollection<E> extends AbstractCollectionDecorator<E> {
    private static final long serialVersionUID = 8692300188161871514L;
    protected final Transformer<? super E, ? extends E> transformer;

    public static <E> TransformedCollection<E> transformedCollection(Collection<E> collection, Transformer<? super E, ? extends E> transformer) {
        TransformedCollection<E> decorated = new TransformedCollection<>(collection, transformer);
        if (!collection.isEmpty()) {
            Object[] array = collection.toArray();
            collection.clear();
            for (Object obj : array) {
                decorated.decorated().add(transformer.apply(obj));
            }
        }
        return decorated;
    }

    public static <E> TransformedCollection<E> transformingCollection(Collection<E> coll, Transformer<? super E, ? extends E> transformer) {
        return new TransformedCollection<>(coll, transformer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TransformedCollection(Collection<E> collection, Transformer<? super E, ? extends E> transformer) {
        super(collection);
        this.transformer = (Transformer) Objects.requireNonNull(transformer, "transformer");
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean add(E object) {
        return decorated().add(transform((TransformedCollection<E>) object));
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public boolean addAll(Collection<? extends E> coll) {
        return decorated().addAll(transform((Collection) coll));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Collection<E> transform(Collection<? extends E> coll) {
        List<E> list = new ArrayList<>(coll.size());
        for (E item : coll) {
            list.add(transform((TransformedCollection<E>) item));
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public E transform(E object) {
        return this.transformer.apply(object);
    }
}
