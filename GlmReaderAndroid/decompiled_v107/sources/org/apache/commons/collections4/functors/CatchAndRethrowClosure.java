package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.FunctorException;

/* loaded from: classes9.dex */
public abstract class CatchAndRethrowClosure<T> implements Closure<T> {
    protected abstract void executeAndThrow(T t) throws Throwable;

    @Override // org.apache.commons.collections4.Closure
    public void execute(T input) {
        try {
            executeAndThrow(input);
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Throwable t) {
            throw new FunctorException(t);
        }
    }
}
