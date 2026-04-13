package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetLineDashProperties;

/* loaded from: classes10.dex */
public class XDDFPresetLineDash {
    private CTPresetLineDashProperties props;

    public XDDFPresetLineDash(PresetLineDash dash) {
        this(CTPresetLineDashProperties.Factory.newInstance());
        setValue(dash);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XDDFPresetLineDash(CTPresetLineDashProperties properties) {
        this.props = properties;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTPresetLineDashProperties getXmlObject() {
        return this.props;
    }

    public PresetLineDash getValue() {
        if (this.props.isSetVal()) {
            return PresetLineDash.valueOf(this.props.getVal());
        }
        return null;
    }

    public void setValue(PresetLineDash dash) {
        if (dash == null) {
            if (this.props.isSetVal()) {
                this.props.unsetVal();
                return;
            }
            return;
        }
        this.props.setVal(dash.underlying);
    }
}
