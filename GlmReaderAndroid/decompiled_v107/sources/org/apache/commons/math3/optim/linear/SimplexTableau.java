package org.apache.commons.math3.optim.linear;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
class SimplexTableau implements Serializable {
    private static final String NEGATIVE_VAR_COLUMN_LABEL = "x-";
    private static final long serialVersionUID = -1369660067587938365L;
    private int[] basicRows;
    private int[] basicVariables;
    private final List<String> columnLabels;
    private final List<LinearConstraint> constraints;
    private final double epsilon;
    private final LinearObjectiveFunction f;
    private final int maxUlps;
    private int numArtificialVariables;
    private final int numDecisionVariables;
    private final int numSlackVariables;
    private final boolean restrictToNonNegative;
    private transient Array2DRowRealMatrix tableau;

    SimplexTableau(LinearObjectiveFunction f, Collection<LinearConstraint> constraints, GoalType goalType, boolean restrictToNonNegative, double epsilon) {
        this(f, constraints, goalType, restrictToNonNegative, epsilon, 10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimplexTableau(LinearObjectiveFunction linearObjectiveFunction, Collection<LinearConstraint> collection, GoalType goalType, boolean z, double d, int i) {
        this.columnLabels = new ArrayList();
        this.f = linearObjectiveFunction;
        this.constraints = normalizeConstraints(collection);
        this.restrictToNonNegative = z;
        this.epsilon = d;
        this.maxUlps = i;
        this.numDecisionVariables = linearObjectiveFunction.getCoefficients().getDimension() + (!z ? 1 : 0);
        this.numSlackVariables = getConstraintTypeCounts(Relationship.LEQ) + getConstraintTypeCounts(Relationship.GEQ);
        this.numArtificialVariables = getConstraintTypeCounts(Relationship.EQ) + getConstraintTypeCounts(Relationship.GEQ);
        this.tableau = createTableau(goalType == GoalType.MAXIMIZE);
        initializeBasicVariables(getSlackVariableOffset());
        initializeColumnLabels();
    }

    protected void initializeColumnLabels() {
        if (getNumObjectiveFunctions() == 2) {
            this.columnLabels.add("W");
        }
        this.columnLabels.add("Z");
        for (int i = 0; i < getOriginalNumDecisionVariables(); i++) {
            this.columnLabels.add("x" + i);
        }
        if (!this.restrictToNonNegative) {
            this.columnLabels.add(NEGATIVE_VAR_COLUMN_LABEL);
        }
        for (int i2 = 0; i2 < getNumSlackVariables(); i2++) {
            this.columnLabels.add("s" + i2);
        }
        for (int i3 = 0; i3 < getNumArtificialVariables(); i3++) {
            this.columnLabels.add("a" + i3);
        }
        this.columnLabels.add("RHS");
    }

    protected Array2DRowRealMatrix createTableau(boolean maximize) {
        double d;
        boolean z;
        boolean z2 = true;
        int width = this.numDecisionVariables + this.numSlackVariables + this.numArtificialVariables + getNumObjectiveFunctions() + 1;
        int height = this.constraints.size() + getNumObjectiveFunctions();
        Array2DRowRealMatrix matrix = new Array2DRowRealMatrix(height, width);
        if (getNumObjectiveFunctions() == 2) {
            matrix.setEntry(0, 0, -1.0d);
        }
        int zIndex = getNumObjectiveFunctions() == 1 ? 0 : 1;
        double d2 = 1.0d;
        matrix.setEntry(zIndex, zIndex, maximize ? 1.0d : -1.0d);
        RealVector objectiveCoefficients = this.f.getCoefficients();
        if (maximize) {
            objectiveCoefficients = objectiveCoefficients.mapMultiply(-1.0d);
        }
        copyArray(objectiveCoefficients.toArray(), matrix.getDataRef()[zIndex]);
        int i = width - 1;
        double constantTerm = this.f.getConstantTerm();
        if (!maximize) {
            constantTerm *= -1.0d;
        }
        matrix.setEntry(zIndex, i, constantTerm);
        if (!this.restrictToNonNegative) {
            matrix.setEntry(zIndex, getSlackVariableOffset() - 1, getInvertedCoefficientSum(objectiveCoefficients));
        }
        int slackVar = 0;
        int artificialVar = 0;
        int i2 = 0;
        while (i2 < this.constraints.size()) {
            LinearConstraint constraint = this.constraints.get(i2);
            boolean z3 = z2;
            int row = getNumObjectiveFunctions() + i2;
            copyArray(constraint.getCoefficients().toArray(), matrix.getDataRef()[row]);
            if (!this.restrictToNonNegative) {
                matrix.setEntry(row, getSlackVariableOffset() - 1, getInvertedCoefficientSum(constraint.getCoefficients()));
            }
            matrix.setEntry(row, width - 1, constraint.getValue());
            if (constraint.getRelationship() == Relationship.LEQ) {
                matrix.setEntry(row, getSlackVariableOffset() + slackVar, d2);
                slackVar++;
            } else if (constraint.getRelationship() == Relationship.GEQ) {
                matrix.setEntry(row, getSlackVariableOffset() + slackVar, -1.0d);
                slackVar++;
            }
            if (constraint.getRelationship() == Relationship.EQ || constraint.getRelationship() == Relationship.GEQ) {
                d = 1.0d;
                z = false;
                matrix.setEntry(0, getArtificialVariableOffset() + artificialVar, 1.0d);
                matrix.setEntry(row, getArtificialVariableOffset() + artificialVar, 1.0d);
                matrix.setRowVector(0, matrix.getRowVector(0).subtract(matrix.getRowVector(row)));
                artificialVar++;
            } else {
                d = 1.0d;
                z = false;
            }
            i2++;
            d2 = d;
            z2 = z3;
        }
        return matrix;
    }

    public List<LinearConstraint> normalizeConstraints(Collection<LinearConstraint> originalConstraints) {
        List<LinearConstraint> normalized = new ArrayList<>(originalConstraints.size());
        for (LinearConstraint constraint : originalConstraints) {
            normalized.add(normalize(constraint));
        }
        return normalized;
    }

    private LinearConstraint normalize(LinearConstraint constraint) {
        if (constraint.getValue() < 0.0d) {
            return new LinearConstraint(constraint.getCoefficients().mapMultiply(-1.0d), constraint.getRelationship().oppositeRelationship(), constraint.getValue() * (-1.0d));
        }
        return new LinearConstraint(constraint.getCoefficients(), constraint.getRelationship(), constraint.getValue());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getNumObjectiveFunctions() {
        return this.numArtificialVariables > 0 ? 2 : 1;
    }

    private int getConstraintTypeCounts(Relationship relationship) {
        int count = 0;
        for (LinearConstraint constraint : this.constraints) {
            if (constraint.getRelationship() == relationship) {
                count++;
            }
        }
        return count;
    }

    protected static double getInvertedCoefficientSum(RealVector coefficients) {
        double sum = 0.0d;
        double[] arr$ = coefficients.toArray();
        for (double coefficient : arr$) {
            sum -= coefficient;
        }
        return sum;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Integer getBasicRow(int col) {
        int row = this.basicVariables[col];
        if (row == -1) {
            return null;
        }
        return Integer.valueOf(row);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getBasicVariable(int row) {
        return this.basicRows[row];
    }

    private void initializeBasicVariables(int startColumn) {
        this.basicVariables = new int[getWidth() - 1];
        this.basicRows = new int[getHeight()];
        Arrays.fill(this.basicVariables, -1);
        for (int i = startColumn; i < getWidth() - 1; i++) {
            Integer row = findBasicRow(i);
            if (row != null) {
                this.basicVariables[i] = row.intValue();
                this.basicRows[row.intValue()] = i;
            }
        }
    }

    private Integer findBasicRow(int col) {
        Integer row = null;
        for (int i = 0; i < getHeight(); i++) {
            double entry = getEntry(i, col);
            if (Precision.equals(entry, 1.0d, this.maxUlps) && row == null) {
                row = Integer.valueOf(i);
            } else if (!Precision.equals(entry, 0.0d, this.maxUlps)) {
                return null;
            }
        }
        return row;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void dropPhase1Objective() {
        if (getNumObjectiveFunctions() == 1) {
            return;
        }
        Set<Integer> columnsToDrop = new TreeSet<>();
        columnsToDrop.add(0);
        for (int i = getNumObjectiveFunctions(); i < getArtificialVariableOffset(); i++) {
            double entry = getEntry(0, i);
            if (Precision.compareTo(entry, 0.0d, this.epsilon) > 0) {
                columnsToDrop.add(Integer.valueOf(i));
            }
        }
        for (int i2 = 0; i2 < getNumArtificialVariables(); i2++) {
            int col = getArtificialVariableOffset() + i2;
            if (getBasicRow(col) == null) {
                columnsToDrop.add(Integer.valueOf(col));
            }
        }
        int i3 = getHeight();
        double[][] matrix = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i3 - 1, getWidth() - columnsToDrop.size());
        for (int i4 = 1; i4 < getHeight(); i4++) {
            int col2 = 0;
            for (int j = 0; j < getWidth(); j++) {
                if (!columnsToDrop.contains(Integer.valueOf(j))) {
                    matrix[i4 - 1][col2] = getEntry(i4, j);
                    col2++;
                }
            }
        }
        int i5 = columnsToDrop.size();
        Integer[] drop = (Integer[]) columnsToDrop.toArray(new Integer[i5]);
        for (int i6 = drop.length - 1; i6 >= 0; i6--) {
            this.columnLabels.remove(drop[i6].intValue());
        }
        this.tableau = new Array2DRowRealMatrix(matrix);
        this.numArtificialVariables = 0;
        initializeBasicVariables(getNumObjectiveFunctions());
    }

    private void copyArray(double[] src, double[] dest) {
        System.arraycopy(src, 0, dest, getNumObjectiveFunctions(), src.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isOptimal() {
        double[] objectiveFunctionRow = getRow(0);
        int end = getRhsOffset();
        for (int i = getNumObjectiveFunctions(); i < end; i++) {
            double entry = objectiveFunctionRow[i];
            if (Precision.compareTo(entry, 0.0d, this.epsilon) < 0) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PointValuePair getSolution() {
        int negativeVarColumn = this.columnLabels.indexOf(NEGATIVE_VAR_COLUMN_LABEL);
        Integer negativeVarBasicRow = negativeVarColumn > 0 ? getBasicRow(negativeVarColumn) : null;
        double mostNegative = negativeVarBasicRow == null ? 0.0d : getEntry(negativeVarBasicRow.intValue(), getRhsOffset());
        Set<Integer> usedBasicRows = new HashSet<>();
        double[] coefficients = new double[getOriginalNumDecisionVariables()];
        for (int i = 0; i < coefficients.length; i++) {
            int colIndex = this.columnLabels.indexOf("x" + i);
            if (colIndex < 0) {
                coefficients[i] = 0.0d;
            } else {
                Integer basicRow = getBasicRow(colIndex);
                if (basicRow != null && basicRow.intValue() == 0) {
                    coefficients[i] = 0.0d;
                } else if (usedBasicRows.contains(basicRow)) {
                    coefficients[i] = 0.0d - (this.restrictToNonNegative ? 0.0d : mostNegative);
                } else {
                    usedBasicRows.add(basicRow);
                    coefficients[i] = (basicRow == null ? 0.0d : getEntry(basicRow.intValue(), getRhsOffset())) - (this.restrictToNonNegative ? 0.0d : mostNegative);
                }
            }
        }
        return new PointValuePair(coefficients, this.f.value(coefficients));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void performRowOperations(int pivotCol, int pivotRow) {
        double pivotVal = getEntry(pivotRow, pivotCol);
        divideRow(pivotRow, pivotVal);
        for (int i = 0; i < getHeight(); i++) {
            if (i != pivotRow) {
                double multiplier = getEntry(i, pivotCol);
                if (multiplier != 0.0d) {
                    subtractRow(i, pivotRow, multiplier);
                }
            }
        }
        int previousBasicVariable = getBasicVariable(pivotRow);
        this.basicVariables[previousBasicVariable] = -1;
        this.basicVariables[pivotCol] = pivotRow;
        this.basicRows[pivotRow] = pivotCol;
    }

    protected void divideRow(int dividendRowIndex, double divisor) {
        double[] dividendRow = getRow(dividendRowIndex);
        for (int j = 0; j < getWidth(); j++) {
            dividendRow[j] = dividendRow[j] / divisor;
        }
    }

    protected void subtractRow(int minuendRowIndex, int subtrahendRowIndex, double multiplier) {
        double[] minuendRow = getRow(minuendRowIndex);
        double[] subtrahendRow = getRow(subtrahendRowIndex);
        for (int i = 0; i < getWidth(); i++) {
            minuendRow[i] = minuendRow[i] - (subtrahendRow[i] * multiplier);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getWidth() {
        return this.tableau.getColumnDimension();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getHeight() {
        return this.tableau.getRowDimension();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final double getEntry(int row, int column) {
        return this.tableau.getEntry(row, column);
    }

    protected final void setEntry(int row, int column, double value) {
        this.tableau.setEntry(row, column, value);
    }

    protected final int getSlackVariableOffset() {
        return getNumObjectiveFunctions() + this.numDecisionVariables;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getArtificialVariableOffset() {
        return getNumObjectiveFunctions() + this.numDecisionVariables + this.numSlackVariables;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getRhsOffset() {
        return getWidth() - 1;
    }

    protected final int getNumDecisionVariables() {
        return this.numDecisionVariables;
    }

    protected final int getOriginalNumDecisionVariables() {
        return this.f.getCoefficients().getDimension();
    }

    protected final int getNumSlackVariables() {
        return this.numSlackVariables;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getNumArtificialVariables() {
        return this.numArtificialVariables;
    }

    protected final double[] getRow(int row) {
        return this.tableau.getDataRef()[row];
    }

    protected final double[][] getData() {
        return this.tableau.getData();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimplexTableau)) {
            return false;
        }
        SimplexTableau rhs = (SimplexTableau) other;
        return this.restrictToNonNegative == rhs.restrictToNonNegative && this.numDecisionVariables == rhs.numDecisionVariables && this.numSlackVariables == rhs.numSlackVariables && this.numArtificialVariables == rhs.numArtificialVariables && this.epsilon == rhs.epsilon && this.maxUlps == rhs.maxUlps && this.f.equals(rhs.f) && this.constraints.equals(rhs.constraints) && this.tableau.equals(rhs.tableau);
    }

    public int hashCode() {
        return (((((((Boolean.valueOf(this.restrictToNonNegative).hashCode() ^ this.numDecisionVariables) ^ this.numSlackVariables) ^ this.numArtificialVariables) ^ Double.valueOf(this.epsilon).hashCode()) ^ this.maxUlps) ^ this.f.hashCode()) ^ this.constraints.hashCode()) ^ this.tableau.hashCode();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        MatrixUtils.serializeRealMatrix(this.tableau, oos);
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        MatrixUtils.deserializeRealMatrix(this, "tableau", ois);
    }
}
