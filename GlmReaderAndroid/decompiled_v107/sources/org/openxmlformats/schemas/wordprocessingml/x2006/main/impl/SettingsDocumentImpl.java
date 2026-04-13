package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.SettingsDocument;

/* loaded from: classes12.dex */
public class SettingsDocumentImpl extends XmlComplexContentImpl implements SettingsDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "settings")};
    private static final long serialVersionUID = 1;

    public SettingsDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.SettingsDocument
    public CTSettings getSettings() {
        CTSettings cTSettings;
        synchronized (monitor()) {
            check_orphaned();
            CTSettings target = (CTSettings) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTSettings = target == null ? null : target;
        }
        return cTSettings;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.SettingsDocument
    public void setSettings(CTSettings settings) {
        generatedSetterHelperImpl(settings, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.SettingsDocument
    public CTSettings addNewSettings() {
        CTSettings target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSettings) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
