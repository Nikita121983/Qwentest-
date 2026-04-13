package org.apache.xmlbeans.impl.store;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlDocumentProperties;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.store.Saver;
import org.w3c.dom.Node;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class DomSaver extends Saver {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final boolean _isFrag;
    private Cur _nodeCur;
    private final XmlOptions _options;
    private final SchemaTypeLoader _stl;
    private SchemaType _type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DomSaver(Cur c, boolean isFrag, XmlOptions options) {
        super(c, options);
        if (c.isUserNode()) {
            this._type = c.getUser().get_schema_type();
        }
        this._stl = c._locale._schemaTypeLoader;
        this._options = options;
        this._isFrag = isFrag;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Node saveDom() {
        Locale l = Locale.getLocale(this._stl, this._options);
        l.enter();
        try {
            this._nodeCur = l.getCur();
            do {
            } while (process());
            while (!this._nodeCur.isRoot()) {
                this._nodeCur.toParent();
            }
            if (this._type != null) {
                this._nodeCur.setType(this._type);
            }
            Node node = (Node) this._nodeCur.getDom();
            this._nodeCur.release();
            this._nodeCur = null;
            return node;
        } finally {
            l.exit();
        }
    }

    @Override // org.apache.xmlbeans.impl.store.Saver
    protected boolean emitElement(Saver.SaveCur c, List<QName> attrNames, List<String> attrValues) {
        if (Locale.isFragmentQName(c.getName())) {
            this._nodeCur.moveTo(null, -2);
        }
        ensureDoc();
        this._nodeCur.createElement(getQualifiedName(c, c.getName()));
        this._nodeCur.next();
        iterateMappings();
        while (hasMapping()) {
            this._nodeCur.createAttr(this._nodeCur._locale.createXmlns(mappingPrefix()));
            this._nodeCur.next();
            this._nodeCur.insertString(mappingUri());
            this._nodeCur.toParent();
            this._nodeCur.skipWithAttrs();
            nextMapping();
        }
        for (int i = 0; i < attrNames.size(); i++) {
            this._nodeCur.createAttr(getQualifiedName(c, attrNames.get(i)));
            this._nodeCur.next();
            this._nodeCur.insertString(attrValues.get(i));
            this._nodeCur.toParent();
            this._nodeCur.skipWithAttrs();
        }
        return false;
    }

    @Override // org.apache.xmlbeans.impl.store.Saver
    protected void emitFinish(Saver.SaveCur c) {
        if (!Locale.isFragmentQName(c.getName())) {
            if (!this._nodeCur.isEnd()) {
                throw new AssertionError();
            }
            this._nodeCur.next();
        }
    }

    @Override // org.apache.xmlbeans.impl.store.Saver
    protected void emitText(Saver.SaveCur c) {
        ensureDoc();
        Object src = c.getChars();
        if (c._cchSrc > 0) {
            this._nodeCur.insertChars(src, c._offSrc, c._cchSrc);
            this._nodeCur.next();
        }
    }

    @Override // org.apache.xmlbeans.impl.store.Saver
    protected void emitComment(Saver.SaveCur c) {
        ensureDoc();
        this._nodeCur.createComment();
        emitTextValue(c);
        this._nodeCur.skip();
    }

    @Override // org.apache.xmlbeans.impl.store.Saver
    protected void emitProcinst(Saver.SaveCur c) {
        ensureDoc();
        this._nodeCur.createProcinst(c.getName().getLocalPart());
        emitTextValue(c);
        this._nodeCur.skip();
    }

    @Override // org.apache.xmlbeans.impl.store.Saver
    protected void emitDocType(String docTypeName, String publicId, String systemId) {
        ensureDoc();
        XmlDocumentProperties props = Locale.getDocProps(this._nodeCur, true);
        props.setDoctypeName(docTypeName);
        props.setDoctypePublicId(publicId);
        props.setDoctypeSystemId(systemId);
    }

    @Override // org.apache.xmlbeans.impl.store.Saver
    protected void emitStartDoc(Saver.SaveCur c) {
        ensureDoc();
    }

    @Override // org.apache.xmlbeans.impl.store.Saver
    protected void emitEndDoc(Saver.SaveCur c) {
    }

    private QName getQualifiedName(Saver.SaveCur c, QName name) {
        String uri = name.getNamespaceURI();
        String prefix = uri.length() > 0 ? getUriMapping(uri) : "";
        if (prefix.equals(name.getPrefix())) {
            return name;
        }
        return this._nodeCur._locale.makeQName(uri, name.getLocalPart(), prefix);
    }

    private void emitTextValue(Saver.SaveCur c) {
        c.push();
        c.next();
        if (c.isText()) {
            this._nodeCur.next();
            this._nodeCur.insertChars(c.getChars(), c._offSrc, c._cchSrc);
            this._nodeCur.toParent();
        }
        c.pop();
    }

    private void ensureDoc() {
        if (!this._nodeCur.isPositioned()) {
            if (this._isFrag) {
                this._nodeCur.createDomDocFragRoot();
            } else {
                this._nodeCur.createDomDocumentRoot();
            }
            this._nodeCur.next();
        }
    }
}
