package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Keybase;
import org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument;

/* loaded from: classes11.dex */
public class KeybaseImpl extends AnnotatedImpl implements Keybase {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "selector"), new QName("http://www.w3.org/2001/XMLSchema", "field"), new QName("", "name")};
    private static final long serialVersionUID = 1;

    public KeybaseImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Keybase
    public SelectorDocument.Selector getSelector() {
        SelectorDocument.Selector selector;
        synchronized (monitor()) {
            check_orphaned();
            SelectorDocument.Selector target = (SelectorDocument.Selector) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            selector = target == null ? null : target;
        }
        return selector;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Keybase
    public void setSelector(SelectorDocument.Selector selector) {
        generatedSetterHelperImpl(selector, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Keybase
    public SelectorDocument.Selector addNewSelector() {
        SelectorDocument.Selector target;
        synchronized (monitor()) {
            check_orphaned();
            target = (SelectorDocument.Selector) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Keybase
    public List<FieldDocument.Field> getFieldList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.KeybaseImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return KeybaseImpl.this.getFieldArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.KeybaseImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    KeybaseImpl.this.setFieldArray(((Integer) obj).intValue(), (FieldDocument.Field) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.KeybaseImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return KeybaseImpl.this.insertNewField(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.KeybaseImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    KeybaseImpl.this.removeField(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.KeybaseImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(KeybaseImpl.this.sizeOfFieldArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Keybase
    public FieldDocument.Field[] getFieldArray() {
        return (FieldDocument.Field[]) getXmlObjectArray(PROPERTY_QNAME[1], new FieldDocument.Field[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Keybase
    public FieldDocument.Field getFieldArray(int i) {
        FieldDocument.Field target;
        synchronized (monitor()) {
            check_orphaned();
            target = (FieldDocument.Field) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Keybase
    public int sizeOfFieldArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Keybase
    public void setFieldArray(FieldDocument.Field[] fieldArray) {
        check_orphaned();
        arraySetterHelper(fieldArray, PROPERTY_QNAME[1]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Keybase
    public void setFieldArray(int i, FieldDocument.Field field) {
        generatedSetterHelperImpl(field, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Keybase
    public FieldDocument.Field insertNewField(int i) {
        FieldDocument.Field target;
        synchronized (monitor()) {
            check_orphaned();
            target = (FieldDocument.Field) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Keybase
    public FieldDocument.Field addNewField() {
        FieldDocument.Field target;
        synchronized (monitor()) {
            check_orphaned();
            target = (FieldDocument.Field) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Keybase
    public void removeField(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Keybase
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Keybase
    public XmlNCName xgetName() {
        XmlNCName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlNCName) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Keybase
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(name);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Keybase
    public void xsetName(XmlNCName name) {
        synchronized (monitor()) {
            check_orphaned();
            XmlNCName target = (XmlNCName) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (XmlNCName) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(name);
        }
    }
}
