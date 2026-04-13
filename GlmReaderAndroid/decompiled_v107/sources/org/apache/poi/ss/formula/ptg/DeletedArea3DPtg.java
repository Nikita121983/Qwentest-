package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.FormulaRenderingWorkbook;
import org.apache.poi.ss.formula.WorkbookDependentFormula;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class DeletedArea3DPtg extends OperandPtg implements WorkbookDependentFormula {
    public static final byte sid = 61;
    private final int field_1_index_extern_sheet;
    private final int unused1;
    private final int unused2;

    public DeletedArea3DPtg(int externSheetIndex) {
        this.field_1_index_extern_sheet = externSheetIndex;
        this.unused1 = 0;
        this.unused2 = 0;
    }

    public DeletedArea3DPtg(LittleEndianInput in) {
        this.field_1_index_extern_sheet = in.readUShort();
        this.unused1 = in.readInt();
        this.unused2 = in.readInt();
    }

    @Override // org.apache.poi.ss.formula.WorkbookDependentFormula
    public String toFormulaString(FormulaRenderingWorkbook book) {
        return ExternSheetNameResolver.prependSheetName(book, this.field_1_index_extern_sheet, FormulaError.REF.getString());
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public String toFormulaString() {
        throw new IllegalStateException("3D references need a workbook to determine formula text");
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getDefaultOperandClass() {
        return (byte) 0;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public byte getSid() {
        return (byte) 61;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public int getSize() {
        return 11;
    }

    public int getExternSheetIndex() {
        return this.field_1_index_extern_sheet;
    }

    @Override // org.apache.poi.ss.formula.ptg.Ptg
    public void write(LittleEndianOutput out) {
        out.writeByte(getPtgClass() + 61);
        out.writeShort(this.field_1_index_extern_sheet);
        out.writeInt(this.unused1);
        out.writeInt(this.unused2);
    }

    @Override // org.apache.poi.ss.formula.ptg.OperandPtg, org.apache.poi.ss.formula.ptg.Ptg, org.apache.poi.common.Duplicatable
    public DeletedArea3DPtg copy() {
        return this;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("externSheetIndex", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.DeletedArea3DPtg$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(DeletedArea3DPtg.this.getExternSheetIndex());
            }
        }, "unused1", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.DeletedArea3DPtg$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return DeletedArea3DPtg.this.m2547xa9582118();
            }
        }, "unused2", new Supplier() { // from class: org.apache.poi.ss.formula.ptg.DeletedArea3DPtg$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return DeletedArea3DPtg.this.m2548xe322c2f7();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ss-formula-ptg-DeletedArea3DPtg, reason: not valid java name */
    public /* synthetic */ Object m2547xa9582118() {
        return Integer.valueOf(this.unused1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-ss-formula-ptg-DeletedArea3DPtg, reason: not valid java name */
    public /* synthetic */ Object m2548xe322c2f7() {
        return Integer.valueOf(this.unused2);
    }
}
