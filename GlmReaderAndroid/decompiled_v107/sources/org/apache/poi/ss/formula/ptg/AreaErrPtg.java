package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class AreaErrPtg extends OperandPtg {
    public static final byte sid = 43;
    private final int unused1;
    private final int unused2;

    public AreaErrPtg() {
        this.unused1 = 0;
        this.unused2 = 0;
    }

    public AreaErrPtg(LittleEndianInput in) {
        this.unused1 = in.readInt();
        this.unused2 = in.readInt();
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public void write(LittleEndianOutput out) {
        out.writeByte(getPtgClass() + sid);
        out.writeInt(this.unused1);
        out.writeInt(this.unused2);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public String toFormulaString() {
        return FormulaError.REF.getString();
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getDefaultOperandClass() {
        return (byte) 0;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return sid;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public int getSize() {
        return 9;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperandPtg, org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public AreaErrPtg copy() {
        return this;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("unused1", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.AreaErrPtg$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return AreaErrPtg.this.m2535x822dce17();
            }
        }, "unused2", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.AreaErrPtg$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return AreaErrPtg.this.m2536x89930336();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ss-formula-ptg-AreaErrPtg, reason: not valid java name */
    public /* synthetic */ Object m2535x822dce17() {
        return Integer.valueOf(this.unused1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-ss-formula-ptg-AreaErrPtg, reason: not valid java name */
    public /* synthetic */ Object m2536x89930336() {
        return Integer.valueOf(this.unused2);
    }
}
