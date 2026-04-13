package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPaperSource;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;

/* loaded from: classes12.dex */
public class CTPaperSourceImpl extends XmlComplexContentImpl implements CTPaperSource {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "first"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "other")};
    private static final long serialVersionUID = 1;

    public CTPaperSourceImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPaperSource
    public BigInteger getFirst() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPaperSource
    public STDecimalNumber xgetFirst() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPaperSource
    public boolean isSetFirst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPaperSource
    public void setFirst(BigInteger first) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setBigIntegerValue(first);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPaperSource
    public void xsetFirst(STDecimalNumber first) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(first);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPaperSource
    public void unsetFirst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPaperSource
    public BigInteger getOther() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPaperSource
    public STDecimalNumber xgetOther() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPaperSource
    public boolean isSetOther() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPaperSource
    public void setOther(BigInteger other) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setBigIntegerValue(other);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPaperSource
    public void xsetOther(STDecimalNumber other) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(other);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPaperSource
    public void unsetOther() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }
}
