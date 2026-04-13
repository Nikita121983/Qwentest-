package org.apache.poi.hssf.usermodel;

import org.apache.poi.ddf.EscherBlipRecord;
import org.apache.poi.ddf.EscherRecordTypes;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.sl.image.ImageHeaderPNG;
import org.apache.poi.ss.usermodel.PictureData;

/* loaded from: classes10.dex */
public class HSSFPictureData implements PictureData {
    public static final short FORMAT_MASK = -16;
    public static final short MSOBI_DIB = 31360;
    public static final short MSOBI_EMF = 15680;
    public static final short MSOBI_JPEG = 18080;
    public static final short MSOBI_PICT = 21536;
    public static final short MSOBI_PNG = 28160;
    public static final short MSOBI_WMF = 8544;
    private final EscherBlipRecord blip;

    public HSSFPictureData(EscherBlipRecord blip) {
        this.blip = blip;
    }

    @Override // org.apache.poi.ss.usermodel.PictureData
    public byte[] getData() {
        return new ImageHeaderPNG(this.blip.getPicturedata()).extractPNG();
    }

    public int getFormat() {
        return this.blip.getRecordId() - EscherRecordTypes.BLIP_START.typeID;
    }

    @Override // org.apache.poi.ss.usermodel.PictureData
    public String suggestFileExtension() {
        switch (EscherRecordTypes.forTypeID(this.blip.getRecordId())) {
            case BLIP_WMF:
                return "wmf";
            case BLIP_EMF:
                return "emf";
            case BLIP_PICT:
                return ContentTypes.EXTENSION_PICT;
            case BLIP_PNG:
                return ContentTypes.EXTENSION_PNG;
            case BLIP_JPEG:
                return ContentTypes.EXTENSION_JPG_2;
            case BLIP_DIB:
                return "dib";
            case BLIP_TIFF:
                return "tif";
            default:
                return "";
        }
    }

    @Override // org.apache.poi.ss.usermodel.PictureData
    public String getMimeType() {
        switch (EscherRecordTypes.forTypeID(this.blip.getRecordId())) {
            case BLIP_WMF:
                return "image/x-wmf";
            case BLIP_EMF:
                return "image/x-emf";
            case BLIP_PICT:
                return ContentTypes.IMAGE_PICT;
            case BLIP_PNG:
                return ContentTypes.IMAGE_PNG;
            case BLIP_JPEG:
                return ContentTypes.IMAGE_JPEG;
            case BLIP_DIB:
                return "image/bmp";
            case BLIP_TIFF:
                return ContentTypes.IMAGE_TIFF;
            default:
                return "image/unknown";
        }
    }

    @Override // org.apache.poi.ss.usermodel.PictureData
    public int getPictureType() {
        switch (EscherRecordTypes.forTypeID(this.blip.getRecordId())) {
            case BLIP_WMF:
                return 3;
            case BLIP_EMF:
                return 2;
            case BLIP_PICT:
                return 4;
            case BLIP_PNG:
                return 6;
            case BLIP_JPEG:
                return 5;
            case BLIP_DIB:
                return 7;
            default:
                return 0;
        }
    }
}
