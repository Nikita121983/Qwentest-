package org.apache.poi.xddf.usermodel.text;

import java.util.function.Function;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes10.dex */
public final /* synthetic */ class XDDFTextRun$$ExternalSyntheticLambda23 implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Integer.valueOf(POIXMLUnits.parsePercent((STPercentage) obj));
    }
}
