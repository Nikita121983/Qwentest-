package org.etsi.uri.x01903.v13.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.AnyType;
import org.etsi.uri.x01903.v13.SigPolicyQualifiersListType;

/* loaded from: classes11.dex */
public class SigPolicyQualifiersListTypeImpl extends XmlComplexContentImpl implements SigPolicyQualifiersListType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "SigPolicyQualifier")};
    private static final long serialVersionUID = 1;

    public SigPolicyQualifiersListTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.SigPolicyQualifiersListType
    public List<AnyType> getSigPolicyQualifierList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.SigPolicyQualifiersListTypeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SigPolicyQualifiersListTypeImpl.this.getSigPolicyQualifierArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.SigPolicyQualifiersListTypeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    SigPolicyQualifiersListTypeImpl.this.setSigPolicyQualifierArray(((Integer) obj).intValue(), (AnyType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.SigPolicyQualifiersListTypeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SigPolicyQualifiersListTypeImpl.this.insertNewSigPolicyQualifier(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.SigPolicyQualifiersListTypeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    SigPolicyQualifiersListTypeImpl.this.removeSigPolicyQualifier(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.SigPolicyQualifiersListTypeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(SigPolicyQualifiersListTypeImpl.this.sizeOfSigPolicyQualifierArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.SigPolicyQualifiersListType
    public AnyType[] getSigPolicyQualifierArray() {
        return (AnyType[]) getXmlObjectArray(PROPERTY_QNAME[0], new AnyType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.SigPolicyQualifiersListType
    public AnyType getSigPolicyQualifierArray(int i) {
        AnyType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AnyType) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SigPolicyQualifiersListType
    public int sizeOfSigPolicyQualifierArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.SigPolicyQualifiersListType
    public void setSigPolicyQualifierArray(AnyType[] sigPolicyQualifierArray) {
        check_orphaned();
        arraySetterHelper(sigPolicyQualifierArray, PROPERTY_QNAME[0]);
    }

    @Override // org.etsi.uri.x01903.v13.SigPolicyQualifiersListType
    public void setSigPolicyQualifierArray(int i, AnyType sigPolicyQualifier) {
        generatedSetterHelperImpl(sigPolicyQualifier, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.SigPolicyQualifiersListType
    public AnyType insertNewSigPolicyQualifier(int i) {
        AnyType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AnyType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SigPolicyQualifiersListType
    public AnyType addNewSigPolicyQualifier() {
        AnyType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AnyType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SigPolicyQualifiersListType
    public void removeSigPolicyQualifier(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
