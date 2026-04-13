package org.apache.xmlbeans.impl.common;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import org.apache.xmlbeans.XmlError;

/* loaded from: classes11.dex */
public class XmlErrorWatcher extends AbstractCollection<XmlError> {
    private XmlError _firstError;
    private final Collection<XmlError> _underlying;

    public XmlErrorWatcher(Collection<XmlError> underlying) {
        this._underlying = underlying;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean add(XmlError o) {
        if (this._firstError == null && o != null && o.getSeverity() == 0) {
            this._firstError = o;
        }
        if (this._underlying == null) {
            return false;
        }
        return this._underlying.add(o);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<XmlError> iterator() {
        if (this._underlying == null) {
            return Collections.emptyIterator();
        }
        return this._underlying.iterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        if (this._underlying == null) {
            return 0;
        }
        return this._underlying.size();
    }

    public boolean hasError() {
        return this._firstError != null;
    }

    public XmlError firstError() {
        return this._firstError;
    }
}
