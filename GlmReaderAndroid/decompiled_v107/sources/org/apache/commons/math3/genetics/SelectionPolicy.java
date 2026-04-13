package org.apache.commons.math3.genetics;

import org.apache.commons.math3.exception.MathIllegalArgumentException;

/* loaded from: classes10.dex */
public interface SelectionPolicy {
    ChromosomePair select(Population population) throws MathIllegalArgumentException;
}
