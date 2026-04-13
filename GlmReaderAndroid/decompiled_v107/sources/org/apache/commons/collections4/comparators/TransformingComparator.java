package org.apache.commons.collections4.comparators;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import org.apache.commons.collections4.ComparatorUtils;
import org.apache.commons.collections4.Transformer;

/* loaded from: classes9.dex */
public class TransformingComparator<I, O> implements Comparator<I>, Serializable {
    private static final long serialVersionUID = 3456940356043606220L;
    private final Comparator<O> decorated;
    private final Transformer<? super I, ? extends O> transformer;

    public TransformingComparator(Transformer<? super I, ? extends O> transformer) {
        this(transformer, ComparatorUtils.NATURAL_COMPARATOR);
    }

    public TransformingComparator(Transformer<? super I, ? extends O> transformer, Comparator<O> decorated) {
        this.decorated = decorated;
        this.transformer = transformer;
    }

    @Override // java.util.Comparator
    public int compare(I obj1, I obj2) {
        O value1 = this.transformer.apply(obj1);
        O value2 = this.transformer.apply(obj2);
        return this.decorated.compare(value1, value2);
    }

    @Override // java.util.Comparator
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || !object.getClass().equals(getClass())) {
            return false;
        }
        TransformingComparator<?, ?> comp = (TransformingComparator) object;
        if (Objects.equals(this.decorated, comp.decorated) && Objects.equals(this.transformer, comp.transformer)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int total = (17 * 37) + (this.decorated == null ? 0 : this.decorated.hashCode());
        return (total * 37) + (this.transformer != null ? this.transformer.hashCode() : 0);
    }
}
