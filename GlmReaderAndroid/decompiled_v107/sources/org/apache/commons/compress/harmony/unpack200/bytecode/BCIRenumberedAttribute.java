package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntUnaryOperator;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;

/* loaded from: classes9.dex */
public abstract class BCIRenumberedAttribute extends Attribute {
    protected boolean renumbered;

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected abstract int getLength();

    protected abstract int[] getStartPCs();

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public abstract String toString();

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected abstract void writeBody(DataOutputStream dataOutputStream) throws IOException;

    public BCIRenumberedAttribute(CPUTF8 attributeName) {
        super(attributeName);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    public boolean hasBCIRenumbering() {
        return true;
    }

    public void renumber(final List<Integer> byteCodeOffsets) throws Pack200Exception {
        if (this.renumbered) {
            throw new Error("Trying to renumber a line number table that has already been renumbered");
        }
        this.renumbered = true;
        final int[] startPCs = getStartPCs();
        Arrays.setAll(startPCs, new IntUnaryOperator() { // from class: org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute$$ExternalSyntheticLambda0
            @Override // java.util.function.IntUnaryOperator
            public final int applyAsInt(int i) {
                int intValue;
                intValue = ((Integer) byteCodeOffsets.get(startPCs[i])).intValue();
                return intValue;
            }
        });
    }
}
