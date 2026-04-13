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
import org.etsi.uri.x01903.v13.CRLRefType;
import org.etsi.uri.x01903.v13.CRLRefsType;

/* loaded from: classes11.dex */
public class CRLRefsTypeImpl extends XmlComplexContentImpl implements CRLRefsType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "CRLRef")};
    private static final long serialVersionUID = 1;

    public CRLRefsTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.CRLRefsType
    public List<CRLRefType> getCRLRefList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.CRLRefsTypeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CRLRefsTypeImpl.this.getCRLRefArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.CRLRefsTypeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CRLRefsTypeImpl.this.setCRLRefArray(((Integer) obj).intValue(), (CRLRefType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.CRLRefsTypeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CRLRefsTypeImpl.this.insertNewCRLRef(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.CRLRefsTypeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CRLRefsTypeImpl.this.removeCRLRef(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.CRLRefsTypeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CRLRefsTypeImpl.this.sizeOfCRLRefArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.CRLRefsType
    public CRLRefType[] getCRLRefArray() {
        return (CRLRefType[]) getXmlObjectArray(PROPERTY_QNAME[0], new CRLRefType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.CRLRefsType
    public CRLRefType getCRLRefArray(int i) {
        CRLRefType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CRLRefType) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CRLRefsType
    public int sizeOfCRLRefArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.CRLRefsType
    public void setCRLRefArray(CRLRefType[] crlRefArray) {
        check_orphaned();
        arraySetterHelper(crlRefArray, PROPERTY_QNAME[0]);
    }

    @Override // org.etsi.uri.x01903.v13.CRLRefsType
    public void setCRLRefArray(int i, CRLRefType crlRef) {
        generatedSetterHelperImpl(crlRef, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.CRLRefsType
    public CRLRefType insertNewCRLRef(int i) {
        CRLRefType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CRLRefType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CRLRefsType
    public CRLRefType addNewCRLRef() {
        CRLRefType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CRLRefType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CRLRefsType
    public void removeCRLRef(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
