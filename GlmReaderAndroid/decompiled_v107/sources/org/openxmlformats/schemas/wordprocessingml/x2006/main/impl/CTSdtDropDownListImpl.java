package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

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
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtListItem;

/* loaded from: classes12.dex */
public class CTSdtDropDownListImpl extends XmlComplexContentImpl implements CTSdtDropDownList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "listItem"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "lastValue")};
    private static final long serialVersionUID = 1;

    public CTSdtDropDownListImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList
    public List<CTSdtListItem> getListItemList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSdtDropDownListImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSdtDropDownListImpl.this.getListItemArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSdtDropDownListImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSdtDropDownListImpl.this.setListItemArray(((Integer) obj).intValue(), (CTSdtListItem) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSdtDropDownListImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSdtDropDownListImpl.this.insertNewListItem(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSdtDropDownListImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSdtDropDownListImpl.this.removeListItem(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSdtDropDownListImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSdtDropDownListImpl.this.sizeOfListItemArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList
    public CTSdtListItem[] getListItemArray() {
        return (CTSdtListItem[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTSdtListItem[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList
    public CTSdtListItem getListItemArray(int i) {
        CTSdtListItem target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtListItem) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList
    public int sizeOfListItemArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList
    public void setListItemArray(CTSdtListItem[] listItemArray) {
        check_orphaned();
        arraySetterHelper(listItemArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList
    public void setListItemArray(int i, CTSdtListItem listItem) {
        generatedSetterHelperImpl(listItem, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList
    public CTSdtListItem insertNewListItem(int i) {
        CTSdtListItem target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtListItem) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList
    public CTSdtListItem addNewListItem() {
        CTSdtListItem target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtListItem) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList
    public void removeListItem(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList
    public String getLastValue() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList
    public STString xgetLastValue() {
        STString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STString) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList
    public boolean isSetLastValue() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList
    public void setLastValue(String lastValue) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(lastValue);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList
    public void xsetLastValue(STString lastValue) {
        synchronized (monitor()) {
            check_orphaned();
            STString target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STString) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(lastValue);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList
    public void unsetLastValue() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }
}
