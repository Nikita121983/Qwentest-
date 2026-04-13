package org.apache.commons.collections4.functors;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.commons.collections4.CollectionUtils;

/* loaded from: classes9.dex */
final class FunctorUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <R extends Predicate<T>, P extends Predicate<? super T>, T> R coerce(P p) {
        return p;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <R extends Function<I, O>, P extends Function<? super I, ? extends O>, I, O> R coerce(P p) {
        return p;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends Consumer<?>> T[] copy(T... tArr) {
        if (tArr == null) {
            return null;
        }
        return (T[]) ((Consumer[]) tArr.clone());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends Predicate<?>> T[] copy(T... tArr) {
        if (tArr == null) {
            return null;
        }
        return (T[]) ((Predicate[]) tArr.clone());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends Function<?, ?>> T[] copy(T... tArr) {
        if (tArr == null) {
            return null;
        }
        return (T[]) ((Function[]) tArr.clone());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> org.apache.commons.collections4.Predicate<? super T>[] validate(Collection<? extends Predicate<? super T>> predicates) {
        Objects.requireNonNull(predicates, "predicates");
        org.apache.commons.collections4.Predicate<? super T>[] preds = new org.apache.commons.collections4.Predicate[predicates.size()];
        int i = 0;
        for (Predicate<? super T> predicate : predicates) {
            preds[i] = (org.apache.commons.collections4.Predicate) predicate;
            if (preds[i] == null) {
                throw new NullPointerException("predicates[" + i + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            }
            i++;
        }
        return preds;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validate(Consumer<?>... consumers) {
        Objects.requireNonNull(consumers, "closures");
        for (int i = 0; i < consumers.length; i++) {
            if (consumers[i] == null) {
                throw new NullPointerException("closures[" + i + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validate(Function<?, ?>... functions) {
        Objects.requireNonNull(functions, "functions");
        for (int i = 0; i < functions.length; i++) {
            if (functions[i] == null) {
                throw new NullPointerException("functions[" + i + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validate(Predicate<?>... predicates) {
        Objects.requireNonNull(predicates, "predicates");
        for (int i = 0; i < predicates.length; i++) {
            if (predicates[i] == null) {
                throw new NullPointerException("predicates[" + i + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            }
        }
    }

    private FunctorUtils() {
    }
}
