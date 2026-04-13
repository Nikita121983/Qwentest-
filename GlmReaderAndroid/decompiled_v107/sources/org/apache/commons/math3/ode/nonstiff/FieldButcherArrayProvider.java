package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.RealFieldElement;

/* loaded from: classes10.dex */
public interface FieldButcherArrayProvider<T extends RealFieldElement<T>> {
    T[][] getA();

    T[] getB();

    T[] getC();
}
