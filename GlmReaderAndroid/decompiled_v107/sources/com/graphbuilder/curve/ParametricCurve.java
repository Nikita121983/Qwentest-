package com.graphbuilder.curve;

/* loaded from: classes.dex */
public abstract class ParametricCurve extends Curve {
    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void eval(double[] dArr);

    public abstract int getSampleLimit();

    public ParametricCurve(ControlPath cp, GroupIterator gp) {
        super(cp, gp);
    }
}
