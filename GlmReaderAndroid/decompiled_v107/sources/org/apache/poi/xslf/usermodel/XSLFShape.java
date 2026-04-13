package org.apache.poi.xslf.usermodel;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.ShapeContainer;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.model.PropertyFetcher;
import org.apache.poi.xslf.usermodel.XSLFPropertiesDelegate;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrixReference;
import org.openxmlformats.schemas.drawingml.x2006.main.STSchemeColorVal;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPicture;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPlaceholder;

/* loaded from: classes10.dex */
public abstract class XSLFShape implements Shape<XSLFShape, XSLFTextParagraph> {
    static final String DML_NS = "http://schemas.openxmlformats.org/drawingml/2006/main";
    static final String PML_NS = "http://schemas.openxmlformats.org/presentationml/2006/main";
    private CTNonVisualDrawingProps _nvPr;
    private XSLFShapeContainer _parent;
    private final XmlObject _shape;
    private final XSLFSheet _sheet;
    private CTShapeStyle _spStyle;
    private static final QName[] NV_CONTAINER = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvSpPr"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvCxnSpPr"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvGrpSpPr"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvPicPr"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvGraphicFramePr")};
    private static final QName[] CNV_PROPS = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cNvPr")};

    @Internal
    /* loaded from: classes10.dex */
    public interface ReparseFactory<T extends XmlObject> {
        T parse(XMLStreamReader xMLStreamReader) throws XmlException;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XSLFShape(XmlObject shape, XSLFSheet sheet) {
        this._shape = shape;
        this._sheet = sheet;
    }

    public final XmlObject getXmlObject() {
        return this._shape;
    }

    @Override // org.apache.poi.sl.usermodel.Shape
    public Sheet<XSLFShape, XSLFTextParagraph> getSheet() {
        return this._sheet;
    }

    @Override // org.apache.poi.sl.usermodel.Shape
    public String getShapeName() {
        CTNonVisualDrawingProps nonVisualDrawingProps = getCNvPr();
        if (nonVisualDrawingProps == null) {
            return null;
        }
        return nonVisualDrawingProps.getName();
    }

    @Override // org.apache.poi.sl.usermodel.Shape
    public int getShapeId() {
        CTNonVisualDrawingProps nonVisualDrawingProps = getCNvPr();
        if (nonVisualDrawingProps == null) {
            throw new IllegalStateException("no underlying shape exists");
        }
        return Math.toIntExact(nonVisualDrawingProps.getId());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    @Internal
    public void copy(XSLFShape sh) {
        if (!getClass().isInstance(sh)) {
            throw new IllegalArgumentException("Can't copy " + sh.getClass().getSimpleName() + " into " + getClass().getSimpleName());
        }
        if (this instanceof PlaceableShape) {
            PlaceableShape<?, ?> ps = (PlaceableShape) this;
            Rectangle2D anchor = sh.getAnchor();
            if (anchor != null) {
                ps.setAnchor(anchor);
            }
        }
    }

    public void setParent(XSLFShapeContainer parent) {
        this._parent = parent;
    }

    @Override // org.apache.poi.sl.usermodel.Shape
    public ShapeContainer<XSLFShape, XSLFTextParagraph> getParent() {
        return this._parent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Type inference failed for: r0v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
    public PaintStyle getFillPaint() {
        final XSLFTheme theme = getSheet().getTheme();
        final boolean hasPlaceholder = getPlaceholder() != null;
        PropertyFetcher<PaintStyle> fetcher = new PropertyFetcher<PaintStyle>() { // from class: org.apache.poi.xslf.usermodel.XSLFShape.1
            /* JADX WARN: Type inference failed for: r0v0, types: [org.apache.poi.xslf.usermodel.XSLFSheet] */
            @Override // org.apache.poi.xslf.model.PropertyFetcher
            public boolean fetch(XSLFShape shape) {
                PackagePart pp = shape.getSheet().getPackagePart();
                if (shape instanceof XSLFPictureShape) {
                    CTPicture pic = (CTPicture) shape.getXmlObject();
                    if (pic.getBlipFill() != null) {
                        setValue(XSLFShape.this.selectPaint(pic.getBlipFill(), pp, (CTSchemeColor) null, theme));
                        return true;
                    }
                }
                XSLFPropertiesDelegate.XSLFFillProperties fp = XSLFPropertiesDelegate.getFillDelegate(shape.getShapeProperties());
                if (fp == null) {
                    return false;
                }
                if (fp.isSetNoFill()) {
                    setValue(null);
                    return true;
                }
                PaintStyle paint = XSLFShape.this.selectPaint(fp, null, pp, theme, hasPlaceholder);
                if (paint != null) {
                    setValue(paint);
                    return true;
                }
                CTShapeStyle style = shape.getSpStyle();
                if (style != null) {
                    paint = XSLFShape.this.selectPaint(XSLFPropertiesDelegate.getFillDelegate(style.getFillRef()), null, pp, theme, hasPlaceholder);
                }
                if (paint == null) {
                    return false;
                }
                setValue(paint);
                return true;
            }
        };
        fetchShapeProperty(fetcher);
        return fetcher.getValue();
    }

    protected CTBackgroundProperties getBgPr() {
        return (CTBackgroundProperties) getChild(CTBackgroundProperties.class, "http://schemas.openxmlformats.org/presentationml/2006/main", "bgPr");
    }

    protected CTStyleMatrixReference getBgRef() {
        return (CTStyleMatrixReference) getChild(CTStyleMatrixReference.class, "http://schemas.openxmlformats.org/presentationml/2006/main", "bgRef");
    }

    protected CTGroupShapeProperties getGrpSpPr() {
        return (CTGroupShapeProperties) getChild(CTGroupShapeProperties.class, "http://schemas.openxmlformats.org/presentationml/2006/main", "grpSpPr");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CTNonVisualDrawingProps getCNvPr() {
        try {
            if (this._nvPr == null) {
                this._nvPr = (CTNonVisualDrawingProps) XPathHelper.selectProperty(getXmlObject(), CTNonVisualDrawingProps.class, null, NV_CONTAINER, CNV_PROPS);
            }
            return this._nvPr;
        } catch (XmlException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CTShapeStyle getSpStyle() {
        if (this._spStyle == null) {
            this._spStyle = (CTShapeStyle) getChild(CTShapeStyle.class, "http://schemas.openxmlformats.org/presentationml/2006/main", "style");
        }
        return this._spStyle;
    }

    protected <T extends XmlObject> T getChild(Class<T> cls, String str, String str2) {
        T t = null;
        XmlCursor newCursor = getXmlObject().newCursor();
        try {
            if (newCursor.toChild(str, str2)) {
                t = (T) newCursor.getObject();
            }
            if (newCursor.toChild("http://schemas.openxmlformats.org/drawingml/2006/main", str2)) {
                t = (T) newCursor.getObject();
            }
            if (newCursor != null) {
                newCursor.close();
            }
            return t;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (newCursor != null) {
                    try {
                        newCursor.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public boolean isPlaceholder() {
        return getPlaceholderDetails().getCTPlaceholder(false) != null;
    }

    public Placeholder getPlaceholder() {
        return getPlaceholderDetails().getPlaceholder();
    }

    public void setPlaceholder(Placeholder placeholder) {
        getPlaceholderDetails().setPlaceholder(placeholder);
    }

    public XSLFPlaceholderDetails getPlaceholderDetails() {
        return new XSLFPlaceholderDetails(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T extends XmlObject> T selectProperty(Class<T> cls, String str) {
        XmlObject[] selectPath = getXmlObject().selectPath(str);
        if (selectPath.length != 0 && cls.isInstance(selectPath[0])) {
            return (T) selectPath[0];
        }
        return null;
    }

    @Internal
    public boolean fetchShapeProperty(PropertyFetcher<?> visitor) {
        if (visitor.fetch(this)) {
            return true;
        }
        CTPlaceholder ph = getPlaceholderDetails().getCTPlaceholder(false);
        if (ph == null) {
            return false;
        }
        MasterSheet<XSLFShape, XSLFTextParagraph> sm = getSheet().getMasterSheet();
        if (sm instanceof XSLFSlideLayout) {
            XSLFSlideLayout slideLayout = (XSLFSlideLayout) sm;
            XSLFSimpleShape placeholderShape = slideLayout.getPlaceholder(ph);
            if (placeholderShape != null && visitor.fetch(placeholderShape)) {
                return true;
            }
            sm = slideLayout.getMasterSheet();
        }
        if (!(sm instanceof XSLFSlideMaster)) {
            return false;
        }
        XSLFSlideMaster master = (XSLFSlideMaster) sm;
        int textType = getPlaceholderType(ph);
        XSLFSimpleShape masterShape = master.getPlaceholderByType(textType);
        return masterShape != null && visitor.fetch(masterShape);
    }

    private static int getPlaceholderType(CTPlaceholder ph) {
        if (!ph.isSetType()) {
            return 2;
        }
        switch (ph.getType().intValue()) {
            case 1:
            case 3:
                return 1;
            case 2:
            case 4:
            default:
                return 2;
            case 5:
            case 6:
            case 7:
                return ph.getType().intValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PaintStyle selectPaint(XSLFPropertiesDelegate.XSLFFillProperties fp, CTSchemeColor phClr, PackagePart parentPart, XSLFTheme theme, boolean hasPlaceholder) {
        if (fp == null || fp.isSetNoFill()) {
            return null;
        }
        if (fp.isSetSolidFill()) {
            return selectPaint(fp.getSolidFill(), phClr, theme);
        }
        if (fp.isSetBlipFill()) {
            return selectPaint(fp.getBlipFill(), parentPart, phClr, theme);
        }
        if (fp.isSetGradFill()) {
            return selectPaint(fp.getGradFill(), phClr, theme);
        }
        if (fp.isSetMatrixStyle()) {
            return selectPaint(fp.getMatrixStyle(), theme, fp.isLineStyle(), hasPlaceholder);
        }
        if (phClr == null) {
            return null;
        }
        return selectPaint(phClr, theme);
    }

    protected PaintStyle selectPaint(CTSchemeColor phClr, XSLFTheme theme) {
        XSLFColor c = new XSLFColor(null, theme, phClr, this._sheet);
        return DrawPaint.createSolidPaint(c.getColorStyle());
    }

    protected PaintStyle selectPaint(CTSolidColorFillProperties solidFill, CTSchemeColor phClr, XSLFTheme theme) {
        CTSchemeColor nestedPhClr = solidFill.getSchemeClr();
        boolean useNested = (nestedPhClr == null || nestedPhClr.getVal() == null || STSchemeColorVal.PH_CLR.equals(nestedPhClr.getVal())) ? false : true;
        XSLFColor c = new XSLFColor(solidFill, theme, useNested ? nestedPhClr : phClr, this._sheet);
        return DrawPaint.createSolidPaint(c.getColorStyle());
    }

    protected PaintStyle selectPaint(CTBlipFillProperties blipFill, PackagePart parentPart, CTSchemeColor phClr, XSLFTheme theme) {
        return new XSLFTexturePaint(this, blipFill, parentPart, phClr, theme, this._sheet);
    }

    protected PaintStyle selectPaint(CTGradientFillProperties gradFill, CTSchemeColor phClr, XSLFTheme theme) {
        return new XSLFGradientPaint(gradFill, phClr, theme, this._sheet);
    }

    protected PaintStyle selectPaint(CTStyleMatrixReference fillRef, XSLFTheme theme, boolean isLineStyle, boolean hasPlaceholder) {
        XmlObject styleLst;
        long childIdx;
        if (fillRef == null) {
            return null;
        }
        long idx = fillRef.getIdx();
        if (theme == null || theme.getXmlObject() == null || theme.getXmlObject().getThemeElements() == null) {
            throw new IllegalArgumentException("Could not retrieve theme elements from shape");
        }
        CTStyleMatrix matrix = theme.getXmlObject().getThemeElements().getFmtScheme();
        if (idx >= 1 && idx <= 999) {
            long childIdx2 = idx - 1;
            styleLst = isLineStyle ? matrix.getLnStyleLst() : matrix.getFillStyleLst();
            childIdx = childIdx2;
        } else {
            if (idx < 1001) {
                return null;
            }
            long childIdx3 = idx - 1001;
            styleLst = matrix.getBgFillStyleLst();
            childIdx = childIdx3;
        }
        XSLFPropertiesDelegate.XSLFFillProperties fp = null;
        XmlCursor cur = styleLst.newCursor();
        try {
            if (cur.toChild(Math.toIntExact(childIdx))) {
                fp = XSLFPropertiesDelegate.getFillDelegate(cur.getObject());
            }
            if (cur != null) {
                cur.close();
            }
            CTSchemeColor phClr = fillRef.getSchemeClr();
            PaintStyle res = selectPaint(fp, phClr, theme.getPackagePart(), theme, hasPlaceholder);
            if (res != null || hasPlaceholder) {
                return res;
            }
            XSLFColor col = new XSLFColor(fillRef, theme, phClr, this._sheet);
            return DrawPaint.createSolidPaint(col.getColorStyle());
        } finally {
        }
    }

    @Override // org.apache.poi.sl.usermodel.Shape
    public void draw(Graphics2D graphics, Rectangle2D bounds) {
        DrawFactory.getInstance(graphics).drawShape(graphics, this, bounds);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XmlObject getShapeProperties() {
        return getChild(CTShapeProperties.class, "http://schemas.openxmlformats.org/presentationml/2006/main", "spPr");
    }
}
