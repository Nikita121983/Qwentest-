package org.apache.xmlbeans.impl.store;

import java.util.Iterator;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.SOAPHeader;
import org.apache.xmlbeans.impl.soap.SOAPHeaderElement;

/* loaded from: classes11.dex */
class SoapHeaderXobj extends SoapElementXobj implements SOAPHeader {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SoapHeaderXobj(Locale l, QName name) {
        super(l, name);
    }

    @Override // org.apache.xmlbeans.impl.store.SoapElementXobj, org.apache.xmlbeans.impl.store.ElementXobj, org.apache.xmlbeans.impl.store.Xobj
    Xobj newNode(Locale l) {
        return new SoapHeaderXobj(l, this._name);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPHeader
    public Iterator<SOAPHeaderElement> examineAllHeaderElements() {
        return DomImpl.soapHeader_examineAllHeaderElements(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPHeader
    public Iterator<SOAPHeaderElement> extractAllHeaderElements() {
        return DomImpl.soapHeader_extractAllHeaderElements(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPHeader
    public Iterator<SOAPHeaderElement> examineHeaderElements(String actor) {
        return DomImpl.soapHeader_examineHeaderElements(this, actor);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPHeader
    public Iterator<SOAPHeaderElement> examineMustUnderstandHeaderElements(String mustUnderstandString) {
        return DomImpl.soapHeader_examineMustUnderstandHeaderElements(this, mustUnderstandString);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPHeader
    public Iterator<SOAPHeaderElement> extractHeaderElements(String actor) {
        return DomImpl.soapHeader_extractHeaderElements(this, actor);
    }

    @Override // org.apache.xmlbeans.impl.soap.SOAPHeader
    public SOAPHeaderElement addHeaderElement(Name name) {
        return DomImpl.soapHeader_addHeaderElement(this, name);
    }
}
