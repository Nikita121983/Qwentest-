package org.apache.xmlbeans.impl.xb.xmlconfig.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument;
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Usertypeconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl;

/* loaded from: classes11.dex */
public class ConfigDocumentImpl extends XmlComplexContentImpl implements ConfigDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "config")};
    private static final long serialVersionUID = 1;

    public ConfigDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument
    public ConfigDocument.Config getConfig() {
        ConfigDocument.Config config;
        synchronized (monitor()) {
            check_orphaned();
            ConfigDocument.Config target = (ConfigDocument.Config) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            config = target == null ? null : target;
        }
        return config;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument
    public void setConfig(ConfigDocument.Config config) {
        generatedSetterHelperImpl(config, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument
    public ConfigDocument.Config addNewConfig() {
        ConfigDocument.Config target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ConfigDocument.Config) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class ConfigImpl extends XmlComplexContentImpl implements ConfigDocument.Config {
        private static final QName[] PROPERTY_QNAME = {new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "namespace"), new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "qname"), new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "extension"), new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "usertype")};
        private static final long serialVersionUID = 1;

        public ConfigImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public List<Nsconfig> getNamespaceList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda1
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ConfigDocumentImpl.ConfigImpl.this.getNamespaceArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda2
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        ConfigDocumentImpl.ConfigImpl.this.setNamespaceArray(((Integer) obj).intValue(), (Nsconfig) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda3
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ConfigDocumentImpl.ConfigImpl.this.insertNewNamespace(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda4
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ConfigDocumentImpl.ConfigImpl.this.removeNamespace(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda5
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(ConfigDocumentImpl.ConfigImpl.this.sizeOfNamespaceArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public Nsconfig[] getNamespaceArray() {
            return (Nsconfig[]) getXmlObjectArray(PROPERTY_QNAME[0], new Nsconfig[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public Nsconfig getNamespaceArray(int i) {
            Nsconfig target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Nsconfig) get_store().find_element_user(PROPERTY_QNAME[0], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public int sizeOfNamespaceArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public void setNamespaceArray(Nsconfig[] namespaceArray) {
            check_orphaned();
            arraySetterHelper(namespaceArray, PROPERTY_QNAME[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public void setNamespaceArray(int i, Nsconfig namespace) {
            generatedSetterHelperImpl(namespace, PROPERTY_QNAME[0], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public Nsconfig insertNewNamespace(int i) {
            Nsconfig target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Nsconfig) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public Nsconfig addNewNamespace() {
            Nsconfig target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Nsconfig) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public void removeNamespace(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public List<Qnameconfig> getQnameList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda15
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ConfigDocumentImpl.ConfigImpl.this.getQnameArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda16
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        ConfigDocumentImpl.ConfigImpl.this.setQnameArray(((Integer) obj).intValue(), (Qnameconfig) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda17
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ConfigDocumentImpl.ConfigImpl.this.insertNewQname(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda18
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ConfigDocumentImpl.ConfigImpl.this.removeQname(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda19
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(ConfigDocumentImpl.ConfigImpl.this.sizeOfQnameArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public Qnameconfig[] getQnameArray() {
            return (Qnameconfig[]) getXmlObjectArray(PROPERTY_QNAME[1], new Qnameconfig[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public Qnameconfig getQnameArray(int i) {
            Qnameconfig target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Qnameconfig) get_store().find_element_user(PROPERTY_QNAME[1], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public int sizeOfQnameArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public void setQnameArray(Qnameconfig[] qnameArray) {
            check_orphaned();
            arraySetterHelper(qnameArray, PROPERTY_QNAME[1]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public void setQnameArray(int i, Qnameconfig qname) {
            generatedSetterHelperImpl(qname, PROPERTY_QNAME[1], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public Qnameconfig insertNewQname(int i) {
            Qnameconfig target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Qnameconfig) get_store().insert_element_user(PROPERTY_QNAME[1], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public Qnameconfig addNewQname() {
            Qnameconfig target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Qnameconfig) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public void removeQname(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[1], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public List<Extensionconfig> getExtensionList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda6
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ConfigDocumentImpl.ConfigImpl.this.getExtensionArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda7
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        ConfigDocumentImpl.ConfigImpl.this.setExtensionArray(((Integer) obj).intValue(), (Extensionconfig) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda8
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ConfigDocumentImpl.ConfigImpl.this.insertNewExtension(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda9
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ConfigDocumentImpl.ConfigImpl.this.removeExtension(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda10
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(ConfigDocumentImpl.ConfigImpl.this.sizeOfExtensionArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public Extensionconfig[] getExtensionArray() {
            return (Extensionconfig[]) getXmlObjectArray(PROPERTY_QNAME[2], new Extensionconfig[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public Extensionconfig getExtensionArray(int i) {
            Extensionconfig target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Extensionconfig) get_store().find_element_user(PROPERTY_QNAME[2], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public int sizeOfExtensionArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public void setExtensionArray(Extensionconfig[] extensionArray) {
            check_orphaned();
            arraySetterHelper(extensionArray, PROPERTY_QNAME[2]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public void setExtensionArray(int i, Extensionconfig extension) {
            generatedSetterHelperImpl(extension, PROPERTY_QNAME[2], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public Extensionconfig insertNewExtension(int i) {
            Extensionconfig target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Extensionconfig) get_store().insert_element_user(PROPERTY_QNAME[2], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public Extensionconfig addNewExtension() {
            Extensionconfig target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Extensionconfig) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public void removeExtension(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[2], i);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public List<Usertypeconfig> getUsertypeList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ConfigDocumentImpl.ConfigImpl.this.getUsertypeArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda11
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        ConfigDocumentImpl.ConfigImpl.this.setUsertypeArray(((Integer) obj).intValue(), (Usertypeconfig) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda12
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ConfigDocumentImpl.ConfigImpl.this.insertNewUsertype(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda13
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ConfigDocumentImpl.ConfigImpl.this.removeUsertype(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ConfigDocumentImpl$ConfigImpl$$ExternalSyntheticLambda14
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(ConfigDocumentImpl.ConfigImpl.this.sizeOfUsertypeArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public Usertypeconfig[] getUsertypeArray() {
            return (Usertypeconfig[]) getXmlObjectArray(PROPERTY_QNAME[3], new Usertypeconfig[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public Usertypeconfig getUsertypeArray(int i) {
            Usertypeconfig target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Usertypeconfig) get_store().find_element_user(PROPERTY_QNAME[3], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public int sizeOfUsertypeArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public void setUsertypeArray(Usertypeconfig[] usertypeArray) {
            check_orphaned();
            arraySetterHelper(usertypeArray, PROPERTY_QNAME[3]);
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public void setUsertypeArray(int i, Usertypeconfig usertype) {
            generatedSetterHelperImpl(usertype, PROPERTY_QNAME[3], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public Usertypeconfig insertNewUsertype(int i) {
            Usertypeconfig target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Usertypeconfig) get_store().insert_element_user(PROPERTY_QNAME[3], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public Usertypeconfig addNewUsertype() {
            Usertypeconfig target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Usertypeconfig) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument.Config
        public void removeUsertype(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[3], i);
            }
        }
    }
}
