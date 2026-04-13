package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.FormulaRenderingWorkbook;
import org.apache.poi.ss.formula.WorkbookDependentFormula;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class NameXPtg extends OperandPtg implements WorkbookDependentFormula {
    private static final int SIZE = 7;
    public static final short sid = 57;
    private final int _nameNumber;
    private final int _reserved;
    private final int _sheetRefIndex;

    private NameXPtg(int sheetRefIndex, int nameNumber, int reserved) {
        this._sheetRefIndex = sheetRefIndex;
        this._nameNumber = nameNumber;
        this._reserved = reserved;
    }

    public NameXPtg(int sheetRefIndex, int nameIndex) {
        this(sheetRefIndex, nameIndex + 1, 0);
    }

    public NameXPtg(LittleEndianInput in) {
        this(in.readUShort(), in.readUShort(), in.readUShort());
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public void write(LittleEndianOutput out) {
        out.writeByte(getPtgClass() + 57);
        out.writeShort(this._sheetRefIndex);
        out.writeShort(this._nameNumber);
        out.writeShort(this._reserved);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) 57;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public int getSize() {
        return 7;
    }

    @Override // org.apache.poi.ss.formula.WorkbookDependentFormula
    public String toFormulaString(FormulaRenderingWorkbook book) {
        return book.resolveNameXText(this);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public String toFormulaString() {
        throw new IllegalStateException("3D references need a workbook to determine formula text");
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getDefaultOperandClass() {
        return (byte) 32;
    }

    public int getSheetRefIndex() {
        return this._sheetRefIndex;
    }

    public int getNameIndex() {
        return this._nameNumber - 1;
    }

    @Override // org.apache.poi.ss.formula.ptg.OperandPtg, org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public NameXPtg copy() {
        return this;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("sheetRefIndex", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.NameXPtg$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(NameXPtg.this.getSheetRefIndex());
            }
        }, "nameIndex", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.NameXPtg$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(NameXPtg.this.getNameIndex());
            }
        });
    }
}
