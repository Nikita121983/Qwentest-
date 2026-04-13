package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTransition;
import org.openxmlformats.schemas.presentationml.x2006.main.STSlideLayoutType;

/* loaded from: classes11.dex */
public class CTSlideLayoutImpl extends XmlComplexContentImpl implements CTSlideLayout {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "cSld"), new QName(XSSFRelation.NS_PRESENTATIONML, "clrMapOvr"), new QName(XSSFRelation.NS_PRESENTATIONML, "transition"), new QName(XSSFRelation.NS_PRESENTATIONML, "timing"), new QName(XSSFRelation.NS_PRESENTATIONML, "hf"), new QName(XSSFRelation.NS_PRESENTATIONML, "extLst"), new QName("", "showMasterSp"), new QName("", "showMasterPhAnim"), new QName("", "matchingName"), new QName("", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY), new QName("", "preserve"), new QName("", "userDrawn")};
    private static final long serialVersionUID = 1;

    public CTSlideLayoutImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public CTCommonSlideData getCSld() {
        CTCommonSlideData cTCommonSlideData;
        synchronized (monitor()) {
            check_orphaned();
            CTCommonSlideData target = (CTCommonSlideData) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTCommonSlideData = target == null ? null : target;
        }
        return cTCommonSlideData;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void setCSld(CTCommonSlideData cSld) {
        generatedSetterHelperImpl(cSld, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public CTCommonSlideData addNewCSld() {
        CTCommonSlideData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCommonSlideData) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public CTColorMappingOverride getClrMapOvr() {
        CTColorMappingOverride cTColorMappingOverride;
        synchronized (monitor()) {
            check_orphaned();
            CTColorMappingOverride target = (CTColorMappingOverride) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTColorMappingOverride = target == null ? null : target;
        }
        return cTColorMappingOverride;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public boolean isSetClrMapOvr() {
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

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void setClrMapOvr(CTColorMappingOverride clrMapOvr) {
        generatedSetterHelperImpl(clrMapOvr, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public CTColorMappingOverride addNewClrMapOvr() {
        CTColorMappingOverride target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColorMappingOverride) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void unsetClrMapOvr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public CTSlideTransition getTransition() {
        CTSlideTransition cTSlideTransition;
        synchronized (monitor()) {
            check_orphaned();
            CTSlideTransition target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTSlideTransition = target == null ? null : target;
        }
        return cTSlideTransition;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public boolean isSetTransition() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void setTransition(CTSlideTransition transition) {
        generatedSetterHelperImpl(transition, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public CTSlideTransition addNewTransition() {
        CTSlideTransition target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void unsetTransition() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public CTSlideTiming getTiming() {
        CTSlideTiming cTSlideTiming;
        synchronized (monitor()) {
            check_orphaned();
            CTSlideTiming target = (CTSlideTiming) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTSlideTiming = target == null ? null : target;
        }
        return cTSlideTiming;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public boolean isSetTiming() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void setTiming(CTSlideTiming timing) {
        generatedSetterHelperImpl(timing, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public CTSlideTiming addNewTiming() {
        CTSlideTiming target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSlideTiming) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void unsetTiming() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public CTHeaderFooter getHf() {
        CTHeaderFooter cTHeaderFooter;
        synchronized (monitor()) {
            check_orphaned();
            CTHeaderFooter target = (CTHeaderFooter) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTHeaderFooter = target == null ? null : target;
        }
        return cTHeaderFooter;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public boolean isSetHf() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void setHf(CTHeaderFooter hf) {
        generatedSetterHelperImpl(hf, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public CTHeaderFooter addNewHf() {
        CTHeaderFooter target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHeaderFooter) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void unsetHf() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public CTExtensionListModify getExtLst() {
        CTExtensionListModify cTExtensionListModify;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionListModify target = get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTExtensionListModify = target == null ? null : target;
        }
        return cTExtensionListModify;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void setExtLst(CTExtensionListModify extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public CTExtensionListModify addNewExtLst() {
        CTExtensionListModify target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public boolean getShowMasterSp() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[6]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public XmlBoolean xgetShowMasterSp() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[6]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public boolean isSetShowMasterSp() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void setShowMasterSp(boolean showMasterSp) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setBooleanValue(showMasterSp);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void xsetShowMasterSp(XmlBoolean showMasterSp) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(showMasterSp);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void unsetShowMasterSp() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public boolean getShowMasterPhAnim() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[7]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public XmlBoolean xgetShowMasterPhAnim() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[7]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public boolean isSetShowMasterPhAnim() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void setShowMasterPhAnim(boolean showMasterPhAnim) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setBooleanValue(showMasterPhAnim);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void xsetShowMasterPhAnim(XmlBoolean showMasterPhAnim) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(showMasterPhAnim);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void unsetShowMasterPhAnim() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public String getMatchingName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[8]);
            }
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public XmlString xgetMatchingName() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (XmlString) get_default_attribute_value(PROPERTY_QNAME[8]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public boolean isSetMatchingName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void setMatchingName(String matchingName) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setStringValue(matchingName);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void xsetMatchingName(XmlString matchingName) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(matchingName);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void unsetMatchingName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public STSlideLayoutType.Enum getType() {
        STSlideLayoutType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[9]);
            }
            r1 = target == null ? null : (STSlideLayoutType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public STSlideLayoutType xgetType() {
        STSlideLayoutType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSlideLayoutType) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (STSlideLayoutType) get_default_attribute_value(PROPERTY_QNAME[9]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public boolean isSetType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void setType(STSlideLayoutType.Enum type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setEnumValue(type);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void xsetType(STSlideLayoutType type) {
        synchronized (monitor()) {
            check_orphaned();
            STSlideLayoutType target = (STSlideLayoutType) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (STSlideLayoutType) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(type);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public boolean getPreserve() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[10]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public XmlBoolean xgetPreserve() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[10]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public boolean isSetPreserve() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void setPreserve(boolean preserve) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setBooleanValue(preserve);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void xsetPreserve(XmlBoolean preserve) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(preserve);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void unsetPreserve() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public boolean getUserDrawn() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[11]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public XmlBoolean xgetUserDrawn() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[11]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public boolean isSetUserDrawn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void setUserDrawn(boolean userDrawn) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.setBooleanValue(userDrawn);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void xsetUserDrawn(XmlBoolean userDrawn) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.set(userDrawn);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideLayout
    public void unsetUserDrawn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[11]);
        }
    }
}
