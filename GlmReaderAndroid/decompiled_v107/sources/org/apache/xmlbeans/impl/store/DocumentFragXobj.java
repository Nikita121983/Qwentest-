package org.apache.xmlbeans.impl.store;

import org.w3c.dom.DocumentFragment;

/* loaded from: classes11.dex */
class DocumentFragXobj extends NodeXobj implements DocumentFragment {
    /* JADX INFO: Access modifiers changed from: package-private */
    public DocumentFragXobj(Locale l) {
        super(l, 1, 11);
    }

    @Override // org.apache.xmlbeans.impl.store.Xobj
    Xobj newNode(Locale l) {
        return new DocumentFragXobj(l);
    }
}
