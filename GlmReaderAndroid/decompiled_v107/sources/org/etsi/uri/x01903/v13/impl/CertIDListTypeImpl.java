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
import org.etsi.uri.x01903.v13.CertIDListType;
import org.etsi.uri.x01903.v13.CertIDType;

/* loaded from: classes11.dex */
public class CertIDListTypeImpl extends XmlComplexContentImpl implements CertIDListType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "Cert")};
    private static final long serialVersionUID = 1;

    public CertIDListTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.CertIDListType
    public List<CertIDType> getCertList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.CertIDListTypeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CertIDListTypeImpl.this.getCertArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.CertIDListTypeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CertIDListTypeImpl.this.setCertArray(((Integer) obj).intValue(), (CertIDType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.CertIDListTypeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CertIDListTypeImpl.this.insertNewCert(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.CertIDListTypeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CertIDListTypeImpl.this.removeCert(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.CertIDListTypeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CertIDListTypeImpl.this.sizeOfCertArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.CertIDListType
    public CertIDType[] getCertArray() {
        return (CertIDType[]) getXmlObjectArray(PROPERTY_QNAME[0], new CertIDType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.CertIDListType
    public CertIDType getCertArray(int i) {
        CertIDType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CertIDType) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CertIDListType
    public int sizeOfCertArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.CertIDListType
    public void setCertArray(CertIDType[] certArray) {
        check_orphaned();
        arraySetterHelper(certArray, PROPERTY_QNAME[0]);
    }

    @Override // org.etsi.uri.x01903.v13.CertIDListType
    public void setCertArray(int i, CertIDType cert) {
        generatedSetterHelperImpl(cert, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.CertIDListType
    public CertIDType insertNewCert(int i) {
        CertIDType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CertIDType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CertIDListType
    public CertIDType addNewCert() {
        CertIDType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CertIDType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CertIDListType
    public void removeCert(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
