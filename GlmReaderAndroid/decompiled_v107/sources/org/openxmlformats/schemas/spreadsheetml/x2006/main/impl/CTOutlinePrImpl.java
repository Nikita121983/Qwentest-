package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr;

/* loaded from: classes12.dex */
public class CTOutlinePrImpl extends XmlComplexContentImpl implements CTOutlinePr {
    private static final QName[] PROPERTY_QNAME = {new QName("", "applyStyles"), new QName("", "summaryBelow"), new QName("", "summaryRight"), new QName("", "showOutlineSymbols")};
    private static final long serialVersionUID = 1;

    public CTOutlinePrImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public boolean getApplyStyles() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = false;
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
            if (target != null) {
                z = target.getBooleanValue();
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public XmlBoolean xgetApplyStyles() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public boolean isSetApplyStyles() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public void setApplyStyles(boolean applyStyles) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setBooleanValue(applyStyles);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public void xsetApplyStyles(XmlBoolean applyStyles) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(applyStyles);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public void unsetApplyStyles() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public boolean getSummaryBelow() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public XmlBoolean xgetSummaryBelow() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public boolean isSetSummaryBelow() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public void setSummaryBelow(boolean summaryBelow) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setBooleanValue(summaryBelow);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public void xsetSummaryBelow(XmlBoolean summaryBelow) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(summaryBelow);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public void unsetSummaryBelow() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public boolean getSummaryRight() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public XmlBoolean xgetSummaryRight() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public boolean isSetSummaryRight() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public void setSummaryRight(boolean summaryRight) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setBooleanValue(summaryRight);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public void xsetSummaryRight(XmlBoolean summaryRight) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(summaryRight);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public void unsetSummaryRight() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public boolean getShowOutlineSymbols() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public XmlBoolean xgetShowOutlineSymbols() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public boolean isSetShowOutlineSymbols() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public void setShowOutlineSymbols(boolean showOutlineSymbols) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setBooleanValue(showOutlineSymbols);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public void xsetShowOutlineSymbols(XmlBoolean showOutlineSymbols) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(showOutlineSymbols);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr
    public void unsetShowOutlineSymbols() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }
}
