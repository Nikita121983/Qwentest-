package org.apache.commons.math3.ml.neuralnet.twod.util;

import java.lang.reflect.Array;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.MapUtils;
import org.apache.commons.math3.ml.neuralnet.Network;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;
import org.apache.commons.math3.ml.neuralnet.twod.util.LocationFinder;
import org.apache.commons.math3.util.Pair;

/* loaded from: classes10.dex */
public class TopographicErrorHistogram implements MapDataVisualization {
    private final DistanceMeasure distance;
    private final boolean relativeCount;

    public TopographicErrorHistogram(boolean relativeCount, DistanceMeasure distance) {
        this.relativeCount = relativeCount;
        this.distance = distance;
    }

    @Override // org.apache.commons.math3.ml.neuralnet.twod.util.MapDataVisualization
    public double[][] computeImage(NeuronSquareMesh2D map, Iterable<double[]> data) {
        NeuronSquareMesh2D neuronSquareMesh2D = map;
        int nR = neuronSquareMesh2D.getNumberOfRows();
        int nC = neuronSquareMesh2D.getNumberOfColumns();
        Network net = neuronSquareMesh2D.getNetwork();
        LocationFinder finder = new LocationFinder(neuronSquareMesh2D);
        int[][] hit = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, nR, nC);
        double[][] error = (double[][]) Array.newInstance((Class<?>) Double.TYPE, nR, nC);
        for (double[] sample : data) {
            Pair<Neuron, Neuron> p = MapUtils.findBestAndSecondBest(sample, neuronSquareMesh2D, this.distance);
            Neuron best = p.getFirst();
            LocationFinder.Location loc = finder.getLocation(best);
            int row = loc.getRow();
            int col = loc.getColumn();
            int[] iArr = hit[row];
            iArr[col] = iArr[col] + 1;
            if (!net.getNeighbours(best).contains(p.getSecond())) {
                double[] dArr = error[row];
                dArr[col] = dArr[col] + 1.0d;
            }
            neuronSquareMesh2D = map;
        }
        if (this.relativeCount) {
            for (int r = 0; r < nR; r++) {
                for (int c = 0; c < nC; c++) {
                    double[] dArr2 = error[r];
                    dArr2[c] = dArr2[c] / hit[r][c];
                }
            }
        }
        return error;
    }
}
