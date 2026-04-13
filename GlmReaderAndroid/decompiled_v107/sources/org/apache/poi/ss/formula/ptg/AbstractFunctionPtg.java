package org.apache.poi.ss.formula.ptg;

import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.function.FunctionMetadata;
import org.apache.poi.ss.formula.function.FunctionMetadataRegistry;
import org.apache.poi.util.GenericRecordUtil;

/* loaded from: classes10.dex */
public abstract class AbstractFunctionPtg extends OperationPtg {
    private static final short FUNCTION_INDEX_EXTERNAL = 255;
    public static final String FUNCTION_NAME_IF = "IF";
    private final short _functionIndex;
    private final int _numberOfArgs;
    private final byte[] paramClass;
    private final byte returnClass;

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public abstract int getSize();

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractFunctionPtg(int functionIndex, int pReturnClass, byte[] paramTypes, int nParams) {
        this._numberOfArgs = nParams;
        if (functionIndex < -32768 || functionIndex > 32767) {
            throw new IllegalStateException("functionIndex " + functionIndex + " cannot be cast to short");
        }
        this._functionIndex = (short) functionIndex;
        if (pReturnClass < -128 || pReturnClass > 127) {
            throw new IllegalStateException("pReturnClass " + pReturnClass + " cannot be cast to byte");
        }
        this.returnClass = (byte) pReturnClass;
        this.paramClass = paramTypes;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public final boolean isBaseToken() {
        return false;
    }

    public final short getFunctionIndex() {
        return this._functionIndex;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public final int getNumberOfOperands() {
        return this._numberOfArgs;
    }

    public final String getName() {
        return lookupName(this._functionIndex);
    }

    public final boolean isExternalFunction() {
        return this._functionIndex == 255;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public final String toFormulaString() {
        return getName();
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg
    public String toFormulaString(String[] operands) {
        StringBuilder buf = new StringBuilder();
        if (isExternalFunction()) {
            buf.append(operands[0]);
            appendArgs(buf, 1, operands);
        } else {
            buf.append(getName());
            appendArgs(buf, 0, operands);
        }
        return buf.toString();
    }

    private static void appendArgs(StringBuilder buf, int firstArgIx, String[] operands) {
        buf.append('(');
        for (int i = firstArgIx; i < operands.length; i++) {
            if (i > firstArgIx) {
                buf.append(',');
            }
            buf.append(operands[i]);
        }
        buf.append(")");
    }

    public static boolean isBuiltInFunctionName(String name) {
        short ix = FunctionMetadataRegistry.lookupIndexByName(name.toUpperCase(Locale.ROOT));
        return ix >= 0;
    }

    protected String lookupName(short index) {
        return lookupName(index, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String lookupName(short index, boolean isCetab) {
        FunctionMetadata fm;
        if (index == 255) {
            return "#external#";
        }
        if (isCetab) {
            fm = FunctionMetadataRegistry.getCetabFunctionByIndex(index);
        } else {
            fm = FunctionMetadataRegistry.getFunctionByIndex(index);
        }
        if (fm == null) {
            throw new IllegalStateException("bad function index (" + ((int) index) + ", " + isCetab + ")");
        }
        return fm.getName();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static short lookupIndex(String name) {
        short ix = FunctionMetadataRegistry.lookupIndexByName(name.toUpperCase(Locale.ROOT));
        if (ix < 0) {
            return (short) 255;
        }
        return ix;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg, org.apache.poi.ss.formula.ptg.Ptg
    public byte getDefaultOperandClass() {
        return this.returnClass;
    }

    public final byte getParameterClass(int index) {
        if (index >= this.paramClass.length) {
            return this.paramClass[this.paramClass.length - 1];
        }
        return this.paramClass[index];
    }

    @Override // org.apache.poi.ss.formula.ptg.OperationPtg, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("functionIndex", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.AbstractFunctionPtg$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(AbstractFunctionPtg.this.getFunctionIndex());
            }
        }, "functionName", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.AbstractFunctionPtg$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return AbstractFunctionPtg.this.getName();
            }
        }, "numberOfOperands", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.AbstractFunctionPtg$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(AbstractFunctionPtg.this.getNumberOfOperands());
            }
        }, "externalFunction", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.AbstractFunctionPtg$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(AbstractFunctionPtg.this.isExternalFunction());
            }
        }, "defaultOperandClass", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.AbstractFunctionPtg$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Byte.valueOf(AbstractFunctionPtg.this.getDefaultOperandClass());
            }
        });
    }
}
