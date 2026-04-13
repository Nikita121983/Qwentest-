package org.apache.commons.math3.util;

import org.apache.commons.math3.exception.MathIllegalArgumentException;

/* loaded from: classes10.dex */
public interface PivotingStrategyInterface {
    int pivotIndex(double[] dArr, int i, int i2) throws MathIllegalArgumentException;
}
