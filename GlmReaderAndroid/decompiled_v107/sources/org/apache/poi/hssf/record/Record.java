package org.apache.poi.hssf.record;

import java.io.IOException;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordJsonWriter;

/* loaded from: classes10.dex */
public abstract class Record extends RecordBase implements Duplicatable, GenericRecord {
    @Override // org.apache.poi.common.Duplicatable
    public abstract Record copy();

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public abstract HSSFRecordTypes getGenericRecordType();

    public abstract short getSid();

    /* JADX INFO: Access modifiers changed from: protected */
    public Record() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Record(Record other) {
    }

    public final byte[] serialize() {
        byte[] retval = new byte[getRecordSize()];
        serialize(0, retval);
        return retval;
    }

    public final String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    public Record cloneViaReserialise() {
        byte[] b = serialize();
        try {
            RecordInputStream rinp = new RecordInputStream(UnsynchronizedByteArrayInputStream.builder().setByteArray(b).get());
            rinp.nextRecord();
            Record[] r = RecordFactory.createRecord(rinp);
            if (r.length != 1) {
                throw new IllegalStateException("Re-serialised a record to clone it, but got " + r.length + " records back!");
            }
            return r[0];
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
