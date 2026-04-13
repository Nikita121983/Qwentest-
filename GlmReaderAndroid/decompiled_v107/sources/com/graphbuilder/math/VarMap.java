package com.graphbuilder.math;

/* loaded from: classes.dex */
public class VarMap {
    private boolean caseSensitive;
    private String[] name;
    private int numVars;
    private double[] value;

    public VarMap() {
        this(true);
    }

    public VarMap(boolean caseSensitive) {
        this.caseSensitive = true;
        this.name = new String[2];
        this.value = new double[2];
        this.numVars = 0;
        this.caseSensitive = caseSensitive;
    }

    public double getValue(String varName) {
        for (int i = 0; i < this.numVars; i++) {
            if ((this.caseSensitive && this.name[i].equals(varName)) || (!this.caseSensitive && this.name[i].equalsIgnoreCase(varName))) {
                return this.value[i];
            }
        }
        throw new RuntimeException("variable value has not been set: " + varName);
    }

    public void setValue(String varName, double val) {
        if (varName == null) {
            throw new IllegalArgumentException("varName cannot be null");
        }
        for (int i = 0; i < this.numVars; i++) {
            if ((this.caseSensitive && this.name[i].equals(varName)) || (!this.caseSensitive && this.name[i].equalsIgnoreCase(varName))) {
                this.value[i] = val;
                return;
            }
        }
        int i2 = this.numVars;
        if (i2 == this.name.length) {
            String[] tmp1 = new String[this.numVars * 2];
            double[] tmp2 = new double[tmp1.length];
            for (int i3 = 0; i3 < this.numVars; i3++) {
                tmp1[i3] = this.name[i3];
                tmp2[i3] = this.value[i3];
            }
            this.name = tmp1;
            this.value = tmp2;
        }
        this.name[this.numVars] = varName;
        this.value[this.numVars] = val;
        this.numVars++;
    }

    public boolean isCaseSensitive() {
        return this.caseSensitive;
    }

    public String[] getVariableNames() {
        String[] arr = new String[this.numVars];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = this.name[i];
        }
        return arr;
    }

    public double[] getValues() {
        double[] arr = new double[this.numVars];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = this.value[i];
        }
        return arr;
    }

    public void remove(String varName) {
        for (int i = 0; i < this.numVars; i++) {
            if ((this.caseSensitive && this.name[i].equals(varName)) || (!this.caseSensitive && this.name[i].equalsIgnoreCase(varName))) {
                for (int j = i + 1; j < this.numVars; j++) {
                    this.name[j - 1] = this.name[j];
                    this.value[j - 1] = this.value[j];
                }
                int j2 = this.numVars;
                this.numVars = j2 - 1;
                this.name[this.numVars] = null;
                this.value[this.numVars] = 0.0d;
                return;
            }
        }
    }
}
