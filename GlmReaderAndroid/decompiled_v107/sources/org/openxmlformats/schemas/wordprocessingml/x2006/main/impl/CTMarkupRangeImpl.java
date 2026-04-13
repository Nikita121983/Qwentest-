package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDisplacedByCustomXml;

/* loaded from: classes12.dex */
public class CTMarkupRangeImpl extends CTMarkupImpl implements CTMarkupRange {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "displacedByCustomXml")};
    private static final long serialVersionUID = 1;

    public CTMarkupRangeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange
    public STDisplacedByCustomXml.Enum getDisplacedByCustomXml() {
        STDisplacedByCustomXml.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STDisplacedByCustomXml.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange
    public STDisplacedByCustomXml xgetDisplacedByCustomXml() {
        STDisplacedByCustomXml target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDisplacedByCustomXml) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange
    public boolean isSetDisplacedByCustomXml() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange
    public void setDisplacedByCustomXml(STDisplacedByCustomXml.Enum displacedByCustomXml) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(displacedByCustomXml);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange
    public void xsetDisplacedByCustomXml(STDisplacedByCustomXml displacedByCustomXml) {
        synchronized (monitor()) {
            check_orphaned();
            STDisplacedByCustomXml target = (STDisplacedByCustomXml) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STDisplacedByCustomXml) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(displacedByCustomXml);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange
    public void unsetDisplacedByCustomXml() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }
}
