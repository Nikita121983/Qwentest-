package org.apache.poi.hssf.extractor;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.CodepageRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.OldFormulaRecord;
import org.apache.poi.hssf.record.OldLabelRecord;
import org.apache.poi.hssf.record.OldSheetRecord;
import org.apache.poi.hssf.record.OldStringRecord;
import org.apache.poi.hssf.record.RKRecord;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentNode;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.util.IOUtils;

/* loaded from: classes10.dex */
public class OldExcelExtractor implements POITextExtractor {
    private static final int FILE_PASS_RECORD_SID = 47;
    private int biffVersion;
    private int fileType;
    private RecordInputStream ris;
    private Closeable toClose;

    public OldExcelExtractor(InputStream input) throws IOException {
        open(input);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public OldExcelExtractor(java.io.File r5) throws java.io.IOException {
        /*
            r4 = this;
            r4.<init>()
            r0 = 0
            org.apache.poi.poifs.filesystem.POIFSFileSystem r1 = new org.apache.poi.poifs.filesystem.POIFSFileSystem     // Catch: java.lang.Throwable -> L17 org.apache.poi.poifs.filesystem.NotOLE2FileException -> L20 org.apache.poi.hssf.OldExcelFormatException -> L22
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L17 org.apache.poi.poifs.filesystem.NotOLE2FileException -> L20 org.apache.poi.hssf.OldExcelFormatException -> L22
            r0 = r1
            r4.open(r0)     // Catch: java.lang.Throwable -> L17 org.apache.poi.poifs.filesystem.NotOLE2FileException -> L20 org.apache.poi.hssf.OldExcelFormatException -> L22
            r4.toClose = r0     // Catch: java.lang.Throwable -> L17 org.apache.poi.poifs.filesystem.NotOLE2FileException -> L20 org.apache.poi.hssf.OldExcelFormatException -> L22
            java.io.Closeable r1 = r4.toClose
            if (r1 != 0) goto L16
            org.apache.poi.util.IOUtils.closeQuietly(r0)
        L16:
            return
        L17:
            r1 = move-exception
            java.io.Closeable r2 = r4.toClose
            if (r2 != 0) goto L1f
            org.apache.poi.util.IOUtils.closeQuietly(r0)
        L1f:
            throw r1
        L20:
            r1 = move-exception
            goto L23
        L22:
            r1 = move-exception
        L23:
            java.io.Closeable r1 = r4.toClose
            if (r1 != 0) goto L2a
            org.apache.poi.util.IOUtils.closeQuietly(r0)
        L2a:
            java.nio.file.Path r1 = r5.toPath()
            r2 = 0
            java.nio.file.OpenOption[] r2 = new java.nio.file.OpenOption[r2]
            java.io.InputStream r1 = java.nio.file.Files.newInputStream(r1, r2)
            r4.open(r1)     // Catch: java.lang.RuntimeException -> L3a java.io.IOException -> L3c
            return
        L3a:
            r2 = move-exception
            goto L3d
        L3c:
            r2 = move-exception
        L3d:
            r1.close()
            java.io.Closeable r3 = r4.toClose
            r3.close()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.extractor.OldExcelExtractor.<init>(java.io.File):void");
    }

    public OldExcelExtractor(POIFSFileSystem fs) throws IOException {
        this.toClose = fs;
        open(fs);
    }

    public OldExcelExtractor(DirectoryNode directory) throws IOException {
        this.toClose = directory.getFileSystem();
        open(directory);
    }

    private void open(InputStream biffStream) throws IOException {
        Closeable closeable;
        BufferedInputStream bis = biffStream instanceof BufferedInputStream ? (BufferedInputStream) biffStream : new BufferedInputStream(biffStream, 8);
        if (FileMagic.valueOf(bis) == FileMagic.OLE2) {
            POIFSFileSystem poifs = new POIFSFileSystem(bis);
            try {
                open(poifs);
                this.toClose = poifs;
                if (closeable == null) {
                    return;
                } else {
                    return;
                }
            } finally {
                if (this.toClose == null) {
                    poifs.close();
                }
            }
        }
        this.ris = new RecordInputStream(bis);
        this.toClose = bis;
        prepare();
    }

    private void open(POIFSFileSystem fs) throws IOException {
        open(fs.getRoot());
    }

    private void open(DirectoryNode directory) throws IOException {
        Entry entry;
        Entry entry2;
        try {
            entry2 = directory.getEntryCaseInsensitive(InternalWorkbook.OLD_WORKBOOK_DIR_ENTRY_NAME);
        } catch (FileNotFoundException | IllegalArgumentException e) {
            Entry entry3 = directory.getEntryCaseInsensitive(InternalWorkbook.WORKBOOK);
            if (!(entry3 instanceof DocumentNode)) {
                throw new IllegalArgumentException("Did not have an Excel 5/95 Book stream: " + entry3);
            }
            entry = (DocumentNode) entry3;
        }
        if (!(entry2 instanceof DocumentNode)) {
            throw new IllegalArgumentException("Did not have an Excel 5/95 Book stream: " + entry2);
        }
        entry = (DocumentNode) entry2;
        this.ris = new RecordInputStream(directory.createDocumentInputStream(entry));
        prepare();
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Use:");
            System.err.println("   OldExcelExtractor <filename>");
            System.exit(1);
        }
        OldExcelExtractor extractor = new OldExcelExtractor(new File(args[0]));
        try {
            System.out.println(extractor.getText());
            extractor.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    extractor.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    private void prepare() {
        if (!this.ris.hasNextRecord()) {
            throw new IllegalArgumentException("File contains no records!");
        }
        this.ris.nextRecord();
        int bofSid = this.ris.getSid();
        switch (bofSid) {
            case 9:
                this.biffVersion = 2;
                break;
            case 521:
                this.biffVersion = 3;
                break;
            case 1033:
                this.biffVersion = 4;
                break;
            case 2057:
                this.biffVersion = 5;
                break;
            default:
                throw new IllegalArgumentException("File does not begin with a BOF, found sid of " + bofSid);
        }
        BOFRecord bof = new BOFRecord(this.ris);
        this.fileType = bof.getType();
    }

    public int getBiffVersion() {
        return this.biffVersion;
    }

    public int getFileType() {
        return this.fileType;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public String getText() {
        StringBuilder text = new StringBuilder();
        CodepageRecord codepage = null;
        while (this.ris.hasNextRecord()) {
            int sid = this.ris.getNextSid();
            this.ris.nextRecord();
            switch (sid) {
                case 4:
                case 516:
                    OldLabelRecord lr = new OldLabelRecord(this.ris);
                    lr.setCodePage(codepage);
                    text.append(lr.getValue());
                    text.append('\n');
                    break;
                case 6:
                case 518:
                case 1030:
                    if (this.biffVersion == 5) {
                        FormulaRecord fr = new FormulaRecord(this.ris);
                        if (fr.getCachedResultTypeEnum() != CellType.NUMERIC) {
                            break;
                        } else {
                            handleNumericCell(text, fr.getValue());
                            break;
                        }
                    } else {
                        OldFormulaRecord fr2 = new OldFormulaRecord(this.ris);
                        if (fr2.getCachedResultTypeEnum() != CellType.NUMERIC) {
                            break;
                        } else {
                            handleNumericCell(text, fr2.getValue());
                            break;
                        }
                    }
                case 7:
                case 519:
                    OldStringRecord sr = new OldStringRecord(this.ris);
                    sr.setCodePage(codepage);
                    text.append(sr.getString());
                    text.append('\n');
                    break;
                case 47:
                    throw new EncryptedDocumentException("Encryption not supported for Old Excel files");
                case 66:
                    CodepageRecord codepage2 = new CodepageRecord(this.ris);
                    codepage = codepage2;
                    break;
                case 133:
                    OldSheetRecord shr = new OldSheetRecord(this.ris);
                    shr.setCodePage(codepage);
                    text.append("Sheet: ");
                    text.append(shr.getSheetname());
                    text.append('\n');
                    break;
                case 515:
                    NumberRecord nr = new NumberRecord(this.ris);
                    handleNumericCell(text, nr.getValue());
                    break;
                case 638:
                    RKRecord rr = new RKRecord(this.ris);
                    handleNumericCell(text, rr.getRKNumber());
                    break;
                default:
                    this.ris.readFully(IOUtils.safelyAllocate(this.ris.remaining(), HSSFWorkbook.getMaxRecordLength()));
                    break;
            }
        }
        this.ris = null;
        return text.toString();
    }

    protected void handleNumericCell(StringBuilder text, double value) {
        text.append(value);
        text.append('\n');
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public POITextExtractor getMetadataTextExtractor() {
        return new POITextExtractor() { // from class: org.apache.poi.hssf.extractor.OldExcelExtractor.1
            @Override // org.apache.poi.extractor.POITextExtractor
            public String getText() {
                return "";
            }

            @Override // org.apache.poi.extractor.POITextExtractor
            public POITextExtractor getMetadataTextExtractor() {
                throw new IllegalStateException("You already have the Metadata Text Extractor, not recursing!");
            }

            @Override // org.apache.poi.extractor.POITextExtractor
            public void setCloseFilesystem(boolean doCloseFilesystem) {
            }

            @Override // org.apache.poi.extractor.POITextExtractor
            public boolean isCloseFilesystem() {
                return OldExcelExtractor.this.toClose != null;
            }

            @Override // org.apache.poi.extractor.POITextExtractor
            public Closeable getFilesystem() {
                return OldExcelExtractor.this.toClose;
            }

            @Override // org.apache.poi.extractor.POITextExtractor
            public Object getDocument() {
                return OldExcelExtractor.this.ris;
            }
        };
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public void setCloseFilesystem(boolean doCloseFilesystem) {
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public boolean isCloseFilesystem() {
        return this.toClose != null;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public Closeable getFilesystem() {
        return this.toClose;
    }

    @Override // org.apache.poi.extractor.POITextExtractor
    public Object getDocument() {
        return this.ris;
    }
}
