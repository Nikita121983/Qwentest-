package org.apache.poi.common.usermodel;

import java.util.HashMap;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.poifs.filesystem.FileMagic;

/* loaded from: classes10.dex */
public enum PictureType {
    EMF("image/x-emf", ".emf", 2),
    WMF("image/x-wmf", ".wmf", 3),
    PICT(ContentTypes.IMAGE_PICT, ".pict", 4),
    JPEG(ContentTypes.IMAGE_JPEG, ".jpg", 5),
    PNG(ContentTypes.IMAGE_PNG, ".png", 6),
    DIB("image/dib", ".dib", 7),
    GIF(ContentTypes.IMAGE_GIF, ".gif", 8),
    TIFF(ContentTypes.IMAGE_TIFF, ".tif", 9),
    EPS("image/x-eps", ".eps", 10),
    BMP("image/x-ms-bmp", ".bmp", 11),
    WPG("image/x-wpg", ".wpg", 12),
    WDP("image/vnd.ms-photo", ".wdp", 13),
    SVG("image/svg+xml", ".svg", 14),
    UNKNOWN("", ".dat", -1),
    ERROR("", ".dat", -1),
    CMYKJPEG(ContentTypes.IMAGE_JPEG, ".jpg", -1),
    CLIENT("", ".dat", -1);

    private static final HashMap<Integer, PictureType> PICTURE_TYPE_BY_OOXML_ID = new HashMap<>();
    public final String contentType;
    public final String extension;
    public final int ooxmlId;

    static {
        for (PictureType pictureType : values()) {
            if (pictureType.ooxmlId >= -1) {
                PICTURE_TYPE_BY_OOXML_ID.put(Integer.valueOf(pictureType.ooxmlId), pictureType);
            }
        }
    }

    PictureType(String contentType, String extension, int ooxmlId) {
        this.contentType = contentType;
        this.extension = extension;
        this.ooxmlId = ooxmlId;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getExtension() {
        return this.extension;
    }

    public int getOoxmlId() {
        return this.ooxmlId;
    }

    public static PictureType valueOf(FileMagic fm) {
        switch (fm) {
            case BMP:
                return BMP;
            case GIF:
                return GIF;
            case JPEG:
                return JPEG;
            case PNG:
                return PNG;
            case XML:
                return SVG;
            case WMF:
                return WMF;
            case EMF:
                return EMF;
            case TIFF:
                return TIFF;
            default:
                return UNKNOWN;
        }
    }

    public static PictureType findByOoxmlId(int ooxmlId) {
        return PICTURE_TYPE_BY_OOXML_ID.get(Integer.valueOf(ooxmlId));
    }
}
