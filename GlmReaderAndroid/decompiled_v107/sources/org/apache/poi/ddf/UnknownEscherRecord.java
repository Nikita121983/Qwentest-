package org.apache.poi.ddf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;

/* loaded from: classes10.dex */
public final class UnknownEscherRecord extends EscherRecord {
    private static final int MAX_NESTED_CHILD_NODES = 1000;
    private final List<EscherRecord> _childRecords;
    private byte[] thedata;
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000000;
    private static int MAX_RECORD_LENGTH = DEFAULT_MAX_RECORD_LENGTH;
    private static final byte[] NO_BYTES = new byte[0];

    public static void setMaxRecordLength(int length) {
        MAX_RECORD_LENGTH = length;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public UnknownEscherRecord() {
        this.thedata = NO_BYTES;
        this._childRecords = new ArrayList();
    }

    public UnknownEscherRecord(UnknownEscherRecord other) {
        super(other);
        this.thedata = NO_BYTES;
        this._childRecords = new ArrayList();
        Stream<R> map = other._childRecords.stream().map(new EscherContainerRecord$$ExternalSyntheticLambda1());
        List<EscherRecord> list = this._childRecords;
        list.getClass();
        map.forEach(new EscherContainerRecord$$ExternalSyntheticLambda2(list));
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        return fillFields(data, offset, recordFactory, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ddf.EscherRecord
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory, int nesting) {
        int childBytesWritten;
        if (nesting > 1000) {
            throw new IllegalStateException("Had more than the limit of 1000 nested child notes");
        }
        int bytesRemaining = readHeader(data, offset);
        int available = data.length - (offset + 8);
        if (bytesRemaining > available) {
            bytesRemaining = available;
        }
        if (isContainerRecord()) {
            this.thedata = new byte[0];
            int offset2 = offset + 8;
            int bytesWritten = 0 + 8;
            while (bytesRemaining > 0) {
                EscherRecord child = recordFactory.createRecord(data, offset2);
                if (child instanceof EscherContainerRecord) {
                    childBytesWritten = ((EscherContainerRecord) child).fillFields(data, offset2, recordFactory, nesting + 1);
                } else {
                    int childBytesWritten2 = nesting + 1;
                    childBytesWritten = child.fillFields(data, offset2, recordFactory, childBytesWritten2);
                }
                bytesWritten += childBytesWritten;
                offset2 += childBytesWritten;
                bytesRemaining -= childBytesWritten;
                getChildRecords().add(child);
            }
            return bytesWritten;
        }
        if (bytesRemaining < 0) {
            bytesRemaining = 0;
        }
        this.thedata = IOUtils.safelyClone(data, offset + 8, bytesRemaining, MAX_RECORD_LENGTH);
        return bytesRemaining + 8;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, getRecordId(), this);
        LittleEndian.putShort(data, offset, getOptions());
        LittleEndian.putShort(data, offset + 2, getRecordId());
        int remainingBytes = this.thedata.length;
        for (EscherRecord r : this._childRecords) {
            remainingBytes += r.getRecordSize();
        }
        LittleEndian.putInt(data, offset + 4, remainingBytes);
        System.arraycopy(this.thedata, 0, data, offset + 8, this.thedata.length);
        int pos = offset + 8 + this.thedata.length;
        for (EscherRecord r2 : this._childRecords) {
            pos += r2.serialize(pos, data, listener);
        }
        listener.afterRecordSerialize(pos, getRecordId(), pos - offset, this);
        return pos - offset;
    }

    public byte[] getData() {
        return this.thedata;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int getRecordSize() {
        return this.thedata.length + 8;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public List<EscherRecord> getChildRecords() {
        return this._childRecords;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public void setChildRecords(List<EscherRecord> childRecords) {
        if (childRecords == this._childRecords) {
            return;
        }
        if (childRecords.size() > MAX_NUMBER_OF_CHILDREN) {
            throw new IllegalStateException("Cannot add more than " + MAX_NUMBER_OF_CHILDREN + " child records, you can use 'EscherRecord.setMaxNumberOfChildren()' to increase the allow size");
        }
        this._childRecords.clear();
        this._childRecords.addAll(childRecords);
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public String getRecordName() {
        return "Unknown 0x" + HexDump.toHex(getRecordId());
    }

    public void addChildRecord(EscherRecord childRecord) {
        if (this._childRecords.size() >= MAX_NUMBER_OF_CHILDREN) {
            throw new IllegalStateException("Cannot add more than " + MAX_NUMBER_OF_CHILDREN + " child records, you can use 'EscherRecord.setMaxNumberOfChildren()' to increase the allow size");
        }
        getChildRecords().add(childRecord);
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.ddf.UnknownEscherRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return UnknownEscherRecord.this.m2248x7fd1dba2();
            }
        }, "data", new Supplier() { // from class: org.apache.poi.ddf.UnknownEscherRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return UnknownEscherRecord.this.getData();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-UnknownEscherRecord, reason: not valid java name */
    public /* synthetic */ Object m2248x7fd1dba2() {
        return super.getGenericProperties();
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Enum getGenericRecordType() {
        return EscherRecordTypes.UNKNOWN;
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.Duplicatable
    public UnknownEscherRecord copy() {
        return new UnknownEscherRecord(this);
    }
}
