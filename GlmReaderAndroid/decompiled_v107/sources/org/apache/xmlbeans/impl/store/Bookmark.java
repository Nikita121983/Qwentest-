package org.apache.xmlbeans.impl.store;

import org.apache.xmlbeans.XmlCursor;

/* loaded from: classes11.dex */
class Bookmark implements XmlCursor.XmlMark {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    Object _key;
    Bookmark _next;
    int _pos;
    Bookmark _prev;
    Object _value;
    Xobj _xobj;

    boolean isOnList(Bookmark head) {
        while (head != null) {
            if (head != this) {
                head = head._next;
            } else {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bookmark listInsert(Bookmark head) {
        if (this._next != null || this._prev != null) {
            throw new AssertionError();
        }
        if (head == null) {
            this._prev = this;
            return this;
        }
        this._prev = head._prev;
        head._prev._next = this;
        head._prev = this;
        return head;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bookmark listRemove(Bookmark head) {
        if (this._prev == null || !isOnList(head)) {
            throw new AssertionError();
        }
        if (this._prev == this) {
            head = null;
        } else {
            if (head == this) {
                head = this._next;
            } else {
                this._prev._next = this._next;
            }
            if (this._next == null) {
                if (head != null) {
                    head._prev = this._prev;
                }
            } else {
                this._next._prev = this._prev;
                this._next = null;
            }
        }
        this._prev = null;
        if (this._next != null) {
            throw new AssertionError();
        }
        return head;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void moveTo(Xobj x, int p) {
        if (!isOnList(this._xobj._bookmarks)) {
            throw new AssertionError();
        }
        if (this._xobj != x) {
            this._xobj._bookmarks = listRemove(this._xobj._bookmarks);
            x._bookmarks = listInsert(x._bookmarks);
            this._xobj = x;
        }
        this._pos = p;
    }

    @Override // org.apache.xmlbeans.XmlCursor.XmlMark
    public XmlCursor createCursor() {
        if (this._xobj == null) {
            throw new IllegalStateException("Attempting to create a cursor on a bookmark that has been cleared or replaced.");
        }
        return Cursor.newCursor(this._xobj, this._pos);
    }
}
