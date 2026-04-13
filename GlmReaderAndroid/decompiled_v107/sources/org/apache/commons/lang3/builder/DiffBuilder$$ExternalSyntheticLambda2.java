package org.apache.commons.lang3.builder;

import java.io.Serializable;
import org.apache.commons.lang3.builder.DiffBuilder;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes9.dex */
public final /* synthetic */ class DiffBuilder$$ExternalSyntheticLambda2 implements DiffBuilder.SerializableSupplier, Serializable {
    public final /* synthetic */ char f$0;

    public /* synthetic */ DiffBuilder$$ExternalSyntheticLambda2(char c) {
        this.f$0 = c;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        Character valueOf;
        valueOf = Character.valueOf(this.f$0);
        return valueOf;
    }
}
