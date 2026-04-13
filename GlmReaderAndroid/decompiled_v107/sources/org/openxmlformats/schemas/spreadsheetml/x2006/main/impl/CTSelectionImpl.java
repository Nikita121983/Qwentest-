package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellRef;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPane;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSqref;

/* loaded from: classes12.dex */
public class CTSelectionImpl extends XmlComplexContentImpl implements CTSelection {
    private static final QName[] PROPERTY_QNAME = {new QName("", "pane"), new QName("", "activeCell"), new QName("", "activeCellId"), new QName("", "sqref")};
    private static final long serialVersionUID = 1;

    public CTSelectionImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public STPane.Enum getPane() {
        STPane.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
            r1 = target == null ? null : (STPane.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public STPane xgetPane() {
        STPane target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPane) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STPane) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public boolean isSetPane() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public void setPane(STPane.Enum pane) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(pane);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public void xsetPane(STPane pane) {
        synchronized (monitor()) {
            check_orphaned();
            STPane target = (STPane) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STPane) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(pane);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public void unsetPane() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public String getActiveCell() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public STCellRef xgetActiveCell() {
        STCellRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCellRef) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public boolean isSetActiveCell() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public void setActiveCell(String activeCell) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(activeCell);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public void xsetActiveCell(STCellRef activeCell) {
        synchronized (monitor()) {
            check_orphaned();
            STCellRef target = (STCellRef) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STCellRef) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(activeCell);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public void unsetActiveCell() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public long getActiveCellId() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public XmlUnsignedInt xgetActiveCellId() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (XmlUnsignedInt) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public boolean isSetActiveCellId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public void setActiveCellId(long activeCellId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setLongValue(activeCellId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public void xsetActiveCellId(XmlUnsignedInt activeCellId) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(activeCellId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public void unsetActiveCellId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public List getSqref() {
        List<?> listValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
            listValue = target == null ? null : target.getListValue();
        }
        return listValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public STSqref xgetSqref() {
        STSqref target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSqref) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STSqref) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public boolean isSetSqref() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public void setSqref(List sqref) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setListValue(sqref);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public void xsetSqref(STSqref sqref) {
        synchronized (monitor()) {
            check_orphaned();
            STSqref target = (STSqref) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STSqref) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(sqref);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection
    public void unsetSqref() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }
}
