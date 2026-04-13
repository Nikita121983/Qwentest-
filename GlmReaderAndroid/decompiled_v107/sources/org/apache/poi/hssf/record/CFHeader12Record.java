package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.common.FtrHeader;
import org.apache.poi.hssf.record.common.FutureRecord;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class CFHeader12Record extends CFHeaderBase implements FutureRecord {
    public static final short sid = 2169;
    private FtrHeader futureHeader;

    public CFHeader12Record() {
        createEmpty();
        this.futureHeader = new FtrHeader();
        this.futureHeader.setRecordType(sid);
    }

    public CFHeader12Record(CFHeader12Record other) {
        super(other);
        this.futureHeader = other.futureHeader.copy();
    }

    public CFHeader12Record(CellRangeAddress[] regions, int nRules) {
        super(regions, nRules);
        this.futureHeader = new FtrHeader();
        this.futureHeader.setRecordType(sid);
    }

    public CFHeader12Record(RecordInputStream in) {
        this.futureHeader = new FtrHeader(in);
        read(in);
    }

    @Override // org.apache.poi.hssf.record.CFHeaderBase
    protected String getRecordName() {
        return "CFHEADER12";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.hssf.record.CFHeaderBase, org.apache.poi.hssf.record.StandardRecord
    public int getDataSize() {
        return FtrHeader.getDataSize() + super.getDataSize();
    }

    @Override // org.apache.poi.hssf.record.CFHeaderBase, org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        this.futureHeader.setAssociatedRange(getEnclosingCellRange());
        this.futureHeader.serialize(out);
        super.serialize(out);
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.common.FutureRecord
    public short getFutureRecordType() {
        return this.futureHeader.getRecordType();
    }

    @Override // org.apache.poi.hssf.record.common.FutureRecord
    public FtrHeader getFutureHeader() {
        return this.futureHeader;
    }

    @Override // org.apache.poi.hssf.record.common.FutureRecord
    public CellRangeAddress getAssociatedRange() {
        return this.futureHeader.getAssociatedRange();
    }

    @Override // org.apache.poi.hssf.record.CFHeaderBase, org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public CFHeader12Record copy() {
        return new CFHeader12Record(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CF_HEADER_12;
    }

    @Override // org.apache.poi.hssf.record.CFHeaderBase, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.hssf.record.CFHeader12Record$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFHeader12Record.this.m2256x748a7461();
            }
        }, "futureHeader", new Supplier() { // from class: org.apache.poi.hssf.record.CFHeader12Record$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return CFHeader12Record.this.getFutureHeader();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-CFHeader12Record, reason: not valid java name */
    public /* synthetic */ Object m2256x748a7461() {
        return super.getGenericProperties();
    }
}
