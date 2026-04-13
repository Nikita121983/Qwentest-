package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;

/* loaded from: classes12.dex */
public class CTFtnEdnRefImpl extends XmlComplexContentImpl implements CTFtnEdnRef {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "customMarkFollows"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "id")};
    private static final long serialVersionUID = 1;

    public CTFtnEdnRefImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef
    public Object getCustomMarkFollows() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef
    public STOnOff xgetCustomMarkFollows() {
        STOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef
    public boolean isSetCustomMarkFollows() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef
    public void setCustomMarkFollows(Object customMarkFollows) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setObjectValue(customMarkFollows);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef
    public void xsetCustomMarkFollows(STOnOff customMarkFollows) {
        synchronized (monitor()) {
            check_orphaned();
            STOnOff target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STOnOff) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(customMarkFollows);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef
    public void unsetCustomMarkFollows() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef
    public BigInteger getId() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef
    public STDecimalNumber xgetId() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef
    public void setId(BigInteger id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setBigIntegerValue(id);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef
    public void xsetId(STDecimalNumber id) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(id);
        }
    }
}
