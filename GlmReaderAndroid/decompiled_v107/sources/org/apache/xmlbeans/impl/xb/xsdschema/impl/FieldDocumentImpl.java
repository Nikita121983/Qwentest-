package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.JavaStringHolderEx;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument;

/* loaded from: classes11.dex */
public class FieldDocumentImpl extends XmlComplexContentImpl implements FieldDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "field")};
    private static final long serialVersionUID = 1;

    public FieldDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument
    public FieldDocument.Field getField() {
        FieldDocument.Field field;
        synchronized (monitor()) {
            check_orphaned();
            FieldDocument.Field target = (FieldDocument.Field) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            field = target == null ? null : target;
        }
        return field;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument
    public void setField(FieldDocument.Field field) {
        generatedSetterHelperImpl(field, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument
    public FieldDocument.Field addNewField() {
        FieldDocument.Field target;
        synchronized (monitor()) {
            check_orphaned();
            target = (FieldDocument.Field) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class FieldImpl extends AnnotatedImpl implements FieldDocument.Field {
        private static final QName[] PROPERTY_QNAME = {new QName("", "xpath")};
        private static final long serialVersionUID = 1;

        public FieldImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument.Field
        public String getXpath() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument.Field
        public FieldDocument.Field.Xpath xgetXpath() {
            FieldDocument.Field.Xpath target;
            synchronized (monitor()) {
                check_orphaned();
                target = (FieldDocument.Field.Xpath) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument.Field
        public void setXpath(String xpath) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
                }
                target.setStringValue(xpath);
            }
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument.Field
        public void xsetXpath(FieldDocument.Field.Xpath xpath) {
            synchronized (monitor()) {
                check_orphaned();
                FieldDocument.Field.Xpath target = (FieldDocument.Field.Xpath) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                if (target == null) {
                    target = (FieldDocument.Field.Xpath) get_store().add_attribute_user(PROPERTY_QNAME[0]);
                }
                target.set(xpath);
            }
        }

        /* loaded from: classes11.dex */
        public static class XpathImpl extends JavaStringHolderEx implements FieldDocument.Field.Xpath {
            private static final long serialVersionUID = 1;

            public XpathImpl(SchemaType sType) {
                super(sType, false);
            }

            protected XpathImpl(SchemaType sType, boolean b) {
                super(sType, b);
            }
        }
    }
}
