package org.apache.xmlbeans.impl.xpath.xmlbeans;

import java.util.ConcurrentModificationException;
import org.apache.xmlbeans.impl.store.Cur;
import org.apache.xmlbeans.impl.store.Locale;
import org.apache.xmlbeans.impl.xpath.XPath;
import org.apache.xmlbeans.impl.xpath.XPathEngine;
import org.apache.xmlbeans.impl.xpath.XPathExecutionContext;

/* loaded from: classes11.dex */
class XmlbeansXPathEngine extends XPathExecutionContext implements XPathEngine {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Cur _cur;
    private final long _version;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XmlbeansXPathEngine(XPath xpath, Cur c) {
        if (!c.isContainer()) {
            throw new AssertionError();
        }
        this._version = c.getLocale().version();
        this._cur = c.weakCur(this);
        this._cur.push();
        init(xpath);
        int ret = start();
        if ((ret & 1) != 0) {
            c.addToSelection();
        }
        doAttrs(ret, c);
        if ((ret & 2) == 0 || !Locale.toFirstChildElement(this._cur)) {
            release();
        }
    }

    private void advance(Cur c) {
        if (this._cur == null) {
            throw new AssertionError();
        }
        if (this._cur.isFinish()) {
            if (this._cur.isAtEndOfLastPush()) {
                release();
                return;
            } else {
                end();
                this._cur.next();
                return;
            }
        }
        if (this._cur.isElem()) {
            int ret = element(this._cur.getName());
            if ((ret & 1) != 0) {
                c.addToSelection(this._cur);
            }
            doAttrs(ret, c);
            if ((ret & 2) == 0 || !Locale.toFirstChildElement(this._cur)) {
                end();
                this._cur.skip();
                return;
            }
            return;
        }
        do {
            this._cur.next();
        } while (!this._cur.isContainerOrFinish());
    }

    private void doAttrs(int ret, Cur c) {
        if (!this._cur.isContainer()) {
            throw new AssertionError();
        }
        if ((ret & 4) == 0 || !this._cur.toFirstAttr()) {
            return;
        }
        do {
            if (attr(this._cur.getName())) {
                c.addToSelection(this._cur);
            }
        } while (this._cur.toNextAttr());
        this._cur.toParent();
    }

    @Override // org.apache.xmlbeans.impl.xpath.XPathEngine
    public boolean next(Cur c) {
        if (this._cur != null && this._version != this._cur.getLocale().version()) {
            throw new ConcurrentModificationException("Document changed during select");
        }
        int startCount = c.selectionCount();
        while (this._cur != null) {
            advance(c);
            if (startCount != c.selectionCount()) {
                return true;
            }
        }
        return false;
    }

    @Override // org.apache.xmlbeans.impl.xpath.XPathEngine
    public void release() {
        if (this._cur != null) {
            this._cur.release();
            this._cur = null;
        }
    }
}
