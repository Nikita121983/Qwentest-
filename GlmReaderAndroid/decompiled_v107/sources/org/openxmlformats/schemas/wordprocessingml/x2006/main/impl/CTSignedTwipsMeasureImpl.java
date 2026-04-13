package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedTwipsMeasure;

/* loaded from: classes12.dex */
public class CTSignedTwipsMeasureImpl extends XmlComplexContentImpl implements CTSignedTwipsMeasure {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "val")};
    private static final long serialVersionUID = 1;

    public CTSignedTwipsMeasureImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure
    public Object getVal() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure
    public STSignedTwipsMeasure xgetVal() {
        STSignedTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure
    public void setVal(Object val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setObjectValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure
    public void xsetVal(STSignedTwipsMeasure val) {
        synchronized (monitor()) {
            check_orphaned();
            STSignedTwipsMeasure target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STSignedTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }
}
