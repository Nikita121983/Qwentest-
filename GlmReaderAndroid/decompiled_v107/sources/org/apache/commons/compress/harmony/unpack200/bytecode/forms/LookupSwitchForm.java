package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

/* loaded from: classes9.dex */
public class LookupSwitchForm extends SwitchForm {
    public LookupSwitchForm(int opcode, String name) {
        super(opcode, name);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ByteCodeForm
    public void setByteCodeOperands(ByteCode byteCode, final OperandManager operandManager, int codeLength) {
        int caseCount = operandManager.nextCaseCount();
        int defaultPc = operandManager.nextLabel();
        int[] caseValues = new int[caseCount];
        Arrays.setAll(caseValues, new IntUnaryOperator() { // from class: org.apache.commons.compress.harmony.unpack200.bytecode.forms.LookupSwitchForm$$ExternalSyntheticLambda0
            @Override // java.util.function.IntUnaryOperator
            public final int applyAsInt(int i) {
                int nextCaseValues;
                nextCaseValues = OperandManager.this.nextCaseValues();
                return nextCaseValues;
            }
        });
        int[] casePcs = new int[caseCount];
        Arrays.setAll(casePcs, new IntUnaryOperator() { // from class: org.apache.commons.compress.harmony.unpack200.bytecode.forms.LookupSwitchForm$$ExternalSyntheticLambda1
            @Override // java.util.function.IntUnaryOperator
            public final int applyAsInt(int i) {
                int nextLabel;
                nextLabel = OperandManager.this.nextLabel();
                return nextLabel;
            }
        });
        int[] labelsArray = new int[caseCount + 1];
        int i = 0;
        labelsArray[0] = defaultPc;
        System.arraycopy(casePcs, 0, labelsArray, 1, (caseCount + 1) - 1);
        byteCode.setByteCodeTargets(labelsArray);
        int padLength = 3 - (codeLength % 4);
        int rewriteSize = padLength + 1 + 4 + 4 + (caseValues.length * 4) + (casePcs.length * 4);
        int[] newRewrite = new int[rewriteSize];
        int rewriteIndex = 0 + 1;
        newRewrite[0] = byteCode.getOpcode();
        int index = 0;
        while (index < padLength) {
            newRewrite[rewriteIndex] = 0;
            index++;
            rewriteIndex++;
        }
        int index2 = rewriteIndex + 1;
        int i2 = -1;
        newRewrite[rewriteIndex] = -1;
        int rewriteIndex2 = index2 + 1;
        newRewrite[index2] = -1;
        int rewriteIndex3 = rewriteIndex2 + 1;
        newRewrite[rewriteIndex2] = -1;
        int rewriteIndex4 = rewriteIndex3 + 1;
        newRewrite[rewriteIndex3] = -1;
        setRewrite4Bytes(caseValues.length, rewriteIndex4, newRewrite);
        int rewriteIndex5 = rewriteIndex4 + 4;
        int length = caseValues.length;
        while (i < length) {
            int i3 = i2;
            int caseValue = caseValues[i];
            setRewrite4Bytes(caseValue, rewriteIndex5, newRewrite);
            int rewriteIndex6 = rewriteIndex5 + 4;
            int rewriteIndex7 = rewriteIndex6 + 1;
            newRewrite[rewriteIndex6] = i3;
            int rewriteIndex8 = rewriteIndex7 + 1;
            newRewrite[rewriteIndex7] = i3;
            int rewriteIndex9 = rewriteIndex8 + 1;
            newRewrite[rewriteIndex8] = i3;
            rewriteIndex5 = rewriteIndex9 + 1;
            newRewrite[rewriteIndex9] = i3;
            i++;
            i2 = i3;
        }
        byteCode.setRewrite(newRewrite);
    }
}
