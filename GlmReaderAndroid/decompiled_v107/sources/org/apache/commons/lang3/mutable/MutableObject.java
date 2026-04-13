package org.apache.commons.lang3.mutable;

import java.io.Serializable;
import java.util.Objects;

/* loaded from: classes9.dex */
public class MutableObject<T> implements Mutable<T>, Serializable {
    private static final long serialVersionUID = 86241875189L;
    private T value;

    public MutableObject() {
    }

    public MutableObject(T value) {
        this.value = value;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MutableObject<?> that = (MutableObject) obj;
        return Objects.equals(this.value, that.value);
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    @Deprecated
    /* renamed from: getValue */
    public T getValue2() {
        return this.value;
    }

    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    public void setValue(T value) {
        this.value = value;
    }

    public String toString() {
        return Objects.toString(this.value);
    }
}
