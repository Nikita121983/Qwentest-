package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBuildList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList;

/* loaded from: classes11.dex */
public class CTSlideTimingImpl extends XmlComplexContentImpl implements CTSlideTiming {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "tnLst"), new QName(XSSFRelation.NS_PRESENTATIONML, "bldLst"), new QName(XSSFRelation.NS_PRESENTATIONML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTSlideTimingImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming
    public CTTimeNodeList getTnLst() {
        CTTimeNodeList cTTimeNodeList;
        synchronized (monitor()) {
            check_orphaned();
            CTTimeNodeList target = (CTTimeNodeList) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTimeNodeList = target == null ? null : target;
        }
        return cTTimeNodeList;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming
    public boolean isSetTnLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming
    public void setTnLst(CTTimeNodeList tnLst) {
        generatedSetterHelperImpl(tnLst, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming
    public CTTimeNodeList addNewTnLst() {
        CTTimeNodeList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTimeNodeList) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming
    public void unsetTnLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming
    public CTBuildList getBldLst() {
        CTBuildList cTBuildList;
        synchronized (monitor()) {
            check_orphaned();
            CTBuildList target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTBuildList = target == null ? null : target;
        }
        return cTBuildList;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming
    public boolean isSetBldLst() {
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

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming
    public void setBldLst(CTBuildList bldLst) {
        generatedSetterHelperImpl(bldLst, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming
    public CTBuildList addNewBldLst() {
        CTBuildList target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming
    public void unsetBldLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming
    public CTExtensionListModify getExtLst() {
        CTExtensionListModify cTExtensionListModify;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionListModify target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTExtensionListModify = target == null ? null : target;
        }
        return cTExtensionListModify;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming
    public void setExtLst(CTExtensionListModify extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming
    public CTExtensionListModify addNewExtLst() {
        CTExtensionListModify target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }
}
