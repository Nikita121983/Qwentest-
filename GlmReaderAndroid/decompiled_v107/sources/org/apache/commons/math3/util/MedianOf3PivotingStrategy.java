package org.apache.commons.math3.util;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;

/* loaded from: classes10.dex */
public class MedianOf3PivotingStrategy implements PivotingStrategyInterface, Serializable {
    private static final long serialVersionUID = 20140713;

    @Override // org.apache.commons.math3.util.PivotingStrategyInterface
    public int pivotIndex(double[] work, int begin, int end) throws MathIllegalArgumentException {
        MathArrays.verifyValues(work, begin, end - begin);
        int inclusiveEnd = end - 1;
        int middle = ((inclusiveEnd - begin) / 2) + begin;
        double wBegin = work[begin];
        double wMiddle = work[middle];
        double wEnd = work[inclusiveEnd];
        if (wBegin < wMiddle) {
            if (wMiddle < wEnd) {
                return middle;
            }
            return wBegin < wEnd ? inclusiveEnd : begin;
        }
        if (wBegin < wEnd) {
            return begin;
        }
        return wMiddle < wEnd ? inclusiveEnd : middle;
    }
}
