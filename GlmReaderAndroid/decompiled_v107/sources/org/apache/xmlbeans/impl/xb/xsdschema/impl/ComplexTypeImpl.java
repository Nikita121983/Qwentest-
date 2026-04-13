package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.ComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.DerivationSet;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.GroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleContentDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Wildcard;

/* loaded from: classes11.dex */
public class ComplexTypeImpl extends AnnotatedImpl implements ComplexType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "simpleContent"), new QName("http://www.w3.org/2001/XMLSchema", "complexContent"), new QName("http://www.w3.org/2001/XMLSchema", "group"), new QName("http://www.w3.org/2001/XMLSchema", "all"), new QName("http://www.w3.org/2001/XMLSchema", "choice"), new QName("http://www.w3.org/2001/XMLSchema", "sequence"), new QName("http://www.w3.org/2001/XMLSchema", "attribute"), new QName("http://www.w3.org/2001/XMLSchema", "attributeGroup"), new QName("http://www.w3.org/2001/XMLSchema", "anyAttribute"), new QName("", "name"), new QName("", "mixed"), new QName("", "abstract"), new QName("", "final"), new QName("", "block")};
    private static final long serialVersionUID = 1;

    public ComplexTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public SimpleContentDocument.SimpleContent getSimpleContent() {
        SimpleContentDocument.SimpleContent simpleContent;
        synchronized (monitor()) {
            check_orphaned();
            SimpleContentDocument.SimpleContent target = (SimpleContentDocument.SimpleContent) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            simpleContent = target == null ? null : target;
        }
        return simpleContent;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public boolean isSetSimpleContent() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void setSimpleContent(SimpleContentDocument.SimpleContent simpleContent) {
        generatedSetterHelperImpl(simpleContent, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public SimpleContentDocument.SimpleContent addNewSimpleContent() {
        SimpleContentDocument.SimpleContent target;
        synchronized (monitor()) {
            check_orphaned();
            target = (SimpleContentDocument.SimpleContent) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void unsetSimpleContent() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public ComplexContentDocument.ComplexContent getComplexContent() {
        ComplexContentDocument.ComplexContent complexContent;
        synchronized (monitor()) {
            check_orphaned();
            ComplexContentDocument.ComplexContent target = (ComplexContentDocument.ComplexContent) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            complexContent = target == null ? null : target;
        }
        return complexContent;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public boolean isSetComplexContent() {
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

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void setComplexContent(ComplexContentDocument.ComplexContent complexContent) {
        generatedSetterHelperImpl(complexContent, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public ComplexContentDocument.ComplexContent addNewComplexContent() {
        ComplexContentDocument.ComplexContent target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ComplexContentDocument.ComplexContent) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void unsetComplexContent() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public GroupRef getGroup() {
        GroupRef groupRef;
        synchronized (monitor()) {
            check_orphaned();
            GroupRef target = (GroupRef) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            groupRef = target == null ? null : target;
        }
        return groupRef;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public boolean isSetGroup() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void setGroup(GroupRef group) {
        generatedSetterHelperImpl(group, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public GroupRef addNewGroup() {
        GroupRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (GroupRef) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void unsetGroup() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public All getAll() {
        All all;
        synchronized (monitor()) {
            check_orphaned();
            All target = (All) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            all = target == null ? null : target;
        }
        return all;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public boolean isSetAll() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void setAll(All all) {
        generatedSetterHelperImpl(all, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public All addNewAll() {
        All target;
        synchronized (monitor()) {
            check_orphaned();
            target = (All) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void unsetAll() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public ExplicitGroup getChoice() {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            ExplicitGroup target = (ExplicitGroup) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            explicitGroup = target == null ? null : target;
        }
        return explicitGroup;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public boolean isSetChoice() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void setChoice(ExplicitGroup choice) {
        generatedSetterHelperImpl(choice, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public ExplicitGroup addNewChoice() {
        ExplicitGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ExplicitGroup) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void unsetChoice() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public ExplicitGroup getSequence() {
        ExplicitGroup explicitGroup;
        synchronized (monitor()) {
            check_orphaned();
            ExplicitGroup target = (ExplicitGroup) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            explicitGroup = target == null ? null : target;
        }
        return explicitGroup;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public boolean isSetSequence() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void setSequence(ExplicitGroup sequence) {
        generatedSetterHelperImpl(sequence, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public ExplicitGroup addNewSequence() {
        ExplicitGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ExplicitGroup) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void unsetSequence() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public List<Attribute> getAttributeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ComplexTypeImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ComplexTypeImpl.this.getAttributeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ComplexTypeImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ComplexTypeImpl.this.setAttributeArray(((Integer) obj).intValue(), (Attribute) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ComplexTypeImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ComplexTypeImpl.this.insertNewAttribute(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ComplexTypeImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ComplexTypeImpl.this.removeAttribute(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ComplexTypeImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(ComplexTypeImpl.this.sizeOfAttributeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public Attribute[] getAttributeArray() {
        return (Attribute[]) getXmlObjectArray(PROPERTY_QNAME[6], new Attribute[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public Attribute getAttributeArray(int i) {
        Attribute target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Attribute) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public int sizeOfAttributeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void setAttributeArray(Attribute[] attributeArray) {
        check_orphaned();
        arraySetterHelper(attributeArray, PROPERTY_QNAME[6]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void setAttributeArray(int i, Attribute attribute) {
        generatedSetterHelperImpl(attribute, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public Attribute insertNewAttribute(int i) {
        Attribute target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Attribute) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public Attribute addNewAttribute() {
        Attribute target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Attribute) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void removeAttribute(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public List<AttributeGroupRef> getAttributeGroupList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ComplexTypeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ComplexTypeImpl.this.getAttributeGroupArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ComplexTypeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ComplexTypeImpl.this.setAttributeGroupArray(((Integer) obj).intValue(), (AttributeGroupRef) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ComplexTypeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ComplexTypeImpl.this.insertNewAttributeGroup(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ComplexTypeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ComplexTypeImpl.this.removeAttributeGroup(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ComplexTypeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(ComplexTypeImpl.this.sizeOfAttributeGroupArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public AttributeGroupRef[] getAttributeGroupArray() {
        return (AttributeGroupRef[]) getXmlObjectArray(PROPERTY_QNAME[7], new AttributeGroupRef[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public AttributeGroupRef getAttributeGroupArray(int i) {
        AttributeGroupRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AttributeGroupRef) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public int sizeOfAttributeGroupArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void setAttributeGroupArray(AttributeGroupRef[] attributeGroupArray) {
        check_orphaned();
        arraySetterHelper(attributeGroupArray, PROPERTY_QNAME[7]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void setAttributeGroupArray(int i, AttributeGroupRef attributeGroup) {
        generatedSetterHelperImpl(attributeGroup, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public AttributeGroupRef insertNewAttributeGroup(int i) {
        AttributeGroupRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AttributeGroupRef) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public AttributeGroupRef addNewAttributeGroup() {
        AttributeGroupRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AttributeGroupRef) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void removeAttributeGroup(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public Wildcard getAnyAttribute() {
        Wildcard wildcard;
        synchronized (monitor()) {
            check_orphaned();
            Wildcard target = (Wildcard) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            wildcard = target == null ? null : target;
        }
        return wildcard;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public boolean isSetAnyAttribute() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void setAnyAttribute(Wildcard anyAttribute) {
        generatedSetterHelperImpl(anyAttribute, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public Wildcard addNewAnyAttribute() {
        Wildcard target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Wildcard) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void unsetAnyAttribute() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public XmlNCName xgetName() {
        XmlNCName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlNCName) get_store().find_attribute_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public boolean isSetName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setStringValue(name);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void xsetName(XmlNCName name) {
        synchronized (monitor()) {
            check_orphaned();
            XmlNCName target = (XmlNCName) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (XmlNCName) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(name);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void unsetName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public boolean getMixed() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[10]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public XmlBoolean xgetMixed() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[10]);
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public boolean isSetMixed() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void setMixed(boolean mixed) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setBooleanValue(mixed);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void xsetMixed(XmlBoolean mixed) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(mixed);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void unsetMixed() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public boolean getAbstract() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[11]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public XmlBoolean xgetAbstract() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[11]);
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public boolean isSetAbstract() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void setAbstract(boolean xabstract) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.setBooleanValue(xabstract);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void xsetAbstract(XmlBoolean xabstract) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.set(xabstract);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void unsetAbstract() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[11]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public Object getFinal() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public DerivationSet xgetFinal() {
        DerivationSet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (DerivationSet) get_store().find_attribute_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public boolean isSetFinal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void setFinal(Object xfinal) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.setObjectValue(xfinal);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void xsetFinal(DerivationSet xfinal) {
        synchronized (monitor()) {
            check_orphaned();
            DerivationSet target = (DerivationSet) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (DerivationSet) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.set(xfinal);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void unsetFinal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[12]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public Object getBlock() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public DerivationSet xgetBlock() {
        DerivationSet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (DerivationSet) get_store().find_attribute_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public boolean isSetBlock() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void setBlock(Object block) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setObjectValue(block);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void xsetBlock(DerivationSet block) {
        synchronized (monitor()) {
            check_orphaned();
            DerivationSet target = (DerivationSet) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (DerivationSet) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(block);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexType
    public void unsetBlock() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }
}
