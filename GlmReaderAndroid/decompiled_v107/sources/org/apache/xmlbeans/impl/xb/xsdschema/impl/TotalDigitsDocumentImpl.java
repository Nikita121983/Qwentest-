package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument;

/* loaded from: classes11.dex */
public class TotalDigitsDocumentImpl extends XmlComplexContentImpl implements TotalDigitsDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "totalDigits")};
    private static final long serialVersionUID = 1;

    public TotalDigitsDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument
    public TotalDigitsDocument.TotalDigits getTotalDigits() {
        TotalDigitsDocument.TotalDigits totalDigits;
        synchronized (monitor()) {
            check_orphaned();
            TotalDigitsDocument.TotalDigits target = (TotalDigitsDocument.TotalDigits) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            totalDigits = target == null ? null : target;
        }
        return totalDigits;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument
    public void setTotalDigits(TotalDigitsDocument.TotalDigits totalDigits) {
        generatedSetterHelperImpl(totalDigits, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument
    public TotalDigitsDocument.TotalDigits addNewTotalDigits() {
        TotalDigitsDocument.TotalDigits target;
        synchronized (monitor()) {
            check_orphaned();
            target = (TotalDigitsDocument.TotalDigits) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class TotalDigitsImpl extends NumFacetImpl implements TotalDigitsDocument.TotalDigits {
        private static final long serialVersionUID = 1;

        public TotalDigitsImpl(SchemaType sType) {
            super(sType);
        }
    }
}
