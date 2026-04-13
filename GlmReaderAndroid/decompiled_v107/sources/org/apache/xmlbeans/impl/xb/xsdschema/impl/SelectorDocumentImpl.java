package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.JavaStringHolderEx;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument;

/* loaded from: classes11.dex */
public class SelectorDocumentImpl extends XmlComplexContentImpl implements SelectorDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "selector")};
    private static final long serialVersionUID = 1;

    public SelectorDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument
    public SelectorDocument.Selector getSelector() {
        SelectorDocument.Selector selector;
        synchronized (monitor()) {
            check_orphaned();
            SelectorDocument.Selector target = (SelectorDocument.Selector) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            selector = target == null ? null : target;
        }
        return selector;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument
    public void setSelector(SelectorDocument.Selector selector) {
        generatedSetterHelperImpl(selector, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument
    public SelectorDocument.Selector addNewSelector() {
        SelectorDocument.Selector target;
        synchronized (monitor()) {
            check_orphaned();
            target = (SelectorDocument.Selector) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class SelectorImpl extends AnnotatedImpl implements SelectorDocument.Selector {
        private static final QName[] PROPERTY_QNAME = {new QName("", "xpath")};
        private static final long serialVersionUID = 1;

        public SelectorImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument.Selector
        public String getXpath() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument.Selector
        public SelectorDocument.Selector.Xpath xgetXpath() {
            SelectorDocument.Selector.Xpath target;
            synchronized (monitor()) {
                check_orphaned();
                target = (SelectorDocument.Selector.Xpath) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument.Selector
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

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument.Selector
        public void xsetXpath(SelectorDocument.Selector.Xpath xpath) {
            synchronized (monitor()) {
                check_orphaned();
                SelectorDocument.Selector.Xpath target = (SelectorDocument.Selector.Xpath) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                if (target == null) {
                    target = (SelectorDocument.Selector.Xpath) get_store().add_attribute_user(PROPERTY_QNAME[0]);
                }
                target.set(xpath);
            }
        }

        /* loaded from: classes11.dex */
        public static class XpathImpl extends JavaStringHolderEx implements SelectorDocument.Selector.Xpath {
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
