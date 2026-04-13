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
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.BlockSet;
import org.apache.xmlbeans.impl.xb.xsdschema.FormChoice;
import org.apache.xmlbeans.impl.xb.xsdschema.FullDerivationSet;
import org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedAttributeGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelAttribute;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl;

/* loaded from: classes11.dex */
public class SchemaDocumentImpl extends XmlComplexContentImpl implements SchemaDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "schema")};
    private static final long serialVersionUID = 1;

    public SchemaDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument
    public SchemaDocument.Schema getSchema() {
        SchemaDocument.Schema schema;
        synchronized (monitor()) {
            check_orphaned();
            SchemaDocument.Schema target = (SchemaDocument.Schema) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            schema = target == null ? null : target;
        }
        return schema;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument
    public void setSchema(SchemaDocument.Schema schema) {
        generatedSetterHelperImpl(schema, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument
    public SchemaDocument.Schema addNewSchema() {
        SchemaDocument.Schema target;
        synchronized (monitor()) {
            check_orphaned();
            target = (SchemaDocument.Schema) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class SchemaImpl extends OpenAttrsImpl implements SchemaDocument.Schema {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "include"), new QName("http://www.w3.org/2001/XMLSchema", "import"), new QName("http://www.w3.org/2001/XMLSchema", "redefine"), new QName("http://www.w3.org/2001/XMLSchema", "annotation"), new QName("http://www.w3.org/2001/XMLSchema", "simpleType"), new QName("http://www.w3.org/2001/XMLSchema", "complexType"), new QName("http://www.w3.org/2001/XMLSchema", "group"), new QName("http://www.w3.org/2001/XMLSchema", "attributeGroup"), new QName("http://www.w3.org/2001/XMLSchema", "element"), new QName("http://www.w3.org/2001/XMLSchema", "attribute"), new QName("http://www.w3.org/2001/XMLSchema", "notation"), new QName("", "targetNamespace"), new QName("", "version"), new QName("", "finalDefault"), new QName("", "blockDefault"), new QName("", "attributeFormDefault"), new QName("", "elementFormDefault"), new QName("", "id"), new QName("http://www.w3.org/XML/1998/namespace", "lang")};
        private static final long serialVersionUID = 1;

        public SchemaImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public List<IncludeDocument.Include> getIncludeList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda23
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.getIncludeArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda24
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        SchemaDocumentImpl.SchemaImpl.this.setIncludeArray(((Integer) obj).intValue(), (IncludeDocument.Include) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda25
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.insertNewInclude(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda26
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        SchemaDocumentImpl.SchemaImpl.this.removeInclude(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda27
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(SchemaDocumentImpl.SchemaImpl.this.sizeOfIncludeArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public IncludeDocument.Include[] getIncludeArray() {
            return (IncludeDocument.Include[]) getXmlObjectArray(PROPERTY_QNAME[0], new IncludeDocument.Include[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public IncludeDocument.Include getIncludeArray(int i) {
            IncludeDocument.Include target;
            synchronized (monitor()) {
                check_orphaned();
                target = (IncludeDocument.Include) get_store().find_element_user(PROPERTY_QNAME[0], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public int sizeOfIncludeArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setIncludeArray(IncludeDocument.Include[] includeArray) {
            check_orphaned();
            arraySetterHelper(includeArray, PROPERTY_QNAME[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setIncludeArray(int i, IncludeDocument.Include include) {
            generatedSetterHelperImpl(include, PROPERTY_QNAME[0], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public IncludeDocument.Include insertNewInclude(int i) {
            IncludeDocument.Include target;
            synchronized (monitor()) {
                check_orphaned();
                target = (IncludeDocument.Include) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public IncludeDocument.Include addNewInclude() {
            IncludeDocument.Include target;
            synchronized (monitor()) {
                check_orphaned();
                target = (IncludeDocument.Include) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void removeInclude(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public List<ImportDocument.Import> getImportList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda34
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.getImportArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda35
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        SchemaDocumentImpl.SchemaImpl.this.setImportArray(((Integer) obj).intValue(), (ImportDocument.Import) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda36
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.insertNewImport(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda37
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        SchemaDocumentImpl.SchemaImpl.this.removeImport(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda38
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(SchemaDocumentImpl.SchemaImpl.this.sizeOfImportArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public ImportDocument.Import[] getImportArray() {
            return (ImportDocument.Import[]) getXmlObjectArray(PROPERTY_QNAME[1], new ImportDocument.Import[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public ImportDocument.Import getImportArray(int i) {
            ImportDocument.Import target;
            synchronized (monitor()) {
                check_orphaned();
                target = (ImportDocument.Import) get_store().find_element_user(PROPERTY_QNAME[1], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public int sizeOfImportArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setImportArray(ImportDocument.Import[] ximportArray) {
            check_orphaned();
            arraySetterHelper(ximportArray, PROPERTY_QNAME[1]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setImportArray(int i, ImportDocument.Import ximport) {
            generatedSetterHelperImpl(ximport, PROPERTY_QNAME[1], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public ImportDocument.Import insertNewImport(int i) {
            ImportDocument.Import target;
            synchronized (monitor()) {
                check_orphaned();
                target = (ImportDocument.Import) get_store().insert_element_user(PROPERTY_QNAME[1], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public ImportDocument.Import addNewImport() {
            ImportDocument.Import target;
            synchronized (monitor()) {
                check_orphaned();
                target = (ImportDocument.Import) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void removeImport(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[1], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public List<RedefineDocument.Redefine> getRedefineList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda39
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.getRedefineArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda40
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        SchemaDocumentImpl.SchemaImpl.this.setRedefineArray(((Integer) obj).intValue(), (RedefineDocument.Redefine) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda41
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.insertNewRedefine(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda42
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        SchemaDocumentImpl.SchemaImpl.this.removeRedefine(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda43
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(SchemaDocumentImpl.SchemaImpl.this.sizeOfRedefineArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public RedefineDocument.Redefine[] getRedefineArray() {
            return (RedefineDocument.Redefine[]) getXmlObjectArray(PROPERTY_QNAME[2], new RedefineDocument.Redefine[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public RedefineDocument.Redefine getRedefineArray(int i) {
            RedefineDocument.Redefine target;
            synchronized (monitor()) {
                check_orphaned();
                target = (RedefineDocument.Redefine) get_store().find_element_user(PROPERTY_QNAME[2], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public int sizeOfRedefineArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setRedefineArray(RedefineDocument.Redefine[] redefineArray) {
            check_orphaned();
            arraySetterHelper(redefineArray, PROPERTY_QNAME[2]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setRedefineArray(int i, RedefineDocument.Redefine redefine) {
            generatedSetterHelperImpl(redefine, PROPERTY_QNAME[2], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public RedefineDocument.Redefine insertNewRedefine(int i) {
            RedefineDocument.Redefine target;
            synchronized (monitor()) {
                check_orphaned();
                target = (RedefineDocument.Redefine) get_store().insert_element_user(PROPERTY_QNAME[2], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public RedefineDocument.Redefine addNewRedefine() {
            RedefineDocument.Redefine target;
            synchronized (monitor()) {
                check_orphaned();
                target = (RedefineDocument.Redefine) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void removeRedefine(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[2], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public List<AnnotationDocument.Annotation> getAnnotationList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda6
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.getAnnotationArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda7
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        SchemaDocumentImpl.SchemaImpl.this.setAnnotationArray(((Integer) obj).intValue(), (AnnotationDocument.Annotation) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda8
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.insertNewAnnotation(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda9
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        SchemaDocumentImpl.SchemaImpl.this.removeAnnotation(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda10
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(SchemaDocumentImpl.SchemaImpl.this.sizeOfAnnotationArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public AnnotationDocument.Annotation[] getAnnotationArray() {
            return (AnnotationDocument.Annotation[]) getXmlObjectArray(PROPERTY_QNAME[3], new AnnotationDocument.Annotation[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public AnnotationDocument.Annotation getAnnotationArray(int i) {
            AnnotationDocument.Annotation target;
            synchronized (monitor()) {
                check_orphaned();
                target = (AnnotationDocument.Annotation) get_store().find_element_user(PROPERTY_QNAME[3], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public int sizeOfAnnotationArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setAnnotationArray(AnnotationDocument.Annotation[] annotationArray) {
            check_orphaned();
            arraySetterHelper(annotationArray, PROPERTY_QNAME[3]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setAnnotationArray(int i, AnnotationDocument.Annotation annotation) {
            generatedSetterHelperImpl(annotation, PROPERTY_QNAME[3], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public AnnotationDocument.Annotation insertNewAnnotation(int i) {
            AnnotationDocument.Annotation target;
            synchronized (monitor()) {
                check_orphaned();
                target = (AnnotationDocument.Annotation) get_store().insert_element_user(PROPERTY_QNAME[3], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public AnnotationDocument.Annotation addNewAnnotation() {
            AnnotationDocument.Annotation target;
            synchronized (monitor()) {
                check_orphaned();
                target = (AnnotationDocument.Annotation) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void removeAnnotation(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[3], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public List<TopLevelSimpleType> getSimpleTypeList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda12
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.getSimpleTypeArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda13
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        SchemaDocumentImpl.SchemaImpl.this.setSimpleTypeArray(((Integer) obj).intValue(), (TopLevelSimpleType) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda14
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.insertNewSimpleType(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda15
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        SchemaDocumentImpl.SchemaImpl.this.removeSimpleType(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda16
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(SchemaDocumentImpl.SchemaImpl.this.sizeOfSimpleTypeArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public TopLevelSimpleType[] getSimpleTypeArray() {
            return (TopLevelSimpleType[]) getXmlObjectArray(PROPERTY_QNAME[4], new TopLevelSimpleType[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public TopLevelSimpleType getSimpleTypeArray(int i) {
            TopLevelSimpleType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TopLevelSimpleType) get_store().find_element_user(PROPERTY_QNAME[4], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public int sizeOfSimpleTypeArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setSimpleTypeArray(TopLevelSimpleType[] simpleTypeArray) {
            check_orphaned();
            arraySetterHelper(simpleTypeArray, PROPERTY_QNAME[4]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setSimpleTypeArray(int i, TopLevelSimpleType simpleType) {
            generatedSetterHelperImpl(simpleType, PROPERTY_QNAME[4], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public TopLevelSimpleType insertNewSimpleType(int i) {
            TopLevelSimpleType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TopLevelSimpleType) get_store().insert_element_user(PROPERTY_QNAME[4], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public TopLevelSimpleType addNewSimpleType() {
            TopLevelSimpleType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TopLevelSimpleType) get_store().add_element_user(PROPERTY_QNAME[4]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void removeSimpleType(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[4], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public List<TopLevelComplexType> getComplexTypeList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.getComplexTypeArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda11
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        SchemaDocumentImpl.SchemaImpl.this.setComplexTypeArray(((Integer) obj).intValue(), (TopLevelComplexType) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda22
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.insertNewComplexType(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda33
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        SchemaDocumentImpl.SchemaImpl.this.removeComplexType(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda44
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(SchemaDocumentImpl.SchemaImpl.this.sizeOfComplexTypeArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public TopLevelComplexType[] getComplexTypeArray() {
            return (TopLevelComplexType[]) getXmlObjectArray(PROPERTY_QNAME[5], new TopLevelComplexType[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public TopLevelComplexType getComplexTypeArray(int i) {
            TopLevelComplexType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TopLevelComplexType) get_store().find_element_user(PROPERTY_QNAME[5], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public int sizeOfComplexTypeArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setComplexTypeArray(TopLevelComplexType[] complexTypeArray) {
            check_orphaned();
            arraySetterHelper(complexTypeArray, PROPERTY_QNAME[5]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setComplexTypeArray(int i, TopLevelComplexType complexType) {
            generatedSetterHelperImpl(complexType, PROPERTY_QNAME[5], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public TopLevelComplexType insertNewComplexType(int i) {
            TopLevelComplexType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TopLevelComplexType) get_store().insert_element_user(PROPERTY_QNAME[5], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public TopLevelComplexType addNewComplexType() {
            TopLevelComplexType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TopLevelComplexType) get_store().add_element_user(PROPERTY_QNAME[5]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void removeComplexType(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[5], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public List<NamedGroup> getGroupList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda50
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.getGroupArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda51
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        SchemaDocumentImpl.SchemaImpl.this.setGroupArray(((Integer) obj).intValue(), (NamedGroup) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda52
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.insertNewGroup(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda53
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        SchemaDocumentImpl.SchemaImpl.this.removeGroup(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda54
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(SchemaDocumentImpl.SchemaImpl.this.sizeOfGroupArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public NamedGroup[] getGroupArray() {
            return (NamedGroup[]) getXmlObjectArray(PROPERTY_QNAME[6], new NamedGroup[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public NamedGroup getGroupArray(int i) {
            NamedGroup target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NamedGroup) get_store().find_element_user(PROPERTY_QNAME[6], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public int sizeOfGroupArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setGroupArray(NamedGroup[] groupArray) {
            check_orphaned();
            arraySetterHelper(groupArray, PROPERTY_QNAME[6]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setGroupArray(int i, NamedGroup group) {
            generatedSetterHelperImpl(group, PROPERTY_QNAME[6], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public NamedGroup insertNewGroup(int i) {
            NamedGroup target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NamedGroup) get_store().insert_element_user(PROPERTY_QNAME[6], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public NamedGroup addNewGroup() {
            NamedGroup target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NamedGroup) get_store().add_element_user(PROPERTY_QNAME[6]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void removeGroup(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[6], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public List<NamedAttributeGroup> getAttributeGroupList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda28
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.getAttributeGroupArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda29
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        SchemaDocumentImpl.SchemaImpl.this.setAttributeGroupArray(((Integer) obj).intValue(), (NamedAttributeGroup) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda30
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.insertNewAttributeGroup(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda31
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        SchemaDocumentImpl.SchemaImpl.this.removeAttributeGroup(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda32
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(SchemaDocumentImpl.SchemaImpl.this.sizeOfAttributeGroupArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public NamedAttributeGroup[] getAttributeGroupArray() {
            return (NamedAttributeGroup[]) getXmlObjectArray(PROPERTY_QNAME[7], new NamedAttributeGroup[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public NamedAttributeGroup getAttributeGroupArray(int i) {
            NamedAttributeGroup target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NamedAttributeGroup) get_store().find_element_user(PROPERTY_QNAME[7], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public int sizeOfAttributeGroupArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setAttributeGroupArray(NamedAttributeGroup[] attributeGroupArray) {
            check_orphaned();
            arraySetterHelper(attributeGroupArray, PROPERTY_QNAME[7]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setAttributeGroupArray(int i, NamedAttributeGroup attributeGroup) {
            generatedSetterHelperImpl(attributeGroup, PROPERTY_QNAME[7], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public NamedAttributeGroup insertNewAttributeGroup(int i) {
            NamedAttributeGroup target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NamedAttributeGroup) get_store().insert_element_user(PROPERTY_QNAME[7], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public NamedAttributeGroup addNewAttributeGroup() {
            NamedAttributeGroup target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NamedAttributeGroup) get_store().add_element_user(PROPERTY_QNAME[7]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void removeAttributeGroup(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[7], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public List<TopLevelElement> getElementList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda17
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.getElementArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda18
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        SchemaDocumentImpl.SchemaImpl.this.setElementArray(((Integer) obj).intValue(), (TopLevelElement) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda19
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.insertNewElement(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda20
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        SchemaDocumentImpl.SchemaImpl.this.removeElement(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda21
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(SchemaDocumentImpl.SchemaImpl.this.sizeOfElementArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public TopLevelElement[] getElementArray() {
            return (TopLevelElement[]) getXmlObjectArray(PROPERTY_QNAME[8], new TopLevelElement[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public TopLevelElement getElementArray(int i) {
            TopLevelElement target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TopLevelElement) get_store().find_element_user(PROPERTY_QNAME[8], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public int sizeOfElementArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setElementArray(TopLevelElement[] elementArray) {
            check_orphaned();
            arraySetterHelper(elementArray, PROPERTY_QNAME[8]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setElementArray(int i, TopLevelElement element) {
            generatedSetterHelperImpl(element, PROPERTY_QNAME[8], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public TopLevelElement insertNewElement(int i) {
            TopLevelElement target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TopLevelElement) get_store().insert_element_user(PROPERTY_QNAME[8], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public TopLevelElement addNewElement() {
            TopLevelElement target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TopLevelElement) get_store().add_element_user(PROPERTY_QNAME[8]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void removeElement(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[8], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public List<TopLevelAttribute> getAttributeList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda1
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.getAttributeArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda2
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        SchemaDocumentImpl.SchemaImpl.this.setAttributeArray(((Integer) obj).intValue(), (TopLevelAttribute) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda3
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.insertNewAttribute(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda4
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        SchemaDocumentImpl.SchemaImpl.this.removeAttribute(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda5
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(SchemaDocumentImpl.SchemaImpl.this.sizeOfAttributeArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public TopLevelAttribute[] getAttributeArray() {
            return (TopLevelAttribute[]) getXmlObjectArray(PROPERTY_QNAME[9], new TopLevelAttribute[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public TopLevelAttribute getAttributeArray(int i) {
            TopLevelAttribute target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TopLevelAttribute) get_store().find_element_user(PROPERTY_QNAME[9], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public int sizeOfAttributeArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setAttributeArray(TopLevelAttribute[] attributeArray) {
            check_orphaned();
            arraySetterHelper(attributeArray, PROPERTY_QNAME[9]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setAttributeArray(int i, TopLevelAttribute attribute) {
            generatedSetterHelperImpl(attribute, PROPERTY_QNAME[9], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public TopLevelAttribute insertNewAttribute(int i) {
            TopLevelAttribute target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TopLevelAttribute) get_store().insert_element_user(PROPERTY_QNAME[9], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public TopLevelAttribute addNewAttribute() {
            TopLevelAttribute target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TopLevelAttribute) get_store().add_element_user(PROPERTY_QNAME[9]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void removeAttribute(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[9], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public List<NotationDocument.Notation> getNotationList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda45
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.getNotationArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda46
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        SchemaDocumentImpl.SchemaImpl.this.setNotationArray(((Integer) obj).intValue(), (NotationDocument.Notation) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda47
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return SchemaDocumentImpl.SchemaImpl.this.insertNewNotation(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda48
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        SchemaDocumentImpl.SchemaImpl.this.removeNotation(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda49
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(SchemaDocumentImpl.SchemaImpl.this.sizeOfNotationArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public NotationDocument.Notation[] getNotationArray() {
            return (NotationDocument.Notation[]) getXmlObjectArray(PROPERTY_QNAME[10], new NotationDocument.Notation[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public NotationDocument.Notation getNotationArray(int i) {
            NotationDocument.Notation target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NotationDocument.Notation) get_store().find_element_user(PROPERTY_QNAME[10], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public int sizeOfNotationArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setNotationArray(NotationDocument.Notation[] notationArray) {
            check_orphaned();
            arraySetterHelper(notationArray, PROPERTY_QNAME[10]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setNotationArray(int i, NotationDocument.Notation notation) {
            generatedSetterHelperImpl(notation, PROPERTY_QNAME[10], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public NotationDocument.Notation insertNewNotation(int i) {
            NotationDocument.Notation target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NotationDocument.Notation) get_store().insert_element_user(PROPERTY_QNAME[10], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public NotationDocument.Notation addNewNotation() {
            NotationDocument.Notation target;
            synchronized (monitor()) {
                check_orphaned();
                target = (NotationDocument.Notation) get_store().add_element_user(PROPERTY_QNAME[10]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void removeNotation(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[10], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public String getTargetNamespace() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public XmlAnyURI xgetTargetNamespace() {
            XmlAnyURI target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public boolean isSetTargetNamespace() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setTargetNamespace(String targetNamespace) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
                }
                target.setStringValue(targetNamespace);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void xsetTargetNamespace(XmlAnyURI targetNamespace) {
            synchronized (monitor()) {
                check_orphaned();
                XmlAnyURI target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[11]);
                if (target == null) {
                    target = (XmlAnyURI) get_store().add_attribute_user(PROPERTY_QNAME[11]);
                }
                target.set(targetNamespace);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void unsetTargetNamespace() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[11]);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public String getVersion() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public XmlToken xgetVersion() {
            XmlToken target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public boolean isSetVersion() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setVersion(String version) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
                }
                target.setStringValue(version);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void xsetVersion(XmlToken version) {
            synchronized (monitor()) {
                check_orphaned();
                XmlToken target = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[12]);
                if (target == null) {
                    target = (XmlToken) get_store().add_attribute_user(PROPERTY_QNAME[12]);
                }
                target.set(version);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void unsetVersion() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[12]);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public Object getFinalDefault() {
            Object objectValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
                if (target == null) {
                    target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[13]);
                }
                objectValue = target == null ? null : target.getObjectValue();
            }
            return objectValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public FullDerivationSet xgetFinalDefault() {
            FullDerivationSet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (FullDerivationSet) get_store().find_attribute_user(PROPERTY_QNAME[13]);
                if (target == null) {
                    target = (FullDerivationSet) get_default_attribute_value(PROPERTY_QNAME[13]);
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public boolean isSetFinalDefault() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setFinalDefault(Object finalDefault) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
                }
                target.setObjectValue(finalDefault);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void xsetFinalDefault(FullDerivationSet finalDefault) {
            synchronized (monitor()) {
                check_orphaned();
                FullDerivationSet target = (FullDerivationSet) get_store().find_attribute_user(PROPERTY_QNAME[13]);
                if (target == null) {
                    target = (FullDerivationSet) get_store().add_attribute_user(PROPERTY_QNAME[13]);
                }
                target.set(finalDefault);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void unsetFinalDefault() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[13]);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public Object getBlockDefault() {
            Object objectValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
                if (target == null) {
                    target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[14]);
                }
                objectValue = target == null ? null : target.getObjectValue();
            }
            return objectValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public BlockSet xgetBlockDefault() {
            BlockSet target;
            synchronized (monitor()) {
                check_orphaned();
                target = (BlockSet) get_store().find_attribute_user(PROPERTY_QNAME[14]);
                if (target == null) {
                    target = (BlockSet) get_default_attribute_value(PROPERTY_QNAME[14]);
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public boolean isSetBlockDefault() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[14]) != null;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setBlockDefault(Object blockDefault) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[14]);
                }
                target.setObjectValue(blockDefault);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void xsetBlockDefault(BlockSet blockDefault) {
            synchronized (monitor()) {
                check_orphaned();
                BlockSet target = (BlockSet) get_store().find_attribute_user(PROPERTY_QNAME[14]);
                if (target == null) {
                    target = (BlockSet) get_store().add_attribute_user(PROPERTY_QNAME[14]);
                }
                target.set(blockDefault);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void unsetBlockDefault() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[14]);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public FormChoice.Enum getAttributeFormDefault() {
            FormChoice.Enum r1;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
                if (target == null) {
                    target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[15]);
                }
                r1 = target == null ? null : (FormChoice.Enum) target.getEnumValue();
            }
            return r1;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public FormChoice xgetAttributeFormDefault() {
            FormChoice target;
            synchronized (monitor()) {
                check_orphaned();
                target = (FormChoice) get_store().find_attribute_user(PROPERTY_QNAME[15]);
                if (target == null) {
                    target = (FormChoice) get_default_attribute_value(PROPERTY_QNAME[15]);
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public boolean isSetAttributeFormDefault() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[15]) != null;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setAttributeFormDefault(FormChoice.Enum attributeFormDefault) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[15]);
                }
                target.setEnumValue(attributeFormDefault);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void xsetAttributeFormDefault(FormChoice attributeFormDefault) {
            synchronized (monitor()) {
                check_orphaned();
                FormChoice target = (FormChoice) get_store().find_attribute_user(PROPERTY_QNAME[15]);
                if (target == null) {
                    target = (FormChoice) get_store().add_attribute_user(PROPERTY_QNAME[15]);
                }
                target.set(attributeFormDefault);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void unsetAttributeFormDefault() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[15]);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public FormChoice.Enum getElementFormDefault() {
            FormChoice.Enum r1;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
                if (target == null) {
                    target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[16]);
                }
                r1 = target == null ? null : (FormChoice.Enum) target.getEnumValue();
            }
            return r1;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public FormChoice xgetElementFormDefault() {
            FormChoice target;
            synchronized (monitor()) {
                check_orphaned();
                target = (FormChoice) get_store().find_attribute_user(PROPERTY_QNAME[16]);
                if (target == null) {
                    target = (FormChoice) get_default_attribute_value(PROPERTY_QNAME[16]);
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public boolean isSetElementFormDefault() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[16]) != null;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setElementFormDefault(FormChoice.Enum elementFormDefault) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[16]);
                }
                target.setEnumValue(elementFormDefault);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void xsetElementFormDefault(FormChoice elementFormDefault) {
            synchronized (monitor()) {
                check_orphaned();
                FormChoice target = (FormChoice) get_store().find_attribute_user(PROPERTY_QNAME[16]);
                if (target == null) {
                    target = (FormChoice) get_store().add_attribute_user(PROPERTY_QNAME[16]);
                }
                target.set(elementFormDefault);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void unsetElementFormDefault() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[16]);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public String getId() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public XmlID xgetId() {
            XmlID target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public boolean isSetId() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[17]) != null;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setId(String id) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[17]);
                }
                target.setStringValue(id);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void xsetId(XmlID id) {
            synchronized (monitor()) {
                check_orphaned();
                XmlID target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[17]);
                if (target == null) {
                    target = (XmlID) get_store().add_attribute_user(PROPERTY_QNAME[17]);
                }
                target.set(id);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void unsetId() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[17]);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public String getLang() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public LangAttribute.Lang xgetLang() {
            LangAttribute.Lang target;
            synchronized (monitor()) {
                check_orphaned();
                target = (LangAttribute.Lang) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public boolean isSetLang() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[18]) != null;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void setLang(String lang) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[18]);
                }
                target.setStringValue(lang);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void xsetLang(LangAttribute.Lang lang) {
            synchronized (monitor()) {
                check_orphaned();
                LangAttribute.Lang target = (LangAttribute.Lang) get_store().find_attribute_user(PROPERTY_QNAME[18]);
                if (target == null) {
                    target = (LangAttribute.Lang) get_store().add_attribute_user(PROPERTY_QNAME[18]);
                }
                target.set(lang);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument.Schema
        public void unsetLang() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[18]);
            }
        }
    }
}
