package com.graphbuilder.math.func;

/* loaded from: classes.dex */
public class TanhFunction implements Function {
    @Override // com.graphbuilder.math.func.Function
    public double of(double[] d, int numParam) {
        double e = Math.pow(2.718281828459045d, d[0] * 2.0d);
        return (e - 1.0d) / (1.0d + e);
    }

    @Override // com.graphbuilder.math.func.Function
    public boolean acceptNumParam(int numParam) {
        return numParam == 1;
    }

    public String toString() {
        return "tanh(x)";
    }
}
