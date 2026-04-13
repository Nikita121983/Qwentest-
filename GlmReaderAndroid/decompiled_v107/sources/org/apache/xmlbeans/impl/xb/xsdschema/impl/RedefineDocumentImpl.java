package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedAttributeGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl;

/* loaded from: classes11.dex */
public class RedefineDocumentImpl extends XmlComplexContentImpl implements RedefineDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "redefine")};
    private static final long serialVersionUID = 1;

    public RedefineDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument
    public RedefineDocument.Redefine getRedefine() {
        RedefineDocument.Redefine redefine;
        synchronized (monitor()) {
            check_orphaned();
            RedefineDocument.Redefine target = (RedefineDocument.Redefine) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            redefine = target == null ? null : target;
        }
        return redefine;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument
    public void setRedefine(RedefineDocument.Redefine redefine) {
        generatedSetterHelperImpl(redefine, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument
    public RedefineDocument.Redefine addNewRedefine() {
        RedefineDocument.Redefine target;
        synchronized (monitor()) {
            check_orphaned();
            target = (RedefineDocument.Redefine) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class RedefineImpl extends OpenAttrsImpl implements RedefineDocument.Redefine {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "annotation"), new QName("http://www.w3.org/2001/XMLSchema", "simpleType"), new QName("http://www.w3.org/2001/XMLSchema", "complexType"), new QName("http://www.w3.org/2001/XMLSchema", "group"), new QName("http://www.w3.org/2001/XMLSchema", "attributeGroup"), new QName("", "schemaLocation"), new QName("", "id")};
        private static final long serialVersionUID = 1;

        public RedefineImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public List<AnnotationDocument.Annotation> getAnnotationList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RedefineDocumentImpl.RedefineImpl.this.getAnnotationArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda11
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        RedefineDocumentImpl.RedefineImpl.this.setAnnotationArray(((Integer) obj).intValue(), (AnnotationDocument.Annotation) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda17
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RedefineDocumentImpl.RedefineImpl.this.insertNewAnnotation(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda18
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RedefineDocumentImpl.RedefineImpl.this.removeAnnotation(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda19
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(RedefineDocumentImpl.RedefineImpl.this.sizeOfAnnotationArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public AnnotationDocument.Annotation[] getAnnotationArray() {
            return (AnnotationDocument.Annotation[]) getXmlObjectArray(PROPERTY_QNAME[0], new AnnotationDocument.Annotation[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public AnnotationDocument.Annotation getAnnotationArray(int i) {
            AnnotationDocument.Annotation target;
            synchronized (monitor()) {
                check_orphaned();
                target = (AnnotationDocument.Annotation) get_store().find_element_user(PROPERTY_QNAME[0], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public int sizeOfAnnotationArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public void setAnnotationArray(AnnotationDocument.Annotation[] annotationArray) {
            check_orphaned();
            arraySetterHelper(annotationArray, PROPERTY_QNAME[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public void setAnnotationArray(int i, AnnotationDocument.Annotation annotation) {
            generatedSetterHelperImpl(annotation, PROPERTY_QNAME[0], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public AnnotationDocument.Annotation insertNewAnnotation(int i) {
            AnnotationDocument.Annotation target;
            synchronized (monitor()) {
                check_orphaned();
                target = (AnnotationDocument.Annotation) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public AnnotationDocument.Annotation addNewAnnotation() {
            AnnotationDocument.Annotation target;
            synchronized (monitor()) {
                check_orphaned();
                target = (AnnotationDocument.Annotation) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public void removeAnnotation(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public List<TopLevelSimpleType> getSimpleTypeList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda20
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RedefineDocumentImpl.RedefineImpl.this.getSimpleTypeArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda21
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        RedefineDocumentImpl.RedefineImpl.this.setSimpleTypeArray(((Integer) obj).intValue(), (TopLevelSimpleType) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda22
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RedefineDocumentImpl.RedefineImpl.this.insertNewSimpleType(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda23
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RedefineDocumentImpl.RedefineImpl.this.removeSimpleType(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda24
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(RedefineDocumentImpl.RedefineImpl.this.sizeOfSimpleTypeArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public TopLevelSimpleType[] getSimpleTypeArray() {
            return (TopLevelSimpleType[]) getXmlObjectArray(PROPERTY_QNAME[1], new TopLevelSimpleType[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public TopLevelSimpleType getSimpleTypeArray(int i) {
            TopLevelSimpleType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TopLevelSimpleType) get_store().find_element_user(PROPERTY_QNAME[1], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public int sizeOfSimpleTypeArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public void setSimpleTypeArray(TopLevelSimpleType[] simpleTypeArray) {
            check_orphaned();
            arraySetterHelper(simpleTypeArray, PROPERTY_QNAME[1]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public void setSimpleTypeArray(int i, TopLevelSimpleType simpleType) {
            generatedSetterHelperImpl(simpleType, PROPERTY_QNAME[1], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public TopLevelSimpleType insertNewSimpleType(int i) {
            TopLevelSimpleType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TopLevelSimpleType) get_store().insert_element_user(PROPERTY_QNAME[1], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public TopLevelSimpleType addNewSimpleType() {
            TopLevelSimpleType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TopLevelSimpleType) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public void removeSimpleType(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[1], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public List<TopLevelComplexType> getComplexTypeList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda6
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RedefineDocumentImpl.RedefineImpl.this.getComplexTypeArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda7
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        RedefineDocumentImpl.RedefineImpl.this.setComplexTypeArray(((Integer) obj).intValue(), (TopLevelComplexType) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda8
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RedefineDocumentImpl.RedefineImpl.this.insertNewComplexType(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda9
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RedefineDocumentImpl.RedefineImpl.this.removeComplexType(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda10
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(RedefineDocumentImpl.RedefineImpl.this.sizeOfComplexTypeArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public TopLevelComplexType[] getComplexTypeArray() {
            return (TopLevelComplexType[]) getXmlObjectArray(PROPERTY_QNAME[2], new TopLevelComplexType[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public TopLevelComplexType getComplexTypeArray(int i) {
            TopLevelComplexType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TopLevelComplexType) get_store().find_element_user(PROPERTY_QNAME[2], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public int sizeOfComplexTypeArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public void setComplexTypeArray(TopLevelComplexType[] complexTypeArray) {
            check_orphaned();
            arraySetterHelper(complexTypeArray, PROPERTY_QNAME[2]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public void setComplexTypeArray(int i, TopLevelComplexType complexType) {
            generatedSetterHelperImpl(complexType, PROPERTY_QNAME[2], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public TopLevelComplexType insertNewComplexType(int i) {
            TopLevelComplexType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TopLevelComplexType) get_store().insert_element_user(PROPERTY_QNAME[2], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public TopLevelComplexType addNewComplexType() {
            TopLevelComplexType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TopLevelComplexType) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public void removeComplexType(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[2], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public List<NamedGroup> getGroupList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda12
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RedefineDocumentImpl.RedefineImpl.this.getGroupArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda13
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        RedefineDocumentImpl.RedefineImpl.this.setGroupArray(((Integer) obj).intValue(), (NamedGroup) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda14
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RedefineDocumentImpl.RedefineImpl.this.insertNewGroup(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda15
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RedefineDocumentImpl.RedefineImpl.this.removeGroup(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda16
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(RedefineDocumentImpl.RedefineImpl.this.sizeOfGroupArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public NamedGroup[] getGroupArray() {
            return (NamedGroup[]) getXmlObjectArray(PROPERTY_QNAME[3], new NamedGroup[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public NamedGroup getGroupArray(int i) {
            NamedGroup target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NamedGroup) get_store().find_element_user(PROPERTY_QNAME[3], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public int sizeOfGroupArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public void setGroupArray(NamedGroup[] groupArray) {
            check_orphaned();
            arraySetterHelper(groupArray, PROPERTY_QNAME[3]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public void setGroupArray(int i, NamedGroup group) {
            generatedSetterHelperImpl(group, PROPERTY_QNAME[3], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public NamedGroup insertNewGroup(int i) {
            NamedGroup target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NamedGroup) get_store().insert_element_user(PROPERTY_QNAME[3], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public NamedGroup addNewGroup() {
            NamedGroup target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NamedGroup) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public void removeGroup(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[3], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public List<NamedAttributeGroup> getAttributeGroupList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda1
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RedefineDocumentImpl.RedefineImpl.this.getAttributeGroupArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda2
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        RedefineDocumentImpl.RedefineImpl.this.setAttributeGroupArray(((Integer) obj).intValue(), (NamedAttributeGroup) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda3
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return RedefineDocumentImpl.RedefineImpl.this.insertNewAttributeGroup(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda4
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RedefineDocumentImpl.RedefineImpl.this.removeAttributeGroup(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda5
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(RedefineDocumentImpl.RedefineImpl.this.sizeOfAttributeGroupArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public NamedAttributeGroup[] getAttributeGroupArray() {
            return (NamedAttributeGroup[]) getXmlObjectArray(PROPERTY_QNAME[4], new NamedAttributeGroup[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public NamedAttributeGroup getAttributeGroupArray(int i) {
            NamedAttributeGroup target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NamedAttributeGroup) get_store().find_element_user(PROPERTY_QNAME[4], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public int sizeOfAttributeGroupArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public void setAttributeGroupArray(NamedAttributeGroup[] attributeGroupArray) {
            check_orphaned();
            arraySetterHelper(attributeGroupArray, PROPERTY_QNAME[4]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public void setAttributeGroupArray(int i, NamedAttributeGroup attributeGroup) {
            generatedSetterHelperImpl(attributeGroup, PROPERTY_QNAME[4], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public NamedAttributeGroup insertNewAttributeGroup(int i) {
            NamedAttributeGroup target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NamedAttributeGroup) get_store().insert_element_user(PROPERTY_QNAME[4], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public NamedAttributeGroup addNewAttributeGroup() {
            NamedAttributeGroup target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NamedAttributeGroup) get_store().add_element_user(PROPERTY_QNAME[4]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public void removeAttributeGroup(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[4], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public String getSchemaLocation() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public XmlAnyURI xgetSchemaLocation() {
            XmlAnyURI target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public void setSchemaLocation(String schemaLocation) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
                }
                target.setStringValue(schemaLocation);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public void xsetSchemaLocation(XmlAnyURI schemaLocation) {
            synchronized (monitor()) {
                check_orphaned();
                XmlAnyURI target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[5]);
                if (target == null) {
                    target = (XmlAnyURI) get_store().add_attribute_user(PROPERTY_QNAME[5]);
                }
                target.set(schemaLocation);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public String getId() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public XmlID xgetId() {
            XmlID target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public boolean isSetId() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public void setId(String id) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
                }
                target.setStringValue(id);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public void xsetId(XmlID id) {
            synchronized (monitor()) {
                check_orphaned();
                XmlID target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[6]);
                if (target == null) {
                    target = (XmlID) get_store().add_attribute_user(PROPERTY_QNAME[6]);
                }
                target.set(id);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument.Redefine
        public void unsetId() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[6]);
            }
        }
    }
}
