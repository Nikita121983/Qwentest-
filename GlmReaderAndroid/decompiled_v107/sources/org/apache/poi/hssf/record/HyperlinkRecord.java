package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.hpsf.ClassIDPredefined;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.HexRead;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class HyperlinkRecord extends StandardRecord {
    static final int HLINK_ABS = 2;
    static final int HLINK_LABEL = 20;
    static final int HLINK_PLACE = 8;
    private static final int HLINK_TARGET_FRAME = 128;
    private static final int HLINK_UNC_PATH = 256;
    static final int HLINK_URL = 1;
    public static final short sid = 440;
    private String _address;
    private int _fileOpts;
    private ClassID _guid;
    private String _label;
    private int _linkOpts;
    private ClassID _moniker;
    private CellRangeAddress _range;
    private String _shortFilename;
    private String _targetFrame;
    private String _textMark;
    private byte[] _uninterpretedTail;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) HyperlinkRecord.class);
    private static final byte[] URL_TAIL = HexRead.readFromString("79 58 81 F4  3B 1D 7F 48   AF 2C 82 5D  C4 85 27 63   00 00 00 00  A5 AB 00 00");
    private static final byte[] FILE_TAIL = HexRead.readFromString("FF FF AD DE  00 00 00 00   00 00 00 00  00 00 00 00   00 00 00 00  00 00 00 00");
    private static final int TAIL_SIZE = FILE_TAIL.length;

    public HyperlinkRecord() {
    }

    public HyperlinkRecord(HyperlinkRecord other) {
        super(other);
        this._range = other._range == null ? null : other._range.copy();
        this._guid = other._guid == null ? null : other._guid.copy();
        this._fileOpts = other._fileOpts;
        this._linkOpts = other._linkOpts;
        this._label = other._label;
        this._targetFrame = other._targetFrame;
        this._moniker = other._moniker == null ? null : other._moniker.copy();
        this._shortFilename = other._shortFilename;
        this._address = other._address;
        this._textMark = other._textMark;
        this._uninterpretedTail = other._uninterpretedTail != null ? (byte[]) other._uninterpretedTail.clone() : null;
    }

    public int getFirstColumn() {
        return this._range.getFirstColumn();
    }

    public void setFirstColumn(int firstCol) {
        this._range.setFirstColumn(firstCol);
    }

    public int getLastColumn() {
        return this._range.getLastColumn();
    }

    public void setLastColumn(int lastCol) {
        this._range.setLastColumn(lastCol);
    }

    public int getFirstRow() {
        return this._range.getFirstRow();
    }

    public void setFirstRow(int firstRow) {
        this._range.setFirstRow(firstRow);
    }

    public int getLastRow() {
        return this._range.getLastRow();
    }

    public void setLastRow(int lastRow) {
        this._range.setLastRow(lastRow);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassID getGuid() {
        return this._guid;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassID getMoniker() {
        return this._moniker;
    }

    private static String cleanString(String s) {
        if (s == null) {
            return null;
        }
        int idx = s.indexOf(0);
        if (idx < 0) {
            return s;
        }
        return s.substring(0, idx);
    }

    private static String appendNullTerm(String s) {
        if (s == null) {
            return null;
        }
        return s + (char) 0;
    }

    public String getLabel() {
        return cleanString(this._label);
    }

    public void setLabel(String label) {
        this._label = appendNullTerm(label);
    }

    public String getTargetFrame() {
        return cleanString(this._targetFrame);
    }

    public String getAddress() {
        if ((this._linkOpts & 1) != 0 && ClassIDPredefined.FILE_MONIKER.equals(this._moniker)) {
            return cleanString(this._address != null ? this._address : this._shortFilename);
        }
        if ((this._linkOpts & 8) != 0) {
            return cleanString(this._textMark);
        }
        return cleanString(this._address);
    }

    public void setAddress(String address) {
        if ((this._linkOpts & 1) != 0 && ClassIDPredefined.FILE_MONIKER.equals(this._moniker)) {
            this._shortFilename = appendNullTerm(address);
        } else if ((this._linkOpts & 8) != 0) {
            this._textMark = appendNullTerm(address);
        } else {
            this._address = appendNullTerm(address);
        }
    }

    public String getShortFilename() {
        return cleanString(this._shortFilename);
    }

    public void setShortFilename(String shortFilename) {
        this._shortFilename = appendNullTerm(shortFilename);
    }

    public String getTextMark() {
        return cleanString(this._textMark);
    }

    public void setTextMark(String textMark) {
        this._textMark = appendNullTerm(textMark);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getLinkOptions() {
        return this._linkOpts;
    }

    public int getLabelOptions() {
        return 2;
    }

    public int getFileOptions() {
        return this._fileOpts;
    }

    public HyperlinkRecord(RecordInputStream in) {
        this._range = new CellRangeAddress(in);
        this._guid = new ClassID(in);
        int streamVersion = in.readInt();
        if (streamVersion != 2) {
            throw new RecordFormatException("Stream Version must be 0x2 but found " + streamVersion);
        }
        this._linkOpts = in.readInt();
        if ((this._linkOpts & 20) != 0) {
            int label_len = in.readInt();
            this._label = in.readUnicodeLEString(label_len);
        }
        int label_len2 = this._linkOpts;
        if ((label_len2 & 128) != 0) {
            int len = in.readInt();
            this._targetFrame = in.readUnicodeLEString(len);
        }
        int len2 = this._linkOpts;
        if ((len2 & 1) != 0 && (this._linkOpts & 256) != 0) {
            this._moniker = null;
            int nChars = in.readInt();
            this._address = in.readUnicodeLEString(nChars);
        }
        int nChars2 = this._linkOpts;
        if ((nChars2 & 1) != 0 && (this._linkOpts & 256) == 0) {
            this._moniker = new ClassID(in);
            if (ClassIDPredefined.URL_MONIKER.equals(this._moniker)) {
                int length = in.readInt();
                int remaining = in.remaining();
                if (length != remaining) {
                    int nChars3 = (length - TAIL_SIZE) / 2;
                    this._address = in.readUnicodeLEString(nChars3);
                    this._uninterpretedTail = readTail(URL_TAIL, in);
                } else {
                    int nChars4 = length / 2;
                    this._address = in.readUnicodeLEString(nChars4);
                }
            } else if (ClassIDPredefined.FILE_MONIKER.equals(this._moniker)) {
                this._fileOpts = in.readShort();
                int len3 = in.readInt();
                this._shortFilename = StringUtil.readCompressedUnicode(in, len3);
                this._uninterpretedTail = readTail(FILE_TAIL, in);
                int size = in.readInt();
                if (size > 0) {
                    int charDataSize = in.readInt();
                    in.readUShort();
                    this._address = StringUtil.readUnicodeLE(in, charDataSize / 2);
                } else {
                    this._address = null;
                }
            } else if (ClassIDPredefined.STD_MONIKER.equals(this._moniker)) {
                this._fileOpts = in.readShort();
                int len4 = in.readInt();
                byte[] path_bytes = IOUtils.safelyAllocate(len4, HSSFWorkbook.getMaxRecordLength());
                in.readFully(path_bytes);
                this._address = new String(path_bytes, StringUtil.UTF8);
            }
        }
        if ((this._linkOpts & 8) != 0) {
            int len5 = in.readInt();
            this._textMark = in.readUnicodeLEString(len5);
        }
        int len6 = in.remaining();
        if (len6 > 0) {
            LOG.atWarn().log("Hyperlink data remains: {} : {}", Unbox.box(in.remaining()), HexDump.toHex(in.readRemainder()));
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        this._range.serialize(out);
        this._guid.write(out);
        out.writeInt(2);
        out.writeInt(this._linkOpts);
        if ((this._linkOpts & 20) != 0) {
            out.writeInt(this._label.length());
            StringUtil.putUnicodeLE(this._label, out);
        }
        if ((this._linkOpts & 128) != 0) {
            out.writeInt(this._targetFrame.length());
            StringUtil.putUnicodeLE(this._targetFrame, out);
        }
        if ((this._linkOpts & 1) != 0 && (this._linkOpts & 256) != 0) {
            out.writeInt(this._address.length());
            StringUtil.putUnicodeLE(this._address, out);
        }
        if ((this._linkOpts & 1) != 0 && (this._linkOpts & 256) == 0) {
            this._moniker.write(out);
            if (ClassIDPredefined.URL_MONIKER.equals(this._moniker)) {
                if (this._uninterpretedTail == null) {
                    out.writeInt(this._address.length() * 2);
                    StringUtil.putUnicodeLE(this._address, out);
                } else {
                    out.writeInt((this._address.length() * 2) + TAIL_SIZE);
                    StringUtil.putUnicodeLE(this._address, out);
                    writeTail(this._uninterpretedTail, out);
                }
            } else if (ClassIDPredefined.FILE_MONIKER.equals(this._moniker)) {
                out.writeShort(this._fileOpts);
                out.writeInt(this._shortFilename.length());
                StringUtil.putCompressedUnicode(this._shortFilename, out);
                writeTail(this._uninterpretedTail, out);
                if (this._address != null) {
                    int addrLen = this._address.length() * 2;
                    out.writeInt(addrLen + 6);
                    out.writeInt(addrLen);
                    out.writeShort(3);
                    StringUtil.putUnicodeLE(this._address, out);
                } else {
                    out.writeInt(0);
                }
            }
        }
        if ((this._linkOpts & 8) != 0) {
            out.writeInt(this._textMark.length());
            StringUtil.putUnicodeLE(this._textMark, out);
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        int size = 0 + 8 + 16 + 4 + 4;
        if ((this._linkOpts & 20) != 0) {
            size = size + 4 + (this._label.length() * 2);
        }
        if ((this._linkOpts & 128) != 0) {
            size = size + 4 + (this._targetFrame.length() * 2);
        }
        if ((this._linkOpts & 1) != 0 && (this._linkOpts & 256) != 0) {
            size = size + 4 + (this._address.length() * 2);
        }
        if ((this._linkOpts & 1) != 0 && (this._linkOpts & 256) == 0) {
            size += 16;
            if (ClassIDPredefined.URL_MONIKER.equals(this._moniker)) {
                size = size + 4 + (this._address.length() * 2);
                if (this._uninterpretedTail != null) {
                    size += TAIL_SIZE;
                }
            } else if (ClassIDPredefined.FILE_MONIKER.equals(this._moniker)) {
                size = size + 2 + 4 + this._shortFilename.length() + TAIL_SIZE + 4;
                if (this._address != null) {
                    size = size + 6 + (this._address.length() * 2);
                }
            }
        }
        if ((this._linkOpts & 8) != 0) {
            return size + 4 + (this._textMark.length() * 2);
        }
        return size;
    }

    private static byte[] readTail(byte[] expectedTail, LittleEndianInput in) {
        byte[] result = new byte[TAIL_SIZE];
        in.readFully(result);
        return result;
    }

    private static void writeTail(byte[] tail, LittleEndianOutput out) {
        out.write(tail);
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    public boolean isUrlLink() {
        return (this._linkOpts & 1) > 0 && (this._linkOpts & 2) > 0;
    }

    public boolean isFileLink() {
        return (this._linkOpts & 1) > 0 && (this._linkOpts & 2) == 0;
    }

    public boolean isDocumentLink() {
        return (this._linkOpts & 8) > 0;
    }

    public void newUrlLink() {
        this._range = new CellRangeAddress(0, 0, 0, 0);
        this._guid = ClassIDPredefined.STD_MONIKER.getClassID();
        this._linkOpts = 23;
        setLabel("");
        this._moniker = ClassIDPredefined.URL_MONIKER.getClassID();
        setAddress("");
        this._uninterpretedTail = URL_TAIL;
    }

    public void newFileLink() {
        this._range = new CellRangeAddress(0, 0, 0, 0);
        this._guid = ClassIDPredefined.STD_MONIKER.getClassID();
        this._linkOpts = 21;
        this._fileOpts = 0;
        setLabel("");
        this._moniker = ClassIDPredefined.FILE_MONIKER.getClassID();
        setAddress(null);
        setShortFilename("");
        this._uninterpretedTail = FILE_TAIL;
    }

    public void newDocumentLink() {
        this._range = new CellRangeAddress(0, 0, 0, 0);
        this._guid = ClassIDPredefined.STD_MONIKER.getClassID();
        this._linkOpts = 28;
        setLabel("");
        this._moniker = ClassIDPredefined.FILE_MONIKER.getClassID();
        setAddress("");
        setTextMark("");
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public HyperlinkRecord copy() {
        return new HyperlinkRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.HYPERLINK;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("range", new Supplier() { // from class: org.apache.poi.hssf.record.HyperlinkRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return HyperlinkRecord.this.m2314x8c51ce98();
            }
        }, "guid", new Supplier() { // from class: org.apache.poi.hssf.record.HyperlinkRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return HyperlinkRecord.this.getGuid();
            }
        }, "linkOpts", new Supplier() { // from class: org.apache.poi.hssf.record.HyperlinkRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return HyperlinkRecord.this.m2315x4f3e37f7();
            }
        }, "label", new Supplier() { // from class: org.apache.poi.hssf.record.HyperlinkRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return HyperlinkRecord.this.getLabel();
            }
        }, "targetFrame", new Supplier() { // from class: org.apache.poi.hssf.record.HyperlinkRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return HyperlinkRecord.this.getTargetFrame();
            }
        }, "moniker", new Supplier() { // from class: org.apache.poi.hssf.record.HyperlinkRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return HyperlinkRecord.this.getMoniker();
            }
        }, "textMark", new Supplier() { // from class: org.apache.poi.hssf.record.HyperlinkRecord$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return HyperlinkRecord.this.getTextMark();
            }
        }, "address", new Supplier() { // from class: org.apache.poi.hssf.record.HyperlinkRecord$$ExternalSyntheticLambda8
            @Override // java.util.function.Supplier
            public final Object get() {
                return HyperlinkRecord.this.getAddress();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-HyperlinkRecord, reason: not valid java name */
    public /* synthetic */ Object m2314x8c51ce98() {
        return this._range;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-HyperlinkRecord, reason: not valid java name */
    public /* synthetic */ Object m2315x4f3e37f7() {
        return GenericRecordUtil.getBitsAsString((Supplier<Number>) new Supplier() { // from class: org.apache.poi.hssf.record.HyperlinkRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(HyperlinkRecord.this.getLinkOptions());
            }
        }, new int[]{1, 2, 8, 20, 128, 256}, new String[]{"URL", "ABS", "PLACE", "LABEL", "TARGET_FRAME", "UNC_PATH"});
    }
}
