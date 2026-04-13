package org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperties;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty;

/* loaded from: classes11.dex */
public class CTPropertiesImpl extends XmlComplexContentImpl implements CTProperties {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/custom-properties", "property")};
    private static final long serialVersionUID = 1;

    public CTPropertiesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperties
    public List<CTProperty> getPropertyList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl.CTPropertiesImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPropertiesImpl.this.getPropertyArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl.CTPropertiesImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPropertiesImpl.this.setPropertyArray(((Integer) obj).intValue(), (CTProperty) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl.CTPropertiesImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPropertiesImpl.this.insertNewProperty(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl.CTPropertiesImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPropertiesImpl.this.removeProperty(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl.CTPropertiesImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPropertiesImpl.this.sizeOfPropertyArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperties
    public CTProperty[] getPropertyArray() {
        return (CTProperty[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTProperty[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperties
    public CTProperty getPropertyArray(int i) {
        CTProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTProperty) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperties
    public int sizeOfPropertyArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperties
    public void setPropertyArray(CTProperty[] propertyArray) {
        check_orphaned();
        arraySetterHelper(propertyArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperties
    public void setPropertyArray(int i, CTProperty property) {
        generatedSetterHelperImpl(property, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperties
    public CTProperty insertNewProperty(int i) {
        CTProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTProperty) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperties
    public CTProperty addNewProperty() {
        CTProperty target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTProperty) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperties
    public void removeProperty(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
