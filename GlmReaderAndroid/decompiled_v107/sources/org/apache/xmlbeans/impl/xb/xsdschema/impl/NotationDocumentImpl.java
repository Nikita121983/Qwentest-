package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

/* loaded from: classes11.dex */
public class NotationDocumentImpl extends XmlComplexContentImpl implements NotationDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "notation")};
    private static final long serialVersionUID = 1;

    public NotationDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument
    public NotationDocument.Notation getNotation() {
        NotationDocument.Notation notation;
        synchronized (monitor()) {
            check_orphaned();
            NotationDocument.Notation target = (NotationDocument.Notation) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            notation = target == null ? null : target;
        }
        return notation;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument
    public void setNotation(NotationDocument.Notation notation) {
        generatedSetterHelperImpl(notation, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument
    public NotationDocument.Notation addNewNotation() {
        NotationDocument.Notation target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NotationDocument.Notation) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class NotationImpl extends AnnotatedImpl implements NotationDocument.Notation {
        private static final QName[] PROPERTY_QNAME = {new QName("", "name"), new QName("", "public"), new QName("", "system")};
        private static final long serialVersionUID = 1;

        public NotationImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument.Notation
        public String getName() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument.Notation
        public XmlNCName xgetName() {
            XmlNCName target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlNCName) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument.Notation
        public void setName(String name) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
                }
                target.setStringValue(name);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument.Notation
        public void xsetName(XmlNCName name) {
            synchronized (monitor()) {
                check_orphaned();
                XmlNCName target = (XmlNCName) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                if (target == null) {
                    target = (XmlNCName) get_store().add_attribute_user(PROPERTY_QNAME[0]);
                }
                target.set(name);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument.Notation
        public String getPublic() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument.Notation
        public Public xgetPublic() {
            Public target;
            synchronized (monitor()) {
                check_orphaned();
                target = (Public) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument.Notation
        public boolean isSetPublic() {
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

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument.Notation
        public void setPublic(String xpublic) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
                }
                target.setStringValue(xpublic);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument.Notation
        public void xsetPublic(Public xpublic) {
            synchronized (monitor()) {
                check_orphaned();
                Public target = (Public) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (target == null) {
                    target = (Public) get_store().add_attribute_user(PROPERTY_QNAME[1]);
                }
                target.set(xpublic);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument.Notation
        public void unsetPublic() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[1]);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument.Notation
        public String getSystem() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument.Notation
        public XmlAnyURI xgetSystem() {
            XmlAnyURI target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument.Notation
        public boolean isSetSystem() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument.Notation
        public void setSystem(String system) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
                }
                target.setStringValue(system);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument.Notation
        public void xsetSystem(XmlAnyURI system) {
            synchronized (monitor()) {
                check_orphaned();
                XmlAnyURI target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                if (target == null) {
                    target = (XmlAnyURI) get_store().add_attribute_user(PROPERTY_QNAME[2]);
                }
                target.set(system);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument.Notation
        public void unsetSystem() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[2]);
            }
        }
    }
}
