package org.apache.commons.math3.special;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
public class BesselJ implements UnivariateFunction {
    private static final double ENMTEN = 8.9E-308d;
    private static final double ENSIG = 1.0E16d;
    private static final double ENTEN = 1.0E308d;
    private static final double[] FACT = {1.0d, 1.0d, 2.0d, 6.0d, 24.0d, 120.0d, 720.0d, 5040.0d, 40320.0d, 362880.0d, 3628800.0d, 3.99168E7d, 4.790016E8d, 6.2270208E9d, 8.71782912E10d, 1.307674368E12d, 2.0922789888E13d, 3.55687428096E14d, 6.402373705728E15d, 1.21645100408832E17d, 2.43290200817664E18d, 5.109094217170944E19d, 1.1240007277776077E21d, 2.585201673888498E22d, 6.204484017332394E23d};
    private static final double PI2 = 0.6366197723675814d;
    private static final double RTNSIG = 1.0E-4d;
    private static final double TOWPI1 = 6.28125d;
    private static final double TWOPI = 6.283185307179586d;
    private static final double TWOPI2 = 0.001935307179586477d;
    private static final double X_MAX = 10000.0d;
    private static final double X_MIN = 0.0d;
    private final double order;

    public BesselJ(double order) {
        this.order = order;
    }

    @Override // org.apache.commons.math3.analysis.UnivariateFunction
    public double value(double x) throws MathIllegalArgumentException, ConvergenceException {
        return value(this.order, x);
    }

    public static double value(double order, double x) throws MathIllegalArgumentException, ConvergenceException {
        int n = (int) order;
        double alpha = order - n;
        int nb = n + 1;
        BesselJResult res = rjBesl(x, alpha, nb);
        if (res.nVals >= nb) {
            return res.vals[n];
        }
        if (res.nVals < 0) {
            throw new MathIllegalArgumentException(LocalizedFormats.BESSEL_FUNCTION_BAD_ARGUMENT, Double.valueOf(order), Double.valueOf(x));
        }
        if (FastMath.abs(res.vals[res.nVals - 1]) >= 1.0E-100d) {
            throw new ConvergenceException(LocalizedFormats.BESSEL_FUNCTION_FAILED_CONVERGENCE, Double.valueOf(order), Double.valueOf(x));
        }
        return res.vals[n];
    }

    /* loaded from: classes10.dex */
    public static class BesselJResult {
        private final int nVals;
        private final double[] vals;

        public BesselJResult(double[] b, int n) {
            this.vals = MathArrays.copyOf(b, b.length);
            this.nVals = n;
        }

        public double[] getVals() {
            return MathArrays.copyOf(this.vals, this.vals.length);
        }

        public int getnVals() {
            return this.nVals;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:117:0x0327, code lost:
    
        if (r14 == false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0329, code lost:
    
        r1 = r1 + 1;
        r12 = r12 + 2.0d;
        r7 = r23;
        r23 = r25;
        r25 = ((r12 * r23) / r59) - r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x033b, code lost:
    
        if (r25 < r27) goto L201;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x033d, code lost:
    
        r1 = r1 + 1;
        r12 = r12 + 2.0d;
        r29 = 1.0d / r25;
        r0 = (r1 * 2) - ((r1 / 2) * 4);
        r39 = 0.0d;
        r0 = r1 / 2;
        r42 = (r0 - 1.0d) + r61;
        r35 = (r0 * 2.0d) + r61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x035f, code lost:
    
        if (r0 == 0) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x0361, code lost:
    
        r39 = ((r29 * r42) * r35) / r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0367, code lost:
    
        r3 = r1 - r63;
        r4 = 0;
        r9 = false;
        r10 = 1;
        r43 = r42;
        r1 = r1;
        r0 = r0;
        r41 = r39;
        r39 = r35;
        r34 = r29;
        r29 = r12;
        r12 = 0.0d;
        r7 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x037f, code lost:
    
        if (r10 > r3) goto L203;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x0381, code lost:
    
        r1 = r1 - 1;
        r29 = r29 - 2.0d;
        r45 = r12;
        r12 = r34;
        r34 = ((r29 * r12) / r59) - r45;
        r0 = 2 - r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0391, code lost:
    
        if (r0 == 0) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0393, code lost:
    
        r7 = r7 - 1.0d;
        r39 = (r7 * 2.0d) + r61;
        r36 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x039c, code lost:
    
        if (r1 != 1) goto L119;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x03a1, code lost:
    
        r47 = (r7 - 1.0d) + r61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x03a7, code lost:
    
        if (r47 != 0.0d) goto L122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x03a9, code lost:
    
        r47 = 1.0d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x03ab, code lost:
    
        r41 = ((r41 + (r34 * r39)) * r47) / r7;
        r43 = r47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x03ba, code lost:
    
        r10 = r10 + 1;
        r0 = r36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x039e, code lost:
    
        r0 = r36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x03bf, code lost:
    
        r10 = r1 - 1;
        r5[r10] = r34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x03c3, code lost:
    
        if (r3 < 0) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x03c6, code lost:
    
        if (r63 > 1) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x03c8, code lost:
    
        r39 = r61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x03ce, code lost:
    
        if ((r61 + 1.0d) != 1.0d) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x03d0, code lost:
    
        r39 = 1.0d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x03d2, code lost:
    
        r41 = r41 + (r5[0] * r39);
        r4 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x03da, code lost:
    
        r1 = r1 - 1;
        r29 = r29 - 2.0d;
        r5[r1 - 1] = ((r29 * r34) / r59) - r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x03e9, code lost:
    
        if (r1 != 1) goto L136;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x03eb, code lost:
    
        r9 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x03ed, code lost:
    
        r0 = 2 - r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x03ef, code lost:
    
        if (r0 == 0) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x03f1, code lost:
    
        r7 = r7 - 1.0d;
        r39 = (r7 * 2.0d) + r61;
        r45 = (r7 - 1.0d) + r61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x03fd, code lost:
    
        if (r45 != 0.0d) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x03ff, code lost:
    
        r43 = 1.0d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x0404, code lost:
    
        r41 = ((r41 + (r5[r1 - 1] * r39)) * r43) / r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0402, code lost:
    
        r43 = r45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0410, code lost:
    
        if (r4 != 0) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0412, code lost:
    
        if (r9 != false) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0414, code lost:
    
        r3 = r1 - 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0416, code lost:
    
        if (r3 == 0) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x0418, code lost:
    
        r10 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0419, code lost:
    
        if (r10 > r3) goto L206;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x041b, code lost:
    
        r1 = r1 - 1;
        r29 = r29 - 2.0d;
        r5[r1 - 1] = ((r5[r1] * r29) / r59) - r5[r1 + 1];
        r0 = 2 - r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x0431, code lost:
    
        if (r0 == 0) goto L208;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x0433, code lost:
    
        r7 = r7 - 1.0d;
        r39 = (r7 * 2.0d) + r61;
        r45 = (r7 - 1.0d) + r61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x043f, code lost:
    
        if (r45 != 0.0d) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x0441, code lost:
    
        r45 = 1.0d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x0443, code lost:
    
        r41 = ((r41 + (r5[r1 - 1] * r39)) * r45) / r7;
        r43 = r45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x0451, code lost:
    
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x0454, code lost:
    
        if (r4 != 0) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x0456, code lost:
    
        if (r9 != false) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x0458, code lost:
    
        r5[0] = ((((r61 + 1.0d) * 2.0d) * r5[1]) / r59) - r5[2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x046c, code lost:
    
        r7 = r7 - 1.0d;
        r31 = (2.0d * r7) + r61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x0474, code lost:
    
        if (r31 != 0.0d) goto L162;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x0476, code lost:
    
        r39 = 1.0d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x047d, code lost:
    
        r41 = r41 + (r5[0] * r39);
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x047b, code lost:
    
        r39 = r31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x048e, code lost:
    
        if (org.apache.commons.math3.util.FastMath.abs(r61) <= 1.0E-16d) goto L167;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x0490, code lost:
    
        r37 = r6;
        r41 = r41 * (org.apache.commons.math3.special.Gamma.gamma(r61) * org.apache.commons.math3.util.FastMath.pow(r59 * 0.5d, -r61));
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x04ba, code lost:
    
        r0 = org.apache.commons.math3.special.BesselJ.ENMTEN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x04c1, code lost:
    
        if (r41 <= 1.0d) goto L171;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x04c3, code lost:
    
        r0 = org.apache.commons.math3.special.BesselJ.ENMTEN * r41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x04c5, code lost:
    
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x04c6, code lost:
    
        if (r6 >= r63) goto L209;
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x04d0, code lost:
    
        if (org.apache.commons.math3.util.FastMath.abs(r5[r6]) >= r0) goto L211;
     */
    /* JADX WARN: Code restructure failed: missing block: B:191:0x04d2, code lost:
    
        r5[r6] = 0.0d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x04d4, code lost:
    
        r5[r6] = r5[r6] / r41;
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x04dd, code lost:
    
        r6 = r37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x04ad, code lost:
    
        r37 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x03b8, code lost:
    
        r36 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.apache.commons.math3.special.BesselJ.BesselJResult rjBesl(double r59, double r61, int r63) {
        /*
            Method dump skipped, instructions count: 1287
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.special.BesselJ.rjBesl(double, double, int):org.apache.commons.math3.special.BesselJ$BesselJResult");
    }
}
