package org.apache.poi.xssf.usermodel;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import javax.xml.namespace.QName;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.util.ImageUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.model.Comments;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnector;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTOneCellAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTTwoCellAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STEditAs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObject;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;

/* loaded from: classes10.dex */
public final class XSSFDrawing extends POIXMLDocumentPart implements Drawing<XSSFShape> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XSSFDrawing.class);
    protected static final String NAMESPACE_A = "http://schemas.openxmlformats.org/drawingml/2006/main";
    protected static final String NAMESPACE_C = "http://schemas.openxmlformats.org/drawingml/2006/chart";
    private CTDrawing drawing;
    private long numOfGraphicFrames;

    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFDrawing() {
        this.drawing = newDrawing();
    }

    public XSSFDrawing(PackagePart part) throws IOException, XmlException {
        super(part);
        XmlOptions options = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        options.setLoadReplaceDocumentElement(null);
        InputStream is = part.getInputStream();
        try {
            this.drawing = CTDrawing.Factory.parse(is, options);
            if (is != null) {
                is.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (is != null) {
                    try {
                        is.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private static CTDrawing newDrawing() {
        return CTDrawing.Factory.newInstance();
    }

    @Internal
    public CTDrawing getCTDrawing() {
        return this.drawing;
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void commit() throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTDrawing.type.getName().getNamespaceURI(), "wsDr", "xdr"));
        PackagePart part = getPackagePart();
        OutputStream out = part.getOutputStream();
        try {
            this.drawing.save(out, xmlOptions);
            if (out != null) {
                out.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (out != null) {
                    try {
                        out.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.poi.ss.usermodel.Drawing
    public XSSFClientAnchor createAnchor(int dx1, int dy1, int dx2, int dy2, int col1, int row1, int col2, int row2) {
        return new XSSFClientAnchor(dx1, dy1, dx2, dy2, col1, row1, col2, row2);
    }

    public XSSFTextBox createTextbox(XSSFClientAnchor anchor) {
        long shapeId = newShapeId();
        CTTwoCellAnchor ctAnchor = createTwoCellAnchor(anchor);
        CTShape ctShape = ctAnchor.addNewSp();
        ctShape.set(XSSFSimpleShape.prototype());
        ctShape.getNvSpPr().getCNvPr().setId(shapeId);
        XSSFTextBox shape = new XSSFTextBox(this, ctShape);
        shape.anchor = anchor;
        return shape;
    }

    public XSSFPicture createPicture(XSSFClientAnchor anchor, int pictureIndex) {
        PackageRelationship rel = addPictureReference(pictureIndex);
        long shapeId = newShapeId();
        CTTwoCellAnchor ctAnchor = createTwoCellAnchor(anchor);
        CTPicture ctShape = ctAnchor.addNewPic();
        ctShape.set(XSSFPicture.prototype());
        ctShape.getNvPicPr().getCNvPr().setId(shapeId);
        XSSFPicture shape = new XSSFPicture(this, ctShape);
        shape.anchor = anchor;
        shape.setPictureReference(rel);
        ctShape.getSpPr().setXfrm(createXfrm(anchor));
        return shape;
    }

    @Override // org.apache.poi.ss.usermodel.Drawing
    public XSSFPicture createPicture(ClientAnchor anchor, int pictureIndex) {
        return createPicture((XSSFClientAnchor) anchor, pictureIndex);
    }

    public XSSFChart createChart(XSSFClientAnchor anchor) {
        POIXMLDocumentPart.RelationPart rp = createChartRelationPart();
        XSSFChart chart = (XSSFChart) rp.getDocumentPart();
        String chartRelId = rp.getRelationship().getId();
        XSSFGraphicFrame frame = createGraphicFrame(anchor);
        frame.setChart(chart, chartRelId);
        frame.getCTGraphicalObjectFrame().setXfrm(createXfrm(anchor));
        return chart;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public POIXMLDocumentPart.RelationPart createChartRelationPart() {
        XSSFWorkbook wb = getSheet().getWorkbook();
        XSSFFactory factory = wb == null ? XSSFFactory.getInstance() : wb.getXssfFactory();
        OPCPackage pkg = getPackagePart().getPackage();
        int chartNumber = pkg.getPartsByContentType(XSSFRelation.CHART.getContentType()).size();
        do {
            try {
                chartNumber++;
            } catch (InvalidFormatException e) {
                throw new IllegalStateException("Failed for " + chartNumber, e);
            }
        } while (pkg.getPart(PackagingURIHelper.createPartName(XSSFRelation.CHART.getFileName(chartNumber))) != null);
        return createRelationship(XSSFRelation.CHART, factory, chartNumber, false);
    }

    public XSSFChart createChart(ClientAnchor anchor) {
        return createChart((XSSFClientAnchor) anchor);
    }

    public XSSFChart importChart(XSSFChart srcChart) {
        CTTwoCellAnchor anchor = ((XSSFDrawing) srcChart.getParent()).getCTDrawing().getTwoCellAnchorArray(0);
        CTMarker from = (CTMarker) anchor.getFrom().copy();
        CTMarker to = (CTMarker) anchor.getTo().copy();
        XSSFClientAnchor destAnchor = new XSSFClientAnchor(from, to);
        destAnchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
        XSSFChart destChart = createChart(destAnchor);
        destChart.getCTChartSpace().set(srcChart.getCTChartSpace().copy());
        return destChart;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PackageRelationship addPictureReference(int pictureIndex) {
        XSSFPictureData data = getSheet().getWorkbook().getAllPictures().get(pictureIndex);
        XSSFPictureData pic = new XSSFPictureData(data.getPackagePart());
        POIXMLDocumentPart.RelationPart rp = addRelation(null, XSSFRelation.IMAGES, pic);
        return rp.getRelationship();
    }

    public XSSFSimpleShape createSimpleShape(XSSFClientAnchor anchor) {
        long shapeId = newShapeId();
        CTTwoCellAnchor ctAnchor = createTwoCellAnchor(anchor);
        CTShape ctShape = ctAnchor.addNewSp();
        ctShape.set(XSSFSimpleShape.prototype());
        ctShape.getNvSpPr().getCNvPr().setId(shapeId);
        ctShape.getSpPr().setXfrm(createXfrm(anchor));
        XSSFSimpleShape shape = new XSSFSimpleShape(this, ctShape);
        shape.anchor = anchor;
        return shape;
    }

    public XSSFConnector createConnector(XSSFClientAnchor anchor) {
        CTTwoCellAnchor ctAnchor = createTwoCellAnchor(anchor);
        CTConnector ctShape = ctAnchor.addNewCxnSp();
        ctShape.set(XSSFConnector.prototype());
        XSSFConnector shape = new XSSFConnector(this, ctShape);
        shape.anchor = anchor;
        return shape;
    }

    public XSSFShapeGroup createGroup(XSSFClientAnchor anchor) {
        CTTwoCellAnchor ctAnchor = createTwoCellAnchor(anchor);
        CTGroupShape ctGroup = ctAnchor.addNewGrpSp();
        ctGroup.set(XSSFShapeGroup.prototype());
        CTTransform2D xfrm = createXfrm(anchor);
        CTGroupTransform2D grpXfrm = ctGroup.getGrpSpPr().getXfrm();
        grpXfrm.setOff(xfrm.getOff());
        grpXfrm.setExt(xfrm.getExt());
        grpXfrm.setChExt(xfrm.getExt());
        XSSFShapeGroup shape = new XSSFShapeGroup(this, ctGroup);
        shape.anchor = anchor;
        return shape;
    }

    @Override // org.apache.poi.ss.usermodel.Drawing
    public XSSFComment createCellComment(ClientAnchor anchor) {
        XSSFSheet sheet = getSheet();
        Comments comments = sheet.getCommentsTable(true);
        return comments.createNewComment(anchor);
    }

    private XSSFGraphicFrame createGraphicFrame(XSSFClientAnchor anchor) {
        CTTwoCellAnchor ctAnchor = createTwoCellAnchor(anchor);
        CTGraphicalObjectFrame ctGraphicFrame = ctAnchor.addNewGraphicFrame();
        ctGraphicFrame.set(XSSFGraphicFrame.prototype());
        ctGraphicFrame.setXfrm(createXfrm(anchor));
        long frameId = this.numOfGraphicFrames;
        this.numOfGraphicFrames = 1 + frameId;
        XSSFGraphicFrame graphicFrame = new XSSFGraphicFrame(this, ctGraphicFrame);
        graphicFrame.setAnchor(anchor);
        graphicFrame.setId(frameId);
        graphicFrame.setName("Diagramm" + frameId);
        return graphicFrame;
    }

    /* JADX WARN: Unreachable blocks removed: 2, instructions: 3 */
    @Override // org.apache.poi.ss.usermodel.Drawing
    public XSSFObjectData createObjectData(ClientAnchor anchor, int storageId, int pictureIndex) {
        Throwable th;
        Throwable th2;
        XSSFSheet sh = getSheet();
        PackagePart sheetPart = sh.getPackagePart();
        XSSFSheet sheet = getSheet();
        XSSFWorkbook wb = sheet.getWorkbook();
        int sheetIndex = wb.getSheetIndex(sheet);
        long shapeId = ((sheetIndex + 1) * FileUtils.ONE_KB) + newShapeId();
        XSSFRelation rel = XSSFRelation.OLEEMBEDDINGS;
        try {
            PackagePartName olePN = PackagingURIHelper.createPartName(rel.getFileName(storageId));
            PackageRelationship olePR = sheetPart.addRelationship(olePN, TargetMode.INTERNAL, rel.getRelation());
            XSSFPictureData imgPD = sh.getWorkbook().getAllPictures().get(pictureIndex);
            PackagePartName imgPN = imgPD.getPackagePart().getPartName();
            PackageRelationship imgSheetPR = sheetPart.addRelationship(imgPN, TargetMode.INTERNAL, PackageRelationshipTypes.IMAGE_PART);
            PackageRelationship imgDrawPR = getPackagePart().addRelationship(imgPN, TargetMode.INTERNAL, PackageRelationshipTypes.IMAGE_PART);
            CTWorksheet cwb = sh.getCTWorksheet();
            CTOleObjects oo = cwb.isSetOleObjects() ? cwb.getOleObjects() : cwb.addNewOleObjects();
            CTOleObject ole1 = oo.addNewOleObject();
            ole1.setProgId(ExtractorFactory.OOXML_PACKAGE);
            ole1.setShapeId(shapeId);
            ole1.setId(olePR.getId());
            XmlCursor cur1 = ole1.newCursor();
            try {
                cur1.toEndToken();
                try {
                    cur1.beginElement("objectPr", XSSFRelation.NS_SPREADSHEETML);
                    try {
                        cur1.insertAttributeWithValue("id", PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, imgSheetPR.getId());
                        cur1.insertAttributeWithValue("defaultSize", "0");
                        cur1.beginElement("anchor", XSSFRelation.NS_SPREADSHEETML);
                        cur1.insertAttributeWithValue("moveWithCells", "1");
                        CTTwoCellAnchor ctAnchor = createTwoCellAnchor((XSSFClientAnchor) anchor);
                        XmlCursor cur2 = ctAnchor.newCursor();
                        try {
                            cur2.copyXmlContents(cur1);
                            if (cur2 != null) {
                                cur2.close();
                            }
                            cur1.toParent();
                            cur1.toFirstChild();
                            cur1.setName(new QName(XSSFRelation.NS_SPREADSHEETML, TypedValues.TransitionType.S_FROM));
                            cur1.toNextSibling();
                            cur1.setName(new QName(XSSFRelation.NS_SPREADSHEETML, TypedValues.TransitionType.S_TO));
                            if (cur1 != null) {
                                cur1.close();
                            }
                            CTShape ctShape = ctAnchor.addNewSp();
                            ctShape.set(XSSFObjectData.prototype());
                            ctShape.getSpPr().setXfrm(createXfrm((XSSFClientAnchor) anchor));
                            CTBlipFillProperties blipFill = ctShape.getSpPr().addNewBlipFill();
                            blipFill.addNewBlip().setEmbed(imgDrawPR.getId());
                            blipFill.addNewStretch().addNewFillRect();
                            CTNonVisualDrawingProps cNvPr = ctShape.getNvSpPr().getCNvPr();
                            cNvPr.setId(shapeId);
                            cNvPr.setName("Object " + shapeId);
                            XmlCursor extCur = cNvPr.getExtLst().getExtArray(0).newCursor();
                            try {
                                extCur.toFirstChild();
                                try {
                                    try {
                                        extCur.setAttributeText(new QName("spid"), "_x0000_s" + shapeId);
                                        if (extCur != null) {
                                            extCur.close();
                                        }
                                        XSSFObjectData shape = new XSSFObjectData(this, ctShape);
                                        shape.anchor = (XSSFClientAnchor) anchor;
                                        return shape;
                                    } catch (Throwable th3) {
                                        th2 = th3;
                                        try {
                                            throw th2;
                                        } finally {
                                        }
                                    }
                                } catch (Throwable th4) {
                                    th2 = th4;
                                }
                            } catch (Throwable th5) {
                                th2 = th5;
                            }
                        } finally {
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        try {
                            throw th;
                        } finally {
                        }
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
            } catch (Throwable th8) {
                th = th8;
            }
        } catch (InvalidFormatException e) {
            throw new POIXMLException(e);
        }
    }

    public List<XSSFChart> getCharts() {
        List<XSSFChart> charts = new ArrayList<>();
        for (POIXMLDocumentPart part : getRelations()) {
            if (part instanceof XSSFChart) {
                charts.add((XSSFChart) part);
            }
        }
        return charts;
    }

    private CTTwoCellAnchor createTwoCellAnchor(XSSFClientAnchor anchor) {
        STEditAs.Enum editAs;
        CTTwoCellAnchor ctAnchor = this.drawing.addNewTwoCellAnchor();
        ctAnchor.setFrom(anchor.getFrom());
        ctAnchor.setTo(anchor.getTo());
        ctAnchor.addNewClientData();
        anchor.setTo(ctAnchor.getTo());
        anchor.setFrom(ctAnchor.getFrom());
        switch (anchor.getAnchorType()) {
            case DONT_MOVE_AND_RESIZE:
                editAs = STEditAs.ABSOLUTE;
                break;
            case MOVE_AND_RESIZE:
                editAs = STEditAs.TWO_CELL;
                break;
            case MOVE_DONT_RESIZE:
                editAs = STEditAs.ONE_CELL;
                break;
            default:
                editAs = STEditAs.ONE_CELL;
                break;
        }
        ctAnchor.setEditAs(editAs);
        return ctAnchor;
    }

    private CTTransform2D createXfrm(XSSFClientAnchor anchor) {
        CTTransform2D xfrm = CTTransform2D.Factory.newInstance();
        CTPoint2D off = xfrm.addNewOff();
        off.setX(Integer.valueOf(anchor.getDx1()));
        off.setY(Integer.valueOf(anchor.getDy1()));
        XSSFSheet sheet = getSheet();
        double widthPx = 0.0d;
        for (int col = anchor.getCol1(); col < anchor.getCol2(); col++) {
            widthPx += sheet.getColumnWidthInPixels(col);
        }
        double heightPx = 0.0d;
        for (int row = anchor.getRow1(); row < anchor.getRow2(); row++) {
            heightPx += ImageUtils.getRowHeightInPixels(sheet, row);
        }
        int row2 = (int) widthPx;
        long width = Units.pixelToEMU(row2);
        long height = Units.pixelToEMU((int) heightPx);
        CTPositiveSize2D ext = xfrm.addNewExt();
        ext.setCx((width - anchor.getDx1()) + anchor.getDx2());
        ext.setCy((height - anchor.getDy1()) + anchor.getDy2());
        return xfrm;
    }

    private long newShapeId() {
        return this.drawing.sizeOfAbsoluteAnchorArray() + 1 + this.drawing.sizeOfOneCellAnchorArray() + this.drawing.sizeOfTwoCellAnchorArray();
    }

    public List<XSSFShape> getShapes() {
        List<XSSFShape> lst = new ArrayList<>();
        XmlCursor cur = this.drawing.newCursor();
        try {
            if (cur.toFirstChild()) {
                addShapes(cur, lst);
            }
            if (cur != null) {
                cur.close();
            }
            return lst;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public List<XSSFShape> getShapes(XSSFShapeGroup groupshape) {
        List<XSSFShape> lst = new ArrayList<>();
        XmlCursor cur = groupshape.getCTGroupShape().newCursor();
        try {
            addShapes(cur, lst);
            if (cur != null) {
                cur.close();
            }
            return lst;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0023, code lost:
    
        if ((r0 instanceof org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnector) == false) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0025, code lost:
    
        r1 = new org.apache.poi.xssf.usermodel.XSSFConnector(r6, (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnector) r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0030, code lost:
    
        if ((r0 instanceof org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape) == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0036, code lost:
    
        if (hasOleLink(r0) == false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0038, code lost:
    
        r1 = new org.apache.poi.xssf.usermodel.XSSFObjectData(r6, (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape) r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0041, code lost:
    
        r1 = new org.apache.poi.xssf.usermodel.XSSFSimpleShape(r6, (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape) r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x004c, code lost:
    
        if ((r0 instanceof org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrame) == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004e, code lost:
    
        r1 = new org.apache.poi.xssf.usermodel.XSSFGraphicFrame(r6, (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrame) r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0059, code lost:
    
        if ((r0 instanceof org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape) == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005b, code lost:
    
        r1 = new org.apache.poi.xssf.usermodel.XSSFShapeGroup(r6, (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape) r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0070, code lost:
    
        if ((r0 instanceof org.apache.xmlbeans.impl.values.XmlAnyTypeImpl) == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0072, code lost:
    
        org.apache.poi.xssf.usermodel.XSSFDrawing.LOG.atWarn().log("trying to parse AlternateContent, this unlinks the returned Shapes from the underlying xml content, so those shapes can't be used to modify the drawing, i.e. modifications will be ignored!");
        r7.push();
        r7.toFirstChild();
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0083, code lost:
    
        r1 = org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing.Factory.parse(r7.newXMLStreamReader());
        r2 = r1.newCursor();
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0008, code lost:
    
        if (r7.toFirstChild() != false) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0097, code lost:
    
        if (r2.toFirstChild() == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0099, code lost:
    
        addShapes(r2, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x009c, code lost:
    
        if (r2 == null) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x009e, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a2, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x000a, code lost:
    
        r0 = r7.getObject();
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00a3, code lost:
    
        throw r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00a4, code lost:
    
        r4 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00a5, code lost:
    
        if (r2 != null) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00af, code lost:
    
        throw r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0010, code lost:
    
        if ((r0 instanceof org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker) == false) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00a7, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00ab, code lost:
    
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00ac, code lost:
    
        r3.addSuppressed(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00b2, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00b3, code lost:
    
        org.apache.poi.xssf.usermodel.XSSFDrawing.LOG.atWarn().withThrowable(r1).log("unable to parse CTDrawing in alternate content.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00b0, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00c7, code lost:
    
        r7.pop();
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00ca, code lost:
    
        throw r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0016, code lost:
    
        if ((r0 instanceof org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture) == false) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0018, code lost:
    
        r1 = new org.apache.poi.xssf.usermodel.XSSFPicture(r6, (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture) r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0063, code lost:
    
        r1.anchor = getAnchorFromParent(r0);
        r8.add(r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void addShapes(org.apache.xmlbeans.XmlCursor r7, java.util.List<org.apache.poi.xssf.usermodel.XSSFShape> r8) {
        /*
            r6 = this;
        L1:
            r7.push()
            boolean r0 = r7.toFirstChild()
            if (r0 == 0) goto Ld1
        La:
            org.apache.xmlbeans.XmlObject r0 = r7.getObject()
            boolean r1 = r0 instanceof org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker
            if (r1 == 0) goto L14
            goto Lcb
        L14:
            boolean r1 = r0 instanceof org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture
            if (r1 == 0) goto L21
            org.apache.poi.xssf.usermodel.XSSFPicture r1 = new org.apache.poi.xssf.usermodel.XSSFPicture
            r2 = r0
            org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture r2 = (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture) r2
            r1.<init>(r6, r2)
            goto L63
        L21:
            boolean r1 = r0 instanceof org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnector
            if (r1 == 0) goto L2e
            org.apache.poi.xssf.usermodel.XSSFConnector r1 = new org.apache.poi.xssf.usermodel.XSSFConnector
            r2 = r0
            org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnector r2 = (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnector) r2
            r1.<init>(r6, r2)
            goto L63
        L2e:
            boolean r1 = r0 instanceof org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape
            if (r1 == 0) goto L4a
            boolean r1 = r6.hasOleLink(r0)
            if (r1 == 0) goto L41
            org.apache.poi.xssf.usermodel.XSSFObjectData r1 = new org.apache.poi.xssf.usermodel.XSSFObjectData
            r2 = r0
            org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape r2 = (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape) r2
            r1.<init>(r6, r2)
            goto L49
        L41:
            org.apache.poi.xssf.usermodel.XSSFSimpleShape r1 = new org.apache.poi.xssf.usermodel.XSSFSimpleShape
            r2 = r0
            org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape r2 = (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape) r2
            r1.<init>(r6, r2)
        L49:
            goto L63
        L4a:
            boolean r1 = r0 instanceof org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrame
            if (r1 == 0) goto L57
            org.apache.poi.xssf.usermodel.XSSFGraphicFrame r1 = new org.apache.poi.xssf.usermodel.XSSFGraphicFrame
            r2 = r0
            org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrame r2 = (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrame) r2
            r1.<init>(r6, r2)
            goto L63
        L57:
            boolean r1 = r0 instanceof org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape
            if (r1 == 0) goto L6e
            org.apache.poi.xssf.usermodel.XSSFShapeGroup r1 = new org.apache.poi.xssf.usermodel.XSSFShapeGroup
            r2 = r0
            org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape r2 = (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape) r2
            r1.<init>(r6, r2)
        L63:
            org.apache.poi.xssf.usermodel.XSSFAnchor r2 = r6.getAnchorFromParent(r0)
            r1.anchor = r2
            r8.add(r1)
            goto Lcb
        L6e:
            boolean r1 = r0 instanceof org.apache.xmlbeans.impl.values.XmlAnyTypeImpl
            if (r1 == 0) goto Lcb
            org.apache.logging.log4j.Logger r1 = org.apache.poi.xssf.usermodel.XSSFDrawing.LOG
            org.apache.logging.log4j.LogBuilder r1 = r1.atWarn()
            java.lang.String r2 = "trying to parse AlternateContent, this unlinks the returned Shapes from the underlying xml content, so those shapes can't be used to modify the drawing, i.e. modifications will be ignored!"
            r1.log(r2)
            r7.push()
            r7.toFirstChild()
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing> r1 = org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing.Factory     // Catch: java.lang.Throwable -> Lb0 org.apache.xmlbeans.XmlException -> Lb2
            javax.xml.stream.XMLStreamReader r2 = r7.newXMLStreamReader()     // Catch: java.lang.Throwable -> Lb0 org.apache.xmlbeans.XmlException -> Lb2
            java.lang.Object r1 = r1.parse(r2)     // Catch: java.lang.Throwable -> Lb0 org.apache.xmlbeans.XmlException -> Lb2
            org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing r1 = (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing) r1     // Catch: java.lang.Throwable -> Lb0 org.apache.xmlbeans.XmlException -> Lb2
            org.apache.xmlbeans.XmlCursor r2 = r1.newCursor()     // Catch: java.lang.Throwable -> Lb0 org.apache.xmlbeans.XmlException -> Lb2
            boolean r3 = r2.toFirstChild()     // Catch: java.lang.Throwable -> La2
            if (r3 == 0) goto L9c
            r6.addShapes(r2, r8)     // Catch: java.lang.Throwable -> La2
        L9c:
            if (r2 == 0) goto Lc2
            r2.close()     // Catch: java.lang.Throwable -> Lb0 org.apache.xmlbeans.XmlException -> Lb2
            goto Lc2
        La2:
            r3 = move-exception
            throw r3     // Catch: java.lang.Throwable -> La4
        La4:
            r4 = move-exception
            if (r2 == 0) goto Laf
            r2.close()     // Catch: java.lang.Throwable -> Lab
            goto Laf
        Lab:
            r5 = move-exception
            r3.addSuppressed(r5)     // Catch: java.lang.Throwable -> Lb0 org.apache.xmlbeans.XmlException -> Lb2
        Laf:
            throw r4     // Catch: java.lang.Throwable -> Lb0 org.apache.xmlbeans.XmlException -> Lb2
        Lb0:
            r1 = move-exception
            goto Lc7
        Lb2:
            r1 = move-exception
            org.apache.logging.log4j.Logger r2 = org.apache.poi.xssf.usermodel.XSSFDrawing.LOG     // Catch: java.lang.Throwable -> Lb0
            org.apache.logging.log4j.LogBuilder r2 = r2.atWarn()     // Catch: java.lang.Throwable -> Lb0
            org.apache.logging.log4j.LogBuilder r2 = r2.withThrowable(r1)     // Catch: java.lang.Throwable -> Lb0
            java.lang.String r3 = "unable to parse CTDrawing in alternate content."
            r2.log(r3)     // Catch: java.lang.Throwable -> Lb0
        Lc2:
            r7.pop()
            goto Lcb
        Lc7:
            r7.pop()
            throw r1
        Lcb:
            boolean r0 = r7.toNextSibling()
            if (r0 != 0) goto La
        Ld1:
            r7.pop()
            boolean r0 = r7.toNextSibling()
            if (r0 != 0) goto L1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFDrawing.addShapes(org.apache.xmlbeans.XmlCursor, java.util.List):void");
    }

    private boolean hasOleLink(XmlObject shape) {
        QName uriName = new QName(null, "uri");
        XmlCursor cur = shape.newCursor();
        try {
            cur.selectPath("declare namespace a='http://schemas.openxmlformats.org/drawingml/2006/main' .//a:extLst/a:ext");
            while (cur.toNextSelection()) {
                String uri = cur.getAttributeText(uriName);
                if ("{63B3BB69-23CF-44E3-9099-C40C66FF867C}".equals(uri)) {
                    if (cur != null) {
                        cur.close();
                        return true;
                    }
                    return true;
                }
            }
            if (cur != null) {
                cur.close();
                return false;
            }
            return false;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private XSSFAnchor getAnchorFromParent(XmlObject obj) {
        XmlObject parentXbean = null;
        XmlCursor cursor = obj.newCursor();
        try {
            if (cursor.toParent()) {
                parentXbean = cursor.getObject();
            }
            if (cursor != null) {
                cursor.close();
            }
            if (parentXbean == null) {
                return null;
            }
            if (parentXbean instanceof CTTwoCellAnchor) {
                CTTwoCellAnchor ct = (CTTwoCellAnchor) parentXbean;
                XSSFAnchor anchor = new XSSFClientAnchor(ct.getFrom(), ct.getTo());
                return anchor;
            }
            if (parentXbean instanceof CTOneCellAnchor) {
                CTOneCellAnchor ct2 = (CTOneCellAnchor) parentXbean;
                XSSFAnchor anchor2 = new XSSFClientAnchor(getSheet(), ct2.getFrom(), ct2.getExt());
                return anchor2;
            }
            if (!(parentXbean instanceof CTAbsoluteAnchor)) {
                return null;
            }
            CTAbsoluteAnchor ct3 = (CTAbsoluteAnchor) parentXbean;
            XSSFAnchor anchor3 = new XSSFClientAnchor(getSheet(), ct3.getPos(), ct3.getExt());
            return anchor3;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // java.lang.Iterable
    public Iterator<XSSFShape> iterator() {
        return getShapes().iterator();
    }

    @Override // java.lang.Iterable
    public Spliterator<XSSFShape> spliterator() {
        return getShapes().spliterator();
    }

    public XSSFSheet getSheet() {
        return (XSSFSheet) getParent();
    }
}
