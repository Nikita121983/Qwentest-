package org.apache.poi.ddf;

import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.LittleEndian;

/* loaded from: classes10.dex */
public class DefaultEscherRecordFactory implements EscherRecordFactory {
    private static final BitField IS_CONTAINER = BitFieldFactory.getInstance(15);

    @Override // org.apache.poi.ddf.EscherRecordFactory
    public EscherRecord createRecord(byte[] data, int offset) {
        short options = LittleEndian.getShort(data, offset);
        short recordId = LittleEndian.getShort(data, offset + 2);
        EscherRecord escherRecord = getConstructor(options, recordId).get();
        escherRecord.setRecordId(recordId);
        escherRecord.setOptions(options);
        return escherRecord;
    }

    protected Supplier<? extends EscherRecord> getConstructor(short options, short recordId) {
        EscherRecordTypes recordTypes = EscherRecordTypes.forTypeID(recordId);
        if (recordTypes == EscherRecordTypes.UNKNOWN && IS_CONTAINER.isAllSet(options)) {
            return new DefaultEscherRecordFactory$$ExternalSyntheticLambda0();
        }
        if (recordTypes.constructor != null && recordTypes != EscherRecordTypes.UNKNOWN) {
            return recordTypes.constructor;
        }
        if (EscherBlipRecord.RECORD_ID_START <= recordId && recordId <= EscherBlipRecord.RECORD_ID_END) {
            return new Supplier() { // from class: org.apache.poi.ddf.DefaultEscherRecordFactory$$ExternalSyntheticLambda1
                @Override // java.util.function.Supplier
                public final Object get() {
                    return new EscherBlipRecord();
                }
            };
        }
        return new DefaultEscherRecordFactory$$ExternalSyntheticLambda2();
    }
}
