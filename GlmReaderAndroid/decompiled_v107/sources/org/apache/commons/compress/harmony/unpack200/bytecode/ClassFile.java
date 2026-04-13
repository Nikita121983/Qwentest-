package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

/* loaded from: classes9.dex */
public class ClassFile {
    private static final int MAGIC = -889275714;
    public int accessFlags;
    public Attribute[] attributes;
    public ClassFileEntry[] fields;
    public int[] interfaces;
    public int major;
    public ClassFileEntry[] methods;
    public int minor;
    public ClassConstantPool pool = new ClassConstantPool();
    public int superClass;
    public int thisClass;

    public void write(DataOutputStream dos) throws IOException {
        dos.writeInt(MAGIC);
        dos.writeShort(this.minor);
        dos.writeShort(this.major);
        dos.writeShort(this.pool.size() + 1);
        int i = 1;
        while (i <= this.pool.size()) {
            ConstantPoolEntry entry = (ConstantPoolEntry) this.pool.get(i);
            entry.doWrite(dos);
            if (entry.getTag() == 6 || entry.getTag() == 5) {
                i++;
            }
            i++;
        }
        int i2 = this.accessFlags;
        dos.writeShort(i2);
        dos.writeShort(this.thisClass);
        dos.writeShort(this.superClass);
        dos.writeShort(this.interfaces.length);
        for (int element : this.interfaces) {
            dos.writeShort(element);
        }
        dos.writeShort(this.fields.length);
        for (ClassFileEntry field : this.fields) {
            field.write(dos);
        }
        dos.writeShort(this.methods.length);
        for (ClassFileEntry method : this.methods) {
            method.write(dos);
        }
        dos.writeShort(this.attributes.length);
        for (Attribute attribute : this.attributes) {
            attribute.write(dos);
        }
    }
}
