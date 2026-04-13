package org.apache.xmlbeans.impl.store;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.XmlDocumentProperties;
import org.apache.xmlbeans.XmlLineNumber;
import org.apache.xmlbeans.XmlOptions;
import org.w3c.dom.Node;

/* loaded from: classes11.dex */
public class Jsr173 {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static Node nodeFromStream(XMLStreamReader xs) {
        Node nodeFromStreamImpl;
        if (!(xs instanceof Jsr173GateWay)) {
            return null;
        }
        Jsr173GateWay gw = (Jsr173GateWay) xs;
        Locale l = gw._l;
        if (l.noSync()) {
            l.enter();
            try {
                return nodeFromStreamImpl(gw);
            } finally {
            }
        }
        synchronized (l) {
            l.enter();
            try {
                nodeFromStreamImpl = nodeFromStreamImpl(gw);
            } finally {
            }
        }
        return nodeFromStreamImpl;
    }

    public static Node nodeFromStreamImpl(Jsr173GateWay gw) {
        Cur c = gw._xs.getStreamCur();
        if (c.isNode()) {
            return (Node) c.getDom();
        }
        return null;
    }

    public static XMLStreamReader newXmlStreamReader(Cur c, Object src, int off, int cch) {
        XMLStreamReaderBase xs = new XMLStreamReaderForString(c, src, off, cch);
        if (c._locale.noSync()) {
            return new UnsyncedJsr173(c._locale, xs);
        }
        return new SyncedJsr173(c._locale, xs);
    }

    public static XMLStreamReader newXmlStreamReader(Cur c, XmlOptions options) {
        XMLStreamReaderBase xs;
        XmlOptions options2 = XmlOptions.maskNull(options);
        boolean inner = options2.isSaveInner() && !options2.isSaveOuter();
        int k = c.kind();
        if (k == 0 || k < 0) {
            xs = new XMLStreamReaderForString(c, c.getChars(-1), c._offSrc, c._cchSrc);
        } else if (inner) {
            if (!c.hasAttrs() && !c.hasChildren()) {
                xs = new XMLStreamReaderForString(c, c.getFirstChars(), c._offSrc, c._cchSrc);
            } else {
                if (!c.isContainer()) {
                    throw new AssertionError();
                }
                xs = new XMLStreamReaderForNode(c, true);
            }
        } else {
            xs = new XMLStreamReaderForNode(c, false);
        }
        if (c._locale.noSync()) {
            return new UnsyncedJsr173(c._locale, xs);
        }
        return new SyncedJsr173(c._locale, xs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class XMLStreamReaderForNode extends XMLStreamReaderBase {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private int _cchChars;
        private int _cchSrc;
        private char[] _chars;
        private Cur _cur;
        private boolean _done;
        private Cur _end;
        private int _offChars;
        private int _offSrc;
        private Object _src;
        private boolean _srcFetched;
        private boolean _textFetched;
        private boolean _wholeDoc;

        public XMLStreamReaderForNode(Cur c, boolean inner) {
            super(c);
            if (!c.isContainer() && !c.isComment() && !c.isProcinst() && !c.isAttr()) {
                throw new AssertionError();
            }
            if (inner) {
                if (!c.isContainer()) {
                    throw new AssertionError();
                }
                this._cur = c.weakCur(this);
                if (!this._cur.toFirstAttr()) {
                    this._cur.next();
                }
                this._end = c.weakCur(this);
                this._end.toEnd();
            } else {
                this._cur = c.weakCur(this);
                if (c.isRoot()) {
                    this._wholeDoc = true;
                } else {
                    this._end = c.weakCur(this);
                    if (c.isAttr()) {
                        if (!this._end.toNextAttr()) {
                            this._end.toParent();
                            this._end.next();
                        }
                    } else {
                        this._end.skip();
                    }
                }
            }
            if (!this._wholeDoc) {
                this._cur.push();
                try {
                    next();
                    this._cur.pop();
                } catch (XMLStreamException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
            if (!this._wholeDoc && this._cur.isSamePos(this._end)) {
                throw new AssertionError();
            }
        }

        @Override // org.apache.xmlbeans.impl.store.Jsr173.XMLStreamReaderBase
        protected Cur getStreamCur() {
            return this._cur;
        }

        public boolean hasNext() throws XMLStreamException {
            checkChanged();
            return !this._done;
        }

        public int getEventType() {
            switch (this._cur.kind()) {
                case -2:
                    return 2;
                case -1:
                    return 8;
                case 0:
                    return 4;
                case 1:
                    return 7;
                case 2:
                    return 1;
                case 3:
                    return this._cur.isXmlns() ? 13 : 10;
                case 4:
                    return 5;
                case 5:
                    return 3;
                default:
                    throw new IllegalStateException();
            }
        }

        public int next() throws XMLStreamException {
            checkChanged();
            if (!hasNext()) {
                throw new IllegalStateException("No next event in stream");
            }
            int kind = this._cur.kind();
            boolean z = true;
            if (kind == -1) {
                if (!this._wholeDoc) {
                    throw new AssertionError();
                }
                this._done = true;
            } else {
                if (kind == 3) {
                    if (!this._cur.toNextAttr()) {
                        this._cur.toParent();
                        this._cur.next();
                    }
                } else if (kind == 4 || kind == 5) {
                    this._cur.skip();
                } else if (kind == 1) {
                    if (!this._cur.toFirstAttr()) {
                        this._cur.next();
                    }
                } else {
                    this._cur.next();
                }
                if (!this._wholeDoc && this._end == null) {
                    throw new AssertionError();
                }
                if (!this._wholeDoc) {
                    z = this._cur.isSamePos(this._end);
                } else if (this._cur.kind() != -1) {
                    z = false;
                }
                this._done = z;
            }
            this._textFetched = false;
            this._srcFetched = false;
            return getEventType();
        }

        public String getText() {
            checkChanged();
            int k = this._cur.kind();
            if (k == 4) {
                return this._cur.getValueAsString();
            }
            if (k == 0) {
                return this._cur.getCharsAsString();
            }
            throw new IllegalStateException();
        }

        public boolean isStartElement() {
            return getEventType() == 1;
        }

        public boolean isEndElement() {
            return getEventType() == 2;
        }

        public boolean isCharacters() {
            return getEventType() == 4;
        }

        public String getElementText() throws XMLStreamException {
            checkChanged();
            if (!isStartElement()) {
                throw new IllegalStateException();
            }
            StringBuilder sb = new StringBuilder();
            while (hasNext()) {
                int e = next();
                if (e != 2) {
                    if (e == 1) {
                        throw new XMLStreamException();
                    }
                    if (e != 5 && e != 3) {
                        sb.append(getText());
                    }
                } else {
                    return sb.toString();
                }
            }
            throw new XMLStreamException();
        }

        public int nextTag() throws XMLStreamException {
            checkChanged();
            while (!isStartElement() && !isEndElement()) {
                if (!isWhiteSpace()) {
                    throw new XMLStreamException();
                }
                if (!hasNext()) {
                    throw new XMLStreamException();
                }
                next();
            }
            return getEventType();
        }

        private static boolean matchAttr(Cur c, String uri, String local) {
            if (!c.isNormalAttr()) {
                throw new AssertionError();
            }
            QName name = c.getName();
            return name.getLocalPart().equals(local) && (uri == null || name.getNamespaceURI().equals(uri));
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x001f, code lost:
        
            if (r0.isNormalAttr() == false) goto L15;
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0025, code lost:
        
            if (matchAttr(r0, r4, r5) == false) goto L15;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0027, code lost:
        
            r1 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x002d, code lost:
        
            if (r0.toNextSibling() != false) goto L30;
         */
        /* JADX WARN: Code restructure failed: missing block: B:8:0x0019, code lost:
        
            if (r0.toFirstAttr() != false) goto L10;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static org.apache.xmlbeans.impl.store.Cur toAttr(org.apache.xmlbeans.impl.store.Cur r3, java.lang.String r4, java.lang.String r5) {
            /*
                if (r4 == 0) goto L47
                if (r5 == 0) goto L47
                boolean r0 = r5.isEmpty()
                if (r0 != 0) goto L47
                org.apache.xmlbeans.impl.store.Cur r0 = r3.tempCur()
                r1 = 0
                boolean r2 = r3.isElem()
                if (r2 == 0) goto L30
                boolean r2 = r0.toFirstAttr()
                if (r2 == 0) goto L3a
            L1b:
                boolean r2 = r0.isNormalAttr()
                if (r2 == 0) goto L29
                boolean r2 = matchAttr(r0, r4, r5)
                if (r2 == 0) goto L29
                r1 = 1
                goto L3a
            L29:
                boolean r2 = r0.toNextSibling()
                if (r2 != 0) goto L1b
                goto L3a
            L30:
                boolean r2 = r3.isNormalAttr()
                if (r2 == 0) goto L41
                boolean r1 = matchAttr(r3, r4, r5)
            L3a:
                if (r1 != 0) goto L40
                r0.release()
                r0 = 0
            L40:
                return r0
            L41:
                java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
                r2.<init>()
                throw r2
            L47:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                r0.<init>()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Jsr173.XMLStreamReaderForNode.toAttr(org.apache.xmlbeans.impl.store.Cur, java.lang.String, java.lang.String):org.apache.xmlbeans.impl.store.Cur");
        }

        public String getAttributeValue(String uri, String local) {
            Cur ca = toAttr(this._cur, uri, local);
            if (ca == null) {
                return null;
            }
            String value = ca.getValueAsString();
            ca.release();
            return value;
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0020, code lost:
        
            r5 = r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
        
            r1 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0025, code lost:
        
            if (r0.toNextSibling() != false) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:5:0x0011, code lost:
        
            if (r0.toFirstAttr() != false) goto L7;
         */
        /* JADX WARN: Code restructure failed: missing block: B:7:0x0017, code lost:
        
            if (r0.isNormalAttr() == false) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:8:0x0019, code lost:
        
            r2 = r5 - 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x001b, code lost:
        
            if (r5 != 0) goto L12;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static org.apache.xmlbeans.impl.store.Cur toAttr(org.apache.xmlbeans.impl.store.Cur r4, int r5) {
            /*
                if (r5 < 0) goto L48
                org.apache.xmlbeans.impl.store.Cur r0 = r4.tempCur()
                r1 = 0
                boolean r2 = r4.isElem()
                if (r2 == 0) goto L28
                boolean r2 = r0.toFirstAttr()
                if (r2 == 0) goto L34
            L13:
                boolean r2 = r0.isNormalAttr()
                if (r2 == 0) goto L21
                int r2 = r5 + (-1)
                if (r5 != 0) goto L20
                r1 = 1
                r5 = r2
                goto L34
            L20:
                r5 = r2
            L21:
                boolean r2 = r0.toNextSibling()
                if (r2 != 0) goto L13
                goto L34
            L28:
                boolean r2 = r4.isNormalAttr()
                if (r2 == 0) goto L42
                if (r5 != 0) goto L32
                r2 = 1
                goto L33
            L32:
                r2 = 0
            L33:
                r1 = r2
            L34:
                if (r1 == 0) goto L37
                return r0
            L37:
                r0.release()
                java.lang.IndexOutOfBoundsException r2 = new java.lang.IndexOutOfBoundsException
                java.lang.String r3 = "Attribute index is too large"
                r2.<init>(r3)
                throw r2
            L42:
                java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
                r2.<init>()
                throw r2
            L48:
                java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException
                java.lang.String r1 = "Attribute index is negative"
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Jsr173.XMLStreamReaderForNode.toAttr(org.apache.xmlbeans.impl.store.Cur, int):org.apache.xmlbeans.impl.store.Cur");
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x0023, code lost:
        
            r1.release();
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:?, code lost:
        
            return r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:4:0x0013, code lost:
        
            if (r1.toFirstAttr() != false) goto L6;
         */
        /* JADX WARN: Code restructure failed: missing block: B:6:0x0019, code lost:
        
            if (r1.isNormalAttr() == false) goto L9;
         */
        /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
        
            r0 = r0 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x0021, code lost:
        
            if (r1.toNextSibling() != false) goto L19;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int getAttributeCount() {
            /*
                r3 = this;
                r0 = 0
                org.apache.xmlbeans.impl.store.Cur r1 = r3._cur
                boolean r1 = r1.isElem()
                if (r1 == 0) goto L27
                org.apache.xmlbeans.impl.store.Cur r1 = r3._cur
                org.apache.xmlbeans.impl.store.Cur r1 = r1.tempCur()
                boolean r2 = r1.toFirstAttr()
                if (r2 == 0) goto L23
            L15:
                boolean r2 = r1.isNormalAttr()
                if (r2 == 0) goto L1d
                int r0 = r0 + 1
            L1d:
                boolean r2 = r1.toNextSibling()
                if (r2 != 0) goto L15
            L23:
                r1.release()
                goto L31
            L27:
                org.apache.xmlbeans.impl.store.Cur r1 = r3._cur
                boolean r1 = r1.isNormalAttr()
                if (r1 == 0) goto L32
                int r0 = r0 + 1
            L31:
                return r0
            L32:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                r1.<init>()
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Jsr173.XMLStreamReaderForNode.getAttributeCount():int");
        }

        public QName getAttributeName(int index) {
            Cur ca = toAttr(this._cur, index);
            QName name = ca.getName();
            ca.release();
            return name;
        }

        public String getAttributeNamespace(int index) {
            return getAttributeName(index).getNamespaceURI();
        }

        public String getAttributeLocalName(int index) {
            return getAttributeName(index).getLocalPart();
        }

        public String getAttributePrefix(int index) {
            return getAttributeName(index).getPrefix();
        }

        public String getAttributeType(int index) {
            toAttr(this._cur, index).release();
            return "CDATA";
        }

        public String getAttributeValue(int index) {
            Cur ca = toAttr(this._cur, index);
            if (ca == null) {
                return null;
            }
            String value = ca.getValueAsString();
            ca.release();
            return value;
        }

        public boolean isAttributeSpecified(int index) {
            Cur ca = toAttr(this._cur, index);
            ca.release();
            return false;
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x0039, code lost:
        
            if (r1.toFirstAttr() != false) goto L17;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x003f, code lost:
        
            if (r1.isXmlns() == false) goto L20;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0041, code lost:
        
            r0 = r0 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x0047, code lost:
        
            if (r1.toNextSibling() != false) goto L25;
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x0049, code lost:
        
            r1.release();
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x004d, code lost:
        
            return r0;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int getNamespaceCount() {
            /*
                r4 = this;
                r0 = 0
                org.apache.xmlbeans.impl.store.Cur r1 = r4._cur
                boolean r1 = r1.isElem()
                r2 = -2
                if (r1 != 0) goto L24
                org.apache.xmlbeans.impl.store.Cur r1 = r4._cur
                int r1 = r1.kind()
                if (r1 != r2) goto L13
                goto L24
            L13:
                org.apache.xmlbeans.impl.store.Cur r1 = r4._cur
                boolean r1 = r1.isXmlns()
                if (r1 == 0) goto L1e
                int r0 = r0 + 1
                goto L4d
            L1e:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                r1.<init>()
                throw r1
            L24:
                org.apache.xmlbeans.impl.store.Cur r1 = r4._cur
                org.apache.xmlbeans.impl.store.Cur r1 = r1.tempCur()
                org.apache.xmlbeans.impl.store.Cur r3 = r4._cur
                int r3 = r3.kind()
                if (r3 != r2) goto L35
                r1.toParent()
            L35:
                boolean r2 = r1.toFirstAttr()
                if (r2 == 0) goto L49
            L3b:
                boolean r2 = r1.isXmlns()
                if (r2 == 0) goto L43
                int r0 = r0 + 1
            L43:
                boolean r2 = r1.toNextSibling()
                if (r2 != 0) goto L3b
            L49:
                r1.release()
            L4d:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Jsr173.XMLStreamReaderForNode.getNamespaceCount():int");
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x0035, code lost:
        
            if (r0.toFirstAttr() != false) goto L21;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x003b, code lost:
        
            if (r0.isXmlns() == false) goto L27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x003d, code lost:
        
            r2 = r5 - 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x003f, code lost:
        
            if (r5 != 0) goto L26;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x0044, code lost:
        
            r5 = r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x0041, code lost:
        
            r1 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0049, code lost:
        
            if (r0.toNextSibling() != false) goto L37;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static org.apache.xmlbeans.impl.store.Cur toXmlns(org.apache.xmlbeans.impl.store.Cur r4, int r5) {
            /*
                if (r5 < 0) goto L59
                org.apache.xmlbeans.impl.store.Cur r0 = r4.tempCur()
                r1 = 0
                boolean r2 = r4.isElem()
                r3 = -2
                if (r2 != 0) goto L28
                int r2 = r4.kind()
                if (r2 != r3) goto L15
                goto L28
            L15:
                boolean r2 = r4.isXmlns()
                if (r2 == 0) goto L22
                if (r5 != 0) goto L1f
                r2 = 1
                goto L20
            L1f:
                r2 = 0
            L20:
                r1 = r2
                goto L4b
            L22:
                java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
                r2.<init>()
                throw r2
            L28:
                int r2 = r4.kind()
                if (r2 != r3) goto L31
                r0.toParent()
            L31:
                boolean r2 = r0.toFirstAttr()
                if (r2 == 0) goto L4b
            L37:
                boolean r2 = r0.isXmlns()
                if (r2 == 0) goto L45
                int r2 = r5 + (-1)
                if (r5 != 0) goto L44
                r1 = 1
                r5 = r2
                goto L4b
            L44:
                r5 = r2
            L45:
                boolean r2 = r0.toNextSibling()
                if (r2 != 0) goto L37
            L4b:
                if (r1 == 0) goto L4e
                return r0
            L4e:
                r0.release()
                java.lang.IndexOutOfBoundsException r2 = new java.lang.IndexOutOfBoundsException
                java.lang.String r3 = "Namespace index is too large"
                r2.<init>(r3)
                throw r2
            L59:
                java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException
                java.lang.String r1 = "Namespace index is negative"
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Jsr173.XMLStreamReaderForNode.toXmlns(org.apache.xmlbeans.impl.store.Cur, int):org.apache.xmlbeans.impl.store.Cur");
        }

        public String getNamespacePrefix(int index) {
            Cur ca = toXmlns(this._cur, index);
            String prefix = ca.getXmlnsPrefix();
            ca.release();
            return prefix;
        }

        public String getNamespaceURI(int index) {
            Cur ca = toXmlns(this._cur, index);
            String uri = ca.getXmlnsUri();
            ca.release();
            return uri;
        }

        private void fetchChars() {
            Cur cText;
            if (!this._textFetched) {
                int k = this._cur.kind();
                if (k == 4) {
                    cText = this._cur.tempCur();
                    cText.next();
                } else if (k == 0) {
                    cText = this._cur;
                } else {
                    throw new IllegalStateException();
                }
                Object src = cText.getChars(-1);
                ensureCharBufLen(cText._cchSrc);
                char[] cArr = this._chars;
                this._offChars = 0;
                int i = cText._offSrc;
                int i2 = cText._cchSrc;
                this._cchChars = i2;
                CharUtil.getChars(cArr, 0, src, i, i2);
                if (cText != this._cur) {
                    cText.release();
                }
                this._textFetched = true;
            }
        }

        private void ensureCharBufLen(int cch) {
            if (this._chars == null || this._chars.length < cch) {
                int l = 256;
                while (l < cch) {
                    l *= 2;
                }
                this._chars = new char[l];
            }
        }

        public char[] getTextCharacters() {
            checkChanged();
            fetchChars();
            return this._chars;
        }

        public int getTextStart() {
            checkChanged();
            fetchChars();
            return this._offChars;
        }

        public int getTextLength() {
            checkChanged();
            fetchChars();
            return this._cchChars;
        }

        public int getTextCharacters(int sourceStart, char[] target, int targetStart, int length) throws XMLStreamException {
            Cur cText;
            if (length < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (targetStart < 0 || targetStart >= target.length) {
                throw new IndexOutOfBoundsException();
            }
            if (targetStart + length > target.length) {
                throw new IndexOutOfBoundsException();
            }
            if (!this._srcFetched) {
                int k = this._cur.kind();
                if (k == 4) {
                    cText = this._cur.tempCur();
                    cText.next();
                } else if (k == 0) {
                    cText = this._cur;
                } else {
                    throw new IllegalStateException();
                }
                this._src = cText.getChars(-1);
                this._offSrc = cText._offSrc;
                this._cchSrc = cText._cchSrc;
                if (cText != this._cur) {
                    cText.release();
                }
                this._srcFetched = true;
            }
            if (sourceStart > this._cchSrc) {
                throw new IndexOutOfBoundsException();
            }
            if (sourceStart + length > this._cchSrc) {
                length = this._cchSrc - sourceStart;
            }
            CharUtil.getChars(target, targetStart, this._src, this._offSrc, length);
            return length;
        }

        public boolean hasText() {
            int k = this._cur.kind();
            return k == 4 || k == 0;
        }

        public boolean hasName() {
            int k = this._cur.kind();
            return k == 2 || k == -2;
        }

        public QName getName() {
            if (!hasName()) {
                throw new IllegalStateException();
            }
            return this._cur.getName();
        }

        public String getNamespaceURI() {
            return getName().getNamespaceURI();
        }

        public String getLocalName() {
            return getName().getLocalPart();
        }

        public String getPrefix() {
            return getName().getPrefix();
        }

        public String getPITarget() {
            if (this._cur.kind() == 5) {
                return this._cur.getName().getLocalPart();
            }
            return null;
        }

        public String getPIData() {
            if (this._cur.kind() == 5) {
                return this._cur.getValueAsString();
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static abstract class XMLStreamReaderBase implements XMLStreamReader, NamespaceContext, Location {
        private Locale _locale;
        String _uri;
        private long _version;
        int _line = -1;
        int _column = -1;
        int _offset = -1;

        protected abstract Cur getStreamCur();

        XMLStreamReaderBase(Cur c) {
            this._locale = c._locale;
            this._version = this._locale.version();
        }

        protected final void checkChanged() {
            if (this._version != this._locale.version()) {
                throw new ConcurrentModificationException("Document changed while streaming");
            }
        }

        public void close() throws XMLStreamException {
            checkChanged();
        }

        public boolean isWhiteSpace() {
            checkChanged();
            String s = getText();
            return this._locale.getCharUtil().isWhiteSpace(s, 0, s.length());
        }

        public Location getLocation() {
            checkChanged();
            Cur c = getStreamCur();
            XmlLineNumber ln = (XmlLineNumber) c.getBookmark(XmlLineNumber.class);
            this._uri = null;
            if (ln != null) {
                this._line = ln.getLine();
                this._column = ln.getColumn();
                this._offset = ln.getOffset();
            } else {
                this._line = -1;
                this._column = -1;
                this._offset = -1;
            }
            return this;
        }

        public Object getProperty(String name) {
            checkChanged();
            if (name == null) {
                throw new IllegalArgumentException("Property name is null");
            }
            return null;
        }

        public String getCharacterEncodingScheme() {
            checkChanged();
            XmlDocumentProperties props = Locale.getDocProps(getStreamCur(), false);
            if (props == null) {
                return null;
            }
            return props.getEncoding();
        }

        public String getEncoding() {
            return null;
        }

        public String getVersion() {
            checkChanged();
            XmlDocumentProperties props = Locale.getDocProps(getStreamCur(), false);
            if (props == null) {
                return null;
            }
            return props.getVersion();
        }

        public boolean isStandalone() {
            checkChanged();
            XmlDocumentProperties props = Locale.getDocProps(getStreamCur(), false);
            if (props == null) {
                return false;
            }
            return props.getStandalone();
        }

        public boolean standaloneSet() {
            checkChanged();
            return false;
        }

        public void require(int type, String namespaceURI, String localName) throws XMLStreamException {
            checkChanged();
            if (type != getEventType()) {
                throw new XMLStreamException();
            }
            if (namespaceURI != null && !getNamespaceURI().equals(namespaceURI)) {
                throw new XMLStreamException();
            }
            if (localName != null && !getLocalName().equals(localName)) {
                throw new XMLStreamException();
            }
        }

        public int getCharacterOffset() {
            return this._offset;
        }

        public int getColumnNumber() {
            return this._column;
        }

        public int getLineNumber() {
            return this._line;
        }

        public String getLocationURI() {
            return this._uri;
        }

        public String getPublicId() {
            return null;
        }

        public String getSystemId() {
            return null;
        }

        public NamespaceContext getNamespaceContext() {
            throw new RuntimeException("This version of getNamespaceContext should not be called");
        }

        @Override // javax.xml.namespace.NamespaceContext
        public String getNamespaceURI(String prefix) {
            checkChanged();
            Cur c = getStreamCur();
            c.push();
            if (!c.isContainer()) {
                c.toParent();
            }
            String ns = c.namespaceForPrefix(prefix, true);
            c.pop();
            return ns;
        }

        @Override // javax.xml.namespace.NamespaceContext
        public String getPrefix(String namespaceURI) {
            checkChanged();
            Cur c = getStreamCur();
            c.push();
            if (!c.isContainer()) {
                c.toParent();
            }
            String prefix = c.prefixForNamespace(namespaceURI, null, false);
            c.pop();
            return prefix;
        }

        @Override // javax.xml.namespace.NamespaceContext
        public Iterator<String> getPrefixes(String namespaceURI) {
            checkChanged();
            HashMap<String, String> map = new HashMap<>();
            map.put(namespaceURI, getPrefix(namespaceURI));
            return map.values().iterator();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class XMLStreamReaderForString extends XMLStreamReaderBase {
        private int _cch;
        private Cur _cur;
        private int _off;
        private Object _src;

        XMLStreamReaderForString(Cur c, Object src, int off, int cch) {
            super(c);
            this._src = src;
            this._off = off;
            this._cch = cch;
            this._cur = c;
        }

        @Override // org.apache.xmlbeans.impl.store.Jsr173.XMLStreamReaderBase
        protected Cur getStreamCur() {
            return this._cur;
        }

        public String getText() {
            checkChanged();
            return CharUtil.getString(this._src, this._off, this._cch);
        }

        public char[] getTextCharacters() {
            checkChanged();
            char[] chars = new char[this._cch];
            CharUtil.getChars(chars, 0, this._src, this._off, this._cch);
            return chars;
        }

        public int getTextStart() {
            checkChanged();
            return this._off;
        }

        public int getTextLength() {
            checkChanged();
            return this._cch;
        }

        public int getTextCharacters(int sourceStart, char[] target, int targetStart, int length) {
            checkChanged();
            if (length < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (sourceStart > this._cch) {
                throw new IndexOutOfBoundsException();
            }
            if (sourceStart + length > this._cch) {
                length = this._cch - sourceStart;
            }
            CharUtil.getChars(target, targetStart, this._src, this._off + sourceStart, length);
            return length;
        }

        public int getEventType() {
            checkChanged();
            return 4;
        }

        public boolean hasName() {
            checkChanged();
            return false;
        }

        public boolean hasNext() {
            checkChanged();
            return false;
        }

        public boolean hasText() {
            checkChanged();
            return true;
        }

        public boolean isCharacters() {
            checkChanged();
            return true;
        }

        public boolean isEndElement() {
            checkChanged();
            return false;
        }

        public boolean isStartElement() {
            checkChanged();
            return false;
        }

        public int getAttributeCount() {
            throw new IllegalStateException();
        }

        public String getAttributeLocalName(int index) {
            throw new IllegalStateException();
        }

        public QName getAttributeName(int index) {
            throw new IllegalStateException();
        }

        public String getAttributeNamespace(int index) {
            throw new IllegalStateException();
        }

        public String getAttributePrefix(int index) {
            throw new IllegalStateException();
        }

        public String getAttributeType(int index) {
            throw new IllegalStateException();
        }

        public String getAttributeValue(int index) {
            throw new IllegalStateException();
        }

        public String getAttributeValue(String namespaceURI, String localName) {
            throw new IllegalStateException();
        }

        public String getElementText() {
            throw new IllegalStateException();
        }

        public String getLocalName() {
            throw new IllegalStateException();
        }

        public QName getName() {
            throw new IllegalStateException();
        }

        public int getNamespaceCount() {
            throw new IllegalStateException();
        }

        public String getNamespacePrefix(int index) {
            throw new IllegalStateException();
        }

        public String getNamespaceURI(int index) {
            throw new IllegalStateException();
        }

        public String getNamespaceURI() {
            throw new IllegalStateException();
        }

        public String getPIData() {
            throw new IllegalStateException();
        }

        public String getPITarget() {
            throw new IllegalStateException();
        }

        public String getPrefix() {
            throw new IllegalStateException();
        }

        public boolean isAttributeSpecified(int index) {
            throw new IllegalStateException();
        }

        public int next() {
            throw new IllegalStateException();
        }

        public int nextTag() {
            throw new IllegalStateException();
        }

        @Override // org.apache.xmlbeans.impl.store.Jsr173.XMLStreamReaderBase
        public String getPublicId() {
            throw new IllegalStateException();
        }

        @Override // org.apache.xmlbeans.impl.store.Jsr173.XMLStreamReaderBase
        public String getSystemId() {
            throw new IllegalStateException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static abstract class Jsr173GateWay {
        Locale _l;
        XMLStreamReaderBase _xs;

        public Jsr173GateWay(Locale l, XMLStreamReaderBase xs) {
            this._l = l;
            this._xs = xs;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class SyncedJsr173 extends Jsr173GateWay implements XMLStreamReader, Location, NamespaceContext {
        public SyncedJsr173(Locale l, XMLStreamReaderBase xs) {
            super(l, xs);
        }

        public Object getProperty(String name) {
            Object property;
            synchronized (this._l) {
                this._l.enter();
                try {
                    property = this._xs.getProperty(name);
                } finally {
                    this._l.exit();
                }
            }
            return property;
        }

        public int next() throws XMLStreamException {
            int next;
            synchronized (this._l) {
                this._l.enter();
                try {
                    next = this._xs.next();
                } finally {
                    this._l.exit();
                }
            }
            return next;
        }

        public void require(int type, String namespaceURI, String localName) throws XMLStreamException {
            synchronized (this._l) {
                this._l.enter();
                try {
                    this._xs.require(type, namespaceURI, localName);
                } finally {
                    this._l.exit();
                }
            }
        }

        public String getElementText() throws XMLStreamException {
            String elementText;
            synchronized (this._l) {
                this._l.enter();
                try {
                    elementText = this._xs.getElementText();
                } finally {
                    this._l.exit();
                }
            }
            return elementText;
        }

        public int nextTag() throws XMLStreamException {
            int nextTag;
            synchronized (this._l) {
                this._l.enter();
                try {
                    nextTag = this._xs.nextTag();
                } finally {
                    this._l.exit();
                }
            }
            return nextTag;
        }

        public boolean hasNext() throws XMLStreamException {
            boolean hasNext;
            synchronized (this._l) {
                this._l.enter();
                try {
                    hasNext = this._xs.hasNext();
                } finally {
                    this._l.exit();
                }
            }
            return hasNext;
        }

        public void close() throws XMLStreamException {
            synchronized (this._l) {
                this._l.enter();
                try {
                    this._xs.close();
                } finally {
                    this._l.exit();
                }
            }
        }

        @Override // javax.xml.namespace.NamespaceContext
        public String getNamespaceURI(String prefix) {
            String namespaceURI;
            synchronized (this._l) {
                this._l.enter();
                try {
                    namespaceURI = this._xs.getNamespaceURI(prefix);
                } finally {
                    this._l.exit();
                }
            }
            return namespaceURI;
        }

        public boolean isStartElement() {
            boolean isStartElement;
            synchronized (this._l) {
                this._l.enter();
                try {
                    isStartElement = this._xs.isStartElement();
                } finally {
                    this._l.exit();
                }
            }
            return isStartElement;
        }

        public boolean isEndElement() {
            boolean isEndElement;
            synchronized (this._l) {
                this._l.enter();
                try {
                    isEndElement = this._xs.isEndElement();
                } finally {
                    this._l.exit();
                }
            }
            return isEndElement;
        }

        public boolean isCharacters() {
            boolean isCharacters;
            synchronized (this._l) {
                this._l.enter();
                try {
                    isCharacters = this._xs.isCharacters();
                } finally {
                    this._l.exit();
                }
            }
            return isCharacters;
        }

        public boolean isWhiteSpace() {
            boolean isWhiteSpace;
            synchronized (this._l) {
                this._l.enter();
                try {
                    isWhiteSpace = this._xs.isWhiteSpace();
                } finally {
                    this._l.exit();
                }
            }
            return isWhiteSpace;
        }

        public String getAttributeValue(String namespaceURI, String localName) {
            String attributeValue;
            synchronized (this._l) {
                this._l.enter();
                try {
                    attributeValue = this._xs.getAttributeValue(namespaceURI, localName);
                } finally {
                    this._l.exit();
                }
            }
            return attributeValue;
        }

        public int getAttributeCount() {
            int attributeCount;
            synchronized (this._l) {
                this._l.enter();
                try {
                    attributeCount = this._xs.getAttributeCount();
                } finally {
                    this._l.exit();
                }
            }
            return attributeCount;
        }

        public QName getAttributeName(int index) {
            QName attributeName;
            synchronized (this._l) {
                this._l.enter();
                try {
                    attributeName = this._xs.getAttributeName(index);
                } finally {
                    this._l.exit();
                }
            }
            return attributeName;
        }

        public String getAttributeNamespace(int index) {
            String attributeNamespace;
            synchronized (this._l) {
                this._l.enter();
                try {
                    attributeNamespace = this._xs.getAttributeNamespace(index);
                } finally {
                    this._l.exit();
                }
            }
            return attributeNamespace;
        }

        public String getAttributeLocalName(int index) {
            String attributeLocalName;
            synchronized (this._l) {
                this._l.enter();
                try {
                    attributeLocalName = this._xs.getAttributeLocalName(index);
                } finally {
                    this._l.exit();
                }
            }
            return attributeLocalName;
        }

        public String getAttributePrefix(int index) {
            String attributePrefix;
            synchronized (this._l) {
                this._l.enter();
                try {
                    attributePrefix = this._xs.getAttributePrefix(index);
                } finally {
                    this._l.exit();
                }
            }
            return attributePrefix;
        }

        public String getAttributeType(int index) {
            String attributeType;
            synchronized (this._l) {
                this._l.enter();
                try {
                    attributeType = this._xs.getAttributeType(index);
                } finally {
                    this._l.exit();
                }
            }
            return attributeType;
        }

        public String getAttributeValue(int index) {
            String attributeValue;
            synchronized (this._l) {
                this._l.enter();
                try {
                    attributeValue = this._xs.getAttributeValue(index);
                } finally {
                    this._l.exit();
                }
            }
            return attributeValue;
        }

        public boolean isAttributeSpecified(int index) {
            boolean isAttributeSpecified;
            synchronized (this._l) {
                this._l.enter();
                try {
                    isAttributeSpecified = this._xs.isAttributeSpecified(index);
                } finally {
                    this._l.exit();
                }
            }
            return isAttributeSpecified;
        }

        public int getNamespaceCount() {
            int namespaceCount;
            synchronized (this._l) {
                this._l.enter();
                try {
                    namespaceCount = this._xs.getNamespaceCount();
                } finally {
                    this._l.exit();
                }
            }
            return namespaceCount;
        }

        public String getNamespacePrefix(int index) {
            String namespacePrefix;
            synchronized (this._l) {
                this._l.enter();
                try {
                    namespacePrefix = this._xs.getNamespacePrefix(index);
                } finally {
                    this._l.exit();
                }
            }
            return namespacePrefix;
        }

        public String getNamespaceURI(int index) {
            String namespaceURI;
            synchronized (this._l) {
                this._l.enter();
                try {
                    namespaceURI = this._xs.getNamespaceURI(index);
                } finally {
                    this._l.exit();
                }
            }
            return namespaceURI;
        }

        public NamespaceContext getNamespaceContext() {
            return this;
        }

        public int getEventType() {
            int eventType;
            synchronized (this._l) {
                this._l.enter();
                try {
                    eventType = this._xs.getEventType();
                } finally {
                    this._l.exit();
                }
            }
            return eventType;
        }

        public String getText() {
            String text;
            synchronized (this._l) {
                this._l.enter();
                try {
                    text = this._xs.getText();
                } finally {
                    this._l.exit();
                }
            }
            return text;
        }

        public char[] getTextCharacters() {
            char[] textCharacters;
            synchronized (this._l) {
                this._l.enter();
                try {
                    textCharacters = this._xs.getTextCharacters();
                } finally {
                    this._l.exit();
                }
            }
            return textCharacters;
        }

        public int getTextCharacters(int sourceStart, char[] target, int targetStart, int length) throws XMLStreamException {
            int textCharacters;
            synchronized (this._l) {
                this._l.enter();
                try {
                    textCharacters = this._xs.getTextCharacters(sourceStart, target, targetStart, length);
                } finally {
                    this._l.exit();
                }
            }
            return textCharacters;
        }

        public int getTextStart() {
            int textStart;
            synchronized (this._l) {
                this._l.enter();
                try {
                    textStart = this._xs.getTextStart();
                } finally {
                    this._l.exit();
                }
            }
            return textStart;
        }

        public int getTextLength() {
            int textLength;
            synchronized (this._l) {
                this._l.enter();
                try {
                    textLength = this._xs.getTextLength();
                } finally {
                    this._l.exit();
                }
            }
            return textLength;
        }

        public String getEncoding() {
            String encoding;
            synchronized (this._l) {
                this._l.enter();
                try {
                    encoding = this._xs.getEncoding();
                } finally {
                    this._l.exit();
                }
            }
            return encoding;
        }

        public boolean hasText() {
            boolean hasText;
            synchronized (this._l) {
                this._l.enter();
                try {
                    hasText = this._xs.hasText();
                } finally {
                    this._l.exit();
                }
            }
            return hasText;
        }

        public Location getLocation() {
            Location location;
            synchronized (this._l) {
                this._l.enter();
                try {
                    location = this._xs.getLocation();
                } finally {
                    this._l.exit();
                }
            }
            return location;
        }

        public QName getName() {
            QName name;
            synchronized (this._l) {
                this._l.enter();
                try {
                    name = this._xs.getName();
                } finally {
                    this._l.exit();
                }
            }
            return name;
        }

        public String getLocalName() {
            String localName;
            synchronized (this._l) {
                this._l.enter();
                try {
                    localName = this._xs.getLocalName();
                } finally {
                    this._l.exit();
                }
            }
            return localName;
        }

        public boolean hasName() {
            boolean hasName;
            synchronized (this._l) {
                this._l.enter();
                try {
                    hasName = this._xs.hasName();
                } finally {
                    this._l.exit();
                }
            }
            return hasName;
        }

        public String getNamespaceURI() {
            String namespaceURI;
            synchronized (this._l) {
                this._l.enter();
                try {
                    namespaceURI = this._xs.getNamespaceURI();
                } finally {
                    this._l.exit();
                }
            }
            return namespaceURI;
        }

        public String getPrefix() {
            String prefix;
            synchronized (this._l) {
                this._l.enter();
                try {
                    prefix = this._xs.getPrefix();
                } finally {
                    this._l.exit();
                }
            }
            return prefix;
        }

        public String getVersion() {
            String version;
            synchronized (this._l) {
                this._l.enter();
                try {
                    version = this._xs.getVersion();
                } finally {
                    this._l.exit();
                }
            }
            return version;
        }

        public boolean isStandalone() {
            boolean isStandalone;
            synchronized (this._l) {
                this._l.enter();
                try {
                    isStandalone = this._xs.isStandalone();
                } finally {
                    this._l.exit();
                }
            }
            return isStandalone;
        }

        public boolean standaloneSet() {
            boolean standaloneSet;
            synchronized (this._l) {
                this._l.enter();
                try {
                    standaloneSet = this._xs.standaloneSet();
                } finally {
                    this._l.exit();
                }
            }
            return standaloneSet;
        }

        public String getCharacterEncodingScheme() {
            String characterEncodingScheme;
            synchronized (this._l) {
                this._l.enter();
                try {
                    characterEncodingScheme = this._xs.getCharacterEncodingScheme();
                } finally {
                    this._l.exit();
                }
            }
            return characterEncodingScheme;
        }

        public String getPITarget() {
            String pITarget;
            synchronized (this._l) {
                this._l.enter();
                try {
                    pITarget = this._xs.getPITarget();
                } finally {
                    this._l.exit();
                }
            }
            return pITarget;
        }

        public String getPIData() {
            String pIData;
            synchronized (this._l) {
                this._l.enter();
                try {
                    pIData = this._xs.getPIData();
                } finally {
                    this._l.exit();
                }
            }
            return pIData;
        }

        @Override // javax.xml.namespace.NamespaceContext
        public String getPrefix(String namespaceURI) {
            String prefix;
            synchronized (this._l) {
                this._l.enter();
                try {
                    prefix = this._xs.getPrefix(namespaceURI);
                } finally {
                    this._l.exit();
                }
            }
            return prefix;
        }

        @Override // javax.xml.namespace.NamespaceContext
        public Iterator<String> getPrefixes(String namespaceURI) {
            Iterator<String> prefixes;
            synchronized (this._l) {
                this._l.enter();
                try {
                    prefixes = this._xs.getPrefixes(namespaceURI);
                } finally {
                    this._l.exit();
                }
            }
            return prefixes;
        }

        public int getCharacterOffset() {
            int characterOffset;
            synchronized (this._l) {
                this._l.enter();
                try {
                    characterOffset = this._xs.getCharacterOffset();
                } finally {
                    this._l.exit();
                }
            }
            return characterOffset;
        }

        public int getColumnNumber() {
            int columnNumber;
            synchronized (this._l) {
                this._l.enter();
                try {
                    columnNumber = this._xs.getColumnNumber();
                } finally {
                    this._l.exit();
                }
            }
            return columnNumber;
        }

        public int getLineNumber() {
            int lineNumber;
            synchronized (this._l) {
                this._l.enter();
                try {
                    lineNumber = this._xs.getLineNumber();
                } finally {
                    this._l.exit();
                }
            }
            return lineNumber;
        }

        public String getLocationURI() {
            String locationURI;
            synchronized (this._l) {
                this._l.enter();
                try {
                    locationURI = this._xs.getLocationURI();
                } finally {
                    this._l.exit();
                }
            }
            return locationURI;
        }

        public String getPublicId() {
            String publicId;
            synchronized (this._l) {
                this._l.enter();
                try {
                    publicId = this._xs.getPublicId();
                } finally {
                    this._l.exit();
                }
            }
            return publicId;
        }

        public String getSystemId() {
            String systemId;
            synchronized (this._l) {
                this._l.enter();
                try {
                    systemId = this._xs.getSystemId();
                } finally {
                    this._l.exit();
                }
            }
            return systemId;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class UnsyncedJsr173 extends Jsr173GateWay implements XMLStreamReader, Location, NamespaceContext {
        public UnsyncedJsr173(Locale l, XMLStreamReaderBase xs) {
            super(l, xs);
        }

        public Object getProperty(String name) {
            try {
                this._l.enter();
                return this._xs.getProperty(name);
            } finally {
                this._l.exit();
            }
        }

        public int next() throws XMLStreamException {
            try {
                this._l.enter();
                return this._xs.next();
            } finally {
                this._l.exit();
            }
        }

        public void require(int type, String namespaceURI, String localName) throws XMLStreamException {
            try {
                this._l.enter();
                this._xs.require(type, namespaceURI, localName);
            } finally {
                this._l.exit();
            }
        }

        public String getElementText() throws XMLStreamException {
            try {
                this._l.enter();
                return this._xs.getElementText();
            } finally {
                this._l.exit();
            }
        }

        public int nextTag() throws XMLStreamException {
            try {
                this._l.enter();
                return this._xs.nextTag();
            } finally {
                this._l.exit();
            }
        }

        public boolean hasNext() throws XMLStreamException {
            try {
                this._l.enter();
                return this._xs.hasNext();
            } finally {
                this._l.exit();
            }
        }

        public void close() throws XMLStreamException {
            try {
                this._l.enter();
                this._xs.close();
            } finally {
                this._l.exit();
            }
        }

        @Override // javax.xml.namespace.NamespaceContext
        public String getNamespaceURI(String prefix) {
            try {
                this._l.enter();
                return this._xs.getNamespaceURI(prefix);
            } finally {
                this._l.exit();
            }
        }

        public boolean isStartElement() {
            try {
                this._l.enter();
                return this._xs.isStartElement();
            } finally {
                this._l.exit();
            }
        }

        public boolean isEndElement() {
            try {
                this._l.enter();
                return this._xs.isEndElement();
            } finally {
                this._l.exit();
            }
        }

        public boolean isCharacters() {
            try {
                this._l.enter();
                return this._xs.isCharacters();
            } finally {
                this._l.exit();
            }
        }

        public boolean isWhiteSpace() {
            try {
                this._l.enter();
                return this._xs.isWhiteSpace();
            } finally {
                this._l.exit();
            }
        }

        public String getAttributeValue(String namespaceURI, String localName) {
            try {
                this._l.enter();
                return this._xs.getAttributeValue(namespaceURI, localName);
            } finally {
                this._l.exit();
            }
        }

        public int getAttributeCount() {
            try {
                this._l.enter();
                return this._xs.getAttributeCount();
            } finally {
                this._l.exit();
            }
        }

        public QName getAttributeName(int index) {
            try {
                this._l.enter();
                return this._xs.getAttributeName(index);
            } finally {
                this._l.exit();
            }
        }

        public String getAttributeNamespace(int index) {
            try {
                this._l.enter();
                return this._xs.getAttributeNamespace(index);
            } finally {
                this._l.exit();
            }
        }

        public String getAttributeLocalName(int index) {
            try {
                this._l.enter();
                return this._xs.getAttributeLocalName(index);
            } finally {
                this._l.exit();
            }
        }

        public String getAttributePrefix(int index) {
            try {
                this._l.enter();
                return this._xs.getAttributePrefix(index);
            } finally {
                this._l.exit();
            }
        }

        public String getAttributeType(int index) {
            try {
                this._l.enter();
                return this._xs.getAttributeType(index);
            } finally {
                this._l.exit();
            }
        }

        public String getAttributeValue(int index) {
            try {
                this._l.enter();
                return this._xs.getAttributeValue(index);
            } finally {
                this._l.exit();
            }
        }

        public boolean isAttributeSpecified(int index) {
            try {
                this._l.enter();
                return this._xs.isAttributeSpecified(index);
            } finally {
                this._l.exit();
            }
        }

        public int getNamespaceCount() {
            try {
                this._l.enter();
                return this._xs.getNamespaceCount();
            } finally {
                this._l.exit();
            }
        }

        public String getNamespacePrefix(int index) {
            try {
                this._l.enter();
                return this._xs.getNamespacePrefix(index);
            } finally {
                this._l.exit();
            }
        }

        public String getNamespaceURI(int index) {
            try {
                this._l.enter();
                return this._xs.getNamespaceURI(index);
            } finally {
                this._l.exit();
            }
        }

        public NamespaceContext getNamespaceContext() {
            return this;
        }

        public int getEventType() {
            try {
                this._l.enter();
                return this._xs.getEventType();
            } finally {
                this._l.exit();
            }
        }

        public String getText() {
            try {
                this._l.enter();
                return this._xs.getText();
            } finally {
                this._l.exit();
            }
        }

        public char[] getTextCharacters() {
            try {
                this._l.enter();
                return this._xs.getTextCharacters();
            } finally {
                this._l.exit();
            }
        }

        public int getTextCharacters(int sourceStart, char[] target, int targetStart, int length) throws XMLStreamException {
            try {
                this._l.enter();
                return this._xs.getTextCharacters(sourceStart, target, targetStart, length);
            } finally {
                this._l.exit();
            }
        }

        public int getTextStart() {
            try {
                this._l.enter();
                return this._xs.getTextStart();
            } finally {
                this._l.exit();
            }
        }

        public int getTextLength() {
            try {
                this._l.enter();
                return this._xs.getTextLength();
            } finally {
                this._l.exit();
            }
        }

        public String getEncoding() {
            try {
                this._l.enter();
                return this._xs.getEncoding();
            } finally {
                this._l.exit();
            }
        }

        public boolean hasText() {
            try {
                this._l.enter();
                return this._xs.hasText();
            } finally {
                this._l.exit();
            }
        }

        public Location getLocation() {
            try {
                this._l.enter();
                return this._xs.getLocation();
            } finally {
                this._l.exit();
            }
        }

        public QName getName() {
            try {
                this._l.enter();
                return this._xs.getName();
            } finally {
                this._l.exit();
            }
        }

        public String getLocalName() {
            try {
                this._l.enter();
                return this._xs.getLocalName();
            } finally {
                this._l.exit();
            }
        }

        public boolean hasName() {
            try {
                this._l.enter();
                return this._xs.hasName();
            } finally {
                this._l.exit();
            }
        }

        public String getNamespaceURI() {
            try {
                this._l.enter();
                return this._xs.getNamespaceURI();
            } finally {
                this._l.exit();
            }
        }

        public String getPrefix() {
            try {
                this._l.enter();
                return this._xs.getPrefix();
            } finally {
                this._l.exit();
            }
        }

        public String getVersion() {
            try {
                this._l.enter();
                return this._xs.getVersion();
            } finally {
                this._l.exit();
            }
        }

        public boolean isStandalone() {
            try {
                this._l.enter();
                return this._xs.isStandalone();
            } finally {
                this._l.exit();
            }
        }

        public boolean standaloneSet() {
            try {
                this._l.enter();
                return this._xs.standaloneSet();
            } finally {
                this._l.exit();
            }
        }

        public String getCharacterEncodingScheme() {
            try {
                this._l.enter();
                return this._xs.getCharacterEncodingScheme();
            } finally {
                this._l.exit();
            }
        }

        public String getPITarget() {
            try {
                this._l.enter();
                return this._xs.getPITarget();
            } finally {
                this._l.exit();
            }
        }

        public String getPIData() {
            try {
                this._l.enter();
                return this._xs.getPIData();
            } finally {
                this._l.exit();
            }
        }

        @Override // javax.xml.namespace.NamespaceContext
        public String getPrefix(String namespaceURI) {
            try {
                this._l.enter();
                return this._xs.getPrefix(namespaceURI);
            } finally {
                this._l.exit();
            }
        }

        @Override // javax.xml.namespace.NamespaceContext
        public Iterator<String> getPrefixes(String namespaceURI) {
            try {
                this._l.enter();
                return this._xs.getPrefixes(namespaceURI);
            } finally {
                this._l.exit();
            }
        }

        public int getCharacterOffset() {
            try {
                this._l.enter();
                return this._xs.getCharacterOffset();
            } finally {
                this._l.exit();
            }
        }

        public int getColumnNumber() {
            try {
                this._l.enter();
                return this._xs.getColumnNumber();
            } finally {
                this._l.exit();
            }
        }

        public int getLineNumber() {
            int lineNumber;
            synchronized (this._l) {
                this._l.enter();
                try {
                    lineNumber = this._xs.getLineNumber();
                } finally {
                    this._l.exit();
                }
            }
            return lineNumber;
        }

        public String getLocationURI() {
            String locationURI;
            synchronized (this._l) {
                this._l.enter();
                try {
                    locationURI = this._xs.getLocationURI();
                } finally {
                    this._l.exit();
                }
            }
            return locationURI;
        }

        public String getPublicId() {
            String publicId;
            synchronized (this._l) {
                this._l.enter();
                try {
                    publicId = this._xs.getPublicId();
                } finally {
                    this._l.exit();
                }
            }
            return publicId;
        }

        public String getSystemId() {
            String systemId;
            synchronized (this._l) {
                this._l.enter();
                try {
                    systemId = this._xs.getSystemId();
                } finally {
                    this._l.exit();
                }
            }
            return systemId;
        }
    }
}
