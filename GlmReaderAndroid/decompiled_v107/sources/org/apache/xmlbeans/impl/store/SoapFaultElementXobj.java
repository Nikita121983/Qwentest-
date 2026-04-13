package org.apache.xmlbeans.impl.store;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.soap.SOAPFaultElement;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class SoapFaultElementXobj extends SoapElementXobj implements SOAPFaultElement {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SoapFaultElementXobj(Locale l, QName name) {
        super(l, name);
    }

    @Override // org.apache.xmlbeans.impl.store.SoapElementXobj, org.apache.xmlbeans.impl.store.ElementXobj, org.apache.xmlbeans.impl.store.Xobj
    Xobj newNode(Locale l) {
        return new SoapFaultElementXobj(l, this._name);
    }
}
