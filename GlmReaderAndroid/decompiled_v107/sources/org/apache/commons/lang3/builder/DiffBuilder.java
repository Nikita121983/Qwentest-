package org.apache.commons.lang3.builder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.apache.commons.lang3.ObjectUtils;

/* loaded from: classes9.dex */
public class DiffBuilder<T> implements org.apache.commons.lang3.builder.Builder<DiffResult<T>> {
    static final String TO_STRING_FORMAT = "%s differs from %s";
    private final List<Diff<?>> diffs;
    private final boolean equals;
    private final T left;
    private final T right;
    private final ToStringStyle style;
    private final String toStringFormat;

    /* loaded from: classes9.dex */
    public interface SerializableSupplier<T> extends Supplier<T>, Serializable {
    }

    /* synthetic */ DiffBuilder(Object x0, Object x1, ToStringStyle x2, boolean x3, String x4, AnonymousClass1 x5) {
        this(x0, x1, x2, x3, x4);
    }

    /* loaded from: classes9.dex */
    public static final class Builder<T> {
        private T left;
        private T right;
        private ToStringStyle style;
        private boolean testObjectsEquals = true;
        private String toStringFormat = DiffBuilder.TO_STRING_FORMAT;

        public DiffBuilder<T> build() {
            return new DiffBuilder<>(this.left, this.right, this.style, this.testObjectsEquals, this.toStringFormat);
        }

        public Builder<T> setLeft(T left) {
            this.left = left;
            return this;
        }

        public Builder<T> setRight(T right) {
            this.right = right;
            return this;
        }

        public Builder<T> setStyle(ToStringStyle style) {
            this.style = style != null ? style : ToStringStyle.DEFAULT_STYLE;
            return this;
        }

        public Builder<T> setTestObjectsEquals(boolean testObjectsEquals) {
            this.testObjectsEquals = testObjectsEquals;
            return this;
        }

        public Builder<T> setToStringFormat(String toStringFormat) {
            this.toStringFormat = toStringFormat != null ? toStringFormat : DiffBuilder.TO_STRING_FORMAT;
            return this;
        }
    }

    /* loaded from: classes9.dex */
    public static final class SDiff<T> extends Diff<T> {
        private static final long serialVersionUID = 1;
        private final SerializableSupplier<T> leftSupplier;
        private final SerializableSupplier<T> rightSupplier;

        /* synthetic */ SDiff(String x0, SerializableSupplier x1, SerializableSupplier x2, Class x3, AnonymousClass1 x4) {
            this(x0, x1, x2, x3);
        }

        private SDiff(String fieldName, SerializableSupplier<T> leftSupplier, SerializableSupplier<T> rightSupplier, Class<T> type) {
            super(fieldName, type);
            this.leftSupplier = (SerializableSupplier) Objects.requireNonNull(leftSupplier);
            this.rightSupplier = (SerializableSupplier) Objects.requireNonNull(rightSupplier);
        }

        @Override // org.apache.commons.lang3.tuple.Pair
        public T getLeft() {
            return this.leftSupplier.get();
        }

        @Override // org.apache.commons.lang3.tuple.Pair
        public T getRight() {
            return this.rightSupplier.get();
        }
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    @Deprecated
    public DiffBuilder(T left, T right, ToStringStyle style) {
        this(left, right, style, true);
    }

    @Deprecated
    public DiffBuilder(T left, T right, ToStringStyle style, boolean testObjectsEquals) {
        this(left, right, style, testObjectsEquals, TO_STRING_FORMAT);
    }

    private DiffBuilder(T t, T t2, ToStringStyle toStringStyle, boolean z, String str) {
        this.left = (T) Objects.requireNonNull(t, "left");
        this.right = (T) Objects.requireNonNull(t2, "right");
        this.diffs = new ArrayList();
        this.toStringFormat = str;
        this.style = toStringStyle != null ? toStringStyle : ToStringStyle.DEFAULT_STYLE;
        this.equals = z && Objects.equals(t, t2);
    }

    private <F> DiffBuilder<T> add(String fieldName, SerializableSupplier<F> left, SerializableSupplier<F> right, Class<F> type) {
        this.diffs.add(new SDiff(fieldName, left, right, type));
        return this;
    }

    public DiffBuilder<T> append(String fieldName, boolean lhs, boolean rhs) {
        return (this.equals || lhs == rhs) ? this : add(fieldName, new DiffBuilder$$ExternalSyntheticLambda5(lhs), new DiffBuilder$$ExternalSyntheticLambda6(rhs), Boolean.class);
    }

    public DiffBuilder<T> append(String fieldName, boolean[] lhs, boolean[] rhs) {
        return (this.equals || Arrays.equals(lhs, rhs)) ? this : add(fieldName, new DiffBuilder$$ExternalSyntheticLambda24(lhs), new DiffBuilder$$ExternalSyntheticLambda25(rhs), Boolean[].class);
    }

    public DiffBuilder<T> append(String fieldName, byte lhs, byte rhs) {
        return (this.equals || lhs == rhs) ? this : add(fieldName, new DiffBuilder$$ExternalSyntheticLambda15(lhs), new DiffBuilder$$ExternalSyntheticLambda16(rhs), Byte.class);
    }

    public DiffBuilder<T> append(String fieldName, byte[] lhs, byte[] rhs) {
        return (this.equals || Arrays.equals(lhs, rhs)) ? this : add(fieldName, new DiffBuilder$$ExternalSyntheticLambda12(lhs), new DiffBuilder$$ExternalSyntheticLambda13(rhs), Byte[].class);
    }

    public DiffBuilder<T> append(String fieldName, char lhs, char rhs) {
        return (this.equals || lhs == rhs) ? this : add(fieldName, new DiffBuilder$$ExternalSyntheticLambda1(lhs), new DiffBuilder$$ExternalSyntheticLambda2(rhs), Character.class);
    }

    public DiffBuilder<T> append(String fieldName, char[] lhs, char[] rhs) {
        return (this.equals || Arrays.equals(lhs, rhs)) ? this : add(fieldName, new DiffBuilder$$ExternalSyntheticLambda9(lhs), new DiffBuilder$$ExternalSyntheticLambda10(rhs), Character[].class);
    }

    public DiffBuilder<T> append(final String fieldName, DiffResult<?> diffResult) {
        Objects.requireNonNull(diffResult, "diffResult");
        if (this.equals) {
            return this;
        }
        diffResult.getDiffs().forEach(new Consumer() { // from class: org.apache.commons.lang3.builder.DiffBuilder$$ExternalSyntheticLambda14
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DiffBuilder.this.m2184lambda$append$0$orgapachecommonslang3builderDiffBuilder(fieldName, (Diff) obj);
            }
        });
        return this;
    }

    /* renamed from: lambda$append$0$org-apache-commons-lang3-builder-DiffBuilder */
    public /* synthetic */ void m2184lambda$append$0$orgapachecommonslang3builderDiffBuilder(String fieldName, Diff diff) {
        append(fieldName + "." + diff.getFieldName(), diff.getLeft(), diff.getRight());
    }

    public DiffBuilder<T> append(String fieldName, double lhs, double rhs) {
        if (this.equals || Double.doubleToLongBits(lhs) == Double.doubleToLongBits(rhs)) {
            return this;
        }
        return add(fieldName, new DiffBuilder$$ExternalSyntheticLambda31(lhs), new DiffBuilder$$ExternalSyntheticLambda32(rhs), Double.class);
    }

    public DiffBuilder<T> append(String fieldName, double[] lhs, double[] rhs) {
        return (this.equals || Arrays.equals(lhs, rhs)) ? this : add(fieldName, new DiffBuilder$$ExternalSyntheticLambda26(lhs), new DiffBuilder$$ExternalSyntheticLambda27(rhs), Double[].class);
    }

    public DiffBuilder<T> append(String fieldName, float lhs, float rhs) {
        if (this.equals || Float.floatToIntBits(lhs) == Float.floatToIntBits(rhs)) {
            return this;
        }
        return add(fieldName, new DiffBuilder$$ExternalSyntheticLambda33(lhs), new DiffBuilder$$ExternalSyntheticLambda34(rhs), Float.class);
    }

    public DiffBuilder<T> append(String fieldName, float[] lhs, float[] rhs) {
        return (this.equals || Arrays.equals(lhs, rhs)) ? this : add(fieldName, new DiffBuilder$$ExternalSyntheticLambda17(lhs), new DiffBuilder$$ExternalSyntheticLambda18(rhs), Float[].class);
    }

    public DiffBuilder<T> append(String fieldName, int lhs, int rhs) {
        return (this.equals || lhs == rhs) ? this : add(fieldName, new DiffBuilder$$ExternalSyntheticLambda21(lhs), new DiffBuilder$$ExternalSyntheticLambda23(rhs), Integer.class);
    }

    public DiffBuilder<T> append(String fieldName, int[] lhs, int[] rhs) {
        return (this.equals || Arrays.equals(lhs, rhs)) ? this : add(fieldName, new DiffBuilder$$ExternalSyntheticLambda28(lhs), new DiffBuilder$$ExternalSyntheticLambda29(rhs), Integer[].class);
    }

    public DiffBuilder<T> append(String fieldName, long lhs, long rhs) {
        return (this.equals || lhs == rhs) ? this : add(fieldName, new DiffBuilder$$ExternalSyntheticLambda7(lhs), new DiffBuilder$$ExternalSyntheticLambda8(rhs), Long.class);
    }

    public DiffBuilder<T> append(String fieldName, long[] lhs, long[] rhs) {
        return (this.equals || Arrays.equals(lhs, rhs)) ? this : add(fieldName, new DiffBuilder$$ExternalSyntheticLambda3(lhs), new DiffBuilder$$ExternalSyntheticLambda4(rhs), Long[].class);
    }

    public DiffBuilder<T> append(String fieldName, Object lhs, Object rhs) {
        if (this.equals || lhs == rhs) {
            return this;
        }
        Object test = lhs != null ? lhs : rhs;
        if (!ObjectUtils.isArray(test)) {
            return Objects.equals(lhs, rhs) ? this : add(fieldName, new DiffBuilder$$ExternalSyntheticLambda19(lhs), new DiffBuilder$$ExternalSyntheticLambda20(rhs), Object.class);
        }
        if (test instanceof boolean[]) {
            return append(fieldName, (boolean[]) lhs, (boolean[]) rhs);
        }
        if (test instanceof byte[]) {
            return append(fieldName, (byte[]) lhs, (byte[]) rhs);
        }
        if (test instanceof char[]) {
            return append(fieldName, (char[]) lhs, (char[]) rhs);
        }
        if (test instanceof double[]) {
            return append(fieldName, (double[]) lhs, (double[]) rhs);
        }
        if (test instanceof float[]) {
            return append(fieldName, (float[]) lhs, (float[]) rhs);
        }
        if (test instanceof int[]) {
            return append(fieldName, (int[]) lhs, (int[]) rhs);
        }
        if (test instanceof long[]) {
            return append(fieldName, (long[]) lhs, (long[]) rhs);
        }
        if (test instanceof short[]) {
            return append(fieldName, (short[]) lhs, (short[]) rhs);
        }
        return append(fieldName, (Object[]) lhs, (Object[]) rhs);
    }

    public static /* synthetic */ Object lambda$append$9e3d8e65$1(Object lhs) {
        return lhs;
    }

    public static /* synthetic */ Object lambda$append$9e40489f$1(Object rhs) {
        return rhs;
    }

    public static /* synthetic */ Object[] lambda$append$dbd51caa$1(Object[] lhs) {
        return lhs;
    }

    public static /* synthetic */ Object[] lambda$append$dbd7d6e4$1(Object[] rhs) {
        return rhs;
    }

    public DiffBuilder<T> append(String fieldName, Object[] lhs, Object[] rhs) {
        return (this.equals || Arrays.equals(lhs, rhs)) ? this : add(fieldName, new DiffBuilder$$ExternalSyntheticLambda0(lhs), new DiffBuilder$$ExternalSyntheticLambda11(rhs), Object[].class);
    }

    public DiffBuilder<T> append(String fieldName, short lhs, short rhs) {
        return (this.equals || lhs == rhs) ? this : add(fieldName, new DiffBuilder$$ExternalSyntheticLambda35(lhs), new DiffBuilder$$ExternalSyntheticLambda36(rhs), Short.class);
    }

    public DiffBuilder<T> append(String fieldName, short[] lhs, short[] rhs) {
        return (this.equals || Arrays.equals(lhs, rhs)) ? this : add(fieldName, new DiffBuilder$$ExternalSyntheticLambda22(lhs), new DiffBuilder$$ExternalSyntheticLambda30(rhs), Short[].class);
    }

    @Override // org.apache.commons.lang3.builder.Builder
    public DiffResult<T> build() {
        return new DiffResult<>(this.left, this.right, this.diffs, this.style, this.toStringFormat);
    }

    public T getLeft() {
        return this.left;
    }

    public T getRight() {
        return this.right;
    }
}
