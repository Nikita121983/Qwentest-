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
import org.etsi.uri.x01903.v13.AnyType;
import org.etsi.uri.x01903.v13.EncapsulatedPKIDataType;
import org.etsi.uri.x01903.v13.GenericTimeStampType;
import org.etsi.uri.x01903.v13.IncludeType;
import org.etsi.uri.x01903.v13.ReferenceInfoType;
import org.w3.x2000.x09.xmldsig.CanonicalizationMethodType;

/* loaded from: classes11.dex */
public class GenericTimeStampTypeImpl extends XmlComplexContentImpl implements GenericTimeStampType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "Include"), new QName(SignatureFacet.XADES_132_NS, "ReferenceInfo"), new QName(SignatureFacet.XML_DIGSIG_NS, "CanonicalizationMethod"), new QName(SignatureFacet.XADES_132_NS, "EncapsulatedTimeStamp"), new QName(SignatureFacet.XADES_132_NS, "XMLTimeStamp"), new QName("", PackageRelationship.ID_ATTRIBUTE_NAME)};
    private static final long serialVersionUID = 1;

    public GenericTimeStampTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public List<IncludeType> getIncludeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GenericTimeStampTypeImpl.this.getIncludeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    GenericTimeStampTypeImpl.this.setIncludeArray(((Integer) obj).intValue(), (IncludeType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GenericTimeStampTypeImpl.this.insertNewInclude(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GenericTimeStampTypeImpl.this.removeInclude(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(GenericTimeStampTypeImpl.this.sizeOfIncludeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public IncludeType[] getIncludeArray() {
        return getXmlObjectArray(PROPERTY_QNAME[0], (XmlObject[]) new IncludeType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public IncludeType getIncludeArray(int i) {
        IncludeType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public int sizeOfIncludeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public void setIncludeArray(IncludeType[] includeArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) includeArray, PROPERTY_QNAME[0]);
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public void setIncludeArray(int i, IncludeType include) {
        generatedSetterHelperImpl(include, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public IncludeType insertNewInclude(int i) {
        IncludeType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public IncludeType addNewInclude() {
        IncludeType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public void removeInclude(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public List<ReferenceInfoType> getReferenceInfoList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GenericTimeStampTypeImpl.this.getReferenceInfoArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    GenericTimeStampTypeImpl.this.setReferenceInfoArray(((Integer) obj).intValue(), (ReferenceInfoType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GenericTimeStampTypeImpl.this.insertNewReferenceInfo(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GenericTimeStampTypeImpl.this.removeReferenceInfo(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(GenericTimeStampTypeImpl.this.sizeOfReferenceInfoArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public ReferenceInfoType[] getReferenceInfoArray() {
        return getXmlObjectArray(PROPERTY_QNAME[1], (XmlObject[]) new ReferenceInfoType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public ReferenceInfoType getReferenceInfoArray(int i) {
        ReferenceInfoType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public int sizeOfReferenceInfoArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public void setReferenceInfoArray(ReferenceInfoType[] referenceInfoArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) referenceInfoArray, PROPERTY_QNAME[1]);
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public void setReferenceInfoArray(int i, ReferenceInfoType referenceInfo) {
        generatedSetterHelperImpl(referenceInfo, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public ReferenceInfoType insertNewReferenceInfo(int i) {
        ReferenceInfoType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public ReferenceInfoType addNewReferenceInfo() {
        ReferenceInfoType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public void removeReferenceInfo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public CanonicalizationMethodType getCanonicalizationMethod() {
        CanonicalizationMethodType canonicalizationMethodType;
        synchronized (monitor()) {
            check_orphaned();
            CanonicalizationMethodType target = (CanonicalizationMethodType) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            canonicalizationMethodType = target == null ? null : target;
        }
        return canonicalizationMethodType;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public boolean isSetCanonicalizationMethod() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public void setCanonicalizationMethod(CanonicalizationMethodType canonicalizationMethod) {
        generatedSetterHelperImpl(canonicalizationMethod, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public CanonicalizationMethodType addNewCanonicalizationMethod() {
        CanonicalizationMethodType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CanonicalizationMethodType) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public void unsetCanonicalizationMethod() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public List<EncapsulatedPKIDataType> getEncapsulatedTimeStampList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GenericTimeStampTypeImpl.this.getEncapsulatedTimeStampArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    GenericTimeStampTypeImpl.this.setEncapsulatedTimeStampArray(((Integer) obj).intValue(), (EncapsulatedPKIDataType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GenericTimeStampTypeImpl.this.insertNewEncapsulatedTimeStamp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GenericTimeStampTypeImpl.this.removeEncapsulatedTimeStamp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(GenericTimeStampTypeImpl.this.sizeOfEncapsulatedTimeStampArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public EncapsulatedPKIDataType[] getEncapsulatedTimeStampArray() {
        return (EncapsulatedPKIDataType[]) getXmlObjectArray(PROPERTY_QNAME[3], new EncapsulatedPKIDataType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public EncapsulatedPKIDataType getEncapsulatedTimeStampArray(int i) {
        EncapsulatedPKIDataType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (EncapsulatedPKIDataType) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public int sizeOfEncapsulatedTimeStampArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public void setEncapsulatedTimeStampArray(EncapsulatedPKIDataType[] encapsulatedTimeStampArray) {
        check_orphaned();
        arraySetterHelper(encapsulatedTimeStampArray, PROPERTY_QNAME[3]);
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public void setEncapsulatedTimeStampArray(int i, EncapsulatedPKIDataType encapsulatedTimeStamp) {
        generatedSetterHelperImpl(encapsulatedTimeStamp, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public EncapsulatedPKIDataType insertNewEncapsulatedTimeStamp(int i) {
        EncapsulatedPKIDataType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (EncapsulatedPKIDataType) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public EncapsulatedPKIDataType addNewEncapsulatedTimeStamp() {
        EncapsulatedPKIDataType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (EncapsulatedPKIDataType) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public void removeEncapsulatedTimeStamp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public List<AnyType> getXMLTimeStampList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GenericTimeStampTypeImpl.this.getXMLTimeStampArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    GenericTimeStampTypeImpl.this.setXMLTimeStampArray(((Integer) obj).intValue(), (AnyType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return GenericTimeStampTypeImpl.this.insertNewXMLTimeStamp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GenericTimeStampTypeImpl.this.removeXMLTimeStamp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(GenericTimeStampTypeImpl.this.sizeOfXMLTimeStampArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public AnyType[] getXMLTimeStampArray() {
        return (AnyType[]) getXmlObjectArray(PROPERTY_QNAME[4], new AnyType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public AnyType getXMLTimeStampArray(int i) {
        AnyType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AnyType) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public int sizeOfXMLTimeStampArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public void setXMLTimeStampArray(AnyType[] xmlTimeStampArray) {
        check_orphaned();
        arraySetterHelper(xmlTimeStampArray, PROPERTY_QNAME[4]);
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public void setXMLTimeStampArray(int i, AnyType xmlTimeStamp) {
        generatedSetterHelperImpl(xmlTimeStamp, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public AnyType insertNewXMLTimeStamp(int i) {
        AnyType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AnyType) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public AnyType addNewXMLTimeStamp() {
        AnyType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AnyType) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public void removeXMLTimeStamp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public XmlID xgetId() {
        XmlID target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setStringValue(id);
        }
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public void xsetId(XmlID id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlID target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlID) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(id);
        }
    }

    @Override // org.etsi.uri.x01903.v13.GenericTimeStampType
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }
}
