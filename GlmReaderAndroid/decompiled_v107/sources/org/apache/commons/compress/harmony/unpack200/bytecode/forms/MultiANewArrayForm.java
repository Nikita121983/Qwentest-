package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

/* loaded from: classes9.dex */
public class MultiANewArrayForm extends ClassRefForm {
    public MultiANewArrayForm(int opcode, String name, int[] rewrite) {
        super(opcode, name, rewrite);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ReferenceForm, org.apache.commons.compress.harmony.unpack200.bytecode.forms.ByteCodeForm
    public void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int codeLength) throws Pack200Exception {
        super.setByteCodeOperands(byteCode, operandManager, codeLength);
        int dimension = operandManager.nextByte();
        byteCode.setOperandByte(dimension, 2);
    }
}
