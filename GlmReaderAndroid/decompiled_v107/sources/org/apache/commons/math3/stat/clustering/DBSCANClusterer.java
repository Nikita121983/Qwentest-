package org.apache.commons.math3.stat.clustering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.clustering.Clusterable;
import org.apache.commons.math3.util.MathUtils;

@Deprecated
/* loaded from: classes10.dex */
public class DBSCANClusterer<T extends Clusterable<T>> {
    private final double eps;
    private final int minPts;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public enum PointStatus {
        NOISE,
        PART_OF_CLUSTER
    }

    public DBSCANClusterer(double eps, int minPts) throws NotPositiveException {
        if (eps < 0.0d) {
            throw new NotPositiveException(Double.valueOf(eps));
        }
        if (minPts < 0) {
            throw new NotPositiveException(Integer.valueOf(minPts));
        }
        this.eps = eps;
        this.minPts = minPts;
    }

    public double getEps() {
        return this.eps;
    }

    public int getMinPts() {
        return this.minPts;
    }

    public List<Cluster<T>> cluster(Collection<T> points) throws NullArgumentException {
        Collection<T> points2;
        MathUtils.checkNotNull(points);
        List<Cluster<T>> clusters = new ArrayList<>();
        HashMap hashMap = new HashMap();
        for (T point : points) {
            if (hashMap.get(point) == null) {
                List<T> neighbors = getNeighbors(point, points);
                if (neighbors.size() >= this.minPts) {
                    Cluster<T> cluster = new Cluster<>(null);
                    points2 = points;
                    clusters.add(expandCluster(cluster, point, neighbors, points2, hashMap));
                } else {
                    points2 = points;
                    hashMap.put(point, PointStatus.NOISE);
                }
                points = points2;
            }
        }
        return clusters;
    }

    private Cluster<T> expandCluster(Cluster<T> cluster, T point, List<T> neighbors, Collection<T> points, Map<Clusterable<T>, PointStatus> visited) {
        cluster.addPoint(point);
        visited.put(point, PointStatus.PART_OF_CLUSTER);
        List<T> seeds = new ArrayList<>(neighbors);
        for (int index = 0; index < seeds.size(); index++) {
            T t = seeds.get(index);
            PointStatus pStatus = visited.get(t);
            if (pStatus == null) {
                List<T> currentNeighbors = getNeighbors(t, points);
                if (currentNeighbors.size() >= this.minPts) {
                    seeds = merge(seeds, currentNeighbors);
                }
            }
            if (pStatus != PointStatus.PART_OF_CLUSTER) {
                visited.put(t, PointStatus.PART_OF_CLUSTER);
                cluster.addPoint(t);
            }
        }
        return cluster;
    }

    private List<T> getNeighbors(T point, Collection<T> points) {
        List<T> neighbors = new ArrayList<>();
        for (T neighbor : points) {
            if (point != neighbor && neighbor.distanceFrom(point) <= this.eps) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    private List<T> merge(List<T> one, List<T> two) {
        Set<T> oneSet = new HashSet<>(one);
        for (T item : two) {
            if (!oneSet.contains(item)) {
                one.add(item);
            }
        }
        return one;
    }
}
