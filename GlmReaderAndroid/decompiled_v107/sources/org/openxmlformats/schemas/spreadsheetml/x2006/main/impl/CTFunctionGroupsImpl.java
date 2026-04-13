package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

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
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups;

/* loaded from: classes12.dex */
public class CTFunctionGroupsImpl extends XmlComplexContentImpl implements CTFunctionGroups {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "functionGroup"), new QName("", "builtInGroupCount")};
    private static final long serialVersionUID = 1;

    public CTFunctionGroupsImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups
    public List<CTFunctionGroup> getFunctionGroupList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFunctionGroupsImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFunctionGroupsImpl.this.getFunctionGroupArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFunctionGroupsImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFunctionGroupsImpl.this.setFunctionGroupArray(((Integer) obj).intValue(), (CTFunctionGroup) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFunctionGroupsImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFunctionGroupsImpl.this.insertNewFunctionGroup(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFunctionGroupsImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFunctionGroupsImpl.this.removeFunctionGroup(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFunctionGroupsImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFunctionGroupsImpl.this.sizeOfFunctionGroupArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups
    public CTFunctionGroup[] getFunctionGroupArray() {
        return getXmlObjectArray(PROPERTY_QNAME[0], (XmlObject[]) new CTFunctionGroup[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups
    public CTFunctionGroup getFunctionGroupArray(int i) {
        CTFunctionGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups
    public int sizeOfFunctionGroupArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups
    public void setFunctionGroupArray(CTFunctionGroup[] functionGroupArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) functionGroupArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups
    public void setFunctionGroupArray(int i, CTFunctionGroup functionGroup) {
        generatedSetterHelperImpl(functionGroup, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups
    public CTFunctionGroup insertNewFunctionGroup(int i) {
        CTFunctionGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups
    public CTFunctionGroup addNewFunctionGroup() {
        CTFunctionGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups
    public void removeFunctionGroup(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups
    public long getBuiltInGroupCount() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups
    public XmlUnsignedInt xgetBuiltInGroupCount() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlUnsignedInt) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups
    public boolean isSetBuiltInGroupCount() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups
    public void setBuiltInGroupCount(long builtInGroupCount) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setLongValue(builtInGroupCount);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups
    public void xsetBuiltInGroupCount(XmlUnsignedInt builtInGroupCount) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(builtInGroupCount);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFunctionGroups
    public void unsetBuiltInGroupCount() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }
}
