package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetDimension;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STRef;

/* loaded from: classes12.dex */
public class CTSheetDimensionImpl extends XmlComplexContentImpl implements CTSheetDimension {
    private static final QName[] PROPERTY_QNAME = {new QName("", "ref")};
    private static final long serialVersionUID = 1;

    public CTSheetDimensionImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetDimension
    public String getRef() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetDimension
    public STRef xgetRef() {
        STRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRef) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetDimension
    public void setRef(String ref) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(ref);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetDimension
    public void xsetRef(STRef ref) {
        synchronized (monitor()) {
            check_orphaned();
            STRef target = (STRef) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STRef) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(ref);
        }
    }
}
