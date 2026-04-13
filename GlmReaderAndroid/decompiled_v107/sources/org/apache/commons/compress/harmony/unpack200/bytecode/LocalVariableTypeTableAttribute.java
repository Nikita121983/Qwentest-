package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;

/* loaded from: classes9.dex */
public class LocalVariableTypeTableAttribute extends BCIRenumberedAttribute {
    private static CPUTF8 attributeName;
    private int codeLength;
    private final int[] indexes;
    private final int[] lengths;
    private final int localVariableTypeTableLength;
    private int[] nameIndexes;
    private final CPUTF8[] names;
    private int[] signatureIndexes;
    private final CPUTF8[] signatures;
    private final int[] startPcs;

    public static void setAttributeName(CPUTF8 cpUTF8Value) {
        attributeName = cpUTF8Value;
    }

    public LocalVariableTypeTableAttribute(int localVariableTypeTableLength, int[] startPcs, int[] lengths, CPUTF8[] names, CPUTF8[] signatures, int[] indexes) {
        super(attributeName);
        this.localVariableTypeTableLength = localVariableTypeTableLength;
        this.startPcs = startPcs;
        this.lengths = lengths;
        this.names = names;
        this.signatures = signatures;
        this.indexes = indexes;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute, org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected int getLength() {
        return (this.localVariableTypeTableLength * 10) + 2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        List<CPUTF8> nestedEntries = new ArrayList<>();
        nestedEntries.add(getAttributeName());
        for (int i = 0; i < this.localVariableTypeTableLength; i++) {
            nestedEntries.add(this.names[i]);
            nestedEntries.add(this.signatures[i]);
        }
        return (ClassFileEntry[]) nestedEntries.toArray(NONE);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute
    protected int[] getStartPCs() {
        return this.startPcs;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute
    public void renumber(List<Integer> byteCodeOffsets) throws Pack200Exception {
        int revisedLength;
        int[] unrenumberedStartPcs = Arrays.copyOf(this.startPcs, this.startPcs.length);
        super.renumber(byteCodeOffsets);
        int maxSize = this.codeLength;
        for (int index = 0; index < this.lengths.length; index++) {
            int startPc = this.startPcs[index];
            int encodedLength = this.lengths[index];
            int indexOfStartPC = unrenumberedStartPcs[index];
            int stopIndex = indexOfStartPC + encodedLength;
            if (stopIndex < 0) {
                throw new Pack200Exception("Error renumbering bytecode indexes");
            }
            if (stopIndex == byteCodeOffsets.size()) {
                revisedLength = maxSize - startPc;
            } else {
                int stopValue = byteCodeOffsets.get(stopIndex).intValue();
                revisedLength = stopValue - startPc;
            }
            this.lengths[index] = revisedLength;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool pool) {
        super.resolve(pool);
        this.nameIndexes = new int[this.localVariableTypeTableLength];
        this.signatureIndexes = new int[this.localVariableTypeTableLength];
        for (int i = 0; i < this.localVariableTypeTableLength; i++) {
            this.names[i].resolve(pool);
            this.signatures[i].resolve(pool);
            this.nameIndexes[i] = pool.indexOf(this.names[i]);
            this.signatureIndexes[i] = pool.indexOf(this.signatures[i]);
        }
    }

    public void setCodeLength(int length) {
        this.codeLength = length;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "LocalVariableTypeTable: " + this.localVariableTypeTableLength + " varaibles";
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute, org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected void writeBody(DataOutputStream dos) throws IOException {
        dos.writeShort(this.localVariableTypeTableLength);
        for (int i = 0; i < this.localVariableTypeTableLength; i++) {
            dos.writeShort(this.startPcs[i]);
            dos.writeShort(this.lengths[i]);
            dos.writeShort(this.nameIndexes[i]);
            dos.writeShort(this.signatureIndexes[i]);
            dos.writeShort(this.indexes[i]);
        }
    }
}
