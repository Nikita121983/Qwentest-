package org.apache.xmlbeans.impl.xb.xmlschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlLanguage;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute;

/* loaded from: classes11.dex */
public class LangAttributeImpl extends XmlComplexContentImpl implements LangAttribute {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/XML/1998/namespace", "lang")};
    private static final long serialVersionUID = 1;

    public LangAttributeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute
    public String getLang() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute
    public LangAttribute.Lang xgetLang() {
        LangAttribute.Lang target;
        synchronized (monitor()) {
            check_orphaned();
            target = (LangAttribute.Lang) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute
    public boolean isSetLang() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute
    public void setLang(String lang) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(lang);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute
    public void xsetLang(LangAttribute.Lang lang) {
        synchronized (monitor()) {
            check_orphaned();
            LangAttribute.Lang target = (LangAttribute.Lang) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (LangAttribute.Lang) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(lang);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute
    public void unsetLang() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    /* loaded from: classes11.dex */
    public static class LangImpl extends XmlUnionImpl implements LangAttribute.Lang, XmlLanguage, LangAttribute.Lang.Member {
        private static final long serialVersionUID = 1;

        public LangImpl(SchemaType sType) {
            super(sType, false);
        }

        protected LangImpl(SchemaType sType, boolean b) {
            super(sType, b);
        }

        /* loaded from: classes11.dex */
        public static class MemberImpl extends JavaStringEnumerationHolderEx implements LangAttribute.Lang.Member {
            private static final long serialVersionUID = 1;

            public MemberImpl(SchemaType sType) {
                super(sType, false);
            }

            protected MemberImpl(SchemaType sType, boolean b) {
                super(sType, b);
            }
        }
    }
}
