package org.apache.commons.compress.archivers.zip;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes9.dex */
public final /* synthetic */ class ZipSplitReadOnlySeekableByteChannel$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ List f$0;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.f$0.add((Path) obj);
    }
}
