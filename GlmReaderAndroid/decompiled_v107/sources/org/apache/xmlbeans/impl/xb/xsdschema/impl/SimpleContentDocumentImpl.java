package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleContentDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleExtensionType;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleRestrictionType;

/* loaded from: classes11.dex */
public class SimpleContentDocumentImpl extends XmlComplexContentImpl implements SimpleContentDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "simpleContent")};
    private static final long serialVersionUID = 1;

    public SimpleContentDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleContentDocument
    public SimpleContentDocument.SimpleContent getSimpleContent() {
        SimpleContentDocument.SimpleContent simpleContent;
        synchronized (monitor()) {
            check_orphaned();
            SimpleContentDocument.SimpleContent target = (SimpleContentDocument.SimpleContent) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            simpleContent = target == null ? null : target;
        }
        return simpleContent;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleContentDocument
    public void setSimpleContent(SimpleContentDocument.SimpleContent simpleContent) {
        generatedSetterHelperImpl(simpleContent, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleContentDocument
    public SimpleContentDocument.SimpleContent addNewSimpleContent() {
        SimpleContentDocument.SimpleContent target;
        synchronized (monitor()) {
            check_orphaned();
            target = (SimpleContentDocument.SimpleContent) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class SimpleContentImpl extends AnnotatedImpl implements SimpleContentDocument.SimpleContent {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "restriction"), new QName("http://www.w3.org/2001/XMLSchema", "extension")};
        private static final long serialVersionUID = 1;

        public SimpleContentImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleContentDocument.SimpleContent
        public SimpleRestrictionType getRestriction() {
            SimpleRestrictionType simpleRestrictionType;
            synchronized (monitor()) {
                check_orphaned();
                SimpleRestrictionType target = (SimpleRestrictionType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
                simpleRestrictionType = target == null ? null : target;
            }
            return simpleRestrictionType;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleContentDocument.SimpleContent
        public boolean isSetRestriction() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleContentDocument.SimpleContent
        public void setRestriction(SimpleRestrictionType restriction) {
            generatedSetterHelperImpl(restriction, PROPERTY_QNAME[0], 0, (short) 1);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleContentDocument.SimpleContent
        public SimpleRestrictionType addNewRestriction() {
            SimpleRestrictionType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (SimpleRestrictionType) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleContentDocument.SimpleContent
        public void unsetRestriction() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], 0);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleContentDocument.SimpleContent
        public SimpleExtensionType getExtension() {
            SimpleExtensionType simpleExtensionType;
            synchronized (monitor()) {
                check_orphaned();
                SimpleExtensionType target = (SimpleExtensionType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
                simpleExtensionType = target == null ? null : target;
            }
            return simpleExtensionType;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleContentDocument.SimpleContent
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

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleContentDocument.SimpleContent
        public void setExtension(SimpleExtensionType extension) {
            generatedSetterHelperImpl(extension, PROPERTY_QNAME[1], 0, (short) 1);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleContentDocument.SimpleContent
        public SimpleExtensionType addNewExtension() {
            SimpleExtensionType target;
            synchronized (monitor()) {
                check_orphaned();
                target = (SimpleExtensionType) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleContentDocument.SimpleContent
        public void unsetExtension() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[1], 0);
            }
        }
    }
}
