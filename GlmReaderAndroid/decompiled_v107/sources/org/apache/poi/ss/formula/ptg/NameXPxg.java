package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.SheetNameFormatter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class NameXPxg extends OperandPtg implements Pxg {
    private int externalWorkbookNumber;
    private String nameName;
    private String sheetName;

    public NameXPxg(int externalWorkbookNumber, String sheetName, String nameName) {
        this.externalWorkbookNumber = -1;
        this.externalWorkbookNumber = externalWorkbookNumber;
        this.sheetName = sheetName;
        this.nameName = nameName;
    }

    public NameXPxg(NameXPxg other) {
        super(other);
        this.externalWorkbookNumber = -1;
        this.externalWorkbookNumber = other.externalWorkbookNumber;
        this.sheetName = other.sheetName;
        this.nameName = other.nameName;
    }

    public NameXPxg(String sheetName, String nameName) {
        this(-1, sheetName, nameName);
    }

    public NameXPxg(String nameName) {
        this(-1, null, nameName);
    }

    @Override // org.apache.poi.ss.formula.ptg.Pxg
    public int getExternalWorkbookNumber() {
        return this.externalWorkbookNumber;
    }

    @Override // org.apache.poi.ss.formula.ptg.Pxg
    public String getSheetName() {
        return this.sheetName;
    }

    public String getNameName() {
        return this.nameName;
    }

    @Override // org.apache.poi.ss.formula.ptg.Pxg
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public String toFormulaString() {
        StringBuilder sb = new StringBuilder(64);
        boolean needsExclamation = false;
        if (this.externalWorkbookNumber >= 0) {
            sb.append('[');
            sb.append(this.externalWorkbookNumber);
            sb.append(']');
            needsExclamation = true;
        }
        if (this.sheetName != null) {
            SheetNameFormatter.appendFormat(sb, this.sheetName);
            needsExclamation = true;
        }
        if (needsExclamation) {
            sb.append('!');
        }
        sb.append(this.nameName);
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
    public NameXPxg copy() {
        return new NameXPxg(this);
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("externalWorkbookNumber", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.NameXPxg$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(NameXPxg.this.getExternalWorkbookNumber());
            }
        }, "sheetName", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.NameXPxg$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return NameXPxg.this.getSheetName();
            }
        }, "nameName", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.NameXPxg$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return NameXPxg.this.getNameName();
            }
        });
    }
}
