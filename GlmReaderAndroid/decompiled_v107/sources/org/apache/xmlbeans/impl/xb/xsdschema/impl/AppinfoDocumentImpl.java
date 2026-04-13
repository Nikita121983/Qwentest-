package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument;

/* loaded from: classes11.dex */
public class AppinfoDocumentImpl extends XmlComplexContentImpl implements AppinfoDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "appinfo")};
    private static final long serialVersionUID = 1;

    public AppinfoDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument
    public AppinfoDocument.Appinfo getAppinfo() {
        AppinfoDocument.Appinfo appinfo;
        synchronized (monitor()) {
            check_orphaned();
            AppinfoDocument.Appinfo target = (AppinfoDocument.Appinfo) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            appinfo = target == null ? null : target;
        }
        return appinfo;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument
    public void setAppinfo(AppinfoDocument.Appinfo appinfo) {
        generatedSetterHelperImpl(appinfo, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument
    public AppinfoDocument.Appinfo addNewAppinfo() {
        AppinfoDocument.Appinfo target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AppinfoDocument.Appinfo) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class AppinfoImpl extends XmlComplexContentImpl implements AppinfoDocument.Appinfo {
        private static final QName[] PROPERTY_QNAME = {new QName("", "source")};
        private static final long serialVersionUID = 1;

        public AppinfoImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument.Appinfo
        public String getSource() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument.Appinfo
        public XmlAnyURI xgetSource() {
            XmlAnyURI target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument.Appinfo
        public boolean isSetSource() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
            }
            return z;
        }

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument.Appinfo
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

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument.Appinfo
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

        @Override // org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument.Appinfo
        public void unsetSource() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[0]);
            }
        }
    }
}
