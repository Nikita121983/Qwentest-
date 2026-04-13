package com.graphbuilder.math;

/* loaded from: classes.dex */
public class PowNode extends OpNode {
    public PowNode(Expression leftChild, Expression rightChild) {
        super(leftChild, rightChild);
    }

    @Override // com.graphbuilder.math.Expression
    public double eval(VarMap v, FuncMap f) {
        double a = this.leftChild.eval(v, f);
        double b = this.rightChild.eval(v, f);
        return Math.pow(a, b);
    }

    @Override // com.graphbuilder.math.OpNode
    public String getSymbol() {
        return "^";
    }
}
