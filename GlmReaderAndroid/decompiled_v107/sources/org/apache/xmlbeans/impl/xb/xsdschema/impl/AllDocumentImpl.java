package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.apache.xmlbeans.impl.xb.xsdschema.AllDocument;

/* loaded from: classes11.dex */
public class AllDocumentImpl extends XmlComplexContentImpl implements AllDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "all")};
    private static final long serialVersionUID = 1;

    public AllDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AllDocument
    public All getAll() {
        All all;
        synchronized (monitor()) {
            check_orphaned();
            All target = (All) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            all = target == null ? null : target;
        }
        return all;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AllDocument
    public void setAll(All all) {
        generatedSetterHelperImpl(all, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AllDocument
    public All addNewAll() {
        All target;
        synchronized (monitor()) {
            check_orphaned();
            target = (All) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
