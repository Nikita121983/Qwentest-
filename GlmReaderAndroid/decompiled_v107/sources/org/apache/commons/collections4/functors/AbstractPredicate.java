package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Predicate;

/* loaded from: classes9.dex */
public abstract class AbstractPredicate<T> implements Predicate<T> {
    @Override // org.apache.commons.collections4.Predicate
    public boolean evaluate(T object) {
        return test(object);
    }
}
