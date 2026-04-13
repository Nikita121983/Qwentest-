package org.apache.poi.hssf.eventusermodel;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordFactoryInputStream;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/* loaded from: classes10.dex */
public class HSSFEventFactory {
    public void processWorkbookEvents(HSSFRequest req, POIFSFileSystem fs) throws IOException {
        processWorkbookEvents(req, fs.getRoot());
    }

    public void processWorkbookEvents(HSSFRequest req, DirectoryNode dir) throws IOException {
        String name = null;
        if (dir.hasEntry(InternalWorkbook.WORKBOOK)) {
            name = InternalWorkbook.WORKBOOK;
        } else if (dir.hasEntry(InternalWorkbook.BOOK)) {
            name = InternalWorkbook.BOOK;
        }
        if (name == null) {
            String name2 = InternalWorkbook.WORKBOOK_DIR_ENTRY_NAMES.get(0);
            name = name2;
        }
        InputStream in = dir.createDocumentInputStream(name);
        try {
            processEvents(req, in);
            if (in != null) {
                in.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (in != null) {
                    try {
                        in.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public short abortableProcessWorkbookEvents(HSSFRequest req, POIFSFileSystem fs) throws IOException, HSSFUserException {
        return abortableProcessWorkbookEvents(req, fs.getRoot());
    }

    public short abortableProcessWorkbookEvents(HSSFRequest req, DirectoryNode dir) throws IOException, HSSFUserException {
        InputStream in = dir.createDocumentInputStream("Workbook");
        try {
            short abortableProcessEvents = abortableProcessEvents(req, in);
            if (in != null) {
                in.close();
            }
            return abortableProcessEvents;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (in != null) {
                    try {
                        in.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public void processEvents(HSSFRequest req, InputStream in) {
        try {
            genericProcessEvents(req, in);
        } catch (HSSFUserException e) {
        }
    }

    public short abortableProcessEvents(HSSFRequest req, InputStream in) throws HSSFUserException {
        return genericProcessEvents(req, in);
    }

    private short genericProcessEvents(HSSFRequest req, InputStream in) throws HSSFUserException {
        short userCode = 0;
        RecordFactoryInputStream recordStream = new RecordFactoryInputStream(in, false);
        do {
            Record r = recordStream.nextRecord();
            if (r == null) {
                break;
            }
            userCode = req.processRecord(r);
        } while (userCode == 0);
        return userCode;
    }
}
