package org.apache.poi.xslf.model;

import java.util.function.Consumer;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

@Internal
/* loaded from: classes10.dex */
public final class ParagraphPropertyFetcher<T> extends PropertyFetcher<T> {
    static final String DML_NS = "http://schemas.openxmlformats.org/drawingml/2006/main";
    static final String PML_NS = "http://schemas.openxmlformats.org/presentationml/2006/main";
    int _level;
    private final ParaPropFetcher<T> fetcher;
    private final XSLFTextParagraph para;
    private static final QName[] TX_BODY = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "txBody")};
    private static final QName[] LST_STYLE = {new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lstStyle")};

    /* loaded from: classes10.dex */
    public interface ParaPropFetcher<S> {
        void fetch(CTTextParagraphProperties cTTextParagraphProperties, Consumer<S> consumer);
    }

    public ParagraphPropertyFetcher(XSLFTextParagraph para, ParaPropFetcher<T> fetcher) {
        this.para = para;
        this._level = para.getIndentLevel();
        this.fetcher = fetcher;
    }

    @Override // org.apache.poi.xslf.model.PropertyFetcher
    public boolean fetch(XSLFShape shape) {
        try {
            fetchProp(select(shape, this._level));
        } catch (XmlException e) {
        }
        return isSet();
    }

    public T fetchProperty(XSLFShape shape) {
        Sheet<XSLFShape, XSLFTextParagraph> sheet = shape.getSheet();
        fetchParagraphProp();
        if (!(sheet instanceof XSLFSlideMaster)) {
            fetchShapeProp(shape);
            fetchThemeProp(shape);
        }
        fetchMasterProp();
        if (isSet()) {
            return getValue();
        }
        return null;
    }

    private void fetchParagraphProp() {
        fetchProp(this.para.getXmlObject().getPPr());
    }

    private void fetchShapeProp(XSLFShape shape) {
        if (!isSet()) {
            shape.fetchShapeProperty(this);
        }
    }

    private void fetchThemeProp(XSLFShape shape) {
        if (!isSet()) {
            fetchProp(getThemeProps(shape, this._level));
        }
    }

    private void fetchMasterProp() {
        if (!isSet()) {
            fetchProp(this.para.getDefaultMasterStyle());
        }
    }

    private void fetchProp(CTTextParagraphProperties props) {
        if (props != null) {
            this.fetcher.fetch(props, new Consumer() { // from class: org.apache.poi.xslf.model.ParagraphPropertyFetcher$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ParagraphPropertyFetcher.this.setValue(obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CTTextParagraphProperties select(XSLFShape shape, int level) throws XmlException {
        QName[] lvlProp = {new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lvl" + (level + 1) + "pPr")};
        return (CTTextParagraphProperties) XPathHelper.selectProperty(shape.getXmlObject(), CTTextParagraphProperties.class, new XSLFShape.ReparseFactory() { // from class: org.apache.poi.xslf.model.ParagraphPropertyFetcher$$ExternalSyntheticLambda0
            @Override // org.apache.poi.xslf.usermodel.XSLFShape.ReparseFactory
            public final XmlObject parse(XMLStreamReader xMLStreamReader) {
                return ParagraphPropertyFetcher.parse(xMLStreamReader);
            }
        }, TX_BODY, LST_STYLE, lvlProp);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CTTextParagraphProperties parse(XMLStreamReader reader) throws XmlException {
        CTTextParagraph para = CTTextParagraph.Factory.parse(reader);
        if (para == null || !para.isSetPPr()) {
            return null;
        }
        return para.getPPr();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r0v2, types: [org.apache.poi.xslf.usermodel.XMLSlideShow] */
    public static CTTextParagraphProperties getThemeProps(XSLFShape shape, int _level) {
        CTTextListStyle dts;
        if (shape.isPlaceholder() || (dts = shape.getSheet().getSlideShow().getCTPresentation().getDefaultTextStyle()) == null) {
            return null;
        }
        switch (_level) {
            case 0:
                return dts.getLvl1PPr();
            case 1:
                return dts.getLvl2PPr();
            case 2:
                return dts.getLvl3PPr();
            case 3:
                return dts.getLvl4PPr();
            case 4:
                return dts.getLvl5PPr();
            case 5:
                return dts.getLvl6PPr();
            case 6:
                return dts.getLvl7PPr();
            case 7:
                return dts.getLvl8PPr();
            case 8:
                return dts.getLvl9PPr();
            default:
                return null;
        }
    }
}
