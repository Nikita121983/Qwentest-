package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.KeyDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Keybase;

/* loaded from: classes11.dex */
public class KeyDocumentImpl extends XmlComplexContentImpl implements KeyDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "key")};
    private static final long serialVersionUID = 1;

    public KeyDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.KeyDocument
    public Keybase getKey() {
        Keybase keybase;
        synchronized (monitor()) {
            check_orphaned();
            Keybase target = (Keybase) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            keybase = target == null ? null : target;
        }
        return keybase;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.KeyDocument
    public void setKey(Keybase key) {
        generatedSetterHelperImpl(key, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.KeyDocument
    public Keybase addNewKey() {
        Keybase target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Keybase) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
