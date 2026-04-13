package org.apache.poi.xslf.model;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties;

/* loaded from: classes10.dex */
public abstract class TextBodyPropertyFetcher<T> extends PropertyFetcher<T> {
    private static final QName[] TX_BODY = {new QName(XSSFRelation.NS_PRESENTATIONML, "txBody")};
    private static final QName[] BODY_PR = {new QName(XSSFRelation.NS_DRAWINGML, "bodyPr")};

    public abstract boolean fetch(CTTextBodyProperties cTTextBodyProperties);

    @Override // org.apache.poi.xslf.model.PropertyFetcher
    public boolean fetch(XSLFShape shape) {
        try {
            CTTextBodyProperties props = (CTTextBodyProperties) XPathHelper.selectProperty(shape.getXmlObject(), CTTextBodyProperties.class, new XSLFShape.ReparseFactory() { // from class: org.apache.poi.xslf.model.TextBodyPropertyFetcher$$ExternalSyntheticLambda0
                @Override // org.apache.poi.xslf.usermodel.XSLFShape.ReparseFactory
                public final XmlObject parse(XMLStreamReader xMLStreamReader) {
                    CTTextBodyProperties parse;
                    parse = TextBodyPropertyFetcher.parse(xMLStreamReader);
                    return parse;
                }
            }, TX_BODY, BODY_PR);
            if (props != null) {
                return fetch(props);
            }
            return false;
        } catch (XmlException e) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CTTextBodyProperties parse(XMLStreamReader reader) throws XmlException {
        CTTextBody body = CTTextBody.Factory.parse(reader);
        if (body != null) {
            return body.getBodyPr();
        }
        return null;
    }
}
