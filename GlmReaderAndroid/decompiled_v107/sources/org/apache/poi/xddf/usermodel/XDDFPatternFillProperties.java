package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;

/* loaded from: classes10.dex */
public class XDDFPatternFillProperties implements XDDFFillProperties {
    private CTPatternFillProperties props;

    public XDDFPatternFillProperties() {
        this(CTPatternFillProperties.Factory.newInstance());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFPatternFillProperties(CTPatternFillProperties properties) {
        this.props = properties;
    }

    @Internal
    public CTPatternFillProperties getXmlObject() {
        return this.props;
    }

    public PresetPattern getPresetPattern() {
        if (this.props.isSetPrst()) {
            return PresetPattern.valueOf(this.props.getPrst());
        }
        return null;
    }

    public void setPresetPattern(PresetPattern pattern) {
        if (pattern == null) {
            if (this.props.isSetPrst()) {
                this.props.unsetPrst();
                return;
            }
            return;
        }
        this.props.setPrst(pattern.underlying);
    }

    public XDDFColor getBackgroundColor() {
        if (this.props.isSetBgClr()) {
            return XDDFColor.forColorContainer(this.props.getBgClr());
        }
        return null;
    }

    public void setBackgroundColor(XDDFColor color) {
        if (color == null) {
            if (this.props.isSetBgClr()) {
                this.props.unsetBgClr();
                return;
            }
            return;
        }
        this.props.setBgClr(color.getColorContainer());
    }

    public XDDFColor getForegroundColor() {
        if (this.props.isSetFgClr()) {
            return XDDFColor.forColorContainer(this.props.getFgClr());
        }
        return null;
    }

    public void setForegroundColor(XDDFColor color) {
        if (color == null) {
            if (this.props.isSetFgClr()) {
                this.props.unsetFgClr();
                return;
            }
            return;
        }
        this.props.setFgClr(color.getColorContainer());
    }
}
