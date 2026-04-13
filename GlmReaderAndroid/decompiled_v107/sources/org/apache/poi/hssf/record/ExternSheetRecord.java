package org.apache.poi.hssf.record;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.ExternSheetRecord;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public class ExternSheetRecord extends StandardRecord {
    public static final short sid = 23;
    private final List<RefSubRecord> _list = new ArrayList();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class RefSubRecord implements GenericRecord {
        public static final int ENCODED_SIZE = 6;
        private final int _extBookIndex;
        private int _firstSheetIndex;
        private int _lastSheetIndex;

        public RefSubRecord(int extBookIndex, int firstSheetIndex, int lastSheetIndex) {
            this._extBookIndex = extBookIndex;
            this._firstSheetIndex = firstSheetIndex;
            this._lastSheetIndex = lastSheetIndex;
        }

        public RefSubRecord(RefSubRecord other) {
            this._extBookIndex = other._extBookIndex;
            this._firstSheetIndex = other._firstSheetIndex;
            this._lastSheetIndex = other._lastSheetIndex;
        }

        public RefSubRecord(RecordInputStream in) {
            this(in.readShort(), in.readShort(), in.readShort());
        }

        public int getExtBookIndex() {
            return this._extBookIndex;
        }

        public int getFirstSheetIndex() {
            return this._firstSheetIndex;
        }

        public int getLastSheetIndex() {
            return this._lastSheetIndex;
        }

        public String toString() {
            return GenericRecordJsonWriter.marshal(this);
        }

        public void serialize(LittleEndianOutput out) {
            out.writeShort(this._extBookIndex);
            out.writeShort(this._firstSheetIndex);
            out.writeShort(this._lastSheetIndex);
        }

        @Override // org.apache.poi.common.usermodel.GenericRecord
        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties("extBookIndex", new Supplier() { // from class: org.apache.poi.hssf.record.ExternSheetRecord$RefSubRecord$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(ExternSheetRecord.RefSubRecord.this.getExtBookIndex());
                }
            }, "firstSheetIndex", new Supplier() { // from class: org.apache.poi.hssf.record.ExternSheetRecord$RefSubRecord$$ExternalSyntheticLambda1
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(ExternSheetRecord.RefSubRecord.this.getFirstSheetIndex());
                }
            }, "lastSheetIndex", new Supplier() { // from class: org.apache.poi.hssf.record.ExternSheetRecord$RefSubRecord$$ExternalSyntheticLambda2
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(ExternSheetRecord.RefSubRecord.this.getLastSheetIndex());
                }
            });
        }
    }

    public ExternSheetRecord() {
    }

    public ExternSheetRecord(ExternSheetRecord other) {
        Stream<R> map = other._list.stream().map(new Function() { // from class: org.apache.poi.hssf.record.ExternSheetRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new ExternSheetRecord.RefSubRecord((ExternSheetRecord.RefSubRecord) obj);
            }
        });
        final List<RefSubRecord> list = this._list;
        list.getClass();
        map.forEach(new Consumer() { // from class: org.apache.poi.hssf.record.ExternSheetRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                list.add((ExternSheetRecord.RefSubRecord) obj);
            }
        });
    }

    public ExternSheetRecord(RecordInputStream in) {
        int nItems = in.readShort();
        for (int i = 0; i < nItems; i++) {
            RefSubRecord rec = new RefSubRecord(in);
            this._list.add(rec);
        }
    }

    public int getNumOfRefs() {
        return this._list.size();
    }

    public void addREFRecord(RefSubRecord rec) {
        this._list.add(rec);
    }

    public int getNumOfREFRecords() {
        return this._list.size();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return (this._list.size() * 6) + 2;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        int nItems = this._list.size();
        out.writeShort(nItems);
        for (int i = 0; i < nItems; i++) {
            getRef(i).serialize(out);
        }
    }

    private RefSubRecord getRef(int i) {
        return this._list.get(i);
    }

    public void removeSheet(int sheetIdx) {
        int nItems = this._list.size();
        for (int i = 0; i < nItems; i++) {
            RefSubRecord refSubRecord = this._list.get(i);
            if (refSubRecord.getFirstSheetIndex() == sheetIdx && refSubRecord.getLastSheetIndex() == sheetIdx) {
                this._list.set(i, new RefSubRecord(refSubRecord.getExtBookIndex(), -1, -1));
            } else if (refSubRecord.getFirstSheetIndex() > sheetIdx && refSubRecord.getLastSheetIndex() > sheetIdx) {
                this._list.set(i, new RefSubRecord(refSubRecord.getExtBookIndex(), refSubRecord.getFirstSheetIndex() - 1, refSubRecord.getLastSheetIndex() - 1));
            }
        }
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 23;
    }

    public int getExtbookIndexFromRefIndex(int refIndex) {
        RefSubRecord refRec = getRef(refIndex);
        return refRec.getExtBookIndex();
    }

    public int findRefIndexFromExtBookIndex(int extBookIndex) {
        int nItems = this._list.size();
        for (int i = 0; i < nItems; i++) {
            if (getRef(i).getExtBookIndex() == extBookIndex) {
                return i;
            }
        }
        return -1;
    }

    public int getFirstSheetIndexFromRefIndex(int extRefIndex) {
        return getRef(extRefIndex).getFirstSheetIndex();
    }

    public int getLastSheetIndexFromRefIndex(int extRefIndex) {
        return getRef(extRefIndex).getLastSheetIndex();
    }

    public int addRef(int extBookIndex, int firstSheetIndex, int lastSheetIndex) {
        this._list.add(new RefSubRecord(extBookIndex, firstSheetIndex, lastSheetIndex));
        return this._list.size() - 1;
    }

    public int getRefIxForSheet(int externalBookIndex, int firstSheetIndex, int lastSheetIndex) {
        int nItems = this._list.size();
        for (int i = 0; i < nItems; i++) {
            RefSubRecord ref = getRef(i);
            if (ref.getExtBookIndex() == externalBookIndex && ref.getFirstSheetIndex() == firstSheetIndex && ref.getLastSheetIndex() == lastSheetIndex) {
                return i;
            }
        }
        return -1;
    }

    public static ExternSheetRecord combine(ExternSheetRecord[] esrs) {
        ExternSheetRecord result = new ExternSheetRecord();
        for (ExternSheetRecord esr : esrs) {
            int nRefs = esr.getNumOfREFRecords();
            for (int j = 0; j < nRefs; j++) {
                result.addREFRecord(esr.getRef(j));
            }
        }
        return result;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public ExternSheetRecord copy() {
        return new ExternSheetRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.EXTERN_SHEET;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("refrec", new Supplier() { // from class: org.apache.poi.hssf.record.ExternSheetRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return ExternSheetRecord.this.m2295x14fd4cd1();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-ExternSheetRecord, reason: not valid java name */
    public /* synthetic */ Object m2295x14fd4cd1() {
        return this._list;
    }
}
