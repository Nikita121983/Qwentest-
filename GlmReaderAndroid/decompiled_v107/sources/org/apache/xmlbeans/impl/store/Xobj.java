package org.apache.xmlbeans.impl.store;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.CDataBookmark;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.apache.xmlbeans.impl.common.ValidatorListener;
import org.apache.xmlbeans.impl.common.XmlLocale;
import org.apache.xmlbeans.impl.store.DomImpl;
import org.apache.xmlbeans.impl.store.Locale;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.TypeStoreUser;
import org.apache.xmlbeans.impl.values.TypeStoreUserFactory;
import org.apache.xmlbeans.impl.values.TypeStoreVisitor;
import org.apache.xmlbeans.impl.xpath.XPathFactory;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public abstract class Xobj implements TypeStore {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int INHIBIT_DISCONNECT = 1024;
    static final int STABLE_USER = 512;
    static final int VACANT = 256;
    int _bits;
    Bookmark _bookmarks;
    int _cchAfter;
    int _cchValue;
    CharNode _charNodesAfter;
    CharNode _charNodesValue;
    Cur _embedded;
    Xobj _firstChild;
    Xobj _lastChild;
    Locale _locale;
    QName _name;
    Xobj _nextSibling;
    int _offAfter;
    int _offValue;
    Xobj _parent;
    Xobj _prevSibling;
    Object _srcAfter;
    Object _srcValue;
    TypeStoreUser _user;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract DomImpl.Dom getDom();

    abstract Xobj newNode(Locale locale);

    /* JADX INFO: Access modifiers changed from: package-private */
    public Xobj(Locale l, int kind, int domType) {
        if (kind != 1 && kind != 2 && kind != 3 && kind != 4 && kind != 5) {
            throw new AssertionError();
        }
        this._locale = l;
        this._bits = (domType << 4) + kind;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int kind() {
        return this._bits & 15;
    }

    final int domType() {
        return (this._bits & 240) >> 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isRoot() {
        return kind() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isAttr() {
        return kind() == 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isElem() {
        return kind() == 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isProcinst() {
        return kind() == 5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isComment() {
        return kind() == 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isContainer() {
        return Cur.kindIsContainer(kind());
    }

    final boolean isUserNode() {
        int k = kind();
        if (k == 2 || k == 1) {
            return true;
        }
        return k == 3 && !isXmlns();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isNormalAttr() {
        return isAttr() && !Locale.isXmlns(this._name);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isXmlns() {
        return isAttr() && Locale.isXmlns(this._name);
    }

    final int cchAfter() {
        return this._cchAfter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int posAfter() {
        return this._cchValue + 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int posMax() {
        return this._cchValue + 2 + this._cchAfter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String getXmlnsPrefix() {
        return Locale.xmlnsPrefix(this._name);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String getXmlnsUri() {
        return getValueAsString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean hasTextEnsureOccupancy() {
        ensureOccupancy();
        return hasTextNoEnsureOccupancy();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean hasTextNoEnsureOccupancy() {
        if (this._cchValue > 0) {
            return true;
        }
        Xobj lastAttr = lastAttr();
        return lastAttr != null && lastAttr._cchAfter > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean hasAttrs() {
        return this._firstChild != null && this._firstChild.isAttr();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean hasChildren() {
        return (this._lastChild == null || this._lastChild.isAttr()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getDomZeroOneChildren() {
        if (this._firstChild == null && this._srcValue == null && this._charNodesValue == null) {
            return 0;
        }
        if (this._lastChild != null && this._lastChild.isAttr() && this._lastChild._charNodesAfter == null && this._lastChild._srcAfter == null && this._srcValue == null && this._charNodesValue == null) {
            return 0;
        }
        if (this._firstChild == this._lastChild && this._firstChild != null && !this._firstChild.isAttr() && this._srcValue == null && this._charNodesValue == null && this._firstChild._srcAfter == null) {
            return 1;
        }
        if (this._firstChild == null && this._srcValue != null && (this._charNodesValue == null || (this._charNodesValue._next == null && this._charNodesValue._cch == this._cchValue))) {
            return 1;
        }
        Xobj lastAttr = lastAttr();
        Xobj node = lastAttr == null ? null : lastAttr._nextSibling;
        return (lastAttr != null && lastAttr._srcAfter == null && node != null && node._srcAfter == null && node._nextSibling == null) ? 1 : 2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isFirstChildPtrDomUsable() {
        if (this._firstChild == null && this._srcValue == null && this._charNodesValue == null) {
            return true;
        }
        if (this._firstChild != null && !this._firstChild.isAttr() && this._srcValue == null && this._charNodesValue == null) {
            if (this._firstChild instanceof NodeXobj) {
                return true;
            }
            throw new AssertionError("wrong node type");
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isNextSiblingPtrDomUsable() {
        if (this._charNodesAfter == null && this._srcAfter == null) {
            if (this._nextSibling == null || (this._nextSibling instanceof NodeXobj)) {
                return true;
            }
            throw new AssertionError("wrong node type");
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isExistingCharNodesValueUsable() {
        return this._srcValue != null && this._charNodesValue != null && this._charNodesValue._next == null && this._charNodesValue._cch == this._cchValue;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isCharNodesValueUsable() {
        if (!isExistingCharNodesValueUsable()) {
            CharNode updateCharNodes = Cur.updateCharNodes(this._locale, this, this._charNodesValue, this._cchValue);
            this._charNodesValue = updateCharNodes;
            if (updateCharNodes == null) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isCharNodesAfterUsable() {
        if (this._srcAfter == null) {
            return false;
        }
        if (this._charNodesAfter != null && this._charNodesAfter._next == null && this._charNodesAfter._cch == this._cchAfter) {
            return true;
        }
        CharNode updateCharNodes = Cur.updateCharNodes(this._locale, this, this._charNodesAfter, this._cchAfter);
        this._charNodesAfter = updateCharNodes;
        return updateCharNodes != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Xobj lastAttr() {
        if (this._firstChild == null || !this._firstChild.isAttr()) {
            return null;
        }
        Xobj lastAttr = this._firstChild;
        while (lastAttr._nextSibling != null && lastAttr._nextSibling.isAttr()) {
            lastAttr = lastAttr._nextSibling;
        }
        return lastAttr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int cchLeft(int p) {
        if (isRoot() && p == 0) {
            return 0;
        }
        Xobj x = getDenormal(p);
        int p2 = posTemp();
        int pa = x.posAfter();
        return p2 - (p2 < pa ? 1 : pa);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int cchRight(int p) {
        if (p >= posMax()) {
            throw new AssertionError();
        }
        if (p <= 0) {
            return 0;
        }
        int pa = posAfter();
        return p < pa ? (pa - p) - 1 : posMax() - p;
    }

    public final Locale locale() {
        return this._locale;
    }

    public final int nodeType() {
        return domType();
    }

    public final QName getQName() {
        return this._name;
    }

    public final Cur tempCur() {
        Cur c = this._locale.tempCur();
        c.moveTo(this);
        return c;
    }

    public void dump(PrintStream o, Object ref) {
        Cur.dump(o, this, ref);
    }

    public void dump(PrintStream o) {
        Cur.dump(o, this, this);
    }

    public void dump() {
        dump(System.out);
    }

    final Cur getEmbedded() {
        this._locale.embedCurs();
        return this._embedded;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean inChars(int p, Xobj xIn, int pIn, int cch, boolean includeEnd) {
        int offset;
        if (p <= 0 || p >= posMax() || p == posAfter() - 1 || cch <= 0) {
            throw new AssertionError();
        }
        if (!xIn.isNormal(pIn)) {
            throw new AssertionError();
        }
        if (includeEnd) {
            if (xIn.isRoot() && pIn == 0) {
                return false;
            }
            xIn = xIn.getDenormal(pIn);
            pIn = xIn.posTemp();
            offset = 1;
        } else {
            offset = 0;
        }
        return xIn == this && pIn >= p && pIn < (p + cch) + offset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isJustAfterEnd(Xobj x, int p) {
        if (!x.isNormal(p)) {
            throw new AssertionError();
        }
        if (x.isRoot() && p == 0) {
            return false;
        }
        return x == this ? p == posAfter() : x.getDenormal(p) == this && x.posTemp() == posAfter();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isInSameTree(Xobj x) {
        if (this._locale != x._locale) {
            return false;
        }
        Xobj y = this;
        while (y != x) {
            if (y._parent != null) {
                y = y._parent;
            } else {
                while (x != this) {
                    if (x._parent == null) {
                        return x == y;
                    }
                    x = x._parent;
                }
                return true;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean contains(Cur c) {
        if (!c.isNormal()) {
            throw new AssertionError();
        }
        return contains(c._xobj, c._pos);
    }

    final boolean contains(Xobj x, int p) {
        if (!x.isNormal(p)) {
            throw new AssertionError();
        }
        if (this == x) {
            if (p != -1) {
                return p > 0 && p < posAfter();
            }
            return true;
        }
        if (this._firstChild == null) {
            return false;
        }
        while (x != null) {
            if (x == this) {
                return true;
            }
            x = x._parent;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Bookmark setBookmark(int p, Object key, Object value) {
        if (!isNormal(p)) {
            throw new AssertionError();
        }
        for (Bookmark b = this._bookmarks; b != null; b = b._next) {
            if (p == b._pos && key == b._key) {
                if (value == null) {
                    this._bookmarks = b.listRemove(this._bookmarks);
                    return null;
                }
                b._value = value;
                return b;
            }
        }
        if (value == null) {
            return null;
        }
        Bookmark b2 = new Bookmark();
        b2._xobj = this;
        b2._pos = p;
        b2._key = key;
        b2._value = value;
        this._bookmarks = b2.listInsert(this._bookmarks);
        return b2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean hasBookmark(Object key, int pos) {
        for (Bookmark b = this._bookmarks; b != null; b = b._next) {
            if (b._pos == pos && key == b._key) {
                return true;
            }
        }
        return false;
    }

    final Xobj findXmlnsForPrefix(String prefix) {
        if (!isContainer() || prefix == null) {
            throw new AssertionError();
        }
        for (Xobj c = this; c != null; c = c._parent) {
            for (Xobj a = c.firstAttr(); a != null; a = a.nextAttr()) {
                if (a.isXmlns() && a.getXmlnsPrefix().equals(prefix)) {
                    return a;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean removeAttr(QName name) {
        if (!isContainer()) {
            throw new AssertionError();
        }
        Xobj a = getAttr(name);
        if (a == null) {
            return false;
        }
        Cur c = a.tempCur();
        while (true) {
            c.moveNode(null);
            Xobj a2 = getAttr(name);
            if (a2 != null) {
                c.moveTo(a2);
            } else {
                c.release();
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Xobj setAttr(QName name, String value) {
        if (!isContainer()) {
            throw new AssertionError();
        }
        Cur c = tempCur();
        if (c.toAttr(name)) {
            c.removeFollowingAttrs();
        } else {
            c.next();
            c.createAttr(name);
        }
        c.setValue(value);
        Xobj a = c._xobj;
        c.release();
        return a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setName(QName newName) {
        if (!isAttr() && !isElem() && !isProcinst()) {
            throw new AssertionError();
        }
        if (newName == null) {
            throw new AssertionError();
        }
        if (!this._name.equals(newName) || !this._name.getPrefix().equals(newName.getPrefix())) {
            this._locale.notifyChange();
            QName oldName = this._name;
            this._name = newName;
            if (this instanceof NamedNodeXobj) {
                NamedNodeXobj me = (NamedNodeXobj) this;
                me._canHavePrefixUri = true;
            }
            if (!isProcinst()) {
                Xobj disconnectFromHere = this;
                if (isAttr() && this._parent != null) {
                    if (oldName.equals(Locale._xsiType) || newName.equals(Locale._xsiType)) {
                        disconnectFromHere = this._parent;
                    }
                    if (oldName.equals(Locale._xsiNil) || newName.equals(Locale._xsiNil)) {
                        this._parent.invalidateNil();
                    }
                }
                disconnectFromHere.disconnectNonRootUsers();
            }
            this._locale._versionAll++;
            this._locale._versionSansText++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Xobj ensureParent() {
        if (this._parent != null || (!isRoot() && cchAfter() == 0)) {
            return this._parent == null ? new DocumentFragXobj(this._locale).appendXobj(this) : this._parent;
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Xobj firstAttr() {
        if (this._firstChild == null || !this._firstChild.isAttr()) {
            return null;
        }
        return this._firstChild;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Xobj nextAttr() {
        if (this._firstChild != null && this._firstChild.isAttr()) {
            return this._firstChild;
        }
        if (this._nextSibling != null && this._nextSibling.isAttr()) {
            return this._nextSibling;
        }
        return null;
    }

    final boolean isValid() {
        return !isVacant() || (this._cchValue == 0 && this._user != null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int posTemp() {
        return this._locale._posTemp;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Xobj getNormal(int p) {
        if (p != -1 && (p < 0 || p > posMax())) {
            throw new AssertionError();
        }
        Xobj x = this;
        if (p == x.posMax()) {
            if (x._nextSibling != null) {
                x = x._nextSibling;
                p = 0;
            } else {
                x = x.ensureParent();
                p = -1;
            }
        } else if (p == x.posAfter() - 1) {
            p = -1;
        }
        this._locale._posTemp = p;
        return x;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Xobj getDenormal(int p) {
        if (isRoot() && p != -1 && p <= 0) {
            throw new AssertionError();
        }
        Xobj x = this;
        if (p == 0) {
            if (x._prevSibling == null) {
                x = x.ensureParent();
                p = x.posAfter() - 1;
            } else {
                x = x._prevSibling;
                p = x.posMax();
            }
        } else if (p == -1) {
            if (x._lastChild == null) {
                p = x.posAfter() - 1;
            } else {
                x = x._lastChild;
                p = x.posMax();
            }
        }
        this._locale._posTemp = p;
        return x;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isNormal(int p) {
        if (!isValid()) {
            return false;
        }
        if (p == -1 || p == 0) {
            return true;
        }
        if (p < 0 || p >= posMax()) {
            return false;
        }
        if (p >= posAfter()) {
            if (isRoot()) {
                return false;
            }
            if ((this._nextSibling != null && this._nextSibling.isAttr()) || this._parent == null || !this._parent.isContainer()) {
                return false;
            }
        }
        return p != posAfter() - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Xobj walk(Xobj root, boolean walkChildren) {
        if (this._firstChild != null && walkChildren) {
            return this._firstChild;
        }
        for (Xobj x = this; x != root; x = x._parent) {
            if (x._nextSibling != null) {
                return x._nextSibling;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void removeXobj() {
        if (this._parent != null) {
            if (this._parent._firstChild == this) {
                this._parent._firstChild = this._nextSibling;
            }
            if (this._parent._lastChild == this) {
                this._parent._lastChild = this._prevSibling;
            }
            if (this._prevSibling != null) {
                this._prevSibling._nextSibling = this._nextSibling;
            }
            if (this._nextSibling != null) {
                this._nextSibling._prevSibling = this._prevSibling;
            }
            this._parent = null;
            this._prevSibling = null;
            this._nextSibling = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void insertXobj(Xobj s) {
        if (this._locale != s._locale) {
            throw new AssertionError();
        }
        if (s.isRoot() || isRoot()) {
            throw new AssertionError();
        }
        if (s._parent != null) {
            throw new AssertionError();
        }
        if (s._prevSibling != null) {
            throw new AssertionError();
        }
        if (s._nextSibling != null) {
            throw new AssertionError();
        }
        ensureParent();
        s._parent = this._parent;
        s._prevSibling = this._prevSibling;
        s._nextSibling = this;
        if (this._prevSibling != null) {
            this._prevSibling._nextSibling = s;
        } else {
            this._parent._firstChild = s;
        }
        this._prevSibling = s;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Xobj appendXobj(Xobj c) {
        if (this._locale != c._locale) {
            throw new AssertionError();
        }
        if (c.isRoot()) {
            throw new AssertionError();
        }
        if (c._parent != null) {
            throw new AssertionError();
        }
        if (c._prevSibling != null) {
            throw new AssertionError();
        }
        if (c._nextSibling != null) {
            throw new AssertionError();
        }
        if (this._lastChild != null && this._firstChild == null) {
            throw new AssertionError();
        }
        c._parent = this;
        c._prevSibling = this._lastChild;
        if (this._lastChild == null) {
            this._firstChild = c;
        } else {
            this._lastChild._nextSibling = c;
        }
        this._lastChild = c;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void removeXobjs(Xobj first, Xobj last) {
        if (last._locale != first._locale) {
            throw new AssertionError();
        }
        if (first._parent != this) {
            throw new AssertionError();
        }
        if (last._parent != this) {
            throw new AssertionError();
        }
        if (this._firstChild == first) {
            this._firstChild = last._nextSibling;
        }
        if (this._lastChild == last) {
            this._lastChild = first._prevSibling;
        }
        if (first._prevSibling != null) {
            first._prevSibling._nextSibling = last._nextSibling;
        }
        if (last._nextSibling != null) {
            last._nextSibling._prevSibling = first._prevSibling;
        }
        first._prevSibling = null;
        last._nextSibling = null;
        while (first != null) {
            first._parent = null;
            first = first._nextSibling;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void insertXobjs(Xobj first, Xobj last) {
        if (this._locale != first._locale) {
            throw new AssertionError();
        }
        if (last._locale != first._locale) {
            throw new AssertionError();
        }
        if (first._parent != null || last._parent != null) {
            throw new AssertionError();
        }
        if (first._prevSibling != null) {
            throw new AssertionError();
        }
        if (last._nextSibling != null) {
            throw new AssertionError();
        }
        first._prevSibling = this._prevSibling;
        last._nextSibling = this;
        if (this._prevSibling != null) {
            this._prevSibling._nextSibling = first;
        } else {
            this._parent._firstChild = first;
        }
        this._prevSibling = last;
        while (first != this) {
            first._parent = this._parent;
            first = first._nextSibling;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void appendXobjs(Xobj first, Xobj last) {
        if (this._locale != first._locale) {
            throw new AssertionError();
        }
        if (last._locale != first._locale) {
            throw new AssertionError();
        }
        if (first._parent != null || last._parent != null) {
            throw new AssertionError();
        }
        if (first._prevSibling != null) {
            throw new AssertionError();
        }
        if (last._nextSibling != null) {
            throw new AssertionError();
        }
        if (first.isRoot()) {
            throw new AssertionError();
        }
        first._prevSibling = this._lastChild;
        if (this._lastChild == null) {
            this._firstChild = first;
        } else {
            this._lastChild._nextSibling = first;
        }
        this._lastChild = last;
        while (first != null) {
            first._parent = this;
            first = first._nextSibling;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void invalidateSpecialAttr(Xobj newParent) {
        if (isAttr()) {
            if (this._name.equals(Locale._xsiType)) {
                if (this._parent != null) {
                    this._parent.disconnectNonRootUsers();
                }
                if (newParent != null) {
                    newParent.disconnectNonRootUsers();
                }
            }
            if (this._name.equals(Locale._xsiNil)) {
                if (this._parent != null) {
                    this._parent.invalidateNil();
                }
                if (newParent != null) {
                    newParent.invalidateNil();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void removeCharsHelper(int p, int cchRemove, Xobj xTo, int pTo, boolean moveCurs, boolean invalidate) {
        if (p <= 0 || p >= posMax() || p == posAfter() - 1) {
            throw new AssertionError();
        }
        if (cchRemove <= 0) {
            throw new AssertionError();
        }
        if (cchRight(p) < cchRemove) {
            throw new AssertionError();
        }
        if (moveCurs && xTo == null) {
            throw new AssertionError();
        }
        Cur c = getEmbedded();
        while (c != null) {
            Cur next = c._next;
            if (c._xobj != this) {
                throw new AssertionError();
            }
            if (c._pos >= p && c._pos < p + cchRemove) {
                if (moveCurs) {
                    c.moveToNoCheck(xTo, (c._pos + pTo) - p);
                } else {
                    c.nextChars((cchRemove - c._pos) + p);
                }
            }
            if (c._xobj == this && c._pos >= p + cchRemove) {
                c._pos -= cchRemove;
            }
            c = next;
        }
        for (Bookmark b = this._bookmarks; b != null; b = b._next) {
            if (b._xobj != this) {
                throw new AssertionError();
            }
            if (b._pos >= p && b._pos < p + cchRemove) {
                if (xTo == null) {
                    throw new AssertionError();
                }
                b.moveTo(xTo, (b._pos + pTo) - p);
            }
            if (b._xobj == this && b._pos >= p + cchRemove) {
                b._pos -= cchRemove;
            }
        }
        int pa = posAfter();
        CharUtil cu = this._locale.getCharUtil();
        if (p < pa) {
            this._srcValue = cu.removeChars(p - 1, cchRemove, this._srcValue, this._offValue, this._cchValue);
            this._offValue = cu._offSrc;
            this._cchValue = cu._cchSrc;
            if (invalidate) {
                invalidateUser();
                invalidateSpecialAttr(null);
                return;
            }
            return;
        }
        this._srcAfter = cu.removeChars(p - pa, cchRemove, this._srcAfter, this._offAfter, this._cchAfter);
        this._offAfter = cu._offSrc;
        this._cchAfter = cu._cchSrc;
        if (invalidate && this._parent != null) {
            this._parent.invalidateUser();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void insertCharsHelper(int p, Object src, int off, int cch, boolean invalidate) {
        if (p <= 0) {
            throw new AssertionError();
        }
        if (p < posAfter() && !isOccupied()) {
            throw new AssertionError();
        }
        int pa = posAfter();
        if (p - (p < pa ? 1 : 2) < this._cchValue + this._cchAfter) {
            for (Cur c = getEmbedded(); c != null; c = c._next) {
                if (c._pos >= p) {
                    c._pos += cch;
                }
            }
            for (Bookmark b = this._bookmarks; b != null; b = b._next) {
                if (b._pos >= p) {
                    b._pos += cch;
                }
            }
        }
        CharUtil cu = this._locale.getCharUtil();
        if (p < pa) {
            this._srcValue = cu.insertChars(p - 1, this._srcValue, this._offValue, this._cchValue, src, off, cch);
            this._offValue = cu._offSrc;
            this._cchValue = cu._cchSrc;
            if (invalidate) {
                invalidateUser();
                invalidateSpecialAttr(null);
                return;
            }
            return;
        }
        this._srcAfter = cu.insertChars(p - pa, this._srcAfter, this._offAfter, this._cchAfter, src, off, cch);
        this._offAfter = cu._offSrc;
        this._cchAfter = cu._cchSrc;
        if (invalidate && this._parent != null) {
            this._parent.invalidateUser();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Xobj copyNode(Locale toLocale) {
        Xobj newParent = null;
        Xobj copy = null;
        Xobj x = this;
        while (true) {
            x.ensureOccupancy();
            Xobj newX = x.newNode(toLocale);
            newX._srcValue = x._srcValue;
            newX._offValue = x._offValue;
            newX._cchValue = x._cchValue;
            newX._srcAfter = x._srcAfter;
            newX._offAfter = x._offAfter;
            newX._cchAfter = x._cchAfter;
            for (Bookmark b = x._bookmarks; b != null; b = b._next) {
                if (x.hasBookmark(CDataBookmark.CDATA_BOOKMARK.getKey(), b._pos)) {
                    newX.setBookmark(b._pos, CDataBookmark.CDATA_BOOKMARK.getKey(), CDataBookmark.CDATA_BOOKMARK);
                }
            }
            if (newParent == null) {
                copy = newX;
            } else {
                newParent.appendXobj(newX);
            }
            Xobj y = x;
            Xobj walk = x.walk(this, true);
            x = walk;
            if (walk != null) {
                if (y == x._parent) {
                    newParent = newX;
                } else {
                    while (y._parent != x._parent) {
                        newParent = newParent._parent;
                        y = y._parent;
                    }
                }
            } else {
                copy._srcAfter = null;
                copy._offAfter = 0;
                copy._cchAfter = 0;
                return copy;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getCharsAsString(int p, int cch, int wsr) {
        if (cchRight(p) == 0) {
            return "";
        }
        Object src = getChars(p, cch);
        if (wsr == 1) {
            return CharUtil.getString(src, this._locale._offSrc, this._locale._cchSrc);
        }
        Locale.ScrubBuffer scrub = Locale.getScrubBuffer(wsr);
        scrub.scrub(src, this._locale._offSrc, this._locale._cchSrc);
        return scrub.getResultAsString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getCharsAfterAsString(int off, int cch) {
        int offset = this._cchValue + off + 2;
        if (offset == posMax()) {
            offset = -1;
        }
        return getCharsAsString(offset, cch, 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getCharsValueAsString(int off, int cch) {
        return getCharsAsString(off + 1, cch, 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getValueAsString(int wsr) {
        if (!hasChildren()) {
            Object src = getFirstChars();
            if (wsr == 1) {
                String s = CharUtil.getString(src, this._locale._offSrc, this._locale._cchSrc);
                int cch = s.length();
                if (cch > 0) {
                    Xobj lastAttr = lastAttr();
                    if ((lastAttr == null ? this._cchValue : lastAttr._cchAfter) != cch) {
                        throw new AssertionError();
                    }
                    if (lastAttr != null) {
                        lastAttr._srcAfter = s;
                        lastAttr._offAfter = 0;
                    } else {
                        this._srcValue = s;
                        this._offValue = 0;
                    }
                }
                return s;
            }
            Locale.ScrubBuffer scrub = Locale.getScrubBuffer(wsr);
            scrub.scrub(src, this._locale._offSrc, this._locale._cchSrc);
            return scrub.getResultAsString();
        }
        Locale.ScrubBuffer scrub2 = Locale.getScrubBuffer(wsr);
        Cur c = tempCur();
        c.push();
        c.next();
        while (!c.isAtEndOfLastPush()) {
            if (c.isText()) {
                scrub2.scrub(c.getChars(-1), c._offSrc, c._cchSrc);
            }
            if (c.isComment() || c.isProcinst()) {
                c.skip();
            } else {
                c.next();
            }
        }
        String s2 = scrub2.getResultAsString();
        c.release();
        return s2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getValueAsString() {
        return getValueAsString(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getFirstChars() {
        ensureOccupancy();
        if (this._cchValue > 0) {
            return getChars(1, -1);
        }
        Xobj lastAttr = lastAttr();
        if (lastAttr == null || lastAttr._cchAfter <= 0) {
            this._locale._offSrc = 0;
            this._locale._cchSrc = 0;
            return null;
        }
        return lastAttr.getChars(lastAttr.posAfter(), -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getChars(int pos, int cch, Cur c) {
        Object src = getChars(pos, cch);
        c._offSrc = this._locale._offSrc;
        c._cchSrc = this._locale._cchSrc;
        return src;
    }

    Object getChars(int pos, int cch) {
        if (!isNormal(pos)) {
            throw new AssertionError();
        }
        int cchRight = cchRight(pos);
        if (cch < 0 || cch > cchRight) {
            cch = cchRight;
        }
        if (cch == 0) {
            this._locale._offSrc = 0;
            this._locale._cchSrc = 0;
            return null;
        }
        return getCharsHelper(pos, cch);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getCharsHelper(int pos, int cch) {
        Object src;
        if (cch <= 0 || cchRight(pos) < cch) {
            throw new AssertionError();
        }
        int pa = posAfter();
        if (pos >= pa) {
            src = this._srcAfter;
            this._locale._offSrc = (this._offAfter + pos) - pa;
        } else {
            src = this._srcValue;
            this._locale._offSrc = (this._offValue + pos) - 1;
        }
        this._locale._cchSrc = cch;
        return src;
    }

    final void setBit(int mask) {
        this._bits |= mask;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void clearBit(int mask) {
        this._bits &= ~mask;
    }

    final boolean bitIsSet(int mask) {
        return (this._bits & mask) != 0;
    }

    final boolean bitIsClear(int mask) {
        return (this._bits & mask) == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isVacant() {
        return bitIsSet(256);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isOccupied() {
        return bitIsClear(256);
    }

    final boolean inhibitDisconnect() {
        return bitIsSet(1024);
    }

    final boolean isStableUser() {
        return bitIsSet(512);
    }

    void invalidateNil() {
        if (this._user != null) {
            this._user.invalidate_nilvalue();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStableType(SchemaType type) {
        setStableUser(((TypeStoreUserFactory) type).createTypeStoreUser());
    }

    void setStableUser(TypeStoreUser user) {
        disconnectNonRootUsers();
        disconnectUser();
        if (this._user != null) {
            throw new AssertionError();
        }
        this._user = user;
        this._user.attach_store(this);
        setBit(512);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void disconnectUser() {
        if (this._user != null && !inhibitDisconnect()) {
            ensureOccupancy();
            this._user.disconnect_store();
            this._user = null;
        }
    }

    void disconnectNonRootUsers() {
        Xobj x = this;
        while (x != null) {
            Xobj next = x.walk(this, x._user != null);
            if (!x.isRoot()) {
                x.disconnectUser();
            }
            x = next;
        }
    }

    void disconnectChildrenUsers() {
        Xobj x = walk(this, this._user == null);
        while (x != null) {
            Xobj next = x.walk(this, x._user != null);
            x.disconnectUser();
            x = next;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String namespaceForPrefix(String prefix, boolean defaultAlwaysMapped) {
        if (prefix == null) {
            prefix = "";
        }
        if (prefix.equals("xml")) {
            return "http://www.w3.org/XML/1998/namespace";
        }
        if (prefix.equals(Sax2Dom.XMLNS_PREFIX)) {
            return "http://www.w3.org/2000/xmlns/";
        }
        for (Xobj x = this; x != null; x = x._parent) {
            for (Xobj a = x._firstChild; a != null && a.isAttr(); a = a._nextSibling) {
                if (a.isXmlns() && a.getXmlnsPrefix().equals(prefix)) {
                    return a.getXmlnsUri();
                }
            }
        }
        if (defaultAlwaysMapped && prefix.isEmpty()) {
            return "";
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String prefixForNamespace(String ns, String suggestion, boolean createIfMissing) {
        if (ns == null) {
            ns = "";
        }
        if (ns.equals("http://www.w3.org/XML/1998/namespace")) {
            return "xml";
        }
        if (ns.equals("http://www.w3.org/2000/xmlns/")) {
            return Sax2Dom.XMLNS_PREFIX;
        }
        Xobj base = this;
        while (!base.isContainer()) {
            base = base.ensureParent();
        }
        if (ns.isEmpty()) {
            Xobj a = base.findXmlnsForPrefix("");
            if (a == null || a.getXmlnsUri().isEmpty()) {
                return "";
            }
            if (!createIfMissing) {
                return null;
            }
            base.setAttr(this._locale.createXmlns(null), "");
            return "";
        }
        for (Xobj c = base; c != null; c = c._parent) {
            for (Xobj a2 = c.firstAttr(); a2 != null; a2 = a2.nextAttr()) {
                if (a2.isXmlns() && a2.getXmlnsUri().equals(ns) && base.findXmlnsForPrefix(a2.getXmlnsPrefix()) == a2) {
                    return a2.getXmlnsPrefix();
                }
            }
        }
        if (!createIfMissing) {
            return null;
        }
        if (suggestion != null && (suggestion.isEmpty() || suggestion.toLowerCase(java.util.Locale.ROOT).startsWith("xml") || base.findXmlnsForPrefix(suggestion) != null)) {
            suggestion = null;
        }
        if (suggestion == null) {
            String prefixBase = QNameHelper.suggestPrefix(ns);
            suggestion = prefixBase;
            int i = 1;
            while (base.findXmlnsForPrefix(suggestion) != null) {
                suggestion = prefixBase + i;
                i++;
            }
        }
        for (Xobj c2 = base; !c2.isRoot() && !c2.ensureParent().isRoot(); c2 = c2._parent) {
        }
        base.setAttr(this._locale.createXmlns(suggestion), ns);
        return suggestion;
    }

    final QName getValueAsQName() {
        String prefix;
        String localname;
        if (hasChildren()) {
            throw new AssertionError();
        }
        String value = getValueAsString(3);
        int firstcolon = value.indexOf(58);
        if (firstcolon >= 0) {
            prefix = value.substring(0, firstcolon);
            localname = value.substring(firstcolon + 1);
        } else {
            prefix = "";
            localname = value;
        }
        String uri = namespaceForPrefix(prefix, true);
        if (uri == null) {
            return null;
        }
        return new QName(uri, localname);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Xobj getAttr(QName name) {
        for (Xobj x = this._firstChild; x != null && x.isAttr(); x = x._nextSibling) {
            if (x._name.equals(name)) {
                return x;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final QName getXsiTypeName() {
        if (!isContainer()) {
            throw new AssertionError();
        }
        Xobj a = getAttr(Locale._xsiType);
        if (a == null) {
            return null;
        }
        return a.getValueAsQName();
    }

    final XmlObject getObject() {
        if (isUserNode()) {
            return (XmlObject) getUser();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final TypeStoreUser getUser() {
        TypeStoreUser parentUser;
        TypeStoreUser create_attribute_user;
        if (!isUserNode()) {
            throw new AssertionError();
        }
        if (this._user == null && (isRoot() || isStableUser())) {
            throw new AssertionError();
        }
        if (this._user == null) {
            if (this._parent == null) {
                parentUser = ((TypeStoreUserFactory) XmlBeans.NO_TYPE).createTypeStoreUser();
            } else {
                parentUser = this._parent.getUser();
            }
            if (isElem()) {
                create_attribute_user = parentUser.create_element_user(this._name, getXsiTypeName());
            } else {
                create_attribute_user = parentUser.create_attribute_user(this._name);
            }
            this._user = create_attribute_user;
            this._user.attach_store(this);
        }
        TypeStoreUser parentUser2 = this._user;
        return parentUser2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void invalidateUser() {
        if (!isValid()) {
            throw new AssertionError();
        }
        if (this._user != null && !isUserNode()) {
            throw new AssertionError();
        }
        if (this._user != null) {
            this._user.invalidate_value();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void ensureOccupancy() {
        if (!isValid()) {
            throw new AssertionError();
        }
        if (isVacant()) {
            if (!isUserNode()) {
                throw new AssertionError();
            }
            clearBit(256);
            TypeStoreUser user = this._user;
            this._user = null;
            String value = user.build_text(this);
            long saveVersion = this._locale._versionAll;
            long saveVersionSansText = this._locale._versionSansText;
            setValue(value);
            if (saveVersionSansText != this._locale._versionSansText) {
                throw new AssertionError();
            }
            this._locale._versionAll = saveVersion;
            if (this._user != null) {
                throw new AssertionError();
            }
            this._user = user;
        }
    }

    private void setValue(String val) {
        int startPos;
        Xobj charOwner;
        if (!CharUtil.isValid(val, 0, val.length())) {
            throw new AssertionError();
        }
        if (val.length() <= 0) {
            return;
        }
        this._locale.notifyChange();
        Xobj lastAttr = lastAttr();
        if (lastAttr == null) {
            startPos = 1;
            charOwner = this;
        } else {
            int startPos2 = lastAttr.posAfter();
            startPos = startPos2;
            charOwner = lastAttr;
        }
        charOwner.insertCharsHelper(startPos, val, 0, val.length(), true);
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public SchemaTypeLoader get_schematypeloader() {
        return this._locale._schemaTypeLoader;
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public XmlLocale get_locale() {
        return this._locale;
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public Object get_root_object() {
        return this._locale;
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public boolean is_attribute() {
        if (!isValid()) {
            throw new AssertionError();
        }
        return isAttr();
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public boolean validate_on_set() {
        if (!isValid()) {
            throw new AssertionError();
        }
        return this._locale._validateOnSet;
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public void invalidate_text() {
        this._locale.enter();
        try {
            if (!isValid()) {
                throw new AssertionError();
            }
            if (isOccupied()) {
                if (hasTextNoEnsureOccupancy() || hasChildren()) {
                    TypeStoreUser user = this._user;
                    this._user = null;
                    Cur c = tempCur();
                    c.moveNodeContents(null, false);
                    c.release();
                    if (this._user != null) {
                        throw new AssertionError();
                    }
                    this._user = user;
                }
                setBit(256);
            }
            if (!isValid()) {
                throw new AssertionError();
            }
            this._locale.exit();
        } catch (Throwable th) {
            this._locale.exit();
            throw th;
        }
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public String fetch_text(int wsr) {
        this._locale.enter();
        try {
            if (!isValid() || !isOccupied()) {
                throw new AssertionError();
            }
            return getValueAsString(wsr);
        } finally {
            this._locale.exit();
        }
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public XmlCursor new_cursor() {
        this._locale.enter();
        try {
            Cur c = tempCur();
            XmlCursor xc = new Cursor(c);
            c.release();
            return xc;
        } finally {
            this._locale.exit();
        }
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public SchemaField get_schema_field() {
        if (!isValid()) {
            throw new AssertionError();
        }
        if (isRoot()) {
            return null;
        }
        TypeStoreUser parentUser = ensureParent().getUser();
        if (isAttr()) {
            return parentUser.get_attribute_field(this._name);
        }
        if (!isElem()) {
            throw new AssertionError();
        }
        TypeStoreVisitor visitor = parentUser.new_visitor();
        if (visitor == null) {
            return null;
        }
        Xobj x = this._parent._firstChild;
        while (true) {
            if (x.isElem()) {
                visitor.visit(x._name);
                if (x == this) {
                    return visitor.get_schema_field();
                }
            }
            x = x._nextSibling;
        }
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public void validate(ValidatorListener eventSink) {
        this._locale.enter();
        try {
            Cur c = tempCur();
            new Validate(c, eventSink);
            c.release();
        } finally {
            this._locale.exit();
        }
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public TypeStoreUser change_type(SchemaType type) {
        this._locale.enter();
        try {
            Cur c = tempCur();
            c.setType(type, false);
            c.release();
            this._locale.exit();
            return getUser();
        } catch (Throwable th) {
            this._locale.exit();
            throw th;
        }
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public TypeStoreUser substitute(QName name, SchemaType type) {
        this._locale.enter();
        try {
            Cur c = tempCur();
            c.setSubstitution(name, type);
            c.release();
            this._locale.exit();
            return getUser();
        } catch (Throwable th) {
            this._locale.exit();
            throw th;
        }
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public QName get_xsi_type() {
        return getXsiTypeName();
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public void store_text(String text) {
        this._locale.enter();
        TypeStoreUser user = this._user;
        this._user = null;
        try {
            Cur c = tempCur();
            c.moveNodeContents(null, false);
            if (text != null && !text.isEmpty()) {
                c.next();
                c.insertString(text);
            }
            c.release();
            if (this._user != null) {
                throw new AssertionError();
            }
            this._user = user;
            this._locale.exit();
        } catch (Throwable th) {
            if (this._user != null) {
                throw new AssertionError();
            }
            this._user = user;
            this._locale.exit();
            throw th;
        }
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public int compute_flags() {
        if (isRoot()) {
            return 0;
        }
        TypeStoreUser parentUser = ensureParent().getUser();
        if (isAttr()) {
            return parentUser.get_attributeflags(this._name);
        }
        int f = parentUser.get_elementflags(this._name);
        if (f != -1) {
            return f;
        }
        TypeStoreVisitor visitor = parentUser.new_visitor();
        if (visitor == null) {
            return 0;
        }
        Xobj x = this._parent._firstChild;
        while (true) {
            if (x.isElem()) {
                visitor.visit(x._name);
                if (x == this) {
                    return visitor.get_elementflags();
                }
            }
            x = x._nextSibling;
        }
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public String compute_default_text() {
        if (isRoot()) {
            return null;
        }
        TypeStoreUser parentUser = ensureParent().getUser();
        if (isAttr()) {
            return parentUser.get_default_attribute_text(this._name);
        }
        String result = parentUser.get_default_element_text(this._name);
        if (result != null) {
            return result;
        }
        TypeStoreVisitor visitor = parentUser.new_visitor();
        if (visitor == null) {
            return null;
        }
        Xobj x = this._parent._firstChild;
        while (true) {
            if (x.isElem()) {
                visitor.visit(x._name);
                if (x == this) {
                    return visitor.get_default_text();
                }
            }
            x = x._nextSibling;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x002f, code lost:
    
        if (r2.equals("1") != false) goto L15;
     */
    @Override // org.apache.xmlbeans.impl.values.TypeStore
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean find_nil() {
        /*
            r4 = this;
            boolean r0 = r4.isAttr()
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            org.apache.xmlbeans.impl.store.Locale r0 = r4._locale
            r0.enter()
            javax.xml.namespace.QName r0 = org.apache.xmlbeans.impl.store.Locale._xsiNil     // Catch: java.lang.Throwable -> L38
            org.apache.xmlbeans.impl.store.Xobj r0 = r4.getAttr(r0)     // Catch: java.lang.Throwable -> L38
            if (r0 != 0) goto L1c
        L16:
            org.apache.xmlbeans.impl.store.Locale r2 = r4._locale
            r2.exit()
            return r1
        L1c:
            r2 = 3
            java.lang.String r2 = r0.getValueAsString(r2)     // Catch: java.lang.Throwable -> L38
            java.lang.String r3 = "true"
            boolean r3 = r2.equals(r3)     // Catch: java.lang.Throwable -> L38
            if (r3 != 0) goto L31
            java.lang.String r3 = "1"
            boolean r3 = r2.equals(r3)     // Catch: java.lang.Throwable -> L38
            if (r3 == 0) goto L32
        L31:
            r1 = 1
        L32:
            org.apache.xmlbeans.impl.store.Locale r3 = r4._locale
            r3.exit()
            return r1
        L38:
            r0 = move-exception
            org.apache.xmlbeans.impl.store.Locale r1 = r4._locale
            r1.exit()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.store.Xobj.find_nil():boolean");
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public void invalidate_nil() {
        if (isAttr()) {
            return;
        }
        this._locale.enter();
        try {
            if (!this._user.build_nil()) {
                removeAttr(Locale._xsiNil);
            } else {
                setAttr(Locale._xsiNil, "true");
            }
        } finally {
            this._locale.exit();
        }
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public int count_elements(QName name) {
        return this._locale.count(this, name, null);
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public int count_elements(QNameSet names) {
        return this._locale.count(this, null, names);
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public TypeStoreUser find_element_user(QName name, int i) {
        for (Xobj x = this._firstChild; x != null; x = x._nextSibling) {
            if (x.isElem() && x._name.equals(name) && i - 1 < 0) {
                return x.getUser();
            }
        }
        return null;
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public TypeStoreUser find_element_user(QNameSet names, int i) {
        for (Xobj x = this._firstChild; x != null; x = x._nextSibling) {
            if (x.isElem() && names.contains(x._name) && i - 1 < 0) {
                return x.getUser();
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public <T extends XmlObject> void find_all_element_users(QName name, List<T> list) {
        for (Xobj x = this._firstChild; x != null; x = x._nextSibling) {
            if (x.isElem() && x._name.equals(name)) {
                list.add((XmlObject) x.getUser());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public <T extends XmlObject> void find_all_element_users(QNameSet names, List<T> list) {
        for (Xobj x = this._firstChild; x != null; x = x._nextSibling) {
            if (x.isElem() && names.contains(x._name)) {
                list.add((XmlObject) x.getUser());
            }
        }
    }

    private static TypeStoreUser insertElement(QName name, Xobj x, int pos) {
        x._locale.enter();
        try {
            Cur c = x._locale.tempCur();
            c.moveTo(x, pos);
            c.createElement(name);
            TypeStoreUser user = c.getUser();
            c.release();
            return user;
        } finally {
            x._locale.exit();
        }
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public TypeStoreUser insert_element_user(QName name, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (!isContainer()) {
            throw new IllegalStateException();
        }
        Xobj x = this._locale.findNthChildElem(this, name, null, i);
        if (x == null) {
            if (i > this._locale.count(this, name, null) + 1) {
                throw new IndexOutOfBoundsException();
            }
            return add_element_user(name);
        }
        return insertElement(name, x, 0);
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public TypeStoreUser insert_element_user(QNameSet names, QName name, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (!isContainer()) {
            throw new IllegalStateException();
        }
        Xobj x = this._locale.findNthChildElem(this, null, names, i);
        if (x == null) {
            if (i > this._locale.count(this, null, names) + 1) {
                throw new IndexOutOfBoundsException();
            }
            return add_element_user(name);
        }
        return insertElement(name, x, 0);
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public TypeStoreUser add_element_user(QName name) {
        if (!isContainer()) {
            throw new IllegalStateException();
        }
        QNameSet endSet = null;
        boolean gotEndSet = false;
        Xobj candidate = null;
        for (Xobj x = this._lastChild; x != null; x = x._prevSibling) {
            if (x.isContainer()) {
                if (x._name.equals(name)) {
                    break;
                }
                if (!gotEndSet) {
                    endSet = this._user.get_element_ending_delimiters(name);
                    gotEndSet = true;
                }
                if (endSet == null || endSet.contains(x._name)) {
                    candidate = x;
                }
            }
        }
        if (candidate == null) {
            return insertElement(name, this, -1);
        }
        return insertElement(name, candidate, 0);
    }

    private static void removeElement(Xobj x) {
        if (x == null) {
            throw new IndexOutOfBoundsException();
        }
        x._locale.enter();
        try {
            Cur c = x.tempCur();
            c.moveNode(null);
            c.release();
        } finally {
            x._locale.exit();
        }
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public void remove_element(QName name, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (!isContainer()) {
            throw new IllegalStateException();
        }
        Xobj x = this._firstChild;
        while (x != null && (!x.isElem() || !x._name.equals(name) || i - 1 >= 0)) {
            x = x._nextSibling;
        }
        removeElement(x);
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public void remove_element(QNameSet names, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (!isContainer()) {
            throw new IllegalStateException();
        }
        Xobj x = this._firstChild;
        while (x != null && (!x.isElem() || !names.contains(x._name) || i - 1 >= 0)) {
            x = x._nextSibling;
        }
        removeElement(x);
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public TypeStoreUser find_attribute_user(QName name) {
        Xobj a = getAttr(name);
        if (a == null) {
            return null;
        }
        return a.getUser();
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public TypeStoreUser add_attribute_user(QName name) {
        if (getAttr(name) != null) {
            throw new IndexOutOfBoundsException();
        }
        this._locale.enter();
        try {
            return setAttr(name, "").getUser();
        } finally {
            this._locale.exit();
        }
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public void remove_attribute(QName name) {
        this._locale.enter();
        try {
            if (!removeAttr(name)) {
                throw new IndexOutOfBoundsException();
            }
        } finally {
            this._locale.exit();
        }
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public TypeStoreUser copy_contents_from(TypeStore source) {
        Xobj xSrc = (Xobj) source;
        if (xSrc == this) {
            return getUser();
        }
        this._locale.enter();
        try {
            xSrc._locale.enter();
            Cur c = tempCur();
            try {
                Cur cSrc1 = xSrc.tempCur();
                Map<String, String> sourceNamespaces = Locale.getAllNamespaces(cSrc1, null);
                cSrc1.release();
                if (isAttr()) {
                    Cur cSrc = xSrc.tempCur();
                    String value = Locale.getTextValue(cSrc);
                    cSrc.release();
                    c.setValue(value);
                } else {
                    disconnectChildrenUsers();
                    if (inhibitDisconnect()) {
                        throw new AssertionError();
                    }
                    setBit(1024);
                    QName xsiType = isContainer() ? getXsiTypeName() : null;
                    Xobj copy = xSrc.copyNode(this._locale);
                    Cur.moveNodeContents(this, null, true);
                    c.next();
                    Cur.moveNodeContents(copy, c, true);
                    c.moveTo(this);
                    if (xsiType != null) {
                        c.setXsiType(xsiType);
                    }
                    if (!inhibitDisconnect()) {
                        throw new AssertionError();
                    }
                    clearBit(1024);
                }
                if (sourceNamespaces != null) {
                    if (!c.isContainer()) {
                        c.toParent();
                    }
                    Locale.applyNamespaces(c, sourceNamespaces);
                }
                this._locale.exit();
                return getUser();
            } finally {
                c.release();
                xSrc._locale.exit();
            }
        } catch (Throwable th) {
            this._locale.exit();
            throw th;
        }
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public TypeStoreUser copy(SchemaTypeLoader stl, SchemaType type, XmlOptions options) {
        XmlOptions options2 = XmlOptions.maskNull(options);
        SchemaType sType = options2.getDocumentType();
        if (sType == null) {
            sType = type == null ? XmlObject.type : type;
        }
        Locale locale = locale();
        if (options2.isCopyUseNewSynchronizationDomain()) {
            locale = Locale.getLocale(stl, options2);
        }
        boolean isFragment = (sType.isDocumentType() || (sType.isNoType() && (this instanceof DocumentXobj))) ? false : true;
        Xobj destination = Cur.createDomDocumentRootXobj(locale, isFragment);
        locale.enter();
        try {
            Cur c = destination.tempCur();
            c.setType(type);
            c.release();
            locale.exit();
            return destination.copy_contents_from(this);
        } catch (Throwable th) {
            locale.exit();
            throw th;
        }
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public void array_setter(XmlObject[] sources, QName elementName) {
        this._locale.enter();
        try {
            int m = sources.length;
            List<Xobj> copies = new ArrayList<>();
            List<SchemaType> types = new ArrayList<>();
            for (XmlObject source : sources) {
                if (source == null) {
                    throw new IllegalArgumentException("Array element null");
                }
                if (source.isImmutable()) {
                    copies.add(null);
                    types.add(null);
                } else {
                    Xobj x = (Xobj) ((TypeStoreUser) source).get_store();
                    if (x._locale == this._locale) {
                        copies.add(x.copyNode(this._locale));
                    } else {
                        x._locale.enter();
                        try {
                            copies.add(x.copyNode(this._locale));
                        } finally {
                            x._locale.exit();
                        }
                    }
                    types.add(source.schemaType());
                }
            }
            int n = count_elements(elementName);
            while (n > m) {
                remove_element(elementName, m);
                n--;
            }
            while (m > n) {
                add_element_user(elementName);
                n++;
            }
            if (m != n) {
                throw new AssertionError();
            }
            ArrayList arrayList = new ArrayList();
            find_all_element_users(elementName, arrayList);
            List<Xobj> elements = (List) arrayList.stream().map(new Function() { // from class: org.apache.xmlbeans.impl.store.Xobj$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Xobj.lambda$array_setter$0((XmlObject) obj);
                }
            }).map(new Function() { // from class: org.apache.xmlbeans.impl.store.Xobj$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((TypeStoreUser) obj).get_store();
                }
            }).map(new Function() { // from class: org.apache.xmlbeans.impl.store.Xobj$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Xobj.lambda$array_setter$1((TypeStore) obj);
                }
            }).collect(Collectors.toList());
            if (elements.size() != n) {
                throw new AssertionError();
            }
            Cur c = tempCur();
            for (int i = 0; i < n; i++) {
                Xobj x2 = elements.get(i);
                if (sources[i].isImmutable()) {
                    x2.getObject().set(sources[i]);
                } else {
                    Cur.moveNodeContents(x2, null, true);
                    c.moveTo(x2);
                    c.next();
                    Cur.moveNodeContents(copies.get(i), c, true);
                    x2.change_type(types.get(i));
                }
            }
            c.release();
        } finally {
            this._locale.exit();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ TypeStoreUser lambda$array_setter$0(XmlObject x) {
        return (TypeStoreUser) x;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Xobj lambda$array_setter$1(TypeStore x) {
        return (Xobj) x;
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public void visit_elements(TypeStoreVisitor visitor) {
        throw new RuntimeException("Not implemeneted");
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStore
    public XmlObject[] exec_query(String queryExpr, XmlOptions options) {
        this._locale.enter();
        try {
            Cur c = tempCur();
            XmlObject[] result = XPathFactory.objectExecQuery(c, queryExpr, options);
            c.release();
            return result;
        } finally {
            this._locale.exit();
        }
    }

    @Override // org.apache.xmlbeans.impl.values.NamespaceManager
    public String find_prefix_for_nsuri(String nsuri, String suggested_prefix) {
        this._locale.enter();
        try {
            return prefixForNamespace(nsuri, suggested_prefix, true);
        } finally {
            this._locale.exit();
        }
    }

    @Override // org.apache.xmlbeans.impl.common.PrefixResolver
    public String getNamespaceForPrefix(String prefix) {
        return namespaceForPrefix(prefix, true);
    }
}
