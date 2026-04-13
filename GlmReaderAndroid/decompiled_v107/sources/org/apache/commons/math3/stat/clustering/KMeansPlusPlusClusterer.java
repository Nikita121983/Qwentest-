package org.apache.commons.math3.stat.clustering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.clustering.Clusterable;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.util.MathUtils;

@Deprecated
/* loaded from: classes10.dex */
public class KMeansPlusPlusClusterer<T extends Clusterable<T>> {
    private final EmptyClusterStrategy emptyStrategy;
    private final Random random;

    /* loaded from: classes10.dex */
    public enum EmptyClusterStrategy {
        LARGEST_VARIANCE,
        LARGEST_POINTS_NUMBER,
        FARTHEST_POINT,
        ERROR
    }

    public KMeansPlusPlusClusterer(Random random) {
        this(random, EmptyClusterStrategy.LARGEST_VARIANCE);
    }

    public KMeansPlusPlusClusterer(Random random, EmptyClusterStrategy emptyStrategy) {
        this.random = random;
        this.emptyStrategy = emptyStrategy;
    }

    public List<Cluster<T>> cluster(Collection<T> points, int k, int numTrials, int maxIterationsPerTrial) throws MathIllegalArgumentException, ConvergenceException {
        List<Cluster<T>> best;
        double bestVarianceSum;
        List<Cluster<T>> best2 = null;
        double bestVarianceSum2 = Double.POSITIVE_INFINITY;
        for (int i = 0; i < numTrials; i++) {
            List<Cluster<T>> clusters = cluster(points, k, maxIterationsPerTrial);
            double varianceSum = 0.0d;
            for (Cluster<T> cluster : clusters) {
                if (cluster.getPoints().isEmpty()) {
                    best = best2;
                    bestVarianceSum = bestVarianceSum2;
                } else {
                    T center = cluster.getCenter();
                    Variance stat = new Variance();
                    for (T point : cluster.getPoints()) {
                        List<Cluster<T>> best3 = best2;
                        double bestVarianceSum3 = bestVarianceSum2;
                        double bestVarianceSum4 = point.distanceFrom(center);
                        stat.increment(bestVarianceSum4);
                        best2 = best3;
                        bestVarianceSum2 = bestVarianceSum3;
                    }
                    best = best2;
                    bestVarianceSum = bestVarianceSum2;
                    varianceSum += stat.getResult();
                }
                best2 = best;
                bestVarianceSum2 = bestVarianceSum;
            }
            List<Cluster<T>> best4 = best2;
            double bestVarianceSum5 = bestVarianceSum2;
            if (varianceSum > bestVarianceSum5) {
                best2 = best4;
                bestVarianceSum2 = bestVarianceSum5;
            } else {
                best2 = clusters;
                bestVarianceSum2 = varianceSum;
            }
        }
        return best2;
    }

    public List<Cluster<T>> cluster(Collection<T> points, int k, int maxIterations) throws MathIllegalArgumentException, ConvergenceException {
        Clusterable clusterable;
        MathUtils.checkNotNull(points);
        if (points.size() < k) {
            throw new NumberIsTooSmallException(Integer.valueOf(points.size()), Integer.valueOf(k), false);
        }
        List<Cluster<T>> clusters = chooseInitialCenters(points, k, this.random);
        int[] assignments = new int[points.size()];
        assignPointsToClusters(clusters, points, assignments);
        int max = maxIterations < 0 ? Integer.MAX_VALUE : maxIterations;
        for (int count = 0; count < max; count++) {
            boolean emptyCluster = false;
            List<Cluster<T>> newClusters = new ArrayList<>();
            for (Cluster<T> cluster : clusters) {
                if (cluster.getPoints().isEmpty()) {
                    switch (this.emptyStrategy) {
                        case LARGEST_VARIANCE:
                            clusterable = getPointFromLargestVarianceCluster(clusters);
                            break;
                        case LARGEST_POINTS_NUMBER:
                            clusterable = getPointFromLargestNumberCluster(clusters);
                            break;
                        case FARTHEST_POINT:
                            clusterable = getFarthestPoint(clusters);
                            break;
                        default:
                            throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
                    }
                    emptyCluster = true;
                } else {
                    T newCenter = cluster.getCenter();
                    clusterable = (Clusterable) newCenter.centroidOf(cluster.getPoints());
                }
                newClusters.add(new Cluster<>(clusterable));
            }
            int changes = assignPointsToClusters(newClusters, points, assignments);
            clusters = newClusters;
            if (changes == 0 && !emptyCluster) {
                return clusters;
            }
        }
        return clusters;
    }

    private static <T extends Clusterable<T>> int assignPointsToClusters(List<Cluster<T>> clusters, Collection<T> points, int[] assignments) {
        int assignedDifferently = 0;
        int pointIndex = 0;
        for (T p : points) {
            int clusterIndex = getNearestCluster(clusters, p);
            if (clusterIndex != assignments[pointIndex]) {
                assignedDifferently++;
            }
            Cluster<T> cluster = clusters.get(clusterIndex);
            cluster.addPoint(p);
            assignments[pointIndex] = clusterIndex;
            pointIndex++;
        }
        return assignedDifferently;
    }

    /* JADX WARN: Incorrect condition in loop: B:10:0x0049 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static <T extends org.apache.commons.math3.stat.clustering.Clusterable<T>> java.util.List<org.apache.commons.math3.stat.clustering.Cluster<T>> chooseInitialCenters(java.util.Collection<T> r26, int r27, java.util.Random r28) {
        /*
            r0 = r27
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = r26
            r1.<init>(r2)
            java.util.List r1 = java.util.Collections.unmodifiableList(r1)
            int r3 = r1.size()
            boolean[] r4 = new boolean[r3]
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r6 = r28
            int r7 = r6.nextInt(r3)
            java.lang.Object r8 = r1.get(r7)
            org.apache.commons.math3.stat.clustering.Clusterable r8 = (org.apache.commons.math3.stat.clustering.Clusterable) r8
            org.apache.commons.math3.stat.clustering.Cluster r9 = new org.apache.commons.math3.stat.clustering.Cluster
            r9.<init>(r8)
            r5.add(r9)
            r9 = 1
            r4[r7] = r9
            double[] r10 = new double[r3]
            r11 = 0
        L32:
            if (r11 >= r3) goto L45
            if (r11 == r7) goto L42
            java.lang.Object r12 = r1.get(r11)
            double r12 = r8.distanceFrom(r12)
            double r14 = r12 * r12
            r10[r11] = r14
        L42:
            int r11 = r11 + 1
            goto L32
        L45:
            int r11 = r5.size()
            if (r11 >= r0) goto Lcb
            r11 = 0
            r13 = 0
        L4e:
            if (r13 >= r3) goto L5a
            boolean r14 = r4[r13]
            if (r14 != 0) goto L57
            r14 = r10[r13]
            double r11 = r11 + r14
        L57:
            int r13 = r13 + 1
            goto L4e
        L5a:
            double r13 = r6.nextDouble()
            double r13 = r13 * r11
            r15 = -1
            r16 = 0
            r18 = 0
            r25 = r18
            r18 = r9
            r9 = r25
        L6a:
            if (r9 >= r3) goto L7d
            boolean r19 = r4[r9]
            if (r19 != 0) goto L7a
            r19 = r10[r9]
            double r16 = r16 + r19
            int r19 = (r16 > r13 ? 1 : (r16 == r13 ? 0 : -1))
            if (r19 < 0) goto L7a
            r15 = r9
            goto L7d
        L7a:
            int r9 = r9 + 1
            goto L6a
        L7d:
            r9 = -1
            if (r15 != r9) goto L8d
            int r9 = r3 + (-1)
        L82:
            if (r9 < 0) goto L8d
            boolean r19 = r4[r9]
            if (r19 != 0) goto L8a
            r15 = r9
            goto L8d
        L8a:
            int r9 = r9 + (-1)
            goto L82
        L8d:
            if (r15 < 0) goto Lcb
            java.lang.Object r9 = r1.get(r15)
            org.apache.commons.math3.stat.clustering.Clusterable r9 = (org.apache.commons.math3.stat.clustering.Clusterable) r9
            org.apache.commons.math3.stat.clustering.Cluster r2 = new org.apache.commons.math3.stat.clustering.Cluster
            r2.<init>(r9)
            r5.add(r2)
            r4[r15] = r18
            int r2 = r5.size()
            if (r2 >= r0) goto Lc3
            r2 = 0
        La6:
            if (r2 >= r3) goto Lc3
            boolean r19 = r4[r2]
            if (r19 != 0) goto Lbe
            java.lang.Object r0 = r1.get(r2)
            double r19 = r9.distanceFrom(r0)
            double r21 = r19 * r19
            r23 = r10[r2]
            int r0 = (r21 > r23 ? 1 : (r21 == r23 ? 0 : -1))
            if (r0 >= 0) goto Lbe
            r10[r2] = r21
        Lbe:
            int r2 = r2 + 1
            r0 = r27
            goto La6
        Lc3:
            r2 = r26
            r0 = r27
            r9 = r18
            goto L45
        Lcb:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.stat.clustering.KMeansPlusPlusClusterer.chooseInitialCenters(java.util.Collection, int, java.util.Random):java.util.List");
    }

    private T getPointFromLargestVarianceCluster(Collection<Cluster<T>> clusters) throws ConvergenceException {
        double maxVariance = Double.NEGATIVE_INFINITY;
        Cluster<T> selected = null;
        for (Cluster<T> cluster : clusters) {
            if (!cluster.getPoints().isEmpty()) {
                T center = cluster.getCenter();
                Variance stat = new Variance();
                for (T point : cluster.getPoints()) {
                    stat.increment(point.distanceFrom(center));
                }
                double variance = stat.getResult();
                if (variance > maxVariance) {
                    maxVariance = variance;
                    selected = cluster;
                }
            }
        }
        if (selected == null) {
            throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
        }
        List<T> selectedPoints = selected.getPoints();
        return selectedPoints.remove(this.random.nextInt(selectedPoints.size()));
    }

    private T getPointFromLargestNumberCluster(Collection<Cluster<T>> clusters) throws ConvergenceException {
        int maxNumber = 0;
        Cluster<T> selected = null;
        for (Cluster<T> cluster : clusters) {
            int number = cluster.getPoints().size();
            if (number > maxNumber) {
                maxNumber = number;
                selected = cluster;
            }
        }
        if (selected == null) {
            throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
        }
        List<T> selectedPoints = selected.getPoints();
        return selectedPoints.remove(this.random.nextInt(selectedPoints.size()));
    }

    private T getFarthestPoint(Collection<Cluster<T>> clusters) throws ConvergenceException {
        double maxDistance = Double.NEGATIVE_INFINITY;
        Cluster<T> selectedCluster = null;
        int selectedPoint = -1;
        for (Cluster<T> cluster : clusters) {
            T center = cluster.getCenter();
            List<T> points = cluster.getPoints();
            for (int i = 0; i < points.size(); i++) {
                double distance = points.get(i).distanceFrom(center);
                if (distance > maxDistance) {
                    maxDistance = distance;
                    selectedCluster = cluster;
                    selectedPoint = i;
                }
            }
        }
        if (selectedCluster == null) {
            throw new ConvergenceException(LocalizedFormats.EMPTY_CLUSTER_IN_K_MEANS, new Object[0]);
        }
        return selectedCluster.getPoints().remove(selectedPoint);
    }

    private static <T extends Clusterable<T>> int getNearestCluster(Collection<Cluster<T>> clusters, T point) {
        double minDistance = Double.MAX_VALUE;
        int clusterIndex = 0;
        int minCluster = 0;
        for (Cluster<T> c : clusters) {
            double distance = point.distanceFrom(c.getCenter());
            if (distance < minDistance) {
                minDistance = distance;
                minCluster = clusterIndex;
            }
            clusterIndex++;
        }
        return minCluster;
    }
}
