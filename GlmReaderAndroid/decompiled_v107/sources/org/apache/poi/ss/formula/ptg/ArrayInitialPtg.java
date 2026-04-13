package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.constant.ConstantValueParser;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class ArrayInitialPtg extends Ptg {
    private final int _reserved0;
    private final int _reserved1;
    private final int _reserved2;

    public ArrayInitialPtg(LittleEndianInput in) {
        this._reserved0 = in.readInt();
        this._reserved1 = in.readUShort();
        this._reserved2 = in.readUByte();
    }

    private static RuntimeException invalid() {
        throw new IllegalStateException("This object is a partially initialised tArray, and cannot be used as a Ptg");
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getDefaultOperandClass() {
        throw invalid();
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public int getSize() {
        return 8;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public boolean isBaseToken() {
        return false;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public String toFormulaString() {
        throw invalid();
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public void write(LittleEndianOutput out) {
        throw invalid();
    }

    public ArrayPtg finishReading(LittleEndianInput in) {
        int nColumns = in.readUByte() + 1;
        int nColumns2 = in.readShort() + 1;
        short nRows = (short) nColumns2;
        int totalCount = nRows * nColumns;
        Object[] arrayValues = ConstantValueParser.parse(in, totalCount);
        ArrayPtg result = new ArrayPtg(this._reserved0, this._reserved1, this._reserved2, nColumns, nRows, arrayValues);
        result.setClass(getPtgClass());
        return result;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public ArrayInitialPtg copy() {
        return this;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("reserved0", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.ArrayInitialPtg$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return ArrayInitialPtg.this.m2537x97b97fcc();
            }
        }, "reserved1", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.ArrayInitialPtg$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return ArrayInitialPtg.this.m2538xd33a60d();
            }
        }, "reserved2", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.ArrayInitialPtg$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return ArrayInitialPtg.this.m2539x82adcc4e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ss-formula-ptg-ArrayInitialPtg, reason: not valid java name */
    public /* synthetic */ Object m2537x97b97fcc() {
        return Integer.valueOf(this._reserved0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-ss-formula-ptg-ArrayInitialPtg, reason: not valid java name */
    public /* synthetic */ Object m2538xd33a60d() {
        return Integer.valueOf(this._reserved1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-ss-formula-ptg-ArrayInitialPtg, reason: not valid java name */
    public /* synthetic */ Object m2539x82adcc4e() {
        return Integer.valueOf(this._reserved2);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) -1;
    }
}
