package org.apache.poi.hssf.eventusermodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordFactory;

/* loaded from: classes10.dex */
public class HSSFRequest {
    private final Map<Short, List<HSSFListener>> _records = new HashMap(50);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$addListener$0(Short k) {
        return new ArrayList(1);
    }

    public void addListener(HSSFListener lsnr, short sid) {
        List<HSSFListener> list = this._records.computeIfAbsent(Short.valueOf(sid), new Function() { // from class: org.apache.poi.hssf.eventusermodel.HSSFRequest$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return HSSFRequest.lambda$addListener$0((Short) obj);
            }
        });
        list.add(lsnr);
    }

    public void addListenerForAllRecords(HSSFListener lsnr) {
        short[] rectypes = RecordFactory.getAllKnownRecordSIDs();
        for (short rectype : rectypes) {
            addListener(lsnr, rectype);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public short processRecord(Record rec) throws HSSFUserException {
        List<HSSFListener> listeners = this._records.get(Short.valueOf(rec.getSid()));
        short userCode = 0;
        if (listeners != null) {
            for (Object listenObj : listeners) {
                if (listenObj instanceof AbortableHSSFListener) {
                    AbortableHSSFListener listener = (AbortableHSSFListener) listenObj;
                    userCode = listener.abortableProcessRecord(rec);
                    if (userCode != 0) {
                        break;
                    }
                } else {
                    HSSFListener listener2 = (HSSFListener) listenObj;
                    listener2.processRecord(rec);
                }
            }
        }
        return userCode;
    }
}
