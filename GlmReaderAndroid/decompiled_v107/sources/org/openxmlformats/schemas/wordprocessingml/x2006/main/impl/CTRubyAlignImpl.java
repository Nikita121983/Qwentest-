package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyAlign;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STRubyAlign;

/* loaded from: classes12.dex */
public class CTRubyAlignImpl extends XmlComplexContentImpl implements CTRubyAlign {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "val")};
    private static final long serialVersionUID = 1;

    public CTRubyAlignImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyAlign
    public STRubyAlign.Enum getVal() {
        STRubyAlign.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STRubyAlign.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyAlign
    public STRubyAlign xgetVal() {
        STRubyAlign target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRubyAlign) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyAlign
    public void setVal(STRubyAlign.Enum val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyAlign
    public void xsetVal(STRubyAlign val) {
        synchronized (monitor()) {
            check_orphaned();
            STRubyAlign target = (STRubyAlign) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STRubyAlign) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }
}
