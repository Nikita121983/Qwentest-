package org.apache.poi.hssf.eventusermodel;

import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingCellDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingRowDummyRecord;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.CellValueRecordInterface;
import org.apache.poi.hssf.record.MulBlankRecord;
import org.apache.poi.hssf.record.MulRKRecord;
import org.apache.poi.hssf.record.NoteRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordFactory;
import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.hssf.record.StringRecord;

/* loaded from: classes10.dex */
public final class MissingRecordAwareHSSFListener implements HSSFListener {
    private HSSFListener childListener;
    private int lastCellColumn;
    private int lastCellRow;
    private int lastRowRow;

    public MissingRecordAwareHSSFListener(HSSFListener listener) {
        resetCounts();
        this.childListener = listener;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.poi.hssf.eventusermodel.HSSFListener
    public void processRecord(Record record) {
        int i;
        int i2;
        NumberRecord[] numberRecordArr = null;
        numberRecordArr = null;
        numberRecordArr = null;
        numberRecordArr = null;
        numberRecordArr = null;
        numberRecordArr = null;
        if (record instanceof CellValueRecordInterface) {
            CellValueRecordInterface cellValueRecordInterface = (CellValueRecordInterface) record;
            i = cellValueRecordInterface.getRow();
            i2 = cellValueRecordInterface.getColumn();
        } else {
            if (record instanceof StringRecord) {
                this.childListener.processRecord(record);
                return;
            }
            i = -1;
            i2 = -1;
            switch (record.getSid()) {
                case 28:
                    NoteRecord noteRecord = (NoteRecord) record;
                    i = noteRecord.getRow();
                    i2 = noteRecord.getColumn();
                    break;
                case 189:
                    numberRecordArr = RecordFactory.convertRKRecords((MulRKRecord) record);
                    break;
                case 190:
                    numberRecordArr = RecordFactory.convertBlankRecords((MulBlankRecord) record);
                    break;
                case 520:
                    RowRecord rowRecord = (RowRecord) record;
                    if (this.lastRowRow + 1 < rowRecord.getRowNumber()) {
                        int i3 = this.lastRowRow;
                        while (true) {
                            i3++;
                            if (i3 < rowRecord.getRowNumber()) {
                                this.childListener.processRecord(new MissingRowDummyRecord(i3));
                            }
                        }
                    }
                    this.lastRowRow = rowRecord.getRowNumber();
                    this.lastCellColumn = -1;
                    break;
                case 1212:
                    this.childListener.processRecord(record);
                    return;
                case 2057:
                    BOFRecord bOFRecord = (BOFRecord) record;
                    if (bOFRecord.getType() == 5 || bOFRecord.getType() == 16) {
                        resetCounts();
                        break;
                    }
                    break;
            }
        }
        if (numberRecordArr != null && numberRecordArr.length > 0) {
            i = numberRecordArr[0].getRow();
            i2 = numberRecordArr[0].getColumn();
        }
        if (i != this.lastCellRow && i > 0) {
            if (this.lastCellRow == -1) {
                this.lastCellRow = 0;
            }
            for (int i4 = this.lastCellRow; i4 < i; i4++) {
                int i5 = -1;
                if (i4 == this.lastCellRow) {
                    i5 = this.lastCellColumn;
                }
                this.childListener.processRecord(new LastCellOfRowDummyRecord(i4, i5));
            }
        }
        if (this.lastCellRow != -1 && this.lastCellColumn != -1 && i == -1) {
            this.childListener.processRecord(new LastCellOfRowDummyRecord(this.lastCellRow, this.lastCellColumn));
            this.lastCellRow = -1;
            this.lastCellColumn = -1;
        }
        if (i != this.lastCellRow) {
            this.lastCellColumn = -1;
        }
        if (this.lastCellColumn != i2 - 1) {
            for (int i6 = this.lastCellColumn + 1; i6 < i2; i6++) {
                this.childListener.processRecord(new MissingCellDummyRecord(i, i6));
            }
        }
        if (numberRecordArr != null && numberRecordArr.length > 0) {
            i2 = numberRecordArr[numberRecordArr.length - 1].getColumn();
        }
        if (i2 != -1) {
            this.lastCellColumn = i2;
            this.lastCellRow = i;
        }
        if (numberRecordArr != null && numberRecordArr.length > 0) {
            for (NumberRecord numberRecord : numberRecordArr) {
                this.childListener.processRecord(numberRecord);
            }
            return;
        }
        this.childListener.processRecord(record);
    }

    private void resetCounts() {
        this.lastRowRow = -1;
        this.lastCellRow = -1;
        this.lastCellColumn = -1;
    }
}
