package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabTlc;

/* loaded from: classes12.dex */
public class CTTabStopImpl extends XmlComplexContentImpl implements CTTabStop {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "val"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "leader"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "pos")};
    private static final long serialVersionUID = 1;

    public CTTabStopImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop
    public STTabJc.Enum getVal() {
        STTabJc.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STTabJc.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop
    public STTabJc xgetVal() {
        STTabJc target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTabJc) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop
    public void setVal(STTabJc.Enum val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop
    public void xsetVal(STTabJc val) {
        synchronized (monitor()) {
            check_orphaned();
            STTabJc target = (STTabJc) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STTabJc) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop
    public STTabTlc.Enum getLeader() {
        STTabTlc.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            r1 = target == null ? null : (STTabTlc.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop
    public STTabTlc xgetLeader() {
        STTabTlc target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTabTlc) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop
    public boolean isSetLeader() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop
    public void setLeader(STTabTlc.Enum leader) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setEnumValue(leader);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop
    public void xsetLeader(STTabTlc leader) {
        synchronized (monitor()) {
            check_orphaned();
            STTabTlc target = (STTabTlc) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STTabTlc) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(leader);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop
    public void unsetLeader() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop
    public Object getPos() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop
    public STSignedTwipsMeasure xgetPos() {
        STSignedTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop
    public void setPos(Object pos) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setObjectValue(pos);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop
    public void xsetPos(STSignedTwipsMeasure pos) {
        synchronized (monitor()) {
            check_orphaned();
            STSignedTwipsMeasure target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STSignedTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(pos);
        }
    }
}
