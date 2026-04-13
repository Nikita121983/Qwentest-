package org.apache.commons.compress.harmony.unpack200.bytecode;

import org.apache.commons.compress.harmony.unpack200.Segment;
import org.apache.commons.compress.harmony.unpack200.SegmentConstantPool;

/* loaded from: classes9.dex */
public class OperandManager {
    int[] bcByte;
    int bcByteIndex;
    int[] bcCaseCount;
    int bcCaseCountIndex;
    int[] bcCaseValue;
    int bcCaseValueIndex;
    int[] bcClassRef;
    int bcClassRefIndex;
    int[] bcDoubleRef;
    int bcDoubleRefIndex;
    int[] bcFieldRef;
    int bcFieldRefIndex;
    int[] bcFloatRef;
    int bcFloatRefIndex;
    int[] bcIMethodRef;
    int bcIMethodRefIndex;
    int[] bcInitRef;
    int bcInitRefIndex;
    int[] bcIntRef;
    int bcIntRefIndex;
    int[] bcLabel;
    int bcLabelIndex;
    int[] bcLocal;
    int bcLocalIndex;
    int[] bcLongRef;
    int bcLongRefIndex;
    int[] bcMethodRef;
    int bcMethodRefIndex;
    int[] bcShort;
    int bcShortIndex;
    int[] bcStringRef;
    int bcStringRefIndex;
    int[] bcSuperField;
    int bcSuperFieldIndex;
    int[] bcSuperMethod;
    int bcSuperMethodIndex;
    int[] bcThisField;
    int bcThisFieldIndex;
    int[] bcThisMethod;
    int bcThisMethodIndex;
    String currentClass;
    String newClass;
    Segment segment;
    String superClass;
    int wideByteCodeIndex;
    int[] wideByteCodes;

    public OperandManager(int[] bcCaseCount, int[] bcCaseValue, int[] bcByte, int[] bcShort, int[] bcLocal, int[] bcLabel, int[] bcIntRef, int[] bcFloatRef, int[] bcLongRef, int[] bcDoubleRef, int[] bcStringRef, int[] bcClassRef, int[] bcFieldRef, int[] bcMethodRef, int[] bcIMethodRef, int[] bcThisField, int[] bcSuperField, int[] bcThisMethod, int[] bcSuperMethod, int[] bcInitRef, int[] wideByteCodes) {
        this.bcCaseCount = bcCaseCount;
        this.bcCaseValue = bcCaseValue;
        this.bcByte = bcByte;
        this.bcShort = bcShort;
        this.bcLocal = bcLocal;
        this.bcLabel = bcLabel;
        this.bcIntRef = bcIntRef;
        this.bcFloatRef = bcFloatRef;
        this.bcLongRef = bcLongRef;
        this.bcDoubleRef = bcDoubleRef;
        this.bcStringRef = bcStringRef;
        this.bcClassRef = bcClassRef;
        this.bcFieldRef = bcFieldRef;
        this.bcMethodRef = bcMethodRef;
        this.bcIMethodRef = bcIMethodRef;
        this.bcThisField = bcThisField;
        this.bcSuperField = bcSuperField;
        this.bcThisMethod = bcThisMethod;
        this.bcSuperMethod = bcSuperMethod;
        this.bcInitRef = bcInitRef;
        this.wideByteCodes = wideByteCodes;
    }

    public String getCurrentClass() {
        if (this.currentClass == null) {
            throw new Error("Current class not set yet");
        }
        return this.currentClass;
    }

    public String getNewClass() {
        if (this.newClass == null) {
            throw new Error("New class not set yet");
        }
        return this.newClass;
    }

    public String getSuperClass() {
        if (this.superClass == null) {
            throw new Error("SuperClass not set yet");
        }
        return this.superClass;
    }

    public SegmentConstantPool globalConstantPool() {
        return this.segment.getConstantPool();
    }

    public int nextByte() {
        int[] iArr = this.bcByte;
        int i = this.bcByteIndex;
        this.bcByteIndex = i + 1;
        return iArr[i];
    }

    public int nextCaseCount() {
        int[] iArr = this.bcCaseCount;
        int i = this.bcCaseCountIndex;
        this.bcCaseCountIndex = i + 1;
        return iArr[i];
    }

    public int nextCaseValues() {
        int[] iArr = this.bcCaseValue;
        int i = this.bcCaseValueIndex;
        this.bcCaseValueIndex = i + 1;
        return iArr[i];
    }

    public int nextClassRef() {
        int[] iArr = this.bcClassRef;
        int i = this.bcClassRefIndex;
        this.bcClassRefIndex = i + 1;
        return iArr[i];
    }

    public int nextDoubleRef() {
        int[] iArr = this.bcDoubleRef;
        int i = this.bcDoubleRefIndex;
        this.bcDoubleRefIndex = i + 1;
        return iArr[i];
    }

    public int nextFieldRef() {
        int[] iArr = this.bcFieldRef;
        int i = this.bcFieldRefIndex;
        this.bcFieldRefIndex = i + 1;
        return iArr[i];
    }

    public int nextFloatRef() {
        int[] iArr = this.bcFloatRef;
        int i = this.bcFloatRefIndex;
        this.bcFloatRefIndex = i + 1;
        return iArr[i];
    }

    public int nextIMethodRef() {
        int[] iArr = this.bcIMethodRef;
        int i = this.bcIMethodRefIndex;
        this.bcIMethodRefIndex = i + 1;
        return iArr[i];
    }

    public int nextInitRef() {
        int[] iArr = this.bcInitRef;
        int i = this.bcInitRefIndex;
        this.bcInitRefIndex = i + 1;
        return iArr[i];
    }

    public int nextIntRef() {
        int[] iArr = this.bcIntRef;
        int i = this.bcIntRefIndex;
        this.bcIntRefIndex = i + 1;
        return iArr[i];
    }

    public int nextLabel() {
        int[] iArr = this.bcLabel;
        int i = this.bcLabelIndex;
        this.bcLabelIndex = i + 1;
        return iArr[i];
    }

    public int nextLocal() {
        int[] iArr = this.bcLocal;
        int i = this.bcLocalIndex;
        this.bcLocalIndex = i + 1;
        return iArr[i];
    }

    public int nextLongRef() {
        int[] iArr = this.bcLongRef;
        int i = this.bcLongRefIndex;
        this.bcLongRefIndex = i + 1;
        return iArr[i];
    }

    public int nextMethodRef() {
        int[] iArr = this.bcMethodRef;
        int i = this.bcMethodRefIndex;
        this.bcMethodRefIndex = i + 1;
        return iArr[i];
    }

    public int nextShort() {
        int[] iArr = this.bcShort;
        int i = this.bcShortIndex;
        this.bcShortIndex = i + 1;
        return iArr[i];
    }

    public int nextStringRef() {
        int[] iArr = this.bcStringRef;
        int i = this.bcStringRefIndex;
        this.bcStringRefIndex = i + 1;
        return iArr[i];
    }

    public int nextSuperFieldRef() {
        int[] iArr = this.bcSuperField;
        int i = this.bcSuperFieldIndex;
        this.bcSuperFieldIndex = i + 1;
        return iArr[i];
    }

    public int nextSuperMethodRef() {
        int[] iArr = this.bcSuperMethod;
        int i = this.bcSuperMethodIndex;
        this.bcSuperMethodIndex = i + 1;
        return iArr[i];
    }

    public int nextThisFieldRef() {
        int[] iArr = this.bcThisField;
        int i = this.bcThisFieldIndex;
        this.bcThisFieldIndex = i + 1;
        return iArr[i];
    }

    public int nextThisMethodRef() {
        int[] iArr = this.bcThisMethod;
        int i = this.bcThisMethodIndex;
        this.bcThisMethodIndex = i + 1;
        return iArr[i];
    }

    public int nextWideByteCode() {
        int[] iArr = this.wideByteCodes;
        int i = this.wideByteCodeIndex;
        this.wideByteCodeIndex = i + 1;
        return iArr[i];
    }

    public void setCurrentClass(String string) {
        this.currentClass = string;
    }

    public void setNewClass(String string) {
        this.newClass = string;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public void setSuperClass(String string) {
        this.superClass = string;
    }
}
