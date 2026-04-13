package org.apache.xmlbeans.impl.store;

import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.soap.Text;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class SaajTextNode extends TextNode implements Text {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SaajTextNode(Locale l) {
        super(l);
    }

    @Override // org.apache.xmlbeans.impl.soap.Text
    public boolean isComment() {
        return DomImpl._soapText_isComment(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.Node
    public void detachNode() {
        DomImpl._soapNode_detachNode(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.Node
    public void recycleNode() {
        DomImpl._soapNode_recycleNode(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.Node
    public String getValue() {
        return DomImpl._soapNode_getValue(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.Node
    public void setValue(String value) {
        DomImpl._soapNode_setValue(this, value);
    }

    @Override // org.apache.xmlbeans.impl.soap.Node
    public SOAPElement getParentElement() {
        return DomImpl._soapNode_getParentElement(this);
    }

    @Override // org.apache.xmlbeans.impl.soap.Node
    public void setParentElement(SOAPElement p) {
        DomImpl._soapNode_setParentElement(this, p);
    }
}
