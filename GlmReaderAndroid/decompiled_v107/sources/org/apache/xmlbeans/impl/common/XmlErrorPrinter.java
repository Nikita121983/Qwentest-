package org.apache.xmlbeans.impl.common;

import java.net.URI;
import java.util.AbstractCollection;
import java.util.Collections;
import java.util.Iterator;
import org.apache.xmlbeans.XmlError;

/* loaded from: classes11.dex */
public class XmlErrorPrinter extends AbstractCollection<XmlError> {
    private final URI _baseURI;
    private final boolean _noisy;

    public XmlErrorPrinter(boolean noisy, URI baseURI) {
        this._noisy = noisy;
        this._baseURI = baseURI;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean add(XmlError err) {
        if (err != null) {
            if (err.getSeverity() == 0 || err.getSeverity() == 1) {
                System.err.println(err.toString(this._baseURI));
                return false;
            }
            if (this._noisy) {
                System.out.println(err.toString(this._baseURI));
                return false;
            }
            return false;
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<XmlError> iterator() {
        return Collections.emptyIterator();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return 0;
    }
}
