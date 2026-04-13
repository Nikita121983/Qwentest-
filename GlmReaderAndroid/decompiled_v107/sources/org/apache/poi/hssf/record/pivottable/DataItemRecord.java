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
public final class DataItemRecord extends StandardRecord {
    public static final short sid = 197;
    private int df;
    private int ifmt;
    private int iiftab;
    private int isxvd;
    private int isxvdData;
    private int isxvi;
    private String name;

    public DataItemRecord(DataItemRecord other) {
        super(other);
        this.isxvdData = other.isxvdData;
        this.iiftab = other.iiftab;
        this.df = other.df;
        this.isxvd = other.isxvd;
        this.isxvi = other.isxvi;
        this.ifmt = other.ifmt;
        this.name = other.name;
    }

    public DataItemRecord(RecordInputStream in) {
        this.isxvdData = in.readUShort();
        this.iiftab = in.readUShort();
        this.df = in.readUShort();
        this.isxvd = in.readUShort();
        this.isxvi = in.readUShort();
        this.ifmt = in.readUShort();
        this.name = in.readString();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected void serialize(LittleEndianOutput out) {
        out.writeShort(this.isxvdData);
        out.writeShort(this.iiftab);
        out.writeShort(this.df);
        out.writeShort(this.isxvd);
        out.writeShort(this.isxvi);
        out.writeShort(this.ifmt);
        StringUtil.writeUnicodeString(out, this.name);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return StringUtil.getEncodedSize(this.name) + 12;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 197;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public DataItemRecord copy() {
        return new DataItemRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DATA_ITEM;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("isxvdData", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.DataItemRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return DataItemRecord.this.m2449x6049bcf0();
            }
        }, "iiftab", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.DataItemRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return DataItemRecord.this.m2450x7a653b8f();
            }
        }, "df", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.DataItemRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return DataItemRecord.this.m2451x9480ba2e();
            }
        }, "isxvd", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.DataItemRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return DataItemRecord.this.m2452xae9c38cd();
            }
        }, "isxvi", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.DataItemRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return DataItemRecord.this.m2453xc8b7b76c();
            }
        }, "ifmt", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.DataItemRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return DataItemRecord.this.m2454xe2d3360b();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-pivottable-DataItemRecord, reason: not valid java name */
    public /* synthetic */ Object m2449x6049bcf0() {
        return Integer.valueOf(this.isxvdData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-pivottable-DataItemRecord, reason: not valid java name */
    public /* synthetic */ Object m2450x7a653b8f() {
        return Integer.valueOf(this.iiftab);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-pivottable-DataItemRecord, reason: not valid java name */
    public /* synthetic */ Object m2451x9480ba2e() {
        return Integer.valueOf(this.df);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-pivottable-DataItemRecord, reason: not valid java name */
    public /* synthetic */ Object m2452xae9c38cd() {
        return Integer.valueOf(this.isxvd);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-pivottable-DataItemRecord, reason: not valid java name */
    public /* synthetic */ Object m2453xc8b7b76c() {
        return Integer.valueOf(this.isxvi);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-pivottable-DataItemRecord, reason: not valid java name */
    public /* synthetic */ Object m2454xe2d3360b() {
        return Integer.valueOf(this.ifmt);
    }
}
