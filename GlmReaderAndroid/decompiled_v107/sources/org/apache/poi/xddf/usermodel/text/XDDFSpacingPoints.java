package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.text.XDDFSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPoint;

/* loaded from: classes10.dex */
public class XDDFSpacingPoints extends XDDFSpacing {
    private CTTextSpacingPoint points;

    public XDDFSpacingPoints(double value) {
        this(CTTextSpacing.Factory.newInstance(), CTTextSpacingPoint.Factory.newInstance());
        if (this.spacing.isSetSpcPct()) {
            this.spacing.unsetSpcPct();
        }
        this.spacing.setSpcPts(this.points);
        setPoints(value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFSpacingPoints(CTTextSpacing parent, CTTextSpacingPoint points) {
        super(parent);
        this.points = points;
    }

    @Override // org.apache.poi.xddf.usermodel.text.XDDFSpacing
    public XDDFSpacing.Kind getType() {
        return XDDFSpacing.Kind.POINTS;
    }

    public double getPoints() {
        return this.points.getVal() * 0.01d;
    }

    public void setPoints(double value) {
        this.points.setVal((int) (100.0d * value));
    }
}
