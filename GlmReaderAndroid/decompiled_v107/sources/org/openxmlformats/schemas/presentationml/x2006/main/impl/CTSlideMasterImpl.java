package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayoutIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTransition;

/* loaded from: classes11.dex */
public class CTSlideMasterImpl extends XmlComplexContentImpl implements CTSlideMaster {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "cSld"), new QName(XSSFRelation.NS_PRESENTATIONML, "clrMap"), new QName(XSSFRelation.NS_PRESENTATIONML, "sldLayoutIdLst"), new QName(XSSFRelation.NS_PRESENTATIONML, "transition"), new QName(XSSFRelation.NS_PRESENTATIONML, "timing"), new QName(XSSFRelation.NS_PRESENTATIONML, "hf"), new QName(XSSFRelation.NS_PRESENTATIONML, "txStyles"), new QName(XSSFRelation.NS_PRESENTATIONML, "extLst"), new QName("", "preserve")};
    private static final long serialVersionUID = 1;

    public CTSlideMasterImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public CTCommonSlideData getCSld() {
        CTCommonSlideData cTCommonSlideData;
        synchronized (monitor()) {
            check_orphaned();
            CTCommonSlideData target = (CTCommonSlideData) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTCommonSlideData = target == null ? null : target;
        }
        return cTCommonSlideData;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public void setCSld(CTCommonSlideData cSld) {
        generatedSetterHelperImpl(cSld, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public CTCommonSlideData addNewCSld() {
        CTCommonSlideData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCommonSlideData) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public CTColorMapping getClrMap() {
        CTColorMapping cTColorMapping;
        synchronized (monitor()) {
            check_orphaned();
            CTColorMapping target = (CTColorMapping) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTColorMapping = target == null ? null : target;
        }
        return cTColorMapping;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public void setClrMap(CTColorMapping clrMap) {
        generatedSetterHelperImpl(clrMap, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public CTColorMapping addNewClrMap() {
        CTColorMapping target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColorMapping) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public CTSlideLayoutIdList getSldLayoutIdLst() {
        CTSlideLayoutIdList cTSlideLayoutIdList;
        synchronized (monitor()) {
            check_orphaned();
            CTSlideLayoutIdList target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTSlideLayoutIdList = target == null ? null : target;
        }
        return cTSlideLayoutIdList;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public boolean isSetSldLayoutIdLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public void setSldLayoutIdLst(CTSlideLayoutIdList sldLayoutIdLst) {
        generatedSetterHelperImpl(sldLayoutIdLst, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public CTSlideLayoutIdList addNewSldLayoutIdLst() {
        CTSlideLayoutIdList target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public void unsetSldLayoutIdLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public CTSlideTransition getTransition() {
        CTSlideTransition cTSlideTransition;
        synchronized (monitor()) {
            check_orphaned();
            CTSlideTransition target = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTSlideTransition = target == null ? null : target;
        }
        return cTSlideTransition;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public boolean isSetTransition() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public void setTransition(CTSlideTransition transition) {
        generatedSetterHelperImpl(transition, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public CTSlideTransition addNewTransition() {
        CTSlideTransition target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public void unsetTransition() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public CTSlideTiming getTiming() {
        CTSlideTiming cTSlideTiming;
        synchronized (monitor()) {
            check_orphaned();
            CTSlideTiming target = (CTSlideTiming) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTSlideTiming = target == null ? null : target;
        }
        return cTSlideTiming;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public boolean isSetTiming() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public void setTiming(CTSlideTiming timing) {
        generatedSetterHelperImpl(timing, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public CTSlideTiming addNewTiming() {
        CTSlideTiming target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSlideTiming) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public void unsetTiming() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public CTHeaderFooter getHf() {
        CTHeaderFooter cTHeaderFooter;
        synchronized (monitor()) {
            check_orphaned();
            CTHeaderFooter target = (CTHeaderFooter) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTHeaderFooter = target == null ? null : target;
        }
        return cTHeaderFooter;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public boolean isSetHf() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public void setHf(CTHeaderFooter hf) {
        generatedSetterHelperImpl(hf, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public CTHeaderFooter addNewHf() {
        CTHeaderFooter target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHeaderFooter) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public void unsetHf() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public CTSlideMasterTextStyles getTxStyles() {
        CTSlideMasterTextStyles cTSlideMasterTextStyles;
        synchronized (monitor()) {
            check_orphaned();
            CTSlideMasterTextStyles target = (CTSlideMasterTextStyles) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTSlideMasterTextStyles = target == null ? null : target;
        }
        return cTSlideMasterTextStyles;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public boolean isSetTxStyles() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public void setTxStyles(CTSlideMasterTextStyles txStyles) {
        generatedSetterHelperImpl(txStyles, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public CTSlideMasterTextStyles addNewTxStyles() {
        CTSlideMasterTextStyles target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSlideMasterTextStyles) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public void unsetTxStyles() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public CTExtensionListModify getExtLst() {
        CTExtensionListModify cTExtensionListModify;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionListModify target = get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTExtensionListModify = target == null ? null : target;
        }
        return cTExtensionListModify;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public void setExtLst(CTExtensionListModify extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public CTExtensionListModify addNewExtLst() {
        CTExtensionListModify target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public boolean getPreserve() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[8]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public XmlBoolean xgetPreserve() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[8]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public boolean isSetPreserve() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public void setPreserve(boolean preserve) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setBooleanValue(preserve);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public void xsetPreserve(XmlBoolean preserve) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(preserve);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster
    public void unsetPreserve() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }
}
