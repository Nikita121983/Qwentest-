package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class ChartEndBlockRecord extends StandardRecord {
    public static final short sid = 2131;
    private short grbitFrt;
    private short iObjectKind;
    private short rt;
    private byte[] unused;

    public ChartEndBlockRecord() {
    }

    public ChartEndBlockRecord(ChartEndBlockRecord other) {
        super(other);
        this.rt = other.rt;
        this.grbitFrt = other.grbitFrt;
        this.iObjectKind = other.iObjectKind;
        this.unused = other.unused == null ? null : (byte[]) other.unused.clone();
    }

    public ChartEndBlockRecord(RecordInputStream in) {
        this.rt = in.readShort();
        this.grbitFrt = in.readShort();
        this.iObjectKind = in.readShort();
        if (in.available() == 0) {
            this.unused = new byte[0];
        } else {
            this.unused = new byte[6];
            in.readFully(this.unused);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return this.unused.length + 6;
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
        out.write(this.unused);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public ChartEndBlockRecord copy() {
        return new ChartEndBlockRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CHART_END_BLOCK;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rt", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartEndBlockRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartEndBlockRecord.this.m2411x1aba1773();
            }
        }, "grbitFrt", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartEndBlockRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartEndBlockRecord.this.m2412x34d59612();
            }
        }, "iObjectKind", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartEndBlockRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartEndBlockRecord.this.m2413x4ef114b1();
            }
        }, "unused", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartEndBlockRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartEndBlockRecord.this.m2414x690c9350();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-chart-ChartEndBlockRecord, reason: not valid java name */
    public /* synthetic */ Object m2411x1aba1773() {
        return Short.valueOf(this.rt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-chart-ChartEndBlockRecord, reason: not valid java name */
    public /* synthetic */ Object m2412x34d59612() {
        return Short.valueOf(this.grbitFrt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-chart-ChartEndBlockRecord, reason: not valid java name */
    public /* synthetic */ Object m2413x4ef114b1() {
        return Short.valueOf(this.iObjectKind);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-chart-ChartEndBlockRecord, reason: not valid java name */
    public /* synthetic */ Object m2414x690c9350() {
        return this.unused;
    }
}
