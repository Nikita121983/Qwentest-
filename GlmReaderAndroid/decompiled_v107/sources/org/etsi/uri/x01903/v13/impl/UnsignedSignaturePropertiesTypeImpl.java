package org.etsi.uri.x01903.v13.impl;

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
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CertificateValuesType;
import org.etsi.uri.x01903.v13.CompleteCertificateRefsType;
import org.etsi.uri.x01903.v13.CompleteRevocationRefsType;
import org.etsi.uri.x01903.v13.CounterSignatureType;
import org.etsi.uri.x01903.v13.RevocationValuesType;
import org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType;
import org.etsi.uri.x01903.v13.XAdESTimeStampType;

/* loaded from: classes11.dex */
public class UnsignedSignaturePropertiesTypeImpl extends XmlComplexContentImpl implements UnsignedSignaturePropertiesType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "CounterSignature"), new QName(SignatureFacet.XADES_132_NS, "SignatureTimeStamp"), new QName(SignatureFacet.XADES_132_NS, "CompleteCertificateRefs"), new QName(SignatureFacet.XADES_132_NS, "CompleteRevocationRefs"), new QName(SignatureFacet.XADES_132_NS, "AttributeCertificateRefs"), new QName(SignatureFacet.XADES_132_NS, "AttributeRevocationRefs"), new QName(SignatureFacet.XADES_132_NS, "SigAndRefsTimeStamp"), new QName(SignatureFacet.XADES_132_NS, "RefsOnlyTimeStamp"), new QName(SignatureFacet.XADES_132_NS, "CertificateValues"), new QName(SignatureFacet.XADES_132_NS, "RevocationValues"), new QName(SignatureFacet.XADES_132_NS, "AttrAuthoritiesCertValues"), new QName(SignatureFacet.XADES_132_NS, "AttributeRevocationValues"), new QName(SignatureFacet.XADES_132_NS, "ArchiveTimeStamp"), new QName("", PackageRelationship.ID_ATTRIBUTE_NAME)};
    private static final long serialVersionUID = 1;

    public UnsignedSignaturePropertiesTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public List<CounterSignatureType> getCounterSignatureList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getCounterSignatureArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    UnsignedSignaturePropertiesTypeImpl.this.setCounterSignatureArray(((Integer) obj).intValue(), (CounterSignatureType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.insertNewCounterSignature(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    UnsignedSignaturePropertiesTypeImpl.this.removeCounterSignature(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(UnsignedSignaturePropertiesTypeImpl.this.sizeOfCounterSignatureArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CounterSignatureType[] getCounterSignatureArray() {
        return getXmlObjectArray(PROPERTY_QNAME[0], (XmlObject[]) new CounterSignatureType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CounterSignatureType getCounterSignatureArray(int i) {
        CounterSignatureType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public int sizeOfCounterSignatureArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setCounterSignatureArray(CounterSignatureType[] counterSignatureArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) counterSignatureArray, PROPERTY_QNAME[0]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setCounterSignatureArray(int i, CounterSignatureType counterSignature) {
        generatedSetterHelperImpl(counterSignature, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CounterSignatureType insertNewCounterSignature(int i) {
        CounterSignatureType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CounterSignatureType addNewCounterSignature() {
        CounterSignatureType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void removeCounterSignature(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public List<XAdESTimeStampType> getSignatureTimeStampList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getSignatureTimeStampArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    UnsignedSignaturePropertiesTypeImpl.this.setSignatureTimeStampArray(((Integer) obj).intValue(), (XAdESTimeStampType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.insertNewSignatureTimeStamp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    UnsignedSignaturePropertiesTypeImpl.this.removeSignatureTimeStamp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda60
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(UnsignedSignaturePropertiesTypeImpl.this.sizeOfSignatureTimeStampArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public XAdESTimeStampType[] getSignatureTimeStampArray() {
        return (XAdESTimeStampType[]) getXmlObjectArray(PROPERTY_QNAME[1], new XAdESTimeStampType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public XAdESTimeStampType getSignatureTimeStampArray(int i) {
        XAdESTimeStampType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XAdESTimeStampType) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public int sizeOfSignatureTimeStampArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setSignatureTimeStampArray(XAdESTimeStampType[] signatureTimeStampArray) {
        check_orphaned();
        arraySetterHelper(signatureTimeStampArray, PROPERTY_QNAME[1]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setSignatureTimeStampArray(int i, XAdESTimeStampType signatureTimeStamp) {
        generatedSetterHelperImpl(signatureTimeStamp, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public XAdESTimeStampType insertNewSignatureTimeStamp(int i) {
        XAdESTimeStampType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XAdESTimeStampType) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public XAdESTimeStampType addNewSignatureTimeStamp() {
        XAdESTimeStampType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XAdESTimeStampType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void removeSignatureTimeStamp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public List<CompleteCertificateRefsType> getCompleteCertificateRefsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getCompleteCertificateRefsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    UnsignedSignaturePropertiesTypeImpl.this.setCompleteCertificateRefsArray(((Integer) obj).intValue(), (CompleteCertificateRefsType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.insertNewCompleteCertificateRefs(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    UnsignedSignaturePropertiesTypeImpl.this.removeCompleteCertificateRefs(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(UnsignedSignaturePropertiesTypeImpl.this.sizeOfCompleteCertificateRefsArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CompleteCertificateRefsType[] getCompleteCertificateRefsArray() {
        return (CompleteCertificateRefsType[]) getXmlObjectArray(PROPERTY_QNAME[2], new CompleteCertificateRefsType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CompleteCertificateRefsType getCompleteCertificateRefsArray(int i) {
        CompleteCertificateRefsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CompleteCertificateRefsType) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public int sizeOfCompleteCertificateRefsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setCompleteCertificateRefsArray(CompleteCertificateRefsType[] completeCertificateRefsArray) {
        check_orphaned();
        arraySetterHelper(completeCertificateRefsArray, PROPERTY_QNAME[2]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setCompleteCertificateRefsArray(int i, CompleteCertificateRefsType completeCertificateRefs) {
        generatedSetterHelperImpl(completeCertificateRefs, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CompleteCertificateRefsType insertNewCompleteCertificateRefs(int i) {
        CompleteCertificateRefsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CompleteCertificateRefsType) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CompleteCertificateRefsType addNewCompleteCertificateRefs() {
        CompleteCertificateRefsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CompleteCertificateRefsType) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void removeCompleteCertificateRefs(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public List<CompleteRevocationRefsType> getCompleteRevocationRefsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getCompleteRevocationRefsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda40
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    UnsignedSignaturePropertiesTypeImpl.this.setCompleteRevocationRefsArray(((Integer) obj).intValue(), (CompleteRevocationRefsType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.insertNewCompleteRevocationRefs(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    UnsignedSignaturePropertiesTypeImpl.this.removeCompleteRevocationRefs(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(UnsignedSignaturePropertiesTypeImpl.this.sizeOfCompleteRevocationRefsArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CompleteRevocationRefsType[] getCompleteRevocationRefsArray() {
        return (CompleteRevocationRefsType[]) getXmlObjectArray(PROPERTY_QNAME[3], new CompleteRevocationRefsType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CompleteRevocationRefsType getCompleteRevocationRefsArray(int i) {
        CompleteRevocationRefsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CompleteRevocationRefsType) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public int sizeOfCompleteRevocationRefsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setCompleteRevocationRefsArray(CompleteRevocationRefsType[] completeRevocationRefsArray) {
        check_orphaned();
        arraySetterHelper(completeRevocationRefsArray, PROPERTY_QNAME[3]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setCompleteRevocationRefsArray(int i, CompleteRevocationRefsType completeRevocationRefs) {
        generatedSetterHelperImpl(completeRevocationRefs, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CompleteRevocationRefsType insertNewCompleteRevocationRefs(int i) {
        CompleteRevocationRefsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CompleteRevocationRefsType) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CompleteRevocationRefsType addNewCompleteRevocationRefs() {
        CompleteRevocationRefsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CompleteRevocationRefsType) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void removeCompleteRevocationRefs(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public List<CompleteCertificateRefsType> getAttributeCertificateRefsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda50
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getAttributeCertificateRefsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    UnsignedSignaturePropertiesTypeImpl.this.setAttributeCertificateRefsArray(((Integer) obj).intValue(), (CompleteCertificateRefsType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.insertNewAttributeCertificateRefs(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    UnsignedSignaturePropertiesTypeImpl.this.removeAttributeCertificateRefs(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda54
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(UnsignedSignaturePropertiesTypeImpl.this.sizeOfAttributeCertificateRefsArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CompleteCertificateRefsType[] getAttributeCertificateRefsArray() {
        return (CompleteCertificateRefsType[]) getXmlObjectArray(PROPERTY_QNAME[4], new CompleteCertificateRefsType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CompleteCertificateRefsType getAttributeCertificateRefsArray(int i) {
        CompleteCertificateRefsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CompleteCertificateRefsType) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public int sizeOfAttributeCertificateRefsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setAttributeCertificateRefsArray(CompleteCertificateRefsType[] attributeCertificateRefsArray) {
        check_orphaned();
        arraySetterHelper(attributeCertificateRefsArray, PROPERTY_QNAME[4]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setAttributeCertificateRefsArray(int i, CompleteCertificateRefsType attributeCertificateRefs) {
        generatedSetterHelperImpl(attributeCertificateRefs, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CompleteCertificateRefsType insertNewAttributeCertificateRefs(int i) {
        CompleteCertificateRefsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CompleteCertificateRefsType) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CompleteCertificateRefsType addNewAttributeCertificateRefs() {
        CompleteCertificateRefsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CompleteCertificateRefsType) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void removeAttributeCertificateRefs(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public List<CompleteRevocationRefsType> getAttributeRevocationRefsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getAttributeRevocationRefsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    UnsignedSignaturePropertiesTypeImpl.this.setAttributeRevocationRefsArray(((Integer) obj).intValue(), (CompleteRevocationRefsType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.insertNewAttributeRevocationRefs(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    UnsignedSignaturePropertiesTypeImpl.this.removeAttributeRevocationRefs(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(UnsignedSignaturePropertiesTypeImpl.this.sizeOfAttributeRevocationRefsArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CompleteRevocationRefsType[] getAttributeRevocationRefsArray() {
        return (CompleteRevocationRefsType[]) getXmlObjectArray(PROPERTY_QNAME[5], new CompleteRevocationRefsType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CompleteRevocationRefsType getAttributeRevocationRefsArray(int i) {
        CompleteRevocationRefsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CompleteRevocationRefsType) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public int sizeOfAttributeRevocationRefsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setAttributeRevocationRefsArray(CompleteRevocationRefsType[] attributeRevocationRefsArray) {
        check_orphaned();
        arraySetterHelper(attributeRevocationRefsArray, PROPERTY_QNAME[5]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setAttributeRevocationRefsArray(int i, CompleteRevocationRefsType attributeRevocationRefs) {
        generatedSetterHelperImpl(attributeRevocationRefs, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CompleteRevocationRefsType insertNewAttributeRevocationRefs(int i) {
        CompleteRevocationRefsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CompleteRevocationRefsType) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CompleteRevocationRefsType addNewAttributeRevocationRefs() {
        CompleteRevocationRefsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CompleteRevocationRefsType) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void removeAttributeRevocationRefs(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public List<XAdESTimeStampType> getSigAndRefsTimeStampList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getSigAndRefsTimeStampArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    UnsignedSignaturePropertiesTypeImpl.this.setSigAndRefsTimeStampArray(((Integer) obj).intValue(), (XAdESTimeStampType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.insertNewSigAndRefsTimeStamp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    UnsignedSignaturePropertiesTypeImpl.this.removeSigAndRefsTimeStamp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(UnsignedSignaturePropertiesTypeImpl.this.sizeOfSigAndRefsTimeStampArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public XAdESTimeStampType[] getSigAndRefsTimeStampArray() {
        return (XAdESTimeStampType[]) getXmlObjectArray(PROPERTY_QNAME[6], new XAdESTimeStampType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public XAdESTimeStampType getSigAndRefsTimeStampArray(int i) {
        XAdESTimeStampType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XAdESTimeStampType) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public int sizeOfSigAndRefsTimeStampArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setSigAndRefsTimeStampArray(XAdESTimeStampType[] sigAndRefsTimeStampArray) {
        check_orphaned();
        arraySetterHelper(sigAndRefsTimeStampArray, PROPERTY_QNAME[6]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setSigAndRefsTimeStampArray(int i, XAdESTimeStampType sigAndRefsTimeStamp) {
        generatedSetterHelperImpl(sigAndRefsTimeStamp, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public XAdESTimeStampType insertNewSigAndRefsTimeStamp(int i) {
        XAdESTimeStampType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XAdESTimeStampType) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public XAdESTimeStampType addNewSigAndRefsTimeStamp() {
        XAdESTimeStampType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XAdESTimeStampType) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void removeSigAndRefsTimeStamp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public List<XAdESTimeStampType> getRefsOnlyTimeStampList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getRefsOnlyTimeStampArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    UnsignedSignaturePropertiesTypeImpl.this.setRefsOnlyTimeStampArray(((Integer) obj).intValue(), (XAdESTimeStampType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.insertNewRefsOnlyTimeStamp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    UnsignedSignaturePropertiesTypeImpl.this.removeRefsOnlyTimeStamp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(UnsignedSignaturePropertiesTypeImpl.this.sizeOfRefsOnlyTimeStampArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public XAdESTimeStampType[] getRefsOnlyTimeStampArray() {
        return (XAdESTimeStampType[]) getXmlObjectArray(PROPERTY_QNAME[7], new XAdESTimeStampType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public XAdESTimeStampType getRefsOnlyTimeStampArray(int i) {
        XAdESTimeStampType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XAdESTimeStampType) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public int sizeOfRefsOnlyTimeStampArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setRefsOnlyTimeStampArray(XAdESTimeStampType[] refsOnlyTimeStampArray) {
        check_orphaned();
        arraySetterHelper(refsOnlyTimeStampArray, PROPERTY_QNAME[7]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setRefsOnlyTimeStampArray(int i, XAdESTimeStampType refsOnlyTimeStamp) {
        generatedSetterHelperImpl(refsOnlyTimeStamp, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public XAdESTimeStampType insertNewRefsOnlyTimeStamp(int i) {
        XAdESTimeStampType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XAdESTimeStampType) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public XAdESTimeStampType addNewRefsOnlyTimeStamp() {
        XAdESTimeStampType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XAdESTimeStampType) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void removeRefsOnlyTimeStamp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public List<CertificateValuesType> getCertificateValuesList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getCertificateValuesArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    UnsignedSignaturePropertiesTypeImpl.this.setCertificateValuesArray(((Integer) obj).intValue(), (CertificateValuesType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.insertNewCertificateValues(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    UnsignedSignaturePropertiesTypeImpl.this.removeCertificateValues(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda49
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(UnsignedSignaturePropertiesTypeImpl.this.sizeOfCertificateValuesArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CertificateValuesType[] getCertificateValuesArray() {
        return (CertificateValuesType[]) getXmlObjectArray(PROPERTY_QNAME[8], new CertificateValuesType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CertificateValuesType getCertificateValuesArray(int i) {
        CertificateValuesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CertificateValuesType) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public int sizeOfCertificateValuesArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setCertificateValuesArray(CertificateValuesType[] certificateValuesArray) {
        check_orphaned();
        arraySetterHelper(certificateValuesArray, PROPERTY_QNAME[8]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setCertificateValuesArray(int i, CertificateValuesType certificateValues) {
        generatedSetterHelperImpl(certificateValues, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CertificateValuesType insertNewCertificateValues(int i) {
        CertificateValuesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CertificateValuesType) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CertificateValuesType addNewCertificateValues() {
        CertificateValuesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CertificateValuesType) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void removeCertificateValues(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public List<RevocationValuesType> getRevocationValuesList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getRevocationValuesArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    UnsignedSignaturePropertiesTypeImpl.this.setRevocationValuesArray(((Integer) obj).intValue(), (RevocationValuesType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.insertNewRevocationValues(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    UnsignedSignaturePropertiesTypeImpl.this.removeRevocationValues(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(UnsignedSignaturePropertiesTypeImpl.this.sizeOfRevocationValuesArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public RevocationValuesType[] getRevocationValuesArray() {
        return (RevocationValuesType[]) getXmlObjectArray(PROPERTY_QNAME[9], new RevocationValuesType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public RevocationValuesType getRevocationValuesArray(int i) {
        RevocationValuesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (RevocationValuesType) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public int sizeOfRevocationValuesArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setRevocationValuesArray(RevocationValuesType[] revocationValuesArray) {
        check_orphaned();
        arraySetterHelper(revocationValuesArray, PROPERTY_QNAME[9]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setRevocationValuesArray(int i, RevocationValuesType revocationValues) {
        generatedSetterHelperImpl(revocationValues, PROPERTY_QNAME[9], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public RevocationValuesType insertNewRevocationValues(int i) {
        RevocationValuesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (RevocationValuesType) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public RevocationValuesType addNewRevocationValues() {
        RevocationValuesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (RevocationValuesType) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void removeRevocationValues(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public List<CertificateValuesType> getAttrAuthoritiesCertValuesList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getAttrAuthoritiesCertValuesArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    UnsignedSignaturePropertiesTypeImpl.this.setAttrAuthoritiesCertValuesArray(((Integer) obj).intValue(), (CertificateValuesType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.insertNewAttrAuthoritiesCertValues(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    UnsignedSignaturePropertiesTypeImpl.this.removeAttrAuthoritiesCertValues(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(UnsignedSignaturePropertiesTypeImpl.this.sizeOfAttrAuthoritiesCertValuesArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CertificateValuesType[] getAttrAuthoritiesCertValuesArray() {
        return (CertificateValuesType[]) getXmlObjectArray(PROPERTY_QNAME[10], new CertificateValuesType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CertificateValuesType getAttrAuthoritiesCertValuesArray(int i) {
        CertificateValuesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CertificateValuesType) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public int sizeOfAttrAuthoritiesCertValuesArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setAttrAuthoritiesCertValuesArray(CertificateValuesType[] attrAuthoritiesCertValuesArray) {
        check_orphaned();
        arraySetterHelper(attrAuthoritiesCertValuesArray, PROPERTY_QNAME[10]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setAttrAuthoritiesCertValuesArray(int i, CertificateValuesType attrAuthoritiesCertValues) {
        generatedSetterHelperImpl(attrAuthoritiesCertValues, PROPERTY_QNAME[10], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CertificateValuesType insertNewAttrAuthoritiesCertValues(int i) {
        CertificateValuesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CertificateValuesType) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public CertificateValuesType addNewAttrAuthoritiesCertValues() {
        CertificateValuesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CertificateValuesType) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void removeAttrAuthoritiesCertValues(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public List<RevocationValuesType> getAttributeRevocationValuesList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getAttributeRevocationValuesArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    UnsignedSignaturePropertiesTypeImpl.this.setAttributeRevocationValuesArray(((Integer) obj).intValue(), (RevocationValuesType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.insertNewAttributeRevocationValues(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    UnsignedSignaturePropertiesTypeImpl.this.removeAttributeRevocationValues(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(UnsignedSignaturePropertiesTypeImpl.this.sizeOfAttributeRevocationValuesArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public RevocationValuesType[] getAttributeRevocationValuesArray() {
        return (RevocationValuesType[]) getXmlObjectArray(PROPERTY_QNAME[11], new RevocationValuesType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public RevocationValuesType getAttributeRevocationValuesArray(int i) {
        RevocationValuesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (RevocationValuesType) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public int sizeOfAttributeRevocationValuesArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setAttributeRevocationValuesArray(RevocationValuesType[] attributeRevocationValuesArray) {
        check_orphaned();
        arraySetterHelper(attributeRevocationValuesArray, PROPERTY_QNAME[11]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setAttributeRevocationValuesArray(int i, RevocationValuesType attributeRevocationValues) {
        generatedSetterHelperImpl(attributeRevocationValues, PROPERTY_QNAME[11], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public RevocationValuesType insertNewAttributeRevocationValues(int i) {
        RevocationValuesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (RevocationValuesType) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public RevocationValuesType addNewAttributeRevocationValues() {
        RevocationValuesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (RevocationValuesType) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void removeAttributeRevocationValues(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public List<XAdESTimeStampType> getArchiveTimeStampList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda55
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.getArchiveTimeStampArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda61
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    UnsignedSignaturePropertiesTypeImpl.this.setArchiveTimeStampArray(((Integer) obj).intValue(), (XAdESTimeStampType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda62
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return UnsignedSignaturePropertiesTypeImpl.this.insertNewArchiveTimeStamp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda63
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    UnsignedSignaturePropertiesTypeImpl.this.removeArchiveTimeStamp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.UnsignedSignaturePropertiesTypeImpl$$ExternalSyntheticLambda64
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(UnsignedSignaturePropertiesTypeImpl.this.sizeOfArchiveTimeStampArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public XAdESTimeStampType[] getArchiveTimeStampArray() {
        return (XAdESTimeStampType[]) getXmlObjectArray(PROPERTY_QNAME[12], new XAdESTimeStampType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public XAdESTimeStampType getArchiveTimeStampArray(int i) {
        XAdESTimeStampType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XAdESTimeStampType) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public int sizeOfArchiveTimeStampArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setArchiveTimeStampArray(XAdESTimeStampType[] archiveTimeStampArray) {
        check_orphaned();
        arraySetterHelper(archiveTimeStampArray, PROPERTY_QNAME[12]);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setArchiveTimeStampArray(int i, XAdESTimeStampType archiveTimeStamp) {
        generatedSetterHelperImpl(archiveTimeStamp, PROPERTY_QNAME[12], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public XAdESTimeStampType insertNewArchiveTimeStamp(int i) {
        XAdESTimeStampType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XAdESTimeStampType) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public XAdESTimeStampType addNewArchiveTimeStamp() {
        XAdESTimeStampType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XAdESTimeStampType) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void removeArchiveTimeStamp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public XmlID xgetId() {
        XmlID target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setStringValue(id);
        }
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void xsetId(XmlID id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlID target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (XmlID) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(id);
        }
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }
}
