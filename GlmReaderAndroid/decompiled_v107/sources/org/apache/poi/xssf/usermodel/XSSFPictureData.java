package org.apache.poi.xssf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.util.IOUtils;

/* loaded from: classes10.dex */
public class XSSFPictureData extends POIXMLDocumentPart implements PictureData {
    private static final int DEFAULT_MAX_IMAGE_SIZE = 100000000;
    private static int MAX_IMAGE_SIZE = DEFAULT_MAX_IMAGE_SIZE;
    protected static final POIXMLRelation[] RELATIONS = new POIXMLRelation[13];

    static {
        RELATIONS[2] = XSSFRelation.IMAGE_EMF;
        RELATIONS[3] = XSSFRelation.IMAGE_WMF;
        RELATIONS[4] = XSSFRelation.IMAGE_PICT;
        RELATIONS[5] = XSSFRelation.IMAGE_JPEG;
        RELATIONS[6] = XSSFRelation.IMAGE_PNG;
        RELATIONS[7] = XSSFRelation.IMAGE_DIB;
        RELATIONS[8] = XSSFRelation.IMAGE_GIF;
        RELATIONS[9] = XSSFRelation.IMAGE_TIFF;
        RELATIONS[10] = XSSFRelation.IMAGE_EPS;
        RELATIONS[11] = XSSFRelation.IMAGE_BMP;
        RELATIONS[12] = XSSFRelation.IMAGE_WPG;
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
    public XSSFPictureData() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFPictureData(PackagePart part) {
        super(part);
    }

    @Override // org.apache.poi.ss.usermodel.PictureData
    public byte[] getData() {
        try {
            InputStream inputStream = getPackagePart().getInputStream();
            try {
                byte[] byteArrayWithMaxLength = IOUtils.toByteArrayWithMaxLength(inputStream, getMaxImageSize());
                if (inputStream != null) {
                    inputStream.close();
                }
                return byteArrayWithMaxLength;
            } finally {
            }
        } catch (IOException e) {
            throw new POIXMLException(e);
        }
    }

    @Override // org.apache.poi.ss.usermodel.PictureData
    public String suggestFileExtension() {
        return getPackagePart().getPartName().getExtension();
    }

    @Override // org.apache.poi.ss.usermodel.PictureData
    public int getPictureType() {
        String contentType = getPackagePart().getContentType();
        for (int i = 0; i < RELATIONS.length; i++) {
            if (RELATIONS[i] != null && RELATIONS[i].getContentType().equals(contentType)) {
                return i;
            }
        }
        return 0;
    }

    @Override // org.apache.poi.ss.usermodel.PictureData
    public String getMimeType() {
        return getPackagePart().getContentType();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public void prepareForCommit() {
    }
}
