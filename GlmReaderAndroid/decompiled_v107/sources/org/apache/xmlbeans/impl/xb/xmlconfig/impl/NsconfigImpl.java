package org.apache.xmlbeans.impl.xb.xmlconfig.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xmlconfig.NamespaceList;
import org.apache.xmlbeans.impl.xb.xmlconfig.NamespacePrefixList;
import org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig;

/* loaded from: classes11.dex */
public class NsconfigImpl extends XmlComplexContentImpl implements Nsconfig {
    private static final QName[] PROPERTY_QNAME = {new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "package"), new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "prefix"), new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "suffix"), new QName("", "uri"), new QName("", "uriprefix")};
    private static final long serialVersionUID = 1;

    public NsconfigImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public String getPackage() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public XmlString xgetPackage() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public boolean isSetPackage() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public void setPackage(String xpackage) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(xpackage);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public void xsetPackage(XmlString xpackage) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.set(xpackage);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public void unsetPackage() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public String getPrefix() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public XmlString xgetPrefix() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public boolean isSetPrefix() {
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

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public void setPrefix(String prefix) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(prefix);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public void xsetPrefix(XmlString prefix) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.set(prefix);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public void unsetPrefix() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public String getSuffix() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public XmlString xgetSuffix() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[2], 0);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public boolean isSetSuffix() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public void setSuffix(String suffix) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(suffix);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public void xsetSuffix(XmlString suffix) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            target.set(suffix);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public void unsetSuffix() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public Object getUri() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public NamespaceList xgetUri() {
        NamespaceList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NamespaceList) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public boolean isSetUri() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public void setUri(Object uri) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setObjectValue(uri);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public void xsetUri(NamespaceList uri) {
        synchronized (monitor()) {
            check_orphaned();
            NamespaceList target = (NamespaceList) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (NamespaceList) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(uri);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public void unsetUri() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public List getUriprefix() {
        List<?> listValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            listValue = target == null ? null : target.getListValue();
        }
        return listValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public NamespacePrefixList xgetUriprefix() {
        NamespacePrefixList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NamespacePrefixList) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public boolean isSetUriprefix() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public void setUriprefix(List uriprefix) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setListValue(uriprefix);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public void xsetUriprefix(NamespacePrefixList uriprefix) {
        synchronized (monitor()) {
            check_orphaned();
            NamespacePrefixList target = (NamespacePrefixList) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (NamespacePrefixList) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(uriprefix);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig
    public void unsetUriprefix() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }
}
