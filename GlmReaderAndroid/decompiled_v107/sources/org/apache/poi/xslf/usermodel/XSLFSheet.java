package org.apache.poi.xslf.usermodel;

import com.zaxxer.sparsebits.SparseBitSet;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javax.xml.namespace.QName;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageNamespaces;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.draw.DrawPictureShape;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.usermodel.AutoShape;
import org.apache.poi.sl.usermodel.Background;
import org.apache.poi.sl.usermodel.ConnectorShape;
import org.apache.poi.sl.usermodel.FreeformShape;
import org.apache.poi.sl.usermodel.GroupShape;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.PictureShape;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.sl.usermodel.TableShape;
import org.apache.poi.sl.usermodel.TextBox;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.values.XmlAnyTypeImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride;
import org.openxmlformats.schemas.drawingml.x2006.main.STColorSchemeIndex;
import org.openxmlformats.schemas.presentationml.x2006.main.CTConnector;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPicture;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPlaceholder;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;

/* loaded from: classes10.dex */
public abstract class XSLFSheet extends POIXMLDocumentPart implements XSLFShapeContainer, Sheet<XSLFShape, XSLFTextParagraph> {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XSLFSheet.class);
    private XSLFDrawing _drawing;
    private Map<Integer, XSLFSimpleShape> _placeholderByIdMap;
    private Map<Integer, XSLFSimpleShape> _placeholderByTypeMap;
    private List<XSLFTextShape> _placeholders;
    private List<XSLFShape> _shapes;
    private CTGroupShape _spTree;
    private XSLFTheme _theme;
    private final SparseBitSet shapeIds;

    protected abstract String getRootElementName();

    public abstract XmlObject getXmlObject();

    /* JADX INFO: Access modifiers changed from: protected */
    public XSLFSheet() {
        this.shapeIds = new SparseBitSet();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XSLFSheet(PackagePart part) {
        super(part);
        this.shapeIds = new SparseBitSet();
    }

    @Override // org.apache.poi.sl.usermodel.Sheet
    public SlideShow<XSLFShape, XSLFTextParagraph> getSlideShow() {
        for (POIXMLDocumentPart p = getParent(); p != null; p = p.getParent()) {
            if (p instanceof XMLSlideShow) {
                return (XMLSlideShow) p;
            }
        }
        throw new IllegalStateException("SlideShow was not found");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int allocateShapeId() {
        int nextId = this.shapeIds.nextClearBit(1);
        this.shapeIds.set(nextId);
        return nextId;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void registerShapeId(int shapeId) {
        if (this.shapeIds.get(shapeId)) {
            LOG.atWarn().log("shape id {} has been already used.", Unbox.box(shapeId));
        }
        this.shapeIds.set(shapeId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void deregisterShapeId(int shapeId) {
        if (!this.shapeIds.get(shapeId)) {
            LOG.atWarn().log("shape id {} hasn't been registered.", Unbox.box(shapeId));
        }
        this.shapeIds.clear(shapeId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r9v0, types: [org.apache.poi.xslf.usermodel.XSLFShapeContainer] */
    public static List<XSLFShape> buildShapes(CTGroupShape cTGroupShape, XSLFShapeContainer xSLFShapeContainer) {
        ?? sheet = xSLFShapeContainer instanceof XSLFSheet ? (XSLFSheet) xSLFShapeContainer : ((XSLFShape) xSLFShapeContainer).getSheet();
        ArrayList arrayList = new ArrayList();
        XmlCursor newCursor = cTGroupShape.newCursor();
        try {
            for (boolean firstChild = newCursor.toFirstChild(); firstChild; firstChild = newCursor.toNextSibling()) {
                XmlObject object = newCursor.getObject();
                if (object instanceof CTShape) {
                    arrayList.add(XSLFAutoShape.create((CTShape) object, sheet));
                } else if (object instanceof CTGroupShape) {
                    arrayList.add(new XSLFGroupShape((CTGroupShape) object, sheet));
                } else if (object instanceof CTConnector) {
                    arrayList.add(new XSLFConnectorShape((CTConnector) object, sheet));
                } else if (object instanceof CTPicture) {
                    arrayList.add(new XSLFPictureShape((CTPicture) object, sheet));
                } else if (object instanceof CTGraphicalObjectFrame) {
                    arrayList.add(XSLFGraphicFrame.create((CTGraphicalObjectFrame) object, sheet));
                } else if (object instanceof XmlAnyTypeImpl) {
                    newCursor.push();
                    if (newCursor.toChild(PackageNamespaces.MARKUP_COMPATIBILITY, "Choice") && newCursor.toFirstChild()) {
                        try {
                            arrayList.addAll(buildShapes(CTGroupShape.Factory.parse(newCursor.newXMLStreamReader()), xSLFShapeContainer));
                        } catch (XmlException e) {
                            LOG.atDebug().withThrowable(e).log("unparsable alternate content");
                        }
                    }
                    newCursor.pop();
                }
            }
            if (newCursor != null) {
                newCursor.close();
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((XSLFShape) it.next()).setParent(xSLFShapeContainer);
            }
            return arrayList;
        } finally {
        }
    }

    private XSLFDrawing getDrawing() {
        initDrawingAndShapes();
        return this._drawing;
    }

    @Override // org.apache.poi.sl.usermodel.ShapeContainer
    public List<XSLFShape> getShapes() {
        initDrawingAndShapes();
        return this._shapes;
    }

    private void initDrawingAndShapes() {
        CTGroupShape cgs = getSpTree();
        if (this._drawing == null) {
            this._drawing = new XSLFDrawing(this, cgs);
        }
        if (this._shapes == null) {
            this._shapes = buildShapes(cgs, this);
        }
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFShapeContainer, org.apache.poi.sl.usermodel.ShapeContainer
    public AutoShape<XSLFShape, XSLFTextParagraph> createAutoShape() {
        XSLFAutoShape sh = getDrawing().createAutoShape();
        getShapes().add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFShapeContainer, org.apache.poi.sl.usermodel.ShapeContainer
    public FreeformShape<XSLFShape, XSLFTextParagraph> createFreeform() {
        XSLFFreeformShape sh = getDrawing().createFreeform();
        getShapes().add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFShapeContainer, org.apache.poi.sl.usermodel.ShapeContainer
    public TextBox<XSLFShape, XSLFTextParagraph> createTextBox() {
        XSLFTextBox sh = getDrawing().createTextBox();
        getShapes().add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFShapeContainer, org.apache.poi.sl.usermodel.ShapeContainer
    public ConnectorShape<XSLFShape, XSLFTextParagraph> createConnector() {
        XSLFConnectorShape sh = getDrawing().createConnector();
        getShapes().add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFShapeContainer, org.apache.poi.sl.usermodel.ShapeContainer
    public GroupShape<XSLFShape, XSLFTextParagraph> createGroup() {
        XSLFGroupShape sh = getDrawing().createGroup();
        getShapes().add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFShapeContainer, org.apache.poi.sl.usermodel.ShapeContainer
    public PictureShape<XSLFShape, XSLFTextParagraph> createPicture(PictureData pictureData) {
        if (!(pictureData instanceof XSLFPictureData)) {
            throw new IllegalArgumentException("pictureData needs to be of type XSLFPictureData");
        }
        POIXMLDocumentPart.RelationPart rp = addRelation(null, XSLFRelation.IMAGES, (XSLFPictureData) pictureData);
        XSLFPictureShape sh = getDrawing().createPicture(rp.getRelationship().getId());
        new DrawPictureShape(sh).resize();
        getShapes().add(sh);
        sh.setParent(this);
        return sh;
    }

    public XSLFTable createTable() {
        XSLFTable sh = getDrawing().createTable();
        getShapes().add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override // org.apache.poi.sl.usermodel.ShapeContainer
    public TableShape<XSLFShape, XSLFTextParagraph> createTable(int numRows, int numCols) {
        if (numRows < 1 || numCols < 1) {
            throw new IllegalArgumentException("numRows and numCols must be greater than 0");
        }
        XSLFTable sh = getDrawing().createTable();
        getShapes().add(sh);
        sh.setParent(this);
        for (int r = 0; r < numRows; r++) {
            XSLFTableRow row = sh.addRow();
            for (int c = 0; c < numCols; c++) {
                row.addCell();
            }
        }
        return sh;
    }

    @Override // org.apache.poi.sl.usermodel.ShapeContainer
    public XSLFObjectShape createOleShape(PictureData pictureData) {
        if (!(pictureData instanceof XSLFPictureData)) {
            throw new IllegalArgumentException("pictureData needs to be of type XSLFPictureData");
        }
        POIXMLDocumentPart.RelationPart rp = addRelation(null, XSLFRelation.IMAGES, (XSLFPictureData) pictureData);
        XSLFObjectShape sh = getDrawing().createOleShape(rp.getRelationship().getId());
        CTOleObject oleObj = sh.getCTOleObject();
        Dimension dim = pictureData.getImageDimension();
        oleObj.setImgW(Units.toEMU(dim.getWidth()));
        oleObj.setImgH(Units.toEMU(dim.getHeight()));
        getShapes().add(sh);
        sh.setParent(this);
        return sh;
    }

    @Override // java.lang.Iterable
    public Iterator<XSLFShape> iterator() {
        return getShapes().iterator();
    }

    @Override // org.apache.poi.sl.usermodel.ShapeContainer
    @NotImplemented
    public void addShape(XSLFShape shape) {
        throw new UnsupportedOperationException("Adding a shape from a different container is not supported - create it from scratch with the XSLFSheet.create* methods");
    }

    @Override // org.apache.poi.sl.usermodel.ShapeContainer
    public boolean removeShape(XSLFShape xShape) {
        XmlObject obj = xShape.getXmlObject();
        CTGroupShape spTree = getSpTree();
        deregisterShapeId(xShape.getShapeId());
        if (obj instanceof CTShape) {
            spTree.getSpList().remove(obj);
        } else if (obj instanceof CTGroupShape) {
            XSLFGroupShape gs = (XSLFGroupShape) xShape;
            ArrayList arrayList = new ArrayList(gs.getShapes());
            gs.getClass();
            arrayList.forEach(new XSLFGroupShape$$ExternalSyntheticLambda0(gs));
            spTree.getGrpSpList().remove(obj);
        } else if (obj instanceof CTConnector) {
            spTree.getCxnSpList().remove(obj);
        } else if (obj instanceof CTGraphicalObjectFrame) {
            spTree.getGraphicFrameList().remove(obj);
        } else if (obj instanceof CTPicture) {
            XSLFPictureShape ps = (XSLFPictureShape) xShape;
            removePictureRelation(ps);
            spTree.getPicList().remove(obj);
        } else {
            throw new IllegalArgumentException("Unsupported shape: " + xShape);
        }
        return getShapes().remove(xShape);
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFShapeContainer
    public void clear() {
        List<XSLFShape> shapes = new ArrayList<>(getShapes());
        for (XSLFShape shape : shapes) {
            removeShape(shape);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CTGroupShape getSpTree() {
        if (this._spTree == null) {
            XmlObject root = getXmlObject();
            XmlObject[] sp = root.selectPath("declare namespace p='http://schemas.openxmlformats.org/presentationml/2006/main' .//*/p:spTree");
            if (sp.length == 0) {
                throw new IllegalStateException("CTGroupShape was not found");
            }
            XmlObject xmlObject = sp[0];
            if (!(xmlObject instanceof CTGroupShape)) {
                throw new IllegalArgumentException("Had unexpected type of entry: " + xmlObject.getClass());
            }
            this._spTree = (CTGroupShape) xmlObject;
        }
        return this._spTree;
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected final void commit() throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        String docName = getRootElementName();
        if (docName != null) {
            xmlOptions.setSaveSyntheticDocumentElement(new QName(XSSFRelation.NS_PRESENTATIONML, docName));
        }
        PackagePart part = getPackagePart();
        OutputStream out = part.getOutputStream();
        try {
            getXmlObject().save(out, xmlOptions);
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

    public XSLFSheet importContent(XSLFSheet src) {
        this._spTree = null;
        getSpTree().set(src.getSpTree().copy());
        wipeAndReinitialize(src, 0);
        return this;
    }

    private void wipeAndReinitialize(XSLFSheet src, int offset) {
        this._shapes = null;
        this._drawing = null;
        initDrawingAndShapes();
        this._placeholders = null;
        List<XSLFShape> tgtShapes = getShapes();
        List<XSLFShape> srcShapes = src.getShapes();
        for (int i = 0; i < srcShapes.size(); i++) {
            XSLFShape s1 = srcShapes.get(i);
            XSLFShape s2 = tgtShapes.get(offset + i);
            s2.copy(s1);
        }
    }

    public XSLFSheet appendContent(XSLFSheet src) {
        int numShapes = getShapes().size();
        CTGroupShape spTree = getSpTree();
        CTGroupShape srcTree = src.getSpTree();
        for (XmlObject ch : srcTree.selectPath("*")) {
            if (ch instanceof CTShape) {
                spTree.addNewSp().set(ch.copy());
            } else if (ch instanceof CTGroupShape) {
                spTree.addNewGrpSp().set(ch.copy());
            } else if (ch instanceof CTConnector) {
                spTree.addNewCxnSp().set(ch.copy());
            } else if (ch instanceof CTPicture) {
                spTree.addNewPic().set(ch.copy());
            } else if (ch instanceof CTGraphicalObjectFrame) {
                spTree.addNewGraphicFrame().set(ch.copy());
            }
        }
        wipeAndReinitialize(src, numShapes);
        return this;
    }

    public XSLFTheme getTheme() {
        if (this._theme != null || !isSupportTheme()) {
            return this._theme;
        }
        getRelations().stream().filter(new Predicate() { // from class: org.apache.poi.xslf.usermodel.XSLFSheet$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return XSLFSheet.lambda$getTheme$0((POIXMLDocumentPart) obj);
            }
        }).findAny().ifPresent(new Consumer() { // from class: org.apache.poi.xslf.usermodel.XSLFSheet$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                XSLFSheet.this.m2577lambda$getTheme$1$orgapachepoixslfusermodelXSLFSheet((POIXMLDocumentPart) obj);
            }
        });
        return this._theme;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$getTheme$0(POIXMLDocumentPart p) {
        return p instanceof XSLFTheme;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getTheme$1$org-apache-poi-xslf-usermodel-XSLFSheet, reason: not valid java name */
    public /* synthetic */ void m2577lambda$getTheme$1$orgapachepoixslfusermodelXSLFSheet(POIXMLDocumentPart p) {
        this._theme = (XSLFTheme) p;
    }

    boolean isSupportTheme() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String mapSchemeColor(String schemeColor) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XSLFTextShape getTextShapeByType(Placeholder type) {
        for (XSLFShape shape : getShapes()) {
            if (shape instanceof XSLFTextShape) {
                XSLFTextShape txt = (XSLFTextShape) shape;
                if (txt.getTextType() == type) {
                    return txt;
                }
            }
        }
        return null;
    }

    public XSLFSimpleShape getPlaceholder(Placeholder ph) {
        return getPlaceholderByType(ph.ooxmlId);
    }

    @Internal
    public XSLFSimpleShape getPlaceholder(CTPlaceholder ph) {
        XSLFSimpleShape shape = null;
        if (ph.isSetIdx()) {
            shape = getPlaceholderById((int) ph.getIdx());
        }
        if (shape == null && ph.isSetType()) {
            XSLFSimpleShape shape2 = getPlaceholderByType(ph.getType().intValue());
            return shape2;
        }
        return shape;
    }

    private void initPlaceholders() {
        if (this._placeholders == null) {
            this._placeholders = new ArrayList();
            this._placeholderByIdMap = new HashMap();
            this._placeholderByTypeMap = new HashMap();
            for (XSLFShape sh : getShapes()) {
                if (sh instanceof XSLFTextShape) {
                    XSLFTextShape sShape = (XSLFTextShape) sh;
                    CTPlaceholder ph = sShape.getPlaceholderDetails().getCTPlaceholder(false);
                    if (ph != null) {
                        this._placeholders.add(sShape);
                        if (ph.isSetIdx()) {
                            int idx = (int) ph.getIdx();
                            this._placeholderByIdMap.put(Integer.valueOf(idx), sShape);
                        }
                        if (ph.isSetType()) {
                            this._placeholderByTypeMap.put(Integer.valueOf(ph.getType().intValue()), sShape);
                        }
                    }
                }
            }
        }
    }

    private XSLFSimpleShape getPlaceholderById(int id) {
        initPlaceholders();
        return this._placeholderByIdMap.get(Integer.valueOf(id));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFSimpleShape getPlaceholderByType(int ordinal) {
        initPlaceholders();
        return this._placeholderByTypeMap.get(Integer.valueOf(ordinal));
    }

    public XSLFTextShape getPlaceholder(int idx) {
        initPlaceholders();
        return this._placeholders.get(idx);
    }

    public XSLFTextShape[] getPlaceholders() {
        initPlaceholders();
        return (XSLFTextShape[]) this._placeholders.toArray(new XSLFTextShape[0]);
    }

    @Override // org.apache.poi.sl.usermodel.Sheet
    public boolean getFollowMasterGraphics() {
        return false;
    }

    @Override // org.apache.poi.sl.usermodel.Sheet
    public Background<XSLFShape, XSLFTextParagraph> getBackground() {
        return null;
    }

    @Override // org.apache.poi.sl.usermodel.Sheet
    public void draw(Graphics2D graphics) {
        DrawFactory drawFact = DrawFactory.getInstance(graphics);
        Drawable draw = drawFact.getDrawable(this);
        draw.draw(graphics);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r0v0, types: [org.apache.poi.xslf.usermodel.XMLSlideShow] */
    public String importBlip(String blipId, POIXMLDocumentPart parent) {
        return getSlideShow().importBlip(blipId, parent, this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void importPart(PackageRelationship srcRel, PackagePart srcPart) {
        PackagePart destPP = getPackagePart();
        PackagePartName srcPPName = srcPart.getPartName();
        OPCPackage pkg = destPP.getPackage();
        if (pkg.containPart(srcPPName)) {
            return;
        }
        destPP.addRelationship(srcPPName, TargetMode.INTERNAL, srcRel.getRelationshipType());
        PackagePart part = pkg.createPart(srcPPName, srcPart.getContentType());
        try {
            OutputStream out = part.getOutputStream();
            try {
                InputStream is = srcPart.getInputStream();
                try {
                    IOUtils.copy(is, out);
                    if (is != null) {
                        is.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            throw new POIXMLException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removePictureRelation(XSLFPictureShape pictureShape) {
        int numberOfRelations = 0;
        String targetBlipId = pictureShape.getBlipId();
        for (XSLFShape shape : pictureShape.getSheet().getShapes()) {
            if (shape instanceof XSLFPictureShape) {
                XSLFPictureShape currentPictureShape = (XSLFPictureShape) shape;
                String currentBlipId = currentPictureShape.getBlipId();
                if (currentBlipId != null && currentBlipId.equals(targetBlipId)) {
                    numberOfRelations++;
                }
            }
        }
        if (numberOfRelations <= 1) {
            removeRelation(pictureShape.getBlipId());
        }
    }

    @Override // org.apache.poi.sl.usermodel.Sheet
    public XSLFPlaceholderDetails getPlaceholderDetails(Placeholder placeholder) {
        XSLFSimpleShape ph = getPlaceholder(placeholder);
        if (ph == null) {
            return null;
        }
        return new XSLFPlaceholderDetails(ph);
    }

    public void addChart(XSLFChart chart) {
        addChart(chart, new Rectangle(10, 10, 500000, 500000));
    }

    public void addChart(XSLFChart chart, Rectangle2D rect2D) {
        POIXMLDocumentPart.RelationPart rp = addRelation(null, XSLFRelation.CHART, chart);
        getDrawing().addChart(rp.getRelationship().getId(), rect2D);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String mapSchemeColor(CTColorMappingOverride cmapOver, String schemeColor) {
        String slideColor = mapSchemeColor(cmapOver == null ? null : cmapOver.getOverrideClrMapping(), schemeColor);
        if (slideColor != null) {
            return slideColor;
        }
        XSLFSheet master = (XSLFSheet) getMasterSheet();
        String masterColor = master != null ? master.mapSchemeColor(schemeColor) : null;
        return masterColor == null ? schemeColor : masterColor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public String mapSchemeColor(CTColorMapping cmap, String schemeColor) {
        char c;
        STColorSchemeIndex.Enum schemeMap = null;
        if (cmap != null && schemeColor != null) {
            switch (schemeColor.hashCode()) {
                case -1177623385:
                    if (schemeColor.equals("accent1")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1177623384:
                    if (schemeColor.equals("accent2")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -1177623383:
                    if (schemeColor.equals("accent3")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -1177623382:
                    if (schemeColor.equals("accent4")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -1177623381:
                    if (schemeColor.equals("accent5")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case -1177623380:
                    if (schemeColor.equals("accent6")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 97420:
                    if (schemeColor.equals("bg1")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case 97421:
                    if (schemeColor.equals("bg2")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case 115245:
                    if (schemeColor.equals("tx1")) {
                        c = '\n';
                        break;
                    }
                    c = 65535;
                    break;
                case 115246:
                    if (schemeColor.equals("tx2")) {
                        c = 11;
                        break;
                    }
                    c = 65535;
                    break;
                case 99368034:
                    if (schemeColor.equals("hlink")) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                case 268452191:
                    if (schemeColor.equals("folHlink")) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    schemeMap = cmap.getAccent1();
                    break;
                case 1:
                    schemeMap = cmap.getAccent2();
                    break;
                case 2:
                    schemeMap = cmap.getAccent3();
                    break;
                case 3:
                    schemeMap = cmap.getAccent4();
                    break;
                case 4:
                    schemeMap = cmap.getAccent5();
                    break;
                case 5:
                    schemeMap = cmap.getAccent6();
                    break;
                case 6:
                    schemeMap = cmap.getBg1();
                    break;
                case 7:
                    schemeMap = cmap.getBg2();
                    break;
                case '\b':
                    schemeMap = cmap.getFolHlink();
                    break;
                case '\t':
                    schemeMap = cmap.getHlink();
                    break;
                case '\n':
                    schemeMap = cmap.getTx1();
                    break;
                case 11:
                    schemeMap = cmap.getTx2();
                    break;
            }
        }
        if (schemeMap == null) {
            return null;
        }
        return schemeMap.toString();
    }
}
