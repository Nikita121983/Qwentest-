package org.apache.xmlbeans.impl.store;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlDocumentProperties;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.GlobalLock;
import org.apache.xmlbeans.impl.common.XMLChar;
import org.apache.xmlbeans.impl.store.Locale;
import org.apache.xmlbeans.impl.store.Saver;
import org.apache.xmlbeans.impl.xpath.XPathEngine;
import org.apache.xmlbeans.impl.xpath.XPathFactory;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

/* loaded from: classes11.dex */
public final class Cursor implements XmlCursor, Locale.ChangeListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int ATTR = 3;
    static final int COMMENT = 4;
    private static final int COPY_CHARS = 5;
    private static final int COPY_XML = 1;
    private static final int COPY_XML_CONTENTS = 3;
    static final int ELEM = 2;
    private static final int MOVE_CHARS = 4;
    private static final int MOVE_XML = 0;
    private static final int MOVE_XML_CONTENTS = 2;
    static final int PROCINST = 5;
    static final int ROOT = 1;
    static final int TEXT = 0;
    private Cur _cur;
    private int _currentSelection;
    private Locale.ChangeListener _nextChangeListener;
    private XPathEngine _pathEngine;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public interface WrapIOEx {
        void run() throws IOException;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public interface WrapSAXEx {
        void run() throws SAXException;
    }

    Cursor(Xobj x, int p) {
        this._cur = x._locale.weakCur(this);
        this._cur.moveTo(x, p);
        this._currentSelection = -1;
    }

    public Cursor(Cur c) {
        this(c._xobj, c._pos);
    }

    private static boolean isValid(Cur c) {
        int pk;
        if (c.kind() <= 0) {
            c.push();
            if (c.toParentRaw() && ((pk = c.kind()) == 4 || pk == 5 || pk == 3)) {
                return false;
            }
            c.pop();
            return true;
        }
        return true;
    }

    private boolean isValid() {
        return isValid(this._cur);
    }

    Locale locale() {
        return this._cur._locale;
    }

    Cur tempCur() {
        return this._cur.tempCur();
    }

    public void dump(PrintStream o) {
        this._cur.dump(o);
    }

    static void validateLocalName(QName name) {
        if (name == null) {
            throw new IllegalArgumentException("QName is null");
        }
        validateLocalName(name.getLocalPart());
    }

    static void validateLocalName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name is null");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name is empty");
        }
        if (!XMLChar.isValidNCName(name)) {
            throw new IllegalArgumentException("Name is not valid");
        }
    }

    static void validatePrefix(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Prefix is null");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Prefix is empty");
        }
        if (Locale.beginsWithXml(name)) {
            throw new IllegalArgumentException("Prefix begins with 'xml'");
        }
        if (!XMLChar.isValidNCName(name)) {
            throw new IllegalArgumentException("Prefix is not valid");
        }
    }

    private static void complain(String msg) {
        throw new IllegalArgumentException(msg);
    }

    private void checkInsertionValidity(Cur that) {
        int thatKind = that.kind();
        if (thatKind < 0) {
            complain("Can't move/copy/insert an end token.");
        }
        if (thatKind == 1) {
            complain("Can't move/copy/insert a whole document.");
        }
        int thisKind = this._cur.kind();
        if (thisKind == 1) {
            complain("Can't insert before the start of the document.");
        }
        if (thatKind == 3) {
            this._cur.push();
            this._cur.prevWithAttrs();
            int pk = this._cur.kind();
            this._cur.pop();
            if (pk != 2 && pk != 1 && pk != -3) {
                complain("Can only insert attributes before other attributes or after containers.");
            }
        }
        if (thisKind == 3 && thatKind != 3) {
            complain("Can only insert attributes before other attributes or after containers.");
        }
    }

    private void insertNode(Cur that, String text) {
        if (that.isRoot()) {
            throw new AssertionError();
        }
        if (!that.isNode()) {
            throw new AssertionError();
        }
        if (!isValid(that)) {
            throw new AssertionError();
        }
        if (!isValid()) {
            throw new AssertionError();
        }
        if (text != null && text.length() > 0) {
            that.next();
            that.insertString(text);
            that.toParent();
        }
        checkInsertionValidity(that);
        that.moveNode(this._cur);
        this._cur.toEnd();
        this._cur.nextWithAttrs();
    }

    public void _dispose() {
        this._cur.release();
        this._cur = null;
    }

    public XmlCursor _newCursor() {
        return new Cursor(this._cur);
    }

    public QName _getName() {
        switch (this._cur.kind()) {
            case 2:
            case 5:
                break;
            case 3:
                if (this._cur.isXmlns()) {
                    return this._cur._locale.makeQNameNoCheck(this._cur.getXmlnsUri(), this._cur.getXmlnsPrefix());
                }
                break;
            case 4:
            default:
                return null;
        }
        return this._cur.getName();
    }

    /* renamed from: _setName, reason: merged with bridge method [inline-methods] */
    public void m2666lambda$setName$25$orgapachexmlbeansimplstoreCursor(QName name) {
        if (name == null) {
            throw new IllegalArgumentException("Name is null");
        }
        switch (this._cur.kind()) {
            case 2:
            case 3:
                validateLocalName(name.getLocalPart());
                break;
            case 4:
            default:
                throw new IllegalStateException("Can set name on element, atrtribute and procinst only");
            case 5:
                validatePrefix(name.getLocalPart());
                if (name.getNamespaceURI().length() > 0) {
                    throw new IllegalArgumentException("Procinst name must have no URI");
                }
                if (name.getPrefix().length() > 0) {
                    throw new IllegalArgumentException("Procinst name must have no prefix");
                }
                break;
        }
        this._cur.setName(name);
    }

    public XmlCursor.TokenType _currentTokenType() {
        if (!isValid()) {
            throw new AssertionError();
        }
        switch (this._cur.kind()) {
            case -2:
                return XmlCursor.TokenType.END;
            case -1:
                return XmlCursor.TokenType.ENDDOC;
            case 0:
                return XmlCursor.TokenType.TEXT;
            case 1:
                return XmlCursor.TokenType.STARTDOC;
            case 2:
                return XmlCursor.TokenType.START;
            case 3:
                return this._cur.isXmlns() ? XmlCursor.TokenType.NAMESPACE : XmlCursor.TokenType.ATTR;
            case 4:
                return XmlCursor.TokenType.COMMENT;
            case 5:
                return XmlCursor.TokenType.PROCINST;
            default:
                throw new IllegalStateException();
        }
    }

    public boolean _isStartdoc() {
        if (!isValid()) {
            throw new AssertionError();
        }
        return this._cur.isRoot();
    }

    public boolean _isEnddoc() {
        if (!isValid()) {
            throw new AssertionError();
        }
        return this._cur.isEndRoot();
    }

    public boolean _isStart() {
        if (!isValid()) {
            throw new AssertionError();
        }
        return this._cur.isElem();
    }

    public boolean _isEnd() {
        if (!isValid()) {
            throw new AssertionError();
        }
        return this._cur.isEnd();
    }

    public boolean _isText() {
        if (!isValid()) {
            throw new AssertionError();
        }
        return this._cur.isText();
    }

    public boolean _isAttr() {
        if (!isValid()) {
            throw new AssertionError();
        }
        return this._cur.isNormalAttr();
    }

    public boolean _isNamespace() {
        if (!isValid()) {
            throw new AssertionError();
        }
        return this._cur.isXmlns();
    }

    public boolean _isComment() {
        if (!isValid()) {
            throw new AssertionError();
        }
        return this._cur.isComment();
    }

    public boolean _isProcinst() {
        if (!isValid()) {
            throw new AssertionError();
        }
        return this._cur.isProcinst();
    }

    public boolean _isContainer() {
        if (!isValid()) {
            throw new AssertionError();
        }
        return this._cur.isContainer();
    }

    public boolean _isFinish() {
        if (!isValid()) {
            throw new AssertionError();
        }
        return this._cur.isFinish();
    }

    public boolean _isAnyAttr() {
        if (!isValid()) {
            throw new AssertionError();
        }
        return this._cur.isAttr();
    }

    public XmlCursor.TokenType _toNextToken() {
        if (!isValid()) {
            throw new AssertionError();
        }
        switch (this._cur.kind()) {
            case 1:
            case 2:
                if (!this._cur.toFirstAttr()) {
                    this._cur.next();
                    break;
                }
                break;
            case 3:
                if (!this._cur.toNextSibling()) {
                    this._cur.toParent();
                    this._cur.next();
                    break;
                }
                break;
            case 4:
            case 5:
                this._cur.skip();
                break;
            default:
                if (!this._cur.next()) {
                    return XmlCursor.TokenType.NONE;
                }
                break;
        }
        return _currentTokenType();
    }

    public XmlCursor.TokenType _toPrevToken() {
        if (!isValid()) {
            throw new AssertionError();
        }
        boolean wasText = this._cur.isText();
        if (!this._cur.prev()) {
            if (!this._cur.isRoot() && !this._cur.isAttr()) {
                throw new AssertionError();
            }
            if (this._cur.isRoot()) {
                return XmlCursor.TokenType.NONE;
            }
            this._cur.toParent();
        } else {
            int k = this._cur.kind();
            if (k == -4 || k == -5 || k == -3) {
                this._cur.toParent();
            } else if (this._cur.isContainer()) {
                this._cur.toLastAttr();
            } else if (wasText && this._cur.isText()) {
                return _toPrevToken();
            }
        }
        return _currentTokenType();
    }

    public Object _monitor() {
        return this._cur._locale;
    }

    public boolean _toParent() {
        Cur c = this._cur.tempCur();
        if (!c.toParent()) {
            return false;
        }
        this._cur.moveToCur(c);
        c.release();
        return true;
    }

    /* loaded from: classes11.dex */
    private static final class ChangeStampImpl implements XmlCursor.ChangeStamp {
        private final Locale _locale;
        private final long _versionStamp;

        ChangeStampImpl(Locale l) {
            this._locale = l;
            this._versionStamp = this._locale.version();
        }

        @Override // org.apache.xmlbeans.XmlCursor.ChangeStamp
        public boolean hasChanged() {
            return this._versionStamp != this._locale.version();
        }
    }

    public XmlCursor.ChangeStamp _getDocChangeStamp() {
        return new ChangeStampImpl(this._cur._locale);
    }

    public XMLStreamReader _newXMLStreamReader() {
        return m2650x3f62dfe9(null);
    }

    public Node _newDomNode() {
        return m2647lambda$newDomNode$14$orgapachexmlbeansimplstoreCursor(null);
    }

    public InputStream _newInputStream() {
        return m2648lambda$newInputStream$12$orgapachexmlbeansimplstoreCursor(null);
    }

    public String _xmlText() {
        return m2685lambda$xmlText$11$orgapachexmlbeansimplstoreCursor(null);
    }

    public Reader _newReader() {
        return m2649lambda$newReader$13$orgapachexmlbeansimplstoreCursor(null);
    }

    /* renamed from: _save, reason: merged with bridge method [inline-methods] */
    public void m2660lambda$save$8$orgapachexmlbeansimplstoreCursor(File file) throws IOException {
        m2656lambda$save$16$orgapachexmlbeansimplstoreCursor(file, (XmlOptions) null);
    }

    /* renamed from: _save, reason: merged with bridge method [inline-methods] */
    public void m2661lambda$save$9$orgapachexmlbeansimplstoreCursor(OutputStream os) throws IOException {
        m2657lambda$save$17$orgapachexmlbeansimplstoreCursor(os, (XmlOptions) null);
    }

    /* renamed from: _save, reason: merged with bridge method [inline-methods] */
    public void m2654lambda$save$10$orgapachexmlbeansimplstoreCursor(Writer w) throws IOException {
        m2658lambda$save$18$orgapachexmlbeansimplstoreCursor(w, (XmlOptions) null);
    }

    /* renamed from: _save, reason: merged with bridge method [inline-methods] */
    public void m2659lambda$save$7$orgapachexmlbeansimplstoreCursor(ContentHandler ch, LexicalHandler lh) throws SAXException {
        m2655lambda$save$15$orgapachexmlbeansimplstoreCursor(ch, lh, null);
    }

    public XmlDocumentProperties _documentProperties() {
        return Locale.getDocProps(this._cur, true);
    }

    /* renamed from: _newXMLStreamReader, reason: merged with bridge method [inline-methods] */
    public XMLStreamReader m2650x3f62dfe9(XmlOptions options) {
        return Jsr173.newXmlStreamReader(this._cur, options);
    }

    /* renamed from: _xmlText, reason: merged with bridge method [inline-methods] */
    public String m2685lambda$xmlText$11$orgapachexmlbeansimplstoreCursor(XmlOptions options) {
        if (!isValid()) {
            throw new AssertionError();
        }
        return new Saver.TextSaver(this._cur, options, null).saveToString();
    }

    /* renamed from: _newInputStream, reason: merged with bridge method [inline-methods] */
    public InputStream m2648lambda$newInputStream$12$orgapachexmlbeansimplstoreCursor(XmlOptions options) {
        return new Saver.InputStreamSaver(this._cur, options);
    }

    /* renamed from: _newReader, reason: merged with bridge method [inline-methods] */
    public Reader m2649lambda$newReader$13$orgapachexmlbeansimplstoreCursor(XmlOptions options) {
        return new Saver.TextReader(this._cur, options);
    }

    /* renamed from: _save, reason: merged with bridge method [inline-methods] */
    public void m2655lambda$save$15$orgapachexmlbeansimplstoreCursor(ContentHandler ch, LexicalHandler lh, XmlOptions options) throws SAXException {
        new Saver.SaxSaver(this._cur, options, ch, lh);
    }

    /* renamed from: _save, reason: merged with bridge method [inline-methods] */
    public void m2656lambda$save$16$orgapachexmlbeansimplstoreCursor(File file, XmlOptions options) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("Null file specified");
        }
        OutputStream os = Files.newOutputStream(file.toPath(), new OpenOption[0]);
        try {
            m2657lambda$save$17$orgapachexmlbeansimplstoreCursor(os, options);
            if (os != null) {
                os.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (os != null) {
                    try {
                        os.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    /* renamed from: _save, reason: merged with bridge method [inline-methods] */
    public void m2657lambda$save$17$orgapachexmlbeansimplstoreCursor(OutputStream os, XmlOptions options) throws IOException {
        if (os == null) {
            throw new IllegalArgumentException("Null OutputStream specified");
        }
        InputStream is = m2648lambda$newInputStream$12$orgapachexmlbeansimplstoreCursor(options);
        try {
            byte[] bytes = new byte[8192];
            while (true) {
                int n = is.read(bytes);
                if (n < 0) {
                    break;
                } else {
                    os.write(bytes, 0, n);
                }
            }
            if (is != null) {
                is.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (is != null) {
                    try {
                        is.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    /* renamed from: _save, reason: merged with bridge method [inline-methods] */
    public void m2658lambda$save$18$orgapachexmlbeansimplstoreCursor(Writer w, XmlOptions options) throws IOException {
        if (w == null) {
            throw new IllegalArgumentException("Null Writer specified");
        }
        if (options != null && options.isSaveOptimizeForSpeed()) {
            Saver.OptimizedForSpeedSaver.save(this._cur, w);
            return;
        }
        Reader r = m2649lambda$newReader$13$orgapachexmlbeansimplstoreCursor(options);
        try {
            char[] chars = new char[8192];
            while (true) {
                int n = r.read(chars);
                if (n < 0) {
                    break;
                } else {
                    w.write(chars, 0, n);
                }
            }
            if (r != null) {
                r.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (r != null) {
                    try {
                        r.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public Node _getDomNode() {
        return (Node) this._cur.getDom();
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0015. Please report as an issue. */
    private boolean isDomFragment() {
        if (!isStartdoc()) {
            return true;
        }
        boolean seenElement = false;
        XmlCursor c = newCursor();
        try {
            int token = c.toNextToken().intValue();
            while (true) {
                switch (token) {
                    case 0:
                    case 2:
                        if (c != null) {
                            c.close();
                        }
                        return !seenElement;
                    case 1:
                        throw new AssertionError();
                    case 3:
                        if (seenElement) {
                            if (c != null) {
                                c.close();
                            }
                            return true;
                        }
                        seenElement = true;
                        int token2 = c.toEndToken().intValue();
                        token = token2;
                    case 4:
                    case 8:
                    case 9:
                        int token3 = c.toNextToken().intValue();
                        token = token3;
                    case 5:
                        if (!Locale.isWhiteSpace(c.getChars())) {
                            if (c != null) {
                                c.close();
                            }
                            return true;
                        }
                        int token4 = c.toNextToken().intValue();
                        token = token4;
                    case 6:
                    case 7:
                        if (c != null) {
                            c.close();
                        }
                        return true;
                }
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (c != null) {
                    try {
                        c.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    /* renamed from: _newDomNode, reason: merged with bridge method [inline-methods] */
    public Node m2647lambda$newDomNode$14$orgapachexmlbeansimplstoreCursor(XmlOptions options) {
        if (options != null && options.isSaveInner()) {
            options = new XmlOptions(options);
            options.setSaveInner(false);
        }
        return new DomSaver(this._cur, isDomFragment(), options).saveDom();
    }

    public boolean _toCursor(Cursor other) {
        if (this._cur._locale != other._cur._locale) {
            throw new AssertionError();
        }
        this._cur.moveToCur(other._cur);
        return true;
    }

    public void _push() {
        this._cur.push();
    }

    public boolean _pop() {
        return this._cur.pop();
    }

    @Override // org.apache.xmlbeans.impl.store.Locale.ChangeListener
    public void notifyChange() {
        if (this._cur != null) {
            _getSelectionCount();
        }
    }

    @Override // org.apache.xmlbeans.impl.store.Locale.ChangeListener
    public void setNextChangeListener(Locale.ChangeListener listener) {
        this._nextChangeListener = listener;
    }

    @Override // org.apache.xmlbeans.impl.store.Locale.ChangeListener
    public Locale.ChangeListener getNextChangeListener() {
        return this._nextChangeListener;
    }

    /* renamed from: _selectPath, reason: merged with bridge method [inline-methods] */
    public void m2662lambda$selectPath$19$orgapachexmlbeansimplstoreCursor(String path) {
        m2663lambda$selectPath$20$orgapachexmlbeansimplstoreCursor(path, null);
    }

    /* renamed from: _selectPath, reason: merged with bridge method [inline-methods] */
    public void m2663lambda$selectPath$20$orgapachexmlbeansimplstoreCursor(String pathExpr, XmlOptions options) {
        _clearSelections();
        if (this._pathEngine != null) {
            throw new AssertionError();
        }
        this._pathEngine = XPathFactory.getCompiledPath(pathExpr, options).execute(this._cur, options);
        this._cur._locale.registerForChange(this);
    }

    public boolean _hasNextSelection() {
        int curr = this._currentSelection;
        push();
        try {
            return _toNextSelection();
        } finally {
            this._currentSelection = curr;
            pop();
        }
    }

    public boolean _toNextSelection() {
        return _toSelection(this._currentSelection + 1);
    }

    public boolean _toSelection(int i) {
        if (i < 0) {
            return false;
        }
        while (i >= this._cur.selectionCount()) {
            if (this._pathEngine == null) {
                return false;
            }
            if (!this._pathEngine.next(this._cur)) {
                this._pathEngine.release();
                this._pathEngine = null;
                return false;
            }
        }
        Cur cur = this._cur;
        this._currentSelection = i;
        cur.moveToSelection(i);
        return true;
    }

    public int _getSelectionCount() {
        _toSelection(Integer.MAX_VALUE);
        return this._cur.selectionCount();
    }

    public void _addToSelection() {
        _toSelection(Integer.MAX_VALUE);
        this._cur.addToSelection();
    }

    public void _clearSelections() {
        if (this._pathEngine != null) {
            this._pathEngine.release();
            this._pathEngine = null;
        }
        this._cur.clearSelection();
        this._currentSelection = -1;
    }

    /* renamed from: _namespaceForPrefix, reason: merged with bridge method [inline-methods] */
    public String m2646xcbfe39eb(String prefix) {
        if (!this._cur.isContainer()) {
            throw new IllegalStateException("Not on a container");
        }
        return this._cur.namespaceForPrefix(prefix, true);
    }

    /* renamed from: _prefixForNamespace, reason: merged with bridge method [inline-methods] */
    public String m2651x82e41508(String ns) {
        if (ns == null || ns.isEmpty()) {
            throw new IllegalArgumentException("Must specify a namespace");
        }
        return this._cur.prefixForNamespace(ns, null, true);
    }

    /* renamed from: _getAllNamespaces, reason: merged with bridge method [inline-methods] */
    public void m2622lambda$getAllNamespaces$28$orgapachexmlbeansimplstoreCursor(Map<String, String> addToThis) {
        if (!this._cur.isContainer()) {
            throw new IllegalStateException("Not on a container");
        }
        if (addToThis != null) {
            Locale.getAllNamespaces(this._cur, addToThis);
        }
    }

    public XmlObject _getObject() {
        return this._cur.getObject();
    }

    public XmlCursor.TokenType _prevTokenType() {
        this._cur.push();
        XmlCursor.TokenType tt = _toPrevToken();
        this._cur.pop();
        return tt;
    }

    public boolean _hasNextToken() {
        return (this._cur._pos == -1 && this._cur._xobj.kind() == 1) ? false : true;
    }

    public boolean _hasPrevToken() {
        return this._cur.kind() != 1;
    }

    public XmlCursor.TokenType _toFirstContentToken() {
        if (!this._cur.isContainer()) {
            return XmlCursor.TokenType.NONE;
        }
        this._cur.next();
        return currentTokenType();
    }

    public XmlCursor.TokenType _toEndToken() {
        if (!this._cur.isContainer()) {
            return XmlCursor.TokenType.NONE;
        }
        this._cur.toEnd();
        return currentTokenType();
    }

    public boolean _toChild(String local) {
        return _toChild((String) null, local);
    }

    public boolean _toChild(QName name) {
        return _toChild(name, 0);
    }

    public boolean _toChild(int index) {
        return _toChild((QName) null, index);
    }

    public boolean _toChild(String uri, String local) {
        validateLocalName(local);
        return _toChild(this._cur._locale.makeQName(uri, local), 0);
    }

    public boolean _toChild(QName name, int index) {
        return Locale.toChild(this._cur, name, index);
    }

    public int _toNextChar(int maxCharacterCount) {
        return this._cur.nextChars(maxCharacterCount);
    }

    public int _toPrevChar(int maxCharacterCount) {
        return this._cur.prevChars(maxCharacterCount);
    }

    public boolean _toPrevSibling() {
        return Locale.toPrevSiblingElement(this._cur);
    }

    public boolean _toLastChild() {
        return Locale.toLastChildElement(this._cur);
    }

    public boolean _toFirstChild() {
        return Locale.toFirstChildElement(this._cur);
    }

    public boolean _toNextSibling(String name) {
        return _toNextSibling(new QName(name));
    }

    public boolean _toNextSibling(String uri, String local) {
        validateLocalName(local);
        return _toNextSibling(this._cur._locale._qnameFactory.getQName(uri, local));
    }

    public boolean _toNextSibling(QName name) {
        this._cur.push();
        while (___toNextSibling()) {
            if (this._cur.getName().equals(name)) {
                this._cur.popButStay();
                return true;
            }
        }
        this._cur.pop();
        return false;
    }

    public boolean _toFirstAttribute() {
        return this._cur.isContainer() && Locale.toFirstNormalAttr(this._cur);
    }

    public boolean _toLastAttribute() {
        if (this._cur.isContainer()) {
            this._cur.push();
            this._cur.push();
            boolean foundAttr = false;
            while (this._cur.toNextAttr()) {
                if (this._cur.isNormalAttr()) {
                    this._cur.popButStay();
                    this._cur.push();
                    foundAttr = true;
                }
            }
            this._cur.pop();
            if (foundAttr) {
                this._cur.popButStay();
                return true;
            }
            this._cur.pop();
            return false;
        }
        return false;
    }

    public boolean _toNextAttribute() {
        return this._cur.isAttr() && Locale.toNextNormalAttr(this._cur);
    }

    public boolean _toPrevAttribute() {
        return this._cur.isAttr() && Locale.toPrevNormalAttr(this._cur);
    }

    /* renamed from: _getAttributeText, reason: merged with bridge method [inline-methods] */
    public String m2623lambda$getAttributeText$39$orgapachexmlbeansimplstoreCursor(QName attrName) {
        if (attrName == null) {
            throw new IllegalArgumentException("Attr name is null");
        }
        if (!this._cur.isContainer()) {
            return null;
        }
        return this._cur.getAttrValue(attrName);
    }

    public boolean _setAttributeText(QName attrName, String value) {
        if (attrName == null) {
            throw new IllegalArgumentException("Attr name is null");
        }
        validateLocalName(attrName.getLocalPart());
        if (!this._cur.isContainer()) {
            return false;
        }
        this._cur.setAttrValue(attrName, value);
        return true;
    }

    public boolean _removeAttribute(QName attrName) {
        if (attrName == null) {
            throw new IllegalArgumentException("Attr name is null");
        }
        if (!this._cur.isContainer()) {
            return false;
        }
        return this._cur.removeAttr(attrName);
    }

    public String _getTextValue() {
        if (this._cur.isText()) {
            return _getChars();
        }
        if (this._cur.isNode()) {
            return this._cur.hasChildren() ? Locale.getTextValue(this._cur) : this._cur.getValueAsString();
        }
        throw new IllegalStateException("Can't get text value, current token can have no text value");
    }

    public int _getTextValue(char[] chars, int offset, int max) {
        if (this._cur.isText()) {
            return _getChars(chars, offset, max);
        }
        if (chars == null) {
            throw new IllegalArgumentException("char buffer is null");
        }
        if (offset < 0) {
            throw new IllegalArgumentException("offset < 0");
        }
        if (offset >= chars.length) {
            throw new IllegalArgumentException("offset off end");
        }
        if (max < 0) {
            max = Integer.MAX_VALUE;
        }
        if (offset + max > chars.length) {
            max = chars.length - offset;
        }
        if (!this._cur.isNode()) {
            throw new IllegalStateException("Can't get text value, current token can have no text value");
        }
        if (this._cur.hasChildren()) {
            return Locale.getTextValue(this._cur, chars, offset, max);
        }
        Object src = this._cur.getFirstChars();
        if (this._cur._cchSrc > max) {
            this._cur._cchSrc = max;
        }
        if (this._cur._cchSrc <= 0) {
            return 0;
        }
        CharUtil.getChars(chars, offset, src, this._cur._offSrc, this._cur._cchSrc);
        return this._cur._cchSrc;
    }

    private void setTextValue(Object src, int off, int cch) {
        if (!this._cur.isNode()) {
            throw new IllegalStateException("Can't set text value, current token can have no text value");
        }
        this._cur.moveNodeContents(null, false);
        this._cur.next();
        this._cur.insertChars(src, off, cch);
        this._cur.toParent();
    }

    /* renamed from: _setTextValue, reason: merged with bridge method [inline-methods] */
    public void m2667lambda$setTextValue$43$orgapachexmlbeansimplstoreCursor(String text) {
        if (text == null) {
            text = "";
        }
        setTextValue(text, 0, text.length());
    }

    /* renamed from: _setTextValue, reason: merged with bridge method [inline-methods] */
    public void m2668lambda$setTextValue$44$orgapachexmlbeansimplstoreCursor(char[] sourceChars, int offset, int length) {
        if (length < 0) {
            throw new IndexOutOfBoundsException("setTextValue: length < 0");
        }
        if (sourceChars == null) {
            if (length > 0) {
                throw new IllegalArgumentException("setTextValue: sourceChars == null");
            }
            setTextValue((char[]) null, 0, 0);
        } else {
            if (offset < 0 || offset >= sourceChars.length) {
                throw new IndexOutOfBoundsException("setTextValue: offset out of bounds");
            }
            if (offset + length > sourceChars.length) {
                length = sourceChars.length - offset;
            }
            CharUtil cu = this._cur._locale.getCharUtil();
            setTextValue(cu.saveChars(sourceChars, offset, length), cu._offSrc, cu._cchSrc);
        }
    }

    public String _getChars() {
        return this._cur.getCharsAsString();
    }

    public int _getChars(char[] buf, int off, int cch) {
        int cchRight = this._cur.cchRight();
        if (cch < 0 || cch > cchRight) {
            cch = cchRight;
        }
        if (buf == null || off >= buf.length) {
            return 0;
        }
        if (buf.length - off < cch) {
            cch = buf.length - off;
        }
        Object src = this._cur.getChars(cch);
        CharUtil.getChars(buf, off, src, this._cur._offSrc, this._cur._cchSrc);
        return this._cur._cchSrc;
    }

    public void _toStartDoc() {
        this._cur.toRoot();
    }

    public void _toEndDoc() {
        _toStartDoc();
        this._cur.toEnd();
    }

    public int _comparePosition(Cursor other) {
        int s = this._cur.comparePosition(other._cur);
        if (s == 2) {
            throw new IllegalArgumentException("Cursors not in same document");
        }
        if (s < -1 || s > 1) {
            throw new AssertionError();
        }
        return s;
    }

    public boolean _isLeftOf(Cursor other) {
        return _comparePosition(other) < 0;
    }

    public boolean _isAtSamePositionAs(Cursor other) {
        return this._cur.isSamePos(other._cur);
    }

    public boolean _isRightOf(Cursor other) {
        return _comparePosition(other) > 0;
    }

    /* renamed from: _execQuery, reason: merged with bridge method [inline-methods] */
    public XmlCursor m2619lambda$execQuery$46$orgapachexmlbeansimplstoreCursor(String query) {
        return m2620lambda$execQuery$47$orgapachexmlbeansimplstoreCursor(query, null);
    }

    /* renamed from: _execQuery, reason: merged with bridge method [inline-methods] */
    public XmlCursor m2620lambda$execQuery$47$orgapachexmlbeansimplstoreCursor(String query, XmlOptions options) {
        checkThisCursor();
        return XPathFactory.cursorExecQuery(this._cur, query, options);
    }

    public boolean _toBookmark(XmlCursor.XmlBookmark bookmark) {
        if (bookmark == null || !(bookmark._currentMark instanceof Bookmark)) {
            return false;
        }
        Bookmark m = (Bookmark) bookmark._currentMark;
        if (m._xobj == null || m._xobj._locale != this._cur._locale) {
            return false;
        }
        this._cur.moveTo(m._xobj, m._pos);
        return true;
    }

    /* renamed from: _toNextBookmark, reason: merged with bridge method [inline-methods] */
    public XmlCursor.XmlBookmark m2676lambda$toNextBookmark$23$orgapachexmlbeansimplstoreCursor(Object key) {
        if (key == null) {
            return null;
        }
        this._cur.push();
        do {
            int cch = this._cur.cchRight();
            if (cch > 1) {
                this._cur.nextChars(1);
                Cur cur = this._cur;
                int cch2 = this._cur.firstBookmarkInChars(key, cch - 1);
                cur.nextChars(cch2 >= 0 ? cch2 : -1);
            } else if (_toNextToken().isNone()) {
                this._cur.pop();
                return null;
            }
            XmlCursor.XmlBookmark bm = getBookmark(key, this._cur);
            if (bm != null) {
                this._cur.popButStay();
                return bm;
            }
        } while (this._cur.kind() != -1);
        this._cur.pop();
        return null;
    }

    /* renamed from: _toPrevBookmark, reason: merged with bridge method [inline-methods] */
    public XmlCursor.XmlBookmark m2681lambda$toPrevBookmark$24$orgapachexmlbeansimplstoreCursor(Object key) {
        if (key == null) {
            return null;
        }
        this._cur.push();
        do {
            int cch = this._cur.cchLeft();
            if (cch > 1) {
                this._cur.prevChars(1);
                Cur cur = this._cur;
                int cch2 = this._cur.firstBookmarkInCharsLeft(key, cch - 1);
                cur.prevChars(cch2 >= 0 ? cch2 : -1);
            } else if (cch == 1) {
                this._cur.prevChars(1);
            } else if (_toPrevToken().isNone()) {
                this._cur.pop();
                return null;
            }
            XmlCursor.XmlBookmark bm = getBookmark(key, this._cur);
            if (bm != null) {
                this._cur.popButStay();
                return bm;
            }
        } while (this._cur.kind() != 1);
        this._cur.pop();
        return null;
    }

    /* renamed from: _setBookmark, reason: merged with bridge method [inline-methods] */
    public void m2665lambda$setBookmark$48$orgapachexmlbeansimplstoreCursor(XmlCursor.XmlBookmark bookmark) {
        if (bookmark != null) {
            if (bookmark.getKey() == null) {
                throw new IllegalArgumentException("Annotation key is null");
            }
            bookmark._currentMark = this._cur.setBookmark(bookmark.getKey(), bookmark);
        }
    }

    static XmlCursor.XmlBookmark getBookmark(Object key, Cur c) {
        if (key == null) {
            return null;
        }
        Object bm = c.getBookmark(key);
        if (bm instanceof XmlCursor.XmlBookmark) {
            return (XmlCursor.XmlBookmark) bm;
        }
        return null;
    }

    /* renamed from: _getBookmark, reason: merged with bridge method [inline-methods] */
    public XmlCursor.XmlBookmark m2624lambda$getBookmark$49$orgapachexmlbeansimplstoreCursor(Object key) {
        if (key == null) {
            return null;
        }
        return getBookmark(key, this._cur);
    }

    /* renamed from: _clearBookmark, reason: merged with bridge method [inline-methods] */
    public void m2617lambda$clearBookmark$50$orgapachexmlbeansimplstoreCursor(Object key) {
        if (key != null) {
            this._cur.setBookmark(key, null);
        }
    }

    /* renamed from: _getAllBookmarkRefs, reason: merged with bridge method [inline-methods] */
    public void m2621x328e4062(Collection<Object> listToFill) {
        if (listToFill != null) {
            for (Bookmark b = this._cur._xobj._bookmarks; b != null; b = b._next) {
                if (b._value instanceof XmlCursor.XmlBookmark) {
                    listToFill.add(b._value);
                }
            }
        }
    }

    public boolean _removeXml() {
        if (this._cur.isRoot()) {
            throw new IllegalStateException("Can't remove a whole document.");
        }
        if (this._cur.isFinish()) {
            return false;
        }
        if (!this._cur.isText() && !this._cur.isNode()) {
            throw new AssertionError();
        }
        if (this._cur.isText()) {
            this._cur.moveChars(null, -1);
            return true;
        }
        this._cur.moveNode(null);
        return true;
    }

    public boolean _moveXml(Cursor to) {
        to.checkInsertionValidity(this._cur);
        if (this._cur.isText()) {
            int cchRight = this._cur.cchRight();
            if (cchRight <= 0) {
                throw new AssertionError();
            }
            if (this._cur.inChars(to._cur, cchRight, true)) {
                return false;
            }
            this._cur.moveChars(to._cur, cchRight);
            to._cur.nextChars(cchRight);
            return true;
        }
        if (this._cur.contains(to._cur)) {
            return false;
        }
        Cur c = to.tempCur();
        this._cur.moveNode(to._cur);
        to._cur.moveToCur(c);
        c.release();
        return true;
    }

    public boolean _copyXml(Cursor to) {
        to.checkInsertionValidity(this._cur);
        if (!this._cur.isText() && !this._cur.isNode()) {
            throw new AssertionError();
        }
        Cur c = to.tempCur();
        if (this._cur.isText()) {
            to._cur.insertChars(this._cur.getChars(-1), this._cur._offSrc, this._cur._cchSrc);
        } else {
            this._cur.copyNode(to._cur);
        }
        to._cur.moveToCur(c);
        c.release();
        return true;
    }

    public boolean _removeXmlContents() {
        if (!this._cur.isContainer()) {
            return false;
        }
        this._cur.moveNodeContents(null, false);
        return true;
    }

    private boolean checkContentInsertionValidity(Cursor to) {
        this._cur.push();
        this._cur.next();
        if (this._cur.isFinish()) {
            this._cur.pop();
            return false;
        }
        try {
            to.checkInsertionValidity(this._cur);
            this._cur.pop();
            return true;
        } catch (IllegalArgumentException e) {
            this._cur.pop();
            throw e;
        }
    }

    public boolean _moveXmlContents(Cursor to) {
        if (!this._cur.isContainer() || this._cur.contains(to._cur) || !checkContentInsertionValidity(to)) {
            return false;
        }
        Cur c = to.tempCur();
        this._cur.moveNodeContents(to._cur, false);
        to._cur.moveToCur(c);
        c.release();
        return true;
    }

    public boolean _copyXmlContents(Cursor to) {
        if (!this._cur.isContainer() || this._cur.contains(to._cur) || !checkContentInsertionValidity(to)) {
            return false;
        }
        Cur c = this._cur._locale.tempCur();
        this._cur.copyNode(c);
        Cur c2 = to._cur.tempCur();
        c.moveNodeContents(to._cur, false);
        c.release();
        to._cur.moveToCur(c2);
        c2.release();
        return true;
    }

    public int _removeChars(int cch) {
        int cchRight = this._cur.cchRight();
        if (cchRight == 0 || cch == 0) {
            return 0;
        }
        if (cch < 0 || cch > cchRight) {
            cch = cchRight;
        }
        this._cur.moveChars(null, cch);
        return this._cur._cchSrc;
    }

    public int _moveChars(int cch, Cursor to) {
        int cchRight = this._cur.cchRight();
        if (cchRight <= 0 || cch == 0) {
            return 0;
        }
        if (cch < 0 || cch > cchRight) {
            cch = cchRight;
        }
        to.checkInsertionValidity(this._cur);
        this._cur.moveChars(to._cur, cch);
        to._cur.nextChars(this._cur._cchSrc);
        return this._cur._cchSrc;
    }

    public int _copyChars(int cch, Cursor to) {
        int cchRight = this._cur.cchRight();
        if (cchRight <= 0 || cch == 0) {
            return 0;
        }
        if (cch < 0 || cch > cchRight) {
            cch = cchRight;
        }
        to.checkInsertionValidity(this._cur);
        to._cur.insertChars(this._cur.getChars(cch), this._cur._offSrc, this._cur._cchSrc);
        to._cur.nextChars(this._cur._cchSrc);
        return this._cur._cchSrc;
    }

    /* renamed from: _insertChars, reason: merged with bridge method [inline-methods] */
    public void m2633lambda$insertChars$53$orgapachexmlbeansimplstoreCursor(String text) {
        int l = text == null ? 0 : text.length();
        if (l > 0) {
            if (!this._cur.isRoot() && !this._cur.isAttr()) {
                this._cur.insertChars(text, 0, l);
                this._cur.nextChars(l);
                return;
            }
            throw new IllegalStateException("Can't insert before the document or an attribute.");
        }
    }

    /* renamed from: _beginElement, reason: merged with bridge method [inline-methods] */
    public void m2615lambda$beginElement$58$orgapachexmlbeansimplstoreCursor(String localName) {
        m2640x7b6ee067(localName, null, null);
        _toPrevToken();
    }

    /* renamed from: _beginElement, reason: merged with bridge method [inline-methods] */
    public void m2616lambda$beginElement$59$orgapachexmlbeansimplstoreCursor(String localName, String uri) {
        m2640x7b6ee067(localName, uri, null);
        _toPrevToken();
    }

    /* renamed from: _beginElement, reason: merged with bridge method [inline-methods] */
    public void m2614lambda$beginElement$57$orgapachexmlbeansimplstoreCursor(QName name) {
        m2638xe96cf1e5(name, (String) null);
        _toPrevToken();
    }

    /* renamed from: _insertElement, reason: merged with bridge method [inline-methods] */
    public void m2636lambda$insertElement$55$orgapachexmlbeansimplstoreCursor(String localName) {
        m2640x7b6ee067(localName, null, null);
    }

    /* renamed from: _insertElement, reason: merged with bridge method [inline-methods] */
    public void m2637lambda$insertElement$56$orgapachexmlbeansimplstoreCursor(String localName, String uri) {
        m2640x7b6ee067(localName, uri, null);
    }

    /* renamed from: _insertElement, reason: merged with bridge method [inline-methods] */
    public void m2635lambda$insertElement$54$orgapachexmlbeansimplstoreCursor(QName name) {
        m2638xe96cf1e5(name, (String) null);
    }

    /* renamed from: _insertElementWithText, reason: merged with bridge method [inline-methods] */
    public void m2639xb26de926(String localName, String text) {
        m2640x7b6ee067(localName, null, text);
    }

    /* renamed from: _insertElementWithText, reason: merged with bridge method [inline-methods] */
    public void m2640x7b6ee067(String localName, String uri, String text) {
        validateLocalName(localName);
        m2638xe96cf1e5(this._cur._locale.makeQName(uri, localName), text);
    }

    /* renamed from: _insertElementWithText, reason: merged with bridge method [inline-methods] */
    public void m2638xe96cf1e5(QName name, String text) {
        validateLocalName(name.getLocalPart());
        Cur c = this._cur._locale.tempCur();
        c.createElement(name);
        insertNode(c, text);
        c.release();
    }

    /* renamed from: _insertAttribute, reason: merged with bridge method [inline-methods] */
    public void m2627lambda$insertAttribute$63$orgapachexmlbeansimplstoreCursor(String localName) {
        m2630x7a892e1f(localName, (String) null);
    }

    /* renamed from: _insertAttribute, reason: merged with bridge method [inline-methods] */
    public void m2628lambda$insertAttribute$64$orgapachexmlbeansimplstoreCursor(String localName, String uri) {
        m2631x438a2560(localName, uri, null);
    }

    /* renamed from: _insertAttribute, reason: merged with bridge method [inline-methods] */
    public void m2629lambda$insertAttribute$65$orgapachexmlbeansimplstoreCursor(QName name) {
        m2632xc8b1ca1(name, (String) null);
    }

    /* renamed from: _insertAttributeWithValue, reason: merged with bridge method [inline-methods] */
    public void m2630x7a892e1f(String localName, String value) {
        m2631x438a2560(localName, null, value);
    }

    /* renamed from: _insertAttributeWithValue, reason: merged with bridge method [inline-methods] */
    public void m2631x438a2560(String localName, String uri, String value) {
        validateLocalName(localName);
        m2632xc8b1ca1(this._cur._locale.makeQName(uri, localName), value);
    }

    /* renamed from: _insertAttributeWithValue, reason: merged with bridge method [inline-methods] */
    public void m2632xc8b1ca1(QName name, String text) {
        if (name == null) {
            throw new IllegalArgumentException("QName must not be null");
        }
        validateLocalName(name.getLocalPart());
        Cur c = this._cur._locale.tempCur();
        c.createAttr(name);
        insertNode(c, text);
        c.release();
    }

    /* renamed from: _insertNamespace, reason: merged with bridge method [inline-methods] */
    public void m2641lambda$insertNamespace$69$orgapachexmlbeansimplstoreCursor(String prefix, String namespace) {
        m2632xc8b1ca1(this._cur._locale.createXmlns(prefix), namespace);
    }

    /* renamed from: _insertComment, reason: merged with bridge method [inline-methods] */
    public void m2634lambda$insertComment$70$orgapachexmlbeansimplstoreCursor(String text) {
        Cur c = this._cur._locale.tempCur();
        c.createComment();
        insertNode(c, text);
        c.release();
    }

    /* renamed from: _insertProcInst, reason: merged with bridge method [inline-methods] */
    public void m2642lambda$insertProcInst$71$orgapachexmlbeansimplstoreCursor(String target, String text) {
        validateLocalName(target);
        if (Locale.beginsWithXml(target) && target.length() == 3) {
            throw new IllegalArgumentException("Target is 'xml'");
        }
        Cur c = this._cur._locale.tempCur();
        c.createProcinst(target);
        insertNode(c, text);
        c.release();
    }

    public void _dump() {
        this._cur.dump();
    }

    private void checkThisCursor() {
        if (this._cur == null) {
            throw new IllegalStateException("This cursor has been disposed");
        }
    }

    private Cursor checkCursors(XmlCursor xOther) {
        checkThisCursor();
        if (xOther == null) {
            throw new IllegalArgumentException("Other cursor is <null>");
        }
        if (!(xOther instanceof Cursor)) {
            throw new IllegalArgumentException("Incompatible cursors: " + xOther);
        }
        Cursor other = (Cursor) xOther;
        if (other._cur == null) {
            throw new IllegalStateException("Other cursor has been disposed");
        }
        return other;
    }

    private int twoLocaleOp(XmlCursor xOther, final int op, final int arg) {
        int twoLocaleOp;
        int twoLocaleOp2;
        int twoLocaleOp3;
        final Cursor other = checkCursors(xOther);
        Locale locale = this._cur._locale;
        Locale otherLocale = other._cur._locale;
        if (locale == otherLocale) {
            return ((Integer) syncWrapNoEnter(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda53
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Cursor.this.m2684lambda$twoLocaleOp$0$orgapachexmlbeansimplstoreCursor(other, op, arg);
                }
            })).intValue();
        }
        if (locale.noSync()) {
            if (otherLocale.noSync()) {
                return twoLocaleOp(other, op, arg);
            }
            synchronized (otherLocale) {
                twoLocaleOp3 = twoLocaleOp(other, op, arg);
            }
            return twoLocaleOp3;
        }
        if (otherLocale.noSync()) {
            synchronized (locale) {
                twoLocaleOp2 = twoLocaleOp(other, op, arg);
            }
            return twoLocaleOp2;
        }
        boolean acquired = false;
        try {
            try {
                GlobalLock.acquire();
                acquired = true;
                synchronized (locale) {
                    synchronized (otherLocale) {
                        GlobalLock.release();
                        acquired = false;
                        twoLocaleOp = twoLocaleOp(other, op, arg);
                    }
                }
                return twoLocaleOp;
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        } finally {
            if (acquired) {
                GlobalLock.release();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$twoLocaleOp$0$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Integer m2684lambda$twoLocaleOp$0$orgapachexmlbeansimplstoreCursor(Cursor other, int op, int arg) {
        return Integer.valueOf(twoLocaleOp(other, op, arg));
    }

    private int twoLocaleOp(Cursor cursor, int i, int i2) {
        Locale locale = this._cur._locale;
        Locale locale2 = cursor._cur._locale;
        locale.enter(locale2);
        try {
            switch (i) {
                case 0:
                    return _moveXml(cursor) ? 1 : 0;
                case 1:
                    return _copyXml(cursor) ? 1 : 0;
                case 2:
                    return _moveXmlContents(cursor) ? 1 : 0;
                case 3:
                    return _copyXmlContents(cursor) ? 1 : 0;
                case 4:
                    return _moveChars(i2, cursor);
                case 5:
                    return _copyChars(i2, cursor);
                default:
                    throw new RuntimeException("Unknown operation: " + i);
            }
        } finally {
            locale.exit(locale2);
        }
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean moveXml(XmlCursor xTo) {
        return twoLocaleOp(xTo, 0, 0) == 1;
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean copyXml(XmlCursor xTo) {
        return twoLocaleOp(xTo, 1, 0) == 1;
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean moveXmlContents(XmlCursor xTo) {
        return twoLocaleOp(xTo, 2, 0) == 1;
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean copyXmlContents(XmlCursor xTo) {
        return twoLocaleOp(xTo, 3, 0) == 1;
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public int moveChars(int cch, XmlCursor xTo) {
        return twoLocaleOp(xTo, 4, cch);
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public int copyChars(int cch, XmlCursor xTo) {
        return twoLocaleOp(xTo, 5, cch);
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toCursor(XmlCursor xOther) {
        final Cursor other = checkCursors(xOther);
        return this._cur._locale == other._cur._locale && ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda15
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2675lambda$toCursor$1$orgapachexmlbeansimplstoreCursor(other);
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$toCursor$1$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Boolean m2675lambda$toCursor$1$orgapachexmlbeansimplstoreCursor(Cursor other) {
        return Boolean.valueOf(_toCursor(other));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean isInSameDocument(XmlCursor xOther) {
        return xOther != null && this._cur.isInSameTree(checkCursors(xOther)._cur);
    }

    private Cursor preCheck(XmlCursor xOther) {
        Cursor other = checkCursors(xOther);
        if (this._cur._locale != other._cur._locale) {
            throw new IllegalArgumentException("Cursors not in same document");
        }
        return other;
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public int comparePosition(XmlCursor xOther) {
        final Cursor other = preCheck(xOther);
        return ((Integer) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda101
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2618lambda$comparePosition$2$orgapachexmlbeansimplstoreCursor(other);
            }
        })).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$comparePosition$2$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Integer m2618lambda$comparePosition$2$orgapachexmlbeansimplstoreCursor(Cursor other) {
        return Integer.valueOf(_comparePosition(other));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean isLeftOf(XmlCursor xOther) {
        final Cursor other = preCheck(xOther);
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda42
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2644lambda$isLeftOf$3$orgapachexmlbeansimplstoreCursor(other);
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$isLeftOf$3$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Boolean m2644lambda$isLeftOf$3$orgapachexmlbeansimplstoreCursor(Cursor other) {
        return Boolean.valueOf(_isLeftOf(other));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean isAtSamePositionAs(XmlCursor xOther) {
        final Cursor other = preCheck(xOther);
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda82
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2643x6d5da9ab(other);
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$isAtSamePositionAs$4$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Boolean m2643x6d5da9ab(Cursor other) {
        return Boolean.valueOf(_isAtSamePositionAs(other));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean isRightOf(XmlCursor xOther) {
        final Cursor other = preCheck(xOther);
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda45
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2645lambda$isRightOf$5$orgapachexmlbeansimplstoreCursor(other);
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$isRightOf$5$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Boolean m2645lambda$isRightOf$5$orgapachexmlbeansimplstoreCursor(Cursor other) {
        return Boolean.valueOf(_isRightOf(other));
    }

    public static XmlCursor newCursor(Xobj x, int p) {
        Cursor cursor;
        Locale l = x._locale;
        if (l.noSync()) {
            l.enter();
            try {
                return new Cursor(x, p);
            } finally {
            }
        }
        synchronized (l) {
            l.enter();
            try {
                cursor = new Cursor(x, p);
            } finally {
            }
        }
        return cursor;
    }

    private boolean preCheck() {
        checkThisCursor();
        return this._cur._locale.noSync();
    }

    @Override // org.apache.xmlbeans.XmlCursor, java.lang.AutoCloseable
    public void close() {
        if (this._cur != null) {
            syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda126
                @Override // java.lang.Runnable
                public final void run() {
                    Cursor.this._dispose();
                }
            });
        }
    }

    @Override // org.apache.xmlbeans.XmlCursor
    @Deprecated
    public void dispose() {
        close();
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public Object monitor() {
        return syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda83
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this._monitor();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public XmlDocumentProperties documentProperties() {
        return (XmlDocumentProperties) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda29
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this._documentProperties();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public XmlCursor newCursor() {
        return (XmlCursor) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda116
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this._newCursor();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public XMLStreamReader newXMLStreamReader() {
        return (XMLStreamReader) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda121
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this._newXMLStreamReader();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public XMLStreamReader newXMLStreamReader(final XmlOptions options) {
        return (XMLStreamReader) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda123
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2650x3f62dfe9(options);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public String xmlText() {
        return (String) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda10
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this._xmlText();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public InputStream newInputStream() {
        return (InputStream) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this._newInputStream();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public Reader newReader() {
        return (Reader) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda93
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this._newReader();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public Node newDomNode() {
        return (Node) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda40
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this._newDomNode();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public Node getDomNode() {
        return (Node) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda67
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this._getDomNode();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public void save(final ContentHandler ch, final LexicalHandler lh) throws SAXException {
        syncWrapSAXEx(new WrapSAXEx() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda52
            @Override // org.apache.xmlbeans.impl.store.Cursor.WrapSAXEx
            public final void run() {
                Cursor.this.m2659lambda$save$7$orgapachexmlbeansimplstoreCursor(ch, lh);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public void save(final File file) throws IOException {
        syncWrapIOEx(new WrapIOEx() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda88
            @Override // org.apache.xmlbeans.impl.store.Cursor.WrapIOEx
            public final void run() {
                Cursor.this.m2660lambda$save$8$orgapachexmlbeansimplstoreCursor(file);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public void save(final OutputStream os) throws IOException {
        syncWrapIOEx(new WrapIOEx() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda127
            @Override // org.apache.xmlbeans.impl.store.Cursor.WrapIOEx
            public final void run() {
                Cursor.this.m2661lambda$save$9$orgapachexmlbeansimplstoreCursor(os);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public void save(final Writer w) throws IOException {
        syncWrapIOEx(new WrapIOEx() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda24
            @Override // org.apache.xmlbeans.impl.store.Cursor.WrapIOEx
            public final void run() {
                Cursor.this.m2654lambda$save$10$orgapachexmlbeansimplstoreCursor(w);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public String xmlText(final XmlOptions options) {
        return (String) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda85
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2685lambda$xmlText$11$orgapachexmlbeansimplstoreCursor(options);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public InputStream newInputStream(final XmlOptions options) {
        return (InputStream) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda81
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2648lambda$newInputStream$12$orgapachexmlbeansimplstoreCursor(options);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public Reader newReader(final XmlOptions options) {
        return (Reader) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda46
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2649lambda$newReader$13$orgapachexmlbeansimplstoreCursor(options);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public Node newDomNode(final XmlOptions options) {
        return (Node) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda49
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2647lambda$newDomNode$14$orgapachexmlbeansimplstoreCursor(options);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public void save(final ContentHandler ch, final LexicalHandler lh, final XmlOptions options) throws SAXException {
        syncWrapSAXEx(new WrapSAXEx() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda12
            @Override // org.apache.xmlbeans.impl.store.Cursor.WrapSAXEx
            public final void run() {
                Cursor.this.m2655lambda$save$15$orgapachexmlbeansimplstoreCursor(ch, lh, options);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public void save(final File file, final XmlOptions options) throws IOException {
        syncWrapIOEx(new WrapIOEx() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda124
            @Override // org.apache.xmlbeans.impl.store.Cursor.WrapIOEx
            public final void run() {
                Cursor.this.m2656lambda$save$16$orgapachexmlbeansimplstoreCursor(file, options);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public void save(final OutputStream os, final XmlOptions options) throws IOException {
        syncWrapIOEx(new WrapIOEx() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda17
            @Override // org.apache.xmlbeans.impl.store.Cursor.WrapIOEx
            public final void run() {
                Cursor.this.m2657lambda$save$17$orgapachexmlbeansimplstoreCursor(os, options);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public void save(final Writer w, final XmlOptions options) throws IOException {
        syncWrapIOEx(new WrapIOEx() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda90
            @Override // org.apache.xmlbeans.impl.store.Cursor.WrapIOEx
            public final void run() {
                Cursor.this.m2658lambda$save$18$orgapachexmlbeansimplstoreCursor(w, options);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void push() {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda89
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this._push();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean pop() {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._pop());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void selectPath(final String path) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda84
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2662lambda$selectPath$19$orgapachexmlbeansimplstoreCursor(path);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void selectPath(final String path, final XmlOptions options) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda95
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2663lambda$selectPath$20$orgapachexmlbeansimplstoreCursor(path, options);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean hasNextSelection() {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda43
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._hasNextSelection());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toNextSelection() {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._toNextSelection());
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$toSelection$21$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Boolean m2683lambda$toSelection$21$orgapachexmlbeansimplstoreCursor(int i) {
        return Boolean.valueOf(_toSelection(i));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toSelection(final int i) {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda44
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2683lambda$toSelection$21$orgapachexmlbeansimplstoreCursor(i);
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public int getSelectionCount() {
        return ((Integer) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda76
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(Cursor.this._getSelectionCount());
            }
        })).intValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void addToSelection() {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda108
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this._addToSelection();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void clearSelections() {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda19
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this._clearSelections();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$toBookmark$22$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Boolean m2669lambda$toBookmark$22$orgapachexmlbeansimplstoreCursor(XmlCursor.XmlBookmark bookmark) {
        return Boolean.valueOf(_toBookmark(bookmark));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toBookmark(final XmlCursor.XmlBookmark bookmark) {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda61
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2669lambda$toBookmark$22$orgapachexmlbeansimplstoreCursor(bookmark);
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public XmlCursor.XmlBookmark toNextBookmark(final Object key) {
        return (XmlCursor.XmlBookmark) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda22
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2676lambda$toNextBookmark$23$orgapachexmlbeansimplstoreCursor(key);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public XmlCursor.XmlBookmark toPrevBookmark(final Object key) {
        return (XmlCursor.XmlBookmark) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda37
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2681lambda$toPrevBookmark$24$orgapachexmlbeansimplstoreCursor(key);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public QName getName() {
        return (QName) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda120
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this._getName();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void setName(final QName name) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda41
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2666lambda$setName$25$orgapachexmlbeansimplstoreCursor(name);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public String namespaceForPrefix(final String prefix) {
        return (String) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda34
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2646xcbfe39eb(prefix);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public String prefixForNamespace(final String namespaceURI) {
        return (String) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda109
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2651x82e41508(namespaceURI);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void getAllNamespaces(final Map<String, String> addToThis) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda99
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2622lambda$getAllNamespaces$28$orgapachexmlbeansimplstoreCursor(addToThis);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public XmlObject getObject() {
        return (XmlObject) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda20
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this._getObject();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public XmlCursor.TokenType currentTokenType() {
        return (XmlCursor.TokenType) syncWrapNoEnter(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda78
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this._currentTokenType();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean isStartdoc() {
        return ((Boolean) syncWrapNoEnter(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda112
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._isStartdoc());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean isEnddoc() {
        return ((Boolean) syncWrapNoEnter(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda13
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._isEnddoc());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean isStart() {
        return ((Boolean) syncWrapNoEnter(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda125
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._isStart());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean isEnd() {
        return ((Boolean) syncWrapNoEnter(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda21
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._isEnd());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean isText() {
        return ((Boolean) syncWrapNoEnter(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda23
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._isText());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean isAttr() {
        return ((Boolean) syncWrapNoEnter(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda74
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._isAttr());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean isNamespace() {
        return ((Boolean) syncWrapNoEnter(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda96
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._isNamespace());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean isComment() {
        return ((Boolean) syncWrapNoEnter(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda70
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._isComment());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean isProcinst() {
        return ((Boolean) syncWrapNoEnter(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda32
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._isProcinst());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean isContainer() {
        return ((Boolean) syncWrapNoEnter(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda25
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._isContainer());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean isFinish() {
        return ((Boolean) syncWrapNoEnter(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda118
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._isFinish());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean isAnyAttr() {
        return ((Boolean) syncWrapNoEnter(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda102
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._isAnyAttr());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public XmlCursor.TokenType prevTokenType() {
        return (XmlCursor.TokenType) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda6
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this._prevTokenType();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean hasNextToken() {
        return ((Boolean) syncWrapNoEnter(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda97
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._hasNextToken());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean hasPrevToken() {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda58
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._hasPrevToken());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public XmlCursor.TokenType toNextToken() {
        return (XmlCursor.TokenType) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda114
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this._toNextToken();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public XmlCursor.TokenType toPrevToken() {
        return (XmlCursor.TokenType) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda94
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this._toPrevToken();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public XmlCursor.TokenType toFirstContentToken() {
        return (XmlCursor.TokenType) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this._toFirstContentToken();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public XmlCursor.TokenType toEndToken() {
        return (XmlCursor.TokenType) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda31
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this._toEndToken();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$toNextChar$29$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Integer m2677lambda$toNextChar$29$orgapachexmlbeansimplstoreCursor(int cch) {
        return Integer.valueOf(_toNextChar(cch));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public int toNextChar(final int cch) {
        return ((Integer) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda64
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2677lambda$toNextChar$29$orgapachexmlbeansimplstoreCursor(cch);
            }
        })).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$toPrevChar$30$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Integer m2682lambda$toPrevChar$30$orgapachexmlbeansimplstoreCursor(int cch) {
        return Integer.valueOf(_toPrevChar(cch));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public int toPrevChar(final int cch) {
        return ((Integer) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda92
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2682lambda$toPrevChar$30$orgapachexmlbeansimplstoreCursor(cch);
            }
        })).intValue();
    }

    public boolean ___toNextSibling() {
        if (!this._cur.hasParent()) {
            return false;
        }
        Xobj parent = this._cur.getParentNoRoot();
        if (parent == null) {
            this._cur._locale.enter();
            try {
                parent = this._cur.getParent();
            } finally {
                this._cur._locale.exit();
            }
        }
        return Locale.toNextSiblingElement(this._cur, parent);
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toNextSibling() {
        return ((Boolean) syncWrapNoEnter(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda50
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this.___toNextSibling());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toPrevSibling() {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda36
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._toPrevSibling());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toParent() {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda110
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._toParent());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toFirstChild() {
        return ((Boolean) syncWrapNoEnter(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda80
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._toFirstChild());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toLastChild() {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda79
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._toLastChild());
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$toChild$31$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Boolean m2670lambda$toChild$31$orgapachexmlbeansimplstoreCursor(String name) {
        return Boolean.valueOf(_toChild(name));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toChild(final String name) {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda57
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2670lambda$toChild$31$orgapachexmlbeansimplstoreCursor(name);
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$toChild$32$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Boolean m2671lambda$toChild$32$orgapachexmlbeansimplstoreCursor(String namespace, String name) {
        return Boolean.valueOf(_toChild(namespace, name));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toChild(final String namespace, final String name) {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda16
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2671lambda$toChild$32$orgapachexmlbeansimplstoreCursor(namespace, name);
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$toChild$33$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Boolean m2672lambda$toChild$33$orgapachexmlbeansimplstoreCursor(QName name) {
        return Boolean.valueOf(_toChild(name));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toChild(final QName name) {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda69
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2672lambda$toChild$33$orgapachexmlbeansimplstoreCursor(name);
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$toChild$34$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Boolean m2673lambda$toChild$34$orgapachexmlbeansimplstoreCursor(int index) {
        return Boolean.valueOf(_toChild(index));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toChild(final int index) {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda59
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2673lambda$toChild$34$orgapachexmlbeansimplstoreCursor(index);
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$toChild$35$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Boolean m2674lambda$toChild$35$orgapachexmlbeansimplstoreCursor(QName name, int index) {
        return Boolean.valueOf(_toChild(name, index));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toChild(final QName name, final int index) {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda106
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2674lambda$toChild$35$orgapachexmlbeansimplstoreCursor(name, index);
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$toNextSibling$36$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Boolean m2678lambda$toNextSibling$36$orgapachexmlbeansimplstoreCursor(String name) {
        return Boolean.valueOf(_toNextSibling(name));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toNextSibling(final String name) {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda100
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2678lambda$toNextSibling$36$orgapachexmlbeansimplstoreCursor(name);
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$toNextSibling$37$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Boolean m2679lambda$toNextSibling$37$orgapachexmlbeansimplstoreCursor(String namespace, String name) {
        return Boolean.valueOf(_toNextSibling(namespace, name));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toNextSibling(final String namespace, final String name) {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda71
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2679lambda$toNextSibling$37$orgapachexmlbeansimplstoreCursor(namespace, name);
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$toNextSibling$38$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Boolean m2680lambda$toNextSibling$38$orgapachexmlbeansimplstoreCursor(QName name) {
        return Boolean.valueOf(_toNextSibling(name));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toNextSibling(final QName name) {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda66
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2680lambda$toNextSibling$38$orgapachexmlbeansimplstoreCursor(name);
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toFirstAttribute() {
        return ((Boolean) syncWrapNoEnter(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda72
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._toFirstAttribute());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toLastAttribute() {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda86
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._toLastAttribute());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toNextAttribute() {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda119
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._toNextAttribute());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean toPrevAttribute() {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._toPrevAttribute());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public String getAttributeText(final QName attrName) {
        return (String) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda98
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2623lambda$getAttributeText$39$orgapachexmlbeansimplstoreCursor(attrName);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setAttributeText$40$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Boolean m2664lambda$setAttributeText$40$orgapachexmlbeansimplstoreCursor(QName attrName, String value) {
        return Boolean.valueOf(_setAttributeText(attrName, value));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean setAttributeText(final QName attrName, final String value) {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda33
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2664lambda$setAttributeText$40$orgapachexmlbeansimplstoreCursor(attrName, value);
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$removeAttribute$41$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Boolean m2652lambda$removeAttribute$41$orgapachexmlbeansimplstoreCursor(QName attrName) {
        return Boolean.valueOf(_removeAttribute(attrName));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean removeAttribute(final QName attrName) {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda115
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2652lambda$removeAttribute$41$orgapachexmlbeansimplstoreCursor(attrName);
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public String getTextValue() {
        return (String) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda30
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this._getTextValue();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public int getTextValue(final char[] chars, final int offset, final int cch) {
        return ((Integer) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda8
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2626lambda$getTextValue$42$orgapachexmlbeansimplstoreCursor(chars, offset, cch);
            }
        })).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getTextValue$42$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Integer m2626lambda$getTextValue$42$orgapachexmlbeansimplstoreCursor(char[] chars, int offset, int cch) {
        return Integer.valueOf(_getTextValue(chars, offset, cch));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void setTextValue(final String text) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda91
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2667lambda$setTextValue$43$orgapachexmlbeansimplstoreCursor(text);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void setTextValue(final char[] sourceChars, final int offset, final int length) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda103
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2668lambda$setTextValue$44$orgapachexmlbeansimplstoreCursor(sourceChars, offset, length);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public String getChars() {
        return (String) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda39
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this._getChars();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public int getChars(final char[] chars, final int offset, final int cch) {
        return ((Integer) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda27
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2625lambda$getChars$45$orgapachexmlbeansimplstoreCursor(chars, offset, cch);
            }
        })).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getChars$45$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Integer m2625lambda$getChars$45$orgapachexmlbeansimplstoreCursor(char[] chars, int offset, int cch) {
        return Integer.valueOf(_getChars(chars, offset, cch));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void toStartDoc() {
        syncWrapNoEnter(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda75
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this._toStartDoc();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void toEndDoc() {
        syncWrapNoEnter(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda87
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this._toEndDoc();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public XmlCursor execQuery(final String query) {
        return (XmlCursor) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda11
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2619lambda$execQuery$46$orgapachexmlbeansimplstoreCursor(query);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public XmlCursor execQuery(final String query, final XmlOptions options) {
        return (XmlCursor) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda105
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2620lambda$execQuery$47$orgapachexmlbeansimplstoreCursor(query, options);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public XmlCursor.ChangeStamp getDocChangeStamp() {
        return (XmlCursor.ChangeStamp) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda60
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this._getDocChangeStamp();
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void setBookmark(final XmlCursor.XmlBookmark bookmark) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda56
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2665lambda$setBookmark$48$orgapachexmlbeansimplstoreCursor(bookmark);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public XmlCursor.XmlBookmark getBookmark(final Object key) {
        return (XmlCursor.XmlBookmark) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2624lambda$getBookmark$49$orgapachexmlbeansimplstoreCursor(key);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void clearBookmark(final Object key) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda38
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2617lambda$clearBookmark$50$orgapachexmlbeansimplstoreCursor(key);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void getAllBookmarkRefs(final Collection<Object> listToFill) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda113
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2621x328e4062(listToFill);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean removeXml() {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda54
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._removeXml());
            }
        })).booleanValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public boolean removeXmlContents() {
        return ((Boolean) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda65
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(Cursor.this._removeXmlContents());
            }
        })).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$removeChars$52$org-apache-xmlbeans-impl-store-Cursor, reason: not valid java name */
    public /* synthetic */ Integer m2653lambda$removeChars$52$orgapachexmlbeansimplstoreCursor(int cch) {
        return Integer.valueOf(_removeChars(cch));
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public int removeChars(final int cch) {
        return ((Integer) syncWrap(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda77
            @Override // java.util.function.Supplier
            public final Object get() {
                return Cursor.this.m2653lambda$removeChars$52$orgapachexmlbeansimplstoreCursor(cch);
            }
        })).intValue();
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void insertChars(final String text) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda35
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2633lambda$insertChars$53$orgapachexmlbeansimplstoreCursor(text);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void insertElement(final QName name) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda48
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2635lambda$insertElement$54$orgapachexmlbeansimplstoreCursor(name);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void insertElement(final String localName) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda104
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2636lambda$insertElement$55$orgapachexmlbeansimplstoreCursor(localName);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void insertElement(final String localName, final String uri) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda63
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2637lambda$insertElement$56$orgapachexmlbeansimplstoreCursor(localName, uri);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void beginElement(final QName name) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda55
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2614lambda$beginElement$57$orgapachexmlbeansimplstoreCursor(name);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void beginElement(final String localName) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2615lambda$beginElement$58$orgapachexmlbeansimplstoreCursor(localName);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void beginElement(final String localName, final String uri) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda51
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2616lambda$beginElement$59$orgapachexmlbeansimplstoreCursor(localName, uri);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void insertElementWithText(final QName name, final String text) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda28
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2638xe96cf1e5(name, text);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void insertElementWithText(final String localName, final String text) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda62
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2639xb26de926(localName, text);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void insertElementWithText(final String localName, final String uri, final String text) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2640x7b6ee067(localName, uri, text);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void insertAttribute(final String localName) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda107
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2627lambda$insertAttribute$63$orgapachexmlbeansimplstoreCursor(localName);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void insertAttribute(final String localName, final String uri) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda111
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2628lambda$insertAttribute$64$orgapachexmlbeansimplstoreCursor(localName, uri);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void insertAttribute(final QName name) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda18
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2629lambda$insertAttribute$65$orgapachexmlbeansimplstoreCursor(name);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void insertAttributeWithValue(final String name, final String value) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2630x7a892e1f(name, value);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void insertAttributeWithValue(final String name, final String uri, final String value) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda47
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2631x438a2560(name, uri, value);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void insertAttributeWithValue(final QName name, final String value) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda122
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2632xc8b1ca1(name, value);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void insertNamespace(final String prefix, final String namespace) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda26
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2641lambda$insertNamespace$69$orgapachexmlbeansimplstoreCursor(prefix, namespace);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void insertComment(final String text) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda73
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2634lambda$insertComment$70$orgapachexmlbeansimplstoreCursor(text);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlCursor
    public void insertProcInst(final String target, final String text) {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda68
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this.m2642lambda$insertProcInst$71$orgapachexmlbeansimplstoreCursor(target, text);
            }
        });
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public void dump() {
        syncWrap(new Runnable() { // from class: org.apache.xmlbeans.impl.store.Cursor$$ExternalSyntheticLambda117
            @Override // java.lang.Runnable
            public final void run() {
                Cursor.this._dump();
            }
        });
    }

    private void syncWrap(Runnable inner) {
        if (preCheck()) {
            syncWrapHelper(inner, true);
            return;
        }
        synchronized (this._cur._locale) {
            syncWrapHelper(inner, true);
        }
    }

    private <T> T syncWrap(Supplier<T> supplier) {
        T t;
        if (preCheck()) {
            return (T) syncWrapHelper((Supplier) supplier, true);
        }
        synchronized (this._cur._locale) {
            t = (T) syncWrapHelper((Supplier) supplier, true);
        }
        return t;
    }

    private <T> T syncWrapNoEnter(Supplier<T> supplier) {
        T t;
        if (preCheck()) {
            return (T) syncWrapHelper((Supplier) supplier, false);
        }
        synchronized (this._cur._locale) {
            t = (T) syncWrapHelper((Supplier) supplier, false);
        }
        return t;
    }

    private void syncWrapNoEnter(Runnable inner) {
        if (preCheck()) {
            syncWrapHelper(inner, false);
            return;
        }
        synchronized (this._cur._locale) {
            syncWrapHelper(inner, false);
        }
    }

    private void syncWrapSAXEx(WrapSAXEx inner) throws SAXException {
        if (preCheck()) {
            syncWrapHelper(inner);
            return;
        }
        synchronized (this._cur._locale) {
            syncWrapHelper(inner);
        }
    }

    private void syncWrapIOEx(WrapIOEx inner) throws IOException {
        if (preCheck()) {
            syncWrapHelper(inner);
            return;
        }
        synchronized (this._cur._locale) {
            syncWrapHelper(inner);
        }
    }

    private void syncWrapHelper(Runnable inner, boolean enterLocale) {
        Locale l = this._cur._locale;
        if (enterLocale) {
            l.enter();
        }
        try {
            inner.run();
        } finally {
            if (enterLocale) {
                l.exit();
            }
        }
    }

    private <T> T syncWrapHelper(Supplier<T> inner, boolean enterLocale) {
        Locale l = this._cur._locale;
        if (enterLocale) {
            l.enter();
        }
        try {
            return inner.get();
        } finally {
            if (enterLocale) {
                l.exit();
            }
        }
    }

    private void syncWrapHelper(WrapSAXEx inner) throws SAXException {
        Locale l = this._cur._locale;
        l.enter();
        try {
            inner.run();
        } finally {
            l.exit();
        }
    }

    private void syncWrapHelper(WrapIOEx inner) throws IOException {
        Locale l = this._cur._locale;
        l.enter();
        try {
            inner.run();
        } finally {
            l.exit();
        }
    }
}
