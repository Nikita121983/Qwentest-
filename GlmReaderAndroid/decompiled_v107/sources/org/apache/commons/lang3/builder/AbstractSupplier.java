package org.apache.commons.lang3.builder;

import java.lang.Throwable;
import org.apache.commons.lang3.builder.AbstractSupplier;
import org.apache.commons.lang3.function.FailableSupplier;

/* loaded from: classes9.dex */
public abstract class AbstractSupplier<T, B extends AbstractSupplier<T, B, E>, E extends Throwable> implements FailableSupplier<T, E> {
    /* JADX INFO: Access modifiers changed from: protected */
    public B asThis() {
        return this;
    }
}
