package org.apache.commons.math3.ml.neuralnet.twod.util;

import java.lang.reflect.Array;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.MapUtils;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;
import org.apache.commons.math3.ml.neuralnet.twod.util.LocationFinder;

/* loaded from: classes10.dex */
public class HitHistogram implements MapDataVisualization {
    private final DistanceMeasure distance;
    private final boolean normalizeCount;

    public HitHistogram(boolean normalizeCount, DistanceMeasure distance) {
        this.normalizeCount = normalizeCount;
        this.distance = distance;
    }

    @Override // org.apache.commons.math3.ml.neuralnet.twod.util.MapDataVisualization
    public double[][] computeImage(NeuronSquareMesh2D map, Iterable<double[]> data) {
        int nR = map.getNumberOfRows();
        int nC = map.getNumberOfColumns();
        LocationFinder finder = new LocationFinder(map);
        int numSamples = 0;
        double[][] hit = (double[][]) Array.newInstance((Class<?>) Double.TYPE, nR, nC);
        for (double[] sample : data) {
            Neuron best = MapUtils.findBest(sample, map, this.distance);
            LocationFinder.Location loc = finder.getLocation(best);
            int row = loc.getRow();
            int col = loc.getColumn();
            double[] dArr = hit[row];
            dArr[col] = dArr[col] + 1.0d;
            numSamples++;
        }
        if (this.normalizeCount) {
            for (int r = 0; r < nR; r++) {
                for (int c = 0; c < nC; c++) {
                    double[] dArr2 = hit[r];
                    dArr2[c] = dArr2[c] / numSamples;
                }
            }
        }
        return hit;
    }
}
