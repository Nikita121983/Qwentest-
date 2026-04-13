package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Closure;

/* loaded from: classes9.dex */
public class ForClosure<T> implements Closure<T> {
    private final Closure<? super T> iClosure;
    private final int iCount;

    /* JADX WARN: Multi-variable type inference failed */
    public static <E> Closure<E> forClosure(int count, Closure<? super E> closure) {
        if (count <= 0 || closure == 0) {
            return NOPClosure.nopClosure();
        }
        if (count == 1) {
            return closure;
        }
        return new ForClosure(count, closure);
    }

    public ForClosure(int count, Closure<? super T> closure) {
        this.iCount = count;
        this.iClosure = closure;
    }

    @Override // org.apache.commons.collections4.Closure
    public void execute(T input) {
        for (int i = 0; i < this.iCount; i++) {
            this.iClosure.accept(input);
        }
    }

    public Closure<? super T> getClosure() {
        return this.iClosure;
    }

    public int getCount() {
        return this.iCount;
    }
}
