package org.apache.xmlbeans.impl.store;

import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class ProcInstXobj extends NodeXobj implements ProcessingInstruction {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ProcInstXobj(Locale l, String target) {
        super(l, 5, 7);
        this._name = this._locale.makeQName(null, target);
    }

    @Override // org.apache.xmlbeans.impl.store.Xobj
    Xobj newNode(Locale l) {
        return new ProcInstXobj(l, this._name.getLocalPart());
    }

    @Override // org.apache.xmlbeans.impl.store.NodeXobj, org.w3c.dom.NodeList, org.w3c.dom.CharacterData
    public int getLength() {
        return 0;
    }

    @Override // org.apache.xmlbeans.impl.store.NodeXobj, org.w3c.dom.Node
    public Node getFirstChild() {
        return null;
    }

    @Override // org.w3c.dom.ProcessingInstruction
    public String getData() {
        return DomImpl._processingInstruction_getData(this);
    }

    @Override // org.w3c.dom.ProcessingInstruction
    public String getTarget() {
        return DomImpl._processingInstruction_getTarget(this);
    }

    @Override // org.w3c.dom.ProcessingInstruction
    public void setData(String data) {
        DomImpl._processingInstruction_setData(this, data);
    }
}
