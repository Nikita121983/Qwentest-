package org.apache.commons.lang3.reflect;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes9.dex */
public final /* synthetic */ class MethodUtils$$ExternalSyntheticLambda12 implements Consumer {
    public final /* synthetic */ List f$0;

    public /* synthetic */ MethodUtils$$ExternalSyntheticLambda12(List list) {
        this.f$0 = list;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.f$0.add((Method) obj);
    }
}
