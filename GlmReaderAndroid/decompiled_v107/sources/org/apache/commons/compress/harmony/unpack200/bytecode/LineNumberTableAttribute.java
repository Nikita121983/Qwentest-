package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

/* loaded from: classes9.dex */
public class LineNumberTableAttribute extends BCIRenumberedAttribute {
    private static CPUTF8 attributeName;
    private final int lineNumberTableLength;
    private final int[] lineNumbers;
    private final int[] startPcs;

    public static void setAttributeName(CPUTF8 cpUTF8Value) {
        attributeName = cpUTF8Value;
    }

    public LineNumberTableAttribute(int lineNumberTableLength, int[] startPcs, int[] lineNumbers) {
        super(attributeName);
        this.lineNumberTableLength = lineNumberTableLength;
        this.startPcs = startPcs;
        this.lineNumbers = lineNumbers;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute, org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected int getLength() {
        return (this.lineNumberTableLength * 4) + 2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{getAttributeName()};
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute
    protected int[] getStartPCs() {
        return this.startPcs;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "LineNumberTable: " + this.lineNumberTableLength + " lines";
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute, org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected void writeBody(DataOutputStream dos) throws IOException {
        dos.writeShort(this.lineNumberTableLength);
        for (int i = 0; i < this.lineNumberTableLength; i++) {
            dos.writeShort(this.startPcs[i]);
            dos.writeShort(this.lineNumbers[i]);
        }
    }
}
