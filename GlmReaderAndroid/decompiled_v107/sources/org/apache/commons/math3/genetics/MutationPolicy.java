package org.apache.commons.math3.genetics;

import org.apache.commons.math3.exception.MathIllegalArgumentException;

/* loaded from: classes10.dex */
public interface MutationPolicy {
    Chromosome mutate(Chromosome chromosome) throws MathIllegalArgumentException;
}
