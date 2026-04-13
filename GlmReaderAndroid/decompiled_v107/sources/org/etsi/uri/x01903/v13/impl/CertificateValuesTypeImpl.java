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
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.AnyType;
import org.etsi.uri.x01903.v13.CertificateValuesType;
import org.etsi.uri.x01903.v13.EncapsulatedPKIDataType;

/* loaded from: classes11.dex */
public class CertificateValuesTypeImpl extends XmlComplexContentImpl implements CertificateValuesType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "EncapsulatedX509Certificate"), new QName(SignatureFacet.XADES_132_NS, "OtherCertificate"), new QName("", PackageRelationship.ID_ATTRIBUTE_NAME)};
    private static final long serialVersionUID = 1;

    public CertificateValuesTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public List<EncapsulatedPKIDataType> getEncapsulatedX509CertificateList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.CertificateValuesTypeImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CertificateValuesTypeImpl.this.getEncapsulatedX509CertificateArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.CertificateValuesTypeImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CertificateValuesTypeImpl.this.setEncapsulatedX509CertificateArray(((Integer) obj).intValue(), (EncapsulatedPKIDataType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.CertificateValuesTypeImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CertificateValuesTypeImpl.this.insertNewEncapsulatedX509Certificate(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.CertificateValuesTypeImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CertificateValuesTypeImpl.this.removeEncapsulatedX509Certificate(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.CertificateValuesTypeImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CertificateValuesTypeImpl.this.sizeOfEncapsulatedX509CertificateArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public EncapsulatedPKIDataType[] getEncapsulatedX509CertificateArray() {
        return (EncapsulatedPKIDataType[]) getXmlObjectArray(PROPERTY_QNAME[0], new EncapsulatedPKIDataType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public EncapsulatedPKIDataType getEncapsulatedX509CertificateArray(int i) {
        EncapsulatedPKIDataType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (EncapsulatedPKIDataType) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public int sizeOfEncapsulatedX509CertificateArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public void setEncapsulatedX509CertificateArray(EncapsulatedPKIDataType[] encapsulatedX509CertificateArray) {
        check_orphaned();
        arraySetterHelper(encapsulatedX509CertificateArray, PROPERTY_QNAME[0]);
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public void setEncapsulatedX509CertificateArray(int i, EncapsulatedPKIDataType encapsulatedX509Certificate) {
        generatedSetterHelperImpl(encapsulatedX509Certificate, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public EncapsulatedPKIDataType insertNewEncapsulatedX509Certificate(int i) {
        EncapsulatedPKIDataType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (EncapsulatedPKIDataType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public EncapsulatedPKIDataType addNewEncapsulatedX509Certificate() {
        EncapsulatedPKIDataType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (EncapsulatedPKIDataType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public void removeEncapsulatedX509Certificate(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public List<AnyType> getOtherCertificateList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.CertificateValuesTypeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CertificateValuesTypeImpl.this.getOtherCertificateArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.CertificateValuesTypeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CertificateValuesTypeImpl.this.setOtherCertificateArray(((Integer) obj).intValue(), (AnyType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.CertificateValuesTypeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CertificateValuesTypeImpl.this.insertNewOtherCertificate(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.CertificateValuesTypeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CertificateValuesTypeImpl.this.removeOtherCertificate(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.CertificateValuesTypeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CertificateValuesTypeImpl.this.sizeOfOtherCertificateArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public AnyType[] getOtherCertificateArray() {
        return (AnyType[]) getXmlObjectArray(PROPERTY_QNAME[1], new AnyType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public AnyType getOtherCertificateArray(int i) {
        AnyType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AnyType) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public int sizeOfOtherCertificateArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public void setOtherCertificateArray(AnyType[] otherCertificateArray) {
        check_orphaned();
        arraySetterHelper(otherCertificateArray, PROPERTY_QNAME[1]);
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public void setOtherCertificateArray(int i, AnyType otherCertificate) {
        generatedSetterHelperImpl(otherCertificate, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public AnyType insertNewOtherCertificate(int i) {
        AnyType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AnyType) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public AnyType addNewOtherCertificate() {
        AnyType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AnyType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public void removeOtherCertificate(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public XmlID xgetId() {
        XmlID target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(id);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public void xsetId(XmlID id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlID target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (XmlID) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(id);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CertificateValuesType
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }
}
