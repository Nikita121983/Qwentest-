package org.w3.x2000.x09.xmldsig.impl;

import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes12.dex */
public final /* synthetic */ class TransformTypeImpl$$ExternalSyntheticLambda4 implements Consumer {
    public final /* synthetic */ TransformTypeImpl f$0;

    public /* synthetic */ TransformTypeImpl$$ExternalSyntheticLambda4(TransformTypeImpl transformTypeImpl) {
        this.f$0 = transformTypeImpl;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.f$0.removeXPath(((Integer) obj).intValue());
    }
}
