package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Predicate;

/* loaded from: classes9.dex */
public final class IdentityPredicate<T> extends AbstractPredicate<T> implements Serializable {
    private static final long serialVersionUID = -89901658494523293L;
    private final T iValue;

    public static <T> Predicate<T> identityPredicate(T object) {
        if (object == null) {
            return NullPredicate.nullPredicate();
        }
        return new IdentityPredicate(object);
    }

    public IdentityPredicate(T object) {
        this.iValue = object;
    }

    public T getValue() {
        return this.iValue;
    }

    @Override // org.apache.commons.collections4.Predicate, java.util.function.Predicate
    public boolean test(T object) {
        return this.iValue == object;
    }
}
