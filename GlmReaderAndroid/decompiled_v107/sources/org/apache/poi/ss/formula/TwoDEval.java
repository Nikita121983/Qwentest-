package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public interface TwoDEval extends ValueEval {
    TwoDEval getColumn(int i);

    int getHeight();

    TwoDEval getRow(int i);

    ValueEval getValue(int i, int i2);

    int getWidth();

    boolean isColumn();

    boolean isRowHidden(int i);

    boolean isSubTotal(int i, int i2);

    default boolean isRow() {
        return false;
    }
}
