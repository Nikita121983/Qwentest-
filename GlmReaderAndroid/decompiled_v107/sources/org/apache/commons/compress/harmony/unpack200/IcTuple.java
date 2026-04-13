package org.apache.commons.compress.harmony.unpack200;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: classes9.dex */
public class IcTuple {
    public static final int NESTED_CLASS_FLAG = 65536;
    protected String C;
    protected String C2;
    protected int F;
    protected String N;
    private boolean anonymous;
    private final int c2Index;
    private final int cIndex;
    private int cachedHashCode;
    private String cachedOuterClassString;
    private String cachedSimpleClassName;
    private boolean hashCodeComputed;
    private boolean initialized;
    private final int nIndex;
    private boolean outerIsAnonymous;
    private boolean predictOuter;
    private boolean predictSimple;
    private final int tIndex;
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    static final IcTuple[] EMPTY_ARRAY = new IcTuple[0];
    private boolean member = true;
    private int cachedOuterClassIndex = -1;
    private int cachedSimpleClassNameIndex = -1;

    public IcTuple(String C, int F, String C2, String N, int cIndex, int c2Index, int nIndex, int tIndex) {
        this.C = C;
        this.F = F;
        this.C2 = C2;
        this.N = N;
        this.cIndex = cIndex;
        this.c2Index = c2Index;
        this.nIndex = nIndex;
        this.tIndex = tIndex;
        if (N == null) {
            this.predictSimple = true;
        }
        if (C2 == null) {
            this.predictOuter = true;
        }
        initializeClassStrings();
    }

    private boolean computeOuterIsAnonymous() {
        String[] result = innerBreakAtDollar(this.cachedOuterClassString);
        if (result.length == 0) {
            throw new Error("Should have an outer before checking if it's anonymous");
        }
        for (String element : result) {
            if (isAllDigits(element)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object object) {
        if (object == null || object.getClass() != getClass()) {
            return false;
        }
        IcTuple other = (IcTuple) object;
        return Objects.equals(this.C, other.C) && Objects.equals(this.C2, other.C2) && Objects.equals(this.N, other.N);
    }

    private void generateHashCode() {
        this.hashCodeComputed = true;
        this.cachedHashCode = 17;
        if (this.C != null) {
            this.cachedHashCode = this.C.hashCode();
        }
        if (this.C2 != null) {
            this.cachedHashCode = this.C2.hashCode();
        }
        if (this.N != null) {
            this.cachedHashCode = this.N.hashCode();
        }
    }

    public String getC() {
        return this.C;
    }

    public String getC2() {
        return this.C2;
    }

    public int getF() {
        return this.F;
    }

    public String getN() {
        return this.N;
    }

    public int getTupleIndex() {
        return this.tIndex;
    }

    public int hashCode() {
        if (!this.hashCodeComputed) {
            generateHashCode();
        }
        return this.cachedHashCode;
    }

    private void initializeClassStrings() {
        if (this.initialized) {
            return;
        }
        this.initialized = true;
        if (!this.predictSimple) {
            this.cachedSimpleClassName = this.N;
        }
        if (!this.predictOuter) {
            this.cachedOuterClassString = this.C2;
        }
        String[] nameComponents = innerBreakAtDollar(this.C);
        int length = nameComponents.length;
        int length2 = nameComponents.length;
        if (nameComponents.length >= 2) {
            int lastPosition = nameComponents.length - 1;
            this.cachedSimpleClassName = nameComponents[lastPosition];
            this.cachedOuterClassString = "";
            for (int index = 0; index < lastPosition; index++) {
                this.cachedOuterClassString += nameComponents[index];
                if (isAllDigits(nameComponents[index])) {
                    this.member = false;
                }
                if (index + 1 != lastPosition) {
                    this.cachedOuterClassString += '$';
                }
            }
            if (!this.predictSimple) {
                this.cachedSimpleClassName = this.N;
                this.cachedSimpleClassNameIndex = this.nIndex;
            }
            if (!this.predictOuter) {
                this.cachedOuterClassString = this.C2;
                this.cachedOuterClassIndex = this.c2Index;
            }
            if (isAllDigits(this.cachedSimpleClassName)) {
                this.anonymous = true;
                this.member = false;
                if (nestedExplicitFlagSet()) {
                    this.member = true;
                }
            }
            this.outerIsAnonymous = computeOuterIsAnonymous();
        }
    }

    public String[] innerBreakAtDollar(String className) {
        List<String> resultList = new ArrayList<>();
        int start = 0;
        int index = 0;
        while (index < className.length()) {
            if (className.charAt(index) <= '$') {
                resultList.add(className.substring(start, index));
                start = index + 1;
            }
            index++;
            if (index >= className.length()) {
                resultList.add(className.substring(start));
            }
        }
        return (String[]) resultList.toArray(EMPTY_STRING_ARRAY);
    }

    private boolean isAllDigits(String nameString) {
        if (nameString == null) {
            return false;
        }
        for (int index = 0; index < nameString.length(); index++) {
            if (!Character.isDigit(nameString.charAt(index))) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnonymous() {
        return this.anonymous;
    }

    public boolean isMember() {
        return this.member;
    }

    public boolean nestedExplicitFlagSet() {
        return (this.F & 65536) == 65536;
    }

    public boolean nullSafeEquals(String stringOne, String stringTwo) {
        if (stringOne == null) {
            return stringTwo == null;
        }
        return stringOne.equals(stringTwo);
    }

    public int outerClassIndex() {
        return this.cachedOuterClassIndex;
    }

    public String outerClassString() {
        return this.cachedOuterClassString;
    }

    public boolean outerIsAnonymous() {
        return this.outerIsAnonymous;
    }

    public boolean predicted() {
        return this.predictOuter || this.predictSimple;
    }

    public String simpleClassName() {
        return this.cachedSimpleClassName;
    }

    public int simpleClassNameIndex() {
        return this.cachedSimpleClassNameIndex;
    }

    public int thisClassIndex() {
        if (predicted()) {
            return this.cIndex;
        }
        return -1;
    }

    public String thisClassString() {
        if (predicted()) {
            return this.C;
        }
        return this.C2 + "$" + this.N;
    }

    public String toString() {
        return "IcTuple (" + simpleClassName() + " in " + outerClassString() + ')';
    }
}
