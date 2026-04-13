package org.apache.poi.ddf;

import java.util.List;
import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes10.dex */
public final /* synthetic */ class EscherContainerRecord$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ List f$0;

    public /* synthetic */ EscherContainerRecord$$ExternalSyntheticLambda2(List list) {
        this.f$0 = list;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.f$0.add((EscherRecord) obj);
    }
}
