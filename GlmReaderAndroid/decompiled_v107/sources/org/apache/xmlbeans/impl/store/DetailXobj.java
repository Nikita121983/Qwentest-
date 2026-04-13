package org.apache.xmlbeans.impl.store;

import java.util.Iterator;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.soap.Detail;
import org.apache.xmlbeans.impl.soap.DetailEntry;
import org.apache.xmlbeans.impl.soap.Name;

/* loaded from: classes11.dex */
class DetailXobj extends SoapFaultElementXobj implements Detail {
    /* JADX INFO: Access modifiers changed from: package-private */
    public DetailXobj(Locale l, QName name) {
        super(l, name);
    }

    @Override // org.apache.xmlbeans.impl.store.SoapFaultElementXobj, org.apache.xmlbeans.impl.store.SoapElementXobj, org.apache.xmlbeans.impl.store.ElementXobj, org.apache.xmlbeans.impl.store.Xobj
    Xobj newNode(Locale l) {
        return new DetailXobj(l, this._name);
    }

    @Override // org.apache.xmlbeans.impl.soap.Detail
    public DetailEntry addDetailEntry(Name name) {
        return DomImpl.detail_addDetailEntry(this, name);
    }

    @Override // org.apache.xmlbeans.impl.soap.Detail
    public Iterator<DetailEntry> getDetailEntries() {
        return DomImpl.detail_getDetailEntries(this);
    }
}
