package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;

/* loaded from: classes11.dex */
public class AnnotatedImpl extends OpenAttrsImpl implements Annotated {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "annotation"), new QName("", "id")};
    private static final long serialVersionUID = 1;

    public AnnotatedImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Annotated
    public AnnotationDocument.Annotation getAnnotation() {
        AnnotationDocument.Annotation annotation;
        synchronized (monitor()) {
            check_orphaned();
            AnnotationDocument.Annotation target = (AnnotationDocument.Annotation) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            annotation = target == null ? null : target;
        }
        return annotation;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Annotated
    public boolean isSetAnnotation() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Annotated
    public void setAnnotation(AnnotationDocument.Annotation annotation) {
        generatedSetterHelperImpl(annotation, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Annotated
    public AnnotationDocument.Annotation addNewAnnotation() {
        AnnotationDocument.Annotation target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AnnotationDocument.Annotation) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Annotated
    public void unsetAnnotation() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Annotated
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Annotated
    public XmlID xgetId() {
        XmlID target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Annotated
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().find_attribute_user(PROPERTY_QNAME[1]) == null) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Annotated
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(id);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Annotated
    public void xsetId(XmlID id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlID target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlID) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(id);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Annotated
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }
}
