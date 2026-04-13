package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayout;

/* loaded from: classes10.dex */
public class XDDFLayout {
    private CTLayout layout;

    public XDDFLayout() {
        this(CTLayout.Factory.newInstance());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFLayout(CTLayout layout) {
        this.layout = layout;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public CTLayout getXmlObject() {
        return this.layout;
    }

    public void setExtensionList(XDDFChartExtensionList list) {
        if (list == null) {
            if (this.layout.isSetExtLst()) {
                this.layout.unsetExtLst();
                return;
            }
            return;
        }
        this.layout.setExtLst(list.getXmlObject());
    }

    public XDDFChartExtensionList getExtensionList() {
        if (this.layout.isSetExtLst()) {
            return new XDDFChartExtensionList(this.layout.getExtLst());
        }
        return null;
    }

    public void setManualLayout(XDDFManualLayout manual) {
        if (manual == null) {
            if (this.layout.isSetManualLayout()) {
                this.layout.unsetManualLayout();
                return;
            }
            return;
        }
        this.layout.setManualLayout(manual.getXmlObject());
    }

    public XDDFManualLayout getManualLayout() {
        if (this.layout.isSetManualLayout()) {
            return new XDDFManualLayout(this.layout);
        }
        return null;
    }
}
