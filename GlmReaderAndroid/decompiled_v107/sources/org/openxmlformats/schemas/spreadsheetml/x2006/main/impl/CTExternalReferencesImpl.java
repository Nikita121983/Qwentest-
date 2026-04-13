package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReference;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReferences;

/* loaded from: classes12.dex */
public class CTExternalReferencesImpl extends XmlComplexContentImpl implements CTExternalReferences {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "externalReference")};
    private static final long serialVersionUID = 1;

    public CTExternalReferencesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReferences
    public List<CTExternalReference> getExternalReferenceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTExternalReferencesImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTExternalReferencesImpl.this.getExternalReferenceArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTExternalReferencesImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTExternalReferencesImpl.this.setExternalReferenceArray(((Integer) obj).intValue(), (CTExternalReference) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTExternalReferencesImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTExternalReferencesImpl.this.insertNewExternalReference(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTExternalReferencesImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTExternalReferencesImpl.this.removeExternalReference(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTExternalReferencesImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTExternalReferencesImpl.this.sizeOfExternalReferenceArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReferences
    public CTExternalReference[] getExternalReferenceArray() {
        return (CTExternalReference[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTExternalReference[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReferences
    public CTExternalReference getExternalReferenceArray(int i) {
        CTExternalReference target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExternalReference) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReferences
    public int sizeOfExternalReferenceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReferences
    public void setExternalReferenceArray(CTExternalReference[] externalReferenceArray) {
        check_orphaned();
        arraySetterHelper(externalReferenceArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReferences
    public void setExternalReferenceArray(int i, CTExternalReference externalReference) {
        generatedSetterHelperImpl(externalReference, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReferences
    public CTExternalReference insertNewExternalReference(int i) {
        CTExternalReference target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExternalReference) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReferences
    public CTExternalReference addNewExternalReference() {
        CTExternalReference target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExternalReference) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReferences
    public void removeExternalReference(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
