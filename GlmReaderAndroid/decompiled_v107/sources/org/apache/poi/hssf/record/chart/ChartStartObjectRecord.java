package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class ChartStartObjectRecord extends StandardRecord {
    public static final short sid = 2132;
    private short grbitFrt;
    private short iObjectContext;
    private short iObjectInstance1;
    private short iObjectInstance2;
    private short iObjectKind;
    private short rt;

    public ChartStartObjectRecord(ChartStartObjectRecord other) {
        super(other);
        this.rt = other.rt;
        this.grbitFrt = other.grbitFrt;
        this.iObjectKind = other.iObjectKind;
        this.iObjectContext = other.iObjectContext;
        this.iObjectInstance1 = other.iObjectInstance1;
        this.iObjectInstance2 = other.iObjectInstance2;
    }

    public ChartStartObjectRecord(RecordInputStream in) {
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
    public ChartStartObjectRecord copy() {
        return new ChartStartObjectRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CHART_START_OBJECT;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rt", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartStartObjectRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartStartObjectRecord.this.m2432xb3866be2();
            }
        }, "grbitFrt", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartStartObjectRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartStartObjectRecord.this.m2433xd91a74e3();
            }
        }, "iObjectKind", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartStartObjectRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartStartObjectRecord.this.m2434xfeae7de4();
            }
        }, "iObjectContext", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartStartObjectRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartStartObjectRecord.this.m2435x244286e5();
            }
        }, "iObjectInstance1", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartStartObjectRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartStartObjectRecord.this.m2436x49d68fe6();
            }
        }, "iObjectInstance2", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartStartObjectRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartStartObjectRecord.this.m2437x6f6a98e7();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-chart-ChartStartObjectRecord, reason: not valid java name */
    public /* synthetic */ Object m2432xb3866be2() {
        return Short.valueOf(this.rt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-chart-ChartStartObjectRecord, reason: not valid java name */
    public /* synthetic */ Object m2433xd91a74e3() {
        return Short.valueOf(this.grbitFrt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-chart-ChartStartObjectRecord, reason: not valid java name */
    public /* synthetic */ Object m2434xfeae7de4() {
        return Short.valueOf(this.iObjectKind);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-chart-ChartStartObjectRecord, reason: not valid java name */
    public /* synthetic */ Object m2435x244286e5() {
        return Short.valueOf(this.iObjectContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-chart-ChartStartObjectRecord, reason: not valid java name */
    public /* synthetic */ Object m2436x49d68fe6() {
        return Short.valueOf(this.iObjectInstance1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-chart-ChartStartObjectRecord, reason: not valid java name */
    public /* synthetic */ Object m2437x6f6a98e7() {
        return Short.valueOf(this.iObjectInstance2);
    }
}
