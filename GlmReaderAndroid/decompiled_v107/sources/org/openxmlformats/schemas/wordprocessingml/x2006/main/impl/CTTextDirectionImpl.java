package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTextDirection;

/* loaded from: classes12.dex */
public class CTTextDirectionImpl extends XmlComplexContentImpl implements CTTextDirection {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "val")};
    private static final long serialVersionUID = 1;

    public CTTextDirectionImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection
    public STTextDirection.Enum getVal() {
        STTextDirection.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STTextDirection.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection
    public STTextDirection xgetVal() {
        STTextDirection target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextDirection) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection
    public void setVal(STTextDirection.Enum val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection
    public void xsetVal(STTextDirection val) {
        synchronized (monitor()) {
            check_orphaned();
            STTextDirection target = (STTextDirection) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STTextDirection) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }
}
