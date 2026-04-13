package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPlaceholder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;

/* loaded from: classes12.dex */
public class CTPlaceholderImpl extends XmlComplexContentImpl implements CTPlaceholder {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "docPart")};
    private static final long serialVersionUID = 1;

    public CTPlaceholderImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPlaceholder
    public CTString getDocPart() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPlaceholder
    public void setDocPart(CTString docPart) {
        generatedSetterHelperImpl(docPart, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPlaceholder
    public CTString addNewDocPart() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
