package org.apache.commons.lang3.concurrent;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface Computable<I, O> {
    O compute(I i) throws InterruptedException;
}
