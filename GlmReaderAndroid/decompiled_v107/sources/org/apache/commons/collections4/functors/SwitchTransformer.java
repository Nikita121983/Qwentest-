package org.apache.commons.collections4.functors;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;

/* loaded from: classes9.dex */
public class SwitchTransformer<T, R> implements Transformer<T, R>, Serializable {
    private static final long serialVersionUID = -6404460890903469332L;
    private final Transformer<? super T, ? extends R> iDefault;
    private final Predicate<? super T>[] iPredicates;
    private final Transformer<? super T, ? extends R>[] iTransformers;

    public static <I, O> Transformer<I, O> switchTransformer(Map<? extends Predicate<? super I>, ? extends Transformer<? super I, ? extends O>> map) {
        Objects.requireNonNull(map, "map");
        if (map.isEmpty()) {
            return ConstantTransformer.nullTransformer();
        }
        Transformer<? super I, ? extends O> remove = map.remove(null);
        int size = map.size();
        if (size == 0) {
            return remove == null ? ConstantTransformer.nullTransformer() : remove;
        }
        Transformer[] transformerArr = new Transformer[size];
        Predicate[] predicateArr = new Predicate[size];
        int i = 0;
        for (Map.Entry<? extends Predicate<? super I>, ? extends Transformer<? super I, ? extends O>> entry : map.entrySet()) {
            predicateArr[i] = entry.getKey();
            transformerArr[i] = entry.getValue();
            i++;
        }
        return new SwitchTransformer(false, predicateArr, transformerArr, remove);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <I, O> Transformer<I, O> switchTransformer(Predicate<? super I>[] predicates, Transformer<? super I, ? extends O>[] transformers, Transformer<? super I, ? extends O> transformer) {
        FunctorUtils.validate(predicates);
        FunctorUtils.validate(transformers);
        if (predicates.length != transformers.length) {
            throw new IllegalArgumentException("The predicate and transformer arrays must be the same size");
        }
        if (predicates.length == 0) {
            return transformer == 0 ? ConstantTransformer.nullTransformer() : transformer;
        }
        return new SwitchTransformer(predicates, transformers, transformer);
    }

    private SwitchTransformer(boolean clone, Predicate<? super T>[] predicates, Transformer<? super T, ? extends R>[] transformers, Transformer<? super T, ? extends R> defaultTransformer) {
        this.iPredicates = clone ? (Predicate[]) FunctorUtils.copy(predicates) : predicates;
        this.iTransformers = clone ? (Transformer[]) FunctorUtils.copy(transformers) : transformers;
        this.iDefault = defaultTransformer == null ? ConstantTransformer.nullTransformer() : defaultTransformer;
    }

    public SwitchTransformer(Predicate<? super T>[] predicates, Transformer<? super T, ? extends R>[] transformers, Transformer<? super T, ? extends R> defaultTransformer) {
        this(true, predicates, transformers, defaultTransformer);
    }

    public Transformer<? super T, ? extends R> getDefaultTransformer() {
        return this.iDefault;
    }

    public Predicate<? super T>[] getPredicates() {
        return (Predicate[]) FunctorUtils.copy(this.iPredicates);
    }

    public Transformer<? super T, ? extends R>[] getTransformers() {
        return (Transformer[]) FunctorUtils.copy(this.iTransformers);
    }

    @Override // org.apache.commons.collections4.Transformer
    public R transform(T input) {
        for (int i = 0; i < this.iPredicates.length; i++) {
            if (this.iPredicates[i].test(input)) {
                return this.iTransformers[i].apply(input);
            }
        }
        return this.iDefault.apply(input);
    }
}
