package com.graphbuilder.math.func;

/* loaded from: classes.dex */
public class CoshFunction implements Function {
    @Override // com.graphbuilder.math.func.Function
    public double of(double[] d, int numParam) {
        return (Math.pow(2.718281828459045d, d[0]) + Math.pow(2.718281828459045d, -d[0])) / 2.0d;
    }

    @Override // com.graphbuilder.math.func.Function
    public boolean acceptNumParam(int numParam) {
        return numParam == 1;
    }

    public String toString() {
        return "cosh(x)";
    }
}
