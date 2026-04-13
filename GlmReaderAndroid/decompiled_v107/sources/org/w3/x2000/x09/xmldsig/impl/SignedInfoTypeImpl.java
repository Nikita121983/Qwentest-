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
import org.w3.x2000.x09.xmldsig.CanonicalizationMethodType;
import org.w3.x2000.x09.xmldsig.ReferenceType;
import org.w3.x2000.x09.xmldsig.SignatureMethodType;
import org.w3.x2000.x09.xmldsig.SignedInfoType;

/* loaded from: classes12.dex */
public class SignedInfoTypeImpl extends XmlComplexContentImpl implements SignedInfoType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XML_DIGSIG_NS, "CanonicalizationMethod"), new QName(SignatureFacet.XML_DIGSIG_NS, "SignatureMethod"), new QName(SignatureFacet.XML_DIGSIG_NS, "Reference"), new QName("", PackageRelationship.ID_ATTRIBUTE_NAME)};
    private static final long serialVersionUID = 1;

    public SignedInfoTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public CanonicalizationMethodType getCanonicalizationMethod() {
        CanonicalizationMethodType canonicalizationMethodType;
        synchronized (monitor()) {
            check_orphaned();
            CanonicalizationMethodType target = (CanonicalizationMethodType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            canonicalizationMethodType = target == null ? null : target;
        }
        return canonicalizationMethodType;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public void setCanonicalizationMethod(CanonicalizationMethodType canonicalizationMethod) {
        generatedSetterHelperImpl(canonicalizationMethod, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public CanonicalizationMethodType addNewCanonicalizationMethod() {
        CanonicalizationMethodType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CanonicalizationMethodType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public SignatureMethodType getSignatureMethod() {
        SignatureMethodType signatureMethodType;
        synchronized (monitor()) {
            check_orphaned();
            SignatureMethodType target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            signatureMethodType = target == null ? null : target;
        }
        return signatureMethodType;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public void setSignatureMethod(SignatureMethodType signatureMethod) {
        generatedSetterHelperImpl(signatureMethod, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public SignatureMethodType addNewSignatureMethod() {
        SignatureMethodType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public List<ReferenceType> getReferenceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.w3.x2000.x09.xmldsig.impl.SignedInfoTypeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SignedInfoTypeImpl.this.getReferenceArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.w3.x2000.x09.xmldsig.impl.SignedInfoTypeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    SignedInfoTypeImpl.this.setReferenceArray(((Integer) obj).intValue(), (ReferenceType) obj2);
                }
            }, new Function() { // from class: org.w3.x2000.x09.xmldsig.impl.SignedInfoTypeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SignedInfoTypeImpl.this.insertNewReference(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.w3.x2000.x09.xmldsig.impl.SignedInfoTypeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    SignedInfoTypeImpl.this.removeReference(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.w3.x2000.x09.xmldsig.impl.SignedInfoTypeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(SignedInfoTypeImpl.this.sizeOfReferenceArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public ReferenceType[] getReferenceArray() {
        return (ReferenceType[]) getXmlObjectArray(PROPERTY_QNAME[2], new ReferenceType[0]);
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public ReferenceType getReferenceArray(int i) {
        ReferenceType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ReferenceType) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public int sizeOfReferenceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public void setReferenceArray(ReferenceType[] referenceArray) {
        check_orphaned();
        arraySetterHelper(referenceArray, PROPERTY_QNAME[2]);
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public void setReferenceArray(int i, ReferenceType reference) {
        generatedSetterHelperImpl(reference, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public ReferenceType insertNewReference(int i) {
        ReferenceType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ReferenceType) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public ReferenceType addNewReference() {
        ReferenceType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ReferenceType) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public void removeReference(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public XmlID xgetId() {
        XmlID target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(id);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public void xsetId(XmlID id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlID target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlID) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(id);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.SignedInfoType
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }
}
