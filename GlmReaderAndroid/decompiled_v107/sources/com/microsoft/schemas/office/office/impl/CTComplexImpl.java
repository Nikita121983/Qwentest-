package com.microsoft.schemas.office.office.impl;

import com.microsoft.schemas.office.office.CTComplex;
import com.microsoft.schemas.vml.STExt;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes.dex */
public class CTComplexImpl extends XmlComplexContentImpl implements CTComplex {
    private static final QName[] PROPERTY_QNAME = {new QName("urn:schemas-microsoft-com:vml", "ext")};
    private static final long serialVersionUID = 1;

    public CTComplexImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.office.CTComplex
    public STExt.Enum getExt() {
        STExt.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STExt.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.office.CTComplex
    public STExt xgetExt() {
        STExt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STExt) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTComplex
    public boolean isSetExt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTComplex
    public void setExt(STExt.Enum ext) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(ext);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTComplex
    public void xsetExt(STExt ext) {
        synchronized (monitor()) {
            check_orphaned();
            STExt target = (STExt) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STExt) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(ext);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTComplex
    public void unsetExt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }
}
