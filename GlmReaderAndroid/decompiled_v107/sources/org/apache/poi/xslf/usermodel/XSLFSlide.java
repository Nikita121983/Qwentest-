package org.apache.poi.xslf.usermodel;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.usermodel.Background;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Notes;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.util.NotImplemented;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackground;
import org.openxmlformats.schemas.presentationml.x2006.main.CTComment;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShapeNonVisual;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlide;
import org.openxmlformats.schemas.presentationml.x2006.main.SldDocument;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/* loaded from: classes10.dex */
public final class XSLFSlide extends XSLFSheet implements Slide<XSLFShape, XSLFTextParagraph> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private XSLFCommentAuthors _commentAuthors;
    private XSLFComments _comments;
    private XSLFSlideLayout _layout;
    private XSLFNotes _notes;
    private final CTSlide _slide;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFSlide() {
        this._slide = prototype();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFSlide(PackagePart part) throws IOException, XmlException {
        super(part);
        try {
            InputStream stream = getPackagePart().getInputStream();
            try {
                Document _doc = DocumentHelper.readDocument(stream);
                if (stream != null) {
                    stream.close();
                }
                SldDocument doc = SldDocument.Factory.parse(_doc, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                this._slide = doc.getSld();
            } finally {
            }
        } catch (SAXException e) {
            throw new IOException(e);
        }
    }

    private static CTSlide prototype() {
        CTSlide ctSlide = CTSlide.Factory.newInstance();
        CTCommonSlideData cSld = ctSlide.addNewCSld();
        CTGroupShape spTree = cSld.addNewSpTree();
        CTGroupShapeNonVisual nvGrpSpPr = spTree.addNewNvGrpSpPr();
        CTNonVisualDrawingProps cnvPr = nvGrpSpPr.addNewCNvPr();
        cnvPr.setId(1L);
        cnvPr.setName("");
        nvGrpSpPr.addNewCNvGrpSpPr();
        nvGrpSpPr.addNewNvPr();
        CTGroupShapeProperties grpSpr = spTree.addNewGrpSpPr();
        CTGroupTransform2D xfrm = grpSpr.addNewXfrm();
        CTPoint2D off = xfrm.addNewOff();
        off.setX(0);
        off.setY(0);
        CTPositiveSize2D ext = xfrm.addNewExt();
        ext.setCx(0L);
        ext.setCy(0L);
        CTPoint2D choff = xfrm.addNewChOff();
        choff.setX(0);
        choff.setY(0);
        CTPositiveSize2D chExt = xfrm.addNewChExt();
        chExt.setCx(0L);
        chExt.setCy(0L);
        ctSlide.addNewClrMapOvr().addNewMasterClrMapping();
        return ctSlide;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    public CTSlide getXmlObject() {
        return this._slide;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    protected String getRootElementName() {
        return "sld";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeChartRelation(XSLFChart chart) {
        removeRelation(chart);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeLayoutRelation(XSLFSlideLayout layout) {
        removeRelation((POIXMLDocumentPart) layout, false);
    }

    @Override // org.apache.poi.sl.usermodel.Sheet
    public MasterSheet<XSLFShape, XSLFTextParagraph> getMasterSheet() {
        return getSlideLayout();
    }

    @Override // org.apache.poi.sl.usermodel.Slide
    public MasterSheet<XSLFShape, XSLFTextParagraph> getSlideLayout() {
        if (this._layout == null) {
            for (POIXMLDocumentPart p : getRelations()) {
                if (p instanceof XSLFSlideLayout) {
                    this._layout = (XSLFSlideLayout) p;
                }
            }
        }
        if (this._layout == null) {
            throw new IllegalArgumentException("SlideLayout was not found for " + this);
        }
        return this._layout;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [org.apache.poi.xslf.usermodel.XSLFSlideLayout] */
    public XSLFSlideMaster getSlideMaster() {
        return getSlideLayout().getSlideMaster();
    }

    public XSLFComments getCommentsPart() {
        if (this._comments == null) {
            Iterator<POIXMLDocumentPart> it = getRelations().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                POIXMLDocumentPart p = it.next();
                if (p instanceof XSLFComments) {
                    this._comments = (XSLFComments) p;
                    break;
                }
            }
        }
        return this._comments;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [org.apache.poi.xslf.usermodel.XMLSlideShow] */
    public XSLFCommentAuthors getCommentAuthorsPart() {
        if (this._commentAuthors == null) {
            for (POIXMLDocumentPart p : getRelations()) {
                if (p instanceof XSLFCommentAuthors) {
                    this._commentAuthors = (XSLFCommentAuthors) p;
                    return this._commentAuthors;
                }
            }
            for (POIXMLDocumentPart p2 : getSlideShow().getRelations()) {
                if (p2 instanceof XSLFCommentAuthors) {
                    this._commentAuthors = (XSLFCommentAuthors) p2;
                    return this._commentAuthors;
                }
            }
            return null;
        }
        return null;
    }

    @Override // org.apache.poi.sl.usermodel.Slide
    public List<XSLFComment> getComments() {
        List<XSLFComment> comments = new ArrayList<>();
        XSLFComments xComments = getCommentsPart();
        XSLFCommentAuthors xAuthors = getCommentAuthorsPart();
        if (xComments != null) {
            for (CTComment xc : xComments.getCTCommentsList().getCmArray()) {
                comments.add(new XSLFComment(xc, xAuthors));
            }
        }
        return comments;
    }

    @Override // org.apache.poi.sl.usermodel.Slide
    public Notes<XSLFShape, XSLFTextParagraph> getNotes() {
        if (this._notes == null) {
            for (POIXMLDocumentPart p : getRelations()) {
                if (p instanceof XSLFNotes) {
                    this._notes = (XSLFNotes) p;
                }
            }
        }
        if (this._notes == null) {
            return null;
        }
        return this._notes;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [org.apache.poi.xslf.usermodel.XSLFNotes, org.apache.poi.ooxml.POIXMLDocumentPart] */
    public XSLFNotes removeNotes(XSLFNotesMaster master) {
        ?? notes = getNotes();
        if (notes == 0) {
            return null;
        }
        notes.removeRelations(this, master);
        removeRelation((POIXMLDocumentPart) notes);
        this._notes = null;
        return notes;
    }

    @Override // org.apache.poi.sl.usermodel.Slide
    public String getTitle() {
        XSLFTextShape txt = getTextShapeByType(Placeholder.TITLE);
        if (txt == null) {
            return null;
        }
        return txt.getText();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [org.apache.poi.xslf.usermodel.XSLFSlideLayout] */
    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    public XSLFTheme getTheme() {
        return getSlideLayout().getSlideMaster().getTheme();
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSheet, org.apache.poi.sl.usermodel.Sheet
    public Background<XSLFShape, XSLFTextParagraph> getBackground() {
        if (this._slide.getCSld() != null && this._slide.getCSld().getBg() != null) {
            return new XSLFBackground(this._slide.getCSld().getBg(), this);
        }
        return getMasterSheet().getBackground();
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSheet, org.apache.poi.sl.usermodel.Sheet
    public boolean getFollowMasterGraphics() {
        return this._slide.getShowMasterSp();
    }

    public void setFollowMasterGraphics(boolean value) {
        this._slide.setShowMasterSp(value);
    }

    @Override // org.apache.poi.sl.usermodel.Slide
    public boolean getFollowMasterObjects() {
        return getFollowMasterGraphics();
    }

    @Override // org.apache.poi.sl.usermodel.Slide
    public void setFollowMasterObjects(boolean follow) {
        setFollowMasterGraphics(follow);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [org.apache.poi.xslf.usermodel.XSLFNotes, org.apache.poi.xslf.usermodel.XSLFSheet] */
    /* JADX WARN: Type inference failed for: r1v5, types: [org.apache.poi.xslf.usermodel.XMLSlideShow] */
    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    public XSLFSlide importContent(XSLFSheet src) {
        super.importContent(src);
        if (!(src instanceof XSLFSlide)) {
            return this;
        }
        ?? notes = ((XSLFSlide) src).getNotes();
        if (notes != 0) {
            getSlideShow().getNotesSlide(this).importContent(notes);
        }
        CTBackground bgOther = ((XSLFSlide) src)._slide.getCSld().getBg();
        if (bgOther == null) {
            return this;
        }
        CTBackground bgThis = this._slide.getCSld().getBg();
        if (bgThis != null) {
            if (bgThis.isSetBgPr() && bgThis.getBgPr().isSetBlipFill()) {
                String oldId = bgThis.getBgPr().getBlipFill().getBlip().getEmbed();
                removeRelation(oldId);
            }
            this._slide.getCSld().unsetBg();
        }
        CTBackground bgThis2 = (CTBackground) this._slide.getCSld().addNewBg().set(bgOther);
        if (bgOther.isSetBgPr() && bgOther.getBgPr().isSetBlipFill()) {
            String idOther = bgOther.getBgPr().getBlipFill().getBlip().getEmbed();
            String idThis = importBlip(idOther, src);
            bgThis2.getBgPr().getBlipFill().getBlip().setEmbed(idThis);
        }
        return this;
    }

    @Override // org.apache.poi.sl.usermodel.Slide
    public boolean getFollowMasterBackground() {
        return false;
    }

    @Override // org.apache.poi.sl.usermodel.Slide
    @NotImplemented
    public void setFollowMasterBackground(boolean follow) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.poi.sl.usermodel.Slide
    public boolean getFollowMasterColourScheme() {
        return false;
    }

    @Override // org.apache.poi.sl.usermodel.Slide
    @NotImplemented
    public void setFollowMasterColourScheme(boolean follow) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.poi.sl.usermodel.Slide
    @NotImplemented
    public void setNotes(Notes<XSLFShape, XSLFTextParagraph> notes) {
        if (!(notes instanceof XSLFNotes)) {
            throw new AssertionError();
        }
    }

    @Override // org.apache.poi.sl.usermodel.Slide
    public int getSlideNumber() {
        int idx = getSlideShow().getSlides().indexOf(this);
        return idx == -1 ? idx : idx + 1;
    }

    @Override // org.apache.poi.xslf.usermodel.XSLFSheet, org.apache.poi.sl.usermodel.Sheet
    public void draw(Graphics2D graphics) {
        DrawFactory drawFact = DrawFactory.getInstance(graphics);
        Drawable draw = drawFact.getDrawable((Slide<?, ?>) this);
        draw.draw(graphics);
    }

    @Override // org.apache.poi.sl.usermodel.Slide
    public void setHidden(boolean hidden) {
        CTSlide sld = getXmlObject();
        if (hidden) {
            sld.setShow(false);
        } else if (sld.isSetShow()) {
            sld.unsetShow();
        }
    }

    @Override // org.apache.poi.sl.usermodel.Slide
    public boolean isHidden() {
        CTSlide sld = getXmlObject();
        return sld.isSetShow() && !sld.getShow();
    }

    @Override // org.apache.poi.sl.usermodel.Slide
    public String getSlideName() {
        CTCommonSlideData cSld = getXmlObject().getCSld();
        return cSld.isSetName() ? cSld.getName() : "Slide" + getSlideNumber();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.poi.xslf.usermodel.XSLFSheet
    public String mapSchemeColor(String schemeColor) {
        return mapSchemeColor(this._slide.getClrMapOvr(), schemeColor);
    }
}
