package org.apache.xmlbeans.impl.store;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.xml.namespace.QName;
import kotlin.text.Typography;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Chars;
import org.apache.xmlbeans.SystemProperties;
import org.apache.xmlbeans.XmlDocumentProperties;
import org.apache.xmlbeans.XmlOptionCharEscapeMap;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.EncodingMap;
import org.apache.xmlbeans.impl.common.NameUtil;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.apache.xmlbeans.impl.store.Saver;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.AttributesImpl;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public abstract class Saver {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int ATTR = 3;
    static final int COMMENT = 4;
    static final int ELEM = 2;
    static final int MAX_ARRAY_SIZE = 2147483639;
    static final int PROCINST = 5;
    static final int ROOT = 1;
    static final int TEXT = 0;
    static final String _newLine = SystemProperties.getProperty(org.apache.commons.lang3.SystemProperties.LINE_SEPARATOR, StringUtils.LF);
    private List<String> _ancestorNamespaces;
    private SaveCur _cur;
    private int _currentMapping;
    private String _initialDefaultUri;
    private final Locale _locale;
    private Map<String, String> _preComputedNamespaces;
    protected XmlOptionCharEscapeMap _replaceChar;
    private final boolean _saveNamespacesFirst;
    private final Map<String, String> _suggestedPrefixes;
    private final boolean _useDefaultNamespace;
    private final long _version;
    private final ArrayList<QName> _attrNames = new ArrayList<>();
    private final ArrayList<String> _attrValues = new ArrayList<>();
    private final ArrayList<String> _namespaceStack = new ArrayList<>();
    private final HashMap<String, String> _uriMap = new HashMap<>();
    private final HashMap<String, String> _prefixMap = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public interface SyncWrapFun {
        int process() throws IOException;
    }

    protected abstract void emitComment(SaveCur saveCur);

    protected abstract void emitDocType(String str, String str2, String str3);

    protected abstract boolean emitElement(SaveCur saveCur, List<QName> list, List<String> list2);

    protected abstract void emitEndDoc(SaveCur saveCur);

    protected abstract void emitFinish(SaveCur saveCur);

    protected abstract void emitProcinst(SaveCur saveCur);

    protected abstract void emitStartDoc(SaveCur saveCur);

    protected abstract void emitText(SaveCur saveCur);

    protected void syntheticNamespace(String prefix, String uri, boolean considerDefault) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Saver(Cur c, XmlOptions options) {
        if (!c._locale.entered()) {
            throw new AssertionError();
        }
        XmlOptions options2 = XmlOptions.maskNull(options);
        this._cur = createSaveCur(c, options2);
        this._locale = c._locale;
        this._version = this._locale.version();
        addMapping("xml", "http://www.w3.org/XML/1998/namespace");
        Map<String, String> m = options2.getSaveImplicitNamespaces();
        if (m != null) {
            for (String prefix : m.keySet()) {
                addMapping(prefix, m.get(prefix));
            }
        }
        this._replaceChar = options2.getSaveSubstituteCharacters();
        if (getNamespaceForPrefix("") == null) {
            this._initialDefaultUri = "";
            addMapping("", this._initialDefaultUri);
        }
        if (options2.isSaveAggressiveNamespaces() && !(this instanceof SynthNamespaceSaver)) {
            SynthNamespaceSaver saver = new SynthNamespaceSaver(c, options2);
            do {
            } while (saver.process());
            if (!saver._synthNamespaces.isEmpty()) {
                this._preComputedNamespaces = saver._synthNamespaces;
            }
        }
        this._useDefaultNamespace = options2.isUseDefaultNamespace();
        this._saveNamespacesFirst = options2.isSaveNamespacesFirst();
        this._suggestedPrefixes = options2.getSaveSuggestedPrefixes();
        this._ancestorNamespaces = this._cur.getAncestorNamespaces();
    }

    private static SaveCur createSaveCur(Cur c, XmlOptions options) {
        QName synthName = options.getSaveSyntheticDocumentElement();
        QName fragName = synthName;
        if (fragName == null) {
            fragName = options.isSaveUseOpenFrag() ? Locale._openuriFragment : Locale._xmlFragment;
        }
        boolean saveInner = options.isSaveInner() && !options.isSaveOuter();
        Cur start = c.tempCur();
        Cur end = c.tempCur();
        SaveCur cur = null;
        int k = c.kind();
        switch (k) {
            case 1:
                positionToInner(c, start, end);
                if (Locale.isFragment(start, end)) {
                    cur = new FragSaveCur(start, end, fragName);
                    break;
                } else if (synthName != null) {
                    cur = new FragSaveCur(start, end, synthName);
                    break;
                } else {
                    cur = new DocSaveCur(c);
                    break;
                }
            case 2:
                if (saveInner) {
                    positionToInner(c, start, end);
                    cur = new FragSaveCur(start, end, Locale.isFragment(start, end) ? fragName : synthName);
                    break;
                } else if (synthName != null) {
                    positionToInner(c, start, end);
                    cur = new FragSaveCur(start, end, synthName);
                    break;
                } else {
                    start.moveToCur(c);
                    end.moveToCur(c);
                    end.skip();
                    cur = new FragSaveCur(start, end, null);
                    break;
                }
        }
        if (cur == null) {
            if (k >= 0 && k != 3 && k != 4 && k != 5 && k != 0) {
                throw new AssertionError();
            }
            if (k < 0) {
                start.moveToCur(c);
                end.moveToCur(c);
            } else if (k == 0) {
                start.moveToCur(c);
                end.moveToCur(c);
                end.next();
            } else if (saveInner) {
                start.moveToCur(c);
                start.next();
                end.moveToCur(c);
                end.toEnd();
            } else if (k == 3) {
                start.moveToCur(c);
                end.moveToCur(c);
            } else {
                start.moveToCur(c);
                end.moveToCur(c);
                end.skip();
            }
            cur = new FragSaveCur(start, end, fragName);
        }
        String filterPI = options.getSaveFilterProcinst();
        if (filterPI != null) {
            cur = new FilterPiSaveCur(cur, filterPI);
        }
        if (options.isSavePrettyPrint()) {
            cur = new PrettySaveCur(cur, options);
        }
        start.release();
        end.release();
        return cur;
    }

    private static void positionToInner(Cur c, Cur start, Cur end) {
        if (!c.isContainer()) {
            throw new AssertionError();
        }
        start.moveToCur(c);
        if (!start.toFirstAttr()) {
            start.next();
        }
        end.moveToCur(c);
        end.toEnd();
    }

    static boolean isBadChar(char ch) {
        return (Character.isHighSurrogate(ch) || Character.isLowSurrogate(ch) || (ch >= ' ' && ch <= 55295) || ((ch >= 57344 && ch <= 65533) || ch == '\t' || ch == '\n' || ch == '\r')) ? false : true;
    }

    protected boolean saveNamespacesFirst() {
        return this._saveNamespacesFirst;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean process() {
        if (!this._locale.entered()) {
            throw new AssertionError();
        }
        if (this._cur == null) {
            return false;
        }
        if (this._version != this._locale.version()) {
            throw new ConcurrentModificationException("Document changed during save");
        }
        switch (this._cur.kind()) {
            case -2:
                processFinish();
                break;
            case -1:
                emitEndDoc(this._cur);
                this._cur.release();
                this._cur = null;
                return true;
            case 0:
                emitText(this._cur);
                break;
            case 1:
                processRoot();
                break;
            case 2:
                processElement();
                break;
            case 3:
            default:
                throw new RuntimeException("Unexpected kind");
            case 4:
                emitComment(this._cur);
                this._cur.toEnd();
                break;
            case 5:
                emitProcinst(this._cur);
                this._cur.toEnd();
                break;
        }
        this._cur.next();
        return true;
    }

    private void processFinish() {
        emitFinish(this._cur);
        popMappings();
    }

    private void processRoot() {
        if (!this._cur.isRoot()) {
            throw new AssertionError();
        }
        XmlDocumentProperties props = this._cur.getDocProps();
        String systemId = null;
        String docTypeName = null;
        if (props != null) {
            systemId = props.getDoctypeSystemId();
            docTypeName = props.getDoctypeName();
        }
        if (systemId != null || docTypeName != null) {
            if (docTypeName == null) {
                this._cur.push();
                while (!this._cur.isElem() && this._cur.next()) {
                }
                if (this._cur.isElem()) {
                    docTypeName = this._cur.getName().getLocalPart();
                }
                this._cur.pop();
            }
            String publicId = props.getDoctypePublicId();
            if (docTypeName != null) {
                QName rootElemName = this._cur.getName();
                if (rootElemName == null) {
                    this._cur.push();
                    while (true) {
                        if (this._cur.isFinish()) {
                            break;
                        }
                        if (this._cur.isElem()) {
                            rootElemName = this._cur.getName();
                            break;
                        }
                        this._cur.next();
                    }
                    this._cur.pop();
                }
                if (rootElemName != null && docTypeName.equals(rootElemName.getLocalPart())) {
                    emitDocType(docTypeName, publicId, systemId);
                    return;
                }
            }
        }
        emitStartDoc(this._cur);
    }

    private void processElement() {
        if (!this._cur.isElem() || this._cur.getName() == null) {
            throw new AssertionError();
        }
        QName name = this._cur.getName();
        boolean ensureDefaultEmpty = name.getNamespaceURI().isEmpty();
        pushMappings(this._cur, ensureDefaultEmpty);
        ensureMapping(name.getNamespaceURI(), name.getPrefix(), !ensureDefaultEmpty, false);
        this._attrNames.clear();
        this._attrValues.clear();
        this._cur.push();
        boolean A = this._cur.toFirstAttr();
        while (A) {
            if (this._cur.isNormalAttr()) {
                QName attrName = this._cur.getName();
                this._attrNames.add(attrName);
                int i = this._attrNames.size() - 2;
                while (true) {
                    if (i >= 0) {
                        if (this._attrNames.get(i).equals(attrName)) {
                            this._attrNames.remove(this._attrNames.size() - 1);
                            break;
                        }
                        i--;
                    } else {
                        this._attrValues.add(this._cur.getAttrValue());
                        ensureMapping(attrName.getNamespaceURI(), attrName.getPrefix(), false, true);
                        break;
                    }
                }
            }
            A = this._cur.toNextAttr();
        }
        this._cur.pop();
        if (this._preComputedNamespaces != null) {
            for (Map.Entry<String, String> entry : this._preComputedNamespaces.entrySet()) {
                String uri = entry.getKey();
                String prefix = entry.getValue();
                boolean considerDefault = prefix.isEmpty() && !ensureDefaultEmpty;
                ensureMapping(uri, prefix, considerDefault, false);
            }
            this._preComputedNamespaces = null;
        }
        if (emitElement(this._cur, this._attrNames, this._attrValues)) {
            popMappings();
            this._cur.toEnd();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void iterateMappings() {
        this._currentMapping = this._namespaceStack.size();
        while (this._currentMapping > 0 && this._namespaceStack.get(this._currentMapping - 1) != null) {
            this._currentMapping -= 8;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasMapping() {
        return this._currentMapping < this._namespaceStack.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void nextMapping() {
        this._currentMapping += 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String mappingPrefix() {
        if (!hasMapping()) {
            throw new AssertionError();
        }
        return this._namespaceStack.get(this._currentMapping + 6);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String mappingUri() {
        if (!hasMapping()) {
            throw new AssertionError();
        }
        return this._namespaceStack.get(this._currentMapping + 7);
    }

    private void pushMappings(SaveCur c, boolean ensureDefaultEmpty) {
        if (!c.isContainer()) {
            throw new AssertionError();
        }
        this._namespaceStack.add(null);
        c.push();
        boolean A = c.toFirstAttr();
        while (A) {
            if (c.isXmlns()) {
                addNewFrameMapping(c.getXmlnsPrefix(), c.getXmlnsUri(), ensureDefaultEmpty);
            }
            A = c.toNextAttr();
        }
        c.pop();
        if (this._ancestorNamespaces != null) {
            for (int i = 0; i < this._ancestorNamespaces.size(); i += 2) {
                String prefix = this._ancestorNamespaces.get(i);
                String uri = this._ancestorNamespaces.get(i + 1);
                addNewFrameMapping(prefix, uri, ensureDefaultEmpty);
            }
            this._ancestorNamespaces = null;
        }
        if (ensureDefaultEmpty) {
            String defaultUri = this._prefixMap.get("");
            if (defaultUri == null) {
                throw new AssertionError();
            }
            if (!defaultUri.isEmpty()) {
                addMapping("", "");
            }
        }
    }

    private void addNewFrameMapping(String prefix, String uri, boolean ensureDefaultEmpty) {
        if (prefix.isEmpty() || !uri.isEmpty()) {
            if (!ensureDefaultEmpty || !prefix.isEmpty() || uri.isEmpty()) {
                iterateMappings();
                while (hasMapping()) {
                    if (!mappingPrefix().equals(prefix)) {
                        nextMapping();
                    } else {
                        return;
                    }
                }
                if (uri.equals(getNamespaceForPrefix(prefix))) {
                    return;
                }
                addMapping(prefix, uri);
            }
        }
    }

    private void addMapping(String prefix, String uri) {
        if (uri == null) {
            throw new AssertionError();
        }
        if (prefix == null) {
            throw new AssertionError();
        }
        String renameUri = this._prefixMap.get(prefix);
        String renamePrefix = null;
        if (renameUri != null) {
            if (renameUri.equals(uri)) {
                renameUri = null;
            } else {
                int i = this._namespaceStack.size();
                while (i > 0) {
                    if (this._namespaceStack.get(i - 1) == null) {
                        i--;
                    } else if (this._namespaceStack.get(i - 7).equals(renameUri) && ((renamePrefix = this._namespaceStack.get(i - 8)) == null || !renamePrefix.equals(prefix))) {
                        break;
                    } else {
                        i -= 8;
                    }
                }
                if (i <= 0) {
                    throw new AssertionError();
                }
            }
        }
        this._namespaceStack.add(this._uriMap.get(uri));
        this._namespaceStack.add(uri);
        if (renameUri != null) {
            this._namespaceStack.add(this._uriMap.get(renameUri));
            this._namespaceStack.add(renameUri);
        } else {
            this._namespaceStack.add(null);
            this._namespaceStack.add(null);
        }
        this._namespaceStack.add(prefix);
        this._namespaceStack.add(this._prefixMap.get(prefix));
        this._namespaceStack.add(prefix);
        this._namespaceStack.add(uri);
        this._uriMap.put(uri, prefix);
        this._prefixMap.put(prefix, uri);
        if (renameUri != null) {
            this._uriMap.put(renameUri, renamePrefix);
        }
    }

    private void popMappings() {
        while (true) {
            int i = this._namespaceStack.size();
            if (i != 0) {
                if (this._namespaceStack.get(i - 1) == null) {
                    this._namespaceStack.remove(i - 1);
                    return;
                }
                String oldUri = this._namespaceStack.get(i - 7);
                String oldPrefix = this._namespaceStack.get(i - 8);
                if (oldPrefix == null) {
                    this._uriMap.remove(oldUri);
                } else {
                    this._uriMap.put(oldUri, oldPrefix);
                }
                String oldPrefix2 = this._namespaceStack.get(i - 4);
                String oldUri2 = this._namespaceStack.get(i - 3);
                if (oldUri2 == null) {
                    this._prefixMap.remove(oldPrefix2);
                } else {
                    this._prefixMap.put(oldPrefix2, oldUri2);
                }
                String uri = this._namespaceStack.get(i - 5);
                if (uri != null) {
                    this._uriMap.put(uri, this._namespaceStack.get(i - 6));
                }
                this._namespaceStack.remove(i - 1);
                this._namespaceStack.remove(i - 2);
                this._namespaceStack.remove(i - 3);
                this._namespaceStack.remove(i - 4);
                this._namespaceStack.remove(i - 5);
                this._namespaceStack.remove(i - 6);
                this._namespaceStack.remove(i - 7);
                this._namespaceStack.remove(i - 8);
            } else {
                return;
            }
        }
    }

    private void ensureMapping(String uri, String candidatePrefix, boolean considerCreatingDefault, boolean mustHavePrefix) {
        if (uri == null) {
            throw new AssertionError();
        }
        if (uri.isEmpty()) {
            return;
        }
        String prefix = this._uriMap.get(uri);
        if (prefix != null && (!prefix.isEmpty() || !mustHavePrefix)) {
            return;
        }
        if (candidatePrefix != null && candidatePrefix.isEmpty()) {
            candidatePrefix = null;
        }
        if (!tryPrefix(candidatePrefix)) {
            if (this._suggestedPrefixes != null && this._suggestedPrefixes.containsKey(uri) && tryPrefix(this._suggestedPrefixes.get(uri))) {
                candidatePrefix = this._suggestedPrefixes.get(uri);
            } else if (considerCreatingDefault && this._useDefaultNamespace && tryPrefix("")) {
                candidatePrefix = "";
            } else {
                String basePrefix = QNameHelper.suggestPrefix(uri);
                candidatePrefix = basePrefix;
                int i = 1;
                while (!tryPrefix(candidatePrefix)) {
                    candidatePrefix = basePrefix + i;
                    i++;
                }
            }
        }
        if (candidatePrefix == null) {
            throw new AssertionError();
        }
        syntheticNamespace(candidatePrefix, uri, considerCreatingDefault);
        addMapping(candidatePrefix, uri);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String getUriMapping(String uri) {
        if (!this._uriMap.containsKey(uri)) {
            throw new AssertionError();
        }
        return this._uriMap.get(uri);
    }

    String getNonDefaultUriMapping(String uri) {
        String prefix = this._uriMap.get(uri);
        if (prefix != null && !prefix.isEmpty()) {
            return prefix;
        }
        for (String s : this._prefixMap.keySet()) {
            if (!s.isEmpty() && this._prefixMap.get(s).equals(uri)) {
                return s;
            }
        }
        throw new AssertionError("Could not find non-default mapping");
    }

    private boolean tryPrefix(String prefix) {
        if (prefix == null || Locale.beginsWithXml(prefix)) {
            return false;
        }
        String existingUri = this._prefixMap.get(prefix);
        return existingUri == null || (prefix.length() <= 0 && Objects.equals(existingUri, this._initialDefaultUri));
    }

    public final String getNamespaceForPrefix(String prefix) {
        if (!prefix.equals("xml") || this._prefixMap.get(prefix).equals("http://www.w3.org/XML/1998/namespace")) {
            return this._prefixMap.get(prefix);
        }
        throw new AssertionError();
    }

    /* loaded from: classes11.dex */
    static final class SynthNamespaceSaver extends Saver {
        LinkedHashMap<String, String> _synthNamespaces;

        SynthNamespaceSaver(Cur c, XmlOptions options) {
            super(c, options);
            this._synthNamespaces = new LinkedHashMap<>();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void syntheticNamespace(String prefix, String uri, boolean considerCreatingDefault) {
            this._synthNamespaces.put(uri, considerCreatingDefault ? "" : prefix);
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected boolean emitElement(SaveCur c, List<QName> attrNames, List<String> attrValues) {
            return false;
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitFinish(SaveCur c) {
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitText(SaveCur c) {
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitComment(SaveCur c) {
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitProcinst(SaveCur c) {
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitDocType(String docTypeName, String publicId, String systemId) {
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitStartDoc(SaveCur c) {
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitEndDoc(SaveCur c) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class TextSaver extends Saver {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final int _initialBufSize = 4096;
        private char[] _cbuf;
        private int _cdataEntityCountThreshold;
        private int _cdataLengthThreshold;
        private int _free;
        private int _in;
        private boolean _isPrettyPrint;
        private int _lastEmitCch;
        private int _lastEmitIn;
        private int _out;
        private boolean _useCDataBookmarks;

        /* JADX INFO: Access modifiers changed from: package-private */
        public TextSaver(Cur c, XmlOptions options, String encoding) {
            super(c, options);
            boolean noSaveDecl;
            this._cdataLengthThreshold = 32;
            this._cdataEntityCountThreshold = 5;
            this._useCDataBookmarks = false;
            this._isPrettyPrint = false;
            if (options != null && options.isSaveNoXmlDecl()) {
                noSaveDecl = true;
            } else {
                noSaveDecl = false;
            }
            if (options != null && options.getSaveCDataLengthThreshold() != null) {
                this._cdataLengthThreshold = options.getSaveCDataLengthThreshold().intValue();
            }
            if (options != null && options.getSaveCDataEntityCountThreshold() != null) {
                this._cdataEntityCountThreshold = options.getSaveCDataEntityCountThreshold().intValue();
            }
            if (options != null && options.isUseCDataBookmarks()) {
                this._useCDataBookmarks = true;
            }
            if (options != null && options.isSavePrettyPrint()) {
                this._isPrettyPrint = true;
            }
            this._out = 0;
            this._in = 0;
            this._free = 0;
            if (this._cbuf != null && ((this._out >= this._in || this._free != this._cbuf.length - (this._in - this._out)) && ((this._out <= this._in || this._free != this._out - this._in) && ((this._out != this._in || this._free != this._cbuf.length) && (this._out != this._in || this._free != 0))))) {
                throw new AssertionError("_buf.length:" + this._cbuf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free);
            }
            if (encoding != null && !noSaveDecl) {
                XmlDocumentProperties props = Locale.getDocProps(c, false);
                String version = props == null ? null : props.getVersion();
                version = version == null ? "1.0" : version;
                Boolean standalone = null;
                if (props != null && props.get(XmlDocumentProperties.STANDALONE) != null) {
                    standalone = Boolean.valueOf(props.getStandalone());
                }
                emit("<?xml version=\"");
                emit(version);
                emit("\" encoding=\"" + encoding + "\"");
                if (standalone != null) {
                    emit(" standalone=\"" + (standalone.booleanValue() ? BooleanUtils.YES : BooleanUtils.NO) + "\"");
                }
                emit("?>" + _newLine);
            }
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected boolean emitElement(SaveCur c, List<QName> attrNames, List<String> attrValues) {
            if (!c.isElem()) {
                throw new AssertionError();
            }
            emit(Typography.less);
            emitName(c.getName(), false);
            if (saveNamespacesFirst()) {
                emitNamespacesHelper();
            }
            for (int i = 0; i < attrNames.size(); i++) {
                emitAttrHelper(attrNames.get(i), attrValues.get(i));
            }
            if (!saveNamespacesFirst()) {
                emitNamespacesHelper();
            }
            if (!c.hasChildren() && !c.hasText()) {
                emit('/', Typography.greater);
                return true;
            }
            emit(Typography.greater);
            return false;
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitFinish(SaveCur c) {
            emit(Typography.less, '/');
            emitName(c.getName(), false);
            emit(Typography.greater);
        }

        protected void emitXmlns(String prefix, String uri) {
            if (prefix == null) {
                throw new AssertionError();
            }
            if (uri == null) {
                throw new AssertionError();
            }
            emit(Sax2Dom.XMLNS_PREFIX);
            if (!prefix.isEmpty()) {
                emit(NameUtil.COLON);
                emit(prefix);
            }
            emit(Chars.EQ, '\"');
            emit(uri);
            entitizeAttrValue(false);
            emit('\"');
        }

        private void emitNamespacesHelper() {
            LinkedHashMap<String, String> nsMap = new LinkedHashMap<>();
            iterateMappings();
            while (hasMapping()) {
                String prefix = mappingPrefix();
                String uri = mappingUri();
                if (nsMap.containsKey(prefix)) {
                    if (prefix.isEmpty() && nsMap.get(prefix).isEmpty()) {
                        nsMap.put(prefix, uri);
                    }
                } else {
                    nsMap.put(prefix, uri);
                }
                nextMapping();
            }
            for (Map.Entry<String, String> nsEntry : nsMap.entrySet()) {
                emit(Chars.SPACE);
                emitXmlns(nsEntry.getKey(), nsEntry.getValue());
            }
        }

        private void emitAttrHelper(QName attrName, String attrValue) {
            emit(Chars.SPACE);
            emitName(attrName, true);
            emit(Chars.EQ, '\"');
            emit(attrValue);
            entitizeAttrValue(true);
            emit('\"');
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitText(SaveCur c) {
            if (!c.isText()) {
                throw new AssertionError();
            }
            boolean forceCData = this._useCDataBookmarks && c.isTextCData();
            emit(c);
            entitizeContent(forceCData);
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitComment(SaveCur c) {
            if (!c.isComment()) {
                throw new AssertionError();
            }
            emit("<!--");
            c.push();
            c.next();
            emit(c);
            c.pop();
            entitizeComment();
            emit("-->");
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitProcinst(SaveCur c) {
            if (!c.isProcinst()) {
                throw new AssertionError();
            }
            emit("<?");
            emit(c.getName().getLocalPart());
            c.push();
            c.next();
            if (c.isText()) {
                emit(StringUtils.SPACE);
                emit(c);
                entitizeProcinst();
            }
            c.pop();
            emit("?>");
        }

        private void emitLiteral(String literal) {
            if (!literal.contains("\"")) {
                emit('\"');
                emit(literal);
                emit('\"');
            } else {
                emit(Chars.QUOTE);
                emit(literal);
                emit(Chars.QUOTE);
            }
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitDocType(String docTypeName, String publicId, String systemId) {
            if (docTypeName == null) {
                throw new AssertionError();
            }
            emit("<!DOCTYPE ");
            emit(docTypeName);
            if (publicId == null && systemId != null) {
                emit(" SYSTEM ");
                emitLiteral(systemId);
            } else if (publicId != null) {
                emit(" PUBLIC ");
                emitLiteral(publicId);
                emit(StringUtils.SPACE);
                emitLiteral(systemId);
            }
            emit(">");
            emit(_newLine);
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitStartDoc(SaveCur c) {
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitEndDoc(SaveCur c) {
        }

        private void emitName(QName name, boolean needsPrefix) {
            if (name == null) {
                throw new AssertionError();
            }
            if (name != null && (name.getLocalPart() == null || name.getLocalPart().isEmpty())) {
                throw new IllegalArgumentException("emitName does not support names with empty local part");
            }
            String uri = name.getNamespaceURI();
            if (uri == null) {
                throw new AssertionError();
            }
            if (!uri.isEmpty()) {
                String prefix = name.getPrefix();
                String mappedUri = getNamespaceForPrefix(prefix);
                if (mappedUri == null || !mappedUri.equals(uri)) {
                    prefix = getUriMapping(uri);
                }
                if (needsPrefix && prefix.isEmpty()) {
                    prefix = getNonDefaultUriMapping(uri);
                }
                if (!prefix.isEmpty()) {
                    emit(prefix);
                    emit(NameUtil.COLON);
                }
            }
            emit(name.getLocalPart());
        }

        private void emit(char ch) {
            if (this._cbuf != null && ((this._out >= this._in || this._free != this._cbuf.length - (this._in - this._out)) && ((this._out <= this._in || this._free != this._out - this._in) && ((this._out != this._in || this._free != this._cbuf.length) && (this._out != this._in || this._free != 0))))) {
                throw new AssertionError("_buf.length:" + this._cbuf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free);
            }
            preEmit(1);
            this._cbuf[this._in] = ch;
            this._in = (this._in + 1) % this._cbuf.length;
            if (this._out >= this._in || this._free != this._cbuf.length - (this._in - this._out)) {
                if (this._out <= this._in || this._free != this._out - this._in) {
                    if (this._out == this._in && this._free == this._cbuf.length) {
                        return;
                    }
                    if (this._out != this._in || this._free != 0) {
                        throw new AssertionError("_buf.length:" + this._cbuf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free);
                    }
                }
            }
        }

        private void emit(char ch1, char ch2) {
            if (preEmit(2)) {
                return;
            }
            this._cbuf[this._in] = ch1;
            this._in = (this._in + 1) % this._cbuf.length;
            this._cbuf[this._in] = ch2;
            this._in = (this._in + 1) % this._cbuf.length;
            if (this._out >= this._in || this._free != this._cbuf.length - (this._in - this._out)) {
                if (this._out <= this._in || this._free != this._out - this._in) {
                    if (this._out == this._in && this._free == this._cbuf.length) {
                        return;
                    }
                    if (this._out != this._in || this._free != 0) {
                        throw new AssertionError("_buf.length:" + this._cbuf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free);
                    }
                }
            }
        }

        private void emit(String s) {
            int chunk;
            if (this._cbuf != null && ((this._out >= this._in || this._free != this._cbuf.length - (this._in - this._out)) && ((this._out <= this._in || this._free != this._out - this._in) && ((this._out != this._in || this._free != this._cbuf.length) && (this._out != this._in || this._free != 0))))) {
                throw new AssertionError("_buf.length:" + this._cbuf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free);
            }
            int cch = s == null ? 0 : s.length();
            if (preEmit(cch) || s == null) {
                return;
            }
            if (this._in <= this._out || cch < (chunk = this._cbuf.length - this._in)) {
                s.getChars(0, cch, this._cbuf, this._in);
                this._in += cch;
            } else {
                s.getChars(0, chunk, this._cbuf, this._in);
                s.getChars(chunk, cch, this._cbuf, 0);
                this._in = (this._in + cch) % this._cbuf.length;
            }
            if (this._out >= this._in || this._free != this._cbuf.length - (this._in - this._out)) {
                if (this._out <= this._in || this._free != this._out - this._in) {
                    if (this._out == this._in && this._free == this._cbuf.length) {
                        return;
                    }
                    if (this._out != this._in || this._free != 0) {
                        throw new AssertionError("_buf.length:" + this._cbuf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free);
                    }
                }
            }
        }

        private void emit(SaveCur c) {
            int chunk;
            if (c.isText()) {
                Object src = c.getChars();
                int cch = c._cchSrc;
                if (preEmit(cch)) {
                    return;
                }
                if (this._in <= this._out || cch < (chunk = this._cbuf.length - this._in)) {
                    CharUtil.getChars(this._cbuf, this._in, src, c._offSrc, cch);
                    this._in += cch;
                    return;
                } else {
                    CharUtil.getChars(this._cbuf, this._in, src, c._offSrc, chunk);
                    CharUtil.getChars(this._cbuf, 0, src, c._offSrc + chunk, cch - chunk);
                    this._in = (this._in + cch) % this._cbuf.length;
                    return;
                }
            }
            preEmit(0);
        }

        private boolean preEmit(int cch) {
            int i;
            int i2;
            if (cch < 0) {
                throw new AssertionError();
            }
            if (this._cbuf != null && ((this._out >= this._in || this._free != this._cbuf.length - (this._in - this._out)) && ((this._out <= this._in || this._free != this._out - this._in) && ((this._out != this._in || this._free != this._cbuf.length) && (this._out != this._in || this._free != 0))))) {
                throw new AssertionError("_buf.length:" + this._cbuf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free);
            }
            this._lastEmitCch = cch;
            if (cch == 0) {
                return true;
            }
            if (this._free <= cch) {
                resize(cch, -1);
            }
            if (cch > this._free) {
                throw new AssertionError();
            }
            int used = getAvailable();
            if (used == 0) {
                if (this._in != this._out) {
                    throw new AssertionError();
                }
                if (this._cbuf != null && this._free != this._cbuf.length) {
                    throw new AssertionError();
                }
                this._out = 0;
                this._in = 0;
            }
            this._lastEmitIn = this._in;
            this._free -= cch;
            if (this._cbuf != null) {
                int i3 = this._free;
                if (this._in >= this._out) {
                    i = this._cbuf.length;
                    i2 = this._in - this._out;
                } else {
                    i = this._out;
                    i2 = this._in;
                }
                if (i3 != (i - i2) - cch) {
                    throw new AssertionError("_buf.length:" + this._cbuf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free);
                }
            }
            if (this._cbuf == null || ((this._out < this._in && this._free == (this._cbuf.length - (this._in - this._out)) - cch) || ((this._out > this._in && this._free == (this._out - this._in) - cch) || ((this._out == this._in && this._free == this._cbuf.length - cch) || (this._out == this._in && this._free == 0))))) {
                return false;
            }
            throw new AssertionError("_buf.length:" + this._cbuf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free);
        }

        private void entitizeContent(boolean forceCData) {
            char c;
            if (this._free < 0) {
                throw new AssertionError();
            }
            if (this._lastEmitCch == 0) {
                return;
            }
            int i = this._lastEmitIn;
            int n = this._cbuf.length;
            boolean hasCharToBeReplaced = false;
            int count = 0;
            char prevChar = 0;
            char prevPrevChar = 0;
            int cch = this._lastEmitCch;
            while (true) {
                c = Typography.amp;
                if (cch <= 0) {
                    break;
                }
                char ch = this._cbuf[i];
                if (ch == '<' || ch == '&') {
                    count++;
                } else if (prevPrevChar == ']' && prevChar == ']' && ch == '>') {
                    hasCharToBeReplaced = true;
                } else if (isBadChar(ch) || isEscapedChar(ch) || (!this._isPrettyPrint && ch == '\r')) {
                    hasCharToBeReplaced = true;
                }
                i++;
                if (i == n) {
                    i = 0;
                }
                prevPrevChar = prevChar;
                prevChar = ch;
                cch--;
            }
            if (!forceCData && count == 0 && !hasCharToBeReplaced && count < this._cdataEntityCountThreshold) {
                return;
            }
            int i2 = this._lastEmitIn;
            if (forceCData || (this._lastEmitCch > this._cdataLengthThreshold && count > this._cdataEntityCountThreshold)) {
                boolean lastWasBracket = this._cbuf[i2] == ']';
                int i3 = replace(i2, "<![CDATA[" + this._cbuf[i2]);
                boolean secondToLastWasBracket = lastWasBracket;
                boolean lastWasBracket2 = this._cbuf[i3] == ']';
                int i4 = i3 + 1;
                if (i4 == this._cbuf.length) {
                    i4 = 0;
                }
                for (int cch2 = this._lastEmitCch - 2; cch2 > 0; cch2--) {
                    char ch2 = this._cbuf[i4];
                    if (ch2 == '>' && secondToLastWasBracket && lastWasBracket2) {
                        i4 = replace(i4, "]]>><![CDATA[");
                    } else if (isBadChar(ch2)) {
                        i4 = replace(i4, "?");
                    } else {
                        i4++;
                    }
                    secondToLastWasBracket = lastWasBracket2;
                    lastWasBracket2 = ch2 == ']';
                    if (i4 == this._cbuf.length) {
                        i4 = 0;
                    }
                }
                emit("]]>");
                return;
            }
            char ch3 = 0;
            char ch_1 = 0;
            int cch3 = this._lastEmitCch;
            while (cch3 > 0) {
                char ch_2 = ch_1;
                ch_1 = ch3;
                ch3 = this._cbuf[i2];
                if (ch3 == '<') {
                    i2 = replace(i2, "&lt;");
                } else if (ch3 == c) {
                    i2 = replace(i2, "&amp;");
                } else {
                    if (ch3 == '>' && ch_1 == ']') {
                        if (ch_2 == ']') {
                            i2 = replace(i2, "&gt;");
                        }
                    }
                    i2 = isBadChar(ch3) ? replace(i2, "?") : (this._isPrettyPrint || ch3 != '\r') ? isEscapedChar(ch3) ? replace(i2, this._replaceChar.getEscapedString(ch3)) : i2 + 1 : replace(i2, "&#13;");
                }
                if (i2 == this._cbuf.length) {
                    i2 = 0;
                }
                cch3--;
                c = Typography.amp;
            }
        }

        private void entitizeAttrValue(boolean replaceEscapedChar) {
            if (this._lastEmitCch == 0) {
                return;
            }
            int i = this._lastEmitIn;
            for (int cch = this._lastEmitCch; cch > 0; cch--) {
                char ch = this._cbuf[i];
                if (ch == '<') {
                    i = replace(i, "&lt;");
                } else if (ch == '&') {
                    i = replace(i, "&amp;");
                } else if (ch == '\"') {
                    i = replace(i, "&quot;");
                } else if (isEscapedChar(ch)) {
                    if (replaceEscapedChar) {
                        i = replace(i, this._replaceChar.getEscapedString(ch));
                    }
                } else {
                    i++;
                }
                if (i == this._cbuf.length) {
                    i = 0;
                }
            }
        }

        private void entitizeComment() {
            if (this._lastEmitCch == 0) {
                return;
            }
            int i = this._lastEmitIn;
            boolean lastWasDash = false;
            for (int cch = this._lastEmitCch; cch > 0; cch--) {
                char ch = this._cbuf[i];
                if (isBadChar(ch)) {
                    i = replace(i, "?");
                } else if (ch == '-') {
                    if (lastWasDash) {
                        i = replace(i, StringUtils.SPACE);
                        lastWasDash = false;
                    } else {
                        lastWasDash = true;
                        i++;
                    }
                } else {
                    lastWasDash = false;
                    i++;
                }
                if (i == this._cbuf.length) {
                    i = 0;
                }
            }
            int cch2 = this._lastEmitIn;
            int offset = ((cch2 + this._lastEmitCch) - 1) % this._cbuf.length;
            if (this._cbuf[offset] == '-') {
                replace(offset, StringUtils.SPACE);
            }
        }

        private void entitizeProcinst() {
            if (this._lastEmitCch == 0) {
                return;
            }
            int i = this._lastEmitIn;
            boolean lastWasQuestion = false;
            for (int cch = this._lastEmitCch; cch > 0; cch--) {
                char ch = this._cbuf[i];
                if (isBadChar(ch)) {
                    i = replace(i, "?");
                }
                if (ch == '>') {
                    if (lastWasQuestion) {
                        i = replace(i, StringUtils.SPACE);
                    } else {
                        i++;
                    }
                    lastWasQuestion = false;
                } else {
                    boolean lastWasQuestion2 = ch == '?';
                    i++;
                    lastWasQuestion = lastWasQuestion2;
                }
                if (i == this._cbuf.length) {
                    i = 0;
                }
            }
        }

        private boolean isEscapedChar(char ch) {
            return this._replaceChar != null && this._replaceChar.containsChar(ch);
        }

        private int replace(int i, String replacement) {
            if (replacement.isEmpty()) {
                throw new AssertionError();
            }
            int dCch = replacement.length() - 1;
            if (dCch == 0) {
                this._cbuf[i] = replacement.charAt(0);
                return i + 1;
            }
            if (this._free < 0) {
                throw new AssertionError();
            }
            if (dCch > this._free) {
                i = resize(dCch, i);
            }
            if (this._free < 0) {
                throw new AssertionError();
            }
            if (this._free < dCch) {
                throw new AssertionError();
            }
            if (getAvailable() <= 0) {
                throw new AssertionError();
            }
            int charsToCopy = dCch + 1;
            if (this._out > this._in && i >= this._out) {
                System.arraycopy(this._cbuf, this._out, this._cbuf, this._out - dCch, i - this._out);
                this._out -= dCch;
                i -= dCch;
            } else {
                if (i >= this._in) {
                    throw new AssertionError();
                }
                int availableEndChunk = this._cbuf.length - this._in;
                if (dCch <= availableEndChunk) {
                    System.arraycopy(this._cbuf, i, this._cbuf, i + dCch, this._in - i);
                    this._in = (this._in + dCch) % this._cbuf.length;
                } else if (dCch <= ((this._in + availableEndChunk) - i) - 1) {
                    int numToCopyToStart = dCch - availableEndChunk;
                    System.arraycopy(this._cbuf, this._in - numToCopyToStart, this._cbuf, 0, numToCopyToStart);
                    System.arraycopy(this._cbuf, i + 1, this._cbuf, i + 1 + dCch, ((this._in - i) - 1) - numToCopyToStart);
                    this._in = numToCopyToStart;
                } else {
                    int numToCopyToStart2 = (this._in - i) - 1;
                    charsToCopy = (this._in + availableEndChunk) - i;
                    System.arraycopy(this._cbuf, this._in - numToCopyToStart2, this._cbuf, (dCch - charsToCopy) + 1, numToCopyToStart2);
                    replacement.getChars(charsToCopy, dCch + 1, this._cbuf, 0);
                    this._in = ((numToCopyToStart2 + dCch) - charsToCopy) + 1;
                }
            }
            replacement.getChars(0, charsToCopy, this._cbuf, i);
            this._free -= dCch;
            if (this._free < 0) {
                throw new AssertionError();
            }
            if ((this._out >= this._in || this._free != this._cbuf.length - (this._in - this._out)) && ((this._out <= this._in || this._free != this._out - this._in) && ((this._out != this._in || this._free != this._cbuf.length) && (this._out != this._in || this._free != 0)))) {
                throw new AssertionError("_buf.length:" + this._cbuf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free);
            }
            return ((i + dCch) + 1) % this._cbuf.length;
        }

        private int ensure(int cch) {
            if (cch <= 0) {
                cch = 1;
            }
            int available = getAvailable();
            while (available < cch && process()) {
                available = getAvailable();
            }
            if (available != getAvailable()) {
                throw new AssertionError();
            }
            return available;
        }

        int getAvailable() {
            if (this._cbuf == null) {
                return 0;
            }
            return this._cbuf.length - this._free;
        }

        private int resize(int cch, int i) {
            if (this._free < 0) {
                throw new AssertionError();
            }
            if (cch <= 0) {
                throw new AssertionError();
            }
            if (cch < this._free) {
                throw new AssertionError();
            }
            if (this._cbuf != null && ((this._out >= this._in || this._free != this._cbuf.length - (this._in - this._out)) && ((this._out <= this._in || this._free != this._out - this._in) && ((this._out != this._in || this._free != this._cbuf.length) && (this._out != this._in || this._free != 0))))) {
                throw new AssertionError("_buf.length:" + this._cbuf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free);
            }
            long newLen = this._cbuf == null ? 4096L : this._cbuf.length * 2;
            int used = getAvailable();
            while (newLen - used < cch) {
                newLen *= 2;
            }
            int bufLen = (int) Math.min(newLen, 2147483639L);
            char[] newBuf = new char[bufLen];
            if (used > 0) {
                if (this._in > this._out) {
                    if (i != -1 && (i < this._out || i >= this._in)) {
                        throw new AssertionError();
                    }
                    System.arraycopy(this._cbuf, this._out, newBuf, 0, used);
                    i -= this._out;
                } else {
                    if (i != -1 && i < this._out && i >= this._in) {
                        throw new AssertionError();
                    }
                    int oldestSize = used - this._in;
                    System.arraycopy(this._cbuf, this._out, newBuf, 0, oldestSize);
                    System.arraycopy(this._cbuf, 0, newBuf, oldestSize, this._in);
                    i = i >= this._out ? i - this._out : i + oldestSize;
                }
                this._out = 0;
                this._in = used;
                this._free += newBuf.length - this._cbuf.length;
            } else {
                this._free = newBuf.length;
                if (this._in != 0 || this._out != 0) {
                    throw new AssertionError();
                }
                if (i != -1) {
                    throw new AssertionError();
                }
            }
            this._cbuf = newBuf;
            if (this._free < 0) {
                throw new AssertionError();
            }
            if (this._cbuf == null || ((this._out < this._in && this._free == this._cbuf.length - (this._in - this._out)) || ((this._out > this._in && this._free == this._out - this._in) || ((this._out == this._in && this._free == this._cbuf.length) || (this._out == this._in && this._free == 0))))) {
                return i;
            }
            throw new AssertionError("_cbuf.length:" + this._cbuf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free);
        }

        public int read() {
            if (ensure(1) == 0) {
                return -1;
            }
            if (getAvailable() <= 0) {
                throw new AssertionError();
            }
            char c = this._cbuf[this._out];
            this._out = (this._out + 1) % this._cbuf.length;
            this._free++;
            if ((this._out >= this._in || this._free != this._cbuf.length - (this._in - this._out)) && ((this._out <= this._in || this._free != this._out - this._in) && ((this._out != this._in || this._free != this._cbuf.length) && (this._out != this._in || this._free != 0)))) {
                throw new AssertionError("_cbuf.length:" + this._cbuf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free);
            }
            return c;
        }

        public int read(char[] cbuf, int off, int len) {
            int n = ensure(len);
            if (n == 0) {
                return -1;
            }
            if (cbuf == null || len <= 0) {
                return 0;
            }
            if (n < len) {
                len = n;
            }
            if (this._out < this._in) {
                System.arraycopy(this._cbuf, this._out, cbuf, off, len);
            } else {
                int chunk = this._cbuf.length - this._out;
                if (chunk >= len) {
                    System.arraycopy(this._cbuf, this._out, cbuf, off, len);
                } else {
                    System.arraycopy(this._cbuf, this._out, cbuf, off, chunk);
                    System.arraycopy(this._cbuf, 0, cbuf, off + chunk, len - chunk);
                }
            }
            this._out = (this._out + len) % this._cbuf.length;
            this._free += len;
            if ((this._out >= this._in || this._free != this._cbuf.length - (this._in - this._out)) && ((this._out <= this._in || this._free != this._out - this._in) && ((this._out != this._in || this._free != this._cbuf.length) && (this._out != this._in || this._free != 0)))) {
                throw new AssertionError("_cbuf.length:" + this._cbuf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free);
            }
            if (this._free < 0) {
                throw new AssertionError();
            }
            return len;
        }

        public int write(Writer writer, int cchMin) {
            while (getAvailable() < cchMin && process()) {
            }
            int charsAvailable = getAvailable();
            if (charsAvailable > 0) {
                if (this._out != 0) {
                    throw new AssertionError();
                }
                if (this._in < this._out) {
                    throw new AssertionError("_in:" + this._in + " < _out:" + this._out);
                }
                if (this._cbuf.length - this._in != this._free) {
                    this._in = this._cbuf.length;
                }
                if (this._free != this._cbuf.length - this._in) {
                    throw new AssertionError();
                }
                try {
                    writer.write(this._cbuf, 0, charsAvailable);
                    writer.flush();
                    this._free += charsAvailable;
                    if (this._free < 0) {
                        throw new AssertionError();
                    }
                    this._in = 0;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (this._cbuf == null || ((this._out < this._in && this._free == this._cbuf.length - (this._in - this._out)) || ((this._out > this._in && this._free == this._out - this._in) || ((this._out == this._in && this._free == this._cbuf.length) || (this._out == this._in && this._free == 0))))) {
                return charsAvailable;
            }
            throw new AssertionError("_cbuf.length:" + this._cbuf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free);
        }

        public String saveToString() {
            do {
            } while (process());
            if (this._out != 0) {
                throw new AssertionError();
            }
            int available = getAvailable();
            return available == 0 ? "" : new String(this._cbuf, this._out, available);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class OptimizedForSpeedSaver extends Saver {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final char[] _buf;
        Writer _w;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes11.dex */
        public static class SaverIOException extends RuntimeException {
            SaverIOException(IOException e) {
                super(e);
            }
        }

        OptimizedForSpeedSaver(Cur cur, Writer writer) {
            super(cur, XmlOptions.maskNull(null));
            this._buf = new char[1024];
            this._w = writer;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static void save(Cur cur, Writer writer) throws IOException {
            try {
                Saver saver = new OptimizedForSpeedSaver(cur, writer);
                do {
                } while (saver.process());
            } catch (SaverIOException e) {
                throw ((IOException) e.getCause());
            }
        }

        private void emit(String s) {
            try {
                this._w.write(s);
            } catch (IOException e) {
                throw new SaverIOException(e);
            }
        }

        private void emit(char c) {
            try {
                this._buf[0] = c;
                this._w.write(this._buf, 0, 1);
            } catch (IOException e) {
                throw new SaverIOException(e);
            }
        }

        private void emit(char c1, char c2) {
            try {
                this._buf[0] = c1;
                this._buf[1] = c2;
                this._w.write(this._buf, 0, 2);
            } catch (IOException e) {
                throw new SaverIOException(e);
            }
        }

        private void emit(char[] buf, int start, int len) {
            try {
                this._w.write(buf, start, len);
            } catch (IOException e) {
                throw new SaverIOException(e);
            }
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected boolean emitElement(SaveCur c, List<QName> attrNames, List<String> attrValues) {
            if (!c.isElem()) {
                throw new AssertionError();
            }
            emit(Typography.less);
            emitName(c.getName(), false);
            for (int i = 0; i < attrNames.size(); i++) {
                emitAttrHelper(attrNames.get(i), attrValues.get(i));
            }
            if (!saveNamespacesFirst()) {
                emitNamespacesHelper();
            }
            if (!c.hasChildren() && !c.hasText()) {
                emit('/', Typography.greater);
                return true;
            }
            emit(Typography.greater);
            return false;
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitFinish(SaveCur c) {
            emit(Typography.less, '/');
            emitName(c.getName(), false);
            emit(Typography.greater);
        }

        protected void emitXmlns(String prefix, String uri) {
            if (prefix == null) {
                throw new AssertionError();
            }
            if (uri == null) {
                throw new AssertionError();
            }
            emit(Sax2Dom.XMLNS_PREFIX);
            if (!prefix.isEmpty()) {
                emit(NameUtil.COLON);
                emit(prefix);
            }
            emit(Chars.EQ, '\"');
            emitAttrValue(uri);
            emit('\"');
        }

        private void emitNamespacesHelper() {
            iterateMappings();
            while (hasMapping()) {
                emit(Chars.SPACE);
                emitXmlns(mappingPrefix(), mappingUri());
                nextMapping();
            }
        }

        private void emitAttrHelper(QName attrName, String attrValue) {
            emit(Chars.SPACE);
            emitName(attrName, true);
            emit(Chars.EQ, '\"');
            emitAttrValue(attrValue);
            emit('\"');
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitComment(SaveCur c) {
            if (!c.isComment()) {
                throw new AssertionError();
            }
            emit("<!--");
            c.push();
            c.next();
            emitCommentText(c);
            c.pop();
            emit("-->");
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitProcinst(SaveCur c) {
            if (!c.isProcinst()) {
                throw new AssertionError();
            }
            emit("<?");
            emit(c.getName().getLocalPart());
            c.push();
            c.next();
            if (c.isText()) {
                emit(Chars.SPACE);
                emitPiText(c);
            }
            c.pop();
            emit("?>");
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitDocType(String docTypeName, String publicId, String systemId) {
            if (docTypeName == null) {
                throw new AssertionError();
            }
            emit("<!DOCTYPE ");
            emit(docTypeName);
            if (publicId == null && systemId != null) {
                emit(" SYSTEM ");
                emitLiteral(systemId);
            } else if (publicId != null) {
                emit(" PUBLIC ");
                emitLiteral(publicId);
                emit(Chars.SPACE);
                emitLiteral(systemId);
            }
            emit(Typography.greater);
            emit(_newLine);
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitStartDoc(SaveCur c) {
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitEndDoc(SaveCur c) {
        }

        private void emitName(QName name, boolean needsPrefix) {
            if (name == null) {
                throw new AssertionError();
            }
            String uri = name.getNamespaceURI();
            if (uri == null) {
                throw new AssertionError();
            }
            if (!uri.isEmpty()) {
                String prefix = name.getPrefix();
                String mappedUri = getNamespaceForPrefix(prefix);
                if (mappedUri == null || !mappedUri.equals(uri)) {
                    prefix = getUriMapping(uri);
                }
                if (needsPrefix && prefix.isEmpty()) {
                    prefix = getNonDefaultUriMapping(uri);
                }
                if (!prefix.isEmpty()) {
                    emit(prefix);
                    emit(NameUtil.COLON);
                }
            }
            if (name.getLocalPart().isEmpty()) {
                throw new AssertionError();
            }
            emit(name.getLocalPart());
        }

        private void emitAttrValue(CharSequence attVal) {
            int len = attVal.length();
            for (int i = 0; i < len; i++) {
                char ch = attVal.charAt(i);
                if (ch == '<') {
                    emit("&lt;");
                } else if (ch == '&') {
                    emit("&amp;");
                } else if (ch == '\"') {
                    emit("&quot;");
                } else {
                    emit(ch);
                }
            }
        }

        private void emitLiteral(String literal) {
            if (!literal.contains("\"")) {
                emit('\"');
                emit(literal);
                emit('\"');
            } else {
                emit(Chars.QUOTE);
                emit(literal);
                emit(Chars.QUOTE);
            }
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitText(SaveCur c) {
            if (!c.isText()) {
                throw new AssertionError();
            }
            Object src = c.getChars();
            int cch = c._cchSrc;
            int off = c._offSrc;
            int index = 0;
            while (index < cch) {
                int indexLimit = Math.min(index + 512, cch);
                CharUtil.getChars(this._buf, 0, src, off + index, indexLimit - index);
                entitizeAndWriteText(indexLimit - index);
                index = indexLimit;
            }
        }

        protected void emitPiText(SaveCur c) {
            if (!c.isText()) {
                throw new AssertionError();
            }
            Object src = c.getChars();
            int cch = c._cchSrc;
            int off = c._offSrc;
            int index = 0;
            while (index < cch) {
                int indexLimit = index + 512 > cch ? cch : 512;
                CharUtil.getChars(this._buf, 0, src, off + index, indexLimit);
                entitizeAndWritePIText(indexLimit - index);
                index = indexLimit;
            }
        }

        protected void emitCommentText(SaveCur c) {
            if (!c.isText()) {
                throw new AssertionError();
            }
            Object src = c.getChars();
            int cch = c._cchSrc;
            int off = c._offSrc;
            int index = 0;
            while (index < cch) {
                int indexLimit = index + 512 > cch ? cch : 512;
                CharUtil.getChars(this._buf, 0, src, off + index, indexLimit);
                entitizeAndWriteCommentText(indexLimit - index);
                index = indexLimit;
            }
        }

        private void entitizeAndWriteText(int bufLimit) {
            int index = 0;
            for (int i = 0; i < bufLimit; i++) {
                char c = this._buf[i];
                switch (c) {
                    case '&':
                        emit(this._buf, index, i - index);
                        emit("&amp;");
                        index = i + 1;
                        break;
                    case '<':
                        emit(this._buf, index, i - index);
                        emit("&lt;");
                        index = i + 1;
                        break;
                }
            }
            emit(this._buf, index, bufLimit - index);
        }

        private void entitizeAndWriteCommentText(int bufLimit) {
            boolean lastWasDash = false;
            int i = 0;
            while (i < bufLimit) {
                char ch = this._buf[i];
                if (isBadChar(ch)) {
                    this._buf[i] = '?';
                } else if (ch == '-') {
                    if (lastWasDash) {
                        this._buf[i] = Chars.SPACE;
                        lastWasDash = false;
                    } else {
                        lastWasDash = true;
                    }
                } else {
                    lastWasDash = false;
                }
                if (i == this._buf.length) {
                    i = 0;
                }
                i++;
            }
            if (this._buf[bufLimit - 1] == '-') {
                this._buf[bufLimit - 1] = Chars.SPACE;
            }
            emit(this._buf, 0, bufLimit);
        }

        private void entitizeAndWritePIText(int bufLimit) {
            boolean lastWasQuestion = false;
            int i = 0;
            while (true) {
                if (i < bufLimit) {
                    char ch = this._buf[i];
                    if (isBadChar(ch)) {
                        this._buf[i] = '?';
                        ch = '?';
                    }
                    if (ch == '>') {
                        if (lastWasQuestion) {
                            this._buf[i] = Chars.SPACE;
                        }
                        lastWasQuestion = false;
                    } else {
                        lastWasQuestion = ch == '?';
                    }
                    i++;
                } else {
                    emit(this._buf, 0, bufLimit);
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int syncWrap(Locale l, SyncWrapFun fun) throws IOException {
        int process;
        if (l.noSync()) {
            l.enter();
            try {
                return fun.process();
            } finally {
            }
        }
        synchronized (l) {
            l.enter();
            try {
                process = fun.process();
            } finally {
            }
        }
        return process;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class TextReader extends Reader {
        private boolean _closed = false;
        private final Locale _locale;
        private final TextSaver _textSaver;

        /* JADX INFO: Access modifiers changed from: package-private */
        public TextReader(Cur c, XmlOptions options) {
            this._textSaver = new TextSaver(c, options, null);
            this._locale = c._locale;
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this._closed = true;
        }

        @Override // java.io.Reader
        public boolean ready() {
            return !this._closed;
        }

        @Override // java.io.Reader
        public int read() throws IOException {
            checkClosed();
            Locale locale = this._locale;
            final TextSaver textSaver = this._textSaver;
            textSaver.getClass();
            return Saver.syncWrap(locale, new SyncWrapFun() { // from class: org.apache.xmlbeans.impl.store.Saver$TextReader$$ExternalSyntheticLambda0
                @Override // org.apache.xmlbeans.impl.store.Saver.SyncWrapFun
                public final int process() {
                    return Saver.TextSaver.this.read();
                }
            });
        }

        @Override // java.io.Reader
        public int read(final char[] cbuf) throws IOException {
            checkClosed();
            return Saver.syncWrap(this._locale, new SyncWrapFun() { // from class: org.apache.xmlbeans.impl.store.Saver$TextReader$$ExternalSyntheticLambda1
                @Override // org.apache.xmlbeans.impl.store.Saver.SyncWrapFun
                public final int process() {
                    return Saver.TextReader.this.m2689lambda$read$0$orgapachexmlbeansimplstoreSaver$TextReader(cbuf);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$read$0$org-apache-xmlbeans-impl-store-Saver$TextReader, reason: not valid java name */
        public /* synthetic */ int m2689lambda$read$0$orgapachexmlbeansimplstoreSaver$TextReader(char[] cbuf) throws IOException {
            return this._textSaver.read(cbuf, 0, cbuf == null ? 0 : cbuf.length);
        }

        @Override // java.io.Reader
        public int read(final char[] cbuf, final int off, final int len) throws IOException {
            checkClosed();
            return Saver.syncWrap(this._locale, new SyncWrapFun() { // from class: org.apache.xmlbeans.impl.store.Saver$TextReader$$ExternalSyntheticLambda2
                @Override // org.apache.xmlbeans.impl.store.Saver.SyncWrapFun
                public final int process() {
                    return Saver.TextReader.this.m2690lambda$read$1$orgapachexmlbeansimplstoreSaver$TextReader(cbuf, off, len);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$read$1$org-apache-xmlbeans-impl-store-Saver$TextReader, reason: not valid java name */
        public /* synthetic */ int m2690lambda$read$1$orgapachexmlbeansimplstoreSaver$TextReader(char[] cbuf, int off, int len) throws IOException {
            return this._textSaver.read(cbuf, off, len);
        }

        private void checkClosed() throws IOException {
            if (this._closed) {
                throw new IOException("Reader has been closed");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class InputStreamSaver extends InputStream {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private boolean _closed = false;
        private final OutputStreamWriter _converter;
        private final Locale _locale;
        private final OutputStreamImpl _outStreamImpl;
        private final TextSaver _textSaver;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Multi-variable type inference failed */
        public InputStreamSaver(Cur cur, XmlOptions xmlOptions) {
            String java2IANAMapping;
            this._locale = cur._locale;
            if (!this._locale.entered()) {
                throw new AssertionError();
            }
            XmlOptions maskNull = XmlOptions.maskNull(xmlOptions);
            this._outStreamImpl = new OutputStreamImpl();
            String str = null;
            XmlDocumentProperties docProps = Locale.getDocProps(cur, false);
            if (docProps != null && docProps.getEncoding() != null) {
                str = EncodingMap.getIANA2JavaMapping(docProps.getEncoding());
            }
            String characterEncoding = maskNull.getCharacterEncoding();
            str = characterEncoding != null ? characterEncoding : str;
            if (str != null && (java2IANAMapping = EncodingMap.getJava2IANAMapping(str)) != null) {
                str = java2IANAMapping;
            }
            str = str == null ? EncodingMap.getJava2IANAMapping("UTF8") : str;
            String iANA2JavaMapping = str != null ? EncodingMap.getIANA2JavaMapping(str) : null;
            if (iANA2JavaMapping == null) {
                throw new IllegalStateException("Unknown encoding: " + str);
            }
            try {
                this._converter = new OutputStreamWriter(this._outStreamImpl, iANA2JavaMapping);
                this._textSaver = new TextSaver(cur, maskNull, str);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this._closed = true;
        }

        private void checkClosed() throws IOException {
            if (this._closed) {
                throw new IOException("Stream closed");
            }
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            int read;
            checkClosed();
            if (this._locale.noSync()) {
                this._locale.enter();
                try {
                    return this._outStreamImpl.read();
                } finally {
                }
            }
            synchronized (this._locale) {
                this._locale.enter();
                try {
                    read = this._outStreamImpl.read();
                } finally {
                }
            }
            return read;
        }

        @Override // java.io.InputStream
        public int read(final byte[] bbuf, final int off, final int len) throws IOException {
            checkClosed();
            if (bbuf == null) {
                throw new NullPointerException("buf to read into is null");
            }
            if (off >= 0 && off <= bbuf.length) {
                return Saver.syncWrap(this._locale, new SyncWrapFun() { // from class: org.apache.xmlbeans.impl.store.Saver$InputStreamSaver$$ExternalSyntheticLambda1
                    @Override // org.apache.xmlbeans.impl.store.Saver.SyncWrapFun
                    public final int process() {
                        return Saver.InputStreamSaver.this.m2688x1d6ffe23(bbuf, off, len);
                    }
                });
            }
            throw new IndexOutOfBoundsException("Offset is not within buf");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$read$0$org-apache-xmlbeans-impl-store-Saver$InputStreamSaver, reason: not valid java name */
        public /* synthetic */ int m2688x1d6ffe23(byte[] bbuf, int off, int len) throws IOException {
            return this._outStreamImpl.read(bbuf, off, len);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int ensure(int cbyte) {
            if (cbyte <= 0) {
                cbyte = 1;
            }
            int bytesAvailable = this._outStreamImpl.getAvailable();
            while (bytesAvailable < cbyte && this._textSaver.write(this._converter, 2048) >= 2048) {
                bytesAvailable = this._outStreamImpl.getAvailable();
            }
            int bytesAvailable2 = this._outStreamImpl.getAvailable();
            return bytesAvailable2;
        }

        @Override // java.io.InputStream
        public int available() {
            try {
                return Saver.syncWrap(this._locale, new SyncWrapFun() { // from class: org.apache.xmlbeans.impl.store.Saver$InputStreamSaver$$ExternalSyntheticLambda0
                    @Override // org.apache.xmlbeans.impl.store.Saver.SyncWrapFun
                    public final int process() {
                        return Saver.InputStreamSaver.this.m2687x5543563d();
                    }
                });
            } catch (IOException e) {
                throw new AssertionError("ensure doesn't throw IOException and available() shouldn't throw either");
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$available$1$org-apache-xmlbeans-impl-store-Saver$InputStreamSaver, reason: not valid java name */
        public /* synthetic */ int m2687x5543563d() throws IOException {
            return ensure(1024);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes11.dex */
        public final class OutputStreamImpl extends OutputStream {
            static final /* synthetic */ boolean $assertionsDisabled = false;
            private static final int _initialBufSize = 4096;
            private byte[] _buf;
            private int _free;
            private int _in;
            private int _out;

            private OutputStreamImpl() {
            }

            int read() {
                if (InputStreamSaver.this.ensure(1) == 0) {
                    return -1;
                }
                if (getAvailable() <= 0) {
                    throw new AssertionError();
                }
                int bite = this._buf[this._out];
                this._out = (this._out + 1) % this._buf.length;
                this._free++;
                return bite;
            }

            int read(byte[] bbuf, int off, int len) {
                int n = InputStreamSaver.this.ensure(len);
                if (n == 0) {
                    return -1;
                }
                if (bbuf == null || len <= 0) {
                    return 0;
                }
                if (n < len) {
                    len = n;
                }
                if (this._out < this._in) {
                    System.arraycopy(this._buf, this._out, bbuf, off, len);
                } else {
                    int chunk = this._buf.length - this._out;
                    if (chunk >= len) {
                        System.arraycopy(this._buf, this._out, bbuf, off, len);
                    } else {
                        System.arraycopy(this._buf, this._out, bbuf, off, chunk);
                        System.arraycopy(this._buf, 0, bbuf, off + chunk, len - chunk);
                    }
                }
                this._out = (this._out + len) % this._buf.length;
                this._free += len;
                return len;
            }

            int getAvailable() {
                if (this._buf == null) {
                    return 0;
                }
                return this._buf.length - this._free;
            }

            @Override // java.io.OutputStream
            public void write(int bite) {
                if (this._free == 0) {
                    resize(1);
                }
                if (this._free <= 0) {
                    throw new AssertionError();
                }
                this._buf[this._in] = (byte) bite;
                this._in = (this._in + 1) % this._buf.length;
                this._free--;
            }

            @Override // java.io.OutputStream
            public void write(byte[] buf, int off, int cbyte) {
                if (cbyte < 0) {
                    throw new AssertionError();
                }
                if (cbyte == 0) {
                    return;
                }
                if (this._free < cbyte) {
                    resize(cbyte);
                }
                if (this._in == this._out) {
                    if (getAvailable() != 0 || this._buf == null || this._free != this._buf.length) {
                        throw new AssertionError();
                    }
                    this._out = 0;
                    this._in = 0;
                }
                int chunk = this._buf.length - this._in;
                if (this._in <= this._out || cbyte < chunk) {
                    System.arraycopy(buf, off, this._buf, this._in, cbyte);
                    this._in += cbyte;
                } else {
                    System.arraycopy(buf, off, this._buf, this._in, chunk);
                    System.arraycopy(buf, off + chunk, this._buf, 0, cbyte - chunk);
                    this._in = (this._in + cbyte) % this._buf.length;
                }
                this._free -= cbyte;
            }

            void resize(int cbyte) {
                if (cbyte <= this._free) {
                    throw new AssertionError(cbyte + " !> " + this._free);
                }
                long newLen = this._buf == null ? 4096L : this._buf.length * 2;
                int used = getAvailable();
                while (newLen - used < cbyte) {
                    newLen *= 2;
                }
                int bufLen = (int) Math.min(newLen, 2147483639L);
                byte[] newBuf = new byte[bufLen];
                if (used > 0) {
                    if (this._in > this._out) {
                        System.arraycopy(this._buf, this._out, newBuf, 0, used);
                    } else {
                        System.arraycopy(this._buf, this._out, newBuf, 0, used - this._in);
                        System.arraycopy(this._buf, 0, newBuf, used - this._in, this._in);
                    }
                    this._out = 0;
                    this._in = used;
                    this._free += newBuf.length - this._buf.length;
                } else {
                    this._free = newBuf.length;
                    if (this._in != this._out) {
                        throw new AssertionError();
                    }
                }
                this._buf = newBuf;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class SaxSaver extends Saver {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final AttributesImpl _attributes;
        private char[] _buf;
        private final ContentHandler _contentHandler;
        private final LexicalHandler _lexicalHandler;
        private final boolean _nsAsAttrs;

        /* JADX INFO: Access modifiers changed from: package-private */
        public SaxSaver(Cur c, XmlOptions options, ContentHandler ch, LexicalHandler lh) throws SAXException {
            super(c, options);
            this._contentHandler = ch;
            this._lexicalHandler = lh;
            this._attributes = new AttributesImpl();
            this._nsAsAttrs = !options.isSaveSaxNoNSDeclsInAttributes();
            this._contentHandler.startDocument();
            do {
                try {
                } catch (SaverSAXException e) {
                    throw e._saxException;
                }
            } while (process());
            this._contentHandler.endDocument();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes11.dex */
        public static class SaverSAXException extends RuntimeException {
            SAXException _saxException;

            SaverSAXException(SAXException e) {
                this._saxException = e;
            }
        }

        private String getPrefixedName(QName name) {
            String uri = name.getNamespaceURI();
            String local = name.getLocalPart();
            if (uri.isEmpty()) {
                return local;
            }
            String prefix = getUriMapping(uri);
            if (prefix.isEmpty()) {
                return local;
            }
            return prefix + ":" + local;
        }

        private void emitNamespacesHelper() {
            iterateMappings();
            while (hasMapping()) {
                String prefix = mappingPrefix();
                String uri = mappingUri();
                try {
                    this._contentHandler.startPrefixMapping(prefix, uri);
                    if (this._nsAsAttrs) {
                        if (prefix == null || prefix.isEmpty()) {
                            this._attributes.addAttribute("http://www.w3.org/2000/xmlns/", Sax2Dom.XMLNS_PREFIX, Sax2Dom.XMLNS_PREFIX, "CDATA", uri);
                        } else {
                            this._attributes.addAttribute("http://www.w3.org/2000/xmlns/", prefix, Sax2Dom.XMLNS_STRING + prefix, "CDATA", uri);
                        }
                    }
                    nextMapping();
                } catch (SAXException e) {
                    throw new SaverSAXException(e);
                }
            }
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected boolean emitElement(SaveCur c, List<QName> attrNames, List<String> attrValues) {
            this._attributes.clear();
            if (saveNamespacesFirst()) {
                emitNamespacesHelper();
            }
            for (int i = 0; i < attrNames.size(); i++) {
                QName name = attrNames.get(i);
                this._attributes.addAttribute(name.getNamespaceURI(), name.getLocalPart(), getPrefixedName(name), "CDATA", attrValues.get(i));
            }
            if (!saveNamespacesFirst()) {
                emitNamespacesHelper();
            }
            QName elemName = c.getName();
            try {
                this._contentHandler.startElement(elemName.getNamespaceURI(), elemName.getLocalPart(), getPrefixedName(elemName), this._attributes);
                return false;
            } catch (SAXException e) {
                throw new SaverSAXException(e);
            }
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitFinish(SaveCur c) {
            QName name = c.getName();
            try {
                this._contentHandler.endElement(name.getNamespaceURI(), name.getLocalPart(), getPrefixedName(name));
                iterateMappings();
                while (hasMapping()) {
                    this._contentHandler.endPrefixMapping(mappingPrefix());
                    nextMapping();
                }
            } catch (SAXException e) {
                throw new SaverSAXException(e);
            }
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitText(SaveCur c) {
            if (!c.isText()) {
                throw new AssertionError();
            }
            Object src = c.getChars();
            try {
                if (src instanceof char[]) {
                    this._contentHandler.characters((char[]) src, c._offSrc, c._cchSrc);
                    return;
                }
                if (this._buf == null) {
                    this._buf = new char[1024];
                }
                while (c._cchSrc > 0) {
                    int cch = Math.min(this._buf.length, c._cchSrc);
                    CharUtil.getChars(this._buf, 0, src, c._offSrc, cch);
                    this._contentHandler.characters(this._buf, 0, cch);
                    c._offSrc += cch;
                    c._cchSrc -= cch;
                }
            } catch (SAXException e) {
                throw new SaverSAXException(e);
            }
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitComment(SaveCur c) {
            if (this._lexicalHandler != null) {
                c.push();
                c.next();
                try {
                    if (!c.isText()) {
                        this._lexicalHandler.comment(null, 0, 0);
                    } else {
                        Object src = c.getChars();
                        if (src instanceof char[]) {
                            this._lexicalHandler.comment((char[]) src, c._offSrc, c._cchSrc);
                        } else {
                            if (this._buf == null || this._buf.length < c._cchSrc) {
                                this._buf = new char[Math.max(1024, c._cchSrc)];
                            }
                            CharUtil.getChars(this._buf, 0, src, c._offSrc, c._cchSrc);
                            this._lexicalHandler.comment(this._buf, 0, c._cchSrc);
                        }
                    }
                    c.pop();
                } catch (SAXException e) {
                    throw new SaverSAXException(e);
                }
            }
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitProcinst(SaveCur c) {
            c.push();
            c.next();
            String value = CharUtil.getString(c.getChars(), c._offSrc, c._cchSrc);
            c.pop();
            try {
                this._contentHandler.processingInstruction(c.getName().getLocalPart(), value);
            } catch (SAXException e) {
                throw new SaverSAXException(e);
            }
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitDocType(String docTypeName, String publicId, String systemId) {
            if (this._lexicalHandler != null) {
                try {
                    this._lexicalHandler.startDTD(docTypeName, publicId, systemId);
                    this._lexicalHandler.endDTD();
                } catch (SAXException e) {
                    throw new SaverSAXException(e);
                }
            }
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitStartDoc(SaveCur c) {
        }

        @Override // org.apache.xmlbeans.impl.store.Saver
        protected void emitEndDoc(SaveCur c) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static abstract class SaveCur {
        int _cchSrc;
        int _offSrc;

        abstract List<String> getAncestorNamespaces();

        abstract String getAttrValue();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Object getChars();

        abstract XmlDocumentProperties getDocProps();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract QName getName();

        abstract String getXmlnsPrefix();

        abstract String getXmlnsUri();

        abstract boolean hasChildren();

        abstract boolean hasText();

        abstract boolean isTextCData();

        abstract boolean isXmlns();

        abstract int kind();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract boolean next();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void pop();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void push();

        abstract void release();

        abstract void toEnd();

        abstract boolean toFirstAttr();

        abstract boolean toNextAttr();

        SaveCur() {
        }

        final boolean isRoot() {
            return kind() == 1;
        }

        final boolean isElem() {
            return kind() == 2;
        }

        final boolean isAttr() {
            return kind() == 3;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final boolean isText() {
            return kind() == 0;
        }

        final boolean isComment() {
            return kind() == 4;
        }

        final boolean isProcinst() {
            return kind() == 5;
        }

        final boolean isFinish() {
            return Cur.kindIsFinish(kind());
        }

        final boolean isContainer() {
            return Cur.kindIsContainer(kind());
        }

        final boolean isNormalAttr() {
            return kind() == 3 && !isXmlns();
        }

        final boolean skip() {
            toEnd();
            return next();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class DocSaveCur extends SaveCur {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Cur _cur;

        DocSaveCur(Cur c) {
            if (!c.isRoot()) {
                throw new AssertionError();
            }
            this._cur = c.weakCur(this);
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        void release() {
            this._cur.release();
            this._cur = null;
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        int kind() {
            return this._cur.kind();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        QName getName() {
            return this._cur.getName();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        String getXmlnsPrefix() {
            return this._cur.getXmlnsPrefix();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        String getXmlnsUri() {
            return this._cur.getXmlnsUri();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean isXmlns() {
            return this._cur.isXmlns();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean hasChildren() {
            return this._cur.hasChildren();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean hasText() {
            return this._cur.hasText();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean isTextCData() {
            return this._cur.isTextCData();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean toFirstAttr() {
            return this._cur.toFirstAttr();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean toNextAttr() {
            return this._cur.toNextAttr();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        String getAttrValue() {
            if (!this._cur.isAttr()) {
                throw new AssertionError();
            }
            return this._cur.getValueAsString();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        void toEnd() {
            this._cur.toEnd();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean next() {
            return this._cur.next();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        void push() {
            this._cur.push();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        void pop() {
            this._cur.pop();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        List<String> getAncestorNamespaces() {
            return null;
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        Object getChars() {
            Object o = this._cur.getChars(-1);
            this._offSrc = this._cur._offSrc;
            this._cchSrc = this._cur._cchSrc;
            return o;
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        XmlDocumentProperties getDocProps() {
            return Locale.getDocProps(this._cur, false);
        }
    }

    /* loaded from: classes11.dex */
    private static abstract class FilterSaveCur extends SaveCur {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private SaveCur _cur;

        protected abstract boolean filter();

        FilterSaveCur(SaveCur c) {
            if (!c.isRoot()) {
                throw new AssertionError();
            }
            this._cur = c;
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        void release() {
            this._cur.release();
            this._cur = null;
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        int kind() {
            return this._cur.kind();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        QName getName() {
            return this._cur.getName();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        String getXmlnsPrefix() {
            return this._cur.getXmlnsPrefix();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        String getXmlnsUri() {
            return this._cur.getXmlnsUri();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean isXmlns() {
            return this._cur.isXmlns();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean hasChildren() {
            return this._cur.hasChildren();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean hasText() {
            return this._cur.hasText();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean isTextCData() {
            return this._cur.isTextCData();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean toFirstAttr() {
            return this._cur.toFirstAttr();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean toNextAttr() {
            return this._cur.toNextAttr();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        String getAttrValue() {
            return this._cur.getAttrValue();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        void toEnd() {
            this._cur.toEnd();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean next() {
            if (!this._cur.next()) {
                return false;
            }
            if (!filter()) {
                return true;
            }
            if (isRoot() || isText() || isAttr()) {
                throw new AssertionError();
            }
            toEnd();
            return next();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        void push() {
            this._cur.push();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        void pop() {
            this._cur.pop();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        List<String> getAncestorNamespaces() {
            return this._cur.getAncestorNamespaces();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        Object getChars() {
            Object o = this._cur.getChars();
            this._offSrc = this._cur._offSrc;
            this._cchSrc = this._cur._cchSrc;
            return o;
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        XmlDocumentProperties getDocProps() {
            return this._cur.getDocProps();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class FilterPiSaveCur extends FilterSaveCur {
        private final String _piTarget;

        FilterPiSaveCur(SaveCur c, String target) {
            super(c);
            this._piTarget = target;
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.FilterSaveCur
        protected boolean filter() {
            return kind() == 5 && getName().getLocalPart().equals(this._piTarget);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class FragSaveCur extends SaveCur {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final int CUR = 5;
        private static final int ELEM_END = 4;
        private static final int ELEM_START = 2;
        private static final int ROOT_END = 3;
        private static final int ROOT_START = 1;
        private ArrayList<String> _ancestorNamespaces;
        private Cur _cur;
        private final QName _elem;
        private Cur _end;
        private final boolean _saveAttr;
        private int _state;
        private int[] _stateStack;
        private int _stateStackSize;

        FragSaveCur(Cur start, Cur end, QName synthElem) {
            this._saveAttr = start.isAttr() && start.isSamePos(end);
            this._cur = start.weakCur(this);
            this._end = end.weakCur(this);
            this._elem = synthElem;
            this._state = 1;
            this._stateStack = new int[8];
            start.push();
            computeAncestorNamespaces(start);
            start.pop();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        List<String> getAncestorNamespaces() {
            return this._ancestorNamespaces;
        }

        private void computeAncestorNamespaces(Cur c) {
            this._ancestorNamespaces = new ArrayList<>();
            while (c.toParentRaw()) {
                if (c.toFirstAttr()) {
                    do {
                        if (c.isXmlns()) {
                            String prefix = c.getXmlnsPrefix();
                            String uri = c.getXmlnsUri();
                            if (!uri.isEmpty() || prefix.isEmpty()) {
                                this._ancestorNamespaces.add(c.getXmlnsPrefix());
                                this._ancestorNamespaces.add(c.getXmlnsUri());
                            }
                        }
                    } while (c.toNextAttr());
                    c.toParent();
                }
            }
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        void release() {
            this._cur.release();
            this._cur = null;
            this._end.release();
            this._end = null;
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        int kind() {
            switch (this._state) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return -1;
                case 4:
                    return -2;
                default:
                    if (this._state != 5) {
                        throw new AssertionError();
                    }
                    return this._cur.kind();
            }
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        QName getName() {
            switch (this._state) {
                case 1:
                case 3:
                    return null;
                case 2:
                case 4:
                    return this._elem;
                default:
                    if (this._state != 5) {
                        throw new AssertionError();
                    }
                    return this._cur.getName();
            }
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        String getXmlnsPrefix() {
            if (this._state != 5 || !this._cur.isAttr()) {
                throw new AssertionError();
            }
            return this._cur.getXmlnsPrefix();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        String getXmlnsUri() {
            if (this._state != 5 || !this._cur.isAttr()) {
                throw new AssertionError();
            }
            return this._cur.getXmlnsUri();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean isXmlns() {
            if (this._state != 5 || !this._cur.isAttr()) {
                throw new AssertionError();
            }
            return this._cur.isXmlns();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean hasChildren() {
            boolean hasChildren = false;
            if (isContainer()) {
                push();
                next();
                if (!isText() && !isFinish()) {
                    hasChildren = true;
                }
                pop();
            }
            return hasChildren;
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean hasText() {
            boolean hasText = false;
            if (isContainer()) {
                push();
                next();
                if (isText()) {
                    hasText = true;
                }
                pop();
            }
            return hasText;
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean isTextCData() {
            return this._cur.isTextCData();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        Object getChars() {
            if (this._state != 5 || !this._cur.isText()) {
                throw new AssertionError();
            }
            Object src = this._cur.getChars(-1);
            this._offSrc = this._cur._offSrc;
            this._cchSrc = this._cur._cchSrc;
            return src;
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean next() {
            switch (this._state) {
                case 1:
                    this._state = this._elem != null ? 2 : 5;
                    return true;
                case 2:
                    if (this._saveAttr) {
                        this._state = 4;
                        return true;
                    }
                    if (this._cur.isAttr()) {
                        this._cur.toParent();
                        this._cur.next();
                    }
                    if (this._cur.isSamePos(this._end)) {
                        this._state = 4;
                        return true;
                    }
                    this._state = 5;
                    return true;
                case 3:
                    return false;
                case 4:
                    this._state = 3;
                    return true;
                case 5:
                    if (this._cur.isAttr()) {
                        throw new AssertionError();
                    }
                    this._cur.next();
                    if (this._cur.isSamePos(this._end)) {
                        this._state = this._elem != null ? 4 : 3;
                        return true;
                    }
                    return true;
                default:
                    return true;
            }
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        void toEnd() {
            switch (this._state) {
                case 1:
                    this._state = 3;
                    return;
                case 2:
                    this._state = 4;
                    return;
                case 3:
                case 4:
                    return;
                default:
                    if (this._state != 5 || this._cur.isAttr() || this._cur.isText()) {
                        throw new AssertionError();
                    }
                    this._cur.toEnd();
                    return;
            }
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean toFirstAttr() {
            switch (this._state) {
                case 1:
                case 3:
                case 4:
                    return false;
                case 2:
                default:
                    if (this._state != 2) {
                        throw new AssertionError();
                    }
                    if (!this._cur.isAttr()) {
                        return false;
                    }
                    this._state = 5;
                    return true;
                case 5:
                    return this._cur.toFirstAttr();
            }
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean toNextAttr() {
            if (this._state == 5) {
                return !this._saveAttr && this._cur.toNextAttr();
            }
            throw new AssertionError();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        String getAttrValue() {
            if (this._state != 5 || !this._cur.isAttr()) {
                throw new AssertionError();
            }
            return this._cur.getValueAsString();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        void push() {
            if (this._stateStackSize == this._stateStack.length) {
                int[] newStateStack = new int[this._stateStackSize * 2];
                System.arraycopy(this._stateStack, 0, newStateStack, 0, this._stateStackSize);
                this._stateStack = newStateStack;
            }
            int[] newStateStack2 = this._stateStack;
            int i = this._stateStackSize;
            this._stateStackSize = i + 1;
            newStateStack2[i] = this._state;
            this._cur.push();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        void pop() {
            this._cur.pop();
            int[] iArr = this._stateStack;
            int i = this._stateStackSize - 1;
            this._stateStackSize = i;
            this._state = iArr[i];
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        XmlDocumentProperties getDocProps() {
            return Locale.getDocProps(this._cur, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class PrettySaveCur extends SaveCur {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final SaveCur _cur;
        private int _depth;
        private int _prettyIndent;
        private int _prettyOffset;
        private String _txt;
        private final boolean _useCDataBookmarks;
        private boolean _isTextCData = false;
        private final StringBuffer _sb = new StringBuffer();
        private final ArrayList<Object> _stack = new ArrayList<>();

        PrettySaveCur(SaveCur c, XmlOptions options) {
            this._cur = c;
            if (options == null) {
                throw new AssertionError();
            }
            this._prettyIndent = 2;
            if (options.getSavePrettyPrintIndent() != null) {
                this._prettyIndent = options.getSavePrettyPrintIndent().intValue();
            }
            if (options.getSavePrettyPrintOffset() != null) {
                this._prettyOffset = options.getSavePrettyPrintOffset().intValue();
            }
            this._useCDataBookmarks = options.isUseCDataBookmarks();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        List<String> getAncestorNamespaces() {
            return this._cur.getAncestorNamespaces();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        void release() {
            this._cur.release();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        int kind() {
            if (this._txt == null) {
                return this._cur.kind();
            }
            return 0;
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        QName getName() {
            if (this._txt != null) {
                throw new AssertionError();
            }
            return this._cur.getName();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        String getXmlnsPrefix() {
            if (this._txt != null) {
                throw new AssertionError();
            }
            return this._cur.getXmlnsPrefix();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        String getXmlnsUri() {
            if (this._txt != null) {
                throw new AssertionError();
            }
            return this._cur.getXmlnsUri();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean isXmlns() {
            return this._txt == null && this._cur.isXmlns();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean hasChildren() {
            return this._txt == null && this._cur.hasChildren();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean hasText() {
            return this._txt == null && this._cur.hasText();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean isTextCData() {
            return this._txt == null ? this._useCDataBookmarks && this._cur.isTextCData() : this._isTextCData;
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean toFirstAttr() {
            if (this._txt != null) {
                throw new AssertionError();
            }
            return this._cur.toFirstAttr();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean toNextAttr() {
            if (this._txt != null) {
                throw new AssertionError();
            }
            return this._cur.toNextAttr();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        String getAttrValue() {
            if (this._txt != null) {
                throw new AssertionError();
            }
            return this._cur.getAttrValue();
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        void toEnd() {
            if (this._txt != null) {
                throw new AssertionError();
            }
            this._cur.toEnd();
            if (this._cur.kind() == -2) {
                this._depth--;
            }
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        boolean next() {
            int prevKind;
            if (this._txt != null) {
                if (this._txt.isEmpty()) {
                    throw new AssertionError();
                }
                if (this._cur.isText()) {
                    throw new AssertionError();
                }
                this._txt = null;
                this._isTextCData = false;
                prevKind = this._cur.kind();
            } else {
                int prevKind2 = this._cur.kind();
                if (!this._cur.next()) {
                    return false;
                }
                this._sb.delete(0, this._sb.length());
                if (this._txt != null) {
                    throw new AssertionError();
                }
                if (this._cur.isText()) {
                    this._isTextCData = this._useCDataBookmarks && this._cur.isTextCData();
                    CharUtil.getString(this._sb, this._cur.getChars(), this._cur._offSrc, this._cur._cchSrc);
                    this._cur.next();
                    int k = this._cur.kind();
                    if (prevKind2 != 2 || k != -2) {
                        trim(this._sb);
                    }
                }
                int k2 = this._cur.kind();
                if (this._prettyIndent >= 0 && prevKind2 != 4 && prevKind2 != 5 && (prevKind2 != 2 || k2 != -2)) {
                    if (this._sb.length() > 0) {
                        this._sb.insert(0, Saver._newLine);
                        spaces(this._sb, Saver._newLine.length(), this._prettyOffset + (this._prettyIndent * this._depth));
                    }
                    if (k2 != -1) {
                        if (prevKind2 != 1) {
                            this._sb.append(Saver._newLine);
                        }
                        int d = this._depth;
                        if (k2 < 0) {
                            d--;
                        }
                        spaces(this._sb, this._sb.length(), this._prettyOffset + (this._prettyIndent * d));
                    }
                }
                if (this._sb.length() <= 0) {
                    prevKind = k2;
                } else {
                    this._txt = this._sb.toString();
                    prevKind = 0;
                }
            }
            if (prevKind == 2) {
                this._depth++;
            } else if (prevKind == -2) {
                this._depth--;
            }
            return true;
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        void push() {
            this._cur.push();
            this._stack.add(this._txt);
            this._stack.add(Integer.valueOf(this._depth));
            this._isTextCData = false;
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        void pop() {
            this._cur.pop();
            this._depth = ((Integer) this._stack.remove(this._stack.size() - 1)).intValue();
            this._txt = (String) this._stack.remove(this._stack.size() - 1);
            this._isTextCData = false;
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        Object getChars() {
            if (this._txt != null) {
                this._offSrc = 0;
                this._cchSrc = this._txt.length();
                return this._txt;
            }
            Object o = this._cur.getChars();
            this._offSrc = this._cur._offSrc;
            this._cchSrc = this._cur._cchSrc;
            return o;
        }

        @Override // org.apache.xmlbeans.impl.store.Saver.SaveCur
        XmlDocumentProperties getDocProps() {
            return this._cur.getDocProps();
        }

        static void spaces(StringBuffer sb, int offset, int count) {
            while (true) {
                int count2 = count - 1;
                if (count > 0) {
                    sb.insert(offset, Chars.SPACE);
                    count = count2;
                } else {
                    return;
                }
            }
        }

        static void trim(StringBuffer sb) {
            int i = 0;
            while (i < sb.length() && CharUtil.isWhiteSpace(sb.charAt(i))) {
                i++;
            }
            sb.delete(0, i);
            int i2 = sb.length();
            while (i2 > 0 && CharUtil.isWhiteSpace(sb.charAt(i2 - 1))) {
                i2--;
            }
            sb.delete(i2, sb.length());
        }
    }
}
