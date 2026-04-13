package com.graphbuilder.math.func;

/* loaded from: classes.dex */
public class RandFunction implements Function {
    @Override // com.graphbuilder.math.func.Function
    public double of(double[] d, int numParam) {
        return Math.random();
    }

    @Override // com.graphbuilder.math.func.Function
    public boolean acceptNumParam(int numParam) {
        return numParam == 0;
    }

    public String toString() {
        return "rand()";
    }
}
