package org.apache.commons.lang3.builder;

/* loaded from: classes9.dex */
final class IDKey {
    private final int id;
    private final Object value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IDKey(Object value) {
        this.id = System.identityHashCode(value);
        this.value = value;
    }

    public boolean equals(Object other) {
        if (!(other instanceof IDKey)) {
            return false;
        }
        IDKey idKey = (IDKey) other;
        return this.id == idKey.id && this.value == idKey.value;
    }

    public int hashCode() {
        return this.id;
    }
}
