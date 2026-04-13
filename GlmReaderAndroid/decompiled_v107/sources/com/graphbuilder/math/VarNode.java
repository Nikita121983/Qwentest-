package com.graphbuilder.math;

/* loaded from: classes.dex */
public class VarNode extends TermNode {
    public VarNode(String name, boolean negate) {
        super(name, negate);
    }

    @Override // com.graphbuilder.math.Expression
    public double eval(VarMap v, FuncMap f) {
        double val = v.getValue(this.name);
        return this.negate ? -val : val;
    }
}
