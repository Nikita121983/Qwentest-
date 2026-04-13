package org.apache.commons.lang3.builder;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import org.apache.commons.lang3.ArraySorter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

/* loaded from: classes9.dex */
public class ReflectionDiffBuilder<T> implements org.apache.commons.lang3.builder.Builder<DiffResult<T>> {
    private final DiffBuilder<T> diffBuilder;
    private String[] excludeFieldNames;

    /* loaded from: classes9.dex */
    public static final class Builder<T> {
        private DiffBuilder<T> diffBuilder;
        private String[] excludeFieldNames = ArrayUtils.EMPTY_STRING_ARRAY;

        public ReflectionDiffBuilder<T> build() {
            return new ReflectionDiffBuilder<>(this.diffBuilder, this.excludeFieldNames);
        }

        public Builder<T> setDiffBuilder(DiffBuilder<T> diffBuilder) {
            this.diffBuilder = diffBuilder;
            return this;
        }

        public Builder<T> setExcludeFieldNames(String... excludeFieldNames) {
            this.excludeFieldNames = ReflectionDiffBuilder.toExcludeFieldNames(excludeFieldNames);
            return this;
        }
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String[] toExcludeFieldNames(String[] excludeFieldNames) {
        if (excludeFieldNames == null) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        return (String[]) ArraySorter.sort(ReflectionToStringBuilder.toNoNullStringArray(excludeFieldNames));
    }

    private ReflectionDiffBuilder(DiffBuilder<T> diffBuilder, String[] excludeFieldNames) {
        this.diffBuilder = diffBuilder;
        this.excludeFieldNames = excludeFieldNames;
    }

    @Deprecated
    public ReflectionDiffBuilder(T left, T right, ToStringStyle style) {
        this(DiffBuilder.builder().setLeft(left).setRight(right).setStyle(style).build(), null);
    }

    private boolean accept(Field field) {
        if (field.getName().indexOf(36) != -1 || Modifier.isTransient(field.getModifiers()) || Modifier.isStatic(field.getModifiers())) {
            return false;
        }
        if (this.excludeFieldNames == null || Arrays.binarySearch(this.excludeFieldNames, field.getName()) < 0) {
            return !field.isAnnotationPresent(DiffExclude.class);
        }
        return false;
    }

    private void appendFields(Class<?> clazz) {
        for (Field field : FieldUtils.getAllFields(clazz)) {
            if (accept(field)) {
                try {
                    this.diffBuilder.append(field.getName(), readField(field, getLeft()), readField(field, getRight()));
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException("Unexpected IllegalAccessException: " + e.getMessage(), e);
                }
            }
        }
    }

    @Override // org.apache.commons.lang3.builder.Builder
    public DiffResult<T> build() {
        if (getLeft().equals(getRight())) {
            return this.diffBuilder.build();
        }
        appendFields(getLeft().getClass());
        return this.diffBuilder.build();
    }

    public String[] getExcludeFieldNames() {
        return (String[]) this.excludeFieldNames.clone();
    }

    private T getLeft() {
        return this.diffBuilder.getLeft();
    }

    private T getRight() {
        return this.diffBuilder.getRight();
    }

    private Object readField(Field field, Object target) throws IllegalAccessException {
        return FieldUtils.readField(field, target, true);
    }

    @Deprecated
    public ReflectionDiffBuilder<T> setExcludeFieldNames(String... excludeFieldNames) {
        this.excludeFieldNames = toExcludeFieldNames(excludeFieldNames);
        return this;
    }
}
