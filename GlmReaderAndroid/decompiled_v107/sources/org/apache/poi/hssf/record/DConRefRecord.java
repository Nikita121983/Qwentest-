package org.apache.poi.hssf.record;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public class DConRefRecord extends StandardRecord {
    public static final short sid = 81;
    private byte[] _unused;
    private final int charCount;
    private final int charType;
    private final int firstCol;
    private final int firstRow;
    private final int lastCol;
    private final int lastRow;
    private final byte[] path;

    public DConRefRecord(DConRefRecord other) {
        super(other);
        this.firstCol = other.firstCol;
        this.firstRow = other.firstRow;
        this.lastCol = other.lastCol;
        this.lastRow = other.lastRow;
        this.charCount = other.charCount;
        this.charType = other.charType;
        this.path = other.path == null ? null : (byte[]) other.path.clone();
        this._unused = other._unused != null ? (byte[]) other._unused.clone() : null;
    }

    public DConRefRecord(byte[] data) {
        this(bytesToRIStream(data));
    }

    public DConRefRecord(RecordInputStream inStream) {
        if (inStream.getSid() != 81) {
            throw new RecordFormatException("Wrong sid: " + ((int) inStream.getSid()));
        }
        this.firstRow = inStream.readUShort();
        this.lastRow = inStream.readUShort();
        this.firstCol = inStream.readUByte();
        this.lastCol = inStream.readUByte();
        this.charCount = inStream.readUShort();
        this.charType = inStream.readUByte() & 1;
        int byteLength = this.charCount * (this.charType + 1);
        this.path = IOUtils.safelyAllocate(byteLength, HSSFWorkbook.getMaxRecordLength());
        inStream.readFully(this.path);
        if (this.path[0] == 2) {
            this._unused = inStream.readRemainder();
        }
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        int sz = this.path.length + 9;
        if (this.path[0] == 2) {
            return sz + this._unused.length;
        }
        return sz;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected void serialize(LittleEndianOutput out) {
        out.writeShort(this.firstRow);
        out.writeShort(this.lastRow);
        out.writeByte(this.firstCol);
        out.writeByte(this.lastCol);
        out.writeShort(this.charCount);
        out.writeByte(this.charType);
        out.write(this.path);
        if (this.path[0] == 2) {
            out.write(this._unused);
        }
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 81;
    }

    public int getFirstColumn() {
        return this.firstCol;
    }

    public int getFirstRow() {
        return this.firstRow;
    }

    public int getLastColumn() {
        return this.lastCol;
    }

    public int getLastRow() {
        return this.lastRow;
    }

    public byte[] getPath() {
        return Arrays.copyOf(this.path, this.path.length);
    }

    public String getReadablePath() {
        if (this.path != null) {
            int offset = 1;
            while (offset < this.path.length && this.path[offset] < 32) {
                offset++;
            }
            String out = new String(Arrays.copyOfRange(this.path, offset, this.path.length), StringUtil.UTF8);
            return out.replace("\u0003", PackagingURIHelper.FORWARD_SLASH_STRING);
        }
        return null;
    }

    public boolean isExternalRef() {
        return this.path[0] == 1;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public DConRefRecord copy() {
        return new DConRefRecord(this);
    }

    private static RecordInputStream bytesToRIStream(byte[] data) {
        try {
            RecordInputStream ric = new RecordInputStream(UnsynchronizedByteArrayInputStream.builder().setByteArray(data).get());
            ric.nextRecord();
            return ric;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DCON_REF;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("firstRow", new Supplier() { // from class: org.apache.poi.hssf.record.DConRefRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(DConRefRecord.this.getFirstRow());
            }
        }, "lastRow", new Supplier() { // from class: org.apache.poi.hssf.record.DConRefRecord$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(DConRefRecord.this.getLastRow());
            }
        }, "firstColumn", new Supplier() { // from class: org.apache.poi.hssf.record.DConRefRecord$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(DConRefRecord.this.getFirstColumn());
            }
        }, "lastColumn", new Supplier() { // from class: org.apache.poi.hssf.record.DConRefRecord$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(DConRefRecord.this.getLastColumn());
            }
        }, "charCount", new Supplier() { // from class: org.apache.poi.hssf.record.DConRefRecord$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return DConRefRecord.this.m2272xd820c467();
            }
        }, "charType", new Supplier() { // from class: org.apache.poi.hssf.record.DConRefRecord$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return DConRefRecord.this.m2273xdf85f986();
            }
        }, "path", new Supplier() { // from class: org.apache.poi.hssf.record.DConRefRecord$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return DConRefRecord.this.getReadablePath();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-DConRefRecord, reason: not valid java name */
    public /* synthetic */ Object m2272xd820c467() {
        return Integer.valueOf(this.charCount);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-DConRefRecord, reason: not valid java name */
    public /* synthetic */ Object m2273xdf85f986() {
        return Integer.valueOf(this.charType);
    }
}
