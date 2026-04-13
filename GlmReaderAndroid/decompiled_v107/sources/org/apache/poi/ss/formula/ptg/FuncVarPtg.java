package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.function.FunctionMetadata;
import org.apache.poi.ss.formula.function.FunctionMetadataRegistry;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class FuncVarPtg extends AbstractFunctionPtg {
    private static final int SIZE = 4;
    public static final byte sid = 34;
    private final boolean _isCetab;
    private static final BitField ceFunc = BitFieldFactory.getInstance(61440);
    public static final OperationPtg SUM = create("SUM", 1);

    private FuncVarPtg(int functionIndex, int returnClass, byte[] paramClasses, int numArgs, boolean isCetab) {
        super(functionIndex, returnClass, paramClasses, numArgs);
        this._isCetab = isCetab;
    }

    public static FuncVarPtg create(LittleEndianInput in) {
        return create(in.readByte(), in.readUShort());
    }

    public static FuncVarPtg create(String pName, int numArgs) {
        return create(numArgs, lookupIndex(pName));
    }

    private static FuncVarPtg create(int numArgs, int functionIndex) {
        FunctionMetadata fm;
        int functionIndex2;
        boolean isCetab = ceFunc.isSet(functionIndex);
        if (isCetab) {
            int functionIndex3 = ceFunc.clear(functionIndex);
            fm = FunctionMetadataRegistry.getCetabFunctionByIndex(functionIndex3);
            functionIndex2 = functionIndex3;
        } else {
            fm = FunctionMetadataRegistry.getFunctionByIndex(functionIndex);
            functionIndex2 = functionIndex;
        }
        if (fm == null) {
            return new FuncVarPtg(functionIndex2, 32, new byte[]{32}, numArgs, isCetab);
        }
        return new FuncVarPtg(functionIndex2, fm.getReturnClassCode(), fm.getParameterClassCodes(), numArgs, isCetab);
    }

    @Override // org.apache.poi.ss.formula.ptg.AbstractFunctionPtg
    protected String lookupName(short index) {
        return lookupName(index, this._isCetab);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public void write(LittleEndianOutput out) {
        out.writeByte(getPtgClass() + 34);
        out.writeByte(getNumberOfOperands());
        out.writeShort(getFunctionIndex());
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) 34;
    }

    @Override // org.apache.poi.ss.formula.ptg.AbstractFunctionPtg, org.apache.poi.ss.formula.ptg.Ptg
    public int getSize() {
        return 4;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public FuncVarPtg copy() {
        return this;
    }

    @Override // org.apache.poi.ss.formula.ptg.AbstractFunctionPtg, org.apache.poi.ss.formula.ptg.OperationPtg, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.FuncVarPtg$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return FuncVarPtg.this.m2551x31342e0c();
            }
        }, "cetab", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.FuncVarPtg$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return FuncVarPtg.this.m2552x3899632b();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ss-formula-ptg-FuncVarPtg, reason: not valid java name */
    public /* synthetic */ Object m2551x31342e0c() {
        return super.getGenericProperties();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-ss-formula-ptg-FuncVarPtg, reason: not valid java name */
    public /* synthetic */ Object m2552x3899632b() {
        return Boolean.valueOf(this._isCetab);
    }
}
