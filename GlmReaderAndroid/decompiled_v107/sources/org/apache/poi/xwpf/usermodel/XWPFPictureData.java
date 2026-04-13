package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Removal;

/* loaded from: classes10.dex */
public class XWPFPictureData extends POIXMLDocumentPart {
    private static final int DEFAULT_MAX_IMAGE_SIZE = 100000000;
    private static int MAX_IMAGE_SIZE = DEFAULT_MAX_IMAGE_SIZE;
    protected static final POIXMLRelation[] RELATIONS = new POIXMLRelation[15];
    private Long checksum;

    static {
        RELATIONS[PictureType.EMF.ooxmlId] = XWPFRelation.IMAGE_EMF;
        RELATIONS[PictureType.WMF.ooxmlId] = XWPFRelation.IMAGE_WMF;
        RELATIONS[PictureType.PICT.ooxmlId] = XWPFRelation.IMAGE_PICT;
        RELATIONS[PictureType.JPEG.ooxmlId] = XWPFRelation.IMAGE_JPEG;
        RELATIONS[PictureType.PNG.ooxmlId] = XWPFRelation.IMAGE_PNG;
        RELATIONS[PictureType.DIB.ooxmlId] = XWPFRelation.IMAGE_DIB;
        RELATIONS[PictureType.GIF.ooxmlId] = XWPFRelation.IMAGE_GIF;
        RELATIONS[PictureType.TIFF.ooxmlId] = XWPFRelation.IMAGE_TIFF;
        RELATIONS[PictureType.EPS.ooxmlId] = XWPFRelation.IMAGE_EPS;
        RELATIONS[PictureType.BMP.ooxmlId] = XWPFRelation.IMAGE_BMP;
        RELATIONS[PictureType.WPG.ooxmlId] = XWPFRelation.IMAGE_WPG;
        RELATIONS[PictureType.WDP.ooxmlId] = XWPFRelation.HDPHOTO_WDP;
        RELATIONS[PictureType.SVG.ooxmlId] = XWPFRelation.IMAGE_SVG;
    }

    public static void setMaxImageSize(int length) {
        MAX_IMAGE_SIZE = length;
    }

    public static int getMaxImageSize() {
        int ioMaxSize = IOUtils.getByteArrayMaxOverride();
        int i = MAX_IMAGE_SIZE;
        return ioMaxSize < 0 ? i : Math.min(i, ioMaxSize);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XWPFPictureData() {
    }

    public XWPFPictureData(PackagePart part) {
        super(part);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public void onDocumentRead() throws IOException {
        super.onDocumentRead();
    }

    public byte[] getData() {
        try {
            InputStream stream = getPackagePart().getInputStream();
            try {
                byte[] byteArrayWithMaxLength = IOUtils.toByteArrayWithMaxLength(stream, getMaxImageSize());
                if (stream != null) {
                    stream.close();
                }
                return byteArrayWithMaxLength;
            } finally {
            }
        } catch (IOException e) {
            throw new POIXMLException(e);
        }
    }

    public String getFileName() {
        String name = getPackagePart().getPartName().getName();
        return name.substring(name.lastIndexOf(47) + 1);
    }

    public String suggestFileExtension() {
        return getPackagePart().getPartName().getExtension();
    }

    @Removal(version = "7.0.0")
    @Deprecated
    public int getPictureType() {
        String contentType = getPackagePart().getContentType();
        for (int i = 0; i < RELATIONS.length; i++) {
            if (RELATIONS[i] != null && RELATIONS[i].getContentType().equals(contentType)) {
                return i;
            }
        }
        return 0;
    }

    public PictureType getPictureTypeEnum() {
        return PictureType.findByOoxmlId(getPictureType());
    }

    public Long getChecksum() {
        if (this.checksum == null) {
            try {
                InputStream is = getPackagePart().getInputStream();
                try {
                    this.checksum = Long.valueOf(IOUtils.calculateChecksum(is));
                    if (is != null) {
                        is.close();
                    }
                } finally {
                }
            } catch (IOException e) {
                throw new POIXMLException(e);
            }
        }
        return this.checksum;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof XWPFPictureData)) {
            return false;
        }
        XWPFPictureData picData = (XWPFPictureData) obj;
        PackagePart foreignPackagePart = picData.getPackagePart();
        PackagePart ownPackagePart = getPackagePart();
        if ((foreignPackagePart != null && ownPackagePart == null) || (foreignPackagePart == null && ownPackagePart != null)) {
            return false;
        }
        if (ownPackagePart != null) {
            OPCPackage foreignPackage = foreignPackagePart.getPackage();
            OPCPackage ownPackage = ownPackagePart.getPackage();
            if ((foreignPackage != null && ownPackage == null) || (foreignPackage == null && ownPackage != null)) {
                return false;
            }
            if (ownPackage != null && !ownPackage.equals(foreignPackage)) {
                return false;
            }
        }
        Long foreignChecksum = picData.getChecksum();
        Long localChecksum = getChecksum();
        if (localChecksum == null) {
            if (foreignChecksum != null) {
                return false;
            }
        } else if (!localChecksum.equals(foreignChecksum)) {
            return false;
        }
        return Arrays.equals(getData(), picData.getData());
    }

    public int hashCode() {
        Long checksum = getChecksum();
        return checksum == null ? super.hashCode() : checksum.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public void prepareForCommit() {
    }
}
