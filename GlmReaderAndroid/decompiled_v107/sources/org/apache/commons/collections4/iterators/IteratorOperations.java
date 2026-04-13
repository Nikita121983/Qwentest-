package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* loaded from: classes9.dex */
public interface IteratorOperations<E> extends Iterator<E> {
    static /* synthetic */ HashSet $r8$lambda$j35xzDqY00kBEc7DMQyJC7sG_u4() {
        return new HashSet();
    }

    /* renamed from: $r8$lambda$wBZ5N9SAmxJLRX-vx8hIUeo_fgc, reason: not valid java name */
    static /* synthetic */ ArrayList m2043$r8$lambda$wBZ5N9SAmxJLRXvx8hIUeo_fgc() {
        return new ArrayList();
    }

    default <C extends Collection<E>> C addTo(final C collection) {
        Objects.requireNonNull(collection);
        forEachRemaining(new Consumer() { // from class: org.apache.commons.collections4.iterators.IteratorOperations$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                collection.add(obj);
            }
        });
        return collection;
    }

    default E removeNext() {
        E result = next();
        remove();
        return result;
    }

    default <C extends Collection<E>> C toCollection(Supplier<C> supplier) {
        return (C) addTo(supplier.get());
    }

    default List<E> toList() {
        return (List) toCollection(new Supplier() { // from class: org.apache.commons.collections4.iterators.IteratorOperations$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return IteratorOperations.m2043$r8$lambda$wBZ5N9SAmxJLRXvx8hIUeo_fgc();
            }
        });
    }

    default Set<E> toSet() {
        return (Set) toCollection(new Supplier() { // from class: org.apache.commons.collections4.iterators.IteratorOperations$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return IteratorOperations.$r8$lambda$j35xzDqY00kBEc7DMQyJC7sG_u4();
            }
        });
    }
}
