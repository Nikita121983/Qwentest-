package com.graphbuilder.curve;

import com.graphbuilder.math.Expression;
import com.graphbuilder.math.ExpressionParseException;
import com.graphbuilder.math.ExpressionTree;
import com.graphbuilder.math.FuncMap;
import com.graphbuilder.math.VarMap;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.util.Chars;

/* loaded from: classes.dex */
public class GroupIterator {
    protected String controlString;
    protected int[] group;
    protected int index_i = 0;
    protected int count_j = 0;

    public GroupIterator(String controlString, int n) {
        this.controlString = null;
        this.group = null;
        if (controlString == null) {
            throw new IllegalArgumentException("control string cannot be null");
        }
        this.group = parseControlString(controlString, n);
        this.controlString = controlString;
    }

    public GroupIterator(int[] group) {
        this.controlString = null;
        this.group = null;
        if (group == null) {
            throw new IllegalArgumentException("group array cannot be null");
        }
        if (group.length == 0) {
            throw new IllegalArgumentException("group array length cannot be 0");
        }
        if (group.length % 2 != 0) {
            throw new IllegalArgumentException("group array must have even length");
        }
        double log10 = Math.log(10.0d);
        int numDigits = 0;
        int[] arr = new int[group.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = group[i];
            int x = arr[i];
            if (x < 0) {
                numDigits++;
                x = -x;
            }
            numDigits += ((int) (Math.log(x) / log10)) + 1;
        }
        this.group = arr;
        StringBuffer sb = new StringBuffer((arr.length / 2) + numDigits + (arr.length - 1));
        sb.append(arr[0]);
        sb.append(":");
        sb.append(arr[1]);
        for (int i2 = 2; i2 < group.length; i2 += 2) {
            sb.append(CollectionUtils.COMMA);
            sb.append(group[i2]);
            sb.append(":");
            sb.append(group[i2 + 1]);
        }
        this.controlString = sb.toString();
    }

    public static int[] parseControlString(String controlString, int n) {
        char c;
        char c2;
        char sLength;
        int sLength2;
        int numGroups;
        String s;
        int i = n;
        String s2 = controlString;
        int sLength3 = s2.length();
        int numGroups2 = 1;
        int b = 0;
        int i2 = 0;
        while (true) {
            c = ')';
            c2 = '(';
            sLength = ',';
            if (i2 >= sLength3) {
                break;
            }
            char c3 = s2.charAt(i2);
            if (c3 == ',' && b == 0) {
                numGroups2++;
            } else if (c3 == '(') {
                b++;
            } else if (c3 == ')') {
                b--;
            }
            i2++;
        }
        if (b != 0) {
            throw new ControlStringParseException("round brackets do not balance");
        }
        int[] group = new int[numGroups2 * 2];
        int j = 0;
        int k = 0;
        int colon = -1;
        VarMap vm = new VarMap();
        FuncMap fm = new FuncMap();
        fm.loadDefaultFunctions();
        int i3 = 0;
        while (i3 <= sLength3) {
            char c4 = Chars.SPACE;
            if (i3 < sLength3) {
                c4 = s2.charAt(i3);
            }
            if (i3 == sLength3 || (c4 == sLength && b == 0)) {
                if (colon == -1) {
                    Expression x = setVariables(s2, vm, i, j, i3);
                    group[k] = (int) Math.round(x.eval(vm, fm));
                    group[k + 1] = group[k];
                    k += 2;
                    sLength2 = sLength3;
                    numGroups = numGroups2;
                    s = s2;
                } else {
                    Expression x1 = setVariables(s2, vm, i, j, colon);
                    int k2 = k + 1;
                    sLength2 = sLength3;
                    numGroups = numGroups2;
                    group[k] = (int) Math.round(x1.eval(vm, fm));
                    Expression x2 = setVariables(s2, vm, i, colon + 1, i3);
                    k = k2 + 1;
                    s = s2;
                    group[k2] = (int) Math.round(x2.eval(vm, fm));
                }
                int j2 = i3 + 1;
                j = j2;
                colon = -1;
            } else if (c4 == c2) {
                b++;
                sLength2 = sLength3;
                numGroups = numGroups2;
                s = s2;
            } else if (c4 == c) {
                b--;
                sLength2 = sLength3;
                numGroups = numGroups2;
                s = s2;
            } else if (c4 != ':') {
                sLength2 = sLength3;
                numGroups = numGroups2;
                s = s2;
            } else {
                sLength2 = sLength3;
                numGroups = numGroups2;
                colon = i3;
                s = s2;
            }
            i3++;
            i = n;
            s2 = s;
            sLength3 = sLength2;
            numGroups2 = numGroups;
            c = ')';
            c2 = '(';
            sLength = ',';
        }
        return group;
    }

    private static Expression setVariables(String s, VarMap vm, int n, int j, int i) {
        try {
            Expression x = ExpressionTree.parse(s.substring(j, i));
            if (x == null) {
                throw new ControlStringParseException("control substring is empty", j, i);
            }
            String[] v = x.getVariableNames();
            if (v.length > 1) {
                throw new ControlStringParseException("too many variables", j, i);
            }
            if (v.length == 1) {
                vm.setValue(v[0], n);
            }
            return x;
        } catch (ExpressionParseException epe) {
            throw new ControlStringParseException("error parsing expression", j, i, epe);
        }
    }

    public String getControlString() {
        return this.controlString;
    }

    public int getGroupLength() {
        return this.group.length;
    }

    public int getGroupValue(int index) {
        if (index < 0 || index >= this.group.length) {
            throw new IllegalArgumentException("required: (index >= 0 && index < group.length) but: (index = " + index + ", group.length = " + this.group.length + ")");
        }
        return this.group[index];
    }

    public int getGroupSize() {
        int size = 0;
        for (int i = 0; i < this.group.length; i += 2) {
            int dif = this.group[i] - this.group[i + 1];
            if (dif < 0) {
                dif = -dif;
            }
            size += dif + 1;
        }
        return size;
    }

    public void copyGroupArray(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("specified array cannot be null");
        }
        if (arr.length < this.group.length) {
            throw new IllegalArgumentException("specified array is too small");
        }
        for (int i = 0; i < this.group.length; i++) {
            arr[i] = this.group[i];
        }
    }

    public boolean hasNext() {
        return this.index_i < this.group.length;
    }

    public int next() {
        int x;
        int x2 = this.group[this.index_i];
        int y = this.group[this.index_i + 1];
        if (x2 <= y) {
            x = x2 + this.count_j;
            if (x >= y) {
                this.count_j = 0;
                this.index_i += 2;
            } else {
                this.count_j++;
            }
        } else {
            x = x2 - this.count_j;
            if (x <= y) {
                this.count_j = 0;
                this.index_i += 2;
            } else {
                this.count_j++;
            }
        }
        return x;
    }

    public void set(int index_i, int count_j) {
        if (index_i < 0) {
            throw new IllegalArgumentException("index_i >= 0 required");
        }
        if (index_i % 2 == 1) {
            throw new IllegalArgumentException("index_i must be an even number");
        }
        if (count_j < 0) {
            throw new IllegalArgumentException("count_j >= 0 required");
        }
        this.index_i = index_i;
        this.count_j = count_j;
    }

    public int index_i() {
        return this.index_i;
    }

    public int count_j() {
        return this.count_j;
    }

    public void reset() {
        this.index_i = 0;
        this.count_j = 0;
    }

    public boolean isInRange(int min, int max) {
        for (int i = 0; i < this.group.length; i++) {
            if (this.group[i] < min || this.group[i] >= max) {
                return false;
            }
        }
        return true;
    }
}
