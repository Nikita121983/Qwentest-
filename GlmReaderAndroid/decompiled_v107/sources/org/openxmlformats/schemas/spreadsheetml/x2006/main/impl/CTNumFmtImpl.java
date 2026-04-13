package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STNumFmtId;

/* loaded from: classes12.dex */
public class CTNumFmtImpl extends XmlComplexContentImpl implements CTNumFmt {
    private static final QName[] PROPERTY_QNAME = {new QName("", "numFmtId"), new QName("", "formatCode")};
    private static final long serialVersionUID = 1;

    public CTNumFmtImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt
    public long getNumFmtId() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt
    public STNumFmtId xgetNumFmtId() {
        STNumFmtId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STNumFmtId) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt
    public void setNumFmtId(long numFmtId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setLongValue(numFmtId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt
    public void xsetNumFmtId(STNumFmtId numFmtId) {
        synchronized (monitor()) {
            check_orphaned();
            STNumFmtId target = (STNumFmtId) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STNumFmtId) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(numFmtId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt
    public String getFormatCode() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt
    public STXstring xgetFormatCode() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt
    public void setFormatCode(String formatCode) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(formatCode);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt
    public void xsetFormatCode(STXstring formatCode) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(formatCode);
        }
    }
}
