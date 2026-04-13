package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.FunctorException;

/* loaded from: classes9.dex */
public final class ExceptionClosure<T> implements Closure<T>, Serializable {
    public static final Closure INSTANCE = new ExceptionClosure();
    private static final long serialVersionUID = 7179106032121985545L;

    public static <T> Closure<T> exceptionClosure() {
        return INSTANCE;
    }

    private ExceptionClosure() {
    }

    @Override // org.apache.commons.collections4.Closure
    public void execute(T input) {
        throw new FunctorException("ExceptionClosure invoked");
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
