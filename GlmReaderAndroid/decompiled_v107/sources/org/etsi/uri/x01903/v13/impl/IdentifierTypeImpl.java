package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.JavaUriHolderEx;
import org.etsi.uri.x01903.v13.IdentifierType;
import org.etsi.uri.x01903.v13.QualifierType;
import org.etsi.uri.x01903.v13.QualifierType$Enum;

/* loaded from: classes11.dex */
public class IdentifierTypeImpl extends JavaUriHolderEx implements IdentifierType {
    private static final QName[] PROPERTY_QNAME = {new QName("", "Qualifier")};
    private static final long serialVersionUID = 1;

    public IdentifierTypeImpl(SchemaType sType) {
        super(sType, true);
    }

    protected IdentifierTypeImpl(SchemaType sType, boolean b) {
        super(sType, b);
    }

    @Override // org.etsi.uri.x01903.v13.IdentifierType
    public QualifierType$Enum getQualifier() {
        QualifierType$Enum qualifierType$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            qualifierType$Enum = target == null ? null : (QualifierType$Enum) target.getEnumValue();
        }
        return qualifierType$Enum;
    }

    @Override // org.etsi.uri.x01903.v13.IdentifierType
    public QualifierType xgetQualifier() {
        QualifierType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.IdentifierType
    public boolean isSetQualifier() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.IdentifierType
    public void setQualifier(QualifierType$Enum qualifier) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(qualifier);
        }
    }

    @Override // org.etsi.uri.x01903.v13.IdentifierType
    public void xsetQualifier(QualifierType qualifier) {
        synchronized (monitor()) {
            check_orphaned();
            QualifierType target = get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (QualifierType) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(qualifier);
        }
    }

    @Override // org.etsi.uri.x01903.v13.IdentifierType
    public void unsetQualifier() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }
}
