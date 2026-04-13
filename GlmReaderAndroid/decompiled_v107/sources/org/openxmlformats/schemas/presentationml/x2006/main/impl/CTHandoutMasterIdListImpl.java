package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTHandoutMasterIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTHandoutMasterIdListEntry;

/* loaded from: classes11.dex */
public class CTHandoutMasterIdListImpl extends XmlComplexContentImpl implements CTHandoutMasterIdList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "handoutMasterId")};
    private static final long serialVersionUID = 1;

    public CTHandoutMasterIdListImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTHandoutMasterIdList
    public CTHandoutMasterIdListEntry getHandoutMasterId() {
        CTHandoutMasterIdListEntry cTHandoutMasterIdListEntry;
        synchronized (monitor()) {
            check_orphaned();
            CTHandoutMasterIdListEntry target = (CTHandoutMasterIdListEntry) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTHandoutMasterIdListEntry = target == null ? null : target;
        }
        return cTHandoutMasterIdListEntry;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTHandoutMasterIdList
    public boolean isSetHandoutMasterId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTHandoutMasterIdList
    public void setHandoutMasterId(CTHandoutMasterIdListEntry handoutMasterId) {
        generatedSetterHelperImpl(handoutMasterId, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTHandoutMasterIdList
    public CTHandoutMasterIdListEntry addNewHandoutMasterId() {
        CTHandoutMasterIdListEntry target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHandoutMasterIdListEntry) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTHandoutMasterIdList
    public void unsetHandoutMasterId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }
}
