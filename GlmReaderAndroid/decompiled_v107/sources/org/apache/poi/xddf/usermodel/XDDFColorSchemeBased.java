package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;

/* loaded from: classes10.dex */
public class XDDFColorSchemeBased extends XDDFColor {
    private CTSchemeColor color;

    public XDDFColorSchemeBased(SchemeColor color) {
        this(CTSchemeColor.Factory.newInstance(), CTColor.Factory.newInstance());
        setValue(color);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFColorSchemeBased(CTSchemeColor color) {
        this(color, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFColorSchemeBased(CTSchemeColor color, CTColor container) {
        super(container);
        this.color = color;
    }

    @Override // org.apache.poi.xddf.usermodel.XDDFColor
    @Internal
    protected XmlObject getXmlObject() {
        return this.color;
    }

    public SchemeColor getValue() {
        return SchemeColor.valueOf(this.color.getVal());
    }

    public void setValue(SchemeColor scheme) {
        this.color.setVal(scheme.underlying);
    }
}
