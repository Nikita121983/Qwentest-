package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLongHexNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMultiLevelType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;

/* loaded from: classes12.dex */
public class CTAbstractNumImpl extends XmlComplexContentImpl implements CTAbstractNum {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "nsid"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "multiLevelType"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tmpl"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "name"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "styleLink"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "numStyleLink"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "lvl"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "abstractNumId")};
    private static final long serialVersionUID = 1;

    public CTAbstractNumImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public CTLongHexNumber getNsid() {
        CTLongHexNumber cTLongHexNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTLongHexNumber target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTLongHexNumber = target == null ? null : target;
        }
        return cTLongHexNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public boolean isSetNsid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public void setNsid(CTLongHexNumber nsid) {
        generatedSetterHelperImpl(nsid, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public CTLongHexNumber addNewNsid() {
        CTLongHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public void unsetNsid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public CTMultiLevelType getMultiLevelType() {
        CTMultiLevelType cTMultiLevelType;
        synchronized (monitor()) {
            check_orphaned();
            CTMultiLevelType target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTMultiLevelType = target == null ? null : target;
        }
        return cTMultiLevelType;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public boolean isSetMultiLevelType() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public void setMultiLevelType(CTMultiLevelType multiLevelType) {
        generatedSetterHelperImpl(multiLevelType, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public CTMultiLevelType addNewMultiLevelType() {
        CTMultiLevelType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public void unsetMultiLevelType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public CTLongHexNumber getTmpl() {
        CTLongHexNumber cTLongHexNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTLongHexNumber target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTLongHexNumber = target == null ? null : target;
        }
        return cTLongHexNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public boolean isSetTmpl() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public void setTmpl(CTLongHexNumber tmpl) {
        generatedSetterHelperImpl(tmpl, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public CTLongHexNumber addNewTmpl() {
        CTLongHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public void unsetTmpl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public CTString getName() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public boolean isSetName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public void setName(CTString name) {
        generatedSetterHelperImpl(name, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public CTString addNewName() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public void unsetName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public CTString getStyleLink() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public boolean isSetStyleLink() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public void setStyleLink(CTString styleLink) {
        generatedSetterHelperImpl(styleLink, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public CTString addNewStyleLink() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public void unsetStyleLink() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public CTString getNumStyleLink() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public boolean isSetNumStyleLink() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public void setNumStyleLink(CTString numStyleLink) {
        generatedSetterHelperImpl(numStyleLink, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public CTString addNewNumStyleLink() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public void unsetNumStyleLink() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public List<CTLvl> getLvlList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTAbstractNumImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTAbstractNumImpl.this.getLvlArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTAbstractNumImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTAbstractNumImpl.this.setLvlArray(((Integer) obj).intValue(), (CTLvl) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTAbstractNumImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTAbstractNumImpl.this.insertNewLvl(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTAbstractNumImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTAbstractNumImpl.this.removeLvl(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTAbstractNumImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTAbstractNumImpl.this.sizeOfLvlArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public CTLvl[] getLvlArray() {
        return (CTLvl[]) getXmlObjectArray(PROPERTY_QNAME[6], new CTLvl[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public CTLvl getLvlArray(int i) {
        CTLvl target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLvl) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public int sizeOfLvlArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public void setLvlArray(CTLvl[] lvlArray) {
        check_orphaned();
        arraySetterHelper(lvlArray, PROPERTY_QNAME[6]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public void setLvlArray(int i, CTLvl lvl) {
        generatedSetterHelperImpl(lvl, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public CTLvl insertNewLvl(int i) {
        CTLvl target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLvl) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public CTLvl addNewLvl() {
        CTLvl target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLvl) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public void removeLvl(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public BigInteger getAbstractNumId() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public STDecimalNumber xgetAbstractNumId() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public void setAbstractNumId(BigInteger abstractNumId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setBigIntegerValue(abstractNumId);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum
    public void xsetAbstractNumId(STDecimalNumber abstractNumId) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(abstractNumId);
        }
    }
}
