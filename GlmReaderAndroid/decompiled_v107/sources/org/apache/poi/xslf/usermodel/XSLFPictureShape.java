package org.apache.poi.xslf.usermodel;

import java.awt.Insets;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import javax.imageio.ImageIO;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.commons.compress.compressors.bzip2.BZip2Constants;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.PictureShape;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.draw.SVGImageRenderer;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtension;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.presentationml.x2006.main.CTApplicationNonVisualDrawingProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPicture;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPictureNonVisual;

/* loaded from: classes10.dex */
public class XSLFPictureShape extends XSLFSimpleShape implements PictureShape<XSLFShape, XSLFTextParagraph> {
    private static final String BITMAP_URI = "{28A0092B-C50C-407E-A947-70E740481C1C}";
    private static final String MS_DML_NS = "http://schemas.microsoft.com/office/drawing/2010/main";
    private static final String MS_SVG_NS = "http://schemas.microsoft.com/office/drawing/2016/SVG/main";
    private static final String SVG_URI = "{96DAC541-7B7A-43D3-8B79-37D633B846F1}";
    private XSLFPictureData _data;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XSLFPictureShape.class);
    private static final QName EMBED_TAG = new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "embed", "rel");
    private static final QName[] BLIP_FILL = {new QName(XSSFRelation.NS_PRESENTATIONML, "blipFill")};

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFPictureShape(CTPicture shape, XSLFSheet sheet) {
        super(shape, sheet);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CTPicture prototype(int shapeId, String rel) {
        CTPicture ct = CTPicture.Factory.newInstance();
        CTPictureNonVisual nvSpPr = ct.addNewNvPicPr();
        CTNonVisualDrawingProps cnv = nvSpPr.addNewCNvPr();
        cnv.setName("Picture " + shapeId);
        cnv.setId(shapeId);
        nvSpPr.addNewCNvPicPr().addNewPicLocks().setNoChangeAspect(true);
        nvSpPr.addNewNvPr();
        CTBlipFillProperties blipFill = ct.addNewBlipFill();
        CTBlip blip = blipFill.addNewBlip();
        blip.setEmbed(rel);
        blipFill.addNewStretch().addNewFillRect();
        CTShapeProperties spPr = ct.addNewSpPr();
        CTPresetGeometry2D prst = spPr.addNewPrstGeom();
        prst.setPrst(STShapeType.RECT);
        prst.addNewAvLst();
        return ct;
    }

    public boolean isExternalLinkedPicture() {
        return getBlipId() == null && getBlipLink() != null;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    @Override // org.apache.poi.sl.usermodel.PictureShape
    public XSLFPictureData getPictureData() {
        if (this._data == null) {
            String blipId = getBlipId();
            if (blipId == null) {
                return null;
            }
            this._data = (XSLFPictureData) getSheet().getRelationById(blipId);
        }
        return this._data;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFShape, org.apache.poi.sl.usermodel.SimpleShape
    public void setPlaceholder(Placeholder placeholder) {
        super.setPlaceholder(placeholder);
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    public URI getPictureLink() {
        String rId;
        if (getBlipId() != null || (rId = getBlipLink()) == null) {
            return null;
        }
        PackagePart p = getSheet().getPackagePart();
        PackageRelationship rel = p.getRelationship(rId);
        if (rel != null) {
            return rel.getTargetURI();
        }
        return null;
    }

    protected CTBlipFillProperties getBlipFill() {
        CTPicture ct = (CTPicture) getXmlObject();
        CTBlipFillProperties bfp = ct.getBlipFill();
        if (bfp != null) {
            return bfp;
        }
        try {
            return (CTBlipFillProperties) XPathHelper.selectProperty(getXmlObject(), CTBlipFillProperties.class, new XSLFShape.ReparseFactory() { // from class: org.apache.poi.xslf.usermodel.XSLFPictureShape$$ExternalSyntheticLambda0
                @Override // org.apache.poi.xslf.usermodel.XSLFShape.ReparseFactory
                public final XmlObject parse(XMLStreamReader xMLStreamReader) {
                    CTBlipFillProperties parse;
                    parse = XSLFPictureShape.parse(xMLStreamReader);
                    return parse;
                }
            }, BLIP_FILL);
        } catch (XmlException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CTBlipFillProperties parse(XMLStreamReader reader) throws XmlException {
        CTPicture pic = CTPicture.Factory.parse(reader);
        if (pic != null) {
            return pic.getBlipFill();
        }
        return null;
    }

    protected CTBlip getBlip() {
        return getBlipFill().getBlip();
    }

    protected String getBlipLink() {
        CTBlip blip = getBlip();
        if (blip == null) {
            return null;
        }
        String link = blip.getLink();
        if (link.isEmpty()) {
            return null;
        }
        return link;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getBlipId() {
        CTBlip blip = getBlip();
        if (blip == null) {
            return null;
        }
        String id = blip.getEmbed();
        if (id.isEmpty()) {
            return null;
        }
        return id;
    }

    @Override // org.apache.poi.sl.usermodel.PictureShape
    public Insets getClipping() {
        CTRelativeRect r = getBlipFill().getSrcRect();
        if (r == null) {
            return null;
        }
        return new Insets(POIXMLUnits.parsePercent(r.xgetT()), POIXMLUnits.parsePercent(r.xgetL()), POIXMLUnits.parsePercent(r.xgetB()), POIXMLUnits.parsePercent(r.xgetR()));
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    /* JADX WARN: Type inference failed for: r8v2, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    public void setSvgImage(XSLFPictureData svgPic) {
        XmlCursor cur;
        CTBlip blip = getBlip();
        CTOfficeArtExtensionList extLst = blip.isSetExtLst() ? blip.getExtLst() : blip.addNewExtLst();
        int bitmapId = getExt(extLst, BITMAP_URI);
        if (bitmapId == -1) {
            CTOfficeArtExtension extBitmap = extLst.addNewExt();
            extBitmap.setUri(BITMAP_URI);
            cur = extBitmap.newCursor();
            try {
                cur.toEndToken();
                cur.beginElement(new QName(MS_DML_NS, "useLocalDpi", "a14"));
                cur.insertNamespace("a14", MS_DML_NS);
                cur.insertAttributeWithValue("val", "0");
                if (cur != null) {
                    cur.close();
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } finally {
                }
            }
        }
        int svgId = getExt(extLst, SVG_URI);
        if (svgId != -1) {
            extLst.removeExt(svgId);
        }
        String svgRelId = getSheet().getRelationId(svgPic);
        if (svgRelId == null) {
            svgRelId = getSheet().addRelation(null, XSLFRelation.IMAGE_SVG, svgPic).getRelationship().getId();
        }
        CTOfficeArtExtension svgBitmap = extLst.addNewExt();
        svgBitmap.setUri(SVG_URI);
        cur = svgBitmap.newCursor();
        try {
            cur.toEndToken();
            cur.beginElement(new QName(MS_SVG_NS, "svgBlip", "asvg"));
            cur.insertNamespace("asvg", MS_SVG_NS);
            cur.insertAttributeWithValue(EMBED_TAG, svgRelId);
            if (cur != null) {
                cur.close();
            }
        } catch (Throwable th2) {
            try {
                throw th2;
            } finally {
            }
        }
    }

    @Override // org.apache.poi.sl.usermodel.PictureShape
    public PictureData getAlternativePictureData() {
        return getSvgImage();
    }

    public String getName() {
        CTNonVisualDrawingProps cnvdProps;
        CTPictureNonVisual nvPicPr = getCTPictureNonVisual();
        if (nvPicPr == null || (cnvdProps = nvPicPr.getCNvPr()) == null) {
            return null;
        }
        String name = cnvdProps.getName();
        return name;
    }

    public boolean setName(String name) {
        XmlObject xmlObject = getXmlObject();
        if (xmlObject instanceof CTPicture) {
            CTPicture ctPicture = (CTPicture) xmlObject;
            CTPictureNonVisual nvPicPr = ctPicture.getNvPicPr();
            if (nvPicPr == null) {
                nvPicPr = ctPicture.addNewNvPicPr();
            }
            if (nvPicPr != null) {
                CTNonVisualDrawingProps cnvdProps = nvPicPr.getCNvPr();
                if (cnvdProps == null) {
                    cnvdProps = nvPicPr.addNewCNvPr();
                }
                if (cnvdProps != null) {
                    cnvdProps.setName(name);
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    public XSLFPictureData getSvgImage() {
        CTOfficeArtExtensionList extLst;
        CTBlip blip = getBlip();
        if (blip == null || (extLst = blip.getExtLst()) == null) {
            return null;
        }
        int size = extLst.sizeOfExtArray();
        for (int i = 0; i < size; i++) {
            XmlCursor cur = extLst.getExtArray(i).newCursor();
            try {
                if (cur.toChild(MS_SVG_NS, "svgBlip")) {
                    String svgRelId = cur.getAttributeText(EMBED_TAG);
                    XSLFPictureData xSLFPictureData = svgRelId != null ? (XSLFPictureData) getSheet().getRelationById(svgRelId) : null;
                    if (cur != null) {
                        cur.close();
                    }
                    return xSLFPictureData;
                }
                if (cur != null) {
                    cur.close();
                }
            } finally {
            }
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r7v3, types: [org.apache.poi.xslf.usermodel.XSLFPictureShape] */
    public static XSLFPictureShape addSvgImage(XSLFSheet sheet, XSLFPictureData svgPic, PictureData.PictureType previewType, Rectangle2D anchor) throws IOException {
        PictureData.PictureType pt;
        if (svgPic == null || svgPic.getType() == null) {
            throw new IllegalArgumentException("Cannot process svgPic with null type");
        }
        SVGImageRenderer renderer = new SVGImageRenderer();
        InputStream is = svgPic.getInputStream();
        try {
            renderer.loadImage(is, svgPic.getType().contentType);
            if (is != null) {
                is.close();
            }
            Dimension2D dim = renderer.getDimension();
            Rectangle2D anc = anchor != null ? anchor : new Rectangle2D.Double(0.0d, 0.0d, Units.pixelToPoints((int) dim.getWidth()), Units.pixelToPoints((int) dim.getHeight()));
            PictureData.PictureType pt2 = previewType != null ? previewType : PictureData.PictureType.PNG;
            if (pt2 != PictureData.PictureType.JPEG && pt2 != PictureData.PictureType.GIF && pt2 != PictureData.PictureType.PNG) {
                pt = PictureData.PictureType.PNG;
            } else {
                pt = pt2;
            }
            BufferedImage thmBI = renderer.getImage(dim);
            UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().setBufferSize(BZip2Constants.BASEBLOCKSIZE).get();
            try {
                ImageIO.write(thmBI, pt.extension.substring(1), bos);
                XSLFPictureData pngPic = sheet.getSlideShow().addPicture(bos.toInputStream(), pt);
                ?? createPicture = sheet.createPicture((PictureData) pngPic);
                createPicture.setAnchor(anc);
                createPicture.setSvgImage(svgPic);
                if (bos != null) {
                    bos.close();
                }
                return createPicture;
            } finally {
            }
        } finally {
        }
    }

    private int getExt(CTOfficeArtExtensionList extLst, String uri) {
        int size = extLst.sizeOfExtArray();
        for (int i = 0; i < size; i++) {
            CTOfficeArtExtension ext = extLst.getExtArray(i);
            if (uri.equals(ext.getUri())) {
                return i;
            }
        }
        return -1;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    /* JADX WARN: Type inference failed for: r0v11, types: [org.apache.poi.xslf.usermodel.XSLFSheet, org.apache.poi.ooxml.POIXMLDocumentPart] */
    /* JADX WARN: Type inference failed for: r3v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet, org.apache.poi.ooxml.POIXMLDocumentPart] */
    /* JADX WARN: Type inference failed for: r9v4, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    @Override // org.apache.poi.xslf.usermodel.XSLFSimpleShape, org.apache.poi.xslf.usermodel.XSLFShape
    void copy(XSLFShape sh) {
        CTOfficeArtExtension[] cTOfficeArtExtensionArr;
        super.copy(sh);
        XSLFPictureShape p = (XSLFPictureShape) sh;
        String blipId = p.getBlipId();
        if (blipId == null) {
            LOG.atWarn().log("unable to copy invalid picture shape");
            return;
        }
        String relId = getSheet().importBlip(blipId, p.getSheet());
        CTBlip blip = getBlipFill().getBlip();
        blip.setEmbed(relId);
        CTPictureNonVisual nvPicPr = getCTPictureNonVisual();
        CTApplicationNonVisualDrawingProps nvPr = nvPicPr == null ? null : nvPicPr.getNvPr();
        if (nvPr != null && nvPr.isSetCustDataLst()) {
            nvPr.unsetCustDataLst();
        }
        if (blip.isSetExtLst()) {
            CTOfficeArtExtensionList extLst = blip.getExtLst();
            CTOfficeArtExtension[] extArray = extLst.getExtArray();
            int length = extArray.length;
            char c = 0;
            int i = 0;
            while (i < length) {
                CTOfficeArtExtension ext = extArray[i];
                XmlObject[] obj = ext.selectPath("declare namespace a14='http://schemas.microsoft.com/office/drawing/2010/main' $this//a14:imgProps/a14:imgLayer");
                if (obj == null || obj.length != 1) {
                    cTOfficeArtExtensionArr = extArray;
                } else {
                    XmlCursor c2 = obj[c].newCursor();
                    try {
                        String id = c2.getAttributeText(EMBED_TAG);
                        cTOfficeArtExtensionArr = extArray;
                        String newId = getSheet().importBlip(id, p.getSheet());
                        c2.setAttributeText(EMBED_TAG, newId);
                        if (c2 != null) {
                            c2.close();
                        }
                    } finally {
                    }
                }
                i++;
                extArray = cTOfficeArtExtensionArr;
                c = 0;
            }
        }
    }

    public boolean isVideoFile() {
        CTApplicationNonVisualDrawingProps nvPr;
        CTPictureNonVisual nvPicPr = getCTPictureNonVisual();
        if (nvPicPr != null && (nvPr = nvPicPr.getNvPr()) != null) {
            return nvPr.isSetVideoFile();
        }
        return false;
    }

    public String getVideoFileLink() {
        CTPictureNonVisual nvPicPr;
        CTApplicationNonVisualDrawingProps nvPr;
        if (isVideoFile() && (nvPicPr = getCTPictureNonVisual()) != null && (nvPr = nvPicPr.getNvPr()) != null && nvPr.getVideoFile() != null) {
            return nvPr.getVideoFile().getLink();
        }
        return null;
    }

    private CTApplicationNonVisualDrawingProps getCTApplicationNonVisualDrawing() {
        CTPictureNonVisual nvPicPr = getCTPictureNonVisual();
        if (nvPicPr == null) {
            return null;
        }
        return nvPicPr.getNvPr();
    }

    public boolean isAudioFile() {
        CTApplicationNonVisualDrawingProps nvPr = getCTApplicationNonVisualDrawing();
        return nvPr != null && nvPr.isSetAudioFile();
    }

    public String getAudioFileLink() {
        CTApplicationNonVisualDrawingProps nvPr = getCTApplicationNonVisualDrawing();
        if (nvPr == null || !nvPr.isSetAudioFile()) {
            return null;
        }
        return nvPr.getAudioFile().getLink();
    }

    private CTPictureNonVisual getCTPictureNonVisual() {
        XmlObject xmlObject = getXmlObject();
        if (xmlObject instanceof CTPicture) {
            CTPicture ctPicture = (CTPicture) xmlObject;
            return ctPicture.getNvPicPr();
        }
        return null;
    }
}
