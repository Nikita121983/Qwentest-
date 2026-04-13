package org.apache.commons.lang3.builder;

import java.io.Serializable;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.DiffBuilder;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes9.dex */
public final /* synthetic */ class DiffBuilder$$ExternalSyntheticLambda12 implements DiffBuilder.SerializableSupplier, Serializable {
    public final /* synthetic */ byte[] f$0;

    public /* synthetic */ DiffBuilder$$ExternalSyntheticLambda12(byte[] bArr) {
        this.f$0 = bArr;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        Byte[] object;
        object = ArrayUtils.toObject(this.f$0);
        return object;
    }
}
