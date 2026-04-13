package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import kotlin.UByte;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.Segment;

/* loaded from: classes9.dex */
public class CodeAttribute extends BCIRenumberedAttribute {
    private static CPUTF8 attributeName;
    public List<Attribute> attributes;
    public List<Integer> byteCodeOffsets;
    public List<ByteCode> byteCodes;
    public int codeLength;
    public List<ExceptionTableEntry> exceptionTable;
    public int maxLocals;
    public int maxStack;

    public static void setAttributeName(CPUTF8 attributeName2) {
        attributeName = attributeName2;
    }

    public CodeAttribute(int maxStack, int maxLocals, byte[] codePacked, Segment segment, OperandManager operandManager, List<ExceptionTableEntry> exceptionTable) throws Pack200Exception {
        super(attributeName);
        this.attributes = new ArrayList();
        this.byteCodeOffsets = new ArrayList();
        this.byteCodes = new ArrayList();
        this.maxLocals = maxLocals;
        this.maxStack = maxStack;
        this.codeLength = 0;
        this.exceptionTable = exceptionTable;
        this.byteCodeOffsets.add(0);
        int byteCodeIndex = 0;
        int i = 0;
        while (i < codePacked.length) {
            ByteCode byteCode = ByteCode.getByteCode(codePacked[i] & UByte.MAX_VALUE);
            byteCode.setByteCodeIndex(byteCodeIndex);
            byteCodeIndex++;
            byteCode.extractOperands(operandManager, segment, this.codeLength);
            this.byteCodes.add(byteCode);
            this.codeLength += byteCode.getLength();
            int lastBytecodePosition = this.byteCodeOffsets.get(this.byteCodeOffsets.size() - 1).intValue();
            if (byteCode.hasMultipleByteCodes()) {
                this.byteCodeOffsets.add(Integer.valueOf(lastBytecodePosition + 1));
                byteCodeIndex++;
            }
            if (i < codePacked.length - 1) {
                this.byteCodeOffsets.add(Integer.valueOf(byteCode.getLength() + lastBytecodePosition));
            }
            if (byteCode.getOpcode() == 196) {
                i++;
            }
            i++;
        }
        Iterator<ByteCode> it = this.byteCodes.iterator();
        while (it.hasNext()) {
            it.next().applyByteCodeTargetFixup(this);
        }
    }

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
        if (attribute instanceof LocalVariableTableAttribute) {
            ((LocalVariableTableAttribute) attribute).setCodeLength(this.codeLength);
        }
        if (attribute instanceof LocalVariableTypeTableAttribute) {
            ((LocalVariableTypeTableAttribute) attribute).setCodeLength(this.codeLength);
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute, org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected int getLength() {
        int attributesSize = 0;
        for (Attribute attribute : this.attributes) {
            attributesSize += attribute.getLengthIncludingHeader();
        }
        return this.codeLength + 8 + 2 + (this.exceptionTable.size() * 8) + 2 + attributesSize;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        List<ClassFileEntry> nestedEntries = new ArrayList<>(this.attributes.size() + this.byteCodes.size() + 10);
        nestedEntries.add(getAttributeName());
        nestedEntries.addAll(this.byteCodes);
        nestedEntries.addAll(this.attributes);
        for (ExceptionTableEntry entry : this.exceptionTable) {
            CPClass catchType = entry.getCatchType();
            if (catchType != null) {
                nestedEntries.add(catchType);
            }
        }
        return (ClassFileEntry[]) nestedEntries.toArray(NONE);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute
    protected int[] getStartPCs() {
        return null;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute
    public void renumber(final List<Integer> byteCodeOffsets) {
        this.exceptionTable.forEach(new Consumer() { // from class: org.apache.commons.compress.harmony.unpack200.bytecode.CodeAttribute$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((ExceptionTableEntry) obj).renumber(byteCodeOffsets);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(final ClassConstantPool pool) {
        super.resolve(pool);
        this.attributes.forEach(new Consumer() { // from class: org.apache.commons.compress.harmony.unpack200.bytecode.CodeAttribute$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Attribute) obj).resolve(ClassConstantPool.this);
            }
        });
        this.byteCodes.forEach(new Consumer() { // from class: org.apache.commons.compress.harmony.unpack200.bytecode.CodeAttribute$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((ByteCode) obj).resolve(ClassConstantPool.this);
            }
        });
        this.exceptionTable.forEach(new Consumer() { // from class: org.apache.commons.compress.harmony.unpack200.bytecode.CodeAttribute$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((ExceptionTableEntry) obj).resolve(ClassConstantPool.this);
            }
        });
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "Code: " + getLength() + " bytes";
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute, org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected void writeBody(DataOutputStream dos) throws IOException {
        dos.writeShort(this.maxStack);
        dos.writeShort(this.maxLocals);
        dos.writeInt(this.codeLength);
        for (ByteCode byteCode : this.byteCodes) {
            byteCode.write(dos);
        }
        dos.writeShort(this.exceptionTable.size());
        for (ExceptionTableEntry entry : this.exceptionTable) {
            entry.write(dos);
        }
        dos.writeShort(this.attributes.size());
        for (Attribute attribute : this.attributes) {
            attribute.write(dos);
        }
    }
}
