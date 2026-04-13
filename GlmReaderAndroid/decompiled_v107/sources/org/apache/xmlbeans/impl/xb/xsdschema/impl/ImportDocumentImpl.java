package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument;

/* loaded from: classes11.dex */
public class ImportDocumentImpl extends XmlComplexContentImpl implements ImportDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "import")};
    private static final long serialVersionUID = 1;

    public ImportDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument
    public ImportDocument.Import getImport() {
        ImportDocument.Import r1;
        synchronized (monitor()) {
            check_orphaned();
            ImportDocument.Import target = (ImportDocument.Import) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            r1 = target == null ? null : target;
        }
        return r1;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument
    public void setImport(ImportDocument.Import ximport) {
        generatedSetterHelperImpl(ximport, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument
    public ImportDocument.Import addNewImport() {
        ImportDocument.Import target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ImportDocument.Import) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class ImportImpl extends AnnotatedImpl implements ImportDocument.Import {
        private static final QName[] PROPERTY_QNAME = {new QName("", "namespace"), new QName("", "schemaLocation")};
        private static final long serialVersionUID = 1;

        public ImportImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument.Import
        public String getNamespace() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument.Import
        public XmlAnyURI xgetNamespace() {
            XmlAnyURI target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument.Import
        public boolean isSetNamespace() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument.Import
        public void setNamespace(String namespace) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
                }
                target.setStringValue(namespace);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument.Import
        public void xsetNamespace(XmlAnyURI namespace) {
            synchronized (monitor()) {
                check_orphaned();
                XmlAnyURI target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                if (target == null) {
                    target = (XmlAnyURI) get_store().add_attribute_user(PROPERTY_QNAME[0]);
                }
                target.set(namespace);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument.Import
        public void unsetNamespace() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[0]);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument.Import
        public String getSchemaLocation() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument.Import
        public XmlAnyURI xgetSchemaLocation() {
            XmlAnyURI target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument.Import
        public boolean isSetSchemaLocation() {
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

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument.Import
        public void setSchemaLocation(String schemaLocation) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
                }
                target.setStringValue(schemaLocation);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument.Import
        public void xsetSchemaLocation(XmlAnyURI schemaLocation) {
            synchronized (monitor()) {
                check_orphaned();
                XmlAnyURI target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (target == null) {
                    target = (XmlAnyURI) get_store().add_attribute_user(PROPERTY_QNAME[1]);
                }
                target.set(schemaLocation);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument.Import
        public void unsetSchemaLocation() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[1]);
            }
        }
    }
}
