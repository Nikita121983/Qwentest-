package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.StyleSheetDocument;

/* loaded from: classes12.dex */
public class StyleSheetDocumentImpl extends XmlComplexContentImpl implements StyleSheetDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "styleSheet")};
    private static final long serialVersionUID = 1;

    public StyleSheetDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.StyleSheetDocument
    public CTStylesheet getStyleSheet() {
        CTStylesheet cTStylesheet;
        synchronized (monitor()) {
            check_orphaned();
            CTStylesheet target = (CTStylesheet) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTStylesheet = target == null ? null : target;
        }
        return cTStylesheet;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.StyleSheetDocument
    public void setStyleSheet(CTStylesheet styleSheet) {
        generatedSetterHelperImpl(styleSheet, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.StyleSheetDocument
    public CTStylesheet addNewStyleSheet() {
        CTStylesheet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStylesheet) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
