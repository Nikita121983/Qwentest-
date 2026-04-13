package org.apache.commons.math3.analysis.integration.gauss;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Pair;

/* loaded from: classes10.dex */
public class HermiteRuleFactory extends BaseRuleFactory<Double> {
    private static final double H0 = 0.7511255444649425d;
    private static final double H1 = 1.0622519320271968d;
    private static final double SQRT_PI = 1.772453850905516d;

    @Override // org.apache.commons.math3.analysis.integration.gauss.BaseRuleFactory
    protected Pair<Double[], Double[]> computeRule(int numberOfPoints) throws DimensionMismatchException {
        int i = numberOfPoints;
        Double valueOf = Double.valueOf(0.0d);
        if (i == 1) {
            return new Pair<>(new Double[]{valueOf}, new Double[]{Double.valueOf(SQRT_PI)});
        }
        int lastNumPoints = i - 1;
        Double[] previousPoints = getRuleInternal(lastNumPoints).getFirst();
        Double[] points = new Double[i];
        Double[] weights = new Double[i];
        double sqrtTwoTimesLastNumPoints = FastMath.sqrt(lastNumPoints * 2);
        double sqrtTwoTimesNumPoints = FastMath.sqrt(i * 2);
        int iMax = i / 2;
        int i2 = 0;
        while (i2 < iMax) {
            double a = i2 == 0 ? -sqrtTwoTimesLastNumPoints : previousPoints[i2 - 1].doubleValue();
            double b = iMax == 1 ? -0.5d : previousPoints[i2].doubleValue();
            double hma = H0;
            double ha = a * H1;
            double hmb = H0;
            double hb = b * H1;
            int j = 1;
            while (j < i) {
                int i3 = i2;
                int i4 = j + 1;
                Double d = valueOf;
                double a2 = a;
                double jp1 = i4;
                double s = FastMath.sqrt(2.0d / jp1);
                double sm = FastMath.sqrt(j / jp1);
                double hpa = ((s * a2) * ha) - (sm * hma);
                double hpb = ((s * b) * hb) - (sm * hmb);
                hma = ha;
                ha = hpa;
                hmb = hb;
                hb = hpb;
                j++;
                i2 = i3;
                valueOf = d;
                a = a2;
            }
            int i5 = i2;
            Double d2 = valueOf;
            double a3 = a;
            double d3 = 0.5d;
            double c = (a3 + b) * 0.5d;
            double hmc = H0;
            double d4 = c * H1;
            boolean done = false;
            while (!done) {
                boolean done2 = b - a3 <= Math.ulp(c);
                hmc = H0;
                double hc = c * H1;
                double d5 = d3;
                int j2 = 1;
                while (j2 < i) {
                    double jp12 = j2 + 1;
                    double hpc = ((FastMath.sqrt(2.0d / jp12) * c) * hc) - (FastMath.sqrt(j2 / jp12) * hmc);
                    hmc = hc;
                    hc = hpc;
                    j2++;
                    done2 = done2;
                }
                boolean done3 = done2;
                if (done3) {
                    d3 = d5;
                    done = done3;
                } else {
                    if (ha * hc < 0.0d) {
                        b = c;
                    } else {
                        ha = hc;
                        a3 = c;
                    }
                    c = (a3 + b) * d5;
                    d3 = d5;
                    done = done3;
                }
            }
            double d6 = sqrtTwoTimesNumPoints * hmc;
            double w = 2.0d / (d6 * d6);
            points[i5] = Double.valueOf(c);
            weights[i5] = Double.valueOf(w);
            int idx = lastNumPoints - i5;
            points[idx] = Double.valueOf(-c);
            weights[idx] = Double.valueOf(w);
            i2 = i5 + 1;
            valueOf = d2;
        }
        Double d7 = valueOf;
        int i6 = i % 2;
        if (i6 != 0) {
            double hm = H0;
            int j3 = 1;
            while (j3 < i) {
                hm = (-FastMath.sqrt(j3 / (j3 + 1))) * hm;
                j3 += 2;
                i = numberOfPoints;
            }
            double hm2 = sqrtTwoTimesNumPoints * hm;
            points[iMax] = d7;
            weights[iMax] = Double.valueOf(2.0d / (hm2 * hm2));
        }
        return new Pair<>(points, weights);
    }
}
