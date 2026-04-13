package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class ChartStartBlockRecord extends StandardRecord {
    public static final short sid = 2130;
    private short grbitFrt;
    private short iObjectContext;
    private short iObjectInstance1;
    private short iObjectInstance2;
    private short iObjectKind;
    private short rt;

    public ChartStartBlockRecord() {
    }

    public ChartStartBlockRecord(ChartStartBlockRecord other) {
        super(other);
        this.rt = other.rt;
        this.grbitFrt = other.grbitFrt;
        this.iObjectKind = other.iObjectKind;
        this.iObjectContext = other.iObjectContext;
        this.iObjectInstance1 = other.iObjectInstance1;
        this.iObjectInstance2 = other.iObjectInstance2;
    }

    public ChartStartBlockRecord(RecordInputStream in) {
        this.rt = in.readShort();
        this.grbitFrt = in.readShort();
        this.iObjectKind = in.readShort();
        this.iObjectContext = in.readShort();
        this.iObjectInstance1 = in.readShort();
        this.iObjectInstance2 = in.readShort();
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
        out.writeShort(this.iObjectContext);
        out.writeShort(this.iObjectInstance1);
        out.writeShort(this.iObjectInstance2);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public ChartStartBlockRecord copy() {
        return new ChartStartBlockRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CHART_START_BLOCK;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rt", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartStartBlockRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartStartBlockRecord.this.m2426xd76c6d8c();
            }
        }, "grbitFrt", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartStartBlockRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartStartBlockRecord.this.m2427xd8a2c06b();
            }
        }, "iObjectKind", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartStartBlockRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartStartBlockRecord.this.m2428xd9d9134a();
            }
        }, "iObjectContext", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartStartBlockRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartStartBlockRecord.this.m2429xdb0f6629();
            }
        }, "iObjectInstance1", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartStartBlockRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartStartBlockRecord.this.m2430xdc45b908();
            }
        }, "iObjectInstance2", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartStartBlockRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartStartBlockRecord.this.m2431xdd7c0be7();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-chart-ChartStartBlockRecord, reason: not valid java name */
    public /* synthetic */ Object m2426xd76c6d8c() {
        return Short.valueOf(this.rt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-chart-ChartStartBlockRecord, reason: not valid java name */
    public /* synthetic */ Object m2427xd8a2c06b() {
        return Short.valueOf(this.grbitFrt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-chart-ChartStartBlockRecord, reason: not valid java name */
    public /* synthetic */ Object m2428xd9d9134a() {
        return Short.valueOf(this.iObjectKind);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-chart-ChartStartBlockRecord, reason: not valid java name */
    public /* synthetic */ Object m2429xdb0f6629() {
        return Short.valueOf(this.iObjectContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-chart-ChartStartBlockRecord, reason: not valid java name */
    public /* synthetic */ Object m2430xdc45b908() {
        return Short.valueOf(this.iObjectInstance1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-chart-ChartStartBlockRecord, reason: not valid java name */
    public /* synthetic */ Object m2431xdd7c0be7() {
        return Short.valueOf(this.iObjectInstance2);
    }
}
