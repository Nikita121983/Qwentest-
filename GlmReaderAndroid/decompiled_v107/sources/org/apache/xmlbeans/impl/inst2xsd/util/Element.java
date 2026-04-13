package org.apache.xmlbeans.impl.inst2xsd.util;

import javax.xml.namespace.QName;

/* loaded from: classes11.dex */
public class Element {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int UNBOUNDED = -1;
    private QName _name = null;
    private Element _ref = null;
    private boolean _isGlobal = false;
    private int _minOccurs = 1;
    private int _maxOccurs = 1;
    private boolean _isNillable = false;
    private Type _type = null;
    private String _comment = null;

    public QName getName() {
        return this._name;
    }

    public void setName(QName name) {
        this._name = name;
    }

    public boolean isRef() {
        return this._ref != null;
    }

    public Element getRef() {
        return this._ref;
    }

    public void setRef(Element ref) {
        if (this._isGlobal) {
            throw new AssertionError();
        }
        this._ref = ref;
        this._type = null;
    }

    public boolean isGlobal() {
        return this._isGlobal;
    }

    public void setGlobal(boolean isGlobal) {
        this._isGlobal = isGlobal;
        this._minOccurs = 1;
        this._maxOccurs = 1;
    }

    public int getMinOccurs() {
        return this._minOccurs;
    }

    public void setMinOccurs(int minOccurs) {
        this._minOccurs = minOccurs;
    }

    public int getMaxOccurs() {
        return this._maxOccurs;
    }

    public void setMaxOccurs(int maxOccurs) {
        this._maxOccurs = maxOccurs;
    }

    public boolean isNillable() {
        return this._isNillable;
    }

    public void setNillable(boolean isNillable) {
        this._isNillable = isNillable;
    }

    public Type getType() {
        return isRef() ? getRef().getType() : this._type;
    }

    public void setType(Type type) {
        if (isRef()) {
            throw new AssertionError();
        }
        this._type = type;
    }

    public String getComment() {
        return this._comment;
    }

    public void setComment(String comment) {
        this._comment = comment;
    }

    public String toString() {
        String qName;
        StringBuilder append = new StringBuilder().append("\n  Element{ _name = ").append(this._name).append(", _ref = ").append(this._ref != null).append(", _isGlobal = ").append(this._isGlobal).append(", _minOccurs = ").append(this._minOccurs).append(", _maxOccurs = ").append(this._maxOccurs).append(", _isNillable = ").append(this._isNillable).append(", _comment = ").append(this._comment).append(",\n    _type = ");
        if (this._type == null) {
            qName = "null";
        } else {
            qName = this._type.isGlobal() ? this._type.getName().toString() : this._type.toString();
        }
        return append.append(qName).append("\n  }").toString();
    }
}
