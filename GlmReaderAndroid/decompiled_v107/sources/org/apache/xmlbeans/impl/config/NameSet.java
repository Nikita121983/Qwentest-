package org.apache.xmlbeans.impl.config;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes11.dex */
public class NameSet {
    public static final NameSet EMPTY = new NameSet(true, Collections.EMPTY_SET);
    public static final NameSet EVERYTHING = new NameSet(false, Collections.EMPTY_SET);
    private Set<String> _finiteSet;
    private boolean _isFinite;

    private NameSet(boolean isFinite, Set<String> finiteSet) {
        this._isFinite = isFinite;
        this._finiteSet = finiteSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static NameSet newInstance(boolean isFinite, Set<String> finiteSet) {
        if (finiteSet.isEmpty()) {
            if (isFinite) {
                return EMPTY;
            }
            return EVERYTHING;
        }
        Set<String> fs = new HashSet<>();
        fs.addAll(finiteSet);
        return new NameSet(isFinite, fs);
    }

    private static Set<String> intersectFiniteSets(Set<String> a, Set<String> b) {
        Set<String> intersection = new HashSet<>();
        while (a.iterator().hasNext()) {
            String name = a.iterator().next();
            if (b.contains(name)) {
                intersection.add(name);
            }
        }
        return intersection;
    }

    public NameSet union(NameSet with) {
        if (this._isFinite) {
            if (with._isFinite) {
                Set<String> union = new HashSet<>();
                union.addAll(this._finiteSet);
                union.addAll(with._finiteSet);
                return newInstance(true, union);
            }
            Set<String> subst = new HashSet<>();
            subst.addAll(with._finiteSet);
            subst.removeAll(this._finiteSet);
            return newInstance(false, subst);
        }
        if (with._isFinite) {
            Set<String> subst2 = new HashSet<>();
            subst2.addAll(this._finiteSet);
            subst2.removeAll(with._finiteSet);
            return newInstance(false, subst2);
        }
        return newInstance(false, intersectFiniteSets(this._finiteSet, with._finiteSet));
    }

    public NameSet intersect(NameSet with) {
        if (this._isFinite) {
            if (with._isFinite) {
                return newInstance(true, intersectFiniteSets(this._finiteSet, with._finiteSet));
            }
            Set<String> subst = new HashSet<>();
            subst.addAll(this._finiteSet);
            subst.removeAll(with._finiteSet);
            return newInstance(false, subst);
        }
        if (with._isFinite) {
            Set<String> subst2 = new HashSet<>();
            subst2.addAll(with._finiteSet);
            subst2.removeAll(this._finiteSet);
            return newInstance(true, subst2);
        }
        Set<String> union = new HashSet<>();
        union.addAll(this._finiteSet);
        union.addAll(with._finiteSet);
        return newInstance(false, union);
    }

    public NameSet substractFrom(NameSet from) {
        return from.substract(this);
    }

    public NameSet substract(NameSet what) {
        if (this._isFinite) {
            if (what._isFinite) {
                Set<String> subst = new HashSet<>();
                subst.addAll(this._finiteSet);
                subst.removeAll(what._finiteSet);
                return newInstance(true, subst);
            }
            return newInstance(true, intersectFiniteSets(this._finiteSet, what._finiteSet));
        }
        if (what._isFinite) {
            Set<String> union = new HashSet<>();
            union.addAll(this._finiteSet);
            union.addAll(what._finiteSet);
            return newInstance(false, union);
        }
        Set<String> subst2 = new HashSet<>();
        subst2.addAll(what._finiteSet);
        subst2.removeAll(this._finiteSet);
        return newInstance(true, subst2);
    }

    public NameSet invert() {
        return newInstance(!this._isFinite, this._finiteSet);
    }

    public boolean contains(String name) {
        if (this._isFinite) {
            return this._finiteSet.contains(name);
        }
        return !this._finiteSet.contains(name);
    }
}
