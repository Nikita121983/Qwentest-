package org.apache.poi.ddf;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndian;

/* loaded from: classes10.dex */
public final class EscherContainerRecord extends EscherRecord implements Iterable<EscherRecord> {
    private static final int MAX_NESTED_CHILD_NODES = 1000;
    private final List<EscherRecord> _childRecords;
    private int _remainingLength;
    public static final short DGG_CONTAINER = EscherRecordTypes.DGG_CONTAINER.typeID;
    public static final short BSTORE_CONTAINER = EscherRecordTypes.BSTORE_CONTAINER.typeID;
    public static final short DG_CONTAINER = EscherRecordTypes.DG_CONTAINER.typeID;
    public static final short SPGR_CONTAINER = EscherRecordTypes.SPGR_CONTAINER.typeID;
    public static final short SP_CONTAINER = EscherRecordTypes.SP_CONTAINER.typeID;
    public static final short SOLVER_CONTAINER = EscherRecordTypes.SOLVER_CONTAINER.typeID;
    private static final Logger LOGGER = PoiLogManager.getLogger((Class<?>) EscherContainerRecord.class);

    public EscherContainerRecord() {
        this._childRecords = new ArrayList();
    }

    public EscherContainerRecord(EscherContainerRecord other) {
        super(other);
        this._childRecords = new ArrayList();
        this._remainingLength = other._remainingLength;
        Stream<R> map = other._childRecords.stream().map(new EscherContainerRecord$$ExternalSyntheticLambda1());
        List<EscherRecord> list = this._childRecords;
        list.getClass();
        map.forEach(new EscherContainerRecord$$ExternalSyntheticLambda2(list));
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int fillFields(byte[] data, int pOffset, EscherRecordFactory recordFactory) {
        return fillFields(data, pOffset, recordFactory, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ddf.EscherRecord
    public int fillFields(byte[] data, int pOffset, EscherRecordFactory recordFactory, int nesting) {
        int childBytesWritten;
        if (nesting > 1000) {
            throw new IllegalStateException("Had more than the limit of 1000 nested child notes");
        }
        int bytesRemaining = readHeader(data, pOffset);
        int bytesWritten = 8;
        int offset = pOffset + 8;
        while (bytesRemaining > 0 && offset < data.length) {
            EscherRecord child = recordFactory.createRecord(data, offset);
            if (child instanceof EscherContainerRecord) {
                childBytesWritten = ((EscherContainerRecord) child).fillFields(data, offset, recordFactory, nesting + 1);
            } else if (child instanceof UnknownEscherRecord) {
                childBytesWritten = ((UnknownEscherRecord) child).fillFields(data, offset, recordFactory, nesting + 1);
            } else {
                int childBytesWritten2 = nesting + 1;
                childBytesWritten = child.fillFields(data, offset, recordFactory, childBytesWritten2);
            }
            bytesWritten += childBytesWritten;
            offset += childBytesWritten;
            bytesRemaining -= childBytesWritten;
            addChildRecord(child);
            if (offset >= data.length && bytesRemaining > 0) {
                this._remainingLength = bytesRemaining;
                LOGGER.atWarn().log("Not enough Escher data: {} bytes remaining but no space left", Unbox.box(bytesRemaining));
            }
        }
        return bytesWritten;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, getRecordId(), this);
        LittleEndian.putShort(data, offset, getOptions());
        LittleEndian.putShort(data, offset + 2, getRecordId());
        int remainingBytes = 0;
        Iterator<EscherRecord> it = iterator();
        while (it.hasNext()) {
            EscherRecord r = it.next();
            remainingBytes += r.getRecordSize();
        }
        LittleEndian.putInt(data, offset + 4, remainingBytes + this._remainingLength);
        int pos = offset + 8;
        Iterator<EscherRecord> it2 = iterator();
        while (it2.hasNext()) {
            EscherRecord r2 = it2.next();
            pos += r2.serialize(pos, data, listener);
        }
        listener.afterRecordSerialize(pos, getRecordId(), pos - offset, this);
        return pos - offset;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public int getRecordSize() {
        int childRecordsSize = 0;
        Iterator<EscherRecord> it = iterator();
        while (it.hasNext()) {
            EscherRecord r = it.next();
            childRecordsSize += r.getRecordSize();
        }
        return childRecordsSize + 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$hasChildOfType$0(short recordId, EscherRecord r) {
        return r.getRecordId() == recordId;
    }

    public boolean hasChildOfType(final short recordId) {
        return this._childRecords.stream().anyMatch(new Predicate() { // from class: org.apache.poi.ddf.EscherContainerRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return EscherContainerRecord.lambda$hasChildOfType$0(recordId, (EscherRecord) obj);
            }
        });
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public EscherRecord getChild(int index) {
        return this._childRecords.get(index);
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public List<EscherRecord> getChildRecords() {
        return new ArrayList(this._childRecords);
    }

    public int getChildCount() {
        return this._childRecords.size();
    }

    @Override // java.lang.Iterable
    public Iterator<EscherRecord> iterator() {
        return Collections.unmodifiableList(this._childRecords).iterator();
    }

    @Override // java.lang.Iterable
    public Spliterator<EscherRecord> spliterator() {
        return this._childRecords.spliterator();
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public void setChildRecords(List<EscherRecord> childRecords) {
        if (childRecords == this._childRecords) {
            throw new IllegalStateException("Child records private data member has escaped");
        }
        if (childRecords.size() > MAX_NUMBER_OF_CHILDREN) {
            throw new IllegalStateException("Cannot add more than " + MAX_NUMBER_OF_CHILDREN + " child records, you can use 'EscherRecord.setMaxNumberOfChildren()' to increase the allow size");
        }
        this._childRecords.clear();
        this._childRecords.addAll(childRecords);
    }

    public boolean removeChildRecord(EscherRecord toBeRemoved) {
        return this._childRecords.remove(toBeRemoved);
    }

    public List<EscherContainerRecord> getChildContainers() {
        List<EscherContainerRecord> containers = new ArrayList<>();
        Iterator<EscherRecord> it = iterator();
        while (it.hasNext()) {
            EscherRecord r = it.next();
            if (r instanceof EscherContainerRecord) {
                containers.add((EscherContainerRecord) r);
            }
        }
        return containers;
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public String getRecordName() {
        short id = getRecordId();
        EscherRecordTypes t = EscherRecordTypes.forTypeID(id);
        return t != EscherRecordTypes.UNKNOWN ? t.recordName : "Container 0x" + HexDump.toHex(id);
    }

    @Override // org.apache.poi.ddf.EscherRecord
    public void display(PrintWriter w, int indent) {
        super.display(w, indent);
        Iterator<EscherRecord> it = iterator();
        while (it.hasNext()) {
            EscherRecord escherRecord = it.next();
            escherRecord.display(w, indent + 1);
        }
    }

    public void addChildRecord(EscherRecord record) {
        if (this._childRecords.size() >= MAX_NUMBER_OF_CHILDREN) {
            throw new IllegalStateException("Cannot add more than " + MAX_NUMBER_OF_CHILDREN + " child records, you can use 'EscherRecord.setMaxNumberOfChildren()' to increase the allow size");
        }
        this._childRecords.add(record);
    }

    public void addChildBefore(EscherRecord record, int insertBeforeRecordId) {
        int idx = 0;
        Iterator<EscherRecord> it = iterator();
        while (it.hasNext()) {
            EscherRecord rec = it.next();
            if (rec.getRecordId() == ((short) insertBeforeRecordId)) {
                break;
            } else {
                idx++;
            }
        }
        this._childRecords.add(idx, record);
    }

    public <T extends EscherRecord> T getChildById(short recordId) {
        Iterator<EscherRecord> it = iterator();
        while (it.hasNext()) {
            T t = (T) it.next();
            if (t.getRecordId() == recordId) {
                return t;
            }
        }
        return null;
    }

    public void getRecordsById(short recordId, List<EscherRecord> out) {
        Iterator<EscherRecord> it = iterator();
        while (it.hasNext()) {
            EscherRecord r = it.next();
            if (r instanceof EscherContainerRecord) {
                EscherContainerRecord c = (EscherContainerRecord) r;
                c.getRecordsById(recordId, out);
            } else if (r.getRecordId() == recordId) {
                out.add(r);
            }
        }
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Supplier() { // from class: org.apache.poi.ddf.EscherContainerRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherContainerRecord.this.m2239x85f7d638();
            }
        }, "isContainer", new Supplier() { // from class: org.apache.poi.ddf.EscherContainerRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(EscherContainerRecord.this.isContainerRecord());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-ddf-EscherContainerRecord, reason: not valid java name */
    public /* synthetic */ Object m2239x85f7d638() {
        return super.getGenericProperties();
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Enum getGenericRecordType() {
        return EscherRecordTypes.forTypeID(getRecordId());
    }

    @Override // org.apache.poi.ddf.EscherRecord, org.apache.poi.common.Duplicatable
    public EscherContainerRecord copy() {
        return new EscherContainerRecord(this);
    }
}
