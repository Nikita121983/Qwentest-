package org.apache.commons.math3.ml.clustering;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
public class FuzzyKMeansClusterer<T extends Clusterable> extends Clusterer<T> {
    private static final double DEFAULT_EPSILON = 0.001d;
    private List<CentroidCluster<T>> clusters;
    private final double epsilon;
    private final double fuzziness;
    private final int k;
    private final int maxIterations;
    private double[][] membershipMatrix;
    private List<T> points;
    private final RandomGenerator random;

    public FuzzyKMeansClusterer(int k, double fuzziness) throws NumberIsTooSmallException {
        this(k, fuzziness, -1, new EuclideanDistance());
    }

    public FuzzyKMeansClusterer(int k, double fuzziness, int maxIterations, DistanceMeasure measure) throws NumberIsTooSmallException {
        this(k, fuzziness, maxIterations, measure, 0.001d, new JDKRandomGenerator());
    }

    public FuzzyKMeansClusterer(int k, double fuzziness, int maxIterations, DistanceMeasure measure, double epsilon, RandomGenerator random) throws NumberIsTooSmallException {
        super(measure);
        if (fuzziness <= 1.0d) {
            throw new NumberIsTooSmallException(Double.valueOf(fuzziness), Double.valueOf(1.0d), false);
        }
        this.k = k;
        this.fuzziness = fuzziness;
        this.maxIterations = maxIterations;
        this.epsilon = epsilon;
        this.random = random;
        this.membershipMatrix = null;
        this.points = null;
        this.clusters = null;
    }

    public int getK() {
        return this.k;
    }

    public double getFuzziness() {
        return this.fuzziness;
    }

    public int getMaxIterations() {
        return this.maxIterations;
    }

    public double getEpsilon() {
        return this.epsilon;
    }

    public RandomGenerator getRandomGenerator() {
        return this.random;
    }

    public RealMatrix getMembershipMatrix() {
        if (this.membershipMatrix == null) {
            throw new MathIllegalStateException();
        }
        return MatrixUtils.createRealMatrix(this.membershipMatrix);
    }

    public List<T> getDataPoints() {
        return this.points;
    }

    public List<CentroidCluster<T>> getClusters() {
        return this.clusters;
    }

    public double getObjectiveFunctionValue() {
        if (this.points == null || this.clusters == null) {
            throw new MathIllegalStateException();
        }
        int i = 0;
        double objFunction = 0.0d;
        for (T point : this.points) {
            int j = 0;
            for (CentroidCluster<T> cluster : this.clusters) {
                double dist = distance(point, cluster.getCenter());
                objFunction += dist * dist * FastMath.pow(this.membershipMatrix[i][j], this.fuzziness);
                j++;
                i = i;
            }
            i++;
        }
        return objFunction;
    }

    @Override // org.apache.commons.math3.ml.clustering.Clusterer
    public List<CentroidCluster<T>> cluster(Collection<T> dataPoints) throws MathIllegalArgumentException {
        MathUtils.checkNotNull(dataPoints);
        int size = dataPoints.size();
        if (size < this.k) {
            throw new NumberIsTooSmallException(Integer.valueOf(size), Integer.valueOf(this.k), false);
        }
        this.points = Collections.unmodifiableList(new ArrayList(dataPoints));
        this.clusters = new ArrayList();
        this.membershipMatrix = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, this.k);
        double[][] oldMatrix = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, this.k);
        if (size == 0) {
            return this.clusters;
        }
        initializeMembershipMatrix();
        int pointDimension = this.points.get(0).getPoint().length;
        for (int i = 0; i < this.k; i++) {
            this.clusters.add(new CentroidCluster<>(new DoublePoint(new double[pointDimension])));
        }
        int iteration = 0;
        int max = this.maxIterations < 0 ? Integer.MAX_VALUE : this.maxIterations;
        do {
            saveMembershipMatrix(oldMatrix);
            updateClusterCenters();
            updateMembershipMatrix();
            double difference = calculateMaxMembershipChange(oldMatrix);
            if (difference <= this.epsilon) {
                break;
            }
            iteration++;
        } while (iteration < max);
        return this.clusters;
    }

    private void updateClusterCenters() {
        int j;
        int j2 = 0;
        List<CentroidCluster<T>> newClusters = new ArrayList<>(this.k);
        Iterator i$ = this.clusters.iterator();
        while (i$.hasNext()) {
            CentroidCluster<T> cluster = i$.next();
            Clusterable center = cluster.getCenter();
            int i = 0;
            double[] arr = new double[center.getPoint().length];
            double sum = 0.0d;
            for (T point : this.points) {
                Iterator i$2 = i$;
                CentroidCluster<T> cluster2 = cluster;
                double u = FastMath.pow(this.membershipMatrix[i][j2], this.fuzziness);
                double[] pointArr = point.getPoint();
                int idx = 0;
                while (true) {
                    j = j2;
                    int j3 = arr.length;
                    if (idx < j3) {
                        arr[idx] = arr[idx] + (pointArr[idx] * u);
                        idx++;
                        j2 = j;
                    }
                }
                sum += u;
                i++;
                i$ = i$2;
                cluster = cluster2;
                j2 = j;
            }
            MathArrays.scaleInPlace(1.0d / sum, arr);
            newClusters.add(new CentroidCluster<>(new DoublePoint(arr)));
            j2++;
            i$ = i$;
        }
        this.clusters.clear();
        this.clusters = newClusters;
    }

    private void updateMembershipMatrix() {
        double maxMembership;
        double d;
        double d2;
        double membership;
        for (int i = 0; i < this.points.size(); i++) {
            T point = this.points.get(i);
            double maxMembership2 = Double.MIN_VALUE;
            int newCluster = -1;
            for (int j = 0; j < this.clusters.size(); j++) {
                double sum = 0.0d;
                double distA = FastMath.abs(distance(point, this.clusters.get(j).getCenter()));
                double distB = 0.0d;
                if (distA == 0.0d) {
                    maxMembership = maxMembership2;
                    d = 0.0d;
                    d2 = 1.0d;
                } else {
                    Iterator i$ = this.clusters.iterator();
                    while (true) {
                        if (!i$.hasNext()) {
                            maxMembership = maxMembership2;
                            d = distB;
                            d2 = 1.0d;
                            break;
                        }
                        CentroidCluster<T> c = i$.next();
                        d = distB;
                        double distB2 = FastMath.abs(distance(point, c.getCenter()));
                        if (distB2 != d) {
                            double maxMembership3 = maxMembership2;
                            double maxMembership4 = this.fuzziness;
                            sum += FastMath.pow(distA / distB2, 2.0d / (maxMembership4 - 1.0d));
                            distB = d;
                            maxMembership2 = maxMembership3;
                        } else {
                            sum = Double.POSITIVE_INFINITY;
                            maxMembership = maxMembership2;
                            d2 = 1.0d;
                            break;
                        }
                    }
                }
                if (sum == d) {
                    membership = 1.0d;
                } else if (sum == Double.POSITIVE_INFINITY) {
                    membership = 0.0d;
                } else {
                    membership = d2 / sum;
                }
                this.membershipMatrix[i][j] = membership;
                if (this.membershipMatrix[i][j] <= maxMembership) {
                    maxMembership2 = maxMembership;
                } else {
                    double maxMembership5 = this.membershipMatrix[i][j];
                    newCluster = j;
                    maxMembership2 = maxMembership5;
                }
            }
            this.clusters.get(newCluster).addPoint(point);
        }
    }

    private void initializeMembershipMatrix() {
        for (int i = 0; i < this.points.size(); i++) {
            for (int j = 0; j < this.k; j++) {
                this.membershipMatrix[i][j] = this.random.nextDouble();
            }
            this.membershipMatrix[i] = MathArrays.normalizeArray(this.membershipMatrix[i], 1.0d);
        }
    }

    private double calculateMaxMembershipChange(double[][] matrix) {
        double maxMembership = 0.0d;
        for (int i = 0; i < this.points.size(); i++) {
            for (int j = 0; j < this.clusters.size(); j++) {
                double v = FastMath.abs(this.membershipMatrix[i][j] - matrix[i][j]);
                maxMembership = FastMath.max(v, maxMembership);
            }
        }
        return maxMembership;
    }

    private void saveMembershipMatrix(double[][] matrix) {
        for (int i = 0; i < this.points.size(); i++) {
            System.arraycopy(this.membershipMatrix[i], 0, matrix[i], 0, this.clusters.size());
        }
    }
}
