package org.apache.commons.collections4.functors;

import java.io.Serializable;
import java.util.Objects;
import org.apache.commons.collections4.Equator;
import org.apache.commons.collections4.Predicate;

/* loaded from: classes9.dex */
public final class EqualPredicate<T> extends AbstractPredicate<T> implements Serializable {
    private static final long serialVersionUID = 5633766978029907089L;
    private final Equator<T> equator;
    private final T test;

    public static <T> Predicate<T> equalPredicate(T object) {
        if (object == null) {
            return NullPredicate.nullPredicate();
        }
        return new EqualPredicate(object);
    }

    public static <T> Predicate<T> equalPredicate(T object, Equator<T> equator) {
        if (object == null) {
            return NullPredicate.nullPredicate();
        }
        return new EqualPredicate(object, equator);
    }

    public EqualPredicate(T object) {
        this(object, null);
    }

    public EqualPredicate(T test, Equator<T> equator) {
        this.test = test;
        this.equator = equator;
    }

    public Object getValue() {
        return this.test;
    }

    @Override // org.apache.commons.collections4.Predicate, java.util.function.Predicate
    public boolean test(T object) {
        if (this.equator != null) {
            return this.equator.equate(this.test, object);
        }
        return Objects.equals(this.test, object);
    }
}
