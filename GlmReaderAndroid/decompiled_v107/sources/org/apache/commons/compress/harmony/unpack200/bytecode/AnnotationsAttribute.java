package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public abstract class AnnotationsAttribute extends Attribute {

    /* loaded from: classes9.dex */
    public static class Annotation {
        private final CPUTF8[] elementNames;
        private final ElementValue[] elementValues;
        private int[] nameIndexes;
        private final int numPairs;
        private final CPUTF8 type;
        private int typeIndex;

        public Annotation(int numPairs, CPUTF8 type, CPUTF8[] elementNames, ElementValue[] elementValues) {
            this.numPairs = numPairs;
            this.type = type;
            this.elementNames = elementNames;
            this.elementValues = elementValues;
        }

        public List<Object> getClassFileEntries() {
            List<Object> entries = new ArrayList<>();
            for (int i = 0; i < this.elementNames.length; i++) {
                entries.add(this.elementNames[i]);
                entries.addAll(this.elementValues[i].getClassFileEntries());
            }
            entries.add(this.type);
            return entries;
        }

        public int getLength() {
            int length = 4;
            for (int i = 0; i < this.numPairs; i++) {
                length = length + 2 + this.elementValues[i].getLength();
            }
            return length;
        }

        public void resolve(ClassConstantPool pool) {
            this.type.resolve(pool);
            this.typeIndex = pool.indexOf(this.type);
            this.nameIndexes = new int[this.numPairs];
            for (int i = 0; i < this.elementNames.length; i++) {
                this.elementNames[i].resolve(pool);
                this.nameIndexes[i] = pool.indexOf(this.elementNames[i]);
                this.elementValues[i].resolve(pool);
            }
        }

        public void writeBody(DataOutputStream dos) throws IOException {
            dos.writeShort(this.typeIndex);
            dos.writeShort(this.numPairs);
            for (int i = 0; i < this.numPairs; i++) {
                dos.writeShort(this.nameIndexes[i]);
                this.elementValues[i].writeBody(dos);
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class ElementValue {
        private int constantValueIndex = -1;
        private final int tag;
        private final Object value;

        public ElementValue(int tag, Object value) {
            this.tag = tag;
            this.value = value;
        }

        public List<Object> getClassFileEntries() {
            List<Object> entries = new ArrayList<>(1);
            if (this.value instanceof CPNameAndType) {
                entries.add(((CPNameAndType) this.value).name);
                entries.add(((CPNameAndType) this.value).descriptor);
            } else if (this.value instanceof ClassFileEntry) {
                entries.add(this.value);
            } else if (this.value instanceof ElementValue[]) {
                ElementValue[] values = (ElementValue[]) this.value;
                for (ElementValue value2 : values) {
                    entries.addAll(value2.getClassFileEntries());
                }
            } else if (this.value instanceof Annotation) {
                entries.addAll(((Annotation) this.value).getClassFileEntries());
            }
            return entries;
        }

        public int getLength() {
            switch (this.tag) {
                case 64:
                    return ((Annotation) this.value).getLength() + 1;
                case 66:
                case 67:
                case 68:
                case 70:
                case 73:
                case 74:
                case 83:
                case 90:
                case 99:
                case 115:
                    return 3;
                case 91:
                    int length = 3;
                    ElementValue[] nestedValues = (ElementValue[]) this.value;
                    for (ElementValue nestedValue : nestedValues) {
                        length += nestedValue.getLength();
                    }
                    return length;
                case 101:
                    return 5;
                default:
                    return 0;
            }
        }

        public void resolve(ClassConstantPool pool) {
            if (this.value instanceof CPConstant) {
                ((CPConstant) this.value).resolve(pool);
                this.constantValueIndex = pool.indexOf((CPConstant) this.value);
                return;
            }
            if (this.value instanceof CPClass) {
                ((CPClass) this.value).resolve(pool);
                this.constantValueIndex = pool.indexOf((CPClass) this.value);
                return;
            }
            if (this.value instanceof CPUTF8) {
                ((CPUTF8) this.value).resolve(pool);
                this.constantValueIndex = pool.indexOf((CPUTF8) this.value);
                return;
            }
            if (this.value instanceof CPNameAndType) {
                ((CPNameAndType) this.value).resolve(pool);
                return;
            }
            if (this.value instanceof Annotation) {
                ((Annotation) this.value).resolve(pool);
                return;
            }
            if (this.value instanceof ElementValue[]) {
                ElementValue[] nestedValues = (ElementValue[]) this.value;
                for (ElementValue nestedValue : nestedValues) {
                    nestedValue.resolve(pool);
                }
            }
        }

        public void writeBody(DataOutputStream dos) throws IOException {
            dos.writeByte(this.tag);
            if (this.constantValueIndex != -1) {
                dos.writeShort(this.constantValueIndex);
                return;
            }
            if (this.value instanceof CPNameAndType) {
                ((CPNameAndType) this.value).writeBody(dos);
                return;
            }
            if (this.value instanceof Annotation) {
                ((Annotation) this.value).writeBody(dos);
                return;
            }
            if (this.value instanceof ElementValue[]) {
                ElementValue[] nestedValues = (ElementValue[]) this.value;
                dos.writeShort(nestedValues.length);
                for (ElementValue nestedValue : nestedValues) {
                    nestedValue.writeBody(dos);
                }
                return;
            }
            throw new Error("");
        }
    }

    public AnnotationsAttribute(CPUTF8 attributeName) {
        super(attributeName);
    }
}
