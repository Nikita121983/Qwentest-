package org.etsi.uri.x01903.v13.impl;

import com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda66;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CommitmentTypeIndicationType;
import org.etsi.uri.x01903.v13.CommitmentTypeQualifiersListType;
import org.etsi.uri.x01903.v13.ObjectIdentifierType;

/* loaded from: classes11.dex */
public class CommitmentTypeIndicationTypeImpl extends XmlComplexContentImpl implements CommitmentTypeIndicationType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "CommitmentTypeId"), new QName(SignatureFacet.XADES_132_NS, "ObjectReference"), new QName(SignatureFacet.XADES_132_NS, "AllSignedDataObjects"), new QName(SignatureFacet.XADES_132_NS, "CommitmentTypeQualifiers")};
    private static final long serialVersionUID = 1;

    public CommitmentTypeIndicationTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public ObjectIdentifierType getCommitmentTypeId() {
        ObjectIdentifierType objectIdentifierType;
        synchronized (monitor()) {
            check_orphaned();
            ObjectIdentifierType target = (ObjectIdentifierType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            objectIdentifierType = target == null ? null : target;
        }
        return objectIdentifierType;
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public void setCommitmentTypeId(ObjectIdentifierType commitmentTypeId) {
        generatedSetterHelperImpl(commitmentTypeId, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public ObjectIdentifierType addNewCommitmentTypeId() {
        ObjectIdentifierType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ObjectIdentifierType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public List<String> getObjectReferenceList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CommitmentTypeIndicationTypeImpl.this.getObjectReferenceArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CommitmentTypeIndicationTypeImpl.this.setObjectReferenceArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CommitmentTypeIndicationTypeImpl.this.insertObjectReference(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda4(this), new CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getObjectReferenceArray$0(int x$0) {
        return new String[x$0];
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public String[] getObjectReferenceArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[1], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: org.etsi.uri.x01903.v13.impl.CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda1
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CommitmentTypeIndicationTypeImpl.lambda$getObjectReferenceArray$0(i);
            }
        });
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public String getObjectReferenceArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public List<XmlAnyURI> xgetObjectReferenceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.etsi.uri.x01903.v13.impl.CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CommitmentTypeIndicationTypeImpl.this.xgetObjectReferenceArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.etsi.uri.x01903.v13.impl.CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CommitmentTypeIndicationTypeImpl.this.xsetObjectReferenceArray(((Integer) obj).intValue(), (XmlAnyURI) obj2);
                }
            }, new Function() { // from class: org.etsi.uri.x01903.v13.impl.CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CommitmentTypeIndicationTypeImpl.this.insertNewObjectReference(((Integer) obj).intValue());
                }
            }, new CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda4(this), new CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlAnyURI[] lambda$xgetObjectReferenceArray$1(int x$0) {
        return new XmlAnyURI[x$0];
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public XmlAnyURI[] xgetObjectReferenceArray() {
        return (XmlAnyURI[]) xgetArray(PROPERTY_QNAME[1], new IntFunction() { // from class: org.etsi.uri.x01903.v13.impl.CommitmentTypeIndicationTypeImpl$$ExternalSyntheticLambda6
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CommitmentTypeIndicationTypeImpl.lambda$xgetObjectReferenceArray$1(i);
            }
        });
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public XmlAnyURI xgetObjectReferenceArray(int i) {
        XmlAnyURI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlAnyURI) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public int sizeOfObjectReferenceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public void setObjectReferenceArray(String[] objectReferenceArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(objectReferenceArray, PROPERTY_QNAME[1]);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public void setObjectReferenceArray(int i, String objectReference) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(objectReference);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public void xsetObjectReferenceArray(XmlAnyURI[] objectReferenceArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(objectReferenceArray, PROPERTY_QNAME[1]);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public void xsetObjectReferenceArray(int i, XmlAnyURI objectReference) {
        synchronized (monitor()) {
            check_orphaned();
            XmlAnyURI target = (XmlAnyURI) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(objectReference);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public void insertObjectReference(int i, String objectReference) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[1], i);
            target.setStringValue(objectReference);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public void addObjectReference(String objectReference) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            target.setStringValue(objectReference);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public XmlAnyURI insertNewObjectReference(int i) {
        XmlAnyURI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlAnyURI) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public XmlAnyURI addNewObjectReference() {
        XmlAnyURI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlAnyURI) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public void removeObjectReference(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public XmlObject getAllSignedDataObjects() {
        XmlObject xmlObject;
        synchronized (monitor()) {
            check_orphaned();
            XmlObject target = (XmlObject) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            xmlObject = target == null ? null : target;
        }
        return xmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public boolean isSetAllSignedDataObjects() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public void setAllSignedDataObjects(XmlObject allSignedDataObjects) {
        generatedSetterHelperImpl(allSignedDataObjects, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public XmlObject addNewAllSignedDataObjects() {
        XmlObject target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlObject) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public void unsetAllSignedDataObjects() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public CommitmentTypeQualifiersListType getCommitmentTypeQualifiers() {
        CommitmentTypeQualifiersListType commitmentTypeQualifiersListType;
        synchronized (monitor()) {
            check_orphaned();
            CommitmentTypeQualifiersListType target = (CommitmentTypeQualifiersListType) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            commitmentTypeQualifiersListType = target == null ? null : target;
        }
        return commitmentTypeQualifiersListType;
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public boolean isSetCommitmentTypeQualifiers() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public void setCommitmentTypeQualifiers(CommitmentTypeQualifiersListType commitmentTypeQualifiers) {
        generatedSetterHelperImpl(commitmentTypeQualifiers, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public CommitmentTypeQualifiersListType addNewCommitmentTypeQualifiers() {
        CommitmentTypeQualifiersListType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CommitmentTypeQualifiersListType) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
    public void unsetCommitmentTypeQualifiers() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
