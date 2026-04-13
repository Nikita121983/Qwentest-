package com.graphbuilder.math.func;

/* loaded from: classes.dex */
public class ModFunction implements Function {
    @Override // com.graphbuilder.math.func.Function
    public double of(double[] d, int numParam) {
        return d[0] % d[1];
    }

    @Override // com.graphbuilder.math.func.Function
    public boolean acceptNumParam(int numParam) {
        return numParam == 2;
    }

    public String toString() {
        return "mod(x, y)";
    }
}
