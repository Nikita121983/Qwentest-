package org.apache.xmlbeans.impl.xb.xsdownload.impl;

import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes11.dex */
public final /* synthetic */ class DownloadedSchemaEntryImpl$$ExternalSyntheticLambda4 implements Consumer {
    public final /* synthetic */ DownloadedSchemaEntryImpl f$0;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.f$0.removeSchemaLocation(((Integer) obj).intValue());
    }
}
