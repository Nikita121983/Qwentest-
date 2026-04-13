package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;

/* loaded from: classes10.dex */
public class XDDFChartExtensionList {
    private CTExtensionList list;

    public XDDFChartExtensionList() {
        this(CTExtensionList.Factory.newInstance());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFChartExtensionList(CTExtensionList list) {
        this.list = list;
    }

    @Internal
    public CTExtensionList getXmlObject() {
        return this.list;
    }
}
