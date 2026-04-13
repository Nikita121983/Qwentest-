package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.util.CellRangeAddress8Bit;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.SharedFormula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class SharedFormulaRecord extends SharedValueRecordBase {
    public static final short sid = 1212;
    private int field_5_reserved;
    private Formula field_7_parsed_expr;

    public SharedFormulaRecord() {
        this(new CellRangeAddress8Bit(0, 0, 0, 0));
    }

    public SharedFormulaRecord(SharedFormulaRecord other) {
        super(other);
        this.field_5_reserved = other.field_5_reserved;
        this.field_7_parsed_expr = other.field_7_parsed_expr == null ? null : other.field_7_parsed_expr.copy();
    }

    private SharedFormulaRecord(CellRangeAddress8Bit range) {
        super(range);
        this.field_7_parsed_expr = Formula.create(Ptg.EMPTY_PTG_ARRAY);
    }

    public SharedFormulaRecord(RecordInputStream in) {
        super(in);
        this.field_5_reserved = in.readShort();
        int field_6_expression_len = in.readShort();
        int nAvailableBytes = in.available();
        this.field_7_parsed_expr = Formula.read(field_6_expression_len, in, nAvailableBytes);
    }

    @Override // org.apache.poi.hssf.record.SharedValueRecordBase
    protected void serializeExtraData(LittleEndianOutput out) {
        out.writeShort(this.field_5_reserved);
        this.field_7_parsed_expr.serialize(out);
    }

    @Override // org.apache.poi.hssf.record.SharedValueRecordBase
    protected int getExtraDataSize() {
        return this.field_7_parsed_expr.getEncodedSize() + 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    public Ptg[] getFormulaTokens(FormulaRecord formula) {
        int formulaRow = formula.getRow();
        int formulaColumn = formula.getColumn();
        if (!isInRange(formulaRow, formulaColumn)) {
            throw new IllegalStateException("Shared Formula Conversion: Coding Error");
        }
        SharedFormula sf = new SharedFormula(SpreadsheetVersion.EXCEL97);
        return sf.convertSharedFormulas(this.field_7_parsed_expr.getTokens(), formulaRow, formulaColumn);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public SharedFormulaRecord copy() {
        return new SharedFormulaRecord(this);
    }

    public boolean isFormulaSame(SharedFormulaRecord other) {
        return this.field_7_parsed_expr.isSame(other.field_7_parsed_expr);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SHARED_FORMULA;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("range", new Supplier() { // from class: org.apache.poi.hssf.record.SharedFormulaRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return SharedFormulaRecord.this.getRange();
            }
        }, "reserved", new Supplier() { // from class: org.apache.poi.hssf.record.SharedFormulaRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return SharedFormulaRecord.this.m2370x51200eb3();
            }
        }, "formula", new Supplier() { // from class: org.apache.poi.hssf.record.SharedFormulaRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return SharedFormulaRecord.this.m2371x8aeab092();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-SharedFormulaRecord, reason: not valid java name */
    public /* synthetic */ Object m2370x51200eb3() {
        return Integer.valueOf(this.field_5_reserved);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-SharedFormulaRecord, reason: not valid java name */
    public /* synthetic */ Object m2371x8aeab092() {
        return this.field_7_parsed_expr;
    }
}
