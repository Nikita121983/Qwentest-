package org.apache.xmlbeans;

import javax.xml.namespace.QName;

/* loaded from: classes.dex */
public final class QNameCache {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final float DEFAULT_LOAD = 0.7f;
    private int hashmask;
    private final float loadFactor;
    private int numEntries;
    private QName[] table;
    private int threshold;

    public QNameCache(int initialCapacity, float loadFactor) {
        this.numEntries = 0;
        if (initialCapacity <= 0) {
            throw new AssertionError();
        }
        if (loadFactor <= 0.0f || loadFactor >= 1.0f) {
            throw new AssertionError();
        }
        int capacity = 16;
        while (capacity < initialCapacity) {
            capacity <<= 1;
        }
        this.loadFactor = loadFactor;
        this.hashmask = capacity - 1;
        this.threshold = (int) (capacity * loadFactor);
        this.table = new QName[capacity];
    }

    public QNameCache(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD);
    }

    public QName getName(String uri, String localName) {
        return getName(uri, localName, "");
    }

    public QName getName(String uri, String localName, String prefix) {
        if (localName == null) {
            throw new AssertionError();
        }
        if (uri == null) {
            uri = "";
        }
        String uri2 = uri;
        if (prefix == null) {
            prefix = "";
        }
        String prefix2 = prefix;
        int index = hash(uri2, localName, prefix2) & this.hashmask;
        while (true) {
            QName q = this.table[index];
            if (q == null) {
                this.numEntries++;
                if (this.numEntries >= this.threshold) {
                    rehash();
                }
                QName[] qNameArr = this.table;
                QName qName = new QName(uri2, localName, prefix2);
                qNameArr[index] = qName;
                return qName;
            }
            if (equals(q, uri2, localName, prefix2)) {
                return q;
            }
            index = (index - 1) & this.hashmask;
        }
    }

    private void rehash() {
        int newLength = this.table.length * 2;
        QName[] newTable = new QName[newLength];
        int newHashmask = newLength - 1;
        for (int i = 0; i < this.table.length; i++) {
            QName q = this.table[i];
            if (q != null) {
                int newIndex = hash(q.getNamespaceURI(), q.getLocalPart(), q.getPrefix()) & newHashmask;
                while (newTable[newIndex] != null) {
                    newIndex = (newIndex - 1) & newHashmask;
                }
                newTable[newIndex] = q;
            }
        }
        this.table = newTable;
        this.hashmask = newHashmask;
        this.threshold = (int) (newLength * this.loadFactor);
    }

    private static int hash(String uri, String localName, String prefix) {
        int h = 0 + (prefix.hashCode() << 10);
        return h + (uri.hashCode() << 5) + localName.hashCode();
    }

    private static boolean equals(QName q, String uri, String localName, String prefix) {
        return q.getLocalPart().equals(localName) && q.getNamespaceURI().equals(uri) && q.getPrefix().equals(prefix);
    }
}
