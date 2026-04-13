package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.math.BigInteger;
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
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.xb.xsdschema.AllNNI;
import org.apache.xmlbeans.impl.xb.xsdschema.BlockSet;
import org.apache.xmlbeans.impl.xb.xsdschema.DerivationSet;
import org.apache.xmlbeans.impl.xb.xsdschema.Element;
import org.apache.xmlbeans.impl.xb.xsdschema.FormChoice;
import org.apache.xmlbeans.impl.xb.xsdschema.Keybase;
import org.apache.xmlbeans.impl.xb.xsdschema.KeyrefDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;

/* loaded from: classes11.dex */
public class ElementImpl extends AnnotatedImpl implements Element {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "simpleType"), new QName("http://www.w3.org/2001/XMLSchema", "complexType"), new QName("http://www.w3.org/2001/XMLSchema", "unique"), new QName("http://www.w3.org/2001/XMLSchema", "key"), new QName("http://www.w3.org/2001/XMLSchema", "keyref"), new QName("", "name"), new QName("", "ref"), new QName("", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY), new QName("", "substitutionGroup"), new QName("", "minOccurs"), new QName("", "maxOccurs"), new QName("", "default"), new QName("", "fixed"), new QName("", "nillable"), new QName("", "abstract"), new QName("", "final"), new QName("", "block"), new QName("", "form")};
    private static final long serialVersionUID = 1;

    public ElementImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public LocalSimpleType getSimpleType() {
        LocalSimpleType localSimpleType;
        synchronized (monitor()) {
            check_orphaned();
            LocalSimpleType target = (LocalSimpleType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            localSimpleType = target == null ? null : target;
        }
        return localSimpleType;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public boolean isSetSimpleType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setSimpleType(LocalSimpleType simpleType) {
        generatedSetterHelperImpl(simpleType, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public LocalSimpleType addNewSimpleType() {
        LocalSimpleType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (LocalSimpleType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void unsetSimpleType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public LocalComplexType getComplexType() {
        LocalComplexType localComplexType;
        synchronized (monitor()) {
            check_orphaned();
            LocalComplexType target = (LocalComplexType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            localComplexType = target == null ? null : target;
        }
        return localComplexType;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public boolean isSetComplexType() {
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

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setComplexType(LocalComplexType complexType) {
        generatedSetterHelperImpl(complexType, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public LocalComplexType addNewComplexType() {
        LocalComplexType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (LocalComplexType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void unsetComplexType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public List<Keybase> getUniqueList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ElementImpl.this.getUniqueArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ElementImpl.this.setUniqueArray(((Integer) obj).intValue(), (Keybase) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ElementImpl.this.insertNewUnique(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ElementImpl.this.removeUnique(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(ElementImpl.this.sizeOfUniqueArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public Keybase[] getUniqueArray() {
        return (Keybase[]) getXmlObjectArray(PROPERTY_QNAME[2], new Keybase[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public Keybase getUniqueArray(int i) {
        Keybase target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Keybase) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public int sizeOfUniqueArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setUniqueArray(Keybase[] uniqueArray) {
        check_orphaned();
        arraySetterHelper(uniqueArray, PROPERTY_QNAME[2]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setUniqueArray(int i, Keybase unique) {
        generatedSetterHelperImpl(unique, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public Keybase insertNewUnique(int i) {
        Keybase target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Keybase) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public Keybase addNewUnique() {
        Keybase target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Keybase) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void removeUnique(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public List<Keybase> getKeyList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ElementImpl.this.getKeyArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ElementImpl.this.setKeyArray(((Integer) obj).intValue(), (Keybase) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ElementImpl.this.insertNewKey(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ElementImpl.this.removeKey(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(ElementImpl.this.sizeOfKeyArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public Keybase[] getKeyArray() {
        return (Keybase[]) getXmlObjectArray(PROPERTY_QNAME[3], new Keybase[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public Keybase getKeyArray(int i) {
        Keybase target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Keybase) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public int sizeOfKeyArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setKeyArray(Keybase[] keyArray) {
        check_orphaned();
        arraySetterHelper(keyArray, PROPERTY_QNAME[3]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setKeyArray(int i, Keybase key) {
        generatedSetterHelperImpl(key, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public Keybase insertNewKey(int i) {
        Keybase target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Keybase) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public Keybase addNewKey() {
        Keybase target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Keybase) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void removeKey(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public List<KeyrefDocument.Keyref> getKeyrefList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ElementImpl.this.getKeyrefArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ElementImpl.this.setKeyrefArray(((Integer) obj).intValue(), (KeyrefDocument.Keyref) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ElementImpl.this.insertNewKeyref(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ElementImpl.this.removeKeyref(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(ElementImpl.this.sizeOfKeyrefArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public KeyrefDocument.Keyref[] getKeyrefArray() {
        return (KeyrefDocument.Keyref[]) getXmlObjectArray(PROPERTY_QNAME[4], new KeyrefDocument.Keyref[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public KeyrefDocument.Keyref getKeyrefArray(int i) {
        KeyrefDocument.Keyref target;
        synchronized (monitor()) {
            check_orphaned();
            target = (KeyrefDocument.Keyref) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public int sizeOfKeyrefArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setKeyrefArray(KeyrefDocument.Keyref[] keyrefArray) {
        check_orphaned();
        arraySetterHelper(keyrefArray, PROPERTY_QNAME[4]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setKeyrefArray(int i, KeyrefDocument.Keyref keyref) {
        generatedSetterHelperImpl(keyref, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public KeyrefDocument.Keyref insertNewKeyref(int i) {
        KeyrefDocument.Keyref target;
        synchronized (monitor()) {
            check_orphaned();
            target = (KeyrefDocument.Keyref) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public KeyrefDocument.Keyref addNewKeyref() {
        KeyrefDocument.Keyref target;
        synchronized (monitor()) {
            check_orphaned();
            target = (KeyrefDocument.Keyref) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void removeKeyref(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public XmlNCName xgetName() {
        XmlNCName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlNCName) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public boolean isSetName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setStringValue(name);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void xsetName(XmlNCName name) {
        synchronized (monitor()) {
            check_orphaned();
            XmlNCName target = (XmlNCName) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlNCName) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(name);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void unsetName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public QName getRef() {
        QName qNameValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            qNameValue = target == null ? null : target.getQNameValue();
        }
        return qNameValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public XmlQName xgetRef() {
        XmlQName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public boolean isSetRef() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setRef(QName ref) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setQNameValue(ref);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void xsetRef(XmlQName ref) {
        synchronized (monitor()) {
            check_orphaned();
            XmlQName target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (XmlQName) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(ref);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void unsetRef() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public QName getType() {
        QName qNameValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            qNameValue = target == null ? null : target.getQNameValue();
        }
        return qNameValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public XmlQName xgetType() {
        XmlQName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public boolean isSetType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setType(QName type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setQNameValue(type);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void xsetType(XmlQName type) {
        synchronized (monitor()) {
            check_orphaned();
            XmlQName target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (XmlQName) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(type);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public QName getSubstitutionGroup() {
        QName qNameValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            qNameValue = target == null ? null : target.getQNameValue();
        }
        return qNameValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public XmlQName xgetSubstitutionGroup() {
        XmlQName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public boolean isSetSubstitutionGroup() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setSubstitutionGroup(QName substitutionGroup) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setQNameValue(substitutionGroup);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void xsetSubstitutionGroup(XmlQName substitutionGroup) {
        synchronized (monitor()) {
            check_orphaned();
            XmlQName target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (XmlQName) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(substitutionGroup);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void unsetSubstitutionGroup() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public BigInteger getMinOccurs() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[9]);
            }
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public XmlNonNegativeInteger xgetMinOccurs() {
        XmlNonNegativeInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlNonNegativeInteger) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (XmlNonNegativeInteger) get_default_attribute_value(PROPERTY_QNAME[9]);
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public boolean isSetMinOccurs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setMinOccurs(BigInteger minOccurs) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setBigIntegerValue(minOccurs);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void xsetMinOccurs(XmlNonNegativeInteger minOccurs) {
        synchronized (monitor()) {
            check_orphaned();
            XmlNonNegativeInteger target = (XmlNonNegativeInteger) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (XmlNonNegativeInteger) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(minOccurs);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void unsetMinOccurs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public Object getMaxOccurs() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[10]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public AllNNI xgetMaxOccurs() {
        AllNNI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AllNNI) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (AllNNI) get_default_attribute_value(PROPERTY_QNAME[10]);
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public boolean isSetMaxOccurs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setMaxOccurs(Object maxOccurs) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setObjectValue(maxOccurs);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void xsetMaxOccurs(AllNNI maxOccurs) {
        synchronized (monitor()) {
            check_orphaned();
            AllNNI target = (AllNNI) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (AllNNI) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(maxOccurs);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void unsetMaxOccurs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public String getDefault() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public XmlString xgetDefault() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public boolean isSetDefault() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setDefault(String xdefault) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.setStringValue(xdefault);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void xsetDefault(XmlString xdefault) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.set(xdefault);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void unsetDefault() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[11]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public String getFixed() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public XmlString xgetFixed() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public boolean isSetFixed() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setFixed(String fixed) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.setStringValue(fixed);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void xsetFixed(XmlString fixed) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.set(fixed);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void unsetFixed() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[12]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public boolean getNillable() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[13]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public XmlBoolean xgetNillable() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[13]);
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public boolean isSetNillable() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setNillable(boolean nillable) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setBooleanValue(nillable);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void xsetNillable(XmlBoolean nillable) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(nillable);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void unsetNillable() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public boolean getAbstract() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[14]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public XmlBoolean xgetAbstract() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[14]);
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public boolean isSetAbstract() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[14]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setAbstract(boolean xabstract) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.setBooleanValue(xabstract);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void xsetAbstract(XmlBoolean xabstract) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.set(xabstract);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void unsetAbstract() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[14]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public Object getFinal() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public DerivationSet xgetFinal() {
        DerivationSet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (DerivationSet) get_store().find_attribute_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public boolean isSetFinal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[15]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setFinal(Object xfinal) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.setObjectValue(xfinal);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void xsetFinal(DerivationSet xfinal) {
        synchronized (monitor()) {
            check_orphaned();
            DerivationSet target = (DerivationSet) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (DerivationSet) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.set(xfinal);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void unsetFinal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[15]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public Object getBlock() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public BlockSet xgetBlock() {
        BlockSet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (BlockSet) get_store().find_attribute_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public boolean isSetBlock() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[16]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setBlock(Object block) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.setObjectValue(block);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void xsetBlock(BlockSet block) {
        synchronized (monitor()) {
            check_orphaned();
            BlockSet target = (BlockSet) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (BlockSet) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.set(block);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void unsetBlock() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[16]);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public FormChoice.Enum getForm() {
        FormChoice.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            r1 = target == null ? null : (FormChoice.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public FormChoice xgetForm() {
        FormChoice target;
        synchronized (monitor()) {
            check_orphaned();
            target = (FormChoice) get_store().find_attribute_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public boolean isSetForm() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[17]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setForm(FormChoice.Enum form) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.setEnumValue(form);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void xsetForm(FormChoice form) {
        synchronized (monitor()) {
            check_orphaned();
            FormChoice target = (FormChoice) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (FormChoice) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.set(form);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void unsetForm() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[17]);
        }
    }
}
