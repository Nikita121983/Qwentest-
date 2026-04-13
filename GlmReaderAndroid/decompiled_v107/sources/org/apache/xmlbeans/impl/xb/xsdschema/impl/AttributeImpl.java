package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.FormChoice;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;

/* loaded from: classes11.dex */
public class AttributeImpl extends AnnotatedImpl implements Attribute {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "simpleType"), new QName("", "name"), new QName("", "ref"), new QName("", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY), new QName("", "use"), new QName("", "default"), new QName("", "fixed"), new QName("", "form")};
    private static final long serialVersionUID = 1;

    public AttributeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public LocalSimpleType getSimpleType() {
        LocalSimpleType localSimpleType;
        synchronized (monitor()) {
            check_orphaned();
            LocalSimpleType target = (LocalSimpleType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            localSimpleType = target == null ? null : target;
        }
        return localSimpleType;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public boolean isSetSimpleType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void setSimpleType(LocalSimpleType simpleType) {
        generatedSetterHelperImpl(simpleType, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public LocalSimpleType addNewSimpleType() {
        LocalSimpleType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (LocalSimpleType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void unsetSimpleType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public XmlNCName xgetName() {
        XmlNCName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlNCName) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public boolean isSetName() {
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

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(name);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void xsetName(XmlNCName name) {
        synchronized (monitor()) {
            check_orphaned();
            XmlNCName target = (XmlNCName) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlNCName) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(name);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void unsetName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public QName getRef() {
        QName qNameValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            qNameValue = target == null ? null : target.getQNameValue();
        }
        return qNameValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public XmlQName xgetRef() {
        XmlQName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public boolean isSetRef() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void setRef(QName ref) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setQNameValue(ref);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void xsetRef(XmlQName ref) {
        synchronized (monitor()) {
            check_orphaned();
            XmlQName target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (XmlQName) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(ref);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void unsetRef() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public QName getType() {
        QName qNameValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            qNameValue = target == null ? null : target.getQNameValue();
        }
        return qNameValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public XmlQName xgetType() {
        XmlQName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public boolean isSetType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void setType(QName type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setQNameValue(type);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void xsetType(XmlQName type) {
        synchronized (monitor()) {
            check_orphaned();
            XmlQName target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlQName) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(type);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public Attribute.Use.Enum getUse() {
        Attribute.Use.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
            r1 = target == null ? null : (Attribute.Use.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public Attribute.Use xgetUse() {
        Attribute.Use target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Attribute.Use) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (Attribute.Use) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public boolean isSetUse() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void setUse(Attribute.Use.Enum use) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setEnumValue(use);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void xsetUse(Attribute.Use use) {
        synchronized (monitor()) {
            check_orphaned();
            Attribute.Use target = (Attribute.Use) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (Attribute.Use) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(use);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void unsetUse() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public String getDefault() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public XmlString xgetDefault() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public boolean isSetDefault() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void setDefault(String xdefault) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setStringValue(xdefault);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void xsetDefault(XmlString xdefault) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(xdefault);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void unsetDefault() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public String getFixed() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public XmlString xgetFixed() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public boolean isSetFixed() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void setFixed(String fixed) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setStringValue(fixed);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void xsetFixed(XmlString fixed) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(fixed);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void unsetFixed() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public FormChoice.Enum getForm() {
        FormChoice.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            r1 = target == null ? null : (FormChoice.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public FormChoice xgetForm() {
        FormChoice target;
        synchronized (monitor()) {
            check_orphaned();
            target = (FormChoice) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public boolean isSetForm() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void setForm(FormChoice.Enum form) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setEnumValue(form);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void xsetForm(FormChoice form) {
        synchronized (monitor()) {
            check_orphaned();
            FormChoice target = (FormChoice) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (FormChoice) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(form);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Attribute
    public void unsetForm() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    /* loaded from: classes11.dex */
    public static class UseImpl extends JavaStringEnumerationHolderEx implements Attribute.Use {
        private static final long serialVersionUID = 1;

        public UseImpl(SchemaType sType) {
            super(sType, false);
        }

        protected UseImpl(SchemaType sType, boolean b) {
            super(sType, b);
        }
    }
}
