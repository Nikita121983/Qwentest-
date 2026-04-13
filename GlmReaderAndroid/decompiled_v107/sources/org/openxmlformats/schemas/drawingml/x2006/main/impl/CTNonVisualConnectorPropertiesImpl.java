package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnection;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectorLocking;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;

/* loaded from: classes11.dex */
public class CTNonVisualConnectorPropertiesImpl extends XmlComplexContentImpl implements CTNonVisualConnectorProperties {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "cxnSpLocks"), new QName(XSSFRelation.NS_DRAWINGML, "stCxn"), new QName(XSSFRelation.NS_DRAWINGML, "endCxn"), new QName(XSSFRelation.NS_DRAWINGML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTNonVisualConnectorPropertiesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties
    public CTConnectorLocking getCxnSpLocks() {
        CTConnectorLocking cTConnectorLocking;
        synchronized (monitor()) {
            check_orphaned();
            CTConnectorLocking target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTConnectorLocking = target == null ? null : target;
        }
        return cTConnectorLocking;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties
    public boolean isSetCxnSpLocks() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties
    public void setCxnSpLocks(CTConnectorLocking cxnSpLocks) {
        generatedSetterHelperImpl(cxnSpLocks, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties
    public CTConnectorLocking addNewCxnSpLocks() {
        CTConnectorLocking target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties
    public void unsetCxnSpLocks() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties
    public CTConnection getStCxn() {
        CTConnection cTConnection;
        synchronized (monitor()) {
            check_orphaned();
            CTConnection target = (CTConnection) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTConnection = target == null ? null : target;
        }
        return cTConnection;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties
    public boolean isSetStCxn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties
    public void setStCxn(CTConnection stCxn) {
        generatedSetterHelperImpl(stCxn, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties
    public CTConnection addNewStCxn() {
        CTConnection target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTConnection) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties
    public void unsetStCxn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties
    public CTConnection getEndCxn() {
        CTConnection cTConnection;
        synchronized (monitor()) {
            check_orphaned();
            CTConnection target = (CTConnection) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTConnection = target == null ? null : target;
        }
        return cTConnection;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties
    public boolean isSetEndCxn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties
    public void setEndCxn(CTConnection endCxn) {
        generatedSetterHelperImpl(endCxn, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties
    public CTConnection addNewEndCxn() {
        CTConnection target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTConnection) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties
    public void unsetEndCxn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties
    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTOfficeArtExtensionList target = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTOfficeArtExtensionList = target == null ? null : target;
        }
        return cTOfficeArtExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties
    public void setExtLst(CTOfficeArtExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties
    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
