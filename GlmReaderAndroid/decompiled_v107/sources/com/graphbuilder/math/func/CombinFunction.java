package com.graphbuilder.math.func;

import com.graphbuilder.math.PascalsTriangle;

/* loaded from: classes.dex */
public class CombinFunction implements Function {
    private final PascalsTriangle pascalsTriangle = new PascalsTriangle();

    @Override // com.graphbuilder.math.func.Function
    public double of(double[] d, int numParam) {
        int n = (int) d[0];
        int r = (int) d[1];
        return this.pascalsTriangle.nCr(n, r);
    }

    @Override // com.graphbuilder.math.func.Function
    public boolean acceptNumParam(int numParam) {
        return numParam == 2;
    }

    public String toString() {
        return "combin(n, r)";
    }
}
