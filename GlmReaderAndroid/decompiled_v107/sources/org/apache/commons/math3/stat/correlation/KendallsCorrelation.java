package org.apache.commons.math3.stat.correlation;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Pair;

/* loaded from: classes10.dex */
public class KendallsCorrelation {
    private final RealMatrix correlationMatrix;

    public KendallsCorrelation() {
        this.correlationMatrix = null;
    }

    public KendallsCorrelation(double[][] data) {
        this(MatrixUtils.createRealMatrix(data));
    }

    public KendallsCorrelation(RealMatrix matrix) {
        this.correlationMatrix = computeCorrelationMatrix(matrix);
    }

    public RealMatrix getCorrelationMatrix() {
        return this.correlationMatrix;
    }

    public RealMatrix computeCorrelationMatrix(RealMatrix matrix) {
        int nVars = matrix.getColumnDimension();
        RealMatrix outMatrix = new BlockRealMatrix(nVars, nVars);
        for (int i = 0; i < nVars; i++) {
            for (int j = 0; j < i; j++) {
                double corr = correlation(matrix.getColumn(i), matrix.getColumn(j));
                outMatrix.setEntry(i, j, corr);
                outMatrix.setEntry(j, i, corr);
            }
            outMatrix.setEntry(i, i, 1.0d);
        }
        return outMatrix;
    }

    public RealMatrix computeCorrelationMatrix(double[][] matrix) {
        return computeCorrelationMatrix(new BlockRealMatrix(matrix));
    }

    public double correlation(double[] xArray, double[] yArray) throws DimensionMismatchException {
        int iEnd;
        int jEnd;
        if (xArray.length != yArray.length) {
            throw new DimensionMismatchException(xArray.length, yArray.length);
        }
        int n = xArray.length;
        long numPairs = sum(n - 1);
        Pair<Double, Double>[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair<>(Double.valueOf(xArray[i]), Double.valueOf(yArray[i]));
        }
        Arrays.sort(pairs, new Comparator<Pair<Double, Double>>() { // from class: org.apache.commons.math3.stat.correlation.KendallsCorrelation.1
            @Override // java.util.Comparator
            public int compare(Pair<Double, Double> pair1, Pair<Double, Double> pair2) {
                int compareFirst = pair1.getFirst().compareTo(pair2.getFirst());
                return compareFirst != 0 ? compareFirst : pair1.getSecond().compareTo(pair2.getSecond());
            }
        });
        long tiedXPairs = 0;
        long tiedXYPairs = 0;
        long consecutiveXTies = 1;
        long consecutiveXYTies = 1;
        Pair<Double, Double> prev = pairs[0];
        int i2 = 1;
        while (i2 < n) {
            Pair<Double, Double> curr = pairs[i2];
            long numPairs2 = numPairs;
            if (curr.getFirst().equals(prev.getFirst())) {
                consecutiveXTies++;
                if (curr.getSecond().equals(prev.getSecond())) {
                    consecutiveXYTies++;
                } else {
                    tiedXYPairs += sum(consecutiveXYTies - 1);
                    consecutiveXYTies = 1;
                }
            } else {
                long consecutiveXYTies2 = consecutiveXTies - 1;
                tiedXPairs += sum(consecutiveXYTies2);
                long consecutiveXTies2 = consecutiveXYTies - 1;
                tiedXYPairs += sum(consecutiveXTies2);
                consecutiveXYTies = 1;
                consecutiveXTies = 1;
            }
            prev = curr;
            i2++;
            numPairs = numPairs2;
        }
        long numPairs3 = numPairs;
        long numPairs4 = consecutiveXTies - 1;
        long tiedXPairs2 = tiedXPairs + sum(numPairs4);
        long tiedXYPairs2 = tiedXYPairs + sum(consecutiveXYTies - 1);
        Pair<Double, Double>[] pairsDestination = new Pair[n];
        int segmentSize = 1;
        long swaps = 0;
        while (segmentSize < n) {
            int offset = 0;
            while (offset < n) {
                int i3 = offset;
                int segmentSize2 = segmentSize;
                int iEnd2 = FastMath.min(i3 + segmentSize2, n);
                int offset2 = offset;
                int offset3 = iEnd2 + segmentSize2;
                int jEnd2 = FastMath.min(offset3, n);
                int copyLocation = offset2;
                Pair<Double, Double>[] pairs2 = pairs;
                int i4 = i3;
                Pair<Double, Double>[] pairsDestination2 = pairsDestination;
                int j = iEnd2;
                while (true) {
                    if (i4 < iEnd2 || j < jEnd2) {
                        if (i4 < iEnd2) {
                            if (j < jEnd2) {
                                iEnd = iEnd2;
                                jEnd = jEnd2;
                                if (pairs2[i4].getSecond().compareTo(pairs2[j].getSecond()) <= 0) {
                                    pairsDestination2[copyLocation] = pairs2[i4];
                                    i4++;
                                } else {
                                    pairsDestination2[copyLocation] = pairs2[j];
                                    j++;
                                    swaps += iEnd - i4;
                                }
                            } else {
                                iEnd = iEnd2;
                                jEnd = jEnd2;
                                pairsDestination2[copyLocation] = pairs2[i4];
                                i4++;
                            }
                        } else {
                            iEnd = iEnd2;
                            jEnd = jEnd2;
                            pairsDestination2[copyLocation] = pairs2[j];
                            j++;
                        }
                        copyLocation++;
                        iEnd2 = iEnd;
                        jEnd2 = jEnd;
                    }
                }
                offset = offset2 + (segmentSize2 * 2);
                pairs = pairs2;
                segmentSize = segmentSize2;
                pairsDestination = pairsDestination2;
            }
            Pair<Double, Double>[] pairs3 = pairs;
            pairs = pairsDestination;
            pairsDestination = pairs3;
            segmentSize <<= 1;
        }
        Pair<Double, Double>[] pairs4 = pairs;
        long consecutiveYTies = 1;
        Pair<Double, Double> prev2 = pairs4[0];
        long tiedYPairs = 0;
        int i5 = 1;
        while (i5 < n) {
            Pair<Double, Double> curr2 = pairs4[i5];
            int n2 = n;
            int i6 = i5;
            if (curr2.getSecond().equals(prev2.getSecond())) {
                consecutiveYTies++;
            } else {
                tiedYPairs += sum(consecutiveYTies - 1);
                consecutiveYTies = 1;
            }
            prev2 = curr2;
            i5 = i6 + 1;
            n = n2;
        }
        long concordantMinusDiscordant = (((numPairs3 - tiedXPairs2) - (tiedYPairs + sum(consecutiveYTies - 1))) + tiedXYPairs2) - (2 * swaps);
        long consecutiveYTies2 = numPairs3 - tiedXPairs2;
        double nonTiedPairsMultiplied = (numPairs3 - r27) * consecutiveYTies2;
        double nonTiedPairsMultiplied2 = concordantMinusDiscordant;
        return nonTiedPairsMultiplied2 / FastMath.sqrt(nonTiedPairsMultiplied);
    }

    private static long sum(long n) {
        return ((1 + n) * n) / 2;
    }
}
