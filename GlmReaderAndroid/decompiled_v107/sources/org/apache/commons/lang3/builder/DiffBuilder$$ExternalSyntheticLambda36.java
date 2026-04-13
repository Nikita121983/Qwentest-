package org.apache.commons.lang3.builder;

import java.io.Serializable;
import org.apache.commons.lang3.builder.DiffBuilder;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes9.dex */
public final /* synthetic */ class DiffBuilder$$ExternalSyntheticLambda36 implements DiffBuilder.SerializableSupplier, Serializable {
    public final /* synthetic */ short f$0;

    public /* synthetic */ DiffBuilder$$ExternalSyntheticLambda36(short s) {
        this.f$0 = s;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        Short valueOf;
        valueOf = Short.valueOf(this.f$0);
        return valueOf;
    }
}
