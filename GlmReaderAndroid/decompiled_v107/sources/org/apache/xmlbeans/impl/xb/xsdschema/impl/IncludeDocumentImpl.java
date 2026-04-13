package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument;

/* loaded from: classes11.dex */
public class IncludeDocumentImpl extends XmlComplexContentImpl implements IncludeDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "include")};
    private static final long serialVersionUID = 1;

    public IncludeDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument
    public IncludeDocument.Include getInclude() {
        IncludeDocument.Include include;
        synchronized (monitor()) {
            check_orphaned();
            IncludeDocument.Include target = (IncludeDocument.Include) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            include = target == null ? null : target;
        }
        return include;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument
    public void setInclude(IncludeDocument.Include include) {
        generatedSetterHelperImpl(include, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument
    public IncludeDocument.Include addNewInclude() {
        IncludeDocument.Include target;
        synchronized (monitor()) {
            check_orphaned();
            target = (IncludeDocument.Include) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class IncludeImpl extends AnnotatedImpl implements IncludeDocument.Include {
        private static final QName[] PROPERTY_QNAME = {new QName("", "schemaLocation")};
        private static final long serialVersionUID = 1;

        public IncludeImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument.Include
        public String getSchemaLocation() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument.Include
        public XmlAnyURI xgetSchemaLocation() {
            XmlAnyURI target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument.Include
        public void setSchemaLocation(String schemaLocation) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
                }
                target.setStringValue(schemaLocation);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument.Include
        public void xsetSchemaLocation(XmlAnyURI schemaLocation) {
            synchronized (monitor()) {
                check_orphaned();
                XmlAnyURI target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                if (target == null) {
                    target = (XmlAnyURI) get_store().add_attribute_user(PROPERTY_QNAME[0]);
                }
                target.set(schemaLocation);
            }
        }
    }
}
