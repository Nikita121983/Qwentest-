package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

/* loaded from: classes10.dex */
public final class FormulaRecord extends CellRecord {
    private static final int FIXED_SIZE = 14;
    private static final BitField alwaysCalc = BitFieldFactory.getInstance(1);
    private static final BitField calcOnLoad = BitFieldFactory.getInstance(2);
    private static final BitField sharedFormula = BitFieldFactory.getInstance(8);
    public static final short sid = 6;
    private double field_4_value;
    private short field_5_options;
    private int field_6_zero;
    private Formula field_8_parsed_expr;
    private FormulaSpecialCachedValue specialCachedValue;

    public FormulaRecord() {
        this.field_8_parsed_expr = Formula.create(Ptg.EMPTY_PTG_ARRAY);
    }

    public FormulaRecord(FormulaRecord other) {
        super(other);
        this.field_4_value = other.field_4_value;
        this.field_5_options = other.field_5_options;
        this.field_6_zero = other.field_6_zero;
        this.field_8_parsed_expr = other.field_8_parsed_expr == null ? null : new Formula(other.field_8_parsed_expr);
        this.specialCachedValue = other.specialCachedValue != null ? new FormulaSpecialCachedValue(other.specialCachedValue) : null;
    }

    public FormulaRecord(RecordInputStream ris) {
        super(ris);
        long valueLongBits = ris.readLong();
        this.field_5_options = ris.readShort();
        this.specialCachedValue = FormulaSpecialCachedValue.create(valueLongBits);
        if (this.specialCachedValue == null) {
            this.field_4_value = Double.longBitsToDouble(valueLongBits);
        }
        this.field_6_zero = ris.readInt();
        int field_7_expression_len = ris.readShort();
        int nBytesAvailable = ris.available();
        this.field_8_parsed_expr = Formula.read(field_7_expression_len, ris, nBytesAvailable);
    }

    public void setValue(double value) {
        this.field_4_value = value;
        this.specialCachedValue = null;
    }

    public void setCachedResultTypeEmptyString() {
        this.specialCachedValue = FormulaSpecialCachedValue.createCachedEmptyValue();
    }

    public void setCachedResultTypeString() {
        this.specialCachedValue = FormulaSpecialCachedValue.createForString();
    }

    public void setCachedResultErrorCode(int errorCode) {
        this.specialCachedValue = FormulaSpecialCachedValue.createCachedErrorCode(errorCode);
    }

    public void setCachedResultBoolean(boolean value) {
        this.specialCachedValue = FormulaSpecialCachedValue.createCachedBoolean(value);
    }

    public boolean hasCachedResultString() {
        return this.specialCachedValue != null && this.specialCachedValue.getTypeCode() == 0;
    }

    @Removal(version = "6.0.0")
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

    public void setOptions(short options) {
        this.field_5_options = options;
    }

    public double getValue() {
        return this.field_4_value;
    }

    public short getOptions() {
        return this.field_5_options;
    }

    public boolean isSharedFormula() {
        return sharedFormula.isSet(this.field_5_options);
    }

    public void setSharedFormula(boolean flag) {
        this.field_5_options = sharedFormula.setShortBoolean(this.field_5_options, flag);
    }

    public boolean isAlwaysCalc() {
        return alwaysCalc.isSet(this.field_5_options);
    }

    public void setAlwaysCalc(boolean flag) {
        this.field_5_options = alwaysCalc.setShortBoolean(this.field_5_options, flag);
    }

    public boolean isCalcOnLoad() {
        return calcOnLoad.isSet(this.field_5_options);
    }

    public void setCalcOnLoad(boolean flag) {
        this.field_5_options = calcOnLoad.setShortBoolean(this.field_5_options, flag);
    }

    public Ptg[] getParsedExpression() {
        return this.field_8_parsed_expr.getTokens();
    }

    public Formula getFormula() {
        return this.field_8_parsed_expr;
    }

    public void setParsedExpression(Ptg[] ptgs) {
        this.field_8_parsed_expr = Formula.create(ptgs);
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 6;
    }

    @Override // org.apache.poi.hssf.record.CellRecord
    protected int getValueDataSize() {
        return this.field_8_parsed_expr.getEncodedSize() + 14;
    }

    @Override // org.apache.poi.hssf.record.CellRecord
    protected void serializeValue(LittleEndianOutput out) {
        if (this.specialCachedValue == null) {
            out.writeDouble(this.field_4_value);
        } else {
            this.specialCachedValue.serialize(out);
        }
        out.writeShort(getOptions());
        out.writeInt(this.field_6_zero);
        this.field_8_parsed_expr.serialize(out);
    }

    @Override // org.apache.poi.hssf.record.CellRecord
    protected String getRecordName() {
        return "FORMULA";
    }

    @Override // org.apache.poi.hssf.record.CellRecord, org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public FormulaRecord copy() {
        return new FormulaRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FORMULA;
    }

    @Override // org.apache.poi.hssf.record.CellRecord, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.hssf.record.FormulaRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return FormulaRecord.this.m2308xe61823d8();
            }
        }, "options", new Supplier() { // from class: org.apache.poi.hssf.record.FormulaRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(FormulaRecord.this.getOptions());
            }
        }, "alwaysCalc", new Supplier() { // from class: org.apache.poi.hssf.record.FormulaRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(FormulaRecord.this.isAlwaysCalc());
            }
        }, "calcOnLoad", new Supplier() { // from class: org.apache.poi.hssf.record.FormulaRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(FormulaRecord.this.isCalcOnLoad());
            }
        }, "shared", new Supplier() { // from class: org.apache.poi.hssf.record.FormulaRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(FormulaRecord.this.isSharedFormula());
            }
        }, "zero", new Supplier() { // from class: org.apache.poi.hssf.record.FormulaRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return FormulaRecord.this.m2309xed7d58f7();
            }
        }, "value", new Supplier() { // from class: org.apache.poi.hssf.record.FormulaRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return FormulaRecord.this.m2310xf4e28e16();
            }
        }, "formula", new Supplier() { // from class: org.apache.poi.hssf.record.FormulaRecord$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return FormulaRecord.this.getFormula();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-FormulaRecord, reason: not valid java name */
    public /* synthetic */ Object m2308xe61823d8() {
        return super.getGenericProperties();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-FormulaRecord, reason: not valid java name */
    public /* synthetic */ Object m2309xed7d58f7() {
        return Integer.valueOf(this.field_6_zero);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-FormulaRecord, reason: not valid java name */
    public /* synthetic */ Object m2310xf4e28e16() {
        return this.specialCachedValue == null ? Double.valueOf(this.field_4_value) : this.specialCachedValue;
    }
}
