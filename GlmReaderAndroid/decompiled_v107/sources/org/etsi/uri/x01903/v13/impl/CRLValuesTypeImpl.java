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
import org.etsi.uri.x01903.v13.CRLValuesType;
import org.etsi.uri.x01903.v13.EncapsulatedPKIDataType;

/* loaded from: classes11.dex */
public class CRLValuesTypeImpl extends XmlComplexContentImpl implements CRLValuesType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "EncapsulatedCRLValue")};
    private static final long serialVersionUID = 1;

    public CRLValuesTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.CRLValuesType
    public List<EncapsulatedPKIDataType> getEncapsulatedCRLValueList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.CRLValuesTypeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CRLValuesTypeImpl.this.getEncapsulatedCRLValueArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.CRLValuesTypeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CRLValuesTypeImpl.this.setEncapsulatedCRLValueArray(((Integer) obj).intValue(), (EncapsulatedPKIDataType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.CRLValuesTypeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CRLValuesTypeImpl.this.insertNewEncapsulatedCRLValue(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.CRLValuesTypeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CRLValuesTypeImpl.this.removeEncapsulatedCRLValue(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.CRLValuesTypeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CRLValuesTypeImpl.this.sizeOfEncapsulatedCRLValueArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.CRLValuesType
    public EncapsulatedPKIDataType[] getEncapsulatedCRLValueArray() {
        return (EncapsulatedPKIDataType[]) getXmlObjectArray(PROPERTY_QNAME[0], new EncapsulatedPKIDataType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.CRLValuesType
    public EncapsulatedPKIDataType getEncapsulatedCRLValueArray(int i) {
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

    @Override // org.etsi.uri.x01903.v13.CRLValuesType
    public int sizeOfEncapsulatedCRLValueArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.CRLValuesType
    public void setEncapsulatedCRLValueArray(EncapsulatedPKIDataType[] encapsulatedCRLValueArray) {
        check_orphaned();
        arraySetterHelper(encapsulatedCRLValueArray, PROPERTY_QNAME[0]);
    }

    @Override // org.etsi.uri.x01903.v13.CRLValuesType
    public void setEncapsulatedCRLValueArray(int i, EncapsulatedPKIDataType encapsulatedCRLValue) {
        generatedSetterHelperImpl(encapsulatedCRLValue, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.CRLValuesType
    public EncapsulatedPKIDataType insertNewEncapsulatedCRLValue(int i) {
        EncapsulatedPKIDataType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (EncapsulatedPKIDataType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CRLValuesType
    public EncapsulatedPKIDataType addNewEncapsulatedCRLValue() {
        EncapsulatedPKIDataType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (EncapsulatedPKIDataType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CRLValuesType
    public void removeEncapsulatedCRLValue(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
