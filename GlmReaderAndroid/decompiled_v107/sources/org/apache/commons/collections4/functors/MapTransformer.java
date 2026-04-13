package org.apache.commons.collections4.functors;

import java.io.Serializable;
import java.util.Map;
import org.apache.commons.collections4.Transformer;

/* loaded from: classes9.dex */
public final class MapTransformer<T, R> implements Transformer<T, R>, Serializable {
    private static final long serialVersionUID = 862391807045468939L;
    private final Map<? super T, ? extends R> iMap;

    public static <I, O> Transformer<I, O> mapTransformer(Map<? super I, ? extends O> map) {
        if (map == null) {
            return ConstantTransformer.nullTransformer();
        }
        return new MapTransformer(map);
    }

    private MapTransformer(Map<? super T, ? extends R> map) {
        this.iMap = map;
    }

    public Map<? super T, ? extends R> getMap() {
        return this.iMap;
    }

    @Override // org.apache.commons.collections4.Transformer
    public R transform(T input) {
        return this.iMap.get(input);
    }
}
