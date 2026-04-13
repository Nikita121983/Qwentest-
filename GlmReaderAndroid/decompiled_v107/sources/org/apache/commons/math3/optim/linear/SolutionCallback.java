package org.apache.commons.math3.optim.linear;

import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;

/* loaded from: classes10.dex */
public class SolutionCallback implements OptimizationData {
    private SimplexTableau tableau;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTableau(SimplexTableau tableau) {
        this.tableau = tableau;
    }

    public PointValuePair getSolution() {
        if (this.tableau != null) {
            return this.tableau.getSolution();
        }
        return null;
    }

    public boolean isSolutionOptimal() {
        if (this.tableau != null) {
            return this.tableau.isOptimal();
        }
        return false;
    }
}
