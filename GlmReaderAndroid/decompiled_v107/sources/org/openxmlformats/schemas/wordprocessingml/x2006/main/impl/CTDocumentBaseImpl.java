package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocumentBase;

/* loaded from: classes12.dex */
public class CTDocumentBaseImpl extends XmlComplexContentImpl implements CTDocumentBase {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "background")};
    private static final long serialVersionUID = 1;

    public CTDocumentBaseImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocumentBase
    public CTBackground getBackground() {
        CTBackground cTBackground;
        synchronized (monitor()) {
            check_orphaned();
            CTBackground target = (CTBackground) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTBackground = target == null ? null : target;
        }
        return cTBackground;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocumentBase
    public boolean isSetBackground() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocumentBase
    public void setBackground(CTBackground background) {
        generatedSetterHelperImpl(background, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocumentBase
    public CTBackground addNewBackground() {
        CTBackground target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBackground) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocumentBase
    public void unsetBackground() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }
}
