package org.apache.xmlbeans;

import org.apache.xmlbeans.XmlCursor;

/* loaded from: classes.dex */
public class SchemaBookmark extends XmlCursor.XmlBookmark {
    private Object _value;

    public SchemaBookmark(Object value) {
        this._value = value;
    }

    public Object getValue() {
        return this._value;
    }
}
