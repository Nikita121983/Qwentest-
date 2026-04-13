package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotationDocumentImpl;

/* loaded from: classes11.dex */
public class AnnotationDocumentImpl extends XmlComplexContentImpl implements AnnotationDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "annotation")};
    private static final long serialVersionUID = 1;

    public AnnotationDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument
    public AnnotationDocument.Annotation getAnnotation() {
        AnnotationDocument.Annotation annotation;
        synchronized (monitor()) {
            check_orphaned();
            AnnotationDocument.Annotation target = (AnnotationDocument.Annotation) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            annotation = target == null ? null : target;
        }
        return annotation;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument
    public void setAnnotation(AnnotationDocument.Annotation annotation) {
        generatedSetterHelperImpl(annotation, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument
    public AnnotationDocument.Annotation addNewAnnotation() {
        AnnotationDocument.Annotation target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AnnotationDocument.Annotation) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class AnnotationImpl extends OpenAttrsImpl implements AnnotationDocument.Annotation {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "appinfo"), new QName("http://www.w3.org/2001/XMLSchema", "documentation"), new QName("", "id")};
        private static final long serialVersionUID = 1;

        public AnnotationImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public List<AppinfoDocument.Appinfo> getAppinfoList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda5
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return AnnotationDocumentImpl.AnnotationImpl.this.getAppinfoArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda6
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        AnnotationDocumentImpl.AnnotationImpl.this.setAppinfoArray(((Integer) obj).intValue(), (AppinfoDocument.Appinfo) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda7
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return AnnotationDocumentImpl.AnnotationImpl.this.insertNewAppinfo(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda8
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        AnnotationDocumentImpl.AnnotationImpl.this.removeAppinfo(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda9
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(AnnotationDocumentImpl.AnnotationImpl.this.sizeOfAppinfoArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public AppinfoDocument.Appinfo[] getAppinfoArray() {
            return (AppinfoDocument.Appinfo[]) getXmlObjectArray(PROPERTY_QNAME[0], new AppinfoDocument.Appinfo[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public AppinfoDocument.Appinfo getAppinfoArray(int i) {
            AppinfoDocument.Appinfo target;
            synchronized (monitor()) {
                check_orphaned();
                target = (AppinfoDocument.Appinfo) get_store().find_element_user(PROPERTY_QNAME[0], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public int sizeOfAppinfoArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public void setAppinfoArray(AppinfoDocument.Appinfo[] appinfoArray) {
            check_orphaned();
            arraySetterHelper(appinfoArray, PROPERTY_QNAME[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public void setAppinfoArray(int i, AppinfoDocument.Appinfo appinfo) {
            generatedSetterHelperImpl(appinfo, PROPERTY_QNAME[0], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public AppinfoDocument.Appinfo insertNewAppinfo(int i) {
            AppinfoDocument.Appinfo target;
            synchronized (monitor()) {
                check_orphaned();
                target = (AppinfoDocument.Appinfo) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public AppinfoDocument.Appinfo addNewAppinfo() {
            AppinfoDocument.Appinfo target;
            synchronized (monitor()) {
                check_orphaned();
                target = (AppinfoDocument.Appinfo) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public void removeAppinfo(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public List<DocumentationDocument.Documentation> getDocumentationList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return AnnotationDocumentImpl.AnnotationImpl.this.getDocumentationArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda1
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        AnnotationDocumentImpl.AnnotationImpl.this.setDocumentationArray(((Integer) obj).intValue(), (DocumentationDocument.Documentation) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda2
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return AnnotationDocumentImpl.AnnotationImpl.this.insertNewDocumentation(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda3
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        AnnotationDocumentImpl.AnnotationImpl.this.removeDocumentation(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda4
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(AnnotationDocumentImpl.AnnotationImpl.this.sizeOfDocumentationArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public DocumentationDocument.Documentation[] getDocumentationArray() {
            return (DocumentationDocument.Documentation[]) getXmlObjectArray(PROPERTY_QNAME[1], new DocumentationDocument.Documentation[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public DocumentationDocument.Documentation getDocumentationArray(int i) {
            DocumentationDocument.Documentation target;
            synchronized (monitor()) {
                check_orphaned();
                target = (DocumentationDocument.Documentation) get_store().find_element_user(PROPERTY_QNAME[1], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public int sizeOfDocumentationArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public void setDocumentationArray(DocumentationDocument.Documentation[] documentationArray) {
            check_orphaned();
            arraySetterHelper(documentationArray, PROPERTY_QNAME[1]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public void setDocumentationArray(int i, DocumentationDocument.Documentation documentation) {
            generatedSetterHelperImpl(documentation, PROPERTY_QNAME[1], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public DocumentationDocument.Documentation insertNewDocumentation(int i) {
            DocumentationDocument.Documentation target;
            synchronized (monitor()) {
                check_orphaned();
                target = (DocumentationDocument.Documentation) get_store().insert_element_user(PROPERTY_QNAME[1], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public DocumentationDocument.Documentation addNewDocumentation() {
            DocumentationDocument.Documentation target;
            synchronized (monitor()) {
                check_orphaned();
                target = (DocumentationDocument.Documentation) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public void removeDocumentation(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[1], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public String getId() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public XmlID xgetId() {
            XmlID target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public boolean isSetId() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
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

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
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

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument.Annotation
        public void unsetId() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[2]);
            }
        }
    }
}
