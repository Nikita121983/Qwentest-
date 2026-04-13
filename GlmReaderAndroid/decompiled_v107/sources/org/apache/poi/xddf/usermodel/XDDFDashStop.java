package org.apache.poi.xddf.usermodel;

import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDashStop;

/* loaded from: classes10.dex */
public class XDDFDashStop {
    private CTDashStop stop;

    /* JADX INFO: Access modifiers changed from: protected */
    @Internal
    public XDDFDashStop(CTDashStop stop) {
        this.stop = stop;
    }

    @Internal
    protected CTDashStop getXmlObject() {
        return this.stop;
    }

    public int getDashLength() {
        return POIXMLUnits.parsePercent(this.stop.xgetD());
    }

    public void setDashLength(int length) {
        this.stop.setD(Integer.valueOf(length));
    }

    public int getSpaceLength() {
        return POIXMLUnits.parsePercent(this.stop.xgetSp());
    }

    public void setSpaceLength(int length) {
        this.stop.setSp(Integer.valueOf(length));
    }
}
