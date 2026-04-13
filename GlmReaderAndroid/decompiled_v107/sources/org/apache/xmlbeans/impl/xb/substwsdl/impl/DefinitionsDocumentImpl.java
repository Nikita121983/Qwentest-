package org.apache.xmlbeans.impl.xb.substwsdl.impl;

import androidx.core.app.NotificationCompat;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument;
import org.apache.xmlbeans.impl.xb.substwsdl.TImport;
import org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl;

/* loaded from: classes11.dex */
public class DefinitionsDocumentImpl extends XmlComplexContentImpl implements DefinitionsDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", "definitions")};
    private static final long serialVersionUID = 1;

    public DefinitionsDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument
    public DefinitionsDocument.Definitions getDefinitions() {
        DefinitionsDocument.Definitions definitions;
        synchronized (monitor()) {
            check_orphaned();
            DefinitionsDocument.Definitions target = (DefinitionsDocument.Definitions) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            definitions = target == null ? null : target;
        }
        return definitions;
    }

    @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument
    public void setDefinitions(DefinitionsDocument.Definitions definitions) {
        generatedSetterHelperImpl(definitions, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument
    public DefinitionsDocument.Definitions addNewDefinitions() {
        DefinitionsDocument.Definitions target;
        synchronized (monitor()) {
            check_orphaned();
            target = (DefinitionsDocument.Definitions) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class DefinitionsImpl extends XmlComplexContentImpl implements DefinitionsDocument.Definitions {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", "import"), new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", "types"), new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", "message"), new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", "binding"), new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", "portType"), new QName("http://www.apache.org/internal/xmlbeans/wsdlsubst", NotificationCompat.CATEGORY_SERVICE)};
        private static final long serialVersionUID = 1;

        public DefinitionsImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public List<TImport> getImportList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda25
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return DefinitionsDocumentImpl.DefinitionsImpl.this.getImportArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda26
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        DefinitionsDocumentImpl.DefinitionsImpl.this.setImportArray(((Integer) obj).intValue(), (TImport) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda27
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return DefinitionsDocumentImpl.DefinitionsImpl.this.insertNewImport(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda28
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        DefinitionsDocumentImpl.DefinitionsImpl.this.removeImport(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda29
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(DefinitionsDocumentImpl.DefinitionsImpl.this.sizeOfImportArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public TImport[] getImportArray() {
            return (TImport[]) getXmlObjectArray(PROPERTY_QNAME[0], new TImport[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public TImport getImportArray(int i) {
            TImport target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TImport) get_store().find_element_user(PROPERTY_QNAME[0], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public int sizeOfImportArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public void setImportArray(TImport[] ximportArray) {
            check_orphaned();
            arraySetterHelper(ximportArray, PROPERTY_QNAME[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public void setImportArray(int i, TImport ximport) {
            generatedSetterHelperImpl(ximport, PROPERTY_QNAME[0], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public TImport insertNewImport(int i) {
            TImport target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TImport) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public TImport addNewImport() {
            TImport target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TImport) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public void removeImport(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public List<XmlObject> getTypesList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda1
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return DefinitionsDocumentImpl.DefinitionsImpl.this.getTypesArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda2
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        DefinitionsDocumentImpl.DefinitionsImpl.this.setTypesArray(((Integer) obj).intValue(), (XmlObject) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda3
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return DefinitionsDocumentImpl.DefinitionsImpl.this.insertNewTypes(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda4
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        DefinitionsDocumentImpl.DefinitionsImpl.this.removeTypes(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda5
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(DefinitionsDocumentImpl.DefinitionsImpl.this.sizeOfTypesArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public XmlObject[] getTypesArray() {
            return getXmlObjectArray(PROPERTY_QNAME[1], new XmlObject[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public XmlObject getTypesArray(int i) {
            XmlObject target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlObject) get_store().find_element_user(PROPERTY_QNAME[1], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public int sizeOfTypesArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public void setTypesArray(XmlObject[] typesArray) {
            check_orphaned();
            arraySetterHelper(typesArray, PROPERTY_QNAME[1]);
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public void setTypesArray(int i, XmlObject types) {
            generatedSetterHelperImpl(types, PROPERTY_QNAME[1], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public XmlObject insertNewTypes(int i) {
            XmlObject target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlObject) get_store().insert_element_user(PROPERTY_QNAME[1], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public XmlObject addNewTypes() {
            XmlObject target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlObject) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public void removeTypes(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[1], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public List<XmlObject> getMessageList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return DefinitionsDocumentImpl.DefinitionsImpl.this.getMessageArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda11
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        DefinitionsDocumentImpl.DefinitionsImpl.this.setMessageArray(((Integer) obj).intValue(), (XmlObject) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda22
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return DefinitionsDocumentImpl.DefinitionsImpl.this.insertNewMessage(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda23
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        DefinitionsDocumentImpl.DefinitionsImpl.this.removeMessage(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda24
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(DefinitionsDocumentImpl.DefinitionsImpl.this.sizeOfMessageArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public XmlObject[] getMessageArray() {
            return getXmlObjectArray(PROPERTY_QNAME[2], new XmlObject[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public XmlObject getMessageArray(int i) {
            XmlObject target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlObject) get_store().find_element_user(PROPERTY_QNAME[2], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public int sizeOfMessageArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public void setMessageArray(XmlObject[] messageArray) {
            check_orphaned();
            arraySetterHelper(messageArray, PROPERTY_QNAME[2]);
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public void setMessageArray(int i, XmlObject message) {
            generatedSetterHelperImpl(message, PROPERTY_QNAME[2], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public XmlObject insertNewMessage(int i) {
            XmlObject target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlObject) get_store().insert_element_user(PROPERTY_QNAME[2], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public XmlObject addNewMessage() {
            XmlObject target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlObject) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public void removeMessage(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[2], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public List<XmlObject> getBindingList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda17
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return DefinitionsDocumentImpl.DefinitionsImpl.this.getBindingArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda18
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        DefinitionsDocumentImpl.DefinitionsImpl.this.setBindingArray(((Integer) obj).intValue(), (XmlObject) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda19
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return DefinitionsDocumentImpl.DefinitionsImpl.this.insertNewBinding(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda20
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        DefinitionsDocumentImpl.DefinitionsImpl.this.removeBinding(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda21
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(DefinitionsDocumentImpl.DefinitionsImpl.this.sizeOfBindingArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public XmlObject[] getBindingArray() {
            return getXmlObjectArray(PROPERTY_QNAME[3], new XmlObject[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public XmlObject getBindingArray(int i) {
            XmlObject target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlObject) get_store().find_element_user(PROPERTY_QNAME[3], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public int sizeOfBindingArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public void setBindingArray(XmlObject[] bindingArray) {
            check_orphaned();
            arraySetterHelper(bindingArray, PROPERTY_QNAME[3]);
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public void setBindingArray(int i, XmlObject binding) {
            generatedSetterHelperImpl(binding, PROPERTY_QNAME[3], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public XmlObject insertNewBinding(int i) {
            XmlObject target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlObject) get_store().insert_element_user(PROPERTY_QNAME[3], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public XmlObject addNewBinding() {
            XmlObject target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlObject) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public void removeBinding(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[3], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public List<XmlObject> getPortTypeList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda6
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return DefinitionsDocumentImpl.DefinitionsImpl.this.getPortTypeArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda7
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        DefinitionsDocumentImpl.DefinitionsImpl.this.setPortTypeArray(((Integer) obj).intValue(), (XmlObject) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda8
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return DefinitionsDocumentImpl.DefinitionsImpl.this.insertNewPortType(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda9
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        DefinitionsDocumentImpl.DefinitionsImpl.this.removePortType(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda10
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(DefinitionsDocumentImpl.DefinitionsImpl.this.sizeOfPortTypeArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public XmlObject[] getPortTypeArray() {
            return getXmlObjectArray(PROPERTY_QNAME[4], new XmlObject[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public XmlObject getPortTypeArray(int i) {
            XmlObject target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlObject) get_store().find_element_user(PROPERTY_QNAME[4], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public int sizeOfPortTypeArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public void setPortTypeArray(XmlObject[] portTypeArray) {
            check_orphaned();
            arraySetterHelper(portTypeArray, PROPERTY_QNAME[4]);
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public void setPortTypeArray(int i, XmlObject portType) {
            generatedSetterHelperImpl(portType, PROPERTY_QNAME[4], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public XmlObject insertNewPortType(int i) {
            XmlObject target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlObject) get_store().insert_element_user(PROPERTY_QNAME[4], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public XmlObject addNewPortType() {
            XmlObject target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlObject) get_store().add_element_user(PROPERTY_QNAME[4]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public void removePortType(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[4], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public List<XmlObject> getServiceList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda12
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return DefinitionsDocumentImpl.DefinitionsImpl.this.getServiceArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda13
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        DefinitionsDocumentImpl.DefinitionsImpl.this.setServiceArray(((Integer) obj).intValue(), (XmlObject) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda14
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return DefinitionsDocumentImpl.DefinitionsImpl.this.insertNewService(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda15
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        DefinitionsDocumentImpl.DefinitionsImpl.this.removeService(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.substwsdl.impl.DefinitionsDocumentImpl$DefinitionsImpl$$ExternalSyntheticLambda16
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(DefinitionsDocumentImpl.DefinitionsImpl.this.sizeOfServiceArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public XmlObject[] getServiceArray() {
            return getXmlObjectArray(PROPERTY_QNAME[5], new XmlObject[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public XmlObject getServiceArray(int i) {
            XmlObject target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlObject) get_store().find_element_user(PROPERTY_QNAME[5], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public int sizeOfServiceArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public void setServiceArray(XmlObject[] serviceArray) {
            check_orphaned();
            arraySetterHelper(serviceArray, PROPERTY_QNAME[5]);
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public void setServiceArray(int i, XmlObject service) {
            generatedSetterHelperImpl(service, PROPERTY_QNAME[5], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public XmlObject insertNewService(int i) {
            XmlObject target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlObject) get_store().insert_element_user(PROPERTY_QNAME[5], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public XmlObject addNewService() {
            XmlObject target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlObject) get_store().add_element_user(PROPERTY_QNAME[5]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.substwsdl.DefinitionsDocument.Definitions
        public void removeService(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[5], i);
            }
        }
    }
}
