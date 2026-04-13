package org.apache.poi.xddf.usermodel;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSystemColor;

/* loaded from: classes10.dex */
public class XDDFGradientStop {
    private CTGradientStop stop;

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFGradientStop(CTGradientStop stop) {
        this.stop = stop;
    }

    @Internal
    protected CTGradientStop getXmlObject() {
        return this.stop;
    }

    public int getPosition() {
        return POIXMLUnits.parsePercent(this.stop.xgetPos());
    }

    public void setPosition(int position) {
        this.stop.setPos(Integer.valueOf(position));
    }

    public XDDFColor getColor() {
        if (this.stop.isSetHslClr()) {
            return new XDDFColorHsl(this.stop.getHslClr());
        }
        if (this.stop.isSetPrstClr()) {
            return new XDDFColorPreset(this.stop.getPrstClr());
        }
        if (this.stop.isSetSchemeClr()) {
            return new XDDFColorSchemeBased(this.stop.getSchemeClr());
        }
        if (this.stop.isSetScrgbClr()) {
            return new XDDFColorRgbPercent(this.stop.getScrgbClr());
        }
        if (this.stop.isSetSrgbClr()) {
            return new XDDFColorRgbBinary(this.stop.getSrgbClr());
        }
        if (this.stop.isSetSysClr()) {
            return new XDDFColorSystemDefined(this.stop.getSysClr());
        }
        return null;
    }

    public void setColor(XDDFColor color) {
        if (this.stop.isSetHslClr()) {
            this.stop.unsetHslClr();
        }
        if (this.stop.isSetPrstClr()) {
            this.stop.unsetPrstClr();
        }
        if (this.stop.isSetSchemeClr()) {
            this.stop.unsetSchemeClr();
        }
        if (this.stop.isSetScrgbClr()) {
            this.stop.unsetScrgbClr();
        }
        if (this.stop.isSetSrgbClr()) {
            this.stop.unsetSrgbClr();
        }
        if (this.stop.isSetSysClr()) {
            this.stop.unsetSysClr();
        }
        if (color == null) {
            return;
        }
        if (color instanceof XDDFColorHsl) {
            this.stop.setHslClr((CTHslColor) color.getXmlObject());
            return;
        }
        if (color instanceof XDDFColorPreset) {
            this.stop.setPrstClr((CTPresetColor) color.getXmlObject());
            return;
        }
        if (color instanceof XDDFColorSchemeBased) {
            this.stop.setSchemeClr((CTSchemeColor) color.getXmlObject());
            return;
        }
        if (color instanceof XDDFColorRgbPercent) {
            this.stop.setScrgbClr((CTScRgbColor) color.getXmlObject());
        } else if (color instanceof XDDFColorRgbBinary) {
            this.stop.setSrgbClr((CTSRgbColor) color.getXmlObject());
        } else if (color instanceof XDDFColorSystemDefined) {
            this.stop.setSysClr((CTSystemColor) color.getXmlObject());
        }
    }
}
