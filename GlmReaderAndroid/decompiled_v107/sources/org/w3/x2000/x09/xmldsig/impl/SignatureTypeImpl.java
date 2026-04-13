package org.w3.x2000.x09.xmldsig.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.w3.x2000.x09.xmldsig.KeyInfoType;
import org.w3.x2000.x09.xmldsig.ObjectType;
import org.w3.x2000.x09.xmldsig.SignatureType;
import org.w3.x2000.x09.xmldsig.SignatureValueType;
import org.w3.x2000.x09.xmldsig.SignedInfoType;

/* loaded from: classes12.dex */
public class SignatureTypeImpl extends XmlComplexContentImpl implements SignatureType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XML_DIGSIG_NS, "SignedInfo"), new QName(SignatureFacet.XML_DIGSIG_NS, "SignatureValue"), new QName(SignatureFacet.XML_DIGSIG_NS, "KeyInfo"), new QName(SignatureFacet.XML_DIGSIG_NS, "Object"), new QName("", PackageRelationship.ID_ATTRIBUTE_NAME)};
    private static final long serialVersionUID = 1;

    public SignatureTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public SignedInfoType getSignedInfo() {
        SignedInfoType signedInfoType;
        synchronized (monitor()) {
            check_orphaned();
            SignedInfoType target = (SignedInfoType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            signedInfoType = target == null ? null : target;
        }
        return signedInfoType;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public void setSignedInfo(SignedInfoType signedInfo) {
        generatedSetterHelperImpl(signedInfo, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public SignedInfoType addNewSignedInfo() {
        SignedInfoType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (SignedInfoType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public SignatureValueType getSignatureValue() {
        SignatureValueType signatureValueType;
        synchronized (monitor()) {
            check_orphaned();
            SignatureValueType target = (SignatureValueType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            signatureValueType = target == null ? null : target;
        }
        return signatureValueType;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public void setSignatureValue(SignatureValueType signatureValue) {
        generatedSetterHelperImpl(signatureValue, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public SignatureValueType addNewSignatureValue() {
        SignatureValueType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (SignatureValueType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public KeyInfoType getKeyInfo() {
        KeyInfoType keyInfoType;
        synchronized (monitor()) {
            check_orphaned();
            KeyInfoType target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            keyInfoType = target == null ? null : target;
        }
        return keyInfoType;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public boolean isSetKeyInfo() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public void setKeyInfo(KeyInfoType keyInfo) {
        generatedSetterHelperImpl(keyInfo, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public KeyInfoType addNewKeyInfo() {
        KeyInfoType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public void unsetKeyInfo() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public List<ObjectType> getObjectList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.w3.x2000.x09.xmldsig.impl.SignatureTypeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SignatureTypeImpl.this.getObjectArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.w3.x2000.x09.xmldsig.impl.SignatureTypeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    SignatureTypeImpl.this.setObjectArray(((Integer) obj).intValue(), (ObjectType) obj2);
                }
            }, new Function() { // from class: org.w3.x2000.x09.xmldsig.impl.SignatureTypeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SignatureTypeImpl.this.insertNewObject(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.w3.x2000.x09.xmldsig.impl.SignatureTypeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    SignatureTypeImpl.this.removeObject(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.w3.x2000.x09.xmldsig.impl.SignatureTypeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(SignatureTypeImpl.this.sizeOfObjectArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public ObjectType[] getObjectArray() {
        return (ObjectType[]) getXmlObjectArray(PROPERTY_QNAME[3], new ObjectType[0]);
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public ObjectType getObjectArray(int i) {
        ObjectType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ObjectType) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public int sizeOfObjectArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public void setObjectArray(ObjectType[] objectArray) {
        check_orphaned();
        arraySetterHelper(objectArray, PROPERTY_QNAME[3]);
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public void setObjectArray(int i, ObjectType object) {
        generatedSetterHelperImpl(object, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public ObjectType insertNewObject(int i) {
        ObjectType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ObjectType) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public ObjectType addNewObject() {
        ObjectType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ObjectType) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public void removeObject(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public XmlID xgetId() {
        XmlID target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setStringValue(id);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public void xsetId(XmlID id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlID target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlID) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(id);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureType
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }
}
