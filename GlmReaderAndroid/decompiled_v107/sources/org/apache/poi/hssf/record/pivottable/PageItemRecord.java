package org.apache.poi.hssf.record.pivottable;

import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.hssf.record.pivottable.PageItemRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
public final class PageItemRecord extends StandardRecord {
    public static final short sid = 182;
    private final FieldInfo[] _fieldInfos;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class FieldInfo implements GenericRecord {
        public static final int ENCODED_SIZE = 6;
        private int _idObj;
        private int _isxvd;
        private int _isxvi;

        public FieldInfo(FieldInfo other) {
            this._isxvi = other._isxvi;
            this._isxvd = other._isxvd;
            this._idObj = other._idObj;
        }

        public FieldInfo(RecordInputStream in) {
            this._isxvi = in.readShort();
            this._isxvd = in.readShort();
            this._idObj = in.readShort();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void serialize(LittleEndianOutput out) {
            out.writeShort(this._isxvi);
            out.writeShort(this._isxvd);
            out.writeShort(this._idObj);
        }

        @Override // org.apache.poi.common.usermodel.GenericRecord
        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties("isxvi", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.PageItemRecord$FieldInfo$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return PageItemRecord.FieldInfo.this.m2462x2d8cd459();
                }
            }, "isxvd", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.PageItemRecord$FieldInfo$$ExternalSyntheticLambda1
                @Override // java.util.function.Supplier
                public final Object get() {
                    return PageItemRecord.FieldInfo.this.m2463xabedd838();
                }
            }, "idObj", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.PageItemRecord$FieldInfo$$ExternalSyntheticLambda2
                @Override // java.util.function.Supplier
                public final Object get() {
                    return PageItemRecord.FieldInfo.this.m2464x2a4edc17();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-pivottable-PageItemRecord$FieldInfo, reason: not valid java name */
        public /* synthetic */ Object m2462x2d8cd459() {
            return Integer.valueOf(this._isxvi);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-pivottable-PageItemRecord$FieldInfo, reason: not valid java name */
        public /* synthetic */ Object m2463xabedd838() {
            return Integer.valueOf(this._isxvd);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-pivottable-PageItemRecord$FieldInfo, reason: not valid java name */
        public /* synthetic */ Object m2464x2a4edc17() {
            return Integer.valueOf(this._idObj);
        }
    }

    public PageItemRecord(PageItemRecord other) {
        super(other);
        this._fieldInfos = (FieldInfo[]) Stream.of((Object[]) other._fieldInfos).map(new Function() { // from class: org.apache.poi.hssf.record.pivottable.PageItemRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new PageItemRecord.FieldInfo((PageItemRecord.FieldInfo) obj);
            }
        }).toArray(new IntFunction() { // from class: org.apache.poi.hssf.record.pivottable.PageItemRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return PageItemRecord.lambda$new$0(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ FieldInfo[] lambda$new$0(int x$0) {
        return new FieldInfo[x$0];
    }

    public PageItemRecord(RecordInputStream in) {
        int dataSize = in.remaining();
        if (dataSize % 6 != 0) {
            throw new RecordFormatException("Bad data size " + dataSize);
        }
        int nItems = dataSize / 6;
        FieldInfo[] fis = new FieldInfo[nItems];
        for (int i = 0; i < fis.length; i++) {
            fis[i] = new FieldInfo(in);
        }
        this._fieldInfos = fis;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected void serialize(LittleEndianOutput out) {
        for (FieldInfo fieldInfo : this._fieldInfos) {
            fieldInfo.serialize(out);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return this._fieldInfos.length * 6;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 182;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public PageItemRecord copy() {
        return new PageItemRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.PAGE_ITEM;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("fieldInfos", new Supplier() { // from class: org.apache.poi.hssf.record.pivottable.PageItemRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return PageItemRecord.this.m2461x581434();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-pivottable-PageItemRecord, reason: not valid java name */
    public /* synthetic */ Object m2461x581434() {
        return this._fieldInfos;
    }
}
