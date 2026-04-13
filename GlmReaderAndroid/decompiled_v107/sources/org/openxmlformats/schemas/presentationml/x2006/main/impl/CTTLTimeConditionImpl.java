package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTriggerRuntimeNode;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTriggerTimeNodeID;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTime;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTriggerEvent;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTriggerEvent$Enum;

/* loaded from: classes11.dex */
public class CTTLTimeConditionImpl extends XmlComplexContentImpl implements CTTLTimeCondition {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "tgtEl"), new QName(XSSFRelation.NS_PRESENTATIONML, "tn"), new QName(XSSFRelation.NS_PRESENTATIONML, "rtn"), new QName("", "evt"), new QName("", "delay")};
    private static final long serialVersionUID = 1;

    public CTTLTimeConditionImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public CTTLTimeTargetElement getTgtEl() {
        CTTLTimeTargetElement cTTLTimeTargetElement;
        synchronized (monitor()) {
            check_orphaned();
            CTTLTimeTargetElement target = (CTTLTimeTargetElement) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTLTimeTargetElement = target == null ? null : target;
        }
        return cTTLTimeTargetElement;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public boolean isSetTgtEl() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public void setTgtEl(CTTLTimeTargetElement tgtEl) {
        generatedSetterHelperImpl(tgtEl, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public CTTLTimeTargetElement addNewTgtEl() {
        CTTLTimeTargetElement target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTLTimeTargetElement) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public void unsetTgtEl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public CTTLTriggerTimeNodeID getTn() {
        CTTLTriggerTimeNodeID cTTLTriggerTimeNodeID;
        synchronized (monitor()) {
            check_orphaned();
            CTTLTriggerTimeNodeID target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTTLTriggerTimeNodeID = target == null ? null : target;
        }
        return cTTLTriggerTimeNodeID;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public boolean isSetTn() {
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

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public void setTn(CTTLTriggerTimeNodeID tn) {
        generatedSetterHelperImpl(tn, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public CTTLTriggerTimeNodeID addNewTn() {
        CTTLTriggerTimeNodeID target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public void unsetTn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public CTTLTriggerRuntimeNode getRtn() {
        CTTLTriggerRuntimeNode cTTLTriggerRuntimeNode;
        synchronized (monitor()) {
            check_orphaned();
            CTTLTriggerRuntimeNode target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTTLTriggerRuntimeNode = target == null ? null : target;
        }
        return cTTLTriggerRuntimeNode;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public boolean isSetRtn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public void setRtn(CTTLTriggerRuntimeNode rtn) {
        generatedSetterHelperImpl(rtn, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public CTTLTriggerRuntimeNode addNewRtn() {
        CTTLTriggerRuntimeNode target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public void unsetRtn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public STTLTriggerEvent$Enum getEvt() {
        STTLTriggerEvent$Enum sTTLTriggerEvent$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            sTTLTriggerEvent$Enum = target == null ? null : (STTLTriggerEvent$Enum) target.getEnumValue();
        }
        return sTTLTriggerEvent$Enum;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public STTLTriggerEvent xgetEvt() {
        STTLTriggerEvent target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public boolean isSetEvt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public void setEvt(STTLTriggerEvent$Enum evt) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setEnumValue(evt);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public void xsetEvt(STTLTriggerEvent evt) {
        synchronized (monitor()) {
            check_orphaned();
            STTLTriggerEvent target = get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STTLTriggerEvent) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(evt);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public void unsetEvt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public Object getDelay() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public STTLTime xgetDelay() {
        STTLTime target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTLTime) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public boolean isSetDelay() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public void setDelay(Object delay) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setObjectValue(delay);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public void xsetDelay(STTLTime delay) {
        synchronized (monitor()) {
            check_orphaned();
            STTLTime target = (STTLTime) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STTLTime) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(delay);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
    public void unsetDelay() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }
}
