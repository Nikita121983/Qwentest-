package org.apache.poi.hssf.record.pivottable;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class ViewFieldsRecord extends StandardRecord {
    private static final int BASE_SIZE = 10;
    private static final int STRING_NOT_PRESENT_LEN = 65535;
    public static final short sid = 177;
    private final int _cItm;
    private final int _cSub;
    private final int _grbitSub;
    private String _name;
    private final int _sxaxis;

    /* loaded from: classes10.dex */
    private enum Axis {
        NO_AXIS(0),
        ROW(1),
        COLUMN(2),
        PAGE(4),
        DATA(8);

        final int id;

        Axis(int id) {
            this.id = id;
        }
    }

    public ViewFieldsRecord(ViewFieldsRecord other) {
        super(other);
        this._sxaxis = other._sxaxis;
        this._cSub = other._cSub;
        this._grbitSub = other._grbitSub;
        this._cItm = other._cItm;
        this._name = other._name;
    }

    public ViewFieldsRecord(RecordInputStream in) {
        this._sxaxis = in.readShort();
        this._cSub = in.readShort();
        this._grbitSub = in.readShort();
        this._cItm = in.readShort();
        int cchName = in.readUShort();
        if (cchName != 65535) {
            int flag = in.readByte();
            if ((flag & 1) != 0) {
                this._name = in.readUnicodeLEString(cchName);
            } else {
                this._name = in.readCompressedUnicode(cchName);
            }
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected void serialize(LittleEndianOutput out) {
        out.writeShort(this._sxaxis);
        out.writeShort(this._cSub);
        out.writeShort(this._grbitSub);
        out.writeShort(this._cItm);
        if (this._name != null) {
            StringUtil.writeUnicodeString(out, this._name);
        } else {
            out.writeShort(65535);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        if (this._name == null) {
            return 10;
        }
        return (this._name.length() * (StringUtil.hasMultibyte(this._name) ? 2 : 1)) + 11;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 177;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public ViewFieldsRecord copy() {
        return new ViewFieldsRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.VIEW_FIELDS;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("sxaxis", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewFieldsRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewFieldsRecord.this.m2488x66d85b91();
            }
        }, "cSub", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewFieldsRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewFieldsRecord.this.m2489x680eae70();
            }
        }, "grbitSub", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewFieldsRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewFieldsRecord.this.m2490x6945014f();
            }
        }, "cItm", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewFieldsRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewFieldsRecord.this.m2491x6a7b542e();
            }
        }, "name", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.ViewFieldsRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return ViewFieldsRecord.this.m2492x6bb1a70d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-pivottable-ViewFieldsRecord, reason: not valid java name */
    public /* synthetic */ Object m2488x66d85b91() {
        return Integer.valueOf(this._sxaxis);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-pivottable-ViewFieldsRecord, reason: not valid java name */
    public /* synthetic */ Object m2489x680eae70() {
        return Integer.valueOf(this._cSub);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-pivottable-ViewFieldsRecord, reason: not valid java name */
    public /* synthetic */ Object m2490x6945014f() {
        return Integer.valueOf(this._grbitSub);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-pivottable-ViewFieldsRecord, reason: not valid java name */
    public /* synthetic */ Object m2491x6a7b542e() {
        return Integer.valueOf(this._cItm);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-pivottable-ViewFieldsRecord, reason: not valid java name */
    public /* synthetic */ Object m2492x6bb1a70d() {
        return this._name;
    }
}
