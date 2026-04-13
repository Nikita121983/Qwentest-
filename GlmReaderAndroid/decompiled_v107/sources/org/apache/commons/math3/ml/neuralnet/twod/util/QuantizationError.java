package org.apache.commons.math3.ml.neuralnet.twod.util;

import java.lang.reflect.Array;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.MapUtils;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;
import org.apache.commons.math3.ml.neuralnet.twod.util.LocationFinder;

/* loaded from: classes10.dex */
public class QuantizationError implements MapDataVisualization {
    private final DistanceMeasure distance;

    public QuantizationError(DistanceMeasure distance) {
        this.distance = distance;
    }

    @Override // org.apache.commons.math3.ml.neuralnet.twod.util.MapDataVisualization
    public double[][] computeImage(NeuronSquareMesh2D map, Iterable<double[]> data) {
        QuantizationError quantizationError = this;
        int nR = map.getNumberOfRows();
        int nC = map.getNumberOfColumns();
        LocationFinder finder = new LocationFinder(map);
        int i = 1;
        int[][] hit = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, nR, nC);
        double[][] error = (double[][]) Array.newInstance((Class<?>) Double.TYPE, nR, nC);
        for (double[] sample : data) {
            Neuron best = MapUtils.findBest(sample, map, quantizationError.distance);
            LocationFinder.Location loc = finder.getLocation(best);
            int row = loc.getRow();
            int col = loc.getColumn();
            int[] iArr = hit[row];
            iArr[col] = iArr[col] + i;
            double[] dArr = error[row];
            dArr[col] = dArr[col] + quantizationError.distance.compute(sample, best.getFeatures());
            i = 1;
            quantizationError = this;
        }
        for (int r = 0; r < nR; r++) {
            for (int c = 0; c < nC; c++) {
                int count = hit[r][c];
                if (count != 0) {
                    double[] dArr2 = error[r];
                    dArr2[c] = dArr2[c] / count;
                }
            }
        }
        return error;
    }
}
