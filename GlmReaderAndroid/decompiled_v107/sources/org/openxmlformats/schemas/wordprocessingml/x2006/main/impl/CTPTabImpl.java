package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabAlignment;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabAlignment$Enum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabLeader;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabLeader$Enum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabRelativeTo;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPTabRelativeTo$Enum;

/* loaded from: classes12.dex */
public class CTPTabImpl extends XmlComplexContentImpl implements CTPTab {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, CellUtil.ALIGNMENT), new QName(XSSFRelation.NS_WORDPROCESSINGML, "relativeTo"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "leader")};
    private static final long serialVersionUID = 1;

    public CTPTabImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab
    public STPTabAlignment$Enum getAlignment() {
        STPTabAlignment$Enum sTPTabAlignment$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            sTPTabAlignment$Enum = target == null ? null : (STPTabAlignment$Enum) target.getEnumValue();
        }
        return sTPTabAlignment$Enum;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab
    public STPTabAlignment xgetAlignment() {
        STPTabAlignment target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab
    public void setAlignment(STPTabAlignment$Enum alignment) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(alignment);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab
    public void xsetAlignment(STPTabAlignment alignment) {
        synchronized (monitor()) {
            check_orphaned();
            STPTabAlignment target = get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STPTabAlignment) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(alignment);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab
    public STPTabRelativeTo$Enum getRelativeTo() {
        STPTabRelativeTo$Enum sTPTabRelativeTo$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            sTPTabRelativeTo$Enum = target == null ? null : (STPTabRelativeTo$Enum) target.getEnumValue();
        }
        return sTPTabRelativeTo$Enum;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab
    public STPTabRelativeTo xgetRelativeTo() {
        STPTabRelativeTo target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab
    public void setRelativeTo(STPTabRelativeTo$Enum relativeTo) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setEnumValue(relativeTo);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab
    public void xsetRelativeTo(STPTabRelativeTo relativeTo) {
        synchronized (monitor()) {
            check_orphaned();
            STPTabRelativeTo target = get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STPTabRelativeTo) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(relativeTo);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab
    public STPTabLeader$Enum getLeader() {
        STPTabLeader$Enum sTPTabLeader$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            sTPTabLeader$Enum = target == null ? null : (STPTabLeader$Enum) target.getEnumValue();
        }
        return sTPTabLeader$Enum;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab
    public STPTabLeader xgetLeader() {
        STPTabLeader target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab
    public void setLeader(STPTabLeader$Enum leader) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setEnumValue(leader);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab
    public void xsetLeader(STPTabLeader leader) {
        synchronized (monitor()) {
            check_orphaned();
            STPTabLeader target = get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STPTabLeader) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(leader);
        }
    }
}
