package org.apache.commons.math3.ode;

import java.io.Serializable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public class ParameterConfiguration implements Serializable {
    private static final long serialVersionUID = 2247518849090889379L;
    private double hP;
    private String parameterName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ParameterConfiguration(String parameterName, double hP) {
        this.parameterName = parameterName;
        this.hP = hP;
    }

    public String getParameterName() {
        return this.parameterName;
    }

    public double getHP() {
        return this.hP;
    }

    public void setHP(double hParam) {
        this.hP = hParam;
    }
}
