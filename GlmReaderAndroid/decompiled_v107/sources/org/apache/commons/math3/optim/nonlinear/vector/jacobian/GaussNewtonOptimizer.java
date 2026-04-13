package org.apache.commons.math3.optim.nonlinear.vector.jacobian;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.PointVectorValuePair;

@Deprecated
/* loaded from: classes10.dex */
public class GaussNewtonOptimizer extends AbstractLeastSquaresOptimizer {
    private final boolean useLU;

    public GaussNewtonOptimizer(ConvergenceChecker<PointVectorValuePair> checker) {
        this(true, checker);
    }

    public GaussNewtonOptimizer(boolean useLU, ConvergenceChecker<PointVectorValuePair> checker) {
        super(checker);
        this.useLU = useLU;
    }

    @Override // org.apache.commons.math3.optim.BaseOptimizer
    public PointVectorValuePair doOptimize() {
        int nR;
        RealMatrix weightMatrix;
        GaussNewtonOptimizer gaussNewtonOptimizer = this;
        gaussNewtonOptimizer.checkParameters();
        ConvergenceChecker<PointVectorValuePair> checker = gaussNewtonOptimizer.getConvergenceChecker();
        if (checker == null) {
            throw new NullArgumentException();
        }
        double[] targetValues = gaussNewtonOptimizer.getTarget();
        int nR2 = targetValues.length;
        RealMatrix weightMatrix2 = gaussNewtonOptimizer.getWeight();
        double[] residualsWeights = new double[nR2];
        for (int i = 0; i < nR2; i++) {
            residualsWeights[i] = weightMatrix2.getEntry(i, i);
        }
        double[] currentPoint = gaussNewtonOptimizer.getStartPoint();
        int nC = currentPoint.length;
        PointVectorValuePair current = null;
        boolean converged = false;
        while (!converged) {
            gaussNewtonOptimizer.incrementIterationCount();
            PointVectorValuePair previous = current;
            double[] currentObjective = gaussNewtonOptimizer.computeObjectiveValue(currentPoint);
            double[] currentResiduals = gaussNewtonOptimizer.computeResiduals(currentObjective);
            RealMatrix weightedJacobian = gaussNewtonOptimizer.computeWeightedJacobian(currentPoint);
            PointVectorValuePair current2 = new PointVectorValuePair(currentPoint, currentObjective);
            double[] b = new double[nC];
            double[] targetValues2 = targetValues;
            double[][] a = (double[][]) Array.newInstance((Class<?>) Double.TYPE, nC, nC);
            int k = 0;
            while (k < nR2) {
                double[] grad = weightedJacobian.getRow(k);
                double weight = residualsWeights[k];
                double residual = currentResiduals[k];
                double wr = weight * residual;
                int i2 = k;
                for (int i3 = 0; i3 < nC; i3++) {
                    b[i3] = b[i3] + (grad[i3] * wr);
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
            if (previous == null) {
                nR = nR2;
                weightMatrix = weightMatrix2;
            } else {
                boolean converged2 = checker.converged(gaussNewtonOptimizer.getIterations(), previous, current2);
                if (!converged2) {
                    nR = nR2;
                    weightMatrix = weightMatrix2;
                    converged = converged2;
                } else {
                    gaussNewtonOptimizer.setCost(gaussNewtonOptimizer.computeCost(currentResiduals));
                    return current2;
                }
            }
            try {
                RealMatrix mA = new BlockRealMatrix(a);
                DecompositionSolver solver = gaussNewtonOptimizer.useLU ? new LUDecomposition(mA).getSolver() : new QRDecomposition(mA).getSolver();
                double[] dX = solver.solve(new ArrayRealVector(b, false)).toArray();
                for (int i4 = 0; i4 < nC; i4++) {
                    currentPoint[i4] = currentPoint[i4] + dX[i4];
                }
                gaussNewtonOptimizer = this;
                current = current2;
                targetValues = targetValues2;
                nR2 = nR;
                weightMatrix2 = weightMatrix;
            } catch (SingularMatrixException e) {
                throw new ConvergenceException(LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM, new Object[0]);
            }
        }
        throw new MathInternalError();
    }

    private void checkParameters() {
        if (getLowerBound() != null || getUpperBound() != null) {
            throw new MathUnsupportedOperationException(LocalizedFormats.CONSTRAINT, new Object[0]);
        }
    }
}
