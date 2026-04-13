package org.apache.xmlbeans.impl.xb.xmlconfig.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.JavaNameList;

/* loaded from: classes11.dex */
public class ExtensionconfigImpl extends XmlComplexContentImpl implements Extensionconfig {
    private static final QName[] PROPERTY_QNAME = {new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "interface"), new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "prePostSet"), new QName("", "for")};
    private static final long serialVersionUID = 1;

    public ExtensionconfigImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig
    public List<Extensionconfig.Interface> getInterfaceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ExtensionconfigImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ExtensionconfigImpl.this.getInterfaceArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ExtensionconfigImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ExtensionconfigImpl.this.setInterfaceArray(((Integer) obj).intValue(), (Extensionconfig.Interface) obj2);
                }
            }, new Function() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ExtensionconfigImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ExtensionconfigImpl.this.insertNewInterface(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ExtensionconfigImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ExtensionconfigImpl.this.removeInterface(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.xmlconfig.impl.ExtensionconfigImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(ExtensionconfigImpl.this.sizeOfInterfaceArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig
    public Extensionconfig.Interface[] getInterfaceArray() {
        return (Extensionconfig.Interface[]) getXmlObjectArray(PROPERTY_QNAME[0], new Extensionconfig.Interface[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig
    public Extensionconfig.Interface getInterfaceArray(int i) {
        Extensionconfig.Interface target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Extensionconfig.Interface) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig
    public int sizeOfInterfaceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig
    public void setInterfaceArray(Extensionconfig.Interface[] xinterfaceArray) {
        check_orphaned();
        arraySetterHelper(xinterfaceArray, PROPERTY_QNAME[0]);
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig
    public void setInterfaceArray(int i, Extensionconfig.Interface xinterface) {
        generatedSetterHelperImpl(xinterface, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig
    public Extensionconfig.Interface insertNewInterface(int i) {
        Extensionconfig.Interface target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Extensionconfig.Interface) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig
    public Extensionconfig.Interface addNewInterface() {
        Extensionconfig.Interface target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Extensionconfig.Interface) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig
    public void removeInterface(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig
    public Extensionconfig.PrePostSet getPrePostSet() {
        Extensionconfig.PrePostSet prePostSet;
        synchronized (monitor()) {
            check_orphaned();
            Extensionconfig.PrePostSet target = (Extensionconfig.PrePostSet) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            prePostSet = target == null ? null : target;
        }
        return prePostSet;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig
    public boolean isSetPrePostSet() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig
    public void setPrePostSet(Extensionconfig.PrePostSet prePostSet) {
        generatedSetterHelperImpl(prePostSet, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig
    public Extensionconfig.PrePostSet addNewPrePostSet() {
        Extensionconfig.PrePostSet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Extensionconfig.PrePostSet) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig
    public void unsetPrePostSet() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig
    public Object getFor() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig
    public JavaNameList xgetFor() {
        JavaNameList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (JavaNameList) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig
    public boolean isSetFor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig
    public void setFor(Object xfor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setObjectValue(xfor);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig
    public void xsetFor(JavaNameList xfor) {
        synchronized (monitor()) {
            check_orphaned();
            JavaNameList target = (JavaNameList) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (JavaNameList) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(xfor);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig
    public void unsetFor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    /* loaded from: classes11.dex */
    public static class InterfaceImpl extends XmlComplexContentImpl implements Extensionconfig.Interface {
        private static final QName[] PROPERTY_QNAME = {new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "staticHandler"), new QName("", "name")};
        private static final long serialVersionUID = 1;

        public InterfaceImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig.Interface
        public String getStaticHandler() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig.Interface
        public XmlString xgetStaticHandler() {
            XmlString target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig.Interface
        public void setStaticHandler(String staticHandler) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
                if (target == null) {
                    target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
                }
                target.setStringValue(staticHandler);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig.Interface
        public void xsetStaticHandler(XmlString staticHandler) {
            synchronized (monitor()) {
                check_orphaned();
                XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
                if (target == null) {
                    target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[0]);
                }
                target.set(staticHandler);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig.Interface
        public String getName() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig.Interface
        public XmlString xgetName() {
            XmlString target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig.Interface
        public boolean isSetName() {
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

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig.Interface
        public void setName(String name) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
                }
                target.setStringValue(name);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig.Interface
        public void xsetName(XmlString name) {
            synchronized (monitor()) {
                check_orphaned();
                XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (target == null) {
                    target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[1]);
                }
                target.set(name);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig.Interface
        public void unsetName() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[1]);
            }
        }
    }

    /* loaded from: classes11.dex */
    public static class PrePostSetImpl extends XmlComplexContentImpl implements Extensionconfig.PrePostSet {
        private static final QName[] PROPERTY_QNAME = {new QName("http://xml.apache.org/xmlbeans/2004/02/xbean/config", "staticHandler")};
        private static final long serialVersionUID = 1;

        public PrePostSetImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig.PrePostSet
        public String getStaticHandler() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig.PrePostSet
        public XmlString xgetStaticHandler() {
            XmlString target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig.PrePostSet
        public void setStaticHandler(String staticHandler) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
                if (target == null) {
                    target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
                }
                target.setStringValue(staticHandler);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig.PrePostSet
        public void xsetStaticHandler(XmlString staticHandler) {
            synchronized (monitor()) {
                check_orphaned();
                XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
                if (target == null) {
                    target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[0]);
                }
                target.set(staticHandler);
            }
        }
    }
}
