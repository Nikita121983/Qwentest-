package org.apache.commons.math3.optimization.general;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.PointVectorValuePair;
import org.apache.commons.math3.optimization.SimpleVectorValueChecker;

@Deprecated
/* loaded from: classes10.dex */
public class GaussNewtonOptimizer extends AbstractLeastSquaresOptimizer {
    private final boolean useLU;

    @Deprecated
    public GaussNewtonOptimizer() {
        this(true);
    }

    public GaussNewtonOptimizer(ConvergenceChecker<PointVectorValuePair> checker) {
        this(true, checker);
    }

    @Deprecated
    public GaussNewtonOptimizer(boolean useLU) {
        this(useLU, new SimpleVectorValueChecker());
    }

    public GaussNewtonOptimizer(boolean useLU, ConvergenceChecker<PointVectorValuePair> checker) {
        super(checker);
        this.useLU = useLU;
    }

    @Override // org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateVectorOptimizer
    public PointVectorValuePair doOptimize() {
        int nR;
        DecompositionSolver solver;
        ConvergenceChecker<PointVectorValuePair> checker = getConvergenceChecker();
        if (checker == null) {
            throw new NullArgumentException();
        }
        double[] targetValues = getTarget();
        int nR2 = targetValues.length;
        RealMatrix weightMatrix = getWeight();
        double[] residualsWeights = new double[nR2];
        for (int i = 0; i < nR2; i++) {
            residualsWeights[i] = weightMatrix.getEntry(i, i);
        }
        double[] currentPoint = getStartPoint();
        int nC = currentPoint.length;
        PointVectorValuePair current = null;
        int iter = 0;
        boolean converged = false;
        while (!converged) {
            iter++;
            PointVectorValuePair previous = current;
            double[] currentObjective = computeObjectiveValue(currentPoint);
            double[] currentResiduals = computeResiduals(currentObjective);
            RealMatrix weightedJacobian = computeWeightedJacobian(currentPoint);
            PointVectorValuePair current2 = new PointVectorValuePair(currentPoint, currentObjective);
            double[] targetValues2 = targetValues;
            double[] targetValues3 = new double[nC];
            RealMatrix weightMatrix2 = weightMatrix;
            double[][] a = (double[][]) Array.newInstance((Class<?>) Double.TYPE, nC, nC);
            int k = 0;
            while (k < nR2) {
                double[] grad = weightedJacobian.getRow(k);
                double weight = residualsWeights[k];
                double residual = currentResiduals[k];
                double wr = weight * residual;
                int i2 = k;
                for (int i3 = 0; i3 < nC; i3++) {
                    targetValues3[i3] = targetValues3[i3] + (grad[i3] * wr);
                }
                int l = 0;
                while (l < nC) {
                    double[] ak = a[l];
                    double wgk = grad[l] * weight;
                    int k2 = l;
                    for (int k3 = 0; k3 < nC; k3++) {
                        ak[k3] = ak[k3] + (grad[k3] * wgk);
                    }
                    l = k2 + 1;
                }
                k = i2 + 1;
            }
            try {
                RealMatrix mA = new BlockRealMatrix(a);
                nR = nR2;
                try {
                    if (!this.useLU) {
                        solver = new QRDecomposition(mA).getSolver();
                    } else {
                        try {
                            solver = new LUDecomposition(mA).getSolver();
                        } catch (SingularMatrixException e) {
                            throw new ConvergenceException(LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM, new Object[0]);
                        }
                    }
                } catch (SingularMatrixException e2) {
                }
            } catch (SingularMatrixException e3) {
            }
            try {
                double[] dX = solver.solve(new ArrayRealVector(targetValues3, false)).toArray();
                for (int i4 = 0; i4 < nC; i4++) {
                    currentPoint[i4] = currentPoint[i4] + dX[i4];
                }
                if (previous != null) {
                    boolean converged2 = checker.converged(iter, previous, current2);
                    if (converged2) {
                        this.cost = computeCost(currentResiduals);
                        this.point = current2.getPoint();
                        return current2;
                    }
                    converged = converged2;
                }
                current = current2;
                targetValues = targetValues2;
                weightMatrix = weightMatrix2;
                nR2 = nR;
            } catch (SingularMatrixException e4) {
                throw new ConvergenceException(LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM, new Object[0]);
            }
        }
        throw new MathInternalError();
    }
}
