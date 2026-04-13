package org.apache.commons.lang3.builder;

import java.io.Serializable;
import org.apache.commons.lang3.builder.DiffBuilder;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes9.dex */
public final /* synthetic */ class DiffBuilder$$ExternalSyntheticLambda31 implements DiffBuilder.SerializableSupplier, Serializable {
    public final /* synthetic */ double f$0;

    public /* synthetic */ DiffBuilder$$ExternalSyntheticLambda31(double d) {
        this.f$0 = d;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        Double valueOf;
        valueOf = Double.valueOf(this.f$0);
        return valueOf;
    }
}
