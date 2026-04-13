package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.STColorSchemeIndex;

/* loaded from: classes11.dex */
public class CTColorMappingImpl extends XmlComplexContentImpl implements CTColorMapping {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "extLst"), new QName("", "bg1"), new QName("", "tx1"), new QName("", "bg2"), new QName("", "tx2"), new QName("", "accent1"), new QName("", "accent2"), new QName("", "accent3"), new QName("", "accent4"), new QName("", "accent5"), new QName("", "accent6"), new QName("", "hlink"), new QName("", "folHlink")};
    private static final long serialVersionUID = 1;

    public CTColorMappingImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTOfficeArtExtensionList target = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTOfficeArtExtensionList = target == null ? null : target;
        }
        return cTOfficeArtExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void setExtLst(CTOfficeArtExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex.Enum getBg1() {
        STColorSchemeIndex.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            r1 = target == null ? null : (STColorSchemeIndex.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex xgetBg1() {
        STColorSchemeIndex target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void setBg1(STColorSchemeIndex.Enum bg1) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setEnumValue(bg1);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void xsetBg1(STColorSchemeIndex bg1) {
        synchronized (monitor()) {
            check_orphaned();
            STColorSchemeIndex target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STColorSchemeIndex) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(bg1);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex.Enum getTx1() {
        STColorSchemeIndex.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            r1 = target == null ? null : (STColorSchemeIndex.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex xgetTx1() {
        STColorSchemeIndex target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void setTx1(STColorSchemeIndex.Enum tx1) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setEnumValue(tx1);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void xsetTx1(STColorSchemeIndex tx1) {
        synchronized (monitor()) {
            check_orphaned();
            STColorSchemeIndex target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STColorSchemeIndex) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(tx1);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex.Enum getBg2() {
        STColorSchemeIndex.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            r1 = target == null ? null : (STColorSchemeIndex.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex xgetBg2() {
        STColorSchemeIndex target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void setBg2(STColorSchemeIndex.Enum bg2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setEnumValue(bg2);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void xsetBg2(STColorSchemeIndex bg2) {
        synchronized (monitor()) {
            check_orphaned();
            STColorSchemeIndex target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STColorSchemeIndex) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(bg2);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex.Enum getTx2() {
        STColorSchemeIndex.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            r1 = target == null ? null : (STColorSchemeIndex.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex xgetTx2() {
        STColorSchemeIndex target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void setTx2(STColorSchemeIndex.Enum tx2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setEnumValue(tx2);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void xsetTx2(STColorSchemeIndex tx2) {
        synchronized (monitor()) {
            check_orphaned();
            STColorSchemeIndex target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STColorSchemeIndex) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(tx2);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex.Enum getAccent1() {
        STColorSchemeIndex.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            r1 = target == null ? null : (STColorSchemeIndex.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex xgetAccent1() {
        STColorSchemeIndex target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void setAccent1(STColorSchemeIndex.Enum accent1) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setEnumValue(accent1);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void xsetAccent1(STColorSchemeIndex accent1) {
        synchronized (monitor()) {
            check_orphaned();
            STColorSchemeIndex target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STColorSchemeIndex) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(accent1);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex.Enum getAccent2() {
        STColorSchemeIndex.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            r1 = target == null ? null : (STColorSchemeIndex.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex xgetAccent2() {
        STColorSchemeIndex target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void setAccent2(STColorSchemeIndex.Enum accent2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setEnumValue(accent2);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void xsetAccent2(STColorSchemeIndex accent2) {
        synchronized (monitor()) {
            check_orphaned();
            STColorSchemeIndex target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STColorSchemeIndex) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(accent2);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex.Enum getAccent3() {
        STColorSchemeIndex.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            r1 = target == null ? null : (STColorSchemeIndex.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex xgetAccent3() {
        STColorSchemeIndex target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void setAccent3(STColorSchemeIndex.Enum accent3) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setEnumValue(accent3);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void xsetAccent3(STColorSchemeIndex accent3) {
        synchronized (monitor()) {
            check_orphaned();
            STColorSchemeIndex target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (STColorSchemeIndex) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(accent3);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex.Enum getAccent4() {
        STColorSchemeIndex.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            r1 = target == null ? null : (STColorSchemeIndex.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex xgetAccent4() {
        STColorSchemeIndex target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void setAccent4(STColorSchemeIndex.Enum accent4) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setEnumValue(accent4);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void xsetAccent4(STColorSchemeIndex accent4) {
        synchronized (monitor()) {
            check_orphaned();
            STColorSchemeIndex target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (STColorSchemeIndex) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(accent4);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex.Enum getAccent5() {
        STColorSchemeIndex.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            r1 = target == null ? null : (STColorSchemeIndex.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex xgetAccent5() {
        STColorSchemeIndex target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void setAccent5(STColorSchemeIndex.Enum accent5) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setEnumValue(accent5);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void xsetAccent5(STColorSchemeIndex accent5) {
        synchronized (monitor()) {
            check_orphaned();
            STColorSchemeIndex target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (STColorSchemeIndex) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(accent5);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex.Enum getAccent6() {
        STColorSchemeIndex.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            r1 = target == null ? null : (STColorSchemeIndex.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex xgetAccent6() {
        STColorSchemeIndex target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void setAccent6(STColorSchemeIndex.Enum accent6) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setEnumValue(accent6);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void xsetAccent6(STColorSchemeIndex accent6) {
        synchronized (monitor()) {
            check_orphaned();
            STColorSchemeIndex target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (STColorSchemeIndex) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(accent6);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex.Enum getHlink() {
        STColorSchemeIndex.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            r1 = target == null ? null : (STColorSchemeIndex.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex xgetHlink() {
        STColorSchemeIndex target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void setHlink(STColorSchemeIndex.Enum hlink) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.setEnumValue(hlink);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void xsetHlink(STColorSchemeIndex hlink) {
        synchronized (monitor()) {
            check_orphaned();
            STColorSchemeIndex target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (STColorSchemeIndex) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.set(hlink);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex.Enum getFolHlink() {
        STColorSchemeIndex.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            r1 = target == null ? null : (STColorSchemeIndex.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public STColorSchemeIndex xgetFolHlink() {
        STColorSchemeIndex target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void setFolHlink(STColorSchemeIndex.Enum folHlink) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.setEnumValue(folHlink);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping
    public void xsetFolHlink(STColorSchemeIndex folHlink) {
        synchronized (monitor()) {
            check_orphaned();
            STColorSchemeIndex target = (STColorSchemeIndex) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (STColorSchemeIndex) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.set(folHlink);
        }
    }
}
