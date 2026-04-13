package org.apache.xmlbeans.impl.store;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Chars;
import org.apache.xmlbeans.CDataBookmark;
import org.apache.xmlbeans.QNameCache;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlDocumentProperties;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlLineNumber;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlRuntimeException;
import org.apache.xmlbeans.XmlSaxHandler;
import org.apache.xmlbeans.XmlTokenSource;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ResolverUtil;
import org.apache.xmlbeans.impl.common.SAXHelper;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.apache.xmlbeans.impl.common.XmlLocale;
import org.apache.xmlbeans.impl.logging.XmlBeansLogManager;
import org.apache.xmlbeans.impl.store.Cur;
import org.apache.xmlbeans.impl.store.DomImpl;
import org.apache.xmlbeans.impl.store.Saaj;
import org.apache.xmlbeans.impl.util.ExceptionUtil;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;

/* loaded from: classes11.dex */
public final class Locale implements DOMImplementation, Saaj.SaajCallback, XmlLocale {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int ATTR = 3;
    static final int COMMENT = 4;
    static final int ELEM = 2;
    static final int PROCINST = 5;
    static final int ROOT = 1;
    static final int TEXT = 0;
    static final String _xml1998Uri = "http://www.w3.org/XML/1998/namespace";
    static final String _xmlnsUri = "http://www.w3.org/2000/xmlns/";
    int _cchSrc;
    ChangeListener _changeListeners;
    private CharUtil _charUtil;
    Cur _curPool;
    int _curPoolCount;
    private int _entryCount;
    Cur.Locations _locations;
    boolean _noSync;
    int _numTempFramesLeft;
    int _offSrc;
    DomImpl.Dom _ownerDoc;
    int _posTemp;
    QNameFactory _qnameFactory;
    private ReferenceQueue<Ref> _refQueue;
    Cur _registered;
    Saaj _saaj;
    SchemaTypeLoader _schemaTypeLoader;
    Cur[] _tempFrames;
    boolean _validateOnSet;
    long _versionAll;
    long _versionSansText;
    private static final Logger LOG = XmlBeansLogManager.getLogger(Locale.class);
    static final String _xsi = "http://www.w3.org/2001/XMLSchema-instance";
    static final QName _xsiNil = new QName(_xsi, "nil", "xsi");
    static final QName _xsiType = new QName(_xsi, ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "xsi");
    static final QName _xsiLoc = new QName(_xsi, "schemaLocation", "xsi");
    static final QName _xsiNoLoc = new QName(_xsi, "noNamespaceSchemaLocation", "xsi");
    static final String _openFragUri = "http://www.openuri.org/fragment";
    static final QName _openuriFragment = new QName(_openFragUri, "fragment", "frag");
    static final QName _xmlFragment = new QName("xml-fragment");
    private static final ThreadLocal<SoftReference<ScrubBuffer>> tl_scrubBuffer = ThreadLocal.withInitial(new Supplier() { // from class: org.apache.xmlbeans.impl.store.Locale$$ExternalSyntheticLambda0
        @Override // java.util.function.Supplier
        public final Object get() {
            return Locale.lambda$static$7();
        }
    });
    nthCache _nthCache_A = new nthCache();
    nthCache _nthCache_B = new nthCache();
    domNthCache _domNthCache_A = new domNthCache();
    domNthCache _domNthCache_B = new domNthCache();

    /* loaded from: classes11.dex */
    interface ChangeListener {
        ChangeListener getNextChangeListener();

        void notifyChange();

        void setNextChangeListener(ChangeListener changeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public interface SyncWrapFun<T> {
        T parse(Locale locale) throws XmlException, IOException;
    }

    private Locale(SchemaTypeLoader stl, XmlOptions options) {
        XmlOptions options2 = XmlOptions.maskNull(options);
        this._noSync = options2.isUnsynchronized();
        this._numTempFramesLeft = 8;
        this._tempFrames = new Cur[8];
        this._qnameFactory = new DefaultQNameFactory();
        this._locations = new Cur.Locations(this);
        this._schemaTypeLoader = stl;
        this._validateOnSet = options2.isValidateOnSet();
        this._saaj = options2.getSaaj();
        if (this._saaj != null) {
            this._saaj.setCallback(this);
        }
    }

    public static Locale getLocale(SchemaTypeLoader stl, XmlOptions options) {
        Locale l;
        if (stl == null) {
            stl = XmlBeans.getContextTypeLoader();
        }
        XmlOptions options2 = XmlOptions.maskNull(options);
        Object source = options2.getUseSameLocale();
        if (source == null) {
            return new Locale(stl, options2);
        }
        if (source instanceof Locale) {
            l = (Locale) source;
        } else if (source instanceof XmlTokenSource) {
            l = (Locale) ((XmlTokenSource) source).monitor();
        } else {
            throw new IllegalArgumentException("Source locale not understood: " + source);
        }
        if (l._schemaTypeLoader != stl) {
            throw new IllegalArgumentException("Source locale does not support same schema type loader");
        }
        if (l._saaj != null && l._saaj != options2.getSaaj()) {
            throw new IllegalArgumentException("Source locale does not support same saaj");
        }
        if (l._validateOnSet && !options2.isValidateOnSet()) {
            throw new IllegalArgumentException("Source locale does not support same validate on set");
        }
        return l;
    }

    public static void associateSourceName(Cur c, XmlOptions options) {
        String sourceName = options == null ? null : options.getDocumentSourceName();
        if (sourceName != null) {
            getDocProps(c, true).setSourceName(sourceName);
        }
    }

    public static void autoTypeDocument(Cur c, SchemaType requestedType, XmlOptions options) throws XmlException {
        SchemaType xsiSchemaType;
        QName docElemName;
        if (!c.isRoot()) {
            throw new AssertionError();
        }
        SchemaType optionType = XmlOptions.maskNull(options).getDocumentType();
        if (optionType != null) {
            c.setType(optionType);
            return;
        }
        SchemaType type = null;
        SchemaType schemaType = null;
        if (requestedType == null || requestedType.getName() != null) {
            QName xsiTypeName = c.getXsiTypeName();
            if (xsiTypeName == null) {
                xsiSchemaType = null;
            } else {
                xsiSchemaType = c._locale._schemaTypeLoader.findType(xsiTypeName);
            }
            if (requestedType == null || requestedType.isAssignableFrom(xsiSchemaType)) {
                type = xsiSchemaType;
            }
        }
        if (type == null && (requestedType == null || requestedType.isDocumentType())) {
            if (!c.isRoot()) {
                throw new AssertionError();
            }
            c.push();
            if (c.hasAttrs() || !toFirstChildElement(c) || toNextSiblingElement(c)) {
                docElemName = null;
            } else {
                docElemName = c.getName();
            }
            c.pop();
            if (docElemName != null && (type = c._locale._schemaTypeLoader.findDocumentType(docElemName)) != null && requestedType != null) {
                QName requesteddocElemNameName = requestedType.getDocumentElementName();
                if (!requesteddocElemNameName.equals(docElemName) && !requestedType.isValidSubstitution(docElemName)) {
                    throw new XmlException("Element " + QNameHelper.pretty(docElemName) + " is not a valid " + QNameHelper.pretty(requesteddocElemNameName) + " document or a valid substitution.");
                }
            }
        }
        if (type == null && requestedType == null) {
            c.push();
            if (toFirstNormalAttr(c) && !toNextNormalAttr(c)) {
                schemaType = c._locale._schemaTypeLoader.findAttributeType(c.getName());
            }
            type = schemaType;
            c.pop();
        }
        if (type == null) {
            type = requestedType;
        }
        if (type == null) {
            type = XmlBeans.NO_TYPE;
        }
        c.setType(type);
        if (requestedType != null) {
            if (type.isDocumentType()) {
                verifyDocumentType(c, type.getDocumentElementName());
            } else if (type.isAttributeType()) {
                verifyAttributeType(c, type.getAttributeTypeAttributeName());
            }
        }
    }

    private static boolean namespacesSame(QName n1, QName n2) {
        if (n1 == n2) {
            return true;
        }
        if (n1 == null || n2 == null) {
            return false;
        }
        return Objects.equals(n1.getNamespaceURI(), n2.getNamespaceURI());
    }

    private static void addNamespace(StringBuilder sb, QName name) {
        if (name.getNamespaceURI() == null) {
            sb.append("<no namespace>");
            return;
        }
        sb.append("\"");
        sb.append(name.getNamespaceURI());
        sb.append("\"");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00a5 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00aa A[Catch: all -> 0x00c2, TRY_ENTER, TryCatch #0 {all -> 0x00c2, blocks: (B:5:0x000a, B:8:0x0012, B:11:0x0019, B:13:0x0023, B:16:0x0043, B:17:0x0057, B:19:0x005d, B:20:0x0077, B:25:0x00aa, B:26:0x00c1, B:28:0x0084, B:31:0x00a0), top: B:4:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void verifyDocumentType(org.apache.xmlbeans.impl.store.Cur r5, javax.xml.namespace.QName r6) throws org.apache.xmlbeans.XmlException {
        /*
            boolean r0 = r5.isRoot()
            if (r0 == 0) goto Lc7
            r5.push()
            r0 = 0
            boolean r1 = toFirstChildElement(r5)     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r2 = "The document is not a "
            if (r1 == 0) goto L84
            boolean r1 = toNextSiblingElement(r5)     // Catch: java.lang.Throwable -> Lc2
            if (r1 == 0) goto L19
            goto L84
        L19:
            javax.xml.namespace.QName r1 = r5.getName()     // Catch: java.lang.Throwable -> Lc2
            boolean r3 = r1.equals(r6)     // Catch: java.lang.Throwable -> Lc2
            if (r3 != 0) goto La3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc2
            r3.<init>()     // Catch: java.lang.Throwable -> Lc2
            r0 = r3
            r0.append(r2)     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r2 = org.apache.xmlbeans.impl.common.QNameHelper.pretty(r6)     // Catch: java.lang.Throwable -> Lc2
            r0.append(r2)     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r2 = r6.getLocalPart()     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r3 = r1.getLocalPart()     // Catch: java.lang.Throwable -> Lc2
            boolean r2 = r2.equals(r3)     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r3 = " got "
            if (r2 == 0) goto L57
            java.lang.String r2 = ": document element namespace mismatch "
            r0.append(r2)     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r2 = "expected "
            r0.append(r2)     // Catch: java.lang.Throwable -> Lc2
            addNamespace(r0, r6)     // Catch: java.lang.Throwable -> Lc2
            r0.append(r3)     // Catch: java.lang.Throwable -> Lc2
            addNamespace(r0, r1)     // Catch: java.lang.Throwable -> Lc2
            goto La3
        L57:
            boolean r2 = namespacesSame(r6, r1)     // Catch: java.lang.Throwable -> Lc2
            if (r2 == 0) goto L77
            java.lang.String r2 = ": document element local name mismatch expected "
            java.lang.StringBuilder r2 = r0.append(r2)     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r4 = r6.getLocalPart()     // Catch: java.lang.Throwable -> Lc2
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch: java.lang.Throwable -> Lc2
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r3 = r1.getLocalPart()     // Catch: java.lang.Throwable -> Lc2
            r2.append(r3)     // Catch: java.lang.Throwable -> Lc2
            goto La3
        L77:
            java.lang.String r2 = ": document element mismatch got "
            r0.append(r2)     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r2 = org.apache.xmlbeans.impl.common.QNameHelper.pretty(r1)     // Catch: java.lang.Throwable -> Lc2
            r0.append(r2)     // Catch: java.lang.Throwable -> Lc2
            goto La3
        L84:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc2
            r1.<init>()     // Catch: java.lang.Throwable -> Lc2
            r0 = r1
            r0.append(r2)     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r1 = org.apache.xmlbeans.impl.common.QNameHelper.pretty(r6)     // Catch: java.lang.Throwable -> Lc2
            r0.append(r1)     // Catch: java.lang.Throwable -> Lc2
            boolean r1 = r5.isRoot()     // Catch: java.lang.Throwable -> Lc2
            if (r1 == 0) goto L9e
            java.lang.String r1 = ": no document element"
            goto La0
        L9e:
            java.lang.String r1 = ": multiple document elements"
        La0:
            r0.append(r1)     // Catch: java.lang.Throwable -> Lc2
        La3:
            if (r0 != 0) goto Laa
            r5.pop()
            return
        Laa:
            java.lang.String r1 = r0.toString()     // Catch: java.lang.Throwable -> Lc2
            org.apache.xmlbeans.impl.store.Cursor r2 = new org.apache.xmlbeans.impl.store.Cursor     // Catch: java.lang.Throwable -> Lc2
            r2.<init>(r5)     // Catch: java.lang.Throwable -> Lc2
            org.apache.xmlbeans.XmlError r1 = org.apache.xmlbeans.XmlError.forCursor(r1, r2)     // Catch: java.lang.Throwable -> Lc2
            org.apache.xmlbeans.XmlException r2 = new org.apache.xmlbeans.XmlException     // Catch: java.lang.Throwable -> Lc2
            java.lang.String r3 = r1.toString()     // Catch: java.lang.Throwable -> Lc2
            r4 = 0
            r2.<init>(r3, r4, r1)     // Catch: java.lang.Throwable -> Lc2
            throw r2     // Catch: java.lang.Throwable -> Lc2
        Lc2:
            r0 = move-exception
            r5.pop()
            throw r0
        Lc7:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Locale.verifyDocumentType(org.apache.xmlbeans.impl.store.Cur, javax.xml.namespace.QName):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00ad A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b2 A[Catch: all -> 0x00ca, TRY_ENTER, TryCatch #0 {all -> 0x00ca, blocks: (B:5:0x000a, B:8:0x0012, B:11:0x001a, B:13:0x0024, B:16:0x0046, B:17:0x0058, B:19:0x005e, B:20:0x007a, B:25:0x00b2, B:26:0x00c9, B:28:0x008c, B:31:0x00a8), top: B:4:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void verifyAttributeType(org.apache.xmlbeans.impl.store.Cur r5, javax.xml.namespace.QName r6) throws org.apache.xmlbeans.XmlException {
        /*
            boolean r0 = r5.isRoot()
            if (r0 == 0) goto Lcf
            r5.push()
            r0 = 0
            boolean r1 = toFirstNormalAttr(r5)     // Catch: java.lang.Throwable -> Lca
            java.lang.String r2 = "The document is not a "
            if (r1 == 0) goto L8c
            boolean r1 = toNextNormalAttr(r5)     // Catch: java.lang.Throwable -> Lca
            if (r1 == 0) goto L1a
            goto L8c
        L1a:
            javax.xml.namespace.QName r1 = r5.getName()     // Catch: java.lang.Throwable -> Lca
            boolean r3 = r1.equals(r6)     // Catch: java.lang.Throwable -> Lca
            if (r3 != 0) goto Lab
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lca
            r3.<init>()     // Catch: java.lang.Throwable -> Lca
            r0 = r3
            r0.append(r2)     // Catch: java.lang.Throwable -> Lca
            java.lang.String r2 = org.apache.xmlbeans.impl.common.QNameHelper.pretty(r6)     // Catch: java.lang.Throwable -> Lca
            r0.append(r2)     // Catch: java.lang.Throwable -> Lca
            java.lang.String r2 = r6.getLocalPart()     // Catch: java.lang.Throwable -> Lca
            java.lang.String r3 = r1.getLocalPart()     // Catch: java.lang.Throwable -> Lca
            boolean r2 = r2.equals(r3)     // Catch: java.lang.Throwable -> Lca
            java.lang.String r3 = " got "
            java.lang.String r4 = "expected "
            if (r2 == 0) goto L58
            java.lang.String r2 = ": attribute namespace mismatch "
            r0.append(r2)     // Catch: java.lang.Throwable -> Lca
            r0.append(r4)     // Catch: java.lang.Throwable -> Lca
            addNamespace(r0, r6)     // Catch: java.lang.Throwable -> Lca
            r0.append(r3)     // Catch: java.lang.Throwable -> Lca
            addNamespace(r0, r1)     // Catch: java.lang.Throwable -> Lca
            goto Lab
        L58:
            boolean r2 = namespacesSame(r6, r1)     // Catch: java.lang.Throwable -> Lca
            if (r2 == 0) goto L7a
            java.lang.String r2 = ": attribute local name mismatch "
            r0.append(r2)     // Catch: java.lang.Throwable -> Lca
            java.lang.StringBuilder r2 = r0.append(r4)     // Catch: java.lang.Throwable -> Lca
            java.lang.String r4 = r6.getLocalPart()     // Catch: java.lang.Throwable -> Lca
            r2.append(r4)     // Catch: java.lang.Throwable -> Lca
            java.lang.StringBuilder r2 = r0.append(r3)     // Catch: java.lang.Throwable -> Lca
            java.lang.String r3 = r1.getLocalPart()     // Catch: java.lang.Throwable -> Lca
            r2.append(r3)     // Catch: java.lang.Throwable -> Lca
            goto Lab
        L7a:
            java.lang.String r2 = ": attribute element mismatch "
            r0.append(r2)     // Catch: java.lang.Throwable -> Lca
            java.lang.String r2 = "got "
            r0.append(r2)     // Catch: java.lang.Throwable -> Lca
            java.lang.String r2 = org.apache.xmlbeans.impl.common.QNameHelper.pretty(r1)     // Catch: java.lang.Throwable -> Lca
            r0.append(r2)     // Catch: java.lang.Throwable -> Lca
            goto Lab
        L8c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lca
            r1.<init>()     // Catch: java.lang.Throwable -> Lca
            r0 = r1
            r0.append(r2)     // Catch: java.lang.Throwable -> Lca
            java.lang.String r1 = org.apache.xmlbeans.impl.common.QNameHelper.pretty(r6)     // Catch: java.lang.Throwable -> Lca
            r0.append(r1)     // Catch: java.lang.Throwable -> Lca
            boolean r1 = r5.isRoot()     // Catch: java.lang.Throwable -> Lca
            if (r1 == 0) goto La6
            java.lang.String r1 = ": no attributes"
            goto La8
        La6:
            java.lang.String r1 = ": multiple attributes"
        La8:
            r0.append(r1)     // Catch: java.lang.Throwable -> Lca
        Lab:
            if (r0 != 0) goto Lb2
            r5.pop()
            return
        Lb2:
            java.lang.String r1 = r0.toString()     // Catch: java.lang.Throwable -> Lca
            org.apache.xmlbeans.impl.store.Cursor r2 = new org.apache.xmlbeans.impl.store.Cursor     // Catch: java.lang.Throwable -> Lca
            r2.<init>(r5)     // Catch: java.lang.Throwable -> Lca
            org.apache.xmlbeans.XmlError r1 = org.apache.xmlbeans.XmlError.forCursor(r1, r2)     // Catch: java.lang.Throwable -> Lca
            org.apache.xmlbeans.XmlException r2 = new org.apache.xmlbeans.XmlException     // Catch: java.lang.Throwable -> Lca
            java.lang.String r3 = r1.toString()     // Catch: java.lang.Throwable -> Lca
            r4 = 0
            r2.<init>(r3, r4, r1)     // Catch: java.lang.Throwable -> Lca
            throw r2     // Catch: java.lang.Throwable -> Lca
        Lca:
            r0 = move-exception
            r5.pop()
            throw r0
        Lcf:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Locale.verifyAttributeType(org.apache.xmlbeans.impl.store.Cur, javax.xml.namespace.QName):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isFragmentQName(QName name) {
        return name.equals(_openuriFragment) || name.equals(_xmlFragment);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isFragment(Cur start, Cur end) {
        int k;
        if (end.isAttr()) {
            throw new AssertionError();
        }
        start.push();
        end.push();
        int numDocElems = 0;
        boolean isFrag = false;
        while (true) {
            if (!start.isSamePos(end) && (k = start.kind()) != 3) {
                if (k == 0 && !isWhiteSpace(start.getCharsAsString())) {
                    isFrag = true;
                    break;
                }
                if (k == 2 && (numDocElems = numDocElems + 1) > 1) {
                    isFrag = true;
                    break;
                }
                if (k != 0) {
                    start.toEnd();
                }
                start.next();
            } else {
                break;
            }
        }
        start.pop();
        end.pop();
        return isFrag || numDocElems != 1;
    }

    public static XmlObject newInstance(SchemaTypeLoader stl, final SchemaType type, final XmlOptions options) {
        try {
            return (XmlObject) syncWrap(stl, options, new SyncWrapFun() { // from class: org.apache.xmlbeans.impl.store.Locale$$ExternalSyntheticLambda6
                @Override // org.apache.xmlbeans.impl.store.Locale.SyncWrapFun
                public final Object parse(Locale locale) {
                    return Locale.lambda$newInstance$0(XmlOptions.this, type, locale);
                }
            });
        } catch (IOException | XmlException e) {
            throw new AssertionError("newInstance doesn't throw XmlException or IOException");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlObject lambda$newInstance$0(XmlOptions options, SchemaType type, Locale l) throws XmlException, IOException {
        Cur c = l.tempCur();
        SchemaType sType = XmlOptions.maskNull(options).getDocumentType();
        if (sType == null) {
            sType = type == null ? XmlObject.type : type;
        }
        if (sType.isDocumentType()) {
            c.createDomDocumentRoot();
        } else {
            c.createRoot();
        }
        c.setType(sType);
        XmlObject x = (XmlObject) c.getUser();
        c.release();
        return x;
    }

    public static DOMImplementation newDomImplementation(SchemaTypeLoader stl, XmlOptions options) {
        return getLocale(stl, options);
    }

    private static <T> T syncWrap(SchemaTypeLoader stl, XmlOptions options, SyncWrapFun<T> fun) throws XmlException, IOException {
        T parse;
        Locale l = getLocale(stl, options);
        if (l.noSync()) {
            l.enter();
            try {
                return fun.parse(l);
            } finally {
            }
        }
        synchronized (l) {
            l.enter();
            try {
                parse = fun.parse(l);
            } finally {
            }
        }
        return parse;
    }

    public static XmlObject parseToXmlObject(SchemaTypeLoader stl, final String xmlText, final SchemaType type, final XmlOptions options) throws XmlException {
        try {
            return (XmlObject) syncWrap(stl, options, new SyncWrapFun() { // from class: org.apache.xmlbeans.impl.store.Locale$$ExternalSyntheticLambda7
                @Override // org.apache.xmlbeans.impl.store.Locale.SyncWrapFun
                public final Object parse(Locale locale) {
                    return Locale.lambda$parseToXmlObject$1(xmlText, options, type, locale);
                }
            });
        } catch (IOException e) {
            throw new AssertionError("StringReader should not throw IOException");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlObject lambda$parseToXmlObject$1(String xmlText, XmlOptions options, SchemaType type, Locale l) throws XmlException, IOException {
        Reader r = new StringReader(xmlText);
        try {
            Cur c = getSaxLoader(options).load(l, new InputSource(r), options);
            autoTypeDocument(c, type, options);
            XmlObject x = (XmlObject) c.getUser();
            c.release();
            r.close();
            return x;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    r.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public static XmlObject parseToXmlObject(SchemaTypeLoader stl, final XMLStreamReader xsr, final SchemaType type, final XmlOptions options) throws XmlException {
        try {
            return (XmlObject) syncWrap(stl, options, new SyncWrapFun() { // from class: org.apache.xmlbeans.impl.store.Locale$$ExternalSyntheticLambda4
                @Override // org.apache.xmlbeans.impl.store.Locale.SyncWrapFun
                public final Object parse(Locale locale) {
                    return Locale.lambda$parseToXmlObject$2(xsr, options, type, locale);
                }
            });
        } catch (IOException e) {
            throw new AssertionError("doesn't throw IOException");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlObject lambda$parseToXmlObject$2(XMLStreamReader xsr, XmlOptions options, SchemaType type, Locale l) throws XmlException, IOException {
        try {
            Cur c = l.loadXMLStreamReader(xsr, options);
            autoTypeDocument(c, type, options);
            XmlObject x = (XmlObject) c.getUser();
            c.release();
            return x;
        } catch (XMLStreamException e) {
            throw new XmlException(e.getMessage(), e);
        }
    }

    private static void lineNumber(XMLStreamReader xsr, LoadContext context) {
        Location loc = xsr.getLocation();
        if (loc != null) {
            context.lineNumber(loc.getLineNumber(), loc.getColumnNumber(), loc.getCharacterOffset());
        }
    }

    private void doAttributes(XMLStreamReader xsr, LoadContext context) {
        int n = xsr.getAttributeCount();
        for (int a = 0; a < n; a++) {
            context.attr(xsr.getAttributeLocalName(a), xsr.getAttributeNamespace(a), xsr.getAttributePrefix(a), xsr.getAttributeValue(a));
        }
    }

    private void doNamespaces(XMLStreamReader xsr, LoadContext context) {
        int n = xsr.getNamespaceCount();
        for (int a = 0; a < n; a++) {
            String prefix = xsr.getNamespacePrefix(a);
            if (prefix == null || prefix.isEmpty()) {
                context.attr(Sax2Dom.XMLNS_PREFIX, "http://www.w3.org/2000/xmlns/", null, xsr.getNamespaceURI(a));
            } else {
                context.attr(prefix, "http://www.w3.org/2000/xmlns/", Sax2Dom.XMLNS_PREFIX, xsr.getNamespaceURI(a));
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0015. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00bb A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c4 A[ADDED_TO_REGION, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.apache.xmlbeans.impl.store.Cur loadXMLStreamReader(javax.xml.stream.XMLStreamReader r11, org.apache.xmlbeans.XmlOptions r12) throws javax.xml.stream.XMLStreamException {
        /*
            r10 = this;
            org.apache.xmlbeans.XmlOptions r12 = org.apache.xmlbeans.XmlOptions.maskNull(r12)
            boolean r0 = r12.isLoadLineNumbers()
            r1 = 0
            r2 = 0
            r3 = 0
            org.apache.xmlbeans.impl.store.Cur$CurLoadContext r4 = new org.apache.xmlbeans.impl.store.Cur$CurLoadContext
            r4.<init>(r10, r12)
            r5 = 0
            int r6 = r11.getEventType()
        L15:
            switch(r6) {
                case 1: goto La0;
                case 2: goto L95;
                case 3: goto L84;
                case 4: goto L6f;
                case 5: goto L62;
                case 6: goto L61;
                case 7: goto L4d;
                case 8: goto L44;
                case 9: goto L3b;
                case 10: goto L36;
                case 11: goto L61;
                case 12: goto L6f;
                case 13: goto L31;
                default: goto L18;
            }
        L18:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Unhandled xml event type: "
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.StringBuilder r8 = r8.append(r6)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L31:
            r10.doNamespaces(r11, r4)
            goto Lb5
        L36:
            r10.doAttributes(r11, r4)
            goto Lb5
        L3b:
            java.lang.String r7 = r11.getText()
            r4.text(r7)
            goto Lb5
        L44:
            int r5 = r5 + (-1)
            if (r0 == 0) goto Lc4
            lineNumber(r11, r4)
            goto Lc4
        L4d:
            int r5 = r5 + 1
            java.lang.String r1 = r11.getCharacterEncodingScheme()
            java.lang.String r2 = r11.getVersion()
            boolean r3 = r11.isStandalone()
            if (r0 == 0) goto Lb5
            lineNumber(r11, r4)
            goto Lb5
        L61:
            goto Lb5
        L62:
            java.lang.String r7 = r11.getText()
            r4.comment(r7)
            if (r0 == 0) goto Lb5
            lineNumber(r11, r4)
            goto Lb5
        L6f:
            char[] r7 = r11.getTextCharacters()
            int r8 = r11.getTextStart()
            int r9 = r11.getTextLength()
            r4.text(r7, r8, r9)
            if (r0 == 0) goto Lb5
            lineNumber(r11, r4)
            goto Lb5
        L84:
            java.lang.String r7 = r11.getPITarget()
            java.lang.String r8 = r11.getPIData()
            r4.procInst(r7, r8)
            if (r0 == 0) goto Lb5
            lineNumber(r11, r4)
            goto Lb5
        L95:
            int r5 = r5 + (-1)
            r4.endElement()
            if (r0 == 0) goto Lb5
            lineNumber(r11, r4)
            goto Lb5
        La0:
            int r5 = r5 + 1
            javax.xml.namespace.QName r7 = r11.getName()
            r4.startElement(r7)
            if (r0 == 0) goto Lae
            lineNumber(r11, r4)
        Lae:
            r10.doAttributes(r11, r4)
            r10.doNamespaces(r11, r4)
        Lb5:
            boolean r7 = r11.hasNext()
            if (r7 == 0) goto Lc4
            if (r5 > 0) goto Lbe
            goto Lc4
        Lbe:
            int r6 = r11.next()
            goto L15
        Lc4:
            org.apache.xmlbeans.impl.store.Cur r6 = r4.finish()
            associateSourceName(r6, r12)
            r7 = 1
            org.apache.xmlbeans.XmlDocumentProperties r7 = getDocProps(r6, r7)
            r7.setEncoding(r1)
            r7.setVersion(r2)
            r7.setStandalone(r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Locale.loadXMLStreamReader(javax.xml.stream.XMLStreamReader, org.apache.xmlbeans.XmlOptions):org.apache.xmlbeans.impl.store.Cur");
    }

    public static XmlObject parseToXmlObject(SchemaTypeLoader stl, final InputStream is, final SchemaType type, final XmlOptions options) throws XmlException, IOException {
        return (XmlObject) syncWrap(stl, options, new SyncWrapFun() { // from class: org.apache.xmlbeans.impl.store.Locale$$ExternalSyntheticLambda5
            @Override // org.apache.xmlbeans.impl.store.Locale.SyncWrapFun
            public final Object parse(Locale locale) {
                return Locale.lambda$parseToXmlObject$3(XmlOptions.this, is, type, locale);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlObject lambda$parseToXmlObject$3(XmlOptions options, InputStream is, SchemaType type, Locale l) throws XmlException, IOException {
        Cur c = getSaxLoader(options).load(l, new InputSource(is), options);
        autoTypeDocument(c, type, options);
        XmlObject x = (XmlObject) c.getUser();
        c.release();
        return x;
    }

    public static XmlObject parseToXmlObject(SchemaTypeLoader stl, final Reader reader, final SchemaType type, final XmlOptions options) throws XmlException, IOException {
        return (XmlObject) syncWrap(stl, options, new SyncWrapFun() { // from class: org.apache.xmlbeans.impl.store.Locale$$ExternalSyntheticLambda2
            @Override // org.apache.xmlbeans.impl.store.Locale.SyncWrapFun
            public final Object parse(Locale locale) {
                return Locale.lambda$parseToXmlObject$4(XmlOptions.this, reader, type, locale);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlObject lambda$parseToXmlObject$4(XmlOptions options, Reader reader, SchemaType type, Locale l) throws XmlException, IOException {
        Cur c = getSaxLoader(options).load(l, new InputSource(reader), options);
        autoTypeDocument(c, type, options);
        XmlObject x = (XmlObject) c.getUser();
        c.release();
        return x;
    }

    public static XmlObject parseToXmlObject(SchemaTypeLoader stl, final Node node, final SchemaType type, final XmlOptions options) throws XmlException {
        try {
            return (XmlObject) syncWrap(stl, options, new SyncWrapFun() { // from class: org.apache.xmlbeans.impl.store.Locale$$ExternalSyntheticLambda3
                @Override // org.apache.xmlbeans.impl.store.Locale.SyncWrapFun
                public final Object parse(Locale locale) {
                    return Locale.lambda$parseToXmlObject$5(XmlOptions.this, node, type, locale);
                }
            });
        } catch (IOException e) {
            throw new AssertionError("Doesn't throw IOException");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlObject lambda$parseToXmlObject$5(XmlOptions options, Node node, SchemaType type, Locale l) throws XmlException, IOException {
        LoadContext context = new Cur.CurLoadContext(l, options);
        l.loadNode(node, context);
        Cur c = context.finish();
        associateSourceName(c, options);
        autoTypeDocument(c, type, options);
        XmlObject x = (XmlObject) c.getUser();
        c.release();
        return x;
    }

    private void loadNodeChildren(Node n, LoadContext context) {
        for (Node c = n.getFirstChild(); c != null; c = c.getNextSibling()) {
            loadNode(c, context);
        }
    }

    public void loadNode(Node n, LoadContext context) {
        switch (n.getNodeType()) {
            case 1:
                context.startElement(makeQualifiedQName(n.getNamespaceURI(), n.getNodeName()));
                NamedNodeMap attrs = n.getAttributes();
                for (int i = 0; i < attrs.getLength(); i++) {
                    Node a = attrs.item(i);
                    String attrName = a.getNodeName();
                    String attrValue = a.getNodeValue();
                    if (attrName.toLowerCase(java.util.Locale.ROOT).startsWith(Sax2Dom.XMLNS_PREFIX)) {
                        if (attrName.length() == 5) {
                            context.xmlns(null, attrValue);
                        } else {
                            context.xmlns(attrName.substring(6), attrValue);
                        }
                    } else {
                        context.attr(makeQualifiedQName(a.getNamespaceURI(), attrName), attrValue);
                    }
                }
                loadNodeChildren(n, context);
                context.endElement();
                return;
            case 2:
                throw new RuntimeException("Unexpected node");
            case 3:
            case 4:
                context.text(n.getNodeValue());
                return;
            case 5:
            case 9:
            case 11:
                loadNodeChildren(n, context);
                return;
            case 6:
            case 10:
            case 12:
                Node next = n.getNextSibling();
                if (next != null) {
                    loadNode(next, context);
                    return;
                }
                return;
            case 7:
                context.procInst(n.getNodeName(), n.getNodeValue());
                return;
            case 8:
                context.comment(n.getNodeValue());
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class XmlSaxHandlerImpl extends SaxHandler implements XmlSaxHandler {
        private final XmlOptions _options;
        private final SchemaType _type;

        XmlSaxHandlerImpl(Locale l, SchemaType type, XmlOptions options) {
            super(null);
            this._options = options;
            this._type = type;
            XmlOptions saxHandlerOptions = new XmlOptions(options);
            saxHandlerOptions.setLoadUseLocaleCharUtil(true);
            initSaxHandler(l, saxHandlerOptions);
        }

        @Override // org.apache.xmlbeans.XmlSaxHandler
        public ContentHandler getContentHandler() {
            if (this._context == null) {
                return null;
            }
            return this;
        }

        @Override // org.apache.xmlbeans.XmlSaxHandler
        public LexicalHandler getLexicalHandler() {
            if (this._context == null) {
                return null;
            }
            return this;
        }

        @Override // org.apache.xmlbeans.XmlSaxHandler
        public void bookmarkLastEvent(XmlCursor.XmlBookmark mark) {
            this._context.bookmarkLastNonAttr(mark);
        }

        @Override // org.apache.xmlbeans.XmlSaxHandler
        public void bookmarkLastAttr(QName attrName, XmlCursor.XmlBookmark mark) {
            this._context.bookmarkLastAttr(attrName, mark);
        }

        @Override // org.apache.xmlbeans.XmlSaxHandler
        public XmlObject getObject() throws XmlException {
            if (this._context == null) {
                return null;
            }
            this._locale.enter();
            try {
                Cur c = this._context.finish();
                Locale.autoTypeDocument(c, this._type, this._options);
                XmlObject x = (XmlObject) c.getUser();
                c.release();
                this._context = null;
                return x;
            } finally {
                this._locale.exit();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlSaxHandlerImpl lambda$newSaxHandler$6(SchemaType type, XmlOptions options, Locale l) throws XmlException, IOException {
        return new XmlSaxHandlerImpl(l, type, options);
    }

    public static XmlSaxHandler newSaxHandler(SchemaTypeLoader stl, final SchemaType type, final XmlOptions options) {
        try {
            return (XmlSaxHandler) syncWrap(stl, options, new SyncWrapFun() { // from class: org.apache.xmlbeans.impl.store.Locale$$ExternalSyntheticLambda1
                @Override // org.apache.xmlbeans.impl.store.Locale.SyncWrapFun
                public final Object parse(Locale locale) {
                    return Locale.lambda$newSaxHandler$6(SchemaType.this, options, locale);
                }
            });
        } catch (IOException | XmlException e) {
            throw new AssertionError("XmlException or IOException is not thrown");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public QName makeQName(String uri, String localPart) {
        if (localPart == null || localPart.length() <= 0) {
            throw new AssertionError();
        }
        return this._qnameFactory.getQName(uri, localPart);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public QName makeQNameNoCheck(String uri, String localPart) {
        return this._qnameFactory.getQName(uri, localPart);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public QName makeQName(String uri, String local, String prefix) {
        return this._qnameFactory.getQName(uri, local, prefix == null ? "" : prefix);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public QName makeQualifiedQName(String uri, String qname) {
        if (qname == null) {
            qname = "";
        }
        int i = qname.indexOf(58);
        if (i < 0) {
            return this._qnameFactory.getQName(uri, qname);
        }
        return this._qnameFactory.getQName(uri, qname.substring(i + 1), qname.substring(0, i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class DocProps extends XmlDocumentProperties {
        private final HashMap<Object, Object> _map;

        private DocProps() {
            this._map = new HashMap<>();
        }

        @Override // org.apache.xmlbeans.XmlDocumentProperties
        public Object put(Object key, Object value) {
            return this._map.put(key, value);
        }

        @Override // org.apache.xmlbeans.XmlDocumentProperties
        public Object get(Object key) {
            return this._map.get(key);
        }

        @Override // org.apache.xmlbeans.XmlDocumentProperties
        public Object remove(Object key) {
            return this._map.remove(key);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static XmlDocumentProperties getDocProps(Cur c, boolean ensure) {
        c.push();
        do {
        } while (c.toParent());
        DocProps props = (DocProps) c.getBookmark(DocProps.class);
        if (props == null && ensure) {
            DocProps docProps = new DocProps();
            props = docProps;
            c.setBookmark(DocProps.class, docProps);
        }
        c.pop();
        return props;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerForChange(ChangeListener listener) {
        if (listener.getNextChangeListener() == null) {
            if (this._changeListeners == null) {
                listener.setNextChangeListener(listener);
            } else {
                listener.setNextChangeListener(this._changeListeners);
            }
            this._changeListeners = listener;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyChange() {
        while (this._changeListeners != null) {
            this._changeListeners.notifyChange();
            if (this._changeListeners.getNextChangeListener() == this._changeListeners) {
                this._changeListeners.setNextChangeListener(null);
            }
            ChangeListener next = this._changeListeners.getNextChangeListener();
            this._changeListeners.setNextChangeListener(null);
            this._changeListeners = next;
        }
        this._locations.notifyChange();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getTextValue(Cur c) {
        if (!c.isNode()) {
            throw new AssertionError();
        }
        if (!c.hasChildren()) {
            return c.getValueAsString();
        }
        StringBuffer sb = new StringBuffer();
        c.push();
        while (true) {
            c.next();
            if (!c.isAtEndOfLastPush()) {
                if (c.isText() && ((!c._xobj.isComment() && !c._xobj.isProcinst()) || c._pos >= c._xobj._cchValue)) {
                    CharUtil.getString(sb, c.getChars(-1), c._offSrc, c._cchSrc);
                }
            } else {
                c.pop();
                return sb.toString();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getTextValue(Cur c, char[] chars, int off, int maxCch) {
        if (!c.isNode()) {
            throw new AssertionError();
        }
        String s = c._xobj.getValueAsString(1);
        int n = s.length();
        if (n > maxCch) {
            n = maxCch;
        }
        if (n <= 0) {
            return 0;
        }
        s.getChars(0, n, chars, off);
        return n;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String applyWhiteSpaceRule(String s, int wsr) {
        int l = s == null ? 0 : s.length();
        if (l == 0 || wsr == 1) {
            return s;
        }
        if (wsr == 2) {
            for (int i = 0; i < l; i++) {
                char ch = s.charAt(i);
                if (ch == '\n' || ch == '\r' || ch == '\t') {
                    return processWhiteSpaceRule(s, wsr);
                }
            }
        } else if (wsr == 3) {
            if (CharUtil.isWhiteSpace(s.charAt(0)) || CharUtil.isWhiteSpace(s.charAt(l - 1))) {
                return processWhiteSpaceRule(s, wsr);
            }
            boolean lastWasWhite = false;
            for (int i2 = 1; i2 < l; i2++) {
                boolean isWhite = CharUtil.isWhiteSpace(s.charAt(i2));
                if (isWhite && lastWasWhite) {
                    return processWhiteSpaceRule(s, wsr);
                }
                lastWasWhite = isWhite;
            }
        }
        return s;
    }

    static String processWhiteSpaceRule(String s, int wsr) {
        ScrubBuffer sb = getScrubBuffer(wsr);
        sb.scrub(s, 0, s.length());
        return sb.getResultAsString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class ScrubBuffer {
        private static final int NOSPACE_STATE = 2;
        private static final int SPACE_SEEN_STATE = 1;
        private static final int START_STATE = 0;
        private int _state;
        private int _wsr;
        private char[] _srcBuf = new char[1024];
        private final StringBuffer _sb = new StringBuffer();

        ScrubBuffer() {
        }

        void init(int wsr) {
            this._sb.delete(0, this._sb.length());
            this._wsr = wsr;
            this._state = 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void scrub(Object src, int off, int cch) {
            char[] chars;
            if (cch == 0) {
                return;
            }
            if (this._wsr == 1) {
                CharUtil.getString(this._sb, src, off, cch);
                return;
            }
            if (src instanceof char[]) {
                chars = (char[]) src;
            } else {
                char[] chars2 = this._srcBuf;
                if (cch <= chars2.length) {
                    chars = this._srcBuf;
                } else if (cch <= 16384) {
                    chars = new char[16384];
                    this._srcBuf = chars;
                } else {
                    chars = new char[cch];
                }
                CharUtil.getChars(chars, 0, src, off, cch);
                off = 0;
            }
            int start = 0;
            for (int i = 0; i < cch; i++) {
                char ch = chars[off + i];
                if (ch == ' ' || ch == '\n' || ch == '\r' || ch == '\t') {
                    this._sb.append(chars, off + start, i - start);
                    start = i + 1;
                    if (this._wsr != 2) {
                        if (this._state == 2) {
                            this._state = 1;
                        }
                    } else {
                        this._sb.append(Chars.SPACE);
                    }
                } else {
                    if (this._state == 1) {
                        this._sb.append(Chars.SPACE);
                    }
                    this._state = 2;
                }
            }
            this._sb.append(chars, off + start, cch - start);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getResultAsString() {
            return this._sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SoftReference lambda$static$7() {
        return new SoftReference(new ScrubBuffer());
    }

    public static void clearThreadLocals() {
        tl_scrubBuffer.remove();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ScrubBuffer getScrubBuffer(int wsr) {
        SoftReference<ScrubBuffer> softRef = tl_scrubBuffer.get();
        ScrubBuffer scrubBuffer = softRef.get();
        if (scrubBuffer == null) {
            scrubBuffer = new ScrubBuffer();
            tl_scrubBuffer.set(new SoftReference<>(scrubBuffer));
        }
        scrubBuffer.init(wsr);
        return scrubBuffer;
    }

    static boolean pushToContainer(Cur c) {
        c.push();
        while (true) {
            switch (c.kind()) {
                case -2:
                case -1:
                    c.pop();
                    return false;
                case 0:
                case 3:
                default:
                    c.nextWithAttrs();
                    break;
                case 1:
                case 2:
                    return true;
                case 4:
                case 5:
                    c.skip();
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x000f, code lost:
    
        r1.popButStay();
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0013, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x001a, code lost:
    
        r1.pop();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x001e, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0007, code lost:
    
        if (r1.toFirstAttr() != false) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000d, code lost:
    
        if (r1.isXmlns() != false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0018, code lost:
    
        if (r1.toNextAttr() != false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean toFirstNormalAttr(org.apache.xmlbeans.impl.store.Cur r1) {
        /*
            r1.push()
            boolean r0 = r1.toFirstAttr()
            if (r0 == 0) goto L1a
        L9:
            boolean r0 = r1.isXmlns()
            if (r0 != 0) goto L14
            r1.popButStay()
            r0 = 1
            return r0
        L14:
            boolean r0 = r1.toNextAttr()
            if (r0 != 0) goto L9
        L1a:
            r1.pop()
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Locale.toFirstNormalAttr(org.apache.xmlbeans.impl.store.Cur):boolean");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean toPrevNormalAttr(Cur c) {
        if (c.isAttr()) {
            c.push();
            while (c.isAttr()) {
                if (c.prev()) {
                    c.prev();
                    if (!c.isAttr()) {
                        c.prev();
                    }
                    if (c.isNormalAttr()) {
                        c.popButStay();
                        return true;
                    }
                } else {
                    c.pop();
                    return false;
                }
            }
            throw new AssertionError();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean toNextNormalAttr(Cur c) {
        c.push();
        while (c.toNextAttr()) {
            if (!c.isXmlns()) {
                c.popButStay();
                return true;
            }
        }
        c.pop();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Xobj findNthChildElem(Xobj parent, QName name, QNameSet set, int n) {
        Xobj x;
        if (name != null && set != null) {
            throw new AssertionError();
        }
        if (n < 0) {
            throw new AssertionError();
        }
        if (parent == null) {
            return null;
        }
        int da = this._nthCache_A.distance(parent, name, set, n);
        int db = this._nthCache_B.distance(parent, name, set, n);
        if (da <= db) {
            x = this._nthCache_A.fetch(parent, name, set, n);
        } else {
            x = this._nthCache_B.fetch(parent, name, set, n);
        }
        if (da == db) {
            nthCache temp = this._nthCache_A;
            this._nthCache_A = this._nthCache_B;
            this._nthCache_B = temp;
        }
        return x;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int count(Xobj parent, QName name, QNameSet set) {
        int n = 0;
        for (Xobj x = findNthChildElem(parent, name, set, 0); x != null; x = x._nextSibling) {
            if (x.isElem()) {
                if (set == null) {
                    if (x._name.equals(name)) {
                        n++;
                    }
                } else if (set.contains(x._name)) {
                    n++;
                }
            }
        }
        return n;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean toChild(Cur c, QName name, int n) {
        if (n >= 0 && pushToContainer(c)) {
            Xobj x = c._locale.findNthChildElem(c._xobj, name, null, n);
            c.pop();
            if (x != null) {
                c.moveTo(x);
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean toFirstChildElement(Cur c) {
        Xobj originalXobj = c._xobj;
        int originalPos = c._pos;
        while (true) {
            switch (c.kind()) {
                case -2:
                case -1:
                    c.moveTo(originalXobj, originalPos);
                    return false;
                case 0:
                case 3:
                default:
                    c.nextWithAttrs();
                    break;
                case 1:
                case 2:
                    if (!c.toFirstChild() || (!c.isElem() && !toNextSiblingElement(c))) {
                        c.moveTo(originalXobj, originalPos);
                        return false;
                    }
                    return true;
                case 4:
                case 5:
                    c.skip();
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean toLastChildElement(Cur c) {
        if (!pushToContainer(c)) {
            return false;
        }
        if (!c.toLastChild() || (!c.isElem() && !toPrevSiblingElement(c))) {
            c.pop();
            return false;
        }
        c.popButStay();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0020, code lost:
    
        if (r2 == 1) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0023, code lost:
    
        if (r2 != 2) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002b, code lost:
    
        if (r0.kind() != (-2)) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002d, code lost:
    
        r0.toParent();
        r5.moveToCur(r0);
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0035, code lost:
    
        r0.release();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0038, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0012, code lost:
    
        if (r0.kind() != 3) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0018, code lost:
    
        if (r0.prev() != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001b, code lost:
    
        r2 = r0.kind();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean toPrevSiblingElement(org.apache.xmlbeans.impl.store.Cur r5) {
        /*
            boolean r0 = r5.hasParent()
            if (r0 != 0) goto L8
            r0 = 0
            return r0
        L8:
            org.apache.xmlbeans.impl.store.Cur r0 = r5.tempCur()
            r1 = 0
            int r2 = r0.kind()
            r3 = 3
            if (r2 == r3) goto L35
        L14:
            boolean r3 = r0.prev()
            if (r3 != 0) goto L1b
            goto L35
        L1b:
            int r2 = r0.kind()
            r3 = 1
            if (r2 == r3) goto L35
            r3 = 2
            if (r2 != r3) goto L26
            goto L35
        L26:
            int r3 = r0.kind()
            r4 = -2
            if (r3 != r4) goto L14
            r0.toParent()
            r5.moveToCur(r0)
            r1 = 1
        L35:
            r0.release()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Locale.toPrevSiblingElement(org.apache.xmlbeans.impl.store.Cur):boolean");
    }

    static boolean toNextSiblingElement(Cur c) {
        if (!c.hasParent()) {
            return false;
        }
        c.push();
        int k = c.kind();
        if (k == 3) {
            c.toParent();
            c.next();
        } else if (k == 2) {
            c.skip();
        }
        while (true) {
            int k2 = c.kind();
            if (k2 >= 0) {
                if (k2 == 2) {
                    c.popButStay();
                    return true;
                }
                if (k2 > 0) {
                    c.toEnd();
                }
                c.next();
            } else {
                c.pop();
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean toNextSiblingElement(Cur c, Xobj parent) {
        Xobj originalXobj = c._xobj;
        int originalPos = c._pos;
        int k = c.kind();
        if (k == 3) {
            c.moveTo(parent);
            c.next();
        } else if (k == 2) {
            c.skip();
        }
        while (true) {
            int k2 = c.kind();
            if (k2 >= 0) {
                if (k2 == 2) {
                    return true;
                }
                if (k2 > 0) {
                    c.toEnd();
                }
                c.next();
            } else {
                c.moveTo(originalXobj, originalPos);
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void applyNamespaces(Cur c, Map<String, String> namespaces) {
        if (!c.isContainer()) {
            throw new AssertionError();
        }
        for (String prefix : namespaces.keySet()) {
            if (!prefix.toLowerCase(java.util.Locale.ROOT).startsWith("xml") && c.namespaceForPrefix(prefix, false) == null) {
                c.push();
                c.next();
                c.createAttr(c._locale.createXmlns(prefix));
                c.next();
                c.insertString(namespaces.get(prefix));
                c.pop();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, String> getAllNamespaces(Cur c, Map<String, String> filleMe) {
        if (!c.isNode()) {
            throw new AssertionError();
        }
        c.push();
        if (!c.isContainer()) {
            c.toParent();
        }
        if (!c.isContainer()) {
            throw new AssertionError();
        }
        while (true) {
            if (c.toNextAttr()) {
                if (c.isXmlns()) {
                    String prefix = c.getXmlnsPrefix();
                    String uri = c.getXmlnsUri();
                    if (filleMe == null) {
                        filleMe = new HashMap();
                    }
                    if (!filleMe.containsKey(prefix)) {
                        filleMe.put(prefix, uri);
                    }
                }
            } else {
                if (!c.isContainer()) {
                    c.toParentRaw();
                }
                if (!c.toParentRaw()) {
                    c.pop();
                    return filleMe;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public class nthCache {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Xobj _child;
        private int _n;
        private QName _name;
        private Xobj _parent;
        private QNameSet _set;
        private long _version;

        nthCache() {
        }

        private boolean namesSame(QName pattern, QName name) {
            return pattern == null || pattern.equals(name);
        }

        private boolean setsSame(QNameSet patternSet, QNameSet set) {
            return patternSet != null && patternSet == set;
        }

        private boolean nameHit(QName namePattern, QNameSet setPattern, QName name) {
            if (setPattern == null) {
                return namesSame(namePattern, name);
            }
            return setPattern.contains(name);
        }

        private boolean cacheSame(QName namePattern, QNameSet setPattern) {
            if (setPattern == null) {
                return namesSame(namePattern, this._name);
            }
            return setsSame(setPattern, this._set);
        }

        int distance(Xobj parent, QName name, QNameSet set, int n) {
            if (n < 0) {
                throw new AssertionError();
            }
            if (this._version != Locale.this.version()) {
                return 2147483646;
            }
            if (parent == this._parent && cacheSame(name, set)) {
                return n > this._n ? n - this._n : this._n - n;
            }
            return Integer.MAX_VALUE;
        }

        /* JADX WARN: Code restructure failed: missing block: B:24:0x0069, code lost:
        
            r4._child = r0;
            r4._n++;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x0093, code lost:
        
            r4._child = r0;
            r4._n--;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        org.apache.xmlbeans.impl.store.Xobj fetch(org.apache.xmlbeans.impl.store.Xobj r5, javax.xml.namespace.QName r6, org.apache.xmlbeans.QNameSet r7, int r8) {
            /*
                r4 = this;
                if (r8 < 0) goto La3
                long r0 = r4._version
                org.apache.xmlbeans.impl.store.Locale r2 = org.apache.xmlbeans.impl.store.Locale.this
                long r2 = r2.version()
                int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                r1 = 0
                if (r0 != 0) goto L1b
                org.apache.xmlbeans.impl.store.Xobj r0 = r4._parent
                if (r0 != r5) goto L1b
                boolean r0 = r4.cacheSame(r6, r7)
                if (r0 == 0) goto L1b
                if (r8 != 0) goto L47
            L1b:
                org.apache.xmlbeans.impl.store.Locale r0 = org.apache.xmlbeans.impl.store.Locale.this
                long r2 = r0.version()
                r4._version = r2
                r4._parent = r5
                r4._name = r6
                r4._child = r1
                r0 = -1
                r4._n = r0
                org.apache.xmlbeans.impl.store.Xobj r0 = r5._firstChild
            L2e:
                if (r0 == 0) goto L47
                boolean r2 = r0.isElem()
                if (r2 == 0) goto L44
                javax.xml.namespace.QName r2 = r0._name
                boolean r2 = r4.nameHit(r6, r7, r2)
                if (r2 == 0) goto L44
                r4._child = r0
                r2 = 0
                r4._n = r2
                goto L47
            L44:
                org.apache.xmlbeans.impl.store.Xobj r0 = r0._nextSibling
                goto L2e
            L47:
                int r0 = r4._n
                if (r0 >= 0) goto L4c
                return r1
            L4c:
                int r0 = r4._n
                if (r8 <= r0) goto L76
            L50:
                int r0 = r4._n
                if (r8 <= r0) goto La0
                org.apache.xmlbeans.impl.store.Xobj r0 = r4._child
                org.apache.xmlbeans.impl.store.Xobj r0 = r0._nextSibling
            L58:
                if (r0 != 0) goto L5b
                return r1
            L5b:
                boolean r2 = r0.isElem()
                if (r2 == 0) goto L73
                javax.xml.namespace.QName r2 = r0._name
                boolean r2 = r4.nameHit(r6, r7, r2)
                if (r2 == 0) goto L73
                r4._child = r0
                int r2 = r4._n
                int r2 = r2 + 1
                r4._n = r2
                goto L50
            L73:
                org.apache.xmlbeans.impl.store.Xobj r0 = r0._nextSibling
                goto L58
            L76:
                int r0 = r4._n
                if (r8 >= r0) goto La0
            L7a:
                int r0 = r4._n
                if (r8 >= r0) goto La0
                org.apache.xmlbeans.impl.store.Xobj r0 = r4._child
                org.apache.xmlbeans.impl.store.Xobj r0 = r0._prevSibling
            L82:
                if (r0 != 0) goto L85
                return r1
            L85:
                boolean r2 = r0.isElem()
                if (r2 == 0) goto L9d
                javax.xml.namespace.QName r2 = r0._name
                boolean r2 = r4.nameHit(r6, r7, r2)
                if (r2 == 0) goto L9d
                r4._child = r0
                int r2 = r4._n
                int r2 = r2 + (-1)
                r4._n = r2
                goto L7a
            L9d:
                org.apache.xmlbeans.impl.store.Xobj r0 = r0._prevSibling
                goto L82
            La0:
                org.apache.xmlbeans.impl.store.Xobj r0 = r4._child
                return r0
            La3:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Locale.nthCache.fetch(org.apache.xmlbeans.impl.store.Xobj, javax.xml.namespace.QName, org.apache.xmlbeans.QNameSet, int):org.apache.xmlbeans.impl.store.Xobj");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DomImpl.Dom findDomNthChild(DomImpl.Dom parent, int n) {
        DomImpl.Dom x;
        if (n < 0) {
            throw new AssertionError();
        }
        if (parent == null) {
            return null;
        }
        int da = this._domNthCache_A.distance(parent, n);
        int db = this._domNthCache_B.distance(parent, n);
        boolean bInvalidate = db - (this._domNthCache_B._len / 2) > 0 && (db - (this._domNthCache_B._len / 2)) + (-40) > 0;
        boolean aInvalidate = da - (this._domNthCache_A._len / 2) > 0 && (da - (this._domNthCache_A._len / 2)) + (-40) > 0;
        if (da <= db) {
            if (!aInvalidate) {
                x = this._domNthCache_A.fetch(parent, n);
            } else {
                this._domNthCache_B._version = -1L;
                x = this._domNthCache_B.fetch(parent, n);
            }
        } else if (!bInvalidate) {
            x = this._domNthCache_B.fetch(parent, n);
        } else {
            this._domNthCache_A._version = -1L;
            x = this._domNthCache_A.fetch(parent, n);
        }
        if (da == db) {
            domNthCache temp = this._domNthCache_A;
            this._domNthCache_A = this._domNthCache_B;
            this._domNthCache_B = temp;
        }
        return x;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int domLength(DomImpl.Dom parent) {
        int len;
        if (parent == null) {
            return 0;
        }
        int da = this._domNthCache_A.distance(parent, 0);
        int db = this._domNthCache_B.distance(parent, 0);
        if (da <= db) {
            len = this._domNthCache_A.length(parent);
        } else {
            len = this._domNthCache_B.length(parent);
        }
        if (da == db) {
            domNthCache temp = this._domNthCache_A;
            this._domNthCache_A = this._domNthCache_B;
            this._domNthCache_B = temp;
        }
        return len;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void invalidateDomCaches(DomImpl.Dom d) {
        if (this._domNthCache_A._parent == d) {
            this._domNthCache_A._version = -1L;
        }
        if (this._domNthCache_B._parent != d) {
            return;
        }
        this._domNthCache_B._version = -1L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public class domNthCache {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        public static final int BLITZ_BOUNDARY = 40;
        private DomImpl.Dom _child;
        private int _len;
        private int _n;
        private DomImpl.Dom _parent;
        private long _version;

        domNthCache() {
        }

        int distance(DomImpl.Dom parent, int n) {
            if (n < 0) {
                throw new AssertionError();
            }
            if (this._version != Locale.this.version()) {
                return 2147483646;
            }
            if (parent != this._parent) {
                return Integer.MAX_VALUE;
            }
            return n > this._n ? n - this._n : this._n - n;
        }

        int length(DomImpl.Dom parent) {
            DomImpl.Dom x;
            if (this._version != Locale.this.version() || this._parent != parent) {
                this._parent = parent;
                this._version = Locale.this.version();
                this._child = null;
                this._n = -1;
                this._len = -1;
            }
            if (this._len == -1) {
                if (this._child != null && this._n != -1) {
                    x = this._child;
                    this._len = this._n;
                } else {
                    x = (DomImpl.Dom) DomImpl.firstChild(this._parent);
                    this._len = 0;
                    this._child = x;
                    this._n = 0;
                }
                while (x != null) {
                    this._len++;
                    x = (DomImpl.Dom) DomImpl.nextSibling(x);
                }
            }
            return this._len;
        }

        DomImpl.Dom fetch(DomImpl.Dom parent, int n) {
            if (n < 0) {
                throw new AssertionError();
            }
            if (this._version != Locale.this.version() || this._parent != parent) {
                this._parent = parent;
                this._version = Locale.this.version();
                this._child = null;
                this._n = -1;
                this._len = -1;
                DomImpl.Dom x = (DomImpl.Dom) DomImpl.firstChild(this._parent);
                while (true) {
                    if (x == null) {
                        break;
                    }
                    this._n++;
                    if (this._child != null || n != this._n) {
                        x = (DomImpl.Dom) DomImpl.nextSibling(x);
                    } else {
                        this._child = x;
                        break;
                    }
                }
                DomImpl.Dom x2 = this._child;
                return x2;
            }
            if (this._n < 0) {
                return null;
            }
            if (n > this._n) {
                while (n > this._n) {
                    DomImpl.Dom x3 = (DomImpl.Dom) DomImpl.nextSibling(this._child);
                    if (x3 == null) {
                        return null;
                    }
                    this._child = x3;
                    this._n++;
                }
            } else if (n < this._n) {
                while (n < this._n) {
                    DomImpl.Dom x4 = (DomImpl.Dom) DomImpl.prevSibling(this._child);
                    if (x4 == null) {
                        return null;
                    }
                    this._child = x4;
                    this._n--;
                }
            }
            return this._child;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharUtil getCharUtil() {
        if (this._charUtil == null) {
            this._charUtil = new CharUtil(1024);
        }
        return this._charUtil;
    }

    public long version() {
        return this._versionAll;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cur weakCur(Object o) {
        if (o == null || (o instanceof Ref)) {
            throw new AssertionError();
        }
        Cur c = getCur();
        if (c._tempFrame != -1) {
            throw new AssertionError();
        }
        if (c._ref != null) {
            throw new AssertionError();
        }
        c._ref = new Ref(c, o);
        return c;
    }

    final ReferenceQueue<Ref> refQueue() {
        if (this._refQueue == null) {
            this._refQueue = new ReferenceQueue<>();
        }
        return this._refQueue;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class Ref extends PhantomReference {
        Cur _cur;

        Ref(Cur c, Object obj) {
            super(obj, c._locale.refQueue());
            this._cur = c;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cur tempCur() {
        return tempCur(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cur tempCur(String id) {
        Cur c = getCur();
        if (c._tempFrame != -1) {
            throw new AssertionError();
        }
        if (this._numTempFramesLeft >= this._tempFrames.length) {
            throw new AssertionError("Temp frame not pushed");
        }
        int frame = (this._tempFrames.length - this._numTempFramesLeft) - 1;
        if (frame < 0 || frame >= this._tempFrames.length) {
            throw new AssertionError();
        }
        Cur next = this._tempFrames[frame];
        c._nextTemp = next;
        if (c._prevTemp != null) {
            throw new AssertionError();
        }
        if (next != null) {
            if (next._prevTemp != null) {
                throw new AssertionError();
            }
            next._prevTemp = c;
        }
        this._tempFrames[frame] = c;
        c._tempFrame = frame;
        c._id = id;
        return c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cur getCur() {
        Cur c;
        if (this._curPool != null && this._curPoolCount <= 0) {
            throw new AssertionError();
        }
        if (this._curPool == null) {
            c = new Cur(this);
        } else {
            Cur c2 = this._curPool;
            Cur c3 = this._curPool;
            this._curPool = c2.listRemove(c3);
            this._curPoolCount--;
            c = c3;
        }
        if (c._state != 0) {
            throw new AssertionError();
        }
        if (c._prev != null || c._next != null) {
            throw new AssertionError();
        }
        if (c._xobj != null || c._pos != -2) {
            throw new AssertionError();
        }
        if (c._ref != null) {
            throw new AssertionError();
        }
        this._registered = c.listInsert(this._registered);
        c._state = 1;
        return c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void embedCurs() {
        while (true) {
            Cur c = this._registered;
            if (c != null) {
                if (c._xobj == null) {
                    throw new AssertionError();
                }
                this._registered = c.listRemove(this._registered);
                c._xobj._embedded = c.listInsert(c._xobj._embedded);
                c._state = 2;
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextNode createTextNode() {
        return this._saaj == null ? new TextNode(this) : new SaajTextNode(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CdataNode createCdataNode() {
        return this._saaj == null ? new CdataNode(this) : new SaajCdataNode(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean entered() {
        return this._tempFrames.length - this._numTempFramesLeft > 0;
    }

    public void enter(Locale otherLocale) {
        enter();
        if (otherLocale != this) {
            otherLocale.enter();
        }
    }

    @Override // org.apache.xmlbeans.impl.common.XmlLocale
    public void enter() {
        if (this._numTempFramesLeft < 0) {
            throw new AssertionError();
        }
        int i = this._numTempFramesLeft - 1;
        this._numTempFramesLeft = i;
        if (i <= 0) {
            Cur[] newTempFrames = new Cur[this._tempFrames.length * 2];
            this._numTempFramesLeft = this._tempFrames.length;
            System.arraycopy(this._tempFrames, 0, newTempFrames, 0, this._tempFrames.length);
            this._tempFrames = newTempFrames;
        }
        int i2 = this._entryCount + 1;
        this._entryCount = i2;
        if (i2 > 1000) {
            pollQueue();
            this._entryCount = 0;
        }
    }

    private void pollQueue() {
        if (this._refQueue == null) {
            return;
        }
        while (true) {
            Ref ref = (Ref) this._refQueue.poll();
            if (ref != null) {
                if (ref._cur != null) {
                    ref._cur.release();
                }
            } else {
                return;
            }
        }
    }

    public void exit(Locale otherLocale) {
        exit();
        if (otherLocale != this) {
            otherLocale.exit();
        }
    }

    @Override // org.apache.xmlbeans.impl.common.XmlLocale
    public void exit() {
        if (this._numTempFramesLeft < 0 || this._numTempFramesLeft > this._tempFrames.length - 1) {
            throw new AssertionError(" Temp frames mismanaged. Impossible stack frame. Unsynchronized: " + noSync());
        }
        int length = this._tempFrames.length;
        int i = this._numTempFramesLeft + 1;
        this._numTempFramesLeft = i;
        int frame = length - i;
        while (this._tempFrames[frame] != null) {
            this._tempFrames[frame].release();
        }
    }

    @Override // org.apache.xmlbeans.impl.common.XmlLocale
    public boolean noSync() {
        return this._noSync;
    }

    @Override // org.apache.xmlbeans.impl.common.XmlLocale
    public boolean sync() {
        return !this._noSync;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isWhiteSpace(String s) {
        int l = s.length();
        while (true) {
            int l2 = l - 1;
            if (l > 0) {
                if (CharUtil.isWhiteSpace(s.charAt(l2))) {
                    l = l2;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean beginsWithXml(String name) {
        if (name.length() < 3) {
            return false;
        }
        char ch = name.charAt(0);
        if (ch != 'x' && ch != 'X') {
            return false;
        }
        char ch2 = name.charAt(1);
        if (ch2 != 'm' && ch2 != 'M') {
            return false;
        }
        char ch3 = name.charAt(2);
        return ch3 == 'l' || ch3 == 'L';
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isXmlns(QName name) {
        String prefix = name.getPrefix();
        if (prefix.equals(Sax2Dom.XMLNS_PREFIX)) {
            return true;
        }
        return prefix.isEmpty() && name.getLocalPart().equals(Sax2Dom.XMLNS_PREFIX);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public QName createXmlns(String prefix) {
        if (prefix == null) {
            prefix = "";
        }
        if (prefix.isEmpty()) {
            return makeQName("http://www.w3.org/2000/xmlns/", Sax2Dom.XMLNS_PREFIX, "");
        }
        return makeQName("http://www.w3.org/2000/xmlns/", prefix, Sax2Dom.XMLNS_PREFIX);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String xmlnsPrefix(QName name) {
        return name.getPrefix().equals(Sax2Dom.XMLNS_PREFIX) ? name.getLocalPart() : "";
    }

    /* loaded from: classes11.dex */
    public static abstract class LoadContext {
        private Hashtable<String, String> _idAttrs;

        protected abstract void abort();

        protected abstract void attr(String str, String str2, String str3, String str4);

        public abstract void attr(QName qName, String str);

        protected abstract void bookmark(XmlCursor.XmlBookmark xmlBookmark);

        protected abstract void bookmarkLastAttr(QName qName, XmlCursor.XmlBookmark xmlBookmark);

        protected abstract void bookmarkLastNonAttr(XmlCursor.XmlBookmark xmlBookmark);

        protected abstract void comment(String str);

        protected abstract void comment(char[] cArr, int i, int i2);

        protected abstract void endDTD();

        protected abstract void endElement();

        public abstract Cur finish();

        protected abstract void lineNumber(int i, int i2, int i3);

        protected abstract void procInst(String str, String str2);

        protected abstract void startDTD(String str, String str2, String str3);

        protected abstract void startElement(QName qName);

        protected abstract void text(String str);

        protected abstract void text(char[] cArr, int i, int i2);

        protected abstract void xmlns(String str, String str2);

        protected void addIdAttr(String eName, String aName) {
            if (this._idAttrs == null) {
                this._idAttrs = new Hashtable<>();
            }
            this._idAttrs.put(aName, eName);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public boolean isAttrOfTypeId(QName aqn, QName eqn) {
            if (this._idAttrs == null) {
                return "id".equalsIgnoreCase(aqn.getLocalPart());
            }
            String pre = aqn.getPrefix();
            String lName = aqn.getLocalPart();
            String urnName = "".equals(pre) ? lName : pre + ":" + lName;
            String eName = this._idAttrs.get(urnName);
            if (eName == null) {
                return false;
            }
            String pre2 = eqn.getPrefix();
            String lName2 = eqn.getLocalPart();
            String urnName2 = "".equals(pre2) ? lName2 : pre2 + ":" + lName2;
            return eName.equals(urnName2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class DefaultEntityResolver implements EntityResolver {
        private DefaultEntityResolver() {
        }

        @Override // org.xml.sax.EntityResolver
        public InputSource resolveEntity(String publicId, String systemId) {
            return new InputSource(new StringReader(""));
        }
    }

    private static SaxLoader getSaxLoader(XmlOptions options) throws XmlException {
        XmlOptions options2 = XmlOptions.maskNull(options);
        EntityResolver er = null;
        if (!options2.isLoadUseDefaultResolver()) {
            er = options2.getEntityResolver();
            if (er == null) {
                er = ResolverUtil.getGlobalEntityResolver();
            }
            if (er == null) {
                er = new DefaultEntityResolver();
            }
        }
        XMLReader xr = options2.getLoadUseXMLReader();
        if (xr == null) {
            try {
                xr = SAXHelper.newXMLReader(new XmlOptions(options2));
            } catch (Exception e) {
                throw new XmlException("Problem creating XMLReader", e);
            }
        }
        SaxLoader sl = new XmlReaderSaxLoader(xr);
        if (er != null) {
            xr.setEntityResolver(er);
        }
        return sl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class XmlReaderSaxLoader extends SaxLoader {
        XmlReaderSaxLoader(XMLReader xr) {
            super(xr, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static abstract class SaxHandler implements ContentHandler, LexicalHandler, DeclHandler, DTDHandler {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        protected LoadContext _context;
        protected Locale _locale;
        private Locator _startLocator;
        private boolean _wantCdataBookmarks;
        private boolean _wantLineNumbers;
        private boolean _wantLineNumbersAtEndElt;
        private boolean _insideCDATA = false;
        private int _entityBytesLimit = TarConstants.DEFAULT_BLKSIZE;
        private int _entityBytes = 0;
        private int _insideEntity = 0;
        private Map<String, String> delayedPrefixMappings = new LinkedHashMap();

        SaxHandler(Locator startLocator) {
            this._startLocator = startLocator;
        }

        void initSaxHandler(Locale l, XmlOptions options) {
            this._locale = l;
            XmlOptions safeOptions = XmlOptions.maskNull(options);
            this._context = new Cur.CurLoadContext(this._locale, safeOptions);
            this._wantLineNumbers = safeOptions.isLoadLineNumbers();
            this._wantLineNumbersAtEndElt = safeOptions.isLoadLineNumbersEndElement();
            this._wantCdataBookmarks = safeOptions.isUseCDataBookmarks();
            Integer limit = safeOptions.getLoadEntityBytesLimit();
            if (limit != null) {
                this._entityBytesLimit = limit.intValue();
            }
        }

        @Override // org.xml.sax.ContentHandler
        public void startDocument() throws SAXException {
        }

        @Override // org.xml.sax.ContentHandler
        public void endDocument() throws SAXException {
        }

        @Override // org.xml.sax.ContentHandler
        public void startElement(String uri, String localIgnored, String qName, Attributes atts) throws SAXException {
            if (qName.indexOf(58) < 0 || !uri.isEmpty()) {
                this._context.startElement(this._locale.makeQualifiedQName(uri, qName));
                if (this._wantLineNumbers && this._startLocator != null) {
                    this._context.bookmark(new XmlLineNumber(this._startLocator.getLineNumber(), this._startLocator.getColumnNumber() - 1, -1));
                }
                for (Map.Entry<String, String> nsEntry : this.delayedPrefixMappings.entrySet()) {
                    this._context.xmlns(nsEntry.getKey(), nsEntry.getValue());
                }
                this.delayedPrefixMappings.clear();
                int len = atts.getLength();
                for (int i = 0; i < len; i++) {
                    String aqn = atts.getQName(i);
                    int colon = aqn.indexOf(58);
                    if (colon < 0) {
                        this._context.attr(aqn, atts.getURI(i), null, atts.getValue(i));
                    } else {
                        this._context.attr(aqn.substring(colon + 1), atts.getURI(i), aqn.substring(0, colon), atts.getValue(i));
                    }
                }
                return;
            }
            XmlError err = XmlError.forMessage("Use of undefined namespace prefix: " + qName.substring(0, qName.indexOf(58)));
            throw new XmlRuntimeException(err.toString(), (Throwable) null, err);
        }

        @Override // org.xml.sax.ContentHandler
        public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
            this._context.endElement();
            if (this._wantLineNumbersAtEndElt && this._startLocator != null) {
                this._context.bookmark(new XmlLineNumber(this._startLocator.getLineNumber(), this._startLocator.getColumnNumber() - 1, -1));
            }
        }

        @Override // org.xml.sax.ContentHandler
        public void characters(char[] ch, int start, int length) throws SAXException {
            this._context.text(ch, start, length);
            if (this._wantCdataBookmarks && this._insideCDATA && this._startLocator != null) {
                this._context.bookmarkLastNonAttr(CDataBookmark.CDATA_BOOKMARK);
            }
            if (this._insideEntity != 0) {
                int i = this._entityBytes + length;
                this._entityBytes = i;
                if (i > this._entityBytesLimit) {
                    XmlError err = XmlError.forMessage(XmlErrorCodes.EXCEPTION_EXCEEDED_ENTITY_BYTES, new Integer[]{Integer.valueOf(this._entityBytesLimit)});
                    throw new SAXException(err.getMessage());
                }
            }
        }

        @Override // org.xml.sax.ContentHandler
        public void ignorableWhitespace(char[] ch, int start, int length) {
        }

        @Override // org.xml.sax.ext.LexicalHandler
        public void comment(char[] ch, int start, int length) throws SAXException {
            this._context.comment(ch, start, length);
        }

        @Override // org.xml.sax.ContentHandler
        public void processingInstruction(String target, String data) throws SAXException {
            this._context.procInst(target, data);
        }

        @Override // org.xml.sax.ext.LexicalHandler
        public void startDTD(String name, String publicId, String systemId) throws SAXException {
            this._context.startDTD(name, publicId, systemId);
        }

        @Override // org.xml.sax.ext.LexicalHandler
        public void endDTD() {
            this._context.endDTD();
        }

        @Override // org.xml.sax.ContentHandler
        public void startPrefixMapping(String prefix, String uri) throws SAXException {
            if (Locale.beginsWithXml(prefix) && (!"xml".equals(prefix) || !Locale._xml1998Uri.equals(uri))) {
                XmlError err = XmlError.forMessage("Prefix can't begin with XML: " + prefix, 0);
                throw new XmlRuntimeException(err.toString(), (Throwable) null, err);
            }
            this.delayedPrefixMappings.put(prefix, uri);
        }

        @Override // org.xml.sax.ContentHandler
        public void endPrefixMapping(String prefix) throws SAXException {
        }

        @Override // org.xml.sax.ContentHandler
        public void skippedEntity(String name) {
        }

        @Override // org.xml.sax.ext.LexicalHandler
        public void startCDATA() {
            this._insideCDATA = true;
        }

        @Override // org.xml.sax.ext.LexicalHandler
        public void endCDATA() {
            this._insideCDATA = false;
        }

        @Override // org.xml.sax.ext.LexicalHandler
        public void startEntity(String name) throws SAXException {
            this._insideEntity++;
        }

        @Override // org.xml.sax.ext.LexicalHandler
        public void endEntity(String name) throws SAXException {
            this._insideEntity--;
            if (this._insideEntity < 0) {
                throw new AssertionError();
            }
            if (this._insideEntity == 0) {
                this._entityBytes = 0;
            }
        }

        @Override // org.xml.sax.ContentHandler
        public void setDocumentLocator(Locator locator) {
            if (this._startLocator == null) {
                this._startLocator = locator;
            }
        }

        @Override // org.xml.sax.ext.DeclHandler
        public void attributeDecl(String eName, String aName, String type, String valueDefault, String value) {
            if (type.equals("ID")) {
                this._context.addIdAttr(eName, aName);
            }
        }

        @Override // org.xml.sax.ext.DeclHandler
        public void elementDecl(String name, String model) {
        }

        @Override // org.xml.sax.ext.DeclHandler
        public void externalEntityDecl(String name, String publicId, String systemId) {
        }

        @Override // org.xml.sax.ext.DeclHandler
        public void internalEntityDecl(String name, String value) {
        }

        @Override // org.xml.sax.DTDHandler
        public void notationDecl(String name, String publicId, String systemId) {
        }

        @Override // org.xml.sax.DTDHandler
        public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static abstract class SaxLoader extends SaxHandler implements ErrorHandler {
        private final XMLReader _xr;

        SaxLoader(XMLReader xr, Locator startLocator) {
            super(startLocator);
            this._xr = xr;
            try {
                this._xr.setFeature("http://xml.org/sax/features/namespaces", true);
                this._xr.setFeature("http://xml.org/sax/features/validation", false);
                this._xr.setProperty("http://xml.org/sax/properties/lexical-handler", this);
                this._xr.setContentHandler(this);
                this._xr.setDTDHandler(this);
                this._xr.setErrorHandler(this);
                try {
                    this._xr.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
                } catch (Throwable e) {
                    if (ExceptionUtil.isFatal(e)) {
                        ExceptionUtil.rethrow(e);
                    }
                    Locale.LOG.atWarn().withThrowable(e).log("Secure Processing Feature is not supported");
                }
                try {
                    this._xr.setProperty("http://xml.org/sax/properties/declaration-handler", this);
                } catch (Throwable e2) {
                    if (ExceptionUtil.isFatal(e2)) {
                        ExceptionUtil.rethrow(e2);
                    }
                    Locale.LOG.atWarn().withThrowable(e2).log("SAX Declaration Handler is not supported");
                }
            } catch (Throwable e3) {
                if (ExceptionUtil.isFatal(e3)) {
                    ExceptionUtil.rethrow(e3);
                }
                throw new RuntimeException(e3.getMessage(), e3);
            }
        }

        void postLoad(Cur c) {
            this._locale = null;
            this._context = null;
        }

        public Cur load(Locale l, InputSource is, XmlOptions options) throws XmlException, IOException {
            is.setSystemId("file://");
            initSaxHandler(l, options);
            try {
                this._xr.parse(is);
                Cur c = this._context.finish();
                Locale.associateSourceName(c, options);
                postLoad(c);
                return c;
            } catch (XmlRuntimeException e) {
                this._context.abort();
                throw new XmlException(e);
            } catch (RuntimeException e2) {
                this._context.abort();
                throw e2;
            } catch (SAXParseException e3) {
                this._context.abort();
                XmlError err = XmlError.forLocation(e3.getMessage(), options == null ? null : options.getDocumentSourceName(), e3.getLineNumber(), e3.getColumnNumber(), -1);
                throw new XmlException(err.toString(), e3, err);
            } catch (SAXException e4) {
                this._context.abort();
                XmlError err2 = XmlError.forMessage(e4.getMessage());
                throw new XmlException(err2.toString(), e4, err2);
            }
        }

        @Override // org.xml.sax.ErrorHandler
        public void fatalError(SAXParseException e) throws SAXException {
            throw e;
        }

        @Override // org.xml.sax.ErrorHandler
        public void error(SAXParseException e) throws SAXException {
            throw e;
        }

        @Override // org.xml.sax.ErrorHandler
        public void warning(SAXParseException e) throws SAXException {
            throw e;
        }
    }

    private DomImpl.Dom load(InputSource is, XmlOptions options) throws XmlException, IOException {
        return getSaxLoader(options).load(this, is, options).getDom();
    }

    public DomImpl.Dom load(Reader r) throws XmlException, IOException {
        return load(r, (XmlOptions) null);
    }

    public DomImpl.Dom load(Reader r, XmlOptions options) throws XmlException, IOException {
        return load(new InputSource(r), options);
    }

    public DomImpl.Dom load(InputStream in) throws XmlException, IOException {
        return load(in, (XmlOptions) null);
    }

    public DomImpl.Dom load(InputStream in, XmlOptions options) throws XmlException, IOException {
        return load(new InputSource(in), options);
    }

    public DomImpl.Dom load(String s) throws XmlException {
        return load(s, (XmlOptions) null);
    }

    public DomImpl.Dom load(String s, XmlOptions options) throws XmlException {
        try {
            Reader r = new StringReader(s);
            try {
                DomImpl.Dom load = load(r, options);
                r.close();
                return load;
            } finally {
            }
        } catch (IOException e) {
            throw new AssertionError("StringReader should not throw IOException");
        }
    }

    @Override // org.w3c.dom.DOMImplementation
    public Document createDocument(String uri, String qname, DocumentType doctype) {
        return DomImpl._domImplementation_createDocument(this, uri, qname, doctype);
    }

    @Override // org.w3c.dom.DOMImplementation
    public DocumentType createDocumentType(String qname, String publicId, String systemId) {
        throw new RuntimeException("Not implemented");
    }

    @Override // org.w3c.dom.DOMImplementation
    public boolean hasFeature(String feature, String version) {
        return DomImpl._domImplementation_hasFeature(this, feature, version);
    }

    @Override // org.w3c.dom.DOMImplementation
    public Object getFeature(String feature, String version) {
        throw new RuntimeException("DOM Level 3 Not implemented");
    }

    private static DomImpl.Dom checkNode(Node n) {
        if (n == null) {
            throw new IllegalArgumentException("Node is null");
        }
        if (!(n instanceof DomImpl.Dom)) {
            throw new IllegalArgumentException("Node is not an XmlBeans node");
        }
        return (DomImpl.Dom) n;
    }

    public static XmlCursor nodeToCursor(Node n) {
        return DomImpl._getXmlCursor(checkNode(n));
    }

    public static XmlObject nodeToXmlObject(Node n) {
        return DomImpl._getXmlObject(checkNode(n));
    }

    public static XMLStreamReader nodeToXmlStream(Node n) {
        return DomImpl._getXmlStreamReader(checkNode(n));
    }

    public static Node streamToNode(XMLStreamReader xs) {
        return Jsr173.nodeFromStream(xs);
    }

    @Override // org.apache.xmlbeans.impl.store.Saaj.SaajCallback
    public void setSaajData(Node n, Object o) {
        if (!(n instanceof DomImpl.Dom)) {
            throw new AssertionError();
        }
        DomImpl.saajCallback_setSaajData((DomImpl.Dom) n, o);
    }

    @Override // org.apache.xmlbeans.impl.store.Saaj.SaajCallback
    public Object getSaajData(Node n) {
        if (!(n instanceof DomImpl.Dom)) {
            throw new AssertionError();
        }
        return DomImpl.saajCallback_getSaajData((DomImpl.Dom) n);
    }

    @Override // org.apache.xmlbeans.impl.store.Saaj.SaajCallback
    public Element createSoapElement(QName name, QName parentName) {
        if (this._ownerDoc == null) {
            throw new AssertionError();
        }
        return DomImpl.saajCallback_createSoapElement(this._ownerDoc, name, parentName);
    }

    @Override // org.apache.xmlbeans.impl.store.Saaj.SaajCallback
    public Element importSoapElement(Document doc, Element elem, boolean deep, QName parentName) {
        if (!(doc instanceof DomImpl.Dom)) {
            throw new AssertionError();
        }
        return DomImpl.saajCallback_importSoapElement((DomImpl.Dom) doc, elem, deep, parentName);
    }

    public SchemaTypeLoader getSchemaTypeLoader() {
        return this._schemaTypeLoader;
    }

    /* loaded from: classes11.dex */
    private static final class DefaultQNameFactory implements QNameFactory {
        private final QNameCache _cache;

        private DefaultQNameFactory() {
            this._cache = XmlBeans.getQNameCache();
        }

        @Override // org.apache.xmlbeans.impl.store.QNameFactory
        public QName getQName(String uri, String local) {
            return this._cache.getName(uri, local, "");
        }

        @Override // org.apache.xmlbeans.impl.store.QNameFactory
        public QName getQName(String uri, String local, String prefix) {
            return this._cache.getName(uri, local, prefix);
        }

        @Override // org.apache.xmlbeans.impl.store.QNameFactory
        public QName getQName(char[] uriSrc, int uriPos, int uriCch, char[] localSrc, int localPos, int localCch) {
            return this._cache.getName(new String(uriSrc, uriPos, uriCch), new String(localSrc, localPos, localCch), "");
        }

        @Override // org.apache.xmlbeans.impl.store.QNameFactory
        public QName getQName(char[] uriSrc, int uriPos, int uriCch, char[] localSrc, int localPos, int localCch, char[] prefixSrc, int prefixPos, int prefixCch) {
            return this._cache.getName(new String(uriSrc, uriPos, uriCch), new String(localSrc, localPos, localCch), new String(prefixSrc, prefixPos, prefixCch));
        }
    }
}
