package org.apache.commons.math3.filter;

import org.apache.commons.math3.linear.RealMatrix;

/* loaded from: classes10.dex */
public interface MeasurementModel {
    RealMatrix getMeasurementMatrix();

    RealMatrix getMeasurementNoise();
}
