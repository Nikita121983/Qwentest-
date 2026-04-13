package com.graphbuilder.math.func;

/* loaded from: classes.dex */
public class AcoshFunction implements Function {
    @Override // com.graphbuilder.math.func.Function
    public double of(double[] d, int numParam) {
        double a = Math.sqrt((d[0] + 1.0d) / 2.0d);
        double b = Math.sqrt((d[0] - 1.0d) / 2.0d);
        return Math.log(a + b) * 2.0d;
    }

    @Override // com.graphbuilder.math.func.Function
    public boolean acceptNumParam(int numParam) {
        return numParam == 1;
    }

    public String toString() {
        return "acosh(x)";
    }
}
