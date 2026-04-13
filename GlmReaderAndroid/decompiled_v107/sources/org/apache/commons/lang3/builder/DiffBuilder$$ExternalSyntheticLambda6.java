package org.apache.commons.lang3.builder;

import java.io.Serializable;
import org.apache.commons.lang3.builder.DiffBuilder;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes9.dex */
public final /* synthetic */ class DiffBuilder$$ExternalSyntheticLambda6 implements DiffBuilder.SerializableSupplier, Serializable {
    public final /* synthetic */ boolean f$0;

    public /* synthetic */ DiffBuilder$$ExternalSyntheticLambda6(boolean z) {
        this.f$0 = z;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        Boolean valueOf;
        valueOf = Boolean.valueOf(this.f$0);
        return valueOf;
    }
}
