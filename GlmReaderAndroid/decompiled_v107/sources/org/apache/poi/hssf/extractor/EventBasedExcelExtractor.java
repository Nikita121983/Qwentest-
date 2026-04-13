package org.apache.poi.hssf.extractor;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIDocument;
import org.apache.poi.extractor.POIOLE2TextExtractor;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.eventusermodel.FormatTrackingHSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.BoundSheetRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.LabelRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NoteRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SSTRecord;
import org.apache.poi.hssf.record.StringRecord;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/* loaded from: classes10.dex */
public class EventBasedExcelExtractor implements POIOLE2TextExtractor, org.apache.poi.ss.extractor.ExcelExtractor {
    private final DirectoryNode _dir;
    boolean _formulasNotResults;
    boolean _includeSheetNames;
    private boolean doCloseFilesystem;
    private final POIFSFileSystem poifs;

    public EventBasedExcelExtractor(DirectoryNode dir) {
        this.doCloseFilesystem = true;
        this._includeSheetNames = true;
        this.poifs = null;
        this._dir = dir;
    }

    public EventBasedExcelExtractor(POIFSFileSystem fs) {
        this.doCloseFilesystem = true;
        this._includeSheetNames = true;
        this.poifs = fs;
        this._dir = fs.getRoot();
    }

    @Override // org.apache.poi.extractor.POIOLE2TextExtractor
    public DocumentSummaryInformation getDocSummaryInformation() {
        throw new IllegalStateException("Metadata extraction not supported in streaming mode, please use ExcelExtractor");
    }

    @Override // org.apache.poi.extractor.POIOLE2TextExtractor
    public SummaryInformation getSummaryInformation() {
        throw new IllegalStateException("Metadata extraction not supported in streaming mode, please use ExcelExtractor");
    }

    @Override // org.apache.poi.ss.extractor.ExcelExtractor
    public void setIncludeCellComments(boolean includeComments) {
        throw new IllegalStateException("Comment extraction not supported in streaming mode, please use ExcelExtractor");
    }

    @Override // org.apache.poi.ss.extractor.ExcelExtractor
    public void setIncludeHeadersFooters(boolean includeHeadersFooters) {
        throw new IllegalStateException("Header/Footer extraction not supported in streaming mode, please use ExcelExtractor");
    }

    @Override // org.apache.poi.ss.extractor.ExcelExtractor
    public void setIncludeSheetNames(boolean includeSheetNames) {
        this._includeSheetNames = includeSheetNames;
    }

    @Override // org.apache.poi.ss.extractor.ExcelExtractor
    public void setFormulasNotResults(boolean formulasNotResults) {
        this._formulasNotResults = formulasNotResults;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public String getText() {
        try {
            TextListener tl = triggerExtraction();
            String text = tl._text.toString();
            if (!text.endsWith(StringUtils.LF)) {
                return text + StringUtils.LF;
            }
            return text;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private TextListener triggerExtraction() throws IOException {
        TextListener tl = new TextListener();
        FormatTrackingHSSFListener ft = new FormatTrackingHSSFListener(tl);
        tl._ft = ft;
        HSSFEventFactory factory = new HSSFEventFactory();
        HSSFRequest request = new HSSFRequest();
        request.addListenerForAllRecords(ft);
        factory.processWorkbookEvents(request, this._dir);
        return tl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public class TextListener implements HSSFListener {
        FormatTrackingHSSFListener _ft;
        private boolean outputNextStringValue;
        private int rowNum;
        private SSTRecord sstRecord;
        final StringBuilder _text = new StringBuilder();
        private int sheetNum = -1;
        private int nextRow = -1;
        private final List<String> sheetNames = new ArrayList();

        public TextListener() {
        }

        @Override // org.apache.poi.hssf.eventusermodel.HSSFListener
        public void processRecord(Record record) {
            String thisText = null;
            int thisRow = -1;
            switch (record.getSid()) {
                case 6:
                    FormulaRecord frec = (FormulaRecord) record;
                    thisRow = frec.getRow();
                    if (EventBasedExcelExtractor.this._formulasNotResults) {
                        thisText = HSSFFormulaParser.toFormulaString(null, frec.getParsedExpression());
                        break;
                    } else if (frec.hasCachedResultString()) {
                        this.outputNextStringValue = true;
                        this.nextRow = frec.getRow();
                        break;
                    } else {
                        thisText = this._ft.formatNumberDateCell(frec);
                        break;
                    }
                case 28:
                    NoteRecord nrec = (NoteRecord) record;
                    thisRow = nrec.getRow();
                    break;
                case 133:
                    BoundSheetRecord sr = (BoundSheetRecord) record;
                    this.sheetNames.add(sr.getSheetname());
                    break;
                case 252:
                    this.sstRecord = (SSTRecord) record;
                    break;
                case 253:
                    LabelSSTRecord lsrec = (LabelSSTRecord) record;
                    thisRow = lsrec.getRow();
                    if (this.sstRecord == null) {
                        throw new IllegalStateException("No SST record found");
                    }
                    thisText = this.sstRecord.getString(lsrec.getSSTIndex()).toString();
                    break;
                case 515:
                    NumberRecord numrec = (NumberRecord) record;
                    thisRow = numrec.getRow();
                    thisText = this._ft.formatNumberDateCell(numrec);
                    break;
                case 516:
                    LabelRecord lrec = (LabelRecord) record;
                    thisRow = lrec.getRow();
                    thisText = lrec.getValue();
                    break;
                case 519:
                    if (this.outputNextStringValue) {
                        StringRecord srec = (StringRecord) record;
                        thisText = srec.getString();
                        thisRow = this.nextRow;
                        this.outputNextStringValue = false;
                        break;
                    }
                    break;
                case 2057:
                    BOFRecord bof = (BOFRecord) record;
                    if (bof.getType() == 16) {
                        this.sheetNum++;
                        this.rowNum = -1;
                        if (EventBasedExcelExtractor.this._includeSheetNames) {
                            if (this._text.length() > 0) {
                                this._text.append(StringUtils.LF);
                            }
                            this._text.append(this.sheetNames.get(this.sheetNum));
                            break;
                        }
                    }
                    break;
            }
            if (thisText != null) {
                if (thisRow != this.rowNum) {
                    this.rowNum = thisRow;
                    if (this._text.length() > 0) {
                        this._text.append(StringUtils.LF);
                    }
                } else {
                    this._text.append("\t");
                }
                this._text.append(thisText);
            }
        }
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public void setCloseFilesystem(boolean doCloseFilesystem) {
        this.doCloseFilesystem = doCloseFilesystem;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public boolean isCloseFilesystem() {
        return this.doCloseFilesystem;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public Closeable getFilesystem() {
        return this.poifs;
    }

    @Override // org.apache.poi.extractor.POIOLE2TextExtractor, org.apache.poi.extractor.POITextExtractor
    public POIDocument getDocument() {
        return null;
    }

    @Override // org.apache.poi.extractor.POIOLE2TextExtractor
    public DirectoryEntry getRoot() {
        return this._dir;
    }

    @Override // org.apache.poi.extractor.POITextExtractor, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        DirectoryEntry root = getRoot();
        if (root instanceof DirectoryNode) {
            Closeable fs = ((DirectoryNode) root).getFileSystem();
            if (isCloseFilesystem() && fs != null) {
                fs.close();
            }
        }
    }
}
