package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.ExternSheetReferenceToken;
import org.apache.poi.ss.formula.FormulaRenderingWorkbook;
import org.apache.poi.ss.formula.WorkbookDependentFormula;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class Area3DPtg extends AreaPtgBase implements WorkbookDependentFormula, ExternSheetReferenceToken {
    private static final int SIZE = 11;
    public static final byte sid = 59;
    private int field_1_index_extern_sheet;

    public Area3DPtg(String arearef, int externIdx) {
        super(new AreaReference(arearef, SpreadsheetVersion.EXCEL97));
        setExternSheetIndex(externIdx);
    }

    public Area3DPtg(Area3DPtg other) {
        super(other);
        this.field_1_index_extern_sheet = other.field_1_index_extern_sheet;
    }

    public Area3DPtg(LittleEndianInput in) {
        this.field_1_index_extern_sheet = in.readShort();
        readCoordinates(in);
    }

    public Area3DPtg(int firstRow, int lastRow, int firstColumn, int lastColumn, boolean firstRowRelative, boolean lastRowRelative, boolean firstColRelative, boolean lastColRelative, int externalSheetIndex) {
        super(firstRow, lastRow, firstColumn, lastColumn, firstRowRelative, lastRowRelative, firstColRelative, lastColRelative);
        setExternSheetIndex(externalSheetIndex);
    }

    public Area3DPtg(AreaReference arearef, int externIdx) {
        super(arearef);
        setExternSheetIndex(externIdx);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public void write(LittleEndianOutput out) {
        out.writeByte(getPtgClass() + sid);
        out.writeShort(this.field_1_index_extern_sheet);
        writeCoordinates(out);
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return sid;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public int getSize() {
        return 11;
    }

    @Override // org.apache.poi.ss.formula.ExternSheetReferenceToken
    public int getExternSheetIndex() {
        return this.field_1_index_extern_sheet;
    }

    public void setExternSheetIndex(int index) {
        this.field_1_index_extern_sheet = index;
    }

    @Override // org.apache.poi.ss.formula.ExternSheetReferenceToken
    public String format2DRefAsString() {
        return formatReferenceAsString();
    }

    @Override // org.apache.poi.ss.formula.WorkbookDependentFormula
    public String toFormulaString(FormulaRenderingWorkbook book) {
        return ExternSheetNameResolver.prependSheetName(book, this.field_1_index_extern_sheet, formatReferenceAsString());
    }

    @Override // org.apache.poi.ss.formula.ptg.AreaPtgBase, org.apache.poi.ss.formula.ptg.Ptg
    public String toFormulaString() {
        throw new IllegalStateException("3D references need a workbook to determine formula text");
    }

    @Override // org.apache.poi.ss.formula.ptg.OperandPtg, org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public Area3DPtg copy() {
        return new Area3DPtg(this);
    }

    @Override // org.apache.poi.ss.formula.ptg.AreaPtgBase, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.Area3DPtg$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Area3DPtg.this.m2533x5df6b0d9();
            }
        }, "externSheetIndex", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.Area3DPtg$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(Area3DPtg.this.getExternSheetIndex());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ss-formula-ptg-Area3DPtg, reason: not valid java name */
    public /* synthetic */ Object m2533x5df6b0d9() {
        return super.getGenericProperties();
    }
}
