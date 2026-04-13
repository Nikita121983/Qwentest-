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
import org.etsi.uri.x01903.v13.CommitmentTypeIndicationType;
import org.etsi.uri.x01903.v13.DataObjectFormatType;
import org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType;
import org.etsi.uri.x01903.v13.XAdESTimeStampType;

/* loaded from: classes11.dex */
public class SignedDataObjectPropertiesTypeImpl extends XmlComplexContentImpl implements SignedDataObjectPropertiesType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "DataObjectFormat"), new QName(SignatureFacet.XADES_132_NS, "CommitmentTypeIndication"), new QName(SignatureFacet.XADES_132_NS, "AllDataObjectsTimeStamp"), new QName(SignatureFacet.XADES_132_NS, "IndividualDataObjectsTimeStamp"), new QName("", PackageRelationship.ID_ATTRIBUTE_NAME)};
    private static final long serialVersionUID = 1;

    public SignedDataObjectPropertiesTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public List<DataObjectFormatType> getDataObjectFormatList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SignedDataObjectPropertiesTypeImpl.this.getDataObjectFormatArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    SignedDataObjectPropertiesTypeImpl.this.setDataObjectFormatArray(((Integer) obj).intValue(), (DataObjectFormatType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SignedDataObjectPropertiesTypeImpl.this.insertNewDataObjectFormat(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    SignedDataObjectPropertiesTypeImpl.this.removeDataObjectFormat(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(SignedDataObjectPropertiesTypeImpl.this.sizeOfDataObjectFormatArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public DataObjectFormatType[] getDataObjectFormatArray() {
        return (DataObjectFormatType[]) getXmlObjectArray(PROPERTY_QNAME[0], new DataObjectFormatType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public DataObjectFormatType getDataObjectFormatArray(int i) {
        DataObjectFormatType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (DataObjectFormatType) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public int sizeOfDataObjectFormatArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public void setDataObjectFormatArray(DataObjectFormatType[] dataObjectFormatArray) {
        check_orphaned();
        arraySetterHelper(dataObjectFormatArray, PROPERTY_QNAME[0]);
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public void setDataObjectFormatArray(int i, DataObjectFormatType dataObjectFormat) {
        generatedSetterHelperImpl(dataObjectFormat, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public DataObjectFormatType insertNewDataObjectFormat(int i) {
        DataObjectFormatType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (DataObjectFormatType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public DataObjectFormatType addNewDataObjectFormat() {
        DataObjectFormatType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (DataObjectFormatType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public void removeDataObjectFormat(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public List<CommitmentTypeIndicationType> getCommitmentTypeIndicationList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SignedDataObjectPropertiesTypeImpl.this.getCommitmentTypeIndicationArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    SignedDataObjectPropertiesTypeImpl.this.setCommitmentTypeIndicationArray(((Integer) obj).intValue(), (CommitmentTypeIndicationType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SignedDataObjectPropertiesTypeImpl.this.insertNewCommitmentTypeIndication(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    SignedDataObjectPropertiesTypeImpl.this.removeCommitmentTypeIndication(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(SignedDataObjectPropertiesTypeImpl.this.sizeOfCommitmentTypeIndicationArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public CommitmentTypeIndicationType[] getCommitmentTypeIndicationArray() {
        return (CommitmentTypeIndicationType[]) getXmlObjectArray(PROPERTY_QNAME[1], new CommitmentTypeIndicationType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public CommitmentTypeIndicationType getCommitmentTypeIndicationArray(int i) {
        CommitmentTypeIndicationType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CommitmentTypeIndicationType) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public int sizeOfCommitmentTypeIndicationArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public void setCommitmentTypeIndicationArray(CommitmentTypeIndicationType[] commitmentTypeIndicationArray) {
        check_orphaned();
        arraySetterHelper(commitmentTypeIndicationArray, PROPERTY_QNAME[1]);
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public void setCommitmentTypeIndicationArray(int i, CommitmentTypeIndicationType commitmentTypeIndication) {
        generatedSetterHelperImpl(commitmentTypeIndication, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public CommitmentTypeIndicationType insertNewCommitmentTypeIndication(int i) {
        CommitmentTypeIndicationType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CommitmentTypeIndicationType) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public CommitmentTypeIndicationType addNewCommitmentTypeIndication() {
        CommitmentTypeIndicationType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CommitmentTypeIndicationType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public void removeCommitmentTypeIndication(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public List<XAdESTimeStampType> getAllDataObjectsTimeStampList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SignedDataObjectPropertiesTypeImpl.this.getAllDataObjectsTimeStampArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    SignedDataObjectPropertiesTypeImpl.this.setAllDataObjectsTimeStampArray(((Integer) obj).intValue(), (XAdESTimeStampType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SignedDataObjectPropertiesTypeImpl.this.insertNewAllDataObjectsTimeStamp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    SignedDataObjectPropertiesTypeImpl.this.removeAllDataObjectsTimeStamp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(SignedDataObjectPropertiesTypeImpl.this.sizeOfAllDataObjectsTimeStampArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public XAdESTimeStampType[] getAllDataObjectsTimeStampArray() {
        return (XAdESTimeStampType[]) getXmlObjectArray(PROPERTY_QNAME[2], new XAdESTimeStampType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public XAdESTimeStampType getAllDataObjectsTimeStampArray(int i) {
        XAdESTimeStampType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XAdESTimeStampType) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public int sizeOfAllDataObjectsTimeStampArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public void setAllDataObjectsTimeStampArray(XAdESTimeStampType[] allDataObjectsTimeStampArray) {
        check_orphaned();
        arraySetterHelper(allDataObjectsTimeStampArray, PROPERTY_QNAME[2]);
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public void setAllDataObjectsTimeStampArray(int i, XAdESTimeStampType allDataObjectsTimeStamp) {
        generatedSetterHelperImpl(allDataObjectsTimeStamp, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public XAdESTimeStampType insertNewAllDataObjectsTimeStamp(int i) {
        XAdESTimeStampType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XAdESTimeStampType) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public XAdESTimeStampType addNewAllDataObjectsTimeStamp() {
        XAdESTimeStampType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XAdESTimeStampType) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public void removeAllDataObjectsTimeStamp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public List<XAdESTimeStampType> getIndividualDataObjectsTimeStampList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SignedDataObjectPropertiesTypeImpl.this.getIndividualDataObjectsTimeStampArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    SignedDataObjectPropertiesTypeImpl.this.setIndividualDataObjectsTimeStampArray(((Integer) obj).intValue(), (XAdESTimeStampType) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return SignedDataObjectPropertiesTypeImpl.this.insertNewIndividualDataObjectsTimeStamp(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    SignedDataObjectPropertiesTypeImpl.this.removeIndividualDataObjectsTimeStamp(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.etsi.uri.x01903.v13.impl.SignedDataObjectPropertiesTypeImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(SignedDataObjectPropertiesTypeImpl.this.sizeOfIndividualDataObjectsTimeStampArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public XAdESTimeStampType[] getIndividualDataObjectsTimeStampArray() {
        return (XAdESTimeStampType[]) getXmlObjectArray(PROPERTY_QNAME[3], new XAdESTimeStampType[0]);
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public XAdESTimeStampType getIndividualDataObjectsTimeStampArray(int i) {
        XAdESTimeStampType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XAdESTimeStampType) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public int sizeOfIndividualDataObjectsTimeStampArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public void setIndividualDataObjectsTimeStampArray(XAdESTimeStampType[] individualDataObjectsTimeStampArray) {
        check_orphaned();
        arraySetterHelper(individualDataObjectsTimeStampArray, PROPERTY_QNAME[3]);
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public void setIndividualDataObjectsTimeStampArray(int i, XAdESTimeStampType individualDataObjectsTimeStamp) {
        generatedSetterHelperImpl(individualDataObjectsTimeStamp, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public XAdESTimeStampType insertNewIndividualDataObjectsTimeStamp(int i) {
        XAdESTimeStampType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XAdESTimeStampType) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public XAdESTimeStampType addNewIndividualDataObjectsTimeStamp() {
        XAdESTimeStampType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XAdESTimeStampType) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public void removeIndividualDataObjectsTimeStamp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public XmlID xgetId() {
        XmlID target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
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

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
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

    @Override // org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }
}
