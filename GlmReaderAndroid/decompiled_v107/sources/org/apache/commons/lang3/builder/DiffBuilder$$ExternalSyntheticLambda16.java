package org.apache.commons.lang3.builder;

import java.io.Serializable;
import org.apache.commons.lang3.builder.DiffBuilder;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes9.dex */
public final /* synthetic */ class DiffBuilder$$ExternalSyntheticLambda16 implements DiffBuilder.SerializableSupplier, Serializable {
    public final /* synthetic */ byte f$0;

    public /* synthetic */ DiffBuilder$$ExternalSyntheticLambda16(byte b) {
        this.f$0 = b;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        Byte valueOf;
        valueOf = Byte.valueOf(this.f$0);
        return valueOf;
    }
}
