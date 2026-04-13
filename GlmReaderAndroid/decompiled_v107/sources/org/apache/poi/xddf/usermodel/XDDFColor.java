package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;

/* loaded from: classes10.dex */
public abstract class XDDFColor {
    protected CTColor container;

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public abstract XmlObject getXmlObject();

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFColor(CTColor container) {
        this.container = container;
    }

    public static XDDFColor from(byte[] color) {
        return new XDDFColorRgbBinary(color);
    }

    public static XDDFColor from(int red, int green, int blue) {
        return new XDDFColorRgbPercent(red, green, blue);
    }

    public static XDDFColor from(PresetColor color) {
        return new XDDFColorPreset(color);
    }

    public static XDDFColor from(SchemeColor color) {
        return new XDDFColorSchemeBased(color);
    }

    public static XDDFColor from(SystemColor color) {
        return new XDDFColorSystemDefined(color);
    }

    @Internal
    public static XDDFColor forColorContainer(CTColor container) {
        if (container.isSetHslClr()) {
            return new XDDFColorHsl(container.getHslClr(), container);
        }
        if (container.isSetPrstClr()) {
            return new XDDFColorPreset(container.getPrstClr(), container);
        }
        if (container.isSetSchemeClr()) {
            return new XDDFColorSchemeBased(container.getSchemeClr(), container);
        }
        if (container.isSetScrgbClr()) {
            return new XDDFColorRgbPercent(container.getScrgbClr(), container);
        }
        if (container.isSetSrgbClr()) {
            return new XDDFColorRgbBinary(container.getSrgbClr(), container);
        }
        if (container.isSetSysClr()) {
            return new XDDFColorSystemDefined(container.getSysClr(), container);
        }
        return null;
    }

    @Internal
    public CTColor getColorContainer() {
        return this.container;
    }
}
