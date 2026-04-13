package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class ChartEndObjectRecord extends StandardRecord {
    public static final short sid = 2133;
    private short grbitFrt;
    private short iObjectKind;
    private byte[] reserved;
    private short rt;

    public ChartEndObjectRecord(ChartEndObjectRecord other) {
        super(other);
        this.rt = other.rt;
        this.grbitFrt = other.grbitFrt;
        this.iObjectKind = other.iObjectKind;
        this.reserved = other.reserved == null ? null : (byte[]) other.reserved.clone();
    }

    public ChartEndObjectRecord(RecordInputStream in) {
        this.rt = in.readShort();
        this.grbitFrt = in.readShort();
        this.iObjectKind = in.readShort();
        this.reserved = new byte[6];
        if (in.available() != 0) {
            in.readFully(this.reserved);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return 12;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.rt);
        out.writeShort(this.grbitFrt);
        out.writeShort(this.iObjectKind);
        out.write(this.reserved);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public ChartEndObjectRecord copy() {
        return new ChartEndObjectRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CHART_END_OBJECT;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rt", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartEndObjectRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartEndObjectRecord.this.m2415xd9edfedb();
            }
        }, "grbitFrt", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartEndObjectRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartEndObjectRecord.this.m2416x342541c();
            }
        }, "iObjectKind", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartEndObjectRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartEndObjectRecord.this.m2417x2c96a95d();
            }
        }, "reserved", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartEndObjectRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartEndObjectRecord.this.m2418x55eafe9e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-chart-ChartEndObjectRecord, reason: not valid java name */
    public /* synthetic */ Object m2415xd9edfedb() {
        return Short.valueOf(this.rt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-chart-ChartEndObjectRecord, reason: not valid java name */
    public /* synthetic */ Object m2416x342541c() {
        return Short.valueOf(this.grbitFrt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-chart-ChartEndObjectRecord, reason: not valid java name */
    public /* synthetic */ Object m2417x2c96a95d() {
        return Short.valueOf(this.iObjectKind);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-chart-ChartEndObjectRecord, reason: not valid java name */
    public /* synthetic */ Object m2418x55eafe9e() {
        return this.reserved;
    }
}
