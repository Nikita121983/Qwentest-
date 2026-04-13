package org.apache.poi.hssf.record;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.AreaPtg;
import org.apache.poi.ss.formula.ptg.MissingArgPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Ref3DPtg;
import org.apache.poi.ss.formula.ptg.RefPtg;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianInputStream;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class EmbeddedObjectRefSubRecord extends SubRecord {
    public static final short sid = 9;
    private int field_1_unknown_int;
    private Ptg field_2_refPtg;
    private byte[] field_2_unknownFormulaData;
    private boolean field_3_unicode_flag;
    private String field_4_ole_classname;
    private Byte field_4_unknownByte;
    private Integer field_5_stream_id;
    private byte[] field_6_unknown;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) EmbeddedObjectRefSubRecord.class);
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    public EmbeddedObjectRefSubRecord() {
        this.field_2_unknownFormulaData = new byte[]{2, 108, 106, MissingArgPtg.sid, 1};
        this.field_6_unknown = EMPTY_BYTE_ARRAY;
        this.field_4_ole_classname = null;
    }

    public EmbeddedObjectRefSubRecord(EmbeddedObjectRefSubRecord other) {
        super(other);
        this.field_1_unknown_int = other.field_1_unknown_int;
        this.field_2_refPtg = other.field_2_refPtg == null ? null : other.field_2_refPtg.copy();
        this.field_2_unknownFormulaData = other.field_2_unknownFormulaData == null ? null : (byte[]) other.field_2_unknownFormulaData.clone();
        this.field_3_unicode_flag = other.field_3_unicode_flag;
        this.field_4_ole_classname = other.field_4_ole_classname;
        this.field_4_unknownByte = other.field_4_unknownByte;
        this.field_5_stream_id = other.field_5_stream_id;
        this.field_6_unknown = other.field_6_unknown != null ? (byte[]) other.field_6_unknown.clone() : null;
    }

    public EmbeddedObjectRefSubRecord(LittleEndianInput in, int size) {
        this(in, size, -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EmbeddedObjectRefSubRecord(LittleEndianInput in, int size, int cmoOt) {
        int stringByteCount;
        int streamIdOffset = in.readShort();
        int remaining = size - 2;
        int dataLenAfterFormula = remaining - streamIdOffset;
        int formulaSize = in.readUShort();
        this.field_1_unknown_int = in.readInt();
        byte[] formulaRawBytes = readRawData(in, formulaSize);
        int remaining2 = ((remaining - 2) - 4) - formulaSize;
        this.field_2_refPtg = readRefPtg(formulaRawBytes);
        if (this.field_2_refPtg == null) {
            this.field_2_unknownFormulaData = formulaRawBytes;
        } else {
            this.field_2_unknownFormulaData = null;
        }
        if (remaining2 >= dataLenAfterFormula + 3) {
            int tag = in.readByte();
            if (tag != 3) {
                throw new RecordFormatException("Expected byte 0x03 here");
            }
            int nChars = in.readUShort();
            stringByteCount = 1 + 2;
            if (nChars > 0) {
                this.field_3_unicode_flag = (in.readByte() & 1) != 0;
                int stringByteCount2 = stringByteCount + 1;
                if (this.field_3_unicode_flag) {
                    this.field_4_ole_classname = StringUtil.readUnicodeLE(in, nChars);
                    stringByteCount = stringByteCount2 + (nChars * 2);
                } else {
                    this.field_4_ole_classname = StringUtil.readCompressedUnicode(in, nChars);
                    stringByteCount = stringByteCount2 + nChars;
                }
            } else {
                this.field_4_ole_classname = "";
            }
        } else {
            this.field_4_ole_classname = null;
            stringByteCount = 0;
        }
        int remaining3 = remaining2 - stringByteCount;
        if ((stringByteCount + formulaSize) % 2 != 0) {
            int b = in.readByte();
            remaining3--;
            if (this.field_2_refPtg != null && this.field_4_ole_classname == null) {
                this.field_4_unknownByte = Byte.valueOf((byte) b);
            }
        }
        int b2 = remaining3 - dataLenAfterFormula;
        if (b2 > 0) {
            LOG.atError().log("Discarding {} unexpected padding bytes", Unbox.box(b2));
            readRawData(in, b2);
            remaining3 -= b2;
        }
        if (dataLenAfterFormula >= 4) {
            this.field_5_stream_id = Integer.valueOf(in.readInt());
            remaining3 -= 4;
        } else {
            this.field_5_stream_id = null;
        }
        this.field_6_unknown = readRawData(in, remaining3);
    }

    public short getSid() {
        return (short) 9;
    }

    private static Ptg readRefPtg(byte[] formulaRawBytes) {
        try {
            LittleEndianInputStream in = new LittleEndianInputStream(UnsynchronizedByteArrayInputStream.builder().setByteArray(formulaRawBytes).get());
            try {
                byte ptgSid = in.readByte();
                switch (ptgSid) {
                    case 36:
                        RefPtg refPtg = new RefPtg(in);
                        in.close();
                        return refPtg;
                    case 37:
                        AreaPtg areaPtg = new AreaPtg(in);
                        in.close();
                        return areaPtg;
                    case 58:
                        Ref3DPtg ref3DPtg = new Ref3DPtg(in);
                        in.close();
                        return ref3DPtg;
                    case 59:
                        Area3DPtg area3DPtg = new Area3DPtg(in);
                        in.close();
                        return area3DPtg;
                    default:
                        in.close();
                        return null;
                }
            } finally {
            }
        } catch (IOException e) {
            throw new IllegalStateException("Unexpected exception in readRefPtg", e);
        }
    }

    private static byte[] readRawData(LittleEndianInput in, int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Negative size (" + size + ")");
        }
        if (size == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        byte[] result = IOUtils.safelyAllocate(size, HSSFWorkbook.getMaxRecordLength());
        in.readFully(result);
        return result;
    }

    private int getStreamIDOffset(int formulaSize) {
        int result = 6 + formulaSize;
        if (this.field_4_ole_classname != null) {
            result += 3;
            int stringLen = this.field_4_ole_classname.length();
            if (stringLen > 0) {
                int result2 = result + 1;
                if (this.field_3_unicode_flag) {
                    result = result2 + (stringLen * 2);
                } else {
                    result = result2 + stringLen;
                }
            }
        }
        if (result % 2 != 0) {
            return result + 1;
        }
        return result;
    }

    private int getDataSize(int idOffset) {
        int result = idOffset + 2;
        if (this.field_5_stream_id != null) {
            result += 4;
        }
        return this.field_6_unknown.length + result;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.hssf.record.SubRecord
    public int getDataSize() {
        int formulaSize = this.field_2_refPtg == null ? this.field_2_unknownFormulaData.length : this.field_2_refPtg.getSize();
        int idOffset = getStreamIDOffset(formulaSize);
        return getDataSize(idOffset);
    }

    @Override // org.apache.poi.hssf.record.SubRecord
    public void serialize(LittleEndianOutput littleEndianOutput) {
        int length = this.field_2_refPtg == null ? this.field_2_unknownFormulaData.length : this.field_2_refPtg.getSize();
        int streamIDOffset = getStreamIDOffset(length);
        int dataSize = getDataSize(streamIDOffset);
        littleEndianOutput.writeShort(9);
        littleEndianOutput.writeShort(dataSize);
        littleEndianOutput.writeShort(streamIDOffset);
        littleEndianOutput.writeShort(length);
        littleEndianOutput.writeInt(this.field_1_unknown_int);
        if (this.field_2_refPtg == null) {
            littleEndianOutput.write(this.field_2_unknownFormulaData);
        } else {
            this.field_2_refPtg.write(littleEndianOutput);
        }
        int i = 12 + length;
        if (this.field_4_ole_classname != null) {
            littleEndianOutput.writeByte(3);
            int length2 = this.field_4_ole_classname.length();
            littleEndianOutput.writeShort(length2);
            i = i + 1 + 2;
            if (length2 > 0) {
                littleEndianOutput.writeByte(this.field_3_unicode_flag ? 1 : 0);
                int i2 = i + 1;
                if (this.field_3_unicode_flag) {
                    StringUtil.putUnicodeLE(this.field_4_ole_classname, littleEndianOutput);
                    i = i2 + (length2 * 2);
                } else {
                    StringUtil.putCompressedUnicode(this.field_4_ole_classname, littleEndianOutput);
                    i = i2 + length2;
                }
            }
        }
        switch (streamIDOffset - (i - 6)) {
            case 0:
                break;
            case 1:
                littleEndianOutput.writeByte(this.field_4_unknownByte == null ? 0 : this.field_4_unknownByte.intValue());
                break;
            default:
                throw new IllegalStateException("Bad padding calculation (" + streamIDOffset + ", " + i + ")");
        }
        if (this.field_5_stream_id != null) {
            littleEndianOutput.writeInt(this.field_5_stream_id.intValue());
        }
        littleEndianOutput.write(this.field_6_unknown);
    }

    public Integer getStreamId() {
        return this.field_5_stream_id;
    }

    public String getOLEClassName() {
        return this.field_4_ole_classname;
    }

    public byte[] getObjectData() {
        return this.field_6_unknown;
    }

    @Override // org.apache.poi.hssf.record.SubRecord, org.apache.poi.common.Duplicatable
    public EmbeddedObjectRefSubRecord copy() {
        return new EmbeddedObjectRefSubRecord(this);
    }

    public void setUnknownFormulaData(byte[] formularData) {
        this.field_2_unknownFormulaData = formularData;
    }

    public void setOleClassname(String oleClassname) {
        this.field_4_ole_classname = oleClassname;
    }

    public void setStorageId(int storageId) {
        this.field_5_stream_id = Integer.valueOf(storageId);
    }

    @Override // org.apache.poi.hssf.record.SubRecord, org.apache.poi.common.usermodel.GenericRecord
    public SubRecord.SubRecordTypes getGenericRecordType() {
        return SubRecord.SubRecordTypes.EMBEDDED_OBJECT_REF;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("f2unknown", new Supplier() { // from class: org.apache.poi.hssf.record.EmbeddedObjectRefSubRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return EmbeddedObjectRefSubRecord.this.m2287x38309786();
            }
        }, "f3unknown", new Supplier() { // from class: org.apache.poi.hssf.record.EmbeddedObjectRefSubRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return EmbeddedObjectRefSubRecord.this.m2288x6184ecc7();
            }
        }, "formula", new Supplier() { // from class: org.apache.poi.hssf.record.EmbeddedObjectRefSubRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return EmbeddedObjectRefSubRecord.this.m2289x8ad94208();
            }
        }, "unicodeFlag", new Supplier() { // from class: org.apache.poi.hssf.record.EmbeddedObjectRefSubRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return EmbeddedObjectRefSubRecord.this.m2290xb42d9749();
            }
        }, "oleClassname", new Supplier() { // from class: org.apache.poi.hssf.record.EmbeddedObjectRefSubRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return EmbeddedObjectRefSubRecord.this.m2291xdd81ec8a();
            }
        }, "f4unknown", new Supplier() { // from class: org.apache.poi.hssf.record.EmbeddedObjectRefSubRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return EmbeddedObjectRefSubRecord.this.m2292x6d641cb();
            }
        }, "streamId", new Supplier() { // from class: org.apache.poi.hssf.record.EmbeddedObjectRefSubRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return EmbeddedObjectRefSubRecord.this.m2293x302a970c();
            }
        }, "f7unknown", new Supplier() { // from class: org.apache.poi.hssf.record.EmbeddedObjectRefSubRecord$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                return EmbeddedObjectRefSubRecord.this.m2294x597eec4d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-EmbeddedObjectRefSubRecord, reason: not valid java name */
    public /* synthetic */ Object m2287x38309786() {
        return Integer.valueOf(this.field_1_unknown_int);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-EmbeddedObjectRefSubRecord, reason: not valid java name */
    public /* synthetic */ Object m2288x6184ecc7() {
        return this.field_2_unknownFormulaData;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-EmbeddedObjectRefSubRecord, reason: not valid java name */
    public /* synthetic */ Object m2289x8ad94208() {
        return this.field_2_refPtg;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-EmbeddedObjectRefSubRecord, reason: not valid java name */
    public /* synthetic */ Object m2290xb42d9749() {
        return Boolean.valueOf(this.field_3_unicode_flag);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-EmbeddedObjectRefSubRecord, reason: not valid java name */
    public /* synthetic */ Object m2291xdd81ec8a() {
        return this.field_4_ole_classname;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-EmbeddedObjectRefSubRecord, reason: not valid java name */
    public /* synthetic */ Object m2292x6d641cb() {
        return this.field_4_unknownByte;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$6$org-apache-poi-hssf-record-EmbeddedObjectRefSubRecord, reason: not valid java name */
    public /* synthetic */ Object m2293x302a970c() {
        return this.field_5_stream_id;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$7$org-apache-poi-hssf-record-EmbeddedObjectRefSubRecord, reason: not valid java name */
    public /* synthetic */ Object m2294x597eec4d() {
        return this.field_6_unknown;
    }
}
