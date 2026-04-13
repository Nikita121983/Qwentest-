package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

/* loaded from: classes9.dex */
public class CPInteger extends CPConstantNumber {
    public CPInteger(Integer value, int globalIndex) {
        super((byte) 3, value, globalIndex);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "Integer: " + getValue();
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry
    protected void writeBody(DataOutputStream dos) throws IOException {
        dos.writeInt(getNumber().intValue());
    }
}
