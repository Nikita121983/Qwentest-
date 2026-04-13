package org.apache.commons.lang3.builder;

import java.io.Serializable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.DiffBuilder;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes9.dex */
public final /* synthetic */ class DiffBuilder$$ExternalSyntheticLambda17 implements DiffBuilder.SerializableSupplier, Serializable {
    public final /* synthetic */ float[] f$0;

    public /* synthetic */ DiffBuilder$$ExternalSyntheticLambda17(float[] fArr) {
        this.f$0 = fArr;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        Float[] object;
        object = ArrayUtils.toObject(this.f$0);
        return object;
    }
}
