package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.util.CellRangeAddress8Bit;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class ArrayRecord extends SharedValueRecordBase {
    private static final int OPT_ALWAYS_RECALCULATE = 1;
    private static final int OPT_CALCULATE_ON_OPEN = 2;
    public static final short sid = 545;
    private int _field3notUsed;
    private Formula _formula;
    private int _options;

    public ArrayRecord(ArrayRecord other) {
        super(other);
        this._options = other._options;
        this._field3notUsed = other._field3notUsed;
        this._formula = other._formula == null ? null : other._formula.copy();
    }

    public ArrayRecord(RecordInputStream in) {
        super(in);
        this._options = in.readUShort();
        this._field3notUsed = in.readInt();
        int formulaTokenLen = in.readUShort();
        int totalFormulaLen = in.available();
        this._formula = Formula.read(formulaTokenLen, in, totalFormulaLen);
    }

    public ArrayRecord(Formula formula, CellRangeAddress8Bit range) {
        super(range);
        this._options = 0;
        this._field3notUsed = 0;
        this._formula = formula;
    }

    public boolean isAlwaysRecalculate() {
        return (this._options & 1) != 0;
    }

    public boolean isCalculateOnOpen() {
        return (this._options & 2) != 0;
    }

    public Ptg[] getFormulaTokens() {
        return this._formula.getTokens();
    }

    @Override // org.apache.poi.hssf.record.SharedValueRecordBase
    protected int getExtraDataSize() {
        return this._formula.getEncodedSize() + 6;
    }

    @Override // org.apache.poi.hssf.record.SharedValueRecordBase
    protected void serializeExtraData(LittleEndianOutput out) {
        out.writeShort(this._options);
        out.writeInt(this._field3notUsed);
        this._formula.serialize(out);
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public ArrayRecord copy() {
        return new ArrayRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.ARRAY;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("range", new Supplier() { // from class: org.apache.poi.hssf.record.ArrayRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return ArrayRecord.this.getRange();
            }
        }, "options", new Supplier() { // from class: org.apache.poi.hssf.record.ArrayRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return ArrayRecord.this.m2249x819f2ecb();
            }
        }, "notUsed", new Supplier() { // from class: org.apache.poi.hssf.record.ArrayRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return ArrayRecord.this.m2250xd8bd1faa();
            }
        }, "formula", new Supplier() { // from class: org.apache.poi.hssf.record.ArrayRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return ArrayRecord.this.m2251x2fdb1089();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-ArrayRecord, reason: not valid java name */
    public /* synthetic */ Object m2249x819f2ecb() {
        return Integer.valueOf(this._options);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-ArrayRecord, reason: not valid java name */
    public /* synthetic */ Object m2250xd8bd1faa() {
        return Integer.valueOf(this._field3notUsed);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-ArrayRecord, reason: not valid java name */
    public /* synthetic */ Object m2251x2fdb1089() {
        return this._formula;
    }
}
