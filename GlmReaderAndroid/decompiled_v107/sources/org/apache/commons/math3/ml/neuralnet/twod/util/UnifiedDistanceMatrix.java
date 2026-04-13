package org.apache.commons.math3.ml.neuralnet.twod.util;

import java.lang.reflect.Array;
import java.util.Collection;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.Network;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;

/* loaded from: classes10.dex */
public class UnifiedDistanceMatrix implements MapVisualization {
    private final DistanceMeasure distance;
    private final boolean individualDistances;

    public UnifiedDistanceMatrix(boolean individualDistances, DistanceMeasure distance) {
        this.individualDistances = individualDistances;
        this.distance = distance;
    }

    @Override // org.apache.commons.math3.ml.neuralnet.twod.util.MapVisualization
    public double[][] computeImage(NeuronSquareMesh2D map) {
        if (this.individualDistances) {
            return individualDistances(map);
        }
        return averageDistances(map);
    }

    private double[][] individualDistances(NeuronSquareMesh2D neuronSquareMesh2D) {
        int i;
        UnifiedDistanceMatrix unifiedDistanceMatrix = this;
        NeuronSquareMesh2D neuronSquareMesh2D2 = neuronSquareMesh2D;
        int numberOfRows = neuronSquareMesh2D2.getNumberOfRows();
        int numberOfColumns = neuronSquareMesh2D2.getNumberOfColumns();
        int i2 = 1;
        boolean z = false;
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, (numberOfRows * 2) + 1, (numberOfColumns * 2) + 1);
        for (int i3 = 0; i3 < numberOfRows; i3++) {
            int i4 = (i3 * 2) + i2;
            int i5 = 0;
            while (i5 < numberOfColumns) {
                int i6 = (i5 * 2) + i2;
                double[] features = neuronSquareMesh2D2.getNeuron(i3, i5).getFeatures();
                Neuron neuron = neuronSquareMesh2D2.getNeuron(i3, i5, NeuronSquareMesh2D.HorizontalDirection.RIGHT, NeuronSquareMesh2D.VerticalDirection.CENTER);
                if (neuron == null) {
                    i = i2;
                } else {
                    i = i2;
                    dArr[i4][i6 + 1] = unifiedDistanceMatrix.distance.compute(features, neuron.getFeatures());
                }
                Neuron neuron2 = neuronSquareMesh2D2.getNeuron(i3, i5, NeuronSquareMesh2D.HorizontalDirection.CENTER, NeuronSquareMesh2D.VerticalDirection.DOWN);
                if (neuron2 != null) {
                    dArr[i4 + 1][i6] = unifiedDistanceMatrix.distance.compute(features, neuron2.getFeatures());
                }
                i5++;
                i2 = i;
            }
        }
        int i7 = 0;
        while (i7 < numberOfRows) {
            int i8 = (i7 * 2) + 1;
            int i9 = 0;
            while (i9 < numberOfColumns) {
                int i10 = (i9 * 2) + 1;
                Neuron neuron3 = neuronSquareMesh2D2.getNeuron(i7, i9);
                Neuron neuron4 = neuronSquareMesh2D2.getNeuron(i7, i9, NeuronSquareMesh2D.HorizontalDirection.RIGHT, NeuronSquareMesh2D.VerticalDirection.CENTER);
                Neuron neuron5 = neuronSquareMesh2D2.getNeuron(i7, i9, NeuronSquareMesh2D.HorizontalDirection.CENTER, NeuronSquareMesh2D.VerticalDirection.DOWN);
                Neuron neuron6 = neuronSquareMesh2D2.getNeuron(i7, i9, NeuronSquareMesh2D.HorizontalDirection.RIGHT, NeuronSquareMesh2D.VerticalDirection.DOWN);
                boolean z2 = z;
                dArr[i8 + 1][i10 + 1] = ((neuron6 == null ? 0.0d : unifiedDistanceMatrix.distance.compute(neuron3.getFeatures(), neuron6.getFeatures())) + ((neuron4 == null || neuron5 == null) ? 0.0d : unifiedDistanceMatrix.distance.compute(neuron4.getFeatures(), neuron5.getFeatures()))) * 0.5d;
                i9++;
                unifiedDistanceMatrix = this;
                neuronSquareMesh2D2 = neuronSquareMesh2D;
                z = z2;
            }
            i7++;
            unifiedDistanceMatrix = this;
            neuronSquareMesh2D2 = neuronSquareMesh2D;
        }
        boolean z3 = z;
        int length = dArr.length - 1;
        dArr[z3 ? 1 : 0] = dArr[length];
        int length2 = dArr[z3 ? 1 : 0].length - 1;
        for (int i11 = 0; i11 < length; i11++) {
            dArr[i11][z3 ? 1 : 0] = dArr[i11][length2];
        }
        return dArr;
    }

    private double[][] averageDistances(NeuronSquareMesh2D map) {
        int numRows = map.getNumberOfRows();
        int numCols = map.getNumberOfColumns();
        double[][] uMatrix = (double[][]) Array.newInstance((Class<?>) Double.TYPE, numRows, numCols);
        Network net = map.getNetwork();
        for (int i = 0; i < numRows; i++) {
            int j = 0;
            while (j < numCols) {
                Neuron neuron = map.getNeuron(i, j);
                Collection<Neuron> neighbours = net.getNeighbours(neuron);
                double[] features = neuron.getFeatures();
                double d = 0.0d;
                int count = 0;
                for (Neuron n : neighbours) {
                    count++;
                    d += this.distance.compute(features, n.getFeatures());
                    numRows = numRows;
                    numCols = numCols;
                }
                uMatrix[i][j] = d / count;
                j++;
                numRows = numRows;
            }
        }
        return uMatrix;
    }
}
