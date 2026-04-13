package org.apache.poi.xslf.usermodel;

import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.image.ImageHeaderBitmap;
import org.apache.poi.sl.image.ImageHeaderEMF;
import org.apache.poi.sl.image.ImageHeaderPICT;
import org.apache.poi.sl.image.ImageHeaderWMF;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.Units;

/* loaded from: classes10.dex */
public final class XSLFPictureData extends POIXMLDocumentPart implements PictureData {
    private static final int DEFAULT_MAX_IMAGE_SIZE = 100000000;
    private static int MAX_IMAGE_SIZE = DEFAULT_MAX_IMAGE_SIZE;
    private Long checksum;
    private int index;
    private Dimension origSize;

    public static void setMaxImageSize(int length) {
        MAX_IMAGE_SIZE = length;
    }

    public static int getMaxImageSize() {
        int ioMaxSize = IOUtils.getByteArrayMaxOverride();
        int i = MAX_IMAGE_SIZE;
        return ioMaxSize < 0 ? i : Math.min(i, ioMaxSize);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XSLFPictureData() {
        this.index = -1;
    }

    public XSLFPictureData(PackagePart part) {
        super(part);
        this.index = -1;
    }

    public InputStream getInputStream() throws IOException {
        return getPackagePart().getInputStream();
    }

    @Override // org.apache.poi.sl.usermodel.PictureData
    public byte[] getData() {
        try {
            InputStream stream = getInputStream();
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

    @Override // org.apache.poi.sl.usermodel.PictureData
    public byte[] getChecksum() {
        cacheProperties();
        byte[] cs = new byte[8];
        LittleEndian.putLong(cs, 0, this.checksum.longValue());
        return cs;
    }

    @Override // org.apache.poi.sl.usermodel.PictureData
    public Dimension getImageDimension() {
        cacheProperties();
        return this.origSize;
    }

    @Override // org.apache.poi.sl.usermodel.PictureData
    public Dimension getImageDimensionInPixels() {
        Dimension dim = getImageDimension();
        return new Dimension(Units.pointsToPixel(dim.getWidth()), Units.pointsToPixel(dim.getHeight()));
    }

    protected void cacheProperties() {
        if (this.origSize == null || this.checksum == null) {
            byte[] data = getData();
            this.checksum = Long.valueOf(IOUtils.calculateChecksum(data));
            PictureData.PictureType pt = getType();
            if (pt == null) {
                this.origSize = new Dimension(1, 1);
                return;
            }
            switch (pt) {
                case EMF:
                    this.origSize = new ImageHeaderEMF(data, 0).getSize();
                    return;
                case WMF:
                    this.origSize = new ImageHeaderWMF(data, 0).getSize();
                    return;
                case PICT:
                    this.origSize = new ImageHeaderPICT(data, 0).getSize();
                    return;
                default:
                    this.origSize = new ImageHeaderBitmap(data, 0).getSize();
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public void prepareForCommit() {
    }

    @Override // org.apache.poi.sl.usermodel.PictureData
    public String getContentType() {
        return getPackagePart().getContentType();
    }

    @Override // org.apache.poi.sl.usermodel.PictureData
    public void setData(byte[] data) throws IOException {
        OutputStream os = getPackagePart().getOutputStream();
        try {
            os.write(data);
            if (os != null) {
                os.close();
            }
            this.checksum = Long.valueOf(IOUtils.calculateChecksum(data));
            this.origSize = null;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (os != null) {
                    try {
                        os.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.poi.sl.usermodel.PictureData
    public PictureData.PictureType getType() {
        String ct = getContentType();
        if (XSLFRelation.IMAGE_EMF.getContentType().equals(ct)) {
            return PictureData.PictureType.EMF;
        }
        if (XSLFRelation.IMAGE_WMF.getContentType().equals(ct)) {
            return PictureData.PictureType.WMF;
        }
        if (XSLFRelation.IMAGE_PICT.getContentType().equals(ct)) {
            return PictureData.PictureType.PICT;
        }
        if (XSLFRelation.IMAGE_JPEG.getContentType().equals(ct)) {
            return PictureData.PictureType.JPEG;
        }
        if (XSLFRelation.IMAGE_PNG.getContentType().equals(ct)) {
            return PictureData.PictureType.PNG;
        }
        if (XSLFRelation.IMAGE_DIB.getContentType().equals(ct)) {
            return PictureData.PictureType.DIB;
        }
        if (XSLFRelation.IMAGE_GIF.getContentType().equals(ct)) {
            return PictureData.PictureType.GIF;
        }
        if (XSLFRelation.IMAGE_EPS.getContentType().equals(ct)) {
            return PictureData.PictureType.EPS;
        }
        if (XSLFRelation.IMAGE_BMP.getContentType().equals(ct)) {
            return PictureData.PictureType.BMP;
        }
        if (XSLFRelation.IMAGE_WPG.getContentType().equals(ct)) {
            return PictureData.PictureType.WPG;
        }
        if (XSLFRelation.IMAGE_WDP.getContentType().equals(ct)) {
            return PictureData.PictureType.WDP;
        }
        if (XSLFRelation.IMAGE_TIFF.getContentType().equals(ct)) {
            return PictureData.PictureType.TIFF;
        }
        if (XSLFRelation.IMAGE_SVG.getContentType().equals(ct)) {
            return PictureData.PictureType.SVG;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static XSLFRelation getRelationForType(PictureData.PictureType pt) {
        switch (pt) {
            case EMF:
                return XSLFRelation.IMAGE_EMF;
            case WMF:
                return XSLFRelation.IMAGE_WMF;
            case PICT:
                return XSLFRelation.IMAGE_PICT;
            case JPEG:
                return XSLFRelation.IMAGE_JPEG;
            case PNG:
                return XSLFRelation.IMAGE_PNG;
            case DIB:
                return XSLFRelation.IMAGE_DIB;
            case GIF:
                return XSLFRelation.IMAGE_GIF;
            case EPS:
                return XSLFRelation.IMAGE_EPS;
            case BMP:
                return XSLFRelation.IMAGE_BMP;
            case WPG:
                return XSLFRelation.IMAGE_WPG;
            case WDP:
                return XSLFRelation.HDPHOTO_WDP;
            case TIFF:
                return XSLFRelation.IMAGE_TIFF;
            case SVG:
                return XSLFRelation.IMAGE_SVG;
            default:
                return null;
        }
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
