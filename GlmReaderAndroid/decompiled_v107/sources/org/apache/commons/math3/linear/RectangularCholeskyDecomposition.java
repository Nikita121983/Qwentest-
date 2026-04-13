package org.apache.commons.math3.linear;

import java.lang.reflect.Array;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class RectangularCholeskyDecomposition {
    private int rank;
    private final RealMatrix root;

    public RectangularCholeskyDecomposition(RealMatrix matrix) throws NonPositiveDefiniteMatrixException {
        this(matrix, 0.0d);
    }

    public RectangularCholeskyDecomposition(RealMatrix matrix, double small) throws NonPositiveDefiniteMatrixException {
        double inverse2 = small;
        int order = matrix.getRowDimension();
        double[][] c = matrix.getData();
        double[][] b = (double[][]) Array.newInstance((Class<?>) Double.TYPE, order, order);
        int[] index = new int[order];
        for (int i = 0; i < order; i++) {
            index[i] = i;
        }
        int r = 0;
        boolean loop = true;
        while (loop) {
            int swapR = r;
            int swapR2 = swapR;
            for (int i2 = r + 1; i2 < order; i2++) {
                int ii = index[i2];
                int isr = index[swapR2];
                if (c[ii][ii] > c[isr][isr]) {
                    swapR2 = i2;
                }
            }
            if (swapR2 != r) {
                int tmpIndex = index[r];
                index[r] = index[swapR2];
                index[swapR2] = tmpIndex;
                double[] tmpRow = b[r];
                b[r] = b[swapR2];
                b[swapR2] = tmpRow;
            }
            int ir = index[r];
            if (c[ir][ir] <= inverse2) {
                if (r == 0) {
                    throw new NonPositiveDefiniteMatrixException(c[ir][ir], ir, small);
                }
                for (int i3 = r; i3 < order; i3++) {
                    if (c[index[i3]][index[i3]] < (-inverse2)) {
                        throw new NonPositiveDefiniteMatrixException(c[index[i3]][index[i3]], i3, inverse2);
                    }
                }
                loop = false;
            } else {
                double sqrt = FastMath.sqrt(c[ir][ir]);
                b[r][r] = sqrt;
                double inverse = 1.0d / sqrt;
                double inverse22 = 1.0d / c[ir][ir];
                int i4 = r + 1;
                while (i4 < order) {
                    int ii2 = index[i4];
                    double e = c[ii2][ir] * inverse;
                    b[i4][r] = e;
                    double[] dArr = c[ii2];
                    dArr[ii2] = dArr[ii2] - ((c[ii2][ir] * c[ii2][ir]) * inverse22);
                    double sqrt2 = sqrt;
                    for (int j = r + 1; j < i4; j++) {
                        int ij = index[j];
                        double f = c[ii2][ij] - (b[j][r] * e);
                        c[ii2][ij] = f;
                        c[ij][ii2] = f;
                    }
                    i4++;
                    sqrt = sqrt2;
                }
                r++;
                loop = r < order;
            }
            inverse2 = small;
        }
        this.rank = r;
        this.root = MatrixUtils.createRealMatrix(order, r);
        for (int i5 = 0; i5 < order; i5++) {
            for (int j2 = 0; j2 < r; j2++) {
                this.root.setEntry(index[i5], j2, b[i5][j2]);
            }
        }
    }

    public RealMatrix getRootMatrix() {
        return this.root;
    }

    public int getRank() {
        return this.rank;
    }
}
