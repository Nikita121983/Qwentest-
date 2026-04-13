package org.apache.poi.hssf.record;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.apache.poi.ddf.DefaultEscherRecordFactory;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherContainerRecord$$ExternalSyntheticLambda1;
import org.apache.poi.ddf.EscherContainerRecord$$ExternalSyntheticLambda2;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherRecordFactory;
import org.apache.poi.ddf.NullEscherSerializationListener;
import org.apache.poi.hssf.util.LazilyConcatenatedByteArray;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.Removal;

/* loaded from: classes10.dex */
public abstract class AbstractEscherHolderRecord extends Record {
    private static boolean DESERIALIZE;
    private final List<EscherRecord> escherRecords = new ArrayList();
    private final LazilyConcatenatedByteArray rawDataContainer = new LazilyConcatenatedByteArray();

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public abstract AbstractEscherHolderRecord copy();

    protected abstract String getRecordName();

    @Override // org.apache.poi.hssf.record.Record
    public abstract short getSid();

    static {
        try {
            DESERIALIZE = System.getProperty("poi.deserialize.escher") != null;
        } catch (SecurityException e) {
            DESERIALIZE = false;
        }
    }

    public AbstractEscherHolderRecord() {
    }

    public AbstractEscherHolderRecord(AbstractEscherHolderRecord other) {
        Stream<R> map = other.escherRecords.stream().map(new EscherContainerRecord$$ExternalSyntheticLambda1());
        List<EscherRecord> list = this.escherRecords;
        list.getClass();
        map.forEach(new EscherContainerRecord$$ExternalSyntheticLambda2(list));
        this.rawDataContainer.concatenate(other.rawDataContainer);
    }

    public AbstractEscherHolderRecord(RecordInputStream in) {
        if (!DESERIALIZE) {
            this.rawDataContainer.concatenate(in.readRemainder());
        } else {
            byte[] data = in.readAllContinuedRemainder();
            convertToEscherRecords(0, data.length, data);
        }
    }

    @Removal(version = "5.3")
    @Deprecated
    protected void convertRawBytesToEscherRecords() {
        if (!DESERIALIZE) {
            decode();
        }
    }

    private void convertToEscherRecords(int offset, int size, byte[] data) {
        this.escherRecords.clear();
        EscherRecordFactory recordFactory = new DefaultEscherRecordFactory();
        int pos = offset;
        while (pos < offset + size) {
            EscherRecord r = recordFactory.createRecord(data, pos);
            int bytesRead = r.fillFields(data, pos, recordFactory);
            this.escherRecords.add(r);
            pos += bytesRead;
        }
    }

    @Override // org.apache.poi.hssf.record.RecordBase
    public int serialize(int offset, byte[] data) {
        byte[] rawData = getRawData();
        LittleEndian.putShort(data, offset, getSid());
        int offset2 = offset + 2;
        LittleEndian.putShort(data, offset2, (short) (getRecordSize() - 4));
        int offset3 = offset2 + 2;
        if (this.escherRecords.isEmpty() && rawData != null) {
            System.arraycopy(rawData, 0, data, offset3, rawData.length);
            return rawData.length + 4;
        }
        NullEscherSerializationListener listener = new NullEscherSerializationListener();
        for (EscherRecord r : this.escherRecords) {
            offset3 += r.serialize(offset3, data, listener);
        }
        return getRecordSize();
    }

    @Override // org.apache.poi.hssf.record.RecordBase
    public int getRecordSize() {
        byte[] rawData = getRawData();
        if (this.escherRecords.isEmpty() && rawData != null) {
            return rawData.length;
        }
        int size = 0;
        for (EscherRecord r : this.escherRecords) {
            size += r.getRecordSize();
        }
        return size;
    }

    public void addEscherRecord(int index, EscherRecord element) {
        this.escherRecords.add(index, element);
    }

    public boolean addEscherRecord(EscherRecord element) {
        return this.escherRecords.add(element);
    }

    public List<EscherRecord> getEscherRecords() {
        return this.escherRecords;
    }

    public void clearEscherRecords() {
        this.escherRecords.clear();
    }

    public EscherContainerRecord getEscherContainer() {
        for (EscherRecord er : this.escherRecords) {
            if (er instanceof EscherContainerRecord) {
                return (EscherContainerRecord) er;
            }
        }
        return null;
    }

    public EscherRecord findFirstWithId(short id) {
        return findFirstWithId(id, getEscherRecords());
    }

    private EscherRecord findFirstWithId(short id, List<EscherRecord> records) {
        EscherRecord found;
        for (EscherRecord r : records) {
            if (r.getRecordId() == id) {
                return r;
            }
        }
        for (EscherRecord r2 : records) {
            if (r2.isContainerRecord() && (found = findFirstWithId(id, r2.getChildRecords())) != null) {
                return found;
            }
        }
        return null;
    }

    public EscherRecord getEscherRecord(int index) {
        return this.escherRecords.get(index);
    }

    public void join(AbstractEscherHolderRecord record) {
        this.rawDataContainer.concatenate(record.getRawData());
    }

    public void processContinueRecord(byte[] record) {
        this.rawDataContainer.concatenate(record);
    }

    public byte[] getRawData() {
        return this.rawDataContainer.toArray();
    }

    public void setRawData(byte[] rawData) {
        this.rawDataContainer.clear();
        this.rawDataContainer.concatenate(rawData);
    }

    public void decode() {
        if (this.escherRecords.isEmpty()) {
            byte[] rawData = getRawData();
            convertToEscherRecords(0, rawData.length, rawData);
        }
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public List<EscherRecord> getGenericChildren() {
        return this.escherRecords;
    }
}
