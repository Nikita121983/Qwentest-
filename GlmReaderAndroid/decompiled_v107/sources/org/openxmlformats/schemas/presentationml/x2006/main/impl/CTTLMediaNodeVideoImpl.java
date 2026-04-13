package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonMediaNodeData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLMediaNodeVideo;

/* loaded from: classes11.dex */
public class CTTLMediaNodeVideoImpl extends XmlComplexContentImpl implements CTTLMediaNodeVideo {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "cMediaNode"), new QName("", "fullScrn")};
    private static final long serialVersionUID = 1;

    public CTTLMediaNodeVideoImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLMediaNodeVideo
    public CTTLCommonMediaNodeData getCMediaNode() {
        CTTLCommonMediaNodeData cTTLCommonMediaNodeData;
        synchronized (monitor()) {
            check_orphaned();
            CTTLCommonMediaNodeData target = (CTTLCommonMediaNodeData) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTLCommonMediaNodeData = target == null ? null : target;
        }
        return cTTLCommonMediaNodeData;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLMediaNodeVideo
    public void setCMediaNode(CTTLCommonMediaNodeData cMediaNode) {
        generatedSetterHelperImpl(cMediaNode, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLMediaNodeVideo
    public CTTLCommonMediaNodeData addNewCMediaNode() {
        CTTLCommonMediaNodeData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTLCommonMediaNodeData) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLMediaNodeVideo
    public boolean getFullScrn() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLMediaNodeVideo
    public XmlBoolean xgetFullScrn() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLMediaNodeVideo
    public boolean isSetFullScrn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().find_attribute_user(PROPERTY_QNAME[1]) == null) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLMediaNodeVideo
    public void setFullScrn(boolean fullScrn) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setBooleanValue(fullScrn);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLMediaNodeVideo
    public void xsetFullScrn(XmlBoolean fullScrn) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(fullScrn);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLMediaNodeVideo
    public void unsetFullScrn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }
}
