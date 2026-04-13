package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
public final class BoolErrRecord extends CellRecord {
    public static final short sid = 517;
    private boolean _isError;
    private int _value;

    public BoolErrRecord() {
    }

    public BoolErrRecord(BoolErrRecord other) {
        super(other);
        this._value = other._value;
        this._isError = other._isError;
    }

    public BoolErrRecord(RecordInputStream in) {
        super(in);
        switch (in.remaining()) {
            case 2:
                this._value = in.readByte();
                break;
            case 3:
                this._value = in.readUShort();
                break;
            default:
                throw new RecordFormatException("Unexpected size (" + in.remaining() + ") for BOOLERR record.");
        }
        int flag = in.readUByte();
        switch (flag) {
            case 0:
                this._isError = false;
                return;
            case 1:
                this._isError = true;
                return;
            default:
                throw new RecordFormatException("Unexpected isError flag (" + flag + ") for BOOLERR record.");
        }
    }

    public void setValue(boolean z) {
        this._value = z ? 1 : 0;
        this._isError = false;
    }

    public void setValue(byte value) {
        setValue(FormulaError.forInt(value));
    }

    public void setValue(FormulaError value) {
        switch (value) {
            case NULL:
            case DIV0:
            case VALUE:
            case REF:
            case NAME:
            case NUM:
            case NA:
                this._value = value.getCode();
                this._isError = true;
                return;
            default:
                throw new IllegalArgumentException("Error Value can only be 0,7,15,23,29,36 or 42. It cannot be " + ((int) value.getCode()) + " (" + value + ")");
        }
    }

    public boolean getBooleanValue() {
        return this._value != 0;
    }

    public byte getErrorValue() {
        return (byte) this._value;
    }

    public boolean isBoolean() {
        return !this._isError;
    }

    public boolean isError() {
        return this._isError;
    }

    @Override // org.apache.poi.hssf.record.CellRecord
    protected String getRecordName() {
        return "BOOLERR";
    }

    @Override // org.apache.poi.hssf.record.CellRecord
    protected void serializeValue(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(this._value);
        littleEndianOutput.writeByte(this._isError ? 1 : 0);
    }

    @Override // org.apache.poi.hssf.record.CellRecord
    protected int getValueDataSize() {
        return 2;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.CellRecord, org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public BoolErrRecord copy() {
        return new BoolErrRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.BOOL_ERR;
    }

    @Override // org.apache.poi.hssf.record.CellRecord, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.hssf.record.BoolErrRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return BoolErrRecord.this.m2252x9ed0accd();
            }
        }, "isBoolean", new Supplier() { // from class: org.apache.poi.hssf.record.BoolErrRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(BoolErrRecord.this.isBoolean());
            }
        }, "booleanVal", new Supplier() { // from class: org.apache.poi.hssf.record.BoolErrRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(BoolErrRecord.this.getBooleanValue());
            }
        }, "isError", new Supplier() { // from class: org.apache.poi.hssf.record.BoolErrRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(BoolErrRecord.this.isError());
            }
        }, "errorVal", new Supplier() { // from class: org.apache.poi.hssf.record.BoolErrRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Byte.valueOf(BoolErrRecord.this.getErrorValue());
            }
        }, "errorTxt", new Supplier() { // from class: org.apache.poi.hssf.record.BoolErrRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return BoolErrRecord.this.m2253xa635e1ec();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-BoolErrRecord, reason: not valid java name */
    public /* synthetic */ Object m2252x9ed0accd() {
        return super.getGenericProperties();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-BoolErrRecord, reason: not valid java name */
    public /* synthetic */ Object m2253xa635e1ec() {
        if (isError()) {
            return FormulaError.forInt(getErrorValue()).getString();
        }
        return null;
    }
}
