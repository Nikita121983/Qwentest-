package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.NumberingDocument;

/* loaded from: classes12.dex */
public class NumberingDocumentImpl extends XmlComplexContentImpl implements NumberingDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "numbering")};
    private static final long serialVersionUID = 1;

    public NumberingDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.NumberingDocument
    public CTNumbering getNumbering() {
        CTNumbering cTNumbering;
        synchronized (monitor()) {
            check_orphaned();
            CTNumbering target = (CTNumbering) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTNumbering = target == null ? null : target;
        }
        return cTNumbering;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.NumberingDocument
    public void setNumbering(CTNumbering numbering) {
        generatedSetterHelperImpl(numbering, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.NumberingDocument
    public CTNumbering addNewNumbering() {
        CTNumbering target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNumbering) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
