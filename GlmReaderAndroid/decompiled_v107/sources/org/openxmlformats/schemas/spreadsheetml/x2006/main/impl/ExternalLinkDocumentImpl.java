package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalLink;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.ExternalLinkDocument;

/* loaded from: classes12.dex */
public class ExternalLinkDocumentImpl extends XmlComplexContentImpl implements ExternalLinkDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "externalLink")};
    private static final long serialVersionUID = 1;

    public ExternalLinkDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.ExternalLinkDocument
    public CTExternalLink getExternalLink() {
        CTExternalLink cTExternalLink;
        synchronized (monitor()) {
            check_orphaned();
            CTExternalLink target = (CTExternalLink) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTExternalLink = target == null ? null : target;
        }
        return cTExternalLink;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.ExternalLinkDocument
    public void setExternalLink(CTExternalLink externalLink) {
        generatedSetterHelperImpl(externalLink, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.ExternalLinkDocument
    public CTExternalLink addNewExternalLink() {
        CTExternalLink target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExternalLink) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
