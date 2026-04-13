package org.apache.commons.collections4.functors;

import java.io.Serializable;
import java.util.Objects;
import org.apache.commons.collections4.Transformer;

/* loaded from: classes9.dex */
public class ConstantTransformer<T, R> implements Transformer<T, R>, Serializable {
    public static final Transformer NULL_INSTANCE = new ConstantTransformer(null);
    private static final long serialVersionUID = 6374440726369055124L;
    private final R iConstant;

    public static <I, O> Transformer<I, O> constantTransformer(O constantToReturn) {
        if (constantToReturn == null) {
            return nullTransformer();
        }
        return new ConstantTransformer(constantToReturn);
    }

    public static <I, O> Transformer<I, O> nullTransformer() {
        return NULL_INSTANCE;
    }

    public ConstantTransformer(R constantToReturn) {
        this.iConstant = constantToReturn;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConstantTransformer)) {
            return false;
        }
        Object otherConstant = ((ConstantTransformer) obj).getConstant();
        return Objects.equals(otherConstant, getConstant());
    }

    public R getConstant() {
        return this.iConstant;
    }

    public int hashCode() {
        int result = "ConstantTransformer".hashCode() << 2;
        if (getConstant() != null) {
            return result | getConstant().hashCode();
        }
        return result;
    }

    @Override // org.apache.commons.collections4.Transformer
    public R transform(T input) {
        return this.iConstant;
    }
}
