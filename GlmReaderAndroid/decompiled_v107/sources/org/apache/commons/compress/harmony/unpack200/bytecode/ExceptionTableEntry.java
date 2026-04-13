package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

/* loaded from: classes9.dex */
public class ExceptionTableEntry {
    private final CPClass catchType;
    private int catchTypeIndex;
    private final int endPC;
    private int endPcRenumbered;
    private final int handlerPC;
    private int handlerPcRenumbered;
    private final int startPC;
    private int startPcRenumbered;

    public ExceptionTableEntry(int startPC, int endPC, int handlerPC, CPClass catchType) {
        this.startPC = startPC;
        this.endPC = endPC;
        this.handlerPC = handlerPC;
        this.catchType = catchType;
    }

    public CPClass getCatchType() {
        return this.catchType;
    }

    public void renumber(List<Integer> byteCodeOffsets) {
        this.startPcRenumbered = byteCodeOffsets.get(this.startPC).intValue();
        int endPcIndex = this.startPC + this.endPC;
        this.endPcRenumbered = byteCodeOffsets.get(endPcIndex).intValue();
        int handlerPcIndex = this.handlerPC + endPcIndex;
        this.handlerPcRenumbered = byteCodeOffsets.get(handlerPcIndex).intValue();
    }

    public void resolve(ClassConstantPool pool) {
        if (this.catchType == null) {
            this.catchTypeIndex = 0;
        } else {
            this.catchType.resolve(pool);
            this.catchTypeIndex = pool.indexOf(this.catchType);
        }
    }

    public void write(DataOutputStream dos) throws IOException {
        dos.writeShort(this.startPcRenumbered);
        dos.writeShort(this.endPcRenumbered);
        dos.writeShort(this.handlerPcRenumbered);
        dos.writeShort(this.catchTypeIndex);
    }
}
