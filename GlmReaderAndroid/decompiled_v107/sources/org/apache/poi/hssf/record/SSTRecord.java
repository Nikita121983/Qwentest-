package org.apache.poi.hssf.record;

import java.util.Iterator;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.common.UnicodeString;
import org.apache.poi.hssf.record.cont.ContinuableRecord;
import org.apache.poi.hssf.record.cont.ContinuableRecordOutput;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IntMapper;

/* loaded from: classes10.dex */
public final class SSTRecord extends ContinuableRecord {
    private static final UnicodeString EMPTY_STRING = new UnicodeString("");
    public static final short sid = 252;
    private int[] bucketAbsoluteOffsets;
    private int[] bucketRelativeOffsets;
    private final SSTDeserializer deserializer;
    private int field_1_num_strings;
    private int field_2_num_unique_strings;
    private final IntMapper<UnicodeString> field_3_strings;

    public SSTRecord() {
        this.field_1_num_strings = 0;
        this.field_2_num_unique_strings = 0;
        this.field_3_strings = new IntMapper<>();
        this.deserializer = new SSTDeserializer(this.field_3_strings);
    }

    public SSTRecord(SSTRecord other) {
        super(other);
        this.field_1_num_strings = other.field_1_num_strings;
        this.field_2_num_unique_strings = other.field_2_num_unique_strings;
        this.field_3_strings = other.field_3_strings.copy();
        this.deserializer = new SSTDeserializer(this.field_3_strings);
        this.bucketAbsoluteOffsets = other.bucketAbsoluteOffsets == null ? null : (int[]) other.bucketAbsoluteOffsets.clone();
        this.bucketRelativeOffsets = other.bucketRelativeOffsets != null ? (int[]) other.bucketRelativeOffsets.clone() : null;
    }

    public int addString(UnicodeString string) {
        this.field_1_num_strings++;
        UnicodeString ucs = string == null ? EMPTY_STRING : string;
        int index = this.field_3_strings.getIndex(ucs);
        if (index != -1) {
            return index;
        }
        int rval = this.field_3_strings.size();
        this.field_2_num_unique_strings++;
        SSTDeserializer.addToStringTable(this.field_3_strings, ucs);
        return rval;
    }

    public int getNumStrings() {
        return this.field_1_num_strings;
    }

    public int getNumUniqueStrings() {
        return this.field_2_num_unique_strings;
    }

    public UnicodeString getString(int id) {
        return this.field_3_strings.get(id);
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    public SSTRecord(RecordInputStream in) {
        this.field_1_num_strings = in.readInt();
        this.field_2_num_unique_strings = in.readInt();
        this.field_3_strings = new IntMapper<>();
        this.deserializer = new SSTDeserializer(this.field_3_strings);
        if (this.field_1_num_strings == 0) {
            this.field_2_num_unique_strings = 0;
        } else {
            this.deserializer.manufactureStrings(this.field_2_num_unique_strings, in);
        }
    }

    Iterator<UnicodeString> getStrings() {
        return this.field_3_strings.iterator();
    }

    int countStrings() {
        return this.field_3_strings.size();
    }

    @Override // org.apache.poi.hssf.record.cont.ContinuableRecord
    protected void serialize(ContinuableRecordOutput out) {
        SSTSerializer serializer = new SSTSerializer(this.field_3_strings, getNumStrings(), getNumUniqueStrings());
        serializer.serialize(out);
        this.bucketAbsoluteOffsets = serializer.getBucketAbsoluteOffsets();
        this.bucketRelativeOffsets = serializer.getBucketRelativeOffsets();
    }

    public ExtSSTRecord createExtSSTRecord(int sstOffset) {
        if (this.bucketAbsoluteOffsets == null || this.bucketRelativeOffsets == null) {
            throw new IllegalStateException("SST record has not yet been serialized.");
        }
        ExtSSTRecord extSST = new ExtSSTRecord();
        extSST.setNumStringsPerBucket((short) 8);
        int[] absoluteOffsets = (int[]) this.bucketAbsoluteOffsets.clone();
        int[] relativeOffsets = (int[]) this.bucketRelativeOffsets.clone();
        for (int i = 0; i < absoluteOffsets.length; i++) {
            absoluteOffsets[i] = absoluteOffsets[i] + sstOffset;
        }
        extSST.setBucketOffsets(absoluteOffsets, relativeOffsets);
        return extSST;
    }

    public int calcExtSSTRecordSize() {
        return ExtSSTRecord.getRecordSizeForStrings(this.field_3_strings.size());
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public SSTRecord copy() {
        return new SSTRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SST;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        Supplier supplier = new Supplier() { // from class: org.apache.poi.hssf.record.SSTRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(SSTRecord.this.getNumStrings());
            }
        };
        Supplier supplier2 = new Supplier() { // from class: org.apache.poi.hssf.record.SSTRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(SSTRecord.this.getNumUniqueStrings());
            }
        };
        final IntMapper<UnicodeString> intMapper = this.field_3_strings;
        intMapper.getClass();
        return GenericRecordUtil.getGenericProperties("numStrings", supplier, "numUniqueStrings", supplier2, "strings", new Supplier() { // from class: org.apache.poi.hssf.record.SSTRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return IntMapper.this.getElements();
            }
        }, "bucketAbsoluteOffsets", new Supplier() { // from class: org.apache.poi.hssf.record.SSTRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return SSTRecord.this.m2367x46d63a86();
            }
        }, "bucketRelativeOffsets", new Supplier() { // from class: org.apache.poi.hssf.record.SSTRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return SSTRecord.this.m2368xa828d725();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-SSTRecord, reason: not valid java name */
    public /* synthetic */ Object m2367x46d63a86() {
        return this.bucketAbsoluteOffsets;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-SSTRecord, reason: not valid java name */
    public /* synthetic */ Object m2368xa828d725() {
        return this.bucketRelativeOffsets;
    }
}
