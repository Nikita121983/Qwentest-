package com.graphbuilder.math.func;

/* loaded from: classes.dex */
public class AsinFunction implements Function {
    @Override // com.graphbuilder.math.func.Function
    public double of(double[] d, int numParam) {
        return Math.asin(d[0]);
    }

    @Override // com.graphbuilder.math.func.Function
    public boolean acceptNumParam(int numParam) {
        return numParam == 1;
    }

    public String toString() {
        return "asin(x)";
    }
}
