package org.apache.poi.hssf.record;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.LittleEndianOutputStream;

/* loaded from: classes10.dex */
public abstract class SubRecord implements Duplicatable, GenericRecord {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 1000000;
    private static int MAX_RECORD_LENGTH = 1000000;

    @Override // org.apache.poi.common.Duplicatable
    public abstract SubRecord copy();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int getDataSize();

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public abstract SubRecordTypes getGenericRecordType();

    public abstract void serialize(LittleEndianOutput littleEndianOutput);

    /* loaded from: classes10.dex */
    public enum SubRecordTypes {
        UNKNOWN(-1, new RecordConstructor() { // from class: org.apache.poi.hssf.record.SubRecord$SubRecordTypes$$ExternalSyntheticLambda0
            @Override // org.apache.poi.hssf.record.SubRecord.SubRecordTypes.RecordConstructor
            public final SubRecord apply(LittleEndianInput littleEndianInput, int i, int i2) {
                return new SubRecord.UnknownSubRecord(littleEndianInput, i, i2);
            }
        }),
        END(0, new RecordConstructor() { // from class: org.apache.poi.hssf.record.SubRecord$SubRecordTypes$$ExternalSyntheticLambda3
            @Override // org.apache.poi.hssf.record.SubRecord.SubRecordTypes.RecordConstructor
            public final SubRecord apply(LittleEndianInput littleEndianInput, int i, int i2) {
                return new EndSubRecord(littleEndianInput, i, i2);
            }
        }),
        GROUP_MARKER(6, new RecordConstructor() { // from class: org.apache.poi.hssf.record.SubRecord$SubRecordTypes$$ExternalSyntheticLambda4
            @Override // org.apache.poi.hssf.record.SubRecord.SubRecordTypes.RecordConstructor
            public final SubRecord apply(LittleEndianInput littleEndianInput, int i, int i2) {
                return new GroupMarkerSubRecord(littleEndianInput, i, i2);
            }
        }),
        FT_CF(7, new RecordConstructor() { // from class: org.apache.poi.hssf.record.SubRecord$SubRecordTypes$$ExternalSyntheticLambda5
            @Override // org.apache.poi.hssf.record.SubRecord.SubRecordTypes.RecordConstructor
            public final SubRecord apply(LittleEndianInput littleEndianInput, int i, int i2) {
                return new FtCfSubRecord(littleEndianInput, i, i2);
            }
        }),
        FT_PIO_GRBIT(8, new RecordConstructor() { // from class: org.apache.poi.hssf.record.SubRecord$SubRecordTypes$$ExternalSyntheticLambda6
            @Override // org.apache.poi.hssf.record.SubRecord.SubRecordTypes.RecordConstructor
            public final SubRecord apply(LittleEndianInput littleEndianInput, int i, int i2) {
                return new FtPioGrbitSubRecord(littleEndianInput, i, i2);
            }
        }),
        EMBEDDED_OBJECT_REF(9, new RecordConstructor() { // from class: org.apache.poi.hssf.record.SubRecord$SubRecordTypes$$ExternalSyntheticLambda7
            @Override // org.apache.poi.hssf.record.SubRecord.SubRecordTypes.RecordConstructor
            public final SubRecord apply(LittleEndianInput littleEndianInput, int i, int i2) {
                return new EmbeddedObjectRefSubRecord(littleEndianInput, i, i2);
            }
        }),
        FT_CBLS(12, new RecordConstructor() { // from class: org.apache.poi.hssf.record.SubRecord$SubRecordTypes$$ExternalSyntheticLambda8
            @Override // org.apache.poi.hssf.record.SubRecord.SubRecordTypes.RecordConstructor
            public final SubRecord apply(LittleEndianInput littleEndianInput, int i, int i2) {
                return new FtCblsSubRecord(littleEndianInput, i, i2);
            }
        }),
        NOTE_STRUCTURE(13, new RecordConstructor() { // from class: org.apache.poi.hssf.record.SubRecord$SubRecordTypes$$ExternalSyntheticLambda9
            @Override // org.apache.poi.hssf.record.SubRecord.SubRecordTypes.RecordConstructor
            public final SubRecord apply(LittleEndianInput littleEndianInput, int i, int i2) {
                return new NoteStructureSubRecord(littleEndianInput, i, i2);
            }
        }),
        LBS_DATA(19, new RecordConstructor() { // from class: org.apache.poi.hssf.record.SubRecord$SubRecordTypes$$ExternalSyntheticLambda10
            @Override // org.apache.poi.hssf.record.SubRecord.SubRecordTypes.RecordConstructor
            public final SubRecord apply(LittleEndianInput littleEndianInput, int i, int i2) {
                return new LbsDataSubRecord(littleEndianInput, i, i2);
            }
        }),
        COMMON_OBJECT_DATA(21, new RecordConstructor() { // from class: org.apache.poi.hssf.record.SubRecord$SubRecordTypes$$ExternalSyntheticLambda1
            @Override // org.apache.poi.hssf.record.SubRecord.SubRecordTypes.RecordConstructor
            public final SubRecord apply(LittleEndianInput littleEndianInput, int i, int i2) {
                return new CommonObjectDataSubRecord(littleEndianInput, i, i2);
            }
        });

        private static final Map<Short, SubRecordTypes> LOOKUP = Collections.unmodifiableMap((Map) Arrays.stream(values()).collect(Collectors.toMap(new Function() { // from class: org.apache.poi.hssf.record.SubRecord$SubRecordTypes$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Short.valueOf(((SubRecord.SubRecordTypes) obj).getSid());
            }
        }, Function.identity())));
        public final RecordConstructor<?> recordConstructor;
        public final short sid;

        @FunctionalInterface
        /* loaded from: classes10.dex */
        public interface RecordConstructor<T extends SubRecord> {
            T apply(LittleEndianInput littleEndianInput, int i, int i2);
        }

        SubRecordTypes(int sid, RecordConstructor recordConstructor) {
            this.sid = (short) sid;
            this.recordConstructor = recordConstructor;
        }

        public static SubRecordTypes forSID(int sid) {
            return LOOKUP.getOrDefault(Short.valueOf((short) sid), UNKNOWN);
        }

        public short getSid() {
            return this.sid;
        }
    }

    public static void setMaxRecordLength(int length) {
        MAX_RECORD_LENGTH = length;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SubRecord() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SubRecord(SubRecord other) {
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [org.apache.poi.hssf.record.SubRecord] */
    public static SubRecord createSubRecord(LittleEndianInput in, int cmoOt) {
        int sid = in.readUShort();
        int size = in.readUShort();
        SubRecordTypes srt = SubRecordTypes.forSID(sid);
        return srt.recordConstructor.apply(in, size, srt == SubRecordTypes.UNKNOWN ? sid : cmoOt);
    }

    public final String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    public byte[] serialize() {
        int size = getDataSize() + 4;
        UnsynchronizedByteArrayOutputStream baos = UnsynchronizedByteArrayOutputStream.builder().setBufferSize(size).get();
        serialize(new LittleEndianOutputStream(baos));
        if (baos.size() != size) {
            throw new IllegalStateException("write size mismatch");
        }
        return baos.toByteArray();
    }

    public boolean isTerminating() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class UnknownSubRecord extends SubRecord {
        private final byte[] _data;
        private final int _sid;

        public UnknownSubRecord(LittleEndianInput in, int size, int sid) {
            this._sid = sid;
            byte[] buf = IOUtils.safelyAllocate(size, SubRecord.MAX_RECORD_LENGTH);
            in.readFully(buf);
            this._data = buf;
        }

        @Override // org.apache.poi.hssf.record.SubRecord
        protected int getDataSize() {
            return this._data.length;
        }

        @Override // org.apache.poi.hssf.record.SubRecord
        public void serialize(LittleEndianOutput out) {
            out.writeShort(this._sid);
            out.writeShort(this._data.length);
            out.write(this._data);
        }

        @Override // org.apache.poi.hssf.record.SubRecord, org.apache.poi.common.Duplicatable
        public UnknownSubRecord copy() {
            return this;
        }

        @Override // org.apache.poi.hssf.record.SubRecord, org.apache.poi.common.usermodel.GenericRecord
        public SubRecordTypes getGenericRecordType() {
            return SubRecordTypes.UNKNOWN;
        }

        @Override // org.apache.poi.common.usermodel.GenericRecord
        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties("sid", new Supplier() { // from class: org.apache.poi.hssf.record.SubRecord$UnknownSubRecord$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return SubRecord.UnknownSubRecord.this.m2376x6766f659();
                }
            }, "data", new Supplier() { // from class: org.apache.poi.hssf.record.SubRecord$UnknownSubRecord$$ExternalSyntheticLambda1
                @Override // java.util.function.Supplier
                public final Object get() {
                    return SubRecord.UnknownSubRecord.this.m2377x90bb4b9a();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-SubRecord$UnknownSubRecord, reason: not valid java name */
        public /* synthetic */ Object m2376x6766f659() {
            return Integer.valueOf(this._sid);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-SubRecord$UnknownSubRecord, reason: not valid java name */
        public /* synthetic */ Object m2377x90bb4b9a() {
            return this._data;
        }
    }
}
