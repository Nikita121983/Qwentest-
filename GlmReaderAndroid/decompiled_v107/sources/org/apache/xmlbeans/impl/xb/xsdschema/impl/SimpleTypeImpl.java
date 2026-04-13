package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleDerivationSet;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument;

/* loaded from: classes11.dex */
public class SimpleTypeImpl extends AnnotatedImpl implements SimpleType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "restriction"), new QName("http://www.w3.org/2001/XMLSchema", XmlErrorCodes.LIST), new QName("http://www.w3.org/2001/XMLSchema", XmlErrorCodes.UNION), new QName("", "final"), new QName("", "name")};
    private static final long serialVersionUID = 1;

    public SimpleTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public RestrictionDocument.Restriction getRestriction() {
        RestrictionDocument.Restriction restriction;
        synchronized (monitor()) {
            check_orphaned();
            RestrictionDocument.Restriction target = (RestrictionDocument.Restriction) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            restriction = target == null ? null : target;
        }
        return restriction;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public boolean isSetRestriction() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public void setRestriction(RestrictionDocument.Restriction restriction) {
        generatedSetterHelperImpl(restriction, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public RestrictionDocument.Restriction addNewRestriction() {
        RestrictionDocument.Restriction target;
        synchronized (monitor()) {
            check_orphaned();
            target = (RestrictionDocument.Restriction) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public void unsetRestriction() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public ListDocument.List getList() {
        ListDocument.List list;
        synchronized (monitor()) {
            check_orphaned();
            ListDocument.List target = (ListDocument.List) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            list = target == null ? null : target;
        }
        return list;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public boolean isSetList() {
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

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public void setList(ListDocument.List list) {
        generatedSetterHelperImpl(list, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public ListDocument.List addNewList() {
        ListDocument.List target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ListDocument.List) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public void unsetList() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public UnionDocument.Union getUnion() {
        UnionDocument.Union union;
        synchronized (monitor()) {
            check_orphaned();
            UnionDocument.Union target = (UnionDocument.Union) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            union = target == null ? null : target;
        }
        return union;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public boolean isSetUnion() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public void setUnion(UnionDocument.Union union) {
        generatedSetterHelperImpl(union, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public UnionDocument.Union addNewUnion() {
        UnionDocument.Union target;
        synchronized (monitor()) {
            check_orphaned();
            target = (UnionDocument.Union) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public void unsetUnion() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public Object getFinal() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public SimpleDerivationSet xgetFinal() {
        SimpleDerivationSet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (SimpleDerivationSet) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public boolean isSetFinal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public void setFinal(Object xfinal) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setObjectValue(xfinal);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public void xsetFinal(SimpleDerivationSet xfinal) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleDerivationSet target = (SimpleDerivationSet) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleDerivationSet) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(xfinal);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public void unsetFinal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public XmlNCName xgetName() {
        XmlNCName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlNCName) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public boolean isSetName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setStringValue(name);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public void xsetName(XmlNCName name) {
        synchronized (monitor()) {
            check_orphaned();
            XmlNCName target = (XmlNCName) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlNCName) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(name);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleType
    public void unsetName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }
}
