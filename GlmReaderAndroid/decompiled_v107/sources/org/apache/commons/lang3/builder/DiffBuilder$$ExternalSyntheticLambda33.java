package org.apache.commons.lang3.builder;

import java.io.Serializable;
import org.apache.commons.lang3.builder.DiffBuilder;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes9.dex */
public final /* synthetic */ class DiffBuilder$$ExternalSyntheticLambda33 implements DiffBuilder.SerializableSupplier, Serializable {
    public final /* synthetic */ float f$0;

    public /* synthetic */ DiffBuilder$$ExternalSyntheticLambda33(float f) {
        this.f$0 = f;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        Float valueOf;
        valueOf = Float.valueOf(this.f$0);
        return valueOf;
    }
}
