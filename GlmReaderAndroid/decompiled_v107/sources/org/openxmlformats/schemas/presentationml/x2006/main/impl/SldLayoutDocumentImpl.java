package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout;
import org.openxmlformats.schemas.presentationml.x2006.main.SldLayoutDocument;

/* loaded from: classes11.dex */
public class SldLayoutDocumentImpl extends XmlComplexContentImpl implements SldLayoutDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "sldLayout")};
    private static final long serialVersionUID = 1;

    public SldLayoutDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.SldLayoutDocument
    public CTSlideLayout getSldLayout() {
        CTSlideLayout cTSlideLayout;
        synchronized (monitor()) {
            check_orphaned();
            CTSlideLayout target = (CTSlideLayout) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTSlideLayout = target == null ? null : target;
        }
        return cTSlideLayout;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.SldLayoutDocument
    public void setSldLayout(CTSlideLayout sldLayout) {
        generatedSetterHelperImpl(sldLayout, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.SldLayoutDocument
    public CTSlideLayout addNewSldLayout() {
        CTSlideLayout target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSlideLayout) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
