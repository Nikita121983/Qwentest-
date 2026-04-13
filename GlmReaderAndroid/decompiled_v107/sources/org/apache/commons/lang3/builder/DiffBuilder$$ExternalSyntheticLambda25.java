package org.apache.commons.lang3.builder;

import java.io.Serializable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.DiffBuilder;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes9.dex */
public final /* synthetic */ class DiffBuilder$$ExternalSyntheticLambda25 implements DiffBuilder.SerializableSupplier, Serializable {
    public final /* synthetic */ boolean[] f$0;

    public /* synthetic */ DiffBuilder$$ExternalSyntheticLambda25(boolean[] zArr) {
        this.f$0 = zArr;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        Boolean[] object;
        object = ArrayUtils.toObject(this.f$0);
        return object;
    }
}
