package org.apache.xmlbeans.impl.store;

import org.w3c.dom.CDATASection;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class CdataNode extends TextNode implements CDATASection {
    /* JADX INFO: Access modifiers changed from: package-private */
    public CdataNode(Locale l) {
        super(l);
    }

    @Override // org.apache.xmlbeans.impl.store.TextNode, org.apache.xmlbeans.impl.store.DomImpl.Dom
    public int nodeType() {
        return 4;
    }

    @Override // org.apache.xmlbeans.impl.store.TextNode
    public String name() {
        return "#cdata-section";
    }
}
