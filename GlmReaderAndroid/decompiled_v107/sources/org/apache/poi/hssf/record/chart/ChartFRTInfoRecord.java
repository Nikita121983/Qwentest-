package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.hssf.record.chart.ChartFRTInfoRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class ChartFRTInfoRecord extends StandardRecord {
    public static final short sid = 2128;
    private final short grbitFrt;
    private CFRTID[] rgCFRTID;
    private final short rt;
    private final byte verOriginator;
    private final byte verWriter;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class CFRTID {
        public static final int ENCODED_SIZE = 4;
        private final int rtFirst;
        private final int rtLast;

        public CFRTID(CFRTID other) {
            this.rtFirst = other.rtFirst;
            this.rtLast = other.rtLast;
        }

        public CFRTID(LittleEndianInput in) {
            this.rtFirst = in.readShort();
            this.rtLast = in.readShort();
        }

        public void serialize(LittleEndianOutput out) {
            out.writeShort(this.rtFirst);
            out.writeShort(this.rtLast);
        }
    }

    public ChartFRTInfoRecord(ChartFRTInfoRecord other) {
        super(other);
        this.rt = other.rt;
        this.grbitFrt = other.grbitFrt;
        this.verOriginator = other.verOriginator;
        this.verWriter = other.verWriter;
        if (other.rgCFRTID != null) {
            this.rgCFRTID = (CFRTID[]) Stream.of((Object[]) other.rgCFRTID).map(new Function() { // from class: org.apache.poi.hssf.record.chart.ChartFRTInfoRecord$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return new ChartFRTInfoRecord.CFRTID((ChartFRTInfoRecord.CFRTID) obj);
                }
            }).toArray(new IntFunction() { // from class: org.apache.poi.hssf.record.chart.ChartFRTInfoRecord$$ExternalSyntheticLambda1
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return ChartFRTInfoRecord.lambda$new$0(i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CFRTID[] lambda$new$0(int x$0) {
        return new CFRTID[x$0];
    }

    public ChartFRTInfoRecord(RecordInputStream in) {
        this.rt = in.readShort();
        this.grbitFrt = in.readShort();
        this.verOriginator = in.readByte();
        this.verWriter = in.readByte();
        int cCFRTID = in.readShort();
        if (cCFRTID < 0) {
            throw new IllegalArgumentException("Had negative CFRTID: " + cCFRTID);
        }
        this.rgCFRTID = new CFRTID[cCFRTID];
        for (int i = 0; i < cCFRTID; i++) {
            this.rgCFRTID[i] = new CFRTID(in);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return (this.rgCFRTID.length * 4) + 8;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.rt);
        out.writeShort(this.grbitFrt);
        out.writeByte(this.verOriginator);
        out.writeByte(this.verWriter);
        out.writeShort(this.rgCFRTID.length);
        for (CFRTID cfrtid : this.rgCFRTID) {
            cfrtid.serialize(out);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public ChartFRTInfoRecord copy() {
        return new ChartFRTInfoRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CHART_FRT_INFO;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rt", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartFRTInfoRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartFRTInfoRecord.this.m2419x2200c558();
            }
        }, "grbitFrt", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartFRTInfoRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartFRTInfoRecord.this.m2420xaf3b76d9();
            }
        }, "verOriginator", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartFRTInfoRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartFRTInfoRecord.this.m2421x3c76285a();
            }
        }, "verWriter", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartFRTInfoRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartFRTInfoRecord.this.m2422xc9b0d9db();
            }
        }, "rgCFRTIDs", new Supplier() { // from class: org.apache.poi.hssf.record.chart.ChartFRTInfoRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return ChartFRTInfoRecord.this.m2423x56eb8b5c();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-chart-ChartFRTInfoRecord, reason: not valid java name */
    public /* synthetic */ Object m2419x2200c558() {
        return Short.valueOf(this.rt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-chart-ChartFRTInfoRecord, reason: not valid java name */
    public /* synthetic */ Object m2420xaf3b76d9() {
        return Short.valueOf(this.grbitFrt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-chart-ChartFRTInfoRecord, reason: not valid java name */
    public /* synthetic */ Object m2421x3c76285a() {
        return Byte.valueOf(this.verOriginator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-chart-ChartFRTInfoRecord, reason: not valid java name */
    public /* synthetic */ Object m2422xc9b0d9db() {
        return Byte.valueOf(this.verWriter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-chart-ChartFRTInfoRecord, reason: not valid java name */
    public /* synthetic */ Object m2423x56eb8b5c() {
        return this.rgCFRTID;
    }
}
