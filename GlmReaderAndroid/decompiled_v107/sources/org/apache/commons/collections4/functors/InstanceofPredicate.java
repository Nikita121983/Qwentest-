package org.apache.commons.collections4.functors;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.io.Serializable;
import java.util.Objects;
import org.apache.commons.collections4.Predicate;

/* loaded from: classes9.dex */
public final class InstanceofPredicate extends AbstractPredicate<Object> implements Serializable {
    private static final long serialVersionUID = -6682656911025165584L;
    private final Class<?> iType;

    public static Predicate<Object> instanceOfPredicate(Class<?> type) {
        return new InstanceofPredicate((Class) Objects.requireNonNull(type, ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY));
    }

    public InstanceofPredicate(Class<?> type) {
        this.iType = type;
    }

    public Class<?> getType() {
        return this.iType;
    }

    @Override // org.apache.commons.collections4.Predicate, java.util.function.Predicate
    public boolean test(Object object) {
        return this.iType.isInstance(object);
    }
}
