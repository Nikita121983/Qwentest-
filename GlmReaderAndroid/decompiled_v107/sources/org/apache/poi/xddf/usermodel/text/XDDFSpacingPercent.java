package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.text.XDDFSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPercent;

/* loaded from: classes10.dex */
public class XDDFSpacingPercent extends XDDFSpacing {
    private CTTextSpacingPercent percent;
    private Double scale;

    public XDDFSpacingPercent(double value) {
        this(CTTextSpacing.Factory.newInstance(), CTTextSpacingPercent.Factory.newInstance(), null);
        if (this.spacing.isSetSpcPts()) {
            this.spacing.unsetSpcPts();
        }
        this.spacing.setSpcPct(this.percent);
        setPercent(value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFSpacingPercent(CTTextSpacing parent, CTTextSpacingPercent percent, Double scale) {
        super(parent);
        this.percent = percent;
        this.scale = Double.valueOf(scale != null ? 0.001d * scale.doubleValue() : 0.001d);
    }

    @Override // org.apache.poi.xddf.usermodel.text.XDDFSpacing
    public XDDFSpacing.Kind getType() {
        return XDDFSpacing.Kind.PERCENT;
    }

    public double getPercent() {
        return POIXMLUnits.parsePercent(this.percent.xgetVal()) * this.scale.doubleValue();
    }

    public void setPercent(double value) {
        this.percent.setVal(Integer.valueOf((int) (1000.0d * value)));
    }
}
