package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class DataLabelExtensionRecord extends StandardRecord {
    public static final short sid = 2154;
    private int grbitFrt;
    private int rt;
    private final byte[] unused;

    public DataLabelExtensionRecord(DataLabelExtensionRecord other) {
        super(other);
        this.unused = new byte[8];
        this.rt = other.rt;
        this.grbitFrt = other.grbitFrt;
        System.arraycopy(other.unused, 0, this.unused, 0, this.unused.length);
    }

    public DataLabelExtensionRecord(RecordInputStream in) {
        this.unused = new byte[8];
        this.rt = in.readShort();
        this.grbitFrt = in.readShort();
        in.readFully(this.unused);
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
    protected void serialize(LittleEndianOutput out) {
        out.writeShort(this.rt);
        out.writeShort(this.grbitFrt);
        out.write(this.unused);
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public DataLabelExtensionRecord copy() {
        return new DataLabelExtensionRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DATA_LABEL_EXTENSION;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rt", new Supplier() { // from class: org.apache.poi.hssf.record.chart.DataLabelExtensionRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return DataLabelExtensionRecord.this.m2439x2bb7f954();
            }
        }, "grbitFrt", new Supplier() { // from class: org.apache.poi.hssf.record.chart.DataLabelExtensionRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return DataLabelExtensionRecord.this.m2440x3c6dc615();
            }
        }, "unused", new Supplier() { // from class: org.apache.poi.hssf.record.chart.DataLabelExtensionRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return DataLabelExtensionRecord.this.m2441x4d2392d6();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-chart-DataLabelExtensionRecord, reason: not valid java name */
    public /* synthetic */ Object m2439x2bb7f954() {
        return Integer.valueOf(this.rt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-chart-DataLabelExtensionRecord, reason: not valid java name */
    public /* synthetic */ Object m2440x3c6dc615() {
        return Integer.valueOf(this.grbitFrt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-chart-DataLabelExtensionRecord, reason: not valid java name */
    public /* synthetic */ Object m2441x4d2392d6() {
        return this.unused;
    }
}
