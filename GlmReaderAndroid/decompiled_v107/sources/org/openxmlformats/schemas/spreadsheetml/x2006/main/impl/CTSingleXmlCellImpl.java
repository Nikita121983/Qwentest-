package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXmlCellPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellRef;

/* loaded from: classes12.dex */
public class CTSingleXmlCellImpl extends XmlComplexContentImpl implements CTSingleXmlCell {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "xmlCellPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "extLst"), new QName("", "id"), new QName("", "r"), new QName("", "connectionId")};
    private static final long serialVersionUID = 1;

    public CTSingleXmlCellImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell
    public CTXmlCellPr getXmlCellPr() {
        CTXmlCellPr cTXmlCellPr;
        synchronized (monitor()) {
            check_orphaned();
            CTXmlCellPr target = (CTXmlCellPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTXmlCellPr = target == null ? null : target;
        }
        return cTXmlCellPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell
    public void setXmlCellPr(CTXmlCellPr xmlCellPr) {
        generatedSetterHelperImpl(xmlCellPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell
    public CTXmlCellPr addNewXmlCellPr() {
        CTXmlCellPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTXmlCellPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell
    public boolean isSetExtLst() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell
    public long getId() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell
    public XmlUnsignedInt xgetId() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell
    public void setId(long id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setLongValue(id);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell
    public void xsetId(XmlUnsignedInt id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(id);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell
    public String getR() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell
    public STCellRef xgetR() {
        STCellRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCellRef) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell
    public void setR(String r) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(r);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell
    public void xsetR(STCellRef r) {
        synchronized (monitor()) {
            check_orphaned();
            STCellRef target = (STCellRef) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STCellRef) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(r);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell
    public long getConnectionId() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell
    public XmlUnsignedInt xgetConnectionId() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell
    public void setConnectionId(long connectionId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setLongValue(connectionId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell
    public void xsetConnectionId(XmlUnsignedInt connectionId) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(connectionId);
        }
    }
}
