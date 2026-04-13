package com.graphbuilder.math;

import org.apache.logging.log4j.util.ProcessIdUtil;

/* loaded from: classes.dex */
public class SubNode extends OpNode {
    public SubNode(Expression leftChild, Expression rightChild) {
        super(leftChild, rightChild);
    }

    @Override // com.graphbuilder.math.Expression
    public double eval(VarMap v, FuncMap f) {
        double a = this.leftChild.eval(v, f);
        double b = this.rightChild.eval(v, f);
        return a - b;
    }

    @Override // com.graphbuilder.math.OpNode
    public String getSymbol() {
        return ProcessIdUtil.DEFAULT_PROCESSID;
    }
}
