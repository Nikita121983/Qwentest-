package org.apache.xmlbeans.impl.soap;

import java.util.Iterator;
import java.util.Vector;

/* loaded from: classes11.dex */
public class MimeHeaders {
    protected Vector<MimeHeader> headers = new Vector<>();

    /* loaded from: classes11.dex */
    class MatchingIterator implements Iterator<MimeHeader> {
        private final Iterator<MimeHeader> iterator;
        private final boolean match;
        private final String[] names;
        private MimeHeader nextHeader;

        private MimeHeader nextMatch() {
            while (this.iterator.hasNext()) {
                MimeHeader mimeheader = this.iterator.next();
                if (this.names == null) {
                    if (this.match) {
                        return null;
                    }
                    return mimeheader;
                }
                String[] strArr = this.names;
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        String name = strArr[i];
                        if (!mimeheader.getName().equalsIgnoreCase(name)) {
                            i++;
                        } else if (this.match) {
                            return mimeheader;
                        }
                    } else if (!this.match) {
                        return mimeheader;
                    }
                }
            }
            return null;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.nextHeader == null) {
                this.nextHeader = nextMatch();
            }
            return this.nextHeader != null;
        }

        @Override // java.util.Iterator
        public MimeHeader next() {
            if (this.nextHeader != null) {
                MimeHeader obj = this.nextHeader;
                this.nextHeader = null;
                return obj;
            }
            if (hasNext()) {
                return this.nextHeader;
            }
            return null;
        }

        @Override // java.util.Iterator
        public void remove() {
            this.iterator.remove();
        }

        MatchingIterator(String[] as, boolean flag) {
            this.match = flag;
            this.names = as;
            this.iterator = MimeHeaders.this.headers.iterator();
        }
    }

    public String[] getHeader(String name) {
        Vector vector = new Vector();
        for (int i = 0; i < this.headers.size(); i++) {
            MimeHeader mimeheader = this.headers.elementAt(i);
            if (mimeheader.getName().equalsIgnoreCase(name) && mimeheader.getValue() != null) {
                vector.addElement(mimeheader.getValue());
            }
        }
        if (vector.isEmpty()) {
            return null;
        }
        String[] as = new String[vector.size()];
        vector.copyInto(as);
        return as;
    }

    public void setHeader(String name, String value) {
        boolean flag = false;
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Illegal MimeHeader name");
        }
        int i = 0;
        while (i < this.headers.size()) {
            MimeHeader mimeheader = this.headers.elementAt(i);
            if (mimeheader.getName().equalsIgnoreCase(name)) {
                if (!flag) {
                    this.headers.setElementAt(new MimeHeader(mimeheader.getName(), value), i);
                    flag = true;
                } else {
                    this.headers.removeElementAt(i);
                    i--;
                }
            }
            i++;
        }
        if (!flag) {
            addHeader(name, value);
        }
    }

    public void addHeader(String name, String value) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Illegal MimeHeader name");
        }
        int i = this.headers.size();
        for (int j = i - 1; j >= 0; j--) {
            MimeHeader mimeheader = this.headers.elementAt(j);
            if (mimeheader.getName().equalsIgnoreCase(name)) {
                this.headers.insertElementAt(new MimeHeader(name, value), j + 1);
                return;
            }
        }
        this.headers.addElement(new MimeHeader(name, value));
    }

    public void removeHeader(String name) {
        int i = 0;
        while (i < this.headers.size()) {
            MimeHeader mimeheader = this.headers.elementAt(i);
            if (mimeheader.getName().equalsIgnoreCase(name)) {
                this.headers.removeElementAt(i);
                i--;
            }
            i++;
        }
    }

    public void removeAllHeaders() {
        this.headers.removeAllElements();
    }

    public Iterator<MimeHeader> getAllHeaders() {
        return this.headers.iterator();
    }

    public Iterator<MimeHeader> getMatchingHeaders(String[] names) {
        return new MatchingIterator(names, true);
    }

    public Iterator<MimeHeader> getNonMatchingHeaders(String[] names) {
        return new MatchingIterator(names, false);
    }
}
