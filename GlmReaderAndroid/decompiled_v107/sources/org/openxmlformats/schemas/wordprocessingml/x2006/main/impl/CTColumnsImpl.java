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
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumn;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;

/* loaded from: classes12.dex */
public class CTColumnsImpl extends XmlComplexContentImpl implements CTColumns {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "col"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "equalWidth"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "space"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "num"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "sep")};
    private static final long serialVersionUID = 1;

    public CTColumnsImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public List<CTColumn> getColList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTColumnsImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTColumnsImpl.this.getColArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTColumnsImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTColumnsImpl.this.setColArray(((Integer) obj).intValue(), (CTColumn) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTColumnsImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTColumnsImpl.this.insertNewCol(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTColumnsImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTColumnsImpl.this.removeCol(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTColumnsImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTColumnsImpl.this.sizeOfColArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public CTColumn[] getColArray() {
        return getXmlObjectArray(PROPERTY_QNAME[0], (XmlObject[]) new CTColumn[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public CTColumn getColArray(int i) {
        CTColumn target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public int sizeOfColArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public void setColArray(CTColumn[] colArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) colArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public void setColArray(int i, CTColumn col) {
        generatedSetterHelperImpl(col, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public CTColumn insertNewCol(int i) {
        CTColumn target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public CTColumn addNewCol() {
        CTColumn target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public void removeCol(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public Object getEqualWidth() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public STOnOff xgetEqualWidth() {
        STOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public boolean isSetEqualWidth() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public void setEqualWidth(Object equalWidth) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setObjectValue(equalWidth);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public void xsetEqualWidth(STOnOff equalWidth) {
        synchronized (monitor()) {
            check_orphaned();
            STOnOff target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STOnOff) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(equalWidth);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public void unsetEqualWidth() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public Object getSpace() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public STTwipsMeasure xgetSpace() {
        STTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STTwipsMeasure) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public boolean isSetSpace() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public void setSpace(Object space) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setObjectValue(space);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public void xsetSpace(STTwipsMeasure space) {
        synchronized (monitor()) {
            check_orphaned();
            STTwipsMeasure target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(space);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public void unsetSpace() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public BigInteger getNum() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public STDecimalNumber xgetNum() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STDecimalNumber) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public boolean isSetNum() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public void setNum(BigInteger num) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setBigIntegerValue(num);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public void xsetNum(STDecimalNumber num) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(num);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public void unsetNum() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public Object getSep() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public STOnOff xgetSep() {
        STOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public boolean isSetSep() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public void setSep(Object sep) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setObjectValue(sep);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public void xsetSep(STOnOff sep) {
        synchronized (monitor()) {
            check_orphaned();
            STOnOff target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STOnOff) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(sep);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColumns
    public void unsetSep() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }
}
