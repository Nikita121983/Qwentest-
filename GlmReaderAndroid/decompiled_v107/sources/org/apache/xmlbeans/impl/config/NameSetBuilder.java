package org.apache.xmlbeans.impl.config;

import java.util.HashSet;
import java.util.Set;

/* loaded from: classes11.dex */
public class NameSetBuilder {
    private boolean _isFinite = true;
    private final Set<String> _finiteSet = new HashSet();

    public void invert() {
        this._isFinite = !this._isFinite;
    }

    public void add(String name) {
        if (this._isFinite) {
            this._finiteSet.add(name);
        } else {
            this._finiteSet.remove(name);
        }
    }

    public NameSet toNameSet() {
        if (this._finiteSet.isEmpty()) {
            if (this._isFinite) {
                return NameSet.EMPTY;
            }
            return NameSet.EVERYTHING;
        }
        return NameSet.newInstance(this._isFinite, this._finiteSet);
    }
}
