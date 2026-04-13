package org.apache.poi.ss.util;

import java.util.function.Function;
import org.apache.poi.ss.usermodel.Sheet;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes10.dex */
public final /* synthetic */ class ImageUtils$$ExternalSyntheticLambda2 implements Function {
    public final /* synthetic */ Sheet f$0;

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Float.valueOf(this.f$0.getColumnWidthInPixels(((Integer) obj).intValue()));
    }
}
