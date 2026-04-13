package org.apache.xmlbeans.impl.store;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.soap.DetailEntry;

/* loaded from: classes11.dex */
class DetailEntryXobj extends SoapElementXobj implements DetailEntry {
    @Override // org.apache.xmlbeans.impl.store.SoapElementXobj, org.apache.xmlbeans.impl.store.ElementXobj, org.apache.xmlbeans.impl.store.Xobj
    Xobj newNode(Locale l) {
        return new DetailEntryXobj(l, this._name);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DetailEntryXobj(Locale l, QName name) {
        super(l, name);
    }
}
