package org.apache.commons.math3.analysis.differentiation;

import androidx.lifecycle.AtomicReference$$ExternalSyntheticBackportWithForwarding0;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
public class DSCompiler {
    private static AtomicReference<DSCompiler[][]> compilers = new AtomicReference<>(null);
    private final int[][][] compIndirection;
    private final int[][] derivativesIndirection;
    private final int[] lowerIndirection;
    private final int[][][] multIndirection;
    private final int order;
    private final int parameters;
    private final int[][] sizes;

    private DSCompiler(int parameters, int order, DSCompiler valueCompiler, DSCompiler derivativeCompiler) throws NumberIsTooLargeException {
        this.parameters = parameters;
        this.order = order;
        this.sizes = compileSizes(parameters, order, valueCompiler);
        this.derivativesIndirection = compileDerivativesIndirection(parameters, order, valueCompiler, derivativeCompiler);
        this.lowerIndirection = compileLowerIndirection(parameters, order, valueCompiler, derivativeCompiler);
        this.multIndirection = compileMultiplicationIndirection(parameters, order, valueCompiler, derivativeCompiler, this.lowerIndirection);
        this.compIndirection = compileCompositionIndirection(parameters, order, valueCompiler, derivativeCompiler, this.sizes, this.derivativesIndirection);
    }

    public static DSCompiler getCompiler(int parameters, int order) throws NumberIsTooLargeException {
        DSCompiler[][] cache = compilers.get();
        if (cache != null && cache.length > parameters && cache[parameters].length > order && cache[parameters][order] != null) {
            return cache[parameters][order];
        }
        int maxParameters = FastMath.max(parameters, cache == null ? 0 : cache.length);
        int maxOrder = FastMath.max(order, cache == null ? 0 : cache[0].length);
        DSCompiler[][] newCache = (DSCompiler[][]) Array.newInstance((Class<?>) DSCompiler.class, maxParameters + 1, maxOrder + 1);
        if (cache != null) {
            for (int i = 0; i < cache.length; i++) {
                System.arraycopy(cache[i], 0, newCache[i], 0, cache[i].length);
            }
        }
        for (int diag = 0; diag <= parameters + order; diag++) {
            int o = FastMath.max(0, diag - parameters);
            while (o <= FastMath.min(order, diag)) {
                int p = diag - o;
                if (newCache[p][o] == null) {
                    DSCompiler valueCompiler = p == 0 ? null : newCache[p - 1][o];
                    DSCompiler derivativeCompiler = o != 0 ? newCache[p][o - 1] : null;
                    newCache[p][o] = new DSCompiler(p, o, valueCompiler, derivativeCompiler);
                }
                o++;
            }
        }
        AtomicReference$$ExternalSyntheticBackportWithForwarding0.m(compilers, cache, newCache);
        return newCache[parameters][order];
    }

    private static int[][] compileSizes(int parameters, int order, DSCompiler valueCompiler) {
        int[][] sizes = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, parameters + 1, order + 1);
        if (parameters == 0) {
            Arrays.fill(sizes[0], 1);
        } else {
            System.arraycopy(valueCompiler.sizes, 0, sizes, 0, parameters);
            sizes[parameters][0] = 1;
            for (int i = 0; i < order; i++) {
                sizes[parameters][i + 1] = sizes[parameters][i] + sizes[parameters - 1][i + 1];
            }
        }
        return sizes;
    }

    private static int[][] compileDerivativesIndirection(int parameters, int order, DSCompiler valueCompiler, DSCompiler derivativeCompiler) {
        if (parameters == 0 || order == 0) {
            return (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 1, parameters);
        }
        int vSize = valueCompiler.derivativesIndirection.length;
        int dSize = derivativeCompiler.derivativesIndirection.length;
        int[][] derivativesIndirection = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, vSize + dSize, parameters);
        for (int i = 0; i < vSize; i++) {
            System.arraycopy(valueCompiler.derivativesIndirection[i], 0, derivativesIndirection[i], 0, parameters - 1);
        }
        for (int i2 = 0; i2 < dSize; i2++) {
            System.arraycopy(derivativeCompiler.derivativesIndirection[i2], 0, derivativesIndirection[vSize + i2], 0, parameters);
            int[] iArr = derivativesIndirection[vSize + i2];
            int i3 = parameters - 1;
            iArr[i3] = iArr[i3] + 1;
        }
        return derivativesIndirection;
    }

    private static int[] compileLowerIndirection(int parameters, int order, DSCompiler valueCompiler, DSCompiler derivativeCompiler) {
        if (parameters == 0 || order <= 1) {
            return new int[]{0};
        }
        int vSize = valueCompiler.lowerIndirection.length;
        int dSize = derivativeCompiler.lowerIndirection.length;
        int[] lowerIndirection = new int[vSize + dSize];
        System.arraycopy(valueCompiler.lowerIndirection, 0, lowerIndirection, 0, vSize);
        for (int i = 0; i < dSize; i++) {
            lowerIndirection[vSize + i] = valueCompiler.getSize() + derivativeCompiler.lowerIndirection[i];
        }
        return lowerIndirection;
    }

    private static int[][][] compileMultiplicationIndirection(int parameters, int order, DSCompiler valueCompiler, DSCompiler derivativeCompiler, int[] lowerIndirection) {
        int i;
        char c;
        char c2;
        char c3 = 1;
        if (parameters == 0) {
            i = 1;
        } else {
            if (order != 0) {
                int vSize = valueCompiler.multIndirection.length;
                int dSize = derivativeCompiler.multIndirection.length;
                int[][][] multIndirection = new int[vSize + dSize][];
                System.arraycopy(valueCompiler.multIndirection, 0, multIndirection, 0, vSize);
                int i2 = 0;
                while (i2 < dSize) {
                    int[][] dRow = derivativeCompiler.multIndirection[i2];
                    char c4 = 2;
                    List<int[]> row = new ArrayList<>(dRow.length * 2);
                    for (int j = 0; j < dRow.length; j++) {
                        row.add(new int[]{dRow[j][0], lowerIndirection[dRow[j][c3]], dRow[j][2] + vSize});
                        row.add(new int[]{dRow[j][0], dRow[j][c3] + vSize, lowerIndirection[dRow[j][2]]});
                    }
                    List<int[]> combined = new ArrayList<>(row.size());
                    int j2 = 0;
                    while (j2 < row.size()) {
                        int[] termJ = row.get(j2);
                        if (termJ[0] <= 0) {
                            c = c3;
                            c2 = c4;
                        } else {
                            int k = j2 + 1;
                            while (k < row.size()) {
                                int[] termK = row.get(k);
                                char c5 = c4;
                                char c6 = c3;
                                if (termJ[c3] == termK[c6] && termJ[c5] == termK[c5]) {
                                    termJ[0] = termJ[0] + termK[0];
                                    termK[0] = 0;
                                }
                                k++;
                                c4 = c5;
                                c3 = c6;
                            }
                            c = c3;
                            c2 = c4;
                            combined.add(termJ);
                        }
                        j2++;
                        c4 = c2;
                        c3 = c;
                    }
                    multIndirection[vSize + i2] = (int[][]) combined.toArray(new int[combined.size()]);
                    i2++;
                    c3 = c3;
                }
                return multIndirection;
            }
            i = 1;
        }
        return new int[][][]{new int[][]{new int[]{i, 0, 0}}};
    }

    private static int[][][] compileCompositionIndirection(int parameters, int order, DSCompiler valueCompiler, DSCompiler derivativeCompiler, int[][] sizes, int[][] derivativesIndirection) throws NumberIsTooLargeException {
        int i;
        int i2 = parameters;
        int i3 = order;
        DSCompiler dSCompiler = derivativeCompiler;
        int[][] iArr = sizes;
        int i4 = 0;
        if (i2 == 0) {
            i = 1;
        } else {
            if (i3 != 0) {
                int vSize = valueCompiler.compIndirection.length;
                int dSize = dSCompiler.compIndirection.length;
                int[][][] compIndirection = new int[vSize + dSize][];
                System.arraycopy(valueCompiler.compIndirection, 0, compIndirection, 0, vSize);
                int i5 = 0;
                while (i5 < dSize) {
                    List<int[]> row = new ArrayList<>();
                    int[][] arr$ = dSCompiler.compIndirection[i5];
                    int len$ = arr$.length;
                    int i$ = 0;
                    while (i$ < len$) {
                        int[] term = arr$[i$];
                        int[] derivedTermF = new int[term.length + 1];
                        derivedTermF[i4] = term[i4];
                        derivedTermF[1] = term[1] + 1;
                        int i6 = i4;
                        int[] orders = new int[i2];
                        orders[i2 - 1] = 1;
                        int len$2 = len$;
                        int len$3 = term.length;
                        derivedTermF[len$3] = getPartialDerivativeIndex(i2, i3, iArr, orders);
                        int j = 2;
                        while (j < term.length) {
                            int j2 = j;
                            derivedTermF[j2] = convertIndex(term[j2], parameters, dSCompiler.derivativesIndirection, parameters, i3, iArr);
                            j = j2 + 1;
                            i3 = order;
                            iArr = sizes;
                            term = term;
                            i$ = i$;
                        }
                        int i$2 = i$;
                        int[] term2 = term;
                        int j3 = derivedTermF.length;
                        int i7 = 2;
                        Arrays.sort(derivedTermF, 2, j3);
                        row.add(derivedTermF);
                        int l = 2;
                        while (l < term2.length) {
                            int[] derivedTermG = new int[term2.length];
                            derivedTermG[i6] = term2[i6];
                            derivedTermG[1] = term2[1];
                            int j4 = 2;
                            while (j4 < term2.length) {
                                int l2 = l;
                                int l3 = term2[j4];
                                int[] derivedTermG2 = derivedTermG;
                                int j5 = j4;
                                int[] derivedTermF2 = derivedTermF;
                                int[] term3 = term2;
                                int vSize2 = vSize;
                                int vSize3 = i7;
                                derivedTermG2[j5] = convertIndex(l3, parameters, dSCompiler.derivativesIndirection, parameters, order, sizes);
                                if (j5 == l2) {
                                    int i8 = i6;
                                    System.arraycopy(derivativesIndirection[derivedTermG2[j5]], i8, orders, i8, parameters);
                                    int i9 = parameters - 1;
                                    orders[i9] = orders[i9] + 1;
                                    derivedTermG2[j5] = getPartialDerivativeIndex(parameters, order, sizes, orders);
                                }
                                j4 = j5 + 1;
                                l = l2;
                                derivedTermG = derivedTermG2;
                                i7 = vSize3;
                                derivedTermF = derivedTermF2;
                                term2 = term3;
                                vSize = vSize2;
                                i6 = 0;
                                dSCompiler = derivativeCompiler;
                            }
                            int[] derivedTermG3 = derivedTermG;
                            int[] term4 = term2;
                            int vSize4 = vSize;
                            int l4 = l;
                            int vSize5 = i7;
                            int l5 = derivedTermG3.length;
                            Arrays.sort(derivedTermG3, vSize5, l5);
                            row.add(derivedTermG3);
                            l = l4 + 1;
                            dSCompiler = derivativeCompiler;
                            i7 = vSize5;
                            derivedTermF = derivedTermF;
                            term2 = term4;
                            vSize = vSize4;
                            i6 = 0;
                        }
                        i2 = parameters;
                        i3 = order;
                        iArr = sizes;
                        i$ = i$2 + 1;
                        dSCompiler = derivativeCompiler;
                        len$ = len$2;
                        i4 = 0;
                    }
                    int vSize6 = vSize;
                    List<int[]> combined = new ArrayList<>(row.size());
                    for (int j6 = 0; j6 < row.size(); j6++) {
                        int[] termJ = row.get(j6);
                        if (termJ[0] > 0) {
                            for (int k = j6 + 1; k < row.size(); k++) {
                                int[] termK = row.get(k);
                                boolean equals = termJ.length == termK.length;
                                for (int l6 = 1; equals && l6 < termJ.length; l6++) {
                                    equals &= termJ[l6] == termK[l6];
                                }
                                if (equals) {
                                    termJ[0] = termJ[0] + termK[0];
                                    termK[0] = 0;
                                }
                            }
                            combined.add(termJ);
                        }
                    }
                    compIndirection[vSize6 + i5] = (int[][]) combined.toArray(new int[combined.size()]);
                    i5++;
                    dSCompiler = derivativeCompiler;
                    vSize = vSize6;
                    i4 = 0;
                }
                return compIndirection;
            }
            i = 1;
        }
        return new int[][][]{new int[][]{new int[]{i, 0}}};
    }

    public int getPartialDerivativeIndex(int... orders) throws DimensionMismatchException, NumberIsTooLargeException {
        if (orders.length != getFreeParameters()) {
            throw new DimensionMismatchException(orders.length, getFreeParameters());
        }
        return getPartialDerivativeIndex(this.parameters, this.order, this.sizes, orders);
    }

    private static int getPartialDerivativeIndex(int parameters, int order, int[][] sizes, int... orders) throws NumberIsTooLargeException {
        int index = 0;
        int m = order;
        int ordersSum = 0;
        for (int i = parameters - 1; i >= 0; i--) {
            int derivativeOrder = orders[i];
            ordersSum += derivativeOrder;
            if (ordersSum > order) {
                throw new NumberIsTooLargeException(Integer.valueOf(ordersSum), Integer.valueOf(order), true);
            }
            while (true) {
                int derivativeOrder2 = derivativeOrder - 1;
                if (derivativeOrder > 0) {
                    index += sizes[i][m];
                    derivativeOrder = derivativeOrder2;
                    m--;
                }
            }
        }
        return index;
    }

    private static int convertIndex(int index, int srcP, int[][] srcDerivativesIndirection, int destP, int destO, int[][] destSizes) throws NumberIsTooLargeException {
        int[] orders = new int[destP];
        System.arraycopy(srcDerivativesIndirection[index], 0, orders, 0, FastMath.min(srcP, destP));
        return getPartialDerivativeIndex(destP, destO, destSizes, orders);
    }

    public int[] getPartialDerivativeOrders(int index) {
        return this.derivativesIndirection[index];
    }

    public int getFreeParameters() {
        return this.parameters;
    }

    public int getOrder() {
        return this.order;
    }

    public int getSize() {
        return this.sizes[this.parameters][this.order];
    }

    public void linearCombination(double a1, double[] c1, int offset1, double a2, double[] c2, int offset2, double[] result, int resultOffset) {
        for (int i = 0; i < getSize(); i++) {
            result[resultOffset + i] = MathArrays.linearCombination(a1, c1[offset1 + i], a2, c2[offset2 + i]);
        }
    }

    public void linearCombination(double a1, double[] c1, int offset1, double a2, double[] c2, int offset2, double a3, double[] c3, int offset3, double[] result, int resultOffset) {
        for (int i = 0; i < getSize(); i++) {
            result[resultOffset + i] = MathArrays.linearCombination(a1, c1[offset1 + i], a2, c2[offset2 + i], a3, c3[offset3 + i]);
        }
    }

    public void linearCombination(double a1, double[] c1, int offset1, double a2, double[] c2, int offset2, double a3, double[] c3, int offset3, double a4, double[] c4, int offset4, double[] result, int resultOffset) {
        for (int i = 0; i < getSize(); i++) {
            result[resultOffset + i] = MathArrays.linearCombination(a1, c1[offset1 + i], a2, c2[offset2 + i], a3, c3[offset3 + i], a4, c4[offset4 + i]);
        }
    }

    public void add(double[] lhs, int lhsOffset, double[] rhs, int rhsOffset, double[] result, int resultOffset) {
        for (int i = 0; i < getSize(); i++) {
            result[resultOffset + i] = lhs[lhsOffset + i] + rhs[rhsOffset + i];
        }
    }

    public void subtract(double[] lhs, int lhsOffset, double[] rhs, int rhsOffset, double[] result, int resultOffset) {
        for (int i = 0; i < getSize(); i++) {
            result[resultOffset + i] = lhs[lhsOffset + i] - rhs[rhsOffset + i];
        }
    }

    public void multiply(double[] lhs, int lhsOffset, double[] rhs, int rhsOffset, double[] result, int resultOffset) {
        for (int i = 0; i < this.multIndirection.length; i++) {
            int[][] mappingI = this.multIndirection[i];
            double r = 0.0d;
            for (int j = 0; j < mappingI.length; j++) {
                r += mappingI[j][0] * lhs[mappingI[j][1] + lhsOffset] * rhs[mappingI[j][2] + rhsOffset];
            }
            int j2 = resultOffset + i;
            result[j2] = r;
        }
    }

    public void divide(double[] lhs, int lhsOffset, double[] rhs, int rhsOffset, double[] result, int resultOffset) {
        double[] reciprocal = new double[getSize()];
        pow(rhs, lhsOffset, -1, reciprocal, 0);
        multiply(lhs, lhsOffset, reciprocal, 0, result, resultOffset);
    }

    public void remainder(double[] lhs, int lhsOffset, double[] rhs, int rhsOffset, double[] result, int resultOffset) {
        double rem = FastMath.IEEEremainder(lhs[lhsOffset], rhs[rhsOffset]);
        double k = FastMath.rint((lhs[lhsOffset] - rem) / rhs[rhsOffset]);
        result[resultOffset] = rem;
        for (int i = 1; i < getSize(); i++) {
            result[resultOffset + i] = lhs[lhsOffset + i] - (rhs[rhsOffset + i] * k);
        }
    }

    public void pow(double a, double[] operand, int operandOffset, double[] result, int resultOffset) {
        double[] function = new double[this.order + 1];
        if (a != 0.0d) {
            function[0] = FastMath.pow(a, operand[operandOffset]);
            double lnA = FastMath.log(a);
            for (int i = 1; i < function.length; i++) {
                function[i] = function[i - 1] * lnA;
            }
        } else if (operand[operandOffset] == 0.0d) {
            function[0] = 1.0d;
            double infinity = Double.POSITIVE_INFINITY;
            for (int i2 = 1; i2 < function.length; i2++) {
                infinity = -infinity;
                function[i2] = infinity;
            }
        } else if (operand[operandOffset] < 0.0d) {
            Arrays.fill(function, Double.NaN);
        }
        compose(operand, operandOffset, function, result, resultOffset);
    }

    public void pow(double[] operand, int operandOffset, double p, double[] result, int resultOffset) {
        double[] function = new double[this.order + 1];
        double xk = FastMath.pow(operand[operandOffset], p - this.order);
        double xk2 = xk;
        for (int i = this.order; i > 0; i--) {
            function[i] = xk2;
            xk2 *= operand[operandOffset];
        }
        function[0] = xk2;
        double coefficient = p;
        for (int i2 = 1; i2 <= this.order; i2++) {
            function[i2] = function[i2] * coefficient;
            coefficient *= p - i2;
        }
        compose(operand, operandOffset, function, result, resultOffset);
    }

    public void pow(double[] operand, int operandOffset, int n, double[] result, int resultOffset) {
        if (n == 0) {
            result[resultOffset] = 1.0d;
            Arrays.fill(result, resultOffset + 1, getSize() + resultOffset, 0.0d);
            return;
        }
        double[] function = new double[this.order + 1];
        if (n <= 0) {
            double inv = 1.0d / operand[operandOffset];
            double xk = FastMath.pow(inv, -n);
            for (int i = 0; i <= this.order; i++) {
                function[i] = xk;
                xk *= inv;
            }
        } else {
            int maxOrder = FastMath.min(this.order, n);
            double xk2 = FastMath.pow(operand[operandOffset], n - maxOrder);
            for (int i2 = maxOrder; i2 > 0; i2--) {
                function[i2] = xk2;
                xk2 *= operand[operandOffset];
            }
            function[0] = xk2;
        }
        double coefficient = n;
        for (int i3 = 1; i3 <= this.order; i3++) {
            function[i3] = function[i3] * coefficient;
            coefficient *= n - i3;
        }
        compose(operand, operandOffset, function, result, resultOffset);
    }

    public void pow(double[] x, int xOffset, double[] y, int yOffset, double[] result, int resultOffset) {
        double[] logX = new double[getSize()];
        log(x, xOffset, logX, 0);
        double[] yLogX = new double[getSize()];
        multiply(logX, 0, y, yOffset, yLogX, 0);
        exp(yLogX, 0, result, resultOffset);
    }

    public void rootN(double[] operand, int operandOffset, int n, double[] result, int resultOffset) {
        double xk;
        double[] function = new double[this.order + 1];
        if (n != 2) {
            if (n == 3) {
                function[0] = FastMath.cbrt(operand[operandOffset]);
                xk = 1.0d / ((function[0] * 3.0d) * function[0]);
            } else {
                double xk2 = operand[operandOffset];
                function[0] = FastMath.pow(xk2, 1.0d / n);
                xk = 1.0d / (n * FastMath.pow(function[0], n - 1));
            }
        } else {
            function[0] = FastMath.sqrt(operand[operandOffset]);
            xk = 0.5d / function[0];
        }
        double nReciprocal = 1.0d / n;
        double xReciprocal = 1.0d / operand[operandOffset];
        for (int i = 1; i <= this.order; i++) {
            function[i] = xk;
            xk *= (nReciprocal - i) * xReciprocal;
        }
        compose(operand, operandOffset, function, result, resultOffset);
    }

    public void exp(double[] operand, int operandOffset, double[] result, int resultOffset) {
        double[] function = new double[this.order + 1];
        Arrays.fill(function, FastMath.exp(operand[operandOffset]));
        compose(operand, operandOffset, function, result, resultOffset);
    }

    public void expm1(double[] operand, int operandOffset, double[] result, int resultOffset) {
        double[] function = new double[this.order + 1];
        function[0] = FastMath.expm1(operand[operandOffset]);
        Arrays.fill(function, 1, this.order + 1, FastMath.exp(operand[operandOffset]));
        compose(operand, operandOffset, function, result, resultOffset);
    }

    public void log(double[] operand, int operandOffset, double[] result, int resultOffset) {
        double[] function = new double[this.order + 1];
        function[0] = FastMath.log(operand[operandOffset]);
        if (this.order > 0) {
            double inv = 1.0d / operand[operandOffset];
            double xk = inv;
            for (int i = 1; i <= this.order; i++) {
                function[i] = xk;
                xk *= (-i) * inv;
            }
        }
        compose(operand, operandOffset, function, result, resultOffset);
    }

    public void log1p(double[] operand, int operandOffset, double[] result, int resultOffset) {
        double[] function = new double[this.order + 1];
        function[0] = FastMath.log1p(operand[operandOffset]);
        if (this.order > 0) {
            double inv = 1.0d / (operand[operandOffset] + 1.0d);
            double xk = inv;
            for (int i = 1; i <= this.order; i++) {
                function[i] = xk;
                xk *= (-i) * inv;
            }
        }
        compose(operand, operandOffset, function, result, resultOffset);
    }

    public void log10(double[] operand, int operandOffset, double[] result, int resultOffset) {
        double[] function = new double[this.order + 1];
        function[0] = FastMath.log10(operand[operandOffset]);
        if (this.order > 0) {
            double inv = 1.0d / operand[operandOffset];
            double xk = inv / FastMath.log(10.0d);
            for (int i = 1; i <= this.order; i++) {
                function[i] = xk;
                xk *= (-i) * inv;
            }
        }
        compose(operand, operandOffset, function, result, resultOffset);
    }

    public void cos(double[] operand, int operandOffset, double[] result, int resultOffset) {
        double[] function = new double[this.order + 1];
        function[0] = FastMath.cos(operand[operandOffset]);
        if (this.order > 0) {
            function[1] = -FastMath.sin(operand[operandOffset]);
            for (int i = 2; i <= this.order; i++) {
                function[i] = -function[i - 2];
            }
        }
        compose(operand, operandOffset, function, result, resultOffset);
    }

    public void sin(double[] operand, int operandOffset, double[] result, int resultOffset) {
        double[] function = new double[this.order + 1];
        function[0] = FastMath.sin(operand[operandOffset]);
        if (this.order > 0) {
            function[1] = FastMath.cos(operand[operandOffset]);
            for (int i = 2; i <= this.order; i++) {
                function[i] = -function[i - 2];
            }
        }
        compose(operand, operandOffset, function, result, resultOffset);
    }

    public void tan(double[] dArr, int i, double[] dArr2, int i2) {
        boolean z;
        boolean z2;
        double d;
        int i3;
        boolean z3 = true;
        double[] dArr3 = new double[this.order + 1];
        double tan = FastMath.tan(dArr[i]);
        boolean z4 = false;
        dArr3[0] = tan;
        if (this.order > 0) {
            int i4 = 2;
            double[] dArr4 = new double[this.order + 2];
            dArr4[1] = 1.0d;
            double d2 = tan * tan;
            int i5 = 1;
            while (i5 <= this.order) {
                double d3 = 0.0d;
                dArr4[i5 + 1] = i5 * dArr4[i5];
                int i6 = i5 + 1;
                while (i6 >= 0) {
                    d3 = (d3 * d2) + dArr4[i6];
                    if (i6 > i4) {
                        z = z4;
                        z2 = z3;
                        d = tan;
                        dArr4[i6 - 2] = ((i6 - 1) * dArr4[i6 - 1]) + ((i6 - 3) * dArr4[i6 - 3]);
                        i3 = 2;
                    } else {
                        z = z4;
                        z2 = z3;
                        d = tan;
                        i3 = 2;
                        if (i6 == 2) {
                            dArr4[z ? 1 : 0] = dArr4[z2 ? 1 : 0];
                        }
                    }
                    i6 -= 2;
                    i4 = i3;
                    z3 = z2;
                    z4 = z;
                    tan = d;
                }
                boolean z5 = z4;
                boolean z6 = z3;
                double d4 = tan;
                int i7 = i4;
                if ((i5 & 1) == 0) {
                    d3 *= d4;
                }
                dArr3[i5] = d3;
                i5++;
                i4 = i7;
                z3 = z6;
                z4 = z5;
                tan = d4;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void acos(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3;
        DSCompiler dSCompiler = this;
        boolean z = true;
        double[] dArr4 = new double[dSCompiler.order + 1];
        double d = dArr[i];
        boolean z2 = false;
        dArr4[0] = FastMath.acos(d);
        if (dSCompiler.order <= 0) {
            dArr3 = dArr4;
        } else {
            double[] dArr5 = new double[dSCompiler.order];
            dArr5[0] = -1.0d;
            double d2 = d * d;
            double d3 = 1.0d / (1.0d - d2);
            double sqrt = FastMath.sqrt(d3);
            dArr4[1] = dArr5[0] * sqrt;
            int i3 = 2;
            while (i3 <= dSCompiler.order) {
                double d4 = 0.0d;
                boolean z3 = z2;
                boolean z4 = z;
                double[] dArr6 = dArr4;
                dArr5[i3 - 1] = (i3 - 1) * dArr5[i3 - 2];
                for (int i4 = i3 - 1; i4 >= 0; i4 -= 2) {
                    d4 = (d4 * d2) + dArr5[i4];
                    if (i4 > 2) {
                        dArr5[i4 - 2] = ((i4 - 1) * dArr5[i4 - 1]) + (((i3 * 2) - i4) * dArr5[i4 - 3]);
                    } else if (i4 == 2) {
                        dArr5[z3 ? 1 : 0] = dArr5[z4 ? 1 : 0];
                    }
                }
                if ((i3 & 1) == 0) {
                    d4 *= d;
                }
                sqrt *= d3;
                dArr6[i3] = sqrt * d4;
                i3++;
                dSCompiler = this;
                z2 = z3 ? 1 : 0;
                z = z4 ? 1 : 0;
                dArr4 = dArr6;
            }
            dArr3 = dArr4;
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void asin(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3;
        DSCompiler dSCompiler = this;
        boolean z = true;
        double[] dArr4 = new double[dSCompiler.order + 1];
        double d = dArr[i];
        boolean z2 = false;
        dArr4[0] = FastMath.asin(d);
        if (dSCompiler.order <= 0) {
            dArr3 = dArr4;
        } else {
            double[] dArr5 = new double[dSCompiler.order];
            dArr5[0] = 1.0d;
            double d2 = d * d;
            double d3 = 1.0d / (1.0d - d2);
            double sqrt = FastMath.sqrt(d3);
            dArr4[1] = dArr5[0] * sqrt;
            int i3 = 2;
            while (i3 <= dSCompiler.order) {
                double d4 = 0.0d;
                boolean z3 = z2;
                boolean z4 = z;
                double[] dArr6 = dArr4;
                dArr5[i3 - 1] = (i3 - 1) * dArr5[i3 - 2];
                for (int i4 = i3 - 1; i4 >= 0; i4 -= 2) {
                    d4 = (d4 * d2) + dArr5[i4];
                    if (i4 > 2) {
                        dArr5[i4 - 2] = ((i4 - 1) * dArr5[i4 - 1]) + (((i3 * 2) - i4) * dArr5[i4 - 3]);
                    } else if (i4 == 2) {
                        dArr5[z3 ? 1 : 0] = dArr5[z4 ? 1 : 0];
                    }
                }
                if ((i3 & 1) == 0) {
                    d4 *= d;
                }
                sqrt *= d3;
                dArr6[i3] = sqrt * d4;
                i3++;
                dSCompiler = this;
                z2 = z3 ? 1 : 0;
                z = z4 ? 1 : 0;
                dArr4 = dArr6;
            }
            dArr3 = dArr4;
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void atan(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3;
        DSCompiler dSCompiler = this;
        boolean z = true;
        double[] dArr4 = new double[dSCompiler.order + 1];
        double d = dArr[i];
        boolean z2 = false;
        dArr4[0] = FastMath.atan(d);
        if (dSCompiler.order <= 0) {
            dArr3 = dArr4;
        } else {
            double[] dArr5 = new double[dSCompiler.order];
            dArr5[0] = 1.0d;
            double d2 = d * d;
            double d3 = 1.0d / (d2 + 1.0d);
            double d4 = d3;
            dArr4[1] = dArr5[0] * d4;
            int i3 = 2;
            while (i3 <= dSCompiler.order) {
                double d5 = 0.0d;
                boolean z3 = z2;
                boolean z4 = z;
                double[] dArr6 = dArr4;
                dArr5[i3 - 1] = (-i3) * dArr5[i3 - 2];
                for (int i4 = i3 - 1; i4 >= 0; i4 -= 2) {
                    d5 = (d5 * d2) + dArr5[i4];
                    if (i4 > 2) {
                        dArr5[i4 - 2] = ((i4 - 1) * dArr5[i4 - 1]) + (((i4 - 1) - (i3 * 2)) * dArr5[i4 - 3]);
                    } else if (i4 == 2) {
                        dArr5[z3 ? 1 : 0] = dArr5[z4 ? 1 : 0];
                    }
                }
                if ((i3 & 1) == 0) {
                    d5 *= d;
                }
                d4 *= d3;
                dArr6[i3] = d4 * d5;
                i3++;
                dSCompiler = this;
                z2 = z3 ? 1 : 0;
                z = z4 ? 1 : 0;
                dArr4 = dArr6;
            }
            dArr3 = dArr4;
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void atan2(double[] y, int yOffset, double[] x, int xOffset, double[] result, int resultOffset) {
        double[] tmp1 = new double[getSize()];
        multiply(x, xOffset, x, xOffset, tmp1, 0);
        double[] tmp2 = new double[getSize()];
        multiply(y, yOffset, y, yOffset, tmp2, 0);
        add(tmp1, 0, tmp2, 0, tmp2, 0);
        rootN(tmp2, 0, 2, tmp1, 0);
        if (x[xOffset] >= 0.0d) {
            add(tmp1, 0, x, xOffset, tmp2, 0);
            divide(y, yOffset, tmp2, 0, tmp1, 0);
            atan(tmp1, 0, tmp2, 0);
            for (int i = 0; i < tmp2.length; i++) {
                result[resultOffset + i] = tmp2[i] * 2.0d;
            }
        } else {
            subtract(tmp1, 0, x, xOffset, tmp2, 0);
            divide(y, yOffset, tmp2, 0, tmp1, 0);
            atan(tmp1, 0, tmp2, 0);
            result[resultOffset] = (tmp2[0] <= 0.0d ? -3.141592653589793d : 3.141592653589793d) - (tmp2[0] * 2.0d);
            for (int i2 = 1; i2 < tmp2.length; i2++) {
                result[resultOffset + i2] = tmp2[i2] * (-2.0d);
            }
        }
        result[resultOffset] = FastMath.atan2(y[yOffset], x[xOffset]);
    }

    public void cosh(double[] operand, int operandOffset, double[] result, int resultOffset) {
        double[] function = new double[this.order + 1];
        function[0] = FastMath.cosh(operand[operandOffset]);
        if (this.order > 0) {
            function[1] = FastMath.sinh(operand[operandOffset]);
            for (int i = 2; i <= this.order; i++) {
                function[i] = function[i - 2];
            }
        }
        compose(operand, operandOffset, function, result, resultOffset);
    }

    public void sinh(double[] operand, int operandOffset, double[] result, int resultOffset) {
        double[] function = new double[this.order + 1];
        function[0] = FastMath.sinh(operand[operandOffset]);
        if (this.order > 0) {
            function[1] = FastMath.cosh(operand[operandOffset]);
            for (int i = 2; i <= this.order; i++) {
                function[i] = function[i - 2];
            }
        }
        compose(operand, operandOffset, function, result, resultOffset);
    }

    public void tanh(double[] dArr, int i, double[] dArr2, int i2) {
        boolean z;
        boolean z2;
        double d;
        int i3;
        boolean z3 = true;
        double[] dArr3 = new double[this.order + 1];
        double tanh = FastMath.tanh(dArr[i]);
        boolean z4 = false;
        dArr3[0] = tanh;
        if (this.order > 0) {
            int i4 = 2;
            double[] dArr4 = new double[this.order + 2];
            dArr4[1] = 1.0d;
            double d2 = tanh * tanh;
            int i5 = 1;
            while (i5 <= this.order) {
                double d3 = 0.0d;
                dArr4[i5 + 1] = (-i5) * dArr4[i5];
                int i6 = i5 + 1;
                while (i6 >= 0) {
                    d3 = (d3 * d2) + dArr4[i6];
                    if (i6 > i4) {
                        z = z4;
                        z2 = z3;
                        d = tanh;
                        dArr4[i6 - 2] = ((i6 - 1) * dArr4[i6 - 1]) - ((i6 - 3) * dArr4[i6 - 3]);
                        i3 = 2;
                    } else {
                        z = z4;
                        z2 = z3;
                        d = tanh;
                        i3 = 2;
                        if (i6 == 2) {
                            dArr4[z ? 1 : 0] = dArr4[z2 ? 1 : 0];
                        }
                    }
                    i6 -= 2;
                    i4 = i3;
                    z3 = z2;
                    z4 = z;
                    tanh = d;
                }
                boolean z5 = z4;
                boolean z6 = z3;
                double d4 = tanh;
                int i7 = i4;
                if ((i5 & 1) == 0) {
                    d3 *= d4;
                }
                dArr3[i5] = d3;
                i5++;
                i4 = i7;
                z3 = z6;
                z4 = z5;
                tanh = d4;
            }
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void acosh(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3;
        DSCompiler dSCompiler = this;
        boolean z = true;
        double[] dArr4 = new double[dSCompiler.order + 1];
        double d = dArr[i];
        boolean z2 = false;
        dArr4[0] = FastMath.acosh(d);
        if (dSCompiler.order <= 0) {
            dArr3 = dArr4;
        } else {
            double[] dArr5 = new double[dSCompiler.order];
            dArr5[0] = 1.0d;
            double d2 = d * d;
            double d3 = 1.0d / (d2 - 1.0d);
            double sqrt = FastMath.sqrt(d3);
            dArr4[1] = dArr5[0] * sqrt;
            int i3 = 2;
            while (i3 <= dSCompiler.order) {
                double d4 = 0.0d;
                boolean z3 = z2;
                boolean z4 = z;
                double[] dArr6 = dArr4;
                dArr5[i3 - 1] = (1 - i3) * dArr5[i3 - 2];
                for (int i4 = i3 - 1; i4 >= 0; i4 -= 2) {
                    d4 = (d4 * d2) + dArr5[i4];
                    if (i4 > 2) {
                        dArr5[i4 - 2] = ((1 - i4) * dArr5[i4 - 1]) + ((i4 - (i3 * 2)) * dArr5[i4 - 3]);
                    } else if (i4 == 2) {
                        dArr5[z3 ? 1 : 0] = -dArr5[z4 ? 1 : 0];
                    }
                }
                if ((i3 & 1) == 0) {
                    d4 *= d;
                }
                sqrt *= d3;
                dArr6[i3] = sqrt * d4;
                i3++;
                dSCompiler = this;
                z2 = z3 ? 1 : 0;
                z = z4 ? 1 : 0;
                dArr4 = dArr6;
            }
            dArr3 = dArr4;
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void asinh(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3;
        DSCompiler dSCompiler = this;
        boolean z = true;
        double[] dArr4 = new double[dSCompiler.order + 1];
        double d = dArr[i];
        boolean z2 = false;
        dArr4[0] = FastMath.asinh(d);
        if (dSCompiler.order <= 0) {
            dArr3 = dArr4;
        } else {
            double[] dArr5 = new double[dSCompiler.order];
            dArr5[0] = 1.0d;
            double d2 = d * d;
            double d3 = 1.0d / (d2 + 1.0d);
            double sqrt = FastMath.sqrt(d3);
            dArr4[1] = dArr5[0] * sqrt;
            int i3 = 2;
            while (i3 <= dSCompiler.order) {
                double d4 = 0.0d;
                boolean z3 = z2;
                boolean z4 = z;
                double[] dArr6 = dArr4;
                dArr5[i3 - 1] = (1 - i3) * dArr5[i3 - 2];
                for (int i4 = i3 - 1; i4 >= 0; i4 -= 2) {
                    d4 = (d4 * d2) + dArr5[i4];
                    if (i4 > 2) {
                        dArr5[i4 - 2] = ((i4 - 1) * dArr5[i4 - 1]) + ((i4 - (i3 * 2)) * dArr5[i4 - 3]);
                    } else if (i4 == 2) {
                        dArr5[z3 ? 1 : 0] = dArr5[z4 ? 1 : 0];
                    }
                }
                if ((i3 & 1) == 0) {
                    d4 *= d;
                }
                sqrt *= d3;
                dArr6[i3] = sqrt * d4;
                i3++;
                dSCompiler = this;
                z2 = z3 ? 1 : 0;
                z = z4 ? 1 : 0;
                dArr4 = dArr6;
            }
            dArr3 = dArr4;
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void atanh(double[] dArr, int i, double[] dArr2, int i2) {
        double[] dArr3;
        double[] dArr4;
        DSCompiler dSCompiler = this;
        boolean z = true;
        double[] dArr5 = new double[dSCompiler.order + 1];
        double d = dArr[i];
        boolean z2 = false;
        dArr5[0] = FastMath.atanh(d);
        if (dSCompiler.order <= 0) {
            dArr3 = dArr5;
        } else {
            double[] dArr6 = new double[dSCompiler.order];
            dArr6[0] = 1.0d;
            double d2 = d * d;
            double d3 = 1.0d / (1.0d - d2);
            double d4 = d3;
            dArr5[1] = dArr6[0] * d4;
            int i3 = 2;
            while (i3 <= dSCompiler.order) {
                double d5 = 0.0d;
                boolean z3 = z2;
                boolean z4 = z;
                dArr6[i3 - 1] = i3 * dArr6[i3 - 2];
                int i4 = i3 - 1;
                while (i4 >= 0) {
                    d5 = (d5 * d2) + dArr6[i4];
                    if (i4 > 2) {
                        dArr4 = dArr5;
                        dArr6[i4 - 2] = ((i4 - 1) * dArr6[i4 - 1]) + ((((i3 * 2) - i4) + 1) * dArr6[i4 - 3]);
                    } else {
                        dArr4 = dArr5;
                        if (i4 == 2) {
                            dArr6[z3 ? 1 : 0] = dArr6[z4 ? 1 : 0];
                        }
                    }
                    i4 -= 2;
                    dArr5 = dArr4;
                }
                double[] dArr7 = dArr5;
                if ((i3 & 1) == 0) {
                    d5 *= d;
                }
                d4 *= d3;
                dArr7[i3] = d4 * d5;
                i3++;
                dSCompiler = this;
                dArr5 = dArr7;
                z = z4 ? 1 : 0;
                z2 = z3 ? 1 : 0;
            }
            dArr3 = dArr5;
        }
        compose(dArr, i, dArr3, dArr2, i2);
    }

    public void compose(double[] operand, int operandOffset, double[] f, double[] result, int resultOffset) {
        for (int i = 0; i < this.compIndirection.length; i++) {
            int[][] mappingI = this.compIndirection[i];
            double r = 0.0d;
            for (int[] mappingIJ : mappingI) {
                double product = mappingIJ[0] * f[mappingIJ[1]];
                for (int k = 2; k < mappingIJ.length; k++) {
                    product *= operand[mappingIJ[k] + operandOffset];
                }
                r += product;
            }
            int j = resultOffset + i;
            result[j] = r;
        }
    }

    public double taylor(double[] ds, int dsOffset, double... delta) throws MathArithmeticException {
        double value = 0.0d;
        for (int i = getSize() - 1; i >= 0; i--) {
            int[] orders = getPartialDerivativeOrders(i);
            double term = ds[dsOffset + i];
            for (int k = 0; k < orders.length; k++) {
                if (orders[k] > 0) {
                    try {
                        term *= FastMath.pow(delta[k], orders[k]) / CombinatoricsUtils.factorial(orders[k]);
                    } catch (NotPositiveException e) {
                        throw new MathInternalError(e);
                    }
                }
            }
            value += term;
        }
        return value;
    }

    public void checkCompatibility(DSCompiler compiler) throws DimensionMismatchException {
        if (this.parameters != compiler.parameters) {
            throw new DimensionMismatchException(this.parameters, compiler.parameters);
        }
        if (this.order != compiler.order) {
            throw new DimensionMismatchException(this.order, compiler.order);
        }
    }
}
