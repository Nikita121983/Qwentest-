package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute;
import org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument;

/* loaded from: classes11.dex */
public class DocumentationDocumentImpl extends XmlComplexContentImpl implements DocumentationDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "documentation")};
    private static final long serialVersionUID = 1;

    public DocumentationDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument
    public DocumentationDocument.Documentation getDocumentation() {
        DocumentationDocument.Documentation documentation;
        synchronized (monitor()) {
            check_orphaned();
            DocumentationDocument.Documentation target = (DocumentationDocument.Documentation) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            documentation = target == null ? null : target;
        }
        return documentation;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument
    public void setDocumentation(DocumentationDocument.Documentation documentation) {
        generatedSetterHelperImpl(documentation, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument
    public DocumentationDocument.Documentation addNewDocumentation() {
        DocumentationDocument.Documentation target;
        synchronized (monitor()) {
            check_orphaned();
            target = (DocumentationDocument.Documentation) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class DocumentationImpl extends XmlComplexContentImpl implements DocumentationDocument.Documentation {
        private static final QName[] PROPERTY_QNAME = {new QName("", "source"), new QName("http://www.w3.org/XML/1998/namespace", "lang")};
        private static final long serialVersionUID = 1;

        public DocumentationImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument.Documentation
        public String getSource() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument.Documentation
        public XmlAnyURI xgetSource() {
            XmlAnyURI target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument.Documentation
        public boolean isSetSource() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument.Documentation
        public void setSource(String source) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
                }
                target.setStringValue(source);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument.Documentation
        public void xsetSource(XmlAnyURI source) {
            synchronized (monitor()) {
                check_orphaned();
                XmlAnyURI target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                if (target == null) {
                    target = (XmlAnyURI) get_store().add_attribute_user(PROPERTY_QNAME[0]);
                }
                target.set(source);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument.Documentation
        public void unsetSource() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[0]);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument.Documentation
        public String getLang() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument.Documentation
        public LangAttribute.Lang xgetLang() {
            LangAttribute.Lang target;
            synchronized (monitor()) {
                check_orphaned();
                target = (LangAttribute.Lang) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument.Documentation
        public boolean isSetLang() {
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

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument.Documentation
        public void setLang(String lang) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
                }
                target.setStringValue(lang);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument.Documentation
        public void xsetLang(LangAttribute.Lang lang) {
            synchronized (monitor()) {
                check_orphaned();
                LangAttribute.Lang target = (LangAttribute.Lang) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (target == null) {
                    target = (LangAttribute.Lang) get_store().add_attribute_user(PROPERTY_QNAME[1]);
                }
                target.set(lang);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument.Documentation
        public void unsetLang() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[1]);
            }
        }
    }
}
