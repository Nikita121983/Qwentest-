package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;

/* loaded from: classes11.dex */
public class ListDocumentImpl extends XmlComplexContentImpl implements ListDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", XmlErrorCodes.LIST)};
    private static final long serialVersionUID = 1;

    public ListDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ListDocument
    public ListDocument.List getList() {
        ListDocument.List list;
        synchronized (monitor()) {
            check_orphaned();
            ListDocument.List target = (ListDocument.List) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            list = target == null ? null : target;
        }
        return list;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ListDocument
    public void setList(ListDocument.List list) {
        generatedSetterHelperImpl(list, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ListDocument
    public ListDocument.List addNewList() {
        ListDocument.List target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ListDocument.List) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class ListImpl extends AnnotatedImpl implements ListDocument.List {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "simpleType"), new QName("", "itemType")};
        private static final long serialVersionUID = 1;

        public ListImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List
        public LocalSimpleType getSimpleType() {
            LocalSimpleType localSimpleType;
            synchronized (monitor()) {
                check_orphaned();
                LocalSimpleType target = (LocalSimpleType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
                localSimpleType = target == null ? null : target;
            }
            return localSimpleType;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List
        public boolean isSetSimpleType() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List
        public void setSimpleType(LocalSimpleType simpleType) {
            generatedSetterHelperImpl(simpleType, PROPERTY_QNAME[0], 0, (short) 1);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List
        public LocalSimpleType addNewSimpleType() {
            LocalSimpleType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (LocalSimpleType) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List
        public void unsetSimpleType() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], 0);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List
        public QName getItemType() {
            QName qNameValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                qNameValue = target == null ? null : target.getQNameValue();
            }
            return qNameValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List
        public XmlQName xgetItemType() {
            XmlQName target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List
        public boolean isSetItemType() {
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

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List
        public void setItemType(QName itemType) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
                }
                target.setQNameValue(itemType);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List
        public void xsetItemType(XmlQName itemType) {
            synchronized (monitor()) {
                check_orphaned();
                XmlQName target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (target == null) {
                    target = (XmlQName) get_store().add_attribute_user(PROPERTY_QNAME[1]);
                }
                target.set(itemType);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List
        public void unsetItemType() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[1]);
            }
        }
    }
}
