package org.apache.commons.math3.ml.clustering.evaluation;

import java.util.List;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.distance.EuclideanDistance;

/* loaded from: classes10.dex */
public abstract class ClusterEvaluator<T extends Clusterable> {
    private final DistanceMeasure measure;

    public abstract double score(List<? extends Cluster<T>> list);

    public ClusterEvaluator() {
        this(new EuclideanDistance());
    }

    public ClusterEvaluator(DistanceMeasure measure) {
        this.measure = measure;
    }

    public boolean isBetterScore(double score1, double score2) {
        return score1 < score2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double distance(Clusterable p1, Clusterable p2) {
        return this.measure.compute(p1.getPoint(), p2.getPoint());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Clusterable centroidOf(Cluster<T> cluster) {
        List<T> points = cluster.getPoints();
        if (points.isEmpty()) {
            return null;
        }
        if (cluster instanceof CentroidCluster) {
            return ((CentroidCluster) cluster).getCenter();
        }
        int dimension = points.get(0).getPoint().length;
        double[] centroid = new double[dimension];
        for (T p : points) {
            double[] point = p.getPoint();
            for (int i = 0; i < centroid.length; i++) {
                centroid[i] = centroid[i] + point[i];
            }
        }
        for (int i2 = 0; i2 < centroid.length; i2++) {
            centroid[i2] = centroid[i2] / points.size();
        }
        return new DoublePoint(centroid);
    }
}
