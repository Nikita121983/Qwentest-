package org.apache.commons.collections4.functors;

import java.io.Serializable;
import java.util.Objects;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;

/* loaded from: classes9.dex */
public class IfTransformer<T, R> implements Transformer<T, R>, Serializable {
    private static final long serialVersionUID = 8069309411242014252L;
    private final Transformer<? super T, ? extends R> iFalseTransformer;
    private final Predicate<? super T> iPredicate;
    private final Transformer<? super T, ? extends R> iTrueTransformer;

    public static <I, O> Transformer<I, O> ifTransformer(Predicate<? super I> predicate, Transformer<? super I, ? extends O> trueTransformer, Transformer<? super I, ? extends O> falseTransformer) {
        return new IfTransformer((Predicate) Objects.requireNonNull(predicate, "predicate"), (Transformer) Objects.requireNonNull(trueTransformer, "trueTransformer"), (Transformer) Objects.requireNonNull(falseTransformer, "falseTransformer"));
    }

    public static <T> Transformer<T, T> ifTransformer(Predicate<? super T> predicate, Transformer<? super T, ? extends T> trueTransformer) {
        return new IfTransformer((Predicate) Objects.requireNonNull(predicate, "predicate"), (Transformer) Objects.requireNonNull(trueTransformer, "trueTransformer"), NOPTransformer.nopTransformer());
    }

    public IfTransformer(Predicate<? super T> predicate, Transformer<? super T, ? extends R> trueTransformer, Transformer<? super T, ? extends R> falseTransformer) {
        this.iPredicate = predicate;
        this.iTrueTransformer = trueTransformer;
        this.iFalseTransformer = falseTransformer;
    }

    public Transformer<? super T, ? extends R> getFalseTransformer() {
        return this.iFalseTransformer;
    }

    public Predicate<? super T> getPredicate() {
        return this.iPredicate;
    }

    public Transformer<? super T, ? extends R> getTrueTransformer() {
        return this.iTrueTransformer;
    }

    @Override // org.apache.commons.collections4.Transformer
    public R transform(T input) {
        if (this.iPredicate.test(input)) {
            return this.iTrueTransformer.apply(input);
        }
        return this.iFalseTransformer.apply(input);
    }
}
