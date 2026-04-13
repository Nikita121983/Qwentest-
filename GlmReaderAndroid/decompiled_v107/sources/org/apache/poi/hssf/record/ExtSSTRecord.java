package org.apache.poi.hssf.record;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.ExtSSTRecord;
import org.apache.poi.hssf.record.cont.ContinuableRecord;
import org.apache.poi.hssf.record.cont.ContinuableRecordOutput;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class ExtSSTRecord extends ContinuableRecord {
    public static final int DEFAULT_BUCKET_SIZE = 8;
    public static final int MAX_BUCKETS = 128;
    public static final short sid = 255;
    private InfoSubRecord[] _sstInfos;
    private short _stringsPerBucket;

    /* loaded from: classes10.dex */
    public static final class InfoSubRecord implements GenericRecord {
        public static final int ENCODED_SIZE = 8;
        private int field_1_stream_pos;
        private int field_2_bucket_sst_offset;
        private short field_3_zero;

        public InfoSubRecord(int streamPos, int bucketSstOffset) {
            this.field_1_stream_pos = streamPos;
            this.field_2_bucket_sst_offset = bucketSstOffset;
        }

        public InfoSubRecord(InfoSubRecord other) {
            this.field_1_stream_pos = other.field_1_stream_pos;
            this.field_2_bucket_sst_offset = other.field_2_bucket_sst_offset;
            this.field_3_zero = other.field_3_zero;
        }

        public InfoSubRecord(RecordInputStream in) {
            this.field_1_stream_pos = in.readInt();
            this.field_2_bucket_sst_offset = in.readShort();
            this.field_3_zero = in.readShort();
        }

        public int getStreamPos() {
            return this.field_1_stream_pos;
        }

        public int getBucketSSTOffset() {
            return this.field_2_bucket_sst_offset;
        }

        public void serialize(LittleEndianOutput out) {
            out.writeInt(this.field_1_stream_pos);
            out.writeShort(this.field_2_bucket_sst_offset);
            out.writeShort(this.field_3_zero);
        }

        @Override // org.apache.poi.common.usermodel.GenericRecord
        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties("streamPos", new Supplier() { // from class: org.apache.poi.hssf.record.ExtSSTRecord$InfoSubRecord$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(ExtSSTRecord.InfoSubRecord.this.getStreamPos());
                }
            }, "bucketSSTOffset", new Supplier() { // from class: org.apache.poi.hssf.record.ExtSSTRecord$InfoSubRecord$$ExternalSyntheticLambda1
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(ExtSSTRecord.InfoSubRecord.this.getBucketSSTOffset());
                }
            });
        }
    }

    public ExtSSTRecord() {
        this._stringsPerBucket = (short) 8;
        this._sstInfos = new InfoSubRecord[0];
    }

    public ExtSSTRecord(ExtSSTRecord other) {
        this._stringsPerBucket = other._stringsPerBucket;
        this._sstInfos = other._sstInfos == null ? null : (InfoSubRecord[]) Stream.of((Object[]) other._sstInfos).map(new Function() { // from class: org.apache.poi.hssf.record.ExtSSTRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new ExtSSTRecord.InfoSubRecord((ExtSSTRecord.InfoSubRecord) obj);
            }
        }).toArray(new IntFunction() { // from class: org.apache.poi.hssf.record.ExtSSTRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return ExtSSTRecord.lambda$new$0(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ InfoSubRecord[] lambda$new$0(int x$0) {
        return new InfoSubRecord[x$0];
    }

    public ExtSSTRecord(RecordInputStream in) {
        this._stringsPerBucket = in.readShort();
        int nInfos = in.remaining() / 8;
        ArrayList<InfoSubRecord> lst = new ArrayList<>(nInfos);
        while (in.available() > 0) {
            InfoSubRecord info = new InfoSubRecord(in);
            lst.add(info);
            if (in.available() == 0 && in.hasNextRecord() && in.getNextSid() == 60) {
                in.nextRecord();
            }
        }
        this._sstInfos = (InfoSubRecord[]) lst.toArray(new InfoSubRecord[0]);
    }

    public void setNumStringsPerBucket(short numStrings) {
        this._stringsPerBucket = numStrings;
    }

    @Override // org.apache.poi.hssf.record.cont.ContinuableRecord
    public void serialize(ContinuableRecordOutput out) {
        out.writeShort(this._stringsPerBucket);
        for (InfoSubRecord sstInfo : this._sstInfos) {
            sstInfo.serialize(out);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getDataSize() {
        return (this._sstInfos.length * 8) + 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InfoSubRecord[] getInfoSubRecords() {
        return this._sstInfos;
    }

    public static int getNumberOfInfoRecsForStrings(int numStrings) {
        int infoRecs = numStrings / 8;
        if (numStrings % 8 != 0) {
            infoRecs++;
        }
        if (infoRecs > 128) {
            return 128;
        }
        return infoRecs;
    }

    public static int getRecordSizeForStrings(int numStrings) {
        return (getNumberOfInfoRecsForStrings(numStrings) * 8) + 6;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 255;
    }

    public void setBucketOffsets(int[] bucketAbsoluteOffsets, int[] bucketRelativeOffsets) {
        this._sstInfos = new InfoSubRecord[bucketAbsoluteOffsets.length];
        for (int i = 0; i < bucketAbsoluteOffsets.length; i++) {
            this._sstInfos[i] = new InfoSubRecord(bucketAbsoluteOffsets[i], bucketRelativeOffsets[i]);
        }
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public ExtSSTRecord copy() {
        return new ExtSSTRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.EXT_SST;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("dataSize", new Supplier() { // from class: org.apache.poi.hssf.record.ExtSSTRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(ExtSSTRecord.this.getDataSize());
            }
        }, "infoSubRecords", new Supplier() { // from class: org.apache.poi.hssf.record.ExtSSTRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return ExtSSTRecord.this.getInfoSubRecords();
            }
        });
    }
}
