package org.apache.poi.hssf.record.common;

import org.apache.poi.ss.util.CellRangeAddress;

/* loaded from: classes10.dex */
public interface FutureRecord {
    CellRangeAddress getAssociatedRange();

    FtrHeader getFutureHeader();

    short getFutureRecordType();
}
