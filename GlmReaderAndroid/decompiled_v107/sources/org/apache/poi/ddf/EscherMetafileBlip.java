package org.apache.poi.ddf;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.Removal;

/* loaded from: classes10.dex */
public final class EscherMetafileBlip extends EscherBlipRecord {
    private static final int HEADER_SIZE = 8;
    private final byte[] field_1_UID;
    private final byte[] field_2_UID;
    private int field_2_cb;
    private int field_3_rcBounds_x1;
    private int field_3_rcBounds_x2;
    private int field_3_rcBounds_y1;
    private int field_3_rcBounds_y2;
    private int field_4_ptSize_h;
    private int field_4_ptSize_w;
    private int field_5_cbSave;
    private byte field_6_fCompression;
    private byte field_7_fFilter;
    private byte[] raw_pictureData;
    private byte[] remainingData;
    private static final Logger LOGGER = PoiLogManager.getLogger((Class<?>) EscherMetafileBlip.class);
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000000;
    private static int MAX_RECORD_LENGTH = DEFAULT_MAX_RECORD_LENGTH;

    @Removal(version = "5.3")
    @Deprecated
    public static final short RECORD_ID_EMF = EscherRecordTypes.BLIP_EMF.typeID;

    @Removal(version = "5.3")
    @Deprecated
    public static final short RECORD_ID_WMF = EscherRecordTypes.BLIP_WMF.typeID;

    @Removal(version = "5.3")
    @Deprecated
    public static final short RECORD_ID_PICT = EscherRecordTypes.BLIP_PICT.typeID;

    public static void setMaxRecordLength(int length) {
        MAX_RECORD_LENGTH = length;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public EscherMetafileBlip() {
        this.field_1_UID = new byte[16];
        this.field_2_UID = new byte[16];
    }

    public EscherMetafileBlip(EscherMetafileBlip other) {
        super(other);
        this.field_1_UID = new byte[16];
        this.field_2_UID = new byte[16];
        System.arraycopy(other.field_1_UID, 0, this.field_1_UID, 0, this.field_1_UID.length);
        System.arraycopy(other.field_2_UID, 0, this.field_2_UID, 0, this.field_2_UID.length);
        this.field_2_cb = other.field_2_cb;
        this.field_3_rcBounds_x1 = other.field_3_rcBounds_x1;
        this.field_3_rcBounds_y1 = other.field_3_rcBounds_y1;
        this.field_3_rcBounds_x2 = other.field_3_rcBounds_x2;
        this.field_3_rcBounds_y2 = other.field_3_rcBounds_y2;
        this.field_4_ptSize_h = other.field_4_ptSize_h;
        this.field_4_ptSize_w = other.field_4_ptSize_w;
        this.field_5_cbSave = other.field_5_cbSave;
        this.field_6_fCompression = other.field_6_fCompression;
        this.field_7_fFilter = other.field_7_fFilter;
        this.raw_pictureData = other.raw_pictureData == null ? null : (byte[]) other.raw_pictureData.clone();
        this.remainingData = other.remainingData != null ? (byte[]) other.remainingData.clone() : null;
    }

    @Override // org.apache.poi.ddf.EscherBlipRecord, org.apache.poi.ddf.EscherRecord
    public int fillFields(byte[] data, int offset, EscherRecordFactory recordFactory) {
        int bytesAfterHeader = readHeader(data, offset);
        int pos = offset + 8;
        System.arraycopy(data, pos, this.field_1_UID, 0, 16);
        int pos2 = pos + 16;
        if ((getOptions() ^ getSignature()) == 16) {
            System.arraycopy(data, pos2, this.field_2_UID, 0, 16);
            pos2 += 16;
        }
        this.field_2_cb = LittleEndian.getInt(data, pos2);
        int pos3 = pos2 + 4;
        this.field_3_rcBounds_x1 = LittleEndian.getInt(data, pos3);
        int pos4 = pos3 + 4;
        this.field_3_rcBounds_y1 = LittleEndian.getInt(data, pos4);
        int pos5 = pos4 + 4;
        this.field_3_rcBounds_x2 = LittleEndian.getInt(data, pos5);
        int pos6 = pos5 + 4;
        this.field_3_rcBounds_y2 = LittleEndian.getInt(data, pos6);
        int pos7 = pos6 + 4;
        this.field_4_ptSize_w = LittleEndian.getInt(data, pos7);
        int pos8 = pos7 + 4;
        this.field_4_ptSize_h = LittleEndian.getInt(data, pos8);
        int pos9 = pos8 + 4;
        this.field_5_cbSave = LittleEndian.getInt(data, pos9);
        int pos10 = pos9 + 4;
        this.field_6_fCompression = data[pos10];
        int pos11 = pos10 + 1;
        this.field_7_fFilter = data[pos11];
        int pos12 = pos11 + 1;
        this.raw_pictureData = IOUtils.safelyClone(data, pos12, this.field_5_cbSave, MAX_RECORD_LENGTH);
        int pos13 = pos12 + this.field_5_cbSave;
        if (this.field_6_fCompression == 0) {
            super.setPictureData(inflatePictureData(this.raw_pictureData));
        } else {
            super.setPictureData(this.raw_pictureData);
        }
        int remaining = (bytesAfterHeader - pos13) + offset + 8;
        if (remaining > 0) {
            this.remainingData = IOUtils.safelyClone(data, pos13, remaining, MAX_RECORD_LENGTH);
        }
        return bytesAfterHeader + 8;
    }

    @Override // org.apache.poi.ddf.EscherBlipRecord, org.apache.poi.ddf.EscherRecord
    public int serialize(int offset, byte[] data, EscherSerializationListener listener) {
        listener.beforeRecordSerialize(offset, getRecordId(), this);
        LittleEndian.putShort(data, offset, getOptions());
        int pos = offset + 2;
        LittleEndian.putShort(data, pos, getRecordId());
        int pos2 = pos + 2;
        LittleEndian.putInt(data, pos2, getRecordSize() - 8);
        int pos3 = pos2 + 4;
        System.arraycopy(this.field_1_UID, 0, data, pos3, this.field_1_UID.length);
        int pos4 = pos3 + this.field_1_UID.length;
        if ((getOptions() ^ getSignature()) == 16) {
            System.arraycopy(this.field_2_UID, 0, data, pos4, this.field_2_UID.length);
            pos4 += this.field_2_UID.length;
        }
        LittleEndian.putInt(data, pos4, this.field_2_cb);
        int pos5 = pos4 + 4;
        LittleEndian.putInt(data, pos5, this.field_3_rcBounds_x1);
        int pos6 = pos5 + 4;
        LittleEndian.putInt(data, pos6, this.field_3_rcBounds_y1);
        int pos7 = pos6 + 4;
        LittleEndian.putInt(data, pos7, this.field_3_rcBounds_x2);
        int pos8 = pos7 + 4;
        LittleEndian.putInt(data, pos8, this.field_3_rcBounds_y2);
        int pos9 = pos8 + 4;
        LittleEndian.putInt(data, pos9, this.field_4_ptSize_w);
        int pos10 = pos9 + 4;
        LittleEndian.putInt(data, pos10, this.field_4_ptSize_h);
        int pos11 = pos10 + 4;
        LittleEndian.putInt(data, pos11, this.field_5_cbSave);
        int pos12 = pos11 + 4;
        data[pos12] = this.field_6_fCompression;
        int pos13 = pos12 + 1;
        data[pos13] = this.field_7_fFilter;
        int pos14 = pos13 + 1;
        System.arraycopy(this.raw_pictureData, 0, data, pos14, this.raw_pictureData.length);
        int pos15 = pos14 + this.raw_pictureData.length;
        if (this.remainingData != null) {
            System.arraycopy(this.remainingData, 0, data, pos15, this.remainingData.length);
        }
        listener.afterRecordSerialize(getRecordSize() + offset, getRecordId(), getRecordSize(), this);
        return getRecordSize();
    }

    private static byte[] inflatePictureData(byte[] data) {
        try {
            InflaterInputStream in = new InflaterInputStream(UnsynchronizedByteArrayInputStream.builder().setByteArray(data).get());
            try {
                UnsynchronizedByteArrayOutputStream out = UnsynchronizedByteArrayOutputStream.builder().get();
                try {
                    IOUtils.copy(in, out);
                    byte[] byteArray = out.toByteArray();
                    if (out != null) {
                        out.close();
                    }
                    in.close();
                    return byteArray;
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            LOGGER.atWarn().withThrowable(e).log("Possibly corrupt compression or non-compressed data");
            return data;
        }
    }

    @Override // org.apache.poi.ddf.EscherBlipRecord, org.apache.poi.ddf.EscherRecord
    public int getRecordSize() {
        int size = this.raw_pictureData.length + 58;
        if (this.remainingData != null) {
            size += this.remainingData.length;
        }
        if ((getOptions() ^ getSignature()) == 16) {
            return size + this.field_2_UID.length;
        }
        return size;
    }

    public byte[] getUID() {
        return this.field_1_UID;
    }

    public void setUID(byte[] uid) {
        if (uid == null || uid.length != 16) {
            throw new IllegalArgumentException("uid must be byte[16]");
        }
        System.arraycopy(uid, 0, this.field_1_UID, 0, this.field_1_UID.length);
    }

    public byte[] getPrimaryUID() {
        return this.field_2_UID;
    }

    public void setPrimaryUID(byte[] primaryUID) {
        if (primaryUID == null || primaryUID.length != 16) {
            throw new IllegalArgumentException("primaryUID must be byte[16]");
        }
        System.arraycopy(primaryUID, 0, this.field_2_UID, 0, this.field_2_UID.length);
    }

    public int getUncompressedSize() {
        return this.field_2_cb;
    }

    public void setUncompressedSize(int uncompressedSize) {
        this.field_2_cb = uncompressedSize;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.field_3_rcBounds_x1, this.field_3_rcBounds_y1, this.field_3_rcBounds_x2 - this.field_3_rcBounds_x1, this.field_3_rcBounds_y2 - this.field_3_rcBounds_y1);
    }

    public void setBounds(Rectangle bounds) {
        this.field_3_rcBounds_x1 = bounds.x;
        this.field_3_rcBounds_y1 = bounds.y;
        this.field_3_rcBounds_x2 = bounds.x + bounds.width;
        this.field_3_rcBounds_y2 = bounds.y + bounds.height;
    }

    public Dimension getSizeEMU() {
        return new Dimension(this.field_4_ptSize_w, this.field_4_ptSize_h);
    }

    public void setSizeEMU(Dimension sizeEMU) {
        this.field_4_ptSize_w = sizeEMU.width;
        this.field_4_ptSize_h = sizeEMU.height;
    }

    public int getCompressedSize() {
        return this.field_5_cbSave;
    }

    public void setCompressedSize(int compressedSize) {
        this.field_5_cbSave = compressedSize;
    }

    public boolean isCompressed() {
        return this.field_6_fCompression == 0;
    }

    public void setCompressed(boolean compressed) {
        this.field_6_fCompression = compressed ? (byte) 0 : (byte) -2;
    }

    public byte getFilter() {
        return this.field_7_fFilter;
    }

    public void setFilter(byte filter) {
        this.field_7_fFilter = filter;
    }

    public byte[] getRemainingData() {
        return this.remainingData;
    }

    public short getSignature() {
        switch (EscherRecordTypes.forTypeID(getRecordId())) {
            case BLIP_EMF:
                return HSSFPictureData.MSOBI_EMF;
            case BLIP_WMF:
                return HSSFPictureData.MSOBI_WMF;
            case BLIP_PICT:
                return HSSFPictureData.MSOBI_PICT;
            default:
                LOGGER.atWarn().log("Unknown metafile: {}", Unbox.box(getRecordId()));
                return (short) 0;
        }
    }

    @Override // org.apache.poi.ddf.EscherBlipRecord
    public void setPictureData(byte[] pictureData) {
        super.setPictureData(pictureData);
        setUncompressedSize(pictureData.length);
        try {
            UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().get();
            try {
                DeflaterOutputStream dos = new DeflaterOutputStream(bos);
                try {
                    dos.write(pictureData);
                    dos.close();
                    this.raw_pictureData = bos.toByteArray();
                    if (bos != null) {
                        bos.close();
                    }
                    setCompressedSize(this.raw_pictureData.length);
                    setCompressed(true);
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            throw new IllegalStateException("Can't compress metafile picture data", e);
        }
    }

    @Override // org.apache.poi.ddf.EscherBlipRecord, org.apache.poi.ddf.EscherRecord, org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        Map<String, Supplier<?>> m = new LinkedHashMap<>(super.getGenericProperties());
        m.put("uid", new Supplier() { // from class: org.apache.poi.ddf.EscherMetafileBlip$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherMetafileBlip.this.getUID();
            }
        });
        m.put("uncompressedSize", new Supplier() { // from class: org.apache.poi.ddf.EscherMetafileBlip$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherMetafileBlip.this.getUncompressedSize());
            }
        });
        m.put("bounds", new Supplier() { // from class: org.apache.poi.ddf.EscherMetafileBlip$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherMetafileBlip.this.getBounds();
            }
        });
        m.put("sizeInEMU", new Supplier() { // from class: org.apache.poi.ddf.EscherMetafileBlip$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return EscherMetafileBlip.this.getSizeEMU();
            }
        });
        m.put("compressedSize", new Supplier() { // from class: org.apache.poi.ddf.EscherMetafileBlip$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(EscherMetafileBlip.this.getCompressedSize());
            }
        });
        m.put("isCompressed", new Supplier() { // from class: org.apache.poi.ddf.EscherMetafileBlip$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(EscherMetafileBlip.this.isCompressed());
            }
        });
        m.put("filter", new Supplier() { // from class: org.apache.poi.ddf.EscherMetafileBlip$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return Byte.valueOf(EscherMetafileBlip.this.getFilter());
            }
        });
        return Collections.unmodifiableMap(m);
    }

    @Override // org.apache.poi.ddf.EscherBlipRecord, org.apache.poi.ddf.EscherRecord, org.apache.poi.common.Duplicatable
    public EscherMetafileBlip copy() {
        return new EscherMetafileBlip(this);
    }
}
