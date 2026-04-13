package org.apache.commons.math3.analysis.integration.gauss;

import java.math.BigDecimal;
import java.math.MathContext;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.Pair;
import org.apache.logging.log4j.message.StructuredDataId;

/* loaded from: classes10.dex */
public class LegendreHighPrecisionRuleFactory extends BaseRuleFactory<BigDecimal> {
    private final MathContext mContext;
    private final BigDecimal minusOne;
    private final BigDecimal oneHalf;
    private final BigDecimal two;

    public LegendreHighPrecisionRuleFactory() {
        this(MathContext.DECIMAL128);
    }

    public LegendreHighPrecisionRuleFactory(MathContext mContext) {
        this.mContext = mContext;
        this.two = new BigDecimal("2", mContext);
        this.minusOne = new BigDecimal(StructuredDataId.RESERVED, mContext);
        this.oneHalf = new BigDecimal("0.5", mContext);
    }

    @Override // org.apache.commons.math3.analysis.integration.gauss.BaseRuleFactory
    protected Pair<BigDecimal[], BigDecimal[]> computeRule(int numberOfPoints) throws DimensionMismatchException {
        int i = 1;
        if (numberOfPoints != 1) {
            BigDecimal[] previousPoints = getRuleInternal(numberOfPoints - 1).getFirst();
            BigDecimal[] points = new BigDecimal[numberOfPoints];
            BigDecimal[] weights = new BigDecimal[numberOfPoints];
            int iMax = numberOfPoints / 2;
            int i2 = 0;
            while (i2 < iMax) {
                BigDecimal a = i2 == 0 ? this.minusOne : previousPoints[i2 - 1];
                BigDecimal b = iMax == i ? BigDecimal.ONE : previousPoints[i2];
                BigDecimal pma = BigDecimal.ONE;
                BigDecimal pa = a;
                BigDecimal pmb = BigDecimal.ONE;
                BigDecimal pb = b;
                int j = 1;
                while (j < numberOfPoints) {
                    int i3 = i;
                    BigDecimal[] previousPoints2 = previousPoints;
                    BigDecimal b_two_j_p_1 = new BigDecimal((j * 2) + 1, this.mContext);
                    BigDecimal b_j = new BigDecimal(j, this.mContext);
                    int j2 = j;
                    int iMax2 = iMax;
                    BigDecimal b_j_p_1 = new BigDecimal(j2 + 1, this.mContext);
                    BigDecimal tmp1 = a.multiply(b_two_j_p_1, this.mContext);
                    BigDecimal tmp12 = pa.multiply(tmp1, this.mContext);
                    BigDecimal tmp2 = pma.multiply(b_j, this.mContext);
                    int i4 = i2;
                    BigDecimal ppa = tmp12.subtract(tmp2, this.mContext);
                    BigDecimal ppa2 = ppa.divide(b_j_p_1, this.mContext);
                    BigDecimal tmp13 = b.multiply(b_two_j_p_1, this.mContext);
                    BigDecimal tmp14 = pb.multiply(tmp13, this.mContext);
                    BigDecimal tmp22 = pmb.multiply(b_j, this.mContext);
                    BigDecimal ppb = tmp14.subtract(tmp22, this.mContext);
                    pma = pa;
                    pa = ppa2;
                    pmb = pb;
                    pb = ppb.divide(b_j_p_1, this.mContext);
                    j = j2 + 1;
                    i = i3;
                    previousPoints = previousPoints2;
                    iMax = iMax2;
                    i2 = i4;
                }
                int i5 = i;
                BigDecimal[] previousPoints3 = previousPoints;
                int iMax3 = iMax;
                int i6 = i2;
                BigDecimal c = a.add(b, this.mContext).multiply(this.oneHalf, this.mContext);
                BigDecimal pmc = BigDecimal.ONE;
                BigDecimal pc = c;
                int i7 = 0;
                while (i7 == 0) {
                    BigDecimal tmp15 = b.subtract(a, this.mContext);
                    BigDecimal a2 = a;
                    BigDecimal tmp23 = c.ulp().multiply(BigDecimal.TEN, this.mContext);
                    int i8 = tmp15.compareTo(tmp23) <= 0 ? i5 : 0;
                    BigDecimal pc2 = BigDecimal.ONE;
                    BigDecimal pmc2 = c;
                    int j3 = 1;
                    while (j3 < numberOfPoints) {
                        int i9 = i8;
                        BigDecimal b_two_j_p_12 = new BigDecimal((j3 * 2) + 1, this.mContext);
                        BigDecimal b_j2 = new BigDecimal(j3, this.mContext);
                        int j4 = j3;
                        BigDecimal b2 = b;
                        BigDecimal b_j_p_12 = new BigDecimal(j4 + 1, this.mContext);
                        BigDecimal tmp16 = c.multiply(b_two_j_p_12, this.mContext);
                        BigDecimal tmp17 = pmc2.multiply(tmp16, this.mContext);
                        BigDecimal tmp24 = pc2.multiply(b_j2, this.mContext);
                        BigDecimal ppc = tmp17.subtract(tmp24, this.mContext);
                        BigDecimal pc3 = pmc2;
                        pmc2 = ppc.divide(b_j_p_12, this.mContext);
                        pc2 = pc3;
                        tmp15 = tmp17;
                        i8 = i9;
                        j3 = j4 + 1;
                        tmp23 = tmp24;
                        b = b2;
                    }
                    BigDecimal pmc3 = pc2;
                    BigDecimal pc4 = pmc2;
                    int i10 = i8;
                    BigDecimal b3 = b;
                    if (i10 != 0) {
                        a = a2;
                        b = b3;
                    } else {
                        if (pa.signum() * pc4.signum() <= 0) {
                            b = c;
                            a = a2;
                        } else {
                            a = c;
                            pa = pc4;
                            b = b3;
                        }
                        c = a.add(b, this.mContext).multiply(this.oneHalf, this.mContext);
                    }
                    i7 = i10;
                    pmc = pmc3;
                    pc = pc4;
                }
                BigDecimal nP = new BigDecimal(numberOfPoints, this.mContext);
                BigDecimal tmp18 = pmc.subtract(c.multiply(pc, this.mContext), this.mContext);
                BigDecimal tmp19 = tmp18.multiply(nP).pow(2, this.mContext);
                BigDecimal tmp25 = c.pow(2, this.mContext);
                BigDecimal tmp26 = BigDecimal.ONE.subtract(tmp25, this.mContext).multiply(this.two, this.mContext).divide(tmp19, this.mContext);
                points[i6] = c;
                weights[i6] = tmp26;
                int idx = (numberOfPoints - i6) - 1;
                points[idx] = c.negate(this.mContext);
                weights[idx] = tmp26;
                i2 = i6 + 1;
                i = i5;
                previousPoints = previousPoints3;
                iMax = iMax3;
            }
            int iMax4 = iMax;
            if (numberOfPoints % 2 != 0) {
                BigDecimal pmc4 = BigDecimal.ONE;
                for (int j5 = 1; j5 < numberOfPoints; j5 += 2) {
                    BigDecimal b_j3 = new BigDecimal(j5, this.mContext);
                    BigDecimal b_j_p_13 = new BigDecimal(j5 + 1, this.mContext);
                    pmc4 = pmc4.multiply(b_j3, this.mContext).divide(b_j_p_13, this.mContext).negate(this.mContext);
                }
                BigDecimal nP2 = new BigDecimal(numberOfPoints, this.mContext);
                BigDecimal tmp110 = pmc4.multiply(nP2, this.mContext);
                BigDecimal tmp27 = this.two.divide(tmp110.pow(2, this.mContext), this.mContext);
                points[iMax4] = BigDecimal.ZERO;
                weights[iMax4] = tmp27;
            }
            return new Pair<>(points, weights);
        }
        return new Pair<>(new BigDecimal[]{BigDecimal.ZERO}, new BigDecimal[]{this.two});
    }
}
