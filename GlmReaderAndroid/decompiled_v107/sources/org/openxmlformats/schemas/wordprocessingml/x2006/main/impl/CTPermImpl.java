package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDisplacedByCustomXml;

/* loaded from: classes12.dex */
public class CTPermImpl extends XmlComplexContentImpl implements CTPerm {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "id"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "displacedByCustomXml")};
    private static final long serialVersionUID = 1;

    public CTPermImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm
    public STString xgetId() {
        STString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(id);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm
    public void xsetId(STString id) {
        synchronized (monitor()) {
            check_orphaned();
            STString target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STString) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(id);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm
    public STDisplacedByCustomXml.Enum getDisplacedByCustomXml() {
        STDisplacedByCustomXml.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            r1 = target == null ? null : (STDisplacedByCustomXml.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm
    public STDisplacedByCustomXml xgetDisplacedByCustomXml() {
        STDisplacedByCustomXml target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDisplacedByCustomXml) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm
    public boolean isSetDisplacedByCustomXml() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm
    public void setDisplacedByCustomXml(STDisplacedByCustomXml.Enum displacedByCustomXml) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setEnumValue(displacedByCustomXml);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm
    public void xsetDisplacedByCustomXml(STDisplacedByCustomXml displacedByCustomXml) {
        synchronized (monitor()) {
            check_orphaned();
            STDisplacedByCustomXml target = (STDisplacedByCustomXml) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STDisplacedByCustomXml) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(displacedByCustomXml);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm
    public void unsetDisplacedByCustomXml() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }
}
