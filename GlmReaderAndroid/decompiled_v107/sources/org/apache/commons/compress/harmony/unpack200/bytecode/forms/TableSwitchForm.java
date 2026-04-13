package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

/* loaded from: classes9.dex */
public class TableSwitchForm extends SwitchForm {
    public TableSwitchForm(int opcode, String name) {
        super(opcode, name);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ByteCodeForm
    public void setByteCodeOperands(ByteCode byteCode, final OperandManager operandManager, int codeLength) {
        int caseCount = operandManager.nextCaseCount();
        int defaultPc = operandManager.nextLabel();
        int caseValue = operandManager.nextCaseValues();
        int[] casePcs = new int[caseCount];
        Arrays.setAll(casePcs, new IntUnaryOperator() { // from class: org.apache.commons.compress.harmony.unpack200.bytecode.forms.TableSwitchForm$$ExternalSyntheticLambda0
            @Override // java.util.function.IntUnaryOperator
            public final int applyAsInt(int i) {
                int nextLabel;
                nextLabel = OperandManager.this.nextLabel();
                return nextLabel;
            }
        });
        int[] labelsArray = new int[caseCount + 1];
        labelsArray[0] = defaultPc;
        System.arraycopy(casePcs, 0, labelsArray, 1, (caseCount + 1) - 1);
        byteCode.setByteCodeTargets(labelsArray);
        int highValue = (caseValue + caseCount) - 1;
        int padLength = 3 - (codeLength % 4);
        int rewriteSize = padLength + 1 + 4 + 4 + 4 + (casePcs.length * 4);
        int[] newRewrite = new int[rewriteSize];
        int rewriteIndex = 0 + 1;
        newRewrite[0] = byteCode.getOpcode();
        int index = 0;
        while (index < padLength) {
            newRewrite[rewriteIndex] = 0;
            index++;
            rewriteIndex++;
        }
        int rewriteIndex2 = rewriteIndex + 1;
        newRewrite[rewriteIndex] = -1;
        int rewriteIndex3 = rewriteIndex2 + 1;
        newRewrite[rewriteIndex2] = -1;
        int rewriteIndex4 = rewriteIndex3 + 1;
        newRewrite[rewriteIndex3] = -1;
        int rewriteIndex5 = rewriteIndex4 + 1;
        newRewrite[rewriteIndex4] = -1;
        setRewrite4Bytes(caseValue, rewriteIndex5, newRewrite);
        int rewriteIndex6 = rewriteIndex5 + 4;
        setRewrite4Bytes(highValue, rewriteIndex6, newRewrite);
        int rewriteIndex7 = rewriteIndex6 + 4;
        for (int index2 = 0; index2 < caseCount; index2++) {
            int rewriteIndex8 = rewriteIndex7 + 1;
            newRewrite[rewriteIndex7] = -1;
            int rewriteIndex9 = rewriteIndex8 + 1;
            newRewrite[rewriteIndex8] = -1;
            int rewriteIndex10 = rewriteIndex9 + 1;
            newRewrite[rewriteIndex9] = -1;
            rewriteIndex7 = rewriteIndex10 + 1;
            newRewrite[rewriteIndex10] = -1;
        }
        byteCode.setRewrite(newRewrite);
    }
}
