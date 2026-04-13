package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.StylesDocument;

/* loaded from: classes12.dex */
public class StylesDocumentImpl extends XmlComplexContentImpl implements StylesDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "styles")};
    private static final long serialVersionUID = 1;

    public StylesDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.StylesDocument
    public CTStyles getStyles() {
        CTStyles cTStyles;
        synchronized (monitor()) {
            check_orphaned();
            CTStyles target = (CTStyles) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTStyles = target == null ? null : target;
        }
        return cTStyles;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.StylesDocument
    public void setStyles(CTStyles styles) {
        generatedSetterHelperImpl(styles, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.StylesDocument
    public CTStyles addNewStyles() {
        CTStyles target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStyles) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
