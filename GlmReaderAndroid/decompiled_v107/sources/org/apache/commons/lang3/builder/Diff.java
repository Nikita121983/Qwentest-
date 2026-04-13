package org.apache.commons.lang3.builder;

import java.lang.reflect.Type;
import java.util.Objects;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.apache.commons.lang3.tuple.Pair;

/* loaded from: classes9.dex */
public abstract class Diff<T> extends Pair<T, T> {
    private static final long serialVersionUID = 1;
    private final String fieldName;
    private final Type type;

    protected Diff(String fieldName) {
        this.fieldName = (String) Objects.requireNonNull(fieldName);
        this.type = (Type) ObjectUtils.getIfNull((Class) TypeUtils.getTypeArguments(getClass(), Diff.class).get(Diff.class.getTypeParameters()[0]), Object.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Diff(String fieldName, Type type) {
        this.fieldName = (String) Objects.requireNonNull(fieldName);
        this.type = (Type) Objects.requireNonNull(type);
    }

    public final String getFieldName() {
        return this.fieldName;
    }

    @Deprecated
    public final Type getType() {
        return this.type;
    }

    @Override // java.util.Map.Entry
    public final T setValue(T value) {
        throw new UnsupportedOperationException("Cannot alter Diff object.");
    }

    @Override // org.apache.commons.lang3.tuple.Pair
    public final String toString() {
        return String.format("[%s: %s, %s]", this.fieldName, getLeft(), getRight());
    }
}
