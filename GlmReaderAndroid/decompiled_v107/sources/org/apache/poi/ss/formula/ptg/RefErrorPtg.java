package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class RefErrorPtg extends OperandPtg {
    private static final int SIZE = 5;
    public static final byte sid = 42;
    private final int field_1_reserved;

    public RefErrorPtg() {
        this.field_1_reserved = 0;
    }

    public RefErrorPtg(RefErrorPtg other) {
        super(other);
        this.field_1_reserved = other.field_1_reserved;
    }

    public RefErrorPtg(LittleEndianInput in) {
        this.field_1_reserved = in.readInt();
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public void write(LittleEndianOutput out) {
        out.writeByte(getPtgClass() + sid);
        out.writeInt(this.field_1_reserved);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return sid;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public int getSize() {
        return 5;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public String toFormulaString() {
        return FormulaError.REF.getString();
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getDefaultOperandClass() {
        return (byte) 0;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperandPtg, org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public RefErrorPtg copy() {
        return new RefErrorPtg(this);
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("reserved", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.RefErrorPtg$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return RefErrorPtg.this.m2555xc301af02();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ss-formula-ptg-RefErrorPtg, reason: not valid java name */
    public /* synthetic */ Object m2555xc301af02() {
        return Integer.valueOf(this.field_1_reserved);
    }
}
