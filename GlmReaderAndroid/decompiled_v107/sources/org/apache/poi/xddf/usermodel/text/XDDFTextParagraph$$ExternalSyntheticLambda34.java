package org.apache.poi.xddf.usermodel.text;

import java.util.function.Function;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes10.dex */
public final /* synthetic */ class XDDFTextParagraph$$ExternalSyntheticLambda34 implements Function {
    public final /* synthetic */ XDDFTextParagraph f$0;

    public /* synthetic */ XDDFTextParagraph$$ExternalSyntheticLambda34(XDDFTextParagraph xDDFTextParagraph) {
        this.f$0 = xDDFTextParagraph;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        XDDFSpacing extractSpacing;
        extractSpacing = this.f$0.extractSpacing((CTTextSpacing) obj);
        return extractSpacing;
    }
}
