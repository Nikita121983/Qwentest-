package org.apache.xmlbeans.impl.store;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public abstract class NamedNodeXobj extends NodeXobj {
    boolean _canHavePrefixUri;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NamedNodeXobj(Locale l, int kind, int domType) {
        super(l, kind, domType);
        this._canHavePrefixUri = true;
    }

    @Override // org.apache.xmlbeans.impl.store.NodeXobj, org.apache.xmlbeans.impl.store.DomImpl.Dom
    public boolean nodeCanHavePrefixUri() {
        return this._canHavePrefixUri;
    }
}
