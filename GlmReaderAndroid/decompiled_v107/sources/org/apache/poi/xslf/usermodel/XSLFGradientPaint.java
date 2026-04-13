package org.apache.poi.xslf.usermodel;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.sl.usermodel.ColorStyle;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.STPathShadeType;

@Internal
/* loaded from: classes10.dex */
public class XSLFGradientPaint implements PaintStyle.GradientPaint {
    final ColorStyle[] cs;
    final float[] fractions;
    private final CTGradientFillProperties gradFill;

    public XSLFGradientPaint(CTGradientFillProperties gradFill, CTSchemeColor phClr, XSLFTheme theme, XSLFSheet sheet) {
        this.gradFill = gradFill;
        CTGradientStop[] gs = gradFill.getGsLst() == null ? new CTGradientStop[0] : gradFill.getGsLst().getGsArray();
        Arrays.sort(gs, new Comparator() { // from class: org.apache.poi.xslf.usermodel.XSLFGradientPaint$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return XSLFGradientPaint.lambda$new$0((CTGradientStop) obj, (CTGradientStop) obj2);
            }
        });
        this.cs = new ColorStyle[gs.length];
        this.fractions = new float[gs.length];
        int i = 0;
        for (CTGradientStop cgs : gs) {
            CTSchemeColor phClrCgs = phClr;
            if (phClrCgs == null && cgs.isSetSchemeClr()) {
                phClrCgs = cgs.getSchemeClr();
            }
            this.cs[i] = new XSLFColor(cgs, theme, phClrCgs, sheet).getColorStyle();
            this.fractions[i] = POIXMLUnits.parsePercent(cgs.xgetPos()) / 100000.0f;
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$new$0(CTGradientStop o1, CTGradientStop o2) {
        int pos1 = POIXMLUnits.parsePercent(o1.xgetPos());
        int pos2 = POIXMLUnits.parsePercent(o2.xgetPos());
        return Integer.compare(pos1, pos2);
    }

    @Override // org.apache.poi.sl.usermodel.PaintStyle.GradientPaint
    public double getGradientAngle() {
        if (this.gradFill.isSetLin()) {
            return this.gradFill.getLin().getAng() / 60000.0d;
        }
        return 0.0d;
    }

    @Override // org.apache.poi.sl.usermodel.PaintStyle.GradientPaint
    public ColorStyle[] getGradientColors() {
        return this.cs;
    }

    @Override // org.apache.poi.sl.usermodel.PaintStyle.GradientPaint
    public float[] getGradientFractions() {
        return this.fractions;
    }

    @Override // org.apache.poi.sl.usermodel.PaintStyle.GradientPaint
    public boolean isRotatedWithShape() {
        return this.gradFill.getRotWithShape();
    }

    @Override // org.apache.poi.sl.usermodel.PaintStyle.GradientPaint
    public PaintStyle.GradientPaint.GradientType getGradientType() {
        if (this.gradFill.isSetLin()) {
            return PaintStyle.GradientPaint.GradientType.linear;
        }
        if (this.gradFill.isSetPath()) {
            STPathShadeType.Enum ps = this.gradFill.getPath().getPath();
            if (ps == STPathShadeType.CIRCLE) {
                return PaintStyle.GradientPaint.GradientType.circular;
            }
            if (ps == STPathShadeType.SHAPE) {
                return PaintStyle.GradientPaint.GradientType.shape;
            }
            if (ps == STPathShadeType.RECT) {
                return PaintStyle.GradientPaint.GradientType.rectangular;
            }
        }
        return PaintStyle.GradientPaint.GradientType.linear;
    }

    @Override // org.apache.poi.sl.usermodel.PaintStyle.GradientPaint
    public Insets2D getFillToInsets() {
        if (this.gradFill.isSetPath() && this.gradFill.getPath().isSetFillToRect()) {
            CTRelativeRect rect = this.gradFill.getPath().getFillToRect();
            return new Insets2D(POIXMLUnits.parsePercent(rect.xgetT()) / 100000.0d, POIXMLUnits.parsePercent(rect.xgetL()) / 100000.0d, POIXMLUnits.parsePercent(rect.xgetB()) / 100000.0d, POIXMLUnits.parsePercent(rect.xgetR()) / 100000.0d);
        }
        return null;
    }
}
