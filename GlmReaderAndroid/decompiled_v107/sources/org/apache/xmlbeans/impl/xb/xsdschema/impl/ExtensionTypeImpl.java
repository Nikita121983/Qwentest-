package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType;
import org.apache.xmlbeans.impl.xb.xsdschema.GroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.Wildcard;

/* loaded from: classes11.dex */
public class ExtensionTypeImpl extends AnnotatedImpl implements ExtensionType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "group"), new QName("http://www.w3.org/2001/XMLSchema", "all"), new QName("http://www.w3.org/2001/XMLSchema", "choice"), new QName("http://www.w3.org/2001/XMLSchema", "sequence"), new QName("http://www.w3.org/2001/XMLSchema", "attribute"), new QName("http://www.w3.org/2001/XMLSchema", "attributeGroup"), new QName("http://www.w3.org/2001/XMLSchema", "anyAttribute"), new QName("", "base")};
    private static final long serialVersionUID = 1;

    public ExtensionTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public GroupRef getGroup() {
        GroupRef groupRef;
        synchronized (monitor()) {
            check_orphaned();
            GroupRef target = (GroupRef) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            groupRef = target == null ? null : target;
        }
        return groupRef;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public boolean isSetGroup() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public void setGroup(GroupRef group) {
        generatedSetterHelperImpl(group, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public GroupRef addNewGroup() {
        GroupRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (GroupRef) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public void unsetGroup() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public All getAll() {
        All all;
        synchronized (monitor()) {
            check_orphaned();
            All target = (All) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            all = target == null ? null : target;
        }
        return all;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public boolean isSetAll() {
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

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public void setAll(All all) {
        generatedSetterHelperImpl(all, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public All addNewAll() {
        All target;
        synchronized (monitor()) {
            check_orphaned();
            target = (All) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public void unsetAll() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public ExplicitGroup getChoice() {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            ExplicitGroup target = (ExplicitGroup) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            explicitGroup = target == null ? null : target;
        }
        return explicitGroup;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public boolean isSetChoice() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public void setChoice(ExplicitGroup choice) {
        generatedSetterHelperImpl(choice, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public ExplicitGroup addNewChoice() {
        ExplicitGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ExplicitGroup) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public void unsetChoice() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public ExplicitGroup getSequence() {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            ExplicitGroup target = (ExplicitGroup) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            explicitGroup = target == null ? null : target;
        }
        return explicitGroup;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public boolean isSetSequence() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public void setSequence(ExplicitGroup sequence) {
        generatedSetterHelperImpl(sequence, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public ExplicitGroup addNewSequence() {
        ExplicitGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ExplicitGroup) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public void unsetSequence() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public List<Attribute> getAttributeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ExtensionTypeImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ExtensionTypeImpl.this.getAttributeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ExtensionTypeImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ExtensionTypeImpl.this.setAttributeArray(((Integer) obj).intValue(), (Attribute) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ExtensionTypeImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ExtensionTypeImpl.this.insertNewAttribute(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ExtensionTypeImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ExtensionTypeImpl.this.removeAttribute(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ExtensionTypeImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(ExtensionTypeImpl.this.sizeOfAttributeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public Attribute[] getAttributeArray() {
        return (Attribute[]) getXmlObjectArray(PROPERTY_QNAME[4], new Attribute[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public Attribute getAttributeArray(int i) {
        Attribute target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Attribute) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public int sizeOfAttributeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public void setAttributeArray(Attribute[] attributeArray) {
        check_orphaned();
        arraySetterHelper(attributeArray, PROPERTY_QNAME[4]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public void setAttributeArray(int i, Attribute attribute) {
        generatedSetterHelperImpl(attribute, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public Attribute insertNewAttribute(int i) {
        Attribute target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Attribute) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public Attribute addNewAttribute() {
        Attribute target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Attribute) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public void removeAttribute(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public List<AttributeGroupRef> getAttributeGroupList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ExtensionTypeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ExtensionTypeImpl.this.getAttributeGroupArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ExtensionTypeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ExtensionTypeImpl.this.setAttributeGroupArray(((Integer) obj).intValue(), (AttributeGroupRef) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ExtensionTypeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ExtensionTypeImpl.this.insertNewAttributeGroup(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ExtensionTypeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ExtensionTypeImpl.this.removeAttributeGroup(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ExtensionTypeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(ExtensionTypeImpl.this.sizeOfAttributeGroupArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public AttributeGroupRef[] getAttributeGroupArray() {
        return (AttributeGroupRef[]) getXmlObjectArray(PROPERTY_QNAME[5], new AttributeGroupRef[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public AttributeGroupRef getAttributeGroupArray(int i) {
        AttributeGroupRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AttributeGroupRef) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public int sizeOfAttributeGroupArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public void setAttributeGroupArray(AttributeGroupRef[] attributeGroupArray) {
        check_orphaned();
        arraySetterHelper(attributeGroupArray, PROPERTY_QNAME[5]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public void setAttributeGroupArray(int i, AttributeGroupRef attributeGroup) {
        generatedSetterHelperImpl(attributeGroup, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public AttributeGroupRef insertNewAttributeGroup(int i) {
        AttributeGroupRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AttributeGroupRef) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public AttributeGroupRef addNewAttributeGroup() {
        AttributeGroupRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AttributeGroupRef) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public void removeAttributeGroup(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public Wildcard getAnyAttribute() {
        Wildcard wildcard;
        synchronized (monitor()) {
            check_orphaned();
            Wildcard target = (Wildcard) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            wildcard = target == null ? null : target;
        }
        return wildcard;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public boolean isSetAnyAttribute() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public void setAnyAttribute(Wildcard anyAttribute) {
        generatedSetterHelperImpl(anyAttribute, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public Wildcard addNewAnyAttribute() {
        Wildcard target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Wildcard) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public void unsetAnyAttribute() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public QName getBase() {
        QName qNameValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            qNameValue = target == null ? null : target.getQNameValue();
        }
        return qNameValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public XmlQName xgetBase() {
        XmlQName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public void setBase(QName base) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setQNameValue(base);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType
    public void xsetBase(XmlQName base) {
        synchronized (monitor()) {
            check_orphaned();
            XmlQName target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (XmlQName) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(base);
        }
    }
}
