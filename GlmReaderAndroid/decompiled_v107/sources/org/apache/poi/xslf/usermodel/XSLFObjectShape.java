package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.Ole10Native;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.sl.usermodel.ObjectMetaData;
import org.apache.poi.sl.usermodel.ObjectShape;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrameNonVisual;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPicture;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPictureNonVisual;

/* loaded from: classes10.dex */
public class XSLFObjectShape extends XSLFGraphicFrame implements ObjectShape<XSLFShape, XSLFTextParagraph> {
    static final String OLE_URI = "http://schemas.openxmlformats.org/presentationml/2006/ole";
    private XSLFPictureData _data;
    private final CTOleObject _oleObject;
    private static final QName[] GRAPHIC = {new QName(XSSFRelation.NS_DRAWINGML, "graphic")};
    private static final QName[] GRAPHIC_DATA = {new QName(XSSFRelation.NS_DRAWINGML, "graphicData")};
    private static final QName[] OLE_OBJ = {new QName(XSSFRelation.NS_PRESENTATIONML, "oleObj")};
    private static final QName[] CT_PICTURE = {new QName(XSSFRelation.NS_PRESENTATIONML, "pic")};

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFObjectShape(CTGraphicalObjectFrame shape, XSLFSheet sheet) {
        super(shape, sheet);
        try {
            this._oleObject = (CTOleObject) XPathHelper.selectProperty(getXmlObject(), CTOleObject.class, null, GRAPHIC, GRAPHIC_DATA, OLE_OBJ);
        } catch (XmlException e) {
            throw new IllegalStateException(e);
        }
    }

    @Internal
    public CTOleObject getCTOleObject() {
        return this._oleObject;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    @Override // org.apache.poi.sl.usermodel.ObjectShape
    public XSLFObjectData getObjectData() {
        String oleRel = getCTOleObject().getId();
        return (XSLFObjectData) getSheet().getRelationPartById(oleRel).getDocumentPart();
    }

    @Override // org.apache.poi.sl.usermodel.ObjectShape
    public String getProgId() {
        if (this._oleObject == null) {
            return null;
        }
        return this._oleObject.getProgId();
    }

    @Override // org.apache.poi.sl.usermodel.ObjectShape
    public String getFullName() {
        if (this._oleObject == null) {
            return null;
        }
        return this._oleObject.getName();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    @Override // org.apache.poi.sl.usermodel.ObjectShape
    public XSLFPictureData getPictureData() {
        if (this._data == null) {
            String blipId = getBlipId();
            if (blipId == null) {
                return null;
            }
            PackagePart p = getSheet().getPackagePart();
            PackageRelationship rel = p.getRelationship(blipId);
            if (rel != null) {
                try {
                    PackagePart imgPart = p.getRelatedPart(rel);
                    this._data = new XSLFPictureData(imgPart);
                } catch (Exception e) {
                    throw new POIXMLException(e);
                }
            }
        }
        return this._data;
    }

    protected CTBlip getBlip() {
        return getBlipFill().getBlip();
    }

    protected String getBlipId() {
        String id = getBlip().getEmbed();
        if (id.isEmpty()) {
            return null;
        }
        return id;
    }

    protected CTBlipFillProperties getBlipFill() {
        try {
            CTPicture pic = (CTPicture) XPathHelper.selectProperty(getXmlObject(), CTPicture.class, new XSLFShape.ReparseFactory() { // from class: org.apache.poi.xslf.usermodel.XSLFObjectShape$$ExternalSyntheticLambda0
                @Override // org.apache.poi.xslf.usermodel.XSLFShape.ReparseFactory
                public final XmlObject parse(XMLStreamReader xMLStreamReader) {
                    CTPicture parse;
                    parse = XSLFObjectShape.parse(xMLStreamReader);
                    return parse;
                }
            }, GRAPHIC, GRAPHIC_DATA, OLE_OBJ, CT_PICTURE);
            if (pic != null) {
                return pic.getBlipFill();
            }
            return null;
        } catch (XmlException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CTPicture parse(XMLStreamReader reader) throws XmlException {
        CTGroupShape gs = CTGroupShape.Factory.parse(reader);
        if (gs.sizeOfPicArray() > 0) {
            return gs.getPicArray(0);
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    @Override // org.apache.poi.sl.usermodel.ObjectShape
    public OutputStream updateObjectData(ObjectMetaData.Application application, ObjectMetaData metaData) throws IOException {
        final POIXMLDocumentPart.RelationPart rp;
        final ObjectMetaData md = application != null ? application.getMetaData() : metaData;
        if (md == null || md.getClassID() == null) {
            throw new IllegalArgumentException("either application and/or metaData needs to be set.");
        }
        ?? sheet = getSheet();
        if (this._oleObject.isSetId()) {
            rp = sheet.getRelationPartById(this._oleObject.getId());
        } else {
            try {
                XSLFRelation descriptor = XSLFRelation.OLE_OBJECT;
                OPCPackage pack = sheet.getPackagePart().getPackage();
                int nextIdx = pack.getUnusedPartIndex(descriptor.getDefaultFileName());
                POIXMLDocumentPart.RelationPart rp2 = sheet.createRelationship(descriptor, XSLFFactory.getInstance(), nextIdx, false);
                this._oleObject.setId(rp2.getRelationship().getId());
                rp = rp2;
            } catch (InvalidFormatException e) {
                throw new IOException("Unable to add new ole embedding", e);
            }
        }
        this._oleObject.setProgId(md.getProgId());
        this._oleObject.setName(md.getObjectName());
        return new ByteArrayOutputStream() { // from class: org.apache.poi.xslf.usermodel.XSLFObjectShape.1
            @Override // org.apache.commons.io.output.AbstractByteArrayOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                XSLFObjectShape.this.addUpdatedData(rp.getDocumentPart().getPackagePart(), md, this);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addUpdatedData(PackagePart objectPart, ObjectMetaData metaData, ByteArrayOutputStream baos) throws IOException {
        POIFSFileSystem poifs;
        objectPart.clear();
        InputStream bis = FileMagic.prepareToCheckMagic(baos.toInputStream());
        try {
            OutputStream os = objectPart.getOutputStream();
            try {
                FileMagic fm = FileMagic.valueOf(bis);
                if (fm == FileMagic.OLE2) {
                    poifs = new POIFSFileSystem(bis);
                    try {
                        poifs.getRoot().setStorageClsid(metaData.getClassID());
                        poifs.writeFilesystem(os);
                        poifs.close();
                    } finally {
                    }
                } else if (metaData.getOleEntry() == null) {
                    baos.writeTo(os);
                } else {
                    poifs = new POIFSFileSystem();
                    try {
                        ClassID clsId = metaData.getClassID();
                        if (clsId != null) {
                            poifs.getRoot().setStorageClsid(clsId);
                        }
                        poifs.createDocument(bis, metaData.getOleEntry());
                        Ole10Native.createOleMarkerEntry(poifs);
                        poifs.writeFilesystem(os);
                        poifs.close();
                    } finally {
                    }
                }
                if (os != null) {
                    os.close();
                }
                if (bis != null) {
                    bis.close();
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CTGraphicalObjectFrame prototype(int shapeId, String picRel) {
        Throwable th;
        CTGraphicalObjectFrame frame = CTGraphicalObjectFrame.Factory.newInstance();
        CTGraphicalObjectFrameNonVisual nvGr = frame.addNewNvGraphicFramePr();
        CTNonVisualDrawingProps cnv = nvGr.addNewCNvPr();
        cnv.setName("Object " + shapeId);
        cnv.setId(shapeId);
        nvGr.addNewCNvGraphicFramePr();
        nvGr.addNewNvPr();
        frame.addNewXfrm();
        CTGraphicalObjectData gr = frame.addNewGraphic().addNewGraphicData();
        gr.setUri(OLE_URI);
        XmlCursor grCur = gr.newCursor();
        try {
            grCur.toEndToken();
            grCur.beginElement(new QName(XSSFRelation.NS_PRESENTATIONML, "oleObj"));
            grCur.insertElement(new QName(XSSFRelation.NS_PRESENTATIONML, "embed"));
            CTGroupShape grpShp = CTGroupShape.Factory.newInstance();
            CTPicture pic = grpShp.addNewPic();
            CTPictureNonVisual nvPicPr = pic.addNewNvPicPr();
            CTNonVisualDrawingProps cNvPr = nvPicPr.addNewCNvPr();
            cNvPr.setName("");
            cNvPr.setId(0L);
            nvPicPr.addNewCNvPicPr();
            nvPicPr.addNewNvPr();
            CTBlipFillProperties blip = pic.addNewBlipFill();
            try {
                blip.addNewBlip().setEmbed(picRel);
                blip.addNewStretch().addNewFillRect();
                CTShapeProperties spPr = pic.addNewSpPr();
                CTTransform2D xfrm = spPr.addNewXfrm();
                CTPoint2D off = xfrm.addNewOff();
                off.setX(1270000);
                off.setY(1270000);
                CTPositiveSize2D xext = xfrm.addNewExt();
                try {
                    xext.setCx(1270000L);
                    xext.setCy(1270000L);
                    spPr.addNewPrstGeom().setPrst(STShapeType.RECT);
                    XmlCursor picCur = grpShp.newCursor();
                    try {
                        picCur.toStartDoc();
                        picCur.moveXmlContents(grCur);
                        if (picCur != null) {
                            picCur.close();
                        }
                        if (grCur != null) {
                            grCur.close();
                        }
                        return frame;
                    } finally {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        throw th;
                    } finally {
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                th = th;
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }
}
