package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.ComplexRestrictionType;
import org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType;

/* loaded from: classes11.dex */
public class ComplexContentDocumentImpl extends XmlComplexContentImpl implements ComplexContentDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "complexContent")};
    private static final long serialVersionUID = 1;

    public ComplexContentDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument
    public ComplexContentDocument.ComplexContent getComplexContent() {
        ComplexContentDocument.ComplexContent complexContent;
        synchronized (monitor()) {
            check_orphaned();
            ComplexContentDocument.ComplexContent target = (ComplexContentDocument.ComplexContent) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            complexContent = target == null ? null : target;
        }
        return complexContent;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument
    public void setComplexContent(ComplexContentDocument.ComplexContent complexContent) {
        generatedSetterHelperImpl(complexContent, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument
    public ComplexContentDocument.ComplexContent addNewComplexContent() {
        ComplexContentDocument.ComplexContent target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ComplexContentDocument.ComplexContent) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class ComplexContentImpl extends AnnotatedImpl implements ComplexContentDocument.ComplexContent {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "restriction"), new QName("http://www.w3.org/2001/XMLSchema", "extension"), new QName("", "mixed")};
        private static final long serialVersionUID = 1;

        public ComplexContentImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument.ComplexContent
        public ComplexRestrictionType getRestriction() {
            ComplexRestrictionType complexRestrictionType;
            synchronized (monitor()) {
                check_orphaned();
                ComplexRestrictionType target = (ComplexRestrictionType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
                complexRestrictionType = target == null ? null : target;
            }
            return complexRestrictionType;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument.ComplexContent
        public boolean isSetRestriction() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument.ComplexContent
        public void setRestriction(ComplexRestrictionType restriction) {
            generatedSetterHelperImpl(restriction, PROPERTY_QNAME[0], 0, (short) 1);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument.ComplexContent
        public ComplexRestrictionType addNewRestriction() {
            ComplexRestrictionType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (ComplexRestrictionType) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument.ComplexContent
        public void unsetRestriction() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], 0);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument.ComplexContent
        public ExtensionType getExtension() {
            ExtensionType extensionType;
            synchronized (monitor()) {
                check_orphaned();
                ExtensionType target = (ExtensionType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
                extensionType = target == null ? null : target;
            }
            return extensionType;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument.ComplexContent
        public boolean isSetExtension() {
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

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument.ComplexContent
        public void setExtension(ExtensionType extension) {
            generatedSetterHelperImpl(extension, PROPERTY_QNAME[1], 0, (short) 1);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument.ComplexContent
        public ExtensionType addNewExtension() {
            ExtensionType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (ExtensionType) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument.ComplexContent
        public void unsetExtension() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[1], 0);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument.ComplexContent
        public boolean getMixed() {
            boolean booleanValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                booleanValue = target == null ? false : target.getBooleanValue();
            }
            return booleanValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument.ComplexContent
        public XmlBoolean xgetMixed() {
            XmlBoolean target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument.ComplexContent
        public boolean isSetMixed() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument.ComplexContent
        public void setMixed(boolean mixed) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
                }
                target.setBooleanValue(mixed);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument.ComplexContent
        public void xsetMixed(XmlBoolean mixed) {
            synchronized (monitor()) {
                check_orphaned();
                XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                if (target == null) {
                    target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[2]);
                }
                target.set(mixed);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument.ComplexContent
        public void unsetMixed() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[2]);
            }
        }
    }
}
