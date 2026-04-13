package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.xb.xsdschema.NamespaceList;
import org.apache.xmlbeans.impl.xb.xsdschema.Wildcard;

/* loaded from: classes11.dex */
public class WildcardImpl extends AnnotatedImpl implements Wildcard {
    private static final QName[] PROPERTY_QNAME = {new QName("", "namespace"), new QName("", "processContents")};
    private static final long serialVersionUID = 1;

    public WildcardImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Wildcard
    public Object getNamespace() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Wildcard
    public NamespaceList xgetNamespace() {
        NamespaceList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NamespaceList) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (NamespaceList) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Wildcard
    public boolean isSetNamespace() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Wildcard
    public void setNamespace(Object namespace) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setObjectValue(namespace);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Wildcard
    public void xsetNamespace(NamespaceList namespace) {
        synchronized (monitor()) {
            check_orphaned();
            NamespaceList target = (NamespaceList) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (NamespaceList) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(namespace);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Wildcard
    public void unsetNamespace() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Wildcard
    public Wildcard.ProcessContents.Enum getProcessContents() {
        Wildcard.ProcessContents.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
            r1 = target == null ? null : (Wildcard.ProcessContents.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Wildcard
    public Wildcard.ProcessContents xgetProcessContents() {
        Wildcard.ProcessContents target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Wildcard.ProcessContents) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (Wildcard.ProcessContents) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Wildcard
    public boolean isSetProcessContents() {
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

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Wildcard
    public void setProcessContents(Wildcard.ProcessContents.Enum processContents) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setEnumValue(processContents);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Wildcard
    public void xsetProcessContents(Wildcard.ProcessContents processContents) {
        synchronized (monitor()) {
            check_orphaned();
            Wildcard.ProcessContents target = (Wildcard.ProcessContents) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (Wildcard.ProcessContents) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(processContents);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Wildcard
    public void unsetProcessContents() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    /* loaded from: classes11.dex */
    public static class ProcessContentsImpl extends JavaStringEnumerationHolderEx implements Wildcard.ProcessContents {
        private static final long serialVersionUID = 1;

        public ProcessContentsImpl(SchemaType sType) {
            super(sType, false);
        }

        protected ProcessContentsImpl(SchemaType sType, boolean b) {
            super(sType, b);
        }
    }
}
