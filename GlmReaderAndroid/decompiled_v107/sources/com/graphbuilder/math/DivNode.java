package com.graphbuilder.math;

import org.apache.poi.openxml4j.opc.PackagingURIHelper;

/* loaded from: classes.dex */
public class DivNode extends OpNode {
    public DivNode(Expression leftChild, Expression rightChild) {
        super(leftChild, rightChild);
    }

    @Override // com.graphbuilder.math.Expression
    public double eval(VarMap v, FuncMap f) {
        double a = this.leftChild.eval(v, f);
        double b = this.rightChild.eval(v, f);
        return a / b;
    }

    @Override // com.graphbuilder.math.OpNode
    public String getSymbol() {
        return PackagingURIHelper.FORWARD_SLASH_STRING;
    }
}
