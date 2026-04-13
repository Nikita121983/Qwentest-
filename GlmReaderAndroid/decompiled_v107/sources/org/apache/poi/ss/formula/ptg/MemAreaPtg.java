package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class MemAreaPtg extends OperandPtg {
    private static final int SIZE = 7;
    public static final short sid = 38;
    private final int field_1_reserved;
    private final int field_2_subex_len;

    public MemAreaPtg(int subexLen) {
        this.field_1_reserved = 0;
        this.field_2_subex_len = subexLen;
    }

    public MemAreaPtg(LittleEndianInput in) {
        this.field_1_reserved = in.readInt();
        this.field_2_subex_len = in.readShort();
    }

    public int getLenRefSubexpression() {
        return this.field_2_subex_len;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public void write(LittleEndianOutput out) {
        out.writeByte(getPtgClass() + 38);
        out.writeInt(this.field_1_reserved);
        out.writeShort(this.field_2_subex_len);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) 38;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public int getSize() {
        return 7;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public String toFormulaString() {
        return "";
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getDefaultOperandClass() {
        return (byte) 32;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperandPtg, org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public MemAreaPtg copy() {
        return this;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("lenRefSubexpression", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.MemAreaPtg$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(MemAreaPtg.this.getLenRefSubexpression());
            }
        });
    }
}
