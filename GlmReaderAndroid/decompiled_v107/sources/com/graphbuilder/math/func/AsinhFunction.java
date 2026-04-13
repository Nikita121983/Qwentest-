package com.graphbuilder.math.func;

/* loaded from: classes.dex */
public class AsinhFunction implements Function {
    @Override // com.graphbuilder.math.func.Function
    public double of(double[] d, int numParam) {
        return Math.log(d[0] + Math.sqrt((d[0] * d[0]) + 1.0d));
    }

    @Override // com.graphbuilder.math.func.Function
    public boolean acceptNumParam(int numParam) {
        return numParam == 1;
    }

    public String toString() {
        return "asinh(x)";
    }
}
