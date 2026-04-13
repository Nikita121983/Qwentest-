package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.SheetNameFormatter;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class Deleted3DPxg extends OperandPtg implements Pxg {
    private int externalWorkbookNumber;
    private String sheetName;

    public Deleted3DPxg(int externalWorkbookNumber, String sheetName) {
        this.externalWorkbookNumber = -1;
        this.externalWorkbookNumber = externalWorkbookNumber;
        this.sheetName = sheetName;
    }

    public Deleted3DPxg(Deleted3DPxg other) {
        super(other);
        this.externalWorkbookNumber = -1;
        this.externalWorkbookNumber = other.externalWorkbookNumber;
        this.sheetName = other.sheetName;
    }

    public Deleted3DPxg(String sheetName) {
        this(-1, sheetName);
    }

    @Override // org.apache.poi.ss.formula.ptg.Pxg
    public int getExternalWorkbookNumber() {
        return this.externalWorkbookNumber;
    }

    @Override // org.apache.poi.ss.formula.ptg.Pxg
    public String getSheetName() {
        return this.sheetName;
    }

    @Override // org.apache.poi.ss.formula.ptg.Pxg
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public String toFormulaString() {
        StringBuilder sb = new StringBuilder(64);
        if (this.externalWorkbookNumber >= 0) {
            sb.append('[');
            sb.append(this.externalWorkbookNumber);
            sb.append(']');
        }
        if (this.sheetName != null) {
            SheetNameFormatter.appendFormat(sb, this.sheetName);
        }
        sb.append('!');
        sb.append(FormulaError.REF.getString());
        return sb.toString();
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getDefaultOperandClass() {
        return (byte) 32;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) -1;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public int getSize() {
        return 1;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public void write(LittleEndianOutput out) {
        throw new IllegalStateException("XSSF-only Ptg, should not be serialised");
    }

    @Override // org.apache.poi.ss.formula.ptg.OperandPtg, org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public Deleted3DPxg copy() {
        return new Deleted3DPxg(this);
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("externalWorkbookNumber", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.Deleted3DPxg$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(Deleted3DPxg.this.getExternalWorkbookNumber());
            }
        }, "sheetName", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.Deleted3DPxg$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Deleted3DPxg.this.getSheetName();
            }
        }, "formulaError", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.Deleted3DPxg$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                Object obj;
                obj = FormulaError.REF;
                return obj;
            }
        });
    }
}
