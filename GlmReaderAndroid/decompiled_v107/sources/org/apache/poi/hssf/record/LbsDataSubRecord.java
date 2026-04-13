package org.apache.poi.hssf.record;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.LbsDataSubRecord;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public class LbsDataSubRecord extends SubRecord {
    public static final int sid = 19;
    private boolean[] _bsels;
    private int _cLines;
    private int _cbFContinued;
    private LbsDropData _dropData;
    private int _flags;
    private int _iSel;
    private int _idEdit;
    private Ptg _linkPtg;
    private String[] _rgLines;
    private Byte _unknownPostFormulaByte;
    private int _unknownPreFormulaInt;

    LbsDataSubRecord() {
    }

    public LbsDataSubRecord(LbsDataSubRecord other) {
        super(other);
        this._cbFContinued = other._cbFContinued;
        this._unknownPreFormulaInt = other._unknownPreFormulaInt;
        this._linkPtg = other._linkPtg == null ? null : other._linkPtg.copy();
        this._unknownPostFormulaByte = other._unknownPostFormulaByte;
        this._cLines = other._cLines;
        this._iSel = other._iSel;
        this._flags = other._flags;
        this._idEdit = other._idEdit;
        this._dropData = other._dropData == null ? null : other._dropData.copy();
        this._rgLines = other._rgLines == null ? null : (String[]) other._rgLines.clone();
        this._bsels = other._bsels != null ? (boolean[]) other._bsels.clone() : null;
    }

    public LbsDataSubRecord(LittleEndianInput in, int cbFContinued, int cmoOt) {
        this._cbFContinued = cbFContinued;
        int encodedTokenLen = in.readUShort();
        if (encodedTokenLen > 0) {
            int formulaSize = in.readUShort();
            this._unknownPreFormulaInt = in.readInt();
            Ptg[] ptgs = Ptg.readTokens(formulaSize, in);
            if (ptgs.length != 1) {
                throw new RecordFormatException("Read " + ptgs.length + " tokens but expected exactly 1");
            }
            this._linkPtg = ptgs[0];
            switch ((encodedTokenLen - formulaSize) - 6) {
                case 0:
                    this._unknownPostFormulaByte = null;
                    break;
                case 1:
                    this._unknownPostFormulaByte = Byte.valueOf(in.readByte());
                    break;
                default:
                    throw new RecordFormatException("Unexpected leftover bytes");
            }
        }
        this._cLines = in.readUShort();
        this._iSel = in.readUShort();
        this._flags = in.readUShort();
        this._idEdit = in.readUShort();
        if (cmoOt == 20) {
            this._dropData = new LbsDropData(in);
        }
        if ((this._flags & 2) != 0) {
            this._rgLines = new String[this._cLines];
            for (int i = 0; i < this._cLines; i++) {
                this._rgLines[i] = StringUtil.readUnicodeString(in);
            }
        }
        int i2 = this._flags;
        if (((i2 >> 4) & 1) + ((this._flags >> 5) & 1) != 0) {
            this._bsels = new boolean[this._cLines];
            for (int i3 = 0; i3 < this._cLines; i3++) {
                this._bsels[i3] = in.readByte() == 1;
            }
        }
    }

    public static LbsDataSubRecord newAutoFilterInstance() {
        LbsDataSubRecord lbs = new LbsDataSubRecord();
        lbs._cbFContinued = 8174;
        lbs._iSel = 0;
        lbs._flags = 769;
        lbs._dropData = new LbsDropData();
        lbs._dropData._wStyle = 2;
        lbs._dropData._cLine = 8;
        return lbs;
    }

    @Override // org.apache.poi.hssf.record.SubRecord
    public boolean isTerminating() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.hssf.record.SubRecord
    public int getDataSize() {
        int result = 2;
        if (this._linkPtg != null) {
            int result2 = 2 + 2;
            result = result2 + 4 + this._linkPtg.getSize();
            if (this._unknownPostFormulaByte != null) {
                result++;
            }
        }
        int result3 = result + 8;
        if (this._dropData != null) {
            result3 += this._dropData.getDataSize();
        }
        if (this._rgLines != null) {
            for (String str : this._rgLines) {
                result3 += StringUtil.getEncodedSize(str);
            }
        }
        if (this._bsels != null) {
            return result3 + this._bsels.length;
        }
        return result3;
    }

    @Override // org.apache.poi.hssf.record.SubRecord
    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(19);
        littleEndianOutput.writeShort(this._cbFContinued);
        if (this._linkPtg == null) {
            littleEndianOutput.writeShort(0);
        } else {
            int size = this._linkPtg.getSize();
            int i = size + 6;
            if (this._unknownPostFormulaByte != null) {
                i++;
            }
            littleEndianOutput.writeShort(i);
            littleEndianOutput.writeShort(size);
            littleEndianOutput.writeInt(this._unknownPreFormulaInt);
            this._linkPtg.write(littleEndianOutput);
            if (this._unknownPostFormulaByte != null) {
                littleEndianOutput.writeByte(this._unknownPostFormulaByte.intValue());
            }
        }
        littleEndianOutput.writeShort(this._cLines);
        littleEndianOutput.writeShort(this._iSel);
        littleEndianOutput.writeShort(this._flags);
        littleEndianOutput.writeShort(this._idEdit);
        if (this._dropData != null) {
            this._dropData.serialize(littleEndianOutput);
        }
        if (this._rgLines != null) {
            for (String str : this._rgLines) {
                StringUtil.writeUnicodeString(littleEndianOutput, str);
            }
        }
        if (this._bsels != null) {
            for (boolean z : this._bsels) {
                littleEndianOutput.writeByte(z ? 1 : 0);
            }
        }
    }

    @Override // org.apache.poi.hssf.record.SubRecord, org.apache.poi.common.Duplicatable
    public LbsDataSubRecord copy() {
        return new LbsDataSubRecord(this);
    }

    public Ptg getFormula() {
        return this._linkPtg;
    }

    public int getNumberOfItems() {
        return this._cLines;
    }

    @Override // org.apache.poi.hssf.record.SubRecord, org.apache.poi.common.usermodel.GenericRecord
    public SubRecord.SubRecordTypes getGenericRecordType() {
        return SubRecord.SubRecordTypes.LBS_DATA;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        Map<String, Supplier<?>> m = new LinkedHashMap<>();
        m.put("unknownShort1", new Supplier() { // from class: org.apache.poi.hssf.record.LbsDataSubRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return LbsDataSubRecord.this.m2319x73dee549();
            }
        });
        m.put("unknownPreFormulaInt", new Supplier() { // from class: org.apache.poi.hssf.record.LbsDataSubRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return LbsDataSubRecord.this.m2320xe7fa7ca();
            }
        });
        m.put("formula", new Supplier() { // from class: org.apache.poi.hssf.record.LbsDataSubRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return LbsDataSubRecord.this.getFormula();
            }
        });
        m.put("unknownPostFormulaByte", new Supplier() { // from class: org.apache.poi.hssf.record.LbsDataSubRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return LbsDataSubRecord.this.m2321xa9206a4b();
            }
        });
        m.put("numberOfItems", new Supplier() { // from class: org.apache.poi.hssf.record.LbsDataSubRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(LbsDataSubRecord.this.getNumberOfItems());
            }
        });
        m.put("selEntryIx", new Supplier() { // from class: org.apache.poi.hssf.record.LbsDataSubRecord$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return LbsDataSubRecord.this.m2322x43c12ccc();
            }
        });
        m.put("style", new Supplier() { // from class: org.apache.poi.hssf.record.LbsDataSubRecord$$ExternalSyntheticLambda8
            @Override // java.util.function.Supplier
            public final Object get() {
                return LbsDataSubRecord.this.m2323xde61ef4d();
            }
        });
        m.put("unknownShort10", new Supplier() { // from class: org.apache.poi.hssf.record.LbsDataSubRecord$$ExternalSyntheticLambda9
            @Override // java.util.function.Supplier
            public final Object get() {
                return LbsDataSubRecord.this.m2324x7902b1ce();
            }
        });
        m.put("dropData", new Supplier() { // from class: org.apache.poi.hssf.record.LbsDataSubRecord$$ExternalSyntheticLambda10
            @Override // java.util.function.Supplier
            public final Object get() {
                return LbsDataSubRecord.this.m2325x13a3744f();
            }
        });
        m.put("rgLines", new Supplier() { // from class: org.apache.poi.hssf.record.LbsDataSubRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return LbsDataSubRecord.this.m2326xae4436d0();
            }
        });
        m.put("bsels", new Supplier() { // from class: org.apache.poi.hssf.record.LbsDataSubRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return LbsDataSubRecord.this.m2327x48e4f951();
            }
        });
        return Collections.unmodifiableMap(m);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-LbsDataSubRecord, reason: not valid java name */
    public /* synthetic */ Object m2319x73dee549() {
        return Integer.valueOf(this._cbFContinued);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-LbsDataSubRecord, reason: not valid java name */
    public /* synthetic */ Object m2320xe7fa7ca() {
        return Integer.valueOf(this._unknownPreFormulaInt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-LbsDataSubRecord, reason: not valid java name */
    public /* synthetic */ Object m2321xa9206a4b() {
        return this._unknownPostFormulaByte;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-LbsDataSubRecord, reason: not valid java name */
    public /* synthetic */ Object m2322x43c12ccc() {
        return Integer.valueOf(this._iSel);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-LbsDataSubRecord, reason: not valid java name */
    public /* synthetic */ Object m2323xde61ef4d() {
        return Integer.valueOf(this._flags);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-LbsDataSubRecord, reason: not valid java name */
    public /* synthetic */ Object m2324x7902b1ce() {
        return Integer.valueOf(this._idEdit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$6$org-apache-poi-hssf-record-LbsDataSubRecord, reason: not valid java name */
    public /* synthetic */ Object m2325x13a3744f() {
        return this._dropData;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$7$org-apache-poi-hssf-record-LbsDataSubRecord, reason: not valid java name */
    public /* synthetic */ Object m2326xae4436d0() {
        return this._rgLines;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$8$org-apache-poi-hssf-record-LbsDataSubRecord, reason: not valid java name */
    public /* synthetic */ Object m2327x48e4f951() {
        return this._bsels;
    }

    /* loaded from: classes10.dex */
    public static class LbsDropData implements Duplicatable, GenericRecord {
        public static final int STYLE_COMBO_DROPDOWN = 0;
        public static final int STYLE_COMBO_EDIT_DROPDOWN = 1;
        public static final int STYLE_COMBO_SIMPLE_DROPDOWN = 2;
        private int _cLine;
        private int _dxMin;
        private final String _str;
        private Byte _unused;
        private int _wStyle;

        public LbsDropData() {
            this._str = "";
            this._unused = (byte) 0;
        }

        public LbsDropData(LbsDropData other) {
            this._wStyle = other._wStyle;
            this._cLine = other._cLine;
            this._dxMin = other._dxMin;
            this._str = other._str;
            this._unused = other._unused;
        }

        public LbsDropData(LittleEndianInput in) {
            this._wStyle = in.readUShort();
            this._cLine = in.readUShort();
            this._dxMin = in.readUShort();
            this._str = StringUtil.readUnicodeString(in);
            if (StringUtil.getEncodedSize(this._str) % 2 != 0) {
                this._unused = Byte.valueOf(in.readByte());
            }
        }

        public void setStyle(int style) {
            this._wStyle = style;
        }

        public void setNumLines(int num) {
            this._cLine = num;
        }

        public void serialize(LittleEndianOutput out) {
            out.writeShort(this._wStyle);
            out.writeShort(this._cLine);
            out.writeShort(this._dxMin);
            StringUtil.writeUnicodeString(out, this._str);
            if (this._unused != null) {
                out.writeByte(this._unused.byteValue());
            }
        }

        public int getDataSize() {
            int size = 6 + StringUtil.getEncodedSize(this._str);
            if (this._unused != null) {
                return size + 1;
            }
            return size;
        }

        public String toString() {
            return GenericRecordJsonWriter.marshal(this);
        }

        @Override // org.apache.poi.common.Duplicatable
        public LbsDropData copy() {
            return new LbsDropData(this);
        }

        @Override // org.apache.poi.common.usermodel.GenericRecord
        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties("wStyle", new Supplier() { // from class: org.apache.poi.hssf.record.LbsDataSubRecord$LbsDropData$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return LbsDataSubRecord.LbsDropData.this.m2328x20d2a51b();
                }
            }, "cLine", new Supplier() { // from class: org.apache.poi.hssf.record.LbsDataSubRecord$LbsDropData$$ExternalSyntheticLambda1
                @Override // java.util.function.Supplier
                public final Object get() {
                    return LbsDataSubRecord.LbsDropData.this.m2329x4666ae1c();
                }
            }, "dxMin", new Supplier() { // from class: org.apache.poi.hssf.record.LbsDataSubRecord$LbsDropData$$ExternalSyntheticLambda2
                @Override // java.util.function.Supplier
                public final Object get() {
                    return LbsDataSubRecord.LbsDropData.this.m2330x6bfab71d();
                }
            }, "str", new Supplier() { // from class: org.apache.poi.hssf.record.LbsDataSubRecord$LbsDropData$$ExternalSyntheticLambda3
                @Override // java.util.function.Supplier
                public final Object get() {
                    return LbsDataSubRecord.LbsDropData.this.m2331x918ec01e();
                }
            }, "unused", new Supplier() { // from class: org.apache.poi.hssf.record.LbsDataSubRecord$LbsDropData$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return LbsDataSubRecord.LbsDropData.this.m2332xb722c91f();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-LbsDataSubRecord$LbsDropData, reason: not valid java name */
        public /* synthetic */ Object m2328x20d2a51b() {
            return Integer.valueOf(this._wStyle);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-LbsDataSubRecord$LbsDropData, reason: not valid java name */
        public /* synthetic */ Object m2329x4666ae1c() {
            return Integer.valueOf(this._cLine);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-LbsDataSubRecord$LbsDropData, reason: not valid java name */
        public /* synthetic */ Object m2330x6bfab71d() {
            return Integer.valueOf(this._dxMin);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-LbsDataSubRecord$LbsDropData, reason: not valid java name */
        public /* synthetic */ Object m2331x918ec01e() {
            return this._str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-LbsDataSubRecord$LbsDropData, reason: not valid java name */
        public /* synthetic */ Object m2332xb722c91f() {
            return this._unused;
        }
    }
}
