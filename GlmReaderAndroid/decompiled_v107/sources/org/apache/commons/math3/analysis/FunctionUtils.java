package org.apache.commons.math3.analysis;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableFunction;
import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableVectorFunction;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.analysis.function.Identity;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes10.dex */
public class FunctionUtils {
    private FunctionUtils() {
    }

    public static UnivariateFunction compose(final UnivariateFunction... f) {
        return new UnivariateFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.1
            @Override // org.apache.commons.math3.analysis.UnivariateFunction
            public double value(double x) {
                double r = x;
                for (int i = f.length - 1; i >= 0; i--) {
                    r = f[i].value(r);
                }
                return r;
            }
        };
    }

    public static UnivariateDifferentiableFunction compose(final UnivariateDifferentiableFunction... f) {
        return new UnivariateDifferentiableFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.2
            @Override // org.apache.commons.math3.analysis.UnivariateFunction
            public double value(double t) {
                double r = t;
                for (int i = f.length - 1; i >= 0; i--) {
                    r = f[i].value(r);
                }
                return r;
            }

            @Override // org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction
            public DerivativeStructure value(DerivativeStructure t) {
                DerivativeStructure r = t;
                for (int i = f.length - 1; i >= 0; i--) {
                    r = f[i].value(r);
                }
                return r;
            }
        };
    }

    @Deprecated
    public static DifferentiableUnivariateFunction compose(final DifferentiableUnivariateFunction... f) {
        return new DifferentiableUnivariateFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.3
            @Override // org.apache.commons.math3.analysis.UnivariateFunction
            public double value(double x) {
                double r = x;
                for (int i = f.length - 1; i >= 0; i--) {
                    r = f[i].value(r);
                }
                return r;
            }

            @Override // org.apache.commons.math3.analysis.DifferentiableUnivariateFunction
            public UnivariateFunction derivative() {
                return new UnivariateFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.3.1
                    @Override // org.apache.commons.math3.analysis.UnivariateFunction
                    public double value(double x) {
                        double p = 1.0d;
                        double r = x;
                        for (int i = f.length - 1; i >= 0; i--) {
                            p *= f[i].derivative().value(r);
                            r = f[i].value(r);
                        }
                        return p;
                    }
                };
            }
        };
    }

    public static UnivariateFunction add(final UnivariateFunction... f) {
        return new UnivariateFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.4
            @Override // org.apache.commons.math3.analysis.UnivariateFunction
            public double value(double x) {
                double r = f[0].value(x);
                for (int i = 1; i < f.length; i++) {
                    r += f[i].value(x);
                }
                return r;
            }
        };
    }

    public static UnivariateDifferentiableFunction add(final UnivariateDifferentiableFunction... f) {
        return new UnivariateDifferentiableFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.5
            @Override // org.apache.commons.math3.analysis.UnivariateFunction
            public double value(double t) {
                double r = f[0].value(t);
                for (int i = 1; i < f.length; i++) {
                    r += f[i].value(t);
                }
                return r;
            }

            @Override // org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction
            public DerivativeStructure value(DerivativeStructure t) throws DimensionMismatchException {
                DerivativeStructure r = f[0].value(t);
                for (int i = 1; i < f.length; i++) {
                    r = r.add(f[i].value(t));
                }
                return r;
            }
        };
    }

    @Deprecated
    public static DifferentiableUnivariateFunction add(final DifferentiableUnivariateFunction... f) {
        return new DifferentiableUnivariateFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.6
            @Override // org.apache.commons.math3.analysis.UnivariateFunction
            public double value(double x) {
                double r = f[0].value(x);
                for (int i = 1; i < f.length; i++) {
                    r += f[i].value(x);
                }
                return r;
            }

            @Override // org.apache.commons.math3.analysis.DifferentiableUnivariateFunction
            public UnivariateFunction derivative() {
                return new UnivariateFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.6.1
                    @Override // org.apache.commons.math3.analysis.UnivariateFunction
                    public double value(double x) {
                        double r = f[0].derivative().value(x);
                        for (int i = 1; i < f.length; i++) {
                            r += f[i].derivative().value(x);
                        }
                        return r;
                    }
                };
            }
        };
    }

    public static UnivariateFunction multiply(final UnivariateFunction... f) {
        return new UnivariateFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.7
            @Override // org.apache.commons.math3.analysis.UnivariateFunction
            public double value(double x) {
                double r = f[0].value(x);
                for (int i = 1; i < f.length; i++) {
                    r *= f[i].value(x);
                }
                return r;
            }
        };
    }

    public static UnivariateDifferentiableFunction multiply(final UnivariateDifferentiableFunction... f) {
        return new UnivariateDifferentiableFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.8
            @Override // org.apache.commons.math3.analysis.UnivariateFunction
            public double value(double t) {
                double r = f[0].value(t);
                for (int i = 1; i < f.length; i++) {
                    r *= f[i].value(t);
                }
                return r;
            }

            @Override // org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction
            public DerivativeStructure value(DerivativeStructure t) {
                DerivativeStructure r = f[0].value(t);
                for (int i = 1; i < f.length; i++) {
                    r = r.multiply(f[i].value(t));
                }
                return r;
            }
        };
    }

    @Deprecated
    public static DifferentiableUnivariateFunction multiply(final DifferentiableUnivariateFunction... f) {
        return new DifferentiableUnivariateFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.9
            @Override // org.apache.commons.math3.analysis.UnivariateFunction
            public double value(double x) {
                double r = f[0].value(x);
                for (int i = 1; i < f.length; i++) {
                    r *= f[i].value(x);
                }
                return r;
            }

            @Override // org.apache.commons.math3.analysis.DifferentiableUnivariateFunction
            public UnivariateFunction derivative() {
                return new UnivariateFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.9.1
                    @Override // org.apache.commons.math3.analysis.UnivariateFunction
                    public double value(double x) {
                        double sum = 0.0d;
                        for (int i = 0; i < f.length; i++) {
                            double prod = f[i].derivative().value(x);
                            for (int j = 0; j < f.length; j++) {
                                if (i != j) {
                                    prod *= f[j].value(x);
                                }
                            }
                            sum += prod;
                        }
                        return sum;
                    }
                };
            }
        };
    }

    public static UnivariateFunction combine(final BivariateFunction combiner, final UnivariateFunction f, final UnivariateFunction g) {
        return new UnivariateFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.10
            @Override // org.apache.commons.math3.analysis.UnivariateFunction
            public double value(double x) {
                return BivariateFunction.this.value(f.value(x), g.value(x));
            }
        };
    }

    public static MultivariateFunction collector(final BivariateFunction combiner, final UnivariateFunction f, final double initialValue) {
        return new MultivariateFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.11
            @Override // org.apache.commons.math3.analysis.MultivariateFunction
            public double value(double[] point) {
                double result = BivariateFunction.this.value(initialValue, f.value(point[0]));
                for (int i = 1; i < point.length; i++) {
                    result = BivariateFunction.this.value(result, f.value(point[i]));
                }
                return result;
            }
        };
    }

    public static MultivariateFunction collector(BivariateFunction combiner, double initialValue) {
        return collector(combiner, new Identity(), initialValue);
    }

    public static UnivariateFunction fix1stArgument(final BivariateFunction f, final double fixed) {
        return new UnivariateFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.12
            @Override // org.apache.commons.math3.analysis.UnivariateFunction
            public double value(double x) {
                return BivariateFunction.this.value(fixed, x);
            }
        };
    }

    public static UnivariateFunction fix2ndArgument(final BivariateFunction f, final double fixed) {
        return new UnivariateFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.13
            @Override // org.apache.commons.math3.analysis.UnivariateFunction
            public double value(double x) {
                return BivariateFunction.this.value(x, fixed);
            }
        };
    }

    public static double[] sample(UnivariateFunction f, double min, double max, int n) throws NumberIsTooLargeException, NotStrictlyPositiveException {
        if (n <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_NUMBER_OF_SAMPLES, Integer.valueOf(n));
        }
        if (min >= max) {
            throw new NumberIsTooLargeException(Double.valueOf(min), Double.valueOf(max), false);
        }
        double[] s = new double[n];
        double h = (max - min) / n;
        for (int i = 0; i < n; i++) {
            s[i] = f.value((i * h) + min);
        }
        return s;
    }

    @Deprecated
    public static DifferentiableUnivariateFunction toDifferentiableUnivariateFunction(final UnivariateDifferentiableFunction f) {
        return new DifferentiableUnivariateFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.14
            @Override // org.apache.commons.math3.analysis.UnivariateFunction
            public double value(double x) {
                return UnivariateDifferentiableFunction.this.value(x);
            }

            @Override // org.apache.commons.math3.analysis.DifferentiableUnivariateFunction
            public UnivariateFunction derivative() {
                return new UnivariateFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.14.1
                    @Override // org.apache.commons.math3.analysis.UnivariateFunction
                    public double value(double x) {
                        return UnivariateDifferentiableFunction.this.value(new DerivativeStructure(1, 1, 0, x)).getPartialDerivative(1);
                    }
                };
            }
        };
    }

    @Deprecated
    public static UnivariateDifferentiableFunction toUnivariateDifferential(final DifferentiableUnivariateFunction f) {
        return new UnivariateDifferentiableFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.15
            @Override // org.apache.commons.math3.analysis.UnivariateFunction
            public double value(double x) {
                return DifferentiableUnivariateFunction.this.value(x);
            }

            @Override // org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction
            public DerivativeStructure value(DerivativeStructure t) throws NumberIsTooLargeException {
                switch (t.getOrder()) {
                    case 0:
                        return new DerivativeStructure(t.getFreeParameters(), 0, DifferentiableUnivariateFunction.this.value(t.getValue()));
                    case 1:
                        int parameters = t.getFreeParameters();
                        double[] derivatives = new double[parameters + 1];
                        derivatives[0] = DifferentiableUnivariateFunction.this.value(t.getValue());
                        double fPrime = DifferentiableUnivariateFunction.this.derivative().value(t.getValue());
                        int[] orders = new int[parameters];
                        for (int i = 0; i < parameters; i++) {
                            orders[i] = 1;
                            derivatives[i + 1] = t.getPartialDerivative(orders) * fPrime;
                            orders[i] = 0;
                        }
                        return new DerivativeStructure(parameters, 1, derivatives);
                    default:
                        throw new NumberIsTooLargeException(Integer.valueOf(t.getOrder()), 1, true);
                }
            }
        };
    }

    @Deprecated
    public static DifferentiableMultivariateFunction toDifferentiableMultivariateFunction(final MultivariateDifferentiableFunction f) {
        return new DifferentiableMultivariateFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.16
            @Override // org.apache.commons.math3.analysis.MultivariateFunction
            public double value(double[] x) {
                return MultivariateDifferentiableFunction.this.value(x);
            }

            @Override // org.apache.commons.math3.analysis.DifferentiableMultivariateFunction
            public MultivariateFunction partialDerivative(final int k) {
                return new MultivariateFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.16.1
                    @Override // org.apache.commons.math3.analysis.MultivariateFunction
                    public double value(double[] x) {
                        int n = x.length;
                        DerivativeStructure[] dsX = new DerivativeStructure[n];
                        for (int i = 0; i < n; i++) {
                            if (i == k) {
                                dsX[i] = new DerivativeStructure(1, 1, 0, x[i]);
                            } else {
                                dsX[i] = new DerivativeStructure(1, 1, x[i]);
                            }
                        }
                        DerivativeStructure y = MultivariateDifferentiableFunction.this.value(dsX);
                        return y.getPartialDerivative(1);
                    }
                };
            }

            @Override // org.apache.commons.math3.analysis.DifferentiableMultivariateFunction
            public MultivariateVectorFunction gradient() {
                return new MultivariateVectorFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.16.2
                    @Override // org.apache.commons.math3.analysis.MultivariateVectorFunction
                    public double[] value(double[] x) {
                        int n = x.length;
                        DerivativeStructure[] dsX = new DerivativeStructure[n];
                        for (int i = 0; i < n; i++) {
                            dsX[i] = new DerivativeStructure(n, 1, i, x[i]);
                        }
                        DerivativeStructure y = MultivariateDifferentiableFunction.this.value(dsX);
                        double[] gradient = new double[n];
                        int[] orders = new int[n];
                        for (int i2 = 0; i2 < n; i2++) {
                            orders[i2] = 1;
                            gradient[i2] = y.getPartialDerivative(orders);
                            orders[i2] = 0;
                        }
                        return gradient;
                    }
                };
            }
        };
    }

    @Deprecated
    public static MultivariateDifferentiableFunction toMultivariateDifferentiableFunction(final DifferentiableMultivariateFunction f) {
        return new MultivariateDifferentiableFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.17
            @Override // org.apache.commons.math3.analysis.MultivariateFunction
            public double value(double[] x) {
                return DifferentiableMultivariateFunction.this.value(x);
            }

            @Override // org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableFunction
            public DerivativeStructure value(DerivativeStructure[] t) throws DimensionMismatchException, NumberIsTooLargeException {
                int i = 0;
                int parameters = t[0].getFreeParameters();
                int order = t[0].getOrder();
                int n = t.length;
                if (order > 1) {
                    throw new NumberIsTooLargeException(Integer.valueOf(order), 1, true);
                }
                for (int i2 = 0; i2 < n; i2++) {
                    if (t[i2].getFreeParameters() != parameters) {
                        throw new DimensionMismatchException(t[i2].getFreeParameters(), parameters);
                    }
                    if (t[i2].getOrder() != order) {
                        throw new DimensionMismatchException(t[i2].getOrder(), order);
                    }
                }
                double[] point = new double[n];
                for (int i3 = 0; i3 < n; i3++) {
                    point[i3] = t[i3].getValue();
                }
                double value = DifferentiableMultivariateFunction.this.value(point);
                double[] gradient = DifferentiableMultivariateFunction.this.gradient().value(point);
                double[] derivatives = new double[parameters + 1];
                derivatives[0] = value;
                int[] orders = new int[parameters];
                for (int i4 = 0; i4 < parameters; i4++) {
                    orders[i4] = 1;
                    int j = 0;
                    while (j < n) {
                        int i5 = i4 + 1;
                        derivatives[i5] = derivatives[i5] + (gradient[j] * t[j].getPartialDerivative(orders));
                        j++;
                        i = i;
                    }
                    orders[i4] = i;
                }
                return new DerivativeStructure(parameters, order, derivatives);
            }
        };
    }

    @Deprecated
    public static DifferentiableMultivariateVectorFunction toDifferentiableMultivariateVectorFunction(final MultivariateDifferentiableVectorFunction f) {
        return new DifferentiableMultivariateVectorFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.18
            @Override // org.apache.commons.math3.analysis.MultivariateVectorFunction
            public double[] value(double[] x) {
                return MultivariateDifferentiableVectorFunction.this.value(x);
            }

            @Override // org.apache.commons.math3.analysis.DifferentiableMultivariateVectorFunction
            public MultivariateMatrixFunction jacobian() {
                return new MultivariateMatrixFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.18.1
                    @Override // org.apache.commons.math3.analysis.MultivariateMatrixFunction
                    public double[][] value(double[] x) {
                        int n = x.length;
                        DerivativeStructure[] dsX = new DerivativeStructure[n];
                        for (int i = 0; i < n; i++) {
                            dsX[i] = new DerivativeStructure(n, 1, i, x[i]);
                        }
                        DerivativeStructure[] y = MultivariateDifferentiableVectorFunction.this.value(dsX);
                        double[][] jacobian = (double[][]) Array.newInstance((Class<?>) Double.TYPE, y.length, n);
                        int[] orders = new int[n];
                        for (int i2 = 0; i2 < y.length; i2++) {
                            for (int j = 0; j < n; j++) {
                                orders[j] = 1;
                                jacobian[i2][j] = y[i2].getPartialDerivative(orders);
                                orders[j] = 0;
                            }
                        }
                        return jacobian;
                    }
                };
            }
        };
    }

    @Deprecated
    public static MultivariateDifferentiableVectorFunction toMultivariateDifferentiableVectorFunction(final DifferentiableMultivariateVectorFunction f) {
        return new MultivariateDifferentiableVectorFunction() { // from class: org.apache.commons.math3.analysis.FunctionUtils.19
            @Override // org.apache.commons.math3.analysis.MultivariateVectorFunction
            public double[] value(double[] x) {
                return DifferentiableMultivariateVectorFunction.this.value(x);
            }

            @Override // org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableVectorFunction
            public DerivativeStructure[] value(DerivativeStructure[] t) throws DimensionMismatchException, NumberIsTooLargeException {
                int i = 0;
                int parameters = t[0].getFreeParameters();
                int order = t[0].getOrder();
                int n = t.length;
                if (order > 1) {
                    throw new NumberIsTooLargeException(Integer.valueOf(order), 1, true);
                }
                for (int i2 = 0; i2 < n; i2++) {
                    if (t[i2].getFreeParameters() != parameters) {
                        throw new DimensionMismatchException(t[i2].getFreeParameters(), parameters);
                    }
                    if (t[i2].getOrder() != order) {
                        throw new DimensionMismatchException(t[i2].getOrder(), order);
                    }
                }
                double[] point = new double[n];
                for (int i3 = 0; i3 < n; i3++) {
                    point[i3] = t[i3].getValue();
                }
                double[] value = DifferentiableMultivariateVectorFunction.this.value(point);
                double[][] jacobian = DifferentiableMultivariateVectorFunction.this.jacobian().value(point);
                DerivativeStructure[] merged = new DerivativeStructure[value.length];
                int k = 0;
                while (k < merged.length) {
                    double[] derivatives = new double[parameters + 1];
                    derivatives[i] = value[k];
                    int[] orders = new int[parameters];
                    for (int i4 = 0; i4 < parameters; i4++) {
                        orders[i4] = 1;
                        int j = 0;
                        while (j < n) {
                            int i5 = i4 + 1;
                            derivatives[i5] = derivatives[i5] + (jacobian[k][j] * t[j].getPartialDerivative(orders));
                            j++;
                            i = i;
                        }
                        orders[i4] = i;
                    }
                    merged[k] = new DerivativeStructure(parameters, order, derivatives);
                    k++;
                    i = i;
                }
                return merged;
            }
        };
    }
}
