package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.util.GenericRecordUtil;

/* loaded from: classes10.dex */
public final class OldFormulaRecord extends OldCellRecord {
    public static final short biff2_sid = 6;
    public static final short biff3_sid = 518;
    public static final short biff4_sid = 1030;
    public static final short biff5_sid = 6;
    private double field_4_value;
    private short field_5_options;
    private Formula field_6_parsed_expr;
    private FormulaSpecialCachedValue specialCachedValue;

    public OldFormulaRecord(RecordInputStream ris) {
        super(ris, ris.getSid() == 6);
        if (isBiff2()) {
            this.field_4_value = ris.readDouble();
        } else {
            long valueLongBits = ris.readLong();
            this.specialCachedValue = FormulaSpecialCachedValue.create(valueLongBits);
            if (this.specialCachedValue == null) {
                this.field_4_value = Double.longBitsToDouble(valueLongBits);
            }
        }
        if (isBiff2()) {
            this.field_5_options = (short) ris.readUByte();
        } else {
            this.field_5_options = ris.readShort();
        }
        int expression_len = ris.readShort();
        int nBytesAvailable = ris.available();
        this.field_6_parsed_expr = Formula.read(expression_len, ris, nBytesAvailable);
    }

    @Deprecated
    public int getCachedResultType() {
        if (this.specialCachedValue == null) {
            return CellType.NUMERIC.getCode();
        }
        return this.specialCachedValue.getValueType();
    }

    public CellType getCachedResultTypeEnum() {
        if (this.specialCachedValue == null) {
            return CellType.NUMERIC;
        }
        return this.specialCachedValue.getValueTypeEnum();
    }

    public boolean getCachedBooleanValue() {
        return this.specialCachedValue.getBooleanValue();
    }

    public int getCachedErrorValue() {
        return this.specialCachedValue.getErrorValue();
    }

    public double getValue() {
        return this.field_4_value;
    }

    public short getOptions() {
        return this.field_5_options;
    }

    public Ptg[] getParsedExpression() {
        return this.field_6_parsed_expr.getTokens();
    }

    public Formula getFormula() {
        return this.field_6_parsed_expr;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FORMULA;
    }

    @Override // org.apache.poi.hssf.record.OldCellRecord, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.hssf.record.OldFormulaRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return OldFormulaRecord.this.m2346xc6d2c44f();
            }
        }, "options", new Supplier() { // from class: org.apache.poi.hssf.record.OldFormulaRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(OldFormulaRecord.this.getOptions());
            }
        }, "formula", new Supplier() { // from class: org.apache.poi.hssf.record.OldFormulaRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return OldFormulaRecord.this.getFormula();
            }
        }, "value", new Supplier() { // from class: org.apache.poi.hssf.record.OldFormulaRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Double.valueOf(OldFormulaRecord.this.getValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-OldFormulaRecord, reason: not valid java name */
    public /* synthetic */ Object m2346xc6d2c44f() {
        return super.getGenericProperties();
    }
}
