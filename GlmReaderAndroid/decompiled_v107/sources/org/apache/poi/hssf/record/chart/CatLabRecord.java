package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class CatLabRecord extends StandardRecord {
    public static final short sid = 2134;
    private short at;
    private short grbit;
    private short grbitFrt;
    private short rt;
    private Short unused;
    private short wOffset;

    public CatLabRecord(CatLabRecord other) {
        super(other);
        this.rt = other.rt;
        this.grbitFrt = other.grbitFrt;
        this.wOffset = other.wOffset;
        this.at = other.at;
        this.grbit = other.grbit;
        this.unused = other.unused;
    }

    public CatLabRecord(RecordInputStream in) {
        this.rt = in.readShort();
        this.grbitFrt = in.readShort();
        this.wOffset = in.readShort();
        this.at = in.readShort();
        this.grbit = in.readShort();
        if (in.available() == 0) {
            this.unused = null;
        } else {
            this.unused = Short.valueOf(in.readShort());
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return (this.unused == null ? 0 : 2) + 10;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.rt);
        out.writeShort(this.grbitFrt);
        out.writeShort(this.wOffset);
        out.writeShort(this.at);
        out.writeShort(this.grbit);
        if (this.unused != null) {
            out.writeShort(this.unused.shortValue());
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public CatLabRecord copy() {
        return new CatLabRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CAT_LAB;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rt", new Supplier() { // from class: org.apache.poi.hssf.record.chart.CatLabRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return CatLabRecord.this.m2405x508a54b6();
            }
        }, "grbitFrt", new Supplier() { // from class: org.apache.poi.hssf.record.chart.CatLabRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return CatLabRecord.this.m2406xc6047af7();
            }
        }, "wOffset", new Supplier() { // from class: org.apache.poi.hssf.record.chart.CatLabRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return CatLabRecord.this.m2407x3b7ea138();
            }
        }, "at", new Supplier() { // from class: org.apache.poi.hssf.record.chart.CatLabRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return CatLabRecord.this.m2408xb0f8c779();
            }
        }, "grbit", new Supplier() { // from class: org.apache.poi.hssf.record.chart.CatLabRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return CatLabRecord.this.m2409x2672edba();
            }
        }, "unused", new Supplier() { // from class: org.apache.poi.hssf.record.chart.CatLabRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return CatLabRecord.this.m2410x9bed13fb();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-chart-CatLabRecord, reason: not valid java name */
    public /* synthetic */ Object m2405x508a54b6() {
        return Short.valueOf(this.rt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-chart-CatLabRecord, reason: not valid java name */
    public /* synthetic */ Object m2406xc6047af7() {
        return Short.valueOf(this.grbitFrt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-chart-CatLabRecord, reason: not valid java name */
    public /* synthetic */ Object m2407x3b7ea138() {
        return Short.valueOf(this.wOffset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-chart-CatLabRecord, reason: not valid java name */
    public /* synthetic */ Object m2408xb0f8c779() {
        return Short.valueOf(this.at);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-chart-CatLabRecord, reason: not valid java name */
    public /* synthetic */ Object m2409x2672edba() {
        return Short.valueOf(this.grbit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-chart-CatLabRecord, reason: not valid java name */
    public /* synthetic */ Object m2410x9bed13fb() {
        return this.unused;
    }
}
