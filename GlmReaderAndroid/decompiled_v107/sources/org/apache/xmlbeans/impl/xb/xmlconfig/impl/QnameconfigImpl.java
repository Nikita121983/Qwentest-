package org.apache.xmlbeans.impl.xb.xmlconfig.impl;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Qnametargetlist;

/* loaded from: classes11.dex */
public class QnameconfigImpl extends XmlComplexContentImpl implements Qnameconfig {
    private static final QName[] PROPERTY_QNAME = {new QName("", "name"), new QName("", "javaname"), new QName("", TypedValues.AttributesType.S_TARGET)};
    private static final long serialVersionUID = 1;

    public QnameconfigImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig
    public QName getName() {
        QName qNameValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            qNameValue = target == null ? null : target.getQNameValue();
        }
        return qNameValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig
    public XmlQName xgetName() {
        XmlQName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig
    public boolean isSetName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig
    public void setName(QName name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setQNameValue(name);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig
    public void xsetName(XmlQName name) {
        synchronized (monitor()) {
            check_orphaned();
            XmlQName target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (XmlQName) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(name);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig
    public void unsetName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig
    public String getJavaname() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig
    public XmlString xgetJavaname() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig
    public boolean isSetJavaname() {
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

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig
    public void setJavaname(String javaname) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(javaname);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig
    public void xsetJavaname(XmlString javaname) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(javaname);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig
    public void unsetJavaname() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig
    public List getTarget() {
        List<?> listValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
            listValue = target == null ? null : target.getListValue();
        }
        return listValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig
    public Qnametargetlist xgetTarget() {
        Qnametargetlist target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Qnametargetlist) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (Qnametargetlist) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig
    public boolean isSetTarget() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig
    public void setTarget(List targetValue) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setListValue(targetValue);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig
    public void xsetTarget(Qnametargetlist targetValue) {
        synchronized (monitor()) {
            check_orphaned();
            Qnametargetlist target = (Qnametargetlist) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (Qnametargetlist) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(targetValue);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig
    public void unsetTarget() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }
}
