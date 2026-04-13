package org.apache.commons.math3.ode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes10.dex */
public class JacobianMatrices {
    private boolean dirtyParameter;
    private ExpandableStatefulODE efode;
    private int index;
    private List<ParameterJacobianProvider> jacobianProviders;
    private MainStateJacobianProvider jode;
    private double[] matricesData;
    private int paramDim;
    private ParameterizedODE pode;
    private ParameterConfiguration[] selectedParameters;
    private int stateDim;

    public JacobianMatrices(FirstOrderDifferentialEquations fode, double[] hY, String... parameters) throws DimensionMismatchException {
        this(new MainStateJacobianWrapper(fode, hY), parameters);
    }

    public JacobianMatrices(MainStateJacobianProvider jode, String... parameters) {
        this.efode = null;
        this.index = -1;
        this.jode = jode;
        this.pode = null;
        this.stateDim = jode.getDimension();
        if (parameters == null) {
            this.selectedParameters = null;
            this.paramDim = 0;
        } else {
            this.selectedParameters = new ParameterConfiguration[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                this.selectedParameters[i] = new ParameterConfiguration(parameters[i], Double.NaN);
            }
            int i2 = parameters.length;
            this.paramDim = i2;
        }
        this.dirtyParameter = false;
        this.jacobianProviders = new ArrayList();
        this.matricesData = new double[(this.stateDim + this.paramDim) * this.stateDim];
        for (int i3 = 0; i3 < this.stateDim; i3++) {
            this.matricesData[(this.stateDim + 1) * i3] = 1.0d;
        }
    }

    public void registerVariationalEquations(ExpandableStatefulODE expandable) throws DimensionMismatchException, MismatchedEquations {
        FirstOrderDifferentialEquations ode = this.jode instanceof MainStateJacobianWrapper ? ((MainStateJacobianWrapper) this.jode).ode : this.jode;
        if (expandable.getPrimary() != ode) {
            throw new MismatchedEquations();
        }
        this.efode = expandable;
        this.index = this.efode.addSecondaryEquations(new JacobiansSecondaryEquations());
        this.efode.setSecondaryState(this.index, this.matricesData);
    }

    public void addParameterJacobianProvider(ParameterJacobianProvider provider) {
        this.jacobianProviders.add(provider);
    }

    public void setParameterizedODE(ParameterizedODE parameterizedOde) {
        this.pode = parameterizedOde;
        this.dirtyParameter = true;
    }

    public void setParameterStep(String parameter, double hP) throws UnknownParameterException {
        ParameterConfiguration[] arr$ = this.selectedParameters;
        for (ParameterConfiguration param : arr$) {
            if (parameter.equals(param.getParameterName())) {
                param.setHP(hP);
                this.dirtyParameter = true;
                return;
            }
        }
        throw new UnknownParameterException(parameter);
    }

    public void setInitialMainStateJacobian(double[][] dYdY0) throws DimensionMismatchException {
        checkDimension(this.stateDim, dYdY0);
        checkDimension(this.stateDim, dYdY0[0]);
        int i = 0;
        for (double[] row : dYdY0) {
            System.arraycopy(row, 0, this.matricesData, i, this.stateDim);
            i += this.stateDim;
        }
        if (this.efode != null) {
            this.efode.setSecondaryState(this.index, this.matricesData);
        }
    }

    public void setInitialParameterJacobian(String pName, double[] dYdP) throws UnknownParameterException, DimensionMismatchException {
        checkDimension(this.stateDim, dYdP);
        int i = this.stateDim * this.stateDim;
        ParameterConfiguration[] arr$ = this.selectedParameters;
        for (ParameterConfiguration param : arr$) {
            if (pName.equals(param.getParameterName())) {
                System.arraycopy(dYdP, 0, this.matricesData, i, this.stateDim);
                if (this.efode != null) {
                    this.efode.setSecondaryState(this.index, this.matricesData);
                    return;
                }
                return;
            }
            i += this.stateDim;
        }
        throw new UnknownParameterException(pName);
    }

    public void getCurrentMainSetJacobian(double[][] dYdY0) {
        double[] p = this.efode.getSecondaryState(this.index);
        int j = 0;
        for (int i = 0; i < this.stateDim; i++) {
            System.arraycopy(p, j, dYdY0[i], 0, this.stateDim);
            j += this.stateDim;
        }
    }

    public void getCurrentParameterJacobian(String pName, double[] dYdP) {
        double[] p = this.efode.getSecondaryState(this.index);
        int i = this.stateDim * this.stateDim;
        ParameterConfiguration[] arr$ = this.selectedParameters;
        for (ParameterConfiguration param : arr$) {
            if (param.getParameterName().equals(pName)) {
                System.arraycopy(p, i, dYdP, 0, this.stateDim);
                return;
            }
            i += this.stateDim;
        }
    }

    private void checkDimension(int expected, Object array) throws DimensionMismatchException {
        int arrayDimension = array == null ? 0 : Array.getLength(array);
        if (arrayDimension != expected) {
            throw new DimensionMismatchException(arrayDimension, expected);
        }
    }

    /* loaded from: classes10.dex */
    private class JacobiansSecondaryEquations implements SecondaryEquations {
        private JacobiansSecondaryEquations() {
        }

        @Override // org.apache.commons.math3.ode.SecondaryEquations
        public int getDimension() {
            return JacobianMatrices.this.stateDim * (JacobianMatrices.this.stateDim + JacobianMatrices.this.paramDim);
        }

        @Override // org.apache.commons.math3.ode.SecondaryEquations
        public void computeDerivatives(double t, double[] y, double[] yDot, double[] z, double[] zDot) throws MaxCountExceededException, DimensionMismatchException {
            ParameterConfiguration[] arr$;
            int len$;
            int k;
            int len$2;
            if (JacobianMatrices.this.dirtyParameter && JacobianMatrices.this.paramDim != 0) {
                JacobianMatrices.this.jacobianProviders.add(new ParameterJacobianWrapper(JacobianMatrices.this.jode, JacobianMatrices.this.pode, JacobianMatrices.this.selectedParameters));
                JacobianMatrices.this.dirtyParameter = false;
            }
            double[][] dFdY = (double[][]) Array.newInstance((Class<?>) Double.TYPE, JacobianMatrices.this.stateDim, JacobianMatrices.this.stateDim);
            JacobianMatrices.this.jode.computeMainStateJacobian(t, y, yDot, dFdY);
            for (int i = 0; i < JacobianMatrices.this.stateDim; i++) {
                double[] dFdYi = dFdY[i];
                for (int j = 0; j < JacobianMatrices.this.stateDim; j++) {
                    double s = 0.0d;
                    int startIndex = j;
                    int zIndex = startIndex;
                    for (int l = 0; l < JacobianMatrices.this.stateDim; l++) {
                        s += dFdYi[l] * z[zIndex];
                        zIndex += JacobianMatrices.this.stateDim;
                    }
                    zDot[(JacobianMatrices.this.stateDim * i) + startIndex] = s;
                }
            }
            if (JacobianMatrices.this.paramDim != 0) {
                double[] dFdP = new double[JacobianMatrices.this.stateDim];
                int startIndex2 = JacobianMatrices.this.stateDim * JacobianMatrices.this.stateDim;
                ParameterConfiguration[] arr$2 = JacobianMatrices.this.selectedParameters;
                int len$3 = arr$2.length;
                int i$ = 0;
                while (i$ < len$3) {
                    ParameterConfiguration param = arr$2[i$];
                    boolean found = false;
                    int k2 = 0;
                    while (!found && k2 < JacobianMatrices.this.jacobianProviders.size()) {
                        ParameterJacobianProvider provider = (ParameterJacobianProvider) JacobianMatrices.this.jacobianProviders.get(k2);
                        if (!provider.isSupported(param.getParameterName())) {
                            arr$ = arr$2;
                            len$ = len$3;
                            k = k2;
                        } else {
                            k = k2;
                            provider.computeParameterJacobian(t, y, yDot, param.getParameterName(), dFdP);
                            int i2 = 0;
                            while (i2 < JacobianMatrices.this.stateDim) {
                                double[] dFdYi2 = dFdY[i2];
                                int zIndex2 = startIndex2;
                                double s2 = dFdP[i2];
                                ParameterConfiguration[] arr$3 = arr$2;
                                int l2 = 0;
                                while (true) {
                                    len$2 = len$3;
                                    if (l2 < JacobianMatrices.this.stateDim) {
                                        s2 += dFdYi2[l2] * z[zIndex2];
                                        zIndex2++;
                                        l2++;
                                        len$3 = len$2;
                                    }
                                }
                                int l3 = startIndex2 + i2;
                                zDot[l3] = s2;
                                i2++;
                                arr$2 = arr$3;
                                len$3 = len$2;
                            }
                            arr$ = arr$2;
                            len$ = len$3;
                            found = true;
                        }
                        k2 = k + 1;
                        arr$2 = arr$;
                        len$3 = len$;
                    }
                    ParameterConfiguration[] arr$4 = arr$2;
                    int len$4 = len$3;
                    if (!found) {
                        Arrays.fill(zDot, startIndex2, JacobianMatrices.this.stateDim + startIndex2, 0.0d);
                    }
                    startIndex2 += JacobianMatrices.this.stateDim;
                    i$++;
                    arr$2 = arr$4;
                    len$3 = len$4;
                }
            }
        }
    }

    /* loaded from: classes10.dex */
    private static class MainStateJacobianWrapper implements MainStateJacobianProvider {
        private final double[] hY;
        private final FirstOrderDifferentialEquations ode;

        MainStateJacobianWrapper(FirstOrderDifferentialEquations ode, double[] hY) throws DimensionMismatchException {
            this.ode = ode;
            this.hY = (double[]) hY.clone();
            if (hY.length != ode.getDimension()) {
                throw new DimensionMismatchException(ode.getDimension(), hY.length);
            }
        }

        @Override // org.apache.commons.math3.ode.FirstOrderDifferentialEquations
        public int getDimension() {
            return this.ode.getDimension();
        }

        @Override // org.apache.commons.math3.ode.FirstOrderDifferentialEquations
        public void computeDerivatives(double t, double[] y, double[] yDot) throws MaxCountExceededException, DimensionMismatchException {
            this.ode.computeDerivatives(t, y, yDot);
        }

        @Override // org.apache.commons.math3.ode.MainStateJacobianProvider
        public void computeMainStateJacobian(double t, double[] y, double[] yDot, double[][] dFdY) throws MaxCountExceededException, DimensionMismatchException {
            int n = this.ode.getDimension();
            double[] tmpDot = new double[n];
            for (int j = 0; j < n; j++) {
                double savedYj = y[j];
                y[j] = y[j] + this.hY[j];
                this.ode.computeDerivatives(t, y, tmpDot);
                for (int i = 0; i < n; i++) {
                    dFdY[i][j] = (tmpDot[i] - yDot[i]) / this.hY[j];
                }
                y[j] = savedYj;
            }
        }
    }

    /* loaded from: classes10.dex */
    public static class MismatchedEquations extends MathIllegalArgumentException {
        private static final long serialVersionUID = 20120902;

        public MismatchedEquations() {
            super(LocalizedFormats.UNMATCHED_ODE_IN_EXPANDED_SET, new Object[0]);
        }
    }
}
