package org.apache.xmlbeans.impl.store;

import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.impl.common.ValidatorListener;

/* loaded from: classes11.dex */
final class Validate implements ValidatorListener.Event {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Cur _cur;
    private boolean _hasText;
    private boolean _oneChunk;
    private ValidatorListener _sink;
    private Cur _textCur;
    private StringBuffer _textSb;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public Validate(Cur cur, ValidatorListener validatorListener) {
        if (!cur.isUserNode()) {
            throw new IllegalStateException("Inappropriate location to validate");
        }
        this._sink = validatorListener;
        this._cur = cur;
        this._textCur = cur.tempCur();
        this._hasText = false;
        this._cur.push();
        try {
            process();
        } finally {
            this._cur.pop();
            this._cur = null;
            this._sink = null;
            this._textCur.release();
        }
    }

    private void process() {
        emitEvent(1);
        if (this._cur.isAttr()) {
            this._cur.next();
            if (this._cur.isText()) {
                emitText();
            }
        } else {
            if (!this._cur.isContainer()) {
                throw new AssertionError();
            }
            doAttrs();
            while (true) {
                this._cur.next();
                if (!this._cur.isAtEndOfLastPush()) {
                    switch (this._cur.kind()) {
                        case -2:
                            emitEvent(2);
                            break;
                        case -1:
                        case 1:
                        case 3:
                        default:
                            throw new RuntimeException("Unexpected kind: " + this._cur.kind());
                        case 0:
                            emitText();
                            break;
                        case 2:
                            emitEvent(1);
                            doAttrs();
                            break;
                        case 4:
                        case 5:
                            this._cur.toEnd();
                            break;
                    }
                }
            }
        }
        emitEvent(2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002e, code lost:
    
        if (r2._cur.toNextAttr() != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0030, code lost:
    
        r2._cur.toParent();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0035, code lost:
    
        r2._sink.nextEvent(5, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000a, code lost:
    
        if (r2._cur.toFirstAttr() != false) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0012, code lost:
    
        if (r2._cur.isNormalAttr() == false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0020, code lost:
    
        if (r2._cur.getUri().equals("http://www.w3.org/2001/XMLSchema-instance") != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
    
        r2._sink.nextEvent(4, r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void doAttrs() {
        /*
            r2 = this;
            boolean r0 = r2._hasText
            if (r0 != 0) goto L3c
            org.apache.xmlbeans.impl.store.Cur r0 = r2._cur
            boolean r0 = r0.toFirstAttr()
            if (r0 == 0) goto L35
        Lc:
            org.apache.xmlbeans.impl.store.Cur r0 = r2._cur
            boolean r0 = r0.isNormalAttr()
            if (r0 == 0) goto L28
            org.apache.xmlbeans.impl.store.Cur r0 = r2._cur
            java.lang.String r0 = r0.getUri()
            java.lang.String r1 = "http://www.w3.org/2001/XMLSchema-instance"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L28
            org.apache.xmlbeans.impl.common.ValidatorListener r0 = r2._sink
            r1 = 4
            r0.nextEvent(r1, r2)
        L28:
            org.apache.xmlbeans.impl.store.Cur r0 = r2._cur
            boolean r0 = r0.toNextAttr()
            if (r0 != 0) goto Lc
            org.apache.xmlbeans.impl.store.Cur r0 = r2._cur
            r0.toParent()
        L35:
            org.apache.xmlbeans.impl.common.ValidatorListener r0 = r2._sink
            r1 = 5
            r0.nextEvent(r1, r2)
            return
        L3c:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Validate.doAttrs():void");
    }

    private void emitText() {
        if (!this._cur.isText()) {
            throw new AssertionError();
        }
        if (this._hasText) {
            if (this._oneChunk) {
                if (this._textSb == null) {
                    this._textSb = new StringBuffer();
                } else {
                    this._textSb.delete(0, this._textSb.length());
                }
                if (!this._textCur.isText()) {
                    throw new AssertionError();
                }
                CharUtil.getString(this._textSb, this._textCur.getChars(-1), this._textCur._offSrc, this._textCur._cchSrc);
                this._oneChunk = false;
            }
            if (this._textSb == null || this._textSb.length() <= 0) {
                throw new AssertionError();
            }
            CharUtil.getString(this._textSb, this._cur.getChars(-1), this._cur._offSrc, this._cur._cchSrc);
            return;
        }
        this._hasText = true;
        this._oneChunk = true;
        this._textCur.moveToCur(this._cur);
    }

    private void emitEvent(int kind) {
        if (kind == 3) {
            throw new AssertionError();
        }
        if (kind == 4 && this._hasText) {
            throw new AssertionError();
        }
        if (kind == 5 && this._hasText) {
            throw new AssertionError();
        }
        if (this._hasText) {
            this._sink.nextEvent(3, this);
            this._hasText = false;
        }
        this._sink.nextEvent(kind, this);
    }

    @Override // org.apache.xmlbeans.impl.common.ValidatorListener.Event
    public String getText() {
        if (this._cur.isAttr()) {
            return this._cur.getValueAsString();
        }
        if (!this._hasText) {
            throw new AssertionError();
        }
        if (!this._oneChunk && (this._textSb == null || this._textSb.length() <= 0)) {
            throw new AssertionError();
        }
        if (!this._oneChunk || this._textCur.isText()) {
            return this._oneChunk ? this._textCur.getCharsAsString() : this._textSb.toString();
        }
        throw new AssertionError();
    }

    @Override // org.apache.xmlbeans.impl.common.ValidatorListener.Event
    public String getText(int wsr) {
        if (this._cur.isAttr()) {
            return this._cur.getValueAsString(wsr);
        }
        if (!this._hasText) {
            throw new AssertionError();
        }
        if (!this._oneChunk && (this._textSb == null || this._textSb.length() <= 0)) {
            throw new AssertionError();
        }
        if (this._oneChunk && !this._textCur.isText()) {
            throw new AssertionError();
        }
        if (this._oneChunk) {
            return this._textCur.getCharsAsString(wsr);
        }
        return Locale.applyWhiteSpaceRule(this._textSb.toString(), wsr);
    }

    @Override // org.apache.xmlbeans.impl.common.ValidatorListener.Event
    public boolean textIsWhitespace() {
        if (this._cur.isAttr()) {
            return this._cur._locale.getCharUtil().isWhiteSpace(this._cur.getFirstChars(), this._cur._offSrc, this._cur._cchSrc);
        }
        if (!this._hasText) {
            throw new AssertionError();
        }
        if (this._oneChunk) {
            return this._cur._locale.getCharUtil().isWhiteSpace(this._textCur.getChars(-1), this._textCur._offSrc, this._textCur._cchSrc);
        }
        String s = this._textSb.toString();
        return this._cur._locale.getCharUtil().isWhiteSpace(s, 0, s.length());
    }

    @Override // org.apache.xmlbeans.impl.common.PrefixResolver
    public String getNamespaceForPrefix(String prefix) {
        return this._cur.namespaceForPrefix(prefix, true);
    }

    @Override // org.apache.xmlbeans.impl.common.ValidatorListener.Event
    public XmlCursor getLocationAsCursor() {
        return new Cursor(this._cur);
    }

    @Override // org.apache.xmlbeans.impl.common.ValidatorListener.Event
    public Location getLocation() {
        return null;
    }

    @Override // org.apache.xmlbeans.impl.common.ValidatorListener.Event
    public String getXsiType() {
        return this._cur.getAttrValue(Locale._xsiType);
    }

    @Override // org.apache.xmlbeans.impl.common.ValidatorListener.Event
    public String getXsiNil() {
        return this._cur.getAttrValue(Locale._xsiNil);
    }

    @Override // org.apache.xmlbeans.impl.common.ValidatorListener.Event
    public String getXsiLoc() {
        return this._cur.getAttrValue(Locale._xsiLoc);
    }

    @Override // org.apache.xmlbeans.impl.common.ValidatorListener.Event
    public String getXsiNoLoc() {
        return this._cur.getAttrValue(Locale._xsiNoLoc);
    }

    @Override // org.apache.xmlbeans.impl.common.ValidatorListener.Event
    public QName getName() {
        if (this._cur.isAtLastPush()) {
            return null;
        }
        return this._cur.getName();
    }
}
