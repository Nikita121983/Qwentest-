package org.apache.poi.hssf.record.pivottable;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class ExtendedPivotTableViewFieldsRecord extends StandardRecord {
    private static final int STRING_NOT_PRESENT_LEN = 65535;
    public static final short sid = 256;
    private int _citmShow;
    private int _grbit1;
    private int _grbit2;
    private int _isxdiShow;
    private int _isxdiSort;
    private int _reserved1;
    private int _reserved2;
    private String _subtotalName;

    public ExtendedPivotTableViewFieldsRecord(ExtendedPivotTableViewFieldsRecord other) {
        super(other);
        this._grbit1 = other._grbit1;
        this._grbit2 = other._grbit2;
        this._citmShow = other._citmShow;
        this._isxdiSort = other._isxdiSort;
        this._isxdiShow = other._isxdiShow;
        this._reserved1 = other._reserved1;
        this._reserved2 = other._reserved2;
        this._subtotalName = other._subtotalName;
    }

    public ExtendedPivotTableViewFieldsRecord(RecordInputStream in) {
        this._grbit1 = in.readInt();
        this._grbit2 = in.readUByte();
        this._citmShow = in.readUByte();
        this._isxdiSort = in.readUShort();
        this._isxdiShow = in.readUShort();
        switch (in.remaining()) {
            case 0:
                this._reserved1 = 0;
                this._reserved2 = 0;
                this._subtotalName = null;
                return;
            case 10:
                int cchSubName = in.readUShort();
                this._reserved1 = in.readInt();
                this._reserved2 = in.readInt();
                if (cchSubName != 65535) {
                    this._subtotalName = in.readUnicodeLEString(cchSubName);
                    return;
                }
                return;
            default:
                throw new RecordFormatException("Unexpected remaining size (" + in.remaining() + ")");
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected void serialize(LittleEndianOutput out) {
        out.writeInt(this._grbit1);
        out.writeByte(this._grbit2);
        out.writeByte(this._citmShow);
        out.writeShort(this._isxdiSort);
        out.writeShort(this._isxdiShow);
        if (this._subtotalName == null) {
            out.writeShort(65535);
        } else {
            out.writeShort(this._subtotalName.length());
        }
        out.writeInt(this._reserved1);
        out.writeInt(this._reserved2);
        if (this._subtotalName != null) {
            StringUtil.putUnicodeLE(this._subtotalName, out);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return (this._subtotalName == null ? 0 : this._subtotalName.length() * 2) + 20;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public ExtendedPivotTableViewFieldsRecord copy() {
        return new ExtendedPivotTableViewFieldsRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.EXTENDED_PIVOT_TABLE_VIEW_FIELDS;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("grbit1", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ExtendedPivotTableViewFieldsRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return ExtendedPivotTableViewFieldsRecord.this.m2455xca2daff6();
            }
        }, "grbit2", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ExtendedPivotTableViewFieldsRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return ExtendedPivotTableViewFieldsRecord.this.m2456xe49ea915();
            }
        }, "citmShow", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ExtendedPivotTableViewFieldsRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return ExtendedPivotTableViewFieldsRecord.this.m2457xff0fa234();
            }
        }, "isxdiSort", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ExtendedPivotTableViewFieldsRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return ExtendedPivotTableViewFieldsRecord.this.m2458x19809b53();
            }
        }, "isxdiShow", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ExtendedPivotTableViewFieldsRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return ExtendedPivotTableViewFieldsRecord.this.m2459x33f19472();
            }
        }, "subtotalName", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ExtendedPivotTableViewFieldsRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return ExtendedPivotTableViewFieldsRecord.this.m2460x4e628d91();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-pivottable-ExtendedPivotTableViewFieldsRecord, reason: not valid java name */
    public /* synthetic */ Object m2455xca2daff6() {
        return Integer.valueOf(this._grbit1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-pivottable-ExtendedPivotTableViewFieldsRecord, reason: not valid java name */
    public /* synthetic */ Object m2456xe49ea915() {
        return Integer.valueOf(this._grbit2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-pivottable-ExtendedPivotTableViewFieldsRecord, reason: not valid java name */
    public /* synthetic */ Object m2457xff0fa234() {
        return Integer.valueOf(this._citmShow);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-pivottable-ExtendedPivotTableViewFieldsRecord, reason: not valid java name */
    public /* synthetic */ Object m2458x19809b53() {
        return Integer.valueOf(this._isxdiSort);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-pivottable-ExtendedPivotTableViewFieldsRecord, reason: not valid java name */
    public /* synthetic */ Object m2459x33f19472() {
        return Integer.valueOf(this._isxdiShow);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-pivottable-ExtendedPivotTableViewFieldsRecord, reason: not valid java name */
    public /* synthetic */ Object m2460x4e628d91() {
        return this._subtotalName;
    }
}
