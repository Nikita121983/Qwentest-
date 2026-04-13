package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet;
import org.openxmlformats.schemas.drawingml.x2006.main.ThemeDocument;

/* loaded from: classes11.dex */
public class ThemeDocumentImpl extends XmlComplexContentImpl implements ThemeDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "theme")};
    private static final long serialVersionUID = 1;

    public ThemeDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.ThemeDocument
    public CTOfficeStyleSheet getTheme() {
        CTOfficeStyleSheet cTOfficeStyleSheet;
        synchronized (monitor()) {
            check_orphaned();
            CTOfficeStyleSheet target = (CTOfficeStyleSheet) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTOfficeStyleSheet = target == null ? null : target;
        }
        return cTOfficeStyleSheet;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.ThemeDocument
    public void setTheme(CTOfficeStyleSheet theme) {
        generatedSetterHelperImpl(theme, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.ThemeDocument
    public CTOfficeStyleSheet addNewTheme() {
        CTOfficeStyleSheet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeStyleSheet) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
