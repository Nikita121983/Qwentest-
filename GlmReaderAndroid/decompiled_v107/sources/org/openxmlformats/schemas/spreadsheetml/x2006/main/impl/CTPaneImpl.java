package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellRef;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPane;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPaneState;

/* loaded from: classes12.dex */
public class CTPaneImpl extends XmlComplexContentImpl implements CTPane {
    private static final QName[] PROPERTY_QNAME = {new QName("", "xSplit"), new QName("", "ySplit"), new QName("", "topLeftCell"), new QName("", "activePane"), new QName("", "state")};
    private static final long serialVersionUID = 1;

    public CTPaneImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public double getXSplit() {
        double doubleValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
            doubleValue = target == null ? 0.0d : target.getDoubleValue();
        }
        return doubleValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public XmlDouble xgetXSplit() {
        XmlDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (XmlDouble) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public boolean isSetXSplit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public void setXSplit(double xSplit) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setDoubleValue(xSplit);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public void xsetXSplit(XmlDouble xSplit) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDouble target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (XmlDouble) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(xSplit);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public void unsetXSplit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public double getYSplit() {
        double doubleValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
            doubleValue = target == null ? 0.0d : target.getDoubleValue();
        }
        return doubleValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public XmlDouble xgetYSplit() {
        XmlDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlDouble) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public boolean isSetYSplit() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public void setYSplit(double ySplit) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setDoubleValue(ySplit);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public void xsetYSplit(XmlDouble ySplit) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDouble target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlDouble) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(ySplit);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public void unsetYSplit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public String getTopLeftCell() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public STCellRef xgetTopLeftCell() {
        STCellRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCellRef) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public boolean isSetTopLeftCell() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public void setTopLeftCell(String topLeftCell) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(topLeftCell);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public void xsetTopLeftCell(STCellRef topLeftCell) {
        synchronized (monitor()) {
            check_orphaned();
            STCellRef target = (STCellRef) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STCellRef) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(topLeftCell);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public void unsetTopLeftCell() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public STPane.Enum getActivePane() {
        STPane.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
            r1 = target == null ? null : (STPane.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public STPane xgetActivePane() {
        STPane target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPane) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STPane) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public boolean isSetActivePane() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public void setActivePane(STPane.Enum activePane) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setEnumValue(activePane);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public void xsetActivePane(STPane activePane) {
        synchronized (monitor()) {
            check_orphaned();
            STPane target = (STPane) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STPane) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(activePane);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public void unsetActivePane() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public STPaneState.Enum getState() {
        STPaneState.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
            r1 = target == null ? null : (STPaneState.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public STPaneState xgetState() {
        STPaneState target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPaneState) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STPaneState) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public boolean isSetState() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public void setState(STPaneState.Enum state) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setEnumValue(state);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public void xsetState(STPaneState state) {
        synchronized (monitor()) {
            check_orphaned();
            STPaneState target = (STPaneState) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STPaneState) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(state);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
    public void unsetState() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }
}
