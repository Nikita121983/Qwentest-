package com.graphbuilder.math.func;

/* loaded from: classes.dex */
public class EFunction implements Function {
    @Override // com.graphbuilder.math.func.Function
    public double of(double[] d, int numParam) {
        return 2.718281828459045d;
    }

    @Override // com.graphbuilder.math.func.Function
    public boolean acceptNumParam(int numParam) {
        return numParam == 0;
    }

    public String toString() {
        return "e()";
    }
}
