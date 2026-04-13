package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumRestart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STRestartNumber;

/* loaded from: classes12.dex */
public class CTNumRestartImpl extends XmlComplexContentImpl implements CTNumRestart {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "val")};
    private static final long serialVersionUID = 1;

    public CTNumRestartImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumRestart
    public STRestartNumber.Enum getVal() {
        STRestartNumber.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STRestartNumber.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumRestart
    public STRestartNumber xgetVal() {
        STRestartNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRestartNumber) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumRestart
    public void setVal(STRestartNumber.Enum val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumRestart
    public void xsetVal(STRestartNumber val) {
        synchronized (monitor()) {
            check_orphaned();
            STRestartNumber target = (STRestartNumber) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STRestartNumber) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }
}
