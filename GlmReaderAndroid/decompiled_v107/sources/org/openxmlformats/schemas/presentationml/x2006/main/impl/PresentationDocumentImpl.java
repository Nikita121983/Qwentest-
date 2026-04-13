package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation;
import org.openxmlformats.schemas.presentationml.x2006.main.PresentationDocument;

/* loaded from: classes11.dex */
public class PresentationDocumentImpl extends XmlComplexContentImpl implements PresentationDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "presentation")};
    private static final long serialVersionUID = 1;

    public PresentationDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.PresentationDocument
    public CTPresentation getPresentation() {
        CTPresentation cTPresentation;
        synchronized (monitor()) {
            check_orphaned();
            CTPresentation target = (CTPresentation) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTPresentation = target == null ? null : target;
        }
        return cTPresentation;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.PresentationDocument
    public void setPresentation(CTPresentation presentation) {
        generatedSetterHelperImpl(presentation, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.PresentationDocument
    public CTPresentation addNewPresentation() {
        CTPresentation target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPresentation) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
