package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveFixedPercentage;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement;

/* loaded from: classes11.dex */
public class CTTLCommonMediaNodeDataImpl extends XmlComplexContentImpl implements CTTLCommonMediaNodeData {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "cTn"), new QName(XSSFRelation.NS_PRESENTATIONML, "tgtEl"), new QName("", "vol"), new QName("", "mute"), new QName("", "numSld"), new QName("", "showWhenStopped")};
    private static final long serialVersionUID = 1;

    public CTTLCommonMediaNodeDataImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public CTTLCommonTimeNodeData getCTn() {
        CTTLCommonTimeNodeData cTTLCommonTimeNodeData;
        synchronized (monitor()) {
            check_orphaned();
            CTTLCommonTimeNodeData target = (CTTLCommonTimeNodeData) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTLCommonTimeNodeData = target == null ? null : target;
        }
        return cTTLCommonTimeNodeData;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public void setCTn(CTTLCommonTimeNodeData cTn) {
        generatedSetterHelperImpl(cTn, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public CTTLCommonTimeNodeData addNewCTn() {
        CTTLCommonTimeNodeData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTLCommonTimeNodeData) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public CTTLTimeTargetElement getTgtEl() {
        CTTLTimeTargetElement cTTLTimeTargetElement;
        synchronized (monitor()) {
            check_orphaned();
            CTTLTimeTargetElement target = (CTTLTimeTargetElement) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTTLTimeTargetElement = target == null ? null : target;
        }
        return cTTLTimeTargetElement;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public void setTgtEl(CTTLTimeTargetElement tgtEl) {
        generatedSetterHelperImpl(tgtEl, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public CTTLTimeTargetElement addNewTgtEl() {
        CTTLTimeTargetElement target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTLTimeTargetElement) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public Object getVol() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public STPositiveFixedPercentage xgetVol() {
        STPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPositiveFixedPercentage) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STPositiveFixedPercentage) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public boolean isSetVol() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public void setVol(Object vol) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setObjectValue(vol);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public void xsetVol(STPositiveFixedPercentage vol) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveFixedPercentage target = (STPositiveFixedPercentage) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STPositiveFixedPercentage) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(vol);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public void unsetVol() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public boolean getMute() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public XmlBoolean xgetMute() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public boolean isSetMute() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public void setMute(boolean mute) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setBooleanValue(mute);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public void xsetMute(XmlBoolean mute) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(mute);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public void unsetMute() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public long getNumSld() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public XmlUnsignedInt xgetNumSld() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlUnsignedInt) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public boolean isSetNumSld() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public void setNumSld(long numSld) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setLongValue(numSld);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public void xsetNumSld(XmlUnsignedInt numSld) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(numSld);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public void unsetNumSld() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public boolean getShowWhenStopped() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[5]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public XmlBoolean xgetShowWhenStopped() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[5]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public boolean isSetShowWhenStopped() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public void setShowWhenStopped(boolean showWhenStopped) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setBooleanValue(showWhenStopped);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public void xsetShowWhenStopped(XmlBoolean showWhenStopped) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(showWhenStopped);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData
    public void unsetShowWhenStopped() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }
}
