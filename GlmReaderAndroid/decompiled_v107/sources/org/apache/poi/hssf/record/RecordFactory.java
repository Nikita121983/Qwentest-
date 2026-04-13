package org.apache.poi.hssf.record;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.ToIntFunction;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
public final class RecordFactory {
    private static final int DEFAULT_MAX_NUMBER_OF_RECORDS = 5000000;
    private static int MAX_NUMBER_OF_RECORDS = DEFAULT_MAX_NUMBER_OF_RECORDS;
    private static final int NUM_RECORDS = 512;

    public static void setMaxNumberOfRecords(int maxNumberOfRecords) {
        MAX_NUMBER_OF_RECORDS = maxNumberOfRecords;
    }

    public static int getMaxNumberOfRecords() {
        return MAX_NUMBER_OF_RECORDS;
    }

    private RecordFactory() {
    }

    public static Class<? extends Record> getRecordClass(int sid) {
        return HSSFRecordTypes.forSID(sid).clazz;
    }

    public static Record[] createRecord(RecordInputStream in) {
        Record record = createSingleRecord(in);
        if (record instanceof DBCellRecord) {
            return new Record[]{null};
        }
        if (record instanceof RKRecord) {
            return new Record[]{convertToNumberRecord((RKRecord) record)};
        }
        if (record instanceof MulRKRecord) {
            return convertRKRecords((MulRKRecord) record);
        }
        return new Record[]{record};
    }

    public static Record createSingleRecord(RecordInputStream in) {
        HSSFRecordTypes rec = HSSFRecordTypes.forSID(in.getSid());
        if (!rec.isParseable()) {
            rec = HSSFRecordTypes.UNKNOWN;
        }
        return rec.recordConstructor.apply(in);
    }

    public static NumberRecord convertToNumberRecord(RKRecord rk) {
        NumberRecord num = new NumberRecord();
        num.setColumn(rk.getColumn());
        num.setRow(rk.getRow());
        num.setXFIndex(rk.getXFIndex());
        num.setValue(rk.getRKNumber());
        return num;
    }

    public static NumberRecord[] convertRKRecords(MulRKRecord mrk) {
        int numColumns = mrk.getNumColumns();
        if (numColumns < 0) {
            throw new RecordFormatException("Cannot create RKRecords with negative number of columns: " + numColumns);
        }
        NumberRecord[] mulRecs = new NumberRecord[numColumns];
        for (int k = 0; k < numColumns; k++) {
            NumberRecord nr = new NumberRecord();
            nr.setColumn((short) (mrk.getFirstColumn() + k));
            nr.setRow(mrk.getRow());
            nr.setXFIndex(mrk.getXFAt(k));
            nr.setValue(mrk.getRKNumberAt(k));
            mulRecs[k] = nr;
        }
        return mulRecs;
    }

    public static BlankRecord[] convertBlankRecords(MulBlankRecord mbk) {
        BlankRecord[] mulRecs = new BlankRecord[mbk.getNumColumns()];
        for (int k = 0; k < mbk.getNumColumns(); k++) {
            BlankRecord br = new BlankRecord();
            br.setColumn((short) (mbk.getFirstColumn() + k));
            br.setRow(mbk.getRow());
            br.setXFIndex(mbk.getXFAt(k));
            mulRecs[k] = br;
        }
        return mulRecs;
    }

    public static short[] getAllKnownRecordSIDs() {
        int[] intSid = Arrays.stream(HSSFRecordTypes.values()).mapToInt(new ToIntFunction() { // from class: org.apache.poi.hssf.record.RecordFactory$$ExternalSyntheticLambda0
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((HSSFRecordTypes) obj).getSid();
            }
        }).toArray();
        short[] shortSid = new short[intSid.length];
        for (int i = 0; i < intSid.length; i++) {
            shortSid[i] = (short) intSid[i];
        }
        return shortSid;
    }

    public static List<Record> createRecords(InputStream in) throws RecordFormatException {
        List<Record> records = new ArrayList<>(512);
        RecordFactoryInputStream recStream = new RecordFactoryInputStream(in, true);
        while (true) {
            Record record = recStream.nextRecord();
            if (record != null) {
                records.add(record);
                IOUtils.safelyAllocateCheck(records.size(), MAX_NUMBER_OF_RECORDS);
            } else {
                return records;
            }
        }
    }
}
