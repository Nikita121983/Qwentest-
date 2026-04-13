package org.apache.poi.hssf.record;

import java.util.Arrays;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class MergeCellsRecord extends StandardRecord {
    public static final short sid = 229;
    private final int _numberOfRegions;
    private final CellRangeAddress[] _regions;
    private final int _startIndex;

    public MergeCellsRecord(MergeCellsRecord other) {
        super(other);
        this._regions = other._regions == null ? null : (CellRangeAddress[]) Stream.of((Object[]) other._regions).map(new FeatRecord$$ExternalSyntheticLambda8()).toArray(new IntFunction() { // from class: org.apache.poi.hssf.record.MergeCellsRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return MergeCellsRecord.lambda$new$0(i);
            }
        });
        this._startIndex = other._startIndex;
        this._numberOfRegions = other._numberOfRegions;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CellRangeAddress[] lambda$new$0(int x$0) {
        return new CellRangeAddress[x$0];
    }

    public MergeCellsRecord(CellRangeAddress[] regions, int startIndex, int numberOfRegions) {
        this._regions = regions;
        this._startIndex = startIndex;
        this._numberOfRegions = numberOfRegions;
    }

    public MergeCellsRecord(RecordInputStream in) {
        int nRegions = in.readUShort();
        CellRangeAddress[] cras = new CellRangeAddress[nRegions];
        for (int i = 0; i < nRegions; i++) {
            cras[i] = new CellRangeAddress(in);
        }
        this._numberOfRegions = nRegions;
        this._startIndex = 0;
        this._regions = cras;
    }

    public short getNumAreas() {
        return (short) this._numberOfRegions;
    }

    public CellRangeAddress getAreaAt(int index) {
        return this._regions[this._startIndex + index];
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return CellRangeAddressList.getEncodedSize(this._numberOfRegions);
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this._numberOfRegions);
        for (int i = 0; i < this._numberOfRegions; i++) {
            this._regions[this._startIndex + i].serialize(out);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public MergeCellsRecord copy() {
        return new MergeCellsRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.MERGE_CELLS;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("numRegions", new Supplier() { // from class: org.apache.poi.hssf.record.MergeCellsRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(MergeCellsRecord.this.getNumAreas());
            }
        }, "regions", new Supplier() { // from class: org.apache.poi.hssf.record.MergeCellsRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return MergeCellsRecord.this.m2333x7ee4340a();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-MergeCellsRecord, reason: not valid java name */
    public /* synthetic */ Object m2333x7ee4340a() {
        return (CellRangeAddress[]) Arrays.copyOfRange(this._regions, this._startIndex, this._startIndex + this._numberOfRegions);
    }
}
