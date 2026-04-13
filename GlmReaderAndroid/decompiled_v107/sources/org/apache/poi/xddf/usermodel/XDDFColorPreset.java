package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor;

/* loaded from: classes10.dex */
public class XDDFColorPreset extends XDDFColor {
    private CTPresetColor color;

    public XDDFColorPreset(PresetColor color) {
        this(CTPresetColor.Factory.newInstance(), CTColor.Factory.newInstance());
        setValue(color);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFColorPreset(CTPresetColor color) {
        this(color, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFColorPreset(CTPresetColor color, CTColor container) {
        super(container);
        this.color = color;
    }

    @Override // org.apache.poi.xddf.usermodel.XDDFColor
    @Internal
    protected XmlObject getXmlObject() {
        return this.color;
    }

    public PresetColor getValue() {
        if (this.color.xgetVal() != null) {
            return PresetColor.valueOf(this.color.getVal());
        }
        return null;
    }

    public void setValue(PresetColor value) {
        if (value == null) {
            if (this.color.xgetVal() != null) {
                this.color.setVal(PresetColor.WHITE.underlying);
                return;
            }
            return;
        }
        this.color.setVal(value.underlying);
    }
}
