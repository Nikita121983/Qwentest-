package org.apache.poi.ss.formula;

import org.apache.logging.log4j.util.Chars;

/* loaded from: classes10.dex */
public class NameIdentifier {
    private final boolean _isQuoted;
    private final String _name;

    public NameIdentifier(String name, boolean isQuoted) {
        this._name = name;
        this._isQuoted = isQuoted;
    }

    public String getName() {
        return this._name;
    }

    public boolean isQuoted() {
        return this._isQuoted;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(getClass().getName());
        sb.append(" [");
        if (this._isQuoted) {
            sb.append(Chars.QUOTE).append(this._name).append("'");
        } else {
            sb.append(this._name);
        }
        sb.append(']');
        return sb.toString();
    }
}
