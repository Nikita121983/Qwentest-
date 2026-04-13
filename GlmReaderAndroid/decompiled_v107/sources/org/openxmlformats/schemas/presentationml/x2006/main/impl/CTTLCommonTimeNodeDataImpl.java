package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveFixedPercentage;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLIterateData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeConditionList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTime;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTimeNodeFillType;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTimeNodeID;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTimeNodeMasterRelation;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTimeNodeMasterRelation$Enum;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTimeNodePresetClassType;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTimeNodePresetClassType$Enum;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTimeNodeRestartType;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTimeNodeSyncType;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTimeNodeSyncType$Enum;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTimeNodeType;

/* loaded from: classes11.dex */
public class CTTLCommonTimeNodeDataImpl extends XmlComplexContentImpl implements CTTLCommonTimeNodeData {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "stCondLst"), new QName(XSSFRelation.NS_PRESENTATIONML, "endCondLst"), new QName(XSSFRelation.NS_PRESENTATIONML, "endSync"), new QName(XSSFRelation.NS_PRESENTATIONML, "iterate"), new QName(XSSFRelation.NS_PRESENTATIONML, "childTnLst"), new QName(XSSFRelation.NS_PRESENTATIONML, "subTnLst"), new QName("", "id"), new QName("", "presetID"), new QName("", "presetClass"), new QName("", "presetSubtype"), new QName("", "dur"), new QName("", "repeatCount"), new QName("", "repeatDur"), new QName("", "spd"), new QName("", "accel"), new QName("", "decel"), new QName("", "autoRev"), new QName("", "restart"), new QName("", "fill"), new QName("", "syncBehavior"), new QName("", "tmFilter"), new QName("", "evtFilter"), new QName("", "display"), new QName("", "masterRel"), new QName("", "bldLvl"), new QName("", "grpId"), new QName("", "afterEffect"), new QName("", "nodeType"), new QName("", "nodePh")};
    private static final long serialVersionUID = 1;

    public CTTLCommonTimeNodeDataImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public CTTLTimeConditionList getStCondLst() {
        CTTLTimeConditionList cTTLTimeConditionList;
        synchronized (monitor()) {
            check_orphaned();
            CTTLTimeConditionList target = (CTTLTimeConditionList) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTLTimeConditionList = target == null ? null : target;
        }
        return cTTLTimeConditionList;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetStCondLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setStCondLst(CTTLTimeConditionList stCondLst) {
        generatedSetterHelperImpl(stCondLst, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public CTTLTimeConditionList addNewStCondLst() {
        CTTLTimeConditionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTLTimeConditionList) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetStCondLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public CTTLTimeConditionList getEndCondLst() {
        CTTLTimeConditionList cTTLTimeConditionList;
        synchronized (monitor()) {
            check_orphaned();
            CTTLTimeConditionList target = (CTTLTimeConditionList) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTTLTimeConditionList = target == null ? null : target;
        }
        return cTTLTimeConditionList;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetEndCondLst() {
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

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setEndCondLst(CTTLTimeConditionList endCondLst) {
        generatedSetterHelperImpl(endCondLst, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public CTTLTimeConditionList addNewEndCondLst() {
        CTTLTimeConditionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTLTimeConditionList) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetEndCondLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public CTTLTimeCondition getEndSync() {
        CTTLTimeCondition cTTLTimeCondition;
        synchronized (monitor()) {
            check_orphaned();
            CTTLTimeCondition target = (CTTLTimeCondition) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTTLTimeCondition = target == null ? null : target;
        }
        return cTTLTimeCondition;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetEndSync() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setEndSync(CTTLTimeCondition endSync) {
        generatedSetterHelperImpl(endSync, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public CTTLTimeCondition addNewEndSync() {
        CTTLTimeCondition target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTLTimeCondition) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetEndSync() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public CTTLIterateData getIterate() {
        CTTLIterateData cTTLIterateData;
        synchronized (monitor()) {
            check_orphaned();
            CTTLIterateData target = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTTLIterateData = target == null ? null : target;
        }
        return cTTLIterateData;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetIterate() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setIterate(CTTLIterateData iterate) {
        generatedSetterHelperImpl(iterate, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public CTTLIterateData addNewIterate() {
        CTTLIterateData target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetIterate() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public CTTimeNodeList getChildTnLst() {
        CTTimeNodeList cTTimeNodeList;
        synchronized (monitor()) {
            check_orphaned();
            CTTimeNodeList target = (CTTimeNodeList) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTTimeNodeList = target == null ? null : target;
        }
        return cTTimeNodeList;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetChildTnLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setChildTnLst(CTTimeNodeList childTnLst) {
        generatedSetterHelperImpl(childTnLst, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public CTTimeNodeList addNewChildTnLst() {
        CTTimeNodeList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTimeNodeList) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetChildTnLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public CTTimeNodeList getSubTnLst() {
        CTTimeNodeList cTTimeNodeList;
        synchronized (monitor()) {
            check_orphaned();
            CTTimeNodeList target = (CTTimeNodeList) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTTimeNodeList = target == null ? null : target;
        }
        return cTTimeNodeList;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetSubTnLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setSubTnLst(CTTimeNodeList subTnLst) {
        generatedSetterHelperImpl(subTnLst, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public CTTimeNodeList addNewSubTnLst() {
        CTTimeNodeList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTimeNodeList) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetSubTnLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public long getId() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public STTLTimeNodeID xgetId() {
        STTLTimeNodeID target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setId(long id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setLongValue(id);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetId(STTLTimeNodeID id) {
        synchronized (monitor()) {
            check_orphaned();
            STTLTimeNodeID target = get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STTLTimeNodeID) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(id);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public int getPresetID() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public XmlInt xgetPresetID() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetPresetID() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setPresetID(int presetID) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setIntValue(presetID);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetPresetID(XmlInt presetID) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (XmlInt) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(presetID);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetPresetID() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public STTLTimeNodePresetClassType$Enum getPresetClass() {
        STTLTimeNodePresetClassType$Enum sTTLTimeNodePresetClassType$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            sTTLTimeNodePresetClassType$Enum = target == null ? null : (STTLTimeNodePresetClassType$Enum) target.getEnumValue();
        }
        return sTTLTimeNodePresetClassType$Enum;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public STTLTimeNodePresetClassType xgetPresetClass() {
        STTLTimeNodePresetClassType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetPresetClass() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setPresetClass(STTLTimeNodePresetClassType$Enum presetClass) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setEnumValue(presetClass);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetPresetClass(STTLTimeNodePresetClassType presetClass) {
        synchronized (monitor()) {
            check_orphaned();
            STTLTimeNodePresetClassType target = get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (STTLTimeNodePresetClassType) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(presetClass);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetPresetClass() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public int getPresetSubtype() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public XmlInt xgetPresetSubtype() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_attribute_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetPresetSubtype() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setPresetSubtype(int presetSubtype) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setIntValue(presetSubtype);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetPresetSubtype(XmlInt presetSubtype) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (XmlInt) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(presetSubtype);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetPresetSubtype() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public Object getDur() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public STTLTime xgetDur() {
        STTLTime target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTLTime) get_store().find_attribute_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetDur() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setDur(Object dur) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setObjectValue(dur);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetDur(STTLTime dur) {
        synchronized (monitor()) {
            check_orphaned();
            STTLTime target = (STTLTime) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (STTLTime) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(dur);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetDur() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public Object getRepeatCount() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[11]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public STTLTime xgetRepeatCount() {
        STTLTime target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTLTime) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (STTLTime) get_default_attribute_value(PROPERTY_QNAME[11]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetRepeatCount() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setRepeatCount(Object repeatCount) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.setObjectValue(repeatCount);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetRepeatCount(STTLTime repeatCount) {
        synchronized (monitor()) {
            check_orphaned();
            STTLTime target = (STTLTime) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (STTLTime) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.set(repeatCount);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetRepeatCount() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[11]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public Object getRepeatDur() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public STTLTime xgetRepeatDur() {
        STTLTime target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTLTime) get_store().find_attribute_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetRepeatDur() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setRepeatDur(Object repeatDur) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.setObjectValue(repeatDur);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetRepeatDur(STTLTime repeatDur) {
        synchronized (monitor()) {
            check_orphaned();
            STTLTime target = (STTLTime) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (STTLTime) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.set(repeatDur);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetRepeatDur() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[12]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public Object getSpd() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[13]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public STPercentage xgetSpd() {
        STPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (STPercentage) get_default_attribute_value(PROPERTY_QNAME[13]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetSpd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setSpd(Object spd) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setObjectValue(spd);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetSpd(STPercentage spd) {
        synchronized (monitor()) {
            check_orphaned();
            STPercentage target = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (STPercentage) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(spd);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetSpd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public Object getAccel() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[14]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public STPositiveFixedPercentage xgetAccel() {
        STPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPositiveFixedPercentage) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (STPositiveFixedPercentage) get_default_attribute_value(PROPERTY_QNAME[14]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetAccel() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[14]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setAccel(Object accel) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.setObjectValue(accel);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetAccel(STPositiveFixedPercentage accel) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveFixedPercentage target = (STPositiveFixedPercentage) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (STPositiveFixedPercentage) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.set(accel);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetAccel() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[14]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public Object getDecel() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[15]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public STPositiveFixedPercentage xgetDecel() {
        STPositiveFixedPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPositiveFixedPercentage) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (STPositiveFixedPercentage) get_default_attribute_value(PROPERTY_QNAME[15]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetDecel() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[15]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setDecel(Object decel) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.setObjectValue(decel);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetDecel(STPositiveFixedPercentage decel) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveFixedPercentage target = (STPositiveFixedPercentage) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (STPositiveFixedPercentage) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.set(decel);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetDecel() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[15]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean getAutoRev() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[16]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public XmlBoolean xgetAutoRev() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[16]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetAutoRev() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[16]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setAutoRev(boolean autoRev) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.setBooleanValue(autoRev);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetAutoRev(XmlBoolean autoRev) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.set(autoRev);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetAutoRev() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[16]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public STTLTimeNodeRestartType.Enum getRestart() {
        STTLTimeNodeRestartType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            r1 = target == null ? null : (STTLTimeNodeRestartType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public STTLTimeNodeRestartType xgetRestart() {
        STTLTimeNodeRestartType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTLTimeNodeRestartType) get_store().find_attribute_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetRestart() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[17]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setRestart(STTLTimeNodeRestartType.Enum restart) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.setEnumValue(restart);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetRestart(STTLTimeNodeRestartType restart) {
        synchronized (monitor()) {
            check_orphaned();
            STTLTimeNodeRestartType target = (STTLTimeNodeRestartType) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (STTLTimeNodeRestartType) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.set(restart);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetRestart() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[17]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public STTLTimeNodeFillType.Enum getFill() {
        STTLTimeNodeFillType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            r1 = target == null ? null : (STTLTimeNodeFillType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public STTLTimeNodeFillType xgetFill() {
        STTLTimeNodeFillType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTLTimeNodeFillType) get_store().find_attribute_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[18]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setFill(STTLTimeNodeFillType.Enum fill) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.setEnumValue(fill);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetFill(STTLTimeNodeFillType fill) {
        synchronized (monitor()) {
            check_orphaned();
            STTLTimeNodeFillType target = (STTLTimeNodeFillType) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (STTLTimeNodeFillType) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.set(fill);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[18]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public STTLTimeNodeSyncType$Enum getSyncBehavior() {
        STTLTimeNodeSyncType$Enum sTTLTimeNodeSyncType$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            sTTLTimeNodeSyncType$Enum = target == null ? null : (STTLTimeNodeSyncType$Enum) target.getEnumValue();
        }
        return sTTLTimeNodeSyncType$Enum;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public STTLTimeNodeSyncType xgetSyncBehavior() {
        STTLTimeNodeSyncType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetSyncBehavior() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[19]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setSyncBehavior(STTLTimeNodeSyncType$Enum syncBehavior) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.setEnumValue(syncBehavior);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetSyncBehavior(STTLTimeNodeSyncType syncBehavior) {
        synchronized (monitor()) {
            check_orphaned();
            STTLTimeNodeSyncType target = get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (STTLTimeNodeSyncType) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.set(syncBehavior);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetSyncBehavior() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[19]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public String getTmFilter() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public XmlString xgetTmFilter() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetTmFilter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[20]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setTmFilter(String tmFilter) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.setStringValue(tmFilter);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetTmFilter(XmlString tmFilter) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.set(tmFilter);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetTmFilter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[20]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public String getEvtFilter() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public XmlString xgetEvtFilter() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetEvtFilter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[21]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setEvtFilter(String evtFilter) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.setStringValue(evtFilter);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetEvtFilter(XmlString evtFilter) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.set(evtFilter);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetEvtFilter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[21]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean getDisplay() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public XmlBoolean xgetDisplay() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetDisplay() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[22]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setDisplay(boolean display) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.setBooleanValue(display);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetDisplay(XmlBoolean display) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.set(display);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetDisplay() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[22]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public STTLTimeNodeMasterRelation$Enum getMasterRel() {
        STTLTimeNodeMasterRelation$Enum sTTLTimeNodeMasterRelation$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            sTTLTimeNodeMasterRelation$Enum = target == null ? null : (STTLTimeNodeMasterRelation$Enum) target.getEnumValue();
        }
        return sTTLTimeNodeMasterRelation$Enum;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public STTLTimeNodeMasterRelation xgetMasterRel() {
        STTLTimeNodeMasterRelation target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetMasterRel() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[23]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setMasterRel(STTLTimeNodeMasterRelation$Enum masterRel) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.setEnumValue(masterRel);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetMasterRel(STTLTimeNodeMasterRelation masterRel) {
        synchronized (monitor()) {
            check_orphaned();
            STTLTimeNodeMasterRelation target = get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (STTLTimeNodeMasterRelation) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.set(masterRel);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetMasterRel() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[23]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public int getBldLvl() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public XmlInt xgetBldLvl() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_attribute_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetBldLvl() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[24]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setBldLvl(int bldLvl) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.setIntValue(bldLvl);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetBldLvl(XmlInt bldLvl) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (XmlInt) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.set(bldLvl);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetBldLvl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[24]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public long getGrpId() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public XmlUnsignedInt xgetGrpId() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetGrpId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[25]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setGrpId(long grpId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.setLongValue(grpId);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetGrpId(XmlUnsignedInt grpId) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.set(grpId);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetGrpId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[25]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean getAfterEffect() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public XmlBoolean xgetAfterEffect() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetAfterEffect() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[26]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setAfterEffect(boolean afterEffect) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[26]);
            }
            target.setBooleanValue(afterEffect);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetAfterEffect(XmlBoolean afterEffect) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[26]);
            }
            target.set(afterEffect);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetAfterEffect() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[26]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public STTLTimeNodeType.Enum getNodeType() {
        STTLTimeNodeType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            r1 = target == null ? null : (STTLTimeNodeType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public STTLTimeNodeType xgetNodeType() {
        STTLTimeNodeType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTLTimeNodeType) get_store().find_attribute_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetNodeType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[27]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setNodeType(STTLTimeNodeType.Enum nodeType) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[27]);
            }
            target.setEnumValue(nodeType);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetNodeType(STTLTimeNodeType nodeType) {
        synchronized (monitor()) {
            check_orphaned();
            STTLTimeNodeType target = (STTLTimeNodeType) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            if (target == null) {
                target = (STTLTimeNodeType) get_store().add_attribute_user(PROPERTY_QNAME[27]);
            }
            target.set(nodeType);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetNodeType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[27]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean getNodePh() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public XmlBoolean xgetNodePh() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public boolean isSetNodePh() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[28]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void setNodePh(boolean nodePh) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[28]);
            }
            target.setBooleanValue(nodePh);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void xsetNodePh(XmlBoolean nodePh) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[28]);
            }
            target.set(nodePh);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData
    public void unsetNodePh() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[28]);
        }
    }
}
