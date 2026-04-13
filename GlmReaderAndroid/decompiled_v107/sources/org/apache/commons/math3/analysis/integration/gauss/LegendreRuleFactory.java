package org.apache.commons.math3.analysis.integration.gauss;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.Pair;

/* loaded from: classes10.dex */
public class LegendreRuleFactory extends BaseRuleFactory<Double> {
    @Override // org.apache.commons.math3.analysis.integration.gauss.BaseRuleFactory
    protected Pair<Double[], Double[]> computeRule(int numberOfPoints) throws DimensionMismatchException {
        double d = 2.0d;
        double d2 = 0.0d;
        Double valueOf = Double.valueOf(0.0d);
        int i = 1;
        if (numberOfPoints == 1) {
            return new Pair<>(new Double[]{valueOf}, new Double[]{Double.valueOf(2.0d)});
        }
        Double[] previousPoints = getRuleInternal(numberOfPoints - 1).getFirst();
        Double[] points = new Double[numberOfPoints];
        Double[] weights = new Double[numberOfPoints];
        int iMax = numberOfPoints / 2;
        int i2 = 0;
        while (i2 < iMax) {
            double a = i2 == 0 ? -1.0d : previousPoints[i2 - 1].doubleValue();
            double b = iMax == i ? 1.0d : previousPoints[i2].doubleValue();
            double pma = 1.0d;
            double pa = a;
            double pmb = 1.0d;
            double pb = b;
            double d3 = d;
            int j = 1;
            while (j < numberOfPoints) {
                int two_j_p_1 = (j * 2) + i;
                double d4 = d2;
                int j_p_1 = j + 1;
                double ppa = (((two_j_p_1 * a) * pa) - (j * pma)) / j_p_1;
                double ppb = (((two_j_p_1 * b) * pb) - (j * pmb)) / j_p_1;
                pma = pa;
                pa = ppa;
                pmb = pb;
                pb = ppb;
                j++;
                d2 = d4;
                i = i;
                valueOf = valueOf;
            }
            double d5 = d2;
            Double d6 = valueOf;
            int i3 = i;
            double d7 = 0.5d;
            double c = (a + b) * 0.5d;
            double pmc = 1.0d;
            double pc = c;
            int i4 = 0;
            while (i4 == 0) {
                i4 = b - a <= Math.ulp(c) ? i3 : 0;
                double pmc2 = 1.0d;
                pc = c;
                double d8 = d7;
                int j2 = 1;
                while (j2 < numberOfPoints) {
                    double ppc = (((((j2 * 2) + 1) * c) * pc) - (j2 * pmc2)) / (j2 + 1);
                    double pmc3 = pc;
                    pc = ppc;
                    j2++;
                    pmc2 = pmc3;
                }
                double pmc4 = pmc2;
                if (i4 != 0) {
                    d7 = d8;
                    pmc = pmc4;
                } else {
                    if (pa * pc <= d5) {
                        b = c;
                    } else {
                        pa = pc;
                        a = c;
                    }
                    c = (a + b) * d8;
                    d7 = d8;
                    pmc = pmc4;
                }
            }
            double d9 = numberOfPoints * (pmc - (c * pc));
            double w = ((1.0d - (c * c)) * d3) / (d9 * d9);
            points[i2] = Double.valueOf(c);
            weights[i2] = Double.valueOf(w);
            int idx = (numberOfPoints - i2) - 1;
            points[idx] = Double.valueOf(-c);
            weights[idx] = Double.valueOf(w);
            i2++;
            d = d3;
            d2 = d5;
            i = i3;
            valueOf = d6;
        }
        double d10 = d;
        Double d11 = valueOf;
        if (numberOfPoints % 2 != 0) {
            double pmc5 = 1.0d;
            for (int j3 = 1; j3 < numberOfPoints; j3 += 2) {
                pmc5 = ((-j3) * pmc5) / (j3 + 1);
            }
            double d12 = numberOfPoints * pmc5;
            points[iMax] = d11;
            weights[iMax] = Double.valueOf(d10 / (d12 * d12));
        }
        return new Pair<>(points, weights);
    }
}
